package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 项目任务工作表（PROJECT_TASK_WORK）
 */
@Data
public class ProjTaskWork implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String id;//主键id，由文件名称+项目id+taskUid 生成md5码
    private String taskUid;//guid
    private String projectId;//项目编码
    private String taskSequenceCode;//任务序列码
    private String taskName;//任务名称
    private String wbs;
    private Integer active;//是否为活动任务
    private String superiorTaskId;//商机任务ID
    private String superiorTaskUid;//上级任务GUID
    private String notes;//备注
    private String contact;//联系人
    private String taskModel;//任务模式
    private Integer priority;//优先级
    private Integer isMilepost;//是否是里程碑任务
    private Date planStartDate;//计划开始时间
    private Date planEndDate;//计划结束时间
    private Double planTimeLimit;//计划工期
    private Date lastDate;//最后期限
    private Date startDate;//实际开始时间
    private Date endDate;//实际完成时间
    private Double timeLimit;//实际工期
    private Double workHours;//实际工时
    private Double completePercent;//完成百分比
    private Date firstStartDate;//最早开始时间
    private Date firstCompleteDate;//最早完成时间
    private Date lastStartDate;//最晚开始时间
    private Date lastCompleteDate;//最晚完成时间
    private Double startDelay;//开始可宽延时间
    private Double completeDelay;//完成可宽延时间
    private Double totalDelay;//可宽延总时间
    private Date createDate;//创建时间
    private Integer overallocated;//过渡分配
    private String taskType;//任务类型
    private String calendarGuid;//日历guid
    private Integer estimated;//是否为估计值
    private String constraintType;//任务的限制类型
    private Date constraintDate;//任务的限制日期
    private Integer logId;//最新任务日志
    private String fileName;//文件名称
    private Integer startMode;//任务发起模式，1：自动，其他：手动
    private Long summaryId;//最新流程id
    private Integer isSummary;//是否是摘要任务，1：是，0：否
    private String taskSupervision;//任务监理
    private String taskCode;//任务编码
    private Long svnLastVersion;
    private String resourceNames;//资源名称
    private Double cost;//成本
    private Double overtimeCost;//加班成本
    private Double fixedCost;//固定成本
    private Double actualCost;//实际成本
    private Double actualOvertimeCost;//实际加班成本
    private Double actualOvertimeWork;//实际加班工时
    private Double physicalPercentComplete;//实际完成百分比
    private Double remainingCost;//剩余成本
    private Double remainingDuration;//剩余工期
    private Double remainingWork;//剩余工时
    private Double remainingOvertimeCost;//剩余加班成本
    private Double remainingOvertimeWork;//剩余加班工时
    private String hyperlink;//超链接
    private String hyperlinkAddress;//超链接地址
}
