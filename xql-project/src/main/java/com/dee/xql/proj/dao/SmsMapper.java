package com.dee.xql.proj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dee.xql.api.model.SmsInfo;

@Mapper
public interface SmsMapper {

    @ResultMap("BaseResultMap")
    @Select("SELECT * FROM PROJECT_MESS_SEND WHERE CREATE_TIME <= #{createTime} AND SEND_RESULT != #{sendResult}")
    List<SmsInfo> findByCreateTimeBeforeAndSendResultNot(@Param("createTime") Date createTime, @Param("sendResult")
            Integer sendResult);

    @Update({
            "<script>",
            "<foreach collection='smsInfos' item='value' index='key' open='begin' close=';end;' separator=';'>",
            "update PROJECT_MESS_SEND",
            "set SEND_TIME=#{value.sendTime}, SEND_RESULT=#{value.sendResult}",
            "where id=#{value.id}",
            "</foreach>",
            "</script>"
    })
    void updateSms(@Param("smsInfos") List<SmsInfo> smsInfos);
}
