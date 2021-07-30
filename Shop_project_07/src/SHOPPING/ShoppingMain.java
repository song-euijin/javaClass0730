package SHOPPING;

import java.util.Scanner;

public class ShoppingMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ShoppingSQL ssql = new ShoppingSQL();

		// ���̺� ����!
		GSeller gd = new GSeller();

		boolean run = true;
		boolean run2 = true;
		boolean run3 = true;
		boolean run4 = true;

		int menu = 0;
		int menu2 = 0;
		int menu3 = 0;
		int menu4 = 0;

		int kind = 0;
		ssql.connect();

		do {

			System.out.println("===============================");
			System.out.println("1.ȸ������      2.�Ǹ��� �޴�  3.���� ��ȸ");
			System.out.println("4.��� ��ȸ     5.����           6.�������� ��ȸ");
			System.out.println("7.�ֹ� ���     8.��������     9.����");
			System.out.println("===============================");
			System.out.print("�޴� ���� >> ");

			menu = sc.nextInt();

			switch (menu) {

			case 1:
				//ȸ������
				System.out.println("ȸ�������� �Է����ּ���.");

				System.out.print("�̸� >> ");
				String n_name = sc.next();

				System.out.print("���̵� >> ");
				String n_id = sc.next();

				System.out.print("��й�ȣ >> ");
				int n_pw = sc.nextInt();

				System.out.println("�������");
				System.out.print("�⵵(4�ڸ�) >> ");
				String year = sc.next();

				System.out.print("�� >> ");
				int month = sc.nextInt();

				System.out.print("�� >> ");
				int day = sc.nextInt();

				System.out.print("�̸��� >> ");
				String n_email = sc.next();

				System.out.print("��ȭ��ȣ >> ");
				String n_phone = sc.next();

				String month1;
				String day1;

				if (month >= 10) {
					month1 = Integer.toString(month);
				} else {
					month1 = "0" + Integer.toString(month);
				}

				if (day >= 10) {
					day1 = Integer.toString(day);
				} else {
					day1 = "0" + Integer.toString(day);
				}

				String n_birth = year + month1 + day1;

				gd.setIn_name(n_name);
				gd.setIn_id(n_id);
				gd.setPw(n_pw);
				gd.setBirth(n_birth);
				gd.setEmail(n_email);
				gd.setPhone(n_phone);

				ssql.memberJoin(gd);

				break;
			case 2:
				// �Ǹ��� �޴�
				
				// �Ǹ��� �α���				
				System.out.println("���̵� ��й�ȣ�� �Է��ϼ���.");
				System.out.print("���̵�: ");
				String id = sc.next();
				System.out.print("��й�ȣ: ");
				int pw = sc.nextInt();

				boolean check = ssql.idCheck(id, pw);
				
				// ���̺� ��ϵ� �Ǹ��ڰ� ������ if�� ����
				do {

					if (check) {
						System.out.println();
						System.out.println("=====================================");
						System.out.println("1.��ǰ���     2.��ǰ��ȸ     3.��ǰ����	4.����");
						System.out.println("=====================================");
						System.out.println();
						System.out.print("�޴� ����: ");
						menu2 = sc.nextInt();

						switch (menu2) {
						case 1: // ��ǰ���
							System.out.println();
							System.out.println("��ǰ�� ������ּ���!");

							System.out.print("��ǰ��: ");
							String gd_name = sc.next();

							System.out.print("��ǰ�ڵ�: ");
							int gd_code = sc.nextInt();

							System.out.print("��ǰ����: ");
							System.out.println("1. ���� | 2. ���� | 3. ���ǽ�/������Ʈ  | 4. ���  |  5. ���� & �Ź�");
							System.out.print("�޴��� �������ּ���: ");
							int gd_kind = sc.nextInt();

							System.out.print("��ǰ����: ");
							int gd_price = sc.nextInt();

							System.out.print("��ǰ������: ");
							String gd_size = sc.next();

							System.out.print("��ǰ����: ");
							String gd_color = sc.next();

							gd.setGd_name(gd_name);
							gd.setGd_code(gd_code);
							gd.setGd_kind(gd_kind);
							gd.setGd_price(gd_price);
							gd.setGd_size(gd_size);
							gd.setGd_color(gd_color);

							ssql.regi(gd);

							break;
						case 2: // ��ǰ��ȸ
							ssql.select(gd);
							break;

						case 3: // ��ǰ����
							System.out.println("��ǰ�� �������ּ���!");
							System.out.print("��ǰ�ڵ�: ");
							int code = sc.nextInt();

							boolean check1 = ssql.codeCheck(code);

							if (check1) { // �ڵ尡 ��ġ
								ssql.delete(code);
							} else { // ��ġ���� ������
								System.out.println("�ڵ尡 ��ġ���� �ʽ��ϴ�.");
							}
							break;

						case 4: // ����
							System.out.println("�ý����� �����մϴ�!");
							run2 = false;
							break;
						default:
							System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���!");
							break;
						}

					} else { // ���̵�� ��й�ȣ�� ����ġ
						System.out.println(" ���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						run2 = false;
					}

				} while (run2);
				break;
				
			case 3:
				// ������ȸ
				do {

					System.out.println("��������� ī�װ��� �������ּ���!");
					System.out.println("-------------------------------------------------------------");
					System.out.println("1. ���� | 2. ���� | 3. ���ǽ�/������Ʈ  |4. ���   | 5. ����&�Ź�  |  6. ����");
					System.out.println("-------------------------------------------------------------");
					System.out.print("�޴� ���� >> ");
					System.out.println();
					kind = sc.nextInt();

					switch (kind) {

					case 1: // ����
						boolean check2 = ssql.bottom(kind);
						
						break;

					case 2: // ����
						boolean check3 = ssql.top(kind);
						
						break;

					case 3: // �ѹ���
						boolean check4 = ssql.suit(kind);
						
						break;

					case 4:	// ���
						boolean check5 = ssql.jewl(kind);
						break;

					case 5:	// ����&�Ź�
						boolean check6 = ssql.acc(kind);
						break;

					case 6:	//����
						run3 = false;
						break;

					default:
						System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���");
						break;
					}

				} while (run3); 

				break;

			case 4:
				// ��� ��ȸ
				ssql.selectall();
				break;

			case 5:
				// ����

				System.out.print("���̵�: ");
				String nd_id = sc.next();
				System.out.print("��ǰ��ȣ: ");
				int nd_code = sc.nextInt();
				System.out.print("�ּ�: ");
				String nd_addr = sc.next();

				System.out.print("�ŷ� ����: ");
				String nd_Bank = sc.next();

				System.out.print("�ŷ� ����: ");
				long nd_BankNo = sc.nextLong();

				gd.setOd_id(nd_id);
				gd.setOd_code(nd_code);
				gd.setAddr(nd_addr);
				gd.setBank(nd_Bank);
				gd.setBankNo(nd_BankNo);

				ssql.ORDER_REGI(gd);

				break;

			case 6:
				// �������� ��ȸ
				System.out.println();
				System.out.println("���� ��ȸ�� ���� ���̵� �Է����ּ��� ");
				System.out.print("���̵�: ");
				String to_id = sc.next();
				gd.setOd_id(to_id);

				ssql.ORDER_List(gd);
				ssql.ORDER_Total(gd);

				break;
			
			case 7:
				// �ֹ����
				System.out.println("�ֹ� ��Ҹ� ���� ������ �Է����ּ���!");
				System.out.print("���̵�: ");
				String de_id = sc.next();

				System.out.print("��ǰ��ȣ: ");
				int de_code = sc.nextInt();

				gd.setOd_id(de_id);
				gd.setOd_code(de_code);

				ssql.Order_delet(gd);

				break;

			case 8:
				// ��������
				run4 = true;

				do {
					System.out.println();
					System.out.println("===============================");
					System.out.println("1.����ã��   2.ȸ����������    3.ȸ�� Ż��  4. ����");
					System.out.println("===============================");
					System.out.print("�޴�����: ");
					menu3 = sc.nextInt();
					switch (menu3) {
					// [ ����ã�� ]
					case 1:
						do {
							System.out.println("������ ã�� ���� �޴��� �������ּ���!");
							System.out.println("===============================");
							System.out.println("1.���̵� ã��   2.��й�ȣã��    3.����");
							System.out.println("===============================");
							System.out.print("�޴� ����:  ");
							menu4 = sc.nextInt();
							switch (menu4) {
							case 1:
								// ���̵� ã��

								System.out.println("���̵� ã������ ������ �Է����ּ���.");
								System.out.print("�̸�: ");
								String name = sc.next();
								System.out.println("�������");
								System.out.print("�⵵(4�ڸ�) >> ");
								String year2 = sc.next();

								System.out.print("�� >> ");
								int month2 = sc.nextInt();

								System.out.print("�� >> ");
								int day2 = sc.nextInt();

								System.out.print("��ȭ��ȣ: ");
								String phone = sc.next();

								String month3;
								String day3;

								if (month2 >= 10) {
									month3 = Integer.toString(month2);
								} else {
									month3 = "0" + Integer.toString(month2);
								}

								if (day2 >= 10) {
									day3 = Integer.toString(day2);
								} else {
									day3 = "0" + Integer.toString(day2);
								}

								String birth = year2 + month3 + day3;

								boolean check3 = ssql.infoCheck(name, birth, phone);

								if (check3) { // �Է��� �������� ��

								} else { // ������ ���� ��
									System.out.println("���� �����Դϴ�. ȸ�������� ���ּ���!");
								}

								break;
							case 2:
								// ��й�ȣã��
								System.out.println("��й�ȣ�� ã������ ������ �Է����ּ���.");

								System.out.print("�̸�: ");
								String name1 = sc.next();
								System.out.print("���̵�: ");
								String id1 = sc.next();

								boolean check4 = ssql.pwCheck(name1, id1);

								if (check4) { // �Է��� �������� ��

								} else { // ������ ���� ��
									System.out.println("���� ���̵��Դϴ�. ȸ�������� ���ּ���!");
								}

								break;
							case 3:
								// ����
								System.out.println("���� ã�⸦ �����մϴ�.");
								run3 = false;
								break;

							default:
								System.out.println("�߸��Է��ϼ̽��ϴ�.");
								break;
							}
						} while (run3);
						break;

					// [ ������������ ]
					case 2:
						// ȸ����������
						System.out.println("���� ������ ���� ���̵�� ��й�ȣ�� �Է����ּ���!");
						System.out.println("���̵� ��й�ȣ ��ȸ");
						System.out.print("���̵�: ");
						String id5 = sc.next();
						System.out.print("��й�ȣ: ");
						int pw5 = sc.nextInt();

						boolean check5 = ssql.idCheck5(id5, pw5);

						if (check5) { // ���̵�� ��й�ȣ�� ��ġ
							System.out.println(id5 + "��, ȸ�������� �������ּ���.");

							System.out.print("�̸� >> ");
							String n_name1 = sc.next();

							System.out.print("��й�ȣ >> ");
							int n_pw1 = sc.nextInt();

							System.out.println("�������");
							System.out.print("�⵵(4�ڸ�) >> ");
							String year1 = sc.next();

							System.out.print("�� >> ");
							int month5 = sc.nextInt();

							System.out.print("�� >> ");
							int day5 = sc.nextInt();

							System.out.print("�̸��� >> ");
							String n_email1 = sc.next();

							System.out.print("��ȭ��ȣ >> ");
							String n_phone1 = sc.next();

							String month6;
							String day6;

							if (month5 >= 10) {
								month6 = Integer.toString(month5);
							} else {
								month6 = "0" + Integer.toString(month5);
							}

							if (day5 >= 10) {
								day6 = Integer.toString(day5);
							} else {
								day6 = "0" + Integer.toString(day5);
							}

							String n_birth1 = year1 + month6 + day6;

							gd.setIn_id(id5);
							gd.setPw(n_pw1);
							gd.setIn_name(n_name1);
							gd.setPw(n_pw1);
							gd.setBirth(n_birth1);
							gd.setEmail(n_email1);
							gd.setPhone(n_phone1);

							ssql.memberJoin1(gd);

						} else // ���̵�� ��й�ȣ�� ����ġ
							System.out.println(" ���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");

						break;
					// [ Ż���ϱ� ]
					case 3:
						// ȸ������

						System.out.println("�� ��ǰ�� ������̸�  Ż���� �� �����ϴ�.��");
						System.out.println("���̵� ��й�ȣ ��ȸ");
						System.out.print("���̵�: ");
						String id6 = sc.next();
						System.out.print("��й�ȣ: ");
						int pw6 = sc.nextInt();

						boolean check6 = ssql.idCheck5(id6, pw6);

						if (check6) { // ���̵�� ��й�ȣ ��ġ
							ssql.delete(id6);
						} else { // ��ġ���� ������
							System.out.println("���̵�� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
						}
						break;
					case 4:	// ����
						System.out.println("���������� �����մϴ�.");
						run4 = false;
						break;

					default:
						System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���!");
						break;

					}
				} while (run4);
				break;


			case 9:
				// ����
				System.out.println("�ý����� �����մϴ�!");
				run = false;
				ssql.conClose();
				break;
			

			default:
				System.out.println("�߸� �Է��߽��ϴ�. �ٽ� �Է����ּ���!");
				break;

			}
		} while (run);
	}
}