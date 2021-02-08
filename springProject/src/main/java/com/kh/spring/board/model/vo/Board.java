package com.kh.spring.board.model.vo;

import java.sql.Timestamp;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String memberId;
	private int readCount;
	private String categoryName;
	private Timestamp boardCreateDate;
	private Timestamp boardModifyDate;
	private String boardStatus;
	private int boardCode;
	private String boardName;
	
	public Board() {}

	public Board(int boardNo, String boardTitle, String boardContent, String memberId, int readCount,
			String categoryName, Timestamp boardCreateDate, Timestamp boardModifyDate, String boardStatus,
			int boardCode, String boardName) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.memberId = memberId;
		this.readCount = readCount;
		this.categoryName = categoryName;
		this.boardCreateDate = boardCreateDate;
		this.boardModifyDate = boardModifyDate;
		this.boardStatus = boardStatus;
		this.boardCode = boardCode;
		this.boardName = boardName;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Timestamp getBoardCreateDate() {
		return boardCreateDate;
	}

	public void setBoardCreateDate(Timestamp boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}

	public Timestamp getBoardModifyDate() {
		return boardModifyDate;
	}

	public void setBoardModifyDate(Timestamp boardModifyDate) {
		this.boardModifyDate = boardModifyDate;
	}

	public String getBoardStatus() {
		return boardStatus;
	}

	public void setBoardStatus(String boardStatus) {
		this.boardStatus = boardStatus;
	}

	public int getBoardCode() {
		return boardCode;
	}

	public void setBoardCode(int boardCode) {
		this.boardCode = boardCode;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", memberId=" + memberId + ", readCount=" + readCount + ", categoryName=" + categoryName
				+ ", boardCreateDate=" + boardCreateDate + ", boardModifyDate=" + boardModifyDate + ", boardStatus="
				+ boardStatus + ", boardCode=" + boardCode + ", boardName=" + boardName + "]";
	}
}
