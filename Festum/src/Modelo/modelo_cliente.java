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
public class modelo_cliente extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarcliente()  {
         String cons="Select * from cliente";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultarcli(String id)  {
         String cons="Select * from cliente where id_cliente='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultarMXi()  {
         String cons="Select MAX(id_cliente) from cliente";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    

  
    

    
    public void inserta_cliente(String id_cliente,String razon,String direccion,String n_e,String n_i,String colonia,String cp,String referencias, String telefono,String ciudad,String precio){
    
        String tabla="cliente";
                String valores="id_cliente,razon,direccion,n_e,n_i,colonia,cp,referencias,telefono,ciudad,precio";
                String datos="'"+id_cliente+"','"+razon+"','"+direccion+"','"+n_e+"','"+n_i+"','"+colonia+"','"+cp+"','"+referencias+"','"+telefono+"','"+ciudad+"','"+precio+"'";
                insertar(tabla, valores, datos);
               JOptionPane.showMessageDialog(null, "Cliente Registrado Con Exito");
    
    }

    public void modificar_cliente ( String id_cliente,String razon,String direccion,String n_e,String n_i,String colonia,String cp,String referencias,String telefono,String ciudad,String precio){
               
       String tabla= "cliente";
       String valores="razon='"+razon+"',direccion='"+direccion+"',n_e='"+n_e+"',n_i='"+n_i+"',colonia='"+colonia+"',cp='"+cp+"',referencias='"+referencias+"',telefono='"+telefono+"',ciudad='"+ciudad+"',precio='"+precio+"'";
       String condicion = "id_cliente='"+id_cliente+"'";        
        actualizar(tabla, valores, condicion);
        JOptionPane.showMessageDialog(null, "Cliente Modificado Con Exito");

    }

    protected void eliminar_cli ( String id_cliente){
               
       String tabla= "cliente";
       String valores="id_cliente='"+id_cliente+"'"; 
      
        eliminar(tabla, valores);
    }

 

        
}
