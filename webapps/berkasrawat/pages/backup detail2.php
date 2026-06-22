detail2nonhapus.php
<?php
            echo("<table width='99.6%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head'>
                        <td><div align='left'>Data : $jumlah</div></td><td><input name='BtnKeluar' type='submit' class='button' value='&nbsp;&nbsp;&nbsp;Keluar&nbsp;&nbsp;&nbsp;' /></td>                        
                    </tr>     
                 </table>");
            
            $BtnKeluar=isset($_POST['BtnKeluar'])?$_POST['BtnKeluar']:NULL;
            if (isset($BtnKeluar)) {
                echo"<meta http-equiv='refresh' content='1;URL=?act=List&action=Keluar'>";
            }        
        ?>   

detail2.php
<?php
            if ($action=="HAPUS") {
                unlink($norawat["lokasi_file"]);
                Hapus(" berkas_digital_perawatan "," no_rawat ='".validTeks($norawat["no_rawat"])."' and kode ='".validTeks($norawat["kode"])."' and lokasi_file='".validTeks($norawat["lokasi_file"])."' ","?act=Detail2&action=TAMBAH&iyem=".encrypt_decrypt("{\"no_rawat\":\"".validTeks($no_rawat)."\"}","e"));
            }
            
            echo("<table width='99.6%' border='0' align='center' cellpadding='0' cellspacing='0' class='tbl_form'>
                    <tr class='head'>
                        <td><div align='left'>Data : $jumlah</div></td><td><input name='BtnKeluar' type='submit' class='button' value='&nbsp;&nbsp;&nbsp;Keluar&nbsp;&nbsp;&nbsp;' /></td>                        
                    </tr>     
                 </table>");
            
            $BtnKeluar=isset($_POST['BtnKeluar'])?$_POST['BtnKeluar']:NULL;
            if (isset($BtnKeluar)) {
                echo"<meta http-equiv='refresh' content='1;URL=?act=List&action=Keluar'>";
            }        
        ?> 