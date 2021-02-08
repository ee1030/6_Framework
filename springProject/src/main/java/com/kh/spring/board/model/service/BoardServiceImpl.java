package com.kh.spring.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDAO;

@Service // 서비스임을 알려줌 + bean 등록
public class BoardServiceImpl implements BoardService{

	// DAO 객체 의존성 주입
	@Autowired
	private BoardDAO dao;
}
