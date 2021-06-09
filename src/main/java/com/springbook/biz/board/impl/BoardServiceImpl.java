package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.Log4jAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAOSpring boardDAO;
	
	public BoardServiceImpl() {
	}
	
	public void insertBoard(BoardVO vo) {
//		if(vo.getSeq() == 0) {
//			throw new IllegalArgumentException("0번글은 등록할 수 없습니다");
//		}
		// 여기서 두 객체는 같은 vo 객체라 시퀀스가 같은 번호로 등록. 하지만 시퀀스는 프라이머리키가 적용되어 동일한 값이 들어갈 수 없다.
//		boardDAO.insertBoard(vo); 
		boardDAO.insertBoard(vo);
	}
	
	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}
	
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}
	
	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}
	
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}

}
