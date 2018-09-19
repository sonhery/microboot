package com.dee.xql.a8.service.impl;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.a8.config.CommonsConfig;
import com.dee.xql.a8.dao.ContractMapper;
import com.dee.xql.a8.service.ContractService;
import com.dee.xql.a8.service.RestService;
import com.dee.xql.api.constants.SMSType;
import com.dee.xql.api.model.Contract;
import com.dee.xql.api.model.Res;
import com.dee.xql.api.model.SmsParam;
import com.dee.xql.api.utils.ReplaceProjectName;

@Slf4j
@Service
public class ContractServiceImpl implements ContractService {
	@Autowired
	private ContractMapper contractMapper;
	@Autowired
	private RestService restService;
	@Autowired
	private CommonsConfig commonsConfig;
	
	@Override
	public String getPhoneBySummary(Long summaryId) {
		return contractMapper.getPhoneBySummary(summaryId);
	}
	@Override
	public Contract getContractBySummaryId(Long summaryId) {
		return contractMapper.getContractBySummaryId(summaryId);
	}
	
	public boolean sendMsg(Long summaryId){
		String phone = this.getPhoneBySummary(summaryId);
		Contract contract = this.getContractBySummaryId(summaryId);
		String projectName = ReplaceProjectName.sub(contract.getProjectName());
		if("".equals(phone)||phone==null){
			log.error("getPhoneBySummary phone==null||''.equals(phone)=true");
			return false;
		}
		SmsParam smsParam = new SmsParam();
		smsParam.setPhones(phone);
		smsParam.setType(SMSType.SMS_GEN_PROJ_TPL);
		smsParam.setTplParam("{'projectId':'"+contract.getProjectCode().trim()+"','projectName':'"+projectName+"'}");
		Res res = restService.sendSms(commonsConfig.getProject_server()+"sms/send", smsParam);
		log.info(res.toString());
		return true;
	}

	
	
}