package khs.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import khs.jdbc.util.*;
import khs.model.*;

public class SimpleBoardWithUserDAO {
	private SimpleBoardWithUser getInstance(ResultSet rs) throws SQLException {		
		SimpleBoardWithUser obj = new SimpleBoardWithUser(
				rs.getInt("board_id"),
				rs.getInt("article_num"),
				rs.getString("writer_id"),
				rs.getString("writer_nick"),
				rs.getString("article_title"),
				rs.getTimestamp("write_date"),
				rs.getInt("read_count"),
				rs.getInt("like_count"));
		return obj;
	}
	
	
	public ArrayList<SimpleBoardWithUser> selectLimit(Connection conn, User user) {
		ArrayList<SimpleBoardWithUser> result = new ArrayList<>();
		String sql = "select * from simpleBoardWithUser where writer_id=? order by article_num desc limit 5";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
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
	
	
	
	
}
	
