package laporan;
import ipsrs.*;
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
import simrskhanza.DlgCariCaraBayar;

public class DlgRekapIGD extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    private PreparedStatement ps,ps2,ps3;
    private ResultSet rs,rs2,rs3;
    private int i=0,z=0,x=1,totallebraj2=0,totalkurraj2=0,totallebran2=0,totalkurran2=0;
    private boolean[] pilihan;
    private String[] kodebarang,namabarang,satuan,jenis,jumlah,kodesat,ruangan,keterangan;
    private double harga=0,jml=0;
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgRekapIGD(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"No","Tgl Masuk","No RM","Nama Pasien","Usia","Jenis Kelamin","Asuransi","Kelas","Alamat","Diagnosis","DPJP",
            "Status Masuk","Status Keluar","Ruangan","Tanggal","Jam Masuk","Tanggal","Jam Keluar","Waktu","Selisih","Lama","No Rawat","Alasan"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){
                boolean a = false;
                if (colIndex==0) {
                    a=true;
                }
                return a;
             }
             Class[] types = new Class[] {
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class, 
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class
                
             };
             /*Class[] types = new Class[] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };*/
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0;i < 23; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(25);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(75);
            }else if(i==3){
                column.setPreferredWidth(300);
            }else if(i==4){
                column.setPreferredWidth(75);
            }else if(i==5){
                column.setPreferredWidth(125);
            }else if(i==6){
                column.setPreferredWidth(200);
            }else if(i==7){
                column.setPreferredWidth(50);
            }else if(i==8){
                column.setPreferredWidth(300);
            }else if(i==9){
                column.setPreferredWidth(300);
            }else if(i==10){
                column.setPreferredWidth(300);
            }else if(i==11){
                column.setPreferredWidth(250);
            }else if(i==12){
                column.setPreferredWidth(150);
            }else if(i==13){
                column.setPreferredWidth(150);
            }else if(i==14){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==15){
                column.setPreferredWidth(75);
            }else if(i==16){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==17){
                column.setPreferredWidth(75);
            }else if(i==18){
                column.setPreferredWidth(75);
            }else if(i==19){
                column.setPreferredWidth(75);
            }else if(i==20){
                column.setPreferredWidth(75);
            }else if(i==21){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==22){
                column.setPreferredWidth(450);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());   
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        prosesCari();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        prosesCari();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        prosesCari();
                    }
                }
            });
        }
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Popup2 = new javax.swing.JPopupMenu();
        Alasan = new javax.swing.JMenuItem();
        Alasan1 = new javax.swing.JMenuItem();
        Alasan2 = new javax.swing.JMenuItem();
        Alasan3 = new javax.swing.JMenuItem();
        Alasan4 = new javax.swing.JMenuItem();
        Alasan5 = new javax.swing.JMenuItem();
        Alasan6 = new javax.swing.JMenuItem();
        Alasan7 = new javax.swing.JMenuItem();
        Alasan8 = new javax.swing.JMenuItem();
        Alasan9 = new javax.swing.JMenuItem();
        Alasan10 = new javax.swing.JMenuItem();
        Alasan11 = new javax.swing.JMenuItem();
        Alasan12 = new javax.swing.JMenuItem();
        Alasan13 = new javax.swing.JMenuItem();
        Alasan14 = new javax.swing.JMenuItem();
        Alasan15 = new javax.swing.JMenuItem();
        Alasan16 = new javax.swing.JMenuItem();
        Alasan17 = new javax.swing.JMenuItem();
        Alasan18 = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        panelisi1 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        BtnKeluar = new widget.Button();

        Popup2.setName("Popup2"); // NOI18N

        tbDokter.setToolTipText("Silahkan klik untuk memilih data yang mau diedit");
        tbDokter.setComponentPopupMenu(Popup2);

        Alasan.setBackground(new java.awt.Color(255, 255, 254));
        Alasan.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan.setForeground(new java.awt.Color(50, 50, 50));
        Alasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan.setText("Sesuai Target");
        Alasan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan.setName("Alasan"); // NOI18N
        Alasan.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlasanActionPerformed(evt);
            }
        });
        Popup2.add(Alasan);

        Alasan1.setBackground(new java.awt.Color(255, 255, 254));
        Alasan1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan1.setForeground(new java.awt.Color(50, 50, 50));
        Alasan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan1.setText("Menunggu Ruangan Siap");
        Alasan1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan1.setName("Alasan1"); // NOI18N
        Alasan1.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan1ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan1);

        Alasan2.setBackground(new java.awt.Color(255, 255, 254));
        Alasan2.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan2.setForeground(new java.awt.Color(50, 50, 50));
        Alasan2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan2.setText("Pasien Lama Memutuskan Setuju Ranap");
        Alasan2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan2.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan2.setName("Alasan2"); // NOI18N
        Alasan2.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan2ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan2);

        Alasan3.setBackground(new java.awt.Color(255, 255, 254));
        Alasan3.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan3.setForeground(new java.awt.Color(50, 50, 50));
        Alasan3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan3.setText("Pasien Lama Memutuskan Setuju Tindakan Medis");
        Alasan3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan3.setName("Alasan3"); // NOI18N
        Alasan3.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan3ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan3);

        Alasan4.setBackground(new java.awt.Color(255, 255, 254));
        Alasan4.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan4.setForeground(new java.awt.Color(50, 50, 50));
        Alasan4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan4.setText("Tidak Ada Penanggung Jawab Pasien");
        Alasan4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan4.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan4.setName("Alasan4"); // NOI18N
        Alasan4.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan4ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan4);

        Alasan5.setBackground(new java.awt.Color(255, 255, 254));
        Alasan5.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan5.setForeground(new java.awt.Color(50, 50, 50));
        Alasan5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan5.setText("Menunggu Hasil Lab");
        Alasan5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan5.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan5.setName("Alasan5"); // NOI18N
        Alasan5.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan5ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan5);

        Alasan6.setBackground(new java.awt.Color(255, 255, 254));
        Alasan6.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan6.setForeground(new java.awt.Color(50, 50, 50));
        Alasan6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan6.setText("Menunggu Hasil Rontgen");
        Alasan6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan6.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan6.setName("Alasan6"); // NOI18N
        Alasan6.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan6ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan6);

        Alasan7.setBackground(new java.awt.Color(255, 255, 254));
        Alasan7.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan7.setForeground(new java.awt.Color(50, 50, 50));
        Alasan7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan7.setText("Mencari Rujukan");
        Alasan7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan7.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan7.setName("Alasan7"); // NOI18N
        Alasan7.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan7ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan7);

        Alasan8.setBackground(new java.awt.Color(255, 255, 254));
        Alasan8.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan8.setForeground(new java.awt.Color(50, 50, 50));
        Alasan8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan8.setText("Menunggu Obat");
        Alasan8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan8.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan8.setName("Alasan8"); // NOI18N
        Alasan8.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan8ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan8);

        Alasan9.setBackground(new java.awt.Color(255, 255, 254));
        Alasan9.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan9.setForeground(new java.awt.Color(50, 50, 50));
        Alasan9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan9.setText("Menunggu Pendaftaran");
        Alasan9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan9.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan9.setName("Alasan9"); // NOI18N
        Alasan9.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan9ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan9);

        Alasan10.setBackground(new java.awt.Color(255, 255, 254));
        Alasan10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan10.setForeground(new java.awt.Color(50, 50, 50));
        Alasan10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan10.setText("Butuh Perpanjangan Observasi");
        Alasan10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan10.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan10.setName("Alasan10"); // NOI18N
        Alasan10.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan10ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan10);

        Alasan11.setBackground(new java.awt.Color(255, 255, 254));
        Alasan11.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan11.setForeground(new java.awt.Color(50, 50, 50));
        Alasan11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan11.setText("Menunggu Keputusan Keluarga");
        Alasan11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan11.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan11.setName("Alasan11"); // NOI18N
        Alasan11.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan11ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan11);

        Alasan12.setBackground(new java.awt.Color(255, 255, 254));
        Alasan12.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan12.setForeground(new java.awt.Color(50, 50, 50));
        Alasan12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan12.setText("Menunggu USG Abdomen");
        Alasan12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan12.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan12.setName("Alasan12"); // NOI18N
        Alasan12.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan12ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan12);

        Alasan13.setBackground(new java.awt.Color(255, 255, 254));
        Alasan13.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan13.setForeground(new java.awt.Color(50, 50, 50));
        Alasan13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan13.setText("Menunggu Dilakukan Echo di Poli");
        Alasan13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan13.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan13.setName("Alasan13"); // NOI18N
        Alasan13.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan13ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan13);

        Alasan14.setBackground(new java.awt.Color(255, 255, 254));
        Alasan14.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan14.setForeground(new java.awt.Color(50, 50, 50));
        Alasan14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan14.setText("Menunggu Visite DPJP");
        Alasan14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan14.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan14.setName("Alasan14"); // NOI18N
        Alasan14.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan14ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan14);

        Alasan15.setBackground(new java.awt.Color(255, 255, 254));
        Alasan15.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan15.setForeground(new java.awt.Color(50, 50, 50));
        Alasan15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan15.setText("Menunggu Advice DPJP");
        Alasan15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan15.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan15.setName("Alasan15"); // NOI18N
        Alasan15.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan15ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan15);

        Alasan16.setBackground(new java.awt.Color(255, 255, 254));
        Alasan16.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan16.setForeground(new java.awt.Color(50, 50, 50));
        Alasan16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan16.setText("Observasi PK I Fase Laten");
        Alasan16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan16.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan16.setName("Alasan16"); // NOI18N
        Alasan16.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan16ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan16);

        Alasan17.setBackground(new java.awt.Color(255, 255, 254));
        Alasan17.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan17.setForeground(new java.awt.Color(50, 50, 50));
        Alasan17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan17.setText("Observasi di VK");
        Alasan17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan17.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan17.setName("Alasan17"); // NOI18N
        Alasan17.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan17ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan17);

        Alasan18.setBackground(new java.awt.Color(255, 255, 254));
        Alasan18.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        Alasan18.setForeground(new java.awt.Color(50, 50, 50));
        Alasan18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/category.png"))); // NOI18N
        Alasan18.setText("Full Bed IGD");
        Alasan18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        Alasan18.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        Alasan18.setName("Alasan18"); // NOI18N
        Alasan18.setPreferredSize(new java.awt.Dimension(225, 25));
        Alasan18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Alasan18ActionPerformed(evt);
            }
        });
        Popup2.add(Alasan18);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Rekap Pasien IGD ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbDokter.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbDokter.setName("tbDokter"); // NOI18N
        scrollPane1.setViewportView(tbDokter);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tanggal :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi4.add(label11);

        Tgl1.setDisplayFormat("dd-MM-yyyy");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl1KeyPressed(evt);
            }
        });
        panelisi4.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);

        Tgl2.setDisplayFormat("dd-MM-yyyy");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        Tgl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tgl2KeyPressed(evt);
            }
        });
        panelisi4.add(Tgl2);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi1.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(200, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi1.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
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
        panelisi1.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("Alt+2");
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
        panelisi1.add(BtnAll);

        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(25, 23));
        panelisi1.add(label10);

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
        panelisi1.add(BtnKeluar);

        internalFrame1.add(panelisi1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("::[Laporan Jenis Pembayaran Ranap Marketing ]::");
        internalFrame1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TKdKeyPressed
    Valid.pindah(evt,BtnCari,Nm);
}//GEN-LAST:event_TKdKeyPressed
*/

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        dispose();
    }//GEN-LAST:event_BtnKeluarKeyPressed

    private void Tgl1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl1KeyPressed
        Valid.pindah(evt, BtnKeluar,Tgl2);
    }//GEN-LAST:event_Tgl1KeyPressed

    private void Tgl2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tgl2KeyPressed
        
    }//GEN-LAST:event_Tgl2KeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        prosesCari();
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
        prosesCari();
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        prosesCari();
    }//GEN-LAST:event_formWindowOpened

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TCariKeyPressed

    private void AlasanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlasanActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Sesuai Target'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Sesuai Target",tbDokter.getSelectedRow(),22);
            }
        }
    }//GEN-LAST:event_AlasanActionPerformed

    private void Alasan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan1ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Ruangan Siap'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Ruangan Siap",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan1ActionPerformed

    private void Alasan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan2ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Pasien Lama Memutuskan Setuju Ranap'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Pasien Lama Memutuskan Setuju Ranap",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan2ActionPerformed

    private void Alasan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan3ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Pasien Lama Memutuskan Setuju Tindakan Medis'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Pasien Lama Memutuskan Setuju Tindakan Medis",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan3ActionPerformed

    private void Alasan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan4ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Tidak Ada Penanggung Jawab Pasien'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Tidak Ada Penanggung Jawab Pasien",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan4ActionPerformed

    private void Alasan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan5ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Hasil Lab'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Hasil Lab",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan5ActionPerformed

    private void Alasan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan6ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Hasil Rontgen'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Hasil Rontgen",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan6ActionPerformed

    private void Alasan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan7ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Mencari Rujukan'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Mencari Rujukan",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan7ActionPerformed

    private void Alasan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan8ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Obat'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Obat",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan8ActionPerformed

    private void Alasan9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan9ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Pendaftaran'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Pendaftaran",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan9ActionPerformed

    private void Alasan10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan10ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Butuh Perpanjangan Observasi'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Butuh Perpanjangan Observasi",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan10ActionPerformed

    private void Alasan11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan11ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Keputusan Keluarga'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Keputusan Keluarga",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan11ActionPerformed

    private void Alasan12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan12ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu USG Abdomen'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu USG Abdomen",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan12ActionPerformed

    private void Alasan13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan13ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Dilakukan Echo di Poli'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Dilakukan Echo di Poli",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan13ActionPerformed

    private void Alasan14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan14ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Visite DPJP'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Visite DPJP",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan14ActionPerformed

    private void Alasan15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan15ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Menunggu Advice DPJP'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Menunggu Advice DPJP",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan15ActionPerformed

    private void Alasan16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan16ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Observasi PK I Fase Laten'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Observasi PK I Fase Laten",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan16ActionPerformed

    private void Alasan17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan17ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Observasi di VK'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Observasi di VK",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan17ActionPerformed

    private void Alasan18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Alasan18ActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, pilih data terlebih dahulu...!!!!");
        }else{
            if(tbDokter.getSelectedRow()!= -1){
                Sequel.mengedit("pulang_igd","no_rawat=?","alasan='Full Bed IGD'",1,new String[]{tbDokter.getValueAt(tbDokter.getSelectedRow(),21).toString()});
                tabMode.setValueAt("Full Bed IGD",tbDokter.getSelectedRow(),22);
            }
        }// TODO add your handling code here:
    }//GEN-LAST:event_Alasan18ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekapIGD dialog = new DlgRekapIGD(new javax.swing.JFrame(), true);
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
    private javax.swing.JMenuItem Alasan;
    private javax.swing.JMenuItem Alasan1;
    private javax.swing.JMenuItem Alasan10;
    private javax.swing.JMenuItem Alasan11;
    private javax.swing.JMenuItem Alasan12;
    private javax.swing.JMenuItem Alasan13;
    private javax.swing.JMenuItem Alasan14;
    private javax.swing.JMenuItem Alasan15;
    private javax.swing.JMenuItem Alasan16;
    private javax.swing.JMenuItem Alasan17;
    private javax.swing.JMenuItem Alasan18;
    private javax.swing.JMenuItem Alasan2;
    private javax.swing.JMenuItem Alasan3;
    private javax.swing.JMenuItem Alasan4;
    private javax.swing.JMenuItem Alasan5;
    private javax.swing.JMenuItem Alasan6;
    private javax.swing.JMenuItem Alasan7;
    private javax.swing.JMenuItem Alasan8;
    private javax.swing.JMenuItem Alasan9;
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private javax.swing.JPopupMenu Popup2;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label18;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi4;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbDokter;
    // End of variables declaration//GEN-END:variables

    private void prosesCari() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)); 
            z=0;
            for(i=0;i<tbDokter.getRowCount();i++){
                if(!tbDokter.getValueAt(i,0).toString().equals("")){
                    z++;
                }
            } 

            pilihan=null;
            pilihan=new boolean[z]; 
            jumlah=null;
            jumlah=new String[z];
            
            namabarang=null;
            namabarang=new String[z];
            

            z=0;        
            for(i=0;i<tbDokter.getRowCount();i++){
                if(tbDokter.getValueAt(i,0).toString().equals("true")){
                    pilihan[z]=Boolean.parseBoolean(tbDokter.getValueAt(i,0).toString());                
                    
                    namabarang[z]=tbDokter.getValueAt(i,1).toString();
                    
                    jumlah[z]=tbDokter.getValueAt(i,2).toString();
                    
                    z++;
                }
            }
            
            Valid.tabelKosong(tabMode);
            for(i=0;i<z;i++){
                tabMode.addRow(new Object[] {
                    pilihan[i],namabarang[i],jumlah[i]
                });
            }
            totallebraj2=0;totalkurraj2=0;totallebran2=0;totalkurran2=0;
            ps=koneksi.prepareStatement(
                "SELECT DATE_FORMAT(reg_periksa.tgl_registrasi,'%d-%m-%Y'),reg_periksa.no_rkm_medis,pasien.nm_pasien,concat(umurdaftar,' ',sttsumur),if(pasien.jk='P','Perempuan','Laki-Laki'),penjab.png_jawab,bridging_sep.klsrawat,pasien.alamat,RTRIM(LTRIM(REPLACE(REPLACE(REPLACE(penilaian_medis_igd.diagnosis, CHAR(13), ' '), CHAR(10), ' '), CHAR(9), ' '))) AS cleaned_column,"
                + "dokter.nm_dokter,rujuk_masuk.perujuk,if(reg_periksa.status_lanjut='Ranap','Rawat Inap',if(reg_periksa.stts='Berkas Turun','Sudah Periksa',if(reg_periksa.stts='Sudah','Sudah Periksa',if(reg_periksa.stts='Belum','Belum Periksa',reg_periksa.stts)))),if(!bangsal.nm_bangsal,bangsal.nm_bangsal,'-'),reg_periksa.jam_reg,reg_periksa.no_rawat,if(reg_periksa.status_lanjut='Ranap','Rawat Inap','Rawat Jalan') as status_lanjut,reg_periksa.tgl_registrasi,pulang_igd.alasan "
                + "FROM reg_periksa INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis INNER JOIN penjab ON penjab.kd_pj=reg_periksa.kd_pj LEFT JOIN bridging_sep ON bridging_sep.no_rawat=reg_periksa.no_rawat LEFT JOIN penilaian_medis_igd ON penilaian_medis_igd.no_rawat=reg_periksa.no_rawat LEFT JOIN dpjp_ranap ON dpjp_ranap.no_rawat=reg_periksa.no_rawat "
                + "LEFT JOIN dokter ON dokter.kd_dokter=dpjp_ranap.kd_dokter LEFT JOIN rujuk_masuk ON rujuk_masuk.no_rawat=reg_periksa.no_rawat LEFT JOIN kamar_inap ON kamar_inap.no_rawat=reg_periksa.no_rawat LEFT JOIN kamar ON kamar.kd_kamar=kamar_inap.kd_kamar LEFT JOIN bangsal ON bangsal.kd_bangsal=kamar.kd_bangsal LEFT JOIN pulang_igd ON pulang_igd.no_rawat=reg_periksa.no_rawat "
                + "WHERE reg_periksa.kd_poli='IGD' AND reg_periksa.tgl_registrasi BETWEEN ? AND ? and (pasien.nm_pasien like ? or reg_periksa.no_rkm_medis like ?) and reg_periksa.stts<>'Batal' GROUP BY reg_periksa.no_rawat ORDER BY reg_periksa.tgl_registrasi ASC,reg_periksa.jam_reg ASC;");
                                        
            try {
                ps.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                ps.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                ps.setString(3,"%"+TCari.getText()+"%");
                ps.setString(4,"%"+TCari.getText()+"%");
                x=1;
                rs=ps.executeQuery();
                while(rs.next()){
                    ps2=koneksi.prepareStatement(
                        "SELECT rp.no_rawat,if(!b.nm_bangsal,b.nm_bangsal,'-') as terakhir FROM reg_periksa rp "
                        + "left join kamar_inap ki on ki.no_rawat=rp.no_rawat left join kamar k on k.kd_kamar=ki.kd_kamar "
                        + "left JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE rp.kd_poli='IGD' AND rp.tgl_registrasi BETWEEN ? AND ? AND rp.no_rawat=? "
                        + "ORDER BY ki.tgl_masuk DESC,ki.jam_masuk desc limit 1;");
                    try {
                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(3,rs.getString("no_rawat"));
                        rs2=ps2.executeQuery();
                        while(rs2.next()){
//                            if(rs.getString("status_lanjut").equals("Ranap")){
//                                ps3=koneksi.prepareStatement(
//                                    "SELECT if(ki.jam_keluar='00:00:00','',ki.jam_keluar) as jam_keluar,if(ki.jam_keluar='00:00:00','',concat(round((TIME_TO_SEC(concat(ki.tgl_keluar,' ',ki.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,0),' Menit')) as durasi,if(ki.jam_keluar='00:00:00','',if(round((TIME_TO_SEC(concat(ki.tgl_keluar,' ',ki.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,2)>120,'>2 Jam','<2 Jam')) as durasi2,if(ki.jam_keluar='00:00:00','',concat(round((TIME_TO_SEC(concat(ki.tgl_keluar,' ',ki.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,0)-120,' Menit')) as durasi3,ki.tgl_keluar FROM kamar_inap ki inner join reg_periksa rp on rp.no_rawat=ki.no_rawat inner join kamar k ON k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE b.nm_bangsal like \"%IGD%\" AND rp.tgl_registrasi BETWEEN ? AND ? AND rp.no_rawat=?;");
//                            }else{
                                if(Sequel.cariInteger("select count(pulang_igd.no_rawat) from pulang_igd where pulang_igd.no_rawat=?",rs.getString("no_rawat"))>0){
                                    ps3=koneksi.prepareStatement(
//                                    "SELECT pi2.jam_keluar,round((TIME_TO_SEC(concat(pi2.tgl_keluar,' ',pi2.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,2) as durasi FROM pulang_igd pi2 left join reg_periksa rp on rp.no_rawat=pi2.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? AND rp.kd_poli='IGD' AND rp.no_rawat=?;");
//                                    "SELECT pi2.jam_keluar,concat(round((TIME_TO_SEC(concat(pi2.tgl_keluar,' ',pi2.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,0),' Menit') as durasi,if(round((TIME_TO_SEC(concat(pi2.tgl_keluar,' ',pi2.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,2)>120,'>2 Jam','<2 Jam') as durasi2,concat(round((TIME_TO_SEC(concat(pi2.tgl_keluar,' ',pi2.jam_keluar))-TIME_TO_SEC(concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"')))/60,0)-120,' Menit') as durasi3 FROM pulang_igd pi2 left join reg_periksa rp on rp.no_rawat=pi2.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? AND rp.kd_poli='IGD' AND rp.no_rawat=?;");
                                    "SELECT pi2.jam_keluar,concat(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(pi2.tgl_keluar,' ',pi2.jam_keluar)),' Menit') as durasi,if(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(pi2.tgl_keluar,' ',pi2.jam_keluar))>=120,'>2 Jam','<2 Jam') as durasi2,concat(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(pi2.tgl_keluar,' ',pi2.jam_keluar))-120,' Menit') as durasi3,pi2.tgl_keluar FROM reg_periksa rp left join pulang_igd pi2 on pi2.no_rawat=rp.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? AND rp.kd_poli='IGD' AND rp.no_rawat=?;");
                                }else{
                                ps3=koneksi.prepareStatement(
                                    "SELECT nj.jam,concat(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(nj.tanggal,' ',nj.jam)),' Menit') as durasi,if(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(nj.tanggal,' ',nj.jam))>=120,'>2 Jam',ifnull(if(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(nj.tanggal,' ',nj.jam))<120,'<2 Jam',''),'')) as durasi2,concat(TIMESTAMPDIFF(minute, concat('"+rs.getString(17)+"',' ','"+rs.getString(14)+"'),concat(nj.tanggal,' ',nj.jam))-120,' Menit') as durasi3,nj.tanggal FROM reg_periksa rp LEFT join nota_jalan nj on nj.no_rawat=rp.no_rawat WHERE rp.tgl_registrasi BETWEEN ? AND ? AND rp.kd_poli='IGD' AND rp.no_rawat=?;");
                                }
//                            }
                                try {
                                    ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                    ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                    ps3.setString(3,rs.getString("no_rawat"));
                                    rs3=ps3.executeQuery();
                                    
                                    while(rs3.next()){
                                        tabMode.addRow(new Object[]{
                                            x++,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
                                            rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString("status_lanjut"),
                                            rs2.getString(2),rs.getString(17),rs.getString(14),rs3.getString(5),rs3.getString(1),rs3.getString(2),
                                            rs3.getString(4),rs3.getString(3),rs.getString("no_rawat"),rs.getString("alasan")
                                        });
                                        if(rs.getString("status_lanjut").equals("Rawat Jalan")&&rs3.getString(3).equals(">2 Jam")){
                                            totallebraj2++;
                                        }else if(rs.getString("status_lanjut").equals("Rawat Jalan")&&rs3.getString(3).equals("<2 Jam")){
                                            totalkurraj2++;
                                        }else if(rs.getString("status_lanjut").equals("Rawat Inap")&&rs3.getString(3).equals(">2 Jam")){
                                            totallebran2++;
                                        }else if(rs.getString("status_lanjut").equals("Rawat Inap")&&rs3.getString(3).equals("<2 Jam")){
                                            totalkurran2++;
                                        }
                                    } 
                                } catch (Exception e) {
                                    System.out.println("Note : "+e);
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
                        System.out.println("Note : "+e);
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
                System.out.println("Note : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
            if(x>0){
                tabMode.addRow(new Object[]{
                    "","","","","","","","","","","","","","","","","","","","","","",""
                });
                tabMode.addRow(new Object[]{
                    "","","","<2 Jam Rawat Jalan",": "+totalkurraj2,"","","","","","","","","","","","","","","","","",""
                });
                tabMode.addRow(new Object[]{
                    "","","","<2 Jam Rawat Inap",": "+totalkurran2,"","","","","","","","","","","","","","","","","",""
                });
                tabMode.addRow(new Object[]{
                    "","","",">2 Jam Rawat Jalan",": "+totallebraj2,"","","","","","","","","","","","","","","","","",""
                });
                tabMode.addRow(new Object[]{
                    "","","",">2 Jam Rawat Inap",": "+totallebran2,"","","","","","","","","","","","","","","","","",""
                });
            }
                                     
            this.setCursor(Cursor.getDefaultCursor());              
        }   catch (Exception e) {
                System.out.println(e);
        }               
    }
}
