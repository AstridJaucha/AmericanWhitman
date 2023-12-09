package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.Alumno;
import models.DatosExcel;
import models.GestorBD;
import views.RatificarMatricula;
import views.RegistroAlumno;

public class ExcelReporte implements ActionListener {

    private GestorBD gestor;
    private RegistroAlumno alumno;
    private Alumno al;
    private DatosExcel dat;
    private RatificarMatricula rat;

    public ExcelReporte(RegistroAlumno alumno,DatosExcel dat, RatificarMatricula rat, GestorBD gestor) {
        this.alumno = alumno;
        this.dat = dat;
        this.rat = rat;
        this.gestor = gestor;
        this.rat.btnDoc.addActionListener(this);
        this.alumno.btnDoc.addActionListener(this);
    }

    public void GenerarDocRatif() {
        try {

            String codAlumno = rat.txtCodigoEstu.getText();

            if (gestor.obtenerDatosRatific(
                    codAlumno,
                    dat.getDniAlumno(),
                    dat.getNombreAlumno(),
                    dat.getApePaterno(),
                    dat.getApeMaterno(),
                    dat.getFechNacimiento(),
                    dat.getSexo(),
                    dat.getDomicilio(),
                    dat.getDniPadre(),
                    dat.getDniMadre(),
                    dat.getDniApoderado(),
                    dat.getTelefono1(),
                    dat.getTelefono2(),
                    dat.getEmail(),
                    dat.getDiscapacidad(),
                    dat.getGruposanguineo(),
                    dat.getAlergias(),
                    dat.getAñoEduc(),
                    dat.getNivel(),
                    dat.getGrado(),
                    dat.getSeccion(),
                    dat.getCodigoModular(),
                    dat.getIdregistroalumno(),
                    dat.getFechahoraregistroestudiante(),
                    dat.getNombrepersonal()
            )) {
                System.out.println("Ratificación adecuadamente procesando reporte...");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void GenerarDocRegis() {
        try {

            String dniAlumno = alumno.txtDNI.getText();
            // Llama al método GenerarDoc de la instancia de Excel
            if (gestor.obtenerDatosRegistr(
                    dat.getCodAlumno(),
                    dniAlumno,
                    dat.getNombreAlumno(),
                    dat.getApePaterno(),
                    dat.getApeMaterno(),
                    dat.getFechNacimiento(),
                    dat.getSexo(),
                    dat.getDomicilio(),
                    dat.getDniPadre(),
                    dat.getDniMadre(),
                    dat.getDniApoderado(),
                    dat.getTelefono1(),
                    dat.getTelefono2(),
                    dat.getEmail(),
                    dat.getDiscapacidad(),
                    dat.getGruposanguineo(),
                    dat.getAlergias(),
                    dat.getAñoEduc(),
                    dat.getNivel(),
                    dat.getGrado(),
                    dat.getSeccion(),
                    dat.getCodigoModular(),
                    dat.getIdregistroalumno(),
                    dat.getFechahoraregistroestudiante(),
                    dat.getNombrepersonal())) {
                System.out.println("Registro adecuadamente procesando reporte...");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rat.btnDoc) {
            GenerarDocRatif();
        }
        if (e.getSource() == alumno.btnDoc) {
            GenerarDocRegis();
        }
    }
}
