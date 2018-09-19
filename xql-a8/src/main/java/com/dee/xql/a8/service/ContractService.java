package com.dee.xql.a8.service;

import com.dee.xql.api.model.Contract;

public interface ContractService {
	public String getPhoneBySummary(Long summaryId);

	public Contract getContractBySummaryId(Long summaryId);

	public boolean sendMsg(Long summaryId);
}
