package internship;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import javax.swing.ScrollPaneConstants;


class CustomerData
{
	int customer_id,customer_type;
	String fn,ln,email,phn,city,pass;
	
	public CustomerData()
	{
		fn = " ";
		ln = " ";
		email = " ";
		phn = " ";
	}
}

public class Store implements ActionListener{

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField first_name;
	private JTextField last_name;
	private JTextField email;
	private JTextField city;
	private JTextField phone_number;
	private JPasswordField new_password;
	private CardLayout cl;
	private CustomerData cusd = new CustomerData();
	private JPasswordField passwordField_1;
	private int tempvar=0;
	private JTextField textField_1;
	private String imageOfProduct;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private DefaultTableModel dtm = new DefaultTableModel(0, 0);
	private JTable table = new JTable();
	private DefaultTableModel dtm1= new DefaultTableModel(0, 0);
	private JTable table1 = new JTable();
	JLabel lblNewLabel_8 = new JLabel("");
	ArrayList arrayList1 = new ArrayList<>();
	private ArrayList arrayList = new ArrayList<>();
	JPanel panel_8 = new JPanel();
	JPanel panel_3 = new JPanel();
	private DefaultTableModel dtm2 = new DefaultTableModel(0, 0);
	private JTable table2 = new JTable();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Store window = new Store();
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
	public Store() {
		initialize();
	}
	
     JPanel getPanel(String name_p,int stock_p,float price_p,String des_p,String i_p)
     {
    	JPanel panel_8 = new JPanel();
    	Border br = BorderFactory.createRaisedBevelBorder();
    	panel_8.setBorder(br);
    //	panel_8.setLayout(null);
 		panel_8.setBounds(10, 10, 1356, 202);
 		
 		JLabel image_label = new JLabel();
		Icon ficon = new ImageIcon(new ImageIcon(i_p).getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));
 		image_label.setIcon(ficon);
 		//image_label.setBounds(10, 10, 231, 213);
 		panel_8.add(image_label);
 		
 		JLabel name_label = new JLabel(name_p);
 		name_label.setFont(new Font("Tahoma", Font.BOLD, 18));
 		//name_label.setBounds(251, 10, 305, 62);
 		panel_8.add(name_label);
 		
 		JLabel price_label = new JLabel(price_p+"");
 		price_label.setFont(new Font("Tahoma", Font.BOLD, 18));
 		//price_label.setBounds(251, 82, 305, 62);
 		panel_8.add(price_label);
 				
 		JTextPane textPane_1 = new JTextPane();
 		textPane_1.setText(des_p);
 		textPane_1.setEditable(false);
 		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
 		//textPane_1.setBounds(566, 10, 481, 213);
 		panel_8.add(textPane_1);
 		
 		 SpinnerModel value =  new SpinnerNumberModel(1,0,stock_p,1);
 		JSpinner spinner = new JSpinner(value);
 		spinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
 		//spinner.setBounds(1149, 32, 44, 40);
 		panel_8.add(spinner);
 				
 		JLabel stock_label = new JLabel(stock_p+"");
 		//stock_label.setBounds(1068, 35, 79, 37);
 		stock_label.setFont(new Font("Tahoma",Font.PLAIN,17));
 		panel_8.add(stock_label);
 		
 		JButton add_to_cart_button = new JButton("Add to Cart");
 		add_to_cart_button.addActionListener(this);
 		//add_to_cart_button.setBounds(1092, 106, 101, 38);
 		panel_8.add(add_to_cart_button);
 			
 		return panel_8;
     }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1425, 739);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel manip = new JPanel();
		manip.setBounds(10, 10, 1401, 692);
		frame.getContentPane().add(manip);
		cl = new CardLayout(0,0);
		manip.setLayout(cl);
		
	                        	 ////////LOGIN PANEL ////////
		final JPanel login_panel = new JPanel();
		login_panel.setBounds(-10017, -10040, 1401, 692);
		manip.add(login_panel,"one");
		login_panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(988, 169, 137, 33);
		login_panel.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(988, 276, 137, 33);
		login_panel.add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(987, 212, 168, 33);
		login_panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(988, 319, 167, 33);
		login_panel.add(passwordField);
		
		
		
		
		
		
		
		
		                          //////THIS IS CODE FOR LOGGIN IN 
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			// 1. CHECKING IF EMAIL AND PASSWORD ARE ENTERED OR NOT
			// 2. CHECKING IF THE ENTERED INFORMATION IS CORRECT OR NOT AND IS IT PRESENT IN THE SQL DATABASE
			// 3. IF ALL ARE ACCEPTED, STORE PANEL IS SHOWN AND USER INFORMATION IS STORED IN CUSTOMERDATA CLASS FOR SHOWING IN MYPROFILE TAB IN STORE PANEL
				
				String em = textField.getText();
				@SuppressWarnings("deprecation")
				String jpf = passwordField.getText();
				
				if(em.equals("") || jpf.equals(""))
					JOptionPane.showMessageDialog(login_panel,"Enter email or password");
				else {
				try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
				
				PreparedStatement stm = con.prepareStatement("select * from customers where email = ? and password = ?");
				
				stm.setString(1, em);
				stm.setString(2, jpf);
				
				ResultSet rs= stm.executeQuery();
				
				
				if(!rs.next()) {
					JOptionPane.showMessageDialog(login_panel,"User Name or Password Wrong!");
					passwordField.setText("");					
					
				}
				else if(rs.getString(4).equals(em) && rs.getString(7).equals(jpf)) {
				   
				    cusd.customer_id = rs.getInt(1);
				    cusd.fn = rs.getString(2);
				    cusd.ln = rs.getString(3);
				    cusd.email = rs.getString(4);
				    cusd.phn = rs.getString(5);
				    cusd.city = rs.getString(6);
				    cusd.pass = rs.getString(7);
				    cusd.customer_type = rs.getInt(8);
				    
				    if(cusd.customer_type == 1) {
				    	try {
				    		Class.forName("com.mysql.cj.jdbc.Driver");

							Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
							
							PreparedStatement stm1 = con1.prepareStatement("select product_name,quantity,total_price,order_date,shipped_date,status from orders where customer_id = ? ");
							
							stm1.setInt(1, cusd.customer_id);
							
							ResultSet rs1 = stm1.executeQuery();

							while(rs1.next())
							{
								dtm1.addRow(new Object[]{rs1.getString("product_name"),rs1.getInt("quantity"),rs1.getFloat("total_price"),rs1.getString("order_date"),rs1.getString("shipped_date"),rs1.getString("status")});	
							}   
							stm1.close();
							con1.close();			
				    	}
				    	catch(Exception ex23)
				    	{
				    		System.out.print(ex23);
				    	}
				        cl.show(manip, "home_panel");
				    }
				    else {
				    	
				    	try {
				    		Class.forName("com.mysql.cj.jdbc.Driver");

							Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
							
							PreparedStatement stm1 = con1.prepareStatement("select product_name,quantity,total_price,order_date,shipped_date,status from orders ");
							
							ResultSet rs1 = stm1.executeQuery();

							while(rs1.next())
							{
								dtm2.addRow(new Object[]{rs1.getString("product_name"),rs1.getInt("quantity"),rs1.getFloat("total_price"),rs1.getString("order_date"),rs1.getString("shipped_date"),rs1.getString("status")});	
							}   
							stm1.close();
							con1.close();			
				    	}
				    	catch(Exception ex23)
				    	{
				    		System.out.print(ex23);
				    	}			    	
				    	cl.show(manip,"admin_panel");
				    }
				}
				stm.close();
				con.close();
				
				}
				catch(Exception ec) {
					JOptionPane.showMessageDialog(login_panel,ec);
				}
			}
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnNewButton.setBounds(1030, 376, 95, 40);
		login_panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("create an account?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(988, 453, 124, 17);
		login_panel.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("signup");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(manip, "two");
			}
		});
		btnNewButton_1.setBounds(1110, 453, 85, 21);
		login_panel.add(btnNewButton_1);
		
		JButton btnNewButton_9 = new JButton("forgot password");
		btnNewButton_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(manip, "forgot_pass_panel");
			}
		});
		btnNewButton_9.setBounds(1022, 422, 133, 21);
		login_panel.add(btnNewButton_9);
		
		
		////////////////////////REGISTRATION PANEL
		final JPanel reg_panel = new JPanel();
		reg_panel.setBounds(-10017, -10040, 1401, 692);
		manip.add(reg_panel,"two");
		reg_panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(86, 94, 130, 33);
		reg_panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Last Name");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(86, 181, 130, 33);
		reg_panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Email");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(86, 257, 130, 33);
		reg_panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("City");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_3.setBounds(86, 335, 130, 33);
		reg_panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("Phone Number");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_4.setBounds(86, 418, 130, 33);
		reg_panel.add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("Password");
		lblNewLabel_2_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_5.setBounds(86, 495, 130, 33);
		reg_panel.add(lblNewLabel_2_5);
		
		first_name = new JTextField();
		first_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		first_name.setBounds(262, 94, 204, 33);
		reg_panel.add(first_name);
		first_name.setColumns(10);
		
		last_name = new JTextField();
		last_name.setFont(new Font("Tahoma", Font.PLAIN, 16));
		last_name.setColumns(10);
		last_name.setBounds(262, 181, 204, 33);
		reg_panel.add(last_name);
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email.setColumns(10);
		email.setBounds(262, 257, 204, 33);
		reg_panel.add(email);
		
		city = new JTextField();
		city.setFont(new Font("Tahoma", Font.PLAIN, 16));
		city.setColumns(10);
		city.setBounds(262, 335, 204, 33);
		reg_panel.add(city);
		
		phone_number = new JTextField();
		phone_number.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phone_number.setColumns(10);
		phone_number.setBounds(262, 418, 204, 33);
		reg_panel.add(phone_number);
		
		new_password = new JPasswordField();
		new_password.setBounds(262, 497, 204, 33);
		reg_panel.add(new_password);
		
		
		final JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(86, 31, 173, 33);
		comboBox.addItem("Customer");
		comboBox.addItem("Admin");
		reg_panel.add(comboBox);
		
		
		JButton btnNewButton_2 = new JButton("Sign up");
		////////////////////////THIS IS CODE FOR SIGNING UP FOR CUSTOMER / ADMIN ///////////////////////////////////////
		btnNewButton_2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
			    // 1.CHECKING IF ALL FIELDS ARE ENTERED 
			    // 2.IF ALL FIELDS ARE ENTERED CHECKING IF THE MAIL AND PHONE NUMBER ARE ALREADY EXISTS IN THE SQL DATABASE MAIL AND PHONE NUMBER ARE PRIMARY KEYS
				// 3.IF ALL CONDITIONS ARE ACCEPTED CUSTOMER INFORMATION IS STORED IN DATABASE AND LOGIN PANEL IS SHOWN
				if(first_name.getText().equals("") || last_name.getText().equals("") || email.getText().equals("") || phone_number.getText().equals("") || city.getText().equals("") || new_password.getText().equals("") || !new_password.getText().equals(passwordField_1.getText()))
					{
					JOptionPane.showMessageDialog(reg_panel,"Missing Information or Password does not match");
					passwordField_1.setText("");
					}
					
				else {
				try
				{					
					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
					
					PreparedStatement stm = con.prepareStatement("insert into customers(first_name,last_name,email,phone,city,password,customer_type) values(?,?,?,?,?,?,?)");
									
					stm.setString(1,first_name.getText());
					stm.setString(2, last_name.getText());
					stm.setString(3,email.getText());
					stm.setString(4, phone_number.getText());
					stm.setString(5, city.getText());
					stm.setString(6, new_password.getText());
					if (((String)comboBox.getSelectedItem()).equals("Customer")) 
						stm.setInt(7,1);
					else
						stm.setInt(7, 0);
					stm.executeUpdate();
					
					stm.close();
					con.close();
				}
				catch(Exception exc) {
					tempvar=1;
					JOptionPane.showMessageDialog(reg_panel,"Email or Phone number Already Exists!");					
				}
				if(tempvar==0) {
				JOptionPane.showMessageDialog(reg_panel,"Account Sucessfully Created!");
				cl.show(manip, "one");
				}
				tempvar=0;
				}
					
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setBounds(141, 623, 143, 41);
		reg_panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_9 = new JLabel("Re Enter Password");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_9.setBounds(86, 572, 173, 33);
		reg_panel.add(lblNewLabel_9);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(262, 572, 204, 33);
		reg_panel.add(passwordField_1);
		
		
		final JPanel home_panel = new JPanel();
		home_panel.setBackground(new Color(245, 245, 245));
		home_panel.setForeground(Color.BLUE);
		manip.add(home_panel, "home_panel");
		home_panel.setLayout(null);
		
		final JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 47, 1381, 635);
		home_panel.add(tabbedPane_1);
		Icon phoneimg = new ImageIcon(new ImageIcon("B:\\Images\\phones.jpg").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		Icon laptopimg = new ImageIcon(new ImageIcon("B:\\Images\\laptops.jpg").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		Icon cartimg = new ImageIcon(new ImageIcon("B:\\Images\\cart.png").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		Icon myordersimg = new ImageIcon(new ImageIcon("B:\\Images\\myorders.png").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		//Icon profileimg = new ImageIcon(new ImageIcon("B:\\Images\\search.png").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));

		
		panel_3.setLayout(new BorderLayout());
		tabbedPane_1.addTab(null, phoneimg, panel_3, "Mobiles");
				
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(new BorderLayout());
		tabbedPane_1.addTab(null, laptopimg, panel_4, "Laptops");
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout());
		
		String header[] = new String[] { "Product Name", "Price", "Quantity","Total Price"};
		dtm.setColumnIdentifiers(header);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		table.setEnabled(false);
		table.setModel(dtm);
		JScrollPane spt = new JScrollPane(table);
		spt.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//spt.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_5.add(spt);
		//dtm.addRow(new Object[] { "data", "data", "data" });	
		tabbedPane_1.addTab(null, cartimg, panel_5, "Cart");
		
		
		panel_5.add(panel_8);
		panel_8.setLayout(null); 
		    
		JButton btnNewButton_10 = new JButton("Buy Now");
		btnNewButton_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// 1. AFTER PRESSING BUY NOW BUTTON THIS CODE WILL EXECUTE
				// 2. FIRST IT WILL INSERT THE CUSTOMER ID, PRODUCT NAME, QUANTITY, TOTAL PRICE, ORDER STATUS FROM THE JTABLE  AND ORDER DATE INTO ORDERS DB 
				// 3. AND ALSO INSERTING NAME OF PRODUCT AND QUANTITY INTO ARRAYLIST FOR LATER USE (FOR UPDATING STOCK)
				// 4. AFTER INSERTING THE ORDER DETAILS INTO ORDERS TABLE WE LOOK INTO THE UPDATING OF STOCK IN PRODUCTS TABLE
				// 5. WE HAVE ARRAYLIST THAT HAS DETAILS OF NAME OF PRODUCT AND QUANTITY THAT HAS ORDERED
				// 6. AND WE ARE UPDATING PRODUCTS CLASS AND UPDATING THE STOCK LABEL
				// 7. 
				try
				{					
					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
										
					PreparedStatement stm = con.prepareStatement("insert into orders(customer_id,product_name,quantity,total_price,order_date,status) values(?,?,?,?,?,?)");
					
					for(int i = 0;i<table.getModel().getRowCount();i++) {
						
						
					stm.setInt(1,cusd.customer_id);
					stm.setString(2,table.getModel().getValueAt(i,0)+"" );
					stm.setInt(3,(int)table.getModel().getValueAt(i,2));
					stm.setFloat(4,(float) table.getModel().getValueAt(i,3));
					stm.setString(5, "2022/06/28");
					stm.setString(6,"Ready to Dispatch");
					
					arrayList.add(table.getModel().getValueAt(i,2));
					arrayList.add(table.getModel().getValueAt(i,0));
					//arrayList.add(table.getModel().getValueAt(i,0));
					//System.out.print(arrayList);
					
					dtm1.addRow(new Object[]{table.getModel().getValueAt(i,0),table.getModel().getValueAt(i,2),table.getModel().getValueAt(i,3),"2022/06/28","Not Yet Shipped","Ready to Dispatch"});	

					
					stm.executeUpdate();
					}
					lblNewLabel_8.setText("0");
					dtm.setRowCount(0);
					JOptionPane.showMessageDialog(reg_panel,"Order has been Placed, Checkout my Orders!");
					
					stm.close();
					con.close();
				}
				catch(Exception exc) {
					System.out.print(exc);
				}
				
				try
				{					
					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
										
					PreparedStatement stm = con.prepareStatement("update products set quantity_in_stock = quantity_in_stock - ? where name = ?");
					
					for(int i=0;i<arrayList.size();i+=2) {					
						
						stm.setInt(1, (int) arrayList.get(i));
						
						stm.setString(2, (String) arrayList.get(i+1));
						
						JLabel lbl = (JLabel) arrayList1.get(i);
						
						lbl.setText( ( Integer.parseInt(lbl.getText()) - (int)arrayList.get(i) ) +"" );
					
					    stm.executeUpdate();
					}
					arrayList.clear();
					arrayList1.clear();
					
					
					stm.close();
					con.close();
				}
				catch(Exception exc) {
					System.out.print(exc);
				}				
			}
		});
		btnNewButton_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_10.setBounds(231, 452, 141, 40);
		panel_8.add(btnNewButton_10);
		
		JLabel lblNewLabel_7 = new JLabel("Total Price");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_7.setBounds(10, 110, 129, 40);
		panel_8.add(lblNewLabel_7);
		
		
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_8.setBounds(149, 110, 318, 40);
		panel_8.add(lblNewLabel_8);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(new GridLayout(1,0));
		
		String header1[] = new String[] {"Product Name","Quantity","Total Price","Order Date","Shipped Date","Status"};
		dtm1.setColumnIdentifiers(header1);	
		table1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table1.setEnabled(false);
		table1.setModel(dtm1);
		JScrollPane scllpn = new JScrollPane(table1);
		panel_6.add(scllpn);
		tabbedPane_1.addTab(null, myordersimg, panel_6, "My Orders");
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		final JPanel panel_7 = new JPanel();
		panel_7.setLayout(new BorderLayout());
		tabbedPane_1.addTab(null, null, panel_7, null);
		
	    JPanel rowHolderPanel = new JPanel(new GridLayout(0, 1, 1, 2));
	    JPanel outerPanel = new JPanel(new BorderLayout());
	    outerPanel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(outerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        panel_3.add(scrollPane, BorderLayout.CENTER);
        
        
        	try {
        		Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
				
				java.sql.Statement stm = con.createStatement();
				
				ResultSet rs = stm.executeQuery("select name,quantity_in_stock,unit_price,description,image from products where category='m'");
				
				
				while(rs.next())
				{
					
					JPanel funPanel = getPanel(rs.getString("name"),rs.getInt("quantity_in_stock"),rs.getFloat("unit_price"),rs.getString("description"),rs.getString("image"));

					rowHolderPanel.add(funPanel);
		            rowHolderPanel.revalidate();
		            rowHolderPanel.repaint();
				}   
				stm.close();
				con.close();			
        	}
        	catch(Exception ex2)
        	{
        		System.out.print(ex2);
        	}		
        	      	
        	JPanel rowHolderPanel1 = new JPanel(new GridLayout(0, 1, 1, 2));
    	    JPanel outerPanel1 = new JPanel(new BorderLayout());
    	    outerPanel1.add(rowHolderPanel1, BorderLayout.PAGE_START);
            JScrollPane scrollPane1 = new JScrollPane(outerPanel1);
            scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            panel_4.add(scrollPane1, BorderLayout.CENTER);
            
            JToolBar toolBar = new JToolBar();
            
            toolBar.setFloatable(false);
            toolBar.setBounds(0, 0, 1401, 37);
            home_panel.add(toolBar);
            toolBar.addSeparator();
            
            JPanel profile_panel = new JPanel();
    		manip.add(profile_panel, "profile_panel");
    		profile_panel.setLayout(null);

    		JLabel lblNewLabel_6 = new JLabel("User Name");
    		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
    		lblNewLabel_6.setBounds(10, 63, 176, 62);
    		profile_panel.add(lblNewLabel_6);
    		
    		final JLabel name_label = new JLabel();
    		name_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
    		name_label.setBounds(197, 63, 309, 62);
    		profile_panel.add(name_label);
    		
    		JLabel lblNewLabel_6_1 = new JLabel("Email");
    		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
    		lblNewLabel_6_1.setBounds(10, 164, 176, 62);
    		profile_panel.add(lblNewLabel_6_1);
    		
    		JLabel lblNewLabel_6_2 = new JLabel("Phone Number");
    		lblNewLabel_6_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
    		lblNewLabel_6_2.setBounds(10, 262, 176, 62);
    		profile_panel.add(lblNewLabel_6_2);
    		
    		JLabel lblNewLabel_6_3 = new JLabel("City");
    		lblNewLabel_6_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
    		lblNewLabel_6_3.setBounds(10, 370, 176, 62);
    		profile_panel.add(lblNewLabel_6_3);
    		
    		final JLabel email_label = new JLabel(cusd.email);
    		email_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
    		email_label.setBounds(197, 164, 309, 62);
    		profile_panel.add(email_label);
    		
    		final JLabel phone_label = new JLabel(cusd.phn);
    		phone_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
    		phone_label.setBounds(197, 262, 309, 62);
    		profile_panel.add(phone_label);
    		
    		final JLabel city_label = new JLabel(cusd.city);
    		city_label.setFont(new Font("Tahoma", Font.PLAIN, 17));
    		city_label.setBounds(197, 370, 309, 62);
    		profile_panel.add(city_label);
            
            JButton btnNewButton_5 = new JButton("");
            btnNewButton_5.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		name_label.setText(cusd.fn+" "+cusd.ln);
    				email_label.setText(cusd.email);
    				phone_label.setText(cusd.phn);
    				city_label.setText(cusd.city);
    				cl.show(manip, "profile_panel");
            		  cl.show(manip, "profile_panel");
            	}
            });
            btnNewButton_5.setToolTipText("My Profile");
            btnNewButton_5.setIcon(new ImageIcon(new ImageIcon("B:\\Images\\myprofile.png").getImage().getScaledInstance(110, 40, Image.SCALE_DEFAULT)));
            btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
            toolBar.add(btnNewButton_5);
            textField_4 = new JTextField();
            textField_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
            toolBar.add(textField_4);
            textField_4.setColumns(10);
            toolBar.addSeparator();


            final JPanel rowHolderPanel2 = new JPanel(new GridLayout(0, 1, 1, 2));
    	    JPanel outerPanel2 = new JPanel(new BorderLayout());
    	    outerPanel2.add(rowHolderPanel2, BorderLayout.PAGE_START);
            JScrollPane scrollPane2 = new JScrollPane(outerPanel2);
            scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            
            panel_7.add(scrollPane2, BorderLayout.CENTER);
            
            JButton btnNewButton_4 = new JButton("search");
            btnNewButton_4.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		String tempstr = textField_4.getText();
            		
            		if(rowHolderPanel2.getComponentCount()!=0) {
            			rowHolderPanel2.removeAll();
            			rowHolderPanel2.revalidate();
            			rowHolderPanel2.repaint();
            		}
            		if(textField_4.getText().equals(""))
            			JOptionPane.showMessageDialog(home_panel,"Enter some text");
            		else {
            		
           		try {
                		Class.forName("com.mysql.cj.jdbc.Driver");

        				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
        				
        				PreparedStatement stm = con.prepareStatement("select name,quantity_in_stock,unit_price,description,image from products where name regexp ? or description regexp ?");
        				
        				stm.setString(1, tempstr);
        				stm.setString(2, tempstr);
        				
        				ResultSet rs = stm.executeQuery();
        								
        				while(rs.next())
        				{
        					
        					JPanel funPanel2 = getPanel(rs.getString("name"),rs.getInt("quantity_in_stock"),rs.getFloat("unit_price"),rs.getString("description"),rs.getString("image"));

        					rowHolderPanel2.add(funPanel2);
        		            rowHolderPanel2.revalidate();
        		            rowHolderPanel2.repaint();
        				}        		
        				stm.close();
        				con.close();
        				tabbedPane_1.setSelectedIndex(4);
                	}
                	catch(Exception ex2)
                	{
                		System.out.print(ex2);
                	}	
            		}
            		
            		
            	}
            });
            btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
            toolBar.add(btnNewButton_4);
        	
        	
            try {
        		Class.forName("com.mysql.cj.jdbc.Driver");

				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
				
				java.sql.Statement stm = con.createStatement();
				
				ResultSet rs = stm.executeQuery("select name,quantity_in_stock,unit_price,description,image from products where category='l'");
								
				while(rs.next())
				{
					
					JPanel funPanel1 = getPanel(rs.getString("name"),rs.getInt("quantity_in_stock"),rs.getFloat("unit_price"),rs.getString("description"),rs.getString("image"));

					rowHolderPanel1.add(funPanel1);
		            rowHolderPanel1.revalidate();
		            rowHolderPanel1.repaint();
				}        		
				stm.close();
				con.close();
        	}
        	catch(Exception ex2)
        	{
        		System.out.print(ex2);
        	}
		
		final JPanel admin_panel = new JPanel();
		manip.add(admin_panel, "admin_panel");
		admin_panel.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 1391, 682);
		admin_panel.add(tabbedPane);
		
		JPanel panel = new JPanel();
		Icon icn1 = new ImageIcon(new ImageIcon("B:\\Images\\additemsicon.jpg").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		tabbedPane.addTab(null,icn1, panel, "Add Products");
		
		panel.setLayout(null);

		final JTextPane textPane = new JTextPane();
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 16));
		JScrollPane tpsp = new JScrollPane(textPane);
		tpsp.setBounds(199, 280, 512, 142);
		panel.add(tpsp);
		
		final JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.addItem("Mobiles");
		comboBox_1.addItem("Laptops");
		comboBox_1.setBounds(686, 70, 212, 34);
		panel.add(comboBox_1);
		
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);    
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    formatter.setCommitsOnValidEdit(true);
		
		final JFormattedTextField formattedTextField = new JFormattedTextField(formatter);
		formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		formattedTextField.setBounds(199, 135, 212, 34);
		panel.add(formattedTextField);
		//////////////////////////////
		
		final JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		formattedTextField_1.setBounds(199, 206, 212, 34);
		panel.add(formattedTextField_1);
		
		JButton btnNewButton_8 = new JButton("choose image");
		btnNewButton_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(admin_panel);
				File file = fc.getSelectedFile();
			    imageOfProduct = file.getAbsolutePath();
			    if(imageOfProduct != null)
			    	JOptionPane.showMessageDialog(admin_panel,"Image Added Sucessfully!");
			}
		});
		btnNewButton_8.setBounds(199, 449, 212, 34);
		panel.add(btnNewButton_8);
		
		
		JButton btnNewButton_7 = new JButton("Add Item");
		btnNewButton_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_7.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent e) {			
				// 1. THIS CODE IS FOR ADDING ITEMS INTO THE DATABASE 
				// 2. FIRST IT CHECKS WETHER ALL THE INFORMATION IS ENTERED OR NOT IF NOT IT WILL SHOW POP UP MESSAGE INSORTION NOT DONE
				// 3. IF ALL FIELDS ARE ENTERED ALL THE INFORMATION THAT ADMIN ENTERED STORED TO PRODUCTS TABEL IF HE SELECTS MOBILE,
				//    IT STORES m AS CATAGORY ELSE l (LAPTOPS)
				// 4. IF EVERYTHING IS OK IT SHOWS PRODUCT ADDED SUCESSFULLY
				// 5. ELSE PRODUCT NOT ADDED AND SHOWS MESSAGE
				if(textField_1.getText().equals("") || formattedTextField.getText().equals("") || formattedTextField_1.getText().equals("") || textPane.getText().equals("") || imageOfProduct.equals(""))
					JOptionPane.showMessageDialog(admin_panel,"Some Filds are missing!");
				else
				{
					try
					{
						Class.forName("com.mysql.cj.jdbc.Driver");

						Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
						
						PreparedStatement stm = con.prepareStatement("insert into products(name,quantity_in_stock,unit_price,description,image,category) values(?,?,?,?,?,?)");					   
					    
					    stm.setString(1,textField_1.getText());
					    
					    stm.setInt(2,Integer.parseInt(formattedTextField.getText()));
					    
					    stm.setFloat(3,Float.parseFloat(formattedTextField_1.getText()));
					    
					    stm.setString(4, textPane.getText());
					    
					    stm.setString(5, imageOfProduct);		
					    
					    if (((String)comboBox_1.getSelectedItem()).equals("Mobiles"))
					    	stm.setString(6, "m");
					    else
					    	stm.setString(6, "l");
					    
					    stm.executeUpdate();
					
					    JOptionPane.showMessageDialog(admin_panel,"Product Added Sucessfully!");
					
					}
					catch(Exception exc1)
					{
						JOptionPane.showMessageDialog(admin_panel,exc1);
					}
				}
				
			}
		});
		btnNewButton_7.setBounds(115, 489, 125, 34);
		panel.add(btnNewButton_7);
				
		JLabel lblNewLabel_3 = new JLabel("Product Name");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(10, 67, 137, 34);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(199, 71, 212, 34);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3_1 = new JLabel("Stock");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(10, 135, 137, 34);
		panel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("Unit Price");
		lblNewLabel_3_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_2.setBounds(10, 206, 137, 34);
		panel.add(lblNewLabel_3_2);
		
		JLabel lblNewLabel_3_3 = new JLabel("Description of Product");
		lblNewLabel_3_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_3.setBounds(10, 280, 179, 34);
		panel.add(lblNewLabel_3_3);
		
		JLabel lblNewLabel_3_4 = new JLabel("select image");
		lblNewLabel_3_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_4.setBounds(10, 449, 137, 34);
		panel.add(lblNewLabel_3_4);
		
		JLabel lblNewLabel_4 = new JLabel("Select Category");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(499, 70, 212, 34);
		panel.add(lblNewLabel_4);
				
		JPanel panel_1 = new JPanel();
		Icon icn2 = new ImageIcon(new ImageIcon("B:\\Images\\updatestock.jpg").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		tabbedPane.addTab(null, icn2, panel_1, "Update Stock");
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Product Name");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5.setBounds(20, 89, 132, 47);
		panel_1.add(lblNewLabel_5);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(201, 89, 265, 45);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_5_1 = new JLabel("New Stock");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5_1.setBounds(20, 183, 132, 47);
		panel_1.add(lblNewLabel_5_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(201, 183, 265, 45);
		panel_1.add(textField_3);
		
		JButton btnNewButton_3 = new JButton("Update");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					Class.forName("com.mysql.cj.jdbc.Driver");

					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_db","root","933@jayanth");
					
					PreparedStatement stm = con.prepareStatement("update products set quantity_in_stock=quantity_in_stock+? where name=?");					   
				    
				    stm.setInt(1,Integer.parseInt(textField_3.getText()));
				    stm.setString(2, textField_2.getText());
				    
				    stm.executeUpdate();
				
				    JOptionPane.showMessageDialog(admin_panel,"Product Stock Updated Sucessfully!");
				
				}
				catch(Exception exc1)
				{
					JOptionPane.showMessageDialog(admin_panel,exc1);
				}
				
			}
		});
					
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(118, 287, 114, 34);
		panel_1.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(new GridLayout(1,0));
		String header2[] = new String[] { "Product Name", "Quantity", "Price","Ordered Date","Shipped Date","status"};
		dtm2.setColumnIdentifiers(header2);
		table2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table2.setModel(dtm2);
		JScrollPane spt2 = new JScrollPane(table2);
		//panel_5.add(spt2);
		panel_2.add(spt2);
		Icon icn3 = new ImageIcon(new ImageIcon("B:\\Images\\orders.png").getImage().getScaledInstance(110, 90, Image.SCALE_DEFAULT));
		tabbedPane.addTab(null, icn3, panel_2, "All Orders");
		
		JButton btnNewButton_6 = new JButton("back");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(manip, "home_panel");
			}
		});
		btnNewButton_6.setBounds(10, 32, 99, 28);
		profile_panel.add(btnNewButton_6);		
		
		JButton btnNewButton_11 = new JButton("Log Out");
		btnNewButton_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl.show(manip, "one");
			}
		});
		btnNewButton_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_11.setBounds(197, 556, 146, 37);
		profile_panel.add(btnNewButton_11);
		
		JPanel forgot_password_panel = new JPanel();
		manip.add(forgot_password_panel, "forgot_pass_panel");
		forgot_password_panel.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Email");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_10.setBounds(10, 126, 156, 57);
		forgot_password_panel.add(lblNewLabel_10);
		
		final JLabel lblNewLabel_10_1 = new JLabel("New Password");
		lblNewLabel_10_1.setEnabled(false);
		lblNewLabel_10_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_10_1.setBounds(10, 373, 156, 57);
		forgot_password_panel.add(lblNewLabel_10_1);
		
		final JLabel lblNewLabel_11_1 = new JLabel("");
		lblNewLabel_11_1.setEnabled(false);
		lblNewLabel_11_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_11_1.setBounds(182, 359, 232, 57);
		forgot_password_panel.add(lblNewLabel_11_1);
		
		
		JLabel lblNewLabel_11_2 = new JLabel("");
		lblNewLabel_11_2.setEnabled(false);
		lblNewLabel_11_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_11_2.setBounds(182, 373, 232, 57);
		forgot_password_panel.add(lblNewLabel_11_2);
		
		final JButton btnNewButton_12_1 = new JButton("change password");
		btnNewButton_12_1.setEnabled(false);
		btnNewButton_12_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_12_1.setBounds(73, 480, 176, 34);
		forgot_password_panel.add(btnNewButton_12_1);
		
		final JLabel lblNewLabel_10_2 = new JLabel("Enter Verification code");
		lblNewLabel_10_2.setEnabled(false);
		lblNewLabel_10_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_10_2.setBounds(10, 282, 176, 57);
		forgot_password_panel.add(lblNewLabel_10_2);
		
		final JLabel lblNewLabel_11_3 = new JLabel("");
		lblNewLabel_11_3.setEnabled(false);
		lblNewLabel_11_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_11_3.setBounds(196, 282, 232, 57);
		forgot_password_panel.add(lblNewLabel_11_3);
		JButton btnNewButton_12 = new JButton("send");
		btnNewButton_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
			}
		});

		btnNewButton_12.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton_12.setBounds(73, 210, 150, 34);
		forgot_password_panel.add(btnNewButton_12);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_5.setBounds(212, 136, 246, 46);
		forgot_password_panel.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_6.setColumns(10);
		textField_6.setBounds(212, 293, 246, 46);
		forgot_password_panel.add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textField_7.setColumns(10);
		textField_7.setBounds(212, 373, 246, 46);
		forgot_password_panel.add(textField_7);
		
	}
	
	float p=1;
	float tp = 0;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	// 1. WHEN ADD TO CART BUTTON IS CLICKED THIS ACTION EVEN IS PERFORMED
	// 2. FIRST IT CHECKS WETHER THE BUTTON IS ADD TO CART OR NOT ONLY THEN IT WILL PROCED
	// 3. I AM GETTING THE PARENT PANEL OF THE BUTTON THAT IS CLICKED
	// 4. FROM THE PARENT PANEL I AM GETTING THE product_name LABEL, product_price LABEL, product_qunatity SPINNER AND STOCK LABEL
	// 5. I AM CHECKING WETHER THE QUANTITY HE SELECTED IS VALID OR NOT.IF VALID IT WILL PROCEED
	// 6. AFTER GETTING INFORMATION OF THE PRODUCT I AM INSERTING INTO JTABLE 
	// 7. PARLELLY I AM INSERTING THE REFERENCE OF THE STOCK LABEL INTO ARRAYLIST1
	// 8. I AM TAKING ARRALIST1 TO HOLD THE REFERENCE OF THE STOCK LABEL TO MODIFIY THE STOCK WHEN BUY NOW BUTTON CLICKED
	// 9. I AM STORING LABEL REFERENCE IN ARRAYLIST BY ADDING 1 GAP BETWEEN EVERY LABEL BECAUSE TO FLOW SAME IN FOR LOOP IN THE BUY NOW CODE
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Add to Cart"));
		{
			 JPanel jp = (JPanel) ((JButton)e.getSource()).getParent(); // GETTING PARENT PANEL

	         JLabel product_name = (JLabel) jp.getComponent(1);

	         JLabel product_price = (JLabel) jp.getComponent(2);
	         
	         JSpinner product_qunatity = (JSpinner) jp.getComponent(4);
	         
	         JLabel stockLbl = (JLabel) jp.getComponent(5);
	        
	         if((int)product_qunatity.getValue() == 0 || (int)product_qunatity.getValue()>Integer.parseInt(stockLbl.getText()) )
	        	 JOptionPane.showMessageDialog(panel_3,"Enter valid number of products");	         
	         else {
	        	 
	       	         
	         arrayList1.add(jp.getComponent(5));
	         arrayList1.add("");

	         
	         p = Float.parseFloat(product_price.getText()) * (int)product_qunatity.getValue();
	         
	       // System.out.println(product_name.getText());
	       // System.out.println(product_price.getTE);
	       // System.out.println(product_qunatity.getValue());
	         
	         dtm.addRow(new Object[] {product_name.getText(),product_price.getText(),product_qunatity.getValue(),p});
	         
	         for(int i = 0;i<table.getModel().getRowCount();i++)
	         {
	        	 tp = tp+(float) table.getModel().getValueAt(i,3);
	         }
	         JOptionPane.showMessageDialog(panel_8,"Added to Cart");
	         lblNewLabel_8.setText(tp+"");
	         tp=0;
	         p=1;	
	         }
		}
		
	}
	
	
}
