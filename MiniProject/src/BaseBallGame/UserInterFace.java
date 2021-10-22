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
				while (mb == null) {
					loginPrint();

					System.out.print("���̵� >> ");
					String id = sc.next();

					System.out.print("��й�ȣ >> ");
					String pw = sc.next();

					mb = lm.login(id, pw);
					if (mb == null) {
						System.out.println("ȸ�������� �����ϴ�.");
						System.out.println("�ٽ� �Է����ּ���.");
					}
				}
				System.out.println("�α��ο� �����Ͽ����ϴ�...");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;

			// ȸ������
			case 2:
				int result;
				registerPrint();
				
				System.out.print("���̵� >>");
				String id = sc.next();

				System.out.print("��й�ȣ >>");
				String pw = sc.next();
				result = lm.register(id,pw);
				
				if(result>0 ) {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ�!!");
				}else {
					System.out.println("ȸ�����Կ� �����Ͽ����ϴ� �̤�");
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
		System.out.println("��  ��           <[1]�����̱�>  <[2]���ӽ���>  \t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");
		System.out.print(">>");

	}

}
