

package inventaris;
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
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import kepegawaian.DlgCariPetugas;

/**
 *
 * @author perpustakaan
 */
public class InventarisSirkulasiLab extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private InventarisCariSirkulasiLab form=new InventarisCariSirkulasiLab(null,false);
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private String inventariscari="",tglcari="",status="Normal";
    private int ttlmasuk=0,ttlhilang=0,ttlkeluar=0,ttlrusak=0,stokakhir=0,stoklayak=0;
    private int total=0;

    /** Creates new form DlgKamarInap
     * @param parent
     * @param modal */
    public InventarisSirkulasiLab(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(628,674);

        Object[] row={"No. Inventaris","Nama Barang","Stok Gudang","Total Harga (Rp)","Barang Lab Terpakai di Pelayanan","Barang Lab Rusak","Barang Lab Hilang","Barang Lab Layak Pakai","Jumlah Minimal Barang Lab Layak Pakai"
        };
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamIn.setModel(tabMode);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        tbKamIn.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamIn.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbKamIn.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(75);
            }else if(i==1){
                column.setPreferredWidth(150);
            }else if(i==2){
                column.setPreferredWidth(75);
            }else if(i==3){
                column.setPreferredWidth(75);
            }else if(i==4){
                column.setPreferredWidth(150);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setPreferredWidth(75);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(200);
            }
        }
        tbKamIn.setDefaultRenderer(Object.class, new WarnaTable());

        no_inventaris.setDocument(new batasInput((byte)30).getKata(no_inventaris));
//        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
     
        
        WindowInput.setSize(735,245);
        WindowInput.setLocationRelativeTo(null);  
        
        inventaris.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(inventaris.getTable().getSelectedRow()!= -1){                   
                    no_inventaris.setText(inventaris.getTable().getValueAt(inventaris.getTable().getSelectedRow(),0).toString());
                    nama_barang.setText(inventaris.getTable().getValueAt(inventaris.getTable().getSelectedRow(),1).toString());
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
        
        inventaris.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    inventaris.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
        
//        if(koneksiDB.CARICEPAT().equals("aktif")){
//            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
//                @Override
//                public void insertUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil();
//                    }
//                }
//                @Override
//                public void removeUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil();
//                    }
//                }
//                @Override
//                public void changedUpdate(DocumentEvent e) {
//                    if(TCari.getText().length()>2){
//                        tampil();
//                    }
//                }
//            });
//        }
        
    }

    private DlgCariPetugas petugas=new DlgCariPetugas(null,false);
    private InventarisBarangLab inventaris=new InventarisBarangLab(null,false);
    private int pilihan=0;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WindowInput = new javax.swing.JDialog();
        internalFrame2 = new widget.InternalFrame();
        BtnCloseIn = new widget.Button();
        jLabel19 = new widget.Label();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        no_inventaris = new widget.TextBox();
        label1 = new widget.Label();
        LblRusak = new widget.Label();
        nama_barang = new widget.TextBox();
        btnInv = new widget.Button();
        jumlah = new widget.TextBox();
        label11 = new widget.Label();
        jLabel10 = new widget.Label();
        LblStts = new widget.Label();
        sttsrusak = new widget.TextBox();
        TOut = new widget.TextBox();
        TIn = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        panelGlass10 = new widget.panelisi();
        BtnIn = new widget.Button();
        BtnOut = new widget.Button();
        BtnKeluar1 = new widget.Button();
        BtnKeluar2 = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnKeluar = new widget.Button();
        Scroll = new widget.ScrollPane();
        tbKamIn = new widget.Table();

        WindowInput.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        WindowInput.setName("WindowInput"); // NOI18N
        WindowInput.setUndecorated(true);
        WindowInput.setResizable(false);

        internalFrame2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 235, 225)), "::[ Transaksi LAB ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 40))); // NOI18N
        internalFrame2.setName("internalFrame2"); // NOI18N
        internalFrame2.setPreferredSize(new java.awt.Dimension(125, 215));
        internalFrame2.setLayout(null);

        BtnCloseIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/cross.png"))); // NOI18N
        BtnCloseIn.setMnemonic('U');
        BtnCloseIn.setText("Tutup");
        BtnCloseIn.setToolTipText("Alt+U");
        BtnCloseIn.setName("BtnCloseIn"); // NOI18N
        BtnCloseIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCloseInActionPerformed(evt);
            }
        });
        BtnCloseIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCloseInKeyPressed(evt);
            }
        });
        internalFrame2.add(BtnCloseIn);
        BtnCloseIn.setBounds(360, 123, 100, 30);

        jLabel19.setText("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        jLabel19.setName("jLabel19"); // NOI18N
        internalFrame2.add(jLabel19);
        jLabel19.setBounds(0, 110, 530, 14);

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
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
        internalFrame2.add(BtnSimpan);
        BtnSimpan.setBounds(10, 123, 100, 30);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Batal");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
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
        internalFrame2.add(BtnBatal);
        BtnBatal.setBounds(120, 123, 100, 30);

        no_inventaris.setName("no_inventaris"); // NOI18N
        no_inventaris.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                no_inventarisKeyPressed(evt);
            }
        });
        internalFrame2.add(no_inventaris);
        no_inventaris.setBounds(103, 25, 80, 23);

        label1.setText("No.Inventaris :");
        label1.setName("label1"); // NOI18N
        internalFrame2.add(label1);
        label1.setBounds(0, 25, 100, 23);

        LblRusak.setText("Status Rusak :");
        LblRusak.setName("LblRusak"); // NOI18N
        internalFrame2.add(LblRusak);
        LblRusak.setBounds(235, 55, 80, 23);

        nama_barang.setEditable(false);
        nama_barang.setName("nama_barang"); // NOI18N
        internalFrame2.add(nama_barang);
        nama_barang.setBounds(185, 25, 250, 23);

        btnInv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        btnInv.setMnemonic('1');
        btnInv.setToolTipText("Alt+1");
        btnInv.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnInv.setName("btnInv"); // NOI18N
        btnInv.setPreferredSize(new java.awt.Dimension(100, 30));
        btnInv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInvActionPerformed(evt);
            }
        });
        internalFrame2.add(btnInv);
        btnInv.setBounds(440, 25, 25, 23);

        jumlah.setName("jumlah"); // NOI18N
        internalFrame2.add(jumlah);
        jumlah.setBounds(103, 55, 80, 23);

        label11.setText("Jumlah :");
        label11.setName("label11"); // NOI18N
        internalFrame2.add(label11);
        label11.setBounds(0, 55, 100, 23);

        jLabel10.setText("Status :");
        jLabel10.setName("jLabel10"); // NOI18N
        internalFrame2.add(jLabel10);
        jLabel10.setBounds(0, 85, 100, 23);

        LblStts.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LblStts.setText("Masuk");
        LblStts.setName("LblStts"); // NOI18N
        internalFrame2.add(LblStts);
        LblStts.setBounds(103, 85, 180, 23);

        sttsrusak.setName("sttsrusak"); // NOI18N
        internalFrame2.add(sttsrusak);
        sttsrusak.setBounds(320, 55, 140, 23);

        WindowInput.getContentPane().add(internalFrame2, java.awt.BorderLayout.CENTER);

        TOut.setEditable(false);
        TOut.setForeground(new java.awt.Color(255, 255, 255));
        TOut.setHighlighter(null);
        TOut.setName("TOut"); // NOI18N
        TOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TOutKeyPressed(evt);
            }
        });

        TIn.setEditable(false);
        TIn.setForeground(new java.awt.Color(255, 255, 255));
        TIn.setHighlighter(null);
        TIn.setName("TIn"); // NOI18N
        TIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TInKeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Sirkulasi LAB ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        panelGlass10.setName("panelGlass10"); // NOI18N
        panelGlass10.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass10.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/34.png"))); // NOI18N
        BtnIn.setMnemonic('M');
        BtnIn.setText("Masuk");
        BtnIn.setToolTipText("Alt+M");
        BtnIn.setName("BtnIn"); // NOI18N
        BtnIn.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInActionPerformed(evt);
            }
        });
        BtnIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnInKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnIn);

        BtnOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/36.png"))); // NOI18N
        BtnOut.setMnemonic('K');
        BtnOut.setText("Terpakai");
        BtnOut.setToolTipText("Alt+K");
        BtnOut.setName("BtnOut"); // NOI18N
        BtnOut.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOutActionPerformed(evt);
            }
        });
        BtnOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnOutKeyPressed(evt);
            }
        });
        panelGlass10.add(BtnOut);

        BtnKeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar1.setMnemonic('K');
        BtnKeluar1.setText("Rusak");
        BtnKeluar1.setToolTipText("Alt+K");
        BtnKeluar1.setName("BtnKeluar1"); // NOI18N
        BtnKeluar1.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar1ActionPerformed(evt);
            }
        });
        BtnKeluar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar1KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnKeluar1);

        BtnKeluar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar2.setMnemonic('K');
        BtnKeluar2.setText("Hilang");
        BtnKeluar2.setToolTipText("Alt+K");
        BtnKeluar2.setName("BtnKeluar2"); // NOI18N
        BtnKeluar2.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluar2ActionPerformed(evt);
            }
        });
        BtnKeluar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluar2KeyPressed(evt);
            }
        });
        panelGlass10.add(BtnKeluar2);

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
        panelGlass10.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Riwayat");
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
        panelGlass10.add(BtnAll);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('4');
        BtnCari.setToolTipText("Alt+4");
        BtnCari.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelGlass10.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(45, 23));
        panelGlass10.add(LCount);

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
        panelGlass10.add(BtnKeluar);

        internalFrame1.add(panelGlass10, java.awt.BorderLayout.PAGE_END);

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamIn.setAutoCreateRowSorter(true);
        tbKamIn.setName("tbKamIn"); // NOI18N
        tbKamIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamInMouseClicked(evt);
            }
        });
        tbKamIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKamInKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbKamInKeyReleased(evt);
            }
        });
        Scroll.setViewportView(tbKamIn);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInActionPerformed
        status="Masuk";
        LblStts.setText("Masuk");
        btnInv.setEnabled(true);
        LblRusak.setVisible(false);
        sttsrusak.setVisible(false);
        emptTeks();
        WindowInput.setSize(500,170);
        WindowInput.setAlwaysOnTop(false);
        WindowInput.setVisible(true);
}//GEN-LAST:event_BtnInActionPerformed

    private void BtnInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnInKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnInActionPerformed(null);
//        }else{
//            Valid.pindah(evt,TCari,BtnOut);
//        }
}//GEN-LAST:event_BtnInKeyPressed

    private void BtnOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOutActionPerformed
        status="Keluar";
        LblStts.setText("Keluar");
        btnInv.setEnabled(true);
        LblRusak.setVisible(false);
        sttsrusak.setVisible(false);
        emptTeks();
        WindowInput.setSize(500,170);
        WindowInput.setAlwaysOnTop(false);
        WindowInput.setVisible(true);
}//GEN-LAST:event_BtnOutActionPerformed

    private void BtnOutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnOutKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnOutActionPerformed(null);
//        }else{
//            Valid.pindah(evt,BtnIn,BtnHapus);
//        }
}//GEN-LAST:event_BtnOutKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        WindowInput.dispose();
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            WindowInput.dispose();
//            dispose();
//        }else{Valid.pindah(evt,BtnPrint,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
//        if(tabMode.getRowCount()==0){
//            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
//            BtnBatal.requestFocus();
//        }else if(tabMode.getRowCount()!=0){
//                inventariscari="";
//                tglcari="";
//                
//                if(ChkTanggal.isSelected()==true){
//                    tglcari=" inventaris_peminjaman.tgl_pinjam between '"+Valid.SetTgl(TglPinjam1.getSelectedItem()+"")+"' and '"+Valid.SetTgl(TglPinjam2.getSelectedItem()+"")+"' and ";
//                }
//                
//                if(!InventarisCari.getText().equals("")){
//                    inventariscari="inventaris_barang.nama_barang='"+InventarisCari.getText()+"' and ";
//                }
//
//                Map<String, Object> param = new HashMap<>(); 
//                param.put("namars",akses.getnamars());
//                param.put("alamatrs",akses.getalamatrs());
//                param.put("kotars",akses.getkabupatenrs());
//                param.put("propinsirs",akses.getpropinsirs());
//                param.put("kontakrs",akses.getkontakrs());
//                param.put("emailrs",akses.getemailrs());   
//                param.put("logo",Sequel.cariGambar("select setting.logo from setting")); 
//                Valid.MyReportqry("rptSirkulasiInventaris.jasper","report","::[ Data Sirkulasi Inventaris ]::","select inventaris_peminjaman.no_inventaris,"+
//                           "inventaris.kode_barang,"+
//                           "inventaris_barang.nama_barang,"+
//                           "inventaris_produsen.nama_produsen,"+
//                           "inventaris_merk.nama_merk,"+
//                           "inventaris_barang.thn_produksi, "+
//                           "inventaris_barang.isbn,"+
//                           "inventaris_kategori.nama_kategori,"+
//                           "inventaris_jenis.nama_jenis,"+
//                           "inventaris_peminjaman.peminjam,"+
//                           "inventaris_peminjaman.tlp,"+
//                           "inventaris_peminjaman.tgl_pinjam,"+
//                           "inventaris_peminjaman.tgl_kembali,"+
//                           "petugas.nama "+
//                           "from inventaris_peminjaman inner join inventaris inner join inventaris_barang inner join inventaris_produsen "+
//                           "inner join inventaris_merk inner join inventaris_kategori inner join inventaris_jenis inner join petugas "+
//                           "on inventaris_peminjaman.no_inventaris=inventaris.no_inventaris "+
//                           "and inventaris_barang.kode_barang=inventaris.kode_barang "+
//                           "and inventaris_barang.kode_produsen=inventaris_produsen.kode_produsen "+
//                           "and inventaris_barang.id_merk=inventaris_merk.id_merk "+
//                           "and inventaris_barang.id_kategori=inventaris_kategori.id_kategori "+
//                           "and inventaris_barang.id_jenis=inventaris_jenis.id_jenis "+
//                           "and petugas.nip=inventaris_peminjaman.nip "+
//                           "where "+inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_peminjaman.no_inventaris like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_barang.kode_barang like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_barang.nama_barang like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_produsen.nama_produsen like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_merk.nama_merk like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_barang.thn_produksi like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_barang.isbn like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_kategori.nama_kategori like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_jenis.nama_jenis like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_peminjaman.peminjam like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" petugas.nama like '%"+TCari.getText().trim()+"%' or "+
//                           inventariscari+" inventaris_peminjaman.status_pinjam like '%"+StatusCari.getSelectedItem().toString().replaceAll("Semua","")+"%' and "+tglcari+" inventaris_peminjaman.tlp like '%"+TCari.getText().trim()+"%' "+
//                           " order by inventaris_peminjaman.tgl_pinjam",param);
//
//        }
//        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnPrintActionPerformed(null);
//        }else{
//            Valid.pindah(evt, BtnHapus, BtnKeluar);
//        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
//        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
//            BtnCariActionPerformed(null);
//        }else{
//            Valid.pindah(evt, TCari, BtnAll);
//        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        form.emptTeks();
        form.tampil();
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setAlwaysOnTop(false);
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, BtnIn);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void DTPTglKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_DTPTglKeyPressed
     // Valid.pindah(evt,kdkamar,cmbJam);
}//GEN-LAST:event_DTPTglKeyPressed

    private void BtnCloseInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCloseInActionPerformed
        WindowInput.dispose();
    }//GEN-LAST:event_BtnCloseInActionPerformed

    private void BtnCloseInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCloseInKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            WindowInput.dispose();
        }else{Valid.pindah(evt, BtnBatal, no_inventaris);}
    }//GEN-LAST:event_BtnCloseInKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(no_inventaris.getText().trim().equals("")||nama_barang.getText().trim().equals("")){
            Valid.textKosong(no_inventaris,"Inventaris");
        }else if(jumlah.getText().trim().equals("")){
            Valid.textKosong(jumlah,"Jumlah");
        }else {
            if(status.equals("Masuk")){
                Sequel.menyimpan("inventaris_gudang_lab","'"+no_inventaris.getText()+"',CURRENT_TIMESTAMP,'"+jumlah.getText()+"','0','0','-','0'");
                status="Normal";
                WindowInput.dispose();
            }else if(status.equals("Keluar")){
                Sequel.menyimpan("inventaris_gudang_lab","'"+no_inventaris.getText()+"',CURRENT_TIMESTAMP,'0','"+jumlah.getText()+"','0','-','0'");
                status="Normal";
                WindowInput.dispose();
            }else if(status.equals("Rusak")){
                Sequel.menyimpan("inventaris_gudang_lab","'"+no_inventaris.getText()+"',CURRENT_TIMESTAMP,'0','0','"+jumlah.getText()+"','"+sttsrusak.getText()+"','0'");
                status="Normal";
                WindowInput.dispose();
            }else if(status.equals("Hilang")){
                Sequel.menyimpan("inventaris_gudang_lab","'"+no_inventaris.getText()+"',CURRENT_TIMESTAMP,'0','0','0','-','"+jumlah.getText()+"'");
                status="Normal";
                WindowInput.dispose();
            }
            tampil();
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        //Valid.pindah(evt,cmbDtk,BtnBatal);
    }//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        if(no_inventaris.isEditable()==true){
            emptTeks();
        }else if(no_inventaris.isEditable()==false){
            emptTeks();
            WindowInput.dispose();
        }
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnCloseIn);}
    }//GEN-LAST:event_BtnBatalKeyPressed

    private void TInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TInKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TInKeyPressed

    private void DTPTglItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DTPTglItemStateChanged
        
    }//GEN-LAST:event_DTPTglItemStateChanged

    private void TOutKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TOutKeyPressed
        // TODO add your handling code here:
}//GEN-LAST:event_TOutKeyPressed

    private void tbKamInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamInMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            
            
        }
}//GEN-LAST:event_tbKamInMouseClicked

    private void tbKamInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamInKeyPressed
//        if(tabMode.getRowCount()!=0){
//            if(evt.getKeyCode()==KeyEvent.VK_SHIFT){
//                TCari.requestFocus();
//            }                    
//        }
}//GEN-LAST:event_tbKamInKeyPressed

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    tampil();
}//GEN-LAST:event_formWindowOpened

private void no_inventarisKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_no_inventarisKeyPressed
//   if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
//        isInventaris();
//    }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
//        isInventaris();
//        BtnCloseIn.requestFocus();
//    }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//        isInventaris();
//        peminjam.requestFocus();
//    } else if(evt.getKeyCode()==KeyEvent.VK_UP){
//        btnInvActionPerformed(null);
//    }
}//GEN-LAST:event_no_inventarisKeyPressed

private void btnInvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInvActionPerformed
    pilihan=1;
    inventaris.isCek();
    inventaris.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
    inventaris.setLocationRelativeTo(internalFrame1);
    inventaris.setAlwaysOnTop(false);
    inventaris.setVisible(true);        
}//GEN-LAST:event_btnInvActionPerformed

    private void tbKamInKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamInKeyReleased
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }                  
        }
    }//GEN-LAST:event_tbKamInKeyReleased

    private void BtnKeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar1ActionPerformed
        status="Rusak";
        LblStts.setText("Rusak");
        btnInv.setEnabled(true);
        LblRusak.setVisible(true);
        sttsrusak.setVisible(true);
        emptTeks();
        WindowInput.setSize(500,170);
        WindowInput.setAlwaysOnTop(false);
        WindowInput.setVisible(true);
    }//GEN-LAST:event_BtnKeluar1ActionPerformed

    private void BtnKeluar1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar1KeyPressed

    private void BtnKeluar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluar2ActionPerformed
        status="Hilang";
        LblStts.setText("Hilang");
        btnInv.setEnabled(true);
        LblRusak.setVisible(false);
        sttsrusak.setVisible(false);
        emptTeks();
        WindowInput.setSize(500,170);
        WindowInput.setAlwaysOnTop(false);
        WindowInput.setVisible(true);
    }//GEN-LAST:event_BtnKeluar2ActionPerformed

    private void BtnKeluar2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluar2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnKeluar2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            InventarisSirkulasiLab dialog = new InventarisSirkulasiLab(new javax.swing.JFrame(), true);
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
    private widget.Button BtnCloseIn;
    private widget.Button BtnIn;
    private widget.Button BtnKeluar;
    private widget.Button BtnKeluar1;
    private widget.Button BtnKeluar2;
    private widget.Button BtnOut;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.Label LCount;
    private widget.Label LblRusak;
    private widget.Label LblStts;
    private widget.ScrollPane Scroll;
    private widget.TextBox TIn;
    private widget.TextBox TOut;
    private javax.swing.JDialog WindowInput;
    private widget.Button btnInv;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame2;
    private widget.Label jLabel10;
    private widget.Label jLabel19;
    private widget.TextBox jumlah;
    private widget.Label label1;
    private widget.Label label10;
    private widget.Label label11;
    private widget.TextBox nama_barang;
    private widget.TextBox no_inventaris;
    private widget.panelisi panelGlass10;
    private widget.TextBox sttsrusak;
    private widget.Table tbKamIn;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "SELECT ic.id_jenis,ic.nama_jenis,cb.stok,cb.min_stok FROM inventaris_lab ic " +
                    "inner join lab_barang cb on cb.no_inventaris=ic.id_jenis "+
                    "ORDER by ic.id_jenis");
            try {
                ttlmasuk=0;ttlhilang=0;ttlkeluar=0;ttlrusak=0;stokakhir=0;stoklayak=0;total=0;
                rs=ps.executeQuery();
                while(rs.next()){
                    ps2=koneksi.prepareStatement("select sum(igc.hilang) "+
                        " from inventaris_gudang_lab igc inner join inventaris_lab ic "+
                        " on igc.no_inventaris=ic.id_jenis "+
                        " where igc.no_inventaris=? ");
                    try {
                        ps2.setString(1,rs.getString(1));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){                    
                            ttlhilang=rs2.getInt(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Detail Sirkulasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement("select sum(igc.terpakai),sum(igc.rusak),sum(igc.hilang) "+
                        " from inventaris_gudang_lab igc inner join inventaris_lab ic "+
                        " on igc.no_inventaris=ic.id_jenis "+
                        " where igc.no_inventaris=? ");
                    try {
                        ps2.setString(1,rs.getString(1));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){                    
                            ttlkeluar=rs2.getInt(1)-(rs2.getInt(2)+rs2.getInt(3));
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Detail Sirkulasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement("select sum(igc.rusak) "+
                        " from inventaris_gudang_lab igc inner join inventaris_lab ic "+
                        " on igc.no_inventaris=ic.id_jenis "+
                        " where igc.no_inventaris=? ");
                    try {
                        ps2.setString(1,rs.getString(1));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){                    
                            ttlrusak=rs2.getInt(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Detail Sirkulasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    
                    ps2=koneksi.prepareStatement("select sum(igc.masuk) "+
                        " from inventaris_gudang_lab igc inner join inventaris_lab ic "+
                        " on igc.no_inventaris=ic.id_jenis "+
                        " where igc.no_inventaris=? ");
                    try {
                        ps2.setString(1,rs.getString(1));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){                    
                            ttlmasuk=rs2.getInt(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Detail Sirkulasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    stokakhir=rs.getInt(3)+ttlmasuk-(ttlhilang+ttlkeluar+ttlrusak);
                    ps2=koneksi.prepareStatement("select harga "+
                        " from lab_barang igc "+
                        " where igc.no_inventaris=? ");
                    try {
                        ps2.setString(1,rs.getString(1));
                        rs2=ps2.executeQuery();
                        if(rs2.next()){                    
                            total=stokakhir*rs2.getInt(1);
                        }
                    } catch (Exception e) {
                        System.out.println("Notifikasi Detail Sirkulasi : "+e);
                    } finally{
                        if(rs2!=null){
                            rs2.close();
                        }
                        if(ps2!=null){
                            ps2.close();
                        }
                    }
                    stoklayak=stokakhir+ttlkeluar;
                    tabMode.addRow(new Object[]{
                        rs.getString(1),rs.getString(2),stokakhir,total,ttlkeluar,ttlrusak,ttlhilang,stoklayak,rs.getString(4)
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
        no_inventaris.setText("");
        nama_barang.setText("");
        sttsrusak.setText("");
        jumlah.setText("");
        sttsrusak.setText("");
        no_inventaris.requestFocus();
    }

    private void getData() {
//        TOut.setText("");
//        TIn.setText("");
        if(tbKamIn.getSelectedRow()!= -1){
            no_inventaris.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(),0).toString());
            nama_barang.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(),1).toString()+", "+tbKamIn.getValueAt(tbKamIn.getSelectedRow(),2).toString());
//            TIn.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(),11).toString());
//            TOut.setText(tbKamIn.getValueAt(tbKamIn.getSelectedRow(),12).toString());            
        }
    }

    
    public void isCek(){
        if(akses.getjml2()>=1){
            BtnSimpan.setEnabled(akses.getinventaris_sirkulasi());
            BtnIn.setEnabled(akses.getinventaris_sirkulasi());
            BtnOut.setEnabled(akses.getinventaris_sirkulasi());
        } 
    }
    
//    public void isInventaris(){
//        try {
//                ps=koneksi.prepareStatement(
//                   "select inventaris.no_inventaris,inventaris_barang.kode_barang, inventaris_barang.nama_barang, "+
//                   "inventaris_merk.nama_merk,inventaris_jenis.nama_jenis,inventaris.status_barang "+
//                   "from inventaris inner join inventaris_barang inner join inventaris_jenis inner join inventaris_merk "+
//                   "on inventaris_barang.id_merk=inventaris_merk.id_merk and inventaris_barang.id_jenis=inventaris_jenis.id_jenis "+
//                   "and inventaris_barang.kode_barang=inventaris.kode_barang where inventaris.no_inventaris=?");
//                try{
//                    ps.setString(1,no_inventaris.getText());
//                    rs=ps.executeQuery();
//                    if(rs.next()){
//                        nama_barang.setText(rs.getString("kode_barang")+", "+rs.getString("nama_barang"));
//                        merk.setText(rs.getString("nama_merk"));
//                        jenis.setText(rs.getString("nama_jenis"));
//                        status.setText(rs.getString("status_barang"));
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
//            } catch (SQLException ex) {
//                System.out.println("Notifikasi : "+ex);
//            }
//    }
}
