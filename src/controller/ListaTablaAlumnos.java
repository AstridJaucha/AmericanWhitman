package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import models.Alumno;
import models.GestorBD;
import views.ListaAlumnos;
import views.RegistroAlumno;

public class ListaTablaAlumnos extends DefaultTableModel implements ActionListener {

    private GestorBD gestor;
    private RegistroAlumno alumno;
    private Alumno al;
    private ListaAlumnos list;
    
    public ListaTablaAlumnos(GestorBD gestor,RegistroAlumno alumno,Alumno al,ListaAlumnos list) {

        this.al= al;
        this.gestor=gestor;
        this.alumno=alumno;
        this.list=list;
    }

    public void llenar() {

        addColumn("DNI");
        addColumn("Nombres");
        addColumn("Apellido Paterno");
        addColumn("Apellido Materno");
        addColumn("F. Nacimiento");
        addColumn("Sexo");
        addColumn("Domicilio");
        addColumn("DNI Padre");
        addColumn("DNI Madre");
        addColumn("DNI Apoderado");
        addColumn("Telefono 1");
        addColumn("Telefono 2");
        addColumn("E-mail");
        addColumn("Discapacidad");
        addColumn("Grupo sanguíneo");
        addColumn("Alergias");
        addColumn("Nivel");
        addColumn("Grado");
        addColumn("Sección");
        addColumn("Código modular");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == list.btnBuscar) {

        }

    }
}
