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
public class modelo_cotz extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarcotizacion()  {
         String cons="Select * from cotizaciones,cliente where cotizaciones.id_cliente=cliente.id_cliente";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_pro(String id)  {
         String cons="Select * from producto where id_producto='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    protected void report(String f1,String f2){
    generarnota(f1,f2);
}
protected ResultSet  consultar_folio()  {
         String cons="Select MAX(folio) from cotizaciones";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_folio_cotiza()  {
         String cons="Select MAX(folio) from cotizaciones";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}

protected ResultSet  consultar_desc_categoria(String id)  {
         String cons="Select * from categoria where id_categoria='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected ResultSet  consultar_registros_NOTA(String id){
         String cons="select * from notas_cotizaciones inner join producto on producto.id_producto=notas_cotizaciones.id_producto_nota  where folio_cotizacion='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;       
}

  
    
protected ResultSet  consultar_categor()  {
         String cons="Select * from categoria";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected  ResultSet consultarnotadefinida(String folio){
    String cons="Select * From cotizaciones where folio='"+folio+"'";
    ResultSet rs=consultar(cons);
  return rs;
}
    



    public void inserta_nota(String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente){
   
        String tabla="nota";
                String valores="folio,fecha_prestamo,fecha_devolucion,estado,total_nota,id_cliente,estatus_recoleccion";
                String datos="'"+id_folio+"','"+fecha_prestamo+"','"+fecha_devolucion+"','"+estado+"','"+total_nota+"','"+id_cliente+"','"+"P"+"'";
                insertar(tabla, valores, datos);
                JOptionPane.showMessageDialog(null, "Nota Guardada");
    }
     public void inserta_cotizacion(String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente){
   
    String tabla="cotizaciones";
                String valores="folio,fecha_prestamo,fecha_devolucion,total_nota,id_cliente";
                String datos="'"+id_folio+"','"+fecha_prestamo+"','"+fecha_devolucion+"','"+total_nota+"','"+id_cliente+"'";
                insertar(tabla, valores, datos);
    JOptionPane.showMessageDialog(null, "Cotizacion Guardada");
     }
  
    
    public void inserta_notas_prod(String id_producto,String cantidad,String total_cantidad,String folio,String precio,String tprecio){
   
        String tabla="notas_productos";
                String valores="id_producto_nota,cantidad,total_producto,folio_nota,precio,t_precio";
                String datos="'"+id_producto+"','"+cantidad+"','"+total_cantidad+"','"+folio+"','"+precio+"','"+tprecio+"'";
                insertar(tabla, valores, datos);
    
    }
    public void inserta_cotizacion_producto(String id_producto,String cantidad,String total_cantidad,String folio,String precio,String tprecio){
   
        String tabla="notas_cotizaciones";
                String valores="id_producto_nota,cantidad,total_producto,folio_cotizacion,precio,t_precio";
                String datos="'"+id_producto+"','"+cantidad+"','"+total_cantidad+"','"+folio+"','"+precio+"','"+tprecio+"'";
                insertar(tabla, valores, datos);
    
    }

    public void modificar_notas (String id_nota,String fecha_prestamo,String fecha_devolucion,String vestado,String total_nota,String id_cliente){
               
       String tabla= "nota";
       String valores="fecha_prestamo='"+fecha_prestamo+"',fecha_devolucion='"+fecha_devolucion+"',total_nota='"+total_nota+"',id_cliente='"+id_cliente+"'";
       String condicion = "folio='"+id_nota+"'";        
        actualizar(tabla, valores, condicion);
        JOptionPane.showMessageDialog(null, "La Nota ha sido modificada");

    }
    public void modificar_inventario_salidas (String id_producto,String inventario_cantidad){
       String tabla= "producto";
       String valores="cantidad='"+inventario_cantidad+"'";
       String condicion = "id_producto='"+id_producto+"'";        
        actualizar(tabla, valores, condicion);
   }
    

    protected void eliminar_prod ( String id_producto){
               
       String tabla= "cotizaciones";
       String valores="folio='"+id_producto+"'"; 
       eliminar(tabla, valores);
        eliminar_nota_producto(id_producto);
        JOptionPane.showMessageDialog(null,"Registro Eliminado Con Exito" );
    }
    
      protected void eliminar_datos_en_producto(String id){
        String tabla ="notas_cotizaciones";
        String valores ="id_notas_cotizaciones='"+id+"'";
        eliminar(tabla, valores);
        
    }
    protected void eliminar_nota_producto(String id){
        String tabla ="notas_cotizaciones";
        String valores ="folio_cotizacion='"+id+"'";
        eliminar(tabla, valores);
        
    }

 

        
}
