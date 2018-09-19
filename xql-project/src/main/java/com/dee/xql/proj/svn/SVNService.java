package com.dee.xql.proj.svn;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.wc.ISVNDiffStatusHandler;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNDiffStatus;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatusType;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import com.dee.xql.api.utils.FileHelper;
import com.dee.xql.proj.config.CommonsConfig;
import com.dee.xql.proj.enums.ProjectFileType;
import com.dee.xql.proj.service.DJService;
import com.dee.xql.proj.service.GGService;
import com.dee.xql.proj.service.LampsService;
import com.dee.xql.proj.service.MppService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SVNService {

	@Autowired
	private CommonsConfig commonsConfig;

	@Autowired
	private MppService mppService;
	@Autowired
	private DJService djService;
	@Autowired
	private GGService ggService;
	@Autowired
	private LampsService lampsService;

	private SVNURL URL;
	private SVNRevision startingRevision;
	private SVNRevision endingRevision;
	private ISVNAuthenticationManager authManager;
	private HashSet<SVNDiffStatus> changes = new HashSet<>();
	private List<String> n_paths = new ArrayList<>();
	private int curr_path_idx = 0;

	public void setup() {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
	}

	public synchronized void initSVN() {
		DAVRepositoryFactory.setup();
		try {
			URL = SVNURL.parseURIEncoded(commonsConfig.getSvn_url());
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.changes = new HashSet<>();
		this.n_paths = new ArrayList<>();
		this.authManager = SVNWCUtil.createDefaultAuthenticationManager(commonsConfig.getSvn_username(),
				commonsConfig.getSvn_password().toCharArray());
		this.startingRevision = this.getCurrentRevision();
		this.endingRevision = this.getLastRevision();
		log.info("Curr SVN Version: " + this.startingRevision);
		log.info("Last SVN Version: " + this.endingRevision);
		try {
			start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void start() throws SVNException {
		ImplISVNDiffStatusHandler handler = new ImplISVNDiffStatusHandler();
		SVNDiffClient diffClient = new SVNDiffClient(authManager, null);
		diffClient.doDiffStatus(this.URL, this.startingRevision, this.URL, this.endingRevision, SVNDepth.INFINITY,
				false, handler);
		SVNUpdateClient updateClient = new SVNUpdateClient(authManager, SVNWCUtil.createDefaultOptions(true));
		Iterator<SVNDiffStatus> it = changes.iterator();
		while (it.hasNext()) {
			SVNDiffStatus change = it.next();
			log.info(commonsConfig.getSvn_export_path() + change.getPath());
			File destination = new File(commonsConfig.getSvn_export_path() + change.getPath());
			String fileType = FileHelper.getFileSuffix(destination.getName());
			log.info("File Type：" + fileType);
			if (fileType != null && fileType.equals(".mpp")) {
				n_paths.add(commonsConfig.getSvn_export_path() + change.getPath());
			}
			updateClient.doExport(change.getURL(), destination, this.endingRevision, this.endingRevision, null, true,
					SVNDepth.INFINITY);
		}
		saveData();
	}

	/**
	 * 获得最新版本号
	 *
	 * @return SVNRevision
	 */
	private SVNRevision getLastRevision() {
		SVNWCClient wcClient = new SVNWCClient(authManager, null);
		SVNInfo info = null;
		try {
			info = wcClient.doInfo(this.URL, SVNRevision.HEAD, SVNRevision.HEAD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info.getCommittedRevision();
	}

	private SVNRevision getCurrentRevision() {
		String revision = null;
		try {
			revision = getFileText(commonsConfig.getSvn_version_file());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SVNRevision.create(Long.parseLong(revision));
	}

	/**
	 * 读取文件内容
	 *
	 * @param path 文件路径
	 * @return 文件内容
	 * @throws Exception
	 */
	private String getFileText(String path) throws Exception {
		File file = new File(path);
		if (!file.exists() || file.isDirectory()) {
			file.createNewFile();
			setFileText(path, "0");
		}
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[1024];
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len;
		while ((len = fis.read(buf)) != -1) {
			baos.write(buf, 0, len);
		}
		fis.close();
		return baos.toString();
	}

	/**
	 * 写入文件内容
	 *
	 * @param path 文件路径
	 * @param text 内容
	 */
	private void setFileText(String path, String text) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File(path));
			out.write(text.getBytes());
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveData() {
		try {
			if (n_paths.size() > 0) {
				curr_path_idx = 0;
				saveProjData();
			}
			setFileText(commonsConfig.getSvn_version_file(), endingRevision.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private synchronized void saveProjData() {
		try {
			String path = n_paths.get(curr_path_idx);
//			String resPath = commonsConfig.getSvn_export_path() + commonsConfig.getSvn_resource_dir();
//			String djPath = commonsConfig.getSvn_export_path() + commonsConfig.getSvn_dj_dir();
			log.info("saveProjData curr idx: " + curr_path_idx + ", size:" + n_paths.size());
			if (path.contains(commonsConfig.getSvn_resource_dir())) {// 保存资源库数据
				mppService.saveData(path, ProjectFileType.MPP_RESOURCE, this.getLastRevision().getNumber());
			} else if (path.contains(commonsConfig.getSvn_dj_dir())) {
				djService.saveData(path, this.getLastRevision().getNumber());
			} else if (path.contains(commonsConfig.getSvn_gg_dir())) {
				ggService.saveData(path, this.getLastRevision().getNumber());
			} else if (path.contains(commonsConfig.getSvn_dq_dir())) {
				lampsService.saveData(path, this.getLastRevision().getNumber());
			} else { // 保存任务数据
				mppService.saveData(path, ProjectFileType.MPP_TASK, this.getLastRevision().getNumber());
			}
			curr_path_idx += 1;
			if (curr_path_idx < n_paths.size()) {
				saveProjData();
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("saveProjData Exception: ", e);
		}

	}

	private class ImplISVNDiffStatusHandler implements ISVNDiffStatusHandler {
		@Override
		public void handleDiffStatus(SVNDiffStatus status) throws SVNException {
			SVNStatusType type = status.getModificationType();
			if (status.getKind() == SVNNodeKind.FILE
					&& (type == SVNStatusType.STATUS_ADDED || type == SVNStatusType.STATUS_MODIFIED)) {
				Gson gson = new Gson();
				log.info(gson.toJson(status));
				changes.add(status);
			}
		}
	}
}
