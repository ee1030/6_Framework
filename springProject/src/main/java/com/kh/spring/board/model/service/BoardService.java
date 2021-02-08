package com.kh.spring.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.PageInfo;

public interface BoardService {

	/** 게시글 수 조회 Service
	 * @param type
	 * @param cp
	 * @return pInfo
	 */
	public abstract PageInfo getPageInfo(int type, int cp);

	/** 게시글 목록 조회 Service
	 * @param pInfo
	 * @return bList
	 */
	public abstract List<Board> selectList(PageInfo pInfo);

	/** 게시글 상세조회 Service
	 * @param boardNo
	 * @param type 
	 * @return board
	 */
	public abstract Board selectBoard(int boardNo, int type);

	/** 게시글 등록 Service
	 * @param map
	 * @return result
	 */
	public abstract int insertBoard(Map<String, Object> map);

}
