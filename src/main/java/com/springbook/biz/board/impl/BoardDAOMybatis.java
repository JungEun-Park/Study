package com.springbook.biz.board.impl;

import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

// DAO(Data Access Object)

@Repository
public class BoardDAOMybatis {
	@Autowired // @Autowired : 메소드를 컨테이너에서 자동으로 호출
	private SqlSessionTemplate mybatis;
	
	//CRUD 기능의 메소드 구현
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard() 기능 처리");
		mybatis.insert("BoardDAO.insertBoard", vo);
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		mybatis.update("BoardDAO.updateBoard", vo);
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		mybatis.delete("BoardDAO.deleteBoard", vo);
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {  // 시퀀스값에 맞게 하나만 받아오므로 리턴값이 BoardVO 객체(하나)
		System.out.println("===> Mybatis로 getBoard() 기능 처리");
		return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) { // BoardVO 전체 객체를 다 받아오므로 list로 반환
		System.out.println("===> Mybatis로 getBoardList() 기능 처리");
		return mybatis.selectList("BoardDAO.getBoardList", vo);	

	}
	
	

}
