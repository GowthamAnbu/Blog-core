package com.gowtham.service;

import com.gowtham.dao.CommentDetailDAO;
import com.gowtham.dao.CommentDetailDAOInterface;

public class TestCommentDetail {
	
public static void main(String[] args) {
	CommentDetailDAOInterface commentDetailDAO = new CommentDetailDAO();
	System.out.println(commentDetailDAO.getComments(25));
}

}
