package BaseBallGame;

import java.util.Random;
import java.util.Scanner;

public class UserInterFace {
	Random rd =new Random();
	GamePlay gp=new GamePlay();
	// ����� Ű���尪
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
						if(reLogin.equals("n")) {
							mb= new Member();	
							key = 0;
						}

					} else {
						System.out.println("�α��ο� �����Ͽ����ϴ�...");

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
		System.out.println("��  ��      	  " + mb.getId() + "�� ȯ���մϴ�!!"      + "\t\t��  ��");
		System.out.println("��  ��                                      \t��  ��");
		System.out.println("��  ��       <[1]�����̱�> <[2]���ӽ���> <[3]����>\t��  ��");
		System.out.println("��������������������������������������������������������������������������������������������������������");
	}

}
