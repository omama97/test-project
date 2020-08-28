package com.ddemo.OmStore.map;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.exception.DRException;

@SuppressWarnings("deprecation")
public class SimpleReportExample {

  public static void main(String[] args) {
	Connection connection = null;
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/store","root", "");
		System.out.println("Connection Done !");
	} catch (SQLException e) {
		e.printStackTrace();
		return;
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		return;
	}

	JasperReportBuilder report = DynamicReports.report();//a new report
	System.out.println("hiiiiii !");

	report
	  .columns(
	      Columns.column("Customer Id", "id", DataTypes.integerType()),
	      Columns.column("Prodcut Name", "product_name", DataTypes.stringType()),
	     Columns.column("Price", "price", DataTypes.doubleType()))
	  
	  .title(//title of the report
	      Components.text("SimpleReportExample"))
		  .pageFooter(Components.pageXofY())//show page number on the page footer
		  .setDataSource("SELECT id, product_name ,price FROM product",
                                  connection);

	try {
		  JFrame f=new JFrame("Button Example");  
		    JButton b=new JButton("Click Here");  
		    b.setBounds(50,100,95,30);  
		    b.addActionListener(new ActionListener(){  
		public void actionPerformed(ActionEvent e){  
		               //show the report
		    		try {
						report.show();
						//export the report to a pdf file
			    		report.toPdf(new FileOutputStream("D:/report.pdf"));
					} catch (DRException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

		                    
		        }  
		    });  
		    f.add(b); 
		    f.setSize(400,400);  
		    f.setLayout(null);  
		    f.setVisible(true);   
		
 

}catch (Exception e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();}
	
  }}