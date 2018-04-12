package com.study.board.web;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.study.board.model.Board;
import com.study.board.model.BoardSearch;
import com.study.board.service.BoardServiceImpl;
import com.study.servlet.IController;

public class BoardViewController implements IController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("====View====");
		String bo_no = request.getParameter("bo_no");
		
		if(bo_no == null || bo_no.trim().equals("")) {
			return "redirect:/Ex02_board/voardList.do";
		}
		BoardServiceImpl boardService = new BoardServiceImpl();
		Board board = boardService.getBoard(Integer.parseInt(bo_no));
		
//		String currentPage = request.getParameter("currentPage");
//		System.out.println(currentPage);		
//		if(currentPage != null && !currentPage.trim().equals("")) {
//			boardSearch.setCurrentPage(Integer.parseInt(currentPage));
//		}
		
		request.setAttribute("board", board);
		
		String viewPage =  "/WEB-INF/view/Ex02_board/boardView.jsp";
		System.out.println("뷰 : " + viewPage);
		return viewPage;
	}

}
	
	