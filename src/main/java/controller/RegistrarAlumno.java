package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.GestorBD;
import views.RegistroAlumno;
import models.Alumno;

public class RegistrarAlumno implements ActionListener {

    private GestorBD gestor;
    private RegistroAlumno alumno;
    private Alumno al;
    private ValidarRegistro val;

    public RegistrarAlumno(RegistroAlumno alumno, GestorBD gestor, Alumno al, ValidarRegistro val) {
        this.alumno = alumno;
        this.gestor = gestor;
        this.al = al;
        this.val = val;
        this.alumno.btnRegistrar.addActionListener(this);

    }

    public void Registrar() {
        try {

            if (gestor.InsertarAlumno(
                    al.getDniAlumno(),
                    al.getNombreAlumno(),
                    al.getApePaterno(),
                    al.getApeMaterno(),
                    al.getFechNacimiento(),
                    al.getDomicilio(),
                    al.getDniPadre(),
                    al.getDniMadre(),
                    al.getDniApoderado(),
                    al.getTelefono1(),
                    al.getTelefono2(),
                    al.getEmail(),
                    al.getDiscapacidad(),
                    al.getGrupoSangui(),
                    al.getAlergias(),
                    al.getNivel(),
                    al.getGrado(),
                    al.getSeccion(),
                    al.getCodigoModular(),
                    al.getSexo())) {
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Registro fallido");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alumno.btnRegistrar) {
            boolean validacionesExitosas = true;  // Variable para rastrear el estado de las validaciones

            if (!val.ValidarNombre() || !val.ValidarPaterno() || !val.ValidarMatern() || !val.ValidarDNI()
                    || !val.ValidarFecha() || !val.ValidarDomi() || !val.ValidarDNIpadre() || !val.ValidarDNImadre()
                    || !val.ValidarDNIapoderado() || !val.ValidarTelf1() || !val.ValidarTelf2() || !val.ValidarEmail()
                    || !val.ValidarSexo() || !val.ValidarDiscapacidad() || !val.ValidarSangui() || !val.ValidarAlergia()
                    || !val.ValidarNivel() || !val.ValidarGrado() || !val.ValidarSeccion() || !val.ValidarCodigoModular()) {
                validacionesExitosas = false;
            }

            if (validacionesExitosas) {
                // Todas las validaciones fueron exitosas, ahora procede a registrar
                Registrar();
            }
        }

    }
}
