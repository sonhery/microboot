package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class DJData implements Serializable {
	
	private List<DJProject> projs = new ArrayList<DJProject>();
	private List<DJResource> resources = new ArrayList<DJResource>();
	
}
