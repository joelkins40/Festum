/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import REPORTES.categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author JOEL
 */
public class BD_SQL {
        protected Connection con;
    protected Statement pst = null;

       public BD_SQL() {
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
           JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }
    
     
            
    
    protected void insertar(String tabla, String campos, String valores){
        try {
            pst = con.createStatement();
         
            pst.executeUpdate("insert into "+ tabla+ " (" + campos+") values("+ valores+")");
       JOptionPane.showMessageDialog(null, "Registros Insertado Con Exito");
        } catch (SQLException ex) {
            System.out.println(ex);
         
        }
    }
     protected void actualizar(String tabla, String campos, String condicion){
        try {
            pst = con.createStatement();
          
            pst.executeUpdate("UPDATE "+tabla+ " SET "+ campos+" WHERE "+condicion);
       JOptionPane.showMessageDialog(null, "Registros Actualizado Con Exito");
       
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
//       protected void generar_nota_Lavaexport(String folio){
//        try{
//    
//    
//   JasperReport jr=(JasperReport) JRLoader.loadObject(categoria.class.getResource("/REPORTES/CORTEreport1.jasper"));
//  Map  parametro = new HashMap<String,String>();
//  
//  parametro.put("cortes",folio);
//  
//  
//  
//JasperPrint jp=JasperFillManager.fillReport(jr,parametro,con);
//JasperViewer jv =new JasperViewer(jp,false);
//jv.show();
//
//}catch (Exception e){
//    JOptionPane.showMessageDialog(null, "Error"+e);
//}
//
//    }

         

}
