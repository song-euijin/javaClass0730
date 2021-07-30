package SHOPPING;

import java.util.Scanner;

public class ShoppingMain {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		ShoppingSQL ssql = new ShoppingSQL();

		// 테이블 전부!
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
			System.out.println("1.회원가입      2.판매자 메뉴  3.조건 조회");
			System.out.println("4.모두 조회     5.구매           6.결제내역 조회");
			System.out.println("7.주문 취소     8.계정관리     9.종료");
			System.out.println("===============================");
			System.out.print("메뉴 선택 >> ");

			menu = sc.nextInt();

			switch (menu) {

			case 1:
				//회원가입
				System.out.println("회원정보를 입력해주세요.");

				System.out.print("이름 >> ");
				String n_name = sc.next();

				System.out.print("아이디 >> ");
				String n_id = sc.next();

				System.out.print("비밀번호 >> ");
				int n_pw = sc.nextInt();

				System.out.println("생년월일");
				System.out.print("년도(4자리) >> ");
				String year = sc.next();

				System.out.print("월 >> ");
				int month = sc.nextInt();

				System.out.print("일 >> ");
				int day = sc.nextInt();

				System.out.print("이메일 >> ");
				String n_email = sc.next();

				System.out.print("전화번호 >> ");
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
				// 판매자 메뉴
				
				// 판매자 로그인				
				System.out.println("아이디 비밀번호를 입력하세요.");
				System.out.print("아이디: ");
				String id = sc.next();
				System.out.print("비밀번호: ");
				int pw = sc.nextInt();

				boolean check = ssql.idCheck(id, pw);
				
				// 테이블에 등록된 판매자가 맞으면 if문 실행
				do {

					if (check) {
						System.out.println();
						System.out.println("=====================================");
						System.out.println("1.상품등록     2.상품조회     3.상품삭제	4.종료");
						System.out.println("=====================================");
						System.out.println();
						System.out.print("메뉴 선택: ");
						menu2 = sc.nextInt();

						switch (menu2) {
						case 1: // 상품등록
							System.out.println();
							System.out.println("상품을 등록해주세요!");

							System.out.print("상품명: ");
							String gd_name = sc.next();

							System.out.print("상품코드: ");
							int gd_code = sc.nextInt();

							System.out.print("상품종류: ");
							System.out.println("1. 하의 | 2. 상의 | 3. 원피스/점프수트  | 4. 쥬얼리  |  5. 가방 & 신발");
							System.out.print("메뉴를 선택해주세요: ");
							int gd_kind = sc.nextInt();

							System.out.print("상품가격: ");
							int gd_price = sc.nextInt();

							System.out.print("상품사이즈: ");
							String gd_size = sc.next();

							System.out.print("상품색상: ");
							String gd_color = sc.next();

							gd.setGd_name(gd_name);
							gd.setGd_code(gd_code);
							gd.setGd_kind(gd_kind);
							gd.setGd_price(gd_price);
							gd.setGd_size(gd_size);
							gd.setGd_color(gd_color);

							ssql.regi(gd);

							break;
						case 2: // 상품조회
							ssql.select(gd);
							break;

						case 3: // 상품삭제
							System.out.println("상품을 삭제해주세요!");
							System.out.print("상품코드: ");
							int code = sc.nextInt();

							boolean check1 = ssql.codeCheck(code);

							if (check1) { // 코드가 일치
								ssql.delete(code);
							} else { // 일치하지 않을때
								System.out.println("코드가 일치하지 않습니다.");
							}
							break;

						case 4: // 종료
							System.out.println("시스템을 종료합니다!");
							run2 = false;
							break;
						default:
							System.out.println("잘못 입력했습니다. 다시 입력해주세요!");
							break;
						}

					} else { // 아이디와 비밀번호가 불일치
						System.out.println(" 아이디와 비밀번호가 일치하지 않습니다.");
						run2 = false;
					}

				} while (run2);
				break;
				
			case 3:
				// 조건조회
				do {

					System.out.println("보고싶으신 카테고리를 선택해주세요!");
					System.out.println("-------------------------------------------------------------");
					System.out.println("1. 하의 | 2. 상의 | 3. 원피스/점프수트  |4. 쥬얼리   | 5. 가방&신발  |  6. 종료");
					System.out.println("-------------------------------------------------------------");
					System.out.print("메뉴 선택 >> ");
					System.out.println();
					kind = sc.nextInt();

					switch (kind) {

					case 1: // 하의
						boolean check2 = ssql.bottom(kind);
						
						break;

					case 2: // 상의
						boolean check3 = ssql.top(kind);
						
						break;

					case 3: // 한벌옷
						boolean check4 = ssql.suit(kind);
						
						break;

					case 4:	// 쥬얼리
						boolean check5 = ssql.jewl(kind);
						break;

					case 5:	// 가방&신발
						boolean check6 = ssql.acc(kind);
						break;

					case 6:	//종료
						run3 = false;
						break;

					default:
						System.out.println("잘못 입력했습니다. 다시 입력해주세요");
						break;
					}

				} while (run3); 

				break;

			case 4:
				// 모두 조회
				ssql.selectall();
				break;

			case 5:
				// 구매

				System.out.print("아이디: ");
				String nd_id = sc.next();
				System.out.print("상품번호: ");
				int nd_code = sc.nextInt();
				System.out.print("주소: ");
				String nd_addr = sc.next();

				System.out.print("거래 은행: ");
				String nd_Bank = sc.next();

				System.out.print("거래 계좌: ");
				long nd_BankNo = sc.nextLong();

				gd.setOd_id(nd_id);
				gd.setOd_code(nd_code);
				gd.setAddr(nd_addr);
				gd.setBank(nd_Bank);
				gd.setBankNo(nd_BankNo);

				ssql.ORDER_REGI(gd);

				break;

			case 6:
				// 결제내역 조회
				System.out.println();
				System.out.println("결제 조회를 위해 아이디를 입력해주세요 ");
				System.out.print("아이디: ");
				String to_id = sc.next();
				gd.setOd_id(to_id);

				ssql.ORDER_List(gd);
				ssql.ORDER_Total(gd);

				break;
			
			case 7:
				// 주문취소
				System.out.println("주문 취소를 위해 정보를 입력해주세요!");
				System.out.print("아이디: ");
				String de_id = sc.next();

				System.out.print("상품번호: ");
				int de_code = sc.nextInt();

				gd.setOd_id(de_id);
				gd.setOd_code(de_code);

				ssql.Order_delet(gd);

				break;

			case 8:
				// 계정관리
				run4 = true;

				do {
					System.out.println();
					System.out.println("===============================");
					System.out.println("1.계정찾기   2.회원정보수정    3.회원 탈퇴  4. 종료");
					System.out.println("===============================");
					System.out.print("메뉴선택: ");
					menu3 = sc.nextInt();
					switch (menu3) {
					// [ 계정찾기 ]
					case 1:
						do {
							System.out.println("계정을 찾기 위한 메뉴를 선택해주세요!");
							System.out.println("===============================");
							System.out.println("1.아이디 찾기   2.비밀번호찾기    3.종료");
							System.out.println("===============================");
							System.out.print("메뉴 선택:  ");
							menu4 = sc.nextInt();
							switch (menu4) {
							case 1:
								// 아이디 찾기

								System.out.println("아이디를 찾기위해 정보를 입력해주세요.");
								System.out.print("이름: ");
								String name = sc.next();
								System.out.println("생년월일");
								System.out.print("년도(4자리) >> ");
								String year2 = sc.next();

								System.out.print("월 >> ");
								int month2 = sc.nextInt();

								System.out.print("일 >> ");
								int day2 = sc.nextInt();

								System.out.print("전화번호: ");
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

								if (check3) { // 입력한 정보있을 시

								} else { // 정보가 없을 시
									System.out.println("없는 계정입니다. 회원가입을 해주세요!");
								}

								break;
							case 2:
								// 비밀번호찾기
								System.out.println("비밀번호를 찾기위해 정보를 입력해주세요.");

								System.out.print("이름: ");
								String name1 = sc.next();
								System.out.print("아이디: ");
								String id1 = sc.next();

								boolean check4 = ssql.pwCheck(name1, id1);

								if (check4) { // 입력한 정보있을 시

								} else { // 정보가 없을 시
									System.out.println("없는 아이디입니다. 회원가입을 해주세요!");
								}

								break;
							case 3:
								// 종료
								System.out.println("계정 찾기를 종료합니다.");
								run3 = false;
								break;

							default:
								System.out.println("잘못입력하셨습니다.");
								break;
							}
						} while (run3);
						break;

					// [ 개인정보수정 ]
					case 2:
						// 회원정보수정
						System.out.println("정보 수정을 위해 아이디와 비밀번호를 입력해주세요!");
						System.out.println("아이디 비밀번호 조회");
						System.out.print("아이디: ");
						String id5 = sc.next();
						System.out.print("비밀번호: ");
						int pw5 = sc.nextInt();

						boolean check5 = ssql.idCheck5(id5, pw5);

						if (check5) { // 아이디와 비밀번호가 일치
							System.out.println(id5 + "님, 회원정보를 수정해주세요.");

							System.out.print("이름 >> ");
							String n_name1 = sc.next();

							System.out.print("비밀번호 >> ");
							int n_pw1 = sc.nextInt();

							System.out.println("생년월일");
							System.out.print("년도(4자리) >> ");
							String year1 = sc.next();

							System.out.print("월 >> ");
							int month5 = sc.nextInt();

							System.out.print("일 >> ");
							int day5 = sc.nextInt();

							System.out.print("이메일 >> ");
							String n_email1 = sc.next();

							System.out.print("전화번호 >> ");
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

						} else // 아이디와 비밀번호가 불일치
							System.out.println(" 아이디와 비밀번호가 일치하지 않습니다.");

						break;
					// [ 탈퇴하기 ]
					case 3:
						// 회원삭제

						System.out.println("※ 물품이 배송중이면  탈퇴할 수 없습니다.※");
						System.out.println("아이디 비밀번호 조회");
						System.out.print("아이디: ");
						String id6 = sc.next();
						System.out.print("비밀번호: ");
						int pw6 = sc.nextInt();

						boolean check6 = ssql.idCheck5(id6, pw6);

						if (check6) { // 아이디와 비밀번호 일치
							ssql.delete(id6);
						} else { // 일치하지 않을때
							System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
						}
						break;
					case 4:	// 종료
						System.out.println("계정설정을 종료합니다.");
						run4 = false;
						break;

					default:
						System.out.println("잘못 입력했습니다. 다시 입력해주세요!");
						break;

					}
				} while (run4);
				break;


			case 9:
				// 종료
				System.out.println("시스템을 종료합니다!");
				run = false;
				ssql.conClose();
				break;
			

			default:
				System.out.println("잘못 입력했습니다. 다시 입력해주세요!");
				break;

			}
		} while (run);
	}
}