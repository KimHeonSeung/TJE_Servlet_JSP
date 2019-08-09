package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pro.jdbc.Closer;
import pro.model.SimpleArticle;

public class SimpleArticleDAO {
	private SimpleArticle getInstance(ResultSet rs) throws SQLException {		
		SimpleArticle obj = new SimpleArticle(
				rs.getInt("article_id"),
				rs.getString("title"),
				rs.getString("member_id"),
				rs.getString("name"),
				rs.getTimestamp("write_time"),
				rs.getInt("read_count"),
				rs.getInt("comment_count"));
		return obj;
	}
	public ArrayList<SimpleArticle> selectarticlePage(Connection conn, int page_number, int page_divide) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		int start = (page_number - 1) * page_divide;
		int end = page_divide;
		String sql = "select * from simpleArticle limit " + start + " ," + end;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next())
				result.add(getInstance(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(rs);
		Closer.close(pstmt);

		return result;
	}
	public int selectAllCount(Connection conn) {
		int result = 0;
		String sql = "select count(*) from simpleArticle";
		
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
	
	public ArrayList<SimpleArticle> selectAll(Connection conn) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = "select * from simpleArticle";
		
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
	
	public ArrayList<SimpleArticle> select(Connection conn, String searchItem, String searchValue) {
		ArrayList<SimpleArticle> result = new ArrayList<>();
		String sql = 
			String.format(
					"select * from simpleArticle where %s like ?", 
					searchItem);
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, "%" + searchValue + "%");
			
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













