/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

//import Controlador.controlador_cliente;
//import conexion.conexion;
import Controlador.controlador_CP;
import Controlador.controlador_cliente;
import static Vista.NOTAS.txtcp;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Lavaexport
 */
public class Clientess extends javax.swing.JInternalFrame {
    
    controlador_cliente c = new controlador_cliente();
    Controlador.controlador_CP cp = new controlador_CP();
    String valor_tex;
    String accion = "";
    String vId;
    String vRazon;
    String vDireccion;
    String vNumero_Ext;
    String vNumero_Int;
    String vColonia;
    String vreferencias;
    String vCP;
    String vtelefono;
    String vciudad;
    int validacion;
       DefaultTableModel dm;
    public Clientess() {
        initComponents();
        cargar_tabla("");
        bloquear();
        txtid.setVisible(false);
    }
    
    void cargar_tabla(String valor) {
        DefaultTableModel tb = c.cargar_tabla_cliente(valor);
        tbDatos.setModel(tb);
        tbDatos.getColumnModel().getColumn(0).setPreferredWidth(10);
        tbDatos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tbDatos.getColumnModel().getColumn(2).setPreferredWidth(200);
        tbDatos.getColumnModel().getColumn(3).setPreferredWidth(20);
    }
    void validar_campos() {
        validacion = 0;
        
      
        if ("".equals(txtrazon.getText())) {
            validacion = 1;
        }
        if ("".equals(txtdireccion.getText())) {
            validacion = 1;
        }

        if ("".equals(txtni.getText())) {
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

    }
    void generarfolio() {
        ResultSet rs = c.SELECTMAXID();
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
    void bloquear() {
        txtid.setEditable(false);
        txtrazon.setEditable(false);
        txtdireccion.setEditable(false);
        txtne.setEditable(false);
        txtni.setEditable(false);
        cmbcolonia.setEditable(false);
        txtcp.setEditable(false);
        txtmunicipio.setEditable(false);
        txtestado.setEditable(false);
        txttelefono.setEditable(false);
        mnuevo.setVisible(true);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
        mcancelar.setVisible(true);;
        mguardar.setVisible(false);
        mcancelar.setVisible(false);
        
        txtreferencias.setEditable(false);
                btnespecial.setEnabled(false);
        
        btngeneral.setEnabled(false);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(0, true);
        
        jTabbedPane1.setEnabledAt(1, false);
        
    }
    
    void desbloquear() {
        txtrazon.setEditable(true);
        txtdireccion.setEditable(true);
        txtne.setEditable(true);
        txtni.setEditable(true);
        cmbcolonia.setEnabled(true);
        txtcp.setEditable(true);
          btnespecial.setEnabled(true);
        txttelefono.setEditable(true);
        btngeneral.setEnabled(true);
        mnuevo.setVisible(false);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
        mcancelar.setVisible(false);
        txtreferencias.setEditable(true);
        mguardar.setVisible(true);
        mcancelar.setVisible(true);
        jTabbedPane1.setSelectedIndex(1);
        
        jTabbedPane1.setEnabledAt(1, true);
        
        jTabbedPane1.setEnabledAt(0, false);
    }
        private void filtro(String consulta, JTable jtableBuscar) {
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    
    void limpiar() {
        
        txtrazon.setText("");
        txtdireccion.setText("");
        txtid.setText("");
        txtrazon.setText("");
        txtdireccion.setText("");
        txtne.setText("");
        txtni.setText("");
        txtmunicipio.setText("");
        txtestado.setText("");
        txtcp.setText("");
        cmbcolonia.removeAllItems();
        txtreferencias.setText("");
        txttelefono.setText("");
        btnespecial.setSelected(false);
        btngeneral.setSelected(false);
    }
    
    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        int code = evt.getKeyCode();
        char caracter = evt.getKeyChar();
    }
    
    void consult(String id) {
        try {
            
            ResultSet r = c.consultarclient(vId);
            String precio = "";
            String telegono = "";
            while (r.next()) {
                vRazon = r.getString("razon");
                vDireccion = r.getString("direccion");
                vNumero_Ext = r.getString("n_e");
                vNumero_Int = r.getString("n_i");
                vColonia = r.getString("colonia");
                vCP = r.getString("cp");
                vreferencias = r.getString("referencias");
                precio = r.getString("precio");
                telegono = r.getString("telefono");
            }
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
            txttelefono.setText(telegono);
            txtid.setText(vId);
            txtrazon.setText(vRazon);
            txtdireccion.setText(vDireccion);
            txtne.setText(vNumero_Ext);
            txtni.setText(vNumero_Int);
            cmbcolonia.addItem(vColonia);
            txtcp.setText(vCP);
            codp(vCP);
            txtreferencias.setText(vreferencias);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void codp(String codigopostal) {
        String codif = txtcp.getText();
        String estado = "";
        String municiio = "";
        String colo = "";
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
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        txtne = new javax.swing.JTextField();
        txtni = new javax.swing.JTextField();
        txtcp = new javax.swing.JTextField();
        txtmunicipio = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        txtid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        cmbcolonia = new javax.swing.JComboBox<>();
        txtreferencias = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnespecial = new javax.swing.JRadioButton();
        btngeneral = new javax.swing.JRadioButton();
        txtrazon = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnuevo = new javax.swing.JMenu();
        mmodificar = new javax.swing.JMenu();
        mguardar = new javax.swing.JMenu();
        meliminar = new javax.swing.JMenu();
        mcancelar = new javax.swing.JMenu();
        msalir = new javax.swing.JMenu();

        setBackground(new java.awt.Color(36, 47, 97));
        setTitle("GESTION DE CLIENTES");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-user (1).png"))); // NOI18N

        jTabbedPane1.setForeground(new java.awt.Color(36, 47, 97));
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(103, 206, 210));

        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbDatos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tbDatos.getTableHeader().setReorderingAllowed(false);
        tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDatos);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 97));
        jLabel10.setText("Razon Social");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(323, 323, 323))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("BASE DE DATOS CLIENTES", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jPanel2); // NOI18N

        jPanel1.setBackground(new java.awt.Color(103, 206, 210));
        jPanel1.setForeground(new java.awt.Color(240, 240, 240));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 47, 97));
        jLabel1.setText("Razon Social/Nombre");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("Direccion.");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 47, 97));
        jLabel3.setText("Numero Exterior");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("Interior");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 97));
        jLabel5.setText("Colonia");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(36, 47, 97));
        jLabel7.setText("Municipio");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(36, 47, 97));
        jLabel8.setText("Telefono");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(36, 47, 97));
        jLabel9.setText("C. Postal");

        txtdireccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
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

        txtne.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtneKeyTyped(evt);
            }
        });

        txtni.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtniKeyTyped(evt);
            }
        });

        txtcp.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtcp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcpActionPerformed(evt);
            }
        });
        txtcp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcpKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcpKeyTyped(evt);
            }
        });

        txtmunicipio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtestado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        txtid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(36, 47, 97));
        jLabel12.setText("Precio");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 97));
        jLabel13.setText("Estado");

        txttelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        cmbcolonia.setToolTipText("Selecciona La Colonia");

        txtreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtreferenciasActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(36, 47, 97));
        jLabel14.setText("Referencias");

        btnespecial.setBackground(new java.awt.Color(103, 206, 210));
        buttonGroup1.add(btnespecial);
        btnespecial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnespecial.setForeground(new java.awt.Color(36, 47, 97));
        btnespecial.setText("ESPECIAL");
        btnespecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnespecialActionPerformed(evt);
            }
        });

        btngeneral.setBackground(new java.awt.Color(103, 206, 210));
        buttonGroup1.add(btngeneral);
        btngeneral.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btngeneral.setForeground(new java.awt.Color(36, 47, 97));
        btngeneral.setText("GENERAL");
        btngeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneralActionPerformed(evt);
            }
        });

        txtrazon.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtrazon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtrazonActionPerformed(evt);
            }
        });
        txtrazon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrazonKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel13)
                    .addComponent(jLabel5))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbcolonia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtestado)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtdireccion, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtmunicipio, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtni, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcp, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93))
                            .addComponent(txtreferencias)
                            .addComponent(txtrazon, javax.swing.GroupLayout.Alignment.LEADING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnespecial)
                        .addGap(18, 18, 18)
                        .addComponent(btngeneral)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtrazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtdireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbcolonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtreferencias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(btnespecial)
                    .addComponent(btngeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CAPTURA DE DATOS CLIENTE", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-hand-with-pen-filled.png")), jPanel1); // NOI18N

        jMenuBar2.setBackground(new java.awt.Color(103, 210, 190));
        jMenuBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-plus-32.png"))); // NOI18N
        mnuevo.setText("NUEVO");
        mnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuevoMouseClicked(evt);
            }
        });
        jMenuBar2.add(mnuevo);

        mmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-pencil-32.png"))); // NOI18N
        mmodificar.setText("MODIFICAR");
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
        mguardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mguardarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mguardarMouseEntered(evt);
            }
        });
        jMenuBar2.add(mguardar);

        meliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-empty-trash-32.png"))); // NOI18N
        meliminar.setText("ELIMINAR");
        meliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meliminarMouseClicked(evt);
            }
        });
        jMenuBar2.add(meliminar);

        mcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-delete-32.png"))); // NOI18N
        mcancelar.setText("CANCELAR");
        mcancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcancelarMouseClicked(evt);
            }
        });
        jMenuBar2.add(mcancelar);

        msalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-shutdown-32.png"))); // NOI18N
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
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcpActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpActionPerformed

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked
        
        int filasel = tbDatos.getSelectedRow();
        String a = (String) tbDatos.getValueAt(filasel, 0);
        vId = a;
        consult(a);
        mmodificar.setVisible(true);
        meliminar.setVisible(true);
jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_tbDatosMouseClicked

    private void mnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuevoMouseClicked

        limpiar();
generarfolio();
        btngeneral.setSelected(true);
        accion = "I";
        tbDatos.setEnabled(true);
        desbloquear();
        
        jTabbedPane1.setSelectedIndex(1);

    }//GEN-LAST:event_mnuevoMouseClicked

    private void mmodificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mmodificarMouseClicked
        
        desbloquear();
        
        jTabbedPane1.setSelectedIndex(1);
        accion = "M";

        // TODO add your handling code here:
    }//GEN-LAST:event_mmodificarMouseClicked

    private void mmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmodificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mmodificarActionPerformed

    private void meliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meliminarMouseClicked
    
        int fila = tbDatos.getSelectedRow();
        String f = (String) tbDatos.getValueAt(fila, 0);
        
        int filasel = tbDatos.getSelectedRow();
        if (filasel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione Registro");
        } else {
            
            if (JOptionPane.showConfirmDialog(null, "Dese Eliminar Los Datos del Cliente", "¿Eliminar?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                
                c.eliminar_cliente(f);
                cargar_tabla("");
                limpiar();
                bloquear();
            } else {
                
            }
            
        } // TODO add your handling code here:
    }//GEN-LAST:event_meliminarMouseClicked

    private void mcancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcancelarMouseClicked
    if (JOptionPane.showConfirmDialog(null, "Dese Cancelar Los Datos del Cliente", "¿Cancelar?",
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
        if (JOptionPane.showConfirmDialog(null, "Dese Guardar Los Datos del Cliente", "¿Guardar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            
            vId = txtid.getText();
            vRazon = txtrazon.getText();
            vDireccion = txtdireccion.getText();
            vNumero_Ext = txtne.getText();
            vNumero_Int = txtni.getText();
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
            
            c.guardar_cliente(accion, vId, vRazon, vDireccion, vNumero_Ext, vNumero_Int, vColonia, vCP, vreferencias, vtelefono, vciudad, precio);
            cargar_tabla("");
            limpiar();
            bloquear();
            jTabbedPane1.setSelectedIndex(0);
        } else {
            
        }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseClicked

    private void mguardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseEntered

    private void txtcpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcpKeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
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
            
        }
    }//GEN-LAST:event_txtcpKeyPressed

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        
        String Caracteres = txtdireccion.getText();
        if (Caracteres.length() >= 68) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txtneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtneKeyTyped
        
        String Caracteres = txtne.getText();
        if (Caracteres.length() >= 9) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtneKeyTyped

    private void txtniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtniKeyTyped
        
        String Caracteres = txtne.getText();
        if (Caracteres.length() >= 68) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtniKeyTyped

    private void txtcpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcpKeyTyped
        
        String Caracteres = txtcp.getText();
        if (Caracteres.length() >= 5) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpKeyTyped

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

    private void txtreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasActionPerformed

    private void btnespecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnespecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnespecialActionPerformed

    private void btngeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btngeneralActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        filtro(txtBuscar.getText().toUpperCase(), tbDatos);        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void txtrazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrazonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrazonActionPerformed

    private void txtrazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrazonKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnespecial;
    private javax.swing.JRadioButton btngeneral;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cmbcolonia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenu mcancelar;
    private javax.swing.JMenu meliminar;
    private javax.swing.JMenu mguardar;
    private javax.swing.JMenu mmodificar;
    private javax.swing.JMenu mnuevo;
    private javax.swing.JMenu msalir;
    private javax.swing.JTable tbDatos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtcp;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtestado;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtmunicipio;
    private javax.swing.JTextField txtne;
    private javax.swing.JTextField txtni;
    private javax.swing.JTextField txtrazon;
    private javax.swing.JTextField txtreferencias;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
