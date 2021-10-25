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

	//선수번호: playerNum
	public void battlePlayer(int playerNum) {
		
		// a는 스트라이크 개수
		int a = 0;
		// vic는 점수
		int score = 0;
		int add=0;
		while (true) {
			dbConn();
			
			System.out.println("게임을 시작합니다.");
			if ((score < 10) && (a < 3)) {
				System.out.println("선수를 선택하세요.");
				try {
					sql = "select a.p_name, m.num, a.p_stat from allplayer a,myplayer m where a.p_id=m.p_id";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					int m_num = 0;
					String p_name = null;
					int p_stat = 0;
					while (rs.next()) {
						m_num = rs.getInt(1);
						p_name = rs.getString(2);
						p_stat = rs.getInt(3);
						System.out.println("[" + m_num + "]" + " 번 " + p_name + " 능력치:" + p_stat);
					}
					int ch = sc.nextInt();
					sql = "select a.p_name, m.num from allplayer a,myplayer m where a.p_id=m.p_id and m.num =?";
					psmt = conn.prepareStatement(sql);
					psmt.setInt(1, ch);
					rs = psmt.executeQuery();
					while (rs.next()) {
						m_num = rs.getInt(1);
						p_name = rs.getString(2);
						p_stat = rs.getInt(3);
						System.out.println(m_num + "번 투수" + p_name + "등판");
						System.out.println("선택한 선수의 능력치" + p_stat);
					}
					while (a < 3 || score < 10) {

						int enemy = 0;
						sql = "select a.p_name from allplayer a where not in table myplayer m";
						psmt = conn.prepareStatement(sql);

						Random rd =new Random();
						enemy=rd.nextInt(100)+1;
						System.out.println("상대 선수의 능력치는 "+enemy+"입니다." );
						if (Math.abs(p_stat - enemy) < 10) {
							System.out.println("스트라이크");
							a++;
						} else if (Math.abs(p_stat - enemy) < 30) {
							System.out.println("안타");
							score = +1;
							a = 0;
						} else {
							System.out.println("홈런");
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
				add=1;
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
		if (add>0) {
		  try {
			 String id = "";
			 sql="update g_user set score=score+1 where id='?'";
			 psmt = conn.prepareStatement(sql);
			 psmt.setString(1, id);
			 psmt.executeUpdate();
		 } catch (Exception e) {
			e.printStackTrace();
		 } finally {
			dbClose();
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
				System.out.print(a + "위");
				System.out.print(rs.getString("id"));
				System.out.println(rs.getInt("rank"));
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		//어떠하지?
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

	//플레이어 등록 메소드
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
		} finally {
			dbClose();
		}
	}
	
	//플레이어의 선수번호들을 출력해주는 int배열 메소드
	public int[] checkPlayer(String id) {
		
		int num = 0;//id에 해당하는 선수들의 수 
		
		//db연결
		
		dbConn();

		sql = "select id, p_id, p_stat from myplayer m,g_user g where m id =g id ";
		//id에 해당하는 선수들의 총 수를 num에 받기.
				
		//선수들의 번호들을 int배열에 담기
		int[] a = new int[num];
			
		
		//db종료
		dbClose();
		//선수들의 번호를 담은 a배열을 출력.
		return a;
		//
			
	}
}
