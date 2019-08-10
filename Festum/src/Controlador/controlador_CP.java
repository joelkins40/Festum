/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.Modelo_CP;
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
public class controlador_CP extends Modelo_CP{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
       
   
    
        public DefaultTableModel cargar_tabla_cliente(String estado,String Municipio) {

        String[] titulos = {"Estado", "Municipio", "Colonia", "CP"};
        String[] registros = new String[4];
         String[] registros2 = new String[4];
       
        model = new DefaultTableModel(null, titulos);
       
        ResultSet rs = consultar_Colonia(estado, Municipio);
        try {
            while (rs.next()) {
            
            String dias = rs.getString("colonia");
            String diaArray[] = dias.split(";");

            for (String dia : diaArray) {
                registros[0] = rs.getString(4);
                    registros[1] = rs.getString(3);
                    registros[2] = dia.toUpperCase();
                    registros[3] =  rs.getString(1);
                   
                
                model.addRow(registros); 
            }
            
            
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo_cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

    
    
    
    
    
    
    
     public  ResultSet consultar_cpp(String id){
              ResultSet rs=consultarcat(id);
              return rs;
         }
      public  ResultSet consultar_estados_C(){
              ResultSet rs=consultar_estados();
              return rs;
         }
       public  ResultSet consultar_Municipio_c(String estado){
              ResultSet rs=consultar_Municipio(estado);
              
              return rs;
         }
        public  ResultSet consultar_colonias(String estado,String municipio){
              ResultSet rs=consultar_Colonia(estado, municipio);
              return rs;
         }
        
         
}
