package BaseBallGame;

import java.util.Random;
import java.util.Scanner;

public class UserInterFace {
	Random rd =new Random();
	GamePlay gp=new GamePlay();
	// 사용자 키보드값
	int key = 0;
	Scanner sc;
	LoginManagement lm;
	Member mb = new Member();

	public UserInterFace() {
		sc = new Scanner(System.in);
		lm = new LoginManagement();

	}
	//fds
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
				String reLogin = "";
				reLogin = "y";

				while (mb == null || reLogin.equals("y")) {
					loginPrint();

					System.out.print("아이디 >> ");
					String id = sc.next();

					System.out.print("비밀번호 >> ");
					String pw = sc.next();

					mb = lm.login(id, pw);
			
					if (mb == null) {
						System.out.println("회원정보가 없습니다.");
						reLogin = "s";

						while (reLogin.equals("s")) {
							System.out.println("다시 로그인 하시겠습니까? [y,n]");
							reLogin = sc.next();

							if (reLogin.equals("y") || reLogin.equals("n")) {
								break;
							} else {
								System.out.println("y와 n중에 입력해주세요");
								reLogin = "s";
							}
						}
						if(reLogin.equals("n")) {
							mb= new Member();	
							key = 0;
						}

					} else {
						System.out.println("로그인에 성공하였습니다...");

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}

				}
				if (key == 1) {
					reLogin = "n";
					key = 3;
					break;
				}
				break;

			// 회원가입
			case 2:
				int result = 0;
				String reRegister = "";
				while (result <= 0 || reRegister.equals("y")) {
					registerPrint();

					System.out.print("아이디 >>");
					String id = sc.next();

					System.out.print("비밀번호 >>");
					String pw = sc.next();
					result = lm.register(id, pw);

					if (result > 0) {
						System.out.println("회원가입에 성공하였습니다!!");
						reRegister = "n";

						break;
					} else {
						System.out.println("다른 회원이 있습니다!");
						reRegister = "r";

						while (reRegister.equals("r")) {
							System.out.println("다시 회원가입 하시겠습니까? [y,n]");
							reRegister = sc.next();

							if (reRegister.equals("y") || reRegister.equals("n")) {
								if (reRegister.equals("n")) {
									result = 1;
								}
								break;
							} else {
								System.out.println("올바른 글자를 입력해주세요.");
								reRegister = "r";

							}
						}
					}
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
		System.out.print(">> ");
		key = sc.nextInt();

		switch (key) {
		case 1:
		    int[] arr = new int[5];
	         
            for(int i =0; i<5; i++) {
            arr[i] = rd.nextInt(30)+1;
               for(int j =0; j<i; j++) {
                  if(arr[i]==arr[j]) {
                     i--;
                  }
               }
            }
            for(int i=1; i<=5; i++) {
               System.out.print("["+i+"]"+"\t");
            }
            System.out.println();
            for(int i=0; i<5; i++) {
                AllPlayer player =  gp.selectPlayer(arr[i]);
                System.out.print(player.getName()+"\t"); 
             }
            System.out.println();
            for(int i=0; i<5; i++) {
                AllPlayer player =  gp.selectPlayer(arr[i]);
                System.out.print(player.getStat()+"\t"); 
             }
            
            break;
		case 2:
			gp.battlePlayer();
			break;
		case 3:
			System.out.println("종료합니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(0);
		default:
			System.out.println("올바른 숫자를 입력하세요.");
		}
		
	
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
		System.out.println("│  │      	  " + mb.getId() + "님 환영합니다!!"      + "\t\t│  │");
		System.out.println("│  │                                      \t│  │");
		System.out.println("│  │       <[1]선수뽑기> <[2]게임시작> <[3]종료>\t│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");
	}

}
