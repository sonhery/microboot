package com.dee.xql.api.utils;

import java.io.File;

import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNStatus;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SVNHelper {

	/**
	 * 通过不同的协议初始化版本库
	 */
	public static void setup() {
		DAVRepositoryFactory.setup();
		SVNRepositoryFactoryImpl.setup();
		FSRepositoryFactory.setup();
	}

	/**
	 * 验证登陆svn
	 * 
	 * @param svnRoot  svn的跟路径
	 * @param username svn用户名
	 * @param password svn密码
	 * @return
	 */
	public static SVNClientManager authSvn(String svnRoot, String username, String password) {
		setup();
		SVNRepository repository = null;
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(svnRoot));
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper authSvn Error", e);
			return null;
		}
		// 身份验证
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,
				password.toCharArray());
		// 创建身份管理器
		repository.setAuthenticationManager(authManager);
		DefaultSVNOptions options = SVNWCUtil.createDefaultOptions(true);
		SVNClientManager clientManager = SVNClientManager.newInstance(options, authManager);
		return clientManager;
	}

	/**
	 * 创建文件夹
	 * 
	 * @param clientManager
	 * @param url
	 * @param commitMsg
	 * @return
	 */
	public static SVNCommitInfo mkdir(SVNClientManager clientManager, SVNURL url, String commitMsg) {
		try {
			return clientManager.getCommitClient().doMkDir(new SVNURL[] { url }, commitMsg);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper mkdir Error", e);
			return null;
		}
	}

	/**
	 * 导入文件夹
	 * 
	 * @param clientManager
	 * @param localPath     本地路径
	 * @param dstURL        目标地址
	 * @param commitMessage
	 * @param isRecursive
	 * @return
	 */
	public static SVNCommitInfo iptdir(SVNClientManager clientManager, File localPath, SVNURL dstURL,
			String commitMessage, boolean isRecursive) {
		try {
			return clientManager.getCommitClient().doImport(localPath, dstURL, commitMessage, null, true, true,
					SVNDepth.fromRecurse(isRecursive));
		} catch (SVNException e) {
			e.printStackTrace();
			log.error("SVNHelper iptdir Error", e);
			return null;
		}
	}

	public static boolean add(File file, String username, String password) {
		setup();
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,
				password.toCharArray());
		SVNClientManager clientManager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true),
				authManager);
		try {
			boolean bRet = addEntry(clientManager, file);
			if (bRet) {
				SVNCommitInfo commitInfo = commit(clientManager, file, false, "");
				log.info("SVNCommitInfo commitInfo: " + commitInfo.toString());
			}
		} catch (Exception e) {
			log.error("SVNHelper add error", e);
			return false;
		}
		return true;
	}

	/**
	 * 添加入口
	 * 
	 * @param clientManager
	 * @param wcPath
	 */
	public static boolean addEntry(SVNClientManager clientManager, File wcFile) {
		try {
			clientManager.getWCClient().doAdd(new File[] { wcFile }, true, false, false, SVNDepth.INFINITY, false,
					false, true);
			log.info("clientManager.getWCClient().doAdd");
			return true;
		} catch (SVNException e) {
			e.printStackTrace();
			log.error("SVNHelper addEntry Error", e);
			return false;
		}
	}

	/**
	 * 显示状态
	 * 
	 * @param clientManager
	 * @param wcPath
	 * @param remote
	 * @return
	 */
	public static SVNStatus showStatus(SVNClientManager clientManager, File wcPath, boolean remote) {
		SVNStatus status = null;
		try {
			status = clientManager.getStatusClient().doStatus(wcPath, remote);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper showStatus Error", e);
		}
		return status;
	}

	public static boolean commit(String path, String username, String password) {
		setup();
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,
				password.toCharArray());
		SVNClientManager clientManager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true),
				authManager);
		try {
			SVNCommitInfo commitInfo = commit(clientManager, new File(path), false, "");
			log.info("SVNCommitInfo commitInfo: " + commitInfo.toString());
		} catch (Exception e) {
			log.error("SVNHelper add error", e);
			return false;
		}
		return true;
	}

	/**
	 * 提交
	 * 
	 * @param clientManager
	 * @param wcPath
	 * @param keepLocks     是否保持锁定
	 * @param commitMessage
	 * @return
	 */
	private static SVNCommitInfo commit(SVNClientManager clientManager, File wcFile, boolean keepLocks,
			String commitMessage) {
		try {
			return clientManager.getCommitClient().doCommit(new File[] { wcFile }, keepLocks, commitMessage, null, null,
					false, false, SVNDepth.INFINITY);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper commit Error", e);
			return null;
		}
	}

	public static long update(String wcPath, String username, String password) {
		setup();
		ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,
				password.toCharArray());
		SVNClientManager clientManager = SVNClientManager.newInstance(SVNWCUtil.createDefaultOptions(true),
				authManager);
		log.info("SVN Update Path: " + wcPath);
		return update(clientManager, new File(wcPath), SVNRevision.HEAD, SVNDepth.INFINITY);
	}

	/**
	 * 更新
	 * 
	 * @param clientManager
	 * @param wcPath
	 * @param updateToRevision
	 * @param depth
	 * @return
	 */
	public static long update(SVNClientManager clientManager, File wcPath, SVNRevision updateToRevision,
			SVNDepth depth) {
		SVNUpdateClient updateClient = clientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);
		try {
			log.info("SVN Update");
			return updateClient.doUpdate(wcPath, updateToRevision, depth, false, false);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper update Error", e);
			return 0;
		}
	}

	/**
	 * 从SVN导出项目到本地
	 * 
	 * @param clientManager
	 * @param url           SVN的url
	 * @param revision      版本
	 * @param dstPath       目标路径
	 * @param depth
	 * @return
	 */
	public static long checkout(SVNClientManager clientManager, SVNURL url, SVNRevision revision, File dstPath,
			SVNDepth depth) {
		SVNUpdateClient updateClient = clientManager.getUpdateClient();
		updateClient.setIgnoreExternals(false);
		try {
			return updateClient.doCheckout(url, dstPath, revision, revision, depth, false);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper checkout Error", e);
			return 0;
		}
	}

	/**
	 * 确定一个URL在SVN上是否存在
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @return
	 */
	public static boolean isURLExist(SVNURL url, String username, String password) {
		try {
			SVNRepository svnRepository = SVNRepositoryFactory.create(url);
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(username,
					password.toCharArray());
			svnRepository.setAuthenticationManager(authManager);
			SVNNodeKind nodeKind = svnRepository.checkPath("", -1);// 遍历SVN，获取节点
			return nodeKind == SVNNodeKind.NONE ? false : true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SVNHelper isURLExist Error", e);
			return false;
		}
	}

	public static void main(String[] args) {
	}
}
