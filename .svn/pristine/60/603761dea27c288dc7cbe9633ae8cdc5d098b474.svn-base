package com.dee.xql.a8.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dee.xql.a8.service.DoService;
import com.dee.xql.api.model.Res;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DoController {

	@Autowired
	private DoService doService;

	@RequestMapping("/a8/do")
	public Res doSomething(Long summaryId, String tplCode) {
		Res res = new Res();
		try {
			boolean bRet = doService.doSomething(summaryId, tplCode);
			res.setCode(bRet ? 1 : 10002);
			res.setSuccess(bRet);
			res.setMsg(bRet ? "doSomething success" : "doSomething failure");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DoController doSomething Error", e);
			res.setCode(10001);
			res.setMsg("doSomething exception");
			res.setSuccess(false);
		}
		return res;
	}
}
