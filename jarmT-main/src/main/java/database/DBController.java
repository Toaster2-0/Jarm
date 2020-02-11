package database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBController {
	
    private static DBController dbcontroller;
    private static final String DB_PATH = System.getProperty("user.home") + "/JARM/" + "jarmdb.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("Fehler beim Laden des JDBC-Treibers");
            e.printStackTrace();
        }
    }
    
    private DBController() throws SQLException{
    	File folder = new File(System.getProperty("user.home") + "/JARM/");
    	folder.mkdir();
		createTables();

    }
    
    public static DBController getInstance() throws SQLException{
    	
    	if (dbcontroller == null ) {
    		dbcontroller = new DBController();
    	}
        return dbcontroller;
    }
    
    public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + DB_PATH);
    }
    
    private void createTables() throws SQLException{
    	try (Connection con = getConnection(); 
    			Statement stmt = con.createStatement()){
			
			stmt.execute(DBCreation.CREATE_USER_TABLE);
			stmt.execute(DBCreation.CREATE_PROJECT_TABLE);
			stmt.execute(DBCreation.CREATE_PROJECT_USER_TABLE);
			stmt.execute(DBCreation.CREATE_PROJECT_TODO_TABLE);
			stmt.execute(DBCreation.CREATE_PROJECT_TODO_USER_TABLE);
			stmt.execute(DBCreation.CREATE_PROJECT_MESSAGE_TABLE);
			
			System.out.println("Tables created");
		} catch (SQLException e) {
			throw e;
		}
    }
    
    public static void main(String[] args) {
		System.out.println(DB_PATH);
	}
}
