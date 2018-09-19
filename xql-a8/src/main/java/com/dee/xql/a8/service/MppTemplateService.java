package com.dee.xql.a8.service;

import com.dee.xql.api.model.MppTemplate;

public interface MppTemplateService {

	MppTemplate findBySummaryId(Long summaryId);

	MppTemplate findByProjectId(String projectId);

	MppTemplate findByContractRegisterSummaryId(Long summaryId);
}
