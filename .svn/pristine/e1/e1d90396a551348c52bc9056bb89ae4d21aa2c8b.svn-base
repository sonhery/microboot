package com.dee.xql.proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.dee.xql.api.model.Res;
import com.dee.xql.api.model.SAPGoods;
import com.dee.xql.proj.service.LampsService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LampsController {

	@Autowired
	private LampsService lampsService;

	@RequestMapping(value = "/lamps/syncGoodsList", method = RequestMethod.POST)
	public Res syncGoodsList(@RequestBody SAPGoods[] goods) {
		Res res = new Res();
		try {
			if (goods == null || goods.length <= 0) {
				res.setCode(10001);
				res.setSuccess(false);
				res.setMsg("Goods List is null");
			} else {
				log.info("SAPGoods:" + JSON.toJSONString(goods));
				boolean bRet = lampsService.lampsGoodsChanges(goods);
				res.setMsg("Sync Goods List Success");
				res.setValue(bRet);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setCode(10001);
			res.setSuccess(false);
			res.setMsg("LampsController syncGoodsList error");
			log.error("LampsController syncGoodsList error", e);
		}
		System.out.println(res);
		return res;
	}
}
