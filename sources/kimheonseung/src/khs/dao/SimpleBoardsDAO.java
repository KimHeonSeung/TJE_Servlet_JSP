package khs.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import khs.jdbc.util.*;
import khs.model.*;

public class SimpleBoardsDAO {
	private SimpleBoards getInstance(ResultSet rs) throws SQLException {		
		SimpleBoards obj = new SimpleBoards(
				rs.getInt("board_id"),
				rs.getString("board_name"),
				rs.getInt("article_num"),
				rs.getString("writer_nick"),
				rs.getString("article_title"),
				rs.getTimestamp("write_date"),
				rs.getInt("read_count"),
				rs.getInt("like_count"));
		return obj;
	}
	

	public ArrayList<SimpleBoards> selectSimpleBoardPaging(Connection conn, String user_id, int page) {
		ArrayList<SimpleBoards> result = new ArrayList<>();
		String sql = "select s.* from (select * from simpleBoardNameWithUser order by article_num desc) s where writer_id = ? limit ?, 5";
		
		int startNum = (page-1)*5;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, user_id);
			pstmt.setInt(2, startNum);
			rs = pstmt.executeQuery();
			
			while( rs.next() )
				result.add(getInstance(rs));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	
	public int selectSimpleBoardCount(Connection conn, String user_id) {
		int result = 0;
		String sql = "select count(*) from simpleBoardNameWithUser where writer_id=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);	
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	
	
	
	
	
	public ArrayList<SimpleBoards> selectMySimpleBoardWithName(Connection conn, User user) {
		ArrayList<SimpleBoards> result = new ArrayList<>();
		String sql = "select * from simpleBoardNameWithUser where writer_id=? order by write_date desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, user.getUser_id());
			rs = pstmt.executeQuery();
			
			while( rs.next() )
				result.add(getInstance(rs));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	
	public ArrayList<SimpleBoards> selectThisBoardList(Connection conn, int board_id, int page) {
		ArrayList<SimpleBoards> result = new ArrayList<>();
		String sql = "select s.board_id, board_name, article_num, writer_nick, article_title, write_date, read_count, like_count from board_info b, simpleBoard s where s.board_id = b.board_id and s.board_id=? order by article_num desc limit ?,5";
		
		int startNum = (page-1)*5;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setInt(1, board_id);
			pstmt.setInt(2, startNum);
			rs = pstmt.executeQuery();
			
			while( rs.next() )
				result.add(getInstance(rs));
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
}
	
