package com.dee.xql.a8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dee.xql.a8.service.RestService;
import com.dee.xql.api.model.Res;
import com.dee.xql.api.model.SmsParam;

@Service
public class RestServiceImpl implements RestService {
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Res sendSms(String url,SmsParam smsParam) {
		return restTemplate.postForObject(url, smsParam, Res.class);
	}

}
