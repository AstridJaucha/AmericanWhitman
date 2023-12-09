package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Alumno;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import views.RegistroAlumno;
import views.ListaAlumnos;
import views.RatificarMatricula;

public class GestorBD {

    Connection conn = null;
    Statement stm = null;
    ResultSet rsProd;
    int resultUpdate = 0;
    private RegistroAlumno alumno;
    private Alumno al;
    private ListaAlumnos lista;
    private Jovenes nin;
    private RatificarMatricula rat;
    private DatosExcel dat;

    public GestorBD(RegistroAlumno alumno, Alumno al, ListaAlumnos lista, Jovenes nin, RatificarMatricula rat, DatosExcel dat) {
        this.alumno = alumno;
        this.al = al;
        this.lista = lista;
        this.nin = nin;
        this.rat = rat;
        this.dat = dat;
        conn = ConectaBD.abrir();
    }

    public int registraPersonal(String id) {
        try ( Connection cn = ConectaBD.abrir();  PreparedStatement ps = cn.prepareStatement("INSERT INTO registropersonal (idPersonal, fechaHora) VALUES (?, NOW());")) {

            ps.setString(1, id);
            int resultUpdate = ps.executeUpdate();

            if (resultUpdate != 0) {
                System.out.println("Personal registrado exitosamente");
                return 1;  // 1 indica éxito
            } else {
                System.out.println("Error en el registro de personal");
                return 0;  // 0 indica fallo
            }

        } catch (SQLException e) {
            System.err.println("Error en la BD: " + e.getMessage());
            e.printStackTrace();
            return -1;  // -1 indica un error inesperado
        }
    }

    public void tablaTodos(DefaultTableModel modelo) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            String query = "SELECT codAlumno, dniAlumno, nombreAlumno, apePaterno, apeMaterno, telefono1, telefono2, nivel, grado, seccion, codigoModular FROM alumno";
            ps = cn.prepareStatement(query);
            modelo.setRowCount(0);

            try ( ResultSet rs = ps.executeQuery()) {
                modelo.setColumnIdentifiers(new Object[]{"Código Alumno", "DNI Alumno", "Nombre Alumno", "Apellido Paterno", "Apellido Materno",
                    "Teléfono 1", "Teléfono 2", "Nivel", "Grado", "Sección", "Código Modular"});

                while (rs.next()) {
                    Object[] fila = {
                        rs.getString("codAlumno"),
                        rs.getString("dniAlumno"),
                        rs.getString("nombreAlumno"),
                        rs.getString("apePaterno"),
                        rs.getString("apeMaterno"),
                        rs.getInt("telefono1"),
                        rs.getInt("telefono2"),
                        rs.getString("nivel"),
                        rs.getString("grado"),
                        rs.getString("seccion"),
                        rs.getString("codigoModular")
                    };
                    modelo.addRow(fila);
                    System.out.println("Se encontró información: Nombre " + rs.getString("nombreAlumno"));
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        } finally {
            ConectaBD.cerrar();
        }
    }

    public void especial(DefaultTableModel modelo) {
        al.vaciarDatos();
        al.setDniAlumno("");

        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            String nivel, grado, seccion;

            nivel = lista.cbxNivel.getSelectedItem().toString();
            grado = lista.cbxGrado.getSelectedItem().toString();
            seccion = lista.cbxSeccion.getSelectedItem().toString();

            String query;
            modelo.setRowCount(0);
            if (!nivel.equals("Seleccionar") && !grado.equals("Seleccionar") && !seccion.equals("Seleccionar")) {
                query = "SELECT codAlumno, dniAlumno, nombreAlumno, apePaterno, apeMaterno, telefono1, telefono2, nivel, grado, seccion, codigoModular FROM alumno WHERE nivel='" + nivel + "' and grado ='" + grado + "' and seccion='" + seccion + "'";
                ps = cn.prepareStatement(query);

            } else if (!nivel.equals("Seleccionar") && !grado.equals("Seleccionar") && seccion.equals("Seleccionar")) {
                query = "SELECT codAlumno, dniAlumno, nombreAlumno, apePaterno, apeMaterno, telefono1, telefono2, nivel, grado, seccion, codigoModular FROM alumno WHERE nivel='" + nivel + "' and grado ='" + grado + "'";
                ps = cn.prepareStatement(query);
            } else if (!nivel.equals("Seleccionar") && grado.equals("Seleccionar") && seccion.equals("Seleccionar")) {
                query = "SELECT codAlumno, dniAlumno, nombreAlumno, apePaterno, apeMaterno, telefono1, telefono2, nivel, grado, seccion, codigoModular FROM alumno WHERE nivel='" + nivel + "'";
                ps = cn.prepareStatement(query);
            } else {

                JOptionPane.showMessageDialog(null, "Indica al menos una característica");

            }

            try ( ResultSet rs = ps.executeQuery()) {
                modelo.setColumnIdentifiers(new Object[]{"Código Alumno", "DNI Alumno", "Nombre Alumno", "Apellido Paterno", "Apellido Materno",
                    "Teléfono 1", "Teléfono 2", "Nivel", "Grado", "Sección", "Código Modular"});

                while (rs.next()) {
                    Object[] fila = {
                        rs.getString("codAlumno"),
                        rs.getString("dniAlumno"),
                        rs.getString("nombreAlumno"),
                        rs.getString("apePaterno"),
                        rs.getString("apeMaterno"),
                        rs.getInt("telefono1"),
                        rs.getInt("telefono2"),
                        rs.getString("nivel"),
                        rs.getString("grado"),
                        rs.getString("seccion"),
                        rs.getString("codigoModular")
                    };
                    modelo.addRow(fila);
                    System.out.println("Se encontró información: Nombre " + rs.getString("nombreAlumno"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en la BD.");
                e.printStackTrace();
            } finally {
                ConectaBD.cerrar();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        } finally {
            ConectaBD.cerrar();
        }
    }

    public void tablaestu(DefaultTableModel modelo) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;
            al.setDniAlumno(lista.txtBuscarDNI.getText());
            String dniAlumno = al.getDniAlumno();
            String query = "SELECT dniAlumno, nombreAlumno, apePaterno, apeMaterno, telefono1, telefono2, nivel, grado, seccion, codigoModular FROM alumno WHERE dniAlumno = ?";
            ps = cn.prepareStatement(query);
            ps.setString(1, dniAlumno);
            modelo.setRowCount(0);
            try ( ResultSet rs = ps.executeQuery()) {
                modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombres", "Apellido Paterno", "Apellido Materno",
                    "Teléfono 1", "Teléfono 2", "Nivel", "Grado", "Sección", "Código Modular"});

                if (rs.next()) {

                    al.setNombreAlumno(rs.getString("nombreAlumno"));
                    al.setApePaterno(rs.getString("apePaterno"));
                    al.setApeMaterno(rs.getString("apeMaterno"));
                    al.setTelefono1(rs.getInt("telefono1"));
                    al.setTelefono2(rs.getInt("telefono2"));
                    al.setNivel(rs.getString("nivel"));
                    al.setGrado(rs.getString("grado"));
                    al.setSeccion(rs.getString("seccion"));
                    al.setCodigoModular(rs.getString("codigoModular"));

                    Object[] fila = {
                        al.getDniAlumno(),
                        al.getNombreAlumno(),
                        al.getApePaterno(),
                        al.getApeMaterno(),
                        al.getTelefono1(),
                        al.getTelefono2(),
                        al.getNivel(),
                        al.getGrado(),
                        al.getSeccion(),
                        al.getCodigoModular()
                    };
                    modelo.addRow(fila);
                    System.out.println("Se encontró info: Nombre " + al.getNombreAlumno());
                    ConectaBD.cerrar();
                } else {
                    ConectaBD.cerrar();
                    JOptionPane.showMessageDialog(null, "Alumno no encontrado.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        } finally {
            ConectaBD.cerrar();
        }
    }

    public boolean InsertarAlumno(String dniAlumno, String nombreAlumno,
            String apePaterno, String apeMaterno, String fechNacimiento, String domicilio, String dniPadre,
            String dniMadre, String dniApoderado, int telefono1, int telefono2, String email, String discapacidad,
            String grupoSangui, String alergias, String nivel, String grado, String seccion, String codigoModular,
            String sexo) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

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
        } catch (SQLIntegrityConstraintViolationException e) {
            // Verificar si la excepción se debe a una violación de clave única
            if (e.getMessage().contains("Duplicate entry")) {
                // Mensaje personalizado para la violación de clave única
                JOptionPane.showMessageDialog(null, "El alumno ya está registrado. Solo necesita ratificar.");
            } else {
                // Si no es una violación de clave única, muestra el mensaje de la excepción
                e.printStackTrace();
            }
            return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            return false;
        } finally {
            ConectaBD.cerrar();
        }
    }

    public boolean ActualizarAlumno(String dniAlumno, String domicilio, String dniApoderado, int telefono1, int telefono2,
            String email, String discapacidad, String grupoSangui, String alergias, String nivel, String grado,
            String seccion) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            String query = "UPDATE alumno\n"
                    + "SET domicilio = ?, dniApoderado = ?, telefono1 = ?, telefono2 = ?, email = ?, \n"
                    + "    discapacidad = ?, grupoSangui = ?, alergias = ?, nivel = ?, grado = ?, seccion = ?\n"
                    + "WHERE dniAlumno = ?;";
            ps = cn.prepareStatement(query);

            ps.setString(1, domicilio);
            ps.setString(2, dniApoderado);
            ps.setInt(3, telefono1);
            ps.setInt(4, telefono2);
            ps.setString(5, email);
            ps.setString(6, discapacidad);
            ps.setString(7, grupoSangui);
            ps.setString(8, alergias);
            ps.setString(9, nivel);
            ps.setString(10, grado);
            ps.setString(11, seccion);
            ps.setString(12, dniAlumno);

            int resultUpdate = ps.executeUpdate();

            if (resultUpdate != 0) {
                ConectaBD.cerrar();
                JOptionPane.showMessageDialog(null, "Datos del Alumno " + dniAlumno + " actualizado");
                return true;
            } else {
                ConectaBD.cerrar();
                JOptionPane.showMessageDialog(null, "Datos no actualizados");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            return false;
        } finally {
            ConectaBD.cerrar();
        }
    }

    public boolean BuscarAlumno(String codAlumno, String dniAlumno, String nombreAlumno, String apePaterno, String apeMaterno,
            String fechNacimiento, String domicilio, String dniPadre, String dniMadre, String dniApoderado, int telefono1,
            int telefono2, String email, String discapacidad, String grupoSangui, String alergias, String añoEduc,
            String nivel, String grado, String seccion, String codigoModular, String sexo) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            if ("Por DNI".equals((String) rat.cbxCatBus.getSelectedItem())) {
                al.vaciarDatos();
                dniAlumno = rat.txtBuscadorAlumno.getText();
                String query = "SELECT * FROM alumno WHERE dniAlumno = ?";
                ps = cn.prepareStatement(query);

                ps.setString(1, dniAlumno);

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Recuperar datos de la consulta
                        codAlumno = rs.getString("codAlumno");
                        nombreAlumno = rs.getString("nombreAlumno");
                        apePaterno = rs.getString("apePaterno");
                        apeMaterno = rs.getString("apeMaterno");
                        fechNacimiento = rs.getString("fechNacimiento");
                        domicilio = rs.getString("domicilio");
                        dniPadre = rs.getString("dniPadre");
                        dniMadre = rs.getString("dniMadre");
                        dniApoderado = rs.getString("dniApoderado");
                        telefono1 = rs.getInt("telefono1");
                        telefono2 = rs.getInt("telefono2");
                        email = rs.getString("email");
                        discapacidad = rs.getString("discapacidad");
                        grupoSangui = rs.getString("grupoSangui");
                        añoEduc = rs.getString("añoEduc");
                        alergias = rs.getString("alergias");
                        nivel = rs.getString("nivel");
                        grado = rs.getString("grado");
                        seccion = rs.getString("seccion");
                        codigoModular = rs.getString("codigoModular");
                        sexo = rs.getString("sexo");

                        al.setDniAlumno(dniAlumno);
                        al.setNombreAlumno(rs.getString("nombreAlumno"));
                        al.setApePaterno(rs.getString("apePaterno"));
                        al.setApeMaterno(rs.getString("apeMaterno"));
                        al.setFechNacimiento(rs.getString("fechNacimiento"));
                        al.setDomicilio(rs.getString("domicilio"));
                        al.setDniPadre(rs.getString("dniPadre"));
                        al.setDniMadre(rs.getString("dniMadre"));
                        al.setDniAlumno(rs.getString("dniAlumno"));
                        al.setDniApoderado(rs.getString("dniApoderado"));
                        al.setTelefono1(rs.getInt("telefono1"));
                        al.setTelefono2(rs.getInt("telefono2"));
                        al.setEmail(rs.getString("email"));
                        al.setDiscapacidad(rs.getString("discapacidad"));
                        al.setGrupoSangui(rs.getString("grupoSangui"));
                        al.setAlergias(rs.getString("alergias"));
                        al.setAñoEduc(rs.getString("añoEduc"));
                        al.setNivel(rs.getString("nivel"));
                        al.setGrado(rs.getString("grado"));
                        al.setSeccion(rs.getString("seccion"));
                        al.setCodAlumno(rs.getString("codAlumno"));
                        al.setCodigoModular(rs.getString("codigoModular"));
                        al.setSexo(rs.getString("sexo"));

                        // Verificar si el añoEduc es igual a "2024"
                        if ("2024".equals(al.getAñoEduc())) {
                            JOptionPane.showMessageDialog(null, "El alumno " + al.getDniAlumno() + " ya está registrado para el próximo año.");
                        }

                        ConectaBD.cerrar();
                        return true;
                    } else {
                        ConectaBD.cerrar();
                        JOptionPane.showMessageDialog(null, "No se encontraron datos para el DNI proporcionado.");
                        return false;
                    }
                }
            } else if ("Por Código Estu".equals((String) rat.cbxCatBus.getSelectedItem())) {
                al.vaciarDatos();
                codAlumno = rat.txtBuscadorAlumno.getText();
                String query = "SELECT * FROM alumno WHERE codAlumno = ?";
                ps = cn.prepareStatement(query);

                ps.setString(1, codAlumno);

                try ( ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        // Recuperar datos de la consulta
                        dniAlumno = rs.getString("dniAlumno");
                        nombreAlumno = rs.getString("nombreAlumno");
                        apePaterno = rs.getString("apePaterno");
                        apeMaterno = rs.getString("apeMaterno");
                        fechNacimiento = rs.getString("fechNacimiento");
                        domicilio = rs.getString("domicilio");
                        dniPadre = rs.getString("dniPadre");
                        dniMadre = rs.getString("dniMadre");
                        dniApoderado = rs.getString("dniApoderado");
                        telefono1 = rs.getInt("telefono1");
                        telefono2 = rs.getInt("telefono2");
                        email = rs.getString("email");
                        discapacidad = rs.getString("discapacidad");
                        grupoSangui = rs.getString("grupoSangui");
                        añoEduc = rs.getString("añoEduc");
                        alergias = rs.getString("alergias");
                        nivel = rs.getString("nivel");
                        grado = rs.getString("grado");
                        seccion = rs.getString("seccion");
                        codigoModular = rs.getString("codigoModular");
                        sexo = rs.getString("sexo");

                        al.setCodAlumno(codAlumno);
                        al.setNombreAlumno(rs.getString("nombreAlumno"));
                        al.setApePaterno(rs.getString("apePaterno"));
                        al.setApeMaterno(rs.getString("apeMaterno"));
                        al.setFechNacimiento(rs.getString("fechNacimiento"));
                        al.setDomicilio(rs.getString("domicilio"));
                        al.setDniPadre(rs.getString("dniPadre"));
                        al.setDniMadre(rs.getString("dniMadre"));
                        al.setDniAlumno(rs.getString("dniAlumno"));
                        al.setDniApoderado(rs.getString("dniApoderado"));
                        al.setTelefono1(rs.getInt("telefono1"));
                        al.setTelefono2(rs.getInt("telefono2"));
                        al.setEmail(rs.getString("email"));
                        al.setDiscapacidad(rs.getString("discapacidad"));
                        al.setGrupoSangui(rs.getString("grupoSangui"));
                        al.setAlergias(rs.getString("alergias"));
                        al.setAñoEduc(rs.getString("añoEduc"));
                        al.setNivel(rs.getString("nivel"));
                        al.setGrado(rs.getString("grado"));
                        al.setSeccion(rs.getString("seccion"));
                        al.setCodigoModular(rs.getString("codigoModular"));
                        al.setSexo(rs.getString("sexo"));

                        // Verificar si el añoEduc es igual a "2024"
                        if ("2024".equals(al.getAñoEduc())) {
                            JOptionPane.showMessageDialog(null, "El alumno " + al.getCodAlumno() + " ya está registrado para el próximo año.");
                        }

                        ConectaBD.cerrar();
                        return true;
                    } else {
                        ConectaBD.cerrar();
                        JOptionPane.showMessageDialog(null, "No se encontraron datos para el DNI proporcionado.");
                        return false;
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            return false;
        } finally {
            ConectaBD.cerrar();
        }
        return false;
    }

    public boolean BuscarJove(String dni, String nom, String apePa, String apeMa, String fechNac,
            String sexo, String dniPadre, String dniMadre, String nivel, String grado, String codigoModular,
            String añoEduc) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            dni = alumno.txtDNIBuscar.getText();

            // Verificar si el DNI ya está registrado en la tabla de alumnos
            String queryAlumno = "SELECT nombreAlumno FROM alumno WHERE dniAlumno = ?";
            ps = cn.prepareStatement(queryAlumno);
            ps.setString(1, dni);

            try ( ResultSet rsAlumno = ps.executeQuery()) {
                if (rsAlumno.next()) {
                    // El DNI ya está registrado en la tabla de alumnos
                    String nombreAlumno = rsAlumno.getString("nombreAlumno");
                    JOptionPane.showMessageDialog(null, nombreAlumno + " ya es un alumno del colegio.\n"
                            + "Puede pasar a verificar si esta inscrito para el proximo año\n"
                            + "dirijase a Ratificar");
                    ConectaBD.cerrar();
                    return false;
                }
            }

            // El DNI no está registrado en la tabla de alumnos, realizar la consulta original
            String queryNiños = "SELECT * FROM niños WHERE dni = ?";
            ps = cn.prepareStatement(queryNiños);
            ps.setString(1, dni);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Recuperar datos de la consulta
                    dni = rs.getString("dni");
                    nom = rs.getString("nom");
                    apePa = rs.getString("apePa");
                    apeMa = rs.getString("apeMa");
                    fechNac = rs.getString("fechNac");
                    sexo = rs.getString("sexo");
                    dniPadre = rs.getString("dniPadre");
                    dniMadre = rs.getString("dniMadre");
                    nivel = rs.getString("nivel");
                    grado = rs.getString("grado");
                    codigoModular = rs.getString("codigoModular");

                    nin.setDni(dni);
                    nin.setNom(rs.getString("nom"));
                    nin.setApePa(rs.getString("apePa"));
                    nin.setApeMa(rs.getString("apeMa"));
                    nin.setFechNac(rs.getString("fechNac"));
                    nin.setSexo(rs.getString("sexo"));
                    nin.setDniPadre(rs.getString("dniPadre"));
                    nin.setDniMadre(rs.getString("dniMadre"));
                    nin.setNivel(rs.getString("nivel"));
                    nin.setGrado(rs.getString("grado"));
                    nin.setCodigoModular(rs.getString("codigoModular"));
                    nin.setAñoEduc(rs.getString("añoEduc"));

                    ConectaBD.cerrar();
                    return true;
                } else {
                    ConectaBD.cerrar();
                    JOptionPane.showMessageDialog(null, "No se encontraron datos para el DNI proporcionado.");
                    return false;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            return false;
        } finally {
            ConectaBD.cerrar();
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
        } finally {
            ConectaBD.cerrar();
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

        } finally {
            ConectaBD.cerrar();
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

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();

        } finally {
            ConectaBD.cerrar();
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
        } finally {
            ConectaBD.cerrar();
        }
    }

    public boolean obtenerDatosRatific(String codAlumno, String dniAlumno, String nombreAlumno, String apePaterno,
            String apeMaterno, String fechNacimiento, String sexo, String domicilio, String dniPadre, String dniMadre,
            String dniApoderado, String telefono1, String telefono2, String email, String discapacidad, String gruposanguineo,
            String alergias, String añoEduc, String nivel, String grado, String seccion, String codigoModular,
            String idregistroalumno, String fechahoraregistroestudiante, String nombrepersonal) {
        dat.LimpiarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            // Your SQL query
            String query = "SELECT\n"
                    + "    a.codAlumno,\n"
                    + "    a.dniAlumno,\n"
                    + "    a.nombreAlumno,\n"
                    + "    a.apePaterno,\n"
                    + "    a.apeMaterno,\n"
                    + "    a.fechNacimiento,\n"
                    + "    a.sexo,\n"
                    + "    a.domicilio,\n"
                    + "    a.dniPadre,\n"
                    + "    a.dniMadre,\n"
                    + "    a.dniApoderado,\n"
                    + "    a.telefono1,\n"
                    + "    a.telefono2,\n"
                    + "    a.email,\n"
                    + "    a.discapacidad,\n"
                    + "    a.grupoSangui AS gruposanguineo,\n"
                    + "    a.alergias,\n"
                    + "    a.añoEduc,\n"
                    + "    a.nivel,\n"
                    + "    a.grado,\n"
                    + "    a.seccion,\n"
                    + "    a.codigoModular,\n"
                    + "    ra.idregistroAlumno,\n"
                    + "    ra.fechaHora AS fechahoraregistroestudiante,\n"
                    + "    CONCAT(p.NombrePers, ' ', p.ApellidoPers) AS nombrepersonal\n"
                    + "FROM\n"
                    + "    registroalumno ra\n"
                    + "JOIN\n"
                    + "    alumno a ON ra.dnialumno = a.dniAlumno\n"
                    + "JOIN\n"
                    + "    registropersonal rp ON ra.idpersonal = rp.idPersonal\n"
                    + "JOIN\n"
                    + "    personal p ON rp.idPersonal = p.idPersonal\n"
                    + "WHERE\n"
                    + "    a.añoEduc = '2024' AND a.codAlumno=?\n"
                    + "ORDER BY ra.fechaHora DESC\n"
                    + "LIMIT 1;";

            ps = cn.prepareStatement(query);
            ps.setString(1, codAlumno);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    codAlumno = rs.getString("codAlumno");
                    dniAlumno = rs.getString("dniAlumno");
                    nombreAlumno = rs.getString("nombreAlumno");
                    apePaterno = rs.getString("apePaterno");
                    apeMaterno = rs.getString("apeMaterno");
                    fechNacimiento = rs.getString("fechNacimiento");
                    sexo = rs.getString("sexo");
                    domicilio = rs.getString("domicilio");
                    dniPadre = rs.getString("dniPadre");
                    dniMadre = rs.getString("dniMadre");
                    dniApoderado = rs.getString("dniApoderado");
                    telefono1 = rs.getString("telefono1");
                    telefono2 = rs.getString("telefono2");
                    email = rs.getString("email");
                    discapacidad = rs.getString("discapacidad");
                    gruposanguineo = rs.getString("gruposanguineo");
                    alergias = rs.getString("alergias");
                    añoEduc = rs.getString("añoEduc");
                    nivel = rs.getString("nivel");
                    grado = rs.getString("grado");
                    seccion = rs.getString("seccion");
                    codigoModular = rs.getString("codigoModular");
                    idregistroalumno = rs.getString("idregistroAlumno");
                    fechahoraregistroestudiante = rs.getString("fechahoraregistroestudiante");
                    nombrepersonal = rs.getString("nombrepersonal");

                    dat.setCodAlumno(rs.getString("codAlumno"));
                    dat.setDniAlumno(rs.getString("dniAlumno"));
                    dat.setNombreAlumno(rs.getString("nombreAlumno"));
                    dat.setApePaterno(rs.getString("apePaterno"));
                    dat.setApeMaterno(rs.getString("apeMaterno"));
                    dat.setFechNacimiento(rs.getString("fechNacimiento"));
                    dat.setSexo(rs.getString("sexo"));
                    dat.setDomicilio(rs.getString("domicilio"));
                    dat.setDniPadre(rs.getString("dniPadre"));
                    dat.setDniMadre(rs.getString("dniMadre"));
                    dat.setDniApoderado(rs.getString("dniApoderado"));
                    dat.setTelefono1(rs.getString("telefono1"));
                    dat.setTelefono2(rs.getString("telefono2"));
                    dat.setEmail(rs.getString("email"));
                    dat.setDiscapacidad(rs.getString("discapacidad"));
                    dat.setGruposanguineo(rs.getString("gruposanguineo"));
                    dat.setAlergias(rs.getString("alergias"));
                    dat.setAñoEduc(rs.getString("añoEduc"));
                    dat.setNivel(rs.getString("nivel"));
                    dat.setGrado(rs.getString("grado"));
                    dat.setSeccion(rs.getString("seccion"));
                    dat.setCodigoModular(rs.getString("codigoModular"));
                    dat.setIdregistroalumno(rs.getString("idregistroAlumno"));
                    dat.setFechahoraregistroestudiante(rs.getString("fechahoraregistroestudiante"));
                    dat.setNombrepersonal(rs.getString("nombrepersonal"));

                    ConectaBD.cerrar();
                }

                String rutaArchivo = "/reporte/Resumen.xlsx";
                try {
                    InputStream inputStream = GestorBD.class.getResourceAsStream(rutaArchivo);

                    if (inputStream != null) {
                        Workbook workbook = WorkbookFactory.create(inputStream);

                        // Asumimos que la hoja se llama "Hoja"
                        Sheet sheet = workbook.getSheet("Hoja");

                        // Asegurarse de que hay al menos una fila en la hoja
                        Row rowcod = sheet.getRow(8); // Fila D9
                        if (rowcod != null) {
                            // Obtener la celda correspondiente a codAlumno (columna D)
                            Cell cellCodAlumno = rowcod.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            cellCodAlumno.setCellValue(codAlumno);

                            Row rowdnial = sheet.getRow(15); // Fila D16
                            if (rowdnial != null) {
                                // Obtener la celda correspondiente a dniAlumno (columna D)
                                Cell celldnial = rowdnial.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                celldnial.setCellValue(dniAlumno);
                            }

                            Row rowNombreAlumno = sheet.getRow(16); // Fila D17
                            if (rowNombreAlumno != null) {
                                Cell cellNombreAlumno = rowNombreAlumno.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellNombreAlumno.setCellValue(nombreAlumno);
                            }

                            Row rowApePaterno = sheet.getRow(17); // Fila D18
                            if (rowApePaterno != null) {
                                Cell cellApePaterno = rowApePaterno.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellApePaterno.setCellValue(apePaterno);
                            }

                            Row rowApeMaterno = sheet.getRow(18); // Fila D19
                            if (rowApeMaterno != null) {
                                Cell cellApeMaterno = rowApeMaterno.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellApeMaterno.setCellValue(apeMaterno);
                            }

                            Row rowFechNacimiento = sheet.getRow(19); // Fila D20
                            if (rowFechNacimiento != null) {
                                Cell cellFechNacimiento = rowFechNacimiento.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellFechNacimiento.setCellValue(fechNacimiento);
                            }

                            Row rowDomicilio = sheet.getRow(20); // Fila D21
                            if (rowDomicilio != null) {
                                Cell cellDomicilio = rowDomicilio.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDomicilio.setCellValue(domicilio);
                            }

                            Row rowDniPadre = sheet.getRow(16); // Fila I17
                            if (rowDniPadre != null) {
                                Cell cellDniPadre = rowDniPadre.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDniPadre.setCellValue(dniPadre);
                            }

                            Row rowDniMadre = sheet.getRow(17); // Fila I18
                            if (rowDniMadre != null) {
                                Cell cellDniMadre = rowDniMadre.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDniMadre.setCellValue(dniMadre);
                            }

                            Row rowDniApoderado = sheet.getRow(18); // Fila I19
                            if (rowDniApoderado != null) {
                                Cell cellDniApoderado = rowDniApoderado.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDniApoderado.setCellValue(dniApoderado);
                            }

                            Row rowTelefono1 = sheet.getRow(27); // Fila I28
                            if (rowTelefono1 != null) {
                                Cell cellTelefono1 = rowTelefono1.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellTelefono1.setCellValue(telefono1);
                            }

                            Row rowTelefono2 = sheet.getRow(28); // Fila I29
                            if (rowTelefono2 != null) {
                                Cell cellTelefono2 = rowTelefono2.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellTelefono2.setCellValue(telefono2);
                            }

                            Row rowEmail = sheet.getRow(29); // Fila I30
                            if (rowEmail != null) {
                                Cell cellEmail = rowEmail.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellEmail.setCellValue(email);
                            }

                            Row rowDiscapacidad = sheet.getRow(37); // Fila D38
                            if (rowDiscapacidad != null) {
                                Cell cellDiscapacidad = rowDiscapacidad.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDiscapacidad.setCellValue(discapacidad);
                            }

                            Row rowGrupoSanguineo = sheet.getRow(38); // Fila D39
                            if (rowGrupoSanguineo != null) {
                                Cell cellGrupoSanguineo = rowGrupoSanguineo.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellGrupoSanguineo.setCellValue(gruposanguineo);
                            }

                            Row rowAlergias = sheet.getRow(39); // Fila D40
                            if (rowAlergias != null) {
                                Cell cellAlergias = rowAlergias.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellAlergias.setCellValue(alergias);
                            }

                            Row rowNivel = sheet.getRow(27); // Fila D28
                            if (rowNivel != null) {
                                Cell cellNivel = rowNivel.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellNivel.setCellValue(nivel);
                            }

                            Row rowGrado = sheet.getRow(28); // Fila D29
                            if (rowGrado != null) {
                                Cell cellGrado = rowGrado.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellGrado.setCellValue(grado);
                            }

                            Row rowSeccion = sheet.getRow(29); // Fila D30
                            if (rowSeccion != null) {
                                Cell cellSeccion = rowSeccion.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellSeccion.setCellValue(seccion);
                            }

                            Row rowCodigoModular = sheet.getRow(8); // Fila I9
                            if (rowCodigoModular != null) {
                                Cell cellCodigoModular = rowCodigoModular.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellCodigoModular.setCellValue(codigoModular);
                            }

                            Row rowIdRegistroAlumno = sheet.getRow(6); // Fila J7
                            if (rowIdRegistroAlumno != null) {
                                Cell cellIdRegistroAlumno = rowIdRegistroAlumno.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellIdRegistroAlumno.setCellValue(idregistroalumno);
                            }

                            Row rowFechaHoraRegistroEstudiante = sheet.getRow(9); // Fila I10
                            if (rowFechaHoraRegistroEstudiante != null) {
                                Cell cellFechaHoraRegistroEstudiante = rowFechaHoraRegistroEstudiante.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellFechaHoraRegistroEstudiante.setCellValue(fechahoraregistroestudiante);
                            }

                            Row rowNombrePersonal = sheet.getRow(9); // Fila D10
                            if (rowNombrePersonal != null) {
                                Cell cellNombrePersonal = rowNombrePersonal.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellNombrePersonal.setCellValue(nombrepersonal);
                            }

                            // Guardar el archivo
                            try ( FileOutputStream fileOut = new FileOutputStream("P:/Reporte_Ratificación_" + codAlumno + "_" + nombreAlumno + "_" + apePaterno + ".xlsx")) {
                                workbook.write(fileOut);
                            }

                            JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");

                        } else {
                            System.out.println("La hoja está vacía. No se puede obtener la fila 8.");
                        }

                        // Cerrar el flujo de entrada
                        inputStream.close();
                    } else {
                        System.out.println("No se pudo encontrar el archivo en la ruta especificada.");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        } finally {
            ConectaBD.cerrar();
        }
        return false;

    }

    public boolean obtenerDatosRegistr(String codAlumno, String dniAlumno, String nombreAlumno, String apePaterno,
            String apeMaterno, String fechNacimiento, String sexo, String domicilio, String dniPadre, String dniMadre,
            String dniApoderado, String telefono1, String telefono2, String email, String discapacidad, String gruposanguineo,
            String alergias, String añoEduc, String nivel, String grado, String seccion, String codigoModular,
            String idregistroalumno, String fechahoraregistroestudiante, String nombrepersonal) {

        dat.LimpiarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            // Your SQL query
            String query = "SELECT\n"
                    + "    a.codAlumno,\n"
                    + "    a.dniAlumno,\n"
                    + "    a.nombreAlumno,\n"
                    + "    a.apePaterno,\n"
                    + "    a.apeMaterno,\n"
                    + "    a.fechNacimiento,\n"
                    + "    a.sexo,\n"
                    + "    a.domicilio,\n"
                    + "    a.dniPadre,\n"
                    + "    a.dniMadre,\n"
                    + "    a.dniApoderado,\n"
                    + "    a.telefono1,\n"
                    + "    a.telefono2,\n"
                    + "    a.email,\n"
                    + "    a.discapacidad,\n"
                    + "    a.grupoSangui AS gruposanguineo,\n"
                    + "    a.alergias,\n"
                    + "    a.añoEduc,\n"
                    + "    a.nivel,\n"
                    + "    a.grado,\n"
                    + "    a.seccion,\n"
                    + "    a.codigoModular,\n"
                    + "    ra.idregistroAlumno,\n"
                    + "    ra.fechaHora AS fechahoraregistroestudiante,\n"
                    + "    CONCAT(p.NombrePers, ' ', p.ApellidoPers) AS nombrepersonal\n"
                    + "FROM\n"
                    + "    registroalumno ra\n"
                    + "JOIN\n"
                    + "    alumno a ON ra.dnialumno = a.dniAlumno\n"
                    + "JOIN\n"
                    + "    registropersonal rp ON ra.idpersonal = rp.idPersonal\n"
                    + "JOIN\n"
                    + "    personal p ON rp.idPersonal = p.idPersonal\n"
                    + "WHERE\n"
                    + "    a.añoEduc = '2024' AND a.dniAlumno = ?\n"
                    + "ORDER BY\n"
                    + "    ra.fechaHora DESC \n"
                    + "LIMIT 1;";

            ps = cn.prepareStatement(query);
            ps.setString(1, dniAlumno);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    codAlumno = rs.getString("codAlumno");
                    dniAlumno = rs.getString("dniAlumno");
                    nombreAlumno = rs.getString("nombreAlumno");
                    apePaterno = rs.getString("apePaterno");
                    apeMaterno = rs.getString("apeMaterno");
                    fechNacimiento = rs.getString("fechNacimiento");
                    sexo = rs.getString("sexo");
                    domicilio = rs.getString("domicilio");
                    dniPadre = rs.getString("dniPadre");
                    dniMadre = rs.getString("dniMadre");
                    dniApoderado = rs.getString("dniApoderado");
                    telefono1 = rs.getString("telefono1");
                    telefono2 = rs.getString("telefono2");
                    email = rs.getString("email");
                    discapacidad = rs.getString("discapacidad");
                    gruposanguineo = rs.getString("gruposanguineo");
                    alergias = rs.getString("alergias");
                    añoEduc = rs.getString("añoEduc");
                    nivel = rs.getString("nivel");
                    grado = rs.getString("grado");
                    seccion = rs.getString("seccion");
                    codigoModular = rs.getString("codigoModular");
                    idregistroalumno = rs.getString("idregistroAlumno");
                    fechahoraregistroestudiante = rs.getString("fechahoraregistroestudiante");
                    nombrepersonal = rs.getString("nombrepersonal");

                    dat.setCodAlumno(rs.getString("codAlumno"));
                    dat.setDniAlumno(rs.getString("dniAlumno"));
                    dat.setNombreAlumno(rs.getString("nombreAlumno"));
                    dat.setApePaterno(rs.getString("apePaterno"));
                    dat.setApeMaterno(rs.getString("apeMaterno"));
                    dat.setFechNacimiento(rs.getString("fechNacimiento"));
                    dat.setSexo(rs.getString("sexo"));
                    dat.setDomicilio(rs.getString("domicilio"));
                    dat.setDniPadre(rs.getString("dniPadre"));
                    dat.setDniMadre(rs.getString("dniMadre"));
                    dat.setDniApoderado(rs.getString("dniApoderado"));
                    dat.setTelefono1(rs.getString("telefono1"));
                    dat.setTelefono2(rs.getString("telefono2"));
                    dat.setEmail(rs.getString("email"));
                    dat.setDiscapacidad(rs.getString("discapacidad"));
                    dat.setGruposanguineo(rs.getString("gruposanguineo"));
                    dat.setAlergias(rs.getString("alergias"));
                    dat.setAñoEduc(rs.getString("añoEduc"));
                    dat.setNivel(rs.getString("nivel"));
                    dat.setGrado(rs.getString("grado"));
                    dat.setSeccion(rs.getString("seccion"));
                    dat.setCodigoModular(rs.getString("codigoModular"));
                    dat.setIdregistroalumno(rs.getString("idregistroAlumno"));
                    dat.setFechahoraregistroestudiante(rs.getString("fechahoraregistroestudiante"));
                    dat.setNombrepersonal(rs.getString("nombrepersonal"));

                    ConectaBD.cerrar();
                }

                String rutaArchivo = "/reporte/Resumen.xlsx";
                try {
                    InputStream inputStream = GestorBD.class.getResourceAsStream(rutaArchivo);

                    if (inputStream != null) {
                        Workbook workbook = WorkbookFactory.create(inputStream);

                        // Asumimos que la hoja se llama "Hoja"
                        Sheet sheet = workbook.getSheet("Hoja");

                        // Asegurarse de que hay al menos una fila en la hoja
                        Row rowcod = sheet.getRow(8); // Fila D9
                        if (rowcod != null) {
                            // Obtener la celda correspondiente a codAlumno (columna D)
                            Cell cellCodAlumno = rowcod.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                            cellCodAlumno.setCellValue(codAlumno);

                            Row rowdnial = sheet.getRow(15); // Fila D16
                            if (rowdnial != null) {
                                // Obtener la celda correspondiente a dniAlumno (columna D)
                                Cell celldnial = rowdnial.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                celldnial.setCellValue(dniAlumno);
                            }

                            Row rowNombreAlumno = sheet.getRow(16); // Fila D17
                            if (rowNombreAlumno != null) {
                                Cell cellNombreAlumno = rowNombreAlumno.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellNombreAlumno.setCellValue(nombreAlumno);
                            }

                            Row rowApePaterno = sheet.getRow(17); // Fila D18
                            if (rowApePaterno != null) {
                                Cell cellApePaterno = rowApePaterno.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellApePaterno.setCellValue(apePaterno);
                            }

                            Row rowApeMaterno = sheet.getRow(18); // Fila D19
                            if (rowApeMaterno != null) {
                                Cell cellApeMaterno = rowApeMaterno.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellApeMaterno.setCellValue(apeMaterno);
                            }

                            Row rowFechNacimiento = sheet.getRow(19); // Fila D20
                            if (rowFechNacimiento != null) {
                                Cell cellFechNacimiento = rowFechNacimiento.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellFechNacimiento.setCellValue(fechNacimiento);
                            }

                            Row rowDomicilio = sheet.getRow(20); // Fila D21
                            if (rowDomicilio != null) {
                                Cell cellDomicilio = rowDomicilio.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDomicilio.setCellValue(domicilio);
                            }

                            Row rowDniPadre = sheet.getRow(16); // Fila I17
                            if (rowDniPadre != null) {
                                Cell cellDniPadre = rowDniPadre.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDniPadre.setCellValue(dniPadre);
                            }

                            Row rowDniMadre = sheet.getRow(17); // Fila I18
                            if (rowDniMadre != null) {
                                Cell cellDniMadre = rowDniMadre.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDniMadre.setCellValue(dniMadre);
                            }

                            Row rowDniApoderado = sheet.getRow(18); // Fila I19
                            if (rowDniApoderado != null) {
                                Cell cellDniApoderado = rowDniApoderado.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDniApoderado.setCellValue(dniApoderado);
                            }

                            Row rowTelefono1 = sheet.getRow(27); // Fila I28
                            if (rowTelefono1 != null) {
                                Cell cellTelefono1 = rowTelefono1.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellTelefono1.setCellValue(telefono1);
                            }

                            Row rowTelefono2 = sheet.getRow(28); // Fila I29
                            if (rowTelefono2 != null) {
                                Cell cellTelefono2 = rowTelefono2.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellTelefono2.setCellValue(telefono2);
                            }

                            Row rowEmail = sheet.getRow(29); // Fila I30
                            if (rowEmail != null) {
                                Cell cellEmail = rowEmail.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellEmail.setCellValue(email);
                            }

                            Row rowDiscapacidad = sheet.getRow(37); // Fila D38
                            if (rowDiscapacidad != null) {
                                Cell cellDiscapacidad = rowDiscapacidad.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellDiscapacidad.setCellValue(discapacidad);
                            }

                            Row rowGrupoSanguineo = sheet.getRow(38); // Fila D39
                            if (rowGrupoSanguineo != null) {
                                Cell cellGrupoSanguineo = rowGrupoSanguineo.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellGrupoSanguineo.setCellValue(gruposanguineo);
                            }

                            Row rowAlergias = sheet.getRow(39); // Fila D40
                            if (rowAlergias != null) {
                                Cell cellAlergias = rowAlergias.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellAlergias.setCellValue(alergias);
                            }

                            Row rowNivel = sheet.getRow(27); // Fila D28
                            if (rowNivel != null) {
                                Cell cellNivel = rowNivel.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellNivel.setCellValue(nivel);
                            }

                            Row rowGrado = sheet.getRow(28); // Fila D29
                            if (rowGrado != null) {
                                Cell cellGrado = rowGrado.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellGrado.setCellValue(grado);
                            }

                            Row rowSeccion = sheet.getRow(29); // Fila D30
                            if (rowSeccion != null) {
                                Cell cellSeccion = rowSeccion.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellSeccion.setCellValue(seccion);
                            }

                            Row rowCodigoModular = sheet.getRow(8); // Fila I9
                            if (rowCodigoModular != null) {
                                Cell cellCodigoModular = rowCodigoModular.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellCodigoModular.setCellValue(codigoModular);
                            }

                            Row rowIdRegistroAlumno = sheet.getRow(6); // Fila J7
                            if (rowIdRegistroAlumno != null) {
                                Cell cellIdRegistroAlumno = rowIdRegistroAlumno.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellIdRegistroAlumno.setCellValue(idregistroalumno);
                            }

                            Row rowFechaHoraRegistroEstudiante = sheet.getRow(9); // Fila I10
                            if (rowFechaHoraRegistroEstudiante != null) {
                                Cell cellFechaHoraRegistroEstudiante = rowFechaHoraRegistroEstudiante.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellFechaHoraRegistroEstudiante.setCellValue(fechahoraregistroestudiante);
                            }

                            Row rowNombrePersonal = sheet.getRow(9); // Fila D10
                            if (rowNombrePersonal != null) {
                                Cell cellNombrePersonal = rowNombrePersonal.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                                cellNombrePersonal.setCellValue(nombrepersonal);
                            }

                            // Guardar el archivo
                            try ( FileOutputStream fileOut = new FileOutputStream("P:/Reporte_Ratificación_" + codAlumno + "_" + nombreAlumno + "_" + apePaterno + ".xlsx")) {
                                workbook.write(fileOut);
                            }

                            JOptionPane.showMessageDialog(null, "Reporte generado con éxito.");

                        } else {
                            System.out.println("La hoja está vacía. No se puede obtener la fila 8.");
                        }

                        // Cerrar el flujo de entrada
                        inputStream.close();
                    } else {
                        System.out.println("No se pudo encontrar el archivo en la ruta especificada.");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GestorBD.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
        } finally {
            ConectaBD.cerrar();
        }
        return false;
    }
}
