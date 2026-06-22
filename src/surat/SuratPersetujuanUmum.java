/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * kontribusi dari dokter Salim Mulyana
 */

package surat;

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


/**
 * 
 * @author salimmulyana
 */
public final class SuratPersetujuanUmum extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private String tanggal="",finger="",baca,pend,isyarat,bahasa,terjemah,hambatan,cara,yakin,sedia,m1,m2,m3,m4,m5,m6,m7,
            m8,m9,m10,m11,m12,m13,penjelasan,diskusi,demonstrasi,praktek,tujuan,evaluasi;
    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    
    public SuratPersetujuanUmum(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Persetujuan","No.Rawat","No.R.M.","Nama Pasien","Umur","J.K.","Tgl.Lahir","Tanggal","Pengobatan Kepada","Alamat PJ",
            "Nama Penanggung Jawab","Umur P.J.","Nomor KTP P.J.","J.K. P.J.","Nomor Telp/HP","Sebagai","NIP","Nama Petugas","Status",
            "Membaca","Pendidikan","Keyakinan","Isyarat","Bahasa","Penerjemah","Hambatan","Belajar","Bersedia","Materi 1","Materi 2",
            "Materi 3","Materi 4","Materi 5","Materi 6","Materi 7","Materi 8","Materi 9","Materi 10","Penjelasan","Diskusi",
            "Demonstrasi","Praktek","Tujuan","Evaluasi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 44; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(105);
            }else if(i==2){
                column.setPreferredWidth(70);
            }else if(i==3){
                column.setPreferredWidth(250);
            }else if(i==4){
                column.setPreferredWidth(45);
            }else if(i==5){
                column.setPreferredWidth(25);
            }else if(i==6){
                column.setPreferredWidth(65);
            }else if(i==7){
                column.setPreferredWidth(65);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==9){
                column.setPreferredWidth(250);
            }else if(i==10){
                column.setPreferredWidth(250);
            }else if(i==11){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==12){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==13){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==14){
                column.setPreferredWidth(100);
            }else if(i==15){
                column.setPreferredWidth(88);
            }else if(i==16){
                column.setPreferredWidth(90);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(50);
            }else if(i==19){
                column.setPreferredWidth(100);
            }else if(i==20){
                column.setPreferredWidth(100);
            }else if(i==21){
                column.setPreferredWidth(100);
            }else if(i==22){
                column.setPreferredWidth(100);
            }else if(i==23){
                column.setPreferredWidth(100);
            }else if(i==24){
                column.setPreferredWidth(100);
            }else if(i==25){
                column.setPreferredWidth(100);
            }else if(i==26){
                column.setPreferredWidth(100);
            }else if(i==27){
                column.setPreferredWidth(100);
            }else if(i==28){
                column.setPreferredWidth(100);
            }else if(i==29){
                column.setPreferredWidth(100);
            }else if(i==30){
                column.setPreferredWidth(100);
            }else if(i==31){
                column.setPreferredWidth(100);
            }else if(i==32){
                column.setPreferredWidth(100);
            }else if(i==33){
                column.setPreferredWidth(100);
            }else if(i==34){
                column.setPreferredWidth(100);
            }else if(i==35){
                column.setPreferredWidth(100);
            }else if(i==36){
                column.setPreferredWidth(100);
            }else if(i==37){
                column.setPreferredWidth(100);
            }else if(i==38){
                column.setPreferredWidth(100);
            }else if(i==39){
                column.setPreferredWidth(100);
            }else if(i==40){
                column.setPreferredWidth(100);
            }else if(i==41){
                column.setPreferredWidth(100);
            }else if(i==42){
                column.setPreferredWidth(100);
            }else if(i==43){
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());
        
        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));    
        NIP.setDocument(new batasInput((byte)20).getKata(NIP));  
        NoSurat.setDocument(new batasInput((byte)20).getKata(NoSurat));
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        NamaPJ.setDocument(new batasInput((byte)50).getKata(NamaPJ));
        HubPasien.setDocument(new batasInput((byte)50).getKata(HubPasien));  
        Alamat.setDocument(new batasInput((byte)50).getKata(Alamat));  
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
        
        ChkInput.setSelected(false);
        isForm();
        
        ChkAccor.setSelected(true);
        isPhoto();
        
        HTMLEditorKit kit = new HTMLEditorKit();
        LoadHTML2.setEditable(true);
        LoadHTML2.setEditorKit(kit);
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
    }
    
    
    
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        SuratPersetujuanUmum = new javax.swing.JMenuItem();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        buttonGroup9 = new javax.swing.ButtonGroup();
        buttonGroup10 = new javax.swing.ButtonGroup();
        buttonGroup11 = new javax.swing.ButtonGroup();
        buttonGroup12 = new javax.swing.ButtonGroup();
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
        jLabel17 = new widget.Label();
        LahirPasien = new widget.TextBox();
        jLabel18 = new widget.Label();
        NIP = new widget.TextBox();
        NamaPetugas = new widget.TextBox();
        btnPetugas = new widget.Button();
        jLabel16 = new widget.Label();
        HubPasien = new widget.TextBox();
        Tanggal = new widget.Tanggal();
        jLabel14 = new widget.Label();
        jLabel3 = new widget.Label();
        NoSurat = new widget.TextBox();
        NoTelp = new widget.TextBox();
        jLabel20 = new widget.Label();
        jLabel11 = new widget.Label();
        Alamat = new widget.TextBox();
        jLabel31 = new widget.Label();
        Materi1 = new widget.CekBox();
        jLabel41 = new widget.Label();
        Materi2 = new widget.CekBox();
        jLabel42 = new widget.Label();
        Materi3 = new widget.CekBox();
        jLabel43 = new widget.Label();
        Materi9 = new widget.CekBox();
        scrollPane1 = new widget.ScrollPane();
        Tambahan = new widget.TextArea();
        ChkSemua = new widget.CekBox();
        jLabel44 = new widget.Label();
        jSeparator1 = new javax.swing.JSeparator();
        Materi4 = new widget.CekBox();
        jLabel45 = new widget.Label();
        Materi5 = new widget.CekBox();
        Materi6 = new widget.CekBox();
        Materi7 = new widget.CekBox();
        Materi8 = new widget.CekBox();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        jLabel48 = new widget.Label();
        jLabel49 = new widget.Label();
        jLabel50 = new widget.Label();
        jLabel51 = new widget.Label();
        jLabel52 = new widget.Label();
        Asuransi = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        Permasalahan = new widget.TextArea();
        jLabel32 = new widget.Label();
        ChkRenogram19 = new widget.CekBox();
        jLabel57 = new widget.Label();
        ChkRenogram20 = new widget.CekBox();
        jLabel58 = new widget.Label();
        jLabel59 = new widget.Label();
        ChkRenogram21 = new widget.CekBox();
        ChkRenogram22 = new widget.CekBox();
        jLabel60 = new widget.Label();
        jLabel33 = new widget.Label();
        Mengulangi1 = new widget.RadioButton();
        jLabel34 = new widget.Label();
        Mengerti1 = new widget.RadioButton();
        Tidak1 = new widget.RadioButton();
        Reedukasi1 = new widget.RadioButton();
        Tglre1 = new widget.TextBox();
        Tanggalre1 = new widget.RadioButton();
        Redemonstrasi1 = new widget.RadioButton();
        Mengulangi2 = new widget.RadioButton();
        jLabel9 = new widget.Label();
        jLabel13 = new widget.Label();
        jLabel15 = new widget.Label();
        jLabel22 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel24 = new widget.Label();
        Baca1 = new widget.RadioButton();
        Baca2 = new widget.RadioButton();
        Pend1 = new widget.RadioButton();
        Pend2 = new widget.RadioButton();
        Pend3 = new widget.RadioButton();
        Pend4 = new widget.RadioButton();
        Pend5 = new widget.RadioButton();
        Pend6 = new widget.RadioButton();
        Pend7 = new widget.RadioButton();
        Isyarat1 = new widget.RadioButton();
        Isyarat2 = new widget.RadioButton();
        KetBahasa1 = new widget.TextBox();
        KetBahasa2 = new widget.TextBox();
        Bahasa1 = new widget.RadioButton();
        Bahasa2 = new widget.RadioButton();
        Bahasa3 = new widget.RadioButton();
        KetTerjemah2 = new widget.TextBox();
        Terjemah1 = new widget.RadioButton();
        Terjemah2 = new widget.RadioButton();
        jLabel25 = new widget.Label();
        Hambatan1 = new widget.RadioButton();
        Hambatan2 = new widget.RadioButton();
        Hambatan3 = new widget.RadioButton();
        Hambatan4 = new widget.RadioButton();
        Hambatan5 = new widget.RadioButton();
        Hambatan6 = new widget.RadioButton();
        Hambatan7 = new widget.RadioButton();
        Hambatan8 = new widget.RadioButton();
        jLabel26 = new widget.Label();
        Cara1 = new widget.RadioButton();
        Cara2 = new widget.RadioButton();
        Cara3 = new widget.RadioButton();
        Cara4 = new widget.RadioButton();
        Cara5 = new widget.RadioButton();
        Cara6 = new widget.RadioButton();
        jLabel27 = new widget.Label();
        KetYakin2 = new widget.TextBox();
        Yakin1 = new widget.RadioButton();
        Yakin2 = new widget.RadioButton();
        jLabel28 = new widget.Label();
        Sedia1 = new widget.RadioButton();
        Sedia2 = new widget.RadioButton();
        jSeparator2 = new javax.swing.JSeparator();
        Bayar1 = new widget.RadioButton();
        Bayar2 = new widget.RadioButton();
        Bayar3 = new widget.RadioButton();
        Bayar4 = new widget.RadioButton();
        PanelAccor = new widget.PanelBiasa();
        ChkAccor = new widget.CekBox();
        FormPhoto = new widget.PanelBiasa();
        FormPass3 = new widget.PanelBiasa();
        btnAmbil = new widget.Button();
        BtnRefreshPhoto1 = new widget.Button();
        Scroll5 = new widget.ScrollPane();
        LoadHTML2 = new widget.editorpane();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        SuratPersetujuanUmum.setBackground(new java.awt.Color(255, 255, 254));
        SuratPersetujuanUmum.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        SuratPersetujuanUmum.setForeground(new java.awt.Color(50, 50, 50));
        SuratPersetujuanUmum.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        SuratPersetujuanUmum.setText("Surat Persetujuan Umum");
        SuratPersetujuanUmum.setName("SuratPersetujuanUmum"); // NOI18N
        SuratPersetujuanUmum.setPreferredSize(new java.awt.Dimension(250, 26));
        SuratPersetujuanUmum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SuratPersetujuanUmumActionPerformed(evt);
            }
        });
        jPopupMenu1.add(SuratPersetujuanUmum);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Persetujuan Umum ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        jLabel19.setText("Tanggal :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(57, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-02-2026" }));
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
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-02-2026" }));
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
        TCari.setPreferredSize(new java.awt.Dimension(215, 23));
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
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 175));
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
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 1022));

        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 1020));
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
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(212, 10, 111, 23);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Hubungan dengan pasien :");
        jLabel8.setName("jLabel8"); // NOI18N
        FormInput.add(jLabel8);
        jLabel8.setBounds(20, 120, 160, 23);

        NamaPJ.setName("NamaPJ"); // NOI18N
        NamaPJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NamaPJKeyPressed(evt);
            }
        });
        FormInput.add(NamaPJ);
        NamaPJ.setBounds(59, 90, 410, 23);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Nama :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(20, 90, 60, 23);

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
        jLabel18.setBounds(170, 40, 55, 23);

        NIP.setEditable(false);
        NIP.setHighlighter(null);
        NIP.setName("NIP"); // NOI18N
        FormInput.add(NIP);
        NIP.setBounds(229, 40, 100, 23);

        NamaPetugas.setEditable(false);
        NamaPetugas.setName("NamaPetugas"); // NOI18N
        FormInput.add(NamaPetugas);
        NamaPetugas.setBounds(331, 40, 157, 23);

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
        btnPetugas.setBounds(490, 40, 28, 23);

        jLabel16.setText("Tanggal :");
        jLabel16.setName("jLabel16"); // NOI18N
        jLabel16.setVerifyInputWhenFocusTarget(false);
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 40, 70, 23);

        HubPasien.setName("HubPasien"); // NOI18N
        HubPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HubPasienKeyPressed(evt);
            }
        });
        FormInput.add(HubPasien);
        HubPasien.setBounds(153, 120, 110, 23);

        Tanggal.setForeground(new java.awt.Color(50, 70, 50));
        Tanggal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-02-2026" }));
        Tanggal.setDisplayFormat("dd-MM-yyyy");
        Tanggal.setName("Tanggal"); // NOI18N
        Tanggal.setOpaque(false);
        Tanggal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKeyPressed(evt);
            }
        });
        FormInput.add(Tanggal);
        Tanggal.setBounds(74, 40, 90, 23);

        jLabel14.setText("Penanggung Jawab Pasien :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(0, 66, 154, 23);

        jLabel3.setText("No.Persetujuan :");
        jLabel3.setName("jLabel3"); // NOI18N
        FormInput.add(jLabel3);
        jLabel3.setBounds(520, 40, 90, 23);

        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(614, 40, 119, 23);

        NoTelp.setName("NoTelp"); // NOI18N
        NoTelp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoTelpKeyPressed(evt);
            }
        });
        FormInput.add(NoTelp);
        NoTelp.setBounds(560, 90, 170, 23);

        jLabel20.setText("Nomor Telp/HP :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(460, 90, 96, 23);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Alamat PJ :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(279, 120, 70, 23);

        Alamat.setName("Alamat"); // NOI18N
        Alamat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AlamatKeyPressed(evt);
            }
        });
        FormInput.add(Alamat);
        Alamat.setBounds(340, 120, 390, 23);

        jLabel31.setText("Materi Edukasi");
        jLabel31.setName("jLabel31"); // NOI18N
        FormInput.add(jLabel31);
        jLabel31.setBounds(40, 560, 110, 23);

        Materi1.setBorder(null);
        Materi1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi1.setName("Materi1"); // NOI18N
        Materi1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi1ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi1);
        Materi1.setBounds(155, 560, 23, 23);

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("General consent");
        jLabel41.setName("jLabel41"); // NOI18N
        FormInput.add(jLabel41);
        jLabel41.setBounds(180, 560, 130, 23);

        Materi2.setBorder(null);
        Materi2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi2.setName("Materi2"); // NOI18N
        Materi2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi2ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi2);
        Materi2.setBounds(155, 585, 23, 23);

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel42.setText("Hak - hak pasien dan keluarga");
        jLabel42.setName("jLabel42"); // NOI18N
        FormInput.add(jLabel42);
        jLabel42.setBounds(180, 585, 180, 23);

        Materi3.setBorder(null);
        Materi3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi3.setName("Materi3"); // NOI18N
        Materi3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi3ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi3);
        Materi3.setBounds(155, 610, 23, 23);

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Kewajiban pasien dan keluarga");
        jLabel43.setToolTipText("");
        jLabel43.setName("jLabel43"); // NOI18N
        FormInput.add(jLabel43);
        jLabel43.setBounds(180, 610, 180, 23);

        Materi9.setBorder(null);
        Materi9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi9.setName("Materi9"); // NOI18N
        Materi9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi9ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi9);
        Materi9.setBounds(155, 835, 23, 23);

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
        scrollPane1.setBounds(180, 835, 560, 70);

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
        ChkSemua.setBounds(580, 560, 23, 23);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel44.setText("Pilih semua");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(605, 560, 70, 23);

        jSeparator1.setName("jSeparator1"); // NOI18N
        FormInput.add(jSeparator1);
        jSeparator1.setBounds(20, 150, 720, 3);

        Materi4.setBorder(null);
        Materi4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi4.setName("Materi4"); // NOI18N
        Materi4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi4ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi4);
        Materi4.setBounds(155, 635, 23, 23);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setText("Peraturan dan tata tertib rumah sakit");
        jLabel45.setToolTipText("");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(180, 635, 190, 23);

        Materi5.setBorder(null);
        Materi5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi5.setName("Materi5"); // NOI18N
        Materi5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi5ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi5);
        Materi5.setBounds(365, 560, 23, 23);

        Materi6.setBorder(null);
        Materi6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi6.setName("Materi6"); // NOI18N
        Materi6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi6ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi6);
        Materi6.setBounds(365, 585, 23, 23);

        Materi7.setBorder(null);
        Materi7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi7.setName("Materi7"); // NOI18N
        Materi7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi7ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi7);
        Materi7.setBounds(365, 610, 23, 23);

        Materi8.setBorder(null);
        Materi8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        Materi8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Materi8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Materi8.setName("Materi8"); // NOI18N
        Materi8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                Materi8ItemStateChanged(evt);
            }
        });
        FormInput.add(Materi8);
        Materi8.setBounds(365, 635, 23, 23);

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Kelas rawat inap dan fasilitasnya");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(390, 560, 200, 23);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel47.setText("Pernyataan membuka rahasia kedokteran");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(390, 585, 200, 23);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel48.setText("Perkiraan biaya");
        jLabel48.setName("jLabel48"); // NOI18N
        FormInput.add(jLabel48);
        jLabel48.setBounds(390, 610, 200, 23);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel49.setText("Pasien tidak bisa menggunakan BPJS");
        jLabel49.setName("jLabel49"); // NOI18N
        FormInput.add(jLabel49);
        jLabel49.setBounds(390, 635, 270, 23);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel50.setText("- Tidak memenuhi kriteria gawat darurat/");
        jLabel50.setName("jLabel50"); // NOI18N
        FormInput.add(jLabel50);
        jLabel50.setBounds(390, 650, 270, 23);

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("pelayanan atas permintaan sendiri");
        jLabel51.setName("jLabel51"); // NOI18N
        FormInput.add(jLabel51);
        jLabel51.setBounds(397, 665, 260, 23);

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setText("Pembiayaan");
        jLabel52.setToolTipText("");
        jLabel52.setName("jLabel52"); // NOI18N
        FormInput.add(jLabel52);
        jLabel52.setBounds(180, 660, 210, 23);

        Asuransi.setName("Asuransi"); // NOI18N
        Asuransi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AsuransiKeyPressed(evt);
            }
        });
        FormInput.add(Asuransi);
        Asuransi.setBounds(265, 735, 150, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        Permasalahan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Permasalahan.setColumns(20);
        Permasalahan.setRows(5);
        Permasalahan.setName("Permasalahan"); // NOI18N
        Permasalahan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PermasalahanKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(Permasalahan);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(200, 785, 540, 40);

        jLabel32.setText("Metode Edukasi");
        jLabel32.setName("jLabel32"); // NOI18N
        FormInput.add(jLabel32);
        jLabel32.setBounds(40, 910, 110, 23);

        ChkRenogram19.setBorder(null);
        ChkRenogram19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram19.setName("ChkRenogram19"); // NOI18N
        ChkRenogram19.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram19ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram19);
        ChkRenogram19.setBounds(155, 910, 23, 23);

        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel57.setText("Penjelasan");
        jLabel57.setToolTipText("");
        jLabel57.setName("jLabel57"); // NOI18N
        FormInput.add(jLabel57);
        jLabel57.setBounds(180, 910, 90, 23);

        ChkRenogram20.setBorder(null);
        ChkRenogram20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram20.setName("ChkRenogram20"); // NOI18N
        ChkRenogram20.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram20ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram20);
        ChkRenogram20.setBounds(260, 910, 23, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Diskusi");
        jLabel58.setToolTipText("");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(285, 910, 90, 23);

        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Demonstrasi");
        jLabel59.setToolTipText("");
        jLabel59.setName("jLabel59"); // NOI18N
        FormInput.add(jLabel59);
        jLabel59.setBounds(390, 910, 90, 23);

        ChkRenogram21.setBorder(null);
        ChkRenogram21.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram21.setName("ChkRenogram21"); // NOI18N
        ChkRenogram21.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram21ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram21);
        ChkRenogram21.setBounds(365, 910, 23, 23);

        ChkRenogram22.setBorder(null);
        ChkRenogram22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkRenogram22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkRenogram22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkRenogram22.setName("ChkRenogram22"); // NOI18N
        ChkRenogram22.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ChkRenogram22ItemStateChanged(evt);
            }
        });
        FormInput.add(ChkRenogram22);
        ChkRenogram22.setBounds(475, 910, 23, 23);

        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Praktek Langsung");
        jLabel60.setToolTipText("");
        jLabel60.setName("jLabel60"); // NOI18N
        FormInput.add(jLabel60);
        jLabel60.setBounds(500, 910, 90, 23);

        jLabel33.setText("Tujuan");
        jLabel33.setName("jLabel33"); // NOI18N
        FormInput.add(jLabel33);
        jLabel33.setBounds(40, 935, 110, 23);

        buttonGroup2.add(Mengulangi1);
        Mengulangi1.setForeground(new java.awt.Color(0, 0, 0));
        Mengulangi1.setText("Informasi baru");
        Mengulangi1.setName("Mengulangi1"); // NOI18N
        Mengulangi1.setPreferredSize(new java.awt.Dimension(40, 20));
        Mengulangi1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Mengulangi1ActionPerformed(evt);
            }
        });
        FormInput.add(Mengulangi1);
        Mengulangi1.setBounds(160, 935, 100, 23);

        jLabel34.setText("Evaluasi/Verifikasi");
        jLabel34.setName("jLabel34"); // NOI18N
        FormInput.add(jLabel34);
        jLabel34.setBounds(40, 960, 110, 23);

        buttonGroup1.add(Mengerti1);
        Mengerti1.setForeground(new java.awt.Color(0, 0, 0));
        Mengerti1.setText("Mengerti");
        Mengerti1.setName("Mengerti1"); // NOI18N
        Mengerti1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Mengerti1);
        Mengerti1.setBounds(160, 960, 100, 23);

        buttonGroup1.add(Tidak1);
        Tidak1.setForeground(new java.awt.Color(0, 0, 0));
        Tidak1.setText("Tidak mengerti");
        Tidak1.setName("Tidak1"); // NOI18N
        Tidak1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Tidak1);
        Tidak1.setBounds(265, 960, 100, 23);

        buttonGroup1.add(Reedukasi1);
        Reedukasi1.setForeground(new java.awt.Color(0, 0, 0));
        Reedukasi1.setText("Re - edukasi");
        Reedukasi1.setName("Reedukasi1"); // NOI18N
        Reedukasi1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Reedukasi1);
        Reedukasi1.setBounds(370, 960, 140, 23);

        Tglre1.setName("Tglre1"); // NOI18N
        Tglre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tglre1KeyPressed(evt);
            }
        });
        FormInput.add(Tglre1);
        Tglre1.setBounds(350, 985, 100, 23);

        buttonGroup1.add(Tanggalre1);
        Tanggalre1.setForeground(new java.awt.Color(0, 0, 0));
        Tanggalre1.setText("Tanggal Re -");
        Tanggalre1.setName("Tanggalre1"); // NOI18N
        Tanggalre1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Tanggalre1);
        Tanggalre1.setBounds(265, 985, 90, 23);

        buttonGroup1.add(Redemonstrasi1);
        Redemonstrasi1.setForeground(new java.awt.Color(0, 0, 0));
        Redemonstrasi1.setText("Re - demonstrasi");
        Redemonstrasi1.setName("Redemonstrasi1"); // NOI18N
        Redemonstrasi1.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Redemonstrasi1);
        Redemonstrasi1.setBounds(160, 985, 110, 23);

        buttonGroup2.add(Mengulangi2);
        Mengulangi2.setForeground(new java.awt.Color(0, 0, 0));
        Mengulangi2.setText("Dapat mengulangi edukasi yang didapat");
        Mengulangi2.setName("Mengulangi2"); // NOI18N
        Mengulangi2.setPreferredSize(new java.awt.Dimension(40, 20));
        FormInput.add(Mengulangi2);
        Mengulangi2.setBounds(265, 935, 240, 23);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Identifikasi Kemampuan dan Kemauan Belajar Pasien dan Keluarga");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(20, 155, 350, 23);

        jLabel13.setText("Tingkat pendidikan");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(20, 205, 130, 23);

        jLabel15.setText("Kemampuan membaca");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(20, 180, 130, 23);

        jLabel22.setText("Bahasa isyarat");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(20, 255, 130, 23);

        jLabel23.setText("Bahasa sehari-hari");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(20, 280, 130, 23);

        jLabel24.setText("Perlu penerjemah");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(20, 330, 130, 23);

        buttonGroup3.add(Baca1);
        Baca1.setForeground(new java.awt.Color(0, 0, 0));
        Baca1.setText("Bisa membaca");
        Baca1.setName("Baca1"); // NOI18N
        Baca1.setPreferredSize(new java.awt.Dimension(40, 20));
        Baca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Baca1ActionPerformed(evt);
            }
        });
        FormInput.add(Baca1);
        Baca1.setBounds(160, 180, 100, 23);

        buttonGroup3.add(Baca2);
        Baca2.setForeground(new java.awt.Color(0, 0, 0));
        Baca2.setText("Tidak bisa membaca/buta huruf");
        Baca2.setName("Baca2"); // NOI18N
        Baca2.setPreferredSize(new java.awt.Dimension(40, 20));
        Baca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Baca2ActionPerformed(evt);
            }
        });
        FormInput.add(Baca2);
        Baca2.setBounds(265, 180, 200, 23);

        buttonGroup4.add(Pend1);
        Pend1.setForeground(new java.awt.Color(0, 0, 0));
        Pend1.setText("SD");
        Pend1.setName("Pend1"); // NOI18N
        Pend1.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend1ActionPerformed(evt);
            }
        });
        FormInput.add(Pend1);
        Pend1.setBounds(160, 205, 100, 23);

        buttonGroup4.add(Pend2);
        Pend2.setForeground(new java.awt.Color(0, 0, 0));
        Pend2.setText("SMP");
        Pend2.setName("Pend2"); // NOI18N
        Pend2.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend2ActionPerformed(evt);
            }
        });
        FormInput.add(Pend2);
        Pend2.setBounds(265, 205, 100, 23);

        buttonGroup4.add(Pend3);
        Pend3.setForeground(new java.awt.Color(0, 0, 0));
        Pend3.setText("SMA");
        Pend3.setName("Pend3"); // NOI18N
        Pend3.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend3ActionPerformed(evt);
            }
        });
        FormInput.add(Pend3);
        Pend3.setBounds(370, 205, 100, 23);

        buttonGroup4.add(Pend4);
        Pend4.setForeground(new java.awt.Color(0, 0, 0));
        Pend4.setText("Tidak sekolah");
        Pend4.setName("Pend4"); // NOI18N
        Pend4.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend4ActionPerformed(evt);
            }
        });
        FormInput.add(Pend4);
        Pend4.setBounds(475, 205, 100, 23);

        buttonGroup4.add(Pend5);
        Pend5.setForeground(new java.awt.Color(0, 0, 0));
        Pend5.setText("Akademi");
        Pend5.setName("Pend5"); // NOI18N
        Pend5.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend5ActionPerformed(evt);
            }
        });
        FormInput.add(Pend5);
        Pend5.setBounds(160, 230, 100, 23);

        buttonGroup4.add(Pend6);
        Pend6.setForeground(new java.awt.Color(0, 0, 0));
        Pend6.setText("Sarjana");
        Pend6.setName("Pend6"); // NOI18N
        Pend6.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend6ActionPerformed(evt);
            }
        });
        FormInput.add(Pend6);
        Pend6.setBounds(265, 230, 100, 23);

        buttonGroup4.add(Pend7);
        Pend7.setForeground(new java.awt.Color(0, 0, 0));
        Pend7.setText("Pascasarjana");
        Pend7.setName("Pend7"); // NOI18N
        Pend7.setPreferredSize(new java.awt.Dimension(40, 20));
        Pend7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Pend7ActionPerformed(evt);
            }
        });
        FormInput.add(Pend7);
        Pend7.setBounds(370, 230, 100, 23);

        buttonGroup5.add(Isyarat1);
        Isyarat1.setForeground(new java.awt.Color(0, 0, 0));
        Isyarat1.setText("Tidak");
        Isyarat1.setName("Isyarat1"); // NOI18N
        Isyarat1.setPreferredSize(new java.awt.Dimension(40, 20));
        Isyarat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Isyarat1ActionPerformed(evt);
            }
        });
        FormInput.add(Isyarat1);
        Isyarat1.setBounds(160, 255, 100, 23);

        buttonGroup5.add(Isyarat2);
        Isyarat2.setForeground(new java.awt.Color(0, 0, 0));
        Isyarat2.setText("Ya");
        Isyarat2.setName("Isyarat2"); // NOI18N
        Isyarat2.setPreferredSize(new java.awt.Dimension(40, 20));
        Isyarat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Isyarat2ActionPerformed(evt);
            }
        });
        FormInput.add(Isyarat2);
        Isyarat2.setBounds(265, 255, 100, 23);

        KetBahasa1.setName("KetBahasa1"); // NOI18N
        KetBahasa1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetBahasa1KeyPressed(evt);
            }
        });
        FormInput.add(KetBahasa1);
        KetBahasa1.setBounds(220, 280, 120, 23);

        KetBahasa2.setName("KetBahasa2"); // NOI18N
        KetBahasa2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetBahasa2KeyPressed(evt);
            }
        });
        FormInput.add(KetBahasa2);
        KetBahasa2.setBounds(420, 280, 120, 23);

        buttonGroup6.add(Bahasa1);
        Bahasa1.setForeground(new java.awt.Color(0, 0, 0));
        Bahasa1.setText("Daerah");
        Bahasa1.setName("Bahasa1"); // NOI18N
        Bahasa1.setPreferredSize(new java.awt.Dimension(40, 20));
        Bahasa1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bahasa1ActionPerformed(evt);
            }
        });
        FormInput.add(Bahasa1);
        Bahasa1.setBounds(160, 280, 100, 23);

        buttonGroup6.add(Bahasa2);
        Bahasa2.setForeground(new java.awt.Color(0, 0, 0));
        Bahasa2.setText("Asing");
        Bahasa2.setName("Bahasa2"); // NOI18N
        Bahasa2.setPreferredSize(new java.awt.Dimension(40, 20));
        Bahasa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bahasa2ActionPerformed(evt);
            }
        });
        FormInput.add(Bahasa2);
        Bahasa2.setBounds(370, 280, 100, 23);

        buttonGroup6.add(Bahasa3);
        Bahasa3.setForeground(new java.awt.Color(0, 0, 0));
        Bahasa3.setText("Indonesia");
        Bahasa3.setName("Bahasa3"); // NOI18N
        Bahasa3.setPreferredSize(new java.awt.Dimension(40, 20));
        Bahasa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bahasa3ActionPerformed(evt);
            }
        });
        FormInput.add(Bahasa3);
        Bahasa3.setBounds(160, 305, 100, 23);

        KetTerjemah2.setName("KetTerjemah2"); // NOI18N
        KetTerjemah2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetTerjemah2KeyPressed(evt);
            }
        });
        FormInput.add(KetTerjemah2);
        KetTerjemah2.setBounds(345, 330, 120, 23);

        buttonGroup7.add(Terjemah1);
        Terjemah1.setForeground(new java.awt.Color(0, 0, 0));
        Terjemah1.setText("Tidak");
        Terjemah1.setName("Terjemah1"); // NOI18N
        Terjemah1.setPreferredSize(new java.awt.Dimension(40, 20));
        Terjemah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Terjemah1ActionPerformed(evt);
            }
        });
        FormInput.add(Terjemah1);
        Terjemah1.setBounds(160, 330, 100, 23);

        buttonGroup7.add(Terjemah2);
        Terjemah2.setForeground(new java.awt.Color(0, 0, 0));
        Terjemah2.setText("Ya, bahasa");
        Terjemah2.setName("Terjemah2"); // NOI18N
        Terjemah2.setPreferredSize(new java.awt.Dimension(40, 20));
        Terjemah2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Terjemah2ActionPerformed(evt);
            }
        });
        FormInput.add(Terjemah2);
        Terjemah2.setBounds(265, 330, 100, 23);

        jLabel25.setText("Hambatan belajar");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(20, 355, 130, 23);

        buttonGroup8.add(Hambatan1);
        Hambatan1.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan1.setText("Marah");
        Hambatan1.setName("Hambatan1"); // NOI18N
        Hambatan1.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan1ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan1);
        Hambatan1.setBounds(160, 355, 100, 23);

        buttonGroup8.add(Hambatan2);
        Hambatan2.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan2.setText("Cemas");
        Hambatan2.setName("Hambatan2"); // NOI18N
        Hambatan2.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan2ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan2);
        Hambatan2.setBounds(370, 355, 100, 23);

        buttonGroup8.add(Hambatan3);
        Hambatan3.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan3.setText("Masalah pendengaran");
        Hambatan3.setName("Hambatan3"); // NOI18N
        Hambatan3.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan3ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan3);
        Hambatan3.setBounds(580, 355, 150, 23);

        buttonGroup8.add(Hambatan4);
        Hambatan4.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan4.setText("Gangguan bicara");
        Hambatan4.setName("Hambatan4"); // NOI18N
        Hambatan4.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan4ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan4);
        Hambatan4.setBounds(160, 380, 120, 23);

        buttonGroup8.add(Hambatan5);
        Hambatan5.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan5.setText("Masalah penglihatan");
        Hambatan5.setName("Hambatan5"); // NOI18N
        Hambatan5.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan5ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan5);
        Hambatan5.setBounds(370, 380, 140, 23);

        buttonGroup8.add(Hambatan6);
        Hambatan6.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan6.setText("Tidak ada motivasi belajar");
        Hambatan6.setName("Hambatan6"); // NOI18N
        Hambatan6.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan6ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan6);
        Hambatan6.setBounds(580, 380, 150, 23);

        buttonGroup8.add(Hambatan7);
        Hambatan7.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan7.setText("Tidak ditemukan hambatan belajar");
        Hambatan7.setName("Hambatan7"); // NOI18N
        Hambatan7.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan7ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan7);
        Hambatan7.setBounds(160, 405, 200, 23);

        buttonGroup8.add(Hambatan8);
        Hambatan8.setForeground(new java.awt.Color(0, 0, 0));
        Hambatan8.setText("Secara fisiologis tidak dapat belajar");
        Hambatan8.setName("Hambatan8"); // NOI18N
        Hambatan8.setPreferredSize(new java.awt.Dimension(40, 20));
        Hambatan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Hambatan8ActionPerformed(evt);
            }
        });
        FormInput.add(Hambatan8);
        Hambatan8.setBounds(370, 405, 210, 23);

        jLabel26.setText("Cara belajar yang disukai");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(20, 430, 130, 23);

        buttonGroup9.add(Cara1);
        Cara1.setForeground(new java.awt.Color(0, 0, 0));
        Cara1.setText("Menulis");
        Cara1.setName("Cara1"); // NOI18N
        Cara1.setPreferredSize(new java.awt.Dimension(40, 20));
        Cara1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cara1ActionPerformed(evt);
            }
        });
        FormInput.add(Cara1);
        Cara1.setBounds(160, 430, 100, 23);

        buttonGroup9.add(Cara2);
        Cara2.setForeground(new java.awt.Color(0, 0, 0));
        Cara2.setText("Demonstrasi");
        Cara2.setName("Cara2"); // NOI18N
        Cara2.setPreferredSize(new java.awt.Dimension(40, 20));
        Cara2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cara2ActionPerformed(evt);
            }
        });
        FormInput.add(Cara2);
        Cara2.setBounds(265, 430, 100, 23);

        buttonGroup9.add(Cara3);
        Cara3.setForeground(new java.awt.Color(0, 0, 0));
        Cara3.setText("Mendengar");
        Cara3.setName("Cara3"); // NOI18N
        Cara3.setPreferredSize(new java.awt.Dimension(40, 20));
        Cara3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cara3ActionPerformed(evt);
            }
        });
        FormInput.add(Cara3);
        Cara3.setBounds(370, 430, 100, 23);

        buttonGroup9.add(Cara4);
        Cara4.setForeground(new java.awt.Color(0, 0, 0));
        Cara4.setText("Diskusi");
        Cara4.setName("Cara4"); // NOI18N
        Cara4.setPreferredSize(new java.awt.Dimension(40, 20));
        Cara4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cara4ActionPerformed(evt);
            }
        });
        FormInput.add(Cara4);
        Cara4.setBounds(160, 455, 100, 23);

        buttonGroup9.add(Cara5);
        Cara5.setForeground(new java.awt.Color(0, 0, 0));
        Cara5.setText("Membaca");
        Cara5.setName("Cara5"); // NOI18N
        Cara5.setPreferredSize(new java.awt.Dimension(40, 20));
        Cara5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cara5ActionPerformed(evt);
            }
        });
        FormInput.add(Cara5);
        Cara5.setBounds(265, 455, 100, 23);

        buttonGroup9.add(Cara6);
        Cara6.setForeground(new java.awt.Color(0, 0, 0));
        Cara6.setText("Audio Visual");
        Cara6.setName("Cara6"); // NOI18N
        Cara6.setPreferredSize(new java.awt.Dimension(40, 20));
        Cara6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cara6ActionPerformed(evt);
            }
        });
        FormInput.add(Cara6);
        Cara6.setBounds(370, 455, 100, 23);

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Keyakinan dan nilai-nilai pasien dan keluarga");
        jLabel27.setName("jLabel27"); // NOI18N
        FormInput.add(jLabel27);
        jLabel27.setBounds(30, 480, 250, 23);

        KetYakin2.setName("KetYakin2"); // NOI18N
        KetYakin2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetYakin2KeyPressed(evt);
            }
        });
        FormInput.add(KetYakin2);
        KetYakin2.setBounds(355, 505, 380, 23);

        buttonGroup10.add(Yakin1);
        Yakin1.setForeground(new java.awt.Color(0, 0, 0));
        Yakin1.setText("Tidak");
        Yakin1.setName("Yakin1"); // NOI18N
        Yakin1.setPreferredSize(new java.awt.Dimension(40, 20));
        Yakin1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Yakin1ActionPerformed(evt);
            }
        });
        FormInput.add(Yakin1);
        Yakin1.setBounds(160, 505, 100, 23);

        buttonGroup10.add(Yakin2);
        Yakin2.setForeground(new java.awt.Color(0, 0, 0));
        Yakin2.setText("Ada, Jelaskan");
        Yakin2.setName("Yakin2"); // NOI18N
        Yakin2.setPreferredSize(new java.awt.Dimension(40, 20));
        Yakin2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Yakin2ActionPerformed(evt);
            }
        });
        FormInput.add(Yakin2);
        Yakin2.setBounds(265, 505, 100, 23);

        jLabel28.setText("Bersedia menerima informasi");
        jLabel28.setName("jLabel28"); // NOI18N
        FormInput.add(jLabel28);
        jLabel28.setBounds(10, 530, 140, 23);

        buttonGroup11.add(Sedia1);
        Sedia1.setForeground(new java.awt.Color(0, 0, 0));
        Sedia1.setText("Tidak");
        Sedia1.setName("Sedia1"); // NOI18N
        Sedia1.setPreferredSize(new java.awt.Dimension(40, 20));
        Sedia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sedia1ActionPerformed(evt);
            }
        });
        FormInput.add(Sedia1);
        Sedia1.setBounds(160, 530, 100, 23);

        buttonGroup11.add(Sedia2);
        Sedia2.setForeground(new java.awt.Color(0, 0, 0));
        Sedia2.setText("Ya");
        Sedia2.setName("Sedia2"); // NOI18N
        Sedia2.setPreferredSize(new java.awt.Dimension(40, 20));
        Sedia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sedia2ActionPerformed(evt);
            }
        });
        FormInput.add(Sedia2);
        Sedia2.setBounds(265, 530, 100, 23);

        jSeparator2.setName("jSeparator2"); // NOI18N
        FormInput.add(jSeparator2);
        jSeparator2.setBounds(20, 555, 720, 3);

        buttonGroup12.add(Bayar1);
        Bayar1.setForeground(new java.awt.Color(0, 0, 0));
        Bayar1.setText("Umum");
        Bayar1.setName("Bayar1"); // NOI18N
        Bayar1.setPreferredSize(new java.awt.Dimension(40, 20));
        Bayar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bayar1ActionPerformed(evt);
            }
        });
        FormInput.add(Bayar1);
        Bayar1.setBounds(180, 685, 100, 23);

        buttonGroup12.add(Bayar2);
        Bayar2.setForeground(new java.awt.Color(0, 0, 0));
        Bayar2.setText("BPJS");
        Bayar2.setName("Bayar2"); // NOI18N
        Bayar2.setPreferredSize(new java.awt.Dimension(40, 20));
        Bayar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bayar2ActionPerformed(evt);
            }
        });
        FormInput.add(Bayar2);
        Bayar2.setBounds(180, 710, 100, 23);

        buttonGroup12.add(Bayar3);
        Bayar3.setForeground(new java.awt.Color(0, 0, 0));
        Bayar3.setText("Asuransi lain");
        Bayar3.setName("Bayar3"); // NOI18N
        Bayar3.setPreferredSize(new java.awt.Dimension(40, 20));
        Bayar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bayar3ActionPerformed(evt);
            }
        });
        FormInput.add(Bayar3);
        Bayar3.setBounds(180, 735, 100, 23);

        buttonGroup12.add(Bayar4);
        Bayar4.setForeground(new java.awt.Color(0, 0, 0));
        Bayar4.setText("Permasalahan");
        Bayar4.setName("Bayar4"); // NOI18N
        Bayar4.setPreferredSize(new java.awt.Dimension(40, 20));
        Bayar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bayar4ActionPerformed(evt);
            }
        });
        FormInput.add(Bayar4);
        Bayar4.setBounds(180, 760, 100, 23);

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
        }else if(HubPasien.getText().trim().equals("")){
            Valid.textKosong(HubPasien,"Umur");
        }else if(NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NamaPetugas,"Petugas");
        }else if(NoTelp.getText().trim().equals("")){
            Valid.textKosong(NoTelp,"Nomor Telepon");
        }else if(NoSurat.getText().trim().equals("")){
            Valid.textKosong(NoSurat,"No.Pernyataan");
        }else{
            if(Baca1.isSelected()==true){
                baca="Bisa membaca";
            }else if(Baca2.isSelected()==true){
                baca="Tidak bisa membaca/buta huruf";
            }
            
            if(Pend1.isSelected()==true){
                pend="SD";
            }else if(Pend2.isSelected()==true){
                pend="SMP";
            }else if(Pend3.isSelected()==true){
                pend="SMA";
            }else if(Pend4.isSelected()==true){
                pend="Tidak sekolah";
            }else if(Pend5.isSelected()==true){
                pend="Akademi";
            }else if(Pend6.isSelected()==true){
                pend="Sarjana";
            }else if(Pend7.isSelected()==true){
                pend="Pascasarjana";
            }
            
            if(Isyarat1.isSelected()==true){
                isyarat="Tidak";
            }else if(Isyarat2.isSelected()==true){
                isyarat="Ya";
            }
            
            if(Bahasa1.isSelected()==true){
                bahasa="Daerah "+KetBahasa1.getText();
            }else if(Bahasa2.isSelected()==true){
                bahasa="Asing "+KetBahasa2.getText();
            }else if(Bahasa3.isSelected()==true){
                bahasa="Indonesia";
            }
            
            if(Terjemah1.isSelected()==true){
                terjemah="Tidak";
            }else if(Terjemah2.isSelected()==true){
                terjemah="Ya,bahasa "+KetTerjemah2.getText();
            }
            
            if(Hambatan1.isSelected()==true){
                hambatan="Marah";
            }else if(Hambatan2.isSelected()==true){
                hambatan="Cemas";
            }else if(Hambatan3.isSelected()==true){
                hambatan="Masalah pendengaran";
            }else if(Hambatan4.isSelected()==true){
                hambatan="Gangguan bicara";
            }else if(Hambatan5.isSelected()==true){
                hambatan="Masalah penglihatan";
            }else if(Hambatan6.isSelected()==true){
                hambatan="Tidak ada motivasi belajar";
            }else if(Hambatan7.isSelected()==true){
                hambatan="Tidak ditemukan hambatan belajar";
            }else if(Hambatan8.isSelected()==true){
                hambatan="Secara fisiologis tidak dapat belajar";
            }
            
            if(Cara1.isSelected()==true){
                cara="Menulis";
            }else if(Cara2.isSelected()==true){
                cara="Demonstrasi";
            }else if(Cara3.isSelected()==true){
                cara="Mendengar";
            }else if(Cara4.isSelected()==true){
                cara="Diskusi";
            }else if(Cara5.isSelected()==true){
                cara="Membaca";
            }else if(Cara6.isSelected()==true){
                cara="Audio visual";
            }
            
            if(Yakin1.isSelected()==true){
                yakin="Tidak";
            }else if(Yakin2.isSelected()==true){
                yakin="Ada, Jelaskan "+KetYakin2.getText();
            }
            
            if(Sedia1.isSelected()==true){
                sedia="Tidak";
            }else if(Sedia2.isSelected()==true){
                sedia="Ya";
            }
            
            if(Materi1.isSelected()==true){
                m1="true";
            }else{
                m1="false";
            }
            
            if(Materi2.isSelected()==true){
                m2="true";
            }else{
                m2="false";
            }
            
            if(Materi3.isSelected()==true){
                m3="true";
            }else{
                m3="false";
            }
            
            if(Materi4.isSelected()==true){
                m4="true";
            }else{
                m4="false";
            }
            
            if(Materi5.isSelected()==true){
                m5="true";
            }else{
                m5="false";
            }
            
            if(Materi6.isSelected()==true){
                m6="true";
            }else{
                m6="false";
            }
            
            if(Materi7.isSelected()==true){
                m7="true";
            }else{
                m7="false";
            }
            
            if(Materi8.isSelected()==true){
                m8="true";
            }else{
                m8="false";
            }
            
            if(Materi9.isSelected()==true){
                m9=Tambahan.getText();
            }else{
                m9="false";
            }
            
            if(Bayar1.isSelected()==true){
                m10="Umum";
            }else if(Bayar2.isSelected()==true){
                m10="BPJS";
            }else if(Bayar3.isSelected()==true){
                m10="Asuransi lain ("+Asuransi.getText()+")";
            }else if(Bayar4.isSelected()==true){
                m10=Permasalahan.getText();
            }
            
            if(ChkRenogram19.isSelected()==true){
                penjelasan="true";
            }else{
                penjelasan="false";
            }
            
            if(ChkRenogram20.isSelected()==true){
                diskusi="true";
            }else{
                diskusi="false";
            }
            
            if(ChkRenogram21.isSelected()==true){
                demonstrasi="true";
            }else{
                demonstrasi="false";
            }
            
            if(ChkRenogram22.isSelected()==true){
                praktek="true";
            }else{
                praktek="false";
            }
            
            if(Mengulangi1.isSelected()==true){
                tujuan="Dapat mengulangi edukasi yang didapat";
            }
            
            if(Mengerti1.isSelected()==true){
                evaluasi="Mengerti";
            }else if(Tidak1.isSelected()==true){
                evaluasi="Tidak mengerti";
            }else if(Reedukasi1.isSelected()==true){
                evaluasi="Re-edukasi";
            }else if(Redemonstrasi1.isSelected()==true){
                evaluasi="Re-demonstrasi";
            }else if(Tanggalre1.isSelected()==true){
                evaluasi="Tanggal Re-"+Tglre1.getText();
            }
            
            if(Sequel.menyimpantf("surat_persetujuan_umum","?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?","Data",37,new String[]{
                    NoSurat.getText(),TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),"00:00:00",Alamat.getText(),NamaPJ.getText(),"-","-",
                    "-",HubPasien.getText(),NoTelp.getText(),baca,pend,yakin,isyarat,bahasa,terjemah,hambatan,cara,sedia,m1,m2,m3,m4,m10,
                    m5,m6,m7,m8,m9,penjelasan,diskusi,demonstrasi,praktek,tujuan,evaluasi,NIP.getText()
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
            Valid.pindah(evt,Alamat,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
        Materi1.setSelected(false);
        Materi2.setSelected(false);
        Materi3.setSelected(false);
        Materi4.setSelected(false);
        Materi5.setSelected(false);
        Materi6.setSelected(false);
        Materi7.setSelected(false);
        Materi8.setSelected(false);
        Materi9.setSelected(false);
        ChkSemua.setSelected(false);
        ChkRenogram19.setSelected(false);
        ChkRenogram20.setSelected(false);
        ChkRenogram21.setSelected(false);
        ChkRenogram22.setSelected(false);
        Bayar1.setSelected(false);
        Bayar2.setSelected(false);
        Bayar3.setSelected(false);
        Bayar4.setSelected(false);
        buttonGroup1.clearSelection();
        buttonGroup2.clearSelection();
        buttonGroup3.clearSelection();
        buttonGroup4.clearSelection();
        buttonGroup5.clearSelection();
        buttonGroup6.clearSelection();
        buttonGroup7.clearSelection();
        buttonGroup8.clearSelection();
        buttonGroup9.clearSelection();
        buttonGroup10.clearSelection();
        buttonGroup11.clearSelection();
        buttonGroup12.clearSelection();
        isForm();  
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
                if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString())){
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
        }else if(HubPasien.getText().trim().equals("")){
            Valid.textKosong(HubPasien,"Umur");
        }else if(NoTelp.getText().trim().equals("")){
            Valid.textKosong(NoTelp,"Nomor Telp");
        }else if(NamaPetugas.getText().trim().equals("")){
            Valid.textKosong(NamaPetugas,"Petugas");
        }else if(NoSurat.getText().trim().equals("")){
            Valid.textKosong(NoSurat,"No.Pernyataan");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(NIP.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString())){
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
            Map<String, Object> param = new HashMap<>(); 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            
            if(TCari.getText().trim().equals("")){
                Valid.MyReportqry("rptDataPersetujuanUmum.jasper","report","::[ Data Persetujuan Umum ]::",
                    "select surat_persetujuan_umum.no_surat,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,"+
                    "reg_periksa.sttsumur,pasien.jk,pasien.tgl_lahir,surat_persetujuan_umum.tanggal,surat_persetujuan_umum.jam,"+
                    "surat_persetujuan_umum.nilai_kepercayaan,surat_persetujuan_umum.nama_pj,surat_persetujuan_umum.umur_pj,surat_persetujuan_umum.no_ktppj,"+
                    "surat_persetujuan_umum.jkpj,surat_persetujuan_umum.bertindak_atas,surat_persetujuan_umum.no_telp,surat_persetujuan_umum.nip,"+
                    "petugas.nama from surat_persetujuan_umum inner join reg_periksa on surat_persetujuan_umum.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on surat_persetujuan_umum.nip=petugas.nip where "+
                    "surat_persetujuan_umum.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' order by surat_persetujuan_umum.tanggal",param);
            }else{
                Valid.MyReportqry("rptDataPersetujuanUmum.jasper","report","::[ Data Persetujuan Umum ]::",
                    "select surat_persetujuan_umum.no_surat,reg_periksa.no_rawat,pasien.no_rkm_medis,pasien.nm_pasien,reg_periksa.umurdaftar,"+
                    "reg_periksa.sttsumur,pasien.jk,pasien.tgl_lahir,surat_persetujuan_umum.tanggal,surat_persetujuan_umum.jam,"+
                    "surat_persetujuan_umum.nilai_kepercayaan,surat_persetujuan_umum.nama_pj,surat_persetujuan_umum.umur_pj,surat_persetujuan_umum.no_ktppj,"+
                    "surat_persetujuan_umum.jkpj,surat_persetujuan_umum.bertindak_atas,surat_persetujuan_umum.no_telp,surat_persetujuan_umum.nip,"+
                    "petugas.nama from surat_persetujuan_umum inner join reg_periksa on surat_persetujuan_umum.no_rawat=reg_periksa.no_rawat "+
                    "inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
                    "inner join petugas on surat_persetujuan_umum.nip=petugas.nip where "+
                    "surat_persetujuan_umum.tanggal between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' and "+
                    "(reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or pasien.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
                    "surat_persetujuan_umum.no_telp like '%"+TCari.getText().trim()+"%' or surat_persetujuan_umum.nama_pj like '%"+TCari.getText().trim()+"%' or "+
                    "surat_persetujuan_umum.nip like '%"+TCari.getText().trim()+"%' or petugas.nama like '%"+TCari.getText().trim()+"%') "+
                    "order by surat_persetujuan_umum.tanggal",param);
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

    private void NamaPJKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NamaPJKeyPressed
        Valid.pindah(evt,NoSurat,HubPasien);
    }//GEN-LAST:event_NamaPJKeyPressed

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
    // Valid.pindah(evt, TNm, BtnSimpan);
    }//GEN-LAST:event_TNoRMKeyPressed

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
        Valid.pindah2(evt,TCari,NoSurat);
    }//GEN-LAST:event_TanggalKeyPressed

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
        Valid.pindah(evt,btnPetugas,NamaPJ);
    }//GEN-LAST:event_NoSuratKeyPressed

    private void NoTelpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoTelpKeyPressed
        Valid.pindah(evt,HubPasien,Alamat);
    }//GEN-LAST:event_NoTelpKeyPressed

    private void ChkAccorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkAccorActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            isPhoto();
            panggilPhoto();
        }else{
            ChkAccor.setSelected(false);
            JOptionPane.showMessageDialog(null,"Silahkan pilih No.Pernyataan..!!!");
        }
    }//GEN-LAST:event_ChkAccorActionPerformed

    private void btnAmbilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbilActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().equals("Sudah")){
                JOptionPane.showMessageDialog(null,"Maaf, pasien sudah tanda tangan...!!!!");
            }else if(tbObat.getSelectedRow()>-1){
                Sequel.queryu("delete from antripersetujuanumum");
                Sequel.queryu("insert into antripersetujuanumum values('"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"','"+tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()+"')");
                Sequel.queryu("delete from surat_persetujuan_umum_pembuat_pernyataan where no_surat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'");
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Pernyataan terlebih dahulu..!!");
            }
        }
    }//GEN-LAST:event_btnAmbilActionPerformed

    private void BtnRefreshPhoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRefreshPhoto1ActionPerformed
        if(tbObat.getSelectedRow()>-1){
            panggilPhoto();
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Pernyataan terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnRefreshPhoto1ActionPerformed

    private void HubPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HubPasienKeyPressed
        Valid.pindah(evt,NamaPJ,NoTelp);
    }//GEN-LAST:event_HubPasienKeyPressed

    private void AlamatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AlamatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AlamatKeyPressed

    private void btnAmbil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAmbil1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis...!!!!");
            TCari.requestFocus();
        }else{
            if(tbObat.getValueAt(tbObat.getSelectedRow(),18).toString().equals("Sudah")){
                JOptionPane.showMessageDialog(null,"Maaf, pasien sudah tanda tangan...!!!!");
            }else if(tbObat.getSelectedRow()>-1){
                Sequel.queryu("delete from antripersetujuanumum");
                Sequel.queryu("insert into antripersetujuanumum values('"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"','"+tbObat.getValueAt(tbObat.getSelectedRow(),1).toString()+"')");
                Sequel.queryu("delete from surat_persetujuan_umum_pembuat_pernyataan where no_surat='"+tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()+"'");
            }else{
                JOptionPane.showMessageDialog(rootPane,"Silahkan anda pilih No.Pernyataan terlebih dahulu..!!");
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_btnAmbil1ActionPerformed

    private void SuratPersetujuanUmumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SuratPersetujuanUmumActionPerformed
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
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),17).toString()+
                    "\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),16).toString()+"\n"+tanggal);
            param.put("photo","http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuanumum/"+
                    Sequel.cariIsi("select surat_persetujuan_umum_pembuat_pernyataan.photo from surat_persetujuan_umum_pembuat_pernyataan where surat_persetujuan_umum_pembuat_pernyataan.no_surat=?",NoSurat.getText()));
           
            Valid.MyReportqry("rptPersetujuanUmum.jasper","report","::[ Persetujuan Umum ]::",
                    "select spu.no_surat,rp.no_rawat,p.no_rkm_medis,p.nm_pasien,rp.umurdaftar,"+
                    "rp.sttsumur,p.jk,p.tgl_lahir,date_format(spu.tanggal,'%d-%m-%Y') as tanggal,spu.jam,p.alamat,p.no_tlp,"+
                    "spu.nilai_kepercayaan,spu.nama_pj,spu.umur_pj,spu.no_ktppj,"+
                    "spu.jkpj,spu.bertindak_atas,spu.no_telp,spu.nip,"+
                    "p2.nama,IF(spupp.photo is null,\"\",\"Sudah\") as status "+
                    "from surat_persetujuan_umum spu inner join reg_periksa rp on spu.no_rawat=rp.no_rawat "+
                    "inner join pasien p on rp.no_rkm_medis=p.no_rkm_medis "+
                    "inner join petugas p2 on spu.nip=p2.nip "+
                    "left join surat_persetujuan_umum_pembuat_pernyataan spupp on spupp.no_surat=spu.no_surat where "+
                    "spu.no_surat='"+NoSurat.getText()+"' ",param);
            this.setCursor(Cursor.getDefaultCursor());
        }
    }//GEN-LAST:event_SuratPersetujuanUmumActionPerformed

    private void Materi1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi1ItemStateChanged

    private void Materi2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi2ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi2ItemStateChanged

    private void Materi3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi3ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi3ItemStateChanged

    private void Materi9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi9ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi9ItemStateChanged

    private void TambahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TambahanKeyPressed
        Valid.pindah2(evt,Permasalahan,Tglre1);
    }//GEN-LAST:event_TambahanKeyPressed

    private void ChkSemuaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkSemuaItemStateChanged
        if(ChkSemua.isSelected()==true){
            Materi1.setSelected(true);
            Materi2.setSelected(true);
            Materi3.setSelected(true);
            Materi4.setSelected(true);
        }
    }//GEN-LAST:event_ChkSemuaItemStateChanged

    private void Materi4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi4ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi4ItemStateChanged

    private void Materi5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi5ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi5ItemStateChanged

    private void Materi6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi6ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi6ItemStateChanged

    private void Materi7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi7ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi7ItemStateChanged

    private void Materi8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_Materi8ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_Materi8ItemStateChanged

    private void AsuransiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AsuransiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_AsuransiKeyPressed

    private void PermasalahanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PermasalahanKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_PermasalahanKeyPressed

    private void ChkSemuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkSemuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkSemuaActionPerformed

    private void ChkRenogram19ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram19ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram19ItemStateChanged

    private void ChkRenogram20ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram20ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram20ItemStateChanged

    private void ChkRenogram21ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram21ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram21ItemStateChanged

    private void ChkRenogram22ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ChkRenogram22ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ChkRenogram22ItemStateChanged

    private void Tglre1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tglre1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tglre1KeyPressed

    private void Mengulangi1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Mengulangi1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Mengulangi1ActionPerformed

    private void Baca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Baca1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Baca1ActionPerformed

    private void Baca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Baca2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Baca2ActionPerformed

    private void Pend1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend1ActionPerformed

    private void Pend2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend2ActionPerformed

    private void Pend3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend3ActionPerformed

    private void Pend4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend4ActionPerformed

    private void Pend5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend5ActionPerformed

    private void Pend6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend6ActionPerformed

    private void Pend7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Pend7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Pend7ActionPerformed

    private void Isyarat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Isyarat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Isyarat1ActionPerformed

    private void Isyarat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Isyarat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Isyarat2ActionPerformed

    private void Bahasa1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bahasa1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bahasa1ActionPerformed

    private void Bahasa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bahasa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bahasa2ActionPerformed

    private void Bahasa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bahasa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bahasa3ActionPerformed

    private void Terjemah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Terjemah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Terjemah1ActionPerformed

    private void Terjemah2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Terjemah2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Terjemah2ActionPerformed

    private void Hambatan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan1ActionPerformed

    private void Hambatan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan2ActionPerformed

    private void Hambatan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan3ActionPerformed

    private void Hambatan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan4ActionPerformed

    private void Hambatan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan5ActionPerformed

    private void Hambatan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan6ActionPerformed

    private void Hambatan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan7ActionPerformed

    private void Hambatan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Hambatan8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Hambatan8ActionPerformed

    private void Cara1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cara1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara1ActionPerformed

    private void Cara2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cara2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara2ActionPerformed

    private void Cara3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cara3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara3ActionPerformed

    private void Cara4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cara4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara4ActionPerformed

    private void Cara5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cara5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara5ActionPerformed

    private void Cara6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cara6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cara6ActionPerformed

    private void Yakin1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Yakin1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Yakin1ActionPerformed

    private void Yakin2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Yakin2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Yakin2ActionPerformed

    private void Sedia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sedia1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sedia1ActionPerformed

    private void Sedia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sedia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sedia2ActionPerformed

    private void KetYakin2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetYakin2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetYakin2KeyPressed

    private void KetBahasa1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetBahasa1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetBahasa1KeyPressed

    private void KetBahasa2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetBahasa2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetBahasa2KeyPressed

    private void KetTerjemah2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetTerjemah2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_KetTerjemah2KeyPressed

    private void Bayar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bayar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bayar1ActionPerformed

    private void Bayar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bayar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bayar2ActionPerformed

    private void Bayar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bayar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Bayar3ActionPerformed

    private void Bayar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bayar4ActionPerformed

    }//GEN-LAST:event_Bayar4ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            SuratPersetujuanUmum dialog = new SuratPersetujuanUmum(new javax.swing.JFrame(), true);
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
    private widget.TextBox Alamat;
    private widget.TextBox Asuransi;
    private widget.RadioButton Baca1;
    private widget.RadioButton Baca2;
    private widget.RadioButton Bahasa1;
    private widget.RadioButton Bahasa2;
    private widget.RadioButton Bahasa3;
    private widget.RadioButton Bayar1;
    private widget.RadioButton Bayar2;
    private widget.RadioButton Bayar3;
    private widget.RadioButton Bayar4;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnRefreshPhoto1;
    private widget.Button BtnSimpan;
    private widget.RadioButton Cara1;
    private widget.RadioButton Cara2;
    private widget.RadioButton Cara3;
    private widget.RadioButton Cara4;
    private widget.RadioButton Cara5;
    private widget.RadioButton Cara6;
    private widget.CekBox ChkAccor;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkRenogram19;
    private widget.CekBox ChkRenogram20;
    private widget.CekBox ChkRenogram21;
    private widget.CekBox ChkRenogram22;
    private widget.CekBox ChkSemua;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.PanelBiasa FormInput;
    private widget.PanelBiasa FormPass3;
    private widget.PanelBiasa FormPhoto;
    private widget.RadioButton Hambatan1;
    private widget.RadioButton Hambatan2;
    private widget.RadioButton Hambatan3;
    private widget.RadioButton Hambatan4;
    private widget.RadioButton Hambatan5;
    private widget.RadioButton Hambatan6;
    private widget.RadioButton Hambatan7;
    private widget.RadioButton Hambatan8;
    private widget.TextBox HubPasien;
    private widget.RadioButton Isyarat1;
    private widget.RadioButton Isyarat2;
    private widget.TextBox KetBahasa1;
    private widget.TextBox KetBahasa2;
    private widget.TextBox KetTerjemah2;
    private widget.TextBox KetYakin2;
    private widget.Label LCount;
    private widget.TextBox LahirPasien;
    private widget.editorpane LoadHTML2;
    private widget.CekBox Materi1;
    private widget.CekBox Materi2;
    private widget.CekBox Materi3;
    private widget.CekBox Materi4;
    private widget.CekBox Materi5;
    private widget.CekBox Materi6;
    private widget.CekBox Materi7;
    private widget.CekBox Materi8;
    private widget.CekBox Materi9;
    private widget.RadioButton Mengerti1;
    private widget.RadioButton Mengulangi1;
    private widget.RadioButton Mengulangi2;
    private widget.TextBox NIP;
    private widget.TextBox NamaPJ;
    private widget.TextBox NamaPetugas;
    private widget.TextBox NoSurat;
    private widget.TextBox NoTelp;
    private widget.PanelBiasa PanelAccor;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton Pend1;
    private widget.RadioButton Pend2;
    private widget.RadioButton Pend3;
    private widget.RadioButton Pend4;
    private widget.RadioButton Pend5;
    private widget.RadioButton Pend6;
    private widget.RadioButton Pend7;
    private widget.TextArea Permasalahan;
    private widget.RadioButton Redemonstrasi1;
    private widget.RadioButton Reedukasi1;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll5;
    private widget.RadioButton Sedia1;
    private widget.RadioButton Sedia2;
    private javax.swing.JMenuItem SuratPersetujuanUmum;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextArea Tambahan;
    private widget.Tanggal Tanggal;
    private widget.RadioButton Tanggalre1;
    private widget.RadioButton Terjemah1;
    private widget.RadioButton Terjemah2;
    private widget.TextBox Tglre1;
    private widget.RadioButton Tidak1;
    private widget.RadioButton Yakin1;
    private widget.RadioButton Yakin2;
    private widget.Button btnAmbil;
    private widget.Button btnAmbil1;
    private widget.Button btnPetugas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup10;
    private javax.swing.ButtonGroup buttonGroup11;
    private javax.swing.ButtonGroup buttonGroup12;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.ButtonGroup buttonGroup9;
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
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel27;
    private widget.Label jLabel28;
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
    private widget.Label jLabel49;
    private widget.Label jLabel50;
    private widget.Label jLabel51;
    private widget.Label jLabel52;
    private widget.Label jLabel57;
    private widget.Label jLabel58;
    private widget.Label jLabel59;
    private widget.Label jLabel6;
    private widget.Label jLabel60;
    private widget.Label jLabel7;
    private widget.Label jLabel8;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane1;
    private widget.ScrollPane scrollPane2;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            if(TCari.getText().trim().equals("")){
                ps=koneksi.prepareStatement(
                    "select spu.no_surat,rp.no_rawat,p.no_rkm_medis,p.nm_pasien,rp.umurdaftar,rp.sttsumur,p.jk,p.tgl_lahir,spu.tanggal,spu.jam,\n" +
                    "spu.nilai_kepercayaan,spu.nama_pj,spu.umur_pj,spu.no_ktppj,spu.jkpj,spu.bertindak_atas,spu.no_telp,spu.membaca,spu.pendidikan,spu.keyakinan,\n" +
                    "spu.bahasa,spu.bahasa2,spu.penerjemah,spu.hambatan,spu.belajar,spu.bersedia,spu.materi_1,spu.materi_2,spu.materi_3,spu.materi_4,spu.materi_5,\n" +
                    "spu.materi_6,spu.materi_7,spu.materi_8,spu.materi_9,spu.materi_10,spu.penjelasan,spu.diskusi,spu.demonstrasi,\n" +
                    "spu.praktek,spu.tujuan,spu.evaluasi,spu.nip,\n" +
                    "p2.nama,IF(spupp.photo is null,\"\",\"Sudah\") as status \n" +
                    "from surat_persetujuan_umum spu\n" +
                    "inner join reg_periksa rp on spu.no_rawat=rp.no_rawat \n" +
                    "inner join pasien p on rp.no_rkm_medis=p.no_rkm_medis \n" +
                    "inner join petugas p2 on spu.nip=p2.nip \n" +
                    "left join surat_persetujuan_umum_pembuat_pernyataan spupp on spupp.no_surat=spu.no_surat where \n" +
                    "spu.tanggal between ? and ? order by spu.tanggal");
            }else{
                ps=koneksi.prepareStatement(
                    "select spu.no_surat,rp.no_rawat,p.no_rkm_medis,p.nm_pasien,rp.umurdaftar,rp.sttsumur,p.jk,p.tgl_lahir,spu.tanggal,spu.jam,\n" +
                    "spu.nilai_kepercayaan,spu.nama_pj,spu.umur_pj,spu.no_ktppj,spu.jkpj,spu.bertindak_atas,spu.no_telp,spu.membaca,spu.pendidikan,spu.keyakinan,\n" +
                    "spu.bahasa,spu.bahasa2,spu.penerjemah,spu.hambatan,spu.belajar,spu.bersedia,spu.materi_1,spu.materi_2,spu.materi_3,spu.materi_4,spu.materi_5,\n" +
                    "spu.materi_6,spu.materi_7,spu.materi_8,spu.materi_9,spu.materi_10,spu.penjelasan,spu.diskusi,spu.demonstrasi,\n" +
                    "spu.praktek,spu.tujuan,spu.evaluasi,spu.nip,\n" +
                    "p2.nama,IF(spupp.photo is null,\"\",\"Sudah\") as status \n" +
                    "from surat_persetujuan_umum spu\n" +
                    "inner join reg_periksa rp on spu.no_rawat=rp.no_rawat \n" +
                    "inner join pasien p on rp.no_rkm_medis=p.no_rkm_medis \n" +
                    "inner join petugas p2 on spu.nip=p2.nip \n" +
                    "left join surat_persetujuan_umum_pembuat_pernyataan spupp on spupp.no_surat=spu.no_surat where \n" +
                    "spu.tanggal between ? and ? and "+
                    "(rp.no_rawat like ? or p.no_rkm_medis like ? or p.nm_pasien like ? or "+
                    "spu.no_telp like ? or spu.nama_pj like ? or "+
                    "spu.nip like ? or p2.nama like ?) "+
                    "order by spu.tanggal");
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
                        rs.getString("umurdaftar")+" "+rs.getString("sttsumur"),rs.getString("jk"),rs.getString("tgl_lahir"),
                        rs.getString("tanggal"),rs.getString("jam"),rs.getString("nilai_kepercayaan"),rs.getString("nama_pj"),
                        rs.getString("umur_pj"),rs.getString("no_ktppj"),rs.getString("jkpj"),rs.getString("no_telp"),
                        rs.getString("bertindak_atas"),rs.getString("nip"),rs.getString("nama"),rs.getString("status"),
                        rs.getString("membaca"),rs.getString("pendidikan"),rs.getString("keyakinan"),rs.getString("bahasa"),
                        rs.getString("bahasa2"),rs.getString("penerjemah"),rs.getString("hambatan"),rs.getString("belajar"),
                        rs.getString("bersedia"),rs.getString("materi_1"),rs.getString("materi_2"),rs.getString("materi_3"),
                        rs.getString("materi_4"),rs.getString("materi_5"),rs.getString("materi_6"),rs.getString("materi_7"),
                        rs.getString("materi_8"),rs.getString("materi_9"),rs.getString("materi_10"),rs.getString("penjelasan"),
                        rs.getString("diskusi"),rs.getString("demonstrasi"),rs.getString("praktek"),rs.getString("tujuan"),
                        rs.getString("evaluasi")
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
        HubPasien.setText("");
        NoTelp.setText("");
        Alamat.setText("");
        Valid.autoNomer3("select ifnull(MAX(CONVERT(RIGHT(surat_persetujuan_umum.no_surat,3),signed)),0) from surat_persetujuan_umum where surat_persetujuan_umum.tanggal='"+Valid.SetTgl(Tanggal.getSelectedItem()+"")+"' ",
                "PSU"+Tanggal.getSelectedItem().toString().substring(6,10)+Tanggal.getSelectedItem().toString().substring(3,5)+Tanggal.getSelectedItem().toString().substring(0,2),3,NoSurat);
        NoTelp.requestFocus();
    }

 
    private void getData() {
         if(tbObat.getSelectedRow()!= -1){
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            LahirPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());
            Valid.SetTgl(Tanggal,tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            Alamat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());
            NamaPJ.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());
            HubPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            NoTelp.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            String getMembaca = tbObat.getValueAt(tbObat.getSelectedRow(), 19).toString();
            buttonGroup3.clearSelection();
            switch (getMembaca) {
                case "Bisa membaca":                  Baca1.setSelected(true); break;
                case "Tidak bisa membaca/buta huruf": Baca2.setSelected(true); break;
            }
            String getPendidikan = tbObat.getValueAt(tbObat.getSelectedRow(), 20).toString();
            buttonGroup4.clearSelection();
            switch (getPendidikan) {
                case "SD":            Pend1.setSelected(true); break;
                case "SMP":           Pend2.setSelected(true); break;
                case "SMA":           Pend3.setSelected(true); break;
                case "Tidak sekolah": Pend4.setSelected(true); break;
                case "Akademi":       Pend5.setSelected(true); break;
                case "Sarjana":       Pend6.setSelected(true); break;
                case "Pascasarjana":  Pend7.setSelected(true); break;
            }
            String getYakin = tbObat.getValueAt(tbObat.getSelectedRow(), 21).toString();
            buttonGroup10.clearSelection();
            KetYakin2.setText("");
            if (getYakin.equals("Tidak")) {
                Yakin1.setSelected(true);
            } else if (getYakin.startsWith("Ada, Jelaskan")) {
                Yakin2.setSelected(true);
                KetYakin2.setText(getYakin.substring(13).trim());
            }
            String getIsyarat = tbObat.getValueAt(tbObat.getSelectedRow(), 22).toString();
            buttonGroup5.clearSelection();
            switch (getIsyarat) {
                case "Tidak":            Isyarat1.setSelected(true); break;
                case "Ya":               Isyarat2.setSelected(true); break;
            }
            String getBahasa = tbObat.getValueAt(tbObat.getSelectedRow(), 23).toString();
            buttonGroup6.clearSelection();
            KetBahasa1.setText("");
            KetBahasa2.setText("");
            if (getBahasa.startsWith("Daerah")) {
                Bahasa1.setSelected(true);
                KetBahasa1.setText(getBahasa.substring(7).trim());
            } else if (getBahasa.startsWith("Asing")) {
                Bahasa2.setSelected(true);
                KetBahasa2.setText(getBahasa.substring(6).trim());
            } else if (getBahasa.equals("Indonesia")) {
                Bahasa3.setSelected(true);
            }
            String getPenerjemah = tbObat.getValueAt(tbObat.getSelectedRow(), 24).toString();
            buttonGroup7.clearSelection();
            if (getPenerjemah.equals("Tidak")) {
                Terjemah1.setSelected(true);
            } else if (getPenerjemah.startsWith("Ya, bahasa")) {
                Terjemah2.setSelected(true);
                KetTerjemah2.setText(getPenerjemah.substring(11).trim());
            }
            String getHambatan = tbObat.getValueAt(tbObat.getSelectedRow(), 25).toString();
            buttonGroup8.clearSelection();
            if (getHambatan.equals("Marah")) {
                Hambatan1.setSelected(true);
            } else if (getHambatan.equals("Cemas")) {
                Hambatan2.setSelected(true);
            } else if (getHambatan.equals("Masalah pendengaran")) {
                Hambatan3.setSelected(true);
            } else if (getHambatan.equals("Gangguan bicara")) {
                Hambatan4.setSelected(true);
            } else if (getHambatan.equals("Masalah penglihatan")) {
                Hambatan5.setSelected(true);
            } else if (getHambatan.equals("Tidak ada motivasi belajar")) {
                Hambatan6.setSelected(true);
            } else if (getHambatan.equals("Tidak ditemukan hambatan belajar")) {
                Hambatan7.setSelected(true);
            } else if (getHambatan.equals("Secara fisiologis tidak dapat belajar")) {
                Hambatan8.setSelected(true);
            }
            String getCara = tbObat.getValueAt(tbObat.getSelectedRow(), 26).toString();
            buttonGroup9.clearSelection();
            if (getCara.equals("Menulis")) {
                Cara1.setSelected(true);
            } else if (getCara.equals("Demonstrasi")) {
                Cara2.setSelected(true);
            } else if (getCara.equals("Mendengar")) {
                Cara3.setSelected(true);
            } else if (getCara.equals("Diskusi")) {
                Cara4.setSelected(true);
            } else if (getCara.equals("Membaca")) {
                Cara5.setSelected(true);
            } else if (getCara.equals("Audio visual")) {
                Cara6.setSelected(true);
            }
            String getSedia = tbObat.getValueAt(tbObat.getSelectedRow(), 27).toString();
            buttonGroup11.clearSelection();
            switch (getSedia) {
                case "Tidak":            Sedia1.setSelected(true); break;
                case "Ya":               Sedia2.setSelected(true); break;
            }
         }
    }

    private void isRawat() {
         Sequel.cariIsi("select reg_periksa.no_rkm_medis from reg_periksa where reg_periksa.no_rawat='"+TNoRw.getText()+"' ",TNoRM);
    }

    private void isPsien() {
       Sequel.cariIsi("select pasien.nm_pasien from pasien where pasien.no_rkm_medis='"+TNoRM.getText()+"' ",TPasien);
       Sequel.cariIsi("select date_format(pasien.tgl_lahir,'%d-%m-%Y') from pasien where pasien.no_rkm_medis=? ",LahirPasien,TNoRM.getText());
       Sequel.cariIsi("select reg_periksa.p_jawab from reg_periksa where reg_periksa.no_rawat=? ",NamaPJ,TNoRw.getText());
       Sequel.cariIsi("select reg_periksa.hubunganpj from reg_periksa where reg_periksa.no_rawat=? ",HubPasien,TNoRw.getText());
       Sequel.cariIsi("select reg_periksa.almt_pj from reg_periksa where reg_periksa.no_rawat=? ",Alamat,TNoRw.getText());
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
        BtnSimpan.setEnabled(akses.getsurat_persetujuan_umum());
        BtnHapus.setEnabled(akses.getsurat_persetujuan_umum());
        BtnEdit.setEnabled(akses.getsurat_persetujuan_umum());
        BtnPrint.setEnabled(akses.getsurat_persetujuan_umum()); 
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
        Sequel.mengedit("surat_persetujuan_umum","no_surat=?","no_surat=?,no_rawat=?,tanggal=?,nilai_kepercayaan=?,nama_pj=?,bertindak_atas=?,no_telp=?,nip=?",9,new String[]{
            NoSurat.getText(),TNoRw.getText(),Valid.SetTgl(Tanggal.getSelectedItem()+""),Alamat.getText(),NamaPJ.getText(),
            HubPasien.getText(),NoTelp.getText(),NIP.getText(),tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        });
        if(tabMode.getRowCount()!=0){tampil();}
        emptTeks();
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from surat_persetujuan_umum where no_surat=?",1,new String[]{
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

    private void panggilPhoto() {
        if(FormPhoto.isVisible()==true){
            try {
                ps=koneksi.prepareStatement("select surat_persetujuan_umum_pembuat_pernyataan.photo from surat_persetujuan_umum_pembuat_pernyataan where surat_persetujuan_umum_pembuat_pernyataan.no_surat=?");
                try {
                    ps.setString(1,tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("photo").equals("")||rs.getString("photo").equals("-")){
                            LoadHTML2.setText("<html><body><center><br><br><font face='tahoma' size='2' color='#434343'>Kosong</font></center></body></html>");
                        }else{
                            LoadHTML2.setText("<html><body><center><img src='http://"+koneksiDB.HOSTHYBRIDWEB()+":"+koneksiDB.PORTWEB()+"/"+koneksiDB.HYBRIDWEB()+"/persetujuanumum/"+rs.getString("photo")+"' alt='photo' width='500' height='500'/></center></body></html>");
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



