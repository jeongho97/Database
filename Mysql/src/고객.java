import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class �� {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/web";
		String id="root";
		String password="1234";
		Connection conn = null; //db������ ���� ��ü
		Statement stmt=null; //sql ��ɾ� ���� ���� ��ü
		ResultSet rs = null; //��� �޾ƺ��� ��ü
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //mysql����̹� �ε� 8���������� cj�� �߰����־����
			conn=DriverManager.getConnection(url,id,password); //db�� ����
			
			String sql="select * from �� where ���=? and ����>?"; //��������
			PreparedStatement pstmt = conn.prepareStatement(sql); //��������
			pstmt.setString(1,"silver"); //ù��° ����ǥ
			pstmt.setInt(2,20);//�ι�° ����ǥ
			
			rs=pstmt.executeQuery();//select�Ҷ�
			System.out.println(rs);
			while(rs.next()) { //select�Ҷ�!!!!
				String custom_id=rs.getString("�����̵�");
				String name=rs.getString("���̸�");
				int age=rs.getInt("����");
				String rank=rs.getString("���");
				String job=rs.getString("����");
				int money=rs.getInt("������");
				System.out.println("----------------------------------------------------------------------------");
				System.out.println(custom_id+"\t"+name+"\t"+age+"\t"+rank+"\t"+job+"\t"+money+"\t");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
