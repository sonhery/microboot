package com.dee.xql.api.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class DJFrontTask implements Serializable {
	private String id;// taskId+frontTaskId 生成md5码
	private String taskId;// 本任务id
    private String frontTaskId;// 前置任务id
    private String type;// 前置任务类型
    private Double delayTime;// 延隔时间
    private String fileCode;// 文件编码
    private Long svnLastVersion;// SVN 最新版本号
}