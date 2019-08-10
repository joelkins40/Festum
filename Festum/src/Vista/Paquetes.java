/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Controlador.controlador_CP;
import Controlador.controlador_cliente;

import Controlador.controlador_notas;
import Controlador.controlador_paquetes;
import Controlador.controlador_producto;
import static Vista.Festum.escritorio;
import static Vista.NOTAS.tbDatos;
import static Vista.NOTAS.txtcantidad;
import static Vista.NOTAS.txtclave;
import static Vista.NOTAS.txtdescripcion;
import static Vista.NOTAS.txtexistencia;
import static Vista.NOTAS.txtprecio;
import static Vista.NOTAS.txttotalproducto;

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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Paquetes extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    controlador_paquetes c= new controlador_paquetes();
    controlador_producto pd = new controlador_producto();
    String accion_cliente = "R";
    String accion;
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

    public Paquetes() {

        initComponents();
    
        bloquear();
        cargar_tabla_notascorte("");
        bloquear_txt();
        auto();
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

            tbDatos.setValueAt(total, filasel, 7);
            acumular();

        }

    }

    void bloquear_txt() {

              txtexistencia.setEditable(false);
        txtprecio.setEditable(false);
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
            Logger.getLogger(Paquetes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void limpiar_tabla() {
    String [] titulos={"ID","Clave","Descripcion","Exist","Cant","Precio","Total"};
            String [] registros=new String[6];
            DefaultTableModel we=new DefaultTableModel(null,titulos);
      tbDatos.setModel(we);
            TableColumnModel colummodel= tbDatos.getColumnModel();
       colummodel.getColumn(0).setPreferredWidth(2);
        colummodel.getColumn(2).setPreferredWidth(450);
       colummodel.getColumn(1).setPreferredWidth(30);
     
        colummodel.getColumn(3).setPreferredWidth(20);
     colummodel.getColumn(4).setPreferredWidth(20);    
        colummodel.getColumn(5).setPreferredWidth(40);
    
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

        txttotal.setEnabled(false);

        mnuevo.setVisible(true);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
    
        mguardar.setVisible(false);
    
    }


    void actualizar_produc_cotizacion(String folio) {

        for (int i = 0; i < tbDatos.getRowCount(); i++) {
            String id_f = (String) tbDatos.getValueAt(i, 0);
            ResultSet rs = pd.consutar_product_descripcion(id_f);
            String id_prod = "";
            try {
                while (rs.next()) {
                    id_prod = rs.getString("id_producto");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Paquetes.class.getName()).log(Level.SEVERE, null, ex);
            }

            String cantidad = this.tbDatos.getValueAt(i, 4).toString();
            String total = this.tbDatos.getValueAt(i, 6).toString();

            c.guardar_nota_prod(accion, folio, cantidad, total, id_prod);

        }
    }

    void desbloquear() {
        mnuevo.setVisible(false);
        mmodificar.setVisible(false);
        meliminar.setVisible(false);
    
        mguardar.setVisible(true);
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


    void cargar_tabla_notascorte(String valor) {
        DefaultTableModel tb = c.cargar_tabla_notas(valor);
        tbdatosnotas.setModel(tb);

    }


    void cargar_registros(String folio) {
        DefaultTableModel mod = c.cargar_registros_notas(folio);
        tbDatos.setModel(mod);
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdatosnotas = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TXTCLAVE = new javax.swing.JTextField();
        TXTNOMBRE = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        txtpreciopaquete1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbDatos = new javax.swing.JTable();
        txtdescripcion = new javax.swing.JTextField();
        txtclave = new javax.swing.JTextField();
        txtcantidad = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        txtexistencia = new javax.swing.JTextField();
        txtprecio = new javax.swing.JTextField();
        txttotalproducto = new javax.swing.JTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        mnuevo = new javax.swing.JMenu();
        mmodificar = new javax.swing.JMenu();
        mguardar = new javax.swing.JMenu();
        meliminar = new javax.swing.JMenu();
        mcancelar = new javax.swing.JMenu();
        msalir = new javax.swing.JMenu();

        jMenuItem1.setText("jMenuItem1");

        setTitle("PAQUETES");

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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbdatosnotasMouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(tbdatosnotas);

        jLabel17.setText("Nombre");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel17)
                        .addGap(51, 51, 51)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 845, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("BASE DE DATOS", jPanel4);

        jLabel14.setText("Total");

        jButton4.setText("Eliminar Producto");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-Búsqueda Filled-16.png"))); // NOI18N
        jButton1.setText("BUSCAR PRODUCTO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Clave del Paquete");

        TXTCLAVE.setText("PKT");

        TXTNOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXTNOMBREActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre Del Paquete");

        jLabel15.setText("Precio Especial");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("$$$$"));

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setText("ELIMINAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        tbDatos.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        txtdescripcion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        txtclave.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        txtcantidad.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        jButton6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(36, 47, 97));
        jButton6.setText("jButton4");

        txtexistencia.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        txtprecio.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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

        txttotalproducto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtclave, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtexistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotalproducto))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 874, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(109, 109, 109))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                    .addContainerGap(904, Short.MAX_VALUE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(107, 107, 107)))
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
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47))
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(132, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TXTCLAVE, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TXTNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(208, 208, 208)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpreciopaquete1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(TXTCLAVE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TXTNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(105, 105, 105))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtpreciopaquete1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(78, 78, 78))))
        );

        jTabbedPane1.addTab("PAQUETES", jPanel2);

        jMenuBar2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.lightGray, null, null));
        jMenuBar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenuBar2.setFont(new java.awt.Font("Lucida Calligraphy", 0, 12)); // NOI18N

        mnuevo.setBackground(new java.awt.Color(255, 204, 204));
        mnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-add-new (2).png"))); // NOI18N
        mnuevo.setText("NUEVO");
        mnuevo.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mnuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnuevoMouseClicked(evt);
            }
        });
        jMenuBar2.add(mnuevo);

        mmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-erase.png"))); // NOI18N
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

        mguardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-save-as-filled.png"))); // NOI18N
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

        meliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-trash.png"))); // NOI18N
        meliminar.setText("ELIMINAR");
        meliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        meliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                meliminarMouseClicked(evt);
            }
        });
        jMenuBar2.add(meliminar);

        mcancelar.setBackground(new java.awt.Color(255, 204, 204));
        mcancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-close-window-filled.png"))); // NOI18N
        mcancelar.setText("CANCELAR");
        mcancelar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        mcancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mcancelarMouseClicked(evt);
            }
        });
        jMenuBar2.add(mcancelar);

        msalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-exit-sign-filled.png"))); // NOI18N
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void limpiar_prod() {
        txtclave.setText("");
        txtdescripcion.setText("");
        txtexistencia.setText("");
      //  txtcolor.setText("");
        txtprecio.setText("");
        txtcantidad.setText("");
        txttotal.setText("");
    }
    private void tbdatosnotasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbdatosnotasMouseEntered

    private void tbdatosnotasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbdatosnotasMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_tbdatosnotasMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int filasel = tbDatos.getSelectedRow();

        if (tbDatos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No Existen Registros");
        } else if (filasel == -1) {

            JOptionPane.showMessageDialog(null, "Seleccione un Registro");
        } else {

            DefaultTableModel dtm = (DefaultTableModel) tbDatos.getModel(); //TableProducto es el nombre de mi tabla ;)
        dtm.removeRow(tbDatos.getSelectedRow());
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        
        Cons_producyos ceq = new Cons_producyos();
        Festum.escritorio.add(ceq);
        ceq.toFront();
        ceq.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void TXTNOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTNOMBREActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXTNOMBREActionPerformed

    private void mnuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuevoMouseClicked

        //limpiar();

        accion = "I";
        tbDatos.setEnabled(true);
        desbloquear();
        Calendar c2 = new GregorianCalendar();
       // txtfecha.setCalendar(c2);

        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_mnuevoMouseClicked

    private void mmodificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mmodificarMouseClicked
        int filasel = tbDatos.getSelectedRow();
        String a = (String) tbDatos.getValueAt(filasel, 0);
        vId = a;
        //consult(a);
        desbloquear();
        //txtid.setEnabled(false);
        jTabbedPane1.setSelectedIndex(1);
        accion = "M";
        // TODO add your handling code here:
    }//GEN-LAST:event_mmodificarMouseClicked

    private void mmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mmodificarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mmodificarActionPerformed

    private void mguardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseEntered

    private void mguardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mguardarMouseClicked

       // validar_campos();
       // if (validacion != 0) {
            JOptionPane.showMessageDialog(null, "Faltan Campos Por Rellenar");
       // } else {
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
         //   }

            if ("A".equals(s) && "I".equals(accion)) {

                JOptionPane.showMessageDialog(null, "La Clave " + a + " Ya Existe");

            } else if (s == "F" || accion == "M") {

                if (JOptionPane.showConfirmDialog(null, "Dese Guardar Un Nuevo Producto", "¿Guardar?",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

//                vId = txtid.getText();
//                vClave = txtclave.getText();
//                vDescripcion = txtdescripcion.getText();
//                vCantidad = Integer.parseInt(txtcantidad.getText());
//                vprecio = Double.parseDouble(txtprecio.getText());
//                SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-dd");
//                vfecha = formatoFecha.format(txtfecha.getDate());
//                vColor=txtNOMBEW.getText();
//                consultar_id();
//                if (cmbtipo.getSelectedItem() == "PRODUCTO") {
//                    vTipo = "P";
//                } else if (cmbtipo.getSelectedItem() == "SERVICIO") {
//                    vTipo = "S";
//                } else {
//                    vTipo = "";
//                }
//                vprecioE = txtprecioe.getText();
//
//                c.guardar_producto(accion, vId, vClave, vDescripcion, vColor, vCantidad,
//                    vfecha, vprecio, vcategoria, vTipo, vprecioE);
//                limpiar();
//                bloquear();
//                cargar_tabla("");

            }

        }
        }
    
        // TODO add your handling code here:
    }//GEN-LAST:event_mguardarMouseClicked

    private void meliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_meliminarMouseClicked

        if (JOptionPane.showConfirmDialog(null, "¿Eliminar Los Datos del producto", "¿Elimilar?",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

//        c.eliminar_produc(txtid.getText());
//        cargar_tabla("");
//        bloquear();
//        limpiar();

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_meliminarMouseClicked

    private void mcancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mcancelarMouseClicked
        if (JOptionPane.showConfirmDialog(null, "¿Cancelar Los Datos del producto?", "¿Cancelar?",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//        limpiar();
//        bloquear();
        jTabbedPane1.setSelectedIndex(0);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_mcancelarMouseClicked

    private void msalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msalirMouseClicked

        this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_msalirMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel registros = (DefaultTableModel) tbDatos.getModel();

        int fila = tbDatos.getSelectedRow();
        if (fila >= 0) {
            int[] filasselec = tbDatos.getSelectedRows();
            for (int i = 0; i < filasselec.length; i++) {
                registros.removeRow(filasselec.length);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Selecciono Ninguna Fila", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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

                }
            } catch (SQLException ex) {
                Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
            }

            if ("".equals(vclave)) {
                JOptionPane.showMessageDialog(null, "El Producto no existe");
                txtcantidad.setText("");
            }
            {
//                if (btngeneral.isSelected()) {
//                    txtprecio.setText(precio);
//                } else if (btnespecial.isSelected()) {
//                    txtprecio.setText(precios_especial);
//                }

                txtclave.setText(vclave);
                txtexistencia.setText(cantidad);
                txtcantidad.setEditable(true);
                txtcantidad.requestFocus();
                txtprecio.setEditable(true);
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyReleased

    private void txtdescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyTyped

//        if(!btngeneral.isSelected()&&!btnespecial.isSelected()){
//            JOptionPane.showMessageDialog(null, "Selecciona el tipo del cliente");
//
//        }else{
//
//        }
//        // TODO add your handling code here:
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
                    vdescrip = r.getString("descripcion_producto");
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
//                if (btngeneral.isSelected()) {
//                    txtprecio.setText(precio);
//                } else if (btnespecial.isSelected()) {
//                    txtprecio.setText(precio_especial);
//                }

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXTCLAVE;
    private javax.swing.JTextField TXTNOMBRE;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField4;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    private javax.swing.JMenu mcancelar;
    private javax.swing.JMenu meliminar;
    private javax.swing.JMenu mguardar;
    private javax.swing.JMenu mmodificar;
    private javax.swing.JMenu mnuevo;
    private javax.swing.JMenu msalir;
    public static javax.swing.JTable tbDatos;
    private javax.swing.JTable tbdatosnotas;
    public static javax.swing.JTextField txtcantidad;
    public static javax.swing.JTextField txtclave;
    public static javax.swing.JTextField txtdescripcion;
    public static javax.swing.JTextField txtexistencia;
    public static javax.swing.JTextField txtprecio;
    private javax.swing.JTextField txtpreciopaquete1;
    private javax.swing.JTextField txttotal;
    public static javax.swing.JTextField txttotalproducto;
    // End of variables declaration//GEN-END:variables
}
