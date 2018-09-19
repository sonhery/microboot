package com.dee.xql.a8.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.a8.dao.MemberMapper;
import com.dee.xql.a8.service.MemberService;
import com.dee.xql.api.model.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Member findById(Long id) {
		return memberMapper.findById(id);
	}

}
