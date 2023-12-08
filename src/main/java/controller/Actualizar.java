
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Alumno;
import models.GestorBD;
import views.RatificarMatricula;
import views.RegistroAlumno;

public class Actualizar implements ActionListener {

    private GestorBD gestor;
    private RegistroAlumno alumno;
    private Alumno al;
    private ValidarRatifica val;
    private RatificarMatricula rat;

    public Actualizar(GestorBD gestor, Alumno al, ValidarRatifica val, RatificarMatricula rat) {
        
        this.gestor = gestor;
        this.al = al;
        this.val = val;
        this.rat=rat;
        this.rat.btnRatificarAlumno.addActionListener(this);

    }

    public void Actualizar() {
        try {

            if (gestor.ActualizarAlumno(
                    al.getDniAlumno(),
                    al.getDomicilio(),
                    al.getDniApoderado(),
                    al.getTelefono1(),
                    al.getTelefono2(),
                    al.getEmail(),
                    al.getDiscapacidad(),
                    al.getGrupoSangui(),
                    al.getAlergias(),
                    al.getNivel(),
                    al.getGrado(),
                    al.getSeccion())) {
                JOptionPane.showMessageDialog(null, "Actualización exitoso");
            } else {
                JOptionPane.showMessageDialog(null, "Actualización fallida");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rat.btnRatificarAlumno) {
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
                Actualizar();
            }
        }

    }

}
