package com.dee.xql.proj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dee.xql.api.model.Member;
import com.dee.xql.proj.dao.MemberMapper;
import com.dee.xql.proj.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public Member findByName(String name) {
		return memberMapper.findByName(name);
	}

}
