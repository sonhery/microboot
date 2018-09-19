package com.dee.xql.proj.service;

import com.dee.xql.api.model.ProjProductionPlanData;

public interface ProjProductionPlanService {

    /**
     * 保存project项目数据
     *
     * @param data
     */
    boolean saveProjData(String data);

    /**
     * 获取此项目下所有任务信息、任务分配信息、资源信息
     * @param projectId 项目编码
     * @return
     */
    ProjProductionPlanData getProjProductionPlayDataByProjectId(String projectId, String fileName);
}
