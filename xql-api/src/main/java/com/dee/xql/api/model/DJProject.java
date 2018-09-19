package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class DJProject extends Formmain_0067 implements Serializable {
	
	private Double cost1;
	private Double cost2;
	private Double cost3;

	private List<DJTaskWork> works = new ArrayList<DJTaskWork>();
	private List<DJTaskAllot> allots = new ArrayList<DJTaskAllot>();
	private List<DJFrontTask> frontTasks = new ArrayList<DJFrontTask>();
}
