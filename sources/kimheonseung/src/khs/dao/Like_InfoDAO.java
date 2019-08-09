package khs.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import khs.jdbc.util.*;
import khs.model.*;

public class Like_InfoDAO {
	private Like_Info getInstance(ResultSet rs) throws SQLException {		
		Like_Info obj = new Like_Info(
				rs.getString("user_id"),
				rs.getInt("article_num"));
		return obj;
	}
	
	
	
	public boolean like_ok(Connection conn, Like_Info obj) {
		boolean result = false;
		String sql = "insert into like_info values (?,?)";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, obj.getUser_id());
			pstmt.setInt(2, obj.getArticle_num());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
	
	public boolean like_cancle(Connection conn, Like_Info obj) {
		boolean result = false;
		String sql = "delete from like_info where user_id=? and article_num=?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, obj.getUser_id());
			pstmt.setInt(2, obj.getArticle_num());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
		
	
	
	public boolean like_check(Connection conn, Like_Info obj) {
		boolean result = false;
		String sql = "select 1 from like_info where user_id=? and article_num=?";
		
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, obj.getUser_id());
			pstmt.setInt(2, obj.getArticle_num());
			
			rs = pstmt.executeQuery();
			if( rs.next() ) {
				result = true;
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
}
	















