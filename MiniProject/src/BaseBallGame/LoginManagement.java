package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginManagement {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	Member mb = new Member();

	// eseuteUpdate()�� ����� ������ �ִ� ����
	int result = 0;

	// sql���� �����ϴ� ����
	String sql;

	// �����ϴ� �޼ҵ�
	public void dbConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB����
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "cgi_2_2_1022";
			String password = "smhrd2";
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			// Exception
			System.out.println("����Ȯ��");
			e.printStackTrace();
		}
	}

	// ������ �޼ҵ�s
	public void dbClose() {

		try {

			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public int register(String id, String pw) {

		dbConn();

		sql = "insert into g_user(id,pw,score) values (?,?,?)";

		int score = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			psmt.setInt(3, score);

			result = psmt.executeUpdate();

		} catch (Exception e) {
		} finally {
			dbClose();
		}
		return result;

	}

	public Member login(String id, String pw) {
		
		dbConn();

		sql = "select * from g_user where id = ? and pw = ?";


		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);

			rs = psmt.executeQuery();
			
			mb.setId(rs.getString(1));
			mb.setPw(rs.getNString(2));
			mb.setRank(rs.getInt(3));
			
			

		} catch (Exception e) {
			
		} finally {
			dbClose();
		}
		return mb;
		
	}

}
