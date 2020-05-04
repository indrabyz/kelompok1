/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kelompok1;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Asus X450J
 */
public class inputbarang extends javax.swing.JFrame {
        Connection conn = null;
        ResultSet rs = null;
        ResultSet rsj = null;
        Connection connj = null;
        PreparedStatement pst = null;
        String tekan;
        
        private void UpdateTabel(){
            try{
            String sql = "select * from databarang";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            conn = kelompok1.koneksidb.ConnectDb();
            connj = kelompok1.koneksidb.ConnectDb();
            tabelpenjualan.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        
}
     
        private void delete(){
        Connection conn = null;
        Statement stmt = null;
        String nama = ednamabarang.getText();
        try{
            conn = kelompok1.koneksidb.ConnectDb();
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE from databarang where Nama=('"+nama+"');");
            //JOptionPane.showMessageDialog(this, rs);
            JOptionPane.showMessageDialog(null, "Data Berhasil Di Hapus");
            rs = pst.executeQuery();
            tabelpenjualan.setModel(DbUtils.resultSetToTableModel(rs));
            reset();
            ednamabarang.requestFocus();
        }catch (Exception t){
            JOptionPane.showMessageDialog(null, "Data Gagal Di Hapus !");
        }
        
    }    
        
        private void updatestok(){
        Connection conn = null;
        Statement stmt = null;
        int stok , tambahstok,totalstok;
        stok = Integer.valueOf(edstok.getText());
        tambahstok = Integer.valueOf(edtambahstok.getText());
        totalstok=stok+tambahstok;
        String stotalstok=String.valueOf(totalstok);
        String updatetabel ="UPDATE stokbaju" + " SET Stok = '" +stotalstok+ "' WHERE kode = '"+edcodebarang.getText()+"'";
        try{
            conn = kelompok1.koneksidb.ConnectDb();
            stmt = conn.createStatement();  
            stmt.execute(updatetabel);
            edstok.setText(stotalstok);
            JOptionPane.showMessageDialog(null, "Stok telah berhasil ditambahkan dengan jumlah "+tambahstok);
            reset();
            
          
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
                    
        private void insert(){
            Connection conn = null;
            Statement stmt = null;
            try{
                conn = kelompok1.koneksidb.ConnectDb();
                stmt = conn.createStatement();
                String sql = "insert into databarang values("+edcodebarang.getText()+"','"+editem.getSelectedItem()+"','"+ednamabarang.getText()+"','"+edharganet.getText()+"','"+edhargajual.getText()+"')";
                stmt.executeUpdate(sql);
                reset();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Data Sudah Ada !");
            }
        }
        
        private void reset(){            
            ednamabarang.setText("");
            edharganet.setText("");
            edhargajual.setText("");
            edcodebarang.setText("");
            edtambahstok.setText("");
            edstok.setText("");
            edcodebarang.requestFocus();
            
        }
    /**
     * Creates new form inputbarang
     */
    public inputbarang() {
        initComponents();
        connj = kelompok1.koneksidb.ConnectDb();
        conn = kelompok1.koneksidb.ConnectDb();
        UpdateTabel();
        setTitle("Data Barang");
        itemjenis();
        updateData();
    }

    private void updateData(){
        Connection conn = null;
        Statement stmt = null;
        
        String id = edcodebarang.getText();
        
        try
        {
            conn = kelompok1.koneksidb.ConnectDb();
            stmt = connj.createStatement();
            String query = "select JenisBarang from databarang where kode= '"+id+"';";
            ResultSet rss =stmt.executeQuery(query);
            while (rss.next())
            {
                editem.setSelectedItem(rss.getString("JenisBarang"));
            }
            query = "select Harga from databarang where kode= '"+id+"';";
            ResultSet Hargaa =stmt.executeQuery(query);
            while (Hargaa.next())
            { 
                edhargajual.setText(Hargaa.getString("Harga"));
            }
            query = "select Nama from databarang where kode= '"+id+"';";
            ResultSet Namaa =stmt.executeQuery(query);
            while (Namaa.next())
            { 
                ednamabarang.setText(Namaa.getString("Nama"));
            }
            query = "select HargaBeli from databarang where kode= '"+id+"';";
            ResultSet HargaBelii =stmt.executeQuery(query);
            while (HargaBelii.next())
            { 
                edharganet.setText(HargaBelii.getString("HargaBeli"));
            }
            //edunit.requestFocus();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
   private void UpdateTabelNama(){
    try{
        String sql = "select * from databarang where Nama LIKE '%"+edcari.getText()+"%';";
        pst = conn.prepareStatement(sql);
        rs = pst.executeQuery();
        tabelpenjualan.setModel(DbUtils.resultSetToTableModel(rs));
    }
    catch (Exception e){
        JOptionPane.showMessageDialog(null, e);                
    }
}
    
   private void itemjenis(){
        editem.removeAllItems();
        editem.addItem("              PILIH KATEGORI          ");
        editem.addItem("Mie");
        editem.addItem("Alat Tulis");
        editem.addItem("Makanan Ringan");
        editem.addItem("Bahan Dapur");
        editem.addItem("Rokok");
        editem.addItem("Peralatan Mandi");
        editem.addItem("Minuman");
        editem.addItem("Gas");    
    }    
   
   private void updatebarang(){
        Connection conn = null;
        Statement stmt = null;        
        String nama = ednamabarang.getText();
        String kode = edcodebarang.getText();
        String jenis = editem.getSelectedItem().toString();
        String hargabeli = edharganet.getText();
        String harga = edhargajual.getText();
        String stok = edtambahstok.getText();
        String updatetabel = ("UPDATE databarang SET HargaBeli = '"+hargabeli+"',Harga = '"+harga+"',Nama = '"+nama+"',JenisBarang = '"+jenis+"',Stok = '"+stok+"',Kode = '"+kode+"' WHERE kode = '"+edcodebarang.getText()+"'");
        try{
            conn = kelompok1.koneksidb.ConnectDb();
            stmt = conn.createStatement();  
            stmt.execute(updatetabel);           
            JOptionPane.showMessageDialog(null, "Data Telah diubah");
            rs = pst.executeQuery();
            tabelpenjualan.setModel(DbUtils.resultSetToTableModel(rs));
            reset();
            
          
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
   
   private void tambahbarang(){
       String Jenis = editem.getSelectedItem().toString();
        String Nama = ednamabarang.getText();
        String HargaBeli = edharganet.getText();
        String Harga = edhargajual.getText();   
        String Stok = edtambahstok.getText();
        Connection conn = null;
        Statement stmt = null;
        if (edcodebarang.getText().equals("") ||  ednamabarang.getText().equals("") || edharganet.getText().equals("") || edhargajual.getText().equals("") || edtambahstok.getText().equals("") || editem.getSelectedItem().equals("              PILIH KATEGORI          ")){
            JOptionPane.showMessageDialog(rootPane, "Data belum lengkap !");
        }
        else
        {            
            String Kode,kodejenis;
            Kode = (edcodebarang.getText());
            try
            {
                conn = kelompok1.koneksidb.ConnectDb();
                stmt = conn.createStatement();
                stmt.executeUpdate("insert into databarang values('"+Kode+"','"+Jenis+"','"+Nama+"','"+HargaBeli+"','"+Harga+"','"+Stok+"')");
                String pesan = ("Data telah berhasil ditambahkan dengan kode = "+Kode);
                JOptionPane.showMessageDialog(null, pesan); 
                rs = pst.executeQuery();
                tabelpenjualan.setModel(DbUtils.resultSetToTableModel(rs));
                edharganet.setText("");
                edcodebarang.setText("");
                edhargajual.setText("");
                ednamabarang.setText("");
                edtambahstok.setText("");
                edstok.setText("");
                edtambahstok.setText("");
                edtambahstok.setText("");
                edstok.setText("");
                edcodebarang.requestFocus();
                
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, e);
                JOptionPane.showMessageDialog(rootPane, "Data sudah tersedia!");
            }
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

        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        edcodebarang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpenjualan = new javax.swing.JTable();
        edharganet = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        edhargajual = new javax.swing.JTextField();
        transaksibutton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        ednamabarang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        edcari = new javax.swing.JTextField();
        edubah = new javax.swing.JButton();
        editem = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        edtambahstok = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        edstok = new javax.swing.JTextField();

        jButton4.setText("jButton4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Old English Text MT", 1, 36)); // NOI18N
        jLabel1.setText("   Input Barang");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Kode Barang");

        edcodebarang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                edcodebarangFocusLost(evt);
            }
        });
        edcodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edcodebarangActionPerformed(evt);
            }
        });
        edcodebarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edcodebarangKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Harga Beli");

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        tabelpenjualan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpenjualan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpenjualanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpenjualan);

        edharganet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edharganetActionPerformed(evt);
            }
        });
        edharganet.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edharganetKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setText("Harga Jual");

        edhargajual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edhargajualActionPerformed(evt);
            }
        });
        edhargajual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edhargajualKeyTyped(evt);
            }
        });

        transaksibutton.setText("Menu");
        transaksibutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaksibuttonMouseClicked(evt);
            }
        });
        transaksibutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transaksibuttonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Jenis Barang");

        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ednamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ednamabarangActionPerformed(evt);
            }
        });
        ednamabarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ednamabarangKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setText("Pencarian");

        edcari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edcariActionPerformed(evt);
            }
        });

        edubah.setText("Ubah");
        edubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edubahActionPerformed(evt);
            }
        });

        editem.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Enter");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setText("Tambah Stok");

        edtambahstok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edtambahstokKeyTyped(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel9.setText("Stok");

        edstok.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edcari))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(transaksibutton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edcodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editem, 0, 200, Short.MAX_VALUE)
                            .addComponent(ednamabarang, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtambahstok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(edubah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edharganet, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edstok, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(4, 4, 4)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edcodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(edhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ednamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(edtambahstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(edstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(edharganet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edubah)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(transaksibutton)
                    .addComponent(jButton3)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transaksibuttonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaksibuttonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_transaksibuttonMouseClicked

    private void transaksibuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transaksibuttonActionPerformed
        // TODO add your handling code here:
        Menu w = new Menu();
        w.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_transaksibuttonActionPerformed

    private void edcodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edcodebarangActionPerformed
        // TODO add your handling code here:
        Connection conn = null;
        Statement stmt = null;
        
        String id = edcodebarang.getText();
        
        try
        {
            conn = kelompok1.koneksidb.ConnectDb();
            stmt = connj.createStatement();
            String query = "select JenisBarang from databarang where kode= '"+id+"';";
            ResultSet rss =stmt.executeQuery(query);
            while (rss.next())
            {
                editem.setSelectedItem(rss.getString("JenisBarang"));
            }
            query = "select Harga from databarang where kode= '"+id+"';";
            ResultSet Hargaa =stmt.executeQuery(query);
            while (Hargaa.next())
            { 
                edhargajual.setText(Hargaa.getString("Harga"));
            }
            query = "select Nama from databarang where kode= '"+id+"';";
            ResultSet Namaa =stmt.executeQuery(query);
            while (Namaa.next())
            { 
                ednamabarang.setText(Namaa.getString("Nama"));
            }
            query = "select HargaBeli from databarang where kode= '"+id+"';";
            ResultSet HargaBelii =stmt.executeQuery(query);
            while (HargaBelii.next())
            { 
                edharganet.setText(HargaBelii.getString("HargaBeli"));
            }
            query = "select Stok from databarang where kode= '"+id+"';";
            ResultSet Stokk= stmt.executeQuery(query);
            while (Stokk.next())
            { 
                edstok.setText(Stokk.getString("Stok"));
            }    
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(rootPane, "Data Sudah Tersedia !");
        }
        ednamabarang.requestFocus();
    }//GEN-LAST:event_edcodebarangActionPerformed

    private void edharganetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edharganetActionPerformed
        // TODO add your handling code here:
        edhargajual.requestFocus();
    }//GEN-LAST:event_edharganetActionPerformed

    private void edcodebarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edcodebarangKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();  
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){  
            getToolkit().beep();  
            evt.consume();  
        }
    }//GEN-LAST:event_edcodebarangKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        edcodebarang.setText("");
        ednamabarang.setText("");        
        edharganet.setText("");
        edhargajual.setText("");
        edstok.setText("");
        edcodebarang.enable(true);
        ednamabarang.enable(true);        
        edharganet.enable(true);
        edhargajual.enable(true);       
        edcodebarang.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void edhargajualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edhargajualActionPerformed
        // TODO add your handling code here:       
        edtambahstok.requestFocus();
    }//GEN-LAST:event_edhargajualActionPerformed

    private void edhargajualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edhargajualKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();  
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){  
            getToolkit().beep();  
            evt.consume();  
        }
    }//GEN-LAST:event_edhargajualKeyTyped

    private void ednamabarangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ednamabarangKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ednamabarangKeyTyped

    private void edharganetKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edharganetKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();  
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){  
            getToolkit().beep();  
            evt.consume();  
        }
    }//GEN-LAST:event_edharganetKeyTyped

    private void ednamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ednamabarangActionPerformed
        // TODO add your handling code here:
        edharganet.requestFocus();
    }//GEN-LAST:event_ednamabarangActionPerformed

    private void edcariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edcariActionPerformed
        // TODO add your handling code here:
        UpdateTabelNama();
        
    }//GEN-LAST:event_edcariActionPerformed

    private void edcodebarangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_edcodebarangFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_edcodebarangFocusLost

    private void edubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edubahActionPerformed
        // TODO add your handling code here:    
        updatebarang();
    }//GEN-LAST:event_edubahActionPerformed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jScrollPane1MouseClicked
int brs;
    private void tabelpenjualanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpenjualanMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==1){
        brs=tabelpenjualan.rowAtPoint(evt.getPoint());
        edcodebarang.setText(tabelpenjualan.getValueAt(brs, 0).toString());
        editem.setSelectedItem(tabelpenjualan.getValueAt(brs, 1).toString());
        ednamabarang.setText(tabelpenjualan.getValueAt(brs, 2).toString());
        edharganet.setText(tabelpenjualan.getValueAt(brs, 3).toString());
        edhargajual.setText(tabelpenjualan.getValueAt(brs, 4).toString());
        }
    }//GEN-LAST:event_tabelpenjualanMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       if (edstok.getText().equals("")){
            tambahbarang();
        }
        else{
            insert();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void edtambahstokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtambahstokKeyTyped
        // TODO add your handling code here:
        char karakter = evt.getKeyChar();  
        if(!(((karakter >= '0') && (karakter <= '9') || (karakter == KeyEvent.VK_BACK_SPACE) || (karakter == KeyEvent.VK_DELETE)))){  
            getToolkit().beep();  
            evt.consume();  
        }
    }//GEN-LAST:event_edtambahstokKeyTyped

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_jButton3ActionPerformed
    
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
            java.util.logging.Logger.getLogger(inputbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(inputbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(inputbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(inputbarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new inputbarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField edcari;
    private javax.swing.JTextField edcodebarang;
    private javax.swing.JTextField edhargajual;
    private javax.swing.JTextField edharganet;
    private javax.swing.JComboBox editem;
    private javax.swing.JTextField ednamabarang;
    private javax.swing.JTextField edstok;
    private javax.swing.JTextField edtambahstok;
    private javax.swing.JButton edubah;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelpenjualan;
    private javax.swing.JButton transaksibutton;
    // End of variables declaration//GEN-END:variables

   private void close(){
    WindowEvent winClosing = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
    Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosing);
   }
}