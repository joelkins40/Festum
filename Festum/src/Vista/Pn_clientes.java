/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_CP;

import Controlador.controlador_cliente;

import Utilerias.CambiaPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
 * @author RojeruSan
 */
public class Pn_clientes extends javax.swing.JPanel {
  
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
       DefaultTableModel dme;
    /**
     * Creates new form pnlHome
     */
    public Pn_clientes() {
        initComponents();
        RowApariencia();
        RowApariencia();
    cargar_tabla("");
        bloquear();
     
   
        //cargar_tabla("");

    }
//  public void aparenciaTabs() {
//        ImageIcon iconPestaña = new ImageIcon(this.getClass().getResource("../Imagenes/addusers.png"));
//        jt_category.addTab("usuarios", iconPestaña, jp_captura);
//        this.add(jt_category, BorderLayout.CENTER);
//
//        ImageIcon iconPestaña2 = new ImageIcon(this.getClass().getResource("../Imagenes/menu32.png"));
//        jt_category.addTab("Tabla de Contenido", iconPestaña2,jp_database);
//        this.add(jt_category, BorderLayout.CENTER);
//
//    }
    
         
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
            vId="1";

        } else {
            int intfolio = Integer.parseInt(folioc);
            String total = String.valueOf(intfolio + 1);
            vId=total;
        }
    }
    void bloquear() {
       
        txtrazon.setEditable(false);
        txtdireccion.setEditable(false);
        txtne.setEditable(false);
        txtni.setEditable(false);
        cmbcolonia.setEditable(false);
        txtcp.setEditable(false);
        txtmunicipio.setEditable(false);
        txtestado.setEditable(false);
        txttelefono.setEditable(false);
       
      
       
        txtreferencias.setEditable(false);
                btnespecial.setEnabled(false);
        
        btngeneral.setEnabled(false);
        jt_category.setSelectedIndex(0);
       
        
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
     
     
      
        txtreferencias.setEditable(true);
   
        jt_category.setSelectedIndex(1);
 
    }
        private void filtro(String consulta, JTable jtableBuscar) {
        dme = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dme);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    
    void limpiar() {
        
        txtrazon.setText("");
        txtdireccion.setText("");
       
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
   
  
    
    
    

    public void RowApariencia(){
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        t_cargo2 = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        jp_captura = new javax.swing.JPanel();
        mcancelar = new Utilerias.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        txtrazon = new javax.swing.JTextField();
        txtestado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        cmbcolonia = new javax.swing.JComboBox<>();
        txtdireccion = new javax.swing.JTextField();
        txtreferencias = new javax.swing.JTextField();
        txtne = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtni = new javax.swing.JTextField();
        btnespecial = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        txtcp = new javax.swing.JTextField();
        btngeneral = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        txtmunicipio = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        mguardar = new Utilerias.RSButtonMetro();
        mnuevo = new Utilerias.RSButtonMetro();
        meliminar = new Utilerias.RSButtonMetro();

        jSeparator6.setBackground(new java.awt.Color(128, 128, 131));

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(128, 128, 131));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/archivocargosmall.png"))); // NOI18N
        jLabel8.setText("Modulo de Clientes");
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

        tbDatos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbDatos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDatosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDatos);

        t_cargo2.setForeground(new java.awt.Color(153, 153, 153));
        t_cargo2.setText("Buscar Clientes");
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

        javax.swing.GroupLayout jp_databaseLayout = new javax.swing.GroupLayout(jp_database);
        jp_database.setLayout(jp_databaseLayout);
        jp_databaseLayout.setHorizontalGroup(
            jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_databaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jp_databaseLayout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t_cargo2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(392, Short.MAX_VALUE))
        );
        jp_databaseLayout.setVerticalGroup(
            jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_databaseLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(t_cargo2, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                .addContainerGap())
        );

        jt_category.addTab("Consultar clientes", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jp_database); // NOI18N

        jp_captura.setBackground(new java.awt.Color(255, 255, 255));

        mcancelar.setBackground(new java.awt.Color(97, 212, 195));
        mcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Cancel23x23.png"))); // NOI18N
        mcancelar.setText("Cancelar");
        mcancelar.setColorHover(new java.awt.Color(128, 128, 131));
        mcancelar.setColorNormal(new java.awt.Color(97, 212, 195));
        mcancelar.setIconTextGap(25);
        mcancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcancelarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(97, 212, 195), 3));

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

        txtestado.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("Interior");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 97));
        jLabel5.setText("Colonia");

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(36, 47, 97));
        jLabel12.setText("Precio");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(36, 47, 97));
        jLabel7.setText("Municipio");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 97));
        jLabel13.setText("Estado");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(36, 47, 97));
        jLabel9.setText("Telefono");

        txttelefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(36, 47, 97));
        jLabel16.setText("C. Postal");

        cmbcolonia.setToolTipText("Selecciona La Colonia");

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

        txtreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtreferenciasActionPerformed(evt);
            }
        });

        txtne.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtneKeyTyped(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(36, 47, 97));
        jLabel14.setText("Referencias");

        txtni.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtniKeyTyped(evt);
            }
        });

        btnespecial.setBackground(new java.awt.Color(255, 255, 255));
        btnespecial.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnespecial.setForeground(new java.awt.Color(36, 47, 97));
        btnespecial.setText("ESPECIAL");
        btnespecial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnespecialActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 47, 97));
        jLabel1.setText("Razon Social/Nombre");

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

        btngeneral.setBackground(new java.awt.Color(255, 255, 255));
        btngeneral.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btngeneral.setForeground(new java.awt.Color(36, 47, 97));
        btngeneral.setText("GENERAL");
        btngeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngeneralActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("Direccion.");

        txtmunicipio.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(36, 47, 97));
        jLabel3.setText("Numero Exterior");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel13)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(123, 123, 123)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnespecial)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btngeneral))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cmbcolonia, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtestado)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel9)
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
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtcp, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                .addComponent(txtreferencias)
                                .addComponent(txtrazon, javax.swing.GroupLayout.Alignment.LEADING)))))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(jLabel16)
                    .addComponent(txtcp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtmunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
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
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnespecial)
                    .addComponent(jLabel12)
                    .addComponent(btngeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

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

        mnuevo.setBackground(new java.awt.Color(97, 212, 195));
        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/personal30x30.png"))); // NOI18N
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

        meliminar.setBackground(new java.awt.Color(97, 212, 195));
        meliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-delete-32.png"))); // NOI18N
        meliminar.setText("Eliminar");
        meliminar.setColorHover(new java.awt.Color(128, 128, 131));
        meliminar.setColorNormal(new java.awt.Color(97, 212, 195));
        meliminar.setIconTextGap(25);
        meliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_capturaLayout = new javax.swing.GroupLayout(jp_captura);
        jp_captura.setLayout(jp_capturaLayout);
        jp_capturaLayout.setHorizontalGroup(
            jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_capturaLayout.createSequentialGroup()
                .addGap(0, 48, Short.MAX_VALUE)
                .addGroup(jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_capturaLayout.createSequentialGroup()
                        .addComponent(mnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(meliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_capturaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jp_capturaLayout.setVerticalGroup(
            jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_capturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jt_category.addTab("Captura", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-dishwasher.png")), jp_captura); // NOI18N

        add(jt_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 880, 480));
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
accion="I";
    generarfolio();

    }//GEN-LAST:event_mnuevoActionPerformed

    private void mguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mguardarActionPerformed
   validar_campos();
        if (validacion != 0) {
            JOptionPane.showMessageDialog(null, "Faltan Campos Por Rellenar");
        } else {        
        if (JOptionPane.showConfirmDialog(null, "¿Guardar datos del Cliente?", "¿Guardar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
          
            
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
            jt_category.setSelectedIndex(0);
        } else {
            
        }
        }
        
        
    }//GEN-LAST:event_mguardarActionPerformed

    private void mcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcancelarActionPerformed
     

        // TODO add your handling code here:
    }//GEN-LAST:event_mcancelarActionPerformed

    private void jl_atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_atrasMouseClicked
        Principal acceso = new Principal();
        new CambiaPanel(acceso.pnlPrincipal, new Vista.Pn_Pantalla_inicio());
        // TODO add your handling code here:
    }//GEN-LAST:event_jl_atrasMouseClicked

    private void t_cargo2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_t_cargo2FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2FocusLost

    private void t_cargo2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_cargo2MouseClicked
if(t_cargo2.getText().equals("Buscar Clientes")){
    t_cargo2.setText("");
}
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2MouseClicked

    private void t_cargo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cargo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2ActionPerformed

    private void meliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meliminarActionPerformed
 
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
            
        }
            
         // TODO add your handling code here:

               // TODO add your handling code here:
    }//GEN-LAST:event_meliminarActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked
int filasel= tbDatos.getSelectedRow();
String a=(String)tbDatos.getValueAt( filasel,0);
vId=a;
accion="M";
        consult(a);
   mguardar.setText("Actualizar");
   meliminar.setVisible(true);
   desbloquear();
   jt_category.setSelectedIndex(1);        // TODO add your handling code here:
    }//GEN-LAST:event_tbDatosMouseClicked

    private void txtrazonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtrazonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrazonActionPerformed

    private void txtrazonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrazonKeyTyped

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

    private void txtdireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionActionPerformed

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped

        String Caracteres = txtdireccion.getText();
        if (Caracteres.length() >= 68) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txtreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtreferenciasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtreferenciasActionPerformed

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

    private void btnespecialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnespecialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnespecialActionPerformed

    private void txtcpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcpActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpActionPerformed

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

    private void txtcpKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcpKeyTyped

        String Caracteres = txtcp.getText();
        if (Caracteres.length() >= 5) {
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcpKeyTyped

    private void btngeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngeneralActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btngeneralActionPerformed

    private void t_cargo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cargo2KeyReleased
        filtro(t_cargo2.getText().toUpperCase(), tbDatos);        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2KeyReleased

   
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnespecial;
    private javax.swing.JRadioButton btngeneral;
    private javax.swing.JComboBox<String> cmbcolonia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel jl_atras;
    private javax.swing.JPanel jp_captura;
    private javax.swing.JPanel jp_database;
    private javax.swing.JTabbedPane jt_category;
    private Utilerias.RSButtonMetro mcancelar;
    private Utilerias.RSButtonMetro meliminar;
    private Utilerias.RSButtonMetro mguardar;
    private Utilerias.RSButtonMetro mnuevo;
    private javax.swing.JTextField t_cargo2;
    private javax.swing.JTable tbDatos;
    private javax.swing.JTextField txtcp;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtestado;
    private javax.swing.JTextField txtmunicipio;
    private javax.swing.JTextField txtne;
    private javax.swing.JTextField txtni;
    private javax.swing.JTextField txtrazon;
    private javax.swing.JTextField txtreferencias;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
