package com.dee.xql.a8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.xql.a8.param.CustomerDiscard;
import com.dee.xql.a8.service.CustomerService;
import com.dee.xql.api.model.Res;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/customer/doDiscard")
	public Res doDiscard(CustomerDiscard cDiscard) {
		Res res = new Res();
		try {
			System.out.println(cDiscard.toString());
			String str = customerService.discard(cDiscard.getDiscardId(), cDiscard.getRemainId());
			if ("成功".equals(str)) {// 成功
				res.setMsg("废弃客户：" + cDiscard.getDiscardId() + " 成功！");
			} else {// 失败
				res.setCode(10001);
				res.setMsg(str);
				res.setSuccess(false);
			}
		} catch (Exception e) {
			log.error("CustomerController discard error: ", e);
			res.setCode(10001);
			res.setMsg("内部错误");
			res.setSuccess(false);
		}
		return res;
	}
}
