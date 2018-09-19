package com.dee.xql.a8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.a8.service.ContractService;
import com.dee.xql.a8.service.DoService;
import com.dee.xql.a8.service.ProjectService;
import com.dee.xql.api.constants.FormTplCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DoServiceImpl implements DoService {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private ContractService contractService;
	@Override
	public boolean doSomething(Long summaryId, String tplCode) {
		log.info("DoServiceImpl doSomething summaryId: " + summaryId + ", tplCode: " + tplCode);
		switch (tplCode) {
		case FormTplCode.TPLCODE_XMLXSH:// 项目立项审核
			return projectService.genMppTemplateByProjectSetup(summaryId);
		case FormTplCode.TPLCODE_YWHTDJB:// 业务合同登记表
			contractService.sendMsg(summaryId);
			return projectService.genMppTemplateByContractRegister(summaryId);
		}
		return false;
	}

}
