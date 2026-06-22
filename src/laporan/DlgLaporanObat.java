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
import kepegawaian.DlgCariDokter;

public class DlgLaporanObat extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    //private DlgCariCaraBayar penjab=new DlgCariCaraBayar(null,false);
    private DlgCariDokter penjab=new DlgCariDokter(null,false);
    private PreparedStatement ps;
    private ResultSet rs;
    
    private int i=0,z=0;
    private String tangg;
    private boolean[] pilihan;
    private String[] kodebarang,namabarang,satuan,jenis,jumlah,kodesat,ruangan,keterangan;
    private double harga=0,jml=0,jobat=0,hjual=0,total=0;
    
    /** Creates new form DlgProgramStudi
     * @param parent
     * @param modal */
    public DlgLaporanObat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        Object[] row={"Nama Dokter","Kode Barang","Nama Barang","Jumlah","Harga (Rp)","Total (Rp)","Status","Tanggungan","Kategori"};
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
                java.lang.Double.class,java.lang.Double.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class
                
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

        for (i = 0;i < 9; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(300);
            }else if(i==1){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==2){
                column.setPreferredWidth(200);
            }else if(i==3){
                column.setPreferredWidth(75);
            }else if(i==4){
                column.setPreferredWidth(100);
            }else if(i==5){
                column.setPreferredWidth(75);
            }else if(i==6){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setPreferredWidth(200);
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
        
        penjab.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {}
            @Override
            public void windowClosed(WindowEvent e) {
                if(penjab.getTable().getSelectedRow()!= -1){
                    kdpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),0).toString());
                    nmpenjab.setText(penjab.getTable().getValueAt(penjab.getTable().getSelectedRow(),1).toString());
                    prosesCari();
                }      
                kdpenjab.requestFocus();
            }
            @Override
            public void windowIconified(WindowEvent e) {}
            @Override
            public void windowDeiconified(WindowEvent e) {}
            @Override
            public void windowActivated(WindowEvent e) {penjab.emptTeks();}
            @Override
            public void windowDeactivated(WindowEvent e) {}
        });   
        
        penjab.getTable().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_SPACE){
                    penjab.dispose();
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
        });
                
         
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        scrollPane1 = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        kdpenjab = new widget.TextBox();
        nmpenjab = new widget.TextBox();
        BtnSeek2 = new widget.Button();
        jLabel20 = new widget.Label();
        cmbStatus = new widget.ComboBox();
        jLabel21 = new widget.Label();
        cmbStatus1 = new widget.ComboBox();
        panelisi1 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Laporan Penggunaan Obat Ralan dan Ranap ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(50, 50, 50))); // NOI18N
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

        label17.setText("Nama Dokter :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(120, 23));
        panelisi4.add(label17);

        kdpenjab.setName("kdpenjab"); // NOI18N
        kdpenjab.setPreferredSize(new java.awt.Dimension(70, 23));
        kdpenjab.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kdpenjabKeyPressed(evt);
            }
        });
        panelisi4.add(kdpenjab);

        nmpenjab.setEditable(false);
        nmpenjab.setName("nmpenjab"); // NOI18N
        nmpenjab.setPreferredSize(new java.awt.Dimension(250, 23));
        panelisi4.add(nmpenjab);

        BtnSeek2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnSeek2.setMnemonic('3');
        BtnSeek2.setToolTipText("Alt+3");
        BtnSeek2.setName("BtnSeek2"); // NOI18N
        BtnSeek2.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSeek2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSeek2ActionPerformed(evt);
            }
        });
        BtnSeek2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSeek2KeyPressed(evt);
            }
        });
        panelisi4.add(BtnSeek2);

        jLabel20.setText("Status :");
        jLabel20.setName("jLabel20"); // NOI18N
        jLabel20.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi4.add(jLabel20);

        cmbStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ralan", "Ranap" }));
        cmbStatus.setName("cmbStatus"); // NOI18N
        cmbStatus.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(cmbStatus);

        jLabel21.setText("Tanggungan :");
        jLabel21.setName("jLabel21"); // NOI18N
        jLabel21.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(jLabel21);

        cmbStatus1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BPJS", "UMUM", "IKS", "KITAS", "PASSPORT" }));
        cmbStatus1.setName("cmbStatus1"); // NOI18N
        cmbStatus1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(cmbStatus1);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setPreferredSize(new java.awt.Dimension(100, 56));
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi1.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
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
        panelisi1.add(BtnPrint);

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

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Map<String, Object> param = new HashMap<>();                 
            param.put("namars",akses.getnamars());
            param.put("alamatrs",akses.getalamatrs());
            param.put("kotars",akses.getkabupatenrs());
            param.put("propinsirs",akses.getpropinsirs());
            param.put("kontakrs",akses.getkontakrs());
            param.put("emailrs",akses.getemailrs());  
              
            param.put("tanggal1",Valid.SetTgl(Tgl1.getSelectedItem()+""));  
            param.put("tanggal2",Valid.SetTgl(Tgl2.getSelectedItem()+""));  
               
            param.put("logo",Sequel.cariGambar("select logo from setting")); 
            Valid.MyReport("rptRekapPendidikan1mbakput11111.jasper",param,"::[ Laporan Rekap Jenis Bayar Pasien Ranap ]::");
            this.setCursor(Cursor.getDefaultCursor());
        }        
    }//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,Tgl2,BtnKeluar);
        }
    }//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            dispose();
        }else{Valid.pindah(evt,BtnPrint,Tgl1);}
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
        kdpenjab.setText("");
        nmpenjab.setText("");
        cmbStatus.setSelectedItem("Semua");
        
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

    private void kdpenjabKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kdpenjabKeyPressed

    }//GEN-LAST:event_kdpenjabKeyPressed

    private void BtnSeek2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek2ActionPerformed
        penjab.isCek();
        penjab.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        penjab.setLocationRelativeTo(internalFrame1);
        penjab.setAlwaysOnTop(false);
        penjab.setVisible(true);
    }//GEN-LAST:event_BtnSeek2ActionPerformed

    private void BtnSeek2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSeek2KeyPressed
        //Valid.pindah(evt,DTPCari2,TCari);
    }//GEN-LAST:event_BtnSeek2KeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLaporanObat dialog = new DlgLaporanObat(new javax.swing.JFrame(), true);
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
    private widget.Button BtnPrint;
    private widget.Button BtnSeek2;
    private widget.TextBox TCari;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.ComboBox cmbStatus;
    private widget.ComboBox cmbStatus1;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel20;
    private widget.Label jLabel21;
    private widget.TextBox kdpenjab;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.Label label9;
    private widget.TextBox nmpenjab;
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
            
            if(cmbStatus1.getSelectedItem()=="BPJS"){
                tangg="BPJ";
            }else if(cmbStatus1.getSelectedItem()=="UMUM"){
                tangg="A09";
            }else if(cmbStatus1.getSelectedItem()=="IKS"){
                tangg="IKS";
            }else if(cmbStatus1.getSelectedItem()=="KITAS"){
                tangg="A07";
            }else if(cmbStatus1.getSelectedItem()=="PASSPORT"){
                tangg="A08";
            }
 
            if(cmbStatus.getSelectedItem()=="Ranap"){
                ps=koneksi.prepareStatement(
                    "SELECT d.nm_dokter,rd.kode_brng,d2.nama_brng,COUNT(d2.nama_brng) as jumlah,rp.status_lanjut,d2.ralan,p.png_jawab,kb.nama FROM resep_obat ro  \n" +
                    "INNER JOIN dokter d ON d.kd_dokter=ro.kd_dokter \n" +
                    "INNER JOIN resep_dokter rd  ON rd.no_resep=ro.no_resep \n" +
                    "INNER JOIN databarang d2  on d2.kode_brng=rd.kode_brng \n" +
                    "INNER JOIN reg_periksa rp  on rp.no_rawat=ro.no_rawat\n" +
                    "INNER JOIN penjab p on p.kd_pj =rp.kd_pj \n" +
                    "INNER JOIN kategori_barang kb on kb.kode =d2.kode_kategori \n" +
                    "WHERE rp.status_lanjut like ? AND d.nm_dokter like ? and rp.kd_pj like ? AND ro.tgl_peresepan BETWEEN ? AND ? AND d2.kode_golongan<>'G53'\n" +
                    "GROUP BY d.nm_dokter,d2.nama_brng,rp.status_lanjut order by jumlah desc");                    
                try {
                    ps.setString(1,"%"+cmbStatus.getSelectedItem().toString()+"%");
                    ps.setString(2,"%"+nmpenjab.getText()+"%");
                    ps.setString(3,"%"+tangg+"%");
                    ps.setString(4,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                    ps.setString(5,Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    rs=ps.executeQuery();
                    while(rs.next()){
                        jobat=rs.getDouble("jumlah");
                        hjual=rs.getDouble("ralan");
                        total=jobat*hjual;
                        tabMode.addRow(new Object[]{
                            rs.getString("nm_dokter"),rs.getString("kode_brng"),rs.getString("nama_brng"),rs.getDouble("jumlah"),rs.getDouble("ralan"),Valid.SetAngka(total),rs.getString("status_lanjut"),rs.getString("png_jawab"),rs.getString("nama")
                        });          
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
            }else if(cmbStatus.getSelectedItem()=="Ralan"){
                ps=koneksi.prepareStatement(
                    "SELECT d.nm_dokter,dpo.kode_brng,d2.nama_brng,SUM(dpo.jml) as jumlah,rp.status_lanjut,d2.ralan,p.png_jawab,kb.nama FROM detail_pemberian_obat dpo\n" +
                    "INNER JOIN reg_periksa rp  on rp.no_rawat=dpo.no_rawat\n" +
                    "INNER JOIN dokter d ON d.kd_dokter=rp.kd_dokter \n" +
                    "INNER JOIN databarang d2  on d2.kode_brng=dpo.kode_brng \n" +
                    "INNER JOIN penjab p on p.kd_pj =rp.kd_pj \n" +
                    "INNER JOIN kategori_barang kb on kb.kode =d2.kode_kategori \n" +
                    "WHERE rp.status_lanjut like ? AND d.nm_dokter like ? and rp.kd_pj like ? AND dpo.tgl_perawatan BETWEEN ? AND ? AND d2.kode_golongan<>'G53'\n" +
                    "GROUP BY d.nm_dokter,d2.nama_brng,rp.status_lanjut order by jumlah desc");                    
                try {
                    ps.setString(1,"%"+cmbStatus.getSelectedItem().toString()+"%");
                    ps.setString(2,"%"+nmpenjab.getText()+"%");
                    ps.setString(3,"%"+tangg+"%");
                    ps.setString(4,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                    ps.setString(5,Valid.SetTgl(Tgl2.getSelectedItem()+""));

                    rs=ps.executeQuery();
                    while(rs.next()){
                        jobat=rs.getDouble("jumlah");
                        hjual=rs.getDouble("ralan");
                        total=jobat*hjual;
                        tabMode.addRow(new Object[]{
                            rs.getString("nm_dokter"),rs.getString("kode_brng"),rs.getString("nama_brng"),rs.getDouble("jumlah"),rs.getDouble("ralan"),Valid.SetAngka(total),rs.getString("status_lanjut"),rs.getString("png_jawab"),rs.getString("nama")
                        });          
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
            }
//            ps=koneksi.prepareStatement(
//                    "SELECT d.nm_dokter,rd.kode_brng,d2.nama_brng,COUNT(d2.nama_brng) as jumlah,rp.status_lanjut,d2.ralan,p.png_jawab,kb.nama FROM resep_obat ro  \n" +
//                    "INNER JOIN dokter d ON d.kd_dokter=ro.kd_dokter \n" +
//                    "INNER JOIN resep_dokter rd  ON rd.no_resep=ro.no_resep \n" +
//                    "INNER JOIN databarang d2  on d2.kode_brng=rd.kode_brng \n" +
//                    "INNER JOIN reg_periksa rp  on rp.no_rawat=ro.no_rawat\n" +
//                    "INNER JOIN penjab p on p.kd_pj =rp.kd_pj \n" +
//                    "INNER JOIN kategori_barang kb on kb.kode =d2.kode_kategori \n" +
//                    "WHERE rp.status_lanjut like ? AND d.nm_dokter like ? and rp.kd_pj like ? AND ro.tgl_peresepan BETWEEN ? AND ? AND d2.kode_golongan<>'G53'\n" +
//                    "GROUP BY d.nm_dokter,d2.nama_brng,rp.status_lanjut order by jumlah desc");                    
//            try {
//                ps.setString(1,"%"+cmbStatus.getSelectedItem().toString()+"%");
//                ps.setString(2,"%"+nmpenjab.getText()+"%");
//                ps.setString(3,"%"+tangg+"%");
//                ps.setString(4,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                ps.setString(5,Valid.SetTgl(Tgl2.getSelectedItem()+""));
//                //ps.setString(5,"%"+TCari.getText()+"%");
//                
//                rs=ps.executeQuery();
////                jobat=rs.getDouble("jumlah");
////                hjual=rs.getDouble("ralan");
////                total=jobat*hjual;
//                while(rs.next()){
//                    jobat=rs.getDouble("jumlah");
//                    hjual=rs.getDouble("ralan");
//                    total=jobat*hjual;
//                    tabMode.addRow(new Object[]{
//                        rs.getString("nm_dokter"),rs.getString("kode_brng"),rs.getString("nama_brng"),rs.getDouble("jumlah"),rs.getDouble("ralan"),Valid.SetAngka(total),rs.getString("status_lanjut"),rs.getString("png_jawab"),rs.getString("nama")
//                    });          
//                } 
//            } catch (Exception e) {
//                System.out.println("Note : "+e);
//            } finally{
//                if(rs!=null){
//                    rs.close();
//                }
//                if(ps!=null){
//                    ps.close();
//                }
//            }
                                     
            this.setCursor(Cursor.getDefaultCursor());              
        } catch (Exception e) {
            System.out.println(e);
        }               
    }
}
