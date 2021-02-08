package com.kh.spring.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring.board.model.dao.BoardDAO;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;

@Service // 서비스임을 알려줌 + bean 등록
public class BoardServiceImpl implements BoardService{

	// DAO 객체 의존성 주입
	@Autowired
	private BoardDAO dao;

	@Override
	public PageInfo getPageInfo(int type, int cp) {
		// 전체 게시글 수 조회
		int listCount = dao.getListCount(type);
		return new PageInfo(cp, listCount, type);
	}

	// 게시글 목록 조회 Service 구현
	@Override
	public List<Board> selectList(PageInfo pInfo) {
		return dao.selectList(pInfo);
	}

	// 게시글 상세조회 Service 구현
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Board selectBoard(int boardNo, int type) {
		// 1) 게시글 상세 조회
		Board temp = new Board();
		temp.setBoardNo(boardNo);
		temp.setBoardCode(type);
		
		Board board = dao.selectBoard(temp);
		
		// 2) 상세 조회 성공 시 조회 수 증가
		if(board != null) {
			int result = dao.increaseReadCount(boardNo);
			
			if(result > 0) { // DB 조회 수 증가 성공 시
				// 먼저 조회된 board의 조회수도 1 증가
				board.setReadCount(board.getReadCount() + 1);
			}
		}
		
		return board;
	}
}
