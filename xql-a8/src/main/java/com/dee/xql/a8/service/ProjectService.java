package com.dee.xql.a8.service;

import com.dee.xql.api.model.MppTemplate;

public interface ProjectService {

	/**
	 * 从项目立项表生成项目计划project文件模板
	 * 
	 * @param summaryId 项目立项表表单流程ID
	 * @return
	 */
	boolean genMppTemplateByProjectSetup(Long summaryId);

	/**
	 * 从业务合同登记表生成项目计划project文件模板
	 * 
	 * @param summaryId 业务合同登记表表单流程ID
	 * @return
	 */
	boolean genMppTemplateByContractRegister(Long summaryId);
	
	String getPhones(MppTemplate mppTpl);
}
