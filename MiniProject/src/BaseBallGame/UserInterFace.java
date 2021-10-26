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
	// ����� Ű���尪
	int key = 0;
	List<String> result;

	public UserInterFace() {
		sc = new Scanner(System.in);
		lm = new LoginManagement();

	}


	public void start() {

		// ������ ����
		openingPrint1();
		openingPrint2();

		while (key != 3) {
			// ���� â
			mainPrint();

			try {
				key = sc.nextInt();
			} catch (Exception e) {
				System.out.println("���ڸ� �Է����ּ���.");

			}

			switch (key) {

			// �α���
			case 1:
				String reLogin = "";
				reLogin = "y";

				while (mb == null || reLogin.equals("y")) {
					loginPrint();

					System.out.print("���̵� >> ");
					String id = sc.next();

					System.out.print("��й�ȣ >> ");
					String pw = sc.next();

					mb = lm.login(id, pw);

					if (mb == null) {
						System.out.println("ȸ�������� �����ϴ�.");
						reLogin = "s";

						while (reLogin.equals("s")) {
							System.out.println("�ٽ� �α��� �Ͻðڽ��ϱ�? [y,n]");
							reLogin = sc.next();

							if (reLogin.equals("y") || reLogin.equals("n")) {
								break;
							} else {
								System.out.println("y�� n�߿� �Է����ּ���");
								reLogin = "s";
							}
						}
						if (reLogin.equals("n")) {
							mb = new Member();
							key = 0;
						}

					} else {
						System.out.println("�α��ο� �����Ͽ����ϴ�...");

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

			// ȸ������
			case 2:
				int result = 0;
				String reRegister = "";
				while (result <= 0 || reRegister.equals("y")) {
					registerPrint();

					System.out.print("���̵� >>");
					String id = sc.next();

					System.out.print("��й�ȣ >>");
					String pw = sc.next();
					result = lm.register(id, pw);

					if (result > 0) {
						System.out.println("ȸ�����Կ� �����Ͽ����ϴ�!!");
						reRegister = "n";
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						break;
					} else {
						System.out.println("�ٸ� ȸ���� �ֽ��ϴ�!");
						reRegister = "r";

						while (reRegister.equals("r")) {
							System.out.println("�ٽ� ȸ������ �Ͻðڽ��ϱ�? [y,n]");
							reRegister = sc.next();

							if (reRegister.equals("y") || reRegister.equals("n")) {
								if (reRegister.equals("n")) {
									result = 1;
								}
								break;
							} else {
								System.out.println("�ùٸ� ���ڸ� �Է����ּ���.");
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
				System.out.println("1,2,3 �߿� �� ���ڸ� ���ּ���.");

			}

		}

		while (key != 4) {
			if (key == 5) {
				mainLoginPrintNoClean();// �̱����� ��, �ٷ� �Ʒ��� mainLoginâ�� ��������
			} else {
				mainLoginPrint();// �̱���� �⺻ ����Ʈ
			}
				System.out.print(">> ");
				key = sc.nextInt();
				int num = mb.getPick();// ���� �� �ޱ�.
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

						// �Ʒ� �ڵ忡, ar�迭�� ������ ���� �����鵵�Բ� ���

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
						System.out.println("�� �� ������");

						int count = mb.getPick();
						for (int i = 0; i < num; i++) {
							--count;
						}
						gp.playerPickRegist(mb.getId(), count); // ���� ������ �ش��ϴ� ȸ������ ����
						mb.setPick(count); // ���� Ƚ����ŭ �پ��.
						key = 5;

						break;

					} else {
						System.out.println("���� �� �ִ� ������ �����ϴ�.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;
					}
				case 2:
					//���� �������ִ� ������ �� �� ������ֱ�.
					
					
					//���� �������ִ� ������ ��ȣ�� int�迭�� ����				
					if (gp.havePlayer(mb.getId())) {
						gp.battlePlayer(mb.getId(), result);
					} else {
						System.out.println("������ ������ �����ϴ�");
						System.out.println("������ �����մϴ�.");
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					// ��� �ȵǽǱ��?? ��ø��� �ϳ��� Ȯ���ϰ� �ٷ� �����帱�Կ��
					// �̰� ������� �������� �����µ� �� ģ���� �ٸ� ���̵𿡿�
					/*
					 * System.out.print("������ ������ ����ּ��� >> "); int pick = sc.nextInt();
					 */
					//int�迭�� index�� ���� ������ ��ȣ-1
					//ex)
					//�� ������ �ذ��� ��� �ؾ��ϳ� �;��.
					//[1]�߽ż� [2]����ȣ
					//�߽ż� ���̵� ����ִ� �迭��, arr[0] = �߽ż���ȣ.
					//����ȣ ���̵� ����ִ� �迭��, arr[1] = ����ȣ ��ȣ. 
					//���� arr[�ε���]��, ���� ������ ��ȣ-1��. arr[���������� ��ȣ -1]
					//�̰��� �̿��ϸ� �ɰŰ����ϴ�
						
					//gp.battlePlayer(������ȣ) ���·� ���ָ� �ɰŰ��ƿ�.
					
					//while�� �ɾ��־, �ش��ϴ� ������ �Ǿ��� ��, while�� �������� �� �ֵ���. 
					//���������鼭, db�� member���̺� rank ���� �� �� �ֵ���.
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
					System.out.println("�����մϴ�.");
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.exit(0);

					break;
				default:
					System.out.println("�ùٸ� ���ڸ� �Է��ϼ���.");
				}
			}
		}

	

	public void openingPrint1() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ܡۡܡ� BaseBall Game �ۡܡۡ�    \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void openingPrint2() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ۡܡۡ� BaseBall Game �ܡۡܡ�    \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void mainPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ܡۡܡ� BaseBall Game �ۡܡۡ�    \t��  ��");
		System.out.println("��  ��                 [�� �� �� ��]          \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��      <[1]�α���>  <[2]ȸ������>  <[3]����>\t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.print(">>");
	}

	private void loginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ܡۡܡ� BaseBall Game �ۡܡۡ�    \t��  ��");
		System.out.println("��  ��                  [�� �� ��]          \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��      ���̵�, ��й�ȣ�� �Է����ּ���..    \t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");

	}

	private void registerPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ܡۡܡ� BaseBall Game �ۡܡۡ�    \t��  ��");
		System.out.println("��  ��                 [ȸ �� �� ��]          \t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��   ����Ͻ� ���̵�, ��й�ȣ�� �Է����ּ���~ ^^// \t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");

	}

	private void mainLoginPrint() {
		for (int i = 0; i < 80; i++)
			System.out.println("\n");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ܡۡܡ� BaseBall Game �ۡܡۡ�    \t��  ��");
		System.out.println("��  ��      	  " + mb.getId() + "�� ȯ���մϴ�!!" + "\t\t��  ��");
		System.out.println("��  ��         " + "���� ���� �� �ִ� ���� " + mb.getPick() + "�� �ֽ��ϴ�! \t��  ��");
		System.out.println("��  ��<[1]�����̱�> <[2]���ӽ���> <[3]����Ȯ��> <[4]����>��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");
	}

	private void mainLoginPrintNoClean() {

		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.println("��  ��           �ܡۡܡ� BaseBall Game �ۡܡۡ�    \t��  ��");
		System.out.println("��  ��      	  " + mb.getId() + "�� ȯ���մϴ�!!" + "\t\t��  ��");
		System.out.println("��  ��         " + "���� ���� �� �ִ� ���� " + mb.getPick() + "�� �ֽ��ϴ�! \t��  ��");
		System.out.println("��  ��<[1]�����̱�> <[2]���ӽ���> <[3]����Ȯ��> <[4]����>��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");
	}
	// �����ּ����
}
