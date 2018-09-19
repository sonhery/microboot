package com.dee.xql.a8.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dee.xql.api.model.MppTemplate;

@Mapper
public interface MppTemplateMapper {

	@Results(id = "BaseResultMap", value = { @Result(column = "ID", property = "id", javaType = Long.class),
			@Result(column = "FIELD0004", property = "projectId", javaType = String.class),
			@Result(column = "FIELD0005", property = "projectName", javaType = String.class),
			@Result(column = "FIELD0075", property = "saleAttId", javaType = String.class),
			@Result(column = "FIELD0076", property = "designAttId", javaType = String.class),
			@Result(column = "FIELD0083", property = "factoryAttId", javaType = String.class),
			@Result(column = "FIELD0078", property = "sceneAttId", javaType = String.class),
			@Result(column = "FIELD0079", property = "salePersionId", javaType = String.class),
			@Result(column = "FIELD0080", property = "designPersionId", javaType = String.class),
			@Result(column = "FIELD0081", property = "factoryPersionId", javaType = String.class),
			@Result(column = "FIELD0040", property = "auditResult", javaType = String.class),
			@Result(column = "FIELD0082", property = "scenePersionId", javaType = String.class) })
	@Select("SELECT * FROM FORMMAIN_0731")
	List<MppTemplate> findAll();

	@ResultMap("BaseResultMap")
	@Select("SELECT A.ID, A.FIELD0004, A.FIELD0005, A.FIELD0075, A.FIELD0076, A.FIELD0083, A.FIELD0078, A.FIELD0079, A.FIELD0080, A.FIELD0081, A.FIELD0082, C.SHOWVALUE FIELD0040 FROM FORMMAIN_0731 A LEFT JOIN COL_SUMMARY B ON A.ID = B.FORM_RECORDID LEFT JOIN CTP_ENUM_ITEM C ON C.ID = A.FIELD0040 WHERE B.ID = #{summaryId}")
	MppTemplate findBySummaryId(@Param("summaryId") Long summaryId);
	
	@ResultMap("BaseResultMap")
	@Select("SELECT A.ID, A.FIELD0004, A.FIELD0005, A.FIELD0075, A.FIELD0076, A.FIELD0083, A.FIELD0078, A.FIELD0079, A.FIELD0080, A.FIELD0081, A.FIELD0082, C.SHOWVALUE FIELD0040 FROM FORMMAIN_0731 A LEFT JOIN CTP_ENUM_ITEM C ON C.ID = A.FIELD0040 WHERE A.FIELD0004 = #{projectId}")
	MppTemplate findByProjectId(@Param("projectId") String projectId);
	
	@Results(id="ResultMap",value= {@Result(column = "ID", property = "id", javaType = Long.class),
			@Result(column = "FIELD0002", property = "projectId", javaType = String.class),
			@Result(column = "FIELD0003", property = "projectName", javaType = String.class),
			@Result(column = "FIELD0079", property = "factoryAttId", javaType = String.class),
			@Result(column = "FIELD0077", property = "sceneAttId", javaType = String.class),
			@Result(column = "FIELD0076", property = "factoryPersionId", javaType = String.class),
			@Result(column = "FIELD0037", property = "auditResult", javaType = String.class),
			@Result(column = "FIELD0078", property = "scenePersionId", javaType = String.class)
	})
	@Select("SELECT A.ID, A.FIELD0002, A.FIELD0003, A.FIELD0079, A.FIELD0077, A.FIELD0076, A.FIELD0078, C.SHOWVALUE FIELD0037 FROM FORMMAIN_1435 A LEFT JOIN COL_SUMMARY B ON A.ID = B.FORM_RECORDID LEFT JOIN CTP_ENUM_ITEM C ON C.ID = A.FIELD0037 WHERE B.ID = #{summaryId}")
	MppTemplate findByContractRegisterSummaryId(@Param("summaryId")Long summaryId);
}
