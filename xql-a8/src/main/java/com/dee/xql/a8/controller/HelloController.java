package com.dee.xql.a8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.xql.a8.config.CommonsConfig;
import com.dee.xql.a8.service.MppTemplateService;
import com.dee.xql.a8.service.ProjectService;
import com.dee.xql.a8.service.RestService;
import com.dee.xql.api.constants.SMSType;
import com.dee.xql.api.model.MppTemplate;
import com.dee.xql.api.model.SmsParam;
import com.dee.xql.api.utils.ReplaceProjectName;

@RestController
public class HelloController {

	@Autowired
	private MppTemplateService mppTemplateService;
	@Autowired
	private ProjectService projectService;

	@Autowired
	private RestService restService;
	@Autowired
	private CommonsConfig commonsConfig;

	@RequestMapping("/hello")
	public String hello(String projectId) {

		MppTemplate mppTpl = mppTemplateService.findByProjectId(projectId);

		String phones = projectService.getPhones(mppTpl);
		String projectName = ReplaceProjectName.sub(mppTpl.getProjectName());
		if (!StringUtils.isEmpty(phones)) {
			SmsParam param = new SmsParam();
			param.setType(SMSType.SMS_GEN_PROJ_TPL);
			param.setPhones(phones+",18190798870");
//			param.setPhones("18190798870");
			param.setTplParam(
					"{'projectId':'" + mppTpl.getProjectId().trim() + "','projectName':'" + projectName + "'}");
			restService.sendSms(commonsConfig.getProject_server() + "sms/send", param);
		}
		return projectId;
	}
}
