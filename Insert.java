package dbms;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
@SuppressWarnings("serial")
public class Insert extends Frame implements ActionListener 
{
	MenuBar mb;
	MenuItem m1,m2,m3,m4,m5,m6,m7,m8,m9,m10,m11,m12,m13,m14,m15,m16;
	Menu menu,bmi,orders,customerAccount;
	Button insertButton;
	TextField dishidText, dishnameText, dishpriceText, dishtypeText;
	TextField bmiidText, bmicategoryText, bmicountText, bmidietText;
	TextField orderidText, ordernameText, orderpriceText, ordertypeText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	
	
	//For updates
	Button updateButton,submit;
	List menuList,ordersList,bmiList,customerList;
	ResultSet rs;
	//TextField dishidText, dishnameText, dishpriceText, dishtypeText;
	
	//For delete
	Button deleteRowButton,Delete,modify;
	TextField customeridText,customerNameText,customerPhoneNumberText,customerMailText,customerAddressText;
	
	public Insert()
	{
		try 
		{
			Class.forName ("oracle.jdbc.driver.OracleDriver");
		} 
		catch (Exception e) 
		{
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB ();
	}

	public void connectToDB() 
    {
		try 
		{
		  connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","kanishka","vasavi");
		  statement = connection.createStatement();

		} 
		catch (SQLException connectException) 
		{
		  System.out.println(connectException.getMessage());
		  System.out.println(connectException.getSQLState());
		  System.out.println(connectException.getErrorCode());
		  System.exit(1);
		}
    }
	public void buildFrame()
	{
		//Basic Frame Properties
		setTitle("Online bmi Management System");
		setSize(500, 600);
		setVisible(true);
		
		//menubar
		mb = new MenuBar();
		setMenuBar(mb);  
        setSize(550,500);  
        setLayout(null);  
        setVisible(true);
        
        //Menu 
         menu=new Menu("Menu");  
        
         m1=new MenuItem("Insert Menu");  
         m2=new MenuItem("Update Menu");  
         m3=new MenuItem("Delete Menu");  
         m4=new MenuItem("View Menu");
           
        menu.add(m1);  
        menu.add(m2);  
        menu.add(m3); 
        menu.add(m4);
        
        mb.add(menu);
        
        //bmi
        bmi=new Menu("bmi"); 
        m5=new MenuItem("Insert bmi");
        m6=new MenuItem("Update bmi");
        m7=new MenuItem("Delete bmi");
        m8=new MenuItem("View bmi");
        
        bmi.add(m5);
        bmi.add(m6);
        bmi.add(m7);
        bmi.add(m8);
        
        mb.add(bmi);
        
        //Orders
        orders=new Menu("Orders");  
        m9=new MenuItem("Insert Orders");
        m10=new MenuItem("Update Orders");
        m11=new MenuItem("Delete Orders");
        m12=new MenuItem("View Orders");
       
        orders.add(m9);
        orders.add(m10);
        orders.add(m11);
        orders.add(m12);
        
        mb.add(orders);
        
        customerAccount=new Menu("Customer Account");
        m13=new MenuItem("Insert Customer Details");
        m14=new MenuItem("Update Customer Details");
        m15=new MenuItem("Delete Customer Details");
 
        
        customerAccount.add(m13);
        customerAccount.add(m14);
        customerAccount.add(m15);
   
        
        mb.add(customerAccount);
        
       //Registering action Listeners
        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        m8.addActionListener(this);
        m9.addActionListener(this);
        m10.addActionListener(this);
        m11.addActionListener(this);
        m12.addActionListener(this);
        m13.addActionListener(this);
        m14.addActionListener(this);
        m15.addActionListener(this);
     
        
      
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String arg = ae.getActionCommand();
		if(arg.equals("Insert Menu"))
			this.buildGUIMenu();
		if(arg.equals("Update Menu"))
			this.updateMenuGUI();
		if(arg.equals("Delete Menu"))
			this.deleteGUIMenu();
		if(arg.equals("View Menu"))
			this.viewMenuGUI();	
		if(arg.equals("Insert bmi"))
			this.buildGUIbmi();
		if(arg.equals("Update bmi"))
			this.updatebmiGUI();
		if(arg.equals("Delete bmi"))
			this.deleteGUIbmi();
		if(arg.equals("View bmi"))
			this.viewbmiGUI();	
		if(arg.equals("Insert Orders"))
			this.buildGUIOrders();
		if(arg.equals("Update Orders"))
			this.updateOrdersGUI();
		if(arg.equals("Delete Orders"))
			this.deleteGUIOrders();
		if(arg.equals("View Orders"))
			this.viewOrdersGUI();	
		if(arg.equals("Insert Customer Details"))
			this.buildGUICustomerDetails();
		if(arg.equals("Update Customer Details"))
			this.updateCustomerAccount();
		if(arg.equals("Delete Customer Details"))
			this.deleteCustomerDetails();
		
	}
	
	public void buildGUIMenu() 
	{	
		removeAll();
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO menu VALUES('" + dishidText.getText() + "', " + "'" + dishnameText.getText() + "'," + dishpriceText.getText() + ",'" + dishtypeText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		dishidText = new TextField(15);
		dishnameText = new TextField(15);
		dishpriceText = new TextField(15);
		dishtypeText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Dish ID:"));
		first.add(dishidText);
		first.add(new Label("Dish Name:"));
		first.add(dishnameText);
		first.add(new Label("Dish Price:"));
		first.add(dishpriceText);
		first.add(new Label("Dish Type:"));
		first.add(dishtypeText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    
	}
	public void buildGUIbmi() 
	{	 	
		removeAll();
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO bmi VALUES('" + bmiidText.getText() + "', " + "'" + bmicategoryText.getText() + "'," + bmicountText.getText() + ",'" + bmidietText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		bmiidText = new TextField(15);
		bmicategoryText = new TextField(15);
		bmicountText = new TextField(15);
		bmidietText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("bmi ID:"));
		first.add(bmiidText);
		first.add(new Label("bmi Name:"));
		first.add(bmicategoryText);
		first.add(new Label("bmi weight:"));
		first.add(bmicountText);
		first.add(new Label("bmi Type:"));
		first.add(bmidietText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    
	
	}
	public void buildGUIOrders() 
	{	removeAll();	
		//Handle Insert Account Button
		insertButton = new Button("Submit");
		insertButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO Orders VALUES('" + orderidText.getText() + "', " + "'" + ordernameText.getText() + "'," + orderpriceText.getText() + ",'" + ordertypeText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nInserted " + i + " rows successfully");
				} 
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});	
		orderidText = new TextField(15);
		ordernameText = new TextField(15);
		orderpriceText = new TextField(15);
		ordertypeText= new TextField(15);

		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Payment Type:"));
		first.add(ordertypeText);
		first.setBounds(125,90,200,100);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertButton);
        		second.setBounds(125,220,150,100);         
		
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		
		//setLayout(null);
		

		add(first);
		add(second);
		add(third);
		
		setLayout(new FlowLayout());
		setVisible(true);
	    

	}
	
	private void loadMenu() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM menu");
		  while (rs.next()) 
		  {
			  menuList.add(rs.getString("dishid"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  //displaySQLErrors(e);
		}
	}
	public void updateMenuGUI() 
	{	
		removeAll();
		menuList = new List(6);
		loadMenu();
		add(menuList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM menu");
					while (rs.next()) 
					{
						if (rs.getString("dishid").equals(menuList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						dishidText.setText(rs.getString("dishid"));
						dishnameText.setText(rs.getString("dishname"));
						dishpriceText.setText(rs.getString("dishprice"));
						dishtypeText.setText(rs.getString("dishtype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET dishprice=" + dishpriceText.getText()  
					+ " WHERE dishid = '" + menuList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					menuList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		dishidText = new TextField(15);
		dishidText.setEditable(false);
		dishnameText = new TextField(15);
		dishnameText.setEditable(false);
		dishpriceText = new TextField(15);
		dishtypeText = new TextField(15);
		dishtypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Dish ID:"));
		first.add(dishidText);
		first.add(new Label("Dish Name:"));
		first.add(dishnameText);
		first.add(new Label("Dish Price:"));
		first.add(dishpriceText);
		first.add(new Label("Dish Type:"));
		first.add(dishtypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void deleteGUIMenu() 
	{	
		removeAll();
	    menuList = new List(10);
		loadMenu();
		add(menuList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM menu");
					while (rs.next()) 
					{
						if (rs.getString("dishid").equals(menuList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						dishidText.setText(rs.getString("dishid"));
						dishnameText.setText(rs.getString("dishname"));
						dishpriceText.setText(rs.getString("dishprice"));
						dishtypeText.setText(rs.getString("dishtype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete menu Button
		deleteRowButton = new Button("Delete Row");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM menu WHERE dishid = '" + menuList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					dishidText.setText(null);
					dishnameText.setText(null);
					dishpriceText.setText(null);
					dishtypeText.setText(null);
					menuList.removeAll();
					loadMenu();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		dishidText = new TextField(15);
		dishnameText = new TextField(15);
		dishpriceText = new TextField(15);
		dishtypeText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		dishidText.setEditable(false);
		dishnameText.setEditable(false);
		dishpriceText.setEditable(false);
		dishtypeText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Dish ID:"));
		first.add(dishidText);
		first.add(new Label("Dish Name:"));
		first.add(dishnameText);
		first.add(new Label("Dish Price:"));
		first.add(dishpriceText);
		first.add(new Label("Dish Type:"));
		first.add(dishtypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	private void loadOrders() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM orders");
		  while (rs.next()) 
		  {
			  ordersList.add(rs.getString("orderid"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updateOrdersGUI() 
	{	
		removeAll();
		ordersList = new List(6);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM orders");
					while (rs.next()) 
					{
						if (rs.getString("orderid").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						orderidText.setText(rs.getString("orderid"));
						ordernameText.setText(rs.getString("ordername"));
						orderpriceText.setText(rs.getString("orderprice"));
						ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE orders "
					+ "SET orderprice=" + orderpriceText.getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		orderidText = new TextField(15);
		orderidText.setEditable(false);
		ordernameText = new TextField(15);
		ordernameText.setEditable(false);
		orderpriceText = new TextField(15);
		ordertypeText = new TextField(15);
		ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Order Type:"));
		first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}

	public void deleteGUIOrders()
	{
		removeAll();
	    ordersList = new List(10);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM orders");
					while (rs.next()) 
					{
						if (rs.getString("orderid").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						orderidText.setText(rs.getString("orderid"));
						ordernameText.setText(rs.getString("ordername"));
						orderpriceText.setText(rs.getString("orderprice"));
						ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete orders Button
		deleteRowButton = new Button("Delete Row");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM orders WHERE orderid = '" + ordersList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					orderidText.setText(null);
					ordernameText.setText(null);
					orderpriceText.setText(null);
					ordertypeText.setText(null);
					ordersList.removeAll();
					loadOrders();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		orderidText = new TextField(15);
		ordernameText = new TextField(15);
		orderpriceText = new TextField(15);
		ordertypeText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		orderidText.setEditable(false);
		ordernameText.setEditable(false);
		orderpriceText.setEditable(false);
		ordertypeText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Order Type:"));
		first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	
	private void loadbmi() 
	{	   
		try 
		{
		  rs = statement.executeQuery("SELECT * FROM bmi");
		  while (rs.next()) 
		  {
			  bmiList.add(rs.getString("bmiid"));
		  }
		} 
		catch (SQLException e) 
		{ 
		  displaySQLErrors(e);
		}
	}
	public void updatebmiGUI() 
	{	
		removeAll();
		bmiList = new List(6);
		loadbmi();
		add(bmiList);
		
		//When a list item is selected populate the text fields
		bmiList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM bmi");
					while (rs.next()) 
					{
						if (rs.getString("bmiid").equals(bmiList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						bmiidText.setText(rs.getString("bmiid"));
						bmicategoryText.setText(rs.getString("bmicategory"));
						bmicountText.setText(rs.getString("bmicount"));
						bmidietText.setText(rs.getString("bmidiet"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE bmi "
					+ "SET bmicount=" + bmicountText.getText()  
					+ " WHERE bmiid = '" + bmiList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					bmiList.removeAll();
					loadbmi();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		bmiidText = new TextField(15);
		bmiidText.setEditable(false);
		bmicategoryText = new TextField(15);
		bmicategoryText.setEditable(false);
		bmicountText = new TextField(15);
		bmidietText = new TextField(15);
		bmidietText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("bmi ID:"));
		first.add(bmiidText);
		first.add(new Label("bmi Name:"));
		first.add(bmicategoryText);
		first.add(new Label("bmi Price:"));
		first.add(bmicountText);
		first.add(new Label("bmi Type:"));
		first.add(bmidietText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void loadCustomerDetails()
	{					   
			try 
			{
			  rs = statement.executeQuery("SELECT * FROM cust_account");
			  while (rs.next()) 
			  {
				  customerList.add(rs.getString("cust_id"));
			  }
			} 
			catch (SQLException e) 
			{ 
			  displaySQLErrors(e);
			}

	}
	public void deleteGUIbmi()
	{
		removeAll();
	    bmiList = new List(10);
		loadbmi();
		add(bmiList);
		
		//When a list item is selected populate the text fields
		bmiList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM bmi");
					while (rs.next()) 
					{
						if (rs.getString("bmiid").equals(bmiList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						bmiidText.setText(rs.getString("bmiid"));
						bmicategoryText.setText(rs.getString("bmicategory"));
						bmicountText.setText(rs.getString("bmicount"));
						bmidietText.setText(rs.getString("bmidiet"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});
	    
		//Handle Delete bmi Button
		deleteRowButton = new Button("Delete Row");
		deleteRowButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("DELETE FROM bmi WHERE bmiid = '" + bmiList.getSelectedItem()+"'");
					errorText.append("\nDeleted " + i + " rows successfully");
					bmiidText.setText(null);
					bmicategoryText.setText(null);
					bmicountText.setText(null);
					bmidietText.setText(null);
					bmiList.removeAll();
					loadbmi();
				} 
				catch (SQLException deleteException) 
				{
					displaySQLErrors(deleteException);
				}
			}
		});
		
		bmiidText = new TextField(15);
		bmicategoryText = new TextField(15);
		bmicountText = new TextField(15);
		bmidietText = new TextField(15);
		
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		
		bmiidText.setEditable(false);
		bmicategoryText.setEditable(false);
		bmicountText.setEditable(false);
		bmidietText.setEditable(false);
	

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("bmi ID:"));
		first.add(bmiidText);
		first.add(new Label("bmi Name:"));
		first.add(bmicategoryText);
		first.add(new Label("bmi Price:"));
		first.add(bmicountText);
		first.add(new Label("bmi Type:"));
		first.add(bmidietText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(deleteRowButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    

		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void updateCustomerAccount()
	{
		removeAll();
		customerList = new List(10);
		loadCustomerDetails();
		add(customerList);
		
		//When a list item is selected populate the text fields
		customerList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM cust_account");
					while (rs.next()) 
					{
						if (rs.getString("cust_id").equals(customerList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						customeridText.setText(rs.getString("cust_id"));
						customerNameText.setText(rs.getString("cust_name"));
						customerPhoneNumberText.setText(rs.getString("ph_no"));
						customerMailText.setText(rs.getString("email"));
						customerAddressText.setText(rs.getString("address"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		modify = new Button("Modify");
		modify.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE cust_account  SET cust_name='" + customerNameText.getText() + "',ph_no='" + customerPhoneNumberText.getText() + "',email='" + customerMailText.getText() + "',address='" + customerAddressText.getText()+ "' WHERE cust_id = '" + customerList.getSelectedItem() + "' ");
					errorText.append("\nUpdated " + i + " rows successfully");
					customerList.removeAll();
					loadCustomerDetails();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		customeridText = new TextField(20);
		customeridText.setEditable(false);
		customerNameText = new TextField(20);
		
		customerPhoneNumberText = new TextField(20);
		customerMailText = new TextField(20);
		customerAddressText=new TextField(20);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customeridText);
		first.add(new Label("Customer Name:"));
		first.add(customerNameText);
		first.add(new Label("Phone Number :"));
		first.add(customerPhoneNumberText);
		first.add(new Label("Email:"));
		first.add(customerMailText);
		first.add(new Label("Address:"));
		first.add(customerAddressText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(modify);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		setLayout(new FlowLayout());
		setVisible(true);
		
		
	}
	
	public void deleteCustomerDetails()
	{
			removeAll();
		    customerList = new List(10);
			loadCustomerDetails();
			add(customerList);
			
			//When a list item is selected populate the text fields
			customerList.addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e) 
				{
					try 
					{
						rs = statement.executeQuery("SELECT * FROM cust_account");
						while (rs.next()) 
						{
							if (rs.getString("cust_id").equals(customerList.getSelectedItem()))
							break;
						}
						if (!rs.isAfterLast()) 
						{
							customeridText.setText(rs.getString("cust_id"));
							customerNameText.setText(rs.getString("cust_name"));
							customerPhoneNumberText.setText(rs.getString("ph_no"));
							customerMailText.setText(rs.getString("email"));
							customerAddressText.setText(rs.getString("address"));
						}
					} 
					catch (SQLException selectException) 
					{
						displaySQLErrors(selectException);
					}
				}
			});
		    
			//Handle Delete orders Button
			deleteRowButton = new Button("Delete");
			deleteRowButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) 
				{
					try 
					{
						Statement statement = connection.createStatement();
						int i = statement.executeUpdate("DELETE FROM cust_account WHERE cust_id = '" + customerList.getSelectedItem()+"'");
						errorText.append("\nDeleted " + i + " rows successfully");
						customeridText.setText(null);
						customerNameText.setText(null);
						customerPhoneNumberText.setText(null);
						customerMailText.setText(null);
						customerAddressText.setText(null);
						customerList.removeAll();
						loadCustomerDetails();
					} 
					catch (SQLException deleteException) 
					{
						displaySQLErrors(deleteException);
					}
				}
			});
			
			

			customeridText = new TextField(20);
			customeridText.setEditable(false);
			customerNameText = new TextField(20);
			
			customerPhoneNumberText = new TextField(20);
			customerMailText = new TextField(20);
			customerAddressText=new TextField(20);
			

			customeridText.setEditable(false);
			customerNameText.setEditable(false);
			customerPhoneNumberText.setEditable(false);
			customerMailText.setEditable(false);
			customerAddressText.setEditable(false);

			errorText = new TextArea(10, 40);
			errorText.setEditable(false);

			Panel first = new Panel();
			first.setLayout(new GridLayout(5, 2));
			first.add(new Label("Customer ID:"));
			first.add(customeridText);
			first.add(new Label("Customer Name:"));
			first.add(customerNameText);
			first.add(new Label("Phone Number :"));
			first.add(customerPhoneNumberText);
			first.add(new Label("Email:"));
			first.add(customerMailText);
			first.add(new Label("Address:"));
			first.add(customerAddressText);
			
			Panel second = new Panel(new GridLayout(4, 1));
			second.add(deleteRowButton);
			
			Panel third = new Panel();
			third.add(errorText);
			
			add(first);
			
			add(second);
			add(third);
		    
			setLayout(new FlowLayout());
			setVisible(true);
			
			
		
		
	}
	
	public void viewMenuGUI() 
	{	
		removeAll();
		menuList = new List(6);
		loadMenu();
		add(menuList);
		
		//When a list item is selected populate the text fields
		menuList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM menu");
					while (rs.next()) 
					{
						if (rs.getString("dishid").equals(menuList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						dishidText.setText(rs.getString("dishid"));
						dishnameText.setText(rs.getString("dishname"));
						dishpriceText.setText(rs.getString("dishprice"));
						dishtypeText.setText(rs.getString("dishtype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET dishprice=" + dishpriceText.getText()  
					+ " WHERE dishid = '" + menuList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					menuList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		dishidText = new TextField(15);
		dishidText.setEditable(false);
		dishnameText = new TextField(15);
		dishnameText.setEditable(false);
		dishpriceText = new TextField(15);
		dishpriceText.setEditable(false);
		dishtypeText = new TextField(15);
		dishtypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Dish ID:"));
		first.add(dishidText);
		first.add(new Label("Dish Name:"));
		first.add(dishnameText);
		first.add(new Label("Dish Price:"));
		first.add(dishpriceText);
		first.add(new Label("Dish Type:"));
		first.add(dishtypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		//setTitle("Update ....");
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewbmiGUI() 
	{	
		removeAll();
		bmiList = new List(6);
		loadbmi();
		add(bmiList);
		
		//When a list item is selected populate the text fields
		bmiList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM bmi");
					while (rs.next()) 
					{
						if (rs.getString("bmiid").equals(bmiList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						bmiidText.setText(rs.getString("bmiid"));
						bmicategoryText.setText(rs.getString("bmicategory"));
						bmicountText.setText(rs.getString("bmicount"));
						bmidietText.setText(rs.getString("bmidiet"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE bmi "
					+ "SET bmicount=" + bmicountText.getText()  
					+ " WHERE bmiid = '" + bmiList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					bmiList.removeAll();
					loadbmi();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		bmiidText = new TextField(15);
		bmiidText.setEditable(false);
		bmicategoryText = new TextField(15);
		bmicategoryText.setEditable(false);
		bmicountText = new TextField(15);
		bmicountText.setEditable(false);
		bmidietText = new TextField(15);
		bmidietText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("bmi ID:"));
		first.add(bmiidText);
		first.add(new Label("bmi Name:"));
		first.add(bmicategoryText);
		first.add(new Label("bmi Price:"));
		first.add(bmicountText);
		first.add(new Label("bmi Type:"));
		first.add(bmidietText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		//second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
		
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void viewOrdersGUI() 
	{	
		removeAll();
		ordersList = new List(6);
		loadOrders();
		add(ordersList);
		
		//When a list item is selected populate the text fields
		ordersList.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e) 
			{
				try 
				{
					rs = statement.executeQuery("SELECT * FROM orders");
					while (rs.next()) 
					{
						if (rs.getString("orderid").equals(ordersList.getSelectedItem()))
						break;
					}
					if (!rs.isAfterLast()) 
					{
						orderidText.setText(rs.getString("orderid"));
						ordernameText.setText(rs.getString("ordername"));
						orderpriceText.setText(rs.getString("orderprice"));
						ordertypeText.setText(rs.getString("ordertype"));
					}
				} 
				catch (SQLException selectException) 
				{
					displaySQLErrors(selectException);
				}
			}
		});	    
		//Handle Update Menu Button
		updateButton = new Button("Modify");
		updateButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					Statement statement = connection.createStatement();
					int i = statement.executeUpdate("UPDATE menu "
					+ "SET orderprice=" + orderpriceText.getText()  
					+ " WHERE orderid = '" + ordersList.getSelectedItem() + "'");
					errorText.append("\nUpdated " + i + " rows successfully");
					ordersList.removeAll();
					loadMenu();
				} 
				catch (SQLException insertException) 
				{
					displaySQLErrors(insertException);
				}
			}
		});
		
		orderidText = new TextField(15);
		orderidText.setEditable(false);
		ordernameText = new TextField(15);
		ordernameText.setEditable(false);
		orderpriceText = new TextField(15);
		orderpriceText.setEditable(false);
		ordertypeText = new TextField(15);
		ordertypeText.setEditable(false);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("Order ID:"));
		first.add(orderidText);
		first.add(new Label("Order Name:"));
		first.add(ordernameText);
		first.add(new Label("Order Price:"));
		first.add(orderpriceText);
		first.add(new Label("Order Type:"));
		first.add(ordertypeText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updateButton);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		add(second);
		add(third);
	    
	
		//setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	public void displaySQLErrors(SQLException e) 
	{
		errorText.append("\nSQLException: " + e.getMessage() + "\n");
		errorText.append("SQLState:     " + e.getSQLState() + "\n");
		errorText.append("VendorError:  " + e.getErrorCode() + "\n");
	}
	public void buildGUICustomerDetails()
	{
		removeAll();
		submit = new Button("Submit");
		submit.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{		  
				  String query= "INSERT INTO cust_account VALUES('" + customeridText.getText() + "','" + customerNameText.getText() + "'," + customerPhoneNumberText.getText() + ",'" + customerMailText.getText() + "','" + customerAddressText.getText() + "')";
				  int i = statement.executeUpdate(query);
				  errorText.append("\nCreated " + i + " Account successfully");
				}
				catch (SQLException insertException) 
				{
				  displaySQLErrors(insertException);
				}
			}
		});
		
		customeridText = new TextField(20);
		customerNameText = new TextField(20);
		
		customerPhoneNumberText = new TextField(20);
		customerMailText = new TextField(20);
		customerAddressText=new TextField(20);

		errorText = new TextArea(10, 40);
		errorText.setEditable(false);

		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("Customer ID:"));
		first.add(customeridText);
		first.add(new Label("Customer Name:"));
		first.add(customerNameText);
		first.add(new Label("Phone Number :"));
		first.add(customerPhoneNumberText);
		first.add(new Label("Email:"));
		first.add(customerMailText);
		first.add(new Label("Address:"));
		first.add(customerAddressText);
		
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(submit);
		
		Panel third = new Panel();
		third.add(errorText);
		
		add(first);
		
		add(second);
		add(third);
	    
		setLayout(new FlowLayout());
		setVisible(true);
		
	
		
	}
public static void main(String[] args) 
	{
		Insert it = new Insert();
		it.addWindowListener(new WindowAdapter(){
		  public void windowClosing(WindowEvent e) 
		  {
			System.exit(0);
		  }
		});
		it.buildFrame();
	}
}
