package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePlay {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Scanner sc = new Scanner(System.in);
	Random rd = new Random();
	LoginManagement lm = new LoginManagement();
	Member mm = new Member();
	AllPlayer ap = new AllPlayer();

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
			System.out.println("������ �����ϰڽ��ϴ�.");
		}
	}

	public AllPlayer selectPlayer(int p_id) {
		// allplayer
		AllPlayer player = null;

		dbConn();

		sql = "select * from allplayer where p_id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, p_id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				int stat = rs.getInt(3);

				player = new AllPlayer(id, name, stat);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return player;
	}

	public void registerPlayer() {
		// allplayer
	}

	public void printPlayer() {

		System.out.println(ap.getName() + "\t");
		System.out.println(ap.getStat() + "\t");
	}

	public void battlePlayer(int Score) {
		// a�� ��Ʈ����ũ ����
		int a = 0;
		// vic�� ����
		int score = 0;
		while (true) {
			System.out.println("������ �����մϴ�.");
			if ((score < 10) && (a < 3)) {
				System.out.println("������ �����ϼ���.");
				try {
					sql = "select a.p_name, m.num, p_stat from allplayer a,myplayer m where a.id=m.id ";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					int m_num = 0;
					String p_name = null;
					int p_stat = 0;
					while (rs.next()) {
						m_num = rs.getInt(1);
						p_name = rs.getString(2);
						p_stat = rs.getInt(3);
						System.out.println("[" + m_num + "]" + " �� " + p_name + " �ɷ�ġ:" + p_stat);
					}
					int ch = sc.nextInt();
					sql = "select a.p_name, m.num from allplayer a,myplayer m where a.id=m.id and m.num =?";
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, ch);
					rs = psmt.executeQuery();
					while (rs.next()) {
						m_num = rs.getInt(1);
						p_name = rs.getString(2);
						p_stat = rs.getInt(3);
						System.out.println(m_num + "�� ����" + p_name + "����");
						System.out.println("������ ������ �ɷ�ġ" + p_stat);
					}
					while (a < 3 || score < 10) {
						if (Math.abs(p_stat - 1995) < 10) {
							System.out.println("��Ʈ����ũ");
							a++;
						} else if (Math.abs(p_stat - 1995) < 30) {
							System.out.println("��Ÿ 1�� �԰� ���ϴ�.");
							score = +1;
							a = 0;
						} else {
							System.out.println("Ȩ��");
							score = +2;
							a = 0;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					dbClose();
				}
			} else if (a >= 3) {
				System.out.println("���� ��Ʈ����ũ ������ü!");
				System.out.println("����� �й��� �Դϴ�.�� �� �� �Ͻðڽ��ϱ� y/n?");
				String c = sc.next();
				if (c.equals("y") || c.equals("Y")) {
					a = 0;
					score = 0;
				} else {
					System.out.println("������ �����մϴ�.");
					break;
				}
			} else if (score >= 10) {
				System.out.println("���� �¸�!!");
				System.out.println("������ �̰���ϴ�.�� �� �� �Ͻðڽ��ϱ� y/n?");
				String c = sc.next();
				if (c.equals("y") || c.equals("Y")) {
					a = 0;
					score = 0;
				} else {
					System.out.println("������ �����մϴ�.");
					break;
				}
			}
		}
	}
	private void getRank(int rank) {

		dbConn();
		int a = 0;

		try {
			sql = "select id,score from g_user order by score";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				a++;
				System.out.print(a + "��");
				System.out.print(rs.getString("id"));
				System.out.println(rs.getInt("rank"));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}
}
