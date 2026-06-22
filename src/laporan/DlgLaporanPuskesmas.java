/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgLhtBiaya.java
 *
 * Created on 12 Jul 10, 16:21:34
 */

package laporan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import simrskhanza.DlgCariBangsal;

/**
 *
 * @author perpustakaan
 */
public final class DlgLaporanPuskesmas extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabMode2;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2,ps3,ps4,ps5;
    private ResultSet rs,rs2,rs3,rs4,rs5;
    private int i=0,kamar=0,jumlahhari=0;
    private double hari,lama,hariperawatan,jumlahpasien,jumlahmati,jumlahmati48jam,imt;
    private String caristts="",rpk,rpd,lp,gd;
    private DlgCariBangsal ruang=new DlgCariBangsal(null,false);
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgLaporanPuskesmas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        tabMode=new DefaultTableModel(null,new String[]{
            "No","Tgl.Pemeriksaan","NIK","Nama Pasien","Tgl.Lahir","Jenis Kelamin","Alamat",
            "Riwayat Penyakit Keluarga","Riwayat Penyakit Diri Sendiri","Tekanan Darah","IMT",
            "Lingkar Perut","Pemeriksaan Gula","Diagnosa"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        Tabel1.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel1.setPreferredScrollableViewportSize(new Dimension(500,500));
        Tabel1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 14; i++) {
            TableColumn column = Tabel1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(30);
            }else if(i==1){
                column.setPreferredWidth(80);
            }else if(i==2){
                column.setPreferredWidth(110);
            }else if(i==3){
                column.setPreferredWidth(250);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(80);
            }else if(i==6){
                column.setPreferredWidth(250);
            }else if(i==7){
                column.setPreferredWidth(250);
            }else if(i==8){
                column.setPreferredWidth(250);
            }else if(i==9){
                column.setPreferredWidth(80);
            }else if(i==10){
                column.setPreferredWidth(80);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(100);
            }else if(i==13){
                column.setPreferredWidth(250);
            }
        }

        Tabel1.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabMode2=new DefaultTableModel(null,new String[]{"No","No.Rawat","Nomer RM","Nama Pasien","Kamar","Tgl.Masuk","Tgl.Keluar","Lama","Status"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        Tabel2.setModel(tabMode2);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel2.setPreferredScrollableViewportSize(new Dimension(500,500));
        Tabel2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = Tabel2.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(110);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(180);
            }else if(i==4){
                column.setPreferredWidth(180);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(70);
            }else if(i==8){
                column.setPreferredWidth(80);
            }
        }

        Tabel2.setDefaultRenderer(Object.class, new WarnaTable());
        
        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        
        ruang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(ruang.getTable().getSelectedRow()!= -1){   
                    Kamar.setText(ruang.getTable().getValueAt(ruang.getTable().getSelectedRow(),1).toString());  
                    Kamar.requestFocus();
                }                      
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        
    }    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel17 = new widget.Label();
        Kamar = new widget.TextBox();
        BtnSeek6 = new widget.Button();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel7 = new widget.Label();
        BtnKeluar = new widget.Button();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        Tabel1 = new widget.Table();
        internalFrame3 = new widget.InternalFrame();
        Scroll1 = new widget.ScrollPane();
        Tabel2 = new widget.Table();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Puskesmas ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(53, 23));
        panelGlass5.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(25, 23));
        panelGlass5.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass5.add(Tgl2);

        jLabel17.setText("Ruang :");
        jLabel17.setName("jLabel17"); // NOI18N
        jLabel17.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass5.add(jLabel17);

        Kamar.setName("Kamar"); // NOI18N
        Kamar.setPreferredSize(new java.awt.Dimension(140, 23));
        panelGlass5.add(Kamar);

        BtnSeek6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek6.setMnemonic('5');
        BtnSeek6.setToolTipText("ALt+5");
        BtnSeek6.setName("BtnSeek6"); // NOI18N
        BtnSeek6.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek6ActionPerformed(evt);
            }
        });
        panelGlass5.add(BtnSeek6);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnAll);

        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(10, 23));
        panelGlass5.add(jLabel7);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        TabRawat.setBackground(new java.awt.Color(250, 255, 245));
        TabRawat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(241, 246, 236)));
        TabRawat.setForeground(new java.awt.Color(50, 50, 50));
        TabRawat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        TabRawat.setName("TabRawat"); // NOI18N
        TabRawat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabRawatMouseClicked(evt);
            }
        });

        internalFrame2.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame2.setBorder(null);
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        Tabel1.setName("Tabel1"); // NOI18N
        Tabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel1MouseClicked(evt);
            }
        });
        Tabel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel1KeyPressed(evt);
            }
        });
        Scroll.setViewportView(Tabel1);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Laporan", internalFrame2);

        internalFrame3.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame3.setBorder(null);
        internalFrame3.setName("internalFrame3"); // NOI18N
        internalFrame3.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll1.setName("Scroll1"); // NOI18N
        Scroll1.setOpaque(true);

        Tabel2.setName("Tabel2"); // NOI18N
        Tabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel2MouseClicked(evt);
            }
        });
        Tabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel2KeyPressed(evt);
            }
        });
        Scroll1.setViewportView(Tabel2);

        internalFrame3.add(Scroll1, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("", internalFrame3);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnKeluar,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    if(TabRawat.getSelectedIndex()==0){
        tampil();
    }else if(TabRawat.getSelectedIndex()==1){
     
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            tampil();
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            Valid.pindah(evt, TKd, BtnKeluar);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil();
    }//GEN-LAST:event_formWindowOpened

    private void BtnSeek6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek6ActionPerformed
        ruang.isCek();
        ruang.emptTeks();
        ruang.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        ruang.setLocationRelativeTo(internalFrame1);
        ruang.setVisible(true);
    }//GEN-LAST:event_BtnSeek6ActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        Kamar.setText("");
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }else if(TabRawat.getSelectedIndex()==1){
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void Tabel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel2KeyPressed

    private void Tabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel2MouseClicked

    private void Tabel1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel1KeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_Tabel1KeyPressed

    private void Tabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel1MouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
    }//GEN-LAST:event_Tabel1MouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanPuskesmas dialog = new DlgLaporanPuskesmas(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSeek6;
    private widget.TextBox Kamar;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll1;
    private widget.TextBox TKd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Table Tabel1;
    private widget.Table Tabel2;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.InternalFrame internalFrame3;
    private widget.Label jLabel17;
    private widget.Label jLabel7;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    // End of variables declaration//GEN-END:variables

    public void tampil(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode);   
            
            ps=koneksi.prepareStatement(
                       "SELECT rp.no_rawat,rp.kd_poli,DATE_FORMAT(rp.tgl_registrasi,'%d/%m/%Y') as tgl,p.no_ktp,p.nm_pasien,DATE_FORMAT(p.tgl_lahir,'%d/%m/%Y') as tgl2,if(p.jk='L','Laki-Laki','Perempuan') as kelamin,"
                       + "concat(p.alamat,', ',kelurahan.nm_kel,', ',kecamatan.nm_kec,', ',kabupaten.nm_kab) as alamat "
                       + "FROM reg_periksa rp inner join pasien p on p.no_rkm_medis=rp.no_rkm_medis "+
                       "inner join kelurahan on p.kd_kel=kelurahan.kd_kel inner join kecamatan on p.kd_kec=kecamatan.kd_kec "
                       + "inner join kabupaten on p.kd_kab=kabupaten.kd_kab "
                       + "WHERE rp.tgl_registrasi BETWEEN ? AND ? AND rp.stts!='Batal' "
                       + "order by rp.tgl_registrasi asc,rp.no_rawat asc "); 
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                rs=ps.executeQuery();
                i=1;
                while(rs.next()){
                    caristts=Sequel.cariIsi("select kd_poli from reg_periksa where no_rawat=?",rs.getString("no_rawat"));
                    if(caristts.equals("IGD")){
                        ps2=koneksi.prepareStatement(
                            "SELECT pmi.rpk,pmi.rpd FROM reg_periksa rp left join penilaian_medis_igd pmi on rp.no_rawat=pmi.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? and rp.no_rawat=? ");
                    }else{
                        if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_anak where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_anak.rpk,penilaian_medis_ralan_anak.rpd FROM reg_periksa left join penilaian_medis_ralan_anak on reg_periksa.no_rawat=penilaian_medis_ralan_anak.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_bedah where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_bedah.rpd FROM reg_periksa left join penilaian_medis_ralan_bedah on reg_periksa.no_rawat=penilaian_medis_ralan_bedah.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_bedah_mulut where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_bedah_mulut.rpk,penilaian_medis_ralan_bedah_mulut.rpd FROM reg_periksa left join penilaian_medis_ralan_bedah_mulut on reg_periksa.no_rawat=penilaian_medis_ralan_bedah_mulut.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_geriatri where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_geriatri.rpd FROM reg_periksa left join penilaian_medis_ralan_geriatri on reg_periksa.no_rawat=penilaian_medis_ralan_geriatri.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_kandungan where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_kandungan.rpk,penilaian_medis_ralan_kandungan.rpd FROM reg_periksa left join penilaian_medis_ralan_kandungan on reg_periksa.no_rawat=penilaian_medis_ralan_kandungan.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_mata where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_mata.rpd FROM reg_periksa left join penilaian_medis_ralan_mata on reg_periksa.no_rawat=penilaian_medis_ralan_mata.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_neurologi where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_neurologi.rpd FROM reg_periksa left join penilaian_medis_ralan_neurologi on reg_periksa.no_rawat=penilaian_medis_ralan_neurologi.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_orthopedi where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_orthopedi.rpd FROM reg_periksa left join penilaian_medis_ralan_orthopedi on reg_periksa.no_rawat=penilaian_medis_ralan_orthopedi.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_penyakit_dalam where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_penyakit_dalam.rpd FROM reg_periksa left join penilaian_medis_ralan_penyakit_dalam on reg_periksa.no_rawat=penilaian_medis_ralan_penyakit_dalam.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_psikiatrik where no_rawat=?",rs.getString("no_rawat"))>0){
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan_psikiatrik.rpk,penilaian_medis_ralan_psikiatrik.rpd FROM reg_periksa left join penilaian_medis_ralan_psikiatrik on reg_periksa.no_rawat=penilaian_medis_ralan_psikiatrik.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }else{
                            ps2=koneksi.prepareStatement(
                                "SELECT penilaian_medis_ralan.rpk,penilaian_medis_ralan.rpd FROM reg_periksa left join penilaian_medis_ralan on reg_periksa.no_rawat=penilaian_medis_ralan.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ");
                        }
                    }
   
                    try {
                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(3,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
                            if(rs2.getString("rpk")==null){
                                rpk="-";
                            }else{
                                rpk=rs2.getString("rpk");
                            }
                            if(rs2.getString("rpd")==null){
                                rpd="-";
                            }else{
                                rpd=rs2.getString("rpd");
                            }
                            if(caristts.equals("IGD")){
                                if(Sequel.cariInteger("select count(pmi.no_rawat) from penilaian_medis_igd pmi where pmi.no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT pmi.td,pmi.tb,pmi.bb FROM reg_periksa rp left join penilaian_medis_igd pmi on rp.no_rawat=pmi.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? and rp.no_rawat=? ORDER BY pmi.tanggal DESC limit 1");
                                }else{
                                    ps3=koneksi.prepareStatement(
                                        "SELECT pmi.tensi as td,pmi.tinggi as tb,pmi.berat as bb FROM reg_periksa rp left join pemeriksaan_ralan pmi on rp.no_rawat=pmi.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? and rp.no_rawat=? ORDER BY pmi.tgl_perawatan DESC,pmi.jam_rawat desc limit 1");
                                }
                            }else{
                                if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_tht.td,penilaian_medis_ralan_tht.tb,penilaian_medis_ralan_tht.bb FROM reg_periksa left join penilaian_medis_ralan_tht on reg_periksa.no_rawat=penilaian_medis_ralan_tht.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_tht.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_anak where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_anak.td,penilaian_medis_ralan_anak.tb,penilaian_medis_ralan_anak.bb FROM reg_periksa left join penilaian_medis_ralan_anak on reg_periksa.no_rawat=penilaian_medis_ralan_anak.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_anak.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_bedah where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_bedah.td,penilaian_medis_ralan_bedah.tb,penilaian_medis_ralan_bedah.bb FROM reg_periksa left join penilaian_medis_ralan_bedah on reg_periksa.no_rawat=penilaian_medis_ralan_bedah.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_bedah.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_bedah_mulut where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_bedah_mulut.td,penilaian_medis_ralan_bedah_mulut.tb,penilaian_medis_ralan_bedah_mulut.bb FROM reg_periksa left join penilaian_medis_ralan_bedah_mulut on reg_periksa.no_rawat=penilaian_medis_ralan_bedah_mulut.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_bedah_mulut.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_geriatri where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_geriatri.td,penilaian_medis_ralan_geriatri.tb,penilaian_medis_ralan_geriatri.bb FROM reg_periksa left join penilaian_medis_ralan_geriatri on reg_periksa.no_rawat=penilaian_medis_ralan_geriatri.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_geriatri.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_kandungan where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_kandungan.td,penilaian_medis_ralan_kandungan.tb,penilaian_medis_ralan_kandungan.bb FROM reg_periksa left join penilaian_medis_ralan_kandungan on reg_periksa.no_rawat=penilaian_medis_ralan_kandungan.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_kandungan.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_mata where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_mata.td,penilaian_medis_ralan_mata.tb,penilaian_medis_ralan_mata.bb FROM reg_periksa left join penilaian_medis_ralan_mata on reg_periksa.no_rawat=penilaian_medis_ralan_mata.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_mata.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_neurologi where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_neurologi.td,penilaian_medis_ralan_neurologi.tb,penilaian_medis_ralan_neurologi.bb FROM reg_periksa left join penilaian_medis_ralan_neurologi on reg_periksa.no_rawat=penilaian_medis_ralan_neurologi.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_neurologi.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_orthopedi where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_orthopedi.td,penilaian_medis_ralan_orthopedi.tb,penilaian_medis_ralan_orthopedi.bb FROM reg_periksa left join penilaian_medis_ralan_orthopedi on reg_periksa.no_rawat=penilaian_medis_ralan_orthopedi.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_orthopedi.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_penyakit_dalam where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_penyakit_dalam.td,penilaian_medis_ralan_penyakit_dalam.tb,penilaian_medis_ralan_penyakit_dalam.bb FROM reg_periksa left join penilaian_medis_ralan_penyakit_dalam on reg_periksa.no_rawat=penilaian_medis_ralan_penyakit_dalam.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_penyakit_dalam.tanggal DESC limit 1");
                                }else if(Sequel.cariInteger("select count(no_rawat) from penilaian_medis_ralan_psikiatrik where no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
                                        "SELECT penilaian_medis_ralan_psikiatrik.td,penilaian_medis_ralan_psikiatrik.tb,penilaian_medis_ralan_psikiatrik.bb FROM reg_periksa left join penilaian_medis_ralan_psikiatrik on reg_periksa.no_rawat=penilaian_medis_ralan_psikiatrik.no_rawat WHERE reg_periksa.tgl_registrasi BETWEEN ? AND ? and reg_periksa.no_rawat=? ORDER BY penilaian_medis_ralan_psikiatrik.tanggal DESC limit 1");
                                }else{
                                    ps3=koneksi.prepareStatement(
                                        "SELECT pmi.tensi as td,pmi.tinggi as tb,pmi.berat as bb FROM reg_periksa rp left join pemeriksaan_ralan pmi on rp.no_rawat=pmi.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? and rp.no_rawat=? ORDER BY pmi.tgl_perawatan DESC,pmi.jam_rawat desc limit 1");
                                }
                            }
                            try {
                                ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                ps3.setString(3,rs.getString("no_rawat"));
                                rs3=ps3.executeQuery();
                                while(rs3.next()){
                                    ps4=koneksi.prepareStatement(
                                        "SELECT pmi.lingkar_perut FROM reg_periksa rp left join pemeriksaan_ralan pmi on rp.no_rawat=pmi.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? and rp.no_rawat=? ORDER BY pmi.tgl_perawatan DESC,pmi.jam_rawat desc limit 1");
                                    
                                    try {
                                        ps4.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                        ps4.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                        ps4.setString(3,rs.getString("no_rawat"));
                                        rs4=ps4.executeQuery();
                                        while(rs4.next()){
                                            if(rs4.getString("lingkar_perut")==null||rs4.getString("lingkar_perut").equals("")){
                                                lp="";
                                            }else{
                                                lp=rs4.getString("lingkar_perut");
                                            }
                                            if(Sequel.cariInteger("select count(jpl.nm_perawatan) FROM reg_periksa rp left join periksa_lab pl on pl.no_rawat=rp.no_rawat left join jns_perawatan_lab jpl on jpl.kd_jenis_prw=pl.kd_jenis_prw  WHERE jpl.nm_perawatan like \"%GULA DARAH%\" and rp.no_rawat=?",rs.getString("no_rawat"))>0){
                                                gd="Ya";
                                            }else{
                                                gd="Tidak";
                                            }
                                            ps5=koneksi.prepareStatement(
                                                "SELECT GROUP_CONCAT(p.nm_penyakit separator '; ' limit 3) as penyakit FROM reg_periksa rp left join diagnosa_pasien dp on dp.no_rawat=rp.no_rawat left join penyakit p on p.kd_penyakit=dp.kd_penyakit WHERE rp.tgl_registrasi BETWEEN ? AND ? and rp.no_rawat=? ");
                                             try {
                                                ps5.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                                ps5.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                                ps5.setString(3,rs.getString("no_rawat"));
                                                rs5=ps5.executeQuery();
                                                while(rs5.next()){
                                                    tabMode.addRow(new Object[]{
                                                        i,rs.getString("tgl"),rs.getString("no_ktp"),rs.getString("nm_pasien"),rs.getString("tgl2"),
                                                        rs.getString("kelamin"),rs.getString("alamat"),rpk,rpd,rs3.getString("td"),imt,lp,gd,rs5.getString("penyakit")
                                                        });
                                                        i++;
                                                    }           
                                            } catch (Exception e) {
                                                System.out.println("laporan.DlgLaporanPuskesmas.tampil1() : "+e);
                                            } finally{
                                                if(rs5!=null){
                                                    rs5.close();
                                                }
                                                if(ps5!=null){
                                                    ps5.close();
                                                }
                                            }
                                            }           
                                    } catch (Exception e) {
                                        System.out.println("laporan.DlgLaporanPuskesmas.tampil1() : "+e);
                                    } finally{
                                        if(rs4!=null){
                                            rs4.close();
                                        }
                                        if(ps4!=null){
                                            ps4.close();
                                        }
                                    }
                                }           
                            } catch (Exception e) {
                                System.out.println("laporan.DlgLaporanPuskesmas.tampil1() : "+e);
                            } finally{
                                if(rs3!=null){
                                    rs3.close();
                                }
                                if(ps3!=null){
                                    ps3.close();
                                }
                            }
                            
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgLaporanPuskesmas.tampil2() : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgLaporanPuskesmas.tampil2() : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }
    
    public void tampil2(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode2);   
            
            ps=koneksi.prepareStatement(
                       "select kamar_inap.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar," +
                       "kamar_inap.tgl_masuk,if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar) as tgl_keluar,kamar_inap.lama,kamar_inap.stts_pulang "+
                       "from kamar_inap inner join reg_periksa inner join pasien inner join kamar inner join bangsal " +
                       "on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis " +
                       "and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal  " +
                       "where kamar_inap.tgl_keluar between ? and ? "+(Kamar.getText().equals("")?"":"and bangsal.nm_bangsal=?")+" order by kamar_inap.tgl_keluar");  
            
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                if(!Kamar.getText().equals("")){
                    ps.setString(3,Kamar.getText().trim());
                }
                rs=ps.executeQuery();
                i=1;  
                hari=0;
                while(rs.next()){
                    tabMode2.addRow(new Object[]{
                        i,rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("kamar"),rs.getString("tgl_masuk"),rs.getString("tgl_keluar"),
                        rs.getString("lama"),rs.getString("stts_pulang")
                    });
                    hari=hari+rs.getDouble("lama");
                    i++;
                }
                if(hari>0){
                    kamar=Sequel.cariInteger("select count(*) from kamar  where statusdata='1'");
                    jumlahhari=Sequel.cariInteger("select (to_days('"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"')-to_days('"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"'))")+1;
                    tabMode2.addRow(new Object[]{"","","","Jumlah Hari Perawatan",":","","",hari,""});
                    tabMode2.addRow(new Object[]{"","","","Jumlah Kamar",":","","",kamar,""});
                    tabMode2.addRow(new Object[]{"","","","Jumlah Hari Dalam Periode",":","","",jumlahhari,""});
                    tabMode2.addRow(new Object[]{"","","","Perhitungan BOR ",": ("+hari+"/("+kamar+" X "+jumlahhari+")) X 100%","","",Valid.SetAngka4((hari/(kamar*jumlahhari))*100)+" %",""});
                }                    
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
    }

    
    private void getData() {
        int row=Tabel1.getSelectedRow();
        if(row!= -1){
            TKd.setText(tabMode.getValueAt(row,0).toString());
        }
    }

}
