package com.kh.spring.member.model.service;

public interface MemberService2 {

   // 인터페이스 내에 작성되는 모든 필드는 public static final 이다.
   // 인터페이스 내에 작성되는 모든 메소드는 묵시적으로 public abstract 이다
	   
	
	/** 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 */
	public abstract int idDupCheck(String memberId);

}