package models;

import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Alumno;
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

    public GestorBD(RegistroAlumno alumno, Alumno al, ListaAlumnos lista, Jovenes nin, RatificarMatricula rat) {
        this.alumno = alumno;
        this.al = al;
        this.lista = lista;
        this.nin = nin;
        this.rat = rat;

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

    public boolean ActualizarAlumno(String dniAlumno,
            String domicilio,
            String dniApoderado,
            int telefono1,
            int telefono2,
            String email,
            String discapacidad,
            String grupoSangui,
            String alergias,
            String nivel,
            String grado,
            String seccion) {
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

    public boolean BuscarAlumno(String codAlumno,
            String dniAlumno,
            String nombreAlumno,
            String apePaterno,
            String apeMaterno,
            String fechNacimiento,
            String domicilio,
            String dniPadre,
            String dniMadre,
            String dniApoderado,
            int telefono1,
            int telefono2,
            String email,
            String discapacidad,
            String grupoSangui,
            String alergias,
            String añoEduc,
            String nivel,
            String grado,
            String seccion,
            String codigoModular,
            String sexo) {
        try {
            Connection cn = ConectaBD.abrir();
            PreparedStatement ps = null;

            if ("Por DNI".equals((String) rat.cbxCatBus.getSelectedItem())) {

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
                            JOptionPane.showMessageDialog(null, "El alumno " + al.getDniAlumno()+ " ya está registrado para el próximo año.");
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
                            JOptionPane.showMessageDialog(null, "El alumno " + al.getCodAlumno()+ " ya está registrado para el próximo año.");
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
                    JOptionPane.showMessageDialog(null, nombreAlumno + " ya es un alumno del colegio.");
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

}
