package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoingManagement {

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	// eseuteUpdate()�� ����� ������ �ִ� ����
	int result = 0;
	
	//sql���� �����ϴ� ����
	String sql;

	//�����ϴ� �޼ҵ�
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
	//������ �޼ҵ�
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
}
