package khs.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import khs.jdbc.util.*;
import khs.model.*;

public class Total_BoardDAO {
	private Total_Board getInstance(ResultSet rs) throws SQLException {		
		Total_Board obj = new Total_Board(
				rs.getInt("article_num"),
				rs.getInt("board_id"),
				rs.getString("writer_id"),
				rs.getString("writer_nick"),
				rs.getString("article_title"),
				rs.getString("article_content"),
				rs.getTimestamp("write_date"),
				rs.getInt("read_count"),
				rs.getInt("like_count"),
				rs.getInt("del_pw"));
		return obj;
	}
	
	
	public ArrayList<Total_Board> selectAllDesc(Connection conn) {
		ArrayList<Total_Board> result = new ArrayList<>();
		String sql = "select * from total_board order by article_num desc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);						
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
	
	public ArrayList<Total_Board> selectAllAsc(Connection conn) {
		ArrayList<Total_Board> result = new ArrayList<>();
		String sql = "select * from total_board order by article_num asc";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);						
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
	
	
	
	public boolean insert(Connection conn, Total_Board obj) {
		boolean result = false;
		String sql = "insert into total_board values (null, ?, ?, ?, ?, ?, now(), 0, 0, ?)";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, obj.getBoard_id());
			pstmt.setString(2, obj.getWriter_id());
			pstmt.setString(3, obj.getWriter_nick());
			pstmt.setString(4, obj.getArticle_title());
			pstmt.setString(5, obj.getArticle_content());
			if(obj.getDel_pw() == null) {
				pstmt.setNull(6, Types.NULL);
			} else {
				pstmt.setInt(6, obj.getDel_pw());
			}
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
		
	
	public boolean upLikeCount(Connection conn, Integer article_num) {
		boolean result = false;
		String sql = "update total_board set like_count = like_count + 1 where article_num = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, article_num);
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
	public boolean downLikeCount(Connection conn, Integer article_num) {
		boolean result = false;
		String sql = "update total_board set like_count = like_count - 1 where article_num = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, article_num);
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
}
	