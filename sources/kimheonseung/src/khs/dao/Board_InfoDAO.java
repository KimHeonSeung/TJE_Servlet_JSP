package khs.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;

import khs.jdbc.util.*;
import khs.model.*;

public class Board_InfoDAO {
	private Board_Info getInstance(ResultSet rs) throws SQLException {		
		Board_Info obj = new Board_Info(
				rs.getInt("board_id"),
				rs.getString("board_name"));
		return obj;
	}
	
	
	public ArrayList<Board_Info> selectAll(Connection conn) {
		ArrayList<Board_Info> result = new ArrayList<>();
		String sql = "select * from board_info";
		
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
	
	private void setPreparedStatement(int index, int value, PreparedStatement pstmt) throws SQLException {
		if((int)(Math.log10(value)+1) >= 0 )
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
	
	public boolean insert(Connection conn, Board_Info obj) {
		boolean result = false;
		String sql = "insert into board_info values (null,?)";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, obj.getBoard_name());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	
	
	
	public boolean delete(Connection conn, Board_Info obj) {
		boolean result = false;
		String sql = "delete from board_info where board_name = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getBoard_name());
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
		
}
	
	/*
	public boolean updatePassword(Connection conn, User obj) {
		boolean result = false;
		String sql = "update member set password = ? where member_id = ?";
		
		PreparedStatement pstmt = null;		
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, obj.getPassword());
			pstmt.setString(2, obj.getMember_id());
			
			result = pstmt.executeUpdate() == 1 ? true : false;
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		Closer.close(pstmt);
		
		return result;
	}
	*/














