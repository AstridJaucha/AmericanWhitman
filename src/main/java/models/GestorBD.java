package models;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Alumno;
import views.RegistroAlumno;
import views.ListaAlumnos;

public class GestorBD {

    Connection conn = null;
    Statement stm = null;
    ResultSet rsProd;
    int resultUpdate = 0;
    private RegistroAlumno alumno;
    private Alumno al;
    private ListaAlumnos lista;

    public GestorBD(RegistroAlumno alumno, Alumno al, ListaAlumnos lista) {
        this.alumno = alumno;
        this.al = al;
        this.lista = lista;
        conn = ConectaBD.abrir();
    }

    public void tablaTodos(DefaultTableModel modelo) {
        al.vaciarDatos();
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            String query = "SELECT * FROM alumno";
            ps = cn.prepareStatement(query);
            modelo.setRowCount(0);
            try ( ResultSet rs = ps.executeQuery()) {
                modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombres", "Apellido Paterno", "Apellido Materno",
                    "F. Nacimiento", "Sexo", "Nivel", "Grado", "Sección", "Codigo modular", "Domicilio", "DNI Padre",
                    "DNI Madre", "DNI Apoderado", "Teléfono 1", "Teléfono 2", "E-mail", "Discapacidad", "Tipo sangre",
                    "Alergias"});

                while (rs.next()) {

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
                    al.setNivel(rs.getString("nivel"));
                    al.setGrado(rs.getString("grado"));
                    al.setSeccion(rs.getString("seccion"));
                    al.setCodigoModular(rs.getString("codigoModular"));
                    al.setSexo(rs.getString("sexo"));

                    Object[] fila = {al.getDniAlumno(),
                        al.getNombreAlumno(),
                        al.getApePaterno(),
                        al.getApeMaterno(),
                        al.getFechNacimiento(),
                        al.getSexo(),
                        al.getNivel(),
                        al.getGrado(),
                        al.getSeccion(),
                        al.getCodigoModular(),
                        al.getDomicilio(),
                        al.getDniPadre(),
                        al.getDniMadre(),
                        al.getDniApoderado(),
                        al.getTelefono1(),
                        al.getTelefono2(),
                        al.getEmail(),
                        al.getDiscapacidad(),
                        al.getGrupoSangui(),
                        al.getAlergias()

                    };
                    modelo.addRow(fila);
                    System.out.println("Se encontro info: Nombre " + al.getNombreAlumno());

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
                query = "SELECT * FROM alumno WHERE nivel='" + nivel + "' and grado ='" + grado + "' and seccion='" + seccion + "'";
                ps = cn.prepareStatement(query);

            } else if (!nivel.equals("Seleccionar") && !grado.equals("Seleccionar") && seccion.equals("Seleccionar")) {
                query = "SELECT * FROM alumno WHERE nivel='" + nivel + "' and grado ='" + grado + "'";
                ps = cn.prepareStatement(query);
            } else if (!nivel.equals("Seleccionar") && grado.equals("Seleccionar") && seccion.equals("Seleccionar")) {
                query = "SELECT * FROM alumno WHERE nivel='" + nivel + "'";
                ps = cn.prepareStatement(query);
            } else {

                JOptionPane.showMessageDialog(null, "Indica al menos una característica");

            }

            try ( ResultSet rs = ps.executeQuery()) {
                modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombres", "Apellido Paterno", "Apellido Materno",
                    "F. Nacimiento", "Sexo", "Nivel", "Grado", "Sección", "Codigo modular", "Domicilio", "DNI Padre",
                    "DNI Madre", "DNI Apoderado", "Teléfono 1", "Teléfono 2", "E-mail", "Discapacidad", "Tipo sangre",
                    "Alergias"});

                while (rs.next()) {

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
                    al.setNivel(rs.getString("nivel"));
                    al.setGrado(rs.getString("grado"));
                    al.setSeccion(rs.getString("seccion"));
                    al.setCodigoModular(rs.getString("codigoModular"));
                    al.setSexo(rs.getString("sexo"));

                    Object[] fila = {al.getDniAlumno(),
                        al.getNombreAlumno(),
                        al.getApePaterno(),
                        al.getApeMaterno(),
                        al.getFechNacimiento(),
                        al.getSexo(),
                        al.getNivel(),
                        al.getGrado(),
                        al.getSeccion(),
                        al.getCodigoModular(),
                        al.getDomicilio(),
                        al.getDniPadre(),
                        al.getDniMadre(),
                        al.getDniApoderado(),
                        al.getTelefono1(),
                        al.getTelefono2(),
                        al.getEmail(),
                        al.getDiscapacidad(),
                        al.getGrupoSangui(),
                        al.getAlergias()

                    };
                    modelo.addRow(fila);
                    System.out.println("Se encontro info: Nombre " + al.getNombreAlumno());

                }
                ConectaBD.cerrar();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error en la BD.");
                e.printStackTrace();
                ConectaBD.cerrar();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            e.printStackTrace();
            ConectaBD.cerrar();
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
            String query = "SELECT * FROM alumno WHERE dniAlumno = ?";
            ps = cn.prepareStatement(query);
            ps.setString(1, dniAlumno);
modelo.setRowCount(0);
            try ( ResultSet rs = ps.executeQuery()) {
                modelo.setColumnIdentifiers(new Object[]{"DNI", "Nombres", "Apellido Paterno", "Apellido Materno",
                    "F. Nacimiento", "Sexo", "Nivel", "Grado", "Sección", "Codigo modular", "Domicilio", "DNI Padre",
                    "DNI Madre", "DNI Apoderado", "Teléfono 1", "Teléfono 2", "E-mail", "Discapacidad", "Tipo sangre",
                    "Alergias"});

                if (rs.next()) {

                    al.setNombreAlumno(rs.getString("nombreAlumno"));
                    al.setApePaterno(rs.getString("apePaterno"));
                    al.setApeMaterno(rs.getString("apeMaterno"));
                    al.setFechNacimiento(rs.getString("fechNacimiento"));
                    al.setDomicilio(rs.getString("domicilio"));
                    al.setDniPadre(rs.getString("dniPadre"));
                    al.setDniMadre(rs.getString("dniMadre"));
                    al.setDniAlumno(dniAlumno);
                    al.setDniApoderado(rs.getString("dniApoderado"));
                    al.setTelefono1(rs.getInt("telefono1"));
                    al.setTelefono2(rs.getInt("telefono2"));
                    al.setEmail(rs.getString("email"));
                    al.setDiscapacidad(rs.getString("discapacidad"));
                    al.setGrupoSangui(rs.getString("grupoSangui"));
                    al.setAlergias(rs.getString("alergias"));
                    al.setNivel(rs.getString("nivel"));
                    al.setGrado(rs.getString("grado"));
                    al.setSeccion(rs.getString("seccion"));
                    al.setCodigoModular(rs.getString("codigoModular"));
                    al.setSexo(rs.getString("sexo"));
                    

                    Object[] fila = {al.getDniAlumno(),
                        al.getNombreAlumno(),
                        al.getApePaterno(),
                        al.getApeMaterno(),
                        al.getFechNacimiento(),
                        al.getSexo(),
                        al.getNivel(),
                        al.getGrado(),
                        al.getSeccion(),
                        al.getCodigoModular(),
                        al.getDomicilio(),
                        al.getDniPadre(),
                        al.getDniMadre(),
                        al.getDniApoderado(),
                        al.getTelefono1(),
                        al.getTelefono2(),
                        al.getEmail(),
                        al.getDiscapacidad(),
                        al.getGrupoSangui(),
                        al.getAlergias()

                    };
                    modelo.addRow(fila);
                    System.out.println("Se encontro info: Nombre " + al.getNombreAlumno());
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
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la BD.");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();

            // Mostrar la traza de la pila en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, stackTrace, "Error", JOptionPane.ERROR_MESSAGE);

            return false;
        } finally {
            ConectaBD.cerrar();
        }
    }

    public boolean ActualizarAlumno(String dniAlumno, String nombreAlumno, String apePaterno,
            String apeMaterno, String fechNacimiento, String domicilio, String dniPadre, String dniMadre,
            String dniApoderado, int telefono1, int telefono2, String email, String discapacidad,
            String grupoSangui, String alergias, String nivel, String grado, String seccion, String codigoModular, String sexo) {
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            String query = "UPDATE alumno\n"
                    + "SET nombreAlumno = ?, apePaterno = ?, apeMaterno = ?, fechNacimiento = ?, sexo = ?, domicilio = ?, \n"
                    + "    dniPadre = ?, dniMadre = ?, dniApoderado = ?, telefono1 = ?, telefono2 = ?, email = ?, \n"
                    + "    discapacidad = ?, grupoSangui = ?, alergias = ?, nivel = ?, grado = ?, seccion = ?, codigoModular = ?\n"
                    + "WHERE dniAlumno = ?;";
            ps = cn.prepareStatement(query);

            ps.setString(1, nombreAlumno);
            ps.setString(2, apePaterno);
            ps.setString(3, apeMaterno);
            ps.setString(4, fechNacimiento); // Considera usar setDate si fechNacimiento es de tipo DATE
            ps.setString(5, sexo);
            ps.setString(6, domicilio);
            ps.setString(7, dniPadre);
            ps.setString(8, dniMadre);
            ps.setString(9, dniApoderado);
            ps.setInt(10, telefono1);
            ps.setInt(11, telefono2);
            ps.setString(12, email);
            ps.setString(13, discapacidad);
            ps.setString(14, grupoSangui);
            ps.setString(15, alergias);
            ps.setString(16, nivel);
            ps.setString(17, grado);
            ps.setString(18, seccion);
            ps.setString(19, codigoModular);
            ps.setString(20, dniAlumno); // El dniAlumno va al final para el WHERE

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

    public boolean BuscarAlumno(String dniAlumno, String nombreAlumno, String apePaterno, String apeMaterno,
            String fechNacimiento, String domicilio, String dniPadre, String dniMadre, String dniApoderado, int telefono1,
            int telefono2, String email, String discapacidad, String grupoSangui, String alergias, String nivel,
            String grado, String seccion, String codigoModular, String sexo) {
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;
            dniAlumno = alumno.txtBuscadorAlumno.getText();
            String query = "SELECT * FROM alumno WHERE dniAlumno = ?";
            ps = cn.prepareStatement(query);

            ps.setString(1, dniAlumno);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Recuperar datos de la consulta
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
                    alergias = rs.getString("alergias");
                    nivel = rs.getString("nivel");
                    grado = rs.getString("grado");
                    seccion = rs.getString("seccion");
                    codigoModular = rs.getString("codigoModular");
                    sexo = rs.getString("sexo");

                    al.setNombreAlumno(rs.getString("nombreAlumno"));
                    al.setApePaterno(rs.getString("apePaterno"));
                    al.setApeMaterno(rs.getString("apeMaterno"));
                    al.setFechNacimiento(rs.getString("fechNacimiento"));
                    al.setDomicilio(rs.getString("domicilio"));
                    al.setDniPadre(rs.getString("dniPadre"));
                    al.setDniMadre(rs.getString("dniMadre"));
                    al.setDniAlumno(dniAlumno);
                    al.setDniApoderado(rs.getString("dniApoderado"));
                    al.setTelefono1(rs.getInt("telefono1"));
                    al.setTelefono2(rs.getInt("telefono2"));
                    al.setEmail(rs.getString("email"));
                    al.setDiscapacidad(rs.getString("discapacidad"));
                    al.setGrupoSangui(rs.getString("grupoSangui"));
                    al.setAlergias(rs.getString("alergias"));
                    al.setNivel(rs.getString("nivel"));
                    al.setGrado(rs.getString("grado"));
                    al.setSeccion(rs.getString("seccion"));
                    al.setCodigoModular(rs.getString("codigoModular"));
                    al.setSexo(rs.getString("sexo"));
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
}
