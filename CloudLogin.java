import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
class Login extends JFrame implements ActionListener{

	public int decryption_key,encryption_key;
	public String login_password;
	public String login_username;
	public String check_username;
	public String check_password;
	int check_key,decrypt_key;
	JLabel l1,l2,l3,l4;
	JTextField t1,t3,t4;
	JPasswordField t2;
	JButton b1,b2,b3;
	Login()
	{
		l1=new JLabel("username");
		l1.setFont(new Font("username",Font.BOLD,18));
		l2=new JLabel("password");
		l2.setFont(new Font("password",Font.BOLD,18));
		t1=new JTextField(15);
		t2=new JPasswordField(15);
		t2.setEchoChar('*');
		t2.setFont(new Font("*",Font.BOLD,24));
		b1=new JButton("LOGIN");
		b1.setFont(new Font("LOGIN",Font.BOLD,18));
		b2=new JButton("RESET");
		b2.setFont(new Font("RESET",Font.BOLD,18));
		setSize(500,500);
		setLayout(null);
		l1.setBounds(30,50,100,40);
		t1.setBounds(200,50,150,40);
		l2.setBounds(30,130,100,40);
		t2.setBounds(200,130,150,40);
		b1.setBounds(30,200,100,40);
		b2.setBounds(300,200,100,40);
		add(l1);
		add(t1);
		add(l2);
		add(t2);
		add(b1);
		add(b2);
		b1.addActionListener(this);
		b2.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) 
	{
		check_username=t1.getText();
		check_password=t2.getPassword().toString();
		if(e.getSource()==b1)
		{
			/*if(check_username.equals("udit0211@gmail.com")&&check_password.equals(" chitransh1998"))
			{
				JOptionPane.showMessageDialog(this,"login successfully");
			}
			else
			{
				JOptionPane.showMessageDialog(this,"login unsuccessfull");	
			}*/
			if(check_username==login_username&&check_password==login_password) {
				l3=new JLabel("enter encrypted code");
				l3.setFont(new Font("enter encrypted code",Font.BOLD,18));
				t3=new JTextField(20);
				l4=new JLabel("enter the key");
				l4.setFont(new Font("enter the key",Font.BOLD,18));
				t4=new JTextField(20);
				b3=new JButton("DECRYPT");
				b3.setFont(new Font("DECRYPT",Font.BOLD,18));
				setSize(500,500);
				setLayout(null);
				l3.setBounds(30,50,100,40);
				t3.setBounds(200,50,150,40);
				l4.setBounds(30,130,100,40);
				t4.setBounds(200,130,150,40);
				add(l3);
				add(t3);
				add(b3);
				add(l4);
				add(t4);
				b3.addActionListener(this);
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"illegal login");	
			}
		}
		if(e.getSource()==b2)
		{
			t1.setText(" ");
			t2.setText(" ");
		}
		if(e.getSource()==b3) {
			//int temp_user=Integer.parseInt(login_username);
			//int temp_pass=Integer.parseInt(login_password);
			int key=Integer.parseInt(t4.getText());
			decrypt_key=encryption_key-(key)^key;
			if(decrypt_key==decryption_key) {
				JOptionPane.showMessageDialog(this,"login successfully");
			}
			else {
					JOptionPane.showMessageDialog(this,"login unsuccessfull");
			}
		}
	}
	
}
public class CloudLogin{
	public static void main(String args[])throws Exception {
		String query_password,query_username,query_encrypt,query_decrypt;
		Login log=new Login();
		log.setVisible(true);
		log.setSize(500,500);
		try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","cb#0211#");
				Statement st=con.createStatement();
				query_password="select password from cloud where username="+log.check_username+";";
				query_username="select username from cloud where username="+log.check_username+";";
				query_encrypt="select encrypt_key from cloud where username="+log.check_username+";";
				query_decrypt="select decrypt_key from cloud where username="+log.check_username+";";
				ResultSet rs1=st.executeQuery(query_password);
				ResultSet rs2=st.executeQuery(query_username);
				ResultSet rs3=st.executeQuery(query_encrypt);
				ResultSet rs4=st.executeQuery(query_decrypt);
				log.login_password=rs1.getString(1);
				log.login_username=rs2.getString(1);
				log.encryption_key=rs3.getInt(1);
				log.decryption_key=rs4.getInt(1);	
				
			}
		catch(Exception e){}
		}
}