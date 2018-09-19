package com.dee.xql.proj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.dee.xql.api.model.ProjTaskWork;

@Mapper
public interface ProjTaskWorkMapper {

	@ResultMap("BaseResultMap")
	@Select("SELECT * FROM PROJECT_TASK_WORK_MAIN WHERE UPDATE_DATE IS NOT NULL AND (UPDATE_DATE > EXPORT_DATE OR EXPORT_DATE IS NULL)")
	List<ProjTaskWork> findExportTasks();

}
