package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class DJResource implements Serializable {

	private String id;// 主键ID
	private String guid;// 资源GUID
	private Integer resId;// 资源标识号
	private Integer uniqueId;// 资源唯一标识号
	private String type;// 资源类型
	private String name;// 资源名称
	private String resGroup;// 资源分组
	private String materialLabel;// 资源单位
	private String initials;// 岗位
	private Double maxUnits;// 最大单位
	private String code;// 手机
	private String notes;// 备注
	private Double standardRate;// 标准费率
	private Double overtimeRate;// 加班费率
	private Double costPerUse;// 每次使用成本
	private Date createDate;// 创建时间
	private Integer active;// 活动
	private Integer calendarUniqueId;// 日历
	private String fileCode;// 文件编码
	private Long svnLastVersion;// SVN最新版本号
	
}