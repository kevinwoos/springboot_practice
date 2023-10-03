package com.fastcampus.ch4.repository;

import org.springframework.data.repository.CrudRepository;

import com.fastcampus.ch4.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {

	
}
