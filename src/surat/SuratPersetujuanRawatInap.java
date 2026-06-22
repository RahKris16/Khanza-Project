/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package surat;

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import kepegawaian.DlgCariPetugas;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.Timer;
import java.awt.event.ActionEvent;

/**
 * 
 * @author salimmulyana
 */
public final class SuratPersetujuanRawatInap extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
//    private double umum=0,iks=0;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private StringBuilder htmlContent;
    private String nama_iks,hak_kelas,naik_kelas,penjelasan,diskusi,demonstrasi,praktek,menerima_kartu,hilang,mengembalikan,
            metode_2,tujuan_2="",evaluasi_2="",umum,iks,tanggal,finger,tambahan;
    
    public SuratPersetujuanRawatInap(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Persetujuan","No.Rawat","No.R.M.","Nama Pasien","Tgl MRS","Umur","Agama","Tgl.Lahir","Alamat","Nama PJ","Alamat PJ",
            "Pekerjaan","No. Telp","Hubungan","Depo Umum","IKS","Depo IKS","Hak Kelas","Naik Kelas","Menerima Kartu","Hilang","Mengembalikan",
            "Tambahan","Penjelasan","Diskusi","Demonstrasi","Praktek","Tujuan","Evaluasi","NIP","Nama Petugas","Jam"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 32; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(60);
            }else if(i==3){
                column.setPreferredWidth(250);
            }else if(i==4){
                column.setPreferredWidth(65);
            }else if(i==5){
                column.setPreferredWidth(50);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setPreferredWidth(200);
            }else if(i==9){
                column.setPreferredWidth(250);
            }else if(i==10){
                column.setPreferredWidth(200);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(90);
            }else if(i==13){
                column.setPreferredWidth(90);
            }else if(i==14){
                column.setPreferredWidth(90);
            }else if(i==15){
                column.setPreferredWidth(100);
            }else if(i==16){
                column.setPreferredWidth(90);
            }else if(i==17){
                column.setPreferredWidth(90);
            }else if(i==18){
                column.setPreferredWidth(90);
            }else if(i==19){
                column.setPreferredWidth(90);
            }else if(i==20){
                column.setPreferredWidth(90);
            }else if(i==21){
                column.setPreferredWidth(90);
            }else if(i==22){
                column.setPreferredWidth(90);
            }else if(i==23){
                column.setPreferredWidth(90);
            }else if(i==24){
                column.setPreferredWidth(90);
            }else if(i==25){
                column.setPreferredWidth(90);
            }else if(i==26){
                column.setPreferredWidth(90);
            }else if(i==27){
                column.setPreferredWidth(150);
            }else if(i==28){
                column.setPreferredWidth(90);
            }else if(i==29){
                column.setPreferredWidth(75);
            }else if(i==30){
                column.setPreferredWidth(200);
            }else if(i==31){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));    
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));  
        NoSurat.setDocument(new batasInput((byte)20).getKata(NoSurat));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        NamaPJ.setDocument(new batasInput((byte)50).getKata(NamaPJ));
        PekerjaanPJ.setDocument(new batasInput((byte)20).getKata(PekerjaanPJ));
        AlamatPJ.setDocument(new batasInput((int)100).getKata(AlamatPJ));  
        NoTelp.setDocument(new batasInput((byte)30).getKata(NoTelp));  
        
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
        
        petugas.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(petugas.getTable().getSelectedRow()!= -1){                   
                    NIP.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),0).toString());
                    NamaPetugas.setText(petugas.getTable().getValueAt(petugas.getTable().getSelectedRow(),1).toString());
                }  
                NIP.requestFocus();
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
        jam();
        ChkInput.setSelected(false);
        isForm();
        
        ChkAccor.setSelected(true);
        isPhoto();
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML2.setEditable(true);
        LoadHTML2.setEditorKit(kit);
        LoadHTML.setEditable(true);
        LoadHTML.setEditorKit(kit);
        StyleSheet styleSheet = kit.getStyleSheet();
        styleSheet.addRule(
                ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
        );
        Document doc = kit.createDefaultDocument();
        LoadHTML2.setDocument(doc);
        LoadHTML.setDocument(doc);
        
    }
    
    
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        LoadHTML = new widget.editorpane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        SuratPersetujuanRawatInap = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbObat = new widget.Table();
        jPanel3 = new javax.swing.JPanel();
        panelGlass8 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        btnAmbil1 = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        jLabel8 = new widget.Label();
        NamaPJ = new widget.TextBox();
        jLabel10 = new widget.Label();
        jLabel11 = new widget.Label();
        Hubungan = new widget.ComboBox();
        AlamatPJ = new widget.TextBox();
        jLabel17 = new widget.Label();
        LahirPasien = new widget.TextBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel16 = new widget.Label();
        Tanggal = new widget.Tanggal();
        jLabel14 = new widget.Label();
        jLabel3 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel15 = new widget.Label();
        PekerjaanPJ = new widget.TextBox();
        NoTelp = new widget.TextBox();
        jLabel20 = new widget.Label();
        NaikKelas = new widget.ComboBox();
        jLabel13 = new widget.Label();
        HakKelas = new widget.ComboBox();
        jLabel24 = new widget.Label();
        jLabel5 = new widget.Label();
        jLabel28 = new widget.Label();
        Umur = new widget.TextBox();
        jLabel29 = new widget.Label();
        Alamat = new widget.TextBox();
        Agama = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        DepoUmum = new widget.TextBox();
        NamaIKS = new widget.TextBox();
        DepoIKS = new widget.TextBox();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel31 = new widget.Label();
        jLabel32 = new widget.Label();
        jLabel33 = new widget.Label();
        jLabel34 = new widget.Label();
        Umum = new widget.RadioButton();
        IKS = new widget.RadioButton();
        BPJS = new widget.RadioButton();
        Mengulangi1 = new widget.RadioButton();
        Mengerti1 = new widget.RadioButton();
        Tidak1 = new widget.RadioButton();
        Reedukasi1 = new widget.RadioButton();
        Redemonstrasi1 = new widget.RadioButton();
        Tglre1 = new widget.TextBox();
        Tanggalre1 = new widget.RadioButton();
        ChkRenogram6 = new widget.CekBox();
        jLabel41 = new widget.Label();
        ChkRenogram7 = new widget.CekBox();
        jLabel42 = new widget.Label();
        ChkRenogram8 = new widget.CekBox();
        jLabel43 = new widget.Label();
        ChkRenogram9 = new widget.CekBox();
        scrollPane1 = new widget.ScrollPane();
        Tambahan = new widget.TextArea();
        ChkSemua = new widget.CekBox();
        jLabel44 = new widget.Label();
        ChkRenogram11 = new widget.CekBox();
        ChkRenogram12 = new widget.CekBox();
        ChkRenogram13 = new widget.CekBox();
        ChkRenogram14 = new widget.CekBox();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        ChkJln = new widget.CekBox();
        CmbDetik = new widget.ComboBox();
        CmbMenit = new widget.ComboBox();
        CmbJam = new widget.ComboBox();
        jLabel9 = new widget.Label();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormPhoto = new widget.PanelBiasa();
        FormPass3 = new widget.PanelBiasa();
        btnAmbil = new widget.Button();
        BtnRefreshPhoto1 = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();

        LoadHTML.setBorder(null);
        LoadHTML.setName("LoadHTML"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        SuratPersetujuanRawatInap.setBackground(new java.awt.Color(255, 255, 254));
        SuratPersetujuanRawatInap.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        SuratPersetujuanRawatInap.setForeground(new java.awt.Color(50, 50, 50));
        SuratPersetujuanRawatInap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        SuratPersetujuanRawatInap.setText("Surat Persetujuan Rawat Inap");
        SuratPersetujuanRawatInap.setName("SuratPersetujuanRawatInap"); // NOI18N
        SuratPersetujuanRawatInap.setPreferredSize(new java.awt.Dimension(250, 26));
        SuratPersetujuanRawatInap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuratPersetujuanRawatInapActionPerformed(evt);
            }
        });
        jPopupMenu1.add(SuratPersetujuanRawatInap);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Persetujuan Rawat Inap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(452, 200));

        tbObat.setAutoCreateRowSorter(true);
        tbObat.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbObat.setComponentPopupMenu(jPopupMenu1);
        tbObat.setName("tbObat"); // NOI18N
        tbObat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbObatMouseClicked(evt);
            }
        });
        tbObat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbObatKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbObat);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        jPanel3.setName("jPanel3"); // NOI18N
        jPanel3.setOpaque(false);
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 100));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass8.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnBatal);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnEdit);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass8.add(BtnPrint);

        btnAmbil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnAmbil1.setMnemonic('U');
        btnAmbil1.setText("Ambil");
        btnAmbil1.setToolTipText("Alt+U");
        btnAmbil1.setName("btnAmbil1"); // NOI18N
        btnAmbil1.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAmbil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbil1ActionPerformed(evt);
            }
        });
        panelGlass8.add(btnAmbil1);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
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
        panelGlass8.add(BtnAll);

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
        panelGlass8.add(BtnKeluar);

        jPanel3.add(panelGlass8, java.awt.BorderLayout.CENTER);

        panelGlass9.setName("panelGlass9"); // NOI18N
        panelGlass9.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass9.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel19.setText("Tgl.Persetujuan :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(92, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-02-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-02-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(180, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass9.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('3');
        BtnCari.setToolTipText("Alt+3");
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
        panelGlass9.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass9.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass9.add(LCount);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 265));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('I');
        ChkInput.setText(".: Input Data");
        ChkInput.setToolTipText("Alt+I");
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

        scrollInput.setName("scrollInput"); // NOI18N
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 562));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 560));
        FormInput.setLayout(null);

        jLabel4.setText("No.Rawat :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(0, 10, 70, 23);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(74, 10, 136, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(325, 10, 255, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        FormInput.add(TNoRM);
        TNoRM.setBounds(212, 10, 111, 23);

        jLabel8.setText(":");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(570, 150, 60, 23);

        NamaPJ.setName("NamaPJ"); // NOI18N
        NamaPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPJKeyPressed(evt);
            }
        });
        FormInput.add(NamaPJ);
        NamaPJ.setBounds(104, 120, 190, 23);

        jLabel10.setText("Nama :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(0, 120, 100, 23);

        jLabel11.setText("Alamat :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(310, 120, 55, 23);

        Hubungan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Suami", "Istri", "Anak", "Ayah", "Ibu", "Saudara", "Keponakan", "Diri Sendiri" }));
        Hubungan.setName("Hubungan"); // NOI18N
        Hubungan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubunganKeyPressed(evt);
            }
        });
        FormInput.add(Hubungan);
        Hubungan.setBounds(633, 150, 100, 23);

        AlamatPJ.setName("AlamatPJ"); // NOI18N
        AlamatPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatPJKeyPressed(evt);
            }
        });
        FormInput.add(AlamatPJ);
        AlamatPJ.setBounds(369, 120, 364, 23);

        jLabel17.setText("Tgl.Lahir :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(584, 10, 60, 23);

        LahirPasien.setHighlighter(null);
        LahirPasien.setName("LahirPasien"); // NOI18N
        FormInput.add(LahirPasien);
        LahirPasien.setBounds(648, 10, 85, 23);

        jLabel18.setText("Petugas :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(0, 64, 70, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(74, 64, 100, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(176, 64, 150, 23);

        btnPetugas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnPetugas.setMnemonic('2');
        btnPetugas.setToolTipText("ALt+2");
        btnPetugas.setName("btnPetugas"); // NOI18N
        btnPetugas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPetugasActionPerformed(evt);
            }
        });
        btnPetugas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnPetugasKeyPressed(evt);
            }
        });
        FormInput.add(btnPetugas);
        btnPetugas.setBounds(324, 64, 28, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 37, 70, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12-02-2026" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(74, 37, 90, 23);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Pembuat Persetujuan :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(16, 92, 130, 23);

        jLabel3.setText("No. Persetujuan :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(520, 64, 90, 23);

        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(614, 64, 119, 23);

        jLabel15.setText("Pekerjaan :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 150, 100, 23);

        PekerjaanPJ.setName("PekerjaanPJ"); // NOI18N
        PekerjaanPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PekerjaanPJKeyPressed(evt);
            }
        });
        FormInput.add(PekerjaanPJ);
        PekerjaanPJ.setBounds(104, 150, 160, 23);

        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        FormInput.add(NoTelp);
        NoTelp.setBounds(333, 150, 150, 23);

        jLabel20.setText("No.Telp :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(260, 150, 70, 23);

        NaikKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-", "Kelas 1", "Kelas 2", "Kelas 3", "Kelas VIP", "Kelas VVIP" }));
        NaikKelas.setName("NaikKelas"); // NOI18N
        NaikKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NaikKelasKeyPressed(evt);
            }
        });
        FormInput.add(NaikKelas);
        NaikKelas.setBounds(405, 240, 90, 23);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("atau BPJS naik ke");
        jLabel13.setToolTipText("");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(315, 240, 120, 23);

        HakKelas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kelas 1", "Kelas 2", "Kelas 3", "Kelas VIP", "Kelas VVIP" }));
        HakKelas.setName("HakKelas"); // NOI18N
        HakKelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HakKelasKeyPressed(evt);
            }
        });
        FormInput.add(HakKelas);
        HakKelas.setBounds(225, 240, 82, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Hubungan dengan pasien");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(500, 150, 130, 23);

        jLabel5.setText("Agama :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(330, 64, 70, 23);

        jLabel28.setText("Umur :");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(584, 37, 60, 23);

        Umur.setHighlighter(null);
        Umur.setName("Umur"); // NOI18N
        FormInput.add(Umur);
        Umur.setBounds(648, 37, 85, 23);

        jLabel29.setText("Alamat :");
        jLabel29.setName("jLabel29"); // NOI18N
        FormInput.add(jLabel29);
        jLabel29.setBounds(170, 37, 60, 23);

        Alamat.setHighlighter(null);
        Alamat.setName("Alamat"); // NOI18N
        FormInput.add(Alamat);
        Alamat.setBounds(235, 37, 345, 23);

        Agama.setHighlighter(null);
        Agama.setName("Agama"); // NOI18N
        Agama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AgamaKeyPressed(evt);
            }
        });
        FormInput.add(Agama);
        Agama.setBounds(402, 64, 110, 23);

        jLabel22.setText("Sebagai pasien :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(0, 180, 100, 23);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("membayar uang muka sebesar");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(303, 210, 160, 23);

        DepoUmum.setName("DepoUmum"); // NOI18N
        DepoUmum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DepoUmumKeyPressed(evt);
            }
        });
        FormInput.add(DepoUmum);
        DepoUmum.setBounds(308, 180, 150, 23);

        NamaIKS.setName("NamaIKS"); // NOI18N
        NamaIKS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaIKSKeyPressed(evt);
            }
        });
        FormInput.add(NamaIKS);
        NamaIKS.setBounds(147, 210, 150, 23);

        DepoIKS.setName("DepoIKS"); // NOI18N
        DepoIKS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DepoIKSKeyPressed(evt);
            }
        });
        FormInput.add(DepoIKS);
        DepoIKS.setBounds(452, 210, 150, 23);

        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(20, 270, 720, 3);

        jLabel31.setText("Materi Edukasi :");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(0, 275, 100, 23);

        jLabel32.setText("Metode Edukasi :");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(0, 425, 100, 23);

        jLabel33.setText("Tujuan :");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(0, 450, 100, 23);

        jLabel34.setText("Evaluasi/Verifikasi :");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(0, 475, 100, 23);

        buttonGroup1.add(Umum);
        Umum.setForeground(new java.awt.Color(0, 0, 0));
        Umum.setText("UMUM membayar uang muka sebesar");
        Umum.setName("Umum"); // NOI18N
        Umum.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Umum);
        Umum.setBounds(104, 180, 210, 23);

        buttonGroup1.add(IKS);
        IKS.setForeground(new java.awt.Color(0, 0, 0));
        IKS.setText("IKS");
        IKS.setName("IKS"); // NOI18N
        IKS.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(IKS);
        IKS.setBounds(104, 210, 210, 23);

        buttonGroup1.add(BPJS);
        BPJS.setForeground(new java.awt.Color(0, 0, 0));
        BPJS.setText("BPJS sesuai dengan");
        BPJS.setName("BPJS"); // NOI18N
        BPJS.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(BPJS);
        BPJS.setBounds(104, 240, 210, 23);

        buttonGroup6.add(Mengulangi1);
        Mengulangi1.setForeground(new java.awt.Color(0, 0, 0));
        Mengulangi1.setText("Dapat mengulangi edukasi yang didapat");
        Mengulangi1.setName("Mengulangi1"); // NOI18N
        Mengulangi1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Mengulangi1);
        Mengulangi1.setBounds(104, 450, 240, 23);

        buttonGroup7.add(Mengerti1);
        Mengerti1.setForeground(new java.awt.Color(0, 0, 0));
        Mengerti1.setText("Mengerti");
        Mengerti1.setName("Mengerti1"); // NOI18N
        Mengerti1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Mengerti1);
        Mengerti1.setBounds(104, 475, 140, 23);

        buttonGroup7.add(Tidak1);
        Tidak1.setForeground(new java.awt.Color(0, 0, 0));
        Tidak1.setText("Tidak mengerti");
        Tidak1.setName("Tidak1"); // NOI18N
        Tidak1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Tidak1);
        Tidak1.setBounds(254, 475, 140, 23);

        buttonGroup7.add(Reedukasi1);
        Reedukasi1.setForeground(new java.awt.Color(0, 0, 0));
        Reedukasi1.setText("Re - edukasi");
        Reedukasi1.setName("Reedukasi1"); // NOI18N
        Reedukasi1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Reedukasi1);
        Reedukasi1.setBounds(404, 475, 140, 23);

        buttonGroup7.add(Redemonstrasi1);
        Redemonstrasi1.setForeground(new java.awt.Color(0, 0, 0));
        Redemonstrasi1.setText("Re - demonstrasi");
        Redemonstrasi1.setName("Redemonstrasi1"); // NOI18N
        Redemonstrasi1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Redemonstrasi1);
        Redemonstrasi1.setBounds(104, 500, 140, 23);

        Tglre1.setName("Tglre1"); // NOI18N
        Tglre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tglre1KeyPressed(evt);
            }
        });
        FormInput.add(Tglre1);
        Tglre1.setBounds(342, 500, 100, 23);

        buttonGroup7.add(Tanggalre1);
        Tanggalre1.setForeground(new java.awt.Color(0, 0, 0));
        Tanggalre1.setText("Tanggal Re -");
        Tanggalre1.setName("Tanggalre1"); // NOI18N
        Tanggalre1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Tanggalre1);
        Tanggalre1.setBounds(254, 500, 140, 23);

        ChkRenogram6.setBorder(null);
        ChkRenogram6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram6.setName("ChkRenogram6"); // NOI18N
        ChkRenogram6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram6ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram6);
        ChkRenogram6.setBounds(101, 275, 23, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("Pasien/keluarga sudah diberikan dan sudah menerima kartu penunggu pasien");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(125, 275, 420, 23);

        ChkRenogram7.setBorder(null);
        ChkRenogram7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram7.setName("ChkRenogram7"); // NOI18N
        ChkRenogram7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram7ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram7);
        ChkRenogram7.setBounds(101, 300, 23, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Pasien/keluarga sudah dijelaskan kartu penunggu pasien tidak boleh hilang, apabila hilang akan dikenakan denda Rp 10.000.");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(125, 300, 610, 23);

        ChkRenogram8.setBorder(null);
        ChkRenogram8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram8.setName("ChkRenogram8"); // NOI18N
        ChkRenogram8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram8ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram8);
        ChkRenogram8.setBounds(101, 325, 23, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Apabila pasien pulang harap mengembalikan kartu penunggu pasien ke petugas kasir");
        jLabel43.setToolTipText("");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(125, 325, 610, 23);

        ChkRenogram9.setBorder(null);
        ChkRenogram9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram9.setName("ChkRenogram9"); // NOI18N
        ChkRenogram9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram9ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram9);
        ChkRenogram9.setBounds(101, 350, 23, 23);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane1.setName("scrollPane1"); // NOI18N

        Tambahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Tambahan.setColumns(20);
        Tambahan.setRows(5);
        Tambahan.setName("Tambahan"); // NOI18N
        Tambahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TambahanKeyPressed(evt);
            }
        });
        scrollPane1.setViewportView(Tambahan);

        FormInput.add(scrollPane1);
        scrollPane1.setBounds(125, 352, 610, 70);

        ChkSemua.setBorder(null);
        ChkSemua.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkSemua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkSemua.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkSemua.setName("ChkSemua"); // NOI18N
        ChkSemua.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkSemuaItemStateChanged(evt);
            }
        });
        ChkSemua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkSemuaActionPerformed(evt);
            }
        });
        FormInput.add(ChkSemua);
        ChkSemua.setBounds(650, 275, 23, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("Pilih semua");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(675, 275, 70, 23);

        ChkRenogram11.setBorder(null);
        ChkRenogram11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram11.setName("ChkRenogram11"); // NOI18N
        ChkRenogram11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram11ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram11);
        ChkRenogram11.setBounds(101, 425, 23, 23);

        ChkRenogram12.setBorder(null);
        ChkRenogram12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram12.setName("ChkRenogram12"); // NOI18N
        ChkRenogram12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram12ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram12);
        ChkRenogram12.setBounds(254, 425, 23, 23);

        ChkRenogram13.setBorder(null);
        ChkRenogram13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram13.setName("ChkRenogram13"); // NOI18N
        ChkRenogram13.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram13ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram13);
        ChkRenogram13.setBounds(404, 425, 23, 23);

        ChkRenogram14.setBorder(null);
        ChkRenogram14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram14.setName("ChkRenogram14"); // NOI18N
        ChkRenogram14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram14ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram14);
        ChkRenogram14.setBounds(554, 425, 23, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Penjelasan");
        jLabel45.setToolTipText("");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(125, 425, 90, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Diskusi");
        jLabel46.setToolTipText("");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(278, 425, 90, 23);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Demonstrasi");
        jLabel47.setToolTipText("");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(428, 425, 90, 23);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Praktek Langsung");
        jLabel48.setToolTipText("");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(578, 425, 90, 23);

        ChkJln.setBorder(null);
        ChkJln.setSelected(true);
        ChkJln.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkJln.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkJln.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkJln.setName("ChkJln"); // NOI18N
        ChkJln.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkJlnActionPerformed(evt);
            }
        });
        FormInput.add(ChkJln);
        ChkJln.setBounds(710, 92, 23, 23);

        CmbDetik.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbDetik.setName("CmbDetik"); // NOI18N
        CmbDetik.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbDetikKeyPressed(evt);
            }
        });
        FormInput.add(CmbDetik);
        CmbDetik.setBounds(648, 92, 62, 23);

        CmbMenit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));
        CmbMenit.setName("CmbMenit"); // NOI18N
        CmbMenit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbMenitKeyPressed(evt);
            }
        });
        FormInput.add(CmbMenit);
        CmbMenit.setBounds(584, 92, 62, 23);

        CmbJam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));
        CmbJam.setName("CmbJam"); // NOI18N
        CmbJam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CmbJamKeyPressed(evt);
            }
        });
        FormInput.add(CmbJam);
        CmbJam.setBounds(520, 92, 62, 23);

        jLabel9.setText("Jam :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(479, 92, 36, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        PanelAccor.setBackground(new java.awt.Color(255, 255, 255));
        PanelAccor.setName("PanelAccor"); // NOI18N
        PanelAccor.setPreferredSize(new java.awt.Dimension(430, 43));
        PanelAccor.setLayout(new java.awt.BorderLayout(1, 1));

        ChkAccor.setBackground(new java.awt.Color(255, 250, 250));
        ChkAccor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setSelected(true);
        ChkAccor.setFocusable(false);
        ChkAccor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkAccor.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkAccor.setName("ChkAccor"); // NOI18N
        ChkAccor.setPreferredSize(new java.awt.Dimension(15, 20));
        ChkAccor.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kiri.png"))); // NOI18N
        ChkAccor.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/kanan.png"))); // NOI18N
        ChkAccor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChkAccorActionPerformed(evt);
            }
        });
        PanelAccor.add(ChkAccor, java.awt.BorderLayout.WEST);

        FormPhoto.setBackground(new java.awt.Color(255, 255, 255));
        FormPhoto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), " Bukti Pengambilan Persetujuan : ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        FormPhoto.setName("FormPhoto"); // NOI18N
        FormPhoto.setPreferredSize(new java.awt.Dimension(115, 73));
        FormPhoto.setLayout(new java.awt.BorderLayout());

        FormPass3.setBackground(new java.awt.Color(255, 255, 255));
        FormPass3.setBorder(null);
        FormPass3.setName("FormPass3"); // NOI18N
        FormPass3.setPreferredSize(new java.awt.Dimension(115, 40));

        btnAmbil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        btnAmbil.setMnemonic('U');
        btnAmbil.setText("Ambil");
        btnAmbil.setToolTipText("Alt+U");
        btnAmbil.setName("btnAmbil"); // NOI18N
        btnAmbil.setPreferredSize(new java.awt.Dimension(100, 30));
        btnAmbil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAmbilActionPerformed(evt);
            }
        });
        FormPass3.add(btnAmbil);

        BtnRefreshPhoto1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/refresh.png"))); // NOI18N
        BtnRefreshPhoto1.setMnemonic('U');
        BtnRefreshPhoto1.setText("Refresh");
        BtnRefreshPhoto1.setToolTipText("Alt+U");
        BtnRefreshPhoto1.setName("BtnRefreshPhoto1"); // NOI18N
        BtnRefreshPhoto1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnRefreshPhoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRefreshPhoto1ActionPerformed(evt);
            }
        });
        FormPass3.add(BtnRefreshPhoto1);

        FormPhoto.add(FormPass3, java.awt.BorderLayout.PAGE_END);

        Scroll5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);
        Scroll5.setPreferredSize(new java.awt.Dimension(200, 200));

        LoadHTML2.setBorder(null);
        LoadHTML2.setName("LoadHTML2"); // NOI18N
        Scroll5.setViewportView(LoadHTML2);

        FormPhoto.add(Scroll5, java.awt.BorderLayout.CENTER);

        PanelAccor.add(FormPhoto, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelAccor, java.awt.BorderLayout.EAST);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(NamaPJ.getText().trim().equals("")){
            Valid.textKosong(NamaPJ,"Nama Penanggung Jawab");
        }else if(AlamatPJ.getText().trim().equals("")){
            Valid.textKosong(AlamatPJ,"Alamat Penanggung Jawab");
        }else if(NoTelp.getText().trim().equals("")){
            Valid.textKosong(NoTelp,"Nomor Telp");
        }else if(PekerjaanPJ.getText().trim().equals("")){
            Valid.textKosong(PekerjaanPJ,"Nomor KTP");
        }else if(NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NamaPetugas,"Petugas");
        }else if(NoSurat.getText().trim().equals("")){
            Valid.textKosong(NoSurat,"No.Persetujuan");
        }else{
            if (Umum.isSelected()==true) {
                umum = DepoUmum.getText();
                // Reset nilai lain agar bersih
                nama_iks = ""; iks = "0"; hak_kelas = ""; naik_kelas = "";
            } 
            else if (IKS.isSelected()==true) {
                nama_iks = NamaIKS.getText();
                iks = DepoIKS.getText();
                // Reset nilai lain
                umum = "0"; hak_kelas = ""; naik_kelas = "";
            } 
            else if (BPJS.isSelected()==true) {
                hak_kelas = HakKelas.getSelectedItem().toString();
                naik_kelas = NaikKelas.getSelectedItem().toString();
                // Reset nilai lain
                umum = "0"; nama_iks = ""; iks = "0";
            }
            
            if(ChkRenogram6.isSelected()==true){
                menerima_kartu="Yes";
            }else{
                menerima_kartu="No";
            }
            
            if(ChkRenogram7.isSelected()==true){
                hilang="Yes";
            }else{
                hilang="No";
            }
            
            
            if(ChkRenogram8.isSelected()==true){
                mengembalikan="Yes";
            }else{
                mengembalikan="No";
            }
            
            if(ChkRenogram9.isSelected()==true){
                Tambahan.setEditable(true);
                tambahan=Tambahan.getText();
            }else{
                Tambahan.setEditable(false);
                tambahan="No";
            }
            
            if(ChkRenogram11.isSelected()==true){
                penjelasan="Yes";
            }else{
                penjelasan="No";
            }
            
            if(ChkRenogram12.isSelected()==true){
                diskusi="Yes";
            }else{
                diskusi="No";
            }
            
            if(ChkRenogram13.isSelected()==true){
                demonstrasi="Yes";
            }else{
                demonstrasi="No";
            }
            
            if(ChkRenogram14.isSelected()==true){
                praktek="Yes";
            }else{
                praktek="No";
            }
            
            if(Mengulangi1.isSelected()==true){
                tujuan_2="Dapat mengulangi edukasi yang didapat";
            }
            
            if(Mengerti1.isSelected()==true){
                evaluasi_2="Mengerti";
            }else if(Tidak1.isSelected()==true){
                evaluasi_2="Tidak mengerti";
            }else if(Reedukasi1.isSelected()==true){
                evaluasi_2="Re-edukasi";
            }else if(Redemonstrasi1.isSelected()==true){
                evaluasi_2="Re-demonstrasi";
            }else if(Tanggalre1.isSelected()==true){
                evaluasi_2="Tanggal Re-"+Tglre1.getText();
            }
            
            if(Sequel.menyimpantf("surat_persetujuan_rawat_inap","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",28,new String[]{
                    NoSurat.getText(),TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
                    Umur.getText(),Agama.getText(),Alamat.getText(),NamaPJ.getText(),AlamatPJ.getText(),PekerjaanPJ.getText(),NoTelp.getText(),
                    Hubungan.getSelectedItem().toString(),umum,nama_iks,iks,hak_kelas,naik_kelas,menerima_kartu,hilang,mengembalikan,penjelasan,diskusi,
                    demonstrasi,praktek,tambahan,tujuan_2,evaluasi_2,NIP.getText()
                })==true){
                tampil();
                emptTeks();
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,AlamatPJ,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        isForm();
//        Umum.setSelected(false);
//        IKS.setSelected(false);
//        BPJS.setSelected(false);
        ChkRenogram6.setSelected(false);
        ChkRenogram7.setSelected(false);
        ChkRenogram8.setSelected(false);
        ChkRenogram9.setSelected(false);
        ChkRenogram11.setSelected(false);
        ChkRenogram12.setSelected(false);
        ChkRenogram13.setSelected(false);
        ChkRenogram14.setSelected(false);
        ChkSemua.setSelected(false);
//        Mengulangi1.setSelected(false);
//        Mengerti1.setSelected(false);
//        Tidak1.setSelected(false);
//        Reedukasi1.setSelected(false);
//        Redemonstrasi1.setSelected(false);
//        Tanggalre1.setSelected(false);
        buttonGroup1.clearSelection();
        buttonGroup6.clearSelection();
        buttonGroup7.clearSelection();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        if(tbObat.getSelectedRow()>-1){
            if(akses.getkode().equals("Admin Utama")){
                hapus();
            }else{
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString())){
                    hapus();
                }else{
                    JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh petugas yang bersangkutan..!!");
                }
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
        }   

}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(TNoRw.getText().trim().equals("")||TPasien.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(NamaPJ.getText().trim().equals("")){
            Valid.textKosong(NamaPJ,"Nama Penanggung Jawab");
        }else if(AlamatPJ.getText().trim().equals("")){
            Valid.textKosong(AlamatPJ,"Alamat Penanggung Jawab");
        }else if(NoTelp.getText().trim().equals("")){
            Valid.textKosong(NoTelp,"Nomor Telp");
        }else if(PekerjaanPJ.getText().trim().equals("")){
            Valid.textKosong(PekerjaanPJ,"Nomor KTP");
        }else if(NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NamaPetugas,"Petugas");
        }else if(NoSurat.getText().trim().equals("")){
            Valid.textKosong(NoSurat,"No.Persetujuan");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),19).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh petugas yang bersangkutan..!!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih data terlebih dahulu..!!");
            }
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        petugas.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            try{
                htmlContent = new StringBuilder();
                htmlContent.append(                             
                    "<tr class='isi'>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Persetujuan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Rawat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.R.M.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pasien</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Umur</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>J.K.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tgl.Lahir</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Tanggal</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Pembuat Persetujuan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.KTP P.P.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pendidikan P.P.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Alamat Pembuat Persetujuan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>No.Telp P.P.</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Ruang Dipilih</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Kelas Ruang</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Persetujuan Terhadap</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Hak Kelas</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama & Alamat Keluarga Terdekat</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Pembayaran/Pembiayaan</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>NIP</b></td>"+
                        "<td valign='middle' bgcolor='#FFFAF8' align='center'><b>Nama Petugas</b></td>"+
                    "</tr>"
                );

                for (i = 0; i < tabMode.getRowCount(); i++) {
                    htmlContent.append(
                        "<tr class='isi'>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,0).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,1).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,2).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,3).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,4).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,5).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,6).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,7).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,8).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,9).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,10).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,11).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,12).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,13).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,14).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,15).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,16).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,17).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,18).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,19).toString()+"</td>"+
                           "<td valign='top'>"+tbObat.getValueAt(i,20).toString()+"</td>"+ 
                        "</tr>");
                }
                LoadHTML.setText(
                    "<html>"+
                      "<table width='2200px' border='0' align='center' cellpadding='1px' cellspacing='0' class='tbl_form'>"+
                       htmlContent.toString()+
                      "</table>"+
                    "</html>"
                );

                File g = new File("file2.css");            
                BufferedWriter bg = new BufferedWriter(new FileWriter(g));
                bg.write(
                    ".isi td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-bottom: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi2 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#323232;}"+
                    ".isi3 td{border-right: 1px solid #e2e7dd;font: 8.5px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi4 td{font: 11px tahoma;height:12px;border-top: 1px solid #e2e7dd;background: #ffffff;color:#323232;}"+
                    ".isi5 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#AA0000;}"+
                    ".isi6 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#FF0000;}"+
                    ".isi7 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#C8C800;}"+
                    ".isi8 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#00AA00;}"+
                    ".isi9 td{font: 8.5px tahoma;border:none;height:12px;background: #ffffff;color:#969696;}"
                );
                bg.close();

                File f = new File("DataPersetujuanRawatInap.html");            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));            
                bw.write(LoadHTML.getText().replaceAll("<head>","<head>"+
                            "<link href=\"file2.css\" rel=\"stylesheet\" type=\"text/css\" />"+
                            "<table width='2200px' border='0' align='center' cellpadding='3px' cellspacing='0' class='tbl_form'>"+
                                "<tr class='isi2'>"+
                                    "<td valign='top' align='center'>"+
                                        "<font size='4' face='Tahoma'>"+akses.getnamars()+"</font><br>"+
                                        akses.getalamatrs()+", "+akses.getkabupatenrs()+", "+akses.getpropinsirs()+"<br>"+
                                        akses.getkontakrs()+", E-mail : "+akses.getemailrs()+"<br><br>"+
                                        "<font size='2' face='Tahoma'>DATA PERSETUJUAN RAWAT INAP<br><br></font>"+        
                                    "</td>"+
                               "</tr>"+
                            "</table>")
                );
                bw.close();                         
                Desktop.getDesktop().browse(f.toURI());

            }catch(Exception e){
                System.out.println("Notifikasi : "+e);
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            tampil();
            TCari.setText("");
        }else{
            Valid.pindah(evt, BtnCari, TPasien);
        }
}//GEN-LAST:event_BtnAllKeyPressed
   
                                  
    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            try {
                isPhoto();
                panggilPhoto();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
       isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void tbObatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
    }//GEN-LAST:event_tbObatKeyReleased

    private void btnPetugasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPetugasKeyPressed
        Valid.pindah(evt,Tanggal,NamaPJ);
    }//GEN-LAST:event_btnPetugasKeyPressed

    private void btnPetugasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPetugasActionPerformed
        petugas.emptTeks();
        petugas.isCek();
        petugas.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        petugas.setLocationRelativeTo(internalFrame1);
        petugas.setVisible(true);
    }//GEN-LAST:event_btnPetugasActionPerformed

    private void AlamatPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatPJKeyPressed
        Valid.pindah(evt,NamaPJ,PekerjaanPJ);
    }//GEN-LAST:event_AlamatPJKeyPressed

    private void HubunganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubunganKeyPressed
        Valid.pindah(evt,NoTelp,DepoUmum);
    }//GEN-LAST:event_HubunganKeyPressed

    private void NamaPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPJKeyPressed
        Valid.pindah(evt,NoSurat,AlamatPJ);
    }//GEN-LAST:event_NamaPJKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
    }//GEN-LAST:event_TPasienKeyPressed

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
            isPsien();
        }else{
            Valid.pindah(evt,TCari,Tanggal);
        }
    }//GEN-LAST:event_TNoRwKeyPressed

    private void TanggalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKeyPressed
        //Valid.pindah(evt,TCari,Jam);
    }//GEN-LAST:event_TanggalKeyPressed

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
        Valid.pindah(evt,btnPetugas,NamaPJ);
    }//GEN-LAST:event_NoSuratKeyPressed

    private void PekerjaanPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PekerjaanPJKeyPressed
        Valid.pindah(evt,AlamatPJ,NoTelp);
    }//GEN-LAST:event_PekerjaanPJKeyPressed

    private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
        Valid.pindah(evt,PekerjaanPJ,Hubungan);
    }//GEN-LAST:event_NoTelpKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            isPhoto();
            panggilPhoto();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Silahkan pilih No.Persetujuan..!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void btnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getSelectedRow()>-1){
                Sequel.queryu("delete from antripersetujuanrawatinap");
                Sequel.queryu("insert into antripersetujuanrawatinap values('"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"','"+tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()+"')");
                Sequel.queryu("delete from surat_persetujuan_rawat_inap_pembuat_pernyataan where no_surat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'");
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Persetujuan terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_btnAmbilActionPerformed

    private void BtnRefreshPhoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshPhoto1ActionPerformed
        if(tbObat.getSelectedRow()>-1){
            panggilPhoto();
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Persetujuan terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRefreshPhoto1ActionPerformed

    private void NaikKelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NaikKelasKeyPressed
        Valid.pindah(evt,HakKelas,BtnSimpan);
    }//GEN-LAST:event_NaikKelasKeyPressed

    private void HakKelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HakKelasKeyPressed
        Valid.pindah(evt,NaikKelas,Hubungan);
    }//GEN-LAST:event_HakKelasKeyPressed

    private void AgamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AgamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AgamaKeyPressed

    private void DepoUmumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepoUmumKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepoUmumKeyPressed

    private void NamaIKSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaIKSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NamaIKSKeyPressed

    private void DepoIKSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DepoIKSKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DepoIKSKeyPressed

    private void Tglre1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tglre1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tglre1KeyPressed

    private void ChkRenogram6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram6ItemStateChanged

    private void ChkRenogram7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram7ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram7ItemStateChanged

    private void ChkRenogram8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram8ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram8ItemStateChanged

    private void SuratPersetujuanRawatInapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuratPersetujuanRawatInapActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data pasien sudah habis...!!!!");

        }else if(TPasien.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu data pasien dengan menklik data pada table...!!!");
            tbObat.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());
            param.put("petugas",akses.getnamauser());
            param.put("logo",Sequel.cariGambar("select logo from setting"));
            param.put("finger","No. RM : "+TNoRM.getText()+"\nNama : "+TPasien.getText()+"\nTgl. Lahir : "+LahirPasien.getText()+"");
            tanggal=Sequel.cariIsi("select DATE_FORMAT(tanggal, '%d-%m-%Y') from surat_persetujuan_umum where no_surat=? ",NoSurat.getText());
            finger=Sequel.cariIsi("select nama from pegawai where nik=?",NIP.getText());
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),30).toString()+
                "\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),29).toString()+"\n"+tanggal);
            param.put("photo","http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuanrawatinap/"+
                Sequel.cariIsi("select surat_persetujuan_rawat_inap_pembuat_pernyataan.photo from surat_persetujuan_rawat_inap_pembuat_pernyataan where surat_persetujuan_rawat_inap_pembuat_pernyataan.no_surat=?",NoSurat.getText()));

            Valid.MyReportqry("rptPersetujuanRanap.jasper","report","::[ Persetujuan Rawat Inap ]::",
                "select spri.no_surat,spri.no_rawat,p.no_rkm_medis,p.nm_pasien,date_format(spri.tanggal, '%d-%m-%Y') as tanggal,spri.umur,spri.agama,date_format(p.tgl_lahir, '%d-%m-%Y') as tgl_lahir,\n" +
                    "spri.alamat,spri.nama_pj,spri.alamat_pj,spri.pekerjaan,spri.no_telp_pj,spri.hubungan,if(spri.umum='0','',spri.umum) as umum,spri.nama_iks,if(spri.iks='0','',spri.iks) as iks,\n" +
                    "spri.hak_kelas,spri.naik_kelas,spri.menerima_kartu,spri.hilang,spri.mengembalikan,spri.tambahan,spri.penjelasan,spri.diskusi,spri.demonstrasi,spri.praktek,spri.tujuan_2,\n" +
                    "spri.evaluasi_2,p2.nip,p2.nama "+
                    "from surat_persetujuan_rawat_inap spri \n" +
                    "inner join reg_periksa rp on spri.no_rawat=rp.no_rawat \n" +
                    "inner join pasien p on rp.no_rkm_medis=p.no_rkm_medis \n" +
                    "inner join petugas p2 on spri.nip=p2.nip where spri.no_surat='"+NoSurat.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_SuratPersetujuanRawatInapActionPerformed

    private void ChkRenogram9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram9ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram9ItemStateChanged

    private void TambahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TambahanKeyPressed
        Valid.pindah2(evt,DepoIKS,Tglre1);
    }//GEN-LAST:event_TambahanKeyPressed

    private void ChkSemuaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkSemuaItemStateChanged
        if(ChkSemua.isSelected()==true){
            ChkRenogram6.setSelected(true);
            ChkRenogram7.setSelected(true);
            ChkRenogram8.setSelected(true);
        }
    }//GEN-LAST:event_ChkSemuaItemStateChanged

    private void ChkRenogram11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram11ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram11ItemStateChanged

    private void ChkRenogram12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram12ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram12ItemStateChanged

    private void ChkRenogram13ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram13ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram13ItemStateChanged

    private void ChkRenogram14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram14ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram14ItemStateChanged

    private void ChkJlnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkJlnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkJlnActionPerformed

    private void CmbDetikKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbDetikKeyPressed
        Valid.pindah(evt,CmbMenit,NamaPJ);
    }//GEN-LAST:event_CmbDetikKeyPressed

    private void CmbMenitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbMenitKeyPressed
        Valid.pindah(evt,CmbJam,CmbDetik);
    }//GEN-LAST:event_CmbMenitKeyPressed

    private void CmbJamKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CmbJamKeyPressed
        Valid.pindah(evt,NoSurat,CmbMenit);
    }//GEN-LAST:event_CmbJamKeyPressed

    private void btnAmbil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbil1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getSelectedRow()>-1){
                Sequel.queryu("delete from antripersetujuanrawatinap");
                Sequel.queryu("insert into antripersetujuanrawatinap values('"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"','"+tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()+"')");
                Sequel.queryu("delete from surat_persetujuan_rawat_inap_pembuat_pernyataan where no_surat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'");
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Persetujuan terlebih dahulu..!!");
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnAmbil1ActionPerformed

    private void ChkSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSemuaActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SuratPersetujuanRawatInap dialog = new SuratPersetujuanRawatInap(new javax.swing.JFrame(), true);
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
    private widget.TextBox Agama;
    private widget.TextBox Alamat;
    private widget.TextBox AlamatPJ;
    private widget.RadioButton BPJS;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRefreshPhoto1;
    private widget.Button BtnSimpan;
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkJln;
    private widget.CekBox ChkRenogram11;
    private widget.CekBox ChkRenogram12;
    private widget.CekBox ChkRenogram13;
    private widget.CekBox ChkRenogram14;
    private widget.CekBox ChkRenogram6;
    private widget.CekBox ChkRenogram7;
    private widget.CekBox ChkRenogram8;
    private widget.CekBox ChkRenogram9;
    private widget.CekBox ChkSemua;
    private widget.ComboBox CmbDetik;
    private widget.ComboBox CmbJam;
    private widget.ComboBox CmbMenit;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.TextBox DepoIKS;
    private widget.TextBox DepoUmum;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormPass3;
    private widget.PanelBiasa FormPhoto;
    private widget.ComboBox HakKelas;
    private widget.ComboBox Hubungan;
    private widget.RadioButton IKS;
    private widget.Label LCount;
    private widget.TextBox LahirPasien;
    private widget.editorpane LoadHTML;
    private widget.editorpane LoadHTML2;
    private widget.RadioButton Mengerti1;
    private widget.RadioButton Mengulangi1;
    private widget.TextBox NIP;
    private widget.ComboBox NaikKelas;
    private widget.TextBox NamaIKS;
    private widget.TextBox NamaPJ;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NoSurat;
    private widget.TextBox NoTelp;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private widget.TextBox PekerjaanPJ;
    private widget.RadioButton Redemonstrasi1;
    private widget.RadioButton Reedukasi1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private javax.swing.JMenuItem SuratPersetujuanRawatInap;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Tambahan;
    private widget.Tanggal Tanggal;
    private widget.RadioButton Tanggalre1;
    private widget.TextBox Tglre1;
    private widget.RadioButton Tidak1;
    private widget.RadioButton Umum;
    private widget.TextBox Umur;
    private widget.Button btnAmbil;
    private widget.Button btnAmbil1;
    private widget.Button btnPetugas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel13;
    private widget.Label jLabel14;
    private widget.Label jLabel15;
    private widget.Label jLabel16;
    private widget.Label jLabel17;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel28;
    private widget.Label jLabel29;
    private widget.Label jLabel3;
    private widget.Label jLabel31;
    private widget.Label jLabel32;
    private widget.Label jLabel33;
    private widget.Label jLabel34;
    private widget.Label jLabel4;
    private widget.Label jLabel41;
    private widget.Label jLabel42;
    private widget.Label jLabel43;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel48;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select spri.no_surat,spri.no_rawat,p.no_rkm_medis,p.nm_pasien,spri.tanggal,spri.umur,spri.agama,date_format(p.tgl_lahir, '%d-%m-%Y') as tgl_lahir,\n" +
                    "spri.alamat,spri.nama_pj,spri.alamat_pj,spri.pekerjaan,spri.no_telp_pj,spri.hubungan,if(spri.umum='0','',spri.umum) as umum,spri.nama_iks,if(spri.iks='0','',spri.iks) as iks,\n" +
                    "spri.hak_kelas,spri.naik_kelas,spri.menerima_kartu,spri.hilang,spri.mengembalikan,spri.tambahan,spri.penjelasan,spri.diskusi,spri.demonstrasi,spri.praktek,spri.tujuan_2,\n" +
                    "spri.evaluasi_2,p2.nip,p2.nama,spri.jam "+
                    "from surat_persetujuan_rawat_inap spri \n" +
                    "inner join reg_periksa rp on spri.no_rawat=rp.no_rawat \n" +
                    "inner join pasien p on rp.no_rkm_medis=p.no_rkm_medis \n" +
                    "inner join petugas p2 on spri.nip=p2.nip where \n" +
                    "spri.tanggal between ? and ? order by spri.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select spri.no_surat,spri.no_rawat,p.no_rkm_medis,p.nm_pasien,spri.tanggal,spri.umur,spri.agama,date_format(p.tgl_lahir, '%d-%m-%Y') as tgl_lahir,\n" +
                    "spri.alamat,spri.nama_pj,spri.alamat_pj,spri.pekerjaan,spri.no_telp_pj,spri.hubungan,if(spri.umum='0','',spri.umum) as umum,spri.nama_iks,if(spri.iks='0','',spri.iks) as iks,\n" +
                    "spri.hak_kelas,spri.naik_kelas,spri.menerima_kartu,spri.hilang,spri.mengembalikan,spri.tambahan,spri.penjelasan,spri.diskusi,spri.demonstrasi,spri.praktek,spri.tujuan_2,\n" +
                    "spri.evaluasi_2,p2.nip,p2.nama,spri.jam "+
                    "from surat_persetujuan_rawat_inap spri \n" +
                    "inner join reg_periksa rp on spri.no_rawat=rp.no_rawat \n" +
                    "inner join pasien p on rp.no_rkm_medis=p.no_rkm_medis \n" +
                    "inner join petugas p2 on spri.nip=p2.nip where \n" +
                    "spri.tanggal between ? and ? and "+
                    "(rp.no_rawat like ? or p.no_rkm_medis like ? or p.nm_pasien like ? or "+
                    "spri.alamat_pj like ? or spri.nama_pj like ? or "+
                    "spri.nip like ? or p2.nama like ?) "+
                    "order by spri.tanggal");
            }
                
            try {
                if(TCari.getText().toString().trim().equals("")){
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                }else{
                    ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                    ps.setString(3,"%"+TCari.getText()+"%");
                    ps.setString(4,"%"+TCari.getText()+"%");
                    ps.setString(5,"%"+TCari.getText()+"%");
                    ps.setString(6,"%"+TCari.getText()+"%");
                    ps.setString(7,"%"+TCari.getText()+"%");
                    ps.setString(8,"%"+TCari.getText()+"%");
                    ps.setString(9,"%"+TCari.getText()+"%");
                }
                  
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_surat"),rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),
                        rs.getString("tanggal"),rs.getString("umur"),rs.getString("agama"),rs.getString("tgl_lahir"),
                        rs.getString("alamat"),rs.getString("nama_pj"),rs.getString("alamat_pj"),rs.getString("pekerjaan"),
                        rs.getString("no_telp_pj"),rs.getString("hubungan"),rs.getString("umum"),rs.getString("nama_iks"),
                        rs.getString("iks"),rs.getString("hak_kelas"),rs.getString("naik_kelas"),
                        rs.getString("menerima_kartu"),rs.getString("hilang"),rs.getString("mengembalikan"), 
                        rs.getString("tambahan"),rs.getString("penjelasan"),rs.getString("diskusi"),rs.getString("demonstrasi"),
                        rs.getString("praktek"),rs.getString("tujuan_2"),rs.getString("evaluasi_2"),rs.getString("nip"),
                        rs.getString("nama"),rs.getString("jam")
                    });
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
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        NamaPJ.setText("");
        AlamatPJ.setText("");
        PekerjaanPJ.setText("");
        NoTelp.setText("");
        Hubungan.setSelectedIndex(0);
        NaikKelas.setSelectedIndex(0);
        HakKelas.setSelectedIndex(0);
        DepoUmum.setText("");
        NamaIKS.setText("");
        DepoIKS.setText("");
        Tglre1.setText("");
        Tambahan.setText("");
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(surat_persetujuan_rawat_inap.no_surat,3),signed)),0) from surat_persetujuan_rawat_inap where surat_persetujuan_rawat_inap.tanggal='"+Valid.SetTgl(Tanggal.getSelectedItem()+"")+"' ",
                "PRI"+Tanggal.getSelectedItem().toString().substring(6,10)+Tanggal.getSelectedItem().toString().substring(3,5)+Tanggal.getSelectedItem().toString().substring(0,2),3,NoSurat);
        NamaPJ.requestFocus();
    }

 
    private void getData() {
        DepoUmum.setText("");
        NamaIKS.setText("");
        DepoIKS.setText("");
        NaikKelas.setSelectedIndex(0);
        HakKelas.setSelectedIndex(0);
         if(tbObat.getSelectedRow()!= -1){
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            Umur.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            Agama.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Alamat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());
            NamaPJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            AlamatPJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            PekerjaanPJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            NoTelp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            Hubungan.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            String getUmum = tbObat.getValueAt(tbObat.getSelectedRow(), 14).toString();
            if (getUmum.equals("")) {
                Umum.setSelected(false);
            } else {
                // Ini menangani kondisi "not No" atau kondisi lainnya
                Umum.setSelected(true);
                DepoUmum.setText(getUmum);
            }
            String getIKS = tbObat.getValueAt(tbObat.getSelectedRow(), 15).toString();
            if (getIKS.equals("")) {
                IKS.setSelected(false);
            } else {
                // Ini menangani kondisi "not No" atau kondisi lainnya
                IKS.setSelected(true);
                NamaIKS.setText(getIKS);
                DepoIKS.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            }
            String getBPJS = tbObat.getValueAt(tbObat.getSelectedRow(), 17).toString();
            if (getBPJS.equals("")) {
                BPJS.setSelected(false);
            } else {
                // Ini menangani kondisi "not No" atau kondisi lainnya
                BPJS.setSelected(true);
                HakKelas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
                NaikKelas.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString());
            }
            
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),19).toString()) {
                    case "Yes":
                        ChkRenogram6.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram6.setSelected(false);
                        break;
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),20).toString()) {
                    case "Yes":
                        ChkRenogram7.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram7.setSelected(false);
                        break;
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),21).toString()) {
                    case "Yes":
                        ChkRenogram8.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram8.setSelected(false);
                        break;
            }
            String status = tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString();

            if (status.equals("No")) {
                ChkRenogram9.setSelected(false);
            } else {
                // Ini menangani kondisi "not No" atau kondisi lainnya
                ChkRenogram9.setSelected(true);
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),23).toString()) {
                    case "Yes":
                        ChkRenogram11.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram11.setSelected(false);
                        break;
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),24).toString()) {
                    case "Yes":
                        ChkRenogram12.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram12.setSelected(false);
                        break;
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),25).toString()) {
                    case "Yes":
                        ChkRenogram13.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram13.setSelected(false);
                        break;
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),26).toString()) {
                    case "Yes":
                        ChkRenogram14.setSelected(true);
                        break;
                    case "No":
                        ChkRenogram14.setSelected(false);
                        break;
            }
            switch (tbObat.getValueAt(tbObat.getSelectedRow(),27).toString()) {
                    case "Dapat mengulangi edukasi yang didapat":
                        Mengulangi1.setSelected(true);
                        break;
            }
            // Simpan nilai ke variabel agar tidak memanggil getValueAt berulang kali (lebih cepat)
            String evaluasi = tbObat.getValueAt(tbObat.getSelectedRow(), 28).toString();

            // Reset semua status ke awal (Default: tidak terpilih & text kosong)
            Mengerti1.setSelected(false);
            Tidak1.setSelected(false);
            Reedukasi1.setSelected(false);
            Redemonstrasi1.setSelected(false);
            Tanggalre1.setSelected(false);
            Tglre1.setText("");

            // Gunakan if-else if agar logikanya eksklusif (pilih salah satu)
            if (evaluasi.equals("Mengerti")) {
                Mengerti1.setSelected(true);
            } else if (evaluasi.equals("Tidak mengerti")) {
                Tidak1.setSelected(true);
            } else if (evaluasi.equals("Re-edukasi")) {
                Reedukasi1.setSelected(true);
            } else if (evaluasi.equals("Re-demonstrasi")) {
                Redemonstrasi1.setSelected(true);
            } else if (evaluasi.startsWith("Tanggal Re-")) {
                Tanggalre1.setSelected(true);
                // Mengambil teks setelah "Tanggal Re-" (indeks ke-11)
                Tglre1.setText(evaluasi.substring(11).trim());
            }
            CmbJam.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString().substring(0,2));
            CmbMenit.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString().substring(3,5));
            CmbDetik.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),31).toString().substring(6,8));
        }
    }

    private void isRawat() {
         Sequel.cariIsi("select no_rkm_medis from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",TNoRM);
         Sequel.cariIsi("select concat(umurdaftar,' ',sttsumur) from reg_periksa where no_rawat='"+TNoRw.getText()+"' ",Umur);
    }

    private void isPsien() {
       Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
       Sequel.cariIsi("select alamat from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",Alamat);
       Sequel.cariIsi("select agama from pasien where no_rkm_medis='"+TNoRM.getText()+"' ",Agama);
       Sequel.cariIsi("select date_format(tgl_lahir,'%d-%m-%Y') from pasien where no_rkm_medis=? ",LahirPasien,TNoRM.getText());
    }
    
    public void setNoRm(String norwt,Date tgl2) {
        TNoRw.setText(norwt);
        TCari.setText(norwt);
        Sequel.cariIsi("select reg_periksa.tgl_registrasi from reg_periksa where reg_periksa.no_rawat='"+norwt+"'", DTPCari1);
        DTPCari2.setDate(tgl2);
        isRawat();
        isPsien(); 
        ChkInput.setSelected(true);
        isForm();
    }
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,500));
            FormInput.setVisible(true);      
            ChkInput.setVisible(true);
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
    }
       
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getsurat_persetujuan_rawat_inap());
        BtnHapus.setEnabled(akses.getsurat_persetujuan_rawat_inap());
        BtnEdit.setEnabled(akses.getsurat_persetujuan_rawat_inap());
        BtnPrint.setEnabled(akses.getsurat_persetujuan_rawat_inap()); 
        if(akses.getjml2()>=1){
            NIP.setEditable(false);
            btnPetugas.setEnabled(false);
            NIP.setText(akses.getkode());
            NamaPetugas.setText(petugas.tampil3(NIP.getText()));
            if(NamaPetugas.getText().equals("")){
                NIP.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan petugas...!!");
            }
        }            
    }
  
    private void ganti() {
        if (Umum.isSelected()==true) {
                umum = DepoUmum.getText();
                // Reset nilai lain agar bersih
                nama_iks = ""; iks = "0"; hak_kelas = ""; naik_kelas = "";
            } 
            else if (IKS.isSelected()==true) {
                nama_iks = NamaIKS.getText();
                iks = DepoIKS.getText();
                // Reset nilai lain
                umum = "0"; hak_kelas = ""; naik_kelas = "";
            } 
            else if (BPJS.isSelected()==true) {
                hak_kelas = HakKelas.getSelectedItem().toString();
                naik_kelas = NaikKelas.getSelectedItem().toString();
                // Reset nilai lain
                umum = "0"; nama_iks = ""; iks = "0";
            }
            
            if(ChkRenogram6.isSelected()==true){
                menerima_kartu="Yes";
            }else{
                menerima_kartu="No";
            }
            
            if(ChkRenogram7.isSelected()==true){
                hilang="Yes";
            }else{
                hilang="No";
            }
            
            
            if(ChkRenogram8.isSelected()==true){
                mengembalikan="Yes";
            }else{
                mengembalikan="No";
            }
            
            if(ChkRenogram9.isSelected()==true){
                Tambahan.setEditable(true);
                tambahan=Tambahan.getText();
            }else{
                Tambahan.setEditable(false);
                tambahan="No";
            }
            
            if(ChkRenogram11.isSelected()==true){
                penjelasan="Yes";
            }else{
                penjelasan="No";
            }
            
            if(ChkRenogram12.isSelected()==true){
                diskusi="Yes";
            }else{
                diskusi="No";
            }
            
            if(ChkRenogram13.isSelected()==true){
                demonstrasi="Yes";
            }else{
                demonstrasi="No";
            }
            
            if(ChkRenogram14.isSelected()==true){
                praktek="Yes";
            }else{
                praktek="No";
            }
            
            if(Mengulangi1.isSelected()==true){
                tujuan_2="Dapat mengulangi edukasi yang didapat";
            }
            
            if(Mengerti1.isSelected()==true){
                evaluasi_2="Mengerti";
            }else if(Tidak1.isSelected()==true){
                evaluasi_2="Tidak mengerti";
            }else if(Reedukasi1.isSelected()==true){
                evaluasi_2="Re-edukasi";
            }else if(Redemonstrasi1.isSelected()==true){
                evaluasi_2="Re-demonstrasi";
            }else if(Tanggalre1.isSelected()==true){
                evaluasi_2="Tanggal Re-"+Tglre1.getText();
            }
        Sequel.mengedit("surat_persetujuan_rawat_inap","no_surat=?","no_surat=?,no_rawat=?,tanggal=?,jam=?,umur=?,agama=?,alamat=?,"+
            "nama_pj=?,alamat_pj=?,pekerjaan=?,no_telp_pj=?,hubungan=?,umum=?,nama_iks=?,iks=?,hak_kelas=?,naik_kelas=?,menerima_kartu=?,"+
            "hilang=?,mengembalikan=?,diskusi=?,penjelasan=?,demonstrasi=?,praktek=?,tambahan=?,tujuan_2=?,evaluasi_2=?,nip=?",29,new String[]{
            NoSurat.getText(),TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),CmbJam.getSelectedItem()+":"+CmbMenit.getSelectedItem()+":"+CmbDetik.getSelectedItem(),
            Umur.getText(),Agama.getText(),Alamat.getText(),NamaPJ.getText(),AlamatPJ.getText(),PekerjaanPJ.getText(),NoTelp.getText(),
            Hubungan.getSelectedItem().toString(),umum,nama_iks,iks,hak_kelas,naik_kelas,menerima_kartu,hilang,mengembalikan,penjelasan,diskusi,
            demonstrasi,praktek,tambahan,tujuan_2,evaluasi_2,NIP.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        });
        if(tabMode.getRowCount()!=0){tampil();}
        emptTeks();
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from surat_persetujuan_rawat_inap where no_surat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true){
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    private void isPhoto(){
        if(ChkAccor.isSelected()==true){
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(480,HEIGHT));
            FormPhoto.setVisible(true);  
            ChkAccor.setVisible(true);
        }else if(ChkAccor.isSelected()==false){    
            ChkAccor.setVisible(false);
            PanelAccor.setPreferredSize(new Dimension(15,HEIGHT));
            FormPhoto.setVisible(false);  
            ChkAccor.setVisible(true);
        }
    }
    
    private void jam(){
        ActionListener taskPerformer = new ActionListener(){
            private int nilai_jam;
            private int nilai_menit;
            private int nilai_detik;
            public void actionPerformed(ActionEvent e) {
                String nol_jam = "";
                String nol_menit = "";
                String nol_detik = "";
                
                Date now = Calendar.getInstance().getTime();

                // Mengambil nilaj JAM, MENIT, dan DETIK Sekarang
                if(ChkJln.isSelected()==true){
                    nilai_jam = now.getHours();
                    nilai_menit = now.getMinutes();
                    nilai_detik = now.getSeconds();
                }else if(ChkJln.isSelected()==false){
                    nilai_jam =CmbJam.getSelectedIndex();
                    nilai_menit =CmbMenit.getSelectedIndex();
                    nilai_detik =CmbDetik.getSelectedIndex();
                }

                // Jika nilai JAM lebih kecil dari 10 (hanya 1 digit)
                if (nilai_jam <= 9) {
                    // Tambahkan "0" didepannya
                    nol_jam = "0";
                }
                // Jika nilai MENIT lebih kecil dari 10 (hanya 1 digit)
                if (nilai_menit <= 9) {
                    // Tambahkan "0" didepannya
                    nol_menit = "0";
                }
                // Jika nilai DETIK lebih kecil dari 10 (hanya 1 digit)
                if (nilai_detik <= 9) {
                    // Tambahkan "0" didepannya
                    nol_detik = "0";
                }
                // Membuat String JAM, MENIT, DETIK
                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);
                // Menampilkan pada Layar
                //tampil_jam.setText("  " + jam + " : " + menit + " : " + detik + "  ");
                CmbJam.setSelectedItem(jam);
                CmbMenit.setSelectedItem(menit);
                CmbDetik.setSelectedItem(detik);
            }
        };
        // Timer
        new Timer(1000, taskPerformer).start();
    }

    private void panggilPhoto() {
        if(FormPhoto.isVisible()==true){
            try {
                ps=koneksi.prepareStatement("select surat_persetujuan_rawat_inap_pembuat_pernyataan.photo from surat_persetujuan_rawat_inap_pembuat_pernyataan where surat_persetujuan_rawat_inap_pembuat_pernyataan.no_surat=?");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("photo").equals("")||rs.getString("photo").equals("-")){
                            LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                        }else{
                            LoadHTML2.setText("<html><body><center><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuanrawatinap/"+rs.getString("photo")+"' alt='photo' width='500' height='500'/></center></body></html>");
                        }  
                    }else{
                        LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
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
            } catch (Exception e) {
                System.out.println("Notif : "+e);
            }
        }
    }
}



