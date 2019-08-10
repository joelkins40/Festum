/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import conexion.BDCP;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lavaexport
 */
public class Modelo_CP extends BDCP{
        
        
     
    String cons = "";
    String a = "";


    protected ResultSet  consultarcat(String id)  {
         String cons="Select * from codigospostales where codigopostal='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
       protected ResultSet  consultar_estados()  {
         String cons="SELECT DISTINCT(ESTADO) FROM codigospostales ORDER BY ESTADO";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
              protected ResultSet  consultar_Municipio(String estado)  {
         String cons="SELECT DISTINCT(Municipio) FROM codigospostales where estado='"+estado+"' ORDER BY Municipio";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
  
                           protected ResultSet  consultar_Colonia(String estado,String municipio)  {
                               
         String cons="SELECT * FROM codigospostales where estado='"+estado+"' and municipio='"+municipio+"' ORDER BY colonia";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
}
    
