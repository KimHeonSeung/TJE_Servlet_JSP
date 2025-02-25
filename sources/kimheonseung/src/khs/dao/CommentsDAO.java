package khs.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import khs.jdbc.util.*;
import khs.model.*;

public class CommentsDAO {
	private Comments getInstance(ResultSet rs) throws SQLException {		
		Comments obj = new Comments(
				rs.getInt("comment_id"),
				rs.getInt("article_num"),
				rs.getString("user_id"),
				rs.getString("user_nick"),
				rs.getString("content"),
				rs.getTimestamp("write_time"));
		return obj;
	}
	
	
	
	public ArrayList<Comments> selectCommentMyPaging(Connection conn, String user_id, int page) {
		ArrayList<Comments> result = new ArrayList<>();
		String sql = "select s.* from (select * from comments order by write_time desc) s where user_id = ? limit ?, 5";
		
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
	
	
	
	public int selectCommentMyCount(Connection conn, String user_id) {
		int result = 0;
		String sql = "select count(*) from comments where user_id=?";
		
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
	
	
	
	
	public int selectLastInsertID(Connection conn) {
		int result = 0;
		// 최근에 추가된 기본키값을 추출
		String sql = "select LAST_INSERT_ID()";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
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
	
	
	
	public int selectCount(Connection conn, Comments obj) {
		int result = 0;
		String sql = "select count(*) from comments where article_num = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getArticle_num());
			
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
	
		
	public Comments selectOne(Connection conn, Comments obj) {
		Comments result = null;
		String sql = "select * from comments where comment_id = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getComment_id());
			
			rs = pstmt.executeQuery();
			if( rs.next() )
				result = getInstance(rs);
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	public ArrayList<Comments> selectMyAll(Connection conn, User obj) {
		ArrayList<Comments> result = new ArrayList<>();
		String sql = "select * from comments where user_id = ? order by write_time desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setString(1, obj.getUser_id());
			
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
	
	
	
	
	public ArrayList<Comments> selectMy(Connection conn, User obj) {
		ArrayList<Comments> result = new ArrayList<>();
		String sql = "select * from comments where user_id = ? order by write_time asc limit 5";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setString(1, obj.getUser_id());
			
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
	
	
	
	public ArrayList<Comments> selectAll(Connection conn, Comments obj) {
		ArrayList<Comments> result = new ArrayList<>();
		String sql = "select * from comments where article_num = ? order by write_time asc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			
			pstmt.setInt(1, obj.getArticle_num());
			
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
	
	
	
	public boolean insert(Connection conn, Comments obj) {
		boolean result = false;
		String sql = "insert into total_comment values (null,?,?,?,now(), null, null, null)";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, obj.getArticle_num());
			pstmt.setString(2, obj.getUser_id());
			pstmt.setString(3, obj.getContent());			
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
	
	public boolean delete(Connection conn, Comments obj) {
		boolean result = false;
		String sql = "delete from total_comment where comment_id = ?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, obj.getComment_id());			
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
}
	

