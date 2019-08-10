/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_categoria;
import Controlador.controlador_producto;
import Utilerias.CambiaPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author RojeruSan
 */
public class Pn_products extends javax.swing.JPanel {
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
    /**
     * Creates new form pnlHome
     */
    public Pn_products() {
        initComponents();
        RowApariencia();
        RowApariencia();

        bloquear();
                     
        cargar_tabla("");
        cargarcombocategoria();
        

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
    
         

    void bloquear() {

      

        cmbtipo.setEditable(false);
        txtclave.setEditable(false);
        txtdescripcion.setEditable(false);
        cmbcategoria.setEnabled(false);
        
        txtcantidad.setEditable(false);
        txtprecio.setEditable(false);
        txtprecioe.setEditable(false);
       
       
     
        txtnombrecorte.setEditable(false);
        jt_category.setSelectedIndex(0);
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
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

       public String cargarcombocategoria_JP() {
        ResultSet rs = c.consultar_categorias();
        
   
     
        JComboBox jcb = new JComboBox();
            jcb.addItem("Todos");

        try {
            while (rs.next()) {
                jcb.addItem(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        jcb.setEditable(true);
JOptionPane.showMessageDialog( null, jcb, "Seleccione la Categoria", JOptionPane.QUESTION_MESSAGE);
 String add=jcb.getSelectedItem().toString();
 return add;
 
       }
    public String generarid(String prod, String cat) {
        ResultSet rs = c.generar(prod, cat);
        String a = "";
        try {
            a = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
        }
        vcategoria = cat;
        vclavesss = cat;
    }

    void desbloquear() {
txtnombrecorte.setEditable(true);
        txtclave.setEditable(true);
        txtdescripcion.setEditable(true);
        cmbcategoria.setEnabled(true);
       

        cmbtipo.setEnabled(true);

        txtcantidad.setEditable(true);
        txtprecio.setEditable(true);
        txtprecioe.setEditable(true);
        
  jt_category.setSelectedIndex(1);
    }

    void limpiar() {
       
        txtclave.setText("");
        txtdescripcion.setText("");
        cmbcategoria.setSelectedIndex(0);
        txtcantidad.setText("");
        txtprecio.setText("");
      
        txtprecioe.setText("");
        cmbtipo.setSelectedIndex(0);
        tbDatos.clearSelection();
        txtnombrecorte.setText("");
        vId=null;
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
            Logger.getLogger(Productos.class.getName()).log(Level.SEVERE, null, ex);
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
                vId= r.getString(1);
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
            txtnombrecorte.setText(nombre);
            txtclave.setText(vClave);
            txtdescripcion.setText(vDescripcion);
            txtcantidad.setText(candit);
            txtprecio.setText(price);
            txtprecioe.setText(vprecioE);
            cmbtipo.setSelectedItem(vTipo);
            consult_cat(vcategoria);

        } catch (SQLException ex) {
            Logger.getLogger(Clientess.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public void bloquearComponentes() {
        
        txtdescripcion.setEnabled(false);
        

    }

    public void desbloquearComponentes() {
        
        txtdescripcion.setEnabled(true);
        
        
    }

    public void ComponenteNoEditable() {
        
        txtdescripcion.setEditable(false);
        
        
    }

    public void ComponenteEditable() {
        
       txtdescripcion.setEditable(true);
        
        
    }

    

    public void limpiarCampos() {
      
        txtdescripcion.setText("Ingrese Cargo ");
        
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
        jSeparator5 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtprecioe = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        txtcantidad = new javax.swing.JTextField();
        txtnombrecorte = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        txtclave = new javax.swing.JTextField();
        cmbtipo = new javax.swing.JComboBox<>();
        cmbcategoria = new javax.swing.JComboBox<>();
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
        jLabel8.setText("Modulo de Productos");
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

        t_cargo2.setForeground(new java.awt.Color(153, 153, 153));
        t_cargo2.setText("Buscar Producto");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addContainerGap())
        );

        jt_category.addTab("Consultar productos", new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-list-filled.png")), jp_database); // NOI18N

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

        jSeparator5.setBackground(new java.awt.Color(128, 128, 131));

        jLabel13.setBackground(new java.awt.Color(36, 47, 97));
        jLabel13.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(36, 47, 97));
        jLabel13.setText("TIPO");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(36, 47, 97));
        jLabel10.setText("CATEGORIA");

        jLabel2.setBackground(new java.awt.Color(36, 47, 97));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(36, 47, 97));
        jLabel2.setText("CLAVE");

        jLabel11.setBackground(new java.awt.Color(36, 47, 97));
        jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(36, 47, 97));
        jLabel11.setText("NOMBRE");

        jLabel12.setBackground(new java.awt.Color(36, 47, 97));
        jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(36, 47, 97));
        jLabel12.setText("DESCRIPCIÓN");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("PRECIO"));

        jLabel14.setBackground(new java.awt.Color(36, 47, 97));
        jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(36, 47, 97));
        jLabel14.setText("PUBLICO GENERAL");

        txtprecio.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtprecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioKeyTyped(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(36, 47, 97));
        jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(36, 47, 97));
        jLabel15.setText("CLIENTES");

        txtprecioe.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        txtprecioe.setSelectionColor(new java.awt.Color(255, 255, 255));
        txtprecioe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtprecioeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtprecio))
                .addGap(46, 46, 46)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtprecioe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtprecioe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel4.setBackground(new java.awt.Color(36, 47, 97));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(36, 47, 97));
        jLabel4.setText("CANTIDAD");

        jSeparator8.setBackground(new java.awt.Color(128, 128, 131));

        jSeparator9.setBackground(new java.awt.Color(128, 128, 131));

        jSeparator10.setBackground(new java.awt.Color(128, 128, 131));

        jSeparator11.setBackground(new java.awt.Color(128, 128, 131));

        jSeparator12.setBackground(new java.awt.Color(128, 128, 131));

        txtcantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtnombrecorte.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtdescripcion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtdescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescripcionActionPerformed(evt);
            }
        });

        txtclave.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cmbtipo.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        cmbtipo.setForeground(new java.awt.Color(36, 47, 97));
        cmbtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE EL TIPO", "PRODUCTO", "SERVICIO" }));

        cmbcategoria.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        cmbcategoria.setForeground(new java.awt.Color(36, 47, 97));
        cmbcategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbcategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel2)
                            .addComponent(jLabel13)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator5)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(txtcantidad)
                                    .addComponent(txtclave)
                                    .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(cmbtipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(98, 98, 98)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator10, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(jSeparator8, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(txtnombrecorte, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                    .addComponent(cmbcategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(txtdescripcion)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(cmbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbcategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtnombrecorte, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(37, 37, 37)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        add(jt_category, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 880, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void mnuevoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoFocusGained

    private void mnuevoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mnuevoFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuevoFocusLost

    private void mnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuevoActionPerformed
        desbloquear();

 jt_category.setSelectedIndex(1);
 
limpiar();
                accion="I";

    }//GEN-LAST:event_mnuevoActionPerformed

    private void mguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mguardarActionPerformed
  
        
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

                if (JOptionPane.showConfirmDialog(null, "¿Guardar Datos del Producto?", "¿Guardar?",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

                  
                    vClave = txtclave.getText();
                    vDescripcion = txtdescripcion.getText();
                    vCantidad = Integer.parseInt(txtcantidad.getText());
                    vprecio = Double.parseDouble(txtprecio.getText());
                  
                   vColor=txtnombrecorte.getText();
                    consultar_id();
                    if (cmbtipo.getSelectedItem() == "PRODUCTO") {
                        vTipo = "P";
                    } else if (cmbtipo.getSelectedItem() == "SERVICIO") {
                        vTipo = "S";
                    } else {
                        vTipo = "";
                    }
                    vprecioE = txtprecioe.getText();
vfecha="2019-07-12";
        
                    c.guardar_producto(accion, vId, vClave, vDescripcion, vColor, vCantidad,
                            vfecha, vprecio, vcategoria, vTipo, vprecioE);
                    limpiar();
                    bloquear();
                    cargar_tabla("");

                }

            }
        }
        
        
    }//GEN-LAST:event_mguardarActionPerformed

    private void mcancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcancelarActionPerformed
        bloquear();

        limpiarCampos();
        bloquearComponentes();

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
if(t_cargo2.getText().equals("Buscar Producto")){
 t_cargo2.setText("");
}
// TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2MouseClicked

    private void t_cargo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_cargo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2ActionPerformed

    private void meliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meliminarActionPerformed
     if (JOptionPane.showConfirmDialog(null, "¿Eliminar Los Datos del producto?", "¿Elimilar?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        
                
                c.eliminar_produc(vId);
                cargar_tabla("");
                bloquear();
                limpiar();
                        
            }
            
         // TODO add your handling code here:

               // TODO add your handling code here:
    }//GEN-LAST:event_meliminarActionPerformed

    private void tbDatosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDatosMouseClicked
int filasel= tbDatos.getSelectedRow();
String a=(String)tbDatos.getValueAt( filasel,0);
vId=a;
        consult(a);
        accion="M";
   mguardar.setText("Actualizar");
   meliminar.setVisible(true);
   jt_category.setSelectedIndex(1); 
   desbloquear();
// TODO add your handling code here:
    }//GEN-LAST:event_tbDatosMouseClicked

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

    private void cmbcategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbcategoriaActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_cmbcategoriaActionPerformed

    private void txtdescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionActionPerformed

    private void t_cargo2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_cargo2KeyReleased
      
        filtro(t_cargo2.getText().toUpperCase(), tbDatos);  
        // TODO add your handling code here:
    }//GEN-LAST:event_t_cargo2KeyReleased

   
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbcategoria;
    private javax.swing.JComboBox<String> cmbtipo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
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
    private javax.swing.JTextField txtcantidad;
    private javax.swing.JTextField txtclave;
    private javax.swing.JTextField txtdescripcion;
    private javax.swing.JTextField txtnombrecorte;
    private javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtprecioe;
    // End of variables declaration//GEN-END:variables
}
