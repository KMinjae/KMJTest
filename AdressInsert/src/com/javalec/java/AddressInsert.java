package com.javalec.java;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddressInsert {

	private JFrame frame;
	private JLabel lblNewLabel;
	private JTextField tfName = null;
	private JButton btnOK;

	// Database 환경 정의
	private final String url_mysql = "jdbc:mysql://127.0.0.1/useraddress?serverTimezone=UTC&characterEncoding=utf8&useSSL=FALSE";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";
	private JLabel lblNewLabel_1;
	private JTextField tfPhonnumber;
	private JLabel lblNewLabel_2;
	private JTextField tfAddress;
	private JLabel lblNewLabel_3;
	private JTextField tf;
	private JLabel lblNewLabel_4;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddressInsert window = new AddressInsert();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddressInsert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("주소록 등록");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getTfName());
		frame.getContentPane().add(getBtnOK());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getTfPhonnumber());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getTfAddress());
		frame.getContentPane().add(getLblNewLabel_3());
		frame.getContentPane().add(getTf());
		frame.getContentPane().add(getLblNewLabel_4());
		frame.getContentPane().add(getTextField_3());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("성명 :");
			lblNewLabel.setBounds(12, 30, 57, 15);
		}
		return lblNewLabel;
	}

	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setBounds(57, 27, 308, 21);
			tfName.setColumns(10);
		}
		return tfName;
	}

	private JButton getBtnOK() {
		if (btnOK == null) {
			btnOK = new JButton("입력");
			btnOK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int i_chk = insertFieldCheck();
					if(i_chk == 0 ) {
						insertAction();
					}
				}
			});
			btnOK.setBounds(325, 217, 97, 23);
		}
		return btnOK;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("번호 :");
			lblNewLabel_1.setBounds(12, 62, 57, 15);
		}
		return lblNewLabel_1;
	}

	private JTextField getTfPhonnumber() {
		if (tfPhonnumber == null) {
			tfPhonnumber = new JTextField();
			tfPhonnumber.setColumns(10);
			tfPhonnumber.setBounds(57, 59, 308, 21);
		}
		return tfPhonnumber;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("주소 :");
			lblNewLabel_2.setBounds(12, 92, 57, 15);
		}
		return lblNewLabel_2;
	}

	private JTextField getTfAddress() {
		if (tfAddress == null) {
			tfAddress = new JTextField();
			tfAddress.setColumns(10);
			tfAddress.setBounds(57, 89, 308, 21);
		}
		return tfAddress;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("우편 :");
			lblNewLabel_3.setBounds(12, 122, 57, 15);
		}
		return lblNewLabel_3;
	}

	private JTextField getTf() {
		if (tf == null) {
			tf = new JTextField();
			tf.setColumns(10);
			tf.setBounds(57, 119, 308, 21);
		}
		return tf;
	}

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("관계 :");
			lblNewLabel_4.setBounds(12, 152, 57, 15);
		}
		return lblNewLabel_4;
	}

	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(57, 149, 308, 21);
		}
		return textField_3;
	}

	private void insertAction() { // 사용자 정보 입력
		PreparedStatement ps = null; //PreparedStatement 전용공간 ps 를 만든다.
		try {
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement(); // 선언문

			String query = "insert into userinfo (name, telno) values (?, ?)";
			ps = conn_mysql.prepareStatement(query); //
			ps.setString(1, tfName.getText().trim()); // ps 에 빈칸없이 네임을 insert into 의 첫번째 물음표 컬럼에 가져온다
			ps.setString(2, tfPhonnumber.getText().trim()); // ps 에 빈칸없이 주소를 insert into 의 두번째 물음표 컬럼에 가져온다
			ps.executeUpdate(); // 업데이트 실행

			conn_mysql.close();

			JOptionPane.showMessageDialog(null, tfName.getText() + "님의 정보가 입력 되었습니다!");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
		}
	}
	
	private int insertFieldCheck() {
		int i = 0;
		String message = "";
		if(tfName.getText().trim().length() ==0) {
			i++;
			message = "이름을 ";
			tfName.requestFocus();
		}
		if(tfPhonnumber.getText().trim().length() ==0) {
			i++;
			message = "전화번호를 ";
			tfPhonnumber.requestFocus();
		}
		
		if(i > 0) {
			JOptionPane.showMessageDialog(null, message + "확인하세요!");
		}
		return i;
	}

}// ----