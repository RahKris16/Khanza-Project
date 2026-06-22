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
public final class DlgRl31 extends javax.swing.JDialog {

    private final DefaultTableModel tabMode3, tabMode4, tabMode5, tabMode6, tabMode7;
    private Connection koneksi = koneksiDB.condb();
    private sekuel Sequel = new sekuel();
    private validasi Valid = new validasi();
    private PreparedStatement ps, ps2, ps3, ps4;
    private ResultSet rs, rs2, rs3, rs4;
    private int i = 0, kamar = 0, jumlahhari = 0, lm = 0, br = 0, bpjs = 0, iks = 0, umum = 0, pass = 0;
    private double hari, lama, hariperawatan, jumlahpasien, jumlahmati, jumlahmati48jam, harga, a;
    private DlgCariBangsal ruang = new DlgCariBangsal(null, false);

    /**
     * Creates new form DlgLhtBiaya
     *
     * @param parent
     * @param modal
     */
    public DlgRl31(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8, 1);
        setSize(885, 674);

        tabMode3 = new DefaultTableModel(null, new String[]{"No", "No.Rawat", "Nomer RM", "Daftar", "Nama Pasien", "Kamar", 
            "Tgl.Masuk", "Tgl.Keluar", "Hari", "Lama", "Status", "Bayar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        Tabel3.setModel(tabMode3);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel3.setPreferredScrollableViewportSize(new Dimension(500, 500));
        Tabel3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = Tabel3.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(180);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            }
        }

        Tabel3.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode4 = new DefaultTableModel(null, new String[]{"No", "No.Rawat", "Nomer RM", "Daftar", "Nama Pasien", "Kamar", 
            "Tgl.Masuk", "Tgl.Keluar", "Hari", "Lama", "Status", "Bayar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        Tabel4.setModel(tabMode4);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel4.setPreferredScrollableViewportSize(new Dimension(500, 500));
        Tabel4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = Tabel4.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(180);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            }
        }

        Tabel4.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode5 = new DefaultTableModel(null, new String[]{"No", "No.Rawat", "Nomer RM", "Daftar", "Nama Pasien", "Kamar", 
            "Tgl.Masuk", "Tgl.Keluar", "Hari", "Lama", "Status", "Bayar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        Tabel5.setModel(tabMode5);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel5.setPreferredScrollableViewportSize(new Dimension(500, 500));
        Tabel5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = Tabel5.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(180);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            }
        }

        Tabel5.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode6 = new DefaultTableModel(null, new String[]{"No", "No.Rawat", "Nomer RM", "Daftar", "Nama Pasien", "Kamar", 
            "Tgl.Masuk", "Tgl.Keluar", "Hari", "Lama", "Status", "Bayar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        Tabel6.setModel(tabMode6);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel6.setPreferredScrollableViewportSize(new Dimension(500, 500));
        Tabel6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = Tabel6.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(180);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            }
        }

        Tabel6.setDefaultRenderer(Object.class, new WarnaTable());

        tabMode7 = new DefaultTableModel(null, new String[]{"No", "No.Rawat", "Nomer RM", "Daftar", "Nama Pasien", "Kamar", 
            "Tgl.Masuk", "Tgl.Keluar", "Hari", "Lama", "Status", "Bayar"}) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        Tabel7.setModel(tabMode7);

        //tbObat.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbObat.getBackground()));
        Tabel7.setPreferredScrollableViewportSize(new Dimension(500, 500));
        Tabel7.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 12; i++) {
            TableColumn column = Tabel7.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(35);
            } else if (i == 1) {
                column.setPreferredWidth(110);
            } else if (i == 2) {
                column.setPreferredWidth(70);
            } else if (i == 3) {
                column.setPreferredWidth(50);
            } else if (i == 4) {
                column.setPreferredWidth(250);
            } else if (i == 5) {
                column.setPreferredWidth(180);
            } else if (i == 6) {
                column.setPreferredWidth(75);
            } else if (i == 7) {
                column.setPreferredWidth(75);
            } else if (i == 8) {
                column.setPreferredWidth(50);
            } else if (i == 9) {
                column.setPreferredWidth(50);
            } else if (i == 10) {
                column.setPreferredWidth(80);
            } else if (i == 11) {
                column.setPreferredWidth(120);
            }
        }

        Tabel7.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte) 20).getKata(TKd));

        ruang.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
                if (ruang.getTable().getSelectedRow() != -1) {
                    Kamar.setText(ruang.getTable().getValueAt(ruang.getTable().getSelectedRow(), 1).toString());
                    Kamar.requestFocus();
                }
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
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
        internalFrame4 = new widget.InternalFrame();
        Scroll2 = new widget.ScrollPane();
        Tabel3 = new widget.Table();
        internalFrame5 = new widget.InternalFrame();
        Scroll3 = new widget.ScrollPane();
        Tabel4 = new widget.Table();
        internalFrame6 = new widget.InternalFrame();
        Scroll4 = new widget.ScrollPane();
        Tabel5 = new widget.Table();
        internalFrame7 = new widget.InternalFrame();
        Scroll5 = new widget.ScrollPane();
        Tabel6 = new widget.Table();
        internalFrame8 = new widget.InternalFrame();
        Scroll6 = new widget.ScrollPane();
        Tabel7 = new widget.Table();

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

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ RL 3.1 Indikator Pelayanan ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
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

        internalFrame4.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame4.setBorder(null);
        internalFrame4.setName("internalFrame4"); // NOI18N
        internalFrame4.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll2.setName("Scroll2"); // NOI18N
        Scroll2.setOpaque(true);

        Tabel3.setName("Tabel3"); // NOI18N
        Tabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel3MouseClicked(evt);
            }
        });
        Tabel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel3KeyPressed(evt);
            }
        });
        Scroll2.setViewportView(Tabel3);

        internalFrame4.add(Scroll2, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Non Intensif", internalFrame4);

        internalFrame5.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame5.setBorder(null);
        internalFrame5.setName("internalFrame5"); // NOI18N
        internalFrame5.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll3.setName("Scroll3"); // NOI18N
        Scroll3.setOpaque(true);

        Tabel4.setName("Tabel4"); // NOI18N
        Tabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel4MouseClicked(evt);
            }
        });
        Tabel4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel4KeyPressed(evt);
            }
        });
        Scroll3.setViewportView(Tabel4);

        internalFrame5.add(Scroll3, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("ICU", internalFrame5);

        internalFrame6.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame6.setBorder(null);
        internalFrame6.setName("internalFrame6"); // NOI18N
        internalFrame6.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll4.setName("Scroll4"); // NOI18N
        Scroll4.setOpaque(true);

        Tabel5.setName("Tabel5"); // NOI18N
        Tabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel5MouseClicked(evt);
            }
        });
        Tabel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel5KeyPressed(evt);
            }
        });
        Scroll4.setViewportView(Tabel5);

        internalFrame6.add(Scroll4, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("NICU", internalFrame6);

        internalFrame7.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame7.setBorder(null);
        internalFrame7.setName("internalFrame7"); // NOI18N
        internalFrame7.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll5.setName("Scroll5"); // NOI18N
        Scroll5.setOpaque(true);

        Tabel6.setName("Tabel6"); // NOI18N
        Tabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel6MouseClicked(evt);
            }
        });
        Tabel6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel6KeyPressed(evt);
            }
        });
        Scroll5.setViewportView(Tabel6);

        internalFrame7.add(Scroll5, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("PICU", internalFrame7);

        internalFrame8.setBackground(new java.awt.Color(235, 255, 235));
        internalFrame8.setBorder(null);
        internalFrame8.setName("internalFrame8"); // NOI18N
        internalFrame8.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll6.setName("Scroll6"); // NOI18N
        Scroll6.setOpaque(true);

        Tabel7.setName("Tabel7"); // NOI18N
        Tabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabel7MouseClicked(evt);
            }
        });
        Tabel7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Tabel7KeyPressed(evt);
            }
        });
        Scroll6.setViewportView(Tabel7);

        internalFrame8.add(Scroll6, java.awt.BorderLayout.CENTER);

        TabRawat.addTab("Intensif Lainnya", internalFrame8);

        internalFrame1.add(TabRawat, java.awt.BorderLayout.CENTER);
        TabRawat.getAccessibleContext().setAccessibleName("");
        TabRawat.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            dispose();
        } else {
            Valid.pindah(evt, BtnKeluar, TKd);
        }
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
    if (TabRawat.getSelectedIndex() == 0) {
        tampil3();
    } else if (TabRawat.getSelectedIndex() == 1) {
        tampil4();
    } else if (TabRawat.getSelectedIndex() == 2) {
        tampil5();
    } else if (TabRawat.getSelectedIndex() == 3) {
        tampil6();
    } else if (TabRawat.getSelectedIndex() == 4) {
        tampil7();
    }
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
    if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        tampil3();
        this.setCursor(Cursor.getDefaultCursor());
    } else {
        Valid.pindah(evt, TKd, BtnKeluar);
    }
}//GEN-LAST:event_BtnCariKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        tampil3();
    }//GEN-LAST:event_formWindowOpened

    private void BtnSeek6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSeek6ActionPerformed
        ruang.isCek();
        ruang.emptTeks();
        ruang.setSize(internalFrame1.getWidth() - 20, internalFrame1.getHeight() - 20);
        ruang.setLocationRelativeTo(internalFrame1);
        ruang.setVisible(true);
    }//GEN-LAST:event_BtnSeek6ActionPerformed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        Kamar.setText("");
        if (TabRawat.getSelectedIndex() == 0) {
            tampil3();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil4();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampil5();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampil6();
        } else if (TabRawat.getSelectedIndex() == 4) {
            tampil7();
        }
    }//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            BtnAllActionPerformed(null);
        } else {
            Valid.pindah(evt, BtnCari, BtnKeluar);
        }
    }//GEN-LAST:event_BtnAllKeyPressed

    private void TabRawatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabRawatMouseClicked
        if (TabRawat.getSelectedIndex() == 0) {
            tampil3();
        } else if (TabRawat.getSelectedIndex() == 1) {
            tampil4();
        } else if (TabRawat.getSelectedIndex() == 2) {
            tampil5();
        } else if (TabRawat.getSelectedIndex() == 3) {
            tampil6();
        } else if (TabRawat.getSelectedIndex() == 4) {
            tampil7();
        }
    }//GEN-LAST:event_TabRawatMouseClicked

    private void Tabel7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel7KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel7KeyPressed

    private void Tabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel7MouseClicked

    private void Tabel6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel6KeyPressed

    private void Tabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel6MouseClicked

    private void Tabel5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel5KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel5KeyPressed

    private void Tabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel5MouseClicked

    private void Tabel4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel4KeyPressed

    private void Tabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel4MouseClicked

    private void Tabel3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Tabel3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel3KeyPressed

    private void Tabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabel3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Tabel3MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRl31 dialog = new DlgRl31(new javax.swing.JFrame(), true);
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
    private widget.ScrollPane Scroll2;
    private widget.ScrollPane Scroll3;
    private widget.ScrollPane Scroll4;
    private widget.ScrollPane Scroll5;
    private widget.ScrollPane Scroll6;
    private widget.TextBox TKd;
    private javax.swing.JTabbedPane TabRawat;
    private widget.Table Tabel3;
    private widget.Table Tabel4;
    private widget.Table Tabel5;
    private widget.Table Tabel6;
    private widget.Table Tabel7;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private widget.InternalFrame internalFrame4;
    private widget.InternalFrame internalFrame5;
    private widget.InternalFrame internalFrame6;
    private widget.InternalFrame internalFrame7;
    private widget.InternalFrame internalFrame8;
    private widget.Label jLabel17;
    private widget.Label jLabel7;
    private widget.Label label11;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    // End of variables declaration//GEN-END:variables

    public void tampil3() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode3);

            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,reg_periksa.stts_daftar,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,penjab.png_jawab,kelas.nm_kelas,kelas.tarif_kelas "
                    + "from reg_periksa inner join kamar_inap inner join pasien inner join kamar inner join bangsal "
                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal inner join penjab on penjab.kd_pj=reg_periksa.kd_pj inner join kelas on kelas.kd_kelas =kamar_inap.kd_kelas "
                    + "where reg_periksa.status_lanjut='Ranap' and (bangsal.nm_bangsal like \"Jenggala 2%\" or  bangsal.nm_bangsal like \"Majapahit 3%\" or bangsal.nm_bangsal like \"Singasari 3%\" "
                    + "or bangsal.nm_bangsal like \"Daha 5%\" or bangsal.nm_bangsal like \"Kahuripan 5%\") and kamar_inap.tgl_masuk between ? "
                    + "and ? " + (Kamar.getText().equals("") ? "" : "and bangsal.nm_bangsal=?") + " and kamar_inap.stts_pulang<>'Pindah Kamar' group by kamar_inap.no_rawat order by reg_periksa.tgl_registrasi");

            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                if (!Kamar.getText().equals("")) {
                    ps.setString(3, Kamar.getText().trim());
                }
                rs = ps.executeQuery();
                i = 1;
                lm = 0;
                br = 0;
                bpjs = 0;
                umum = 0;
                iks = 0;
                pass = 0;
                hari = 0;
                lama = 0;
                while (rs.next()) {
                    ps2 = koneksi.prepareStatement(
                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                            + "WHERE no_rawat=?  and stts_pulang<>'Pindah Kamar'");
                    try {
//                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(1, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
//                            harga=rs2.getDouble("hari")*rs.getDouble("tarif_kelas");
                            ps3 = koneksi.prepareStatement(
                                    "SELECT stts_pulang FROM `kamar_inap` WHERE no_rawat=? and stts_pulang<>'Pindah Kamar'");
                            try {
//                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                ps3.setString(1, rs.getString("no_rawat"));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    ps4 = koneksi.prepareStatement(
                                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                                            + "WHERE no_rawat=?");
                                    try {
                                        //                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                        //                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                        ps4.setString(1, rs.getString("no_rawat"));
                                        rs4 = ps4.executeQuery();
                                        while (rs4.next()) {
                                            if (rs.getString("stts_daftar").equals("Baru")) {
                                                br++;
                                            } else if (rs.getString("stts_daftar").equals("Lama")) {
                                                lm++;
                                            }
                                            if (rs.getString("png_jawab").equals("BPJS KESEHATAN")) {
                                                bpjs++;
                                            } else if (rs.getString("png_jawab").equals("UMUM")) {
                                                umum++;
                                            } else if (rs.getString("png_jawab").equals("IKS")) {
                                                iks++;
                                            } else if (rs.getString("png_jawab").equals("PASSPORT")) {
                                                pass++;
                                            }
                                            tabMode3.addRow(new Object[]{
                                                i, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("stts_daftar"),
                                                rs.getString("nm_pasien"),
                                                rs.getString("kamar"), rs2.getString("tanggal_terkecil"), rs2.getString("tanggal_terbesar"),
                                                rs4.getString("hari"), rs4.getString("lama"), rs3.getString("stts_pulang"), rs.getString("png_jawab")
                                            });
                                            hari = hari + rs4.getDouble("hari");
                                            lama = lama + rs4.getDouble("lama");
                                            i++;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                                    } finally {
                                        if (rs4 != null) {
                                            rs4.close();
                                        }
                                        if (ps4 != null) {
                                            ps4.close();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                                if (ps3 != null) {
                                    ps3.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                if (hari > 0) {
                    i = i - 1;
                    jumlahhari = Sequel.cariInteger("select (to_days('" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "')-to_days('" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'))") + 1;
//                    jumlahpasien=Sequel.cariInteger("SELECT COUNT( DISTINCT rp.no_rawat) from reg_periksa rp inner join kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE rp.tgl_registrasi BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' AND rp.status_lanjut ='Ranap' AND b.nm_bangsal not like '%IGD%' AND  b.nm_bangsal not like '%VK%' and b.nm_bangsal not like '%RR%' and b.nm_bangsal not like '%OK%' and b.nm_bangsal not like '%Bayi%' and b.nm_bangsal not like '%Perina%' ORDER BY rp.no_rawat ");
                    jumlahmati = Sequel.cariInteger("select count(DISTINCT no_rawat) from kamar_inap where tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND stts_pulang='Meninggal'");
                    jumlahmati48jam = Sequel.cariInteger("select count(DISTINCT no_rawat) from kamar_inap where tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and (SELECT DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))>=2) AND stts_pulang='Meninggal'");
                    tabMode3.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Hari Perawatan", ":", "", "", hari, "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Lama Perawatan", ":", "", "", lama, "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Pasien H+M", ":", "", "", i, "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati", ":", "", "", jumlahmati, "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati >48 Jam", ":", "", "", jumlahmati48jam, "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Tempat Tidur", ":", "", "", "100", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Jumlah Hari Dalam Periode", ":", "", "", jumlahhari, "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Perhitungan BOR ", ": (" + hari + "/(100 X " + jumlahhari + ")) X 100%", "", "", Valid.SetAngka4((hari / (100 * jumlahhari)) * 100) + " %", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Perhitungan AVLOS ", ": (" + lama + "/" + i + ") Hari", "", "", Valid.SetAngka4(lama / i) + " Hari", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Perhitungan TOI ", ": (((100 X " + jumlahhari + ")-" + hari + ")/" + i + ") Hari", "", "", Valid.SetAngka4(((100 * jumlahhari) - hari) / i) + " Hari", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Perhitungan BTO ", ": (" + i + "/100) Kali", "", "", Valid.SetAngka4(i / 100) + " Kali", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Perhitungan GDR ", ": (" + jumlahmati + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati / i) * 1000) + " %", "", "", ""});
                    tabMode3.addRow(new Object[]{"", "", "", "", "Perhitungan NDR ", ": (" + jumlahmati48jam + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati48jam / i) * 1000) + " %", "", "", ""});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil4() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode4);

            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,reg_periksa.stts_daftar,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,penjab.png_jawab,kelas.nm_kelas,kelas.tarif_kelas "
                    + "from reg_periksa inner join kamar_inap inner join pasien inner join kamar inner join bangsal "
                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal inner join penjab on penjab.kd_pj=reg_periksa.kd_pj inner join kelas on kelas.kd_kelas =kamar_inap.kd_kelas "
                    + "where reg_periksa.status_lanjut='Ranap' and bangsal.nm_bangsal like \"ICU 2%\" and kamar_inap.tgl_masuk between ? "
                    + "and ? " + (Kamar.getText().equals("") ? "" : "and bangsal.nm_bangsal=?") + " and kamar_inap.stts_pulang<>'Pindah Kamar' group by kamar_inap.no_rawat order by reg_periksa.tgl_registrasi");

            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                if (!Kamar.getText().equals("")) {
                    ps.setString(3, Kamar.getText().trim());
                }
                rs = ps.executeQuery();
                i = 1;
                lm = 0;
                br = 0;
                bpjs = 0;
                umum = 0;
                iks = 0;
                pass = 0;
                hari = 0;
                lama = 0;
                while (rs.next()) {
                    ps2 = koneksi.prepareStatement(
                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                            + "WHERE no_rawat=?  and stts_pulang<>'Pindah Kamar'");
                    try {
//                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(1, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
//                            harga=rs2.getDouble("hari")*rs.getDouble("tarif_kelas");
                            ps3 = koneksi.prepareStatement(
                                    "SELECT stts_pulang FROM `kamar_inap` WHERE no_rawat=? and stts_pulang<>'Pindah Kamar'");
                            try {
//                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                ps3.setString(1, rs.getString("no_rawat"));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    ps4 = koneksi.prepareStatement(
                                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                                            + "WHERE no_rawat=?");
                                    try {
                                        //                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                        //                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                        ps4.setString(1, rs.getString("no_rawat"));
                                        rs4 = ps4.executeQuery();
                                        while (rs4.next()) {
                                            if (rs.getString("stts_daftar").equals("Baru")) {
                                                br++;
                                            } else if (rs.getString("stts_daftar").equals("Lama")) {
                                                lm++;
                                            }
                                            if (rs.getString("png_jawab").equals("BPJS KESEHATAN")) {
                                                bpjs++;
                                            } else if (rs.getString("png_jawab").equals("UMUM")) {
                                                umum++;
                                            } else if (rs.getString("png_jawab").equals("IKS")) {
                                                iks++;
                                            } else if (rs.getString("png_jawab").equals("PASSPORT")) {
                                                pass++;
                                            }
                                            tabMode4.addRow(new Object[]{
                                                i, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("stts_daftar"),
                                                rs.getString("nm_pasien"),
                                                rs.getString("kamar"), rs2.getString("tanggal_terkecil"), rs2.getString("tanggal_terbesar"),
                                                rs4.getString("hari"), rs4.getString("lama"), rs3.getString("stts_pulang"), rs.getString("png_jawab")
                                            });
                                            hari = hari + rs4.getDouble("hari");
                                            lama = lama + rs4.getDouble("lama");
                                            i++;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                                    } finally {
                                        if (rs4 != null) {
                                            rs4.close();
                                        }
                                        if (ps4 != null) {
                                            ps4.close();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                                if (ps3 != null) {
                                    ps3.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                if (hari > 0) {
                    i = i - 1;
                    jumlahhari = Sequel.cariInteger("select (to_days('" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "')-to_days('" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'))") + 1;
//                    jumlahpasien=Sequel.cariInteger("SELECT COUNT( DISTINCT rp.no_rawat) from reg_periksa rp inner join kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE rp.tgl_registrasi BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' AND rp.status_lanjut ='Ranap' AND b.nm_bangsal not like '%IGD%' AND  b.nm_bangsal not like '%VK%' and b.nm_bangsal not like '%RR%' and b.nm_bangsal not like '%OK%' and b.nm_bangsal not like '%Bayi%' and b.nm_bangsal not like '%Perina%' ORDER BY rp.no_rawat ");
                    jumlahmati = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE 'ICU 2%'");
                    jumlahmati48jam = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and (SELECT DATEDIFF(MAX(if(ki.tgl_keluar='0000-00-00',current_date(),ki.tgl_keluar)), MIN(ki.tgl_masuk))>=2) " +
                        "AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE 'ICU 2%'");
                    tabMode4.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Hari Perawatan", ":", "", "", hari, "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Lama Perawatan", ":", "", "", lama, "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Pasien H+M", ":", "", "", i, "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati", ":", "", "", jumlahmati, "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati >48 Jam", ":", "", "", jumlahmati48jam, "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Tempat Tidur", ":", "", "", "100", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Jumlah Hari Dalam Periode", ":", "", "", jumlahhari, "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Perhitungan BOR ", ": (" + hari + "/(100 X " + jumlahhari + ")) X 100%", "", "", Valid.SetAngka4((hari / (100 * jumlahhari)) * 100) + " %", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Perhitungan AVLOS ", ": (" + lama + "/" + i + ") Hari", "", "", Valid.SetAngka4(lama / i) + " Hari", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Perhitungan TOI ", ": (((100 X " + jumlahhari + ")-" + hari + ")/" + i + ") Hari", "", "", Valid.SetAngka4(((100 * jumlahhari) - hari) / i) + " Hari", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Perhitungan BTO ", ": (" + i + "/100) Kali", "", "", Valid.SetAngka4(i / 100) + " Kali", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Perhitungan GDR ", ": (" + jumlahmati + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati / i) * 1000) + " %", "", "", ""});
                    tabMode4.addRow(new Object[]{"", "", "", "", "Perhitungan NDR ", ": (" + jumlahmati48jam + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati48jam / i) * 1000) + " %", "", "", ""});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil5() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode5);

            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,reg_periksa.stts_daftar,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,penjab.png_jawab,kelas.nm_kelas,kelas.tarif_kelas "
                    + "from reg_periksa inner join kamar_inap inner join pasien inner join kamar inner join bangsal "
                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal inner join penjab on penjab.kd_pj=reg_periksa.kd_pj inner join kelas on kelas.kd_kelas =kamar_inap.kd_kelas "
                    + "where reg_periksa.status_lanjut='Ranap' and bangsal.nm_bangsal like \"NICU%\" and kamar_inap.tgl_masuk between ? "
                    + "and ? " + (Kamar.getText().equals("") ? "" : "and bangsal.nm_bangsal=?") + " and kamar_inap.stts_pulang<>'Pindah Kamar' group by kamar_inap.no_rawat order by reg_periksa.tgl_registrasi");

            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                if (!Kamar.getText().equals("")) {
                    ps.setString(3, Kamar.getText().trim());
                }
                rs = ps.executeQuery();
                i = 1;
                lm = 0;
                br = 0;
                bpjs = 0;
                umum = 0;
                iks = 0;
                pass = 0;
                hari = 0;
                lama = 0;
                while (rs.next()) {
                    ps2 = koneksi.prepareStatement(
                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                            + "WHERE no_rawat=?  and stts_pulang<>'Pindah Kamar'");
                    try {
//                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(1, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
//                            harga=rs2.getDouble("hari")*rs.getDouble("tarif_kelas");
                            ps3 = koneksi.prepareStatement(
                                    "SELECT stts_pulang FROM `kamar_inap` WHERE no_rawat=? and stts_pulang<>'Pindah Kamar'");
                            try {
//                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                ps3.setString(1, rs.getString("no_rawat"));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    ps4 = koneksi.prepareStatement(
                                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                                            + "WHERE no_rawat=?");
                                    try {
                                        //                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                        //                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                        ps4.setString(1, rs.getString("no_rawat"));
                                        rs4 = ps4.executeQuery();
                                        while (rs4.next()) {
                                            if (rs.getString("stts_daftar").equals("Baru")) {
                                                br++;
                                            } else if (rs.getString("stts_daftar").equals("Lama")) {
                                                lm++;
                                            }
                                            if (rs.getString("png_jawab").equals("BPJS KESEHATAN")) {
                                                bpjs++;
                                            } else if (rs.getString("png_jawab").equals("UMUM")) {
                                                umum++;
                                            } else if (rs.getString("png_jawab").equals("IKS")) {
                                                iks++;
                                            } else if (rs.getString("png_jawab").equals("PASSPORT")) {
                                                pass++;
                                            }
                                            tabMode5.addRow(new Object[]{
                                                i, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("stts_daftar"),
                                                rs.getString("nm_pasien"),
                                                rs.getString("kamar"), rs2.getString("tanggal_terkecil"), rs2.getString("tanggal_terbesar"),
                                                rs4.getString("hari"), rs4.getString("lama"), rs3.getString("stts_pulang"), rs.getString("png_jawab")
                                            });
                                            hari = hari + rs4.getDouble("hari");
                                            lama = lama + rs4.getDouble("lama");
                                            i++;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                                    } finally {
                                        if (rs4 != null) {
                                            rs4.close();
                                        }
                                        if (ps4 != null) {
                                            ps4.close();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                                if (ps3 != null) {
                                    ps3.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                if (hari > 0) {
                    i = i - 1;
                    jumlahhari = Sequel.cariInteger("select (to_days('" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "')-to_days('" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'))") + 1;
//                    jumlahpasien=Sequel.cariInteger("SELECT COUNT( DISTINCT rp.no_rawat) from reg_periksa rp inner join kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE rp.tgl_registrasi BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' AND rp.status_lanjut ='Ranap' AND b.nm_bangsal not like '%IGD%' AND  b.nm_bangsal not like '%VK%' and b.nm_bangsal not like '%RR%' and b.nm_bangsal not like '%OK%' and b.nm_bangsal not like '%Bayi%' and b.nm_bangsal not like '%Perina%' ORDER BY rp.no_rawat ");
                    jumlahmati = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE 'NICU%'");
                    jumlahmati48jam = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and (SELECT DATEDIFF(MAX(if(ki.tgl_keluar='0000-00-00',current_date(),ki.tgl_keluar)), MIN(ki.tgl_masuk))>=2) " +
                        "AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE 'NICU%'");
                    tabMode5.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Hari Perawatan", ":", "", "", hari, "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Lama Perawatan", ":", "", "", lama, "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Pasien H+M", ":", "", "", i, "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati", ":", "", "", jumlahmati, "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati >48 Jam", ":", "", "", jumlahmati48jam, "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Tempat Tidur", ":", "", "", "100", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Jumlah Hari Dalam Periode", ":", "", "", jumlahhari, "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Perhitungan BOR ", ": (" + hari + "/(100 X " + jumlahhari + ")) X 100%", "", "", Valid.SetAngka4((hari / (100 * jumlahhari)) * 100) + " %", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Perhitungan AVLOS ", ": (" + lama + "/" + i + ") Hari", "", "", Valid.SetAngka4(lama / i) + " Hari", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Perhitungan TOI ", ": (((100 X " + jumlahhari + ")-" + hari + ")/" + i + ") Hari", "", "", Valid.SetAngka4(((100 * jumlahhari) - hari) / i) + " Hari", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Perhitungan BTO ", ": (" + i + "/100) Kali", "", "", Valid.SetAngka4(i / 100) + " Kali", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Perhitungan GDR ", ": (" + jumlahmati + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati / i) * 1000) + " %", "", "", ""});
                    tabMode5.addRow(new Object[]{"", "", "", "", "Perhitungan NDR ", ": (" + jumlahmati48jam + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati48jam / i) * 1000) + " %", "", "", ""});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil6() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode6);

            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,reg_periksa.stts_daftar,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,penjab.png_jawab,kelas.nm_kelas,kelas.tarif_kelas "
                    + "from reg_periksa inner join kamar_inap inner join pasien inner join kamar inner join bangsal "
                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal inner join penjab on penjab.kd_pj=reg_periksa.kd_pj inner join kelas on kelas.kd_kelas =kamar_inap.kd_kelas "
                    + "where reg_periksa.status_lanjut='Ranap' and bangsal.nm_bangsal like \"PICU%\" and kamar_inap.tgl_masuk between ? "
                    + "and ? " + (Kamar.getText().equals("") ? "" : "and bangsal.nm_bangsal=?") + " and kamar_inap.stts_pulang<>'Pindah Kamar' group by kamar_inap.no_rawat order by reg_periksa.tgl_registrasi");

            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                if (!Kamar.getText().equals("")) {
                    ps.setString(3, Kamar.getText().trim());
                }
                rs = ps.executeQuery();
                i = 1;
                lm = 0;
                br = 0;
                bpjs = 0;
                umum = 0;
                iks = 0;
                pass = 0;
                hari = 0;
                lama = 0;
                while (rs.next()) {
                    ps2 = koneksi.prepareStatement(
                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                            + "WHERE no_rawat=?  and stts_pulang<>'Pindah Kamar'");
                    try {
//                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(1, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
//                            harga=rs2.getDouble("hari")*rs.getDouble("tarif_kelas");
                            ps3 = koneksi.prepareStatement(
                                    "SELECT stts_pulang FROM `kamar_inap` WHERE no_rawat=? and stts_pulang<>'Pindah Kamar'");
                            try {
//                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                ps3.setString(1, rs.getString("no_rawat"));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    ps4 = koneksi.prepareStatement(
                                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                                            + "WHERE no_rawat=?");
                                    try {
                                        //                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                        //                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                        ps4.setString(1, rs.getString("no_rawat"));
                                        rs4 = ps4.executeQuery();
                                        while (rs4.next()) {
                                            if (rs.getString("stts_daftar").equals("Baru")) {
                                                br++;
                                            } else if (rs.getString("stts_daftar").equals("Lama")) {
                                                lm++;
                                            }
                                            if (rs.getString("png_jawab").equals("BPJS KESEHATAN")) {
                                                bpjs++;
                                            } else if (rs.getString("png_jawab").equals("UMUM")) {
                                                umum++;
                                            } else if (rs.getString("png_jawab").equals("IKS")) {
                                                iks++;
                                            } else if (rs.getString("png_jawab").equals("PASSPORT")) {
                                                pass++;
                                            }
                                            tabMode6.addRow(new Object[]{
                                                i, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("stts_daftar"),
                                                rs.getString("nm_pasien"),
                                                rs.getString("kamar"), rs2.getString("tanggal_terkecil"), rs2.getString("tanggal_terbesar"),
                                                rs4.getString("hari"), rs4.getString("lama"), rs3.getString("stts_pulang"), rs.getString("png_jawab")
                                            });
                                            hari = hari + rs4.getDouble("hari");
                                            lama = lama + rs4.getDouble("lama");
                                            i++;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                                    } finally {
                                        if (rs4 != null) {
                                            rs4.close();
                                        }
                                        if (ps4 != null) {
                                            ps4.close();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                                if (ps3 != null) {
                                    ps3.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                if (hari > 0) {
                    i = i - 1;
                    jumlahhari = Sequel.cariInteger("select (to_days('" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "')-to_days('" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'))") + 1;
//                    jumlahpasien=Sequel.cariInteger("SELECT COUNT( DISTINCT rp.no_rawat) from reg_periksa rp inner join kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE rp.tgl_registrasi BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' AND rp.status_lanjut ='Ranap' AND b.nm_bangsal not like '%IGD%' AND  b.nm_bangsal not like '%VK%' and b.nm_bangsal not like '%RR%' and b.nm_bangsal not like '%OK%' and b.nm_bangsal not like '%Bayi%' and b.nm_bangsal not like '%Perina%' ORDER BY rp.no_rawat ");
                    jumlahmati = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE 'PICU%'");
                    jumlahmati48jam = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and (SELECT DATEDIFF(MAX(if(ki.tgl_keluar='0000-00-00',current_date(),ki.tgl_keluar)), MIN(ki.tgl_masuk))>=2) " +
                        "AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE 'PICU%'");
                    tabMode6.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Hari Perawatan", ":", "", "", hari, "", "", });
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Lama Perawatan", ":", "", "", lama, "", "", });
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Pasien H+M", ":", "", "", i, "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati", ":", "", "", jumlahmati, "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati >48 Jam", ":", "", "", jumlahmati48jam, "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Tempat Tidur", ":", "", "", "100", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Jumlah Hari Dalam Periode", ":", "", "", jumlahhari, "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Perhitungan BOR ", ": (" + hari + "/(100 X " + jumlahhari + ")) X 100%", "", "", Valid.SetAngka4((hari / (100 * jumlahhari)) * 100) + " %", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Perhitungan AVLOS ", ": (" + lama + "/" + i + ") Hari", "", "", Valid.SetAngka4(lama / i) + " Hari", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Perhitungan TOI ", ": (((100 X " + jumlahhari + ")-" + hari + ")/" + i + ") Hari", "", "", Valid.SetAngka4(((100 * jumlahhari) - hari) / i) + " Hari", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Perhitungan BTO ", ": (" + i + "/100) Kali", "", "", Valid.SetAngka4(i / 100) + " Kali", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Perhitungan GDR ", ": (" + jumlahmati + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati / i) * 1000) + " %", "", "", ""});
                    tabMode6.addRow(new Object[]{"", "", "", "", "Perhitungan NDR ", ": (" + jumlahmati48jam + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati48jam / i) * 1000) + " %", "", "", ""});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }

    public void tampil7() {
        try {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Valid.tabelKosong(tabMode7);

            ps = koneksi.prepareStatement(
                    "select reg_periksa.no_rawat,reg_periksa.tgl_registrasi,reg_periksa.no_rkm_medis,reg_periksa.stts_daftar,pasien.nm_pasien,concat(kamar_inap.kd_kamar,' ',bangsal.nm_bangsal) as kamar,penjab.png_jawab,kelas.nm_kelas,kelas.tarif_kelas "
                    + "from reg_periksa inner join kamar_inap inner join pasien inner join kamar inner join bangsal "
                    + "on kamar_inap.no_rawat=reg_periksa.no_rawat and reg_periksa.no_rkm_medis=pasien.no_rkm_medis "
                    + "and kamar_inap.kd_kamar=kamar.kd_kamar and kamar.kd_bangsal=bangsal.kd_bangsal inner join penjab on penjab.kd_pj=reg_periksa.kd_pj inner join kelas on kelas.kd_kelas =kamar_inap.kd_kelas "
                    + "where reg_periksa.status_lanjut='Ranap' and (bangsal.nm_bangsal like \"HCU%\" or bangsal.nm_bangsal like \"Perina%\") and kamar_inap.tgl_masuk between ? "
                    + "and ? " + (Kamar.getText().equals("") ? "" : "and bangsal.nm_bangsal=?") + " and kamar_inap.stts_pulang<>'Pindah Kamar' group by kamar_inap.no_rawat order by reg_periksa.tgl_registrasi");

            try {
                ps.setString(1, Valid.SetTgl(Tgl1.getSelectedItem() + ""));
                ps.setString(2, Valid.SetTgl(Tgl2.getSelectedItem() + ""));
                if (!Kamar.getText().equals("")) {
                    ps.setString(3, Kamar.getText().trim());
                }
                rs = ps.executeQuery();
                i = 1;
                lm = 0;
                br = 0;
                bpjs = 0;
                umum = 0;
                iks = 0;
                pass = 0;
                hari = 0;
                lama = 0;
                while (rs.next()) {
                    ps2 = koneksi.prepareStatement(
                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                            + "WHERE no_rawat=?  and stts_pulang<>'Pindah Kamar'");
                    try {
//                        ps2.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                        ps2.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                        ps2.setString(1, rs.getString("no_rawat"));
                        rs2 = ps2.executeQuery();
                        while (rs2.next()) {
//                            harga=rs2.getDouble("hari")*rs.getDouble("tarif_kelas");
                            ps3 = koneksi.prepareStatement(
                                    "SELECT stts_pulang FROM `kamar_inap` WHERE no_rawat=? and stts_pulang<>'Pindah Kamar'");
                            try {
//                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
//                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                ps3.setString(1, rs.getString("no_rawat"));
                                rs3 = ps3.executeQuery();
                                while (rs3.next()) {
                                    ps4 = koneksi.prepareStatement(
                                            "SELECT MIN(tgl_masuk) AS tanggal_terkecil,MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)) AS tanggal_terbesar,"
                                            + "DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk)) + 1 as hari,"
                                            + "if(DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))='0','1',DATEDIFF(MAX(if(kamar_inap.tgl_keluar='0000-00-00',current_date(),kamar_inap.tgl_keluar)), MIN(tgl_masuk))) as lama FROM kamar_inap "
                                            + "WHERE no_rawat=?");
                                    try {
                                        //                                 ps3.setString(1,Valid.SetTgl(Tgl1.getSelectedItem()+""));
                                        //                                 ps3.setString(2,Valid.SetTgl(Tgl2.getSelectedItem()+""));
                                        ps4.setString(1, rs.getString("no_rawat"));
                                        rs4 = ps4.executeQuery();
                                        while (rs4.next()) {
                                            if (rs.getString("stts_daftar").equals("Baru")) {
                                                br++;
                                            } else if (rs.getString("stts_daftar").equals("Lama")) {
                                                lm++;
                                            }
                                            if (rs.getString("png_jawab").equals("BPJS KESEHATAN")) {
                                                bpjs++;
                                            } else if (rs.getString("png_jawab").equals("UMUM")) {
                                                umum++;
                                            } else if (rs.getString("png_jawab").equals("IKS")) {
                                                iks++;
                                            } else if (rs.getString("png_jawab").equals("PASSPORT")) {
                                                pass++;
                                            }
                                            tabMode7.addRow(new Object[]{
                                                i, rs.getString("no_rawat"), rs.getString("no_rkm_medis"), rs.getString("stts_daftar"),
                                                rs.getString("nm_pasien"),
                                                rs.getString("kamar"), rs2.getString("tanggal_terkecil"), rs2.getString("tanggal_terbesar"),
                                                rs4.getString("hari"), rs4.getString("lama"), rs3.getString("stts_pulang"), rs.getString("png_jawab")
                                            });
                                            hari = hari + rs4.getDouble("hari");
                                            lama = lama + rs4.getDouble("lama");
                                            i++;
                                        }
                                    } catch (Exception e) {
                                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                                    } finally {
                                        if (rs4 != null) {
                                            rs4.close();
                                        }
                                        if (ps4 != null) {
                                            ps4.close();
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                            } finally {
                                if (rs3 != null) {
                                    rs3.close();
                                }
                                if (ps3 != null) {
                                    ps3.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
                    } finally {
                        if (rs2 != null) {
                            rs2.close();
                        }
                        if (ps2 != null) {
                            ps2.close();
                        }
                    }
                }
                if (hari > 0) {
                    i = i - 1;
                    jumlahhari = Sequel.cariInteger("select (to_days('" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "')-to_days('" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "'))") + 1;
//                    jumlahpasien=Sequel.cariInteger("SELECT COUNT( DISTINCT rp.no_rawat) from reg_periksa rp inner join kamar_inap ki on ki.no_rawat=rp.no_rawat INNER JOIN kamar k on k.kd_kamar=ki.kd_kamar INNER JOIN bangsal b on b.kd_bangsal=k.kd_bangsal WHERE rp.tgl_registrasi BETWEEN '"+Valid.SetTgl(Tgl1.getSelectedItem()+"")+"' AND '"+Valid.SetTgl(Tgl2.getSelectedItem()+"")+"' AND rp.status_lanjut ='Ranap' AND b.nm_bangsal not like '%IGD%' AND  b.nm_bangsal not like '%VK%' and b.nm_bangsal not like '%RR%' and b.nm_bangsal not like '%OK%' and b.nm_bangsal not like '%Bayi%' and b.nm_bangsal not like '%Perina%' ORDER BY rp.no_rawat ");
                    jumlahmati = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' AND ki.stts_pulang='Meninggal' and b.nm_bangsal LIKE (b.nm_bangsal like 'HCU%' or b.nm_bangsal like 'Perina%')");
                    jumlahmati48jam = Sequel.cariInteger("select count(DISTINCT ki.no_rawat) from kamar_inap ki\n" +
                        "inner join kamar k on k.kd_kamar=ki.kd_kamar inner join bangsal b on b.kd_bangsal=k.kd_bangsal \n" +
                        "where ki.tgl_masuk between '" + Valid.SetTgl(Tgl1.getSelectedItem() + "") + "' and '" + Valid.SetTgl(Tgl2.getSelectedItem() + "") + "' and (SELECT DATEDIFF(MAX(if(ki.tgl_keluar='0000-00-00',current_date(),ki.tgl_keluar)), MIN(ki.tgl_masuk))>=2) " +
                        "AND ki.stts_pulang='Meninggal' and (b.nm_bangsal like 'HCU%' or b.nm_bangsal like 'Perina%')");
                    tabMode7.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Hari Perawatan", ":", "", "", hari, "", "", });
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Lama Perawatan", ":", "", "", lama, "", "", });
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Pasien H+M", ":", "", "", i, "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati", ":", "", "", jumlahmati, "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Pasien Mati >48 Jam", ":", "", "", jumlahmati48jam, "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Tempat Tidur", ":", "", "", "100", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Jumlah Hari Dalam Periode", ":", "", "", jumlahhari, "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Perhitungan BOR ", ": (" + hari + "/(100 X " + jumlahhari + ")) X 100%", "", "", Valid.SetAngka4((hari / (100 * jumlahhari)) * 100) + " %", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Perhitungan AVLOS ", ": (" + lama + "/" + i + ") Hari", "", "", Valid.SetAngka4(lama / i) + " Hari", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Perhitungan TOI ", ": (((100 X " + jumlahhari + ")-" + hari + ")/" + i + ") Hari", "", "", Valid.SetAngka4(((100 * jumlahhari) - hari) / i) + " Hari", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Perhitungan BTO ", ": (" + i + "/100) Kali", "", "", Valid.SetAngka4(i / 100) + " Kali", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Perhitungan GDR ", ": (" + jumlahmati + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati / i) * 1000) + " %", "", "", ""});
                    tabMode7.addRow(new Object[]{"", "", "", "", "Perhitungan NDR ", ": (" + jumlahmati48jam + "/" + i + ") X 1000%", "", "", Valid.SetAngka4((jumlahmati48jam / i) * 1000) + " %", "", "", ""});
                }
            } catch (Exception e) {
                System.out.println("laporan.DlgHitungBOR.tampil() : " + e);
            } finally {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
            }
            this.setCursor(Cursor.getDefaultCursor());
        } catch (Exception e) {
            System.out.println("Notifikasi : " + e);
        }
    }
    
    private void getData() {
        int row = Tabel3.getSelectedRow();
        if (row != -1) {
            TKd.setText(tabMode3.getValueAt(row, 0).toString());
        }
    }

}
