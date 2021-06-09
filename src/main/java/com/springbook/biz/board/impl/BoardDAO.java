package com.springbook.biz.board.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.*;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.common.JDBCUTIL;

// DAO(Data Access Object)

@Repository("boardDAO")
public class BoardDAO {
	// JDBC 관련 변수
	private Connection conn = null; // 연결객체
	private PreparedStatement stmt = null; // 쿼리문
	private ResultSet rs = null; // 결과값 담을 객체
	
	//SQL 명령어들
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0)+1 from board),?,?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=?, writer=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET= "select * from board where seq=?";
	private final String BOARD_LIST = " select * from board order by seq desc";
	private final String BOARD_LIST_T = "select * from board where title like '%' || ? || '%' order by seq desc";
	private final String BOARD_LIST_C = "select * from board where content like '%' || ? || '%' order by seq desc";
	
	//CRUD 기능의 메소드 구현
	//글 등록
	public void insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			conn = JDBCUTIL.getConnection(); // 제일 먼저 커넥션(연결객체로 연결)
			stmt = conn.prepareStatement(BOARD_INSERT); // 쿼리문
			stmt.setString(1, vo.getTitle()); //변수에 ?에 해당하는 인자값 넣기
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate(); // 쿼리문 실행
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUTIL.close(stmt, conn); // 쿼리문과 연결객체만 사용했기 때문에 두개만 끊어주면 됨
		}
	}
	
	// 글 수정
	public void updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn = JDBCUTIL.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setString(3, vo.getWriter());
			stmt.setInt(4, vo.getSeq());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUTIL.close(stmt, conn);
		}
	}
	
	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUTIL.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate(); 
			// db변경이 있으면 excuteUpdate
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUTIL.close(stmt, conn);
		}
	}
	
	// 글 상세 조회
	public BoardVO getBoard(BoardVO vo) {  // 시퀀스값에 맞게 하나만 받아오므로 리턴값이 BoardVO 객체(하나)
		System.out.println("===> JDBCUtil로 getBoard() 기능 처리");
		BoardVO board = null;
		try {
			conn = JDBCUTIL.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUTIL.close(rs, stmt, conn); // 조회부터는 받아와야하는 결과값(리절트셋)이 있으므로 세개 닫아줌
		}
		return board;
	}
	
	// 글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo) { // BoardVO 전체 객체를 다 받아오므로 list로 반환
		System.out.println("===> JDBCUtil로 getBoardList() 기능 처리");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUTIL.getConnection();
			if(vo.getSearchCondition().equals("TITLE")) {
				stmt = conn.prepareStatement(BOARD_LIST_T);
			}else if(vo.getSearchCondition().equals("CONTENT")) {
				stmt = conn.prepareStatement(BOARD_LIST_C);
			}
			stmt.setString(1, vo.getSearchKeyword());
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUTIL.close(rs, stmt, conn);
		}
		return boardList;
	}
	
	

}
