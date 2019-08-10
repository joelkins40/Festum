/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_producto;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lavaexport
 */
public class Consultar_productos extends javax.swing.JInternalFrame {

    controlador_producto c = new controlador_producto();
    String accion = "";
    String vId;
    String vClave;
    String vDescripcion;
    String vcategoria;
    String vfecha;
    String vColor;
    int vCantidad;
    Double vprecio;
    String vprecioE;
    String vTipo;
    String vclavesss;
    int validacion = 0;
    DefaultTableModel dm;

    public Consultar_productos() {
        initComponents();
        bloquear();
        cargar_tabla("");
        cargarcombocategoria();
        txtid.setVisible(false);
        lblid.setVisible(false);
        jTabbedPane1.setEnabledAt(0, true);
    }

    void bloquear() {

        cmbcategoria.setEnabled(false);

        cmbtipo.setEditable(false);
        txtclave.setEditable(false);
        txtdescripcion.setEditable(false);
        cmbcategoria.setEnabled(false);
        txtfecha.setEnabled(false);
        txtcantidad.setEditable(false);
        txtprecio.setEditable(false);
        txtprecioe.setEditable(false);
        mnuevo.setVisible(true);
        mcancelar.setVisible(true);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
        mguardar.setVisible(false);
        mcancelar.setVisible(false);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, false);
    }

    void validar_campos() {
        validacion = 0;
        int c = cmbcategoria.getSelectedIndex();
        if (c == 0) {
            validacion = 1;
        }
        if (cmbtipo.getSelectedIndex() == 0) {
            validacion = 1;
        }
        if ("".equals(txtclave.getText())) {
            validacion = 1;
        }
        if ("".equals(txtdescripcion.getText())) {
            validacion = 1;
        }

        if ("".equals(txtcantidad.getText())) {
            validacion = 1;
        }
        if ("".equals(txtprecio.getText())) {
            validacion = 1;
        }
        if ("".equals(txtprecioe.getText())) {
            validacion = 1;
        }

    }

    void cargarcombocategoria() {
        ResultSet rs = c.consultar_categorias();
        cmbcategoria.removeAllItems();
        cmbcategoria.addItem("Selecciona Categoria");
        try {
            while (rs.next()) {
                cmbcategoria.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultar_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String generarid(String prod, String cat) {
        ResultSet rs = c.generar(prod, cat);
        String a = "";
        try {
            a = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(Consultar_productos.class.getName()).log(Level.SEVERE, null, ex);
        }

        return a;
    }

    void consultar_id() {
        String d = cmbcategoria.getSelectedItem().toString();
        ResultSet rs = c.consultar_idcategoria(d);
        String cat = "";
        try {

            while (rs.next()) {
                cat = rs.getString("id_categoria");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultar_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        vcategoria = cat;
        vclavesss = cat;
    }

    void desbloquear() {

        txtclave.setEditable(true);
        txtdescripcion.setEditable(true);
        cmbcategoria.setEnabled(true);
        txtfecha.setEnabled(true);

        cmbtipo.setEnabled(true);

        txtcantidad.setEditable(true);
        txtprecio.setEditable(true);
        txtprecioe.setEditable(true);
        mnuevo.setVisible(false);
        mcancelar.setVisible(false);
        meliminar.setVisible(false);
        mmodificar.setVisible(false);
        mguardar.setVisible(true);
        mcancelar.setVisible(true);

        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setEnabledAt(0, false);
    }

    void limpiar() {
        txtid.setText("");
        txtclave.setText("");
        txtdescripcion.setText("");
        cmbcategoria.setSelectedIndex(0);
        txtcantidad.setText("");
        txtprecio.setText("");
        txtfecha.setDate(new Date());
        txtprecioe.setText("");
        cmbtipo.setSelectedIndex(0);
        tbDatos.clearSelection();
    }

    private void filtro(String consulta, JTable jtableBuscar) {
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }

    void cargar_tabla(String valor) {
        DefaultTableModel tb = c.cargar_tabla_Producto(valor);

        tbDatos.setModel(tb);
       TableColumnModel colummodel= tbDatos.getColumnModel();
       colummodel.getColumn(0).setPreferredWidth(5);
        colummodel.getColumn(1).setPreferredWidth(10);
          colummodel.getColumn(1).setPreferredWidth(10);
         colummodel.getColumn(2).setPreferredWidth(230);
          colummodel.getColumn(3).setPreferredWidth(10);

    }

    void consult_cat(String id) {
        
        ResultSet rs = c.consultar_cat_id(id);
        String cat = "";
        try {
            while (rs.next()) {
                cat = rs.getString("descripcion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultar_productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cmbcategoria.setSelectedItem(cat);

    }

    void consult(String id) {
        try {
            ResultSet r = c.consultarproduct(id);
            String candit = "";
            String price = "";
String nombre="";
            while (r.next()) {
                id= r.getString(1);
                vClave = r.getString("Clave_producto");
                vDescripcion = r.getString("descripcion_producto");
           
                vColor = r.getString("color");
                nombre=r.getString("nombre_producto");
                candit = r.getString("cantidad");
                vfecha = r.getString("fecha");
                price = r.getString("precio");
                vcategoria = r.getString("categoria");
                vTipo = r.getString("TIPO_PS");
                vprecioE = r.getString("PRECIO_C");

            }

            if ("S".equals(vTipo)) {
                cmbtipo.setSelectedIndex(2);
            } else if ("P".equals(vTipo)) {
                cmbtipo.setSelectedIndex(1);

            } else {

            }
            txtNOMBEW.setText(nombre);
            txtid.setText(id);
            txtclave.setText(vClave);
            txtdescripcion.setText(vDescripcion);
            txtcantidad.setText(candit);
            txtprecio.setText(price);
            txtprecioe.setText(vprecioE);
            cmbtipo.setSelectedItem(vTipo);
            String ffechad = vfecha;
            java.util.Date fecha = null;
            try {
                fecha = new SimpleDateFormat("yyyy-MM-dd").parse(ffechad);
            } catch (ParseException ex) {
                Logger.getLogger(Consultar_productos.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtfecha.setDate(fecha);
            consult_cat(vcategoria);

        } catch (SQLException ex) {
            Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtclave = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        txtNOMBEW = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbcategoria = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        lblid = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        txtfecha = new com.toedter.calendar.JDateChooser();
        cmbtipo = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtprecioe = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnuevo = new javax.swing.JMenu();
        mmodificar = new javax.swing.JMenu();
        mguardar = new javax.swing.JMenu();
        meliminar = new javax.swing.JMenu();
        mcancelar = new javax.swing.JMenu();
        msalir = new javax.swing.JMenu();

        setBackground(new java.awt.Color(36, 47, 97));
        setForeground(new java.awt.Color(46, 204, 113));
        setTitle("PRODUCTOS");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-espresso-cup-filled.png"))); // NOI18N
        setOpaque(true);

        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(103, 206, 210));
        jPanel2.setForeground(new java.awt.Color(240, 240, 240));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setText("NOMBRE");

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtbuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        tbDatos.setForeground(new java.awt.Color(36, 47, 97));
        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDatos.setToolTipText("");
        tbDatos.setGridColor(new java.awt.Color(36, 47, 97));
        tbDatos.getTableHeader().setReorderingAllowed(false);
        tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDatos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("PRODUCTOS", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jPanel2); // NOI18N

        jPanel1.setBackground(new java.awt.Color(103, 206, 210));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel11.setBackground(new java.awt.Color(36, 47, 97));
        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(36, 47, 97));
        jLabel11.setText("NOMBRE");

        txtclave.setFont(new java.awt.Font("Felix Titling", 0, 14)); // NOI18N
        txtclave.setToolTipText("Ingresa La Clave Del Producto");
        txtclave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtclaveMouseClicked(evt);
            }
        });
        txtclave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtclaveActionPerformed(evt);
            }
        });
        txtclave.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtclaveKeyTyped(evt);
            }
        });

        jLabel12.setBackground(new java.awt.Color(36, 47, 97));
        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(36, 47, 97));
        jLabel12.setText("DESCRIPCION");

        txtdescripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtdescripcion.setToolTipText("Describe El Producto o Servicio");
        txtdescripcion.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        txtdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescripcionActionPerformed(evt);
            }
        });
        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyTyped(evt);
            }
        });

        txtNOMBEW.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNOMBEW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOMBEWActionPerformed(evt);
            }
        });
        txtNOMBEW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNOMBEWKeyTyped(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(36, 47, 97));
        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("CLAVE");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 97));
        jLabel10.setText("CATEGORIA");

        cmbcategoria.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cmbcategoria.setForeground(new java.awt.Color(36, 47, 97));
        cmbcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcategoriaActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(36, 47, 97));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 47, 97));
        jLabel3.setText("FECHA");

        jLabel4.setBackground(new java.awt.Color(36, 47, 97));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("CANTIDAD");

        txtcantidad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcantidadKeyTyped(evt);
            }
        });

        lblid.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblid.setText("ID");

        txtid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        cmbtipo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cmbtipo.setForeground(new java.awt.Color(36, 47, 97));
        cmbtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE EL TIPO", "PRODUCTO", "SERVICIO" }));

        jLabel13.setBackground(new java.awt.Color(36, 47, 97));
        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 97));
        jLabel13.setText("TIPO");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-add-new.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-bubble.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("PRECIO"));

        jLabel7.setBackground(new java.awt.Color(36, 47, 97));
        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(36, 47, 97));
        jLabel7.setText("PUBLICO GENERAL");

        txtprecio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(36, 47, 97));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(36, 47, 97));
        jLabel8.setText("CLIENTES");

        txtprecioe.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtprecioe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtprecioe, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtprecioe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton3.setText("Agregar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel13)
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel2)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(17, 17, 17)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNOMBEW, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(98, 98, 98)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addComponent(txtdescripcion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cmbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))
                                    .addComponent(cmbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblid)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22)))
                .addGap(78, 78, 78))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtNOMBEW, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblid)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("AGREGAR PRODUCTO", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-hand-with-pen-filled.png")), jPanel1); // NOI18N

        jMenuBar2.setBackground(new java.awt.Color(103, 210, 190));
        jMenuBar2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));
        jMenuBar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar2.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N

        mnuevo.setBackground(new java.awt.Color(255, 204, 204));
        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-plus-32.png"))); // NOI18N
        mnuevo.setText("NUEVO");
        mnuevo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuevoMouseClicked(evt);
            }
        });
        jMenuBar2.add(mnuevo);

        mmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-pencil-32.png"))); // NOI18N
        mmodificar.setText("MODIFICAR");
        mmodificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        mguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-save-all-32.png"))); // NOI18N
        mguardar.setText("GUARDAR");
        mguardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mguardarMouseEntered(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mguardarMouseClicked(evt);
            }
        });
        jMenuBar2.add(mguardar);

        meliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-empty-trash-32.png"))); // NOI18N
        meliminar.setText("ELIMINAR");
        meliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        meliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meliminarMouseClicked(evt);
            }
        });
        jMenuBar2.add(meliminar);

        mcancelar.setBackground(new java.awt.Color(255, 204, 204));
        mcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-delete-32.png"))); // NOI18N
        mcancelar.setText("CANCELAR");
        mcancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mcancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcancelarMouseClicked(evt);
            }
        });
        jMenuBar2.add(mcancelar);

        msalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-shutdown-32.png"))); // NOI18N
        msalir.setText("SALIR");
        msalir.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionActionPerformed

    private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarActionPerformed

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void txtcantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyReleased
        // TODO add your handling code here:
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

    private void mnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuevoMouseClicked

        limpiar();

        accion = "I";
        tbDatos.setEnabled(true);
        desbloquear();
        Calendar c2 = new GregorianCalendar();
        txtfecha.setCalendar(c2);

        jTabbedPane1.setSelectedIndex(1);

    }//GEN-LAST:event_mnuevoMouseClicked

    private void mmodificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mmodificarMouseClicked
        int filasel = tbDatos.getSelectedRow();
        String a = (String) tbDatos.getValueAt(filasel, 0);
        vId = a;
        consult(a);
        desbloquear();
        txtid.setEnabled(false);
        jTabbedPane1.setSelectedIndex(1);
        accion = "M";
        // TODO add your handling code here:
    }//GEN-LAST:event_mmodificarMouseClicked
    private void mmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmodificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mmodificarActionPerformed

    private void meliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meliminarMouseClicked


       
            if (JOptionPane.showConfirmDialog(null, "¿Eliminar Los Datos del producto", "¿Elimilar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        
                
                c.eliminar_produc(txtid.getText());
                cargar_tabla("");
                bloquear();
                limpiar();
                        
            }
        // TODO add your handling code here:
    }//GEN-LAST:event_meliminarMouseClicked

    private void mcancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcancelarMouseClicked
        if (JOptionPane.showConfirmDialog(null, "¿Cancelar Los Datos del producto?", "¿Cancelar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            limpiar();
            bloquear();
            jTabbedPane1.setSelectedIndex(0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mcancelarMouseClicked

    private void msalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msalirMouseClicked

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_msalirMouseClicked

    private void mguardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseClicked

        validar_campos();
        if (validacion != 0) {
            JOptionPane.showMessageDialog(null, "Faltan Campos Por Rellenar");
        } else {
            String a = txtclave.getText();
            String total = "";
            String s = "";

            for (int i = 0; i < tbDatos.getRowCount(); i++) {
                total = tbDatos.getValueAt(i, 1).toString();
                if (total.equals(a) || tbDatos.getRowCount() == 0) {
                    s = "A";
                } else {
                    s = "F";
                }
            }

            if ("A".equals(s) && "I".equals(accion)) {

                JOptionPane.showMessageDialog(null, "La Clave " + a + " Ya Existe");

            } else if (s == "F" || accion == "M") {

                if (JOptionPane.showConfirmDialog(null, "Dese Guardar Un Nuevo Producto", "¿Guardar?",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                    vId = txtid.getText();
                    vClave = txtclave.getText();
                    vDescripcion = txtdescripcion.getText();
                    vCantidad = Integer.parseInt(txtcantidad.getText());
                    vprecio = Double.parseDouble(txtprecio.getText());
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
                    vfecha = formatoFecha.format(txtfecha.getDate());
vColor=txtNOMBEW.getText();
                    consultar_id();
                    if (cmbtipo.getSelectedItem() == "PRODUCTO") {
                        vTipo = "P";
                    } else if (cmbtipo.getSelectedItem() == "SERVICIO") {
                        vTipo = "S";
                    } else {
                        vTipo = "";
                    }
                    vprecioE = txtprecioe.getText();

                    c.guardar_producto(accion, vId, vClave, vDescripcion, vColor, vCantidad,
                            vfecha, vprecio, vcategoria, vTipo, vprecioE);
                    limpiar();
                    bloquear();
                    cargar_tabla("");

                }

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseClicked

    private void mguardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseEntered

    private void txtclaveKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclaveKeyTyped


        String Caracteres = txtclave.getText();
        if (Caracteres.length() >= 20) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveKeyTyped

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped
        String Caracteres = txtdescripcion.getText();
        if (Caracteres.length() >= 50) {
            evt.consume();
        }         // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyTyped

    private void txtprecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioKeyTyped

        String Caracteres = txtprecio.getText();
        if (Caracteres.length() >= 48) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }
        if (Character.isSpace(c)) {
            evt.consume();

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioKeyTyped

    private void txtclaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtclaveMouseClicked
        String clave = txtclave.getText();
        if ("Elija Clave del producto".equals(clave)) {
            txtclave.setText("");
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveMouseClicked

    private void txtclaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtclaveActionPerformed
        String cat = cmbcategoria.getSelectedItem().toString();
        String tip = cmbtipo.getSelectedItem().toString();

        // TODO add your handling code here:
    }//GEN-LAST:event_txtclaveActionPerformed

    private void txtprecioeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprecioeKeyTyped
        String Caracteres = txtprecio.getText();
        if (Caracteres.length() >= 48) {
            evt.consume();
        }
        char c = evt.getKeyChar();
        if (Character.isLetter(c)) {
            evt.consume();
        }
        if (Character.isSpace(c)) {
            evt.consume();

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtprecioeKeyTyped

    private void txtbuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyPressed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        filtro(txtbuscar.getText().toUpperCase(), tbDatos);        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void cmbcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcategoriaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_cmbcategoriaActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked

        int filasel = tbDatos.getSelectedRow();
        String a = (String) tbDatos.getValueAt(filasel, 0);
        vId = a;
        consult(a);
        mmodificar.setVisible(true);
        meliminar.setVisible(true);
jTabbedPane1.setSelectedIndex(1);

    }//GEN-LAST:event_tbDatosMouseClicked

    private void txtNOMBEWKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNOMBEWKeyTyped
        String Caracteres = txtNOMBEW.getText();
        if (Caracteres.length() >= 14) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNOMBEWKeyTyped

    private void txtNOMBEWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBEWActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNOMBEWActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    cat_categoria i=new cat_categoria();
        Festum.escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();


        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
cargarcombocategoria();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int filasel=tbDatos.getSelectedRow();
        if(filasel==-1){
            JOptionPane.showMessageDialog(null,"Debe Seleccionar un Equipo","Aviso",JOptionPane.WARNING_MESSAGE);
        }
        else{

            String isid=tbDatos.getValueAt(filasel, 0).toString();
            String isa=tbDatos.getValueAt(filasel, 3).toString();
           
            int as=Integer.parseInt(isa);
            if(as<1){
                JOptionPane.showMessageDialog(null, "NO HAY PRODUCTOS");
            }else{

                ResultSet rs=  c.consultarproduct(isid);
                String vclave="";
                String vdescrip="";
                String vcolor="";
                String precio="";
                String cant="";
                String precios_especiales="";
                try {
                    while(rs.next()){
                        vclave=rs.getString("Clave_producto");
                        vdescrip=rs.getString("descripcion_producto");
                        vcolor=rs.getString("color");
                        precio=rs.getString("precio");
                        cant=rs.getString("cantidad");
                        precios_especiales=rs.getString("PRECIO_C");

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Cons_producyos.class.getName()).log(Level.SEVERE, null, ex);
                }
               if(NOTAS.btngeneral.isSelected()){
                    NOTAS.txtprecio.setText(precio);
                }else if(NOTAS.btnespecial.isSelected()){
                    NOTAS.txtprecio.setText(precios_especiales);
                }

                NOTAS.txtclave.setText(vclave);
                NOTAS.txtdescripcion.setText(vdescrip);
                NOTAS.txtexistencia.setText(vcolor);
                NOTAS.txtprecio.setText(precio);
                NOTAS.txtexistencia.setText(cant);
            }

        }
        NOTAS.txtcantidad.setEditable(true);
        NOTAS.txtcantidad.requestFocus();
        this.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbcategoria;
    private javax.swing.JComboBox<String> cmbtipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblid;
    private javax.swing.JMenu mcancelar;
    private javax.swing.JMenu meliminar;
    private javax.swing.JMenu mguardar;
    private javax.swing.JMenu mmodificar;
    private javax.swing.JMenu mnuevo;
    private javax.swing.JMenu msalir;
    private javax.swing.JTable tbDatos;
    private javax.swing.JTextField txtNOMBEW;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtdescripcion;
    private com.toedter.calendar.JDateChooser txtfecha;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtprecioe;
    // End of variables declaration//GEN-END:variables
}
