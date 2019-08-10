/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modelo_cliente;
import Modelo.modelo_producto;
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
public class controlador_producto extends modelo_producto{
   
    
   String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
    
        public DefaultTableModel cargar_tabla_Producto(String valor)  {
        
         String [] titulos={"Codigo","Nombre","Descripcion","Cantidad","TIPO"};
            String [] registros=new String[9];
            model=new DefaultTableModel(null,titulos);
          
        
        ResultSet rs = consultarproducto();
        try {
            while (rs.next()) {
                
                registros[0] = rs.getString(2);
                registros[1] = rs.getString(12);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(5);
                if("P".equals(rs.getString(10))){
                registros[4] ="PRODUCTO";
                }else if("S".equals(rs.getString(10))){
                  registros[4] ="SERVICIO";   
                }else{
                     registros[4] ="";
                }
                
               
                
                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(controlador_producto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
        
        

   
    
        

    
    
    
    
    
    public void guardar_producto (String vaccion, String id_producto,String clave,String descripcion,String color,int cantidad,String fecha,Double precio,String categoria,String tipo,String precioC){
        if(vaccion=="I"){
            inserta_producto(clave, descripcion, color, cantidad, fecha, precio, categoria, tipo, precioC);
        }
        if(vaccion=="M"){
            modificar_producto(id_producto, clave, descripcion, color, cantidad, fecha, precio, categoria, tipo, precioC);
                    }}
    
    public void eliminar_produc (String id)  {
        eliminar_prod(id);
    }
  
    
        public   ResultSet consultarproduct(String id){
              ResultSet rs=consultar_pro(id);
        return rs;
        }
        
        public   ResultSet generar(String prod,String cat){
              ResultSet rs=generar_clave(prod, cat);
        return rs;
        }
        public   ResultSet consultarproduct_clave(String id){
              ResultSet rs=consultar_pro_CLAVE(id);
        return rs;
        }
           public   ResultSet consultar__pr(){
              ResultSet rs=consultarproducto();
            return rs;
               }
           
           
           
           public   ResultSet consutar_product_descripcion(String valor){
              ResultSet rs=consultar_pro_descr(valor);
            return rs;
               }
           public   ResultSet consultar_idcategoria(String id){
              ResultSet rs=consultar_id_categoria(id);
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
public void cargar_reportes_todos(String accion,String id){
    cargar_reportes(accion, id);
}
}
