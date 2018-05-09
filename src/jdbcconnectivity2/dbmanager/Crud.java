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
    private static final String deleteSQL = "DELETE FROM studente WHERE nome = ? AND cognome = ?";
    
    private DBManager dbManager = null;
    
    public Crud(String host, String db, String user, String password){
        dbManager = new DBManager(host, db, user, password);
    }
    
    public DBManager getDbManager(){
        return dbManager;
    }
        
    public void CreateStudent(Studente s) throws CrudException {    
        try{
            PreparedStatement insertOperation = dbManager.getConnection().prepareStatement(insertSQL);
            insertOperation.setString(1, s.getNome());
            insertOperation.setString(2, s.getCognome());
            insertOperation.setString(3, s.getEmail());
            insertOperation.setString(4, s.getResidenza());
            insertOperation.setString(5, s.getCf());
            insertOperation.setString(6, s.getTelefono());
            insertOperation.setString(7, s.getQualifica());
            insertOperation.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void DeleteStudent(String nome, String cognome) throws CrudException {            
        try{
            PreparedStatement insertOperation = dbManager.getConnection().prepareStatement(deleteSQL);
            insertOperation.setString(1, nome);
            insertOperation.setString(2, cognome);
            insertOperation.executeUpdate();
        }catch(Exception ex){
            Logger.getLogger(Crud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
      
    /* 
            String insertSQL = "UPDATE studente SET nome = ? WHERE email = ?";
            
            PreparedStatement insertOperation = conn.prepareStatement(insertSQL);
            insertOperation.setString(1, "Verdi");
            insertOperation.setString(2, "mario.rossi@live.it");
            
            insertOperation.executeUpdate(); 
    */
    
    
}
