package com.dee.xql.proj.enums;

public enum ProjectFileType {

	MPP_TASK(0), MPP_RESOURCE(1);

	private int m_value;

	private ProjectFileType(int type) {
		this.m_value = type;
	}

	public Integer getValue() {
		return m_value;
	}
}
