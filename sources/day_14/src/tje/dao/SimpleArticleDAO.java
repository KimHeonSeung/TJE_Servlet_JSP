package tje.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import tje.jdbc.util.*;
import tje.model.*;

public class SimpleArticleDAO {
	private SimpleArticle getInstance(ResultSet rs) throws SQLException {
		SimpleArticle obj = new SimpleArticle(
				rs.getInt("article_id"),
				rs.getString("title"), 
				rs.getString("member_id"), 
				rs.getString("name"),
				rs.getTimestamp("write_time"),
				rs.getInt("read_count"));
		return obj;
	}
	
	public ArrayList<SimpleArticle> selectAll(Connection conn) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = "select * from simpleArticle";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(getInstance(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	public ArrayList<SimpleArticle> selectLimit(Connection conn, Member member) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = "select * from simpleArticle where member_id=? order by article_id desc limit 5";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(getInstance(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	
	
	public ArrayList<SimpleArticle> select(Connection conn, String searchItem, String searchValue) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = 
				String.format("select * from simpleArticle where %s like ?", searchItem);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchValue + "%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result.add(getInstance(rs));
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	

	public int selectCount(Connection conn) {
		int result = 0;
		String sql = String.format("select count(*) from simpleArticle");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Closer.close(rs);
		Closer.close(pstmt);
		
		return result;
	}
	
	public boolean delete(Connection conn, SimpleArticle obj) {
		boolean result = false;
		String sql = "delete from article where article_id=?";
		
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getArticle_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
	
}
