package com.dee.xql.api.model;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class LampsTaskAllot implements Serializable {
    private String id;
    private String taskId;
    private String resId;
    private Integer units;
    private String sapGoodsId;
    private Double work;
    private Double costRateTable;
    private Double cost;
    private String fileCode;
    private Long svnLastVersion;
}