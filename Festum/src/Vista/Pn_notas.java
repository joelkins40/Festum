/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_CP;

import Controlador.controlador_cliente;
import Controlador.controlador_notas;
import Controlador.controlador_producto;
import Controlador.crearnotas;

import Utilerias.CambiaPanel;



import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import javax.swing.table.TableRowSorter;

/**
 *
 * @author RojeruSan
 */
public class Pn_notas extends javax.swing.JPanel {

    DefaultTableModel model;
    controlador_cliente j = new controlador_cliente();
    Controlador.controlador_CP cp = new controlador_CP();
    controlador_notas cnnn = new controlador_notas();
    controlador_producto pd = new controlador_producto();
    String accion_cliente = "R";
    String accion = "";
    String vId;
    String vRazon;
    String vDireccion;
    String vNumero_Ext;
    String vNumero_Int = "";
    String vColonia;
    String vreferencias;
    String vCP;
    String vtelefono;
    String vciudad;
    String nombreEvento;
    crearnotas nt = new crearnotas();

    /**
     * Creates new form pnlHome
     */
    public Pn_notas() {
        initComponents();
        RowApariencia();
        RowApariencia();
         limpiar();
        bloquear();
        cargar_tabla_notascorte("");
        bloquear_txt();
        auto();
     
    }

    public void imprimircrearnota(String id) {
        int filas = tbDatos.getRowCount();
        if (filas > 13) {
            nt.crear(id);
            if (JOptionPane.showConfirmDialog(null, "¿Desea imprimir toda la nota?", "¿Imprimir?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                cnnn.cargar_nota_todosalv(id);
            }
        } else {
            nt.crear(id);

        }

    }

    void generarfolio_cliente() {
        ResultSet rs = j.SELECTMAXID();
        String folioc = "";
        try {
            while (rs.next()) {
                folioc = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
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
        int filasel =tbDatos.getSelectedRow();
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
void cargar_datos_autorizacion(){
    tbConfirmacion.setEnabled(true);
   
txteventocon.setEditable(true);
txtcliente.setEditable(true);
txtdireccioncon.setEditable(true);
txtfechareco.setEnabled(true);
txtfechaco.setEnabled(true);
txtxtotalcon.setEditable(true);

DefaultTableModel ms=new  DefaultTableModel();
ms=(DefaultTableModel) tbDatos.getModel();

        
tbConfirmacion.setModel(ms);

TableColumn tcol = tbConfirmacion.getColumnModel().getColumn(0); 
TableColumn tco2 = tbConfirmacion.getColumnModel().getColumn(1);      
      TableColumn tco3 = tbConfirmacion.getColumnModel().getColumn(3); 
tbConfirmacion.getColumnModel().removeColumn(tcol); 
      tbConfirmacion.getColumnModel().removeColumn(tco2);
      tbConfirmacion.getColumnModel().removeColumn(tco3);

      TableColumnModel colummodel = tbConfirmacion.getColumnModel();
       colummodel.getColumn(0).setPreferredWidth(200);
    
      
txteventocon.setText(txtnombreevento.getText());
txtcliente.setText(txtrazon.getText());
txtdireccioncon.setText(txtdireccion.getText()+"  No: "+txtne.getText()+", Colonia: "+cmbcolonia.getSelectedItem().toString()+", "+txtmunicipio.getText()+", Ref: "+txtreferencias.getText()+", Tel: "+txttelefono.getText());
txtfechaco.setDate(txtfechaD.getDate());
     txtfechareco.setDate(   txtfechaD.getDate());
     txtxtotalcon.setText(txttotal.getText());
     tbConfirmacion.setEnabled(false);
txteventocon.setEditable(false);
txtcliente.setEditable(false);
txtdireccioncon.setEditable(false);
txtfechareco.setEnabled(false);
txtfechaco.setEnabled(false);
txtxtotalcon.setEditable(false);
jTabbedPane1.setSelectedIndex(2); 
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
            Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void limpiar_tabla() {
        String[] titulos = {"ID", "Clave", "Descripcion", "Exist", "Cant", "Precio", "Total"};
        String[] registros = new String[6];
        DefaultTableModel we = new DefaultTableModel(null, titulos);
        tbDatos.setModel(we);
        TableColumnModel colummodel = tbDatos.getColumnModel();
        colummodel.getColumn(0).setMaxWidth(1);
        colummodel.getColumn(2).setPreferredWidth(600);
        colummodel.getColumn(1).setPreferredWidth(15);
        colummodel.getColumn(3).setPreferredWidth(10);
        colummodel.getColumn(4).setPreferredWidth(10);
        colummodel.getColumn(5).setPreferredWidth(10);
    }

    void sumar_tabla() {
        int total = tbDatos.getRowCount();
        Double contador = 0.0;
        for (int i = 0; i < total; i++) {
            Double a = Double.parseDouble((String) tbDatos.getValueAt(i, 4));
            Double b = Double.parseDouble((String) tbDatos.getValueAt(i, 5));
            String ttl = String.valueOf(a * b);
            tbDatos.setValueAt(ttl, i, 6);

        }
        for (int i = 0; i < total; i++) {
            Double a = Double.parseDouble((String)tbDatos.getValueAt(i, 6));
            contador = contador + a;

        }
        txttotal.setText(String.valueOf(contador));
    }

    void llenar_tabla() {
        DefaultTableModel tabladet = (DefaultTableModel)tbDatos.getModel();
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
        
        btproducto.setEnabled(false);
txtnombreevento.setEditable(false);
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
        
       
        mcancelar.setVisible(true);
        mcotizacion.setVisible(false);
        mreporte.setVisible(false);
        mcotizacion.setVisible(false);
        mcancelar.setVisible(false);
        tbDatos.setEnabled(false);
    }

    void actualizar_produc_notas(String folio, String t_precio) {
        for (int i = 0; i < tbDatos.getRowCount(); i++) {
            String cond = tbDatos.getValueAt(i, 0).toString();
            String id_producto = (String) tbDatos.getValueAt(i, 1);
            String descripcion_producto = (String)tbDatos.getValueAt(i, 2);

            String cantidad = this.tbDatos.getValueAt(i, 4).toString();
            String precio = this.tbDatos.getValueAt(i, 5).toString();

            String total = this.tbDatos.getValueAt(i, 6).toString();
            ResultSet rs = pd.consultarproduct_clave(id_producto);
            String id = "";
            int exi = 0;
            try {
                while (rs.next()) {
                    id_producto = rs.getString(1);
                    exi = rs.getInt("cantidad");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
            }
            int cantid = Integer.parseInt(cantidad);
            int tot;
            if (exi < cantid) {
                tot = 0;
            } else {
                tot = exi - cantid;
            }

            String s = String.valueOf(tot);
            if ("".equals(cond)) {
                accion = "I";
            } else {
                accion = "M";

            }

            cnnn.guardar_nota_prod(accion, cond, id_producto, cantidad, total, folio, precio, t_precio, descripcion_producto);

        }
    }

    void actualizar_produc_cotizacion(String folio, String t_precio) {
        for (int i = 0; i < tbDatos.getRowCount(); i++) {
            String cond = tbDatos.getValueAt(i, 0).toString();
            String id_f = (String) tbDatos.getValueAt(i, 1);
            String descripcion_producto = (String)tbDatos.getValueAt(i, 2);

            String cantidad = this.tbDatos.getValueAt(i, 4).toString();
            String precio = this.tbDatos.getValueAt(i, 5).toString();

            String total = this.tbDatos.getValueAt(i, 6).toString();
            ResultSet rs = pd.consultarproduct_clave(id_f);
            String producto = "";
            int exi = 0;
            try {
                while (rs.next()) {
                    producto = rs.getString(1);
                    exi = rs.getInt("cantidad");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
            }
            int cantid = Integer.parseInt(cantidad);
            int tot;
            if (exi < cantid) {
                tot = 0;
            } else {
                tot = exi - cantid;
            }

            String s = String.valueOf(tot);
            if ("".equals(cond)) {
                accion = "I";
            } else {
                accion = "M";

            }

            cnnn.guardar_nota_cotizacion(accion, cond, producto, cantidad, total, folio, precio, t_precio, descripcion_producto);

        }

    }

    void agregar_cliente(String id_C) {

        ResultSet rs = j.consultarclient(id_C);
        String as = "";
        try {
            while (rs.next()) {
                as = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("".equals(as)) {
            as = "I";
            generarfolio_cliente();

        } else {
            as = "M";

        }
        vId = txtid.getText();
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
        mguardar.setVisible(true);
        meliminar.setVisible(false);
        mcancelar.setVisible(false);
        mcotizacion.setVisible(true);
        mcancelar.setVisible(true);
        mcotizacion.setVisible(true);
        tbDatos.setEnabled(true);
        txtdescripcion.setEditable(true);
        txtnombreevento.setEditable(true);
        setEnabled(true);
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

        for (int i = 0; i <tbDatos.getRowCount(); i++) {

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
            Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
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
        txtnombreevento.setText("");
        limpiar_tabla();
    }

    void cargar_tabla_notascorte(String valor) {
        DefaultTableModel tb = cnnn.cargar_tabla_notas();
        tbdatosnotas.setModel(tb);
        TableColumnModel colummodel = tbdatosnotas.getColumnModel();
        colummodel.getColumn(0).setPreferredWidth(5);
        colummodel.getColumn(1).setPreferredWidth(150);
        colummodel.getColumn(2).setPreferredWidth(150);
        colummodel.getColumn(6).setPreferredWidth(10);
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
        TableColumnModel colummodel = tbDatos.getColumnModel();
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
        String obs = "";
        try {
            while (rs.next()) {
                fep = rs.getString("fecha_prestamo");
                fed = rs.getString("fecha_devolucion");
                totalnota = rs.getString("total_nota");
                clie = rs.getString("id_cliente");
                nombreEvento = rs.getString("nombreEvento");
                obs = rs.getString("obs");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Pn_notas.class.getName()).log(Level.SEVERE, null, ex);
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
       txtnombreevento.setText(nombreEvento);
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

    public void RowApariencia() {

        tbDatos.setFocusable(false);
        //espacio entre comulnas
       tbDatos.setIntercellSpacing(new Dimension(0, 1));
        //altura de columnas 
       tbDatos.setRowHeight(25);
        //margen entre filas
       tbDatos.setRowMargin(0);
        //sin lineas verticles
        tbDatos.setShowVerticalLines(false);
       tbDatos.setSelectionBackground(new Color(97, 212, 195));
    }

    public void RowHeaderApariencia() {
      tbDatos.getTableHeader().setFont(new Font("Century Gothic", Font.BOLD, 14));
       tbDatos.getTableHeader().setOpaque(false);
       tbDatos.getTableHeader().setBackground(new Color(97, 212, 195));
        tbDatos.getTableHeader().setForeground(new Color(255, 255, 255));

    }

    void limpiar_prod() {
        txtclave.setText("");
        txtdescripcion.setText("");
        txtexistencia.setText("");
        txtprecio.setText("");
        txtcantidad.setText("");
        txttotalproducto.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator6 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jl_atras = new javax.swing.JLabel();
        jt_category = new javax.swing.JTabbedPane();
        jp_database = new javax.swing.JPanel();
        t_cargo2 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdatosnotas = new javax.swing.JTable();
        mnuevo1 = new Utilerias.RSButtonMetro();
        jp_captura = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
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
        panel = new javax.swing.JPanel();
        txtfechaD = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtfechaP = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtfolio = new javax.swing.JTextField();
        txtnombreevento = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        txtdescripcion = new javax.swing.JTextField();
        txtclave = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        txtexistencia = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        txttotalproducto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtobs = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        btproducto = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txteventocon = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtcliente = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbConfirmacion = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        mguardar = new Utilerias.RSButtonMetro();
        mcotizacion = new Utilerias.RSButtonMetro();
        mcancelar = new Utilerias.RSButtonMetro();
        meliminar = new Utilerias.RSButtonMetro();
        mreporte = new Utilerias.RSButtonMetro();
        mreporte1 = new Utilerias.RSButtonMetro();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtdireccioncon = new javax.swing.JTextArea();
        jLabel23 = new javax.swing.JLabel();
        txtfechareco = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        txtfechaco = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();
        txtxtotalcon = new javax.swing.JTextField();
        mnuevo = new Utilerias.RSButtonMetro();

        jSeparator6.setBackground(new java.awt.Color(128, 128, 131));

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(128, 128, 131));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/archivocargosmall.png"))); // NOI18N
        jLabel8.setText("Modulo de Notas");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 860, -1));

        jl_atras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/left-arrow.png"))); // NOI18N
        jl_atras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jl_atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jl_atrasMouseClicked(evt);
            }
        });
        add(jl_atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jp_database.setBackground(new java.awt.Color(255, 255, 255));

        t_cargo2.setForeground(new java.awt.Color(153, 153, 153));
        t_cargo2.setText("Buscar Evento");
        t_cargo2.setBorder(null);
        t_cargo2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                t_cargo2FocusLost(evt);
            }
        });
        t_cargo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_cargo2MouseClicked(evt);
            }
        });
        t_cargo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_cargo2ActionPerformed(evt);
            }
        });
        t_cargo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_cargo2KeyReleased(evt);
            }
        });

        jSeparator7.setBackground(new java.awt.Color(128, 128, 131));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(128, 128, 131));
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Buscar");

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

        mnuevo1.setBackground(new java.awt.Color(97, 212, 195));
        mnuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        mnuevo1.setText("Nuevo");
        mnuevo1.setColorHover(new java.awt.Color(128, 128, 131));
        mnuevo1.setColorNormal(new java.awt.Color(97, 212, 195));
        mnuevo1.setIconTextGap(25);
        mnuevo1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mnuevo1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mnuevo1FocusLost(evt);
            }
        });
        mnuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuevo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_databaseLayout = new javax.swing.GroupLayout(jp_database);
        jp_database.setLayout(jp_databaseLayout);
        jp_databaseLayout.setHorizontalGroup(
            jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_databaseLayout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_databaseLayout.createSequentialGroup()
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(504, Short.MAX_VALUE))
                    .addGroup(jp_databaseLayout.createSequentialGroup()
                        .addComponent(t_cargo2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))))
            .addGroup(jp_databaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_databaseLayout.setVerticalGroup(
            jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_databaseLayout.createSequentialGroup()
                .addGroup(jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_databaseLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(t_cargo2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(mnuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );

        jt_category.addTab("Consultar clientes", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jp_database); // NOI18N

        jp_captura.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setEnabled(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(36, 47, 97));
        jLabel14.setText("Municipio");

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
        txtestado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtestadoActionPerformed(evt);
            }
        });

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

        btnespecial.setBackground(new java.awt.Color(255, 255, 255));
        btnespecial.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnespecial.setForeground(new java.awt.Color(36, 47, 97));
        btnespecial.setText("Especial");

        btngeneral.setBackground(new java.awt.Color(255, 255, 255));
        btngeneral.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btngeneral.setForeground(new java.awt.Color(36, 47, 97));
        btngeneral.setSelected(true);
        btngeneral.setText("General");
        btngeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneralActionPerformed(evt);
            }
        });

        cxbeditar.setBackground(new java.awt.Color(255, 255, 255));
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

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha del evento"));

        jLabel16.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(36, 47, 97));
        jLabel16.setText("Fecha de Devolucion");

        jLabel17.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(36, 47, 97));
        jLabel17.setText("Fecha de Prestamo");

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 47, 97));
        jLabel1.setText("FOLIO");

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
                    .addComponent(jLabel17)
                    .addComponent(txtfechaP, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfechaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jLabel12)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(cxbeditar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btngeneral)
                                .addGap(52, 52, 52)
                                .addComponent(btnespecial)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtreferencias, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
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
                                                .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                                    .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(10, 10, 10))
                                                .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(25, 25, 25))))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cxbeditar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(jLabel14)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(9, 9, 9))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(jLabel9)
                                                    .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cmbcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel18)
                                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(16, 16, 16)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnespecial)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btngeneral)
                                        .addComponent(jLabel19))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(316, 316, 316))
        );

        txtnombreevento.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        txtnombreevento.setForeground(new java.awt.Color(36, 47, 97));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 97));
        jLabel5.setText("Nombre del Evento:");

        jButton2.setText("Siguiente");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtnombreevento, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jButton2)
                        .addGap(79, 79, 79))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombreevento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Datos del Cliente", jPanel2);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttotalproducto))
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
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(36, 47, 97));
        jLabel7.setText("Observaciones");

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

        jButton4.setBackground(new java.awt.Color(103, 210, 190));
        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(36, 47, 97));
        jButton4.setText("Eliminar Producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(36, 47, 97));
        jLabel15.setText("Total");

        txttotal.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        txttotal.setForeground(new java.awt.Color(36, 47, 97));

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

        jButton5.setText("Siguiente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Atras");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btproducto)
                        .addGap(83, 83, 83)
                        .addComponent(jButton5)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(74, 74, 74)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(btproducto)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Productos", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setOpaque(false);

        jLabel20.setText("Nombre del Evento:");

        txteventocon.setOpaque(false);

        jLabel21.setText("Cliente");

        txtcliente.setOpaque(false);

        jLabel22.setText("Direccion");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Lista de Productos"));

        tbConfirmacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbConfirmacion.setOpaque(false);
        jScrollPane1.setViewportView(tbConfirmacion);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Menu de Opciones"));

        mguardar.setBackground(new java.awt.Color(97, 212, 195));
        mguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/save30x30.png"))); // NOI18N
        mguardar.setText("Guardar");
        mguardar.setColorHover(new java.awt.Color(128, 128, 131));
        mguardar.setColorNormal(new java.awt.Color(97, 212, 195));
        mguardar.setIconTextGap(25);
        mguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mguardarActionPerformed(evt);
            }
        });

        mcotizacion.setBackground(new java.awt.Color(97, 212, 195));
        mcotizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/money-transfer.png"))); // NOI18N
        mcotizacion.setText("Cotización");
        mcotizacion.setColorHover(new java.awt.Color(128, 128, 131));
        mcotizacion.setColorNormal(new java.awt.Color(97, 212, 195));
        mcotizacion.setIconTextGap(25);
        mcotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcotizacionActionPerformed(evt);
            }
        });

        mcancelar.setBackground(new java.awt.Color(97, 212, 195));
        mcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/edit-file.png"))); // NOI18N
        mcancelar.setText("Editar");
        mcancelar.setColorHover(new java.awt.Color(128, 128, 131));
        mcancelar.setColorNormal(new java.awt.Color(97, 212, 195));
        mcancelar.setIconTextGap(25);
        mcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcancelarActionPerformed(evt);
            }
        });

        meliminar.setBackground(new java.awt.Color(97, 212, 195));
        meliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/erase.png"))); // NOI18N
        meliminar.setText("Limpiar");
        meliminar.setColorHover(new java.awt.Color(128, 128, 131));
        meliminar.setColorNormal(new java.awt.Color(97, 212, 195));
        meliminar.setIconTextGap(25);
        meliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meliminarActionPerformed(evt);
            }
        });

        mreporte.setBackground(new java.awt.Color(97, 212, 195));
        mreporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/print.png"))); // NOI18N
        mreporte.setText("Imprimir nota");
        mreporte.setColorHover(new java.awt.Color(128, 128, 131));
        mreporte.setColorNormal(new java.awt.Color(97, 212, 195));
        mreporte.setIconTextGap(25);
        mreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mreporteActionPerformed(evt);
            }
        });

        mreporte1.setBackground(new java.awt.Color(97, 212, 195));
        mreporte1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/cancel-subscription.png"))); // NOI18N
        mreporte1.setText("Cancelar Nota");
        mreporte1.setColorHover(new java.awt.Color(128, 128, 131));
        mreporte1.setColorNormal(new java.awt.Color(97, 212, 195));
        mreporte1.setIconTextGap(25);
        mreporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mreporte1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mreporte1, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                    .addComponent(mreporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(meliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mcancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mcotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(mguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mcotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(meliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mreporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mreporte1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtdireccioncon.setColumns(20);
        txtdireccioncon.setLineWrap(true);
        txtdireccioncon.setRows(5);
        txtdireccioncon.setOpaque(false);
        jScrollPane4.setViewportView(txtdireccioncon);

        jLabel23.setText("Fecha");

        txtfechareco.setOpaque(false);

        jLabel25.setText("Recoleccion");

        txtfechaco.setOpaque(false);

        jLabel26.setText("Total");

        txtxtotalcon.setText("0.0");
        txtxtotalcon.setOpaque(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txteventocon, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(txtcliente)
                            .addComponent(jScrollPane4)))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addComponent(txtfechaco, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfechareco, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtxtotalcon, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtfechaco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtfechareco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(txteventocon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(txtxtotalcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))))))
        );

        jTabbedPane1.addTab("Confirmación", jPanel4);

        mnuevo.setBackground(new java.awt.Color(97, 212, 195));
        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        mnuevo.setText("Nuevo");
        mnuevo.setColorHover(new java.awt.Color(128, 128, 131));
        mnuevo.setColorNormal(new java.awt.Color(97, 212, 195));
        mnuevo.setIconTextGap(25);
        mnuevo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                mnuevoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                mnuevoFocusLost(evt);
            }
        });
        mnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuevoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_capturaLayout = new javax.swing.GroupLayout(jp_captura);
        jp_captura.setLayout(jp_capturaLayout);
        jp_capturaLayout.setHorizontalGroup(
            jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_capturaLayout.createSequentialGroup()
                .addContainerGap(593, Short.MAX_VALUE)
                .addComponent(mnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(290, 290, 290))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 995, Short.MAX_VALUE)
        );
        jp_capturaLayout.setVerticalGroup(
            jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_capturaLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        jt_category.addTab("Captura", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-dishwasher.png")), jp_captura); // NOI18N

        add(jt_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 1000, 480));
    }// </editor-fold>//GEN-END:initComponents

    private void mnuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoFocusGained

    private void mnuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoFocusLost

    private void mnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuevoActionPerformed

        jt_category.setSelectedIndex(1);
        desbloquear();
        limpiar();
        accion = "I";
        generarfolio();

    }//GEN-LAST:event_mnuevoActionPerformed

    private void mcotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcotizacionActionPerformed

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


    }//GEN-LAST:event_mcotizacionActionPerformed

    private void mcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcancelarActionPerformed
jTabbedPane1.setSelectedIndex(0);
desbloquear();

        // TODO add your handling code here:
    }//GEN-LAST:event_mcancelarActionPerformed

    private void jl_atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_atrasMouseClicked
        Principal acceso = new Principal();
        new CambiaPanel(acceso.pnlPrincipal, new Vista.Pn_notas());
        // TODO add your handling code here:
    }//GEN-LAST:event_jl_atrasMouseClicked

    private void t_cargo2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cargo2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2FocusLost

    private void t_cargo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_cargo2MouseClicked
        if (t_cargo2.getText().equals("Buscar Clientes")) {
            t_cargo2.setText("");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2MouseClicked

    private void t_cargo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cargo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2ActionPerformed

    private void meliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meliminarActionPerformed
limpiar();
generarfolio();

       

        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_meliminarActionPerformed

    private void t_cargo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cargo2KeyReleased
        filtro(t_cargo2.getText().toUpperCase(), tbdatosnotas);        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2KeyReleased

    private void txtrazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonKeyTyped

        String Caracteres = txtrazon.getText();
        if (Caracteres.length() >= 50) {
            evt.consume();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtrazonKeyTyped

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

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

    private void txtcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpActionPerformed

    private void txtcpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcpKeyPressed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpKeyPressed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasActionPerformed

    private void txtreferenciasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtreferenciasKeyTyped
        String Caracteres = txtreferencias.getText();
        if (Caracteres.length() >= 100) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
 Pn_ConsultarCliente ceq = new Pn_ConsultarCliente();
        ceq.setVisible(true);
      
    }//GEN-LAST:event_jButton3ActionPerformed

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

    private void btngeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btngeneralActionPerformed

    private void cxbeditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cxbeditarActionPerformed
        if (cxbeditar.isSelected()) {
            desbloquear_ingresar();

        } else {
            bloquear_ingresar();
            txtid.setText("");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cxbeditarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
PN_CodigoPostal ceq = new PN_CodigoPostal();
        ceq.setVisible(true);       
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtestadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtestadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtestadoActionPerformed

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

    private void tbDatosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbDatosKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            sumar_tabla();
        }        // TODO add your handling code here:

        // TODO add your handling code here:
    }//GEN-LAST:event_tbDatosKeyReleased

    private void txtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionActionPerformed

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
                    valor = rs.getString("nombre_producto");
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

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped

        if (!btngeneral.isSelected() && !btnespecial.isSelected()) {
            JOptionPane.showMessageDialog(null, "Selecciona el tipo del cliente");

        } else {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyTyped

    private void txtclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclaveActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveActionPerformed

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

    private void txtclaveKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclaveKeyReleased
        String cadena = (txtclave.getText()).toUpperCase();
        txtclave.setText(cadena);        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveKeyReleased

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

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

    private void txtcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyReleased
        String exis = txtcantidad.getText();
        if ("".equals(exis)) {
        } else {
            int existencia = Integer.parseInt(txtexistencia.getText());
            int canti = Integer.parseInt(txtcantidad.getText());
            if (existencia < canti) {

                if (JOptionPane.showConfirmDialog(null, "Solo Tienes " + existencia + " disponibles para este producto, ¿Deseas Agregarlos?, esto no afectara tu inventario", "¿Agregar?",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                } else {
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

    private void txtexistenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtexistenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtexistenciaActionPerformed

    private void txtexistenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtexistenciaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtexistenciaKeyReleased

    private void txtprecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtprecioActionPerformed

        txtprecio.setEditable(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioActionPerformed

    private void txtprecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyPressed

    private void txtprecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyReleased

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txtprecio.setEditable(false);
            txtcantidad.requestFocus();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyReleased

    private void txttotalproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttotalproductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalproductoActionPerformed

    private void txttotalproductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalproductoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalproductoKeyPressed

    private void txttotalproductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttotalproductoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txttotalproductoKeyReleased

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

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int filasel = tbDatos.getSelectedRow();

        if (tbDatos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Registros");
        } else if (filasel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un Registro");
        } else {
            if (JOptionPane.showConfirmDialog(null, "¿Desea Eliminar El Registro?", "¿Eliminar?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                vId = tbDatos.getValueAt(filasel, 0).toString();
                cnnn.Eliminar_registro_nota(vId);
                DefaultTableModel dtm = (DefaultTableModel) tbDatos.getModel();
                dtm.removeRow(tbDatos.getSelectedRow());
                sumar_tabla();
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btproductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btproductoActionPerformed

        Emergence ceq = new Emergence();
        ceq.setVisible(true);
    }//GEN-LAST:event_btproductoActionPerformed

    private void mguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mguardarActionPerformed

        int g = validar_campos();
        if (g == 1) {
            JOptionPane.showMessageDialog(null, "Faltan Campos por Completar en Cliente");
        } else {
            String ssa = txtrazon.getText();
            int qsa = tbDatos.getRowCount();
            if (qsa == 0) {
                JOptionPane.showMessageDialog(null, "La Tabla no puede estar vacia");
            } else if ("".equals(ssa)) {
                JOptionPane.showMessageDialog(null, "Faltan Datos del Cliente");
            } else {

                if (JOptionPane.showConfirmDialog(null, "¿Guardar datos de la nota?", "¿Guardar?",
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
                    nombreEvento = txtnombreevento.getText();
                    String obs = txtobs.getText();

                    cnnn.guardar_nota(accion, vfol, vfecha_prestamo, vfecha_devolucion, estado, total_nota, vvid_cliente, nombreEvento, obs);

                    String t_precio = "";
                    if (btnespecial.isSelected()) {
                        t_precio = "E";
                    } else if (btngeneral.isSelected()) {
                        t_precio = "G";
                    }
                    actualizar_produc_notas(vfol, t_precio);
                    cargar_tabla_notascorte("");
                    bloquear();
                    bloquear_ingresar();
                    jTabbedPane1.setSelectedIndex(2);

                    mreporte.setVisible(true);
                   mguardar.setVisible(false);
                   meliminar.setVisible(true);
                    cargar_registros(vfol);

                }
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarActionPerformed

    private void tbdatosnotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseClicked

        int filasel = tbdatosnotas.getSelectedRow();
        String a = (String) tbdatosnotas.getValueAt(filasel, 0);
        cargarnota(a);
        cargar_registros(a);
        accion="M";
       mguardar.setText("Actualizar");
        meliminar.setVisible(true);
        mreporte.setVisible(true);
        mcancelar.setVisible(true);
       jt_category.setSelectedIndex(1);
        jTabbedPane1.setSelectedIndex(2);
        cargar_datos_autorizacion();
    }//GEN-LAST:event_tbdatosnotasMouseClicked

    private void tbdatosnotasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbdatosnotasMouseEntered

    private void mreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mreporteActionPerformed
imprimircrearnota(txtfolio.getText()); 

// TODO add your handling code here:
    }//GEN-LAST:event_mreporteActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
jTabbedPane1.setSelectedIndex(1);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
DefaultTableModel we = new DefaultTableModel();
tbConfirmacion.setModel(we);
        cargar_datos_autorizacion();       // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
jTabbedPane1.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void mnuevo1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevo1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevo1FocusGained

    private void mnuevo1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevo1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevo1FocusLost

    private void mnuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuevo1ActionPerformed
 jt_category.setSelectedIndex(1);
        desbloquear();
        limpiar();
        accion = "I";
        generarfolio();
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevo1ActionPerformed

    private void mreporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mreporte1ActionPerformed
if (JOptionPane.showConfirmDialog(null, "¿Cancelar nota?", "¿Eliminar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
cnnn.modificarCancelar_NOTA(txtfolio.getText(), "C");
bloquear();
     limpiar();
        cargar_tabla_notascorte("");
    }        // TODO add your handling code here:
    }//GEN-LAST:event_mreporte1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JRadioButton btnespecial;
    public static javax.swing.JRadioButton btngeneral;
    private javax.swing.JButton btproducto;
    public static javax.swing.JComboBox<String> cmbcolonia;
    private javax.swing.JCheckBox cxbeditar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jl_atras;
    private javax.swing.JPanel jp_captura;
    private javax.swing.JPanel jp_database;
    private javax.swing.JTabbedPane jt_category;
    private Utilerias.RSButtonMetro mcancelar;
    private Utilerias.RSButtonMetro mcotizacion;
    private Utilerias.RSButtonMetro meliminar;
    private Utilerias.RSButtonMetro mguardar;
    private Utilerias.RSButtonMetro mnuevo;
    private Utilerias.RSButtonMetro mnuevo1;
    private Utilerias.RSButtonMetro mreporte;
    private Utilerias.RSButtonMetro mreporte1;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField t_cargo2;
    private javax.swing.JTable tbConfirmacion;
    public static javax.swing.JTable tbDatos;
    private javax.swing.JTable tbdatosnotas;
    public static javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtcliente;
    public static javax.swing.JTextField txtcp;
    public static javax.swing.JTextField txtdescripcion;
    public static javax.swing.JTextField txtdireccion;
    private javax.swing.JTextArea txtdireccioncon;
    public static javax.swing.JTextField txtestado;
    private javax.swing.JTextField txteventocon;
    public static javax.swing.JTextField txtexistencia;
    private com.toedter.calendar.JDateChooser txtfechaD;
    private com.toedter.calendar.JDateChooser txtfechaP;
    private com.toedter.calendar.JDateChooser txtfechaco;
    private com.toedter.calendar.JDateChooser txtfechareco;
    private javax.swing.JTextField txtfolio;
    public static javax.swing.JTextField txtid;
    public static javax.swing.JTextField txtmunicipio;
    public static javax.swing.JTextField txtne;
    public static javax.swing.JTextField txtnombreevento;
    public static javax.swing.JTextField txtobs;
    public static javax.swing.JTextField txtprecio;
    public static javax.swing.JTextField txtrazon;
    public static javax.swing.JTextField txtreferencias;
    public static javax.swing.JTextField txttelefono;
    private javax.swing.JTextField txttotal;
    public static javax.swing.JTextField txttotalproducto;
    private javax.swing.JTextField txtxtotalcon;
    // End of variables declaration//GEN-END:variables
}
