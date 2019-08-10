/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import conexion.BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lavaexport
 */
public class modelo_user extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarusuario()  {
         String cons="Select * from user";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
   protected ResultSet  consultar_user(String clave)  {
         String cons="Select * from user where clave='"+clave+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
   }
   
   protected ResultSet  consultar_user_nombre(String clave)  {
         String cons="Select * from user where nombre='"+clave+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
   }
    
   protected void eliminar_user ( String clave){
               
       String tabla= "user";
       String valores="clave='"+clave+"'"; 
      
        eliminar(tabla, valores);
   }




  
    
    
    public void inserta_user(String clave,String nombre,String contrasena,String estatus,String tipo){
        String tabla="user";
                String valores="clave,nombre,contraseña,estatus,t_user";
                String datos="'"+clave+"','"+nombre+"','"+contrasena+"','"+estatus+"','"+tipo+"'";
                insertar(tabla, valores, datos);
    JOptionPane.showMessageDialog(null, "Usuario Registrado Con Exito");
    }

    public void modificar_user (String clave,String nombre,String contrasena,String estatus,String tipo){
               
       String tabla= "user";
       String valores="nombre='"+nombre+"',contraseña='"+contrasena+"',estatus='"+estatus+"',t_user='"+tipo+"'";
       String condicion = "clave='"+clave+"'";        
        actualizar(tabla, valores, condicion);
JOptionPane.showMessageDialog(null, "Usuario Modificado Con Exito");
    }

    
 

        
}
