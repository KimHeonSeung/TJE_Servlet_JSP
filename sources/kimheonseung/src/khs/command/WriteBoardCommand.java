package khs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.x.ResultMessageListener;
import com.sun.xml.internal.ws.api.ha.HaInfo;

import java.sql.*;
import java.util.HashMap;

import khs.jdbc.util.*;
import khs.model.*;
import khs.service.*;

public class WriteBoardCommand extends Command {
	private String formPage = "/WEB-INF/forms/write_board.jsp";
	private String submitPage = "/WEB-INF/submits/write_board.jsp";
	private String errorPage = "/WEB-INF/errors/write_board.jsp";

	private Total_BoardService tbService = new Total_BoardService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		String strBoard_id = request.getParameter("board_id");
		Integer board_id = null;
		
		try {
			board_id = Integer.parseInt(strBoard_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("board_id", board_id);
		
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strBoard_id = request.getParameter("board_id");
		Integer board_id = null;
		try {
			board_id = Integer.parseInt(strBoard_id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		String writer_id = request.getParameter("writer_id");
		String writer_nick = request.getParameter("writer_nick");
		String article_title = request.getParameter("article_title");
		String article_content = request.getParameter("article_content");
		String strDel_pw = request.getParameter("del_pw");
		Integer del_pw = null;
		try {
			del_pw = Integer.parseInt(strDel_pw);
		}catch (Exception e) {
			del_pw = null;
		}
		
		Total_Board total_Board = new Total_Board(0, board_id, writer_id, writer_nick, article_title, article_content, null, 0, 0, del_pw);
		
		boolean result = false;
		
		try(Connection conn = ConnectionProvider.getConnection()) {
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("total_Board", total_Board);
			
			HashMap<String, Object> resultMap = tbService.service(values);
			
			request.setAttribute("board_id", board_id);
			result = (boolean)resultMap.get("result");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result == true) {
			return submitPage;
		} else {
			return errorPage;
		}
		
	}

}
