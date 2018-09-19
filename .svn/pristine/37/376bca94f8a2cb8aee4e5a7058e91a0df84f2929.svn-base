package com.dee.xql.proj.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dee.xql.api.model.ProjTaskWork;

@Mapper
public interface ProjProductionPlanMapper {

	void saveProjData(Map<String, Object> params);

	void saveProjMainData(Map<String, Object> map);

	void saveResourceItems(Map<String, Object> map);

	int uptInvalidTag(@Param("summaryId") Long summaryId, @Param("invalid") String invalid);

	ProjTaskWork getProjFileNameAndSummaryId(String projectId);

	void getProjProductionPlayDataByProjectId(Map<String, Object> map);

	int uptExportDate(@Param("fileName") String fileName, @Param("uptDate") String uptDate);
}
