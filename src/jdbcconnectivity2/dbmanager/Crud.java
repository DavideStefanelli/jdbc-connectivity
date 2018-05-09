/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcconnectivity2.dbmanager;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Studente;

/**
 *
 * @author web4e
 */
public class Crud {
    
    private static final String insertSQL = "INSERT INTO studente (nome, cognome, email, residenza, cf, telefono, qualifica) VALUES (?,?,?,?,?,?,?)";
    private static final String deleteSQL = "DELETE FROM studente WHERE cf = ?";
    private static final String selectSQL = "SELECT * FROM studente WHERE cf = ?";
    
    private DBManager dbManager = null;
    
    public Crud(String host, String db, String user, String password){
        dbManager = new DBManager(host, db, user, password);
    }
    
    public DBManager getDbManager(){
        return dbManager;
    }
        
    public Studente getStudent(String cf) throws CrudException {
        Studente s = new Studente();
        try { 
            PreparedStatement selectOperation = dbManager.getConnection().prepareStatement(selectSQL);
            selectOperation.setString(1, cf);
            ResultSet rs = selectOperation.executeQuery();
            while(rs.next()){
                s.setNome(rs.getString("nome"));
                s.setCognome(rs.getString("cognome"));
                s.setEmail(rs.getString("email"));
                s.setResidenza(rs.getString("residenza"));
                s.setCf(rs.getString("cf"));
                s.setTelefono(rs.getString("telefono"));
                s.setQualifica(rs.getString("qualifica"));
                return s;
            }
        } catch(Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean CreateStudent(Studente s) throws CrudException {    
        try {
            PreparedStatement insertOperation = dbManager.getConnection().prepareStatement(insertSQL);
            insertOperation.setString(1, s.getNome());
            insertOperation.setString(2, s.getCognome());
            insertOperation.setString(3, s.getEmail());
            insertOperation.setString(4, s.getResidenza());
            insertOperation.setString(5, s.getCf());
            insertOperation.setString(6, s.getTelefono());
            insertOperation.setString(7, s.getQualifica());
            insertOperation.executeUpdate();
            return true;
        } catch(Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean DeleteStudent(Studente s) throws CrudException {            
        try {
            PreparedStatement insertOperation = dbManager.getConnection().prepareStatement(deleteSQL);
            insertOperation.setString(1, s.getCf());
            insertOperation.executeUpdate();
            return true;
        } catch(Exception ex) {
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
   
    
}
