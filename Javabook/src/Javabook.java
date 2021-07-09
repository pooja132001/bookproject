import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Javabook {

	private JFrame frame;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTable table;
	private JTextField txtbid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Javabook window = new Javabook();
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
	public Javabook() {
		initialize();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;

	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BookShop", "root","root16");
	            Statement s = con.createStatement();  //creating statement
	            ResultSet rs= s.executeQuery("select * from books");  // executing statement
	            while(rs.next()) {
	            	System.out.println(rs.getInt(1)+"" +rs.getString(2)+"" +rs.getString(3)+rs.getString(4));
	            	
	            }
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }

	  public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from books");
		    rs = pst.executeQuery();
		    table.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 813, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Shop");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(318, 10, 193, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 57, 374, 219);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Edition");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(25, 98, 64, 38);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(25, 160, 64, 23);
		panel.add(lblNewLabel_1_1_1);
		
		txtbname = new JTextField();
		txtbname.setColumns(10);
		txtbname.setBounds(138, 40, 179, 23);
		panel.add(txtbname);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(138, 110, 179, 23);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(138, 164, 179, 23);
		panel.add(txtprice);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Book Name");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBounds(25, 40, 103, 38);
		panel.add(lblNewLabel_1_1_2);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
							
					String  bname,edition,price;
					 /*id = txtbid.getText();*/
					bname = txtbname.getText();
					edition = txtedition.getText();
					price = txtprice.getText();
								
					 try {
						pst = con.prepareStatement("insert into books( name,edition,price)values(?,?,?,)");
						//pst.setString(1,id);
						pst.setString(1, bname);
						pst.setString(2, edition);
						pst.setString(3, price);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
						table_load();
							           
						txtbname.setText("");
						txtedition.setText("");
						txtprice.setText("");
						txtbname.requestFocus();
					   }

					catch (SQLException e1) 
				        {
										
					e1.printStackTrace();
					}	
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(25, 286, 85, 55);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnExit.setBounds(136, 286, 85, 55);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtbname.setText("");
				txtedition.setText("");
				txtprice.setText("");
				txtbname.requestFocus();
				
				
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnClear.setBounds(243, 286, 85, 55);
		frame.getContentPane().add(btnClear);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(422, 57, 346, 316);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(25, 367, 374, 85);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Book ID");
		lblNewLabel_1_1_3.setBounds(26, 32, 75, 17);
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblNewLabel_1_1_3);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					
					 try {
				          
				            String id = txtbid.getText();

				                pst = con.prepareStatement("select name,edition,price from book where id = ?");
				                pst.setString(1, id);
				                ResultSet rs = pst.executeQuery();

				            if(rs.next()==true)
				            {
				              
				                String name = rs.getString(1);
				                String edition = rs.getString(2);
				                String price = rs.getString(3);
				                
				                txtbname.setText(name);
				                txtedition.setText(edition);
				                txtprice.setText(price);
				                
				                
				            }   
				            else
				            {
				            	txtbname.setText("");
				            	txtedition.setText("");
				                txtprice.setText("");
				                 
				            }
				            


				        } 
					
					 catch (SQLException ex) {
				           
				        }
					
					
					
					
				}
				
				
				
				
				
			
		});
		txtbid.setBounds(123, 33, 192, 19);
		txtbid.setColumns(10);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id, bname,edition,price;
				 id = txtbid.getText();
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
							
				 try {
					pst = con.prepareStatement("update books set name=?,edition=?,price=? where id=?");
					//pst.setString(1,id);
					pst.setString(1, bname);
					pst.setString(2, edition);
					pst.setString(3, price);
					pst.setString(4,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record updated!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				 
				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(445, 391, 85, 55);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id;
				 id = txtbid.getText();
				
							
				 try {
					pst = con.prepareStatement("delete from  books  where id=?");
					pst.setString(1,id);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record deleted!!!!!");
					table_load();
						           
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtbname.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
				}
				 
				
			}
		});

				
				
				
				
				
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnDelete.setBounds(572, 391, 85, 55);
		frame.getContentPane().add(btnDelete);		
				
			}
	
		
	}

