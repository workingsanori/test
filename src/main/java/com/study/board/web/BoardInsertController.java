package com.study.board.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.board.model.Board;
import com.study.board.service.BoardServiceImpl;
import com.study.servlet.IController;

public class BoardInsertController implements IController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("====Insert====");
		
		Board board = new Board();
		
		try {
			BeanUtils.populate(board, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		board.setBo_ip(request.getRemoteAddr());
		BoardServiceImpl boardService = new BoardServiceImpl();
		int cnt = boardService.registBoard(board);
		System.out.println("cnt : "+cnt);
		if(cnt >0) {
			request.setAttribute("message", ">>>>성공<<<<");
		}else {
			request.setAttribute("message", "실패!!!!");
		}
		request.setAttribute("board", board);
		return "/WEB-INF/view/Ex02_board/boardInsert.jsp";
	}

}
