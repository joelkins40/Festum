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
public class modelo_paquetes extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarNotas(String valor)  {
         String cons="Select * from producto where tipo_ps='M'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_pro(String id)  {
         String cons="Select * from producto where clave_producto='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_folio()  {
         String cons="Select MAX(id_paquete) from paquetes";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}

protected ResultSet  consultar_registros_NOTA(String id)  {
         String cons="select * from paquetes_productos inner join producto on producto.id_producto=paquetes_productos.id_producto_nota  where id_paquete='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;       
}

  
    

protected  ResultSet consultarnotadefinida(String folio){
    String cons="Select * From paquetes where id_paquete='"+folio+"'";
    ResultSet rs=consultar(cons);
  return rs;
}
    



    public void inserta_paquete(String id_folio,String nombre,String precio,String precio_especial){
   
        String tabla="paquetes";
                String valores="id_paquete,nombre_paquete,precionormal,precio_especial";
                String datos="'"+id_folio+"','"+nombre+"','"+precio+"','"+precio_especial+"'";
                insertar(tabla, valores, datos);
    }
  
    
    public void inserta_paquetes_prod(String id_paquete,String total_producto,String total_cantidaddecimal,String id_producto){
   
        String tabla="paquetes_productos";
                String valores="id_paquete,total_producto,total_product,id_producto";
                String datos="'"+id_producto+"','"+total_producto+"','"+total_cantidaddecimal+"','"+id_producto+"'";
                insertar(tabla, valores, datos);
    
    }

    protected void eliminar_paquete ( String id_paquete){
               
       String tabla= "paquetes";
       String valores="id_paquete='"+id_paquete+"'"; 
      
        eliminar(tabla, valores);
    }

 

        
}
