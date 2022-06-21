package sql.connect;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DeleteQueries {

  public DeleteQueries() {}
  
  /*Z SQL databaze se vymaze zaznam, ktery ma stejne ID jako bylo predano metode, pokud takovy zaznam neni, metoda vrati false).*/
  public boolean deleteByID(int ID) {
	  String sql = "DELETE FROM dbstudentu WHERE ID = ?";
	  
	  if(SelectQueries.existujeTotoID(ID)) {
      try {
    	  Connection conn = DBConnection.connect();
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setInt(1, ID);
          pstmt.executeUpdate();
          return true;

      } catch (SQLException e) {
          System.out.println(e.getMessage());
          return false;
      }
	  }
	  else {
		 System.out.print("Studenta se v SQL databazi nepodarilo najit.");		 
		 return false;
  }
  }
 

}
