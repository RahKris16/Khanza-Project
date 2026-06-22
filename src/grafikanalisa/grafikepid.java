/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package grafikanalisa;

/**
 *
 * @author Via
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Font;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Toolkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Via
 */
public class grafikepid extends JDialog {
      sekuel Sequel = new sekuel();
      validasi Valid = new validasi();
      public grafikepid(String title,String symbol) {
        // super(title);
          setTitle(title);
         JPanel chartPanel = createDemoPanel(symbol);
         
         chartPanel.setSize(screen.width,screen.height);
         setContentPane(chartPanel);       
         
         //setSize(screen.width,screen.height);
         setModal(true);
         //setUndecorated(true);
         setIconImage(new ImageIcon(super.getClass().getResource("/picture/addressbook-edit24.png")).getImage());
         pack();
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      }
      Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
        /**
           * Creates a dataset.
           *
           * @return A dataset.
           */

    public static CategoryDataset createDataset1(String symbol) { //data grafik nilai K dan D

          DefaultCategoryDataset result = new DefaultCategoryDataset();
          String series1 = "Desa";
          String series2 = "Desa";

            try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery("SELECT kelurahan.kd_kel,kelurahan.nm_kel,COUNT(kelurahan.kd_kel) as jml \n" +
                    "FROM reg_periksa INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis\n" +
                    "INNER JOIN diagnosa_pasien ON diagnosa_pasien.no_rawat=reg_periksa.no_rawat INNER JOIN penyakit ON penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit INNER JOIN kelurahan ON kelurahan.kd_kel=pasien.kd_kel \n" +
                    "INNER JOIN kecamatan ON kecamatan.kd_kec=pasien.kd_kec INNER JOIN kabupaten ON kabupaten.kd_kab=pasien.kd_kab "+symbol+" AND (diagnosa_pasien.kd_penyakit='B54' \n" +
                    "OR diagnosa_pasien.kd_penyakit='A90' OR diagnosa_pasien.kd_penyakit='J18.9' OR diagnosa_pasien.kd_penyakit='J69.0' OR diagnosa_pasien.kd_penyakit='J18.0' OR diagnosa_pasien.kd_penyakit='A06.0' OR diagnosa_pasien.kd_penyakit='A01.0' \n" +
                    "OR diagnosa_pasien.kd_penyakit='B15' OR diagnosa_pasien.kd_penyakit='A92.0' OR diagnosa_pasien.kd_penyakit='B05.9' OR diagnosa_pasien.kd_penyakit='B36.9' OR diagnosa_pasien.kd_penyakit='A37.9' OR diagnosa_pasien.kd_penyakit='A82.9' \n" +
                    "OR diagnosa_pasien.kd_penyakit='A27.9' OR diagnosa_pasien.kd_penyakit='A09' OR diagnosa_pasien.kd_penyakit='A33' OR diagnosa_pasien.kd_penyakit='A35' OR diagnosa_pasien.kd_penyakit='J11' OR diagnosa_pasien.kd_penyakit='B34.2' OR diagnosa_pasien.kd_penyakit='A91' OR diagnosa_pasien.kd_penyakit='A09.9') \n" +
                    "and reg_periksa.status_lanjut='Ranap' AND kelurahan.nm_kel<>'-'\n" +
                    "GROUP BY kelurahan.kd_kel ORDER BY kelurahan.nm_kel ASC");
                while (rs.next()) {
                    String tksbr=rs.getString(2)+" ("+rs.getString(3)+")";
                    double field1=rs.getDouble(3);
                    double field2=rs.getDouble(3);

                    //result.addValue(field1, series1,tksbr);
                    result.addValue(field2, series1,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
            return result;
       }

       /**
          * Creates a dataset.
          *
          * @return A dataset.
          */
        public static CategoryDataset createDataset2(String symbol) {//grafik volume
            DefaultCategoryDataset result = new DefaultCategoryDataset();

             String series1 = "Desa";
             String series2 = "Desa";

             try {
                Statement stat = koneksiDB.condb().createStatement();
                ResultSet rs = stat.executeQuery("SELECT kelurahan.kd_kel,kelurahan.nm_kel,COUNT(kelurahan.kd_kel) as jml \n" +
                    "FROM reg_periksa INNER JOIN pasien ON pasien.no_rkm_medis=reg_periksa.no_rkm_medis\n" +
                    "INNER JOIN diagnosa_pasien ON diagnosa_pasien.no_rawat=reg_periksa.no_rawat INNER JOIN penyakit ON penyakit.kd_penyakit=diagnosa_pasien.kd_penyakit INNER JOIN kelurahan ON kelurahan.kd_kel=pasien.kd_kel \n" +
                    "INNER JOIN kecamatan ON kecamatan.kd_kec=pasien.kd_kec INNER JOIN kabupaten ON kabupaten.kd_kab=pasien.kd_kab "+symbol+" AND (diagnosa_pasien.kd_penyakit='B54' \n" +
                    "OR diagnosa_pasien.kd_penyakit='A90' OR diagnosa_pasien.kd_penyakit='J18.9' OR diagnosa_pasien.kd_penyakit='J69.0' OR diagnosa_pasien.kd_penyakit='J18.0' OR diagnosa_pasien.kd_penyakit='A06.0' OR diagnosa_pasien.kd_penyakit='A01.0' \n" +
                    "OR diagnosa_pasien.kd_penyakit='B15' OR diagnosa_pasien.kd_penyakit='A92.0' OR diagnosa_pasien.kd_penyakit='B05.9' OR diagnosa_pasien.kd_penyakit='B36.9' OR diagnosa_pasien.kd_penyakit='A37.9' OR diagnosa_pasien.kd_penyakit='A82.9' \n" +
                    "OR diagnosa_pasien.kd_penyakit='A27.9' OR diagnosa_pasien.kd_penyakit='A09' OR diagnosa_pasien.kd_penyakit='A33' OR diagnosa_pasien.kd_penyakit='A35' OR diagnosa_pasien.kd_penyakit='J11' OR diagnosa_pasien.kd_penyakit='B34.2' OR diagnosa_pasien.kd_penyakit='A91' OR diagnosa_pasien.kd_penyakit='A09.9') \n" +
                    "and reg_periksa.status_lanjut='Ranap' AND kelurahan.nm_kel<>'-'\n" +
                    "GROUP BY kelurahan.kd_kel ORDER BY kelurahan.nm_kel ASC");
                while (rs.next()) {
                    String tksbr=rs.getString(2)+" ("+rs.getString(3)+")";
                    double field1=rs.getDouble(3);
                    double field2=rs.getDouble(3);

                    result.addValue(field1, series1,tksbr);
                    //result.addValue(field2, series2,tksbr);
                }
            } catch (SQLException e) {
                System.out.println("Notifikasi : " + e);
            }
             return result;
         }

         /**
          * Creates a chart.
          *
          * @return A chart.
          */
         private static JFreeChart createChart(String symbol) {

             CategoryDataset dataset1 = createDataset1(symbol);
             NumberAxis rangeAxis1 = new NumberAxis("Jumlah");
             rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
             renderer1.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             CategoryPlot subplot1 = new CategoryPlot(dataset1, null, rangeAxis1,
                     renderer1);
             subplot1.setDomainGridlinesVisible(true);

             CategoryDataset dataset2 = createDataset2(symbol);
             NumberAxis rangeAxis2 = new NumberAxis("Jumlah");
             rangeAxis2.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
             BarRenderer renderer2 = new BarRenderer();
             renderer2.setBaseToolTipGenerator(
                     new StandardCategoryToolTipGenerator());
             CategoryPlot subplot2 = new CategoryPlot(dataset2, null, rangeAxis2,
                     renderer2);
             subplot2.setDomainGridlinesVisible(true);

             CategoryAxis domainAxis = new CategoryAxis("Desa");
             CombinedDomainCategoryPlot plot = new CombinedDomainCategoryPlot(domainAxis);
             domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_90); // Rotasi label 90 derajat (vertikal)
             domainAxis.setCategoryMargin(0.05); // mengurangi jarak antar label
             
//             CombinedCategoryPlot plot = new CombinedCategoryPlot(
//                     domainAxis, new NumberAxis("Range"));
             plot.add(subplot1,2 );
             plot.add(subplot2,1 );

             JFreeChart result = new JFreeChart(
                     "",
                     new Font("SansSerif", Font.PLAIN,6 ), plot, true);
             return result;

         }

         /**
          * Creates a panel for the demo (used by SuperDemo.java).
          *
          * @return A panel.
          */

         public static JPanel createDemoPanel(String symbol) {
             JFreeChart chart = createChart(symbol);
             return new ChartPanel(chart);
         }

         /**
          * Starting point for the demonstration application.
          *
          * @param args  ignored.
          */

//        public static void main(String args[]){
//            //        String title = "test Combined Category Plot Demo 1";
//        cocografik demo = new cocografik("aali");
//        JFrame v = new JFrame(title);
//        v.add(demo);
//        v.setBackground(Color.BLUE);
//        v.setSize(new Dimension(1200, 700));
//        v.setDefaultCloseOperation(v.EXIT_ON_CLOSE);
//        v.setVisible(true);
//        }
//           public static void main(String[] args) {
//             String title = "Combined Category Plot Demo ";
//             CombinedCategoryPlotDemo1 demo = new CombinedCategoryPlotDemo1(title);
//             demo.pack();
////             RefineryUtilities.centerFrameOnScreen(demo);
//             demo.setVisible(true);
//
//         }
}

