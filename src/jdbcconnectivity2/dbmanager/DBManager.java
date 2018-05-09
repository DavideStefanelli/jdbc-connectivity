/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcconnectivity2.dbmanager;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author web4e
 */
public class DBManager {
    
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;

    private String host;
    private String db;
    private String user;
    private String password;
    
    public DBManager(String host, String db, String user, String password) {
        this.host = host;
        this.db = db;
        this.user = user;
        this.password = password;
    }
    
    public Connection getConnection() throws DBConnectionException {
        try {
            Class.forName(MYSQL_DRIVER);
             
            conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + db, user, password);
            return conn;
        } catch(Exception e) {
            throw new DBConnectionException("Errore di connessione al db");
        }
    }
    
    public void closeConnection() {
        try {
            if(conn != null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   

    
    
}
