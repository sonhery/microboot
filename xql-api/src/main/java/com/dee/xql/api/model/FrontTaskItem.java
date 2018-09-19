package com.dee.xql.api.model;

import java.io.Serializable;

/**
 * YSH
 * 前置任务从表
 */
public class FrontTaskItem implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8049247206791613514L;
	private String taskId;//本任务在项目任务工作表的id
    private String taskUid;//本任务唯一编码
    private String projectId;//项目编码
    private String frontTaskId;//前置任务在项目工作表的id
    private String frontTaskUid;//前置任务GUID
    private String frontProjectId;//前置任务项目编码
    private String frontType;//前置任务类型
    private Double delayTime;//延隔时间
    private Long summaryId;//最新流程id
    private Long svnLastVersion;

    public Long getSvnLastVersion() {
        return svnLastVersion;
    }

    public void setSvnLastVersion(Long svnLastVersion) {
        this.svnLastVersion = svnLastVersion;
    }

    public String getTaskUid() {
        return taskUid;
    }

    public void setTaskUid(String taskUid) {
        this.taskUid = taskUid;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getFrontTaskUid() {
        return frontTaskUid;
    }

    public void setFrontTaskUid(String frontTaskUid) {
        this.frontTaskUid = frontTaskUid;
    }

    public String getFrontProjectId() {
        return frontProjectId;
    }

    public void setFrontProjectId(String frontProjectId) {
        this.frontProjectId = frontProjectId;
    }

    public String getFrontType() {
        return frontType;
    }

    public void setFrontType(String frontType) {
        this.frontType = frontType;
    }

    public Double getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Double delayTime) {
        this.delayTime = delayTime;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getFrontTaskId() {
        return frontTaskId;
    }

    public void setFrontTaskId(String frontTaskId) {
        this.frontTaskId = frontTaskId;
    }

    public Long getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(Long summaryId) {
        this.summaryId = summaryId;
    }
}
