package BaseBallGame;

import java.util.Scanner;

public class UserInterFace {

	// ����� Ű���尪
	int key = 0;
	Scanner sc;
	LoginManagement lm;
	Member mb;

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

					} else {
						System.out.println("�α��ο� �����Ͽ����ϴ�...");
						key = 3;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						break;

					}
				}

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
						
						break;
					} else {
						System.out.println("�ٸ� ȸ���� �ֽ��ϴ�!");

						while (reRegister.equals("s")) {
							System.out.println("�ٽ� ȸ������ �Ͻðڽ��ϱ�? [y,n]");
							reRegister = sc.next();

							if (reRegister.equals("y") || reRegister.equals("n")) {
								break;
							} else {
								System.out.println("�ùٸ� ���ڸ� �Է����ּ���.");
								reRegister = "s";

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

		mainLoginPrint();		
		System.out.print(">> ");
		key = sc.nextInt();
		
		switch(key) {
		case 1:
			
		case 2:
			
		case 3:
			System.out.println("�����մϴ�.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			System.exit(0);
			default:
				System.out.println("�ùٸ� ���ڸ� �Է��ϼ���.");
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
		System.out.println("��  ��        " + mb.getId() + "�� ȯ���մϴ�!!" + "\t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��       <[1]�����̱�> <[2]���ӽ���> <[3]����>\t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");
	}

}
