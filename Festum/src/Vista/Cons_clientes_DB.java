package Vista;

import Controlador.controlador_CP;
import Controlador.controlador_cliente;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Cons_clientes_DB extends javax.swing.JInternalFrame {

    controlador_cliente c = new controlador_cliente();

    controlador_CP cp = new controlador_CP();

    String vId;
    String vRazon;
    String vDireccion;
    String vNumero_Ext;
    String vNumero_Int;
    String vColonia;
    String vreferencias;
    String vCP;
    String vTelefono;
    String vprecio;

    /**
     * Creates new form Cons_producyos
     */
    public Cons_clientes_DB() {

        initComponents();
        cargar_tabla("");
    }
    DefaultTableModel dm;
    private void filtro(String consulta, JTable jtableBuscar) {
        dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    void cargar_tabla(String valor) {
        DefaultTableModel model = c.cargar_tabla_cliente(valor);
        txtcliente.setModel(model);

    }

    void llenar_cliente(String id) {

        ResultSet rs = c.consultarclient(id);
        try {
            while (rs.next()) {
                vId = rs.getString("id_cliente");

                vRazon = rs.getString("razon");
                vDireccion = rs.getString("direccion");
                vNumero_Ext = rs.getString("n_e");
                vNumero_Int = rs.getString("n_i");
                vColonia = rs.getString("colonia");
                vreferencias = rs.getString("referencias");
                vCP = rs.getString("cp");
                vTelefono = rs.getString("telefono");
                vprecio = rs.getString("precio");
            }
        } catch (SQLException ex) {
            Logger.getLogger(NOTAS.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("G".equals(vprecio)) {
            NOTAS.btngeneral.setSelected(true);
            NOTAS.btnespecial.setSelected(false);

        } else if ("E".equals(vprecio)) {
            NOTAS.btngeneral.setSelected(false);
            NOTAS.btnespecial.setSelected(true);

        } else {
            NOTAS.btngeneral.setSelected(false);
            NOTAS.btnespecial.setSelected(false);

        }
        NOTAS.txttelefono.setText(vTelefono);

        NOTAS.txtid.setText(vId);
        NOTAS.txtrazon.setText(vRazon);
        NOTAS.txtdireccion.setText(vDireccion);
        NOTAS.txtne.setText(vNumero_Ext);
        NOTAS.cmbcolonia.addItem(vColonia);
        NOTAS.txtreferencias.setText(vreferencias);
        NOTAS.txtcp.setText(vCP);
        codp(vCP);
       
        NOTAS.txtclave.setEditable(true);
        NOTAS.txtdescripcion.setEditable(true);
    }

    public void codp(String codigopostal) {
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

        NOTAS.txtestado.setText(estado);
        NOTAS.txtmunicipio.setText(municiio);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtcliente = new javax.swing.JTable();

        setBackground(new java.awt.Color(103, 206, 210));
        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jLabel1.setText("Nombre");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtbuscarKeyTyped(evt);
            }
        });

        txtcliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        txtcliente.getTableHeader().setReorderingAllowed(false);
        txtcliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtclienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(txtcliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtclienteMouseClicked

        int filasel = txtcliente.getSelectedRow();
        if (filasel == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione Registro");
        } else {
            String a = (String) txtcliente.getValueAt(filasel, 0);
            String b = (String) txtcliente.getValueAt(filasel, 1);
            Ventass_clientes.txtid_Cliente.setText(a);
            Ventass_clientes.txtnombrecliente.setText(b);
        }
        
        this.dispose();
        



        // TODO add your handling code here:
    }//GEN-LAST:event_txtclienteMouseClicked

    private void txtbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyTyped
        filtro(txtbuscar.getText().toUpperCase(), txtcliente);        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_txtbuscarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTable txtcliente;
    // End of variables declaration//GEN-END:variables
}
