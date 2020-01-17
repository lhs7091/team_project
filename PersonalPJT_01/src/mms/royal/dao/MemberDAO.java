package mms.royal.dao;

import java.sql.*;

import static mms.royal.db.JdbcUtils.*;
import mms.royal.vo.MemberDTO;

public class MemberDAO {
	
	/**
	 * 로그인 시 회원 정보 조회
	 */

	public boolean checkMember(MemberDTO dto) {
		
		String sql = "select * from member where id=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		try {
			conn = getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
		
			if(rs.next()) {
				check = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	/**
	 * 회원 가입시 insert 구문
	 */
	
	public boolean insertMember(MemberDTO newMember) {
		
		String sql = "insert into member values(?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean check = false;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMember.getId());
			pstmt.setString(2, newMember.getPassword());
			pstmt.setString(3, newMember.getName());
			pstmt.setString(4, newMember.getPhone());
			pstmt.setString(5, newMember.getAccount());
			pstmt.setString(6, newMember.getEmail());
			pstmt.setInt(7, newMember.getGameMoney());
			result = pstmt.executeUpdate();
			if(result>0) {
				check=true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, null);
		}
		return check;
	}
	
	/**
	 * 회원정보 조회(자기 정보만 조회됨)
	 */

	public MemberDTO selectMember(MemberDTO dto) {
		String sql = "select * from member where id=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//connection 연결
			conn = getConnection();
			//쿼리생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return new MemberDTO(rs.getString(1),
						rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6), rs.getInt(7)); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		
		return null;
	}
	
	/* 포인트 충전을 위한 SQL 문*/
	public boolean pointCharge(int money, MemberDTO dto) {
		String sql = "update member set gamemoney=gamemoney+? where id=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean check = false;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPassword());
			result = pstmt.executeUpdate();
			
			if(result>0) {
				check = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, null);
		}
		
		return check;
	}

	public boolean removeMember(MemberDTO dto) {
		
		String sql = "delete from member where id=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean check = false;
		int result=0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			result=pstmt.executeUpdate();
			
			if(result>0) {
				check=true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, null);
		}
		
		return check;
	}

	public boolean checkId(String id) {
		String sql = "select * from member where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		
		try {
			conn = getConnection();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		
		return check;
	}

	public boolean pointReturn(int money, MemberDTO dto) {
		String sql = "update member set gamemoney=gamemoney-? where id=? and password=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean check = false;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,money);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getPassword());
			result = pstmt.executeUpdate();
			
			if(result>0) {
				check = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,null);
		}
		
		return check;
	}
	
	//비밀번호 변경 전 id 및 email 유효성 확인
	public boolean checkMemberEmail(MemberDTO dto) {
		String sql = "select * from member where id=? and email=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean check = false;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getEmail());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}
		return check;
	}

	public boolean modifyMember(String password, MemberDTO dto) {
		String sql = "update member set password=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		boolean check = false;
		
		try {
			conn = getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, dto.getId());
			result = pstmt.executeUpdate();
			
			if(result>0) {
				check = true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,null);
		}
		
		return check;
	}

	public void gamePoint(int money, MemberDTO dto) {
		String sql = "update member set gamemoney=? where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		//int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, money);
			pstmt.setString(2, dto.getId());
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}



}
