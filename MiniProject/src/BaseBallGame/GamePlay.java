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
			System.out.println("������ �����ϰڽ��ϴ�.");
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
		//int a =(��Ʈ����ũ ����)
		int a=0;
		//int score =(����)
		int score =0;
		while(true){
		 if((score<10) && (a<3)){
			 //System.out.println("��ȭ�� �� �� �̰���ϴ�");
		   if (Math.abs(my_pl_stat-en_pl_stat)<10){
			   System.out.println("��Ʈ����ũ");
		   a++;
		     if(a>2){
		    	 System.out.println("��Ʈ����ũ ���� �ƿ� ������ü");
		         a=0;
		     }
		  }else if(Math.abs(my_pl_stat-en_pl_stat)<30){
		  a=0;
		  System.out.println("��Ÿ! 1�� ���ϴ�");
		  score=+1;
		  }else{
		  a=0;
		  score=+2;
		  System.out.println("Ȩ��");
		  }
		 } else if(score>=10){
			 System.out.println("���� �¸�! �� �� �� �Ͻðڽ��ϱ� y/n?");
			 String c =sc.next();
			 //count.int++;
			    if(c.equals("y") || c.equals("Y")){
			    	System.out.println("������ �����մϴ�.");
			    break;
		        }
		 }else if(a>=3){
			 System.out.println("����� �й��� �Դϴ�.�� �� �� �Ͻðڽ��ϱ� y/n?");
		     String c =sc.next();
		       if(c.equals("y") || c.equals("Y")){
		    	System.out.println("������ �����մϴ�.");
		        break;
		        }
		       }else {
		    	System.out.println("������ �����մϴ�.");
		    	break;
		       }
		     }
	       }
	private void getRank() {
		
	}
}
