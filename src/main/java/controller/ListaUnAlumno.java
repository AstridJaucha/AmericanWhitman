package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Alumno;
import models.GestorBD;
import views.ListaAlumnos;

public class ListaUnAlumno implements ActionListener {

    
    private GestorBD gestor;
    private ListaAlumnos lista;
    private Alumno al;
    DefaultTableModel lisAlumn;

    public ListaUnAlumno(ListaAlumnos lista, GestorBD gestor, Alumno al) {
        this.lista = lista;
        this.gestor = gestor;
        this.al = al;
        this.lista.btnBuscar.addActionListener(this);
//        this.lista.tblEstudiantes(lisAlumn);
    }

    public void buscar() {
        
        try {
            if (gestor.Buscar1alumno(
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

               
                lisAlumn.addColumn("Nombres");
                lisAlumn.addColumn("Apellido Paterno");
                lisAlumn.addColumn("Apellido Materno");
                lisAlumn.addColumn("F. Nacimiento");
                lisAlumn.addColumn("Sexo");
                lisAlumn.addColumn("Domicilio");
                lisAlumn.addColumn("DNI Padre");
                lisAlumn.addColumn("DNI Madre");
                lisAlumn.addColumn("DNI Apoderado");
                lisAlumn.addColumn("Telefono 1");
                lisAlumn.addColumn("Telefono 2");
                lisAlumn.addColumn("E-mail");
                lisAlumn.addColumn("Discapacidad");
                lisAlumn.addColumn("Grupo sanguíneo");
                lisAlumn.addColumn("Alergias");
                lisAlumn.addColumn("Nivel");
                lisAlumn.addColumn("Grado");
                lisAlumn.addColumn("Sección");
                lisAlumn.addColumn("Código modular");

                Object[] fila = new Object[]{
                    al.getDniAlumno(),
                    al.getApePaterno(),
                    al.getApeMaterno(),
                    al.getFechNacimiento(),
                    al.getSexo(),
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
                    al.getCodigoModular()
                };
                lisAlumn.addRow(fila);
                JOptionPane.showMessageDialog(null, "Si sale, mira tengo el " + al.getApeMaterno());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron datos para el DNI proporcionado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lista.btnBuscar) {

            buscar();
        }

    }
}
