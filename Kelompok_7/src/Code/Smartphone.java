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
public class Smartphone extends Barang{
    public static String jenisbarang;
    public static int total;
    private static int quantity;
    
    public Smartphone(String namabarang){
        super(namabarang);
        this.jenisbarang = "Smartphone";
    }
    
    public Smartphone(int quantity){
        this.quantity = quantity;
    }
    
    public int getQuantity(){
        return quantity;
    }
    
    public void getBarang(){
        try{
            String sql = "SELECT * FROM barang WHERE Nama_Barang=('"+namabarang+"')AND Jenis_Barang=('"+jenisbarang+"')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            rs.next();
            kodebarang = rs.getString("Kode_Barang");
            namabarang = rs.getString("Nama_Barang");
            hargabarang = rs.getInt("Harga_Barang");
            jenisbarang = rs.getString("Jenis_Barang");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void addCart(){
        try{
            total = hargabarang * quantity;
            String sql = "INSERT INTO keranjang VALUES ('"+kodebarang+"','"+namabarang+"','"+hargabarang+"','"+quantity+"','"+total+"')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Menyimpan Barang Ke Keranjang");
            new keranjangPage().setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void Bayar(){
        try{
            Akun akun = new Akun();
            total = hargabarang * quantity;
            String sql = "INSERT INTO Pesanan(Username, NamaBarang, Harga, Status) VALUES ('"+akun.Username+"','"+namabarang+"','"+total+"','Dalam Proses')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Pembelian Berhasil");
            new pesananPage().setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
