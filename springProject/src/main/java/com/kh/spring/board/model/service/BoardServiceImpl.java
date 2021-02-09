package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

	// 게시글 등록 Service 구현
	@Transactional(rollbackFor = Exception.class)
	@Override
	public int insertBoard(Map<String, Object> map, List<MultipartFile> images, String savePath) {
		int result = 0; // 최종 결과 저장 변수 선언
		
		// 1) 게시글 번호 얻어오기
		int boardNo = dao.selectNextNo();
		
		// 2) 게시글 삽입
		if(boardNo > 0) { // 다음 게시글 번호를 얻어온 경우
			map.put("boardNo", boardNo); // map에 boardNo 추가
			
			// 크로스 사이트 스크립팅 방지 처리
			// 추후 summernote api 사용을 염두하여
			// 게시판 타입별로 크로스 사이트 스크립팅 방지 처리를 선택적으로 진행
			if((int)map.get("boardType") == 1) { // 자유게시판인 경우
				String boardTitle = (String)map.get("boardTitle");
				String boardContent = (String)map.get("boardContent");
				
				// 크로스 사이트 스크립팅 방지 처리 적용
				boardTitle = replaceParameter(boardTitle);
				boardContent = replaceParameter(boardContent);
				
				// 처리된 문자열을 다시 map에 세팅
				map.put("boardTitle", boardTitle);
				map.put("boardContent", boardContent);
				
				// 개행문자 처리 -> 화면에서 JSTL을 이용해서 처리할 예정
			}
			
			// 게시글 삽입 DAO 메소드 호출
			result = dao.insertBoard(map);
			
			if(result > 0) {
				result = boardNo;
			}
			
			// 3) 게시글 삽입 성공 시 이미지 정보 삽입
		}
		
		
		return result;
	}
	
	private String replaceParameter(String param) {
		String result = param;
		if(param != null) {
			result = result.replaceAll("&", "&amp;");
			result = result.replaceAll("<", "&lt;");
			result = result.replaceAll(">", "&gt;");
			result = result.replaceAll("\"", "&quot;");
		}
		
		return result;
	}

}
