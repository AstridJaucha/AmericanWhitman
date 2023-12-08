package controller;

import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import views.RegistroAlumno;
import models.Alumno;

public class ValidarRegistro {
    
    private RegistroAlumno alumno;
    private Alumno al;
    
    public ValidarRegistro(RegistroAlumno alumno, Alumno al) {
        this.alumno = alumno;
        this.al = al;
    }
    
    public boolean ValidarNombre() {
        if (alumno.txtNombre.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el nombre del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setNombreAlumno(alumno.txtNombre.getText());
            return true;
        }
        
    }
    
    public boolean ValidarPaterno() {
        
        if (alumno.txtApePaterno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido paterno del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setApePaterno(alumno.txtApePaterno.getText());
            return true;
        }
        
    }
    
    public boolean ValidarMatern() {
        if (alumno.txtApeMaterno.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el apellido materno del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setApeMaterno(alumno.txtApeMaterno.getText());
            return true;
        }
        
    }
    
    public boolean ValidarDNI() {
        if (alumno.txtDNI.getText()
                .isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNI.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniAlumno(alumno.txtDNI.getText());
                return true;
            }
        }
        
    }
    
    public boolean ValidarFecha() {
        if (alumno.txtFnacimien.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la fecha de nacimiento del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String fechaNacimiento = alumno.txtFnacimien.getText();
            if (fechaNacimiento.matches("\\d{4}-\\d{2}-\\d{2}")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);  // No permite fechas inválidas

                try {
                    Date parsedDate = dateFormat.parse(fechaNacimiento);
                    al.setFechNacimiento(fechaNacimiento);
                    return true;
                } catch (ParseException e) {
                    JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida en\n el formato AAAA-MM-DD (ejemplo: 2004-06-30)", "Fecha de nacimiento inválida", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha de nacimiento válida en\n el formato AA-MM-DD (ejemplo: 04-06-30)", "Formato de fecha incorrecto", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
    
    public boolean ValidarDomi() {
        
        if (alumno.txtDomici.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el domicilio del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setDomicilio(alumno.txtDomici.getText());
            return true;
        }
        
    }
    
    public boolean ValidarDNIpadre() {
        
        if (alumno.txtDNIPa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del padre", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNIPa.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo en DNI de la madre", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniPadre(alumno.txtDNIPa.getText());
                return true;
            }
        }
        
    }
    
    public boolean ValidarDNImadre() {
        
        if (alumno.txtDNIma.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI de la madre", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNIma.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo en DNI de la madre", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniMadre(alumno.txtDNIma.getText());
                return true;
            }
        }
        
    }
    
    public boolean ValidarDNIapoderado() {
        
        if (alumno.txtDNIapo.getText()
                .isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el DNI del apoderado", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            if (alumno.txtDNIapo.getText().length() != 8) {
                JOptionPane.showMessageDialog(null, "Ingrese un numero de DNI valido", "Valor erroreo en DNI de apoderado", JOptionPane.ERROR_MESSAGE);
                return false;
            } else {
                al.setDniApoderado(alumno.txtDNIapo.getText());
                return true;
            }
        }
        
    }
    
    public boolean ValidarTelf1() {
        
        if (alumno.txtTele1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el número de teléfono 1", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String telefono1 = alumno.txtTele1.getText();
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
        
        if (alumno.txtTele2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el número de teléfono 2\nSino tiene ingrese solo 0", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String telefono2 = alumno.txtTele2.getText();
            
            
            if (telefono2.equals("0")) {
                al.setTelefono2(Integer.parseInt(telefono2));
                return true;
            } else if (!telefono2.matches("\\d{9}")) {
                JOptionPane.showMessageDialog(null, "Ingrese un número de teléfono válido de 9 dígitos numéricos", "Valor erróneo en teléfono 2 del alumno", JOptionPane.ERROR_MESSAGE);
                return true;
            } else {
                al.setTelefono2(Integer.parseInt(telefono2));
                return true;
            }
            
        }
        
    }
    
    public boolean ValidarEmail() {
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@gmail\\.com$";
        Pattern pattern = Pattern.compile(emailRegex);
        String email = alumno.txtEma.getText();
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
        if (alumno.cbxSexoN.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese el sexo del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            al.setSexo(alumno.cbxSexoN.getSelectedItem().toString());
            return true;
        }
        
    }
    
    public boolean ValidarDiscapacidad() {
        if (alumno.txtDiscapa.getText().isEmpty()) {
            int opcion = JOptionPane.showConfirmDialog(null, "El campo de discapacidad está en blanco. ¿Quieres dejarlo en blanco?", "Campo vacío", JOptionPane.YES_NO_OPTION);
            
            if (opcion == JOptionPane.YES_OPTION) {
                al.setDiscapacidad(""); // Establece la discapacidad en blanco
                return true;
            } else {
                return false;
            }
        } else {
            al.setDiscapacidad(alumno.txtDiscapa.getText());
            return true;
        }
    }
    
    public boolean ValidarSangui() {
        
        String grupoSanguineo = alumno.txtGrupoSan.getText().trim();
        
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
        if (alumno.txtAlerg.getText().isEmpty()) {
            int opcion = JOptionPane.showConfirmDialog(null, "El campo de alergias está en blanco. ¿Quieres dejarlo en blanco?", "Campo vacío", JOptionPane.YES_NO_OPTION);
            
            if (opcion == JOptionPane.YES_OPTION) {
                al.setAlergias(""); // Establece alergias en blanco
                return true;
            }
        } else {
            al.setAlergias(alumno.txtAlerg.getText());
            return true;
        }
        
        return false; // Agregamos un valor de retorno predeterminado
    }
    
    public boolean ValidarNivel() {
        if (alumno.cbxNiv.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese el nivel del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "Se selecciono el nivel " + alumno.cbxNiv.getSelectedItem().toString() + "¿esta conforme?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                al.setNivel(alumno.cbxNiv.getSelectedItem().toString());
                return true;
            }
            
        }
        return false;
    }
    
    public boolean ValidarGrado() {
        
        if (alumno.cbxGrado.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese el grado del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "Se selecciono el grado  " + alumno.cbxGrado.getSelectedItem().toString() + "¿esta conforme?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                
                al.setGrado(alumno.cbxGrado.getSelectedItem().toString());
                return true;
            }
            
        }
        return false;
    }
    
    public boolean ValidarSeccion() {
        
        if (alumno.cbxSec.getSelectedItem().toString().equals("Seleccionar")) {
            JOptionPane.showMessageDialog(null, "Ingrese la seccion del alumno", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            int opcion = JOptionPane.showConfirmDialog(null, "Se selecciono la seccion " + alumno.cbxSec.getSelectedItem().toString() + "¿esta conforme?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                
                al.setSeccion(alumno.cbxSec.getSelectedItem().toString());
                return true;
            }
            
        }
        return false;
    }
    
    public boolean ValidarCodigoModular() {
        if (alumno.txtCodMo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el código modular del estudiante", "No tiene un valor registrado", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            String codigoModular = alumno.txtCodMo.getText();
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
