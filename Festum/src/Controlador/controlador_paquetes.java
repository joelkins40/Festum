package Controlador;


import Modelo.modelo_notas;
import Modelo.modelo_paquetes;
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
public class controlador_paquetes extends modelo_paquetes{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
        public DefaultTableModel cargar_tabla_notas(String valor)  {
        
         String [] titulos={"CLAVE_PAQUETE","NOMBRE DEL PAQUETE","PRECIO GENERAL","PRECIO ESPECIAL"};
            String [] registros=new String[4];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultarNotas(valor);
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
               
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_paquetes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
        
        public DefaultTableModel cargar_registros_notas(String valor)  {
        
         String [] titulos={"ID","CLAVE","DESCRIPCION","COLOR","EXISTENCIA","CANTIDAD","PRECIO","TOTAL"};
            String [] registros=new String[8];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultar_registros_NOTA(valor);
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString("id_producto");
                registros[1] = rs.getString("Clave_producto");
                registros[2] = rs.getString("descripcion_producto");
                registros[3] = rs.getString("color");
                registros[4] = rs.getString("cantidad");
               registros[5] = rs.getString(2);
                registros[6] = rs.getString("precio");
                registros[7] = rs.getString(3);
              
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_paquetes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

    
        

    
    
    
    
    
    public void guardar_nota (String vaccion,String id_folio,String nombre,String precio,String precio_especial){
        if(vaccion=="I"){
            inserta_paquete(id_folio, nombre, precio, precio_especial);
    
        }
        if(vaccion=="M"){
     //       modificar_notas(id_producto, cons, vaccion, cons, 0, a, Double.NaN, estado);
                    }}
    
    
    
      public void guardar_nota_prod (String vaccion,String id_paquete,String total_producto,String total_cantidaddecimal,String id_producto){
        if(vaccion=="I"){
            inserta_paquetes_prod(id_paquete, total_producto, total_cantidaddecimal, id_producto);
        }
        if(vaccion=="M"){
           // modificar_producto(id_producto, clave, descripcion, color, cantidad, fecha, precio, categoria);
                    }}
      
    
    public void eliminar_produc (String id)  {
        eliminar_paquete(id);
    }
  
    
        public   ResultSet consulta_folioo(){
              ResultSet rs=consultar_folio();
        return rs;
        }
        
         public   ResultSet consultar_nota_folio(String folio){
              ResultSet rs=consultarnotadefinida(folio);
            return rs;
               }

}
