package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import pro.jdbc.Closer;
import pro.model.*;

public class ThumbDAO {
	private Thumb getInstance(ResultSet rs) throws SQLException {
		Thumb obj = new Thumb(rs.getString("member_id"), rs.getInt("article_id"), rs.getInt("comment_id"),
				rs.getInt("article_thumb"), rs.getInt("comment_thumb"));
		return obj;
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

	public int selectCount(Connection conn, Thumb obj) {
		int result = 0;
		String sql = "select count(*) from thumb where article_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getArticle_id());

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

	public Thumb selectOne(Connection conn, Thumb obj) {
		Thumb result = null;
		String sql = "select * from thumb where comment_id = ?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, obj.getComment_id());

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

	public Thumb selectOneArticleThumb(Connection conn, Thumb obj) {
		Thumb result = null;
		String sql = "select * from thumb where member_id = ? and article_id =? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			pstmt.setInt(2, obj.getArticle_id());
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

	public int selectArticleCount(Connection conn) {
		int result = 0;
		String sql = "select count(*) from thumb where article_thumb = 1 group by article_id";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
		}

		Closer.close(rs);
		Closer.close(pstmt);

		return result;
	}

	public ArrayList<Thumb> selectAll(Connection conn, Comment obj) {
		ArrayList<Thumb> result = new ArrayList<>();
		String sql = "select * from thumb where article_id = ? order by write_time asc";

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, obj.getArticle_id());

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

	public boolean insert(Connection conn, Thumb obj) {
		boolean result = false;
		String sql = "insert into thumb values (?,?,null,null,null)";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, obj.getMember_id());
			pstmt.setInt(2, obj.getArticle_id());

			result = pstmt.executeUpdate() == 1 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(pstmt);

		return result;
	}

	public boolean insertArticleThumb(Connection conn, Thumb obj) {
		boolean result = false;
		String sql = "insert into thumb values (?,?,?,?,0)";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, obj.getArticle_id());
			pstmt.setString(2, obj.getMember_id());
//			pstmt.setString(3, obj.getContent());

			result = pstmt.executeUpdate() == 1 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(pstmt);

		return result;
	}

	public boolean delete(Connection conn, Thumb obj) {
		boolean result = false;
		String sql = "delete from thumb where comment_id = ?";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);

//			pstmt.setInt(1, obj.getComment_id());			

			result = pstmt.executeUpdate() == 1 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(pstmt);

		return result;
	}

	public boolean updateArticleThumb(Connection conn, Thumb obj) {
		boolean result = false;
		String sql = "update thumb set article_thumb = ? where member_id = ? and article_id = ? ";

		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, obj.getArticle_thumb());
			pstmt.setString(2, obj.getMember_id());
			pstmt.setInt(3, obj.getArticle_id());

			result = pstmt.executeUpdate() == 1 ? true : false;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		Closer.close(pstmt);

		return result;
	}
}
