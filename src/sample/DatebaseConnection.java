package sample;

import java.sql.*;

/**
 * Created by dkoby on 28.02.2018.
 */
public class DatebaseConnection {

    private static DatebaseConnection instance=null;
    private Connection con = null;
    private ResultSet rs;
    private Statement st = null;



      private DatebaseConnection() throws SQLException {
          try {
              con = DriverManager.getConnection("jdbc:mysql://localhost:3306/baza", "root", "");
              st = con.createStatement();
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
    public static DatebaseConnection getInstance(){
        if(instance ==null){
            try {
                instance=new DatebaseConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

      public ResultSet getData(String x) {
          try {

              rs = st.executeQuery(x);

          } catch (Exception e) {
              System.out.println(e);
          }
          return rs;
      }

      public void delteWyp(String x,String im, String naz, String tyt){
        try {
            PreparedStatement stat= con.prepareStatement(x);
            stat.setString(1,im);
            stat.setString(2,naz);
            stat.setString(3,tyt);
            stat.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e);
        }
      }
}
