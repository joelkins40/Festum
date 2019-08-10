/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import REPORTES.categoria;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author christian
 */
public class BD {

    protected Connection con;
    protected Statement pst = null;

    
      public BD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost/festum", "root", "");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

   
    
    

    protected ResultSet consultar(String sql) {
            try {
            pst = con.createStatement();
            
            ResultSet rs = pst.executeQuery(sql);
            return rs;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
     
            
    
    protected void insertar(String tabla, String campos, String valores){
        try {
            pst = con.createStatement();
         
            pst.executeUpdate("insert into "+ tabla+ " (" + campos+") values("+ valores+")");
        } catch (SQLException ex) {
            System.out.println(ex);
         
        }
    }
     protected void actualizar(String tabla, String campos, String condicion){
        try {
            pst = con.createStatement();
          
            pst.executeUpdate("UPDATE "+tabla+ " SET "+ campos+" WHERE "+condicion);
       
        } catch (SQLException ex) {
            System.out.println(ex);
         
        }
    }
      protected void eliminar(String tabla, String condicion){
        try {
            pst = con.createStatement();
           
            pst.executeUpdate("DELETE FROM "+tabla+ " WHERE "+condicion);
       
        } catch (SQLException ex) {
            System.out.println(ex);
         
        }
    }
          protected void generarnota(String fecha1,String fecha2){
        try{
    
    
   JasperReport jr=(JasperReport) JRLoader.loadObject(categoria.class.getResource("/REPORTES/Reporte.jasper"));
  Map  parametro = new HashMap<String,String>();
  
  parametro.put("Fecha1",fecha1);
  parametro.put("fecha2",fecha2);
  
  
  
JasperPrint jp=JasperFillManager.fillReport(jr,parametro,con);
JasperViewer jv =new JasperViewer(jp,false);
jv.show();

}catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error"+e);
}

    }
 protected void generarnota(String categoria){
        try{
    
    
   JasperReport jr=(JasperReport) JRLoader.loadObject(categoria.class.getResource("/REPORTES/reportecategoria.jasper"));
  Map  parametro = new HashMap<String,String>();
  
  parametro.put("id",categoria);
 
  
  
  
JasperPrint jp=JasperFillManager.fillReport(jr,parametro,con);
JasperViewer jv =new JasperViewer(jp,false);
jv.show();

}catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error"+e);
}

    }
                protected void notas_remision(String folio){
        try{
    
    
   JasperReport jr=(JasperReport) JRLoader.loadObject(categoria.class.getResource("/REPORTES/NOTAS.jasper"));
  Map  parametro = new HashMap<String,String>();
  
 
   parametro.put("folio",folio);
  
  
 
  
JasperPrint jp=JasperFillManager.fillReport(jr,parametro,con);
JasperViewer jv =new JasperViewer(jp,false);
jv.show();

}catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error"+e);
}

    }     
           protected void generar_productos(){
        try{
    
    
   JasperReport jr=(JasperReport) JRLoader.loadObject(categoria.class.getResource("/REPORTES/allproduct.jasper"));
  Map  parametro = new HashMap<String,String>();
  
 
  
  
  
JasperPrint jp=JasperFillManager.fillReport(jr,parametro,con);
JasperViewer jv =new JasperViewer(jp,false);
jv.show();

}catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error"+e);
}

    }
          
}
