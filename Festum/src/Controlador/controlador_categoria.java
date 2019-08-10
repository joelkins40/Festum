/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modelo_categoria;
import Modelo.modelo_cliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lavaexport
 */
public class controlador_categoria extends modelo_categoria{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
        public DefaultTableModel cargar_tabla_categoria(String valor)  {
        
         String [] titulos={"Id","Descripcion"};
            String [] registros=new String[2];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultarcategoria();
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                 
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

   
    
        

    
    
    
    
    
    public void guardar_cliente (String vaccion, String id,String descripcion){
        if(vaccion=="I"){
            inserta_categoria(descripcion);
                    }
        if(vaccion=="M"){
            modificar_cliente(id, descripcion);
        }}
    
    public void eliminar_cliente (String id)  {
        eliminar_cat(id);
    }
  
    
        public  ResultSet consultarclient(String id) throws SQLException{
              ResultSet rs=consultarcat(id);
        return rs;
           

        }
}
