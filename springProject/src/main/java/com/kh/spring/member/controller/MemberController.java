package com.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.member.model.vo.Member;

//@Component // 객체(컴포넌트)를 나타내는 일반적인 타입으로 bean 등록 역할을 함
@Controller // 프레젠테이션 레이어, 웹 애플리케이션에서 전달된 요청 응답을 처리하는 클래스 + bean 등록
@RequestMapping("/member/*")
public class MemberController {

	/** 로그인 화면 전환용 Controller
	 * @return viewName
	 */
	@RequestMapping("login")
	public String loginView() {
		return "member/login";
	}
	
	// ------------------------------------------------------------------------------
	// 로그인 동작 Controller
	
	// 1. HttpServletRequest를 이용한 파라미터 전달 받기
	/*@RequestMapping("loginAction")
	public String loginAction(HttpServletRequest request) {
		// 매개변수에 HttpServletRequest를 작성한 경우
		// 해당 객체를 스프링 컨테이너가 자동으로 주입해줌
		String memberId = request.getParameter("memberId");
		String memberPwd = request.getParameter("memberPwd");
		
		System.out.println("memberId : " + memberId);
		System.out.println("memberPwd : " + memberPwd);
		
		return "redirect:/";
	}*/
	
	// 2. @RequestParam을 이용한 파라미터 전달 방법
	// - request 객체를 이용한 파라미터 전달 어노테이션
	// - 매개변수 앞에 해당 어노테이션을 작성하면, 매개변수에 값이 주입됨.
	
	// [속성]
	// value : 전달 받은 input 태그의 name 속성값
	// required : 파라미터 입력 필수 여부 지정(기본값 true)
	// -> required = true인 파라미터가 존재하지 않는다면 400 Bad Request 에러 발생
	
	// defaultValue : 파라미터 중 일치하는 name 속성 값이 없을 경우에 대입할 값 지정.
	// -> required = false인 경우 주로 사용
	
	/*@RequestMapping(value = "loginAction", method = RequestMethod.POST)
	public String loginAction(@RequestParam("memberId") String memberId,
							  @RequestParam("memberPwd") String memberPwd,
							  @RequestParam(value = "cp", required = false, defaultValue = "1") int cp) {
		
		System.out.println("memberId : " + memberId);
		System.out.println("memberPwd : " + memberPwd);
		System.out.println("cp : " + cp);
		
		return "redirect:/";
	}*/
	
	// 3. @RequestParam 어노테이션 생략
	// - 매개변수명을 전달되는 파라미터 name 속성값과 일치시키면 자동으로 주입됨.
	// ** 어노테이션 코드를 생략할 경우 가독성이 떨어짐.
	/*@RequestMapping("loginAction")
	public String loginAction(String memberId, String memberPwd) {
		System.out.println("memberId : " + memberId);
		System.out.println("memberPwd : " + memberPwd);
		
		return "redirect:/";
	}*/
	
	// 4. @ModelAttribute를 이용한 파라미터 전달
	// 요청 페이지에서 여러 파라미터가 전달 될 때
	// 해당 파라미터가 한 객체의 필드명과 같다면
	// 힐치하는 객체를 하나 생성하여 자동으로 세팅 후 반환
	
	// (주의사항) 전달 받아 값을 세팅할 VO 내부에는
	// 반드시 기본생성자, setter가 작성되어 있어야한다!!
	// + name 속성값과 필드명이 같아야함!!!
	
	// == 커맨드 객체
	/*@RequestMapping("loginAction")
	public String loginAction(@ModelAttribute Member inputMember) {
		System.out.println("memberId : " + inputMember.getMemberId());
		System.out.println("memberPwd : " + inputMember.getMemberPwd());
		
		return "redirect:/";
	}*/
	
	// 5. @ModelAttribute 어노테이션 생략
	@RequestMapping("loginAction")
	public String loginAction(Member inputMember) {
		System.out.println(inputMember);
		
		return "redirect:/";
	}
}