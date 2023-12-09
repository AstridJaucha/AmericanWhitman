package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Alumno;
import models.GestorBD;
import models.Jovenes;
import views.RatificarMatricula;
import views.RegistroAlumno;

public class BuscarDatos implements ActionListener {

    private GestorBD gestor;
    private Jovenes nin;
    private RegistroAlumno sistema;

    public BuscarDatos(GestorBD gestor, Jovenes nin, RegistroAlumno sistema) {

        this.gestor = gestor;
        this.sistema = sistema;
        this.nin = nin;
        this.sistema.btnBuscador.addActionListener(this);

    }

    public void buscar() {

        try {
            if (gestor.BuscarJove(
                    nin.getDni(),
                    nin.getNom(),
                    nin.getApePa(),
                    nin.getApeMa(),
                    nin.getFechNac(),
                    nin.getSexo(),
                    nin.getDniPadre(),
                    nin.getDniMadre(),
                    nin.getNivel(),
                    nin.getGrado(),
                    nin.getCodigoModular(),
                    nin.getAñoEduc())) {
                if ("2024".equals(nin.getAñoEduc())) {  
                    JOptionPane.showMessageDialog(null, nin.getNom() + " ya es un estudiante de nuestra institución", "Estudiante ya ingresado", 0);
                    sistema.txtAlerg.setText("");
                    sistema.txtApeMaterno.setText("");
                    sistema.txtApePaterno.setText("");
                    sistema.txtDNIBuscar.setText("");
                    sistema.txtCodMo.setText("");
                    sistema.txtDNIPa.setText("");
                    sistema.txtDNI.setText("");
                    sistema.txtDNIapo.setText("");
                    sistema.txtDNIma.setText("");
                    sistema.txtDiscapa.setText("");
                    sistema.txtDomici.setText("");
                    sistema.txtEma.setText("");
                    sistema.txtFnacimien.setText("");
                    sistema.txtGrupoSan.setText("");
                    sistema.txtNombre.setText("");
                    sistema.txtTele1.setText("");
                    sistema.txtTele2.setText("");
                    sistema.txtA.setText("");
                    sistema.cbxGrado.setSelectedItem("Seleccionar");
                    sistema.cbxNiv.setSelectedItem("Seleccionar");
                    sistema.cbxSec.setSelectedItem("Seleccionar");
                    sistema.cbxSexoN.setSelectedItem("Seleccionar");
                } else {

                    // Actualizar los campos de los JTextField con los datos recuperados
                    sistema.txtCodMo.setText(nin.getCodigoModular());
                    sistema.txtApeMaterno.setText(nin.getApePa());
                    sistema.txtApePaterno.setText(nin.getApeMa());
                    sistema.txtDNI.setText(nin.getDni());
                    sistema.txtCodMo.setText(nin.getCodigoModular());
                    sistema.txtDNIPa.setText(nin.getDniPadre());
                    sistema.txtDNIma.setText(nin.getDniMadre());
                    sistema.txtFnacimien.setText(nin.getFechNac());
                    sistema.txtNombre.setText(nin.getNom());
                    sistema.txtA.setText(nin.getAñoEduc());
                    sistema.cbxNiv.setSelectedItem(nin.getNivel());
                    sistema.cbxGrado.setSelectedItem(nin.getGrado());
                    sistema.cbxSexoN.setSelectedItem(nin.getSexo());
                }
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sistema.btnBuscador) {

            buscar();
        }

    }
}
