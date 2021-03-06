package BaseBallGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UserInterFace {
	Random rd = new Random();
	GamePlay gp = new GamePlay();

	Scanner sc;
	LoginManagement lm;
	Member mb = new Member();
//anjdla
	// 사용자 키보드값
	int key = 0;
	List<String> result;

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
						if (reLogin.equals("n")) {
							mb = new Member();
							key = 0;
						}

					} else {
						System.out.println("로그인에 성공하였습니다...");

						try {
							Thread.sleep(500);
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
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

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
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

		while (key != 4) {
			if (key == 5) {
				mainLoginPrintNoClean();// 뽑기했을 때, 바로 아래에 mainLogin창이 나오도록
			} else {
				mainLoginPrint();// 뽑기안한 기본 프린트
			}
				System.out.print(">> ");
				key = sc.nextInt();
				int num = mb.getPick();// 뽑을 수 받기.
				result = gp.checkPlayer(mb.getId());

				switch (key) {
				case 1:
					if (mb.getPick() >= 1) {

						int[] arr = new int[num];

						for (int i = 0; i < num; i++) {

							arr[i] = rd.nextInt(30) + 1;
							for (int j = 0; j < i; j++) {
								if (arr[i] == arr[j]) {
									i--;
								} else {
									for (int k = 0; k < result.size(); k++) {
										if (result.get(k).equals(arr[i] + "")) {
											i--;
										}
									}
								}

							}
						}

						// 아래 코드에, ar배열과 새로이 뽑은 선수들도함께 출력

						for (int i = 0; i < 80; i++)
							System.out.println("\n");

						for (int i = 1; i <= result.size(); i++) {
							System.out.print("[" + i + "]" + "\t");
						}
						for (int i = result.size() + 1; i <= result.size() + num; i++) {
							System.out.print("[" + i + "]" + "\t");
						}
						System.out.println();
						for (int i = 0; i < result.size(); i++) {
							AllPlayer player = gp.selectPlayer(Integer.parseInt(result.get(i) + ""));
							System.out.print(player.getName() + "\t");
						}
						for (int i = result.size(); i < result.size() + num; i++) {
							AllPlayer player = gp.selectPlayer(arr[i - result.size()]);
							System.out.print(player.getName() + "\t");
						}
						System.out.println();
						for (int i = 0; i < result.size(); i++) {
							AllPlayer player = gp.selectPlayer(Integer.parseInt(result.get(i)));
							System.out.print(player.getStat() + "\t");

						}
						for (int i = result.size(); i < result.size() + num; i++) {
							AllPlayer player = gp.selectPlayer(arr[i - result.size()]);
							System.out.print(player.getStat() + "\t");
							gp.playerRegist(mb.getId(), player.getId());

						}
						System.out.println();
						System.out.println("↑ 내 선수들");

						int count = mb.getPick();
						for (int i = 0; i < num; i++) {
							--count;
						}
						gp.playerPickRegist(mb.getId(), count); // 뽑은 선수들 해당하는 회원에게 저장
						mb.setPick(count); // 뽑은 횟수만큼 줄어듬.
						key = 5;

						break;

					} else {
						System.out.println("뽑을 수 있는 선수가 없습니다.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
				case 2:
					//내가 가지고있는 선수들 한 번 출력해주기.
					
					
					//내가 가지고있는 선수들 번호를 int배열에 저장				
					if (gp.havePlayer(mb.getId())) {
						gp.battlePlayer(mb.getId(), result);
					} else {
						System.out.println("출전할 선수가 없습니다");
						System.out.println("게임을 종료합니다.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					// 어디가 안되실까용?? 잠시만요 하나만 확인하고 바로 말씀드릴게요넹
					// 이거 멤버에서 강태율로 나오는데 그 친구는 다른 아이디에요
					/*
					 * System.out.print("출전할 선수를 골라주세요 >> "); int pick = sc.nextInt();
					 */
					//int배열의 index는 내가 선택한 번호-1
					//ex)
					//저 오류가 해결을 어떻게 해야하나 싶어서요.
					//[1]추신수 [2]박찬호
					//추신수 아이디가 들어있는 배열은, arr[0] = 추신수번호.
					//박찬호 아이디가 들어있는 배열은, arr[1] = 박찬호 번호. 
					//위에 arr[인덱스]는, 내가 선택한 번호-1임. arr[내가선택한 번호 -1]
					//이것을 이용하면 될거같습니당
						
					//gp.battlePlayer(선수번호) 형태로 써주면 될거같아요.
					
					//while문 걸어주어서, 해당하는 조건이 되었을 시, while문 빠져나올 수 있도록. 
					//빠져나오면서, db에 member테이블에 rank 점수 줄 수 있도록.
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
			
					break;
				case 3:
					
					gp.getRank();

					break;
				case 4:
					System.out.println("종료합니다.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);

					break;
				default:
					System.out.println("올바른 숫자를 입력하세요.");
				}
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
		System.out.println("│  │      	  " + mb.getId() + "님 환영합니다!!" + "\t\t│  │");
		System.out.println("│  │         " + "현재 뽑을 수 있는 수는 " + mb.getPick() + "개 있습니다! \t│  │");
		System.out.println("│  │<[1]선수뽑기> <[2]게임시작> <[3]순위확인> <[4]종료>│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");
	}

	private void mainLoginPrintNoClean() {

		System.out.println("┌──┬────────────────────────────────────────────┬──┐");
		System.out.println("│  │           ●○●○ BaseBall Game ○●○●    \t│  │");
		System.out.println("│  │      	  " + mb.getId() + "님 환영합니다!!" + "\t\t│  │");
		System.out.println("│  │         " + "현재 뽑을 수 있는 수는 " + mb.getPick() + "개 있습니다! \t│  │");
		System.out.println("│  │<[1]선수뽑기> <[2]게임시작> <[3]순위확인> <[4]종료>│  │");
		System.out.println("└──┴────────────────────────────────────────────┴──┘");
	}
	// 지워주세요우
}
