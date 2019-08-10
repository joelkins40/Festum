/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import conexion.BD;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lavaexport
 */
public class modelo_recoleccion extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarNotas(String valor)  {
         String cons="Select * from NOTA where folio like '%"+valor+"%' and estatus_recoleccion='A'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_pro(String id)  {
         String cons="Select * from producto where id_producto='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    
 
public void modificar_nota_ACTIVA (String id_folio){
               
       String tabla= "nota";
       String valores="estatus_recoleccion='Q'";
       String condicion = "folio='"+id_folio+"'";        
        actualizar(tabla, valores, condicion);

    }
    
protected ResultSet  consultar_registros_NOTA(String id)  {
         String cons="select * from notas_productos inner join producto on producto.id_producto=notas_productos.id_producto_nota  where folio_nota='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;       
}

    protected void eliminar_prod ( String id_producto){
               
       String tabla= "producto";
       String valores="id_producto='"+id_producto+"'"; 
      
        eliminar(tabla, valores);
    }

    protected  ResultSet consultarnotadefinida(String folio){
    String cons="Select * From nota where folio='"+folio+"'";
    ResultSet rs=consultar(cons);
  return rs;
}
 

        
}
