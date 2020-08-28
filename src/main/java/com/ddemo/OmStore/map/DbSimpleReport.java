package com.ddemo.OmStore.map;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ddemo.OmStore.entity.Product;

import net.sf.dynamicreports.examples.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.column.Columns;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.exception.DRException;

public class DbSimpleReport {
	public Connection connection;
	public static JasperReportBuilder report;// a new report
	String id = "", product_name = "", price = "", insertion_date = "", image_path = "";

	

//	
//	static HashMap<String, String> product;
//
//	private static void someMethod() {
//		product =  p.getProductMap();
//		// work with your map here...
//		System.out.print(product);
//
//	}
//	
	
//	 Product x = new Product();       

//     private void someMethod() {
//          HashMap<String, String> people = x.getProductMap();
//          
//     }
	
	public static Product p = new Product();

	public DbSimpleReport() {

		id = p.ID;
		product_name = p.Product_Name;
		price = p.Price;
		insertion_date = p.Insertion_Date;
		image_path = p.Image_Path;
		System.out.println(id);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/omstore", "root", "");

			System.out.println("Connection Done !");
			build();

		} catch (SQLException e) {
			e.printStackTrace();
			return;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

	}

	private void build() {
		// TODO Auto-generated method stub
		report = DynamicReports.report();// a new report
		try {


			report.setTemplate(Templates.reportTemplate)
					.columns(Columns.column(" Id", "id", DataTypes.integerType()),
							Columns.column("Prodcut Name", "product_name", DataTypes.stringType()),

							Columns.column("image_path", "image_path", DataTypes.stringType()),
							
							Columns.column("Price", "price", DataTypes.doubleType()),
							Columns.column("insertion_date", "insertion_date", DataTypes.stringType())

					)

					.title(// title of the report
							Templates.createTitleComponent(""))
					.pageFooter(Templates.footerComponent)// show page number on the page footer
					.setDataSource("SELECT * FROM products", connection);
			// show the report
			try {
				report.show();
				// export the report to a pdf file
				report.toPdf(new FileOutputStream("D:/report.pdf"));
			} catch (DRException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {

			new DbSimpleReport();

		

		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
