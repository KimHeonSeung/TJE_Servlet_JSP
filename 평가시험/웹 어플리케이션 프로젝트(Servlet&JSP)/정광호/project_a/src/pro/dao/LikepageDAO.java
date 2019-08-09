package pro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;

import pro.jdbc.Closer;
import pro.model.Member;
import pro.model.Likepage;

public class LikepageDAO {
	
	private Likepage getInstance(ResultSet rs) throws SQLException {		
		Likepage obj = new Likepage(
				rs.getString("member_id"),
				rs.getString("title"),
				rs.getString("likepage")
				);
		return obj;
	}
	
	public Likepage selectOne(Connection conn, Likepage obj) {
		Likepage result = null;
		String sql = "select * from likepage where member_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			
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
	
	public ArrayList<Likepage> selectAll(Connection conn) {
		ArrayList<Likepage> result = new ArrayList<>();
		String sql = "select * from likepage";
		
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
	
	public ArrayList<Likepage> selectpage(Connection conn, Likepage obj) {
		ArrayList<Likepage> result = new ArrayList<>();
		String sql = "select * from likepage where member_id= ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, obj.getMember_id());
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
	
	private void setPreparedStatement(int index, int value, PreparedStatement pstmt) throws SQLException {
		if( value != 0 )
			pstmt.setInt(index, value);
		else
			pstmt.setNull(index, Types.NULL);
	}

	private void setPreparedStatement(int index, String value, PreparedStatement pstmt) throws SQLException  {
		if( value != null && value.length() > 0 )
			pstmt.setString(index, value);
		else
			pstmt.setNull(index, Types.NULL);
	}
	
	private void setPreparedStatement(int index, Date value, PreparedStatement pstmt) throws SQLException  {
		if( value != null )			
			pstmt.setTimestamp(index, 
					new java.sql.Timestamp(value.getTime()));
		else
			pstmt.setNull(index, Types.NULL);
	}
	
	public boolean insert(Connection conn, Likepage obj) {
		boolean result = false;
		String sql = "insert into likepage values (?,?,?)";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getMember_id());
			setPreparedStatement(2, obj.getTitle(),pstmt);
			setPreparedStatement(3, obj.getLikepage(),pstmt);
			
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
		
	
	public boolean updatePassword(Connection conn, Likepage obj) {
		boolean result = false;
		String sql = "update likepage set password = ? where member_id = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(2, obj.getMember_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
}













