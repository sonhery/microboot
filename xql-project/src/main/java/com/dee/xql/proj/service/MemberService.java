package com.dee.xql.proj.service;

import com.dee.xql.api.model.Member;

public interface MemberService {

	Member findByName(String name);
}
