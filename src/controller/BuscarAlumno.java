package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Alumno;
import models.GestorBD;
import views.RegistroAlumno;

public class BuscarAlumno implements ActionListener {

    private GestorBD gestor;
    private RegistroAlumno alumno;
    private Alumno al;

    public BuscarAlumno(RegistroAlumno alumno, GestorBD gestor, Alumno al) {
        this.alumno = alumno;
        this.gestor = gestor;
        this.al = al;
        this.alumno.btnBuscar.addActionListener(this);

    }

    public void buscar() {
        try {
            if (gestor.BuscarAlumno(
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
                
                // Actualizar los campos de los JTextField con los datos recuperados
                alumno.txtAlergias.setText(al.getAlergias());
                alumno.txtApeMaternoAlumno.setText(al.getApeMaterno());
                alumno.txtApePaternoAlumno.setText(al.getApePaterno());
                alumno.txtDNIalumno.setText(al.getDniAlumno());
                alumno.txtCodigoModular.setText(al.getCodigoModular());
                alumno.txtDNIPadre.setText(al.getDniPadre());
                alumno.txtDNIapoderado.setText(al.getDniApoderado());
                alumno.txtDNImadre.setText(al.getDniMadre());
                alumno.txtDiscapacidad.setText(al.getDiscapacidad());
                alumno.txtDomiciAlumno.setText(al.getDomicilio());
                alumno.txtEmail.setText(al.getEmail());
                alumno.txtFnacimienAlumno.setText(al.getFechNacimiento());
                alumno.txtGrupoSangui.setText(al.getGrupoSangui());
                alumno.txtNombreAlumno.setText(al.getNombreAlumno());
                alumno.txtTelefono1.setText(String.valueOf(al.getTelefono1()));
                alumno.txtTelefono2.setText(String.valueOf((char) al.getTelefono2()));
                alumno.cbxNivel.setSelectedItem(al.getNivel());
                alumno.cbxGradoEscolar.setSelectedItem(al.getGrado());
                alumno.cbxSeccion.setSelectedItem(al.getSeccion());
                alumno.cbxSexo.setSelectedItem(al.getSexo());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el DNI proporcionado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == alumno.btnBuscar) {
            
            buscar();
        }

    }
}
