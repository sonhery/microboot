package com.dee.xql.proj.scheduler;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dee.ssm.jni.JNIDee;
import com.dee.xql.api.utils.DateHelper;
import com.dee.xql.proj.service.LampsService;
import com.dee.xql.proj.service.MppService;
import com.dee.xql.proj.svn.SVNService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SVNScheduler {

	private JNIDee jniDee = new JNIDee();

	@Autowired
	private SVNService exportDiff;
	@Autowired
	private MppService mppService;
	@Autowired
	private LampsService lampsService;

	@Scheduled(cron = "0 0/1 * * * ?")
	public void updateSvn() {
		log.info("Update SVN Start, Current Time:" + DateHelper.getStrDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		exportDiff.initSVN();
	}

	@Scheduled(cron = "0/1 * * * * ?")
	public void clickOk() {
//		jniDee.openProjResFile();
	}

	@Scheduled(cron = "0 0 1 * * ?")
	public void exportMpp() {
		log.info("Export mpp, Current Time: " + DateHelper.getStrDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		mppService.exportMpp();
	}

	@Scheduled(cron = "0 0 3 * * ?")
	public void uptLampsResources() {
		log.info("Update Lamps Resources, Current Time: " + DateHelper.getStrDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
//		lampsService.uptLampsResource();
	}
}
