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
    private ValidarAlumno val;

    public RegistrarAlumno(RegistroAlumno alumno, GestorBD gestor, Alumno al, ValidarAlumno val) {
        this.alumno = alumno;
        this.gestor = gestor;
        this.al = al;
        this.val = val;
        this.alumno.btnRegistrar.addActionListener(this);

    }

    public void Registrar() {

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

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alumno.btnRegistrar) {
            boolean validacionesExitosas = true;  // Variable para rastrear el estado de las validaciones

            if (!val.ValidarNombre()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarPaterno()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarMatern()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarDNI()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarFecha()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarDomi()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarDNIpadre()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarDNImadre()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarDNIapoderado()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarTelf1()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarTelf2()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarEmail()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarSexo()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarDiscapacidad()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarSangui()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarAlergia()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarNivel()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarGrado()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarSeccion()) {
                validacionesExitosas = false;
            }

            if (!val.ValidarCodigoModular()) {
                validacionesExitosas = false;
            }

            if (validacionesExitosas) {
                // Todas las validaciones fueron exitosas, ahora procede a registrar
                Registrar();
            }
        }

    }
}
