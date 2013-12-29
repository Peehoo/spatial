import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;



public class Populate {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {
		Connection connection = getConnection();
		populateBuildingsTable(args, connection);
		populateStudentsTable(args, connection);
		populateAnnouncementsTable(args, connection);
	}

	private static void populateBuildingsTable(String[] args, Connection connection)
			throws SQLException, FileNotFoundException {
		String truncateBuildingsTable = "Truncate table Buildings;";
		executeQuery(truncateBuildingsTable, connection);
		File inFile = new File(args[0]);
		Scanner in = new Scanner(inFile);
		while(in.hasNext()){
			String query = "";
			String values = in.nextLine();
			String[] splitted = values.trim().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			query = query + "Insert into Buildings VALUES ( ";
			query = query + "'" + splitted[0] + "'," +"'" + splitted[1] + "',"
					+ splitted[2] + ", GeomFromText('Polygon((";
			for(int i=3; i< splitted.length; i=i+2){
				query = query + splitted[i];
				query = query + splitted[i+1] + ",";
			}
			query = query + splitted[3];
			query = query + splitted[4] + "))'));";
			System.out.println(query);
			executeQuery(query, connection);
		}
	}

	
	private static void populateStudentsTable(String[] args, Connection connection)
			throws SQLException, FileNotFoundException {
		String truncateStudentsTable = "Truncate table Students;";
		executeQuery(truncateStudentsTable, connection);
		File inFile = new File(args[1]);
		Scanner in = new Scanner(inFile);
		while(in.hasNext()){
			String query = "";
			String values = in.nextLine();
			String[] splitted = values.trim().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			query = query + "Insert into Students VALUES ( ";
			query = query + "'" + splitted[0] + "'," + splitted[1] + ","
					+ splitted[2] + ", GeomFromText('Point(";
			query = query + splitted[1];
			query = query + splitted[2] + ")'));";
			System.out.println(query);
			executeQuery(query, connection);
		}
	}
	
	
	private static void populateAnnouncementsTable(String[] args, Connection connection)
			throws SQLException, FileNotFoundException {
		String truncateAnnouncementsTable = "Truncate table Announcements;";
		executeQuery(truncateAnnouncementsTable, connection);
		File inFile = new File(args[2]);
		Scanner in = new Scanner(inFile);
		while(in.hasNext()){
			String query = "";
			String values = in.nextLine();
			String[] splitted = values.trim().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			query = query + "Insert into Announcements VALUES ( ";
			query = query + "'" + splitted[0] + "'," + splitted[1] + ","
					+ splitted[2] + ","+ splitted[3] + ", GeomFromText('Point(";
			query = query + splitted[1];
			query = query + splitted[2] + ")'));";
			System.out.println(query);
			executeQuery(query, connection);
		}
	}
	
	
	private static Connection getConnection() throws ClassNotFoundException,
	SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection connection = DriverManager
		          .getConnection("jdbc:mysql://localhost/geometric?"
		              + "user=root&password=");
		return connection;
	}

	private static boolean executeQuery(String query, Connection connection) throws SQLException {
		java.sql.Statement stmt = connection.createStatement();
		return stmt.execute(query);
	}

}


