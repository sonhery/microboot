package com.dee.xql.a8.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.a8.config.CommonsConfig;
import com.dee.xql.a8.dao.A8AttachmentMapper;
import com.dee.xql.a8.service.A8AttachmentService;
import com.dee.xql.api.model.A8Attachment;
import com.dee.xql.api.utils.DateHelper;
import com.seeyon.ctp.common.encrypt.CoderFactory;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class A8AttachmentServiceImpl implements A8AttachmentService {

	@Autowired
	private A8AttachmentMapper a8AttachmentMapper;
	@Autowired
	private CommonsConfig commonsConfig;

	@Override
	public List<A8Attachment> findAttachmentBySubReference(Long subReference) {
		return a8AttachmentMapper.findBySubReference(subReference);
	}

	@Override
	public void findAttBySubReference(Long subReference, int sort, File ouFile) {
		List<A8Attachment> atts = this.findAttachmentBySubReference(subReference);
		if (atts == null || atts.size() < sort) {
			ouFile = null;
			return;
		}
		A8Attachment att = atts.get(sort);
		String filePath = this.getA8UploadPath(att.getCreateDate());
		filePath += att.getFileUrl();
		File inFile = new File(filePath);
		this.decryptionFile(inFile, ouFile);
	}

	@Override
	public String getA8UploadPath(Date createDate) {
		if (createDate == null) {
			return null;
		}
		String a8UploadDir = commonsConfig.getA8_upload_dir();
		StringBuilder sb = new StringBuilder();
		sb.append(a8UploadDir);
		sb.append(DateHelper.getYear(createDate));
		sb.append(File.separator);
		sb.append(DateHelper.getMonth(createDate, true));
		sb.append(File.separator);
		sb.append(DateHelper.getDay(createDate, true));
		sb.append(File.separator);
		return sb.toString();
	}

	@Override
	public void decryptionFile(File in, File out) {
		try {
			@Cleanup
			FileOutputStream fop = new FileOutputStream(out);
			@Cleanup
			FileInputStream fin = new FileInputStream(in);
			CoderFactory.getInstance().download(fin, fop);
			fop.flush();
			fop.close();
		} catch (Exception e) {
			log.error("File: " + in.getName() + " Decryption Error", e);
		}
	}
}
