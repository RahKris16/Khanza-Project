package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.akses;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URI;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author dosen
 */
public class BPJSSuratKontrol extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;
    private int i=0;
    private BPJSCekReferensiDokterKontrol dokter=new BPJSCekReferensiDokterKontrol(null,false);
    private BPJSCekReferensiSpesialistikKontrol poli=new BPJSCekReferensiSpesialistikKontrol(null,false);
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private ObjectMapper mapper = new ObjectMapper();
    private JsonNode root;
    private JsonNode nameNode;
    private JsonNode response;
    private String aktifjadwal="",URUTNOREG="",link="",requestJson="",URL="",user="",utc="",bpoli="",bnpoli="",bdokter="",bndokter="";
    private ApiBPJS api=new ApiBPJS();
    private SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");

    /** Creates new form DlgPemberianInfus
     * @param parent
     * @param modal */
    public BPJSSuratKontrol(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        tabMode=new DefaultTableModel(null,new Object[]{
                "No.Rawat","No.SEP","No.Kartu","No.RM","Nama Pasien","Tgl.Lahir","J.K.","Diagnosa","No.Telp",
                "Tgl.Rujukan","Expired","Tgl.Surat","No.Surat","Tgl.Kontrol","Kode Dokter","Nama Dokter/Sepesialis",
                "Kode Poli","Nama Poli/Unit","Status"
            }){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbObat.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbObat.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObat.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 19; i++) {
            TableColumn column = tbObat.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(105);
            }else if(i==1){
                column.setPreferredWidth(125);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setPreferredWidth(65);
            }else if(i==6){
                column.setPreferredWidth(25);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(100);
            }else if(i==9){
                column.setPreferredWidth(65);
            }else if(i==10){
                column.setPreferredWidth(125);
            }else if(i==11){
                column.setPreferredWidth(65);
            }else if(i==12){
                column.setPreferredWidth(125);
            }else if(i==13){
                column.setPreferredWidth(65);
            }else if(i==14){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==15){
                column.setPreferredWidth(150);
            }else if(i==16){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==17){
                column.setPreferredWidth(150);
            }else if(i==18){
                column.setPreferredWidth(250);
            }
        }
        tbObat.setDefaultRenderer(Object.class, new WarnaTable());


        NoRawat.setDocument(new batasInput((byte)15).getKata(NoRawat));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        KdDokter.setDocument(new batasInput((byte)20).getKata(KdDokter));
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
        
        ChkInput.setSelected(false);
        isForm();
        
        dokter.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {;}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(dokter.getTable().getSelectedRow()!= -1){                    
                    KdDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),1).toString());
                    NmDokter.setText(dokter.getTable().getValueAt(dokter.getTable().getSelectedRow(),2).toString());
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
        
        dokter.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    dokter.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        poli.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(poli.getTable().getSelectedRow()!= -1){                    
                    KdPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),1).toString());
                    NmPoli.setText(poli.getTable().getValueAt(poli.getTable().getSelectedRow(),2).toString());
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
        
        poli.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    poli.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });  
        
        try {
            user=akses.getkode().replace(" ","").substring(0,9);
        } catch (Exception e) {
            user=akses.getkode();
        }
        
        try {
            link=koneksiDB.URLAPIBPJS();
        } catch (Exception e) {
            System.out.println("E : "+e);
        }
        
        try {
            aktifjadwal=koneksiDB.JADWALDOKTERDIREGISTRASI();
            URUTNOREG=koneksiDB.URUTNOREG();
        } catch (Exception ex) {
            aktifjadwal="";
            URUTNOREG="";
        }
    }
 
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnSurat2 = new javax.swing.JMenuItem();
        MnSurat = new javax.swing.JMenuItem();
        MnSurat1 = new javax.swing.JMenuItem();
        MnReservasi = new javax.swing.JMenuItem();
        KirimWa = new javax.swing.JMenuItem();
        NoKartu = new widget.TextBox();
        NmPoli2 = new widget.TextBox();
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
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelGlass10 = new widget.panelisi();
        jLabel6 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        jLabel7 = new widget.Label();
        LCount = new widget.Label();
        panelCari = new widget.panelisi();
        R1 = new widget.RadioButton();
        DTPTanggalSurat1 = new widget.Tanggal();
        jLabel22 = new widget.Label();
        DTPTanggalSurat2 = new widget.Tanggal();
        LCount1 = new widget.Label();
        R2 = new widget.RadioButton();
        DTPTanggalKontrol1 = new widget.Tanggal();
        jLabel25 = new widget.Label();
        DTPTanggalKontrol2 = new widget.Tanggal();
        PanelInput = new javax.swing.JPanel();
        ChkInput = new widget.CekBox();
        FormInput = new widget.PanelBiasa();
        jLabel4 = new widget.Label();
        NoRawat = new widget.TextBox();
        jLabel9 = new widget.Label();
        NmDokter = new widget.TextBox();
        NoSEP = new widget.TextBox();
        TanggalSurat = new widget.Tanggal();
        jLabel10 = new widget.Label();
        KdDokter = new widget.TextBox();
        BtnDokter = new widget.Button();
        jLabel11 = new widget.Label();
        KdPoli = new widget.TextBox();
        NmPoli = new widget.TextBox();
        BtnPoli = new widget.Button();
        jLabel14 = new widget.Label();
        TanggalKontrol = new widget.Tanggal();
        jLabel15 = new widget.Label();
        NoSurat = new widget.TextBox();
        jLabel5 = new widget.Label();
        jLabel12 = new widget.Label();
        NmPasien = new widget.TextBox();
        NoRM = new widget.TextBox();
        TglLahir = new widget.TextBox();
        jLabel13 = new widget.Label();
        jLabel16 = new widget.Label();
        JK = new widget.TextBox();
        jLabel17 = new widget.Label();
        Diagnosa = new widget.TextBox();
        KdDokter1 = new widget.TextBox();
        NmDokter1 = new widget.TextBox();
        KdPoli1 = new widget.TextBox();
        NmPoli1 = new widget.TextBox();
        jLabel18 = new widget.Label();
        jLabel19 = new widget.Label();
        jLabel20 = new widget.Label();
        NoReg = new widget.TextBox();
        TanggalBooking = new widget.Tanggal();
        jLabel21 = new widget.Label();
        jLabel23 = new widget.Label();
        NoBook = new widget.TextBox();
        jLabel24 = new widget.Label();
        jLabel26 = new widget.Label();
        CaraBook = new widget.TextBox();

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnSurat2.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat2.setForeground(new java.awt.Color(50, 50, 50));
        MnSurat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat2.setText("Surat Kontrol");
        MnSurat2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat2.setName("MnSurat2"); // NOI18N
        MnSurat2.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSurat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSurat2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat2);

        MnSurat.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat.setForeground(new java.awt.Color(50, 50, 50));
        MnSurat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat.setText("Surat Kontrol (Reservasi)");
        MnSurat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat.setName("MnSurat"); // NOI18N
        MnSurat.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSuratActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat);

        MnSurat1.setBackground(new java.awt.Color(255, 255, 254));
        MnSurat1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnSurat1.setForeground(new java.awt.Color(50, 50, 50));
        MnSurat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnSurat1.setText("Surat Kontrol (Non Reservasi)");
        MnSurat1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnSurat1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnSurat1.setName("MnSurat1"); // NOI18N
        MnSurat1.setPreferredSize(new java.awt.Dimension(160, 26));
        MnSurat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnSurat1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnSurat1);

        MnReservasi.setBackground(new java.awt.Color(255, 255, 254));
        MnReservasi.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        MnReservasi.setForeground(new java.awt.Color(50, 50, 50));
        MnReservasi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        MnReservasi.setText("Reservasi");
        MnReservasi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MnReservasi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        MnReservasi.setName("MnReservasi"); // NOI18N
        MnReservasi.setPreferredSize(new java.awt.Dimension(160, 26));
        MnReservasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnReservasiActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnReservasi);

        KirimWa.setBackground(new java.awt.Color(255, 255, 254));
        KirimWa.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        KirimWa.setForeground(new java.awt.Color(50, 50, 50));
        KirimWa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        KirimWa.setText("Kirim WA");
        KirimWa.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        KirimWa.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        KirimWa.setName("KirimWa"); // NOI18N
        KirimWa.setPreferredSize(new java.awt.Dimension(160, 26));
        KirimWa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KirimWaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(KirimWa);

        NoKartu.setEditable(false);
        NoKartu.setHighlighter(null);
        NoKartu.setName("NoKartu"); // NOI18N

        NmPoli2.setEditable(false);
        NmPoli2.setHighlighter(null);
        NmPoli2.setName("NmPoli2"); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Surat Kontrol VClaim ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

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
        jPanel3.setPreferredSize(new java.awt.Dimension(44, 144));
        jPanel3.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass8.setName("panelGlass8"); // NOI18N
        panelGlass8.setPreferredSize(new java.awt.Dimension(55, 55));
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

        jPanel3.add(panelGlass8, java.awt.BorderLayout.PAGE_END);

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(44, 44));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        jLabel6.setText("Key Word :");
        jLabel6.setName("jLabel6"); // NOI18N
        jLabel6.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(jLabel6);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(450, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelGlass10.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
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
        panelGlass10.add(BtnCari);

        jLabel7.setText("Record :");
        jLabel7.setName("jLabel7"); // NOI18N
        jLabel7.setPreferredSize(new java.awt.Dimension(65, 23));
        panelGlass10.add(jLabel7);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelGlass10.add(LCount);

        jPanel3.add(panelGlass10, java.awt.BorderLayout.CENTER);

        panelCari.setName("panelCari"); // NOI18N
        panelCari.setPreferredSize(new java.awt.Dimension(44, 43));
        panelCari.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 9));

        R1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R1);
        R1.setSelected(true);
        R1.setText("Tanggal Surat :");
        R1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R1.setName("R1"); // NOI18N
        R1.setPreferredSize(new java.awt.Dimension(115, 23));
        panelCari.add(R1);

        DTPTanggalSurat1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025" }));
        DTPTanggalSurat1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat1.setName("DTPTanggalSurat1"); // NOI18N
        DTPTanggalSurat1.setOpaque(false);
        DTPTanggalSurat1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalSurat1ItemStateChanged(evt);
            }
        });
        DTPTanggalSurat1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat1);

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("s.d");
        jLabel22.setName("jLabel22"); // NOI18N
        jLabel22.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel22);

        DTPTanggalSurat2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025" }));
        DTPTanggalSurat2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalSurat2.setName("DTPTanggalSurat2"); // NOI18N
        DTPTanggalSurat2.setOpaque(false);
        DTPTanggalSurat2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalSurat2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalSurat2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalSurat2);

        LCount1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount1.setName("LCount1"); // NOI18N
        LCount1.setPreferredSize(new java.awt.Dimension(45, 23));
        panelCari.add(LCount1);

        R2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.pink));
        buttonGroup1.add(R2);
        R2.setText("Tanggal Kontrol :");
        R2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        R2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        R2.setName("R2"); // NOI18N
        R2.setPreferredSize(new java.awt.Dimension(120, 23));
        panelCari.add(R2);

        DTPTanggalKontrol1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025" }));
        DTPTanggalKontrol1.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol1.setName("DTPTanggalKontrol1"); // NOI18N
        DTPTanggalKontrol1.setOpaque(false);
        DTPTanggalKontrol1.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalKontrol1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalKontrol1ItemStateChanged(evt);
            }
        });
        DTPTanggalKontrol1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKontrol1KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalKontrol1);

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("s.d");
        jLabel25.setName("jLabel25"); // NOI18N
        jLabel25.setPreferredSize(new java.awt.Dimension(25, 23));
        panelCari.add(jLabel25);

        DTPTanggalKontrol2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025" }));
        DTPTanggalKontrol2.setDisplayFormat("dd-MM-yyyy");
        DTPTanggalKontrol2.setName("DTPTanggalKontrol2"); // NOI18N
        DTPTanggalKontrol2.setOpaque(false);
        DTPTanggalKontrol2.setPreferredSize(new java.awt.Dimension(95, 23));
        DTPTanggalKontrol2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DTPTanggalKontrol2ItemStateChanged(evt);
            }
        });
        DTPTanggalKontrol2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                DTPTanggalKontrol2KeyPressed(evt);
            }
        });
        panelCari.add(DTPTanggalKontrol2);

        jPanel3.add(panelCari, java.awt.BorderLayout.PAGE_START);

        internalFrame1.add(jPanel3, java.awt.BorderLayout.PAGE_END);

        PanelInput.setName("PanelInput"); // NOI18N
        PanelInput.setOpaque(false);
        PanelInput.setPreferredSize(new java.awt.Dimension(192, 156));
        PanelInput.setLayout(new java.awt.BorderLayout(1, 1));

        ChkInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/143.png"))); // NOI18N
        ChkInput.setMnemonic('M');
        ChkInput.setText(".: Input Data");
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
        FormInput.setPreferredSize(new java.awt.Dimension(190, 107));
        FormInput.setLayout(null);

        jLabel4.setText("No.SEP :");
        jLabel4.setName("jLabel4"); // NOI18N
        FormInput.add(jLabel4);
        jLabel4.setBounds(232, 10, 50, 23);

        NoRawat.setEditable(false);
        NoRawat.setHighlighter(null);
        NoRawat.setName("NoRawat"); // NOI18N
        FormInput.add(NoRawat);
        NoRawat.setBounds(94, 10, 130, 23);

        jLabel9.setText("Spesialis/Sub :");
        jLabel9.setName("jLabel9"); // NOI18N
        FormInput.add(jLabel9);
        jLabel9.setBounds(0, 100, 90, 23);

        NmDokter.setEditable(false);
        NmDokter.setHighlighter(null);
        NmDokter.setName("NmDokter"); // NOI18N
        FormInput.add(NmDokter);
        NmDokter.setBounds(184, 100, 160, 23);

        NoSEP.setHighlighter(null);
        NoSEP.setName("NoSEP"); // NOI18N
        FormInput.add(NoSEP);
        NoSEP.setBounds(286, 10, 150, 23);

        TanggalSurat.setForeground(new java.awt.Color(50, 70, 50));
        TanggalSurat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025" }));
        TanggalSurat.setDisplayFormat("dd-MM-yyyy");
        TanggalSurat.setName("TanggalSurat"); // NOI18N
        TanggalSurat.setOpaque(false);
        TanggalSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalSuratKeyPressed(evt);
            }
        });
        FormInput.add(TanggalSurat);
        TanggalSurat.setBounds(394, 70, 95, 23);

        jLabel10.setText("Tanggal Surat :");
        jLabel10.setName("jLabel10"); // NOI18N
        FormInput.add(jLabel10);
        jLabel10.setBounds(290, 70, 100, 23);

        KdDokter.setEditable(false);
        KdDokter.setHighlighter(null);
        KdDokter.setName("KdDokter"); // NOI18N
        FormInput.add(KdDokter);
        KdDokter.setBounds(94, 100, 87, 23);

        BtnDokter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnDokter.setMnemonic('X');
        BtnDokter.setToolTipText("Alt+X");
        BtnDokter.setName("BtnDokter"); // NOI18N
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
        BtnDokter.setBounds(347, 100, 28, 23);

        jLabel11.setText("Unit/Poli :");
        jLabel11.setName("jLabel11"); // NOI18N
        FormInput.add(jLabel11);
        jLabel11.setBounds(394, 100, 60, 23);

        KdPoli.setEditable(false);
        KdPoli.setHighlighter(null);
        KdPoli.setName("KdPoli"); // NOI18N
        FormInput.add(KdPoli);
        KdPoli.setBounds(458, 100, 70, 23);

        NmPoli.setEditable(false);
        NmPoli.setHighlighter(null);
        NmPoli.setName("NmPoli"); // NOI18N
        FormInput.add(NmPoli);
        NmPoli.setBounds(531, 100, 165, 23);

        BtnPoli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnPoli.setMnemonic('X');
        BtnPoli.setToolTipText("Alt+X");
        BtnPoli.setName("BtnPoli"); // NOI18N
        BtnPoli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPoliActionPerformed(evt);
            }
        });
        BtnPoli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPoliKeyPressed(evt);
            }
        });
        FormInput.add(BtnPoli);
        BtnPoli.setBounds(699, 100, 28, 23);

        jLabel14.setText("Tanggal Kontrol :");
        jLabel14.setName("jLabel14"); // NOI18N
        FormInput.add(jLabel14);
        jLabel14.setBounds(528, 70, 100, 23);

        TanggalKontrol.setForeground(new java.awt.Color(50, 70, 50));
        TanggalKontrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025" }));
        TanggalKontrol.setDisplayFormat("dd-MM-yyyy");
        TanggalKontrol.setName("TanggalKontrol"); // NOI18N
        TanggalKontrol.setOpaque(false);
        TanggalKontrol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                TanggalKontrolItemStateChanged(evt);
            }
        });
        TanggalKontrol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalKontrolKeyPressed(evt);
            }
        });
        FormInput.add(TanggalKontrol);
        TanggalKontrol.setBounds(632, 70, 95, 23);

        jLabel15.setText("No.Surat :");
        jLabel15.setName("jLabel15"); // NOI18N
        FormInput.add(jLabel15);
        jLabel15.setBounds(0, 70, 90, 23);

        NoSurat.setEditable(false);
        NoSurat.setHighlighter(null);
        NoSurat.setName("NoSurat"); // NOI18N
        NoSurat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoSuratActionPerformed(evt);
            }
        });
        NoSurat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NoSuratKeyPressed(evt);
            }
        });
        FormInput.add(NoSurat);
        NoSurat.setBounds(94, 70, 170, 23);

        jLabel5.setText("No.Rawat :");
        jLabel5.setName("jLabel5"); // NOI18N
        FormInput.add(jLabel5);
        jLabel5.setBounds(0, 10, 90, 23);

        jLabel12.setText("Pasien :");
        jLabel12.setName("jLabel12"); // NOI18N
        FormInput.add(jLabel12);
        jLabel12.setBounds(0, 40, 90, 23);

        NmPasien.setEditable(false);
        NmPasien.setHighlighter(null);
        NmPasien.setName("NmPasien"); // NOI18N
        FormInput.add(NmPasien);
        NmPasien.setBounds(197, 40, 230, 23);

        NoRM.setEditable(false);
        NoRM.setHighlighter(null);
        NoRM.setName("NoRM"); // NOI18N
        FormInput.add(NoRM);
        NoRM.setBounds(94, 40, 100, 23);

        TglLahir.setEditable(false);
        TglLahir.setHighlighter(null);
        TglLahir.setName("TglLahir"); // NOI18N
        FormInput.add(TglLahir);
        TglLahir.setBounds(495, 40, 95, 23);

        jLabel13.setText("Tgl.Lahir :");
        jLabel13.setName("jLabel13"); // NOI18N
        FormInput.add(jLabel13);
        jLabel13.setBounds(429, 40, 62, 23);

        jLabel16.setText("J.K. :");
        jLabel16.setName("jLabel16"); // NOI18N
        FormInput.add(jLabel16);
        jLabel16.setBounds(588, 40, 40, 23);

        JK.setEditable(false);
        JK.setHighlighter(null);
        JK.setName("JK"); // NOI18N
        FormInput.add(JK);
        JK.setBounds(632, 40, 95, 23);

        jLabel17.setText("Diagnosa :");
        jLabel17.setName("jLabel17"); // NOI18N
        FormInput.add(jLabel17);
        jLabel17.setBounds(440, 10, 65, 23);

        Diagnosa.setEditable(false);
        Diagnosa.setHighlighter(null);
        Diagnosa.setName("Diagnosa"); // NOI18N
        FormInput.add(Diagnosa);
        Diagnosa.setBounds(509, 10, 218, 23);

        KdDokter1.setEditable(false);
        KdDokter1.setHighlighter(null);
        KdDokter1.setName("KdDokter1"); // NOI18N
        FormInput.add(KdDokter1);
        KdDokter1.setBounds(850, 10, 100, 23);

        NmDokter1.setEditable(false);
        NmDokter1.setHighlighter(null);
        NmDokter1.setName("NmDokter1"); // NOI18N
        FormInput.add(NmDokter1);
        NmDokter1.setBounds(953, 10, 170, 23);

        KdPoli1.setEditable(false);
        KdPoli1.setHighlighter(null);
        KdPoli1.setName("KdPoli1"); // NOI18N
        FormInput.add(KdPoli1);
        KdPoli1.setBounds(850, 40, 100, 23);

        NmPoli1.setEditable(false);
        NmPoli1.setHighlighter(null);
        NmPoli1.setName("NmPoli1"); // NOI18N
        FormInput.add(NmPoli1);
        NmPoli1.setBounds(953, 40, 170, 23);

        jLabel18.setText("Dokter :");
        jLabel18.setName("jLabel18"); // NOI18N
        FormInput.add(jLabel18);
        jLabel18.setBounds(755, 10, 90, 23);

        jLabel19.setText("Poli :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(755, 40, 90, 23);

        jLabel20.setText("No Booking :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(755, 70, 90, 23);

        NoReg.setEditable(false);
        NoReg.setHighlighter(null);
        NoReg.setName("NoReg"); // NOI18N
        FormInput.add(NoReg);
        NoReg.setBounds(850, 70, 60, 23);

        TanggalBooking.setForeground(new java.awt.Color(50, 70, 50));
        TanggalBooking.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "29-12-2025 08:23:02" }));
        TanggalBooking.setDisplayFormat("dd-MM-yyyy HH:mm:ss");
        TanggalBooking.setName("TanggalBooking"); // NOI18N
        TanggalBooking.setOpaque(false);
        TanggalBooking.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TanggalBookingKeyPressed(evt);
            }
        });
        FormInput.add(TanggalBooking);
        TanggalBooking.setBounds(850, 100, 140, 23);

        jLabel21.setText("Tanggal Booking :");
        jLabel21.setName("jLabel21"); // NOI18N
        FormInput.add(jLabel21);
        jLabel21.setBounds(745, 100, 100, 23);

        jLabel23.setText("No Reg :");
        jLabel23.setName("jLabel23"); // NOI18N
        FormInput.add(jLabel23);
        jLabel23.setBounds(962, 70, 90, 23);

        NoBook.setEditable(false);
        NoBook.setHighlighter(null);
        NoBook.setName("NoBook"); // NOI18N
        FormInput.add(NoBook);
        NoBook.setBounds(1062, 70, 60, 23);

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("*Onsite artinya tidak ada reservasi");
        jLabel24.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel24.setName("jLabel24"); // NOI18N
        FormInput.add(jLabel24);
        jLabel24.setBounds(855, 155, 180, 23);

        jLabel26.setText("Riwayat Reservasi :");
        jLabel26.setName("jLabel26"); // NOI18N
        FormInput.add(jLabel26);
        jLabel26.setBounds(745, 130, 100, 23);

        CaraBook.setEditable(false);
        CaraBook.setHighlighter(null);
        CaraBook.setName("CaraBook"); // NOI18N
        FormInput.add(CaraBook);
        CaraBook.setBounds(853, 130, 270, 23);

        PanelInput.add(FormInput, java.awt.BorderLayout.CENTER);

        internalFrame1.add(PanelInput, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TanggalSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalSuratKeyPressed
        Valid.pindah(evt,TCari,TanggalKontrol);
}//GEN-LAST:event_TanggalSuratKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(NoRawat.getText().trim().equals("")||NoSEP.getText().trim().equals("")){
            Valid.textKosong(NoRawat,"pasien");
        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else if(NmPoli.getText().trim().equals("")||KdPoli.getText().trim().equals("")){
            Valid.textKosong(KdPoli,"Poli");
        }else if(Sequel.cariInteger("select count(no_rkm_medis) from booking_registrasi where tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' and no_rkm_medis='"+NoRM.getText()+"'")>0){
            NmPoli2.setText(Sequel.cariIsi("select p.nm_poli from booking_registrasi br inner join poliklinik p on p.kd_poli=br.kd_poli where br.tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' and br.no_rkm_medis='"+NoRM.getText()+"'"));
            JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah reservasi ke '"+NmPoli2.getText()+"' pada tanggal '"+TanggalKontrol.getSelectedItem()+"' ");
        }else{
            try {
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
                utc=String.valueOf(api.GetUTCdatetimeAsString());
                headers.add("X-Timestamp",utc);
                headers.add("X-Signature",api.getHmac(utc));
                headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                URL = link+"/RencanaKontrol/insert";            
                requestJson ="{" +
                                "\"request\": {" +
                                    "\"noSEP\":\""+NoSEP.getText()+"\"," +
                                    "\"kodeDokter\":\""+KdDokter.getText()+"\"," +
                                    "\"poliKontrol\":\""+KdPoli.getText()+"\"," +
                                    "\"tglRencanaKontrol\":\""+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"\"," +
                                    "\"user\":\""+user+"\"" +
                                "}" +
                             "}";
                System.out.println("JSON : "+requestJson);
                requestEntity = new HttpEntity(requestJson,headers);
                root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody());
                nameNode = root.path("metaData");
                System.out.println("code : "+nameNode.path("code").asText());
                System.out.println("message : "+nameNode.path("message").asText());
                if(nameNode.path("code").asText().equals("200")){
                    response = mapper.readTree(api.Decrypt(root.path("response").asText(),utc)).path("noSuratKontrol");
                    //response = root.path("response").path("noSuratKontrol");
                
                    if(Sequel.menyimpantf("bridging_surat_kontrol_bpjs","?,?,?,?,?,?,?,?","No.Surat",8,new String[]{
                            NoSEP.getText(),Valid.SetTgl(TanggalSurat.getSelectedItem()+""),response.asText(),Valid.SetTgl(TanggalKontrol.getSelectedItem()+""),
                        KdDokter.getText(),NmDokter.getText(),KdPoli.getText(),NmPoli.getText()
                        })==true){
                        emptTeks();
                        tampil();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
                }   
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
                if(ex.toString().contains("UnknownHostException")){
                    JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                }
            }
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,NoSurat,BtnBatal);
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
        if(tbObat.getSelectedRow()!= -1){
            try {
                bodyWithDeleteRequest();
            }catch (Exception ex) {
                System.out.println("Notifikasi Bridging : "+ex);
            }
        }else{
            JOptionPane.showMessageDialog(null,"Silahkan pilih dulu data yang mau dihapus..!!");
        }
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            BtnBatal.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            if(R1.isSelected()==true){
                Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptBridgingSuratKontrol.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_surat between '"+Valid.SetTgl(DTPTanggalSurat1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPTanggalSurat2.getSelectedItem()+"")+"' "+
                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like '%"+TCari.getText().trim()+"%' or bridging_sep.no_sep like '%"+TCari.getText().trim()+"%' or bridging_sep.no_kartu like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_sep.nomr like '%"+TCari.getText().trim()+"%' or bridging_sep.nama_pasien like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.no_surat like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%"+TCari.getText().trim()+"%')")+
                    "order by bridging_surat_kontrol_bpjs.tgl_surat",param);
            }else if(R2.isSelected()==true){
                Map<String, Object> param = new HashMap<>(); 
                param.put("namars",akses.getnamars());
                param.put("alamatrs",akses.getalamatrs());
                param.put("kotars",akses.getkabupatenrs());
                param.put("propinsirs",akses.getpropinsirs());
                param.put("kontakrs",akses.getkontakrs());
                param.put("emailrs",akses.getemailrs());   
                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
                Valid.MyReportqry("rptBridgingSuratKontrol.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,bridging_surat_kontrol_bpjs.nm_poli_bpjs from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep where bridging_surat_kontrol_bpjs.tgl_rencana between '"+Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem()+"")+"' "+
                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like '%"+TCari.getText().trim()+"%' or bridging_sep.no_sep like '%"+TCari.getText().trim()+"%' or bridging_sep.no_kartu like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_sep.nomr like '%"+TCari.getText().trim()+"%' or bridging_sep.nama_pasien like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.no_surat like '%"+TCari.getText().trim()+"%' or "+
                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like '%"+TCari.getText().trim()+"%' or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like '%"+TCari.getText().trim()+"%')")+
                    "order by bridging_surat_kontrol_bpjs.tgl_rencana",param);
            }
                
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
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
            Valid.pindah(evt, BtnCari, NoSEP);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbObatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbObatMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
        }
}//GEN-LAST:event_tbObatMouseClicked

private void BtnDokterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnDokterActionPerformed
    if(KdPoli.getText().equals("")||NmPoli.getText().equals("")){
        Valid.textKosong(BtnPoli,"Unit/Poli");
    }else{
        dokter.SetKontrol(KdPoli.getText(),"2: Rencana Kontrol",Valid.SetTgl(TanggalKontrol.getSelectedItem()+""));
        dokter.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        dokter.setLocationRelativeTo(internalFrame1);
        dokter.setVisible(true);
    }
        
}//GEN-LAST:event_BtnDokterActionPerformed

private void BtnDokterKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnDokterKeyPressed
    if(evt.getKeyCode()==KeyEvent.VK_SPACE){
        BtnDokterActionPerformed(null);
    }else{
        Valid.pindah(evt,TanggalKontrol,BtnPoli);
    }        
}//GEN-LAST:event_BtnDokterKeyPressed

private void ChkInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChkInputActionPerformed
  isForm();                
}//GEN-LAST:event_ChkInputActionPerformed

    private void DTPTanggalKontrol1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTanggalKontrol1KeyPressed

    private void DTPTanggalKontrol2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DTPTanggalKontrol2KeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(NoRawat.getText().trim().equals("")||NoSEP.getText().trim().equals("")){
            Valid.textKosong(NoRawat,"pasien");
        }else if(NmDokter.getText().trim().equals("")||KdDokter.getText().trim().equals("")){
            Valid.textKosong(KdDokter,"Dokter");
        }else if(NmPoli.getText().trim().equals("")||NmPoli.getText().trim().equals("")){
            Valid.textKosong(KdPoli,"Poli");
        }else{
            if(tbObat.getSelectedRow()!= -1){
                try {
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
                    utc=String.valueOf(api.GetUTCdatetimeAsString());
                    headers.add("X-Timestamp",utc);
                    headers.add("X-Signature",api.getHmac(utc));
                    headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
                    URL = link+"/RencanaKontrol/Update";            
                    requestJson ="{" +
                                    "\"request\": {" +
                                        "\"noSuratKontrol\":\""+NoSurat.getText()+"\"," +
                                        "\"noSEP\":\""+NoSEP.getText()+"\"," +
                                        "\"kodeDokter\":\""+KdDokter.getText()+"\"," +
                                        "\"poliKontrol\":\""+KdPoli.getText()+"\"," +
                                        "\"tglRencanaKontrol\":\""+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"\"," +
                                        "\"user\":\""+user+"\"" +
                                    "}" +
                                 "}";
                    System.out.println("JSON : "+requestJson);
                    requestEntity = new HttpEntity(requestJson,headers);
                    root = mapper.readTree(api.getRest().exchange(URL, HttpMethod.PUT, requestEntity, String.class).getBody());
                    nameNode = root.path("metaData");
                    System.out.println("code : "+nameNode.path("code").asText());
                    System.out.println("message : "+nameNode.path("message").asText());
                    if(nameNode.path("code").asText().equals("200")){
                        if(Sequel.mengedittf("bridging_surat_kontrol_bpjs","no_surat=?","tgl_surat=?,tgl_rencana=?,kd_dokter_bpjs=?,nm_dokter_bpjs=?,kd_poli_bpjs=?,nm_poli_bpjs=?",7,new String[]{
                                Valid.SetTgl(TanggalSurat.getSelectedItem()+""),Valid.SetTgl(TanggalKontrol.getSelectedItem()+""),KdDokter.getText(),NmDokter.getText(),KdPoli.getText(),NmPoli.getText(),NoSurat.getText()
                            })==true){
                            emptTeks();
                            tampil();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
                    }   
                }catch (Exception ex) {
                    System.out.println("Notifikasi Bridging : "+ex);
                    if(ex.toString().contains("UnknownHostException")){
                        JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Maaf, Silahkan anda pilih terlebih dulu data yang mau anda ganti...\n Klik data pada table untuk memilih data...!!!!");
            }                
        }
    }//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnKeluar);
        }
    }//GEN-LAST:event_BtnEditKeyPressed

    private void DTPTanggalKontrol1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol1ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPTanggalKontrol1ItemStateChanged

    private void DTPTanggalKontrol2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalKontrol2ItemStateChanged
        R2.setSelected(true);
    }//GEN-LAST:event_DTPTanggalKontrol2ItemStateChanged

    private void TanggalKontrolKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalKontrolKeyPressed
        Valid.pindah(evt,TanggalSurat,BtnDokter);
    }//GEN-LAST:event_TanggalKontrolKeyPressed

    private void NoSuratKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NoSuratKeyPressed
        Valid.pindah(evt,TanggalSurat,TanggalKontrol);
    }//GEN-LAST:event_NoSuratKeyPressed

    private void BtnPoliKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPoliKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPoliActionPerformed(null);
        }else{
            Valid.pindah(evt,BtnDokter,TanggalKontrol);
        }
    }//GEN-LAST:event_BtnPoliKeyPressed

    private void BtnPoliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPoliActionPerformed
        poli.SetKontrol(NoSEP.getText(),"2: Rencana Kontrol",Valid.SetTgl(TanggalKontrol.getSelectedItem()+""));
        poli.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        poli.setLocationRelativeTo(internalFrame1);
        poli.setVisible(true);
    }//GEN-LAST:event_BtnPoliActionPerformed

    private void MnSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSuratActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("logo",Sequel.cariGambar("select gambar.bpjs from gambar")); 
            param.put("parameter",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()+"\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()));
            Valid.MyReportqry("rptBridgingSuratKontrol2.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select br.no_reg,bs.no_rawat,bs.no_sep,bs.no_kartu,bs.nomr,bs.nama_pasien,bs.tanggal_lahir, \n" +
"                    bs.jkel,bs.diagawal,bs.nmdiagnosaawal,bskb.tgl_surat,bskb.no_surat, \n" +
"                    bskb.tgl_rencana,bskb.kd_dokter_bpjs,bskb.nm_dokter_bpjs, \n" +
"                    bskb.kd_poli_bpjs,bskb.nm_poli_bpjs, \n" +
"                    IF(\n" +
                "        (CASE \n" +
                "            WHEN br.kd_dokter IN ('B17072334', 'B17052556') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 1800 - 1800))\n" +
                "            WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 - 1800))\n" +
                "            ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800 - 1800))\n" +
                "         END) >= j.jam_selesai,\n" +
                "        CONCAT(j.jam_mulai, ' - ', j.jam_selesai),\n" +
                "        CONCAT(\n" +
                "            CASE \n" +
                "                WHEN br.kd_dokter IN ('B17072334', 'B17052556') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 1800 - 1800))\n" +
                "                WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 - 1800))\n" +
                "                ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800 - 1800))\n" +
                "            END,\n" +
                "            ' - ',\n" +
                "            IF(\n" +
                "                CASE \n" +
                "                    WHEN br.kd_dokter IN ('B17072334', 'B17052556') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 1800))\n" +
                "                    WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 + 1800))\n" +
                "                    ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800))\n" +
                "                END > j.jam_selesai,\n" +
                "                j.jam_selesai,\n" +
                "                CASE \n" +
                "                    WHEN br.kd_dokter IN ('B17072334', 'B17052556') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 1800))\n" +
                "                    WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 + 1800))\n" +
                "                    ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800))\n" +
                "                END\n" +
                "            )\n" +
                "        )\n" +
                "    ) AS estimasi_dilayani\n" +
                "FROM bridging_sep bs \n" +
                "INNER JOIN bridging_surat_kontrol_bpjs bskb ON bskb.no_sep = bs.no_sep \n" +
                "INNER JOIN maping_dokter_dpjpvclaim mdd ON bskb.kd_dokter_bpjs = mdd.kd_dokter_bpjs \n" +
                "INNER JOIN dokter d ON d.kd_dokter = mdd.kd_dokter \n" +
                "INNER JOIN jadwal j ON j.kd_dokter = d.kd_dokter \n" +
                "INNER JOIN booking_registrasi br ON br.no_rkm_medis = bs.nomr AND br.tanggal_periksa = bskb.tgl_rencana\n" +
                "WHERE bskb.no_surat = '"+NoSurat.getText()+"' \n" +
                "AND DAYNAME(bskb.tgl_rencana) = \n" +
                "    CASE \n" +
                "        WHEN j.hari_kerja = 'AKHAD'  THEN 'Sunday' \n" +
                "        WHEN j.hari_kerja = 'SENIN'  THEN 'Monday' \n" +
                "        WHEN j.hari_kerja = 'SELASA' THEN 'Tuesday' \n" +
                "        WHEN j.hari_kerja = 'RABU'   THEN 'Wednesday' \n" +
                "        WHEN j.hari_kerja = 'KAMIS'  THEN 'Thursday' \n" +
                "        WHEN j.hari_kerja = 'JUMAT'  THEN 'Friday' \n" +
                "        WHEN j.hari_kerja = 'SABTU'  THEN 'Saturday' \n" +
                "    END AND br.tanggal_periksa=bskb.tgl_rencana",param);              
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }  
    }//GEN-LAST:event_MnSuratActionPerformed

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

    private void DTPTanggalSurat1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1KeyPressed

    }//GEN-LAST:event_DTPTanggalSurat1KeyPressed

    private void DTPTanggalSurat1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTanggalSurat1ItemStateChanged
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat1ItemStateChanged

    private void DTPTanggalSurat2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTanggalSurat2KeyPressed
        R1.setSelected(true);
    }//GEN-LAST:event_DTPTanggalSurat2KeyPressed

    private void TanggalKontrolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_TanggalKontrolItemStateChanged
        try {
            isNomer();
        } catch (Exception e) {
        }// TODO add your handling code here:
    }//GEN-LAST:event_TanggalKontrolItemStateChanged

    private void MnReservasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnReservasiActionPerformed
        if(Sequel.cariInteger("select count(no_rkm_medis) from booking_registrasi where tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' and no_rkm_medis='"+NoRM.getText()+"'")>0){
            NmPoli1.setText(Sequel.cariIsi("select p.nm_poli from booking_registrasi br inner join poliklinik p on p.kd_poli=br.kd_poli where br.tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' and br.no_rkm_medis='"+NoRM.getText()+"'"));
            JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah reservasi ke '"+NmPoli1.getText()+"' pada tanggal '"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' ");
        }else if(Sequel.cariInteger("select count(no_rkm_medis) from reg_periksa where tgl_registrasi='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' and no_rkm_medis='"+NoRM.getText()+"'")>0){
            NmPoli1.setText(Sequel.cariIsi("select p.nm_poli from reg_periksa br inner join poliklinik p on p.kd_poli=br.kd_poli where br.tgl_registrasi='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' and br.no_rkm_medis='"+NoRM.getText()+"'"));
            JOptionPane.showMessageDialog(null,"Maaf, Pasien sudah terdaftar ke '"+NmPoli1.getText()+"' pada tanggal '"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"' ");
        }else{
            if(Sequel.menyimpantf("booking_registrasi","?,?,?,?,?,?,?,?,?,?,?,?","Pasien dan Tanggal",12,new String[]{
             Valid.SetTgl(TanggalBooking.getSelectedItem()+""),TanggalBooking.getSelectedItem().toString().substring(11,19),NoRM.getText(),
             Valid.SetTgl(TanggalKontrol.getSelectedItem()+""),KdDokter1.getText(),
             KdPoli1.getText(),NoReg.getText(),"BPJ","1",
             Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+" "+TanggalBooking.getSelectedItem().toString().substring(11,19),
             "Belum","SIPP"
           })==true){
            Sequel.menyimpan("booking_registrasi_rujukan","'"+NoRM.getText()+"','"+Valid.SetTgl(TanggalBooking.getSelectedItem()+"")+"','"+TanggalBooking.getSelectedItem().toString().substring(11,19)+"','-'","no_rkm_medis='"+NoRM.getText()+"'");
            JOptionPane.showMessageDialog(null,"Reservasi sudah berhasil tersimpan");
            emptTeks();
            tampil();
            }
        }
    }//GEN-LAST:event_MnReservasiActionPerformed

    private void TanggalBookingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TanggalBookingKeyPressed
        Valid.pindah(evt,BtnPoli,BtnSimpan);
    }//GEN-LAST:event_TanggalBookingKeyPressed

    private void MnSurat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSurat1ActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("logo",Sequel.cariGambar("select gambar.bpjs from gambar")); 
            param.put("parameter",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()+"\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()));
            
            Valid.MyReportqry("rptBridgingSuratKontrol3.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bs.no_rawat,bs.no_sep,bs.no_kartu,bs.nomr,bs.nama_pasien,bs.tanggal_lahir,\n" +
                    "bs.jkel,bs.diagawal,bs.nmdiagnosaawal,bskb.tgl_surat,bskb.no_surat,\n" +
                    "bskb.kd_dokter_bpjs,bskb.nm_dokter_bpjs,\n" +
                    "bskb.kd_poli_bpjs,bskb.nm_poli_bpjs,\n" +
                    "bskb.tgl_rencana,IF(bskb.kd_dokter_bpjs='101152',\"\",CONCAT(j.jam_mulai,\" - \",j.jam_selesai)) as estimasi_dilayani\n" +
                    "from bridging_sep bs\n" +
                    "inner join bridging_surat_kontrol_bpjs bskb on bskb.no_sep=bs.no_sep\n" +
                    "inner join maping_dokter_dpjpvclaim mdd on bskb.kd_dokter_bpjs =mdd.kd_dokter_bpjs \n" +
                    "inner join dokter d on d.kd_dokter =mdd.kd_dokter \n" +
                    "inner join jadwal j on j.kd_dokter =d.kd_dokter\n" +
                    "where bskb.no_surat='"+NoSurat.getText()+"' AND DAYNAME(bskb.tgl_rencana) = \n" +
                    "    CASE \n" +
                    "        WHEN j.hari_kerja = 'AKHAD' THEN 'Sunday'\n" +
                    "        WHEN j.hari_kerja = 'SENIN' THEN 'Monday'\n" +
                    "        WHEN j.hari_kerja = 'SELASA' THEN 'Tuesday'\n" +
                    "        WHEN j.hari_kerja = 'RABU' THEN 'Wednesday'\n" +
                    "        WHEN j.hari_kerja = 'KAMIS' THEN 'Thursday'\n" +
                    "        WHEN j.hari_kerja = 'JUMAT' THEN 'Friday'\n" +
                    "        WHEN j.hari_kerja = 'SABTU' THEN 'Saturday'\n" +
                    "    END",param);             
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_MnSurat1ActionPerformed

    private void MnSurat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnSurat2ActionPerformed
        if(tbObat.getSelectedRow()!= -1){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            Map<String, Object> param = new HashMap<>();
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("logo",Sequel.cariGambar("select gambar.bpjs from gambar")); 
            param.put("parameter",tbObat.getValueAt(tbObat.getSelectedRow(),0).toString());
            param.put("finger","Dikeluarkan di "+akses.getnamars()+", Kabupaten/Kota "+akses.getkabupatenrs()+"\nDitandatangani secara elektronik oleh "+tbObat.getValueAt(tbObat.getSelectedRow(),12).toString()+"\nID "+tbObat.getValueAt(tbObat.getSelectedRow(),8).toString()+"\n"+Valid.SetTgl3(tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()));
            
            Valid.MyReportqry("rptBridgingSuratKontrol4.jasper","report","::[ Data Surat Kontrol VClaim ]::",
                    "select bs.no_rawat,bs.no_sep,bs.no_kartu,bs.nomr,bs.nama_pasien,bs.tanggal_lahir,\n" +
                    "bs.jkel,bs.diagawal,bs.nmdiagnosaawal,bskb.tgl_surat,bskb.no_surat,\n" +
                    "bskb.kd_dokter_bpjs,bskb.nm_dokter_bpjs,bskb.tgl_rencana,\n" +
                    "bskb.kd_poli_bpjs,bskb.nm_poli_bpjs\n" +
                    "from bridging_sep bs\n" +
                    "inner join bridging_surat_kontrol_bpjs bskb on bskb.no_sep=bs.no_sep\n" +
                    "inner join maping_dokter_dpjpvclaim mdd on bskb.kd_dokter_bpjs =mdd.kd_dokter_bpjs \n" +
                    "inner join dokter d on d.kd_dokter =mdd.kd_dokter \n" +
//                    "inner join jadwal j on j.kd_dokter =d.kd_dokter\n" +
                    "where bskb.no_surat='"+NoSurat.getText()+"' ",param);             
            this.setCursor(Cursor.getDefaultCursor());
        }else{
            JOptionPane.showMessageDialog(null,"Maaf, silahkan pilih data Surat Kontrol yang mau dicetak...!!!!");
            BtnBatal.requestFocus();
        }
    }//GEN-LAST:event_MnSurat2ActionPerformed

    private void KirimWaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KirimWaActionPerformed
        if (tbObat.getSelectedRow() != -1) {
            // 1. Ambil Nomor HP
            if(Sequel.cariInteger("select no_surat from kirim_wa_status where no_surat='"+NoSurat.getText()+"'")>0){
                Sequel.meghapus("kirim_wa_status","no_surat",NoSurat.getText());   
            }
            String noHP = Sequel.cariIsi("select no_tlp from pasien where no_rkm_medis=?", NoRM.getText());

            if (noHP.equals("") || noHP.equals("-")) {
                JOptionPane.showMessageDialog(null, "Nomor telp pasien kosong di data rekam medis!");
                return;
            }

            // 2. Ambil Estimasi Jam Layanan dari Database (Query SQL Kompleks Anda)
            String estimasiLayanan = "";
            String cariReservasi ="SELECT COUNT(br.no_rkm_medis) FROM booking_registrasi br WHERE br.no_rkm_medis='"+NoRM.getText()+"' AND br.tanggal_periksa='"+tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()+"'";
            String Reservasi = Sequel.cariIsi(cariReservasi);
            
            if(Reservasi.equals("0")){
                String sqlEstimasi = "select IF(bskb.kd_dokter_bpjs='101152',\"\",CONCAT(j.jam_mulai,\" - \",j.jam_selesai)) as estimasi_dilayani\n" +
                    "from bridging_sep bs\n" +
                    "inner join bridging_surat_kontrol_bpjs bskb on bskb.no_sep=bs.no_sep\n" +
                    "inner join maping_dokter_dpjpvclaim mdd on bskb.kd_dokter_bpjs =mdd.kd_dokter_bpjs \n" +
                    "inner join dokter d on d.kd_dokter =mdd.kd_dokter \n" +
                    "inner join jadwal j on j.kd_dokter =d.kd_dokter\n" +
                    "where bskb.no_surat='"+NoSurat.getText()+"' AND DAYNAME(bskb.tgl_rencana) = \n" +
                    "    CASE \n" +
                    "        WHEN j.hari_kerja = 'AKHAD' THEN 'Sunday'\n" +
                    "        WHEN j.hari_kerja = 'SENIN' THEN 'Monday'\n" +
                    "        WHEN j.hari_kerja = 'SELASA' THEN 'Tuesday'\n" +
                    "        WHEN j.hari_kerja = 'RABU' THEN 'Wednesday'\n" +
                    "        WHEN j.hari_kerja = 'KAMIS' THEN 'Thursday'\n" +
                    "        WHEN j.hari_kerja = 'JUMAT' THEN 'Friday'\n" +
                    "        WHEN j.hari_kerja = 'SABTU' THEN 'Saturday'\n" +
                    "    END;";
                estimasiLayanan = Sequel.cariIsi(sqlEstimasi);
            }else{
                String sqlEstimasi = "SELECT \n" +
                "    IF(\n" +
                "    -- 1. Cek apakah jam mulai estimasi sudah melewati jam selesai praktek\n" +
                "    (CASE \n" +
                "        WHEN br.kd_dokter = 'B17072334' THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 20) * 3600 - 1800))\n" +
                "        WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 - 1800))\n" +
                "        ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800 - 1800))\n" +
                "     END) >= j.jam_selesai,\n" +
                "    \n" +
                "    -- Jika ya, tampilkan jam standar\n" +
                "    CONCAT(j.jam_mulai, ' - ', j.jam_selesai),\n" +
                "    \n" +
                "    -- Jika tidak, buat range estimasi\n" +
                "    CONCAT(\n" +
                "        -- Jam Mulai Estimasi\n" +
                "        CASE \n" +
                "            WHEN br.kd_dokter = 'B17072334' THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 20) * 3600 - 1800))\n" +
                "            WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 - 1800))\n" +
                "            ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800 - 1800))\n" +
                "        END,\n" +
                "        ' - ',\n" +
                "        -- Jam Selesai Estimasi (di-cap agar tidak melebihi j.jam_selesai)\n" +
                "        IF(\n" +
                "            CASE \n" +
                "                WHEN br.kd_dokter = 'B17072334' THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 20) * 3600 + 1800))\n" +
                "                WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 + 1800))\n" +
                "                ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800))\n" +
                "            END > j.jam_selesai,\n" +
                "            j.jam_selesai,\n" +
                "            CASE \n" +
                "                WHEN br.kd_dokter = 'B17072334' THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 20) * 3600 + 1800))\n" +
                "                WHEN br.kd_dokter IN ('B17042443', 'B17102222', 'B17122561') THEN ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 10) * 3600 + 1800))\n" +
                "                ELSE ADDTIME(j.jam_mulai, SEC_TO_TIME(FLOOR((br.no_reg - 1) / 5) * 1800))\n" +
                "            END\n" +
                "        )\n" +
                "    )\n" +
                ") AS estimasi_dilayani\n" +
                "FROM bridging_sep bs \n" +
                "INNER JOIN bridging_surat_kontrol_bpjs bskb ON bskb.no_sep = bs.no_sep \n" +
                "INNER JOIN maping_dokter_dpjpvclaim mdd ON bskb.kd_dokter_bpjs = mdd.kd_dokter_bpjs \n" +
                "INNER JOIN dokter d ON d.kd_dokter = mdd.kd_dokter \n" +
                "INNER JOIN jadwal j ON j.kd_dokter = d.kd_dokter \n" +
                "INNER JOIN booking_registrasi br ON br.no_rkm_medis = bs.nomr AND br.tanggal_periksa = bskb.tgl_rencana\n" +
                "WHERE bskb.no_surat = '"+NoSurat.getText()+"'\n" +
                "AND DAYNAME(bskb.tgl_rencana) = \n" +
                "    CASE \n" +
                "        WHEN j.hari_kerja = 'AKHAD'  THEN 'Sunday' \n" +
                "        WHEN j.hari_kerja = 'SENIN'  THEN 'Monday' \n" +
                "        WHEN j.hari_kerja = 'SELASA' THEN 'Tuesday' \n" +
                "        WHEN j.hari_kerja = 'RABU'   THEN 'Wednesday' \n" +
                "        WHEN j.hari_kerja = 'KAMIS'  THEN 'Thursday' \n" +
                "        WHEN j.hari_kerja = 'JUMAT'  THEN 'Friday' \n" +
                "        WHEN j.hari_kerja = 'SABTU'  THEN 'Saturday' \n" +
                "    END;";
                estimasiLayanan = Sequel.cariIsi(sqlEstimasi);
            }

            // 3. Konfigurasi Fonnte
            String tokenFonnte = "prZsGU6Bmt5BuXjYB5Ts"; 

            try {
                org.springframework.util.LinkedMultiValueMap<String, String> dataForm = new org.springframework.util.LinkedMultiValueMap<>();

                // Target WA (Gunakan noHP dari variabel)
                dataForm.add("target", noHP); 
                if(NoBook.getText().equals("")){
                    // Pesan Gabungan
                    dataForm.add("message", "Yth " +NmPasien.getText()+ ", berikut adalah Surat Kontrol Anda ke " +NmPoli1.getText()+ " dengan "
                            + "dokter "+NmDokter1.getText()+" "
                            + "\n\nNama Pasien : "+NmPasien.getText()+""
                            + "\nTgl. Lahir : "+TglLahir.getText()
                            + "\nNo. Kartu : "+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()
                            + "\nNo. Surat : "+NoSurat.getText()+" "
                            + "\nBPJS Kesehatan"
                            + "\n\nTgl Kontrol selanjutnya: "+TanggalKontrol.getSelectedItem()
                            + "\nNomor antrean di Poliklinik : Belum reservasi"
                            + "\nEstimasi Kedatangan : Belum reservasi"
                            + "\nMohon melakukan reservasi melalui aplikasi mobile JKN agar memperoleh nomor antrean di poliklinik dan estimasi kedatangan untuk kontrol selanjutnya. Nomor dan estimasi kedatangan dapat dilihat di aplikasi mobile JKN."
    //                        + "Berikut langkah-langkah membuat akun Mobile JKN dan Reservasi ke poliklinik: https://youtube.com/shorts/TNobOhI1qAs?si=biEIKBJb8sWWwY64"
                            + "\n\nMohon ijin menginformasikan, surat rujukan ke "+NmPoli1.getText()+"  berakhir pada tgl "+tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()+", "
                            + "apabila tanggal kontrol selanjutnya setelah tanggal tersebut, mohon bapak/ibu memperpanjang rujukan ke Faskes Tingkat Pertama (FKTP) terlebih dahulu."
                            + "\n\nNomor antrean yg dipanggil dimasing-masing poliklinik adalah nomor antrean reservasi bukan berdasarkan urutan kedatangan di rumah sakit pada hari kontrol."
                            + "\nMohon serahkan berkas-berkas pemeriksaaan pasien ke dokter spesialis ketika nomor antrean pasien sudah dipanggil di masing-masing poliklinik."
                            + "\n\nJika ada kendala atau pertanyaan dapat menghubungi kami melalui nomor Telepon: 03618982888 atau WhatsApp: 081999774696."
                            + "\nJika ada kritik dan saran terkait pelayanan kesehatan yang kami berikan dapat menghubungi humas kami melalui WhatsApp: 087787193298."
                            + "\n\nTerima kasih"
                            + "\nRS Windu Husada");
                }else{
                    // Pesan Gabungan
                    dataForm.add("message", "Yth " +NmPasien.getText()+ ", berikut adalah Surat Kontrol Anda ke " +NmPoli1.getText()+ " dengan "
                            + "dokter "+NmDokter1.getText()+" "
                            + "\n\nNama Pasien : "+NmPasien.getText()+""
                            + "\nTgl. Lahir : "+TglLahir.getText()
                            + "\nNo. Kartu : "+tbObat.getValueAt(tbObat.getSelectedRow(),2).toString()
                            + "\nNo. Surat : "+NoSurat.getText()+" "
                            + "\nBPJS Kesehatan"
                            + "\n\nTgl Kontrol selanjutnya: "+TanggalKontrol.getSelectedItem()
                            + "\nNomor antrean di Poliklinik : "+NoBook.getText()+""
                            + "\nEstimasi Kedatangan : "+estimasiLayanan+" WITA"
                            + "\nMohon hadir tepat waktu sesuai estimasi kedatangan."
//                            + "Berikut langkah-langkah membuat akun Mobile JKN dan Reservasi ke poliklinik: https://youtube.com/shorts/TNobOhI1qAs?si=biEIKBJb8sWWwY64"
                            + "\n\nMohon ijin menginformasikan, surat rujukan ke "+NmPoli1.getText()+"  berakhir pada tgl "+tbObat.getValueAt(tbObat.getSelectedRow(),10).toString()+", "
                            + "apabila tanggal kontrol selanjutnya setelah tanggal tersebut, mohon bapak/ibu memperpanjang rujukan ke Faskes Tingkat Pertama (FKTP) terlebih dahulu."
                            + "\n\nNomor antrean yg dipanggil dimasing-masing poliklinik adalah nomor antrean reservasi bukan berdasarkan urutan kedatangan di rumah sakit pada hari kontrol."
                            + "\nMohon serahkan berkas-berkas pemeriksaaan pasien ke dokter spesialis ketika nomor antrean pasien sudah dipanggil di masing-masing poliklinik."
                            + "\n\nJika ada kendala atau pertanyaan dapat menghubungi kami melalui nomor Telepon: 03618982888 atau WhatsApp: 081999774696."
                            + "\nJika ada kritik dan saran terkait pelayanan kesehatan yang kami berikan dapat menghubungi humas kami melalui WhatsApp: 087787193298."
                            + "\n\nTerima kasih"
                            + "\nRS Windu Husada");
                    }
                
                // Set Header
                org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
                headers.setContentType(org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED);
                headers.add("Authorization", tokenFonnte);

                org.springframework.http.HttpEntity<org.springframework.util.MultiValueMap<String, String>> request = 
                        new org.springframework.http.HttpEntity<>(dataForm, headers);

                // Eksekusi
                RestTemplate restTemplate = new RestTemplate();
                String response = restTemplate.postForObject("https://api.fonnte.com/send", request, String.class);

                if (response != null && response.contains("true")) {
                    JOptionPane.showMessageDialog(null, "Surat Kontrol Berhasil dikirim");
                    Sequel.menyimpan("kirim_wa_status","'"+NoSurat.getText()+"','Sukses'","no_surat='"+NoSurat.getText()+"'");
                    tampil();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal kirim. Respon Server: " + response);
                    Sequel.menyimpan("kirim_wa_status","'"+NoSurat.getText()+"','Gagal'","no_surat='"+NoSurat.getText()+"'");
                    tampil();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error Koneksi: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pilih data pasien dulu di tabel!");
        }   
    }//GEN-LAST:event_KirimWaActionPerformed

    private void NoSuratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoSuratActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NoSuratActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            BPJSSuratKontrol dialog = new BPJSSuratKontrol(new javax.swing.JFrame(), true);
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
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnDokter;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPoli;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox CaraBook;
    private widget.CekBox ChkInput;
    private widget.Tanggal DTPTanggalKontrol1;
    private widget.Tanggal DTPTanggalKontrol2;
    private widget.Tanggal DTPTanggalSurat1;
    private widget.Tanggal DTPTanggalSurat2;
    private widget.TextBox Diagnosa;
    private widget.PanelBiasa FormInput;
    private widget.TextBox JK;
    private widget.TextBox KdDokter;
    private widget.TextBox KdDokter1;
    private widget.TextBox KdPoli;
    private widget.TextBox KdPoli1;
    private javax.swing.JMenuItem KirimWa;
    private widget.Label LCount;
    private widget.Label LCount1;
    private javax.swing.JMenuItem MnReservasi;
    private javax.swing.JMenuItem MnSurat;
    private javax.swing.JMenuItem MnSurat1;
    private javax.swing.JMenuItem MnSurat2;
    private widget.TextBox NmDokter;
    private widget.TextBox NmDokter1;
    private widget.TextBox NmPasien;
    private widget.TextBox NmPoli;
    private widget.TextBox NmPoli1;
    private widget.TextBox NmPoli2;
    private widget.TextBox NoBook;
    private widget.TextBox NoKartu;
    private widget.TextBox NoRM;
    private widget.TextBox NoRawat;
    private widget.TextBox NoReg;
    private widget.TextBox NoSEP;
    private widget.TextBox NoSurat;
    private javax.swing.JPanel PanelInput;
    private widget.RadioButton R1;
    private widget.RadioButton R2;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.Tanggal TanggalBooking;
    private widget.Tanggal TanggalKontrol;
    private widget.Tanggal TanggalSurat;
    private widget.TextBox TglLahir;
    private javax.swing.ButtonGroup buttonGroup1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel10;
    private widget.Label jLabel11;
    private widget.Label jLabel12;
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
    private widget.Label jLabel4;
    private widget.Label jLabel5;
    private widget.Label jLabel6;
    private widget.Label jLabel7;
    private widget.Label jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.panelisi panelCari;
    private widget.panelisi panelGlass10;
    private widget.panelisi panelGlass8;
    private widget.Table tbObat;
    // End of variables declaration//GEN-END:variables

    private void tampil() {     
        Valid.tabelKosong(tabMode);
        try {
           if(R1.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,TRIM(bridging_surat_kontrol_bpjs.nm_poli_bpjs) as nm_poli_bpjs,pasien.no_tlp,bridging_sep.tglrujukan,\n" +
                    "CONCAT(\n" +
                    "    DATE_FORMAT(DATE_ADD(bridging_sep.tglrujukan, INTERVAL 90 DAY), '%d '),\n" +
                    "    CASE MONTH(DATE_ADD(bridging_sep.tglrujukan, INTERVAL 90 DAY))\n" +
                    "        WHEN 1 THEN 'Januari' WHEN 2 THEN 'Februari' WHEN 3 THEN 'Maret'\n" +
                    "        WHEN 4 THEN 'April' WHEN 5 THEN 'Mei' WHEN 6 THEN 'Juni'\n" +
                    "        WHEN 7 THEN 'Juli' WHEN 8 THEN 'Agustus' WHEN 9 THEN 'September'\n" +
                    "        WHEN 10 THEN 'Oktober' WHEN 11 THEN 'November' WHEN 12 THEN 'Desember'\n" +
                    "    END,\n" +
                    "    DATE_FORMAT(DATE_ADD(bridging_sep.tglrujukan, INTERVAL 90 DAY), ' %Y')\n" +
                    ") AS expired,(\n" +
                    "        SELECT GROUP_CONCAT(t.status ORDER BY t.tanggal,t.jam SEPARATOR ', ')\n" +
                    "        FROM bridging_surat_kontrol_bpjs_tambahan t\n" +
                    "        WHERE t.no_rawat = bridging_sep.no_rawat \n" +
                    "    ) AS status from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep INNER join reg_periksa on reg_periksa.no_rawat=bridging_sep.no_rawat\n" +
                    "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                    "where bridging_surat_kontrol_bpjs.tgl_surat between ? and ? "+
                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "+
                    "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "+
                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")+
                    "order by bridging_surat_kontrol_bpjs.tgl_surat");
                try {
                    ps.setString(1,Valid.SetTgl(DTPTanggalSurat1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPTanggalSurat2.getSelectedItem()+""));
                    if(!TCari.getText().trim().equals("")){
                        ps.setString(3,"%"+TCari.getText().trim()+"%");
                        ps.setString(4,"%"+TCari.getText().trim()+"%");
                        ps.setString(5,"%"+TCari.getText().trim()+"%");
                        ps.setString(6,"%"+TCari.getText().trim()+"%");
                        ps.setString(7,"%"+TCari.getText().trim()+"%");
                        ps.setString(8,"%"+TCari.getText().trim()+"%");
                        ps.setString(9,"%"+TCari.getText().trim()+"%");
                        ps.setString(10,"%"+TCari.getText().trim()+"%");
                    }
                        
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabMode.addRow(new Object[]{
                            rs.getString("no_rawat"),rs.getString("no_sep"),rs.getString("no_kartu"),rs.getString("nomr"),rs.getString("nama_pasien"),
                            rs.getString("tanggal_lahir"),rs.getString("jkel"),rs.getString("nmdiagnosaawal"),rs.getString("no_tlp"),rs.getString("tglrujukan"),rs.getString("expired"),
                            rs.getString("tgl_surat"),rs.getString("no_surat"),rs.getString("tgl_rencana"),rs.getString("kd_dokter_bpjs"),rs.getString("nm_dokter_bpjs"),
                            rs.getString("kd_poli_bpjs"),rs.getString("nm_poli_bpjs"),rs.getString("status")
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
            }else if(R2.isSelected()==true){
                ps=koneksi.prepareStatement(
                    "select bridging_sep.no_rawat,bridging_sep.no_sep,bridging_sep.no_kartu,bridging_sep.nomr,bridging_sep.nama_pasien,bridging_sep.tanggal_lahir,"+
                    "bridging_sep.jkel,bridging_sep.diagawal,bridging_sep.nmdiagnosaawal,bridging_surat_kontrol_bpjs.tgl_surat,bridging_surat_kontrol_bpjs.no_surat,"+
                    "bridging_surat_kontrol_bpjs.tgl_rencana,bridging_surat_kontrol_bpjs.kd_dokter_bpjs,bridging_surat_kontrol_bpjs.nm_dokter_bpjs,"+
                    "bridging_surat_kontrol_bpjs.kd_poli_bpjs,TRIM(bridging_surat_kontrol_bpjs.nm_poli_bpjs) as nm_poli_bpjs,pasien.no_tlp,bridging_sep.tglrujukan,\n "+
                    "CONCAT(\n" +
                    "    DATE_FORMAT(DATE_ADD(bridging_sep.tglrujukan, INTERVAL 90 DAY), '%d '),\n" +
                    "    CASE MONTH(DATE_ADD(bridging_sep.tglrujukan, INTERVAL 90 DAY))\n" +
                    "        WHEN 1 THEN 'Januari' WHEN 2 THEN 'Februari' WHEN 3 THEN 'Maret'\n" +
                    "        WHEN 4 THEN 'April' WHEN 5 THEN 'Mei' WHEN 6 THEN 'Juni'\n" +
                    "        WHEN 7 THEN 'Juli' WHEN 8 THEN 'Agustus' WHEN 9 THEN 'September'\n" +
                    "        WHEN 10 THEN 'Oktober' WHEN 11 THEN 'November' WHEN 12 THEN 'Desember'\n" +
                    "    END,\n" +
                    "    DATE_FORMAT(DATE_ADD(bridging_sep.tglrujukan, INTERVAL 90 DAY), ' %Y')\n" +
                    ") AS expired,bridging_surat_kontrol_bpjs_tambahan.status from bridging_sep inner join bridging_surat_kontrol_bpjs "+
                    "on bridging_surat_kontrol_bpjs.no_sep=bridging_sep.no_sep INNER join reg_periksa on reg_periksa.no_rawat=bridging_sep.no_rawat\n "+
                    "inner join pasien on pasien.no_rkm_medis=reg_periksa.no_rkm_medis "+
                    "left join bridging_surat_kontrol_bpjs_tambahan on bridging_sep.no_rawat=bridging_surat_kontrol_bpjs_tambahan.no_rawat "+
                    "where bridging_surat_kontrol_bpjs.tgl_rencana between ? and ? "+
                    (TCari.getText().trim().equals("")?"":"and (bridging_sep.no_rawat like ? or bridging_sep.no_sep like ? or bridging_sep.no_kartu like ? or "+
                    "bridging_sep.nomr like ? or bridging_sep.nama_pasien like ? or bridging_surat_kontrol_bpjs.no_surat like ? or "+
                    "bridging_surat_kontrol_bpjs.nm_poli_bpjs like ? or bridging_surat_kontrol_bpjs.nm_dokter_bpjs like ?)")+
                    "order by bridging_surat_kontrol_bpjs.tgl_rencana");
                try {
                    ps.setString(1,Valid.SetTgl(DTPTanggalKontrol1.getSelectedItem()+""));
                    ps.setString(2,Valid.SetTgl(DTPTanggalKontrol2.getSelectedItem()+""));
                    if(!TCari.getText().trim().equals("")){
                        ps.setString(3,"%"+TCari.getText().trim()+"%");
                        ps.setString(4,"%"+TCari.getText().trim()+"%");
                        ps.setString(5,"%"+TCari.getText().trim()+"%");
                        ps.setString(6,"%"+TCari.getText().trim()+"%");
                        ps.setString(7,"%"+TCari.getText().trim()+"%");
                        ps.setString(8,"%"+TCari.getText().trim()+"%");
                        ps.setString(9,"%"+TCari.getText().trim()+"%");
                        ps.setString(10,"%"+TCari.getText().trim()+"%");
                    }
                        
                    rs=ps.executeQuery();
                    while(rs.next()){
                        tabMode.addRow(new Object[]{
                            rs.getString("no_rawat"),rs.getString("no_sep"),rs.getString("no_kartu"),rs.getString("nomr"),rs.getString("nama_pasien"),
                            rs.getString("tanggal_lahir"),rs.getString("jkel"),rs.getString("nmdiagnosaawal"),rs.getString("no_tlp"),rs.getString("tglrujukan"),rs.getString("expired"),
                            rs.getString("tgl_surat"),rs.getString("no_surat"),rs.getString("tgl_rencana"),rs.getString("kd_dokter_bpjs"),rs.getString("nm_dokter_bpjs"),
                            rs.getString("kd_poli_bpjs"),rs.getString("nm_poli_bpjs"),rs.getString("status")
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
            }
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        } 
        LCount.setText(""+tabMode.getRowCount());
    }


    public void emptTeks() {
        NoRawat.setText("");
        NoSEP.setText("");
        NoKartu.setText("");
        NoRM.setText("");
        NmPasien.setText("");
        TglLahir.setText("");
        JK.setText("");
        Diagnosa.setText("");
        TanggalSurat.setDate(new Date());
        NoSurat.setText("");
        TanggalKontrol.setDate(new Date());
        KdDokter.setText("");
        NmDokter.setText("");
        KdPoli.setText("");
        NmPoli.setText("");
        TanggalSurat.requestFocus();
        isNomer();
    }
   
    private void isNomer(){
        switch (URUTNOREG) {
            case "poli":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_poli='"+KdPoli1.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3,NoReg);
                break;
            case "dokter":
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+KdDokter1.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3,NoReg);
                break;
            case "dokter + poli":             
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+KdDokter1.getText()+"' and kd_poli='"+KdPoli1.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3,NoReg);
                break;
            default:
                Valid.autoNomer3("select ifnull(MAX(CONVERT(no_reg,signed)),0) from booking_registrasi where kd_dokter='"+KdDokter1.getText()+"' and tanggal_periksa='"+Valid.SetTgl(TanggalKontrol.getSelectedItem()+"")+"'","",3,NoReg);
                break;
        }
    }
    
    private void getData() {
        if(tbObat.getSelectedRow()!= -1){
            NoRawat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),0).toString()); 
            NoSEP.setText(tbObat.getValueAt(tbObat.getSelectedRow(),1).toString());
            NoRM.setText(tbObat.getValueAt(tbObat.getSelectedRow(),3).toString());
            NmPasien.setText(tbObat.getValueAt(tbObat.getSelectedRow(),4).toString());
            TglLahir.setText(tbObat.getValueAt(tbObat.getSelectedRow(),5).toString());
            JK.setText(tbObat.getValueAt(tbObat.getSelectedRow(),6).toString().replaceAll("P","PEREMPUAN").replaceAll("L","LAKI-LAKI"));
            Diagnosa.setText(tbObat.getValueAt(tbObat.getSelectedRow(),7).toString());
            NoSurat.setText(tbObat.getValueAt(tbObat.getSelectedRow(),12).toString());
            KdDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),14).toString());
            NmDokter.setText(tbObat.getValueAt(tbObat.getSelectedRow(),15).toString());
            KdPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),16).toString());
            NmPoli.setText(tbObat.getValueAt(tbObat.getSelectedRow(),17).toString());
            Valid.SetTgl(TanggalSurat,tbObat.getValueAt(tbObat.getSelectedRow(),11).toString());
            Valid.SetTgl(TanggalKontrol,tbObat.getValueAt(tbObat.getSelectedRow(),13).toString());
            bpoli=Sequel.cariIsi("SELECT p.kd_poli FROM poliklinik p inner join maping_poli_bpjs mpb on mpb.kd_poli_rs=p.kd_poli WHERE mpb.kd_poli_bpjs='"+tbObat.getValueAt(tbObat.getSelectedRow(),16).toString()+"'");
            bnpoli=Sequel.cariIsi("select nm_poli from poliklinik where kd_poli='"+bpoli+"'");
            bdokter=Sequel.cariIsi("SELECT d.kd_dokter FROM dokter d inner join maping_dokter_dpjpvclaim mdd on mdd.kd_dokter=d.kd_dokter WHERE mdd.kd_dokter_bpjs='"+tbObat.getValueAt(tbObat.getSelectedRow(),14).toString()+"'");
            bndokter=Sequel.cariIsi("select nm_dokter from dokter where kd_dokter='"+bdokter+"'");
            CaraBook.setText(Sequel.cariIsi("SELECT GROUP_CONCAT(IF(asal_booking = '', 'Onsite', asal_booking) ORDER BY tgl_registrasi DESC SEPARATOR ', ') AS asal_booking FROM (SELECT asal_booking, tgl_registrasi FROM reg_periksa WHERE no_rkm_medis = '"+NoRM.getText()+"' group by asal_booking) AS sub"));
        }
            KdDokter1.setText(bdokter);
            NmDokter1.setText(bndokter);
            KdPoli1.setText(bpoli);
            NmPoli1.setText(bnpoli);
            isNomer();
            NoBook.setText(Sequel.cariIsi("SELECT no_reg FROM booking_registrasi br WHERE tanggal_periksa ='"+tbObat.getValueAt(tbObat.getSelectedRow(),13).toString()+"' AND no_rkm_medis ='"+tbObat.getValueAt(tbObat.getSelectedRow(),3).toString()+"'"));
    }
    
    public void setNoRm(String norawat,String nosep,String nokartu,String norm,String namapasien,String tanggallahir,String jk,String diagnosa) {
        NoRawat.setText(norawat);
        NoSEP.setText(nosep);
        NoKartu.setText(nokartu);
        NoRM.setText(norm);
        NmPasien.setText(namapasien);
        TglLahir.setText(tanggallahir);
        JK.setText(jk.replaceAll("L","LAKI-LAKI").replaceAll("P","PEREMPUAN"));
        Diagnosa.setText(diagnosa);
        TCari.setText(nosep);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }
    
    public void setNoRm(String norm) {
        TCari.setText(norm);
        ChkInput.setSelected(true);
        isForm();
        tampil();
    }
    
    private void isForm(){
        if(ChkInput.isSelected()==true){
            ChkInput.setVisible(false);
            PanelInput.setPreferredSize(new Dimension(WIDTH,200));
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
        BtnSimpan.setEnabled(akses.getbpjs_surat_kontrol());
        BtnHapus.setEnabled(akses.getbpjs_surat_kontrol());
        BtnPrint.setEnabled(akses.getbpjs_surat_kontrol());
        BtnEdit.setEnabled(akses.getbpjs_surat_kontrol());
    }

    public JTable getTable(){
        return tbObat;
    }
    
    public static class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    @Test
    public void bodyWithDeleteRequest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
    
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory(){
            @Override
            protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
                if (HttpMethod.DELETE == httpMethod) {
                    return new HttpEntityEnclosingDeleteRequest(uri);
                }
                return super.createHttpUriRequest(httpMethod, uri);
            }
        };
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        restTemplate.setRequestFactory(factory);
        
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.add("X-Cons-ID",koneksiDB.CONSIDAPIBPJS());
            utc=String.valueOf(api.GetUTCdatetimeAsString());
	    headers.add("X-Timestamp",utc);
	    headers.add("X-Signature",api.getHmac(utc));
            headers.add("user_key",koneksiDB.USERKEYAPIBPJS());
            URL = link+"/RencanaKontrol/Delete";
            requestJson ="{\"request\":{\"t_suratkontrol\":{\"noSuratKontrol\":\""+NoSurat.getText()+"\",\"user\":\""+user+"\"}}}";            
            requestEntity = new HttpEntity(requestJson,headers);
            root = mapper.readTree(restTemplate.exchange(URL, HttpMethod.DELETE,requestEntity, String.class).getBody());
            nameNode = root.path("metaData");
            System.out.println("code : "+nameNode.path("code").asText());
            System.out.println("message : "+nameNode.path("message").asText());
            if(nameNode.path("code").asText().equals("200")){
                Sequel.meghapus("bridging_surat_kontrol_bpjs","no_surat",NoSurat.getText());
                tabMode.removeRow(tbObat.getSelectedRow());
                emptTeks();
            }else{
                JOptionPane.showMessageDialog(null,nameNode.path("message").asText());
            }
        } catch (Exception e) {   
            System.out.println("Notif : "+e);
            if(e.toString().contains("UnknownHostException")){
                JOptionPane.showMessageDialog(null,"Koneksi ke server BPJS terputus...!");
            }
        }
    }
}
