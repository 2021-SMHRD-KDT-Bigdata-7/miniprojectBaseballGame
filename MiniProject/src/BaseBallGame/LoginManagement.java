package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class LoginManagement {

//	Scanner sc = new Scanner(System.in);
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
			String url = "jdbc:oracle:thin:@192.168.1.241:1521:xe";
			String user = "hr";
			String password = "hr";
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			// Exception
			System.out.println("����Ȯ��");
			e.printStackTrace();
		}
	}

	// ������ �޼ҵ�
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

		sql = "insert into g_user values (?,?,?)";

		int score = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, mb.getId());
			psmt.setString(2, mb.getPw());
			psmt.setInt(3, score);

			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return result;

	}

	public void login(String id, String pw) {

	}

}
