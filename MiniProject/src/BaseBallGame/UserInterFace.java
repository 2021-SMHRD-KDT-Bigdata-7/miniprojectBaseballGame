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
	// »ç¿ëÀÚ Å°º¸µå°ª
	int key = 0;
	List<String> result;

	public UserInterFace() {
		sc = new Scanner(System.in);
		lm = new LoginManagement();

	}


	public void start() {

		// ¿ÀÇÁ´× ¿µ»ó
		openingPrint1();
		openingPrint2();

		while (key != 3) {
			// ¸ÞÀÎ Ã¢
			mainPrint();

			try {
				key = sc.nextInt();
			} catch (Exception e) {
				System.out.println("¼ýÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");

			}

			switch (key) {

			// ·Î±×ÀÎ
			case 1:
				String reLogin = "";
				reLogin = "y";

				while (mb == null || reLogin.equals("y")) {
					loginPrint();

					System.out.print("¾ÆÀÌµð >> ");
					String id = sc.next();

					System.out.print("ºñ¹Ð¹øÈ£ >> ");
					String pw = sc.next();

					mb = lm.login(id, pw);

					if (mb == null) {
						System.out.println("È¸¿øÁ¤º¸°¡ ¾ø½À´Ï´Ù.");
						reLogin = "s";

						while (reLogin.equals("s")) {
							System.out.println("´Ù½Ã ·Î±×ÀÎ ÇÏ½Ã°Ú½À´Ï±î? [y,n]");
							reLogin = sc.next();

							if (reLogin.equals("y") || reLogin.equals("n")) {
								break;
							} else {
								System.out.println("y¿Í nÁß¿¡ ÀÔ·ÂÇØÁÖ¼¼¿ä");
								reLogin = "s";
							}
						}
						if (reLogin.equals("n")) {
							mb = new Member();
							key = 0;
						}

					} else {
						System.out.println("·Î±×ÀÎ¿¡ ¼º°øÇÏ¿´½À´Ï´Ù...");

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

			// È¸¿ø°¡ÀÔ
			case 2:
				int result = 0;
				String reRegister = "";
				while (result <= 0 || reRegister.equals("y")) {
					registerPrint();

					System.out.print("¾ÆÀÌµð >>");
					String id = sc.next();

					System.out.print("ºñ¹Ð¹øÈ£ >>");
					String pw = sc.next();
					result = lm.register(id, pw);

					if (result > 0) {
						System.out.println("È¸¿ø°¡ÀÔ¿¡ ¼º°øÇÏ¿´½À´Ï´Ù!!");
						reRegister = "n";
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						break;
					} else {
						System.out.println("´Ù¸¥ È¸¿øÀÌ ÀÖ½À´Ï´Ù!");
						reRegister = "r";

						while (reRegister.equals("r")) {
							System.out.println("´Ù½Ã È¸¿ø°¡ÀÔ ÇÏ½Ã°Ú½À´Ï±î? [y,n]");
							reRegister = sc.next();

							if (reRegister.equals("y") || reRegister.equals("n")) {
								if (reRegister.equals("n")) {
									result = 1;
								}
								break;
							} else {
								System.out.println("¿Ã¹Ù¸¥ ±ÛÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
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
				System.out.println("1,2,3 Áß¿¡ ÇÑ ¼ýÀÚ¸¸ ½áÁÖ¼¼¿ä.");

			}

		}

		while (key != 4) {
			if (key == 5) {
				mainLoginPrintNoClean();// »Ì±âÇßÀ» ¶§, ¹Ù·Î ¾Æ·¡¿¡ mainLoginÃ¢ÀÌ ³ª¿Àµµ·Ï
			} else {
				mainLoginPrint();// »Ì±â¾ÈÇÑ ±âº» ÇÁ¸°Æ®
			}
				System.out.print(">> ");
				key = sc.nextInt();
				int num = mb.getPick();// »ÌÀ» ¼ö ¹Þ±â.
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

						// ¾Æ·¡ ÄÚµå¿¡, ar¹è¿­°ú »õ·ÎÀÌ »ÌÀº ¼±¼öµéµµÇÔ²² Ãâ·Â

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
						System.out.println("¡è ³» ¼±¼öµé");

						int count = mb.getPick();
						for (int i = 0; i < num; i++) {
							--count;
						}
						gp.playerPickRegist(mb.getId(), count); // »ÌÀº ¼±¼öµé ÇØ´çÇÏ´Â È¸¿ø¿¡°Ô ÀúÀå
						mb.setPick(count); // »ÌÀº È½¼ö¸¸Å­ ÁÙ¾îµë.
						key = 5;

						break;

					} else {
						System.out.println("»ÌÀ» ¼ö ÀÖ´Â ¼±¼ö°¡ ¾ø½À´Ï´Ù.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
				case 2:
					//³»°¡ °¡Áö°íÀÖ´Â ¼±¼öµé ÇÑ ¹ø Ãâ·ÂÇØÁÖ±â.
					
					
					//³»°¡ °¡Áö°íÀÖ´Â ¼±¼öµé ¹øÈ£¸¦ int¹è¿­¿¡ ÀúÀå				
					if (gp.havePlayer(mb.getId())) {
						gp.battlePlayer(mb.getId(), result);
					} else {
						System.out.println("ÃâÀüÇÒ ¼±¼ö°¡ ¾ø½À´Ï´Ù");
						System.out.println("°ÔÀÓÀ» Á¾·áÇÕ´Ï´Ù.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					// ¾îµð°¡ ¾ÈµÇ½Ç±î¿ë?? Àá½Ã¸¸¿ä ÇÏ³ª¸¸ È®ÀÎÇÏ°í ¹Ù·Î ¸»¾¸µå¸±°Ô¿ä³ß
					// ÀÌ°Å ¸â¹ö¿¡¼­ °­ÅÂÀ²·Î ³ª¿À´Âµ¥ ±× Ä£±¸´Â ´Ù¸¥ ¾ÆÀÌµð¿¡¿ä
					/*
					 * System.out.print("ÃâÀüÇÒ ¼±¼ö¸¦ °ñ¶óÁÖ¼¼¿ä >> "); int pick = sc.nextInt();
					 */
					//int¹è¿­ÀÇ index´Â ³»°¡ ¼±ÅÃÇÑ ¹øÈ£-1
					//ex)
					//Àú ¿À·ù°¡ ÇØ°áÀ» ¾î¶»°Ô ÇØ¾ßÇÏ³ª ½Í¾î¼­¿ä.
					//[1]Ãß½Å¼ö [2]¹ÚÂùÈ£
					//Ãß½Å¼ö ¾ÆÀÌµð°¡ µé¾îÀÖ´Â ¹è¿­Àº, arr[0] = Ãß½Å¼ö¹øÈ£.
					//¹ÚÂùÈ£ ¾ÆÀÌµð°¡ µé¾îÀÖ´Â ¹è¿­Àº, arr[1] = ¹ÚÂùÈ£ ¹øÈ£. 
					//À§¿¡ arr[ÀÎµ¦½º]´Â, ³»°¡ ¼±ÅÃÇÑ ¹øÈ£-1ÀÓ. arr[³»°¡¼±ÅÃÇÑ ¹øÈ£ -1]
					//ÀÌ°ÍÀ» ÀÌ¿ëÇÏ¸é µÉ°Å°°½À´Ï´ç
						
					//gp.battlePlayer(¼±¼ö¹øÈ£) ÇüÅÂ·Î ½áÁÖ¸é µÉ°Å°°¾Æ¿ä.
					
					//while¹® °É¾îÁÖ¾î¼­, ÇØ´çÇÏ´Â Á¶°ÇÀÌ µÇ¾úÀ» ½Ã, while¹® ºüÁ®³ª¿Ã ¼ö ÀÖµµ·Ï. 
					//ºüÁ®³ª¿À¸é¼­, db¿¡ memberÅ×ÀÌºí¿¡ rank Á¡¼ö ÁÙ ¼ö ÀÖµµ·Ï.
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
					System.out.println("Á¾·áÇÕ´Ï´Ù.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);

					break;
				default:
					System.out.println("¿Ã¹Ù¸¥ ¼ýÀÚ¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
		}

	

	public void openingPrint1() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Ü¡Û¡Ü¡Û BaseBall Game ¡Û¡Ü¡Û¡Ü    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openingPrint2() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Û¡Ü¡Û¡Ü BaseBall Game ¡Ü¡Û¡Ü¡Û    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mainPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Ü¡Û¡Ü¡Û BaseBall Game ¡Û¡Ü¡Û¡Ü    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                 [¾ß ±¸ °Ô ÀÓ]          \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢      <[1]·Î±×ÀÎ>  <[2]È¸¿ø°¡ÀÔ>  <[3]Á¾·á>\t¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");
		System.out.print(">>");
	}

	private void loginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Ü¡Û¡Ü¡Û BaseBall Game ¡Û¡Ü¡Û¡Ü    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                  [·Î ±× ÀÎ]          \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢      ¾ÆÀÌµð, ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä..    \t¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");

	}

	private void registerPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Ü¡Û¡Ü¡Û BaseBall Game ¡Û¡Ü¡Û¡Ü    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                 [È¸ ¿ø °¡ ÀÔ]          \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢                                      \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢   »ç¿ëÇÏ½Ç ¾ÆÀÌµð, ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä~ ^^// \t¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");

	}

	private void mainLoginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Ü¡Û¡Ü¡Û BaseBall Game ¡Û¡Ü¡Û¡Ü    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢      	  " + mb.getId() + "´Ô È¯¿µÇÕ´Ï´Ù!!" + "\t\t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢         " + "ÇöÀç »ÌÀ» ¼ö ÀÖ´Â ¼ö´Â " + mb.getPick() + "°³ ÀÖ½À´Ï´Ù! \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢<[1]¼±¼ö»Ì±â> <[2]°ÔÀÓ½ÃÀÛ> <[3]¼øÀ§È®ÀÎ> <[4]Á¾·á>¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");
	}

	private void mainLoginPrintNoClean() {

		System.out.println("¦£¦¡¦¡¦¨¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¨¦¡¦¡¦¤");
		System.out.println("¦¢  ¦¢           ¡Ü¡Û¡Ü¡Û BaseBall Game ¡Û¡Ü¡Û¡Ü    \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢      	  " + mb.getId() + "´Ô È¯¿µÇÕ´Ï´Ù!!" + "\t\t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢         " + "ÇöÀç »ÌÀ» ¼ö ÀÖ´Â ¼ö´Â " + mb.getPick() + "°³ ÀÖ½À´Ï´Ù! \t¦¢  ¦¢");
		System.out.println("¦¢  ¦¢<[1]¼±¼ö»Ì±â> <[2]°ÔÀÓ½ÃÀÛ> <[3]¼øÀ§È®ÀÎ> <[4]Á¾·á>¦¢  ¦¢");
		System.out.println("¦¦¦¡¦¡¦ª¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦¡¦ª¦¡¦¡¦¥");
	}
	// Áö¿öÁÖ¼¼¿ä¿ì
}
