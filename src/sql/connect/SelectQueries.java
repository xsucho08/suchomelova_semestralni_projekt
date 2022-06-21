package sql.connect;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	


public class SelectQueries {
	
	  public SelectQueries() {}
	  
	  /*Overuje, zda zaznam s odpovidajicim ID je v SQL tabulce, pokud ano, vraci true*/
	  public static boolean existujeTotoID(int ID){
		     String sql = "SELECT ID, jmeno, prijmeni FROM dbstudentu where ID=?";
		     Connection conn = DBConnection.connect();
		     try{
		    	 PreparedStatement pstmt = conn.prepareStatement(sql);
		    	 pstmt.setInt(1,ID);
		    	 ResultSet rs = pstmt.executeQuery();
		    	if(rs. next ()) 
		    		return true;
		    	else 
		    		return false;
		     
		     } catch (SQLException e) {
		       e.printStackTrace();
		       return false;
		     }		 
	}
		
	/*Metoda vypisuje nektere sloupce SQL tabulky do konzole*/
	 public  void getAllUsers() {
		 Connection conn = DBConnection.connect();
		 String sql = "SELECT ID, jmeno,prijmeni, datumNarozeni, obor FROM dbstudentu";
		    
		    try (PreparedStatement prStmt = conn.prepareStatement(sql);
			        ResultSet rs = prStmt.executeQuery()) {
			      while (rs.next()) {
			        System.out.println(
			        		rs.getInt("ID")	+ ", " +  rs.getString("jmeno") + ", " + rs.getString("prijmeni")+ ", " +  rs.getString("datumNarozeni") + ", " + rs.getString("obor"));
			      }
			    } catch (SQLException e) {
			      e.printStackTrace();
			    }
	 }

}
