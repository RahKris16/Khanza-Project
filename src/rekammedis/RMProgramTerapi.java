/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rekammedis;

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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariDokter;
import kepegawaian.DlgCariPegawai;
import laporan.DlgBerkasRawat;
import laporan.DlgDiagnosaPenyakit;
import simrskhanza.DlgTanggalJamKontrolCustom;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;


/**
 *
 * @author perpustakaan
 */
public final class RMProgramTerapi extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps,ps2,psJadwal;
    private ResultSet rs,rs2,rsJadwal;
    private int i=0;    
    private DlgCariPegawai dokter=new DlgCariPegawai(null,false);
    private RMCariHasilLaborat2 carilaborat=new RMCariHasilLaborat2(null,false);
    private String kodekamar="",namakamar="",tglkeluar="",jamkeluar="",finger="",finger2="",tanggal="",tglKontrolOtomatis="",
            NoSKDP="",NoSuratKontrol="";
    private static final String KODE_DOKTER_FORM_INI = "B17102226";
    
    /** Creates new form DlgRujuk
     * @param parent
     * @param modal */
    public RMProgramTerapi(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        tabMode=new DefaultTableModel(null,new Object[]{
            "No.Rawat","No.RM","Nama Pasien","NIP","Fisioterapis","Program Ke","Ket. Subjective","Tensi","Suhu","Nadi",
            "Respirasi","Kesadaran","Objective","Assesment","Plan","Tanggal","Tgl Konfirmasi","Jam Konfirmasi"
        }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 18; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(55);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==4){
                column.setPreferredWidth(250);
            }else if(i==5){
                column.setPreferredWidth(100);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(60);
            }else if(i==8){
                column.setPreferredWidth(60);
            }else if(i==9){
                column.setPreferredWidth(60);
            }else if(i==10){
                column.setPreferredWidth(60);
            }else if(i==11){
                column.setPreferredWidth(150);
            }else if(i==12){
                column.setPreferredWidth(200);
            }else if(i==13){
                column.setPreferredWidth(200);
            }else if(i==14){
                column.setPreferredWidth(200);
            }else if(i==15){
                column.setPreferredWidth(80);
            }else if(i==16){
                column.setPreferredWidth(100);
            }else if(i==17){
                column.setPreferredWidth(100);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());

        TNoRw.setDocument(new batasInput((byte)17).getKata(TNoRw));
        Program.setDocument(new batasInput((int)15).getKata(Program));
        KetSubjective.setDocument(new batasInput((int)2000).getKata(KetSubjective));
        Tensi.setDocument(new batasInput((int)15).getKata(Tensi));
        Suhu.setDocument(new batasInput((int)15).getKata(Suhu));
        Nadi.setDocument(new batasInput((int)15).getKata(Nadi));
        Respi.setDocument(new batasInput((int)15).getKata(Respi));
        Objective.setDocument(new batasInput((int)2000).getKata(Objective));
        Assesment.setDocument(new batasInput((int)2000).getKata(Assesment));
        Plan.setDocument(new batasInput((int)2000).getKata(Plan));
        
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
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
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){
                    KodeDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),0).toString());
                    NamaDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    KodeDokter.requestFocus();
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
        
        carilaborat.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(carilaborat.getTable().getSelectedRow()!= -1){
                    Objective.append(carilaborat.getTable().getValueAt(carilaborat.getTable().getSelectedRow(),1).toString()+",\n");
                    Objective.requestFocus();
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
        
        ChkInput.setSelected(false);
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

        KdDokter = new widget.TextBox();
        NmDokter = new widget.TextBox();
        KdPoli = new widget.TextBox();
        KdPoli1 = new widget.TextBox();
        NmPoli = new widget.TextBox();
        NoSEP = new widget.TextBox();
        NoRujukan = new widget.TextBox();
        TglRujukan = new widget.TextBox();
        SttsRujukan = new widget.TextBox();
        NoSEP1 = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnLaporanResume = new javax.swing.JMenuItem();
        MnSKDP = new javax.swing.JMenuItem();
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
        BtnSimpan1 = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        panelGlass9 = new widget.panelisi();
        jLabel19 = new widget.Label();
        DTPCari1 = new widget.Tanggal();
        jLabel21 = new widget.Label();
        DTPCari2 = new widget.Tanggal();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        scrollInput = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        TNoRw = new widget.TextBox();
        TPasien = new widget.TextBox();
        TNoRM = new widget.TextBox();
        scrollPane2 = new widget.ScrollPane();
        KetSubjective = new widget.TextArea();
        jLabel5 = new widget.Label();
        jLabel16 = new widget.Label();
        jLabel24 = new widget.Label();
        Program = new widget.TextBox();
        scrollPane7 = new widget.ScrollPane();
        Objective = new widget.TextArea();
        scrollPane8 = new widget.ScrollPane();
        Plan = new widget.TextArea();
        BtnDokter19 = new widget.Button();
        scrollPane12 = new widget.ScrollPane();
        Assesment = new widget.TextArea();
        jLabel18 = new widget.Label();
        Tensi = new widget.TextBox();
        jLabel22 = new widget.Label();
        jLabel44 = new widget.Label();
        jLabel45 = new widget.Label();
        jLabel46 = new widget.Label();
        jLabel47 = new widget.Label();
        Suhu = new widget.TextBox();
        Nadi = new widget.TextBox();
        Respi = new widget.TextBox();
        Kesadaran = new widget.ComboBox();
        label14 = new widget.Label();
        KodeDokter = new widget.TextBox();
        NamaDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel20 = new widget.Label();
        jLabel23 = new widget.Label();
        jLabel25 = new widget.Label();
        Program1 = new widget.TextBox();
        DTPTgl = new widget.Tanggal();
        jLabel26 = new widget.Label();
        BtnEdit10 = new widget.Button();
        ChkKontrol = new widget.CekBox();
        jLabel58 = new widget.Label();

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N

        NmDokter.setEditable(false);
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N

        KdPoli1.setEditable(false);
        KdPoli1.setHighlighter(null);
        KdPoli1.setName("KdPoli1"); // NOI18N

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N

        NoSEP.setEditable(false);
        NoSEP.setHighlighter(null);
        NoSEP.setName("NoSEP"); // NOI18N

        NoRujukan.setEditable(false);
        NoRujukan.setHighlighter(null);
        NoRujukan.setName("NoRujukan"); // NOI18N

        TglRujukan.setEditable(false);
        TglRujukan.setHighlighter(null);
        TglRujukan.setName("TglRujukan"); // NOI18N

        SttsRujukan.setEditable(false);
        SttsRujukan.setHighlighter(null);
        SttsRujukan.setName("SttsRujukan"); // NOI18N

        NoSEP1.setEditable(false);
        NoSEP1.setHighlighter(null);
        NoSEP1.setName("NoSEP1"); // NOI18N

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnLaporanResume.setBackground(new java.awt.Color(255, 255, 254));
        MnLaporanResume.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnLaporanResume.setForeground(new java.awt.Color(50, 50, 50));
        MnLaporanResume.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnLaporanResume.setText("Laporan Program Terapi");
        MnLaporanResume.setName("MnLaporanResume"); // NOI18N
        MnLaporanResume.setPreferredSize(new java.awt.Dimension(220, 26));
        MnLaporanResume.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnLaporanResumeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnLaporanResume);

        MnSKDP.setBackground(new java.awt.Color(255, 255, 254));
        MnSKDP.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSKDP.setForeground(new java.awt.Color(50, 50, 50));
        MnSKDP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSKDP.setText("Cetak SKDP");
        MnSKDP.setName("MnSKDP"); // NOI18N
        MnSKDP.setPreferredSize(new java.awt.Dimension(220, 26));
        MnSKDP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSKDPActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSKDP);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Form Program Terapi ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbObatKeyPressed(evt);
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

        BtnSimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan1.setMnemonic('S');
        BtnSimpan1.setText("Konfirmasi");
        BtnSimpan1.setToolTipText("Alt+S");
        BtnSimpan1.setName("BtnSimpan1"); // NOI18N
        BtnSimpan1.setPreferredSize(new java.awt.Dimension(120, 30));
        BtnSimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpan1ActionPerformed(evt);
            }
        });
        BtnSimpan1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpan1KeyPressed(evt);
            }
        });
        panelGlass8.add(BtnSimpan1);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 23));
        panelGlass8.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass8.add(LCount);

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

        jLabel19.setText("Tgl.Rawat :");
        jLabel19.setName("jLabel19"); // NOI18N
        jLabel19.setPreferredSize(new java.awt.Dimension(67, 23));
        panelGlass9.add(jLabel19);

        DTPCari1.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-08-2026" }));
        DTPCari1.setDisplayFormat("dd-MM-yyyy");
        DTPCari1.setName("DTPCari1"); // NOI18N
        DTPCari1.setOpaque(false);
        DTPCari1.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari1);

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("s.d.");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(23, 23));
        panelGlass9.add(jLabel21);

        DTPCari2.setForeground(new java.awt.Color(50, 70, 50));
        DTPCari2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-08-2026" }));
        DTPCari2.setDisplayFormat("dd-MM-yyyy");
        DTPCari2.setName("DTPCari2"); // NOI18N
        DTPCari2.setOpaque(false);
        DTPCari2.setPreferredSize(new java.awt.Dimension(95, 23));
        panelGlass9.add(DTPCari2);

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 23));
        panelGlass9.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(310, 23));
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
        panelGlass9.add(BtnAll);

        jPanel3.add(panelGlass9, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 448));
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
        scrollInput.setPreferredSize(new java.awt.Dimension(102, 402));

        FormInput.setBackground(new java.awt.Color(250, 255, 245));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(100, 400));
        FormInput.setLayout(null);

        TNoRw.setHighlighter(null);
        TNoRw.setName("TNoRw"); // NOI18N
        TNoRw.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRwKeyPressed(evt);
            }
        });
        FormInput.add(TNoRw);
        TNoRw.setBounds(104, 15, 141, 23);

        TPasien.setEditable(false);
        TPasien.setHighlighter(null);
        TPasien.setName("TPasien"); // NOI18N
        TPasien.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TPasienKeyPressed(evt);
            }
        });
        FormInput.add(TPasien);
        TPasien.setBounds(348, 15, 328, 23);

        TNoRM.setEditable(false);
        TNoRM.setHighlighter(null);
        TNoRM.setName("TNoRM"); // NOI18N
        TNoRM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TNoRMKeyPressed(evt);
            }
        });
        FormInput.add(TNoRM);
        TNoRM.setBounds(247, 15, 100, 23);

        scrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane2.setName("scrollPane2"); // NOI18N

        KetSubjective.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        KetSubjective.setColumns(20);
        KetSubjective.setRows(5);
        KetSubjective.setName("KetSubjective"); // NOI18N
        KetSubjective.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KetSubjectiveKeyPressed(evt);
            }
        });
        scrollPane2.setViewportView(KetSubjective);

        FormInput.add(scrollPane2);
        scrollPane2.setBounds(104, 113, 561, 50);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 15, 100, 23);

        jLabel16.setText("Subjective :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(0, 110, 100, 23);

        jLabel24.setText("Program Ke :");
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(10, 83, 90, 23);

        Program.setHighlighter(null);
        Program.setName("Program"); // NOI18N
        Program.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ProgramKeyPressed(evt);
            }
        });
        FormInput.add(Program);
        Program.setBounds(104, 83, 70, 23);

        scrollPane7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane7.setName("scrollPane7"); // NOI18N

        Objective.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Objective.setColumns(20);
        Objective.setRows(5);
        Objective.setName("Objective"); // NOI18N
        Objective.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ObjectiveKeyPressed(evt);
            }
        });
        scrollPane7.setViewportView(Objective);

        FormInput.add(scrollPane7);
        scrollPane7.setBounds(104, 226, 561, 50);

        scrollPane8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane8.setName("scrollPane8"); // NOI18N

        Plan.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Plan.setColumns(20);
        Plan.setRows(5);
        Plan.setName("Plan"); // NOI18N
        Plan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PlanKeyPressed(evt);
            }
        });
        scrollPane8.setViewportView(Plan);

        FormInput.add(scrollPane8);
        scrollPane8.setBounds(104, 342, 560, 50);

        BtnDokter19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter19.setMnemonic('2');
        BtnDokter19.setToolTipText("Alt+2");
        BtnDokter19.setName("BtnDokter19"); // NOI18N
        BtnDokter19.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokter19ActionPerformed(evt);
            }
        });
        FormInput.add(BtnDokter19);
        BtnDokter19.setBounds(70, 240, 28, 23);

        scrollPane12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        scrollPane12.setName("scrollPane12"); // NOI18N

        Assesment.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        Assesment.setColumns(20);
        Assesment.setRows(5);
        Assesment.setName("Assesment"); // NOI18N
        Assesment.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                AssesmentKeyPressed(evt);
            }
        });
        scrollPane12.setViewportView(Assesment);

        FormInput.add(scrollPane12);
        scrollPane12.setBounds(104, 283, 561, 50);

        jLabel18.setText("Assesment :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(10, 280, 90, 23);

        Tensi.setHighlighter(null);
        Tensi.setName("Tensi"); // NOI18N
        Tensi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TensiKeyPressed(evt);
            }
        });
        FormInput.add(Tensi);
        Tensi.setBounds(178, 169, 60, 23);

        jLabel22.setText("Tensi :");
        jLabel22.setName("jLabel22"); // NOI18N
        FormInput.add(jLabel22);
        jLabel22.setBounds(104, 169, 70, 23);

        jLabel44.setText("Suhu (°C) :");
        jLabel44.setName("jLabel44"); // NOI18N
        FormInput.add(jLabel44);
        jLabel44.setBounds(104, 197, 70, 23);

        jLabel45.setText("Nadi (/menit) :");
        jLabel45.setName("jLabel45"); // NOI18N
        FormInput.add(jLabel45);
        jLabel45.setBounds(250, 169, 110, 23);

        jLabel46.setText("Respirasi (/menit) :");
        jLabel46.setName("jLabel46"); // NOI18N
        FormInput.add(jLabel46);
        jLabel46.setBounds(250, 197, 110, 23);

        jLabel47.setText("Kesadaran :");
        jLabel47.setName("jLabel47"); // NOI18N
        FormInput.add(jLabel47);
        jLabel47.setBounds(450, 169, 70, 23);

        Suhu.setHighlighter(null);
        Suhu.setName("Suhu"); // NOI18N
        Suhu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SuhuKeyPressed(evt);
            }
        });
        FormInput.add(Suhu);
        Suhu.setBounds(178, 197, 60, 23);

        Nadi.setHighlighter(null);
        Nadi.setName("Nadi"); // NOI18N
        Nadi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NadiKeyPressed(evt);
            }
        });
        FormInput.add(Nadi);
        Nadi.setBounds(362, 169, 60, 23);

        Respi.setHighlighter(null);
        Respi.setName("Respi"); // NOI18N
        Respi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                RespiKeyPressed(evt);
            }
        });
        FormInput.add(Respi);
        Respi.setBounds(362, 197, 60, 23);

        Kesadaran.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Compos Mentis", "Somnolence", "Sopor", "Coma" }));
        Kesadaran.setName("Kesadaran"); // NOI18N
        Kesadaran.setPreferredSize(new java.awt.Dimension(62, 28));
        Kesadaran.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KesadaranKeyPressed(evt);
            }
        });
        FormInput.add(Kesadaran);
        Kesadaran.setBounds(524, 169, 126, 23);

        label14.setText("Petugas :");
        label14.setName("label14"); // NOI18N
        label14.setPreferredSize(new java.awt.Dimension(70, 23));
        FormInput.add(label14);
        label14.setBounds(0, 50, 100, 23);

        KodeDokter.setEditable(false);
        KodeDokter.setName("KodeDokter"); // NOI18N
        KodeDokter.setPreferredSize(new java.awt.Dimension(80, 23));
        KodeDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeDokterKeyPressed(evt);
            }
        });
        FormInput.add(KodeDokter);
        KodeDokter.setBounds(104, 50, 100, 23);

        NamaDokter.setEditable(false);
        NamaDokter.setName("NamaDokter"); // NOI18N
        NamaDokter.setPreferredSize(new java.awt.Dimension(207, 23));
        FormInput.add(NamaDokter);
        NamaDokter.setBounds(206, 50, 272, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('2');
        BtnDokter.setToolTipText("Alt+2");
        BtnDokter.setName("BtnDokter"); // NOI18N
        BtnDokter.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnDokter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnDokterActionPerformed(evt);
            }
        });
        BtnDokter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnDokterKeyPressed(evt);
            }
        });
        FormInput.add(BtnDokter);
        BtnDokter.setBounds(480, 50, 28, 23);

        jLabel20.setText("Objective :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(10, 169, 90, 23);

        jLabel23.setText("Procedure :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(10, 341, 90, 23);

        jLabel25.setText("Program Sebelumnya :");
        jLabel25.setName("jLabel25"); // NOI18N
        FormInput.add(jLabel25);
        jLabel25.setBounds(180, 83, 130, 23);

        Program1.setEditable(false);
        Program1.setHighlighter(null);
        Program1.setName("Program1"); // NOI18N
        Program1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Program1KeyPressed(evt);
            }
        });
        FormInput.add(Program1);
        Program1.setBounds(312, 83, 70, 23);

        DTPTgl.setForeground(new java.awt.Color(50, 70, 50));
        DTPTgl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "26-08-2026" }));
        DTPTgl.setDisplayFormat("dd-MM-yyyy");
        DTPTgl.setName("DTPTgl"); // NOI18N
        DTPTgl.setOpaque(false);
        DTPTgl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTglKeyPressed(evt);
            }
        });
        FormInput.add(DTPTgl);
        DTPTgl.setBounds(585, 50, 90, 23);

        jLabel26.setText("Tanggal :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(520, 50, 60, 23);

        BtnEdit10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnEdit10.setMnemonic('G');
        BtnEdit10.setText("Custom");
        BtnEdit10.setToolTipText("Alt+G");
        BtnEdit10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        BtnEdit10.setName("BtnEdit10"); // NOI18N
        BtnEdit10.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEdit10ActionPerformed(evt);
            }
        });
        BtnEdit10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEdit10KeyPressed(evt);
            }
        });
        FormInput.add(BtnEdit10);
        BtnEdit10.setBounds(700, 115, 100, 30);

        ChkKontrol.setBorder(null);
        ChkKontrol.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        ChkKontrol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ChkKontrol.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ChkKontrol.setName("ChkKontrol"); // NOI18N
        FormInput.add(ChkKontrol);
        ChkKontrol.setBounds(705, 155, 23, 23);

        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Tidak Kontrol");
        jLabel58.setName("jLabel58"); // NOI18N
        FormInput.add(jLabel58);
        jLabel58.setBounds(730, 155, 90, 23);

        scrollInput.setViewportView(FormInput);

        PanelInput.add(scrollInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TNoRwKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRwKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            isRawat();
        }else{            
            Valid.pindah(evt,TCari,BtnDokter);
        }
}//GEN-LAST:event_TNoRwKeyPressed

    private void TPasienKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TPasienKeyPressed
        Valid.pindah(evt,TCari,BtnSimpan);
}//GEN-LAST:event_TPasienKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KodeDokter.getText().equals("")||NamaDokter.getText().equals("")){
            Valid.textKosong(BtnDokter,"Dokter Penanggung Jawab");
        }else{
            // Lewati pengecekan & warning surat kontrol jika:
            // 1) petugas menandai checkbox "Tidak Kontrol", ATAU
            // 2) kunjungan ini adalah "Rujuk Internal" (bukan "Jawaban Rujuk Internal",
            //    yang tetap mengikuti aturan/pengecekan seperti sebelumnya).
            boolean bypassSuratKontrol = ChkKontrol.isSelected();

            boolean perluSuratKontrol = !bypassSuratKontrol && Sequel.cariInteger(
                "SELECT COUNT(rp.no_rawat) FROM bridging_sep bs "
              + "INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat "
              + "LEFT JOIN skdp sd ON sd.no_rawat = rp.no_rawat\n"
              + "LEFT JOIN surat_kontrol skn ON skn.no_rawat = rp.no_rawat "
              + "WHERE rp.no_rawat=? AND rp.kd_pj='BPJ' "
              + "AND rp.kd_poli not in ('IGD','U0023','U0024','U0025','U0022','U0026','U0027','U0028',"
              + "'U0029','U0031','U0032','U0033','U0034','U0036','U0037','U0038','U0040') "
              + "AND sd.no_rawat IS NULL AND skn.no_rawat IS NULL",
                TNoRw.getText()) > 0;

            if (perluSuratKontrol) {
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan buat surat kontrol terlebih dahulu..!!!");
            } else {
                if(Sequel.menyimpantf("program_terapi","?,?,?,?,?,?,?,?,?,?,?,?,?","No.Rawat",13,new String[]{
                        TNoRw.getText(),KodeDokter.getText(),Program.getText(),KetSubjective.getText(),
                        Tensi.getText(),Suhu.getText(),Nadi.getText(),Respi.getText(),Kesadaran.getSelectedItem().toString(),Objective.getText(),
                        Assesment.getText(),Plan.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+"")
                    })==true){
                        tabMode.addRow(new String[]{
                        TNoRw.getText(),TNoRM.getText(),TPasien.getText(),KodeDokter.getText(),NamaDokter.getText(),Program.getText(),KetSubjective.getText(),
                        Tensi.getText(),Suhu.getText(),Nadi.getText(),Respi.getText(),Kesadaran.getSelectedItem().toString(),Objective.getText(),
                        Assesment.getText(),Plan.getText()
                        });
                        Sequel.menyimpan2("pemeriksaan_ralan","'"+TNoRw.getText()+"','"+Valid.SetTgl(DTPTgl.getSelectedItem()+"")+"',CURTIME(),'"+Suhu.getText()+"','"+Tensi.getText()+"','"+Nadi.getText()+
                        "','"+Respi.getText()+"','','','','','"+Kesadaran.getSelectedItem()+"','Program ke "+Program.getText()+"\\n"+KetSubjective.getText()+"','"+
                        Objective.getText()+"','','','','"+Assesment.getText()+"','"+Plan.getText()+"','','"+KodeDokter.getText()+"'","No.Rawat");
                        emptTeks();
                        LCount.setText(""+tabMode.getRowCount());
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Plan,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
        ChkInput.setSelected(true);
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
                if(KodeDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString())){
                    hapus();
                }else{
                    hapus();
                    //JOptionPane.showMessageDialog(null,"Hanya bisa dihapus oleh dokter yang bersangkutan..!!");
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
        if(TNoRw.getText().equals("")||TNoRM.getText().equals("")||TPasien.getText().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KodeDokter.getText().equals("")||NamaDokter.getText().equals("")){
            Valid.textKosong(BtnDokter,"Dokter Penanggung Jawab");
        }else if(KetSubjective.getText().equals("")){
            Valid.textKosong(KetSubjective,"Keluhan utama riwayat penyakit yang postif");
        }else{
            if(tbObat.getSelectedRow()>-1){
                if(akses.getkode().equals("Admin Utama")){
                    ganti();
                }else{
                    if(KodeDokter.getText().equals(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString())){
                        ganti();
                    }else{
                        JOptionPane.showMessageDialog(null,"Hanya bisa diganti oleh dokter yang bersangkutan..!!");
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
        dokter.dispose();
        carilaborat.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnKeluarActionPerformed(null);
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(! TCari.getText().trim().equals("")){
//            BtnCariActionPerformed(evt);
//        }
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//            Map<String, Object> param = new HashMap<>(); 
//            param.put("namars",akses.getnamars());
//            param.put("alamatrs",akses.getalamatrs());
//            param.put("kotars",akses.getkabupatenrs());
//            param.put("propinsirs",akses.getpropinsirs());
//            param.put("kontakrs",akses.getkontakrs());
//            param.put("emailrs",akses.getemailrs());   
//            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
//            Valid.MyReportqry("rptDataResumePasienRanap.jasper","report","::[ Data Resume Pasien ]::",
//                    "select reg_periksa.no_rawat,reg_periksa.no_rkm_medis,pasien.nm_pasien,resume_pasien_ranap.kd_dokter,dokter.nm_dokter,reg_periksa.kd_dokter as kodepengirim,pengirim.nm_dokter as pengirim,"+
//                    "reg_periksa.tgl_registrasi,reg_periksa.jam_reg,resume_pasien_ranap.diagnosa_awal,resume_pasien_ranap.alasan,resume_pasien_ranap.keluhan_utama,resume_pasien_ranap.pemeriksaan_fisik,"+
//                    "resume_pasien_ranap.jalannya_penyakit,resume_pasien_ranap.pemeriksaan_penunjang,resume_pasien_ranap.hasil_laborat,resume_pasien_ranap.tindakan_dan_operasi,resume_pasien_ranap.obat_di_rs,"+
//                    "resume_pasien_ranap.diagnosa_utama,resume_pasien_ranap.kd_diagnosa_utama,resume_pasien_ranap.diagnosa_sekunder,resume_pasien_ranap.kd_diagnosa_sekunder,resume_pasien_ranap.diagnosa_sekunder2,"+
//                    "resume_pasien_ranap.kd_diagnosa_sekunder2,resume_pasien_ranap.diagnosa_sekunder3,resume_pasien_ranap.kd_diagnosa_sekunder3,resume_pasien_ranap.diagnosa_sekunder4,"+
//                    "resume_pasien_ranap.kd_diagnosa_sekunder4,resume_pasien_ranap.prosedur_utama,resume_pasien_ranap.kd_prosedur_utama,resume_pasien_ranap.prosedur_sekunder,resume_pasien_ranap.kd_prosedur_sekunder,"+
//                    "resume_pasien_ranap.prosedur_sekunder2,resume_pasien_ranap.kd_prosedur_sekunder2,resume_pasien_ranap.prosedur_sekunder3,resume_pasien_ranap.kd_prosedur_sekunder3,resume_pasien_ranap.alergi,"+
//                    "resume_pasien_ranap.diet,resume_pasien_ranap.lab_belum,resume_pasien_ranap.edukasi,resume_pasien_ranap.cara_keluar,resume_pasien_ranap.ket_keluar,resume_pasien_ranap.keadaan,"+
//                    "resume_pasien_ranap.ket_keadaan,resume_pasien_ranap.dilanjutkan,resume_pasien_ranap.ket_dilanjutkan,resume_pasien_ranap.kontrol,resume_pasien_ranap.obat_pulang "+
//                    "from resume_pasien_ranap inner join reg_periksa on resume_pasien_ranap.no_rawat=reg_periksa.no_rawat inner join pasien on reg_periksa.no_rkm_medis=pasien.no_rkm_medis "+
//                    "inner join dokter on resume_pasien_ranap.kd_dokter=dokter.kd_dokter inner join dokter as pengirim on reg_periksa.kd_dokter=pengirim.kd_dokter "+
//                    "where reg_periksa.tgl_registrasi between '"+Valid.SetTgl(DTPCari1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPCari2.getSelectedItem()+"")+"' "+
//                    (TCari.getText().trim().equals("")?"":"and (reg_periksa.no_rkm_medis like '%"+TCari.getText().trim()+"%' or pasien.nm_pasien like '%"+TCari.getText().trim()+"%' or "+
//                    "resume_pasien_ranap.kd_dokter like '%"+TCari.getText().trim()+"%' or dokter.nm_dokter like '%"+TCari.getText().trim()+"%' or resume_pasien_ranap.keadaan like '%"+TCari.getText().trim()+"%' or "+
//                    "resume_pasien_ranap.kd_diagnosa_utama like '%"+TCari.getText().trim()+"%' or resume_pasien_ranap.diagnosa_utama like '%"+TCari.getText().trim()+"%' or "+
//                    "resume_pasien_ranap.prosedur_utama like '%"+TCari.getText().trim()+"%' or reg_periksa.no_rawat like '%"+TCari.getText().trim()+"%' or "+
//                    "resume_pasien_ranap.kd_prosedur_utama like '%"+TCari.getText().trim()+"%')")+"order by reg_periksa.tgl_registrasi,reg_periksa.status_lanjut",param);
//        }
//        this.setCursor(Cursor.getDefaultCursor());
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

    private void TNoRMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TNoRMKeyPressed
        // Valid.pindah(evt, TNm, BtnSimpan);
}//GEN-LAST:event_TNoRMKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

    private void tbObatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbObatKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }else if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                try {
                    ChkInput.setSelected(true);
                    isForm(); 
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
        }
}//GEN-LAST:event_tbObatKeyPressed

    private void KetSubjectiveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KetSubjectiveKeyPressed
        Valid.pindah2(evt,Program,Tensi);
    }//GEN-LAST:event_KetSubjectiveKeyPressed

    private void MnLaporanResumeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnLaporanResumeActionPerformed
        if(tbObat.getSelectedRow()>-1){
            Map<String, Object> param = new HashMap<>();    
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());   
            param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
            param.put("norawat",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            finger=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik='B17102226'");
            finger2=Sequel.cariIsi("select sha1(sidikjari.sidikjari) from sidikjari inner join pegawai on pegawai.id=sidikjari.id where pegawai.nik=?",tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            tanggal=Sequel.cariIsi("SELECT DATE_FORMAT(pt.tanggal, '%d-%m-%Y') FROM program_terapi pt WHERE pt.no_rawat =?",TNoRw.getText());
            param.put("tanggal",tanggal);
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh dr. I Komang Gede Dwi Maya Rustadi, Sp.KFR\nID B17102226\n"+tanggal); 
            param.put("finger2","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),4).toString()+"\nID "+(finger2.equals("")?tbObat.getValueAt(tbObat.getSelectedRow(),3).toString():finger2)+"\n"+tanggal); 
            
            try {
                ps=koneksi.prepareStatement("SELECT IF(pt.program='',pt.ket_subjective,CONCAT('Program ke : ',pt.program,'\\n',pt.ket_subjective)) as sub," +
                    "IF(pt.program='',pt.ket_subjective,CONCAT('Tensi: ',pt.tensi,', '))," +
                    "CONCAT(IF(pt.tensi='','',CONCAT('Tensi: ',pt.tensi,', '))," +
                    "IF(pt.suhu='','',CONCAT('Suhu: ',pt.suhu,'/°C, '))," +
                    "IF(pt.nadi='','',CONCAT('Nadi: ',pt.nadi,'/menit, '))," +
                    "IF(pt.respi='','',CONCAT('Respirasi: ',pt.respi,'/menit, '))," +
                    "IF(pt.kesadaran='','',CONCAT('Kesadaran: ',pt.kesadaran)),'\\n',pt.objective) as obj,pt.assesment,p.nama, " +
                    "IF(pt.plan='','',CONCAT(pt.plan)) as pro " +
                    "FROM program_terapi pt " +
                    "inner join pegawai p on p.nik=pt.kd_dokter " +
                    "WHERE pt.no_rawat=?");
                try {
                    ps.setString(1,TNoRw.getText());
                    rs=ps.executeQuery();
                    while(rs.next()){
                        param.put("sub",rs.getString("sub")); 
                        param.put("obj",rs.getString("obj")); 
                        param.put("ass",rs.getString("assesment")); 
                        param.put("pro",rs.getString("pro")); 
                        param.put("dokter",rs.getString("nama")); 
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
            
            // Modifikasi jika ada audit
//            try {
//                ps=koneksi.prepareStatement("SELECT count(no_rawat) from konfirmasi_program_terapi "+
//                    "WHERE no_rawat=?");
//                try {
//                    ps.setString(1,TNoRw.getText());
//                    rs=ps.executeQuery();
//                    while(rs.next()){
//                        if(rs.getString(1).equals("1")){
//                            Valid.MyReportqry("rptCetakProgramTerapi.jasper","report","::[ Form Program Terapi ]::",
//                                "SELECT p.no_rkm_medis ,p.nm_pasien ,DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') as tgl_lahir ,CONCAT(p.alamat,', ',k.nm_kel,', ',k2.nm_kec,', ',k3.nm_kab,', ',p2.nm_prop) as alamat " +
//                                "FROM pasien p inner join kelurahan k on k.kd_kel =p.kd_kel INNER join kecamatan k2 on k2.kd_kec =p.kd_kec inner join kabupaten k3 on k3.kd_kab =p.kd_kab inner join propinsi p2 on p2.kd_prop =p.kd_prop " +
//                                "WHERE p.no_rkm_medis='"+TNoRM.getText()+"'",param);
//                        }else{
//                            JOptionPane.showMessageDialog(null,"Maaf, Program Terapi belum di konfirmasi DPJP..!!!");
//                        }
//                    }
//                } catch (Exception e) {
//                    System.out.println("Notif : "+e);
//                } finally{
//                    if(rs!=null){
//                        rs.close();
//                    }
//                    if(ps!=null){
//                        ps.close();
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println("Notif : "+e);
//            }
            
            Valid.MyReportqry("rptCetakProgramTerapi.jasper","report","::[ Form Program Terapi ]::",
                "SELECT p.no_rkm_medis ,p.nm_pasien ,DATE_FORMAT(p.tgl_lahir, '%d-%m-%Y') as tgl_lahir ,CONCAT(p.alamat,', ',k.nm_kel,', ',k2.nm_kec,', ',k3.nm_kab,', ',p2.nm_prop) as alamat " +
                "FROM pasien p inner join kelurahan k on k.kd_kel =p.kd_kel INNER join kecamatan k2 on k2.kd_kec =p.kd_kec inner join kabupaten k3 on k3.kd_kab =p.kd_kab inner join propinsi p2 on p2.kd_prop =p.kd_prop " +
                "WHERE p.no_rkm_medis='"+TNoRM.getText()+"'",param);
        }
    }//GEN-LAST:event_MnLaporanResumeActionPerformed

    private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
        isForm();
    }//GEN-LAST:event_ChkInputActionPerformed

    private void ObjectiveKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ObjectiveKeyPressed
        Valid.pindah2(evt,Respi,Assesment);
    }//GEN-LAST:event_ObjectiveKeyPressed

    private void PlanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PlanKeyPressed
        Valid.pindah2(evt,Assesment,BtnSimpan);
    }//GEN-LAST:event_PlanKeyPressed

    private void BtnDokter19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokter19ActionPerformed
        if(TNoRw.getText().equals("")&&TNoRM.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Pasien masih kosong...!!!");
        }else{
            carilaborat.setNoRawat(TNoRw.getText());
            carilaborat.tampil();
            carilaborat.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
            carilaborat.setLocationRelativeTo(internalFrame1);
            carilaborat.setVisible(true);
        }
    }//GEN-LAST:event_BtnDokter19ActionPerformed

    private void ProgramKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProgramKeyPressed
        Valid.pindah(evt,NamaDokter,KetSubjective);
    }//GEN-LAST:event_ProgramKeyPressed

    private void AssesmentKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AssesmentKeyPressed
        Valid.pindah2(evt,Objective,Plan);
    }//GEN-LAST:event_AssesmentKeyPressed

    private void TensiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TensiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TensiKeyPressed

    private void SuhuKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SuhuKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_SuhuKeyPressed

    private void NadiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NadiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_NadiKeyPressed

    private void RespiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RespiKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_RespiKeyPressed

    private void KesadaranKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KesadaranKeyPressed
        Valid.pindah(evt,Respi,Objective);
    }//GEN-LAST:event_KesadaranKeyPressed

    private void KodeDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeDokterKeyPressed
        Valid.pindah(evt,TPasien,NamaDokter);
    }//GEN-LAST:event_KodeDokterKeyPressed

    private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
        dokter.emptTeks();
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }//GEN-LAST:event_BtnDokterActionPerformed

    private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
        Valid.pindah(evt,NamaDokter,Program);
    }//GEN-LAST:event_BtnDokterKeyPressed

    private void Program1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Program1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Program1KeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
        Valid.pindah(evt,BtnDokter,Program);
    }//GEN-LAST:event_DTPTglKeyPressed

    private void BtnSimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpan1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
        }else if(TNoRw.getText().trim().equals("")){
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih dulu dengan menklik data pada table...!!!");
            tbObat.requestFocus();
        }else{
            if(Sequel.cariInteger("select count(no_rawat) from program_terapi where no_rawat=?",TNoRw.getText())>0){
               if(Sequel.cariInteger("select count(no_rawat) from konfirmasi_program_terapi where no_rawat=?",TNoRw.getText())>0){
                    JOptionPane.showMessageDialog(null,"Maaf, Program Terapi sudah di konfirmasi..!!!");
                }else {
                    Sequel.menyimpan("konfirmasi_program_terapi","'"+TNoRw.getText()+"',CURDATE(),CURTIME()","No.Rawat");
                    tampil();
                } 
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Pasien belum melakukan Program Terapi..!!!");
            }
        }
    }//GEN-LAST:event_BtnSimpan1ActionPerformed

    private void BtnSimpan1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpan1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSimpan1KeyPressed

    private void BtnEdit10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEdit10ActionPerformed
        if(TNoRw.getText().trim().equals("")){
            Valid.textKosong(TNoRw,"Pasien");
        }else if(KodeDokter.getText().trim().equals("")){
            Valid.textKosong(KodeDokter,"Dokter");
        }else{
            DlgTanggalJamKontrolCustom dlgTglCustom = new DlgTanggalJamKontrolCustom(null, true);
            dlgTglCustom.setLocationRelativeTo(internalFrame1);
            dlgTglCustom.setVisible(true); // modal, kode di bawah baru jalan setelah dialog ditutup

            if(dlgTglCustom.isSimpanDiklik()){
                String tglPilihanCustom = dlgTglCustom.getTanggalTerpilih();
                String jamAwalCustom = dlgTglCustom.getJamTerpilih();
                if(tglPilihanCustom == null || tglPilihanCustom.trim().equals("")){
                    JOptionPane.showMessageDialog(null,"Tanggal kontrol tidak valid, silahkan coba lagi...!");
                }else{
                    prosesSuratKontrol(tglPilihanCustom, " ", jamAwalCustom);
                }
            }
        }
    }//GEN-LAST:event_BtnEdit10ActionPerformed

    private void BtnEdit10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEdit10KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnEdit10KeyPressed

    private void MnSKDPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSKDPActionPerformed
        if(tbObat.getSelectedRow()!= -1){
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Map<String, Object> param = new HashMap<>();  
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                finger="B17102226";
                param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh dr. I Komang Gede Dwi Maya Rustadi, Sp.KFR\nID B17102226\n"+tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
                Valid.MyReportqry("rptSuratKeteranganDalamPerawatan.jasper","report","::[ SKDP ]::",
                    "SELECT s.no_skdp,s.no_rawat,DATE_FORMAT(s.tanggal_surat,'%d-%m-%Y') as tanggal_surat,s.no_rkm_medis,p.nm_pasien,CONCAT(rp.umurdaftar,' ',rp.sttsumur) as umur,s.no_rujukan,\n" +
                    "DATE_FORMAT(bs.tglrujukan,'%d-%m-%Y') as tglrujukan,DATE_FORMAT(DATE_ADD(bs.tglrujukan, INTERVAL 90 DAY),'%d-%m-%Y') AS expired,DATE_FORMAT(s.tanggal_kontrol,'%d-%m-%Y') as tanggal_kontrol,\n" +
                    "s.diagnosa,s.rtl,s.kd_dokter,d.nm_dokter,IFNULL(jf.jam,\"\") as jam \n" +
                    "FROM skdp s\n" +
                    "inner join pasien p on p.no_rkm_medis=s.no_rkm_medis\n" +
                    "inner join reg_periksa rp on rp.no_rawat =s.no_rawat\n" +
                    "inner join bridging_sep bs on bs.no_rujukan =s.no_rujukan and bs.no_rawat =s.no_rawat\n" +
                    "inner join dokter d on d.kd_dokter =s.kd_dokter \n" +
                    "left join jadwal_fisio jf on jf.no_surat =s.no_skdp \n" +
                    "WHERE s.no_rawat='"+TNoRw.getText()+"'",param);
                this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih terlebih dulu data yang mau anda hapus...\n Klik data pada table untuk memilih data...!!!!");
        }
    }//GEN-LAST:event_MnSKDPActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            RMProgramTerapi dialog = new RMProgramTerapi(new javax.swing.JFrame(), true);
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
    private widget.TextArea Assesment;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnDokter19;
    private widget.Button BtnEdit;
    private widget.Button BtnEdit10;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Button BtnSimpan1;
    private widget.CekBox ChkInput;
    private widget.CekBox ChkKontrol;
    private widget.Tanggal DTPCari1;
    private widget.Tanggal DTPCari2;
    private widget.Tanggal DTPTgl;
    private widget.PanelBiasa FormInput;
    private widget.TextBox KdDokter;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPoli1;
    private widget.ComboBox Kesadaran;
    private widget.TextArea KetSubjective;
    private widget.TextBox KodeDokter;
    private widget.Label LCount;
    private javax.swing.JMenuItem MnLaporanResume;
    private javax.swing.JMenuItem MnSKDP;
    private widget.TextBox Nadi;
    private widget.TextBox NamaDokter;
    private widget.TextBox NmDokter;
    private widget.TextBox NmPoli;
    private widget.TextBox NoRujukan;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSEP1;
    private widget.TextArea Objective;
    private javax.swing.JPanel PanelInput;
    private widget.TextArea Plan;
    private widget.TextBox Program;
    private widget.TextBox Program1;
    private widget.TextBox Respi;
    private widget.ScrollPane Scroll;
    private widget.TextBox SttsRujukan;
    private widget.TextBox Suhu;
    private widget.TextBox TCari;
    private widget.TextBox TNoRM;
    private widget.TextBox TNoRw;
    private widget.TextBox TPasien;
    private widget.TextBox Tensi;
    private widget.TextBox TglRujukan;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel16;
    private widget.Label jLabel18;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.Label jLabel22;
    private widget.Label jLabel23;
    private widget.Label jLabel24;
    private widget.Label jLabel25;
    private widget.Label jLabel26;
    private widget.Label jLabel44;
    private widget.Label jLabel45;
    private widget.Label jLabel46;
    private widget.Label jLabel47;
    private widget.Label jLabel5;
    private widget.Label jLabel58;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label14;
    private widget.panelisi panelGlass8;
    private widget.panelisi panelGlass9;
    private widget.ScrollPane scrollInput;
    private widget.ScrollPane scrollPane12;
    private widget.ScrollPane scrollPane2;
    private widget.ScrollPane scrollPane7;
    private widget.ScrollPane scrollPane8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                "SELECT p2.nama ,rp.no_rkm_medis,p.nm_pasien,rk.no_rawat,rk.kd_dokter,rk.program ,rk.ket_subjective, " +
                "rk.tensi ,rk.suhu ,rk.nadi ,rk.respi ,rk.kesadaran ,rk.objective ,rk.assesment ,rk.plan,"+
                "rk.tanggal,ifnull(kpt.tanggal,'') as tgl_konfirm,ifnull(kpt.jam,'') as jam_konfirm " +
                "FROM reg_periksa rp " +
                "inner join program_terapi rk on rk.no_rawat =rp.no_rawat " +
                "inner join pasien p on p.no_rkm_medis =rp.no_rkm_medis " +
                "inner join pegawai p2 on p2.nik =rk.kd_dokter " +
                "left join konfirmasi_program_terapi kpt on kpt.no_rawat=rp.no_rawat " +
                "WHERE rp.tgl_registrasi BETWEEN ? and ? and rp.no_rawat like ? ");
            try {
                ps.setString(1,Valid.SetTgl(DTPCari1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(DTPCari2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText().trim()+"%");

                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new String[]{
                        rs.getString("no_rawat"),rs.getString("no_rkm_medis"),rs.getString("nm_pasien"),rs.getString("kd_dokter"),
                        rs.getString("nama"),rs.getString("program"),rs.getString("ket_subjective"),
                        rs.getString("tensi"),rs.getString("suhu"),rs.getString("nadi"),rs.getString("respi"),
                        rs.getString("kesadaran"),rs.getString("objective"),rs.getString("assesment"),rs.getString("plan"),
                        rs.getString("tanggal"),rs.getString("tgl_konfirm"),rs.getString("jam_konfirm")
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
        Program.setText("");
        KetSubjective.setText("");
        DTPTgl.setDate(new Date());
        Nadi.setText("");
        Suhu.setText("");
        Respi.setText("");
        Tensi.setText("");
        Kesadaran.setSelectedIndex(0);
        Objective.setText("");
        Assesment.setText("");
        Plan.setText("");
        ChkKontrol.setSelected(false);
    } 

    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            TNoRw.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());  
            TNoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());  
            TPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),2).toString());   
            Program.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());  
            KetSubjective.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString());  
            Tensi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());  
            Nadi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),8).toString());  
            Suhu.setText(tbObat.getValueAt(tbObat.getSelectedRow(),9).toString());  
            Respi.setText(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString());  
            Kesadaran.setSelectedItem(tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());  
            Objective.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()); 
            Assesment.setText(tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()); 
            Plan.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            Valid.SetTgl(DTPTgl,tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
        }
    }
    
    private void isRawat() {
        if(Sequel.cariInteger("SELECT rk.no_rawat FROM ralan_kfr rk WHERE rk.no_rawat=? ",TNoRw.getText())>0){
            try {
                ps=koneksi.prepareStatement(
                    "SELECT rk.no_rawat,rk.assesment,rk.got,rk.tindakan,rk.frekuensi,rk.evaluasi,rk.lama,rk.edukasi " +
                    "FROM ralan_kfr rk " +
                    "WHERE rk.no_rawat=? ");
                try {
                    ps.setString(1,TNoRw.getText());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        Assesment.setText(rs.getString("assesment"));
                        Plan.setText(rs.getString("tindakan")+'\n'+rs.getString("got")+'\n'+rs.getString("frekuensi")+'\n'+rs.getString("evaluasi")+'\n'+rs.getString("lama")+'\n'+rs.getString("edukasi"));    
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
        }else{
            try {
                ps=koneksi.prepareStatement(
                    "SELECT pt.objective,pt.assesment,pt.plan,pt.ket_subjective,pt.program " +
                    "FROM program_terapi pt " +
                    "inner join reg_periksa rp on rp.no_rawat =pt.no_rawat " +
                    "WHERE rp.no_rkm_medis =? " +
                    "ORDER BY rp.tgl_registrasi DESC limit 1 ");
                try {
                    ps.setString(1,TNoRM.getText());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        Objective.setText(rs.getString("objective"));
                        KetSubjective.setText(rs.getString("ket_subjective"));
                        Assesment.setText(rs.getString("assesment"));
                        Plan.setText(rs.getString("plan"));
                        Program1.setText(rs.getString("program"));
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
        
        try {
            ps=koneksi.prepareStatement(
                    "SELECT pt.tensi,pt.suhu,pt.nadi,pt.respi,pt.kesadaran " +
                    "FROM ralan_kfr pt " +
                    "WHERE pt.no_rawat=? ");
            try {
                ps.setString(1,TNoRw.getText());
                rs=ps.executeQuery();
                if(rs.next()){
                    Tensi.setText(rs.getString("tensi"));
                    Suhu.setText(rs.getString("suhu"));
                    Nadi.setText(rs.getString("nadi"));
                    Respi.setText(rs.getString("respi"));
                    Kesadaran.setSelectedItem(rs.getString("kesadaran"));
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
    
    public void setNoRm(String norwt,String norm,String pasien,Date tgl2) {
        ChkInput.setSelected(true);
        TNoRw.setText(norwt);
        TNoRM.setText(norm);
        TPasien.setText(pasien);
        TCari.setText(norwt);
        DTPCari2.setDate(tgl2);    
        isRawat();              
        isForm();
        KdDokter.setText(Sequel.cariIsi("SELECT bs.kddpjp from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
        NoSEP.setText(Sequel.cariIsi("SELECT bs.no_sep from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
        NoRujukan.setText(Sequel.cariIsi("SELECT bs.no_rujukan from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
        SttsRujukan.setText(Sequel.cariIsi("SELECT DISTINCT CASE WHEN DATEDIFF(CURDATE(), bs.tglrujukan) > 90 THEN 'Tidak Aktif' ELSE 'Aktif' END AS status_rujukan\n" +
            "FROM bridging_sep bs WHERE bs.no_rujukan = ?",NoRujukan.getText()));
        if(NoRujukan.getText().trim().length()>12 && NoRujukan.getText().trim().charAt(12)=='V'){
            NoSEP1.setText(Sequel.cariIsi("SELECT bs.no_sep FROM bridging_sep bs\n" +
                "INNER JOIN reg_periksa rp ON rp.no_rawat = bs.no_rawat\n" +
                "WHERE rp.no_rkm_medis = ? AND SUBSTRING(bs.no_rujukan, 13, 1) IN ('P','Y') AND DATEDIFF(CURDATE(), bs.tglrujukan) < 90\n" +
                "ORDER BY bs.tglsep DESC LIMIT 1",norm));
            if(!NoSEP1.getText().trim().isEmpty()){
                NoRujukan.setText(Sequel.cariIsi("SELECT bs.no_rujukan from bridging_sep bs WHERE bs.no_sep =?",NoSEP1.getText()));
                SttsRujukan.setText(Sequel.cariIsi("SELECT DISTINCT CASE WHEN DATEDIFF(CURDATE(), bs.tglrujukan) > 90 THEN 'Tidak Aktif' ELSE 'Aktif' END AS status_rujukan\n" +
                    "FROM bridging_sep bs WHERE bs.no_rujukan = ?",NoRujukan.getText()));
            }
        }else{
            NoSEP1.setText("");
        }
        TglRujukan.setText(Sequel.cariIsi("SELECT bs.tglrujukan from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
        KdPoli.setText(Sequel.cariIsi("SELECT bs.kdpolitujuan from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
        KdPoli1.setText(Sequel.cariIsi("SELECT kd_poli_rs from maping_poli_bpjs bs WHERE kd_poli_bpjs =?",KdPoli.getText()));
        NmDokter.setText(Sequel.cariIsi("SELECT bs.nmdpjplayanan from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
        NmPoli.setText(Sequel.cariIsi("SELECT bs.nmpolitujuan from bridging_sep bs WHERE bs.no_rawat =? and bs.jnspelayanan ='2'",norwt));
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            if(internalFrame1.getHeight()>668){
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,500));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }else{
                ChkInput.setVisible(false);
                PanelInput.setPreferredSize(new Dimension(WIDTH,internalFrame1.getHeight()-225));
                FormInput.setVisible(true);      
                ChkInput.setVisible(true);
            }
        }else if(ChkInput.isSelected()==false){           
            ChkInput.setVisible(false);            
            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
            FormInput.setVisible(false);      
            ChkInput.setVisible(true);
        }
//        if(ChkInput.isSelected()==true){
//            ChkInput.setVisible(false);
//            PanelInput.setPreferredSize(new Dimension(WIDTH,this.getHeight()-122));
//            scrollInput.setVisible(true);      
//            ChkInput.setVisible(true);
//        }else if(ChkInput.isSelected()==false){           
//            ChkInput.setVisible(false);            
//            PanelInput.setPreferredSize(new Dimension(WIDTH,20));
//            scrollInput.setVisible(false);      
//            ChkInput.setVisible(true);
//        }
    }
    
    public void isCek(){
        BtnSimpan.setEnabled(akses.getdata_resume_pasien());
        BtnHapus.setEnabled(akses.getdata_resume_pasien());
        BtnEdit.setEnabled(akses.getdata_resume_pasien());
        BtnPrint.setEnabled(akses.getdata_resume_pasien());    
        BtnSimpan1.setEnabled(akses.getedit_registrasi());
        ChkKontrol.setSelected(false);
        if(akses.getjml2()>=1){
            KodeDokter.setEditable(false);
            BtnDokter.setEnabled(false);
            KodeDokter.setText(akses.getkode());
            NamaDokter.setText(dokter.tampil3(KodeDokter.getText()));
            if(NamaDokter.getText().equals("")){
                KodeDokter.setText("");
                JOptionPane.showMessageDialog(null,"User login bukan dokter...!!");
            }
        }            
    }

    private void ganti() {
        if(Sequel.queryu2tf("update program_terapi set kd_dokter=?,program=?,ket_subjective=?,tensi=?,suhu=?,nadi=?,respi=?,kesadaran=?,"+
                "objective=?,assesment=?,plan=?,tanggal=? where no_rawat=?",13,
            new String[]{
                KodeDokter.getText(),Program.getText(),KetSubjective.getText(),Tensi.getText(),Suhu.getText(),Nadi.getText(),Respi.getText(),
                Kesadaran.getSelectedItem().toString(),Objective.getText(),Assesment.getText(),Plan.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),
                tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
            })==true){
                tabMode.setValueAt(TNoRw.getText(),tbObat.getSelectedRow(),0);
                tabMode.setValueAt(TNoRM.getText(),tbObat.getSelectedRow(),1);
                tabMode.setValueAt(TPasien.getText(),tbObat.getSelectedRow(),2);
                tabMode.setValueAt(KodeDokter.getText(),tbObat.getSelectedRow(),3);
                tabMode.setValueAt(NamaDokter.getText(),tbObat.getSelectedRow(),4);
                tabMode.setValueAt(Program.getText(),tbObat.getSelectedRow(),5);
                tabMode.setValueAt(KetSubjective.getText(),tbObat.getSelectedRow(),6);
                tabMode.setValueAt(Tensi.getText(),tbObat.getSelectedRow(),7);
                tabMode.setValueAt(Suhu.getText(),tbObat.getSelectedRow(),8);
                tabMode.setValueAt(Nadi.getText(),tbObat.getSelectedRow(),9);
                tabMode.setValueAt(Respi.getText(),tbObat.getSelectedRow(),10);
                tabMode.setValueAt(Kesadaran.getSelectedItem(),tbObat.getSelectedRow(),11);
                tabMode.setValueAt(Objective.getText(),tbObat.getSelectedRow(),12);
                tabMode.setValueAt(Assesment.getText(),tbObat.getSelectedRow(),13);
                tabMode.setValueAt(Plan.getText(),tbObat.getSelectedRow(),14);
                tabMode.setValueAt(Valid.SetTgl(DTPTgl.getSelectedItem()+""),tbObat.getSelectedRow(),15);
                emptTeks();
            }
    }

    private void hapus() {
        if(Sequel.queryu2tf("delete from program_terapi where no_rawat=?",1,new String[]{
            tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()
        })==true)
        {
            tabMode.removeRow(tbObat.getSelectedRow());
            LCount.setText(""+tabMode.getRowCount());
            emptTeks();
        }else{
            JOptionPane.showMessageDialog(null,"Gagal menghapus..!!");
        }
    }
    
    private String generateNoSurat(String prefix, String table, String kolom) {
        String tgl = new java.text.SimpleDateFormat("ddMMyyyy").format(new java.util.Date());
        String awalan = prefix + tgl;
        int urut = 1;
        try {
            String maxNo = Sequel.cariIsi(
                "SELECT MAX(" + kolom + ") FROM " + table + " WHERE " + kolom + " LIKE '" + awalan + "%'"
            );
            if (maxNo != null && maxNo.trim().length() == awalan.length() + 3) {
                urut = Integer.parseInt(maxNo.trim().substring(awalan.length())) + 1;
            }
        } catch (Exception e) {
            System.out.println("Notifikasi generateNoSurat : " + e);
        }
        return awalan + String.format("%03d", urut);
    }
    
    private boolean cekRujukanMasihAktif(String tglRujukanStr, String tglKontrolStr) {
        final int BATAS_HARI_RUJUKAN = 90;
        if (tglRujukanStr == null || tglRujukanStr.trim().isEmpty()
                || tglKontrolStr == null || tglKontrolStr.trim().isEmpty()) {
            return false;
        }
        try {
            // ambil 10 karakter pertama saja (yyyy-MM-dd), berjaga-jaga kalau field
            // menyimpan format datetime (yyyy-MM-dd HH:mm:ss)
            String tglRujukanOnly = tglRujukanStr.trim().length() >= 10
                    ? tglRujukanStr.trim().substring(0, 10) : tglRujukanStr.trim();
            String tglKontrolOnly = tglKontrolStr.trim().length() >= 10
                    ? tglKontrolStr.trim().substring(0, 10) : tglKontrolStr.trim();

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Date tglRujukan = sdf.parse(tglRujukanOnly);
            Date tglKontrol = sdf.parse(tglKontrolOnly);

            Calendar batas = Calendar.getInstance();
            batas.setTime(tglRujukan);
            batas.add(Calendar.DATE, BATAS_HARI_RUJUKAN);

            // Masih aktif hanya jika tanggal kontrol STRICTLY sebelum batas 90 hari.
            // VClaim BPJS sudah menolak tepat di hari ke-90 (pesan: "Masa Berlaku Habis,
            // Maksimal 3(tiga) bulan dari tanggal rujukan"), jadi hari ke-90 itu sendiri
            // harus dianggap Tidak Aktif, bukan Aktif.
            return tglKontrol.before(batas.getTime());
        } catch (Exception e) {
            System.out.println("Notifikasi cekRujukanMasihAktif : " + e);
            return false;
        }
    }
    
    private String formatTglTampilan(String tglYYYYMMDD){
        if(tglYYYYMMDD == null || tglYYYYMMDD.trim().equals("")){
            return tglYYYYMMDD;
        }
        try{
            String[] pecah = tglYYYYMMDD.trim().split("-");
            if(pecah.length == 3){
                return pecah[2]+"-"+pecah[1]+"-"+pecah[0];
            }
        }catch(Exception e){
        }
        return tglYYYYMMDD;
    }
    
    // Peta nomor hari (java.util.Calendar) ke nama hari sesuai enum kolom hari_kerja
    // di tabel `jadwal` ('SENIN','SELASA','RABU','KAMIS','JUMAT','SABTU','AHAD').
    // Dipakai bersama oleh cariTanggalKontrolOtomatis, hindariTanggalLiburDokter, dan hariDariTanggal.
    private Map<Integer,String> mapHariKerja() {
        Map<Integer,String> mapHari = new HashMap<>();
        mapHari.put(Calendar.MONDAY,   "SENIN");
        mapHari.put(Calendar.TUESDAY,  "SELASA");
        mapHari.put(Calendar.WEDNESDAY,"RABU");
        mapHari.put(Calendar.THURSDAY, "KAMIS");
        mapHari.put(Calendar.FRIDAY,   "JUMAT");
        mapHari.put(Calendar.SATURDAY, "SABTU");
        mapHari.put(Calendar.SUNDAY,   "AKHAD");
        return mapHari;
    }

    // Ambil semua hari_kerja (SENIN, SELASA, dst) tempat dokter tsb terdaftar praktek di tabel `jadwal`.
    private Set<String> ambilHariPraktekDokter(String kdDokter) {
        Set<String> hariPraktek = new HashSet<>();
        try {
            psJadwal = koneksi.prepareStatement(
                "select distinct hari_kerja from jadwal where kd_dokter=?"
            );
            psJadwal.setString(1, kdDokter);
            rsJadwal = psJadwal.executeQuery();
            while (rsJadwal.next()) {
                hariPraktek.add(rsJadwal.getString("hari_kerja").trim().toUpperCase());
            }
        } catch (Exception e) {
            System.out.println("Notifikasi ambilHariPraktekDokter : "+e);
        } finally {
            try {
                if (rsJadwal != null) rsJadwal.close();
                if (psJadwal != null) psJadwal.close();
            } catch (Exception e) {
                System.out.println("Notifikasi : "+e);
            }
        }
        return hariPraktek;
    }

    // Nama hari (SENIN, SELASA, dst) dari sebuah tanggal berformat yyyy-MM-dd.
    private String hariDariTanggal(String tglYYYYMMDD) {
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(tglYYYYMMDD));
            return mapHariKerja().get(cal.get(Calendar.DAY_OF_WEEK));
        } catch (Exception e) {
            System.out.println("Notifikasi hariDariTanggal : "+e);
            return null;
        }
    }
    
    private String cariTanggalKontrolOtomatis(int hariMinimal) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, hariMinimal);

        // Skip hari Minggu
        while (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DATE, 1);
        }

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    // ================================================================
    // POINT 2: Jika tanggal kontrol jatuh pada tanggal dokter "Tidak Praktek"
    // (tabel jadwal_cuti_libur), otomatis maju ke tanggal praktek berikutnya
    // sesuai hari kerja dokter (tabel jadwal), tanpa perlu konfirmasi DPJP.
    // ================================================================
    private String hindariTanggalLiburDokter(String kdDokter, String tglAwalYYYYMMDD) {
        if (tglAwalYYYYMMDD == null || tglAwalYYYYMMDD.trim().isEmpty()) {
            return tglAwalYYYYMMDD;
        }

        Set<String> hariPraktek = ambilHariPraktekDokter(kdDokter);
        if (hariPraktek.isEmpty()) {
            // Tidak ada jadwal praktek terdaftar sama sekali untuk dokter ini,
            // tidak bisa dipakai patokan "tanggal praktek berikutnya" -> pakai tanggal apa adanya.
            return tglAwalYYYYMMDD;
        }

        Map<Integer,String> mapHari = mapHariKerja();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(tglAwalYYYYMMDD));
        } catch (Exception e) {
            System.out.println("Notifikasi hindariTanggalLiburDokter (parse tanggal) : "+e);
            return tglAwalYYYYMMDD;
        }

        int maxLoop = 30; // jaga-jaga supaya tidak muter tanpa henti kalau data cuti/libur tidak wajar
        for (int i = 0; i < maxLoop; i++) {
            String tglCek = sdf.format(cal.getTime());

            boolean dokterLibur = Sequel.cariInteger(
                "select count(*) from jadwal_cuti_libur where kd_dokter='"+kdDokter+"' "+
                "and tanggallibur='"+tglCek+"' and status='Tidak Praktek'"
            ) > 0;

            if (!dokterLibur) {
                return tglCek; // aman, dokter tidak sedang cuti/libur di tanggal ini
            }

            // Dokter libur di tanggal ini -> maju ke tanggal praktek berikutnya sesuai hari kerja dokter,
            // lalu tanggal itu akan dicek ulang lagi (siapa tahu tanggal berikutnya juga kebetulan libur).
            do {
                cal.add(Calendar.DATE, 1);
            } while (!hariPraktek.contains(mapHari.get(cal.get(Calendar.DAY_OF_WEEK))));
        }

        JOptionPane.showMessageDialog(null, "Tidak ditemukan tanggal praktek dokter yang tidak sedang cuti/libur, "
                + "silahkan cek jadwal cuti/libur dokter di menu terkait.");
        return null;
    }

    // ================================================================
    // POINT 1: Pengingat kuota pasien pada tanggal kontrol yang akan dipakai.
    // Kuota diambil dari tabel jadwal (kd_dokter + kd_poli + hari_kerja pada
    // tanggal tsb), dibandingkan dengan jumlah pasien yang sudah terdaftar
    // (reg_periksa) untuk dokter+poli+tanggal yang sama. Notifikasi HANYA
    // muncul kalau kuota sudah penuh/terlampaui, dengan pilihan Ya/Tidak:
    // - Ya   : lanjutkan tetap membuat surat kontrol/SKDP walau kuota penuh
    // - Tidak: batal, tidak terjadi apa-apa
    // Kalau kuota masih tersedia (atau datanya tidak ditemukan), langsung
    // lanjut tanpa menampilkan notifikasi apapun.
    // ================================================================
    private boolean konfirmasiKuotaDokter(String kdDokter, String kdPoli, String tglYYYYMMDD) {
        try {
            String hariKerja = hariDariTanggal(tglYYYYMMDD);
            if (hariKerja == null) {
                return true; // gagal menentukan hari, jangan sampai memblokir proses simpan
            }

            int kuota = Sequel.cariInteger(
                "select ifnull(sum(kuota),0) from jadwal where kd_dokter='"+kdDokter+"' "+
                "and kd_poli='"+kdPoli+"' and hari_kerja='"+hariKerja+"'"
            );

            if (kuota <= 0) {
                // Tidak ada data kuota terdaftar untuk kombinasi dokter/poli/hari ini -> tidak bisa dibandingkan.
                return true;
            }

            // Jumlah pasien (unik berdasarkan no_rkm_medis, supaya pasien yang datanya
            // dobel tidak dihitung 2x) yang SUDAH DIBUATKAN surat kontrol pada tanggal
            // ini oleh dokter yang sama, digabung dari 3 tabel: skdp, surat_kontrol, dan
            // bridging_surat_kontrol_bpjs. Tabel terakhir tidak punya no_rkm_medis
            // langsung, jadi dicari lewat no_sep -> bridging_sep.no_rawat -> reg_periksa.no_rkm_medis.
            int terisi = Sequel.cariInteger(
                "select count(*) from ( "+
                "  select no_rkm_medis from skdp "+
                "    where kd_dokter='"+kdDokter+"' and tanggal_kontrol='"+tglYYYYMMDD+"' "+
                "  union "+
                "  select no_rkm_medis from surat_kontrol "+
                "    where kd_dokter='"+kdDokter+"' and tanggal_kontrol='"+tglYYYYMMDD+"' "+
                "  union "+
                "  select rp.no_rkm_medis from bridging_surat_kontrol_bpjs bsk "+
                "    inner join bridging_sep bs on bs.no_sep = bsk.no_sep "+
                "    inner join reg_periksa rp on rp.no_rawat = bs.no_rawat "+
                "    where bsk.kd_dokter_bpjs='"+kdDokter+"' and bsk.tgl_rencana='"+tglYYYYMMDD+"' "+
                ") as gabungan_terisi"
            );

            if (terisi >= kuota) {
                int pilihan = JOptionPane.showConfirmDialog(null,
                        "Kuota pasien dr. "+NamaDokter.getText()+" pada tanggal "+formatTglTampilan(tglYYYYMMDD)+
                        " sudah penuh ("+terisi+" dari kuota "+kuota+").\n"+
                        "Tetap buat surat kontrol untuk tanggal tersebut?",
                        "Kuota Dokter Sudah Penuh",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                return pilihan == JOptionPane.YES_OPTION;
            }

            return true; // kuota masih tersedia, lanjut tanpa notifikasi
        } catch (Exception e) {
            System.out.println("Notifikasi konfirmasiKuotaDokter : "+e);
            return true; // kalau pengecekan gagal, jangan sampai memblokir proses simpan surat kontrol
        }
    }

    private void prosesSuratKontrol(String tglKontrolOtomatisInput, String labelKontrol, String jamAwal) {
        tglKontrolOtomatis = tglKontrolOtomatisInput;
        
        if(!cekRujukanMasihAktif(TglRujukan.getText().trim(), tglKontrolOtomatis)){
            SttsRujukan.setText("Tidak Aktif");
        }
        if(SttsRujukan.getText().trim().equals("Aktif")){
            NoSKDP = generateNoSurat("SKDP","skdp","no_skdp");
                if(Sequel.menyimpantf("skdp","?,?,?,?,?,?,?,?,?","No.Surat",9,new String[]{
                    NoSKDP,TNoRw.getText(),TNoRM.getText(),NoRujukan.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),tglKontrolOtomatis,
                    Assesment.getText(),"",KODE_DOKTER_FORM_INI
                    })==true){
                    simpanJadwalFisio(NoSKDP, tglKontrolOtomatis, jamAwal);
                    JOptionPane.showMessageDialog(null,"SKDP berhasil dibuat dan disimpan...!");
                }else{
                    JOptionPane.showMessageDialog(null,"SKDP gagal disimpan...!");
                }
            }else{
                NoSuratKontrol = generateNoSurat("SK","surat_kontrol","no_surat_kontrol");
                if(TglRujukan.getText().equals("")){
                    if(Sequel.menyimpantf("surat_kontrol","?,?,?,?,?,?,?,?,?,?,?,?,?","No.Surat",13,new String[]{
                        NoSuratKontrol,TNoRw.getText(),TNoRM.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),"0000:00:00",
                        tglKontrolOtomatis,Assesment.getText(),"","","","","",KODE_DOKTER_FORM_INI
                        })==true){
                        simpanJadwalFisio(NoSuratKontrol, tglKontrolOtomatis, jamAwal);
                        JOptionPane.showMessageDialog(null,"Surat Kontrol berhasil dibuat dan disimpan...!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Surat Kontrol gagal disimpan...!");
                    }
                }else{
                    if(Sequel.menyimpantf("surat_kontrol","?,?,?,?,?,?,?,?,?,?,?,?,?","No.Surat",13,new String[]{
                        NoSuratKontrol,TNoRw.getText(),TNoRM.getText(),Valid.SetTgl(DTPTgl.getSelectedItem()+""),TglRujukan.getText(),
                        tglKontrolOtomatis,Assesment.getText(),"","","","","",KODE_DOKTER_FORM_INI
                        })==true){
                        simpanJadwalFisio(NoSuratKontrol, tglKontrolOtomatis, jamAwal);
                        JOptionPane.showMessageDialog(null,"Surat Kontrol berhasil dibuat dan disimpan...!");
                    }else{
                        JOptionPane.showMessageDialog(null,"Surat Kontrol gagal disimpan...!");
                    }
                }
            }
    }

    /**
     * Menyimpan jadwal fisioterapi (jam kontrol) ke tabel jadwal_fisio,
     * dikaitkan dengan no_surat yang sudah dibuat sebelumnya (bisa NoSKDP
     * atau NoSuratKontrol, tergantung status rujukan pasien).
     *
     * @param noSurat No.SKDP atau No.Surat Kontrol yang baru dibuat
     * @param tanggal tanggal kontrol (format yyyy-MM-dd)
     * @param jamAwal jam awal (format HH:mm:ss), dari DlgTanggalJamKontrolCustom.getJamTerpilih()
     * @param jamAkhir jam akhir (format HH:mm:ss), dari DlgTanggalJamKontrolCustom.getJamAkhirTerpilih()
     */
    private void simpanJadwalFisio(String noSurat, String tanggal, String jamAwal) {
        try {
            if(Sequel.menyimpantf("jadwal_fisio","?,?,?,?","no_surat",4,new String[]{
                noSurat,TNoRw.getText(),tanggal,jamAwal
                })==true){
                JOptionPane.showMessageDialog(null,"Jadwal Fisioterapi berhasil disimpan...!");
            }
        } catch (Exception e) {
            System.out.println("Notifikasi simpanJadwalFisio : "+e);
            JOptionPane.showMessageDialog(null,"Jadwal Fisioterapi gagal disimpan...!");
        }
    }
}
