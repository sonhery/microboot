package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Contract implements Serializable {
	private Long id;
	//合同编码
	private String contractCode;
	//项目编码
	private String projectCode;
	//项目名称
	private String projectName;
	//合同名称
	private String contractName;
	//客户经理
	private String manager;
	//合同开始日期 field0031
	private Date contractStartDate;
	//合同结束日期 field0032
	private Date contractEndDate;
}
