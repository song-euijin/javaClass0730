package SHOPPING;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ShoppingSQL {

	Connection con = null;

	Statement stmt = null;
	PreparedStatement pstmt = null;

	ResultSet rs = null;

	// [ 접속 메소드 ]
	public void connect() {

		con = DBC.DBConnect();

	}


	// [회원가입 메소드]
	public void memberJoin(GSeller gd) {

		String sql = "INSERT INTO INFO VALUES(?,?,?,TO_DATE(?),?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gd.getIn_name());
			pstmt.setString(2, gd.getIn_id());
			pstmt.setInt(3, gd.getPw());
			pstmt.setString(4, gd.getBirth());
			pstmt.setString(5, gd.getEmail());
			pstmt.setString(6, gd.getPhone());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원가입 성공!");
			} else {
				System.out.println("회원가입 실패!");
			}

		} catch (SQLException e) {
			System.out.println("SQLException 발생!");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// [ 판매자 아이디확인 ]
	public boolean idCheck(String id, int pw) {
		boolean checkResult = false;

		String sql = "SELECT * FROM GSELLER WHERE GD_ID = ? AND GD_PW = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setInt(2, pw);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return checkResult;
	}

	// [ 판매자의 상품등록 ]
	public void regi(GSeller gd) {
		String sql = "INSERT INTO GSELLER(GD_NAME,GD_CODE,GD_KIND,GD_PRICE,GD_SIZE,GD_COLOR) VALUES(?,?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gd.getGd_name());
			pstmt.setInt(2, gd.getGd_code());
			pstmt.setInt(3, gd.getGd_kind());
			pstmt.setInt(4, gd.getGd_price());
			pstmt.setString(5, gd.getGd_size());
			pstmt.setString(6, gd.getGd_color());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("상품등록 성공!");
			} else {
				System.out.println("상품등록 실패!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// [판매자 상품조회 ]
	public void select(GSeller gd) {
		String sql = "SELECT GD_NAME,GD_CODE,GD_KIND,GD_PRICE,GD_SIZE,GD_COLOR FROM GSELLER where gd_code > 8 ";

		try {
			// pstmt = con.prepareStatement(sql);

			// rs = pstmt.executeQuery();

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("상품이름: " + rs.getString(1));
				System.out.println("상품코드: " + rs.getInt(2));
				System.out.println("상품종류: " + rs.getInt(3));
				System.out.println("상품가격: " + rs.getInt(4));
				System.out.println("상품사이즈: " + rs.getString(5));
				System.out.println("상품색상: " + rs.getString(6));
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// [ 상품삭제를 위한 코드확인 ]
	public boolean codeCheck(int code) {
		boolean checkResult2 = false;

		String sql = "SELECT * FROM GSELLER WHERE GD_CODE = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, code);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkResult2 = true;
			} else {
				checkResult2 = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkResult2;
	}

	// [ 판매자 상품삭제 ]
	public void delete(int code) {
		String sql = "DELETE GSELLER WHERE GD_CODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("상품삭제 성공!");
			} else {
				System.out.println("상품삭제 실패");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// [ 하의 ]
	public boolean bottom(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("하의");
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품 종류 : " + rs.getInt(5));
				System.out.println("가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
				result = true;
				break;
			}
			while (rs.next()) {
				System.out.println("오류");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public boolean jewl(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("악세사리");
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품 종류 : " + rs.getInt(5));
				System.out.println("가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
				result = true;
				break;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// [가방&신발]
	public boolean acc(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("하의");
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품 종류 : " + rs.getInt(5));
				System.out.println("가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
				result = true;
				break;
			}
			while (rs.next()) {
				System.out.println("오류");
			}

		}catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// [ 상의]
	public boolean top(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("상의");
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품 종류 : " + rs.getInt(5));
				System.out.println("가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
				result = true;
				break;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// [ 수트 ]
	public boolean suit(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("수트");
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품 종류 : " + rs.getInt(5));
				System.out.println("가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
				result = true;
				break;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// 전체상품조회
	public void allSelect() {
		String sql = "SELECT GD_NAME,GD_CODE,GD_KIND,GD_PRICE,GD_SIZE,GD_COLOR FROM GSELLER where gd_code > 8";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품종류 : " + rs.getInt(5));
				System.out.println("상품가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 아이디찾기
	public boolean infoCheck(String name, String birth, String phone) {
		boolean checkResult = false;

		String sql = "SELECT * FROM INFO WHERE In_NAME = ? AND BIRTH = TO_DATE(?) AND PHONE=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, phone);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("아이디는 '" + rs.getString(2) + "' 입니다.");

				checkResult = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checkResult;
	}

	// [ 비밀번호 찾기]
	public boolean pwCheck(String name1, String id1) {

		boolean checkResult = false;

		String sql = "SELECT * FROM INFO WHERE In_NAME = ? AND In_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name1);
			pstmt.setString(2, id1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1) + "님의 비밀번호는 '" + rs.getString(3) + "' 입니다.");

				checkResult = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checkResult;
	}

	// [ 아이디비밀번호 확인메소드]
	public boolean idCheck5(String id5, int pw5) {
		boolean checkResult = false;

		String sql = "SELECT IN_ID FROM INFO WHERE In_ID = ? AND PW = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, id5);
			pstmt.setInt(2, pw5);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkResult = true;
			} else {
				checkResult = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkResult;
	}

	// [회원정보 수정]
	public void memberJoin1(GSeller gd) {
		String sql = "UPDATE INFO SET IN_NAME =?, PW =?, BIRTH =TO_DATE(?), EMAIL =?, PHONE =? WHERE IN_ID =?";
		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gd.getIn_name());
			pstmt.setInt(2, gd.getPw());
			pstmt.setString(3, gd.getBirth());
			pstmt.setString(4, gd.getEmail());
			pstmt.setString(5, gd.getPhone());
			pstmt.setString(6, gd.getIn_id());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원정보 수정 성공!");
			} else {
				System.out.println("회원정보 수정 실패!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// 회원삭제
	public void delete(String id6) {
		String sql = "DELETE INFO WHERE IN_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id6);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("회원정보 삭제 성공!");
			} else {
				System.out.println("회정정보 삭제 실패!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 모든 것 출력
	public void selectall() {
		String sql = "SELECT * FROM GSELLER";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println("상품명 : " + rs.getString(3));
				System.out.println("상품코드 : " + rs.getInt(4));
				System.out.println("상품종류 : " + rs.getInt(5));
				System.out.println("상품가격 : " + rs.getInt(6));
				System.out.println("사이즈 : " + rs.getString(7));
				System.out.println("색상 : " + rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ORDER_REGI(GSeller gd) {
		String sql = "INSERT INTO ORDER_BANK(OD_ID, OD_CODE,ADDR,BANK,BANKNO) VALUES(?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, gd.getOd_id());
			pstmt.setInt(2, gd.getOd_code());
			pstmt.setString(3, gd.getAddr());
			pstmt.setString(4, gd.getBank());
			pstmt.setLong(5, gd.getBankNo());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("주문등록 성공!");
			} else {
				System.out.println("주문등록 실패!");
			}

		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("주문등록 실패!");
			System.out.println("계정 또는 상품이 존재하지 않습니다.");
		}
	}

	// 결제내역
	public void ORDER_List(GSeller gd) {
		String sql = "select distinct OD_ID as 주문자 , gseller.gd_name, gseller.gd_price from order_bank,GSELLER WHERE order_bank.od_code = gseller.gd_code AND OD_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gd.getOd_id());
			rs = pstmt.executeQuery();

			System.out.println("구매자 아이디 :" + gd.getOd_id());

			while (rs.next()) {
				System.out.println("구매 물품 : " + rs.getString(2) + "\t구매 가격 : " + rs.getInt(3));
				
			}
			if(rs.getRow()<=0) {
				System.out.println("해당 계정에 주문이 존재하지 않습니다. ");
			}
		} catch (

		SQLException e) {
			e.printStackTrace();

		}
	}


	//
	public void ORDER_Total(GSeller gd) {
		String sql = "select distinct sum(gseller.gd_price) as 총합계 from order_bank,GSELLER WHERE order_bank.od_code = gseller.gd_code AND OD_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gd.getOd_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getInt(1)!=0) {
					System.out.println("총 결제 금액: " + rs.getInt(1));
				} 
				
			}

		} catch (

		SQLException e) {
			
			e.printStackTrace();

		}
	}

	public void Order_delet(GSeller gd) {
		String sql = "delete from order_bank WHERE OD_CODE = ? AND od_id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, gd.getOd_code());
			pstmt.setString(2, gd.getOd_id());

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("주문 취소 성공");
			} else {
				System.out.println("주문 취소 실패");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// [ 접속해제 메소드 ]
	public void conClose() {
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

}