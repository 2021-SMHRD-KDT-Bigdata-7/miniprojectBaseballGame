package BaseBallGame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class GamePlay {
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Scanner sc =new Scanner(System.in);

	// eseuteUpdate()의 결과를 담을수 있는 변수
	int result = 0;
	
	//sql문을 젖아하는 변수
	String sql;

	//연결하는 메소드
	public void dbConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.DB연결
			String url = "jdbc:oracle:thin:@192.168.1.241:1521:xe";
			String user = "hr";
			String password = "hr";
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
		//int a =(스트라이크 개수)
		int a=0;
		//int score =(점수)
		int score =0;
		while(true){
		 if((score<10) && (a<3)){
			 //System.out.println("한화가 또 또 이겼습니다");
		   if (Math.abs(my_pl_stat-en_pl_stat)<10){
			   System.out.println("스트라이크");
		   a++;
		     if(a>2){
		    	 System.out.println("스트라이크 삼진 아웃 선수교체");
		         a=0;
		     }
		  }else if(Math.abs(my_pl_stat-en_pl_stat)<30){
		  a=0;
		  System.out.println("안타! 1점 들어갑니다");
		  score=+1;
		  }else{
		  a=0;
		  score=+2;
		  System.out.println("홈런");
		  }
		 } else if(score>=10){
			 System.out.println("게임 승리! 한 판 더 하시겠습니까 y/n?");
			 String c =sc.next();
			 //count.int++;
			    if(c.equals("y") || c.equals("Y")){
			    	System.out.println("게임을 종료합니다.");
			    break;
		        }
		 }else if(a>=3){
			 System.out.println("당신은 패배자 입니다.한 판 더 하시겠습니까 y/n?");
		     String c =sc.next();
		       if(c.equals("y") || c.equals("Y")){
		    	System.out.println("게임을 종료합니다.");
		        break;
		        }
		       }else {
		    	System.out.println("게임을 종료합니다.");
		    	break;
		       }
		     }
	       }
	private void getRank() {
		
	}
}
