package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.common.JDBCUTIL;

// DAO(Data Access Object)

@Repository
public class BoardDAOSpring {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// SQL 명령어들
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board),?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq = ?";
	private final String BOARD_LIST = " select * from board order by seq desc";
	private final String BOARD_LIST_T = "select * from board where title like '%' || ? || '%' order by seq desc";
	private final String BOARD_LIST_C = "select * from board where content like '%' || ? || '%' order by seq desc";

	// CRUD 기능의 메소드 구현
	// 글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		jdbcTemplate.update(BOARD_INSERT, vo.getSeq(), vo.getTitle(), vo.getWriter(), vo.getContent());
	}

	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		jdbcTemplate.update(BOARD_UPDATE, vo.getClass(), vo.getWriter(), vo.getContent());

	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		jdbcTemplate.update(BOARD_DELETE, vo.getSeq());
	}

	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) { // 시퀀스값에 맞게 하나만 받아오므로 리턴값이 BoardVO 객체(하나)
		System.out.println("===> JDBCUtil로 getBoard() 기능 처리");
		Object[] args = { vo.getSeq() };
		return jdbcTemplate.queryForObject(BOARD_GET, args, new BoardRowMapper());
	}

	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) { // BoardVO 전체 객체를 다 받아오므로 list로 반환
		System.out.println("===> JDBCUtil로 getBoardList() 기능 처리");
		Object[] args = {vo.getSearchKeyword()};
		if(vo.getSearchCondition().equals("TITLE")) {			
			return jdbcTemplate.query(BOARD_LIST_T, args, new BoardRowMapper());
		} else if(vo.getSearchCondition().equals("CONTENT")) {
			return jdbcTemplate.query(BOARD_LIST_C, args, new BoardRowMapper());
		}
		return null;
	}

}
