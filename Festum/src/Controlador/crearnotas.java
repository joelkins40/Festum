
package Controlador;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Oscar
 */
public class crearnotas {
    controlador_Pendientes p=new controlador_Pendientes();
    controlador_cliente c=new controlador_cliente();
       public static String folio=null;
    
    private static final Logger LOGGER = Logger.getLogger("newexcel.ExcelOOXML");

    public void crear(String nota){
     
       ResultSet rs=p.consultar_nota_folio(nota);
       
               String Cliente = null;
       String total_nota = null;
        String obs = "";
        try {
            while(rs.next()){
        Cliente=rs.getString("id_cliente");
        total_nota=rs.getString("total_nota");
                obs=rs.getString("obs");
                // SOLICITUD ACTA, COMPROBANTE DE DIMICIO 
                
                
            }} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        
       ResultSet cl=c.consultarclient(Cliente);
       String nombre_a = null;
       String direccion_a = null;
       String telefono_a = null;
       String numero_a;
       String colonia_a = null;
       String Referencias_a = null;
       String ciudad_a = null;
       
        try {
            while(cl.next()){
                 nombre_a=cl.getString("razon");
                 direccion_a=cl.getString("direccion")+" # "+cl.getString("n_e");
                 telefono_a=cl.getString("telefono");
                 colonia_a=cl.getString("colonia");
                 Referencias_a=cl.getString("referencias");
                ciudad_a=cl.getString("ciudad");
                
                               
            }} catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            }
        
        File archivo = new File("reporte.xlsx");
        
        Workbook workbook = new XSSFWorkbook(); 
        
        Sheet pagina = workbook.createSheet("NOTA");
        CellStyle style = workbook.createCellStyle();
        CellStyle stylecentro = workbook.createCellStyle();
        CellStyle stylederecha = workbook.createCellStyle();
        
       
        Row fila = pagina.createRow(3);
        
      
    
    
                
                        
        
      fila=pagina.createRow(2);
      fila.setHeight((short) 300);
        Cell folio=fila.createCell(4);
     
      folio.setCellValue(crearnotas.folio);
        
      
         Date date = new Date();
//Caso 1: obtener la hora y salida por pantalla con formato:
DateFormat anoFormat = new SimpleDateFormat("yy");
DateFormat diaFormat = new SimpleDateFormat("dd");
DateFormat mesFormat = new SimpleDateFormat("MM");
String val=String.valueOf(anoFormat.format(date));
String dia=String.valueOf(diaFormat.format(date));
String mes=String.valueOf(mesFormat.format(date));

  
      
        String diaesano="                                     "+dia+"          "+mes+"      "+val;
      fila=pagina.createRow(1);
      Cell fecha=fila.createCell(3);
      fila.setHeight((short) 225);
      
    stylecentro.setAlignment(HorizontalAlignment.CENTER);
    fecha.setCellStyle(stylecentro);

      
      fecha.setCellValue(diaesano);
      
        
      fila=pagina.createRow(4);
      Cell nombre=fila.createCell(1);
      nombre.setCellValue("                         "+nombre_a);
      nombre.getSheet().getLeftCol();
      fila.setHeight((short) 255);
      fila=pagina.createRow(5);
      Cell direccion=fila.createCell(1);
      fila.setHeight((short) 255);
      
      direccion.setCellValue("                         "+direccion_a);
      fila=pagina.createRow(6);
      
      Cell colonia=fila.createCell(1);
      Cell telefono=fila.createCell(4);
    String ESPACIO= String.format("%-20s %s ", colonia_a, ".","     ");
            String name1= String.format("%-20s %s ", ESPACIO, "     ","");
    String ESPACIO2= String.format("%-20s %s ", name1, "","");
            String name2= String.format("%-20s %s ", ESPACIO2, ciudad_a,"");
      
      colonia.setCellValue("                         "+name2);
      telefono.setCellValue(telefono_a);
      fila.setHeight((short) 255);
      
      
      fila=pagina.createRow(7);
      Cell referencnias=fila.createCell(1);
      referencnias.setCellValue("                         "+Referencias_a);
      fila.setHeight((short) 255);
      ResultSet notasrs=p.ConsultarNOTASfolioregistros(nota);
      int its=0;
      
    
       
        try {
            while(notasrs.next()){
          fila=pagina.createRow(its+9);
        
          Cell cantidad=fila.createCell(1);
        Cell descripcion=fila.createCell(2);
        Cell precio=fila.createCell(3);
        Cell total=fila.createCell(4);
       
        fila.setHeight((short) 330);
        cantidad.setCellValue(notasrs.getString("cantidad"));
        precio.setCellValue("$  "+notasrs.getString("precio"));
        descripcion.setCellValue(notasrs.getString(8));
        total.setCellValue("$  "+notasrs.getString("total_producto"));
                its=its+1;
             stylederecha.setAlignment(HorizontalAlignment.RIGHT);
             cantidad.setCellStyle(stylecentro);
   
    precio.setCellStyle(stylederecha);
     total.setCellStyle(stylederecha);
          
                
            } } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
       
       
            }
        
         for(int i=its;i<13;i++){
          fila=pagina.createRow(i+9);
       fila.setHeight((short) 330);
        }             
        
        
       
       
      fila=pagina.createRow(22);
      fila.setHeight((short) 945);
     
      fila=pagina.createRow(22);
      Cell subtotal=fila.createCell(4);
      fila.setHeight((short) 440);
      
      
      subtotal.setCellStyle(stylederecha);
      subtotal.setCellValue("$  "+total_nota);
      
      
      fila=pagina.createRow(34);
      Cell obser=fila.createCell(1);
      fila.setHeight((short) 440);
      obser.setCellValue(obs);
      
      
      Sheet sheet = workbook.getSheetAt(0);
sheet.setColumnWidth(0, 2720);
sheet.setColumnWidth(1, 3030);
sheet.setColumnWidth(2, 8630);
sheet.setColumnWidth(3, 2150);
sheet.setColumnWidth(4, 2520);


fila=pagina.createRow(0);

            fila.setHeight((short) 540);      
fila=pagina.createRow(2);
            fila.setHeight((short) 405);
        fila=pagina.createRow(3);
            fila.setHeight((short) 375);
        fila=pagina.createRow(8);
            fila.setHeight((short) 255);
        
        try {
            FileOutputStream salida = new FileOutputStream(archivo);
            

            workbook.write(salida);
            workbook.close();
            
            LOGGER.log(Level.INFO, "Archivo creado existosamente en {0}", archivo.getAbsolutePath());
            
                        imprimir(archivo.getAbsolutePath());

        
        
        
        } catch (FileNotFoundException ex) {
       JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
         JOptionPane.showMessageDialog(null, ex);
        }
    }
private void abrir(String ruta) {
  //ruta del archivo en el pc
  String file = new String(ruta); 
   
 try{ 
   //definiendo la ruta en la propiedad file
   Runtime.getRuntime().exec("cmd /c start "+file);
     
   }catch(IOException e){
      JOptionPane.showMessageDialog(null, e);
   } 
  }


private void imprimir(String ruta){
java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
java.io.File fichero = new java.io.File(ruta);
if (desktop.isSupported(Desktop.Action.PRINT)){
    try {
        desktop.print(fichero);
        JOptionPane.showMessageDialog(null, "La Nota se esta Imprimiendo...","Imprimiendo",JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException ex) {
      JOptionPane.showMessageDialog(null, ex);
    }
}else{

            JOptionPane.showMessageDialog(null, "NO SE PUEDE IMPRIMIR");
}
    }
}
