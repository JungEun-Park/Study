package com.springbook.biz.board;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class BoardListVO {
	@XmlElement(name = "board")
	private List<BoardVO> boardList;
	
	public List<BoardVO> getBoardList() {
		return boardList;
	}
	public void setBoardList(List<BoardVO> boardList) {
		this.boardList = boardList;
	}
	

}
