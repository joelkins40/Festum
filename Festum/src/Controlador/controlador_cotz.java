package Controlador;


import Modelo.modelo_cotz;
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
public class controlador_cotz extends modelo_cotz{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
        public DefaultTableModel cargar_tabla_notas()  {
        
         String [] titulos={"FOLIO_NOTA","CLIENTE","FECHA DE PRESTAMO","FECHA DEVOLUCION","TOTAL"};
            String [] registros=new String[6];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultarcotizacion();
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(7);
                registros[2] = rs.getString(2);
                registros[3] = rs.getString(3);
                registros[4] = rs.getString(4);
            
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_cotz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
        
        public DefaultTableModel cargar_registros_notas(String valor)  {
        
         String [] titulos={"ID","CLAVE","DESCRIPCION","EXISTENCIA","CANTIDAD","PRECIO","TOTAL"};
            String [] registros=new String[8];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultar_registros_NOTA(valor);
        try {
            while (rs.next()) {
                
            registros[0] = rs.getString(1);
                registros[1] = rs.getString("Clave_producto");
                registros[2] = rs.getString("descripcion_producto");
             
                registros[3] = rs.getString(12);
                  registros[4] = rs.getString(7);
               registros[5] = rs.getString(4);
               registros[6] = rs.getString(5);
              
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_cotz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
        

   
    
        

    
    
    
    public void eliminarregustrstabla(String as){
        eliminar_nota_producto(as);
    }
    
    public void guardar_nota (String vaccion,String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente){
        if(vaccion=="I"){
            inserta_nota(id_folio,fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente);
    
        }
        if(vaccion=="M"){
            modificar_notas(id_folio, fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente);
                    }}
    
    
     public void guardar_cotizac (String vaccion,String id_folio,String fecha_prestamo,String fecha_devolucion,String estado,String total_nota,String id_cliente){
        if(vaccion=="I"){
            inserta_nota(id_folio, fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente);
                    }
        if(vaccion=="M"){
            modificar_notas(id_folio, fecha_prestamo, fecha_devolucion, estado, total_nota, id_cliente);
                    }
     }
     
    
      public void guardar_nota_prod (String vaccion,String id_producto,String cantidad,String total_cantidad,String folio,String existencia,String precio,String t_precio){
        
            
        
}      
    
    public void eliminar_nota (String id)  {
        eliminar_prod(id);
    }
    
  
    
        public   ResultSet consulta_folioo(){
              ResultSet rs=consultar_folio();
        return rs;
        }
        public   ResultSet consulta_folio_cotizacion(){
              ResultSet rs=consultar_folio_cotiza();
        return rs;
        }
            public void Eliminar_registro_nota(String id){
        eliminar_datos_en_producto(id);
        
    }
 
 public   ResultSet consultar_nota_folio(String folio){
              ResultSet rs=consultarnotadefinida(folio);
            return rs;
               }

}
