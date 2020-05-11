package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

public class BookList {
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/books?useSSL=false";
	private static final String DATABASE_USERNAME = "root";
	private static final String DATABASE_PASSWORD = "Tiger";
	private static final String SELECT_QUERY = "SELECT * FROM bo ";
	private static List<String> Publishers = new ArrayList<String>();
	private static List<String> Titles = new ArrayList<String>();
	private static List<String> Categories = new ArrayList<String>();
	private static List<Integer> Publication_year = new ArrayList<Integer>();
	private static List<Integer> ISBN = new ArrayList<Integer>();
	private static List<Integer> Selling_price = new ArrayList<Integer>();
	private static List<ArrayList<String>> Authers = new ArrayList<ArrayList<String>>();
	private static int counter = 0;
	private Pane root;

	public BookList(MainPage mainPage) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BookList.fxml"));
		loader.setController(this);
		try {
			root = loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
		mainPage.ParentPane.getChildren().add(root);
		viewBooks(0);
	}

	@FXML
	private GridPane Grid0, Grid1, Grid2, Grid3, Grid4, Grid5, Grid6, Grid7, Grid8, Grid9;

	@FXML
	private Label Label0, Label1, Label2, Label3, Label4, Label5, Label6, Label7, Label8, Label9;

	@FXML
	private Button Detail0, Detail1, Detail2, Detail3, Detail4, Detail5, Detail6, Detail7, Detail8, Detail9;

	@FXML
	private Button Add0, Add1, Add2, Add3, Add4, Add5, Add6, Add7, Add8, Add9;

	@FXML
	private Line Line0, Line1, Line2, Line3, Line4, Line5, Line6, Line7, Line8, Line9;

	@FXML
	private Button Previous;

	@FXML
	private Button Next;

	@FXML
	void nextAction(ActionEvent event) {
		counter += 10;
		Next.setDisable(true);
		viewBooks(counter);
		if (counter - ISBN.size() < 10) {
			Next.setDisable(true);
		}
		Previous.setDisable(false);
	}

	@FXML
	void previousAction(ActionEvent event) {
		counter -= 10;
		Next.setDisable(false);
		viewBooks(counter);
		if (counter < 10) {
			Previous.setDisable(true);
		}
	}

	public void viewBooks(int counter) {
		if (counter < ISBN.size()) {
			Grid0.setVisible(true);
			Line0.setVisible(true);
			Label0.setText(Titles.get(counter));
			counter++;
		} else {
			Grid0.setVisible(false);
			Line0.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid1.setVisible(true);
			Line1.setVisible(true);
			Label1.setText(Titles.get(counter));
			counter++;
		} else {
			Grid1.setVisible(false);
			Line1.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid2.setVisible(true);
			Line2.setVisible(true);
			Label2.setText(Titles.get(counter));
			counter++;
		} else {
			Grid2.setVisible(false);
			Line2.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid3.setVisible(true);
			Line3.setVisible(true);
			Label3.setText(Titles.get(counter));
			counter++;
		} else {
			Grid3.setVisible(false);
			Line3.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid4.setVisible(true);
			Line4.setVisible(true);
			Label4.setText(Titles.get(counter));
			counter++;
		} else {
			Grid4.setVisible(false);
			Line4.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid5.setVisible(true);
			Line5.setVisible(true);
			Label5.setText(Titles.get(counter));
			counter++;
		} else {
			Grid5.setVisible(false);
			Line5.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid6.setVisible(true);
			Line6.setVisible(true);
			Label6.setText(Titles.get(counter));
			counter++;
		} else {
			Grid6.setVisible(false);
			Line6.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid7.setVisible(true);
			Line7.setVisible(true);
			Label7.setText(Titles.get(counter));
			counter++;
		} else {
			Grid7.setVisible(false);
			Line7.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid8.setVisible(true);
			Line8.setVisible(true);
			Label8.setText(Titles.get(counter));
			counter++;
		} else {
			Grid8.setVisible(false);
			Line8.setVisible(false);
		}
		if (counter < ISBN.size()) {
			Grid9.setVisible(true);
			Line9.setVisible(true);
			Label9.setText(Titles.get(counter));
			counter++;
		} else {
			Grid9.setVisible(false);
			Line9.setVisible(false);
		}
		if (ISBN.size() - counter != 0) {
			Next.setDisable(false);
		}
	}

	@FXML
	void addAction(ActionEvent event) {

		//
		String n = ((Node) event.getSource()).getId();
		String pattern = "(Add)(\\d+)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(n);
		if (m.find()) {
			PassValues.setCartBooks(ISBN.get(counter + Integer.parseInt(m.group(2))));
		}
	}

	@FXML
	void detailAction(ActionEvent event) {
		String n = ((Node) event.getSource()).getId();
		String pattern = "(Detail)(\\d+)";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(n);
		if (m.find()) {
			int index = counter + Integer.parseInt(m.group(2));
			Dialog<String> dialog = new Dialog<>();
			ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
			dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
			GridPane grid = new GridPane();
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(20, 150, 10, 10));
			grid.add(new Label("Title:"), 0, 0);
			grid.add(new Label(Titles.get(index)), 1, 0);
			grid.add(new Label("Auther(s):"), 0, 1);
			grid.add(new Label("Publisher:"), 0, 2);
			grid.add(new Label(Publishers.get(index)), 1, 2);
			grid.add(new Label("Publication year:"), 0, 3);
			grid.add(new Label(Publication_year.get(index).toString()), 1, 3);
			grid.add(new Label("Selling price:"), 0, 4);
			grid.add(new Label(Selling_price.get(index).toString()), 1, 4);
			dialog.getDialogPane().setContent(grid);
			dialog.showAndWait();
		}
	}

	public int validate() throws SQLException {
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ISBN.add(resultSet.getInt("id"));
				Publication_year.add(resultSet.getInt("Publication_Day"));
				Titles.add(resultSet.getString("Title"));
				Publishers.add(resultSet.getString("Publisher"));
				Selling_price.add(resultSet.getInt("Price"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return 0;
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

	@FXML
	public void initialize() {

		try {
			validate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Next.setDisable(true);
		Previous.setDisable(true);
	}
}
