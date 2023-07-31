package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Reply;

/**
 * Servlet implementation class BoardDetailView
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 조회수 증가 서비스
		BoardService bService = new BoardService();
		
		int result = bService.increaseCount(boardNo);
		
		if(result > 0) { // 유효한 게시글 => 게시글정보, 첨부파일 정보 조회
			
			Board b = bService.selectBoard(boardNo);
			ArrayList<Reply> list = new BoardService().selectReplyList(boardNo);
			
			request.setAttribute("list", list);
			
			request.setAttribute("b", b);
			
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
		}else { // 무효한 게시글
			request.setAttribute("errorMsg", "감히 접근 할 수 없습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
		}
		// 게시글 딸린 댓글 조회하는 서비스
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
