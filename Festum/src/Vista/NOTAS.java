package Vista;

import Controlador.controlador_CP;
import Controlador.controlador_cliente;
import Controlador.controlador_notas;
import Controlador.controlador_producto;

import Controlador.crearnotas;
import static Vista.Festum.escritorio;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.RowFilter;
import javax.swing.plaf.ColorChooserUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

public class NOTAS extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    controlador_cliente j = new controlador_cliente();
    Controlador.controlador_CP cp = new controlador_CP();
    controlador_notas cnnn = new controlador_notas();
    controlador_producto pd = new controlador_producto();
    String accion_cliente = "R";
    String accion="";
    String vId;
    String vRazon;
    String vDireccion;
    String vNumero_Ext;
    String vNumero_Int="";
    String vColonia;
    String vreferencias;
    String vCP;
    String vtelefono;
    String vciudad;
String color;
crearnotas nt=new crearnotas();


public void imprimircrearnota(String id){
     int filas = tbDatos.getRowCount();
     if(filas>13){
    nt.crear(id);
     if (JOptionPane.showConfirmDialog(null, "¿Desea imprimir toda la nota?", "¿Imprimir?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
         cnnn.cargar_nota_todosalv(id);
     }
     }else{
      nt.crear(id);
       
     }
    
}
    public NOTAS() {

        initComponents();
        limpiar();
        bloquear();
        cargar_tabla_notascorte("");
        bloquear_txt();
        auto();
        txtcolor.setEditable(false);
    }
 void generarfolio_cliente() {
        ResultSet rs = j.SELECTMAXID();
        String folioc = "";
        try {
            while (rs.next()) {
                folioc = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (folioc == null) {
            txtid.setText("1");

        } else {
            int intfolio = Integer.parseInt(folioc);
            String total = String.valueOf(intfolio + 1);
            txtid.setText(total);
        }
    }
    void actualiza() {
        int filasel = tbDatos.getSelectedRow();
        String a = (String) tbDatos.getValueAt(filasel, 4);
        String b = (String) tbDatos.getValueAt(filasel, 5);
        Integer ta = Integer.parseInt(a);
        Double tb = Double.parseDouble(b);
        Double total = ta * tb;
        String resul = String.valueOf(total);
        tbDatos.setValueAt(resul, filasel, 6);
    }
public int validar_campos() {
       int validacion = 0;
        
      
        if ("".equals(txtrazon.getText())) {
            validacion = 1;
        }
        if ("".equals(txtdireccion.getText())) {
            validacion = 1;
        }

        if ("".equals(txtne.getText())) {
            validacion = 1;
        }
        if ("".equals(txtreferencias.getText())) {
            validacion = 1;
        }
        if ("".equals(txttelefono.getText())) {
            validacion = 1;
        }
         
        
        
return validacion;
    }
    void sumar() {
        int totalRow = tbDatos.getRowCount();
        if (totalRow == 0) {
            JOptionPane.showMessageDialog(null, "No Existen registros");
        } else {

            int filasel = tbDatos.getSelectedRow();
            String v1 = (String) tbDatos.getValueAt(filasel, 5);
            String v2 = (String) tbDatos.getValueAt(filasel, 6);
            Double a = Double.parseDouble(v1);
            Double b = Double.parseDouble(v2);
            Double total = a * b;

            tbDatos.setValueAt(total, filasel, 6);
            acumular();

        }

    }

    void bloquear_txt() {
        txtexistencia.setEditable(false);
        txtcantidad.setEditable(false);
        txttotalproducto.setEditable(false);
    }

    public void auto() {
        TextAutoCompleter textAutoCompleter = new TextAutoCompleter(txtdescripcion);
        ResultSet rs = pd.consultar__pr();
        try {
            while (rs.next()) {
                textAutoCompleter.addItem(rs.getString("descripcion_producto"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void limpiar_tabla() {
    String [] titulos={"ID","Clave","Descripcion","Exist","Cant","Precio","Total"};
            String [] registros=new String[6];
            DefaultTableModel we=new DefaultTableModel(null,titulos);
      tbDatos.setModel(we);
            TableColumnModel colummodel= tbDatos.getColumnModel();
       colummodel.getColumn(0).setMaxWidth(1);
        colummodel.getColumn(2).setPreferredWidth(600);
        colummodel.getColumn(1).setPreferredWidth(15);
        colummodel.getColumn(3).setPreferredWidth(10);
     colummodel.getColumn(4).setPreferredWidth(10);    
        colummodel.getColumn(5).setPreferredWidth(10);    
    }
    void sumar_tabla(){
        int total=tbDatos.getRowCount();
      Double contador=0.0;
        for(int i=0;i<total;i++){
          Double a=Double.parseDouble((String) tbDatos.getValueAt(i, 4));
          Double b=Double.parseDouble((String) tbDatos.getValueAt(i, 5));
          String ttl=String.valueOf(a*b);
          tbDatos.setValueAt(ttl, i, 6);
                   
      }
      for(int i=0;i<total;i++){
          Double a=Double.parseDouble((String) tbDatos.getValueAt(i, 6));
          contador=contador+a;
                   
      }
      txttotal.setText(String.valueOf(contador));
    }
    void llenar_tabla() {
        DefaultTableModel tabladet = (DefaultTableModel) tbDatos.getModel();
        String[] registros = new String[8];
        registros[0] = "";
        registros[1] = txtclave.getText();
        registros[2] = txtdescripcion.getText();
       registros[3] = txtexistencia.getText();
        registros[4] = txtcantidad.getText();
        registros[5] = txtprecio.getText();
        registros[6] = txttotalproducto.getText();
        tabladet.addRow(registros);
        tbDatos.setModel(tabladet);
    }

    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        int code = evt.getKeyCode();
        char caracter = evt.getKeyChar();
    }

    void bloquear() {
        txtid.setEditable(false);
        txtcp.setEditable(false);
        txtdireccion.setEditable(false);
        txtestado.setEditable(false);
      txtdescripcion.setEditable(false);
btncolor.setEnabled(false);
btproducto.setEnabled(false);
        
       
        txtfechaD.setEnabled(false);
        txtfechaP.setEnabled(false);
       txttelefono.setEditable(false);
        txtmunicipio.setEditable(false);
        txtne.setEditable(false);
        txtrazon.setEditable(false);
        txtreferencias.setEditable(false);
        txttotal.setEditable(false);
        cmbcolonia.setEditable(false);
        mnuevo.setVisible(true);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
        mcancelar.setVisible(true);
        mcotizacion.setVisible(false);
        mreporte.setVisible(false);
        mguardar.setVisible(false);
        mcancelar.setVisible(false);
        tbDatos.setEnabled(false);
    }

    void actualizar_produc_notas(String folio,String t_precio) {
        for (int i = 0; i < tbDatos.getRowCount(); i++) {
           String cond=tbDatos.getValueAt(i, 0).toString();
            String id_producto = (String) tbDatos.getValueAt(i, 1);
            String descripcion_producto = (String) tbDatos.getValueAt(i, 2);
           
            String cantidad = this.tbDatos.getValueAt(i, 4).toString();
            String precio = this.tbDatos.getValueAt(i, 5).toString();
           
            String total = this.tbDatos.getValueAt(i, 6).toString();
            ResultSet rs = pd.consultarproduct_clave(id_producto);
            String id = "";
            int exi = 0;
            try {
                while (rs.next()) {
               id_producto=rs.getString(1);
                    exi = rs.getInt("cantidad");
                }
            } catch (SQLException ex) {
                Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            int cantid = Integer.parseInt(cantidad);
            int tot;
            if(exi<cantid){
                tot=0;
            }else{
            tot = exi - cantid;
            }
           
            
            String s = String.valueOf(tot);
            if("".equals(cond)){
                accion="I";
            }else{
                accion="M";
                
            }
            
            cnnn.guardar_nota_prod(accion, cond, id_producto, cantidad, total, folio, precio, t_precio,descripcion_producto);
            
            
        }
    }

    void actualizar_produc_cotizacion(String folio,String t_precio) {
        for (int i = 0; i < tbDatos.getRowCount(); i++) {
           String cond=tbDatos.getValueAt(i, 0).toString();
            String id_f = (String) tbDatos.getValueAt(i, 1);
            String descripcion_producto = (String) tbDatos.getValueAt(i, 2);
            
            String cantidad = this.tbDatos.getValueAt(i, 4).toString();
            String precio = this.tbDatos.getValueAt(i, 5).toString();
           
            String total = this.tbDatos.getValueAt(i, 6).toString();
            ResultSet rs = pd.consultarproduct_clave(id_f);
            String producto = "";
            int exi = 0;
            try {
                while (rs.next()) {
               producto=rs.getString(1);
                    exi = rs.getInt("cantidad");
                }
            } catch (SQLException ex) {
                Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            int cantid = Integer.parseInt(cantidad);
            int tot;
            if(exi<cantid){
                tot=0;
            }else{
            tot = exi - cantid;
            }
           
            
            String s = String.valueOf(tot);
            if("".equals(cond)){
                accion="I";
            }else{
                accion="M";
                
            }
            
            cnnn.guardar_nota_cotizacion(accion,cond, producto, cantidad, total, folio, precio, t_precio,descripcion_producto);
            
            
        }
    
    }
void agregar_cliente(String id_C){
    
   
    ResultSet rs=j.consultarclient(id_C);
    String   as="";   
    try {
        while(rs.next()){
        as =rs.getString(1);
        }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    if("".equals(as)){
      as="I";
    generarfolio_cliente();

      
    }else{
      as="M";  
        
    }
    vId=txtid.getText();
            vRazon = txtrazon.getText();
            vDireccion = txtdireccion.getText();
            vNumero_Ext = txtne.getText();
            vColonia = cmbcolonia.getSelectedItem().toString();
            vCP = txtcp.getText();
            vreferencias = txtreferencias.getText();
            vtelefono = txttelefono.getText();
            vciudad = txtmunicipio.getText();

            String precio;
            if (btnespecial.isSelected()) {
                precio = "E";
            } else if (btngeneral.isSelected()) {
                precio = "G";
            } else {
                precio = "";
            }

            j.guardar_cliente(as, vId, vRazon, vDireccion, vNumero_Ext, vNumero_Int, vColonia, vCP, vreferencias, vtelefono, vciudad, precio);

}
    void desbloquear() {
        txtfechaD.setEnabled(true);
        txtfechaP.setEnabled(true);
        mnuevo.setVisible(false);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
        mcancelar.setVisible(false);
        mguardar.setVisible(true);
        mcancelar.setVisible(true);
        mcotizacion.setVisible(true);
         tbDatos.setEnabled(true);
           txtdescripcion.setEditable(true);
btncolor.setEnabled(true);
btproducto.setEnabled(true);
    }

    void desbloquear_ingresar() {
        txtid.setEditable(false);
        txtcp.setEditable(true);
        txtdireccion.setEditable(true);
        txtestado.setEditable(true);
        txtmunicipio.setEditable(true);
        txtne.setEditable(true);
        txtrazon.setEditable(true);
        txtreferencias.setEditable(true);
        cmbcolonia.setEditable(true);
   txttelefono.setEditable(true);
    btnespecial.setEnabled(true);
    btngeneral.setEnabled(true);
    }

    void bloquear_ingresar() {
        txtid.setEditable(false);
        txtcp.setEditable(false);
        txtdireccion.setEditable(false);
        txtestado.setEditable(false);
        txtmunicipio.setEditable(false);
        txtne.setEditable(false);
        txttelefono.setEditable(false);
        txtrazon.setEditable(false);
        txtreferencias.setEditable(false);
        cmbcolonia.setEditable(false);
       
    btnespecial.setEnabled(false);
    btngeneral.setEnabled(false);
    }

    void limpiar_ingre() {
        txtid.setText("");
        txtcp.setText("");
        txtdireccion.setText("");
        txtestado.setText("");
        txtmunicipio.setText("");
        txtne.setText("");
        txtrazon.setText("");
        txtreferencias.setText("");
        cmbcolonia.removeAllItems();
txttelefono.setText("");
    }

    public void acumular() {

        Double total = 0.0;
        Double b = 0.0;

        for (int i = 0; i < tbDatos.getRowCount(); i++) {

            String valor = tbDatos.getValueAt(i, 6).toString(); // la columna 1 es la de costo. 
            total = total + Double.parseDouble(valor);
        }
        txttotal.setText(String.valueOf(total));
    }

    void generarfoliocotizaion() {
        ResultSet rs = cnnn.consulta_folio_cotizacion();
        String folioc = "";
        try {
            while (rs.next()) {
                folioc = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (folioc == null) {
            txtfolio.setText("1");

        } else {
            int intfolio = Integer.parseInt(folioc);
            String total = String.valueOf(intfolio + 1);
            txtfolio.setText(total);
        }
    }

    void generarfolio() {
        ResultSet rs = cnnn.consulta_folioo();
        String folioc = "";
        try {
            while (rs.next()) {
                folioc = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (folioc == null) {
            txtfolio.setText("1");

        } else {
            int intfolio = Integer.parseInt(folioc);
            String total = String.valueOf(intfolio + 1);
            txtfolio.setText(total);
        }
    }

    void limpiar() {
        txtid.setText("");
        txtcp.setText("");
        txtdireccion.setText("");
        txtestado.setText("");
        txtfechaD.setDate(null);
        txtfechaP.setDate(null);
txttelefono.setText("");
        txtmunicipio.setText("");
        txtne.setText("");
        txtrazon.setText("");
        txtreferencias.setText("");
        txttotal.setText("");
        cmbcolonia.removeAllItems();
        txtobs.setText("");
        limpiar_tabla();
    }

    void cargar_tabla_notascorte(String valor) {
        DefaultTableModel tb = cnnn.cargar_tabla_notas();
        tbdatosnotas.setModel(tb);
 TableColumnModel colummodel= tbdatosnotas.getColumnModel();
       colummodel.getColumn(0).setPreferredWidth(5);
        colummodel.getColumn(1).setPreferredWidth(300);
        colummodel.getColumn(2).setPreferredWidth(10);
        colummodel.getColumn(3).setPreferredWidth(10);
     colummodel.getColumn(4).setPreferredWidth(10);    
        colummodel.getColumn(5).setPreferredWidth(10);
    }

    void consultar_CLIENTE() {
        try {
            ResultSet r = j.consultarclient(vId);
            String precio = "";
            while (r.next()) {
                vRazon = r.getString("razon");
                vDireccion = r.getString("direccion");
                vNumero_Ext = r.getString("n_e");
                vNumero_Int = r.getString("n_i");
                vColonia = r.getString("colonia");
                vCP = r.getString("cp");
                vreferencias = r.getString("referencias");
                vtelefono = r.getString("telefono");
                precio = r.getString("precio");
            }
            txtid.setText(vId);
            txtrazon.setText(vRazon);
            txtdireccion.setText(vDireccion);
            txtne.setText(vNumero_Ext);
            cmbcolonia.addItem(vColonia);
            txtcp.setText(vCP);
            txtreferencias.setText(vreferencias);
            txttelefono.setText(vtelefono);

            if ("G".equals(precio)) {
                btngeneral.setSelected(true);
                btnespecial.setSelected(false);

            } else if ("E".equals(precio)) {
                btngeneral.setSelected(false);
                btnespecial.setSelected(true);

            } else {
                btngeneral.setSelected(false);
                btnespecial.setSelected(false);

            }

            codp(vCP);
        } catch (SQLException ex) {
            Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void codp(String codigopostal) {
        String estado = "";
        String municiio = "";

        try {
            ResultSet rs = cp.consultar_cpp(codigopostal);

            while (rs.next()) {
                estado = rs.getString("estado");
                municiio = rs.getString("municipio");

            }
        } // TODO add your handling code here:
        catch (SQLException ex) {
            Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtestado.setText(estado);
        txtmunicipio.setText(municiio);

    }

    void cargar_registros(String folio) {
        DefaultTableModel mod = cnnn.cargar_registros_notas(folio);
        tbDatos.setModel(mod);
         TableColumnModel colummodel= tbDatos.getColumnModel();
          colummodel.getColumn(0).setMaxWidth(1);
        colummodel.getColumn(2).setPreferredWidth(600);
        colummodel.getColumn(1).setPreferredWidth(15);
        colummodel.getColumn(3).setPreferredWidth(10);
     colummodel.getColumn(4).setPreferredWidth(10);    
        colummodel.getColumn(5).setPreferredWidth(10); 
        
    }

    void cargarnota(String folio) {
        ResultSet rs = cnnn.consultar_nota_folio(folio);
        String fep = "";
        String fed = "";
        String totalnota = "";
        String clie = "";
String obs="";
        try {
            while (rs.next()) {
                fep = rs.getString("fecha_prestamo");
                fed = rs.getString("fecha_devolucion");
                totalnota = rs.getString("total_nota");
                clie = rs.getString("id_cliente");
    color=rs.getString("color");
    obs=rs.getString("obs");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtfolio.setText(folio);
        txtobs.setText(obs);
        String ffechap = fep;

        String ffechad = fed;

        java.util.Date fechap = null;
        java.util.Date fechad = null;
        try {
            fechap = new SimpleDateFormat("yyyy-MM-dd").parse(ffechap);
            fechad = new SimpleDateFormat("yyyy-MM-dd").parse(ffechad);
        } catch (ParseException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtfechaP.setDate(fechap);
        txtfechaD.setDate(fechad);
        txttotal.setText(totalnota);
txtcolor.setText(color);
  txtcolor.setBackground(Color.decode(color));
        vId = clie;
        consultar_CLIENTE();
    }
    DefaultTableModel dm;
    private void filtro(String consulta, JTable jtableBuscar) {
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();
        jMenuItem1 = new javax.swing.JMenuItem();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdatosnotas = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtrazon = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtne = new javax.swing.JTextField();
        txtcp = new javax.swing.JTextField();
        txtmunicipio = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtreferencias = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        cmbcolonia = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        btnespecial = new javax.swing.JRadioButton();
        btngeneral = new javax.swing.JRadioButton();
        cxbeditar = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        txtdescripcion = new javax.swing.JTextField();
        txtclave = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        txtexistencia = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        txttotalproducto = new javax.swing.JTextField();
        txttotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        txtfechaD = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtfechaP = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btproducto = new javax.swing.JButton();
        txtfolio = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtcolor = new javax.swing.JTextField();
        btncolor = new javax.swing.JButton();
        txtobs = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnuevo = new javax.swing.JMenu();
        mmodificar = new javax.swing.JMenu();
        mguardar = new javax.swing.JMenu();
        meliminar = new javax.swing.JMenu();
        mreporte = new javax.swing.JMenu();
        mcancelarnota = new javax.swing.JMenu();
        mcotizacion = new javax.swing.JMenu();
        mcancelar = new javax.swing.JMenu();
        msalir = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setBackground(new java.awt.Color(36, 47, 97));
        setTitle("NOTAS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-sales-performance-filled.png"))); // NOI18N

        jTabbedPane1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        jPanel4.setBackground(new java.awt.Color(103, 206, 210));

        tbdatosnotas.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        tbdatosnotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbdatosnotas.getTableHeader().setReorderingAllowed(false);
        tbdatosnotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdatosnotasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbdatosnotasMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tbdatosnotas);

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel17.setText("Folio");

        txtbuscar.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1108, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(229, 229, 229)
                .addComponent(jLabel17)
                .addGap(32, 32, 32)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("BASE DE DATOS", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jPanel4); // NOI18N

        jPanel2.setBackground(new java.awt.Color(103, 206, 210));

        jPanel3.setBackground(new java.awt.Color(103, 206, 210));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Comic Sans MS", 0, 14), new java.awt.Color(36, 47, 97))); // NOI18N
        jPanel3.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("Razon Social/Nombre");

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 47, 97));
        jLabel3.setText("Calle.");

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("Numero Exterior");

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(36, 47, 97));
        jLabel6.setText("Colonia");

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(36, 47, 97));
        jLabel8.setText("Municipio");

        jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(36, 47, 97));
        jLabel9.setText("Estado");

        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 97));
        jLabel10.setText("C.P.");

        txtrazon.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtrazon.setForeground(new java.awt.Color(36, 47, 97));
        txtrazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrazonKeyTyped(evt);
            }
        });

        txtdireccion.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtdireccion.setForeground(new java.awt.Color(36, 47, 97));
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });

        txtne.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtne.setForeground(new java.awt.Color(36, 47, 97));
        txtne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtneKeyTyped(evt);
            }
        });

        txtcp.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtcp.setForeground(new java.awt.Color(36, 47, 97));
        txtcp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcpFocusLost(evt);
            }
        });
        txtcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcpActionPerformed(evt);
            }
        });
        txtcp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcpKeyPressed(evt);
            }
        });

        txtmunicipio.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtmunicipio.setForeground(new java.awt.Color(36, 47, 97));

        txtestado.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtestado.setForeground(new java.awt.Color(36, 47, 97));

        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(36, 47, 97));
        jLabel11.setText("ID");

        txtid.setForeground(new java.awt.Color(36, 47, 97));
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel12.setForeground(new java.awt.Color(36, 47, 97));

        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 97));
        jLabel13.setText("Referencias");

        txtreferencias.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtreferencias.setForeground(new java.awt.Color(36, 47, 97));
        txtreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtreferenciasActionPerformed(evt);
            }
        });
        txtreferencias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtreferenciasKeyTyped(evt);
            }
        });

        jButton3.setForeground(new java.awt.Color(36, 47, 97));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-search-filled.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(36, 47, 97));
        jLabel18.setText("Telefono");

        txttelefono.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txttelefono.setForeground(new java.awt.Color(36, 47, 97));
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        cmbcolonia.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        cmbcolonia.setForeground(new java.awt.Color(36, 47, 97));

        jLabel19.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(36, 47, 97));
        jLabel19.setText("Precio");

        btnespecial.setBackground(new java.awt.Color(103, 206, 210));
        buttonGroup1.add(btnespecial);
        btnespecial.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnespecial.setForeground(new java.awt.Color(36, 47, 97));
        btnespecial.setText("Especial");

        btngeneral.setBackground(new java.awt.Color(103, 206, 210));
        buttonGroup1.add(btngeneral);
        btngeneral.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btngeneral.setForeground(new java.awt.Color(36, 47, 97));
        btngeneral.setSelected(true);
        btngeneral.setText("General");
        btngeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneralActionPerformed(evt);
            }
        });

        cxbeditar.setBackground(new java.awt.Color(103, 206, 210));
        cxbeditar.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        cxbeditar.setText("Editar");
        cxbeditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cxbeditarActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel12))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel6)
                        .addComponent(jLabel13)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(cmbcolonia, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtdireccion, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtrazon))
                                .addGap(30, 30, 30))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cxbeditar)
                                    .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, Short.MAX_VALUE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btngeneral)
                        .addGap(52, 52, 52)
                        .addComponent(btnespecial))
                    .addComponent(txtreferencias))
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cxbeditar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(jLabel2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cmbcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnespecial)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btngeneral)
                            .addComponent(jLabel19))
                        .addGap(21, 21, 21)
                        .addComponent(jLabel12)))
                .addGap(331, 331, 331))
        );

        jPanel5.setBackground(new java.awt.Color(103, 206, 210));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("$$$$"));

        tbDatos.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "CLAVE", "DESCRIPCION", "COLOR", "EXISTENCIA", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDatos.getTableHeader().setReorderingAllowed(false);
        tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatosMouseClicked(evt);
            }
        });
        tbDatos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbDatosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tbDatos);

        txtdescripcion.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtdescripcion.setForeground(new java.awt.Color(36, 47, 97));
        txtdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescripcionActionPerformed(evt);
            }
        });
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
            }
        });

        txtclave.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtclave.setForeground(new java.awt.Color(36, 47, 97));
        txtclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclaveActionPerformed(evt);
            }
        });
        txtclave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtclaveKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclaveKeyReleased(evt);
            }
        });

        txtcantidad.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtcantidad.setForeground(new java.awt.Color(36, 47, 97));
        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcantidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });

        txtexistencia.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtexistencia.setForeground(new java.awt.Color(36, 47, 97));
        txtexistencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtexistenciaActionPerformed(evt);
            }
        });
        txtexistencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtexistenciaKeyReleased(evt);
            }
        });

        txtprecio.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtprecio.setForeground(new java.awt.Color(36, 47, 97));
        txtprecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtprecioActionPerformed(evt);
            }
        });
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtprecioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprecioKeyReleased(evt);
            }
        });

        txttotalproducto.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txttotalproducto.setForeground(new java.awt.Color(36, 47, 97));
        txttotalproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttotalproductoActionPerformed(evt);
            }
        });
        txttotalproducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txttotalproductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttotalproductoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1054, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalproducto)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotalproducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        txttotal.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txttotal.setForeground(new java.awt.Color(36, 47, 97));

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(36, 47, 97));
        jLabel14.setText("Total");

        panel.setBackground(new java.awt.Color(103, 206, 210));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(36, 47, 97));
        jLabel16.setText("Fecha de Devolucion");

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(36, 47, 97));
        jLabel15.setText("Fecha de Prestamo");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 47, 97));
        jLabel1.setText("FOLIO");

        btproducto.setBackground(new java.awt.Color(103, 210, 190));
        btproducto.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btproducto.setForeground(new java.awt.Color(36, 47, 97));
        btproducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-search-filled.png"))); // NOI18N
        btproducto.setText("BUSCAR PRODUCTO");
        btproducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btproductoActionPerformed(evt);
            }
        });

        txtfolio.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16)
                    .addComponent(txtfechaD, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtfechaP, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btproducto))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfechaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btproducto))
        );

        jButton4.setBackground(new java.awt.Color(103, 210, 190));
        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(36, 47, 97));
        jButton4.setText("Eliminar Producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel7.setText("COLOR");

        txtcolor.setBackground(new java.awt.Color(255, 79, 52));
        txtcolor.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtcolor.setText("#FF4F34");

        btncolor.setBackground(new java.awt.Color(103, 210, 190));
        btncolor.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        btncolor.setText("BUSCAR");
        btncolor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncolorActionPerformed(evt);
            }
        });

        txtobs.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txtobs.setForeground(new java.awt.Color(36, 47, 97));
        txtobs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtobsActionPerformed(evt);
            }
        });
        txtobs.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtobsKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtobsKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtobsKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 97));
        jLabel5.setText("Observaciones");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(76, 76, 76)
                        .addComponent(jLabel14)
                        .addGap(28, 28, 28)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btncolor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(txtcolor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btncolor)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4)
                            .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addGap(66, 66, 66))
        );

        jTabbedPane1.addTab("NOTAS", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-hand-with-pen-filled.png")), jPanel2); // NOI18N

        jMenuBar2.setBackground(new java.awt.Color(103, 210, 190));

        mnuevo.setBackground(new java.awt.Color(103, 206, 210));
        mnuevo.setForeground(new java.awt.Color(36, 47, 97));
        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-plus-32.png"))); // NOI18N
        mnuevo.setText("NUEVO");
        mnuevo.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuevoMouseClicked(evt);
            }
        });
        jMenuBar2.add(mnuevo);

        mmodificar.setBackground(new java.awt.Color(103, 206, 210));
        mmodificar.setForeground(new java.awt.Color(36, 47, 97));
        mmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-pencil-32.png"))); // NOI18N
        mmodificar.setText("MODIFICAR");
        mmodificar.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mmodificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mmodificarMouseClicked(evt);
            }
        });
        mmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mmodificarActionPerformed(evt);
            }
        });
        jMenuBar2.add(mmodificar);

        mguardar.setBackground(new java.awt.Color(103, 206, 210));
        mguardar.setForeground(new java.awt.Color(36, 47, 97));
        mguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-save-all-32.png"))); // NOI18N
        mguardar.setText("GUARDAR");
        mguardar.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mguardarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mguardarMouseEntered(evt);
            }
        });
        jMenuBar2.add(mguardar);

        meliminar.setBackground(new java.awt.Color(103, 206, 210));
        meliminar.setForeground(new java.awt.Color(36, 47, 97));
        meliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-empty-trash-32.png"))); // NOI18N
        meliminar.setText("ELIMINAR");
        meliminar.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        meliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meliminarMouseClicked(evt);
            }
        });
        jMenuBar2.add(meliminar);

        mreporte.setBackground(new java.awt.Color(103, 206, 210));
        mreporte.setForeground(new java.awt.Color(36, 47, 97));
        mreporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-receipt-32.png"))); // NOI18N
        mreporte.setText("IMPRIMIR NOTA");
        mreporte.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mreporte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mreporteMouseClicked(evt);
            }
        });
        jMenuBar2.add(mreporte);

        mcancelarnota.setBackground(new java.awt.Color(103, 206, 210));
        mcancelarnota.setForeground(new java.awt.Color(36, 47, 97));
        mcancelarnota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-delete-file-filled.png"))); // NOI18N
        mcancelarnota.setText("CANCELAR NOTA");
        mcancelarnota.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mcancelarnota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcancelarnotaMouseClicked(evt);
            }
        });
        jMenuBar2.add(mcancelarnota);

        mcotizacion.setBackground(new java.awt.Color(103, 206, 210));
        mcotizacion.setForeground(new java.awt.Color(36, 47, 97));
        mcotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-stack-of-money-32.png"))); // NOI18N
        mcotizacion.setText("CREAR COTIZACION");
        mcotizacion.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mcotizacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcotizacionMouseClicked(evt);
            }
        });
        jMenuBar2.add(mcotizacion);

        mcancelar.setBackground(new java.awt.Color(103, 206, 210));
        mcancelar.setForeground(new java.awt.Color(36, 47, 97));
        mcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-delete-32.png"))); // NOI18N
        mcancelar.setText("CANCELAR");
        mcancelar.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        mcancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcancelarMouseClicked(evt);
            }
        });
        jMenuBar2.add(mcancelar);

        msalir.setBackground(new java.awt.Color(103, 206, 210));
        msalir.setForeground(new java.awt.Color(36, 47, 97));
        msalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-shutdown-32.png"))); // NOI18N
        msalir.setText("SALIR");
        msalir.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        msalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msalirMouseClicked(evt);
            }
        });
        jMenuBar2.add(msalir);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1133, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuevoMouseClicked
        limpiar();
        accion = "I";
        desbloquear();
        generarfolio();
        jTabbedPane1.setSelectedIndex(1);
        Calendar c2 = new GregorianCalendar();
        txtfechaP.setCalendar(c2);
        txtfechaD.setCalendar(c2);
txtprecio.setEditable(false);
    }//GEN-LAST:event_mnuevoMouseClicked

    private void mmodificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mmodificarMouseClicked
desbloquear();
        jTabbedPane1.setSelectedIndex(1);
        accion = "M";


    }//GEN-LAST:event_mmodificarMouseClicked

    private void mmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmodificarActionPerformed
     
accion="M";        
// TODO add your handling code here:
    }//GEN-LAST:event_mmodificarActionPerformed

    private void meliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meliminarMouseClicked
        if (JOptionPane.showConfirmDialog(null, "¿Eliminar Nota?", "¿Eliminar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            vId = txtfolio.getText();

            cnnn.eliminar_nota(vId);
            cargar_tabla_notascorte("");
            limpiar_prod();
            limpiar_ingre();
            limpiar_tabla();
            jTabbedPane1.setSelectedIndex(0);
        }

    }//GEN-LAST:event_meliminarMouseClicked

    private void mcancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcancelarMouseClicked
        bloquear();
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_mcancelarMouseClicked

    private void msalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msalirMouseClicked
        this.dispose();
    }//GEN-LAST:event_msalirMouseClicked

    private void mguardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseClicked
int g=validar_campos();
if(g==1){
    JOptionPane.showMessageDialog(null, "Faltan Campos por Completar en Cliente");
}else{     
String ssa=txtrazon.getText();
        int qsa=tbDatos.getRowCount();
        if(qsa==0){
            JOptionPane.showMessageDialog(null, "La Tabla no puede estar vacia");
        }else if("".equals(ssa)){
            JOptionPane.showMessageDialog(null, "Faltan Datos del Cliente");
        }else{
            
        
        

        if (JOptionPane.showConfirmDialog(null, "¿Desea Guardar Los Datos del la Nota?", "¿Guardar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            agregar_cliente(txtid.getText());
sumar_tabla();            
            
            String vvid_cliente = txtid.getText();
            
            String vfol = txtfolio.getText();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd 00:00:00");
            
            SimpleDateFormat formatoFecha3 = new SimpleDateFormat("YYYY-MM-dd 23:59:59");
            String vfecha_prestamo = formatoFecha.format(txtfechaP.getDate());
            String vfecha_devolucion = formatoFecha3.format(txtfechaD.getDate());
            String estado = "A";
            String total_nota = txttotal.getText();
 color=txtcolor.getText();
 String obs=txtobs.getText();

 cnnn.guardar_nota(accion, vfol, vfecha_prestamo, vfecha_devolucion, estado, total_nota, vvid_cliente,color,obs);
         
            String t_precio="";
           if( btnespecial.isSelected()){
               t_precio="E";
           }else if(btngeneral.isSelected()){
              t_precio="G";
           }
            actualizar_produc_notas(vfol,t_precio);
            cargar_tabla_notascorte("");
            bloquear();
            bloquear_ingresar();
            jTabbedPane1.setSelectedIndex(1);
            
           mreporte.setVisible(true);
           mmodificar.setVisible(true);
            cargar_registros(vfol);
            
            

        }}}
    }//GEN-LAST:event_mguardarMouseClicked

    private void mguardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseEntered


    private void mreporteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mreporteMouseClicked

      imprimircrearnota(txtfolio.getText()); 
          
              // TODO add your handling code here:
            

        



        
        
    }//GEN-LAST:event_mreporteMouseClicked
    void limpiar_prod() {
        txtclave.setText("");
        txtdescripcion.setText("");
        txtexistencia.setText("");
        txtprecio.setText("");
        txtcantidad.setText("");
        txttotalproducto.setText("");
    }
    private void mcotizacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcotizacionMouseClicked
       if (JOptionPane.showConfirmDialog(null, "¿Desea Guardar Los Datos del la Nota?", "¿Guardar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

           
             agregar_cliente(txtid.getText());
             generarfoliocotizaion();
        String vvid_cliente = txtid.getText();
        String vfol = txtfolio.getText();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
        String vfecha_prestamo = formatoFecha.format(txtfechaP.getDate());
        String vfecha_devolucion = formatoFecha.format(txtfechaD.getDate());
        String estado = "A";
        String total_nota = txttotal.getText();
 String obs=txtobs.getText();
        cnnn.guardar_cotizac(accion, vfol, vfecha_prestamo, vfecha_devolucion, estado, total_nota, vvid_cliente,obs);
            String t_precio="";
           if( btnespecial.isSelected()){
               t_precio="E";
           }else if(btngeneral.isSelected()){
              t_precio="G";
           }

        actualizar_produc_cotizacion(vfol,t_precio);

        cargar_tabla_notascorte("");
        limpiar();
        bloquear();
       }
        // TODO add your handling code here:
    }//GEN-LAST:event_mcotizacionMouseClicked

    private void tbdatosnotasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbdatosnotasMouseEntered

    private void tbdatosnotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseClicked

        int filasel = tbdatosnotas.getSelectedRow();
        String a = (String) tbdatosnotas.getValueAt(filasel, 0);
        cargarnota(a);
        cargar_registros(a);
        mmodificar.setVisible(true);
        meliminar.setVisible(true);
        mreporte.setVisible(true);
        mcancelar.setVisible(true);
         mcancelarnota.setVisible(true);
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_tbdatosnotasMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int filasel = tbDatos.getSelectedRow();
        
        if (tbDatos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Registros");
        } else if (filasel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un Registro");
        } else {
                 if (JOptionPane.showConfirmDialog(null, "¿Desea Eliminar El Registro?", "¿Eliminar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            vId=tbDatos.getValueAt(filasel, 0).toString();
            cnnn.Eliminar_registro_nota(vId);
            DefaultTableModel dtm = (DefaultTableModel) tbDatos.getModel();
            dtm.removeRow(tbDatos.getSelectedRow());
            sumar_tabla();
                 }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btproductoActionPerformed

        
        Consultar_productos ceq = new Consultar_productos();
        Festum.escritorio.add(ceq);
        ceq.toFront();
        ceq.setVisible(true);
    }//GEN-LAST:event_btproductoActionPerformed

    private void txttotalproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalproductoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalproductoKeyReleased

    private void txttotalproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalproductoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalproductoKeyPressed

    private void txttotalproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalproductoActionPerformed

    private void txtprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyReleased

 if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
     txtprecio.setEditable(false);
     txtcantidad.requestFocus();
 }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyReleased

    private void txtprecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyPressed

    private void txtprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioActionPerformed

txtprecio.setEditable(true);

// TODO add your handling code here:
    }//GEN-LAST:event_txtprecioActionPerformed

    private void txtexistenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtexistenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtexistenciaKeyReleased

    private void txtexistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtexistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtexistenciaActionPerformed

    private void txtcantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyTyped
        char c = evt.getKeyChar();
        String Caracteres = txtcantidad.getText();
        if (Caracteres.length() >= 10) {
            evt.consume();
        }
        if (Character.isLetter(c)) {
            evt.consume();
        }
        if (Character.isSpace(c)) {
            evt.consume();

        }
        if (!Character.isDigit(c)) {
            evt.consume();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadKeyTyped

    private void txtcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyReleased
        String exis = txtcantidad.getText();
        if ("".equals(exis)) {
        } else {
            int existencia = Integer.parseInt(txtexistencia.getText());
            int canti = Integer.parseInt(txtcantidad.getText());
            if (existencia < canti) {
                
                
                if (JOptionPane.showConfirmDialog(null, "Solo Tienes "+existencia+" disponibles para este producto, ¿Deseas Agregarlos?, esto no afectara tu inventario", "¿Agregar?",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                }else{
                   String cants = String.valueOf(existencia);
                   txtcantidad.setText(cants); 
                }

                
                
                
            }
            String cnat = txtcantidad.getText();
            String preci = txtprecio.getText();
            Double valor1 = Double.parseDouble(cnat);
            Double valor2 = Double.parseDouble(preci);

            Double total = valor1 * valor2;
            String t = String.valueOf(total);
            txttotalproducto.setText(t);

        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadKeyReleased

    private void txtcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if ("0".equals(txtcantidad.getText())) {
                JOptionPane.showMessageDialog(null, "La cantidad no puede ser 0");
            } else {
                
                String tot;
                String a = txtclave.getText();
                int s = 2;
                int fila = 0;
                for (int i = 0; i < tbDatos.getRowCount(); i++) {
                    tot = tbDatos.getValueAt(i, 0).toString();
                    if (tot.equals(a)) {
                        s = 1;
                        fila = i;
                    } else {
                        s = 0;
                        fila = i;
                    }
                }
                if (s == 0 || tbDatos.getRowCount() == 0) {
                               String cnat = txtcantidad.getText();
            String preci = txtprecio.getText();
            Double valor1 = Double.parseDouble(cnat);
            Double valor2 = Double.parseDouble(preci);

            Double total = valor1 * valor2;
            String t = String.valueOf(total);
            txttotalproducto.setText(t);

                    llenar_tabla();
                    limpiar_prod();
                    bloquear_txt();
                    txtclave.setEditable(true);
                    txtclave.requestFocus();
                    acumular();
                } else if (s == 1) {
                    tbDatos.setValueAt(txtcantidad.getText(), fila, 3);
                    tbDatos.setValueAt(txttotalproducto.getText(), fila, 5);
                    limpiar_prod();
                }
            }
        }
    }//GEN-LAST:event_txtcantidadKeyPressed

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void txtclaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclaveKeyReleased
        String cadena = (txtclave.getText()).toUpperCase();
        txtclave.setText(cadena);        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveKeyReleased

    private void txtclaveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclaveKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String clave = txtclave.getText();
            ResultSet r = pd.consultarproduct_clave(clave);
            String vdescrip = "";
            String vcolor = "";
            String cantidad = "";
            String precio = "";
            String precio_especial = "";
            try {
                while (r.next()) {
                    vdescrip = r.getString("nombre_producto");
                    vcolor = r.getString("color");
                    cantidad = r.getString("cantidad");
                    precio = r.getString("precio");
                    precio_especial = r.getString("PRECIO_C");

                }
            } catch (SQLException ex) {
                Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (vdescrip == "") {
                JOptionPane.showMessageDialog(null, "La Clave " + clave + " No Existe");
                txtclave.setText("");

            } else {
                if (btngeneral.isSelected()) {
                    txtprecio.setText(precio);
                } else if (btnespecial.isSelected()) {
                    txtprecio.setText(precio_especial);
                }

                txtdescripcion.setText(vdescrip);
                txtexistencia.setText(cantidad);

                bloquear_txt();
                txtcantidad.setEditable(true);
                txtcantidad.requestFocus();

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveKeyPressed

    private void txtclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclaveActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveActionPerformed

    private void txtdescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String valor = txtdescripcion.getText();
            ResultSet rs = pd.consutar_product_descripcion(valor);
            String vclave = "";
            String vcolor = "";
            String cantidad = "";
            String precio = "";
            String precios_especial = "";
            try {
                while (rs.next()) {
                    vclave = rs.getString("Clave_producto");
                    vcolor = rs.getString("color");
                    cantidad = rs.getString("cantidad");
                    precio = rs.getString("precio");
                    precios_especial = rs.getString("PRECIO_C");
valor=rs.getString("nombre_producto");
                }
            } catch (SQLException ex) {
                Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
            }

            if ("".equals(vclave)) {
                JOptionPane.showMessageDialog(null, "El Producto no existe");
                txtcantidad.setText("");
            }
            {
                if (btngeneral.isSelected()) {
                    txtprecio.setText(precio);
                } else if (btnespecial.isSelected()) {
                    txtprecio.setText(precios_especial);
                }

                txtclave.setText(vclave);
                txtexistencia.setText(cantidad);
                txtcantidad.setEditable(true);
                txtcantidad.requestFocus();
                txtprecio.setEditable(true);
                txtdescripcion.setText(valor);
                
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyReleased

    private void txtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked
        int filasel = tbDatos.getSelectedRow();
        String clave = tbDatos.getValueAt(filasel, 1).toString();
        String deescrip = tbDatos.getValueAt(filasel, 2).toString();
   
        String existenca = tbDatos.getValueAt(filasel, 3).toString();
        String cantidad = tbDatos.getValueAt(filasel, 4).toString();
        String precio = tbDatos.getValueAt(filasel, 5).toString();
        String tota = tbDatos.getValueAt(filasel, 6).toString();
        txtclave.setEditable(true);

        txtclave.setText(clave);
        txtdescripcion.setText(deescrip);
        
        txtexistencia.setText(existenca);
        txtcantidad.setText(cantidad);
        txtprecio.setText(precio);
        txtcantidad.setEditable(true);
        txtcantidad.requestFocus();

        // TODO add your handling code here:
    }//GEN-LAST:event_tbDatosMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        Cons_clientes j = new Cons_clientes();
        escritorio.add(j);
        j.show();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtcpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcpKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpKeyPressed

    private void txtcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void btngeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btngeneralActionPerformed

    private void cxbeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxbeditarActionPerformed
if(cxbeditar.isSelected()){
 desbloquear_ingresar();

 

}else{
    bloquear_ingresar();
    txtid.setText("");
    }


        // TODO add your handling code here:
    }//GEN-LAST:event_cxbeditarActionPerformed

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        filtro(txtbuscar.getText().toUpperCase(), tbdatosnotas);        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped



if(!btngeneral.isSelected()&&!btnespecial.isSelected()){
    JOptionPane.showMessageDialog(null, "Selecciona el tipo del cliente");
    
}else{
    
}
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyTyped

    private void tbDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosKeyReleased
 if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
    sumar_tabla();
 }        // TODO add your handling code here:
    


        // TODO add your handling code here:
    }//GEN-LAST:event_tbDatosKeyReleased

    private void btncolorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncolorActionPerformed
Color color;
        JColorChooser Selectorcolor=new JColorChooser();
                color=Selectorcolor.showDialog(null, "Seleccione un Color", Color.BLUE);
                String hex = "#"+Integer.toHexString(color.getRGB()).substring(2);
                txtcolor.setText(hex);
                txtcolor.setBackground(color);


        
        // TODO add your handling code here:
    }//GEN-LAST:event_btncolorActionPerformed

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped

        char c = evt.getKeyChar();
        String Caracteres = txttelefono.getText();
        
        if (Caracteres.length() >= 10) {
            evt.consume();
        }
        if (Character.isLetter(c)) {
            evt.consume();
        }
        if (Character.isSpace(c)) {
            evt.consume();
            
        }
        if (!Character.isDigit(c)) {
            evt.consume();
            
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtcpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcpFocusGained




        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpFocusGained

    private void txtcpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcpFocusLost

            cmbcolonia.removeAllItems();

            String codif = txtcp.getText();
            String estado = "";
            String municiio = "";
            String colo = "";

            try {
                ResultSet rs = cp.consultar_cpp(codif);

                while (rs.next()) {
                    estado = rs.getString("estado");
                    municiio = rs.getString("municipio");
                    colo = rs.getString("colonia");
                }
            } // TODO add your handling code here:
            catch (SQLException ex) {
                Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
            }
            String dias = colo;
            String diaArray[] = dias.split(";");

            for (String dia : diaArray) {
                cmbcolonia.addItem(dia);
            }

            txtestado.setText(estado);
            txtmunicipio.setText(municiio);

        
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpFocusLost

    private void txtrazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonKeyTyped

        String Caracteres = txtrazon.getText();
        if (Caracteres.length() >= 50) {
            evt.consume();
        }



        // TODO add your handling code here:
    }//GEN-LAST:event_txtrazonKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        String Caracteres = txtdireccion.getText();
        if (Caracteres.length() >= 70) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txtneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtneKeyTyped
        String Caracteres = txtne.getText();
        if (Caracteres.length() >= 10) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtneKeyTyped

    private void txtreferenciasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferenciasKeyTyped
        String Caracteres = txtreferencias.getText();
        if (Caracteres.length() >= 100) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasKeyTyped

    private void txtobsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobsActionPerformed

    private void txtobsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobsKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobsKeyPressed

    private void txtobsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobsKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobsKeyReleased

    private void txtobsKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobsKeyTyped
        String Caracteres = txtobs.getText();
        if (Caracteres.length() >= 70) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobsKeyTyped

    private void mcancelarnotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcancelarnotaMouseClicked
    if (JOptionPane.showConfirmDialog(null, "¿Cancelar Nota?", "¿Eliminar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
cnnn.modificarCancelar_NOTA(txtfolio.getText(), "C");
bloquear();
     limpiar();
        cargar_tabla_notascorte("");
    }

        // TODO add your handling code here:
    }//GEN-LAST:event_mcancelarnotaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
CP j = new CP();
        escritorio.add(j);
        j.show();


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncolor;
    public static javax.swing.JRadioButton btnespecial;
    public static javax.swing.JRadioButton btngeneral;
    private javax.swing.JButton btproducto;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    public static javax.swing.JComboBox<String> cmbcolonia;
    private javax.swing.JCheckBox cxbeditar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JMenu mcancelar;
    private javax.swing.JMenu mcancelarnota;
    private javax.swing.JMenu mcotizacion;
    private javax.swing.JMenu meliminar;
    private javax.swing.JMenu mguardar;
    private javax.swing.JMenu mmodificar;
    private javax.swing.JMenu mnuevo;
    private javax.swing.JMenu mreporte;
    private javax.swing.JMenu msalir;
    private javax.swing.JPanel panel;
    public static javax.swing.JTable tbDatos;
    private javax.swing.JTable tbdatosnotas;
    private javax.swing.JTextField txtbuscar;
    public static javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtcolor;
    public static javax.swing.JTextField txtcp;
    public static javax.swing.JTextField txtdescripcion;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtestado;
    public static javax.swing.JTextField txtexistencia;
    private com.toedter.calendar.JDateChooser txtfechaD;
    private com.toedter.calendar.JDateChooser txtfechaP;
    private javax.swing.JTextField txtfolio;
    public static javax.swing.JTextField txtid;
    public static javax.swing.JTextField txtmunicipio;
    public static javax.swing.JTextField txtne;
    public static javax.swing.JTextField txtobs;
    public static javax.swing.JTextField txtprecio;
    public static javax.swing.JTextField txtrazon;
    public static javax.swing.JTextField txtreferencias;
    public static javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttotal;
    public static javax.swing.JTextField txttotalproducto;
    // End of variables declaration//GEN-END:variables
}
