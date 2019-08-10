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
public class modelo_notas extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarNotas()  {
         String cons="Select * from NOTA,cliente where nota.id_cliente=cliente.id_cliente";
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
         String cons="Select MAX(folio) from NOTA";
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
         String cons="select * from notas_productos inner join producto on producto.id_producto=notas_productos.id_producto_nota  where folio_nota='"+id+"'";
        ResultSet rs=consultar(cons);
        return rs;       
}

  
    
protected ResultSet  consultar_categor()  {
         String cons="Select * from categoria";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
protected  ResultSet consultarnotadefinida(String folio){
    String cons="Select * From nota where folio='"+folio+"'";
    ResultSet rs=consultar(cons);
  return rs;
}
    



    public void inserta_nota(String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente,String color,String obs){
   
        String tabla="nota";
                String valores="folio,fecha_prestamo,fecha_devolucion,estado,total_nota,id_cliente,estatus_recoleccion,NombreEvento,obs";
                String datos="'"+id_folio+"','"+fecha_prestamo+"','"+fecha_devolucion+"','"+estado+"','"+total_nota+"','"+id_cliente+"','"+"P"+"','"+color+"','"+obs+"'";
                insertar(tabla, valores, datos);
                JOptionPane.showMessageDialog(null, "Nota Guardada");
    }
     public void inserta_cotizacion(String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente,String obs){
   
    String tabla="cotizaciones";
                String valores="folio,fecha_prestamo,fecha_devolucion,total_nota,id_cliente,obs";
                String datos="'"+id_folio+"','"+fecha_prestamo+"','"+fecha_devolucion+"','"+total_nota+"','"+id_cliente+"',obs='"+obs+"'";
                insertar(tabla, valores, datos);
    JOptionPane.showMessageDialog(null, "Cotizacion Guardada");
     }
  
    
    public void inserta_notas_prod(String id_producto,String cantidad,String total_cantidad,String folio,String precio,String tprecio,String descripcion_producto){
   
        String tabla="notas_productos";
                String valores="id_producto_nota,cantidad,total_producto,folio_nota,precio,t_precio,descripcion_producto";
                String datos="'"+id_producto+"',"+cantidad+",'"+total_cantidad+"','"+folio+"','"+precio+"','"+tprecio+"','"+descripcion_producto+"'";
                insertar(tabla, valores, datos);
    
    }
    
    public void modificar_cotizacion_prod(String id_notas_producto,String id_producto,String cantidad,String total_cantidad,String precio,String tprecio,String descripcion_producto){
   
        String tabla="notas_cotizaciones";
                String valores="id_producto_nota='"+id_producto+"',cantidad='"+cantidad+"',total_producto='"+total_cantidad+"',precio='"+precio+"',t_precio='"+tprecio+"',descripcion_producto='"+descripcion_producto+"'";
                String condicion="id_notas_cotizaciones='"+id_notas_producto+"'";
                actualizar(tabla, valores, condicion);
    
    }
public void modificar_notas_prod(String id_operacion,String producto,String cantidad,String total_cantidad,String precio,String tprecio,String descripcion_producto){
   
        String tabla="notas_productos";
                String valores="id_producto_nota='"+producto+"',cantidad='"+cantidad+"',total_producto='"+total_cantidad+"',precio='"+precio+"',t_precio='"+tprecio+"',descripcion_producto='"+descripcion_producto+"'";
                String condicion="id_notas_producto='"+id_operacion+"'";
                actualizar(tabla, valores, condicion);
    
    }

    
    public void inserta_cotizacion_producto(String id_producto,String cantidad,String total_cantidad,String folio,String precio,String tprecio,String descripcion_producto){
   
        String tabla="notas_cotizaciones";
                String valores="id_producto_nota,cantidad,total_producto,folio_cotizacion,precio,t_precio,descripcion_producto";
                String datos="'"+id_producto+"','"+cantidad+"','"+total_cantidad+"','"+folio+"','"+precio+"','"+tprecio+"','"+descripcion_producto+"'";
                insertar(tabla, valores, datos);
    
    }

    public void modificar_notas (String id_nota,String fecha_prestamo,String fecha_devolucion,String vestado,String total_nota,String id_cliente,String color,String obs){
               
       String tabla= "nota";
       String valores="fecha_prestamo='"+fecha_prestamo+"',fecha_devolucion='"+fecha_devolucion+"',total_nota='"+total_nota+"',id_cliente='"+id_cliente+"',nombreEvento='"+color+"',obs='"+obs+"',estatus_recoleccion='P'";
       String condicion = "folio='"+id_nota+"'";        
        actualizar(tabla, valores, condicion);
        JOptionPane.showMessageDialog(null, "Nota modificada con Exito");

    }
    public void modificarCancelar_NOTA (String id_folio,String Valor){
               
       String tabla= "nota";
       String valores="estatus_recoleccion='"+Valor+"',total_nota=0.0";
       String condicion = "folio='"+id_folio+"'";        
        actualizar(tabla, valores, condicion);

}
      public void Modificar_Cotizacion_notas (String id_nota,String fecha_prestamo,String fecha_devolucion,String total_nota,String id_cliente,String obs){
               
       String tabla= "cotizaciones";
       String valores="fecha_prestamo='"+fecha_prestamo+"',fecha_devolucion='"+fecha_devolucion+"',total_nota='"+total_nota+"',id_cliente='"+id_cliente+"',obs='"+obs+"'";
       String condicion = "folio='"+id_nota+"'";        
        actualizar(tabla, valores, condicion);
        JOptionPane.showMessageDialog(null, "Cotizacion modificada con Exito");

    }
    public void modificar_inventario_salidas (String id_producto,String inventario_cantidad){
       String tabla= "producto";
       String valores="cantidad='"+inventario_cantidad+"'";
       String condicion = "id_producto='"+id_producto+"'";        
        actualizar(tabla, valores, condicion);
   }
    

    protected void eliminar_prod ( String id_producto){
               
       String tabla= "nota";
       String valores="folio='"+id_producto+"'"; 
       eliminar(tabla, valores);
        eliminar_nota_producto(id_producto);
        JOptionPane.showMessageDialog(null,"Registro Eliminado Con Exito" );
    }
    
     protected void eliminar_cotizacion ( String id_producto){
               
       String tabla= "cotizaciones";
       String valores="folio='"+id_producto+"'"; 
       eliminar(tabla, valores);
        eliminar_nota_producto(id_producto);
        JOptionPane.showMessageDialog(null,"Registro Eliminado Con Exito" );
    }
    protected void eliminar_nota_producto(String id){
        String tabla ="notas_productos";
        String valores ="folio_nota='"+id+"'";
        eliminar(tabla, valores);
        
    }
     protected void eliminar_notas_cotizaciones(String id){
        String tabla ="notas_cotizaciones";
        String valores ="folio_nota='"+id+"'";
        eliminar(tabla, valores);
        
    }
       protected void eliminar_registro_cotizaciones(String id){
        String tabla ="notas_cotizaciones";
        String valores ="id_notas_cotizaciones='"+id+"'";
        eliminar(tabla, valores);
        
    }
   protected void eliminar_datos_en_producto(String id){
        String tabla ="notas_productos";
        String valores ="id_notas_producto='"+id+"'";
        eliminar(tabla, valores);
        
    }
 protected void cargar_nota_remison(String id){
     notas_remision(id);
 }

        
}
