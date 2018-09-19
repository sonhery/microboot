package com.dee.xql.proj.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dee.xql.api.model.ProjProductionPlanData;
import com.dee.xql.api.model.ProjTaskAllot;
import com.dee.xql.api.model.ProjTaskWork;
import com.dee.xql.api.model.ResourceItem;
import com.dee.xql.proj.dao.ProjProductionPlanMapper;
import com.dee.xql.proj.service.ProjProductionPlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjProductionPlanServiceImpl implements ProjProductionPlanService {

	@Autowired
	private ProjProductionPlanMapper projProductionPlanMapper;

	@Override
	public boolean saveProjData(String datas) {

		boolean isSucccess = true;
		try {
			String strres = "";
			if (datas == null || datas.equals("")) {
				isSucccess = false;
				return isSucccess;
			}
			ProjProductionPlanData projProductionPlanData = JSON.parseObject(datas, ProjProductionPlanData.class);
			if (projProductionPlanData == null || projProductionPlanData.getProjTaskWorks().size() <= 0) {
				isSucccess = false;
				return isSucccess;
			}
//            log.info("projProductionPlanData:" + JSON.toJSONString(projProductionPlanData));
			Map<String, Object> map = new HashMap<>();
			map.put("p_summary_id", projProductionPlanData.getSummaryId());
			map.put("p_proj_task_works", projProductionPlanData.getProjTaskWorks());
			map.put("p_front_task_items", projProductionPlanData.getFrontTaskItems());
			map.put("p_proj_task_allots", projProductionPlanData.getProjTaskAllots());
			map.put("p_resource_items", projProductionPlanData.getResourceItems());
			map.put("p_res", strres);
			Map<String, Object> map1 = new HashMap<>();
			map1.put("p_summary_id", projProductionPlanData.getSummaryId());
			map1.put("p_project_id", projProductionPlanData.getProjTaskWorks().get(0).getProjectId());
			map1.put("p_file_name", projProductionPlanData.getProjTaskWorks().get(0).getFileName());
			map1.put("p_res", strres);
			projProductionPlanMapper.saveProjData(map);
			strres = (String) map.get("p_res");
			log.info("strres:" + strres);
			JSONObject jobj = JSON.parseObject(strres);
			if (jobj.getIntValue("code") == 1) {
				log.info("主数据操作");
				projProductionPlanMapper.saveProjMainData(map1);
				strres = (String) map1.get("p_res");
				log.info("strres1:" + strres);
			} else {
				isSucccess = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSucccess = false;
		} finally {
			log.info("saveProjData finally isSucccess:" + isSucccess);
		}

		return isSucccess;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ProjProductionPlanData getProjProductionPlayDataByProjectId(String projectId, String fileName) {
		Map<String, Object> map = new HashMap<>();
		ProjProductionPlanData planData = new ProjProductionPlanData();
		map.put("p_project_id", projectId);
		map.put("p_file_name", fileName);
		map.put("curr_allot_list", planData.getProjTaskAllots());
		map.put("curr_resource_list", planData.getResourceItems());
		map.put("curr_task_list", planData.getProjTaskWorks());
		projProductionPlanMapper.getProjProductionPlayDataByProjectId(map);
//        logger.info(JSON.toJSONString(map));
		List<ProjTaskWork> projTaskWorks = (List<ProjTaskWork>) map.get("curr_task_list");
		Iterator<ProjTaskWork> itorWorks = projTaskWorks.iterator();
		while (itorWorks.hasNext()) {
			ProjTaskWork work = itorWorks.next();
			work.setTaskUid(work.getTaskUid().toUpperCase());
			work.setSuperiorTaskUid(work.getSuperiorTaskUid().toUpperCase());
		}
		planData.setProjTaskWorks(projTaskWorks);
		List<ProjTaskAllot> projTaskAllots = (List<ProjTaskAllot>) map.get("curr_allot_list");
		planData.setProjTaskAllots(projTaskAllots);
		List<ResourceItem> resourceItems = (List<ResourceItem>) map.get("curr_resource_list");
		planData.setResourceItems(resourceItems);
		if (planData.getProjTaskWorks() == null || planData.getProjTaskWorks().size() <= 0) {
			return null;
		}
//        logger.info(JSON.toJSONString(planData));
		return planData;
	}
}
