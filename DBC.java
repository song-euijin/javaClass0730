package SHOPPING;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBC {

	public static Connection DBConnect() {
		Connection con = null;
		// 접속할 DB의 계정정보
		String user = "song";
		String password = "1111";
		
		
		// 접속할 DBㅇ의 주소정보
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, password);
			System.out.println("의류구매&판매 시스템");
			
		} catch(ClassNotFoundException cne) {
			System.out.println("DB접속 실패 : 드라이버 로딩 실패!");
			cne.printStackTrace();
			
		} catch(SQLException se) {
			System.out.println("DB접속 실패 : DB계정 주소 확인!");
			se.printStackTrace();
			
		}
		
		return con;
		
	}
}
