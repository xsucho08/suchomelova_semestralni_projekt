package sql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {

	 
	private static Connection conn = null;
	
	/*Metoda vytvari spojeni s SQL databazi*/
	public static  Connection connect() { 
	if(conn == null) {
		  synchronized (DBConnection.class) {
		  if(conn==null)
	       try {
	    	   Class.forName("org.sqlite.JDBC");
	              conn = DriverManager.getConnection("jdbc:sqlite:myDBB.db");                       
	       }  catch (SQLException  | ClassNotFoundException e) { 
	            System.out.println(e.getMessage());
	            e.printStackTrace();
		    return null;
	      }
	}
	}
	      return conn;
	}

	/*Ukoncuje spojeni s SQL databazi*/
	public static void disconnect() { 
		if (conn != null) {
			try {     
				conn.close();  
		    } catch (SQLException ex) { 
		    	System.out.println(ex.getMessage()); }
		}
	}


}

