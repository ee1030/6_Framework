package com.kh.spring.member.model.service;

import java.util.Map;

import com.kh.spring.member.model.vo.Member;

public interface MemberService2 {

   // 인터페이스 내에 작성되는 모든 필드는 public static final 이다.
   // 인터페이스 내에 작성되는 모든 메소드는 묵시적으로 public abstract 이다
	   
	
	/** 아이디 중복 검사 Service
	 * @param memberId
	 * @return result
	 */
	public abstract int idDupCheck(String memberId);

	/** 회원가입 Service
	 * @param signUpMember
	 * @return result
	 */
	public abstract int signUp(Member signUpMember);

	/** 회원 정보 수정 Service
	 * @param updateMember
	 * @return result
	 */
	public abstract int updateAction(Member updateMember);

	/** 비밀번호 변경 Service
	 * @param map
	 * @return result
	 */
	public abstract int updatePwd(Map<String, Object> map);

	/** 회원탈퇴 Service
	 * @param map
	 * @return result
	 */
	public abstract int deleteMember(Member loginMember);

}
