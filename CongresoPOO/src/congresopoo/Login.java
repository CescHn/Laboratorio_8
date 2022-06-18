package congresopoo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private String Consulta, Usuario, Clave;
    private short Rol;
    private Connection Con;
    private Statement Pila;
    private ResultSet Resultado;
    private Programa P = new Programa();
    protected boolean Conectado;

    public Login() {
        initComponents();
        this.jTF_Usuario.setText("");
        this.jTF_Clave.setText("");
    }

    public void Seleccionar() {
        Conectado = false;
        boolean Existe = false;
        Con = P.Conecta();
        if (Con != null) {
            try {
                Pila = Con.createStatement();
                    Consulta = "select * from Login_BFFR_CELG_DJZG;";
                    Resultado = Pila.executeQuery(Consulta);
                    for (; Resultado.next();) {
                        if( Resultado.getString("usuario").equals(Usuario) ){
                            if( Resultado.getString("clave").equals(Clave)){
                                this.hide();
                                GUI App = new GUI();
                                App.setDefaultCloseOperation(2);
                                App.setVisible(true);
                            }else{
                                JOptionPane.showMessageDialog(null,"Contraseña Incorrecta!!!");                                
                            }
                            Existe = true;
                        }
                    }
                    if( !Existe ){
                        JOptionPane.showMessageDialog(null,"El Usuario Ingresado NO Existe!!!");  
                    }
                P.Desconecta(Con);
            } catch (SQLException e) {
                System.out.println("NO fue posible realizar la selección desde la BD!!!");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Lbl_Login = new javax.swing.JLabel();
        Lbl_Usuario = new javax.swing.JLabel();
        Lbl_Clave = new javax.swing.JLabel();
        jTF_Usuario = new javax.swing.JTextField();
        Btn_InicioSesion = new javax.swing.JButton();
        jTF_Clave = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Lbl_Login.setText("Login");

        Lbl_Usuario.setText("Usuario:");

        Lbl_Clave.setText("Contraseña:");

        jTF_Usuario.setText("jTextField1");
        jTF_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_UsuarioActionPerformed(evt);
            }
        });

        Btn_InicioSesion.setText("Iniciar Sesión");
        Btn_InicioSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InicioSesionActionPerformed(evt);
            }
        });

        jTF_Clave.setText("jPasswordField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Lbl_Usuario)
                            .addComponent(Lbl_Clave))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Lbl_Login)
                            .addComponent(jTF_Clave)
                            .addComponent(jTF_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(Btn_InicioSesion)))
                .addContainerGap(119, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(Lbl_Login)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_Usuario)
                    .addComponent(jTF_Usuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Lbl_Clave)
                    .addComponent(jTF_Clave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(Btn_InicioSesion)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jTF_Clave.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_InicioSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InicioSesionActionPerformed
        Usuario = this.jTF_Usuario.getText();
        Clave = this.jTF_Clave.getText();
        Seleccionar();
    }//GEN-LAST:event_Btn_InicioSesionActionPerformed

    private void jTF_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_UsuarioActionPerformed
        
    }//GEN-LAST:event_jTF_UsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_InicioSesion;
    private javax.swing.JLabel Lbl_Clave;
    private javax.swing.JLabel Lbl_Login;
    private javax.swing.JLabel Lbl_Usuario;
    private javax.swing.JPasswordField jTF_Clave;
    private javax.swing.JTextField jTF_Usuario;
    // End of variables declaration//GEN-END:variables
}
