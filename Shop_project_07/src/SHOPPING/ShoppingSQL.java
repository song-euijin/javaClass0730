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

	// [ ���� �޼ҵ� ]
	public void connect() {

		con = DBC.DBConnect();

	}


	// [ȸ������ �޼ҵ�]
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
				System.out.println("ȸ������ ����!");
			} else {
				System.out.println("ȸ������ ����!");
			}

		} catch (SQLException e) {
			System.out.println("SQLException �߻�!");
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

	// [ �Ǹ��� ���̵�Ȯ�� ]
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

	// [ �Ǹ����� ��ǰ��� ]
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
				System.out.println("��ǰ��� ����!");
			} else {
				System.out.println("��ǰ��� ����!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// [�Ǹ��� ��ǰ��ȸ ]
	public void select(GSeller gd) {
		String sql = "SELECT GD_NAME,GD_CODE,GD_KIND,GD_PRICE,GD_SIZE,GD_COLOR FROM GSELLER where gd_code > 8 ";

		try {
			// pstmt = con.prepareStatement(sql);

			// rs = pstmt.executeQuery();

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println("��ǰ�̸�: " + rs.getString(1));
				System.out.println("��ǰ�ڵ�: " + rs.getInt(2));
				System.out.println("��ǰ����: " + rs.getInt(3));
				System.out.println("��ǰ����: " + rs.getInt(4));
				System.out.println("��ǰ������: " + rs.getString(5));
				System.out.println("��ǰ����: " + rs.getString(6));
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// [ ��ǰ������ ���� �ڵ�Ȯ�� ]
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

	// [ �Ǹ��� ��ǰ���� ]
	public void delete(int code) {
		String sql = "DELETE GSELLER WHERE GD_CODE=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, code);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("��ǰ���� ����!");
			} else {
				System.out.println("��ǰ���� ����");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// [ ���� ]
	public boolean bottom(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("����");
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ ���� : " + rs.getInt(5));
				System.out.println("���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
				result = true;
				break;
			}
			while (rs.next()) {
				System.out.println("����");
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
				System.out.println("�Ǽ��縮");
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ ���� : " + rs.getInt(5));
				System.out.println("���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
				result = true;
				break;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// [����&�Ź�]
	public boolean acc(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("����");
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ ���� : " + rs.getInt(5));
				System.out.println("���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
				result = true;
				break;
			}
			while (rs.next()) {
				System.out.println("����");
			}

		}catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// [ ����]
	public boolean top(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("����");
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ ���� : " + rs.getInt(5));
				System.out.println("���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
				result = true;
				break;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// [ ��Ʈ ]
	public boolean suit(int kind) {
		boolean result = false;
		String sql = "SELECT * FROM GSELLER WHERE GD_KIND=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kind);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("��Ʈ");
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ ���� : " + rs.getInt(5));
				System.out.println("���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
				result = true;
				break;

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return result;
	}

	// ��ü��ǰ��ȸ
	public void allSelect() {
		String sql = "SELECT GD_NAME,GD_CODE,GD_KIND,GD_PRICE,GD_SIZE,GD_COLOR FROM GSELLER where gd_code > 8";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ���� : " + rs.getInt(5));
				System.out.println("��ǰ���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// ���̵�ã��
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
				System.out.println("���̵�� '" + rs.getString(2) + "' �Դϴ�.");

				checkResult = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checkResult;
	}

	// [ ��й�ȣ ã��]
	public boolean pwCheck(String name1, String id1) {

		boolean checkResult = false;

		String sql = "SELECT * FROM INFO WHERE In_NAME = ? AND In_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, name1);
			pstmt.setString(2, id1);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(1) + "���� ��й�ȣ�� '" + rs.getString(3) + "' �Դϴ�.");

				checkResult = true;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return checkResult;
	}

	// [ ���̵��й�ȣ Ȯ�θ޼ҵ�]
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

	// [ȸ������ ����]
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
				System.out.println("ȸ������ ���� ����!");
			} else {
				System.out.println("ȸ������ ���� ����!");
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

	// ȸ������
	public void delete(String id6) {
		String sql = "DELETE INFO WHERE IN_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id6);

			int result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println("ȸ������ ���� ����!");
			} else {
				System.out.println("ȸ������ ���� ����!");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ��� �� ���
	public void selectall() {
		String sql = "SELECT * FROM GSELLER";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println();
				System.out.println("��ǰ�� : " + rs.getString(3));
				System.out.println("��ǰ�ڵ� : " + rs.getInt(4));
				System.out.println("��ǰ���� : " + rs.getInt(5));
				System.out.println("��ǰ���� : " + rs.getInt(6));
				System.out.println("������ : " + rs.getString(7));
				System.out.println("���� : " + rs.getString(8));
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
				System.out.println("�ֹ���� ����!");
			} else {
				System.out.println("�ֹ���� ����!");
			}

		} catch (SQLException e) {
			e.printStackTrace();

			System.out.println("�ֹ���� ����!");
			System.out.println("���� �Ǵ� ��ǰ�� �������� �ʽ��ϴ�.");
		}
	}

	// ��������
	public void ORDER_List(GSeller gd) {
		String sql = "select distinct OD_ID as �ֹ��� , gseller.gd_name, gseller.gd_price from order_bank,GSELLER WHERE order_bank.od_code = gseller.gd_code AND OD_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gd.getOd_id());
			rs = pstmt.executeQuery();

			System.out.println("������ ���̵� :" + gd.getOd_id());

			while (rs.next()) {
				System.out.println("���� ��ǰ : " + rs.getString(2) + "\t���� ���� : " + rs.getInt(3));
				
			}
			if(rs.getRow()<=0) {
				System.out.println("�ش� ������ �ֹ��� �������� �ʽ��ϴ�. ");
			}
		} catch (

		SQLException e) {
			e.printStackTrace();

		}
	}


	//
	public void ORDER_Total(GSeller gd) {
		String sql = "select distinct sum(gseller.gd_price) as ���հ� from order_bank,GSELLER WHERE order_bank.od_code = gseller.gd_code AND OD_ID = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gd.getOd_id());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if(rs.getInt(1)!=0) {
					System.out.println("�� ���� �ݾ�: " + rs.getInt(1));
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
				System.out.println("�ֹ� ��� ����");
			} else {
				System.out.println("�ֹ� ��� ����");

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// [ �������� �޼ҵ� ]
	public void conClose() {
		try {
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

}