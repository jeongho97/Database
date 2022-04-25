import java.sql.*;
public class books {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/web";
		String id="root";
		String password="1234";
		Connection conn = null; //db접속을 위한 객체
		Statement stmt=null; //sql 명령어 실행 위한 객체
		ResultSet rs = null; //결과 받아보는 객체
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql드라이버 로딩 8버전에서는 cj를 추가해주어야함
			conn=DriverManager.getConnection(url,id,password); //db에 접속
			String sql="select * from books";
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				int book_code=rs.getInt("book_code");
				String title=rs.getString("title");
				String author=rs.getString("author");
				String year=rs.getString("year");
				int price=rs.getInt("price");
				System.out.println(book_code+"\t"+title+"\t"+author+"\t"+year+"\t"+price);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
