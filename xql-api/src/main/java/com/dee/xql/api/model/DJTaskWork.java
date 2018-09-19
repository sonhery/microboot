package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class DJTaskWork implements Serializable {

	private String id; // 主键ID
	private String yearMonth; // 项目年月
	private String fileName; // 导入文件名
	private String guid; // 任务GUID
	private Integer tag; // 任务的标识号
	private Integer uniqueId; // 任务唯一标识号
	private String wbs; // WBS
	private String model; // 任务模式
	private String name; // 任务名称
	private String contact; // 接单人
	private Date startDate; // 开始时间
	private Date finishDate; // 结束时间
	private Integer active; // 活动
	private String superiorTaskId; // 上级任务ID
	private Integer calendarUniqueId; // 任务日历UID
	private String calendarName; // 任务日历名称
	private Integer priority; // 优先级
	private Date createDate; // 创建日期
	private String text1; // 内部订单号
	private String text2; // 合同编码
	private String text3; // sap销售订单号
	private String text4; // 品牌
	private String text5; // 物流信息
	private String text6; // 省份
	private String text7; // 客户编码
	private String text8; // 客户联系人
	private String text9; // 客户联系人手机
	private String text10; // 任务序列码
	private String text11; // 任务别名
	private String text14; // 项目编码
	private Date date1; // 最新导入日期
	private Double cost1; // 合同金额
	private Double cost2; // 定金
	private Double cost3; // 尾款
	private Double number1; // 展柜米数
	private String resourceNames; // 资源名称
	private String fileCode;// 文件编码
	private Long svnLastVersion;// SVN最新版本号
}