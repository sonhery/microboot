package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class A8Attachment implements Serializable {

	private Long id;
	private Long reference;
	private Long subReference;
	private String fileName;
	private Long fileUrl;
	private String mimeType;
	private Date createDate;
	private Long size;
	private Long sort;
}
