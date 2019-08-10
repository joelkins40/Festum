/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


import conexion.BD;

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
public class modelo_Ventas extends BD{
        
        
     
    String cons = "";
    String a = "";
    DefaultTableModel model;
    Statement estado;
protected ResultSet  consultarcategoria()  {
         String cons="Select * from categoria";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    protected ResultSet  consultarventasFECHAS(String FE1,String FE2)  {
         String cons="SELECT * FROM nota,cliente WHERE cliente.id_cliente=nota.id_cliente and fecha_prestamo <= '"+FE1+"' AND fecha_prestamo>='"+FE2+"'";
        System.out.println(cons);
         ResultSet rs=consultar(cons);
        return rs;
        
       

    }
     protected ResultSet  consultarventasclientes(String clientes)  {
         String cons="SELECT * FROM nota,cliente WHERE cliente.id_cliente=nota.id_cliente and  cliente.razon='"+clientes+"'";

         ResultSet rs=consultar(cons);
        return rs;
        
       
}
       protected ResultSet  consultarventasCLIENTES()  {
         String cons="SELECT razon,\n" +
"SUM(total_nota) ,COUNT(*)\n" +
"FROM nota,cliente where nota.id_cliente=cliente.id_cliente\n" +
"GROUP BY cliente.razon ORDER by sum(total_nota) DESC";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    protected ResultSet  consultarventas()  {
         String cons="SELECT * FROM nota,cliente WHERE cliente.id_cliente=nota.id_cliente and fecha_prestamo order by folio";
        ResultSet rs=consultar(cons);
        return rs;
        
       
}
    protected void eliminar_cat ( String id){
               
       String tabla= "categoria";
       String valores="id_categoria='"+id+"'"; 
      
        eliminar(tabla, valores);
    }
protected  void reporte_fechas (String f1, String f2){
    generarnota(f1, f2);
}



  
    

//    public void generar_id_cliente(String vCliente) throws SQLException {
//        cons = "Select razon from cliente where description ='" + vCliente + "'";
//        Statement st = cn.createStatement();
//        ResultSet rs = st.executeQuery(cons);
//        Integer li_Categoria = 0;
//        while (rs.next()) {
//            li_Categoria = rs.getInt("categoria_id");
//        }
//
//    }

    
    
    public void inserta_categoria(String descripcion){
    
        String tabla="categoria";
                String valores="descripcion";
                String datos="'"+descripcion+"'";
                insertar(tabla, valores, datos);
    
    }

    public void modificar_cliente ( String id,String descripcion){
               
       String tabla= "categoria";
       String valores="descripcion='"+descripcion+"'";
       String condicion = "id_categoria='"+id+"'";        
        actualizar(tabla, valores, condicion);

    }

    
 

        
}
