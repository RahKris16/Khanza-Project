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
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import simrskhanza.DlgCariPoli;
import simrskhanza.DlgKabupaten;
import simrskhanza.DlgKecamatan;
import simrskhanza.DlgKelurahan;
import simrskhanza.DlgCariCaraBayar;

/**
 *
 * @author perpustakaan
 */
public final class DlgVerifikasiCasemix extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private DlgCariPoli poli=new DlgCariPoli(null,false);
    private DlgCariDokter dokter=new DlgCariDokter(null,false);
    private DlgKabupaten kabupaten=new DlgKabupaten(null,false);
    private DlgKecamatan kecamatan=new DlgKecamatan(null,false);
    private DlgKelurahan kelurahan=new DlgKelurahan(null,false);
    private DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    private int i=0,lama=0,baru=0,laki=0,per=0;   
    private String setbaru="",setlama="",umurlk="",umurpr="",kddiangnosa="",diagnosa="",pilihan="",status="";
    private StringBuilder htmlContent;
    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgVerifikasiCasemix(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        tabMode=new DefaultTableModel(null,new Object[]{"No.","No Rawat","Nama Pasien","Jenis Kelamin","Alamat","Tgl Registrasi","Poliklinik",
            "Nama Dokter","Status","Tgl Verifikasi"}){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        table1.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        table1.setPreferredScrollableViewportSize(new Dimension(500,500));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 10; i++) {
            TableColumn column = table1.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(35);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(300);
            }else if(i==3){
                column.setPreferredWidth(75);
            }else if(i==4){
                column.setPreferredWidth(250);
            }else if(i==5){
                column.setPreferredWidth(125);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(250);
            }else if(i==8){
                column.setPreferredWidth(150);
            }else if(i==9){
                column.setPreferredWidth(150);
            }
        }
        table1.setDefaultRenderer(Object.class, new WarnaTable());

        TCari.setDocument(new batasInput((int)90).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        }
        
        ChkInput.setSelected(true);
        isForm();
        
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        verif = new javax.swing.JMenuItem();
        batalverif = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        panelGlass5 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        jLabel8 = new widget.Label();
        BtnKeluar = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.panelisi();
        label17 = new widget.Label();
        label20 = new widget.Label();
        bayar = new javax.swing.JComboBox<>();
        unit = new javax.swing.JComboBox<>();
        label19 = new widget.Label();
        norawat = new widget.TextBox();
        label21 = new widget.Label();
        nama = new widget.TextBox();
        TabRawat = new javax.swing.JTabbedPane();
        internalFrame2 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        table1 = new widget.Table();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        verif.setBackground(new java.awt.Color(255, 255, 254));
        verif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        verif.setForeground(java.awt.Color.darkGray);
        verif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        verif.setText("Verifikasi");
        verif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        verif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        verif.setName("verif"); // NOI18N
        verif.setPreferredSize(new java.awt.Dimension(175, 25));
        verif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verifBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(verif);

        batalverif.setBackground(new java.awt.Color(255, 255, 254));
        batalverif.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        batalverif.setForeground(java.awt.Color.darkGray);
        batalverif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        batalverif.setText("Batal Verifikasi");
        batalverif.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        batalverif.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        batalverif.setName("batalverif"); // NOI18N
        batalverif.setPreferredSize(new java.awt.Dimension(175, 25));
        batalverif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                batalverifBtnPrintActionPerformed(evt);
            }
        });
        jPopupMenu1.add(batalverif);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Verifikasi Claim Casemix ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
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

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 23));
        panelGlass5.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(155, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass5.add(TCari);

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

        jLabel8.setName("jLabel8"); // NOI18N
        jLabel8.setPreferredSize(new java.awt.Dimension(20, 23));
        panelGlass5.add(jLabel8);

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

        PanelInput.setBackground(new java.awt.Color(255, 255, 255));
        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Filter Data");
        ChkInput.setBorderPainted(true);
        ChkInput.setBorderPaintedFlat(true);
        ChkInput.setFocusable(false);
        ChkInput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ChkInput.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ChkInput.setName("ChkInput"); // NOI18N
        ChkInput.setPreferredSize(new java.awt.Dimension(192, 20));
        ChkInput.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/145.png"))); // NOI18N
        ChkInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkInputActionPerformed(evt);
            }
        });
        PanelInput.add(ChkInput, java.awt.BorderLayout.PAGE_END);

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 104));
        FormInput.setRequestFocusEnabled(false);
        FormInput.setLayout(null);

        label17.setText("Unit/Poli :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(35, 23));
        FormInput.add(label17);
        label17.setBounds(0, 40, 75, 23);

        label20.setText("Status :");
        label20.setName("label20"); // NOI18N
        label20.setPreferredSize(new java.awt.Dimension(35, 23));
        FormInput.add(label20);
        label20.setBounds(250, 40, 75, 23);

        bayar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Ralan", "Ranap" }));
        bayar.setName("bayar"); // NOI18N
        FormInput.add(bayar);
        bayar.setBounds(330, 40, 70, 22);

        unit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "IGD", "Poliklinik Obgyn", "Poliklinik Penyakit Dalam", "Poliklinik Anak", "Poliklinik Bedah", "Poliklinik THT-KL", "Poliklinik Anestesi", "Poliklinik Urologi", "Poliklinik Orthopedi", "Poliklinik Saraf", "Poliklinik Jantung dan Pembuluh Darah", "Poliklinik Psikiatri", "Poliklinik Mata", "Poliklinik Paru", "Poliklinik Umum", "Poliklinik Bedah dan Ortopedi", "Poliklinik Rehabilitasi Medik", "Poliklinik Gigi", "Poliklinik Umum", "Poliklinik Kulit Kelamin", "Poliklinik Persiapan", "Poliklinik Bedah Saraf", "Unit Dialisis", "Hemodialisis" }));
        unit.setName("unit"); // NOI18N
        FormInput.add(unit);
        unit.setBounds(80, 40, 190, 22);

        label19.setText("No Rawat :");
        label19.setName("label19"); // NOI18N
        label19.setPreferredSize(new java.awt.Dimension(35, 23));
        FormInput.add(label19);
        label19.setBounds(0, 10, 75, 23);

        norawat.setName("norawat"); // NOI18N
        norawat.setPreferredSize(new java.awt.Dimension(155, 23));
        norawat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                norawatKeyPressed(evt);
            }
        });
        FormInput.add(norawat);
        norawat.setBounds(80, 10, 140, 23);

        label21.setText("Nama :");
        label21.setName("label21"); // NOI18N
        label21.setPreferredSize(new java.awt.Dimension(35, 23));
        FormInput.add(label21);
        label21.setBounds(230, 10, 50, 23);

        nama.setName("nama"); // NOI18N
        nama.setPreferredSize(new java.awt.Dimension(155, 23));
        nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                namaKeyPressed(evt);
            }
        });
        FormInput.add(nama);
        nama.setBounds(285, 10, 400, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        TabRawat.setBackground(new java.awt.Color(255, 255, 254));
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

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        table1.setComponentPopupMenu(jPopupMenu1);
        table1.setName("table1"); // NOI18N
        table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table1MouseClicked(evt);
            }
        });
        table1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                table1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                table1KeyTyped(evt);
            }
        });
        Scroll.setViewportView(table1);

        internalFrame2.add(Scroll, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Seluruh Kunjungan", internalFrame2);

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

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
    }//GEN-LAST:event_TCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        unit.setSelectedIndex(0);
        bayar.setSelectedIndex(0);
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if(TabRawat.getSelectedIndex()==0){
            tampil();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void verifBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verifBtnPrintActionPerformed
        if(norawat.getText().trim().equals("")){
            Valid.textKosong(norawat,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from verifikasi where no_rawat=?",norawat.getText())>0){
                JOptionPane.showMessageDialog(null,"Pasien sudah terverifikasi..!!!");
            }else{
                Sequel.menyimpan("verifikasi","'"+norawat.getText()+"',now()");
                tampil();
                norawat.setText("");
                nama.setText("");
            }
        }
    }//GEN-LAST:event_verifBtnPrintActionPerformed

    private void batalverifBtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalverifBtnPrintActionPerformed
        if(norawat.getText().trim().equals("")){
            Valid.textKosong(norawat,"No.Rawat");
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from verifikasi where no_rawat=?",norawat.getText())==0){
                JOptionPane.showMessageDialog(null,"Pasien belum terverifikasi..!!!");
            }else{
                Sequel.meghapus("verifikasi","no_rawat", norawat.getText());
                tampil();
                norawat.setText("");
                nama.setText("");
            }
        }
    }//GEN-LAST:event_batalverifBtnPrintActionPerformed

    private void norawatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_norawatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_norawatKeyPressed

    private void namaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaKeyPressed

    private void table1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyPressed
        if(table1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }             
        }
    }//GEN-LAST:event_table1KeyPressed

    private void table1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyReleased
        if(table1.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_table1KeyReleased

    private void table1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_table1KeyTyped

    private void table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table1MouseClicked
        if(table1.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }            
        }
    }//GEN-LAST:event_table1MouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgVerifikasiCasemix dialog = new DlgVerifikasiCasemix(new javax.swing.JFrame(), true);
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
    private widget.CekBox ChkInput;
    private widget.panelisi FormInput;
    private javax.swing.JPanel PanelInput;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private javax.swing.JMenuItem batalverif;
    private javax.swing.JComboBox<String> bayar;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel6;
    private widget.Label jLabel8;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label19;
    private widget.Label label20;
    private widget.Label label21;
    private widget.TextBox nama;
    private widget.TextBox norawat;
    private widget.panelisi panelGlass5;
    private widget.Table table1;
    private javax.swing.JComboBox<String> unit;
    private javax.swing.JMenuItem verif;
    // End of variables declaration//GEN-END:variables

    public void tampil(){        
        try{   
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Valid.tabelKosong(tabMode);   
//            if(unit.getSelectedIndex(0).trim().equals("")&&nmdokter.getText().trim().equals("")&&nmpenjab.getText().trim().equals("")&&nmkabupaten.getText().trim().equals("")&&nmkecamatan.getText().trim().equals("")&&nmkelurahan.getText().trim().equals("")&&TCari.getText().trim().equals("")){
            if(unit.getSelectedItem().toString().equals("Semua")&&bayar.getSelectedItem().toString().equals("Semua")&&TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,\n" +
                        "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,\n" +
                        "pasien.umur,poliklinik.nm_poli,reg_periksa.status_lanjut,reg_periksa.umurdaftar,reg_periksa.sttsumur,\n" +
                        "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,verifikasi.tanggal \n" +
                        "from reg_periksa \n" +
                        "INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter\n" +
                        "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
                        "INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli\n" +
                        "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj\n" +
                        "left join verifikasi on reg_periksa.no_rawat=verifikasi.no_rawat \n" +
                        "where reg_periksa.tgl_registrasi between ? and ? and penjab.png_jawab like '%BPJS KESEHATAN%' " +
                        "order by reg_periksa.tgl_registrasi asc,reg_periksa.jam_reg asc");
            }else{
                ps=koneksi.prepareStatement(
                        "select reg_periksa.no_reg,reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.jam_reg,\n" +
                        "reg_periksa.kd_dokter,dokter.nm_dokter,reg_periksa.no_rkm_medis,pasien.nm_pasien,if(pasien.jk='L','Laki-Laki','Perempuan') as jk,\n" +
                        "pasien.umur,poliklinik.nm_poli,reg_periksa.status_lanjut,reg_periksa.umurdaftar,reg_periksa.sttsumur,\n" +
                        "reg_periksa.p_jawab,reg_periksa.almt_pj,reg_periksa.hubunganpj,reg_periksa.biaya_reg,reg_periksa.stts_daftar,penjab.png_jawab,verifikasi.tanggal \n" +
                        "from reg_periksa \n" +
                        "INNER JOIN dokter ON reg_periksa.kd_dokter = dokter.kd_dokter\n" +
                        "INNER JOIN pasien ON reg_periksa.no_rkm_medis = pasien.no_rkm_medis\n" +
                        "INNER JOIN poliklinik ON reg_periksa.kd_poli = poliklinik.kd_poli\n" +
                        "INNER JOIN penjab ON reg_periksa.kd_pj = penjab.kd_pj\n" +
                        "left join verifikasi on reg_periksa.no_rawat=verifikasi.no_rawat \n" +
                        "where reg_periksa.tgl_registrasi BETWEEN ? AND ? AND penjab.png_jawab LIKE '%BPJS KESEHATAN%'\n" +
                        "AND poliklinik.nm_poli LIKE ? AND reg_periksa.status_lanjut LIKE ? AND (reg_periksa.no_rkm_medis LIKE ? or pasien.nm_pasien LIKE ?) " +
                        "order by reg_periksa.tgl_registrasi asc,reg_periksa.jam_reg asc");
            }
                
            try {
                if(unit.getSelectedItem().toString().equals("Semua")&&bayar.getSelectedItem().toString().equals("Semua")&&TCari.getText().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                }else{
                    ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                    ps.setString(3,"%"+unit.getSelectedItem().toString().replaceAll("Semua","")+"%");
                    ps.setString(4,"%"+bayar.getSelectedItem().toString().replaceAll("Semua","")+"%");
                    ps.setString(5,"%"+TCari.getText().trim()+"%");
                    ps.setString(6,"%"+TCari.getText().trim()+"%");
//                    ps.setString(11,"%"+TCari.getText().trim()+"%");
//                    ps.setString(12,"%"+TCari.getText().trim()+"%");
//                    ps.setString(13,"%"+TCari.getText().trim()+"%");
                }
                    
                rs=ps.executeQuery();
                i=1;   
                lama=0;baru=0;laki=0;per=0;
                while(rs.next()){
                    setbaru="";
                    setlama="";
                    if(rs.getString("stts_daftar").equals("Baru")){
                        setbaru=rs.getString("no_rkm_medis");
                        baru++;
                    }else if(rs.getString("stts_daftar").equals("Lama")){
                        setlama=rs.getString("no_rkm_medis");
                        lama++;
                    }
                    umurlk="";
                    umurpr="";
                    switch (rs.getString("jk")) {
                        case "L":
                            umurlk=rs.getString("umur");
                            laki++;
                            break;
                        case "P":
                            umurpr=rs.getString("umur");
                            per++;
                            break;
                    }
                    diagnosa="";
                    kddiangnosa="";
                    ps2=koneksi.prepareStatement("select penyakit.kd_penyakit,penyakit.nm_penyakit from penyakit inner join diagnosa_pasien " +
                        "on diagnosa_pasien.kd_penyakit=penyakit.kd_penyakit " +
                        "where diagnosa_pasien.no_rawat=? order by prioritas asc limit 1");
                    try {
                        ps2.setString(1,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){
                            kddiangnosa=rs2.getString(1);
                            diagnosa=rs2.getString(2);
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgKunjunganRalan.tampil() 2 :"+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }                        
                    tabMode.addRow(new Object[]{
                        i,rs.getString("no_rawat"),rs.getString("no_rkm_medis")+" "+rs.getString("nm_pasien")+" ("+rs.getString("umurdaftar")
                        +" "+rs.getString("sttsumur")+") ",rs.getString("jk"),rs.getString("almt_pj"),rs.getString("tgl_registrasi")
                        +" "+rs.getString("jam_reg"),rs.getString("nm_poli"),rs.getString("nm_dokter"),
                        rs.getString("status_lanjut")+" ("+rs.getString("png_jawab")+") ",rs.getString("tanggal")
                    });                
                    i++;
                }
//                if(i>=2){
//                    tabMode.addRow(new Object[]{
//                        ">>",lama,baru,"",laki,per,"","","",""
//                    });
//                }
            } catch (Exception e) {
                System.out.println("laporan.DlgKunjunganRalan.tampil() : "+e);
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
        if(table1.getSelectedRow()!= -1){
            norawat.setText(table1.getValueAt(table1.getSelectedRow(),1).toString());
            nama.setText(table1.getValueAt(table1.getSelectedRow(),2).toString());  
        }
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,126));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }

}
