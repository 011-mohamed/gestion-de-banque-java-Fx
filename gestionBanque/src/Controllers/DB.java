package Controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class DB {
	 private static final String USERNAME = "root";
	    private static final String PASSWORD = "";
	    private static final String HOST = "127.0.0.1";
	    private static final int PORT =3306;
	    private static final String DB_NAME = "gestionbank";
	    
	    public static Connection con;
	    
	    static {
	        try {
	        	Class.forName("com.mysql.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME, USERNAME, PASSWORD);
	        } catch (SQLException ex) {          
	            
	            System.out.println("connection failed !");
	        } catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} 
			}
	    public static Connection getConnect (){
	        try {
	            con = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s", HOST,PORT,DB_NAME),USERNAME,PASSWORD);
	        } catch (SQLException ex) {
	            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
	        }
	            
	            return  con;
	        }

	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
}
