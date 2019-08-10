/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;



import Modelo.modelo_pendientes;
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
public class controlador_Pendientes extends modelo_pendientes{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
        public DefaultTableModel cargar_tabla_notas_pendeinte(String valor)  {
        
         String [] titulos={"FOLIO_NOTA","CLIENTE","FECHA PRESTAMO","FECHA DE DEVOLUCION","P-R-S","TOTAL"};
            String [] registros=new String[9];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultarNotas(valor);
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString(1);
                 registros[1] = rs.getString(10);
                registros[2] = rs.getString(2);
                registros[3] = rs.getString(3);
                           if("P".equals(rs.getString(7))){
                              registros[4] ="Pendiente";
                           }else if("A".equals(rs.getString(7))){
                               registros[4] ="Aprobada";
                           }else if("Q".equals(rs.getString(7))){
                               registros[4] ="Recolectada";
                           }
                         
                registros[5] = rs.getString(5);
               
              
          
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_Pendientes.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(controlador_Pendientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }

public void modificarN(String id_folio,String valor){
    modificar_nota_ACTIVA(id_folio,valor);
}
 public   ResultSet consultar_nota_folio(String folio){
              ResultSet rs=consultarnotadefinida(folio);
            return rs;
               }
  public ResultSet ConsultarNOTASfolioregistros(String nota){
     
        ResultSet rs = consultar_registros_NOTA(nota);
        return rs;
 }
}
        
        

   
    
        

    
    
    
    
    
   
    
   