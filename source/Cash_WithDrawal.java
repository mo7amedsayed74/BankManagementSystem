/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package bankmanagementsystem;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Mo7amed
 */
public class Cash_WithDrawal extends javax.swing.JFrame {

    /**
     * Creates new form Cash_WithDrawal
     */
    Connection con;
    int pin;
    public Cash_WithDrawal() {
        initComponents();
        try{
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bankook", "root", "root");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Connection Failed");}

        this.setLocationRelativeTo(null);
    }
    public Cash_WithDrawal(int tmp) {
        pin=tmp;
        initComponents();
        try{
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bankook", "root", "root");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(this, "Connection Failed");}

        this.setLocationRelativeTo(null);
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
        jLabel2 = new javax.swing.JLabel();
        amount_txt = new javax.swing.JTextField();
        withDrawal_btn = new javax.swing.JButton();
        back_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("MAXIMUM WITHDRAWAL IS 10,000");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("PLEASE ENTER YOUR AMOUNT");

        amount_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amount_txtActionPerformed(evt);
            }
        });

        withDrawal_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        withDrawal_btn.setText("WITHDRAWAL");
        withDrawal_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withDrawal_btnActionPerformed(evt);
            }
        });

        back_btn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        back_btn.setText("BACK");
        back_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(back_btn)
                            .addComponent(withDrawal_btn))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(amount_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(69, 69, 69)
                .addComponent(jLabel2)
                .addGap(27, 27, 27)
                .addComponent(amount_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(withDrawal_btn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back_btn)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void amount_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amount_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_amount_txtActionPerformed

    private void back_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_btnActionPerformed
        new client_page().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_back_btnActionPerformed

    private void withDrawal_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withDrawal_btnActionPerformed
        try {
            int cash=Integer.parseInt(amount_txt.getText());
           PreparedStatement stmt1=con.prepareStatement("select PIN,balance from info");
           ResultSet res=stmt1.executeQuery();
           while(res.next()){
               int x=res.getInt(1);
               if(x==pin){
                int oldBalance=res.getInt(2);
                if(oldBalance>=cash){
                int newBalance=oldBalance-cash;
                PreparedStatement stmt2=con.prepareStatement("update info set balance=? where PIN=?"); 
                stmt2.setInt(2, pin);
                stmt2.setInt(1,newBalance );
                stmt2.executeUpdate();
                amount_txt.setText("");
                JOptionPane.showMessageDialog(this, "cashDrawal successful");
                }
                else{
                amount_txt.setText("");
                JOptionPane.showMessageDialog(this, "انت كحياااااان يامحمد");
                }
                break;
              }
           }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_withDrawal_btnActionPerformed

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
            java.util.logging.Logger.getLogger(Cash_WithDrawal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cash_WithDrawal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cash_WithDrawal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cash_WithDrawal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cash_WithDrawal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amount_txt;
    private javax.swing.JButton back_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton withDrawal_btn;
    // End of variables declaration//GEN-END:variables
}