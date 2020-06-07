
package nhom19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class BaoTriTheLoaiRuou extends javax.swing.JFrame {
    ArrayList<TheLoai> dsTl = new ArrayList<TheLoai>();
    String matl;
    String tentl;
   
    public BaoTriTheLoaiRuou() {
        initComponents();
        setTitle("Bảo trì thể loại rượu");
        loadTable(getData());
    }
    public BaoTriTheLoaiRuou(String value1, String value2) {
        initComponents();
        this.matl = value1;
        this.tentl = value2;
        addTlRuou();
        loadTable(getData());
    }
    
    public void loadTable(ArrayList<TheLoai> list){
        jTable1.setModel(new TheLoaiTable(list));
    }
    public ArrayList<TheLoai> getData() {
        ArrayList<TheLoai> dsTl = new ArrayList<TheLoai>();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/TheLoaiDB";
            String user = "tran";
            String password = "1";
            Connection con = DriverManager.getConnection(url, user, password );
            String query = "select * from TheLoai";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            TheLoai tl;
            while(rs.next()){
                tl = new TheLoai(rs.getString("matl"), rs.getString("tentl"));
                dsTl.add(tl);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return dsTl;
    }
    static void insertTheLoaiIntoDB(String matl, String tentl){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/TheLoaiDB";
            String user = "tran";
            String password = "1";
            Connection con = DriverManager.getConnection(url, user, password );
            String query = "insert into TheLoai values(?,?)";
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setString(1, matl);
            pst.setString(2, tentl);
            pst.executeUpdate();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void addTlRuou(){
        TheLoai tl = new TheLoai();
        tl.setMaTl(matl);
        tl.setTenTl(tentl);
        insertTheLoaiIntoDB(matl, tentl);
        
    }
    static void deleteTheLoaiFromDB(String matl) {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/TheLoaiDB";
            String user = "tran";
            String password = "1";
            Connection con = DriverManager.getConnection(url, user, password );
            String query = "delete from TheLoai where matl=?";
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setString(1, matl);
            
            pst.executeUpdate();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static void updateTheLoai(String matl, String tentl){
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/TheLoaiDB";
            String user = "tran";
            String password = "1";
            Connection con = DriverManager.getConnection(url, user, password );
            String query = "update TheLoai set tentl=? where matl=?";
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setString(1, tentl);
            pst.setString(2, matl);
            pst.executeUpdate();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    static void searchTheLoai(String matl) {
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/TheLoaiDB";
            String user = "tran";
            String password = "1";
            Connection con = DriverManager.getConnection(url, user, password );
            String query = "select * from TheLoai where matl=?";
            PreparedStatement pst = con.prepareStatement(query);
            
            pst.setString(1, matl);
            
            pst.executeUpdate();
             
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    } 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Tìm kiếm");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tìm");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel2)
                .addGap(48, 48, 48)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(33, 33, 33))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EditTheLoaiRuou edit = new EditTheLoaiRuou();
        int row= jTable1.getSelectedRow();
        String matl = jTable1.getValueAt(row, 0).toString().trim();
        String tentl = jTable1.getValueAt(row, 1).toString().trim();
        EditTheLoaiRuou.jTextField1.setText(matl);
        EditTheLoaiRuou.jTextField2.setText(tentl);
        edit.setVisible(true);
        //this.dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new AddTheLoaiRuou().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            int click=JOptionPane.showConfirmDialog(null,"Chắc chắn xóa thể loại này","Xoá?",JOptionPane.YES_NO_OPTION);
            if (click==JOptionPane.YES_OPTION) {
                //lấy dòng chọn hiện thời trong table
                int row= jTable1.getSelectedRow();
                String matl = jTable1.getValueAt(row, 0).toString();
                deleteTheLoaiFromDB(matl);
                loadTable(getData());
            }
        } catch (Exception ex) {            
            JOptionPane.showMessageDialog(null, "Không xoa duoc "+ ex.toString() );
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String matl = jTextField1.getText();
        ArrayList<TheLoai> dsTl = new ArrayList<TheLoai>();
        try{
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String url = "jdbc:derby://localhost:1527/TheLoaiDB";
            String user = "tran";
            String password = "1";
            Connection con = DriverManager.getConnection(url, user, password );
            String query = "select * from TheLoai where matl= '" + matl + "' ";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            TheLoai tl;
            while(rs.next()){
                tl = new TheLoai(rs.getString("matl"), rs.getString("tentl"));
                dsTl.add(tl);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        loadTable(dsTl);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(AddTheLoaiRuou.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddTheLoaiRuou.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddTheLoaiRuou.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddTheLoaiRuou.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BaoTriTheLoaiRuou().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
