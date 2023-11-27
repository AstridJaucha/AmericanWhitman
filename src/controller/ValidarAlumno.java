package controller;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import views.RegistroAlumno;
import models.Alumno;

public class ValidarAlumno {

    private RegistroAlumno alumno;
    private Alumno al;

    public ValidarAlumno(RegistroAlumno alumno, Alumno al) {
        this.alumno = alumno;
        this.al = al;
    }

    public boolean ValidarNombre() {
        if (alumno.txtNombreAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setNombreAlumno(alumno.txtNombreAlumno.getText());
            return true;
        }

    }

    public boolean ValidarPaterno() {

        if (alumno.txtApePaternoAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido paterno del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setApePaterno(alumno.txtApePaternoAlumno.getText());
            return true;
        }

    }

    public boolean ValidarMatern() {
        if (alumno.txtApeMaternoAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido materno del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setApeMaterno(alumno.txtApeMaternoAlumno.getText());
            return true;
        }

    }

    public boolean ValidarDNI() {
        if (alumno.txtDNIalumno.getText()
                .isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNIalumno.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniAlumno(alumno.txtDNIalumno.getText());
                return true;
            }
        }

    }

    public boolean ValidarFecha() {
        if (alumno.txtFnacimienAlumno.getText()
                .isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de nacimiento del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String fechaNacimiento = alumno.txtFnacimienAlumno.getText();
            if (fechaNacimiento.matches("\\d{2}-\\d{2}-\\d{2}")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
                dateFormat.setLenient(false);  // No permite fechas inválidas

                try {
                    Date parsedDate = dateFormat.parse(fechaNacimiento);
                    al.setFechNacimiento(fechaNacimiento);
                    return true;
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida en el formato dd-MM-yy (ejemplo: 04-06-30)", "Fecha de nacimiento inválida", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida en el formato dd-MM-yy (ejemplo: 04-06-30)", "Formato de fecha incorrecto", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

    }

    public boolean ValidarDomi() {

        if (alumno.txtDomiciAlumno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el domicilio del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setDomicilio(alumno.txtDomiciAlumno.getText());
            return true;
        }

    }

    public boolean ValidarDNIpadre() {

        if (alumno.txtDNIPadre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del padre", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNIPadre.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo en DNI de la madre", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniPadre(alumno.txtDNIPadre.getText());
                return true;
            }
        }

    }

    public boolean ValidarDNImadre() {

        if (alumno.txtDNImadre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI de la madre", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNImadre.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo en DNI de la madre", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniMadre(alumno.txtDNImadre.getText());
                return true;
            }
        }

    }

    public boolean ValidarDNIapoderado() {

        if (alumno.txtDNIapoderado.getText()
                .isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del apoderado", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNIapoderado.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo en DNI de apoderado", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniApoderado(alumno.txtDNIapoderado.getText());
                return true;
            }
        }

    }

    public boolean ValidarTelf1() {

        if (alumno.txtTelefono1.getText()
                .isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el número de teléfono 1", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String telefono1 = alumno.txtTelefono1.getText();
            if (!telefono1.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido de 9 dígitos numéricos", "Valor erróneo en teléfono 1 del alumno", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setTelefono1(Integer.parseInt(telefono1));
                return true;
            }
        }

    }

    public boolean ValidarTelf2() {

        if (alumno.txtTelefono2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el número de teléfono 2, sino tiene solo ingrese el numero 0", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String telefono2 = alumno.txtTelefono2.getText();

            if (telefono2.equals("0")) {
                JOptionPane.showMessageDialog(null, "Esta indicando que no tiene otro numero de telefono", "No hay telefono 2 registrado", JOptionPane.INFORMATION_MESSAGE);
                al.setTelefono2(Integer.parseInt(telefono2));
                return true;
            } else if (!telefono2.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido de 9 dígitos numéricos.\nSi no tiene colocar el numero 0", "Valor erróneo en teléfono 2 del alumno", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setTelefono2(Integer.parseInt(telefono2));
                return true;
            }
        }
    }

    public boolean ValidarEmail() {

        String emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        String email = alumno.txtEmail.getText();
        Matcher matcher = pattern.matcher(email);

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el correo electrónico", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "Ingrese un correo electrónico válido (ejemplo@gmail.com)", "Correo electrónico inválido", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setEmail(email);
            return true;
        }

    }

    public boolean ValidarSexo() {
        if (alumno.cbxSexo.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese el sexo del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setSexo(alumno.cbxSexo.getSelectedItem().toString());
            return true;
        }

    }

    public boolean ValidarDiscapacidad() {
        if (alumno.txtDiscapacidad.getText().isEmpty()) {
            int opcion = JOptionPane.showConfirmDialog(null, "El campo de discapacidad está en blanco. ¿Quieres dejarlo en blanco?", "Campo vacío", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                al.setDiscapacidad(""); // Establece la discapacidad en blanco
                return true;
            } else {
                return false;
            }
        } else {
            al.setDiscapacidad(alumno.txtDiscapacidad.getText());
            return true;
        }
    }

    public boolean ValidarSangui() {

        String grupoSanguineo = alumno.txtGrupoSangui.getText().trim();

        if (grupoSanguineo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese un valor para el grupo sanguíneo", "Campo vacío", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (grupoSanguineo.length() > 4) {
            JOptionPane.showMessageDialog(null, "El grupo sanguíneo debe tener como máximo 4 caracteres", "Valor incorrecto", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setGrupoSangui(grupoSanguineo);
            return true;
        }

    }

    public boolean ValidarAlergia() {
        if (alumno.txtAlergias.getText().isEmpty()) {
            int opcion = JOptionPane.showConfirmDialog(null, "El campo de alergias está en blanco. ¿Quieres dejarlo en blanco?", "Campo vacío", JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                al.setAlergias(""); // Establece alergias en blanco
                return true;
            }
        } else {
            al.setAlergias(alumno.txtAlergias.getText());
            return true;
        }

        return false; // Agregamos un valor de retorno predeterminado
    }

    public boolean ValidarNivel() {
        if (alumno.cbxNivel.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nivel del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "Se selecciono el nivel " + alumno.cbxNivel.getSelectedItem().toString() + "¿esta conforme?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                al.setNivel(alumno.cbxNivel.getSelectedItem().toString());
                return true;
            }

        }
        return false;
    }

    public boolean ValidarGrado() {

        if (alumno.cbxGradoEscolar.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese el grado del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "Se selecciono el grado  " + alumno.cbxGradoEscolar.getSelectedItem().toString() + "¿esta conforme?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {

                al.setGrado(alumno.cbxGradoEscolar.getSelectedItem().toString());
                return true;
            }

        }
        return false;
    }

    public boolean ValidarSeccion() {

        if (alumno.cbxSeccion.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese la seccion del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "Se selecciono la seccion " + alumno.cbxSeccion.getSelectedItem().toString() + "¿esta conforme?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {

                al.setSeccion(alumno.cbxSeccion.getSelectedItem().toString());
                return true;
            }

        }
        return false;
    }

    public boolean ValidarCodigoModular() {
        if (alumno.txtCodigoModular.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el código modular del estudiante", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String codigoModular = alumno.txtCodigoModular.getText();
            if (!codigoModular.matches("\\d{7}")) {
                JOptionPane.showMessageDialog(null, "Ingrese un código modular válido", "Valor erróneo en código modular del alumno", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setCodigoModular(codigoModular);
                return true;
            }
        }
    }

}
