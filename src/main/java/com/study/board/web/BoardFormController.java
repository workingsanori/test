package com.study.board.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.study.servlet.IController;

public class BoardFormController implements IController {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		System.out.println("====From====");
		String viewPage = "/WEB-INF/view/Ex02_board/boardForm.jsp";
		System.out.println("폼 : " + viewPage);
		return viewPage;
	}

	
	
}
