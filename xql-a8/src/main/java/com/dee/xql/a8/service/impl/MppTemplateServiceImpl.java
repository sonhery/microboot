package com.dee.xql.a8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.a8.dao.MppTemplateMapper;
import com.dee.xql.a8.service.MppTemplateService;
import com.dee.xql.api.model.MppTemplate;

@Service
public class MppTemplateServiceImpl implements MppTemplateService {

	@Autowired
	private MppTemplateMapper mppTemplateMapper;

	@Override
	public MppTemplate findBySummaryId(Long summaryId) {
		return mppTemplateMapper.findBySummaryId(summaryId);
	}

	@Override
	public MppTemplate findByContractRegisterSummaryId(Long summaryId) {
		return mppTemplateMapper.findByContractRegisterSummaryId(summaryId);
	}

	@Override
	public MppTemplate findByProjectId(String projectId) {
		return mppTemplateMapper.findByProjectId(projectId);
	}

}
