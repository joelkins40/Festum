/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Lavaexport
 */
public class conexion {
    

    public static Connection GetConnection(){
        Connection conexion=null;
    try
    {
        Class.forName("com.mysql.jdbc.Driver");
   String servidor="jdbc:mysql://localhost/lavaexport";     
   String usuarioDb="root";
   String passwordDb="";
   conexion=DriverManager.getConnection(servidor,usuarioDb,passwordDb);
   
    }
    catch(ClassNotFoundException ex)
    {
        JOptionPane.showMessageDialog(null, ex,"Error 1 en la conexion con la BD"+ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        conexion=null;  
        
    }
    catch(SQLException ex)
    {
        JOptionPane.showMessageDialog(null, ex,"Error 2 en la conexion con la BD"+ex.getMessage(),JOptionPane.ERROR_MESSAGE);
        conexion=null;  
    }
    finally
    {
        return conexion;
    }
    }   
    
    
    
    
    
    
}
