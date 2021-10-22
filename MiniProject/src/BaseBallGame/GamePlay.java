package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
import java.util.Random;
=======
import java.sql.SQLException;
import java.util.ArrayList;
>>>>>>> branch 'master' of https://github.com/2021-SMHRD-KDT-Bigdata-7/miniprojectBaseballGame.git
import java.util.Scanner;

public class GamePlay {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Scanner sc =new Scanner(System.in);
	LoginManagement lm =new LoginManagement();
	Member mm=new Member();

	// eseuteUpdate()의 결과를 담을수 있는 변수
	int result = 0;
	
	//sql문을 젖아하는 변수
	String sql;

	//연결하는 메소드
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
	//끝나는 메소드
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
	public void selectPlayer(){
	//allplayer		
		
	}
	public void registerPlayer() {
	//allplayer	
	}
	public void printPlayer() {
		
	}
	public void battlePlayer(int Score) {
		
		dbConn();
		//int a =(스트라이크 개수)
		int a=0;
		//int score =(점수)
		int score =0;
	}

	private void getRank(int rank) {
		
		dbConn();
		int a=0;
				
		try {
			sql = "select id,score from g_user order by score";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				a++;
				System.out.print(a+"위");
				System.out.print(rs.getString("id"));
				System.out.println(rs.getInt("rank"));
				System.out.println();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}		
   }
}

