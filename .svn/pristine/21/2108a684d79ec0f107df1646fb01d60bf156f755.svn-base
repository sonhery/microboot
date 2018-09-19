package com.dee.xql.a8.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dee.xql.api.model.A8Attachment;

@Mapper
public interface A8AttachmentMapper {

	@Results(id="BaseResultMap",value= {
			@Result(column="ID",property="id",javaType=Long.class),
			@Result(column="REFERENCE",property="reference",javaType=Long.class),
			@Result(column="SUB_REFERENCE",property="subReference",javaType=Long.class),
			@Result(column="FILENAME",property="fileName",javaType=String.class),
			@Result(column="FILE_URL",property="fileUrl",javaType=Long.class),
			@Result(column="MIME_TYPE",property="mimeType",javaType=String.class),
			@Result(column="CREATEDATE",property="createDate",javaType=Date.class),
			@Result(column="ATTACHMENT_SIZE",property="size",javaType=Long.class),
			@Result(column="SORT",property="sort",javaType=Long.class)
	})
	@Select("SELECT * FROM CTP_ATTACHMENT WHERE SUB_REFERENCE=#{subReference} ORDER BY SORT")
	List<A8Attachment> findBySubReference(@Param("subReference") Long subReference);
}
