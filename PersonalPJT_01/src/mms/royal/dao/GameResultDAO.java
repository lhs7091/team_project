package mms.royal.dao;

import java.sql.*;

import mms.royal.vo.MemberDTO;
import static mms.royal.db.JdbcUtils.*;

public class GameResultDAO {
/*
create table resultgame(
		id varchar(20) references member(id),				--회원id
		gamename varchar(20),								--게임명
		gamenum int,											--게임참여횟수
		tot_win int,											--총 이긴 횟수
		tot_lose int,										--총 진 횟수
		tot_get_point int,									--누적 획득 포인트
		tot_loss_point int,									--누적 손실 포인트
		CONSTRAINT pri_result PRIMARY KEY (id, gamename)
	);
*/
	//처음 게임 접속
	public boolean firstConnection(MemberDTO dto, String gamename) {
		
		String sqlselect="select * from resultgame where id=? and gamename=?";
		String sqlinsert="insert into resultgame values(?,?,0,0,0,0,0)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sqlselect);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, gamename);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				pstmt = conn.prepareStatement(sqlinsert);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, gamename);
				result=pstmt.executeUpdate();
				if(result>0) return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn,pstmt,rs);
		}
		return false;
	}
	
	//게임 이겼을때
	public boolean gameWin(MemberDTO dto, int money, String gamename) {
		String sql="update resultgame set gamenum=gamenum+1,tot_win=tot_win+1,"
				+ "tot_get_point=tot_get_point+? where id=? and gamename=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, money);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, gamename);
			result=pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(conn,pstmt,null);
		}
		
		return false;
	}
	
	//게임 졌을때
	public boolean gameLoose(MemberDTO dto, int money, String gamename) {
		String sql="update resultgame set gamenum=gamenum+1,tot_lose=tot_lose+1,"
				+ "tot_loss_point=tot_loss_point+? where id=? and gamename=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, money);
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, gamename);
			result=pstmt.executeUpdate();
			if(result>0) {
				return true;
			}
		} catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(conn,pstmt,null);
		}
		
		return false;
		
	}

	public void showGameResult(MemberDTO dto) {
		String sql="select * from resultgame where id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn=getConnection();
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs=pstmt.executeQuery();
			if(rs!=null) {
				System.out.println("game\t총횟수\t승\t패\t총획득포인트\t총손실포인트");
				while(rs.next()) {
					System.out.println(rs.getString(2)+"\t"+rs.getInt(3)+"\t"+
					rs.getInt(4)+"\t"+rs.getInt(5)+"\t"+rs.getInt(6)+"\t\t"+rs.getInt(7));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(conn, pstmt, rs);
		}
		
	}

}
