package com.dee.xql.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.dee.xql.api.model.Res;
import com.dee.xql.api.model.SmsParam;
import com.dee.xql.proj.service.SMSService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SMSController {

	@Autowired
	private SMSService smsService;

	@RequestMapping(value = "/sms/send", method = RequestMethod.POST)
	public Res send(@RequestBody SmsParam smsParam) {
		Res res = new Res();
		try {
			log.info(smsParam.toString());
			SendSmsResponse sendSms = smsService.sendSms(smsParam.getType(), smsParam.getPhones(),
					smsParam.getTplParam());
			res.setValue(sendSms);
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(10001);
			res.setSuccess(false);
			res.setMsg("SMSController send error");
			log.error("SMSController send", e);
		}
		return res;
	}
}
