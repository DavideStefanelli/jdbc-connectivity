package jdbcconnectivity2;

import java.util.logging.Level;
import java.util.logging.Logger;
import jdbcconnectivity2.dbmanager.Crud;
import jdbcconnectivity2.dbmanager.CrudException;

import entities.Studente;

public class JdbcConnectivity2 {

    static Crud crud = null;
    
    public static void main(String[] args) {
        
        crud = new Crud("localhost", "corso", "root", "");
        
        try {
            Studente s = new Studente();
            s.setNome("Mario");
            s.setCognome("Rossi");
            s.setEmail("mario.rossi@live.it");
            s.setResidenza("Via dei Radiotelegrafisti 15");
            s.setCf("ASFASD123001");
            s.setTelefono("10001399");
            s.setQualifica("Diploma"); 
            crud.CreateStudent(s);
            
            Studente s2 = crud.getStudent("ASFASD123001");
            System.out.println(s2.getEmail());
            
          
        } catch (CrudException ex) {
            Logger.getLogger(JdbcConnectivity2.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            crud.getDbManager().closeConnection();
        }
        
        
        
    }
    
}
