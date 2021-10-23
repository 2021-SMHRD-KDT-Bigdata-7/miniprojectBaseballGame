package BaseBallGame;

import java.util.Random;
import java.util.Scanner;

public class UserInterFace {
	Random rd = new Random();
	GamePlay gp = new GamePlay();

	Scanner sc;
	LoginManagement lm;
	Member mb = new Member();

	// 餌辨濠 酈爾萄高
	int key = 0;

	public UserInterFace() {
		sc = new Scanner(System.in);
		lm = new LoginManagement();

	}

	public void start() {

		// 螃Щ棚 艙鼻
		openingPrint1();
		openingPrint2();

		while (key != 3) {
			// 詭檣 璽
			mainPrint();

			try {
				key = sc.nextInt();
			} catch (Exception e) {
				System.out.println("璋濠蒂 殮溘п輿撮蹂.");

			}

			switch (key) {

			// 煎斜檣
			case 1:
				String reLogin = "";
				reLogin = "y";

				while (mb == null || reLogin.equals("y")) {
					loginPrint();

					System.out.print("嬴檜蛤 >> ");
					String id = sc.next();

					System.out.print("綠塵廓�� >> ");
					String pw = sc.next();

					mb = lm.login(id, pw);

					if (mb == null) {
						System.out.println("�蛾讔內萼� 橈蝗棲棻.");
						reLogin = "s";

						while (reLogin.equals("s")) {
							System.out.println("棻衛 煎斜檣 ж衛啊蝗棲梱? [y,n]");
							reLogin = sc.next();

							if (reLogin.equals("y") || reLogin.equals("n")) {
								break;
							} else {
								System.out.println("y諦 n醞縑 殮溘п輿撮蹂");
								reLogin = "s";
							}
						}
						if (reLogin.equals("n")) {
							mb = new Member();
							key = 0;
						}

					} else {
						System.out.println("煎斜檣縑 撩奢ж艘蝗棲棻...");

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

			// �蛾灠㊣�
			case 2:
				int result = 0;
				String reRegister = "";
				while (result <= 0 || reRegister.equals("y")) {
					registerPrint();

					System.out.print("嬴檜蛤 >>");
					String id = sc.next();

					System.out.print("綠塵廓�� >>");
					String pw = sc.next();
					result = lm.register(id, pw);

					if (result > 0) {
						System.out.println("�蛾灠㊣埥� 撩奢ж艘蝗棲棻!!");
						reRegister = "n";
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						break;
					} else {
						System.out.println("棻艇 �蛾衋� 氈蝗棲棻!");
						reRegister = "r";

						while (reRegister.equals("r")) {
							System.out.println("棻衛 �蛾灠㊣� ж衛啊蝗棲梱? [y,n]");
							reRegister = sc.next();

							if (reRegister.equals("y") || reRegister.equals("n")) {
								if (reRegister.equals("n")) {
									result = 1;
								}
								break;
							} else {
								System.out.println("螢夥艇 旋濠蒂 殮溘п輿撮蹂.");
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
				System.out.println("1,2,3 醞縑 и 璋濠虜 賦輿撮蹂.");

			}

		}
		while (key != 4) {
			if (key == 5) {
				mainLoginPrintNoClean();//鉻晦ц擊 陽, 夥煎 嬴楚縑 mainLogin璽檜 釭螃紫煙
			} else {
				mainLoginPrint();//鉻晦寰и 晦獄 Щ萼お
			}
				System.out.print(">> ");
				key = sc.nextInt();

				switch (key) {
				case 1:
					if (mb.getPick() >= 1) {
						int num = mb.getPick();
						int[] arr = new int[num];

						for (int i = 0; i < num; i++) {
							arr[i] = rd.nextInt(30) + 1;
							for (int j = 0; j < i; j++) {
								if (arr[i] == arr[j]) {
									i--;
								}
							}
						}
						for (int i = 0; i < 80; i++)
							System.out.println("\n");
						
						for (int i = 1; i <= num; i++) {
							System.out.print("[" + i + "]" + "\t");
						}
						System.out.println();
						for (int i = 0; i < num; i++) {
							AllPlayer player = gp.selectPlayer(arr[i]);
							System.out.print(player.getName() + "\t");
						}
						System.out.println();
						for (int i = 0; i < num; i++) {
							AllPlayer player = gp.selectPlayer(arr[i]);
							System.out.print(player.getStat() + "\t");

							gp.playerRegist(mb.getId(), player.getId());

						}
						System.out.println();
						System.out.println("∟ 頂 摹熱菟");
						int count = mb.getPick();
						for (int i = 0; i < num; i++) {
							--count;
						}
						gp.playerPickRegist(mb.getId(), count);
						mb.setPick(count);
						key = 5;

						break;
					} else {
						System.out.println("鉻擊 熱 氈朝 摹熱陛 橈蝗棲棻.");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
				case 2:

					break;
				case 3:

					break;
				case 4:
					System.out.println("謙猿м棲棻.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);

					break;
				default:
					System.out.println("螢夥艇 璋濠蒂 殮溘ж撮蹂.");
				}
			
		}

	}

	public void openingPrint1() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ≒∞≒∞ BaseBall Game ∞≒∞≒    \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openingPrint2() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ∞≒∞≒ BaseBall Game ≒∞≒∞    \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mainPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ≒∞≒∞ BaseBall Game ∞≒∞≒    \t弛  弛");
		System.out.println("弛  弛                 [撿 掘 啪 歜]          \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛      <[1]煎斜檣>  <[2]�蛾灠㊣�>  <[3]謙猿>\t弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");
		System.out.print(">>");
	}

	private void loginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ≒∞≒∞ BaseBall Game ∞≒∞≒    \t弛  弛");
		System.out.println("弛  弛                  [煎 斜 檣]          \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛      嬴檜蛤, 綠塵廓�ㄧ� 殮溘п輿撮蹂..    \t弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");

	}

	private void registerPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ≒∞≒∞ BaseBall Game ∞≒∞≒    \t弛  弛");
		System.out.println("弛  弛                 [�� 錳 陛 殮]          \t弛  弛");
		System.out.println("弛  弛                                      \t弛  弛");
		System.out.println("弛  弛   餌辨ж褒 嬴檜蛤, 綠塵廓�ㄧ� 殮溘п輿撮蹂~ ^^// \t弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");

	}

	private void mainLoginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ≒∞≒∞ BaseBall Game ∞≒∞≒    \t弛  弛");
		System.out.println("弛  弛      	  " + mb.getId() + "椒 �紊腎桭炴�!!" + "\t\t弛  弛");
		System.out.println("弛  弛         " + "⑷營 鉻擊 熱 氈朝 熱朝 " + mb.getPick() + "偃 氈蝗棲棻! \t弛  弛");
		System.out.println("弛  弛<[1]摹熱鉻晦> <[2]啪歜衛濛> <[3]牖嬪�挫�> <[4]謙猿>弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");
	}

	private void mainLoginPrintNoClean() {

		System.out.println("忙式式成式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式成式式忖");
		System.out.println("弛  弛           ≒∞≒∞ BaseBall Game ∞≒∞≒    \t弛  弛");
		System.out.println("弛  弛      	  " + mb.getId() + "椒 �紊腎桭炴�!!" + "\t\t弛  弛");
		System.out.println("弛  弛         " + "⑷營 鉻擊 熱 氈朝 熱朝 " + mb.getPick() + "偃 氈蝗棲棻! \t弛  弛");
		System.out.println("弛  弛<[1]摹熱鉻晦> <[2]啪歜衛濛> <[3]牖嬪�挫�> <[4]謙猿>弛  弛");
		System.out.println("戌式式扛式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式式扛式式戎");
	}

}
