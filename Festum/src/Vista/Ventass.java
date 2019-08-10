/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_CP;
import Controlador.controlador_Ventas;
import Controlador.controlador_cliente;
import Controlador.controlador_notas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author hassiel
 */
public class Ventass extends javax.swing.JInternalFrame {
controlador_Ventas c=new controlador_Ventas();
    controlador_cliente j=new controlador_cliente();
    controlador_notas cnnn=new controlador_notas();
       Controlador.controlador_CP cp = new controlador_CP();
           String accion;
    String vId;
    String vRazon;
    String vDireccion;
    String vNumero_Ext;
    String vNumero_Int;
    String vColonia;
    String vreferencias;
    String vCP;
    
    /**
     * Creates new form Ventass
     */
    public Ventass() {
        initComponents();
        cargar_tabla_Ventas("T","", "");
                Calendar c2 = new GregorianCalendar();
        txtfechaP.setCalendar(c2);
        txtfechaD.setCalendar(c2);

    }
void cargar_tabla_Ventas(String accion,String FE,String FE2){
    DefaultTableModel model=c.cargar_tabla_Ventas(accion,FE, FE2);
    tbdatosnotas.setModel(model);
    sumar_tabla();
    TableColumnModel colummodel= tbdatosnotas.getColumnModel();
       colummodel.getColumn(0).setPreferredWidth(5);
        colummodel.getColumn(1).setPreferredWidth(300);
        colummodel.getColumn(2).setPreferredWidth(10);
        colummodel.getColumn(3).setPreferredWidth(10);
     colummodel.getColumn(4).setPreferredWidth(10);    
        colummodel.getColumn(5).setPreferredWidth(10);
}
    DefaultTableModel dm;
    private void filtro(String consulta, JTable jtableBuscar) {
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
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

    void limpiar_tabla() {
        DefaultTableModel modelo = (DefaultTableModel) tbDatos.getModel();
        int filas = tbDatos.getRowCount();
        for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
    }

    void bloquear() {
        txtid.setEnabled(false);
        txtcp.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtestado.setEnabled(false);
        txtfolio.setEnabled(false);
        txtfechaD.setEnabled(false);
        txtfechaP.setEnabled(false);
        txtfolio.setEnabled(false);
        txtmunicipio.setEnabled(false);
        txtne.setEnabled(false);
        txtni.setEnabled(false);
        txtrazon.setEnabled(false);
        txtreferencias.setEnabled(false);
        txttotal.setEnabled(false);
        txtcolonia.setEnabled(false);
        
        mnuevo.setVisible(true);
      
        
    }
    

    void desbloquear() {
        txtfechaD.setEnabled(true);
        txtfechaP.setEnabled(true);
        
        mnuevo.setVisible(false);
   
        
    }

    void desbloquear_ingresar() {
        txtid.setEnabled(false);
        txtcp.setEnabled(true);
        txtdireccion.setEnabled(true);
        txtestado.setEnabled(true);
        txtmunicipio.setEnabled(true);
        txtne.setEnabled(true);
        txtni.setEnabled(true);
        txtrazon.setEnabled(true);
        txtreferencias.setEnabled(true);
        txtcolonia.setEnabled(true);
        limpiar_ingre();
        
    }

    void bloquear_ingresar() {
        txtid.setEnabled(false);
        txtcp.setEnabled(false);
        txtdireccion.setEnabled(false);
        txtestado.setEnabled(false);
        txtmunicipio.setEnabled(false);
        txtne.setEnabled(false);
        txtni.setEnabled(false);
        txtrazon.setEnabled(false);
        txtreferencias.setEnabled(false);
        txtcolonia.setEnabled(false);
        limpiar_ingre();
    }

    void limpiar_ingre() {
        txtid.setText("");
        txtcp.setText("");
        txtdireccion.setText("");
        txtestado.setText("");
        txtmunicipio.setText("");
        txtne.setText("");
        txtni.setText("");
        txtrazon.setText("");
        txtreferencias.setText("");
        txtcolonia.setText("");
        
    }
    
void sumar_tabla(){
        Double total = 0.0;
        Double b = 0.0;

        for (int i = 0; i < tbdatosnotas.getRowCount(); i++) {

            String valor = tbdatosnotas.getValueAt(i, 5).toString(); // la columna 1 es la de costo. 
            total = total + Double.parseDouble(valor);
        }
        txttotalfechas.setText(String.valueOf(total));
    
}
public void acumularv() {

        Double total = 0.0;
        Double b = 0.0;

        for (int i = 0; i < tbdatosnotas.getRowCount(); i++) {

            String valor = tbdatosnotas.getValueAt(i, 4).toString(); // la columna 1 es la de costo. 
            total = total + Double.parseDouble(valor);
        }
        txttotalfechas.setText(String.valueOf(total));
    }
    void limpiar() {
        txtid.setText("");
        txtcp.setText("");
        txtdireccion.setText("");
        txtestado.setText("");
        txtfechaD.setDate(null);
        txtfechaP.setDate(null);
        
        txtmunicipio.setText("");
        txtne.setText("");
        txtni.setText("");
        txtrazon.setText("");
        txtreferencias.setText("");
        txttotal.setText("");
        txtcolonia.setText("");
        limpiar_tabla();
    }
    
    void cargar_tabla_notascorte(String valor) {
        DefaultTableModel tb = cnnn.cargar_tabla_notas();
        tbdatosnotas.setModel(tb);
        
        
    }
    

    void consultar_CLIENTE() {
        try {
            ResultSet r = j.consultarclient(vId);
            while (r.next()) {
                vRazon = r.getString("razon");
                vDireccion = r.getString("direccion");
                vNumero_Ext = r.getString("n_e");
                vNumero_Int = r.getString("n_i");
                vColonia = r.getString("colonia");
                vCP = r.getString("cp");
                vreferencias = r.getString("referencias");
            }
            txtid.setText(vId);
            txtrazon.setText(vRazon);
            txtdireccion.setText(vDireccion);
            txtne.setText(vNumero_Ext);
            txtni.setText(vNumero_Int);
            txtcolonia.setText(vColonia);
            txtcp.setText(vCP);
            txtreferencias.setText(vreferencias);
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
void cargar_registros(String folio){
    DefaultTableModel mod=cnnn.cargar_registros_notas(folio);
    tbDatos.setModel(mod);
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
                obs = rs.getString("obs");
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
        
        vId = clie;
        consultar_CLIENTE();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtfe1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtfe2 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbdatosnotas = new javax.swing.JTable();
        txttotalfechas = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtrazon = new javax.swing.JTextField();
        txtdireccion = new javax.swing.JTextField();
        txtne = new javax.swing.JTextField();
        txtni = new javax.swing.JTextField();
        txtcolonia = new javax.swing.JTextField();
        txtcp = new javax.swing.JTextField();
        txtmunicipio = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtreferencias = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        txttotal = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        txtfechaD = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        txtfechaP = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        txtfolio = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtobs = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnuevo = new javax.swing.JMenu();
        msalir = new javax.swing.JMenu();

        setBackground(new java.awt.Color(36, 47, 97));
        setTitle("PERIODO DE VENTAS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-checkout-filled.png"))); // NOI18N

        jTabbedPane1.setForeground(new java.awt.Color(36, 47, 97));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(103, 206, 210));

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(36, 47, 97));
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("HASTA");

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 47, 97));
        jLabel1.setText("DESDE");

        tbdatosnotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbdatosnotas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbdatosnotasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbdatosnotas);

        jLabel18.setText("TOTAL");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(36, 47, 97));
        jLabel19.setText("BUSCAR");

        jButton2.setText("SUMAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("GENERAR REPORTE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfe2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtfe1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1))
                            .addComponent(jLabel2))
                        .addGap(142, 142, 142)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 85, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210)
                .addComponent(jButton2)
                .addGap(57, 57, 57)
                .addComponent(jLabel18)
                .addGap(45, 45, 45)
                .addComponent(txttotalfechas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtfe2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfe1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotalfechas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("BASE DE DATOS", jPanel2);

        jPanel3.setBackground(new java.awt.Color(103, 206, 210));

        jPanel4.setBackground(new java.awt.Color(103, 206, 210));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DATOS DEL CLIENTE", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12), new java.awt.Color(36, 47, 97))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 47, 97));
        jLabel3.setText("Razon Social/Nombre");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("Calle.");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 97));
        jLabel5.setText("Numero Exterior");

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(36, 47, 97));
        jLabel6.setText("Interior");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(36, 47, 97));
        jLabel7.setText("Colonia");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(36, 47, 97));
        jLabel8.setText("Municipio");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(36, 47, 97));
        jLabel9.setText("Estado");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 97));
        jLabel10.setText("C.P.");

        txtrazon.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtrazon.setForeground(new java.awt.Color(36, 47, 97));

        txtdireccion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtdireccion.setForeground(new java.awt.Color(36, 47, 97));
        txtdireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireccionActionPerformed(evt);
            }
        });

        txtne.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtne.setForeground(new java.awt.Color(36, 47, 97));

        txtni.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtni.setForeground(new java.awt.Color(36, 47, 97));

        txtcolonia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcolonia.setForeground(new java.awt.Color(36, 47, 97));
        txtcolonia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcoloniaActionPerformed(evt);
            }
        });

        txtcp.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtcp.setForeground(new java.awt.Color(36, 47, 97));
        txtcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcpActionPerformed(evt);
            }
        });

        txtmunicipio.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtmunicipio.setForeground(new java.awt.Color(36, 47, 97));

        txtestado.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtestado.setForeground(new java.awt.Color(36, 47, 97));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(36, 47, 97));
        jLabel11.setText("ID");

        txtid.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtid.setForeground(new java.awt.Color(36, 47, 97));
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(36, 47, 97));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 97));
        jLabel13.setText("Referencias");

        txtreferencias.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtreferencias.setForeground(new java.awt.Color(36, 47, 97));
        txtreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtreferenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)))
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtdireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(txtrazon))
                                .addComponent(txtcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtni, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel10)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5))
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(txtni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(txtreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addGap(331, 331, 331))
        );

        jPanel5.setBackground(new java.awt.Color(103, 206, 210));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("$$$$"));

        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "CLAVE", "DESCRIPCION", "COLOR", "EXISTENCIA", "CANTIDAD", "PRECIO", "TOTAL"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbDatos);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(172, 172, 172))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel14.setText("Total");

        panel.setBackground(new java.awt.Color(103, 206, 210));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(36, 47, 97));
        jLabel16.setText("Fecha de Devolucion");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(36, 47, 97));
        jLabel15.setText("Fecha de Prestamo");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(36, 47, 97));
        jLabel17.setText("FOLIO");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGap(15, 28, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(txtfechaD, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtfechaP, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtfechaP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtfechaD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

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

        jLabel20.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(36, 47, 97));
        jLabel20.setText("Observaciones");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 902, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(67, 67, 67)
                            .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtobs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel20))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("DATOS DE LA NOTA", jPanel1);

        jMenuBar2.setBackground(new java.awt.Color(103, 210, 190));
        jMenuBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-add-new (2).png"))); // NOI18N
        mnuevo.setText("NUEVO");
        mnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuevoMouseClicked(evt);
            }
        });
        jMenuBar2.add(mnuevo);

        msalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-exit-sign-filled.png"))); // NOI18N
        msalir.setText("SALIR");
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 931, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

 String vfecha;
 String vfecha2;
         SimpleDateFormat formato=new SimpleDateFormat("YYYY-MM-dd 00:00:00");
         SimpleDateFormat formatoFecha=new SimpleDateFormat("YYYY-MM-dd 23:59:59");
           vfecha =formatoFecha.format(txtfe1.getDate());
           vfecha2=formato.format(txtfe2.getDate());
           cargar_tabla_Ventas("",vfecha, vfecha2);
     
           // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void mnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuevoMouseClicked
    
        txtfe1.setDate(null);
        txtfe2.setDate(null);
limpiar_ingre();
jTabbedPane1.setSelectedIndex(0);
        
    }//GEN-LAST:event_mnuevoMouseClicked

    private void msalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msalirMouseClicked

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_msalirMouseClicked

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtcoloniaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcoloniaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcoloniaActionPerformed

    private void txtcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void txtreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_tbDatosMouseClicked

    private void tbdatosnotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseClicked
        
        int filasel = tbdatosnotas.getSelectedRow();
        String a = (String) tbdatosnotas.getValueAt(filasel, 0);
        cargarnota(a);
        cargar_registros(a);
        jTabbedPane1.setSelectedIndex(1);

        // TODO add your handling code here:
    }//GEN-LAST:event_tbdatosnotasMouseClicked

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        filtro(txtbuscar.getText().toUpperCase(), tbdatosnotas);        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
sumar_tabla();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
String vfecha;
 String vfecha2;
         SimpleDateFormat formato=new SimpleDateFormat("YYYY-MM-dd 00:00:00");
         SimpleDateFormat formatoFecha=new SimpleDateFormat("YYYY-MM-dd 23:59:59");
           vfecha =formatoFecha.format(txtfe1.getDate());
           vfecha2=formato.format(txtfe2.getDate());
    
        c.reporte(vfecha, vfecha2);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu mnuevo;
    private javax.swing.JMenu msalir;
    private javax.swing.JPanel panel;
    public static javax.swing.JTable tbDatos;
    private javax.swing.JTable tbdatosnotas;
    private javax.swing.JTextField txtbuscar;
    public static javax.swing.JTextField txtcolonia;
    public static javax.swing.JTextField txtcp;
    public static javax.swing.JTextField txtdireccion;
    public static javax.swing.JTextField txtestado;
    private com.toedter.calendar.JDateChooser txtfe1;
    private com.toedter.calendar.JDateChooser txtfe2;
    private com.toedter.calendar.JDateChooser txtfechaD;
    private com.toedter.calendar.JDateChooser txtfechaP;
    private javax.swing.JTextField txtfolio;
    public static javax.swing.JTextField txtid;
    public static javax.swing.JTextField txtmunicipio;
    public static javax.swing.JTextField txtne;
    public static javax.swing.JTextField txtni;
    public static javax.swing.JTextField txtobs;
    public static javax.swing.JTextField txtrazon;
    public static javax.swing.JTextField txtreferencias;
    private javax.swing.JTextField txttotal;
    private javax.swing.JTextField txttotalfechas;
    // End of variables declaration//GEN-END:variables
}
