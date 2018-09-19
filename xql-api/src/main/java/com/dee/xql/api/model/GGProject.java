package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@SuppressWarnings("serial")
public class GGProject extends Formmain_0067 implements Serializable {
	
	private List<GGTaskWork> works = new ArrayList<GGTaskWork>();
	private List<GGTaskAllot> allots = new ArrayList<GGTaskAllot>();
	private List<GGFrontTask> frontTasks = new ArrayList<GGFrontTask>();
}
