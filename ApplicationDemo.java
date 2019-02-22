package com.gces.jdbc;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ApplicationDemo extends Frame

{
/**
	 * 
	 */
private static final long serialVersionUID = 1L;
public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
public static final String JDB_URL = "jdbc:mysql://localhost:3306/application";
		
	Label nameLabel,passwordLabel,idLabel;
	Button insertButton,updateButton,deleteButton;
	TextField nameField, passwordField,idField;

	
	ApplicationDemo(String title) throws NullPointerException{
		super(title);
		
		setSize(200,300);
		setLayout(new FlowLayout());
		setVisible(true);
		insertButton = new Button("Insert");
        updateButton = new Button("Update");
		deleteButton = new Button("Delete");
		idLabel = new Label("Id");
		nameLabel = new Label("Name");
		passwordLabel = new Label("Password");
		nameField = new TextField("Name", 20);
		passwordField = new TextField("", 20);
		passwordField.setEchoChar('*');
		idField = new TextField("Id",20);
		add(idLabel);
		add(idField);
		add(nameLabel);
		add(nameField);
		add(passwordLabel);
		add(passwordField);
		add(insertButton);
		add(updateButton);
		add(deleteButton);
		
		/* When label gains focus, field should me empty. */
		nameField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				nameField.setText("");
			}
			
			public void focusLost(FocusEvent fe) {
				if(nameField.getText().equals("")) {
					nameField.setText("Name");
				}
			}
		});
		
		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				passwordField.setText("");
			}
			
			public void focusLost(FocusEvent fe) {
				if(passwordField.getText().equals("")) {
					passwordField.setText("");
				}
			}
		});
		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent fe) {
				idField.setText("");
			}
			
			public void focusLost(FocusEvent fe) {
				if(idField.getText().equals("")) {
					idField.setText("Id");
				}
			}
		});

		
	    insertButton.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	             theQuery("INSERT INTO `applicationdemo`(`name`,`password`) VALUES('"+nameField.getText()+"','"+passwordField.getText()+"')");
	         }
	         catch(Exception ex){}
	         }
	     });
	    
	        //button update
	        updateButton.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	         
	           theQuery("UPDATE `applicationdemo` SET `name` = '"+nameField.getText()+"', `password` = '"+passwordField.getText()+"' WHERE `id` = "+idField.getText());
	         }
	         catch(Exception ex){}
	         }
	     });
	       
	         //button delete
	        deleteButton.addActionListener(new  ActionListener() {

	         public void actionPerformed(ActionEvent e) {
	         try{
	          
	             theQuery("DELETE FROM `applicationdemo` WHERE `id` = "+idField.getText());
	         }
	         catch(Exception ex){}
	         }
	     });
	        /* Closable Frame */
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent we) {
					System.exit(0);
				}
			});

	}
	
	public void theQuery(String query)
	{   

		Connection con = null;
		Statement st = null;
		try {
			Class.forName(JDBC_DRIVER);
			con = DriverManager.getConnection(JDB_URL,"root","");
			st = con.createStatement();
			st.executeUpdate(query);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}
	
	public static void main(String[] args) {
		new ApplicationDemo("Data Export to Database");
	}
}

