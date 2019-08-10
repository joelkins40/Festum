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
public class modelo_producto extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarproducto()  {
         String cons="Select * from producto ORDER BY clave_producto";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_pro(String id)  {
         String cons="Select * from producto where clave_producto='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  generar_clave(String prod,String cat)  {
         String cons="select Max(Clave_producto) from producto where categoria="+cat +"and TIPO_PS='"+cat+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}

protected ResultSet  consultar_pro_CLAVE(String id)  {
         String cons="Select * from producto where Clave_producto='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}

protected ResultSet  consultar_pro_descr(String id)  {
         String cons="Select * from producto where descripcion_producto='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    
protected ResultSet  consultar_id_categoria(String id)  {
         String cons="Select * from categoria where descripcion='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}

protected ResultSet  consultar_desc_categoria(String id)  {
         String cons="Select * from categoria where id_categoria='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}

  
    
protected ResultSet  consultar_categor()  {
         String cons="Select * from categoria";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    


    
    
    public void inserta_producto(String clave,String descripcion,String color,int cantidad,String fecha,Double precio,String categoria,String tipo,String precioC){
        String tabla="producto";
                String valores="clave_producto,nombre_producto,descripcion_producto,color,cantidad,fecha,precio,categoria,inventario,TIPO_PS,PRECIO_C";
                String datos="'"+clave+"','"+color+"','"+descripcion+"','"+color+"','"+cantidad+"','"+fecha+"','"+precio+"','"+categoria+"','"+cantidad+"','"+tipo+"','"+precioC+"'";
                insertar(tabla, valores, datos);
    JOptionPane.showMessageDialog(null, "Producto Ingresado Con Exito");
    }

    public void modificar_producto ( String id_producto,String clave,String descripcion,String color,int cantidad,String fecha,Double precio,String categoria,String tipo,String precioC){
               
       String tabla= "producto";
       String valores="nombre_producto='"+color+"',descripcion_producto='"+descripcion+"',clave_producto='"+clave+"',cantidad='"+cantidad+"',fecha='"+fecha+"',precio='"+precio+"',categoria='"+categoria+"',inventario='"
               +cantidad+"',TIPO_PS='"+tipo+"',PRECIO_C='"+precioC+"'";
       String condicion = "id_producto='"+id_producto+"'";        
        actualizar(tabla, valores, condicion);
        JOptionPane.showMessageDialog(null, "Producto Actualizado Con Exito");
    }

    protected void eliminar_prod ( String id_producto){
               
       String tabla= "producto";
       String valores="id_producto='"+id_producto+"'"; 
      
        eliminar(tabla, valores);
         JOptionPane.showMessageDialog(null, "Producto Eliminado Con Exito");
    }

 protected  void cargar_reportes(String accion,String id){
     if(accion=="T"){
     generar_productos();     
     }else{
         generarnota(id);
     }
    
 }

        
}
