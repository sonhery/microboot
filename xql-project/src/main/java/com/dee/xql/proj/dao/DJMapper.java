package com.dee.xql.proj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dee.xql.api.model.DJFrontTask;
import com.dee.xql.api.model.DJProject;
import com.dee.xql.api.model.DJResource;
import com.dee.xql.api.model.DJTaskAllot;
import com.dee.xql.api.model.DJTaskWork;

@Mapper
public interface DJMapper {

	@Insert({
		"<script>",
		"	MERGE INTO FORMMAIN_0067 A",
		"	USING (",
		"		<foreach collection='projs' item='item' index='index' separator='union'>",
		"			SELECT 	#{item.id,jdbcType=BIGINT} ID,",
		"					#{item.state,jdbcType=INTEGER} STATE,",
		"					#{item.startMemberId,jdbcType=VARCHAR} START_MEMBER_ID,",
		"					#{item.startDate,jdbcType=TIMESTAMP} START_DATE,",
		"					#{item.approveMemberId,jdbcType=VARCHAR} APPROVE_MEMBER_ID,",
		"					#{item.approveDate,jdbcType=TIMESTAMP} APPROVE_DATE,",
		"					#{item.finishedflag,jdbcType=INTEGER} FINISHEDFLAG,",
		"					#{item.ratifyflag,jdbcType=INTEGER} RATIFYFLAG,",
		"					#{item.ratifyMemberId,jdbcType=VARCHAR} RATIFY_MEMBER_ID,",
		"					#{item.ratifyDate,jdbcType=TIMESTAMP} RATIFY_DATE,",
		"					#{item.sort,jdbcType=INTEGER} SORT,",
		"					#{item.modifyMemberId,jdbcType=VARCHAR} MODIFY_MEMBER_ID,",
		"					#{item.modifyDate,jdbcType=TIMESTAMP} MODIFY_DATE,",
		"					#{item.projectId,jdbcType=VARCHAR} FIELD0001,",
		"					#{item.projectName,jdbcType=VARCHAR} FIELD0002,",
		"					#{item.projectIndustry,jdbcType=VARCHAR} FIELD0138,",
		"					#{item.planStartDate,jdbcType=TIMESTAMP} FIELD0013,",
		"					#{item.planFinishDate,jdbcType=TIMESTAMP} FIELD0014,",
		"					#{item.projectStage,jdbcType=VARCHAR} FIELD0030,",
		"					(SELECT ID FROM (SELECT ID FROM ORG_MEMBER WHERE NAME = #{item.salePrincipal,jdbcType=VARCHAR}  ORDER BY CREATE_TIME DESC) WHERE ROWNUM=1) FIELD0023,",
		"					#{item.salePrincipalCompay,jdbcType=VARCHAR} FIELD0021,",
		"					#{item.mainDutyCompanyId,jdbcType=VARCHAR} FIELD0166,",
		"					#{item.projectBelongToCompanyId,jdbcType=VARCHAR} FIELD0185,",
		"					#{item.projectBelongToCompanyName,jdbcType=VARCHAR} FIELD0184,",
		"					#{item.authDeptCode,jdbcType=VARCHAR} FIELD0167,",
		"					#{item.authDeptId,jdbcType=VARCHAR} FIELD0168,",
		"					#{item.empCode,jdbcType=VARCHAR} FIELD0165,",
		"					#{item.customerId,jdbcType=VARCHAR} FIELD0003,",
		"					#{item.mainContact,jdbcType=VARCHAR} FIELD0006,",
		"					#{item.mainContactPhone,jdbcType=VARCHAR} FIELD0007,",
		"					#{item.dataNature,jdbcType=VARCHAR} field0164,",
		"					#{item.province,jdbcType=VARCHAR} FIELD0008",
		"			FROM DUAL WHERE 1=1 ",
		"			AND (SELECT ID FROM FORMMAIN_0067 WHERE FIELD0001=#{item.projectId}) IS NULL",			
		"			OR  (SELECT FIELD0030 FROM FORMMAIN_0067 WHERE FIELD0001=#{item.projectId}) != '-7281565050064320032'",// 售后的项目不处理
		"		</foreach>",
		"	) B",
		"	ON (A.FIELD0001=B.FIELD0001)",
		"	WHEN MATCHED THEN",
		"		UPDATE SET 	A.FIELD0002=B.FIELD0002, A.FIELD0138=B.FIELD0138, A.FIELD0013=B.FIELD0013,",
		"					A.FIELD0014=B.FIELD0014, A.FIELD0030=B.FIELD0030, A.FIELD0023=B.FIELD0023,",
		"					A.FIELD0021=B.FIELD0021, A.FIELD0166=B.FIELD0166, A.FIELD0185=B.FIELD0185,",
		"					A.FIELD0184=B.FIELD0184, A.FIELD0167=B.FIELD0167, A.FIELD0168=B.FIELD0168,",
		"					A.FIELD0165=B.FIELD0165, A.FIELD0003=B.FIELD0003, A.FIELD0006=B.FIELD0006, ",
		" 					A.FIELD0007=B.FIELD0007, A.FIELD0008=B.FIELD0008, A.field0164=B.field0164",
		"	WHEN NOT MATCHED THEN",
		"	INSERT(",
		"		A.ID,A.STATE,A.START_MEMBER_ID,A.START_DATE,A.APPROVE_MEMBER_ID,A.APPROVE_DATE,A.FINISHEDFLAG,",
		"		A.RATIFYFLAG,A.RATIFY_MEMBER_ID,A.RATIFY_DATE,A.SORT,A.MODIFY_MEMBER_ID,A.MODIFY_DATE,A.FIELD0001,",
		"		A.FIELD0002,A.FIELD0138,A.FIELD0013,A.FIELD0014,A.FIELD0030,A.FIELD0023,A.FIELD0021,A.FIELD0166,",
		"		A.FIELD0185,A.FIELD0184,A.FIELD0167,A.FIELD0168,A.FIELD0164,A.FIELD0165,A.FIELD0003,A.FIELD0006,",
		"		A.FIELD0007,A.FIELD0008",
		"	)VALUES(",
		"		B.ID,B.STATE,B.START_MEMBER_ID,B.START_DATE,B.APPROVE_MEMBER_ID,B.APPROVE_DATE,B.FINISHEDFLAG,",
		"		B.RATIFYFLAG,B.RATIFY_MEMBER_ID,B.RATIFY_DATE,B.SORT,B.MODIFY_MEMBER_ID,B.MODIFY_DATE,B.FIELD0001,",
		"		B.FIELD0002,B.FIELD0138,B.FIELD0013,B.FIELD0014,B.FIELD0030,B.FIELD0023,B.FIELD0021,B.FIELD0166,",
		"		B.FIELD0185,B.FIELD0184,B.FIELD0167,B.FIELD0168,B.FIELD0164,B.FIELD0165,B.FIELD0003,B.FIELD0006,",
		"		B.FIELD0007,B.FIELD0008)",
		"</script>" 
	})
	int batchProjs(@Param("projs") List<DJProject> projs);

	@Delete({
		"<script>",
		"	DELETE FROM DJ_TASK_WORK WHERE ID NOT IN",
		"	<foreach collection='ids' item='item' open='(' index='index' separator=',' close=')'>",
		"		#{item,jdbcType=VARCHAR}",
		"	</foreach>",
		"</script>"
	})
	int delNotInDJTaskWork(@Param("ids") List<String> ids);

	@Insert({
		"<script>",
		"	MERGE INTO DJ_TASK_WORK A",
		"	USING (",
		"		<foreach collection='allWorks' item='item' index='index' separator='union'>",
		"			SELECT 	#{item.id,jdbcType=VARCHAR} ID,",
		"					#{item.yearMonth,jdbcType=VARCHAR} YEAR_MONTH,",
		"					#{item.fileName,jdbcType=VARCHAR} FILE_NAME,",
		"					#{item.guid,jdbcType=VARCHAR} GUID,",
		"					#{item.tag,jdbcType=INTEGER} TAG,",
		"					#{item.uniqueId,jdbcType=INTEGER} UNIQUE_ID,",
		"					#{item.wbs,jdbcType=VARCHAR} WBS,",
		"					#{item.model,jdbcType=VARCHAR} MODEL,",
		"					#{item.name,jdbcType=VARCHAR} NAME,",
		"					#{item.contact,jdbcType=VARCHAR} CONTACT,",
		"					#{item.startDate,jdbcType=TIMESTAMP} START_DATE,",
		"					#{item.finishDate,jdbcType=TIMESTAMP} FINISH_DATE,",
		"					#{item.active,jdbcType=INTEGER} ACTIVE,",
		"					#{item.superiorTaskId,jdbcType=VARCHAR} SUPERIOR_TASK_ID,",
		"					#{item.calendarUniqueId,jdbcType=INTEGER} CALENDAR_UNIQUE_ID,",
		"					#{item.calendarName,jdbcType=VARCHAR} CALENDAR_NAME,",
		"					#{item.priority,jdbcType=INTEGER} PRIORITY,",
		"					#{item.createDate,jdbcType=TIMESTAMP} CREATE_DATE,",
		"					#{item.text1,jdbcType=VARCHAR} TEXT1,",
		"					#{item.text2,jdbcType=VARCHAR} TEXT2,",
		"					#{item.text3,jdbcType=VARCHAR} TEXT3,",
		"					#{item.text4,jdbcType=VARCHAR} TEXT4,",
		"					#{item.text5,jdbcType=VARCHAR} TEXT5,",
		"					#{item.text6,jdbcType=VARCHAR} TEXT6,",
		"					#{item.text7,jdbcType=VARCHAR} TEXT7,",
		"					#{item.text8,jdbcType=VARCHAR} TEXT8,",
		"					#{item.text9,jdbcType=VARCHAR} TEXT9,",
		"					#{item.text10,jdbcType=VARCHAR} TEXT10,",
		"					#{item.text11,jdbcType=VARCHAR} TEXT11,",
		"					#{item.text14,jdbcType=VARCHAR} TEXT14,",
		"					#{item.date1,jdbcType=TIMESTAMP} DATE1,",
		"					#{item.cost1,jdbcType=DOUBLE} COST1,",
		"					#{item.cost2,jdbcType=DOUBLE} COST2,",
		"					#{item.cost3,jdbcType=DOUBLE} COST3,",
		"					#{item.number1,jdbcType=DOUBLE} NUMBER1,",
		"					#{item.resourceNames,jdbcType=VARCHAR} RESOURCE_NAMES,",
		"					#{item.fileCode,jdbcType=VARCHAR} FILE_CODE,",
		"					#{item.svnLastVersion,jdbcType=BIGINT} SVN_LAST_VERSION",
		"			FROM DUAL",
		"		</foreach>",
		"	) B",
		"	ON (A.ID=B.ID)",
		"	WHEN MATCHED THEN",
		"		UPDATE SET 	A.YEAR_MONTH=B.YEAR_MONTH, A.FILE_NAME=B.FILE_NAME, A.GUID=B.GUID,",
		"					A.TAG=B.TAG, A.UNIQUE_ID=B.UNIQUE_ID, A.WBS=B.WBS,",
		"					A.MODEL=B.MODEL, A.NAME=B.NAME, A.CONTACT=B.CONTACT,",
		"					A.START_DATE=B.START_DATE, A.FINISH_DATE=B.FINISH_DATE, ",
		"					A.ACTIVE=B.ACTIVE,",
		"					A.SUPERIOR_TASK_ID=B.SUPERIOR_TASK_ID, A.CALENDAR_UNIQUE_ID=B.CALENDAR_UNIQUE_ID, ",
		"					A.CALENDAR_NAME=B.CALENDAR_NAME, ",
		" 					A.PRIORITY=B.PRIORITY, A.CREATE_DATE=B.CREATE_DATE, A.TEXT1=B.TEXT1, ",
		" 					A.TEXT2=B.TEXT2, A.TEXT3=B.TEXT3, A.TEXT4=B.TEXT4, ",
		" 					A.TEXT5=B.TEXT5, A.TEXT6=B.TEXT6, A.TEXT7=B.TEXT7, ",
		" 					A.TEXT8=B.TEXT8, A.TEXT9=B.TEXT9, ",
		" 					A.TEXT11=B.TEXT11, A.TEXT14=B.TEXT14, A.DATE1=B.DATE1, ",
		" 					A.COST1=B.COST1, A.COST2=B.COST2, A.COST3=B.COST3, ",
		" 					A.NUMBER1=B.NUMBER1, A.RESOURCE_NAMES=B.RESOURCE_NAMES, ",
		"					A.FILE_CODE=B.FILE_CODE, A.SVN_LAST_VERSION=B.SVN_LAST_VERSION ",
		"	WHEN NOT MATCHED THEN",
		"	INSERT(",
		"		A.ID,A.YEAR_MONTH,A.FILE_NAME,A.GUID,A.TAG,A.UNIQUE_ID,",
		"		A.WBS,A.MODEL,A.NAME,A.CONTACT,A.START_DATE,A.FINISH_DATE,A.ACTIVE,",
		"		A.SUPERIOR_TASK_ID,A.CALENDAR_UNIQUE_ID,A.CALENDAR_NAME,A.PRIORITY,",
		"		A.CREATE_DATE,A.TEXT1,A.TEXT2,A.TEXT3,",
		"		A.TEXT4,A.TEXT5,A.TEXT6,A.TEXT7,A.TEXT8,A.TEXT9,A.TEXT10,A.TEXT11,",
		"		A.TEXT14,A.DATE1,A.COST1,A.COST2,A.COST3,A.NUMBER1,A.RESOURCE_NAMES,A.FILE_CODE,",
		"		A.SVN_LAST_VERSION",
		"	)VALUES(",
		"		B.ID,B.YEAR_MONTH,B.FILE_NAME,B.GUID,B.TAG,B.UNIQUE_ID,",
		"		B.WBS,B.MODEL,B.NAME,B.CONTACT,B.START_DATE,B.FINISH_DATE,B.ACTIVE,",
		"		B.SUPERIOR_TASK_ID,B.CALENDAR_UNIQUE_ID,B.CALENDAR_NAME,B.PRIORITY,",
		"		B.CREATE_DATE,B.TEXT1,B.TEXT2,B.TEXT3,",
		"		B.TEXT4,B.TEXT5,B.TEXT6,B.TEXT7,B.TEXT8,B.TEXT9,B.TEXT10,B.TEXT11,",
		"		B.TEXT14,B.DATE1,B.COST1,B.COST2,B.COST3,B.NUMBER1,B.RESOURCE_NAMES,B.FILE_CODE,",
		"		B.SVN_LAST_VERSION)",
		"</script>" 
	})
	int batchWorks(@Param("allWorks")List<DJTaskWork> allWorks);

	@Delete({
		"<script>",
		"	DELETE FROM DJ_TASK_ALLOT WHERE ID NOT IN",
		"	<foreach collection='ids' item='item' open='(' index='index' separator=',' close=')'>",
		"		#{item,jdbcType=VARCHAR}",
		"	</foreach>",
		"</script>"
	})
	int delNotInDJTaskAllot(@Param("ids") List<String> ids);

	@Insert({
		"<script>",
		"	MERGE INTO DJ_TASK_ALLOT A",
		"	USING (",
		"		<foreach collection='allots' item='item' index='index' separator='union'>",
		"			SELECT 	#{item.id,jdbcType=VARCHAR} ID,",
		"					#{item.taskId,jdbcType=VARCHAR} TASK_ID,",
		"					#{item.resId,jdbcType=VARCHAR} RES_ID,",
		"					#{item.units,jdbcType=DOUBLE} UNITS,",
		"					#{item.work,jdbcType=DOUBLE} WORK,",
		"					#{item.startDate,jdbcType=TIMESTAMP} START_DATE,",
		"					#{item.finishDate,jdbcType=TIMESTAMP} FINISH_DATE,",
		"					#{item.actualStart,jdbcType=TIMESTAMP} ACTUAL_START,",
		"					#{item.actualFinish,jdbcType=TIMESTAMP} ACTUAL_FINISH,",
		"					#{item.costRateTable,jdbcType=VARCHAR} COST_RATE_TABLE,",
		"					#{item.cost,jdbcType=DOUBLE} COST,",
		"					#{item.fileCode,jdbcType=VARCHAR} FILE_CODE,",
		"					#{item.svnLastVersion,jdbcType=BIGINT} SVN_LAST_VERSION",
		"			FROM DUAL",
		"		</foreach>",
		"	) B",
		"	ON (A.ID=B.ID)",
		"	WHEN MATCHED THEN",
		"		UPDATE SET 	A.UNITS=B.UNITS, A.WORK=B.WORK, A.START_DATE=B.START_DATE,",
		"					A.FINISH_DATE=B.FINISH_DATE, A.ACTUAL_START=B.ACTUAL_START, A.ACTUAL_FINISH=B.ACTUAL_FINISH,",
		"					A.COST_RATE_TABLE=B.COST_RATE_TABLE, A.COST=B.COST, A.FILE_CODE=B.FILE_CODE,A.SVN_LAST_VERSION=B.SVN_LAST_VERSION ",
		"	WHEN NOT MATCHED THEN",
		"	INSERT(",
		"		A.ID,A.TASK_ID,A.RES_ID,A.UNITS,A.WORK,A.START_DATE,",
		"		A.FINISH_DATE,A.ACTUAL_START,A.ACTUAL_FINISH,A.COST_RATE_TABLE,A.COST,A.FILE_CODE,A.SVN_LAST_VERSION",
		"	)VALUES(",
		"		B.ID,B.TASK_ID,B.RES_ID,B.UNITS,B.WORK,B.START_DATE,",
		"		B.FINISH_DATE,B.ACTUAL_START,B.ACTUAL_FINISH,B.COST_RATE_TABLE,B.COST,B.FILE_CODE,B.SVN_LAST_VERSION)",
		"</script>" 
	})
	int batchAllots(@Param("allots") List<DJTaskAllot> allots);

	@Delete({
		"<script>",
		"	DELETE FROM DJ_FRONT_TASK WHERE ID NOT IN",
		"	<foreach collection='ids' item='item' open='(' index='index' separator=',' close=')'>",
		"		#{item,jdbcType=VARCHAR}",
		"	</foreach>",
		"</script>"
	})
	int delNotInDJFrontTask(@Param("ids") List<String> ids);

	@Insert({
		"<script>",
		"	MERGE INTO DJ_FRONT_TASK A",
		"	USING (",
		"		<foreach collection='fronts' item='item' index='index' separator='union'>",
		"			SELECT 	#{item.id,jdbcType=VARCHAR} ID,",
		"					#{item.taskId,jdbcType=VARCHAR} TASK_ID,",
		"					#{item.frontTaskId,jdbcType=VARCHAR} FRONT_TASK_ID,",
		"					#{item.type,jdbcType=VARCHAR} TYPE,",
		"					#{item.delayTime,jdbcType=DOUBLE} DELAY_TIME,",
		"					#{item.fileCode,jdbcType=VARCHAR} FILE_CODE,",
		"					#{item.svnLastVersion,jdbcType=BIGINT} SVN_LAST_VERSION", 
		"			FROM DUAL",
		"		</foreach>", 
		"	) B", 
		"	ON (A.ID=B.ID)", 
		"	WHEN MATCHED THEN",
		"		UPDATE SET 	A.TYPE=B.TYPE, A.DELAY_TIME=B.DELAY_TIME, A.FILE_CODE=B.FILE_CODE,A.SVN_LAST_VERSION=B.SVN_LAST_VERSION ",
		"	WHEN NOT MATCHED THEN", 
		"	INSERT(",
		"		A.ID,A.TASK_ID,A.FRONT_TASK_ID,A.TYPE,A.DELAY_TIME,A.FILE_CODE,A.SVN_LAST_VERSION", 
		"	)VALUES(",
		"		B.ID,B.TASK_ID,B.FRONT_TASK_ID,B.TYPE,B.DELAY_TIME,B.FILE_CODE,B.SVN_LAST_VERSION)", 
		"</script>" 
	})
	int batchFrontTasks(@Param("fronts") List<DJFrontTask> fronts);

	@Insert({ 
		"<script>", 
		"	MERGE INTO DJ_RESOURCE A", 
		"	USING (",
		"		<foreach collection='resources' item='item' index='index' separator='union'>",
		"			SELECT 	#{item.id,jdbcType=VARCHAR} ID,",
		"					#{item.guid,jdbcType=VARCHAR} GUID,",
		"					#{item.resId,jdbcType=INTEGER} RES_ID,",
		"					#{item.uniqueId,jdbcType=INTEGER} UNIQUE_ID,",
		"					#{item.type,jdbcType=VARCHAR} TYPE,",
		"					#{item.name,jdbcType=VARCHAR} NAME,",
		"					#{item.resGroup,jdbcType=VARCHAR} RES_GROUP,",
		"					#{item.materialLabel,jdbcType=VARCHAR} MATERIAL_LABEL,",
		"					#{item.initials,jdbcType=VARCHAR} INITIALS,",
		"					#{item.maxUnits,jdbcType=DOUBLE} MAX_UNITS,",
		"					#{item.code,jdbcType=VARCHAR} CODE,",
		"					#{item.notes,jdbcType=VARCHAR} NOTES,",
		"					#{item.standardRate,jdbcType=DOUBLE} STANDARD_RATE,",
		"					#{item.overtimeRate,jdbcType=DOUBLE} OVERTIME_RATE,",
		"					#{item.costPerUse,jdbcType=DOUBLE} COST_PER_USE,",
		"					#{item.createDate,jdbcType=TIMESTAMP} CREATE_DATE,",
		"					#{item.active,jdbcType=INTEGER} ACTIVE,",
		"					#{item.calendarUniqueId,jdbcType=INTEGER} CALENDAR_UNIQUE_ID,",
		"					#{item.fileCode,jdbcType=VARCHAR} FILE_CODE,",
		"					#{item.svnLastVersion,jdbcType=BIGINT} SVN_LAST_VERSION", 
		"			FROM DUAL",
		"		</foreach>", 
		"	) B", 
		"	ON (A.ID=B.ID)", 
		"	WHEN MATCHED THEN",
		"		UPDATE SET 	A.RES_ID=B.RES_ID, A.UNIQUE_ID=B.UNIQUE_ID, A.TYPE=B.TYPE,A.NAME=B.NAME,",
		"		A.RES_GROUP=B.RES_GROUP,A.MATERIAL_LABEL=B.MATERIAL_LABEL,A.INITIALS=B.INITIALS,A.MAX_UNITS=B.MAX_UNITS,",
		"		A.CODE=B.CODE,A.NOTES=B.NOTES,A.STANDARD_RATE=B.STANDARD_RATE,A.OVERTIME_RATE=B.OVERTIME_RATE,",
		"		A.COST_PER_USE=B.COST_PER_USE,A.CREATE_DATE=B.CREATE_DATE,A.ACTIVE=B.ACTIVE,",
		"		A.CALENDAR_UNIQUE_ID=B.CALENDAR_UNIQUE_ID,A.FILE_CODE=B.FILE_CODE,A.SVN_LAST_VERSION=B.SVN_LAST_VERSION ",
		"	WHEN NOT MATCHED THEN", 
		"	INSERT(",
		"		A.ID,A.GUID,A.RES_ID,A.UNIQUE_ID,A.TYPE,A.NAME,A.RES_GROUP,A.MATERIAL_LABEL,A.INITIALS,",
		"		A.MAX_UNITS,A.CODE,A.NOTES,A.STANDARD_RATE,A.OVERTIME_RATE,A.COST_PER_USE,A.CREATE_DATE,",
		"		A.ACTIVE,A.CALENDAR_UNIQUE_ID,A.FILE_CODE,A.SVN_LAST_VERSION", 
		"	)VALUES(",
		"		B.ID,B.GUID,B.RES_ID,B.UNIQUE_ID,B.TYPE,B.NAME,B.RES_GROUP,B.MATERIAL_LABEL,B.INITIALS,",
		"		B.MAX_UNITS,B.CODE,B.NOTES,B.STANDARD_RATE,B.OVERTIME_RATE,B.COST_PER_USE,B.CREATE_DATE,",
		"		B.ACTIVE,B.CALENDAR_UNIQUE_ID,B.FILE_CODE,B.SVN_LAST_VERSION)", 
		"</script>" 
	})
	int batchResources(@Param("resources") List<DJResource> resources);

	@Select("SELECT f_create_multi_DJ_task(#{fileCode,jdbcType=VARCHAR}) FROM dual")
	String generatorTask(@Param("fileCode") String fileCode);
}