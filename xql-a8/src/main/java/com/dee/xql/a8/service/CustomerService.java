package com.dee.xql.a8.service;

public interface CustomerService {

	/**
	 * 废弃客户
	 * 
	 * @param discardId 废弃客户ID
	 * @param remainId  保留客户ID
	 * @return 操作结果信息
	 */
	String discard(String discardId, String remainId);
}
