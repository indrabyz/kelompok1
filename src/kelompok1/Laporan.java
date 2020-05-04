/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelompok1;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Asus X450J
 */
public class Laporan extends javax.swing.JFrame {
Connection conn = null;
ResultSet rs = null;
PreparedStatement pst = null;
    /**
     * Creates new form Laporan
     */
    public Laporan() {
        initComponents();
        conn = kelompok1.koneksidb.ConnectDb();
        setTitle("Laporan");
        UpdateTabel();
        KondisiAwal();
    }

    private void KondisiAwal(){
        edtotal.setText("0");
    }
    private void UpdateTabel()
{
    try
    {
        String sql = "select * from datapenjualan;";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tabelbarang.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (Exception e)
    {
        JOptionPane.showMessageDialog(null, e);           
    }
}
    private void CekTanggal(){
    try{
        String sql = "SELECT * FROM datapenjualan WHERE tanggal BETWEEN '"+edtanggalawal.getText()+"' AND '"+edtanggalakhir.getText()+"'";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tabelbarang.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(null, e);                
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        edtanggalawal = new javax.swing.JTextField();
        edtanggalakhir = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelbarang = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Menu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 0, 36)); // NOI18N
        jLabel1.setText("   Laporan");

        edtanggalawal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtanggalawalActionPerformed(evt);
            }
        });
        edtanggalawal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtanggalawalKeyTyped(evt);
            }
        });

        edtanggalakhir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtanggalakhirActionPerformed(evt);
            }
        });

        tabelbarang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabelbarang);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Total Keuntungan");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText(" Ke Tanggal");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText(" Tanggal");

        edtotal.setFont(new java.awt.Font("Old English Text MT", 0, 36)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Old English Text MT", 0, 36)); // NOI18N
        jLabel6.setText("Rp.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtanggalawal, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtanggalakhir, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 625, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addComponent(edtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(288, 288, 288))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtanggalakhir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(edtanggalawal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(edtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Menu w = new Menu();
        w.setVisible(true); 
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void edtanggalakhirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtanggalakhirActionPerformed
        // TODO add your handling code here:
        CekTanggal();
        try{
        Connection conn = null;
        Statement stmt = null;
        String tanggalawal=edtanggalawal.getText();
        String tanggalakhir=edtanggalakhir.getText();
        String total=edtotal.getText();
        conn = kelompok1.koneksidb.ConnectDb();
        stmt = conn.createStatement();
        String sql_total = "SELECT sum((Harga-HargaBeli)*Unit) FROM datapenjualan WHERE tanggal BETWEEN '"+edtanggalawal.getText()+"' AND '"+edtanggalakhir.getText()+"'";
        ResultSet rsstotal = stmt.executeQuery(sql_total);        
            while (rsstotal.next()){
                edtotal.setText(rsstotal.getString(1));
                JOptionPane.showMessageDialog(rootPane, "Total Keuntungan Anda Dari Tanggal "+tanggalawal+" Sampai Tanggal "+tanggalakhir+" Adalah \nRp."+edtotal.getText());
            }  
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
            
        }
    }//GEN-LAST:event_edtanggalakhirActionPerformed

    private void edtanggalawalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtanggalawalKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_edtanggalawalKeyTyped

    private void edtanggalawalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtanggalawalActionPerformed
        // TODO add your handling code here:
       edtanggalakhir.requestFocus();
    }//GEN-LAST:event_edtanggalawalActionPerformed

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
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Laporan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Laporan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField edtanggalakhir;
    private javax.swing.JTextField edtanggalawal;
    private javax.swing.JLabel edtotal;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelbarang;
    // End of variables declaration//GEN-END:variables
private void close(){
    WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
}
}
