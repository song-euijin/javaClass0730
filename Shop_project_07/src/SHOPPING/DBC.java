package SHOPPING;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {

	public static Connection DBConnect() {
		Connection con = null;
		// ������ DB�� ��������
		String user = "song";
		String password = "1111";
		
		
		// ������ DB���� �ּ�����
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("�Ƿ�����&�Ǹ� �ý���");
			
		} catch(ClassNotFoundException cne) {
			System.out.println("DB���� ���� : ����̹� �ε� ����!");
			cne.printStackTrace();
			
		} catch(SQLException se) {
			System.out.println("DB���� ���� : DB���� �ּ� Ȯ��!");
			se.printStackTrace();
			
		}
		
		return con;
		
	}
}
