import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class TaoTK extends JFrame {

	private JPanel contentPane;
	private JTextField tfMaTK;
	private JTextField tfTenTK;
	private JTextField tfSoDu;
	private JTextField tfDiaChi;
	private 
	Connection conn;
	java.sql.Statement stmt;
	public void connectDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/bankdb", "root", "");
			System.out.println("Connect Sucess");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaoTK frame = new TaoTK();
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
	public TaoTK() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tao Tai Khoan");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(189, 41, 138, 50);
		contentPane.add(lblNewLabel);
		
		JLabel lblTaoTK = new JLabel("Ma Tai Khoan");
		lblTaoTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTaoTK.setBounds(38, 137, 101, 27);
		contentPane.add(lblTaoTK);
		
		tfMaTK = new JTextField();
		tfMaTK.setBounds(177, 135, 229, 27);
		contentPane.add(tfMaTK);
		tfMaTK.setColumns(10);
		
		JLabel lblTenTK = new JLabel("Ten Tai Khoan");
		lblTenTK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenTK.setBounds(38, 216, 120, 19);
		contentPane.add(lblTenTK);
		
		tfTenTK = new JTextField();
		tfTenTK.setBounds(177, 214, 229, 27);
		contentPane.add(tfTenTK);
		tfTenTK.setColumns(10);
		
		JLabel lblSoDu = new JLabel("So Du");
		lblSoDu.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoDu.setBounds(38, 285, 69, 19);
		contentPane.add(lblSoDu);
		
		tfSoDu = new JTextField();
		tfSoDu.setBounds(177, 286, 229, 27);
		contentPane.add(tfSoDu);
		tfSoDu.setColumns(10);
		
		JLabel lblDiaChi = new JLabel("Dia Chi");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblDiaChi.setBounds(38, 354, 69, 19);
		contentPane.add(lblDiaChi);
		
		tfDiaChi = new JTextField();
		tfDiaChi.setBounds(177, 355, 229, 27);
		contentPane.add(tfDiaChi);
		tfDiaChi.setColumns(10);
		
		JButton btnTaoMoi = new JButton("Tao Moi");
		btnTaoMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connectDB();
					stmt = conn.createStatement();
					int n = stmt.executeUpdate("Insert Into account Values('"+tfMaTK.getText()+"','"+tfTenTK.getText()+"','"+Integer.parseInt(tfSoDu.getText())+"','"+tfDiaChi.getText()+"')");
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnTaoMoi.setBounds(189, 431, 89, 23);
		contentPane.add(btnTaoMoi);
	}

}
