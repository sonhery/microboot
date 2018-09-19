package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class GGData implements Serializable {
	
	private List<GGProject> projs = new ArrayList<GGProject>();
	private List<GGResource> resources = new ArrayList<GGResource>();
	
}
