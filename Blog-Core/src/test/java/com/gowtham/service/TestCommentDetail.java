package com.gowtham.service;

import com.gowtham.dao.CommentDetailDAO;

public class TestCommentDetail {
	
public static void main(String[] args) {
	CommentDetailDAO commentDetailDAO = new CommentDetailDAO();
	System.out.println(commentDetailDAO.getComments(25));
}

}
