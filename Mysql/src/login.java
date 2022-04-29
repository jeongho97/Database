import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField inputID;
	private JPasswordField inputPW;
	private JLabel lblResult;
	private JButton btndelete;
	private JButton btnupdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		String url="jdbc:mysql://localhost:3306/web";
		String id="root";
		String pw="1234";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserID=inputID.getText(); //UI에서 입력한 ID 입력값 받아오기
				String UserPW=String.valueOf(inputPW.getPassword()); //UI에서 입력한 PW 입력값 받아오기 비밀번호를 숫자로 받았으니 valueOf를 통해 문자열로 형변환 해준다
				
				Connection conn=null; //mysql 접속
				PreparedStatement pstmt=null; //sql문장 처리
				ResultSet rs=null; //쿼리 결과를 받아옴
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //드라이드 로딩
					conn=DriverManager.getConnection(url,id,pw); //mysql 접속
					
					String sql="select * from userinfo where userid=? and userpw=?";
					pstmt=conn.prepareStatement(sql); //conn 객체에 연결되어있는 mysql에 sql문장을 보냄
					pstmt.setString(1, UserID); //첫번째 물음표에 매칭
					pstmt.setString(2, UserPW); //두번째 물음표에 매칭
					
					rs=pstmt.executeQuery(); //쿼리문을 실행시킨 결과를 ResultSet 객체 rs에 저장
					
					if(rs.next()) {
						lblResult.setText("이름: "+rs.getString("name")+" 나이: "+rs.getString("age")+" 번호: "+rs.getString("tellno"));
					}
					else {
						lblResult.setText("아이디 또는 비밀번호가 일치하지 않습니다.");
					}
					
				}catch(Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		contentPane.add(btnNewButton);
		
		inputID = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.SOUTH, inputID, -199, SpringLayout.SOUTH, contentPane);
		contentPane.add(inputID);
		inputID.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ID");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 34, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, inputID, 14, SpringLayout.EAST, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 29, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		inputPW = new JPasswordField();
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 22, SpringLayout.EAST, inputPW);
		sl_contentPane.putConstraint(SpringLayout.NORTH, inputPW, 60, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, inputPW, 0, SpringLayout.WEST, inputID);
		sl_contentPane.putConstraint(SpringLayout.EAST, inputPW, -254, SpringLayout.EAST, contentPane);
		contentPane.add(inputPW);
		
		JLabel lblNewLabel_1 = new JLabel("PW");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, 0, SpringLayout.SOUTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 63, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 0, SpringLayout.WEST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		lblResult = new JLabel(" ");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblResult, 16, SpringLayout.SOUTH, inputPW);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblResult, 0, SpringLayout.WEST, inputID);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblResult, 379, SpringLayout.WEST, contentPane);
		contentPane.add(lblResult);
		
		JButton btnInsert = new JButton("\uD68C\uC6D0\uAC00\uC785");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnInsert, 21, SpringLayout.SOUTH, lblResult);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserID=inputID.getText();
				String UserPW=String.valueOf(inputPW.getPassword());
		
				
				Connection conn=null;
				PreparedStatement pstmt=null;
				PreparedStatement check_pstmt=null;
				int rs;
				ResultSet result=null;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection(url, id, pw);
					
					String sql_check="select userid from userinfo where userid=?"; //중복 검색
					check_pstmt=conn.prepareStatement(sql_check);
					check_pstmt.setString(1, UserID);
					result=check_pstmt.executeQuery();
					if(result.next()) {
						lblResult.setText("중복되는 아이디가 있습니다");
						
					}
					else
					{
						String sql="insert into userinfo (userid,userpw)"+"values(?,?)";
						pstmt=conn.prepareStatement(sql);
						pstmt.setString(1, UserID);
						pstmt.setString(2, UserPW);
						rs=pstmt.executeUpdate(); //select일때는 executeQuery
						lblResult.setText("회원가입이 완료되었습니다");
					}
					
			
				}catch(Exception e3){
					lblResult.setText("회원가입이 실패하였습니다");
					e3.printStackTrace();
				}
				}
		});
		contentPane.add(btnInsert);
		
		btndelete = new JButton("\uD68C\uC6D0\uD0C8\uD1F4");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btndelete, 0, SpringLayout.NORTH, btnInsert);
		sl_contentPane.putConstraint(SpringLayout.WEST, btndelete, 6, SpringLayout.EAST, btnInsert);
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserID=inputID.getText();
				String UserPW=String.valueOf(inputPW.getPassword());

				
				Connection conn=null;
				PreparedStatement pstmt=null;
				int rs;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection(url, id, pw);
					
					String sql="delete from userinfo where userid=? and userpw=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, UserID);
					pstmt.setString(2, UserPW);
					rs=pstmt.executeUpdate();
					lblResult.setText("회원 탈퇴 되었습니다");
				}catch(Exception e3){
					lblResult.setText("회원 탈퇴 실패되었습니다");
					e3.printStackTrace();
				}
				}
		});
		contentPane.add(btndelete);
		
		btnupdate = new JButton("\uBE44\uBC00\uBC88\uD638\uBCC0\uACBD");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnupdate, 21, SpringLayout.SOUTH, lblResult);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnInsert, 6, SpringLayout.EAST, btnupdate);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnupdate, 0, SpringLayout.WEST, lblNewLabel);
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserID=inputID.getText();
				String UserPW=String.valueOf(inputPW.getPassword());

				
				Connection conn=null;
				PreparedStatement pstmt=null;
				int rs;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					conn=DriverManager.getConnection(url, id, pw);
					
					String sql="update userinfo set userpw=? where userid=?";
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, UserPW);
					pstmt.setString(2, UserID);
					rs=pstmt.executeUpdate();
					lblResult.setText("비밀번호가 변경되었습니다");
				}catch(Exception e3){
					lblResult.setText("비밀번호 변경에 실패하였습니다");
					e3.printStackTrace();
				}
				}
		});
		contentPane.add(btnupdate);
	}
}
