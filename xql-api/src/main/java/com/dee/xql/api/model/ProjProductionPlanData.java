package com.dee.xql.api.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * YSH 项目生产计划数据
 */
public class ProjProductionPlanData implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4002380142423036121L;
	private Long summaryId;// 流程Id
	private List<ProjTaskWork> projTaskWorks;// 项目任务工作表数据
	private List<FrontTaskItem> frontTaskItems;// 前置任务工作表
	private List<ProjTaskAllot> projTaskAllots;// 项目任务分配表
	private List<ResourceItem> resourceItems;// 资源工作表

	public Long getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}

	public List<ProjTaskWork> getProjTaskWorks() {
		if (projTaskWorks == null) {
			projTaskWorks = new ArrayList<>();
		}
		return projTaskWorks;
	}

	public void setProjTaskWorks(List<ProjTaskWork> projTaskWorks) {
		this.projTaskWorks = projTaskWorks;
	}

	public List<FrontTaskItem> getFrontTaskItems() {
		if (frontTaskItems == null) {
			frontTaskItems = new ArrayList<>();
		}
		return frontTaskItems;
	}

	public void setFrontTaskItems(List<FrontTaskItem> frontTaskItems) {
		this.frontTaskItems = frontTaskItems;
	}

	public List<ProjTaskAllot> getProjTaskAllots() {
		if (projTaskAllots == null) {
			projTaskAllots = new ArrayList<>();
		}
		return projTaskAllots;
	}

	public void setProjTaskAllots(List<ProjTaskAllot> projTaskAllots) {
		this.projTaskAllots = projTaskAllots;
	}

	public List<ResourceItem> getResourceItems() {
		if (resourceItems == null) {
			resourceItems = new ArrayList<>();
		}
		return resourceItems;
	}

	public void setResourceItems(List<ResourceItem> resourceItems) {
		this.resourceItems = resourceItems;
	}
}
