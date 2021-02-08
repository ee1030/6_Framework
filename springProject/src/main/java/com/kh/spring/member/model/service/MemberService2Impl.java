package com.kh.spring.member.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.member.model.dao.MemberDAO2;
import com.kh.spring.member.model.vo.Member;

@Service
public class MemberService2Impl implements MemberService2 {

	@Autowired
	private MemberDAO2 dao;
	
	// 암호화를 위한 객체를 의존성 주입(DI)
	@Autowired
	private BCryptPasswordEncoder enc;

	// 아이디 중복 체크 Service 구현
	@Override
	public int idDupCheck(String memberId) {
		return dao.idDupCheck(memberId);
	}
	
	/* 스프링에서 트랜잭션 처리를 하는 방법
	 * 1. 코드 기반 처리 방법 -> 기존 방법
	 * 2. 선언적 트랜잭션 처리 방법
	 *  1) <tx:advice> XML 작성 방법
	 *  2) @Transactional 어노테이션 작성 방법
	 *  * 트랜잭션 처리를 위한 매니저가 bean으로 등록되어 있어야 함.
	 * 
	 * @Transactional이 commit/rollback을 하는 기준
	 * -> 해당 메소드 내에서 RuntimeException이 발생하면 rollback, 발생하지 않으면 commit
	 * -> 발생하는 예외의 기준을 바꾸는 방법 : rollbackFor 속성을 사용.
	 */

	// 회원가입 Service 구현
	@Transactional(rollbackFor = Exception.class) // 아무 예외나 발생 시 rollback
	@Override
	public int signUp(Member signUpMember) {
		// 암호화 추가 예정
		/*
		 * 1. 평문 형태 그대로 저장 -> 범죄행위
		 * 
		 * 2. SHA-512 같은 단방향 암호화(단방향 해쉬함수)를 사용
		 * 	-> 같은 비밀번호를 암호화 하면 똑같은 다이제스트가 반환 된다는 문제점이 있음. (해킹에 취약)
		 * 	(암호화된 비밀번호 == 다이제스트)
		 * 	(일반적인 해킹 장비 성능으로 1초에 56억개의 다이제스트를 비교할 수 있음(참고))
		 * 
		 * 3. bcrypt 방식의 암호화
		 * 	-> 비밀번호 암호화에 특화된 암호화 방식
		 * 	-> 입력된 문자열과 임의의 문자열(salt)을 첨부하여 암호화를 진행
		 * 		--> 같은 비밀번호를 입력해도 서로 다른 다이제스트가 반환됨.
		 * 
		 * ** Spring-security-core 모듈 추가!
		 * 
		 */
		
		// DAO 전달 전에 비밀번호 암호화
		String encPwd = enc.encode(signUpMember.getMemberPwd());
		
		// 암호화된 비밀번호를 signUpMember에 다시 세팅
		signUpMember.setMemberPwd(encPwd);
		
		return dao.signUp(signUpMember);
		
		// 해당 서비스 메소드에서 예외가 발생하지 않으면 마지막에 commit이 수행 됨.
	}

	// 회원 정보 수정 Service 구현
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updateAction(Member updateMember) {
		return dao.updateAction(updateMember);
	}

	// 비밀번호 변경 Service 구현
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int updatePwd(Map<String, Object> map) {
		// 현재 비밀번호, 새 비밀번호, 회원 번호
		
		// 1. 현재 비밀번호가 일치하는지 확인(본인 확인)
		// bcrypt 암호화가 적용되어 있기 때문에
		// DB에서 비밀번호를 조회해서 비교해야 함. == 현재 비밀번호 조회 DAO 필요
		String savePwd = dao.selectPwd((int)map.get("memberNo"));
		
		// 결과 저장용 변수 선언
		int result = 0;
		
		if(savePwd != null) { // 비밀번호 조회 성공시
			// 비밀번호 확인
			if(enc.matches((String)map.get("memberPwd"), savePwd)) {
				// 비밀번호가 일치할 경우
				
				// 2. 현재 비밀번호 일치 확인 시 새 비밀번호로 변경
				// == 비밀번호를 수정할 dao 필요
				
				// 새 비밀번호 암호화
				String encPwd = enc.encode((String)map.get("newPwd"));
				
				// 암호화된 비밀번호를 다시 map에 세팅
				map.put("newPwd", encPwd);
				
				// 비밀번호 수정 DAO 호출
				result = dao.updatePwd(map);
			}
		}
		
		
		
		return result;
	}

	// 회원탈퇴 Service 구현
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int deleteMember(Member loginMember) {
		String savePwd = dao.selectPwd(loginMember.getMemberNo());
		
		int result = 0;
		
		if(savePwd != null) { // 비밀번호 조회 성공시
			// 비밀번호 확인
			if(enc.matches(loginMember.getMemberPwd(), savePwd)) {
				// 비밀번호가 일치할 경우
				
				// 회원탈퇴 DAO 호출
				result = dao.deleteMember(loginMember);
			}
		}
		
		return result;
	}

}
