package com.dee.xql.a8.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CustomerMapper {

	@Select("SELECT F_UPDATE_CUST(#{discardId,jdbcType=VARCHAR},#{remainId,jdbcType=VARCHAR}) FROM DUAL")
	String discardCustomer(@Param("discardId") String discardId,@Param("remainId") String remainId);

}
