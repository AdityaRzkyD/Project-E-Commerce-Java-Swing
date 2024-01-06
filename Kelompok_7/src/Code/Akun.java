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
public class Akun {
    public static String Username;
    public static String Password;
    public static String Nohp;
    
    public void register(){
        try{
            String sql = "INSERT INTO akun VALUES ('"+Username+"','"+Password+"','"+Nohp+"')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Menyimpan Data Berhasil");
            new loginPage().setVisible(true);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void login(){
        try{
            String sql = "SELECT * FROM akun WHERE Username=('"+Username+"')AND Password=('"+Password+"')";
            java.sql.Connection conn = Connect.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            java.sql.ResultSet rs = pst.executeQuery(sql);
            if (rs.next()){
                Nohp = rs.getString("Nohp");
                if(Username.equals(rs.getString("Username")) && Password.equals(rs.getString("Password"))){
                    JOptionPane.showMessageDialog(null, "Berhasil Login");
                    new indexPage().setVisible(true);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Username atau Password salah");
                new loginPage().setVisible(true);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
