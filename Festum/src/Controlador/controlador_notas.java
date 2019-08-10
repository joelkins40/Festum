package Controlador;


import Modelo.modelo_notas;
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
public class controlador_notas extends modelo_notas{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
        public DefaultTableModel cargar_tabla_notas()  {
        
         String [] titulos={"FOLIO","Nombre Evento","CLIENTE","PRESTAMO","DEVOLUCION","ESTADO","TOTAL","ESTATUS"};
            String [] registros=new String[9];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultarNotas();
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString(1);
                registros[1] = rs.getString("NombreEvento");
                registros[2] = rs.getString(10);
                registros[3] = rs.getString(2);
                registros[4] = rs.getString(3);
                registros[5] = rs.getString(4);
                registros[6] = rs.getString(5);
                 if("P".equals(rs.getString(7))){
                              registros[7] ="Pendiente";
                           }else if("A".equals(rs.getString(7))){
                               registros[7] ="Aprobada";
                           }else if("Q".equals(rs.getString(7))){
                               registros[7] ="Recolectada";
                           }else if("C".equals(rs.getString(7))){
                               registros[7] ="Cancelada";
                           }
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_notas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
        
        public DefaultTableModel cargar_registros_notas(String valor)  {
        
         String [] titulos={"Id","Clave","Descripcion","Exist","Cant","Precio","Total"};
            String [] registros=new String[8];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultar_registros_NOTA(valor);
        try {
            while (rs.next()) {
                
            registros[0] = rs.getString(1);
                registros[1] = rs.getString("Clave_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString(13);
               registros[4] = rs.getString(2);
               registros[5] = rs.getString(6);
                registros[6] = rs.getString(3);
              
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_notas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
        

   
    
        

    
    
    
    public void eliminarregustrstabla(String as){
        eliminar_nota_producto(as);
    }
    
    public void guardar_nota (String vaccion,String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente,String color,String obs){
        if(vaccion=="I"){
            inserta_nota(id_folio,fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente,color,obs);
    
        }
        if(vaccion=="M"){
            modificar_notas(id_folio, fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente,color,obs);
                    }}
    
    
     public void guardar_cotizac (String vaccion,String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente,String obs){
        if(vaccion=="I"){
            inserta_cotizacion(id_folio, fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente,obs);
        }
        if(vaccion=="M"){
            Modificar_Cotizacion_notas(id_folio, fecha_prestamo, fecha_devolucion, total_nota, id_cliente,obs);
                    }
     }
     
    
      public void guardar_nota_prod (String vaccion,String id_f,String id_producto,String cantidad,String total_cantidad,String folio,String precio,String tprecio,String descripcion_producto){
        if(vaccion=="I"){
            inserta_notas_prod(id_producto, cantidad, total_cantidad, folio, precio, tprecio,descripcion_producto);
        }
        if(vaccion=="M"){
            modificar_notas_prod(id_f, id_producto, cantidad, total_cantidad, precio, tprecio, descripcion_producto);
        } 
}      
      public void guardar_nota_cotizacion (String vaccion,String cond,String id_producto,String cantidad,String total_cantidad,String folio,String precio,String t_precio,String descripcion_producto){
        if(vaccion=="I"){
            inserta_cotizacion_producto(id_producto, cantidad, total_cantidad, folio, precio, t_precio,descripcion_producto);
            
        }
        if(vaccion=="M"){
            modificar_cotizacion_prod(cond, id_producto, cantidad, total_cantidad, precio, t_precio, descripcion_producto);
                    }
      }
    
    public void eliminar_nota (String id)  {
        eliminar_prod(id);
        eliminar_nota_producto(id);
    }
    public void eliminar_cotizacion (String id)  {
        eliminar_prod(id);
        eliminar_nota_producto(id);
    }
    public void Eliminar_registro_nota(String id){
        eliminar_datos_en_producto(id);
        
    }
  
    
        public   ResultSet consulta_folioo(){
              ResultSet rs=consultar_folio();
        return rs;
        }
        public   ResultSet consulta_folio_cotizacion(){
              ResultSet rs=consultar_folio_cotiza();
        return rs;
        }
        
             public   ResultSet consultar_categorias(){
              ResultSet rs=consultar_categor();
            return rs;
               }
 public   ResultSet consultar_cat_id(String id){
              ResultSet rs=consultar_desc_categoria(id);
            return rs;
               }
 
 public   ResultSet consultar_nota_folio(String folio){
              ResultSet rs=consultarnotadefinida(folio);
            return rs;
               }
public void cargar_nota_todosalv(String d){
    cargar_nota_remison(d);
}
}
