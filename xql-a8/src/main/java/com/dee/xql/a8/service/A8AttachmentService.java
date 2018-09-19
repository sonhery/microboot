package com.dee.xql.a8.service;

import java.io.File;
import java.util.Date;
import java.util.List;

import com.dee.xql.api.model.A8Attachment;

public interface A8AttachmentService {

	List<A8Attachment> findAttachmentBySubReference(Long subReference);

	void findAttBySubReference(Long subReference, int sort, File ouFile);

	String getA8UploadPath(Date createDate);

	void decryptionFile(File in, File out);
}
