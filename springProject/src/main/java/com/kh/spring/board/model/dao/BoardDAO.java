package com.kh.spring.board.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // 저장소(DB)와 연결 객체임을 알려줌 + bean 등록
public class BoardDAO {

	// 마이바티스를 이용한 DB 연결 객체를 의존성 주입
	@Autowired
	private SqlSessionTemplate sqlSession;
}
