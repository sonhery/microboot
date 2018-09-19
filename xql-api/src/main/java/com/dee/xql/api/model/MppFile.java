package com.dee.xql.api.model;

import java.io.FileInputStream;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class MppFile implements Serializable {
	private String name;
	private FileInputStream is;
}
