package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import pro.jdbc.Closer;
import pro.model.DetailArticle;

public class DetailArticleDAO {
	private DetailArticle getInstance(ResultSet rs) throws SQLException {
		DetailArticle obj = new DetailArticle(rs.getInt("article_id"), rs.getString("member_id"), rs.getString("name"),
				rs.getString("title"), rs.getString("content"), rs.getTimestamp("write_time"),
				rs.getTimestamp("last_update_time"), rs.getInt("read_count"));
		return obj;
	}

	public DetailArticle selectOne(Connection conn, DetailArticle obj) {
		DetailArticle result = null;
		String sql = "select * from detailArticle where article_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getArticle_id());

			rs = pstmt.executeQuery();
			if (rs.next())
				result = getInstance(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(rs);
		Closer.close(pstmt);

		return result;
	}
//	public int selectThumbCount (Connection conn, DetailArticle obj) {
//		int result = 0;
//		String sql = "select count(*) from article where article_id =?";
//		
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, obj.getArticle_id());
//			
//			rs = pstmt.executeQuery();
//			rs.next();
//			result = rs.getInt(1);
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}
//		
//		Closer.close(rs);
//		Closer.close(pstmt);
//		
//		return result;
//	}

	

	public ArrayList<DetailArticle> selectAll(Connection conn) {
		ArrayList<DetailArticle> result = new ArrayList<>();
		String sql = "select * from detailArticle";

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

	private void setPreparedStatement(int index, int value, PreparedStatement pstmt) throws SQLException {
		if (value == 1 || value == 2)
			pstmt.setInt(index, value);
		else
			pstmt.setNull(index, Types.NULL);
	}

	public boolean insert(Connection conn, DetailArticle obj) {
		boolean result = false;
		String sql = "insert into article values (null,?,?,?,now(),now(),0,0)";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, obj.getMember_id());
			pstmt.setString(2, obj.getTitle());
			pstmt.setString(3, obj.getContent().replaceAll("\n", "<br>"));

			result = pstmt.executeUpdate() == 1 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(pstmt);

		return result;
	}

	public boolean delete(Connection conn, DetailArticle obj) {
		boolean result = false;
		String sql = "delete from article where article_id = ?";

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

	public boolean updateReadCount(Connection conn, DetailArticle obj) {
		boolean result = false;
		String sql = "update article set read_count = read_count + 1 where article_id = ?";

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
//	public boolean updateThumb (Connection conn, DetailArticle obj) {
//		boolean result = false;
//		String sql = "update article set article_heart = ? where article_id = ? and member_id = ?";
//		
//		PreparedStatement pstmt = null;		
//		
//		try {
//			pstmt = conn.prepareStatement(sql);			
//			pstmt.setInt(1, obj.getArticle_heart());			
//			pstmt.setInt(2, obj.getArticle_id());			
//			pstmt.setString(3, obj.getMember_id());			
//			
//			result = pstmt.executeUpdate() == 1 ? true : false;
//			
//		} catch (SQLException e) {			
//			e.printStackTrace();
//		}
//		
//		Closer.close(pstmt);
//		
//		return result;
//	}	

}
