package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class GGResource implements Serializable {
    private String id;
    private String guid;
    private Integer resId;
    private Integer uniqueId;
    private String type;
    private String name;
    private String resGroup;
    private String materialLabel;
    private Double maxUnits;
    private String code;
    private String notes;
    private Double standardRate;
    private Double overtimeRate;
    private Double costPerUse;
    private Date createDate;
    private Integer active;
    private Integer calendarUniqueId;
    private String bookingType;
    private Integer budget;
    private String fileCode;
    private Long svnLastVersion;
}