import java.sql.*;
public class books {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/web";
		String id="root";
		String password="1234";
		Connection conn = null; //db������ ���� ��ü
		Statement stmt=null; //sql ��ɾ� ���� ���� ��ü
		//ResultSet rs = null; //��� �޾ƺ��� ��ü
		int rs=0; //insert,update,delete�� �Ҷ��� �ݿ��� ���ڵ� �Ǽ��� �޾ƿ��� ������ int�� ����� �޾ƿ;���
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql����̹� �ε� 8���������� cj�� �߰����־����
			conn=DriverManager.getConnection(url,id,password); //db�� ����
			String sql="insert into books (title,author,year,price)" + "values('�ڷᱸ��','������','2022','500000')";
			//String sql="select * from books";
			stmt=conn.createStatement();
			//rs=stmt.executeQuery(sql);//select�Ҷ�
			rs=stmt.executeUpdate(sql);
			System.out.println(rs);
//			while(rs.next()) { //select�Ҷ�!
//				int book_code=rs.getInt("book_code");
//				String title=rs.getString("title");
//				String author=rs.getString("author");
//				String year=rs.getString("year");
//				int price=rs.getInt("price");
//				System.out.println(book_code+"\t"+title+"\t"+author+"\t"+year+"\t"+price);
//			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
