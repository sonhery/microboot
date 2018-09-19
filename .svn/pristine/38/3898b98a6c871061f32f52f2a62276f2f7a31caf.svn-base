package com.dee.xql.a8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.a8.dao.CustomerMapper;
import com.dee.xql.a8.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public String discard(String discardId, String remainId) {
		return customerMapper.discardCustomer(discardId, remainId);
	}

}
