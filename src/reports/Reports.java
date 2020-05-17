package reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Reports {

	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/order_processing_system?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "root";
	
	private Connection connection;
	
	public Reports() {
		try {
			connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void totalSales() {
		if (connection != null) {
			try {
				JasperReport jasperReport = JasperCompileManager
						.compileReport("src/reports/TotalSales.jrxml");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
				JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void totalSalesSummary() {
		if (connection != null) {
			try {
				JasperReport jasperReport = JasperCompileManager
						.compileReport("src/reports/TotalSalesSummary.jrxml");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
				JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void topCustomers() {
		if (connection != null) {
			try {
				JasperReport jasperReport = JasperCompileManager
						.compileReport("src/reports/TopCustomers.jrxml");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
				JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void topSellingBooks() {
		if (connection != null) {
			try {
				JasperReport jasperReport = JasperCompileManager
						.compileReport("src/reports/TopSellingBooks.jrxml");
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, connection);
				JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
