package com.kh.board.model.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Reply;
import com.kh.common.model.vo.PageInfo;

public class BoardDao {

	public int selectListCount(SqlSession sqlSession) {
		return sqlSession.selectOne("boardMapper.selectListCount");
	}
	
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi){
		/*
		 * 마이바티스에서는 페이징 처리를 위해 RowBounds라는 클래스를 제공
		 * 
		 * offset : 전체 조회된 게시글에서 몇개의 게시글을 건너 뛰고 조회할건지에 대한 값
		 * 			(즉, 조회를 시작할 행의 값)
		 * ex) boardLimit가 5일 경우
		 * 							offset(건너뛸 숫자)		ㅣ limit(조회할 숫자)
		 * currentPage 1 => 1 ~ 5		  0				ㅣ		5
		 * currentPage 2 => 6 ~ 10 		  5				ㅣ		5
		 * currentPage 3 => 11~ 15 		  5				ㅣ		5
		 */
				
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * 5;
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		
		/*
		 * RowBounds를 객체로 넘겨야 할경우
		 * selectList메소드의 오버로딩된 형태 중 매개변수가 3개인 메소드를 사용해야만 한다.
		 * 딱히 두번째 매개변수 자리에 넘길 값이 없다면 null을 넘기면 된다.
		 */
		return (ArrayList)sqlSession.selectList("boardMapper.selectList", null, rowBounds);
	}
	
	public int increaseCount(SqlSession sqlsession, int boardNo) {
		return sqlsession.update("boardMapper.increaseCount",boardNo);
	}
	
	public Board selectBoard(SqlSession sqlsession, int boardNo) {
		return sqlsession.selectOne("boardMapper.selectBoard",boardNo);
	}
	
	public ArrayList<Reply> selectReplyList(SqlSession sqlsession, int boardNo) {
		return (ArrayList)sqlsession.selectList("boardMapper.selectReplyList",boardNo);
	}
	
	public int selectSearchCount(SqlSession sqlSession, HashMap<String,String> map) {
		return sqlSession.selectOne("boardMapper.selectSearchCount",map);
	}
	
	public ArrayList<Board> selectSearchList(SqlSession sqlSession, HashMap<String,String> map, PageInfo pi){
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage() -1) * 5;
		
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectSearchList",map,rowBounds);
	}
}

