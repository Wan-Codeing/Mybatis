package com.kh.board.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Reply;
import com.kh.common.Template;
import com.kh.common.model.vo.PageInfo;

public class BoardService {

	private BoardDao dao = new BoardDao();
	
	public int selectListCount() {
		SqlSession sqlSession = Template.getSqlSession();
		
		int listCount = dao.selectListCount(sqlSession);
		
		sqlSession.close();
		
		return listCount;
	}
	
	public ArrayList<Board> selectList(PageInfo pi){
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = dao.selectList(sqlSession, pi);
				
		sqlSession.close();
		
		return list;
	}
	
	public int increaseCount(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int count = dao.increaseCount(sqlSession, boardNo);
		if(count > 0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return count;
	}
	
	public Board selectBoard(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		Board bl = dao.selectBoard(sqlSession, boardNo);
		
		sqlSession.close();
		return bl;
		
	}
	
	public ArrayList<Reply> selectReplyList(int boardNo) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Reply> re = dao.selectReplyList(sqlSession, boardNo);
		
		sqlSession.close();
		
		return re;
	}
	
	public int selectSearchCount(HashMap<String,String> map) {
		SqlSession sqlSession = Template.getSqlSession();
		
		int result = dao.selectSearchCount(sqlSession, map);
		
		sqlSession.close();
		
		return result;
	}
	
	public ArrayList<Board> selectSearchList(HashMap<String, String> map,PageInfo pi) {
		SqlSession sqlSession = Template.getSqlSession();
		
		ArrayList<Board> list = dao.selectSearchList(sqlSession, map, pi);
		
		sqlSession.close();
		
		return list;
	}
}
