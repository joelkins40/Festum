/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import conexion.conexion;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author hassiel
 */
public class REPORT {
 //se estable una conexion con la base de datos
     conexion con = new conexion();    
    
    //public informe_con_parametros(){
    
    
     /* reporte sencillo con conexion a base de datos MySQL, 
      * el reporte cuenta con un parametro de entrada */    
     public void ver_informe_con_parametros(String parametro){         
         System.out.println("Hola Mundo");
     }
}