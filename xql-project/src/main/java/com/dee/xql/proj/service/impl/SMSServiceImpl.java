package com.dee.xql.proj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.dee.xql.api.constants.SMSType;
import com.dee.xql.api.model.SmsInfo;
import com.dee.xql.proj.config.CommonsConfig;
import com.dee.xql.proj.config.SmsCodeConfig;
import com.dee.xql.proj.dao.SmsMapper;
import com.dee.xql.proj.service.SMSService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SMSServiceImpl implements SMSService {

	@Autowired
	private SmsMapper smsMapper;
	@Autowired
	private CommonsConfig commonsConfig;
	@Autowired
	private SmsCodeConfig smsCodeConfig;

	@Override
	public void sendSms() {
		try {

			// 1、先获取数据库未发送的短信
			List<SmsInfo> smsInfos = smsMapper.findByCreateTimeBeforeAndSendResultNot(new Date(), 1);
			if (smsInfos == null || smsInfos.size() <= 0) {
				log.info("Not sms to send.");
				return;
			}
			List<SmsInfo> smsInfos1 = new ArrayList<>();
			for (int i = 0; i < smsInfos.size(); i++) {
				SmsInfo smsInfo = smsInfos.get(i);
				SendSmsResponse sendSmsResponse = sendSms(smsInfo.getModifyType(), smsInfo.getPhones(), "商装宝",
						smsInfo.getMessJsonStr(), null, null);
				log.info("Send result: " + sendSmsResponse.getCode() + ", message: " + sendSmsResponse.getMessage());
//				if (sendSmsResponse.getCode().equals("OK")) {
				smsInfo.setSendTime(new Date());
				smsInfo.setSendResult(1);
				smsInfos1.add(smsInfo);
//				}
			}
			if (smsInfos1.size() > 0) {
				smsMapper.updateSms(smsInfos1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Internal error, ", e);
		}
	}

	@Override
	public SendSmsResponse sendSms(int type, String phoneNumbers, String tplParam) {
		try {
			SendSmsResponse sendSmsResponse = sendSms(type, phoneNumbers, "商装宝", tplParam, null, null);
			log.info("Send result: " + sendSmsResponse.getCode() + ", message: " + sendSmsResponse.getMessage());
			return sendSmsResponse;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("SMSServiceImpl sendSms", e);
			return null;
		}
	}

	@Override
	public SendSmsResponse sendSms(int type, String phoneNumbers, String signName, String tplParam,
			String smsUpExtendCode, String outId) throws Exception {
		String tmplCode = "";
		switch (type) {
		case SMSType.SMS_ADD_TASK:
			tmplCode = smsCodeConfig.getAdd_task();
			break;
		case SMSType.SMS_UPT_TASK:
			tmplCode = smsCodeConfig.getUpt_task();
			break;
		case SMSType.SMS_GEN_PROJ_TPL:
			tmplCode = smsCodeConfig.getGen_proj_tpl();
			break;
		}
		return sendSms(phoneNumbers, signName, tmplCode, tplParam, smsUpExtendCode, outId);
	}

	/**
	 * 发送短信
	 *
	 * @param phoneNumbers    短信接收号码,支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,
	 *                        验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
	 * @param signName        短信签名
	 * @param tmplCode        短信模板ID
	 * @param tmplParam       短信模板变量替换JSON串,友情提示:如果JSON中需要带换行符,请参照标准的JSON协议。
	 * @param smsUpExtendCode 上行短信扩展码,无特殊需要此字段的用户请忽略此字段
	 * @param outId           外部流水扩展字段
	 * @return
	 */
	private SendSmsResponse sendSms(String phoneNumbers, String signName, String tmplCode, String tplParam,
			String smsUpExtendCode, String outId) throws Exception {
		// 产品名称:云通信短信API产品,开发者无需替换
		final String product = "Dysmsapi";
		// 产品域名,开发者无需替换
		final String domain = "dysmsapi.aliyuncs.com";
		// 可自助调整超时时间
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");

		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", commonsConfig.getDydsms_accessKeyId(),
				commonsConfig.getDydsms_accessKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);

		// 组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		// 必填:待发送手机号
		request.setPhoneNumbers(phoneNumbers);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(signName);
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(tmplCode);
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		if (tplParam != null && !tplParam.equals("")) {
			request.setTemplateParam(tplParam);
		}

		// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
		if (smsUpExtendCode != null && !smsUpExtendCode.equals("")) {
			request.setSmsUpExtendCode(smsUpExtendCode);
		}

		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		if (outId != null && !outId.equals("")) {
			request.setOutId(outId);
		}

		// hint 此处可能会抛出异常，注意catch
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

		return sendSmsResponse;
	}

	@Override
	public List<SmsInfo> findByCreateTimeBeforeAndSendResultNot(Date createTime, Integer sendResult) {
		return smsMapper.findByCreateTimeBeforeAndSendResultNot(createTime, sendResult);
	}
}
