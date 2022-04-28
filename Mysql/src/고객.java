import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class 고객 {

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
			
			String sql="select * from 고객 where 등급=? and 나이>?"; //동적쿼리
			PreparedStatement pstmt = conn.prepareStatement(sql); //동적쿼리
			pstmt.setString(1,"silver"); //첫번째 물음표
			pstmt.setInt(2,20);//두번째 물음표
			
			rs=pstmt.executeQuery();//select할때
			System.out.println(rs);
			while(rs.next()) { //select할때!!!!
				String custom_id=rs.getString("고객아이디");
				String name=rs.getString("고객이름");
				int age=rs.getInt("나이");
				String rank=rs.getString("등급");
				String job=rs.getString("직업");
				int money=rs.getInt("적립금");
				System.out.println("----------------------------------------------------------------------------");
				System.out.println(custom_id+"\t"+name+"\t"+age+"\t"+rank+"\t"+job+"\t"+money+"\t");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
