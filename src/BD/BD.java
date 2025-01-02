package BD;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;

import java.sql.Statement;


public class BD {
	
    public Connection connection() {
    	
        try {
        	
            String url = "jdbc:mysql://localhost:3306/db"; //
            
            String user = "root"; // 
            
            String password = ""; // 
            

            Class.forName("com.mysql.cj.jdbc.Driver");
            
            return DriverManager.getConnection(url, user, password);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
            return null;
            
        }
    }

    public ResultSet selection(Connection con) {
    	
        try {
        	
          
            String query = "SELECT humidity, tempretuure, pression, pluie, lumniosite " +
                           "FROM db ORDER BY id DESC LIMIT 1";
            
            Statement stmt = con.createStatement();
            
            return stmt.executeQuery(query);
            
        } catch (Exception e) {
        	
            e.printStackTrace();
            
            return null;
            
        }
    }
}
