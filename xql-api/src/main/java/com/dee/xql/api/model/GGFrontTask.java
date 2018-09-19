package com.dee.xql.api.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class GGFrontTask implements Serializable {
    private String id;
    private String taskId;
    private String frontTaskId;
    private String type;
    private Double delayTime;
    private String fileCode;
    private Long svnLastVersion;
}