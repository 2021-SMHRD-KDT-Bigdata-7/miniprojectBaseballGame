package BaseBallGame;

import java.util.Scanner;

public class UserInterFace {

	// 사용자 키보드값
	int key = 0;
	Scanner sc;
	LoginManagement lm;
	Member mb;

	public UserInterFace() {
		sc = new Scanner(System.in);
		lm = new LoginManagement();

	}

	public void start() {

		// 오프닝 영상
		openingPrint1();
		openingPrint2();

		while (key != 3) {
			// 메인 창
			mainPrint();

			try {
				key = sc.nextInt();
			} catch (Exception e) {
				System.out.println("숫자를 입력해주세요.");

			}

			switch (key) {

			// 로그인
			case 1:
				while (mb == null) {
					loginPrint();

					System.out.print("아이디 >> ");
					String id = sc.next();

					System.out.print("비밀번호 >> ");
					String pw = sc.next();

					mb = lm.login(id, pw);
					if (mb == null) {
						System.out.println("회원정보가 없습니다.");
						System.out.println("다시 입력해주세요.");
					}
				}
				System.out.println("로그인에 성공하였습니다...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;

			// 회원가입
			case 2:
				int result;
				registerPrint();
				
				System.out.print("아이디 >>");
				String id = sc.next();

				System.out.print("비밀번호 >>");
				String pw = sc.next();
				result = lm.register(id,pw);
				
				if(result>0 ) {
					System.out.println("회원가입에 성공하였습니다!!");
				}else {
					System.out.println("회원가입에 실패하였습니다 ㅜㅜ");
				}			
				
				break;

			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("1,2,3 중에 한 숫자만 써주세요.");

			}

		}

		mainLoginPrint();

	}

	public void openingPrint1() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ●○●○ BaseBall Game ○●○●    \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openingPrint2() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ○●○● BaseBall Game ●○●○    \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mainPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ●○●○ BaseBall Game ○●○●    \t│  │");
		System.out.println("│  │                 [야 구 게 임]          \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │      <[1]로그인>  <[2]회원가입>  <[3]종료>\t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");
		System.out.print(">>");
	}

	private void loginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ●○●○ BaseBall Game ○●○●    \t│  │");
		System.out.println("│  │                  [로 그 인]          \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │      아이디, 비밀번호를 입력해주세요..    \t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");

	}

	private void registerPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ●○●○ BaseBall Game ○●○●    \t│  │");
		System.out.println("│  │                 [회 원 가 입]          \t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │   사용하실 아이디, 비밀번호를 입력해주세요~ ^^// \t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");

	}

	private void mainLoginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ●○●○ BaseBall Game ○●○●    \t│  │");
		System.out.println("│  │        " + mb.getId() + "님 환영합니다!!" + "\t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │           <[1]선수뽑기>  <[2]게임시작>  \t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");
		System.out.print(">>");

	}

}
