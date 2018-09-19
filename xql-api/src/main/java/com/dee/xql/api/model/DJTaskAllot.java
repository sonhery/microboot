package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class DJTaskAllot implements Serializable {
	private String id;// taskId+resId 生成md5码
	private String taskId;// 任务id
    private String resId;// 资源id
    private Double units;//单位
    private Double work;// 资源数量
    private Date startDate;// 计划开始时间
    private Date finishDate;// 计划结束时间
    private Date actualStart;// 实际开始时间
    private Date actualFinish;// 实际结束时间
    private String costRateTable;// 成本费率
    private Double cost;// 成本
	private String fileCode;// 文件编码
	private Long svnLastVersion;// SVN最新版本号
}