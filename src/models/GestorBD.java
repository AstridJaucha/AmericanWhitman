package models;

import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import models.Personal;
import views.Login;
import views.RegistroAlumno;

public class GestorBD {

    Connection conn = null;
    Statement stm = null;
    ResultSet rsProd;
    int resultUpdate = 0;

    public GestorBD() {
        conn = ConectaBD.abrir();
    }

    public boolean InsertarAlumno(String dniAlumno, String nombreAlumno, String apePaterno, String apeMaterno, String fechNacimiento, String domicilio, String dniPadre, String dniMadre, String dniApoderado, int telefono1, int telefono2, String email, String discapacidad, String grupoSangui, String alergias, String nivel, String grado, String seccion, String codigoModular, String sexo) {
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;
            JOptionPane.showMessageDialog(null, "GESTORBD datos recibidos: " + dniAlumno
                    + nombreAlumno
                    + apePaterno
                    + apeMaterno
                    + fechNacimiento
                    + domicilio
                    + dniPadre
                    + dniMadre
                    + dniApoderado
                    + telefono1
                    + telefono2
                    + email
                    + discapacidad
                    + grupoSangui
                    + alergias
                    + nivel
                    + grado
                    + seccion
                    + codigoModular
                    + sexo);
            String query = "INSERT INTO alumno (dniAlumno, nombreAlumno, apePaterno, apeMaterno, fechNacimiento, sexo, domicilio, dniPadre, dniMadre, dniApoderado, telefono1, telefono2, email, discapacidad, grupoSangui, alergias, nivel, grado, seccion, codigoModular) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
            ps = cn.prepareStatement(query);

            ps.setString(1, dniAlumno);
            ps.setString(2, nombreAlumno);
            ps.setString(3, apePaterno);
            ps.setString(4, apeMaterno);
            ps.setString(5, fechNacimiento);
            ps.setString(6, sexo);
            ps.setString(7, domicilio);
            ps.setString(8, dniPadre);
            ps.setString(9, dniMadre);
            ps.setString(10, dniApoderado);
            ps.setInt(11, telefono1);
            ps.setInt(12, telefono2);
            ps.setString(13, email);
            ps.setString(14, discapacidad);
            ps.setString(15, grupoSangui);
            ps.setString(16, alergias);
            ps.setString(17, nivel);
            ps.setString(18, grado);
            ps.setString(19, seccion);
            ps.setString(20, codigoModular);

            int resultUpdate = ps.executeUpdate();

            if (resultUpdate != 0) {
                ConectaBD.cerrar();
                JOptionPane.showMessageDialog(null, "Alumno ingresado");
                return true;
            } else {
                ConectaBD.cerrar();
                JOptionPane.showMessageDialog(null, "Alumno no ingresado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            return false;
        }
    }

    public static void DatosComboBoxNivel(JComboBox<String> comboBox) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();

            String query = "SELECT nivel FROM nivel_educativo;";
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String nivel = rs.getString("nivel");
                comboBox.addItem(nivel);
            }

            ConectaBD.cerrar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        }
    }

    public static void DatosComboBoxGrado(JComboBox<String> comboBox) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();

            String query = "SELECT grado FROM grado;";
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String nivel = rs.getString("grado");
                comboBox.addItem(nivel);
            }

            ConectaBD.cerrar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        }
    }

    public static void DatosComboBoxSeccion(JComboBox<String> comboBox) {
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;

        try {
            conn = ConectaBD.abrir();
            stm = conn.createStatement();

            String query = "SELECT seccion FROM seccion;";
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String nivel = rs.getString("seccion");
                comboBox.addItem(nivel);
            }

            ConectaBD.cerrar();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        }
    }

    public boolean Validar(String usuario, String clave) {
        try {

            conn = ConectaBD.abrir();
            stm = conn.createStatement();
            rsProd = stm.executeQuery("SELECT idPersonal FROM personal WHERE idPersonal = '" + usuario + "' AND ClavePers ='" + clave + "';");

            if (rsProd.next()) {
                ConectaBD.cerrar();

                return true; // Usuario válido 
            } else {
                ConectaBD.cerrar();
                JOptionPane.showMessageDialog(null, "Usuario invalido");
                return false; // Usuario inválido

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            return false;
        }
    }
}
