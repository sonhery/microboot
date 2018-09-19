package com.dee.xql.a8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.dee.xql.a8.config.CommonsConfig;
import com.dee.xql.a8.service.ContractService;
import com.dee.xql.a8.service.MppTemplateService;
import com.dee.xql.a8.service.ProjectService;
import com.dee.xql.a8.service.RestService;
import com.dee.xql.api.constants.SMSType;
import com.dee.xql.api.model.Contract;
import com.dee.xql.api.model.MppTemplate;
import com.dee.xql.api.model.Res;
import com.dee.xql.api.model.SmsParam;
import com.dee.xql.api.model.User;
import com.dee.xql.api.utils.ReplaceProjectName;
import com.dee.xql.api.utils.SVNHelper;

@SpringBootTest(classes = SpringbootApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class Tester {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private RestService restService;
	@Autowired
	private CommonsConfig commonsConfig;
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private MppTemplateService mppTemplateService;
	@Autowired
	private ProjectService projectService;

	@Test
	public void testLampsGoodsList() {
		User[] users = new User[2];
		users[0] = new User();
		users[0].setPassword("123");
		users[0].setUsername("YSH");
		;
		users[1] = new User();
		users[1].setPassword("456");
		users[1].setUsername("海");

		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		HttpEntity<String> formEntity = new HttpEntity<String>(JSON.toJSONString(users), headers);
		Res res = restTemplate.postForObject("http://127.0.0.1:8080/lamps/syncGoodsList", formEntity, Res.class);
		System.out.println(res.toString());
	}

	@Test
	public void testRest() {
		// 通过远程的Rest服务中的信息将其自动换换为Res对象实例
		SmsParam smsParam = new SmsParam();
		smsParam.setPhones("13800138000");
		smsParam.setType(SMSType.SMS_GEN_PROJ_TPL);
		smsParam.setTplParam("{'projectId':'2018081152'}");
		Res res = restTemplate.postForObject("http://127.0.0.1:8080/hello/get", smsParam, Res.class);
		System.out.println(res.toString());
	}

	@Test
	public void testSms() {
//		JSONObject jobj = new JSONObject();
//		jobj.put("projectId", "2018081111");
//		jobj.put("projectName", "2018081111");
//		SmsParam param = new SmsParam();
//		param.setType(SMSType.SMS_GEN_PROJ_TPL);
//		param.setPhones("18190798870");
//		param.setTplParam(jobj.toJSONString());
//		Res res = restService.sendSms("http://118.123.247.219:43333/sms/send", param);
//		System.out.println(res.toString());
		
		MppTemplate mppTpl = mppTemplateService.findByProjectId("2018090987");
		
		String phones = projectService.getPhones(mppTpl);
		String projectName = ReplaceProjectName.sub(mppTpl.getProjectName());
//		if (!StringUtils.isEmpty(phones)) {
			SmsParam param = new SmsParam();
			param.setType(SMSType.SMS_GEN_PROJ_TPL);
//			param.setPhones(phones);
			param.setPhones("18190798870");
			param.setTplParam(
					"{'projectId':'" + mppTpl.getProjectId().trim() + "','projectName':'" + projectName + "'}");
			restService.sendSms(commonsConfig.getProject_server() + "sms/send", param);
//		}
	}

	@Test
	public void testSVNUpdate() {
		Long res = SVNHelper.update(commonsConfig.getSvn_export_path(), commonsConfig.getSvn_username(),
				commonsConfig.getSvn_password());
		System.out.println(res);
	}
	
//	@Test
	public void testGetContract(){
		Contract contract = contractService.getContractBySummaryId(8886779310758476567L);
		System.out.println(contract.getContractCode());
	}
	@Test
	public void testSendMsg(){
		System.out.println(contractService.sendMsg(8886779310758476567L));
	}
	
	@Test
	public void test()
	{
		System.out.println("20180910".substring(0, 6));
	}
}
