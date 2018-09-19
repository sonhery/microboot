package com.dee.xql.api.model;

import java.io.Serializable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class SmsParam  implements Serializable {

	private Integer type;
	private String phones;
	private String tplParam;
}
