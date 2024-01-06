/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Code;

import javax.swing.JOptionPane;

/**
 *
 * @author Asus Technology
 */
public class Profile{
    public static String namadepan;
    public static String namabelakang;
    public static String lokasi;
    public static String email;
    
    public void Insert(){
        try{
            Akun profil = new Akun();
            String sql = "INSERT INTO profile VALUES ('"+profil.Username+"','"+namadepan+"','"+namabelakang+"','"+lokasi+"','"+email+"')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Menyimpan Data Berhasil");
            new indexPage().setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void getProfile(){
        try{
            Akun profil = new Akun();
            String sql = "SELECT * FROM profile WHERE Username=('"+profil.Username+"')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            namadepan = rs.getString("Nama_Depan");
            namabelakang = rs.getString("Nama_Belakang");
            email = rs.getString("Email");
            lokasi = rs.getString("Alamat");
            new profilPage().setVisible(true);
        }catch(Exception e){
            new profilPage().setVisible(true);
        }
    }
}
