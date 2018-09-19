package com.dee.xql.proj.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.dee.xql.api.model.SmsInfo;

public interface SMSService {

	void sendSms();

	public SendSmsResponse sendSms(int type, String phoneNumbers, String tplParam);

	SendSmsResponse sendSms(int type, String phoneNumbers, String signName, String tplParam, String smsUpExtendCode,
			String outId) throws Exception;

	List<SmsInfo> findByCreateTimeBeforeAndSendResultNot(Date createTime, @Param("sendResult") Integer sendResult);

}
