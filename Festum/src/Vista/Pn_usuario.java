/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_user;
import Utilerias.CambiaPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;


/**
 *
 * @author RojeruSan
 */
public class Pn_usuario extends javax.swing.JPanel {
  
     Controlador.controlador_user c=new controlador_user();
   String accion="";
   String vid="";
   String vnombre="";
String vclave="";
String vestatus;
String vcontrasena;
       DefaultTableModel dm;
    /**
     * Creates new form pnlHome
     */
    public Pn_usuario() {
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
    
   void bloquear(){
        
        txtid.setEnabled(false);
        txtnombre.setEnabled(false);
        txtclave.setEnabled(false);
         cmbestatus.setEnabled(false);
txtcontrase.setEnabled(false);
        
        
        mnuevo.setVisible(true);
 
        meliminar.setVisible(false);
        mcancelar.setVisible(true);
        
        mguardar.setVisible(false);
        mcancelar.setVisible(false);
        
        jTabbedPane1.setSelectedIndex(0);
        
        
        
        
    }
    
   void desbloquear(){
        
        txtid.setEnabled(true);
        txtnombre.setEnabled(true);
        txtclave.setEnabled(true);
        txtcontrase.setEnabled(true);
        
cmbestatus.setEnabled(true);

        mnuevo.setVisible(false);
   
        meliminar.setVisible(false);
        mcancelar.setVisible(false);
        
        mguardar.setVisible(true);
        mcancelar.setVisible(true);
        
        }

    
    void limpiar(){
txtid.setText("");
        txtnombre.setText("");
        txtclave.setText("");
txtcontrase.setText("");
cmbestatus.setSelectedItem(0);

        
    }
    void consult(String id){
     
        
try{     
         ResultSet r= c.consultaruser(id);
         
         while(r.next()){
           vclave=r.getString("clave");
           vnombre=r.getString("nombre");
           vestatus=r.getString("estatus");
           vcontrasena=r.getString("contraseña");
           vid=r.getString("t_user");
     }
        txtclave.setText(vclave);
        txtnombre.setText(vnombre);
        txtcontrase.setText(vcontrasena);
        if("A".equals(vestatus)){
           cmbestatus.setSelectedItem("ACTIVO");
           
          
        }else if("I".equals(vestatus)){
            cmbestatus.setSelectedItem("INACTIVO");
        }
          if("A".equals(vid)){
           cmbusuario.setSelectedItem("ADMINISTRADOR");
           
          
        }else if("U".equals(vid)){
            cmbusuario.setSelectedItem("USUARIO");
        }
        
        
    } catch (SQLException ex) {
         Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
     }
    }
    
    public void cargar_tabla(String valor){
            DefaultTableModel model=c.cargar_tabla_user(valor);
           
            tbDatos.setModel(model);
            tbDatos.getColumnModel().getColumn(0).setPreferredWidth(50);
             tbDatos.getColumnModel().getColumn(1).setPreferredWidth(150);
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
        jSeparator2 = new javax.swing.JSeparator();
        jl_atras = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jp_database = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        jp_captura = new javax.swing.JPanel();
        mcancelar = new Utilerias.RSButtonMetro();
        jPanel1 = new javax.swing.JPanel();
        chmostrar = new javax.swing.JCheckBox();
        txtid = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbusuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtclave = new javax.swing.JTextField();
        cmbestatus = new javax.swing.JComboBox<>();
        txtcontrase = new javax.swing.JPasswordField();
        txtnombre = new javax.swing.JTextField();
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
        jLabel8.setText("Modulo de Usuarios");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 860, -1));

        jSeparator2.setBackground(new java.awt.Color(128, 128, 131));
        add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 1040, 10));

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

        javax.swing.GroupLayout jp_databaseLayout = new javax.swing.GroupLayout(jp_database);
        jp_database.setLayout(jp_databaseLayout);
        jp_databaseLayout.setHorizontalGroup(
            jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_databaseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                .addContainerGap())
        );
        jp_databaseLayout.setVerticalGroup(
            jp_databaseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_databaseLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consultar clientes", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jp_database); // NOI18N

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

        chmostrar.setBackground(new java.awt.Color(255, 255, 255));
        chmostrar.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        chmostrar.setForeground(new java.awt.Color(36, 47, 97));
        chmostrar.setText("MOSTRAR");
        chmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chmostrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(36, 47, 97));
        jLabel1.setText("CLAVE");

        jLabel3.setText("ID");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("NOMBRE");

        cmbusuario.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cmbusuario.setForeground(new java.awt.Color(36, 47, 97));
        cmbusuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE TIPO ", "ADMINISTRADOR", "USUARIO" }));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("ESTATUS");

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 97));
        jLabel10.setText("T.USUARIO");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(36, 47, 97));
        jLabel5.setText("CONTRASEÑA");

        txtclave.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtclave.setForeground(new java.awt.Color(36, 47, 97));

        cmbestatus.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        cmbestatus.setForeground(new java.awt.Color(36, 47, 97));
        cmbestatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE ESTATUS ", "ACTIVO", "INACTIVO" }));

        txtcontrase.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtcontrase.setForeground(new java.awt.Color(36, 47, 97));

        txtnombre.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtnombre.setForeground(new java.awt.Color(36, 47, 97));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cmbestatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtnombre)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtcontrase, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(chmostrar))
                                            .addComponent(cmbusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtcontrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chmostrar))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbestatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(55, Short.MAX_VALUE))
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
                .addGroup(jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp_capturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mguardar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(meliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jp_capturaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jp_capturaLayout.setVerticalGroup(
            jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp_capturaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_capturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mguardar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnuevo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(meliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        jTabbedPane1.addTab("Captura", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-dishwasher.png")), jp_captura); // NOI18N

        add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 600, 420));
    }// </editor-fold>//GEN-END:initComponents

    private void mnuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoFocusGained

    private void mnuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoFocusLost

    private void mnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuevoActionPerformed
    limpiar();

        accion="I";
        tbDatos.setEnabled(true);
        desbloquear();
        
        jTabbedPane1.setSelectedIndex(1);

    }//GEN-LAST:event_mnuevoActionPerformed

    private void mguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mguardarActionPerformed
  if (JOptionPane.showConfirmDialog(null, "Dese Guardar Los Datos del usuario", "¿Guardar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        
        vid=txtid.getText();
        vclave=txtclave.getText();
        vnombre=txtnombre.getText();
        vcontrasena=String.valueOf(txtcontrase.getPassword());
        
        if("ACTIVO".equals(cmbestatus.getSelectedItem().toString())){
                 vestatus="A";
             }else if("INACTIVO".equals(cmbestatus.getSelectedItem().toString())){
                 vestatus="I";
             
             }
             String tipo="";
        if("ADMINISTRADOR".equals(cmbusuario.getSelectedItem().toString())){
                 tipo="A";
             }else if("USUARIO".equals(cmbusuario.getSelectedItem().toString())){
                 tipo="U";
             }
             
             
        c.guardar_cliente(accion, vclave, vnombre, vcontrasena, vestatus,tipo);
        cargar_tabla("");
        limpiar();
        bloquear();
                

 }
        
        
    }//GEN-LAST:event_mguardarActionPerformed

    private void mcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcancelarActionPerformed
bloquear();
limpiar();

        // TODO add your handling code here:
    }//GEN-LAST:event_mcancelarActionPerformed

    private void jl_atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jl_atrasMouseClicked
        Principal acceso = new Principal();
        new CambiaPanel(acceso.pnlPrincipal, new Vista.Pn_Pantalla_inicio());
        // TODO add your handling code here:
    }//GEN-LAST:event_jl_atrasMouseClicked

    private void meliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meliminarActionPerformed
 if (JOptionPane.showConfirmDialog(null, "¿Eliminar los datos del usuario?", "¿Eliminar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
     String as=txtclave.getText();
 
        c.eliminar_cliente(as);
         limpiar();
    bloquear();cargar_tabla("");
 }   
         // TODO add your handling code here:

               // TODO add your handling code here:
    }//GEN-LAST:event_meliminarActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked
int filasel= tbDatos.getSelectedRow();
String a=(String)tbDatos.getValueAt( filasel,0);
vid=a;
        consult(a);
   mguardar.setText("Actualizar");
   meliminar.setVisible(true);
   jTabbedPane1.setSelectedIndex(1);        // TODO add your handling code here:
    }//GEN-LAST:event_tbDatosMouseClicked

    private void chmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chmostrarActionPerformed

        char i = 0;

        if(chmostrar.isSelected()){
            txtcontrase. setEchoChar((char)0);
        }else{
            txtcontrase.setEchoChar('*');
        }
    }//GEN-LAST:event_chmostrarActionPerformed

   
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chmostrar;
    private javax.swing.JComboBox<String> cmbestatus;
    private javax.swing.JComboBox<String> cmbusuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel jl_atras;
    private javax.swing.JPanel jp_captura;
    private javax.swing.JPanel jp_database;
    private Utilerias.RSButtonMetro mcancelar;
    private Utilerias.RSButtonMetro meliminar;
    private Utilerias.RSButtonMetro mguardar;
    private Utilerias.RSButtonMetro mnuevo;
    private javax.swing.JTable tbDatos;
    private javax.swing.JTextField txtclave;
    private javax.swing.JPasswordField txtcontrase;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
