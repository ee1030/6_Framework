package com.kh.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kh.spring.board.model.service.BoardService;

@Controller // 컨트롤러임을 알려줌 + bean 등록
@SessionAttributes({"loginMember"})
@RequestMapping("/board/*")
public class BoardController {
	
	// Service 객체 의존성 주입
	@Autowired // 등록된 bean 중에서 같은 타입인 bean을 자동적으로 의존성 주입
	private BoardService service;
	
	
}
