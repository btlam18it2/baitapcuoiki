import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Thongtin extends JFrame {

	private JPanel contentPane;
	private JTextField tfSearch;
	private java.sql.Statement stmt;
	Connection conn;
	ResultSet rs;
	ResultSetMetaData rsm;
	private JTable tbTK;
	Object[] colHeader = {"ID","Name","Balance","Diachi"};
	DefaultTableModel model = new DefaultTableModel(colHeader, 0);
	//tao ham connect DB
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
					Thongtin frame = new Thongtin();
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
	public Thongtin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 718, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearch = new JLabel("Keyword");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearch.setBounds(10, 33, 77, 33);
		contentPane.add(lblSearch);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(81, 35, 222, 33);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(313, 40, 89, 23);
		contentPane.add(btnSearch);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaoTK obj = new TaoTK();
				obj.setVisible(true);
			}
		});
		btnCreate.setBounds(412, 40, 89, 23);
		contentPane.add(btnCreate);
		
		JButton btnReload = new JButton("Reload");
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				SuaTK obj = new SuaTK();
				obj.setVisible(true);
			}
		});
		btnReload.setBounds(511, 40, 89, 23);
		contentPane.add(btnReload);
		
		JButton btnAll = new JButton("All");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e1) {
				try {
					connectDB();
					 Statement stmt = (Statement) conn.createStatement();
					rs = stmt.executeQuery("select * from account");
					rsm = rs.getMetaData();
					
					while(rs.next()) {
						String id = rs.getString("ID");
						String name = rs.getString("Name");
						int bal = rs.getInt("Balance");
						String add = rs.getString("Dia Chi");
						Object[] row = new Object[] {id, name, bal, add};
						model.addRow(row);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
		btnAll.setBounds(610, 40, 89, 23);
		contentPane.add(btnAll);
		
		tbTK = new JTable();
		tbTK.setBounds(10, 102, 682, 348);
		contentPane.add(tbTK);
	}
}
