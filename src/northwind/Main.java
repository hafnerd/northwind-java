package northwind;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String connStr = "jdbc:sqlserver://localhost:1433;databaseName=Northwind;user=root;password=root;encrypt=true;trustServerCertificate=true;";
		
		new CustomerViewerGUI(connStr);
	}
}
