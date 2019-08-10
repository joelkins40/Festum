/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author hassiel
 */
public class Festum extends javax.swing.JFrame {
    
public InputStream foto2=this.getClass().getResourceAsStream("/Imagen/valor.png");
public static  String usario;

    /**
     * Creates new form Festum
     */
    public Festum() {
        initComponents();
         this.setExtendedState(Festum.MAXIMIZED_BOTH);
        
        Dimension WH=Toolkit.getDefaultToolkit().getScreenSize();
        setSize(WH);
          cargarImagen(escritorio,foto2);
if(usario=="U"){
    mproductos.setEnabled(false);
    musuarios.setVisible(false);
    
}
    }
    
    void cambiarapariencia(String JOEL){
    try {
		UIManager.setLookAndFeel(JOEL);
	} catch (Exception e) {
		e.printStackTrace();
	}
JOptionPane.showMessageDialog(null,"Tema Cambiado");
}
    public  void cargarImagen(javax.swing.JDesktopPane jDeskp,InputStream fileImagen)
    {   
        try{   
            BufferedImage image = ImageIO.read(fileImagen);        
              escritorio.setBorder(new Fondo(image)); }
        catch (Exception e){   System.out.println("Imagen no disponible");   }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        mproductos = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        musuarios = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FESTUM");

        escritorio.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout escritorioLayout = new javax.swing.GroupLayout(escritorio);
        escritorio.setLayout(escritorioLayout);
        escritorioLayout.setHorizontalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
        );
        escritorioLayout.setVerticalGroup(
            escritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 363, Short.MAX_VALUE)
        );

        jMenuBar1.setForeground(new java.awt.Color(247, 247, 247));
        jMenuBar1.setToolTipText("");

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-house-32.png"))); // NOI18N
        jMenu1.setText("Archivo");
        jMenu1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-workflow.png"))); // NOI18N
        jMenu6.setText("Cambiar Tema");

        jMenuItem12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-bubble.png"))); // NOI18N
        jMenuItem12.setText("NimbusLookAndFeel");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-metal.png"))); // NOI18N
        jMenuItem13.setText("MetalLookAndFeel");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuItem14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-windows-xp.png"))); // NOI18N
        jMenuItem14.setText("WindowsLookAndFeel");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuItem15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-moon-man-filled.png"))); // NOI18N
        jMenuItem15.setText("MotifLookAndFeel");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem15);

        jMenu1.add(jMenu6);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-exit-sign-filled (1).png"))); // NOI18N
        jMenuItem9.setText("Salir");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem9);

        jMenuBar1.add(jMenu1);

        mproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-tableware-32.png"))); // NOI18N
        mproductos.setText("Productos");
        mproductos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-food-16.png"))); // NOI18N
        jMenuItem1.setText("Gestion De Productos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        mproductos.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-open-16.png"))); // NOI18N
        jMenuItem2.setText("Categoria");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        mproductos.add(jMenuItem2);

        jMenuBar1.add(mproductos);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-coins-32.png"))); // NOI18N
        jMenu3.setText("Salidas");
        jMenu3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-purchase-order-16.png"))); // NOI18N
        jMenuItem3.setText("Generar Nota");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-cash-in-hand-16.png"))); // NOI18N
        jMenuItem4.setText("Consultar Cotizaciones");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-purchase-order-16.png"))); // NOI18N
        jMenuItem5.setText("Notas por cliente.");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-user-male-32.png"))); // NOI18N
        jMenu4.setText("Clientes");
        jMenu4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-contacts-16.png"))); // NOI18N
        jMenuItem11.setText("Clientes");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        musuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        musuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-anonymous-mask-16.png"))); // NOI18N
        musuarios.setText("Usuarios");
        musuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musuariosActionPerformed(evt);
            }
        });
        jMenu4.add(musuarios);

        jMenuItem16.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-mexico-16.png"))); // NOI18N
        jMenuItem16.setText("Codigos Postales");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuBar1.add(jMenu4);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-search-32.png"))); // NOI18N
        jMenu5.setText("Consultas");
        jMenu5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-calendar-30-16.png"))); // NOI18N
        jMenuItem6.setText("Programados");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/icons8-total-sales-16.png"))); // NOI18N
        jMenuItem10.setText("Ventas");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Productos i =  new Productos();
 escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();



        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
cat_categoria i=new cat_categoria();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();
           
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

NOTAS i=new NOTAS();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
  Pendientes i=new Pendientes();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
  Ventass i=new Ventass();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed

this.dispose();

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
Clientess i=new Clientess();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();

    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
  cambiarapariencia("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
  // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
       cambiarapariencia("javax.swing.plaf.metal.MetalLookAndFeel");
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
cambiarapariencia("com.sun.java.swing.plaf.windows.WindowsLookAndFeel" );       // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
cambiarapariencia("com.sun.java.swing.plaf.motif.MotifLookAndFeel");        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void musuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musuariosActionPerformed



        usuarios i=new usuarios();
        escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();





        // TODO add your handling code here:
    }//GEN-LAST:event_musuariosActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Cotizaciones i=new Cotizaciones();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();


        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        CP i=new CP();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

   Ventass_clientes i=new Ventass_clientes();
escritorio.add(i);
        Dimension escritorio = Festum.escritorio.getSize();
        Dimension FrameSize = i.getSize();
        i.setLocation((escritorio.width - FrameSize.width)/2, (escritorio.height- FrameSize.height)/2);
       
            i.show();

        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Festum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Festum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Festum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Festum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                                new Festum().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenu mproductos;
    private javax.swing.JMenuItem musuarios;
    // End of variables declaration//GEN-END:variables
}
