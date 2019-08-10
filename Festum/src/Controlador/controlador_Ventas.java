/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.modelo_Ventas;
import Modelo.modelo_categoria;
import Modelo.modelo_cliente;
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
public class controlador_Ventas extends modelo_Ventas {

    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;

    public DefaultTableModel cargar_tabla_Ventas(String accion,String FE1, String FE2) {

        String[] titulos = {"FOLIO","CLIENTE", "FECHA DE PRESTAMO","FECHA DE DEVOLUCION","ESTADO","PRECIO DE LA NOTA"};
        String[] registros = new String[8];
        model = new DefaultTableModel(null, titulos);
        ResultSet rs=null;
if(accion=="T"){
        rs = consultarventas();
}else{
       rs = consultarventasFECHAS(FE1, FE2); 
}
  
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
                           }else if("C".equals(rs.getString(7))){
                               registros[4] ="Cancelada";
                           }
                
                registros[5] =rs.getString(5);
                

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
     public DefaultTableModel cargar_tabla_totaltota() {

        String[] titulos = {"CLIENTE", "NUMERO DE NOTAS","PRECIO"};
        String[] registros = new String[8];
        model = new DefaultTableModel(null, titulos);
        ResultSet rs=null;

        rs = consultarventasCLIENTES();
  
        try {
            while (rs.next()) {

                registros[0] = rs.getString(1);
                registros[1] = rs.getString(3);
                registros[2] = rs.getString(2);
               
                

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
  public DefaultTableModel cargar_tabla_Ventasxcliente(String cliente,String accion) {

        String[] titulos = {"FOLIO","CLIENTE", "FECHA DE PRESTAMO","FECHA DE DEVOLUCION","ESTADO","PRECIO DE LA NOTA"};
        String[] registros = new String[8];
        model = new DefaultTableModel(null, titulos);
        ResultSet rs=null;
if(accion=="T"){
        rs = consultarventas();
}else{
       rs = consultarventasclientes(cliente); 
}
  
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
                           }else if("C".equals(rs.getString(7))){
                               registros[4] ="Cancelada";
                           }
                
                registros[5] =rs.getString(5);
                

                model.addRow(registros);
            }
        } catch (SQLException ex) {
            Logger.getLogger(modelo_categoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return model;

    }
    public void guardar_cliente(String vaccion, String id, String descripcion) {
        if (vaccion == "I") {
            inserta_categoria(descripcion);
        }
        if (vaccion == "M") {
            modificar_cliente(id, descripcion);
        }
    }

    public void eliminar_cliente(String id) {
        eliminar_cat(id);
    }
public void reporte (String f1,String f2){
    reporte_fechas(f1, f2);
}
    
}
