package com.gowtham.service;

import com.gowtham.dao.CommentDetailDAO;

public class TestCommentDetail {
	
public static void main(String[] args) {
	CommentDetailService commentDetailService = new CommentDetailService();
	CommentDetailDAO commentDetailDAO = new CommentDetailDAO();
	System.out.println(commentDetailDAO.getComments(25, 12));
//	System.out.println(commentDetailService.getComments(25, 12));
}

}
