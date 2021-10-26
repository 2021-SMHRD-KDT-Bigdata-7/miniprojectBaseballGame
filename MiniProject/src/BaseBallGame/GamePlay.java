package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

	// eseuteUpdate()의 결과를 담을수 있는 변수
	int result = 0;

	// sql문을 젖아하는 변수
	String sql;

	// 연결하는 메소드
	public void dbConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB연결
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String user = "cgi_2_2_1022";
			String password = "smhrd2";
			conn = DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			// Exception
			System.out.println("오류확인");
			e.printStackTrace();
		}
	}

	// 끝나는 메소드
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
			System.out.println("게임을 종료하겠습니다.");
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
		} 

		return player;
	}

	public void printPlayer() {

		System.out.println(ap.getName() + "\t");
		System.out.println(ap.getStat() + "\t");
	}

	// 선수번호: playerNum
	public void battlePlayer(String p_id, List<String> result) {

		// a는 스트라이크 개수
		int a = 0;
		// vic는 점수
		int score = 0;
		int add = 0;
		try {
			while (true) {
				dbConn();

				System.out.println("게임을 시작합니다.");
				if ((score < 10) && (a < 3)) {

//					int m_num = 1;
//					String p_name = null;
					while ((a < 3) && (score < 10)) {
						int p_stat = 0;

						for (int i = 1; i <= result.size(); i++) {
							System.out.print("[" + i + "]" + "\t");
						}

						System.out.println();
						for (int i = 0; i < result.size(); i++) {
							AllPlayer player = selectPlayer(Integer.parseInt(result.get(i) + ""));
							System.out.print(player.getName() + "\t");
						}

						System.out.println();
						for (int i = 0; i < result.size(); i++) {
							AllPlayer player = selectPlayer(Integer.parseInt(result.get(i)));
							System.out.print(player.getStat() + "\t");

						}

						System.out.println();
						System.out.print("출전할 선수를 골라주세요 >> ");
						int ch = sc.nextInt();

//					
//					
//					sql = "select a.p_name, m.num from allplayer a,myplayer m where a.p_id=m.p_id and m.num =?";
//					psmt = conn.prepareStatement(sql);
//					psmt.setInt(1, ch);
//					rs = psmt.executeQuery();
//					
//					while (rs.next()) {
//						m_num = rs.getInt(1);
//						p_name = rs.getString(2);
//						p_stat = rs.getInt(3);
//						System.out.println(m_num + "번 투수" + p_name + "등판");
//						System.out.println("선택한 선수의 능력치" + p_stat);
//					}

						AllPlayer player = selectPlayer(Integer.parseInt(result.get(ch - 1)));
						p_stat = player.getStat();

						int enemy = 0;
//						sql = "select a.p_name from allplayer a where not in table myplayer m";
//						psmt = conn.prepareStatement(sql);
//확인 끝났습니다. 제가 따로 깃허브 하나 새로 파고 따로 푸쉬한 다음에 병합해볼게요.//
						Random rd = new Random();
						enemy = rd.nextInt(100) + 1;
						System.out.println();
						System.out.println("상대 선수의 능력치는 " + enemy + "입니다.");
						if (Math.abs(p_stat - enemy) < 10) {
							System.out.println("스트라이크");
							a++;
							System.out.println("스트라이크 갯수: " + a);
							System.out.println("점수: " + score);
						} else if (Math.abs(p_stat - enemy) < 30) {
							System.out.println("안타");
							score = score + 1;

							System.out.println("스트라이크 갯수: " + a);
							System.out.println("점수: " + score);
						} else {
							System.out.println("홈런");
							score = score + 2;

							System.out.println("스트라이크 갯수: " + a);
							System.out.println("점수: " + score);
						}
					}

				} else if (a >= 3) {
					System.out.println("쓰리 스트라이크 선수교체!");
					System.out.println("당신은 패배했습니다.한 판 더 하시겠습니까 y/n?");
					String c = sc.next();
					if (c.equals("y") || c.equals("Y")) {
						a = 0;
						score = 0;
					} else {
						System.out.println("게임을 종료합니다.");
						break;
					}
				} else if (score >= 10) {
					System.out.println("게임 승리!!");
					add = 1;
					System.out.println("게임을 이겼습니다.한 판 더 하시겠습니까 y/n?");
					String c = sc.next();
					if (c.equals("y") || c.equals("Y")) {
						a = 0;
						score = 0;
					} else {
						System.out.println("게임을 종료합니다.");
						break;
					}
				}
			}
			if (add > 0) {

				int sco = 0;
				sql = "select score from g_user where id = ?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, p_id);
				psmt.executeQuery();

				if(rs.next()) {
					sco = rs.getInt(1);
				}
//sss
				//잠시만요 뭐 하나만 확인해봐도 될까요? -나민주
				//넹넹 그 민주씨꺼랑 합쳐도됩니당 필요한거있으면 역

				sql = "update g_user set score = ? where id=?";
				psmt = conn.prepareStatement(sql);
				System.out.println(sco);
				sco = sco + 1;
				psmt.setInt(1, sco);
				psmt.setString(2, p_id);
				psmt.executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	void getRank() {

		dbConn();

		try {
			sql = "select id, score, RANK() over (order by score desc) as rank from g_user";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				System.out.print(rs.getString("id"));
				System.out.println(rs.getInt("score"));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

	}

	public void playerRegist(String g_id, String p_id) {

		dbConn();

		sql = "select id, pw, score, pick from g_user where id = ?";

		int score = 0;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, g_id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				mm.setId(rs.getString(1));
				mm.setPw(rs.getString(2));
				mm.setRank(rs.getInt(3));
				mm.setPick(rs.getInt(4));

			} else {
				mm = null;
			}

			sql = "select p_name, p_stat from allplayer where p_id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, p_id);

			rs = psmt.executeQuery();

			if (rs.next()) {
				ap.setName(rs.getString(1));
				ap.setStat(rs.getInt(2));

			} else {
				mm = null;
			}

			sql = "insert into myplayer (num, id, p_id, p_stat) values(mp_num_seq.nextval, ?, ?, ?)";

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, g_id);
			psmt.setString(2, p_id);
			psmt.setInt(3, ap.getStat());

			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// 플레이어 등록 메소드
	public void playerPickRegist(String id, int count) {
		dbConn();

		sql = "update  g_user set pick = ? where id = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, count);
			psmt.setString(2, id);

			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	// 플레이어의 선수번호들을 출력해주는 int배열 메소드
	public List<String> checkPlayer(String id) {
		List<String> result = new ArrayList<>();
		int num = 0;// id에 해당하는 선수들의 수
		// db연결

		dbConn();

		try {

			sql = "select p_id from myplayer where id = ? ";
			// id에 해당하는 선수들의 총 수를 num에 받기.

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();

			while (rs.next()) {
				result.add(rs.getString("p_id"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		// 선수들의 번호를 담은 a배열을 출력.
		return result;
		//

	}

	// 회원이 선수를 보유하고있는가
	public boolean havePlayer(String id) {

		dbConn();
		String name = "";
		try {

			sql = "select p_id from myplayer where id = ? ";

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			rs = psmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("p_id");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		if (name.equals("")) {
			return false;
		} else
			return true;

	}
}
