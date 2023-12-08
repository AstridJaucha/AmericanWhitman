package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Alumno;
import models.GestorBD;
import views.RatificarMatricula;


public class BuscarAlumno implements ActionListener {

    private GestorBD gestor;

    private Alumno al;
    private RatificarMatricula rat;

    public BuscarAlumno(GestorBD gestor, Alumno al,RatificarMatricula rat) {
        
        this.gestor = gestor;
        this.al = al;
        this.rat=rat;
        this.rat.btnBuscar.addActionListener(this);

    }

    public void buscar() {
        try {
            if (gestor.BuscarAlumno(
                    al.getCodAlumno(),
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
                    al.getAñoEduc(),
                    al.getNivel(),
                    al.getGrado(),
                    al.getSeccion(),
                    al.getCodigoModular(),
                    al.getSexo())) {
                
                // Actualizar los campos de los JTextField con los datos recuperados
                rat.txtCodigoEstu.setText(al.getCodAlumno());
                rat.txtAlergias.setText(al.getAlergias());
                rat.txtApeMaternoAlumno.setText(al.getApeMaterno());
                rat.txtApePaternoAlumno.setText(al.getApePaterno());
                rat.txtDNIalumno.setText(al.getDniAlumno());
                rat.txtCodigoModular.setText(al.getCodigoModular());
                rat.txtDNIPadre.setText(al.getDniPadre());
                rat.txtDNIapoderado.setText(al.getDniApoderado());
                rat.txtDNImadre.setText(al.getDniMadre());
                rat.txtDiscapacidad.setText(al.getDiscapacidad());
                rat.txtDomiciAlumno.setText(al.getDomicilio());
                rat.txtEmail.setText(al.getEmail());
                rat.txtFnacimienAlumno.setText(al.getFechNacimiento());
                rat.txtGrupoSangui.setText(al.getGrupoSangui());
                rat.txtNombreAlumno.setText(al.getNombreAlumno());
                rat.txtA.setText(al.getAñoEduc());
                rat.txtCodigoEstu.setText(al.getCodAlumno());
                rat.txtTelefono1.setText(String.valueOf(al.getTelefono1()));
                rat.txtTelefono2.setText(String.valueOf(al.getTelefono2()));
                rat.cbxNivel.setSelectedItem(al.getNivel());
                rat.cbxGradoEscolar.setSelectedItem(al.getGrado());
                rat.cbxSeccion.setSelectedItem(al.getSeccion());
                rat.cbxSexo.setSelectedItem(al.getSexo());
                
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el código proporcionado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rat.btnBuscar) {
            
            buscar();
        }

    }
}
