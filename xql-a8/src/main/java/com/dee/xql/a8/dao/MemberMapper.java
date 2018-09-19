package com.dee.xql.a8.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.dee.xql.api.model.Member;

@Mapper
public interface MemberMapper {

	@Results(id = "BaseResultMap", value = { @Result(column = "ID", property = "id", javaType = Long.class),
			@Result(column = "NAME", property = "name", javaType = String.class),
			@Result(column = "IS_ADMIN", property = "admin", javaType = Integer.class),
			@Result(column = "IS_ENABLE", property = "enable", javaType = Integer.class),
			@Result(column = "IS_DELETED", property = "deleted", javaType = Integer.class),
			@Result(column = "STATUS", property = "status", javaType = Integer.class),
			@Result(column = "SORT_ID", property = "sortId", javaType = Integer.class),
			@Result(column = "ORG_DEPARTMENT_ID", property = "orgDpartmentId", javaType = Long.class),
			@Result(column = "ORG_POST_ID", property = "orgPostId", javaType = Long.class),
			@Result(column = "ORG_ACCOUNT_ID", property = "orgAccountId", javaType = Long.class),
			@Result(column = "CREATE_TIME", property = "createTime", javaType = Date.class),
			@Result(column = "UPDATE_TIME", property = "updateTime", javaType = Date.class),
			@Result(column = "EXT_ATTR_1", property = "extAttr1", javaType = String.class) })
	@Select("SELECT ID, NAME, IS_ADMIN , IS_ENABLE , IS_DELETED , STATUS , SORT_ID , ORG_DEPARTMENT_ID , ORG_POST_ID , ORG_ACCOUNT_ID , CREATE_TIME , UPDATE_TIME , EXT_ATTR_1 FROM ORG_MEMBER WHERE ID=#{id}")
	Member findById(@Param("id") Long id);

}
