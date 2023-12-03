package models;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConectaBD {

    public static Connection con;
    private static String bd = "bdamericawhitman";
    public static String usuario = "root";
    public static String passw = "12345";
    public static String url = "jdbc:mysql://localhost/" + bd;

    public static Connection abrir() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, usuario, passw);
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
             System.out.println("Error al conectarse a la BD");
            e.printStackTrace();
        }
        return con;
    }

    public static void cerrar() {
        try {
            con.close();
        } catch (Exception e) {
             System.out.println("Error al cerrar conexion:\n" + e);
        }
    }
}
