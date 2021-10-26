package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

	// 선수번호: playerNum
	public void battlePlayer(String p_id, List<String> result) {
		// id에 넘버를 부여후 id 넘버 + 5*의 방식으로 해결 ex) 3(입력키) + id넘버*5
		// a는 스트라이크 개수
		int a = 0;
		// vic는 점수
		int score = 0;
		int add = 0;

		while (true) {
			dbConn();
			String id = Member.id;
			try {
				if ((score < 10) && (a < 3)) {
					while (a < 3 && score < 10) {
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


						/*
						 * /////////////////////////////////////////////////////////////////////////////
						 * /////////////////////// sql =
						 * "select m.p_stat, a.p_name, m.num from myplayer m, allplayer a where m.p_id = a.p_id and m.id=?"
						 * ; psmt = conn.prepareStatement(sql); psmt.setString(1, id); rs =
						 * psmt.executeQuery(); int i = 1; while (rs.next()) { System.out.print("[" +
						 * rs.getInt(3) + "]"); System.out.print(rs.getString(2) + " ");
						 * System.out.println(rs.getInt(1) + "\t"); System.out.println(); i++; // 왔습니다!
						 * 저기 i를 1로 입력하고 while 문을 걸었는데 1이 안바껴요아 } System.out.print("출전할 선수를 선택하십시오.  ");
						 * int ch = sc.nextInt(); System.out.println(); sql =
						 * "select g.id, m.p_id, m.p_stat from myplayer m,g_user g where m id =g id ";
						 * // id에 해당하는 선수들의 총 수를 num에 받기.
						 * 
						 * // 선수들의 번호들을 int배열
						 * 
						 * 
						 * sql =
						 * "select a.p_name, m.num, a.p_stat from allplayer a,myplayer m where a.p_id=m.p_id"
						 * ; psmt = conn.prepareStatement(sql); rs = psmt.executeQuery();
						 * 
						 * int m_num = 0; String p_name = null; int p_stat = 0;
						 * 
						 * 
						 * while (rs.next()) { p_name = rs.getString(1); m_num = rs.getInt(2); p_stat =
						 * rs.getInt(3); System.out.println("[" + m_num + "]" + " 번 " + p_name + " 능력치:"
						 * + p_stat); }
						 * 
						 * 
						 sql =
						 "select a.p_name, m.num, m.p_stat from allplayer a,myplayer m where a.p_id=m.p_id and m.num=?"
						 ; psmt = conn.prepareStatement(sql); psmt.setInt(1, ch); // psmt.setString(1,
						 id); rs = psmt.executeQuery();
						 * 
						 while (rs.next()) { m_num = rs.getInt(2); p_name = rs.getString(1); p_stat =
						 rs.getInt(3); System.out.println(m_num + "번 투수 " + p_name + " 등판");
						 System.out.println(); try { Thread.sleep(1000); } catch (InterruptedException
						 e) { e.printStackTrace(); } System.out.println("선택한 선수의 능력치" + p_stat);
						 System.out.println(); try { Thread.sleep(1000); } catch (InterruptedException
						 e) { e.printStackTrace(); } }
						 */

						////////////////////////////////////////////////////////////////////////////////////////////
						/*
						 * int m_num = 0; String p_name = null; sql
						 * ="select a.p_name, m.num, m.p_stat from allplayer a,myplayer m where a.p_id=m.p_id and m.num=?"
						 * ; psmt = conn.prepareStatement(sql); psmt.setInt(1, ch);
						 * psmt.setString(1,id); rs = psmt.executeQuery(); while (rs.next()) { m_num =
						 * rs.getInt(2); p_name = rs.getString(1); p_stat =rs.getInt(3);
						 * System.out.println(m_num + "번 투수 " + p_name + " 등판"); System.out.println();
						 * try { Thread.sleep(1000); } catch (InterruptedException e) {
						 * e.printStackTrace(); } System.out.println("선택한 선수의 능력치" + p_stat);
						 * System.out.println(); try { Thread.sleep(1000); } catch (InterruptedException
						 * e) { e.printStackTrace(); } }
						 */
						int enemy = 0;
						sql = "select a.p_name from allplayer a where not in table myplayer m";
						Random rd = new Random();
						enemy = rd.nextInt(100) + 1;
						System.out.println("내 선수의 능력치는 "+p_stat+"입니다.");
						System.out.println();
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("상대 선수의 능력치는 " + enemy + "입니다.");
						System.out.println();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (Math.abs(p_stat - enemy) < 20) {
							System.out.println("놓쳤습니다.");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("스트라이크");
							a = a + 1;
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("스트라이크" + "  " + a + "!");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if (Math.abs(p_stat - enemy) < 40) {
							System.out.print("쳤습니다.   ");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("안타");
							score = score + 1;
							a = 0;
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println(score + "점 획득!");
							System.out.println();
							System.out.println();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else {
							System.out.print("쳤습니다. 쭉쭉 뻗어나갑니다.    ");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							System.out.println("홈런");
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							score = score + 2;
							a = 0;
							System.out.println(score + "점 획득!");
							System.out.println();
							System.out.println();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}
//
				} else if (a >= 3) {
					System.out.println("쓰리 스트라이크 선수교체!");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("당신은 패배했습니다.한 판 더 하시겠습니까 y/n?");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					String c = sc.next();
					if (add > 0) {
						sql = "update g_user set score=score+1 where id=?";
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, id);
						psmt.executeUpdate();
					}
					if (c.equals("y") || c.equals("Y")) {
						a = 0;
						score = 0;
					} else {
						System.out.println("게임을 종료합니다.");
						break;
					}

				} else if (score >= 10) {
					System.out.println();
					System.out.println("게임 승리!!");
					add = 1;
					if (add > 0) {

						sql = "update g_user set score=score+1 where id=?";
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, id);
						psmt.executeUpdate();

						sql = "select score from g_user where id=?";
						psmt = conn.prepareStatement(sql);
						psmt.setString(1, id);
						rs = psmt.executeQuery();
						rs.next();

						int count = rs.getInt(1);

						if (count != 0 && count % 2 == 0) {

							sql = "update  g_user set pick = ? where id = ?";
							psmt = conn.prepareStatement(sql);
							psmt.setInt(1, 1);
							psmt.setString(2, id);
							psmt.executeUpdate();
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
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

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				dbClose();
			}
		}
	}

	void getRank() {
		int i = 0;

		dbConn();

		try {
			sql = "select id, score, RANK() over (order by score desc) as rank from g_user";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				i++;
				System.out.print(i + "위  ");
				System.out.print(rs.getString("id") + "\t");
				System.out.println(rs.getInt("score") + "점");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
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
		} finally {
			dbClose();
		}
	}

	// 플레이어 등록 메소드
	public void playerPickRegist(String id, int count) {
		dbConn();
		try {
			sql = "update  g_user set pick = ? where id = ?";

			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, count);
			psmt.setString(2, id);

			result = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
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
		} finally {
			// db종료
			dbClose();
		}

		// 선수들의 번호를 담은 a배열을 출력.
		return result;
		//

	}

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
		} finally {
			// db종료
			dbClose();
		}
		if (name.equals("")) {
			return false;
		} else
			return true;

	}
}
