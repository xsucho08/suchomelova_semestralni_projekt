package sql.connect;

import java.sql.*;


public class InsertQueries {

  public InsertQueries() {}
 
  /*Vytvoreni SQL tabulky*/
  public static boolean createTable(){
	  Connection conn = DBConnection.connect();
       if (conn==null)
            return false;
       String sql = "CREATE TABLE IF NOT EXISTS dbstudentu (ID INT NOT NULL PRIMARY KEY, jmeno varchar(255) , prijmeni varchar(255),datumNarozeni varchar(255) , obor varchar(255), soucetZnamek INT , pocetZnamek INT);";
       try {
    	   Statement stmt = conn.createStatement(); 
           stmt.execute(sql);
           return true;
      } 
      catch (SQLException e) {
      System.out.println(e.getMessage());
      }
      return false;
  }
  

/*Hodnoty, ktere metoda dostala jako parametry, ulozi metoda do SQL tabulky, pokud uz zaznam (se stejnym ID) v tabulce je, vola metodu, ktera zaznamu jen zmeni znamky*/
  public void vlozZaznam(int ID, String jmeno,String prijmeni, String datumNarozeni,String obor, int soucetZnamek, int pocetZnamek ) {
	  Connection conn = DBConnection.connect();
	  
	  
	  UpdateQueries up = new UpdateQueries(); 
	  String insertUser = "INSERT INTO dbstudentu(ID,jmeno,prijmeni,datumNarozeni,obor,soucetZnamek,pocetZnamek) VALUES(?,?,?,?,?,?,?)" ;

	  try (PreparedStatement prStmt = conn.prepareStatement(insertUser)) {
	  
	    	prStmt.setInt(1, ID);
	    	prStmt.setString(2, jmeno);
	    	prStmt.setString(3, prijmeni);
	    	prStmt.setString(4, datumNarozeni);
	    	prStmt.setString(5, obor);
	    	prStmt.setInt(6, soucetZnamek);
	    	prStmt.setInt(7, pocetZnamek);
	    	prStmt.executeUpdate();
	    } catch (SQLException e) {
	    if(	!up.update(ID, soucetZnamek,pocetZnamek))
	    	e.printStackTrace();
	    }
	  
      }
  

}
