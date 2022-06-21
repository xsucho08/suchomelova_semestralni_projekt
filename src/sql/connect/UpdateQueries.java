package sql.connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UpdateQueries {

  public UpdateQueries() {}

  /*Zaznamu, ktery ma odpovidajici ID zmeni sloupce se souctem znamek a poctem, podle parametru, ktere byly metode predany.
   * Zajisti se tak, aby se studentovi, ktery uz v SQL databazi je, zapocitaly aktualni znamky.   */
  public boolean update(int ID, int soucet, int pocet) {
      String sql = "UPDATE dbstudentu SET soucetZnamek = ? , "
              + "pocetZnamek = ? "
              + "WHERE ID = ?";
      Connection conn = DBConnection.connect();

      try {
              PreparedStatement pstmt = conn.prepareStatement(sql);             
              pstmt.setInt(1, soucet);
              pstmt.setInt(2, pocet);
              pstmt.setInt(3, ID);
              pstmt.executeUpdate();
              return true;
      } 
      catch (SQLException e) {
          System.out.println(e.getMessage());
          return false;
      }
      
  }


}
