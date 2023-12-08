package controller;

import javax.swing.JComboBox;
import models.GestorBD;
import views.RegistroAlumno;
import views.ListaAlumnos;
import views.RatificarMatricula;

public class ComboBox {

    private RegistroAlumno vista; 
    private ListaAlumnos lis;
    private RatificarMatricula rat;

    public ComboBox(RegistroAlumno vista, ListaAlumnos lis, RatificarMatricula rat) {
        this.vista = vista;
        this.lis = lis;
        this.rat=rat;
    }

    public void DatosComboBoxNivel(JComboBox<String> comboBoxNivel) {
        // Lógica para cargar datos desde la capa de acceso a datos (GestorBD)
        GestorBD.DatosComboBoxNivel(comboBoxNivel);
    }

    public void DatosComboBoxGrado(JComboBox<String> comboBoxNivel, JComboBox<String> comboBoxGrado) {
        // Lógica para cargar datos desde la capa de acceso a datos (GestorBD)

        String nivelSeleccionado = (String) comboBoxNivel.getSelectedItem();

        comboBoxGrado.removeAllItems(); // Limpia los elementos actuales

        if ("Inicial".equals(nivelSeleccionado)) {
            comboBoxGrado.addItem("Seleccionar");
            comboBoxGrado.addItem("3 años");
            comboBoxGrado.addItem("4 años");
            comboBoxGrado.addItem("5 años");
        } else if ("Primaria".equals(nivelSeleccionado)) {
            comboBoxGrado.addItem("Seleccionar");
            comboBoxGrado.addItem("Primero");
            comboBoxGrado.addItem("Segundo");
            comboBoxGrado.addItem("Tercero");
            comboBoxGrado.addItem("Cuarto");
            comboBoxGrado.addItem("Quinto");
            comboBoxGrado.addItem("Sexto");
        } else if ("Secundaria".equals(nivelSeleccionado)) {
            comboBoxGrado.addItem("Seleccionar");
            comboBoxGrado.addItem("Primero");
            comboBoxGrado.addItem("Segundo");
            comboBoxGrado.addItem("Tercero");
            comboBoxGrado.addItem("Cuarto");
            comboBoxGrado.addItem("Quinto");
        }

    }

    public void DatosComboBoxSeccion(JComboBox<String> comboBoxNivel, JComboBox<String> comboBoxGrado,JComboBox<String> comboBoxSeccion) {
        // Lógica para cargar datos desde la capa de acceso a datos (GestorBD)

        String gradoSeleccionado = (String) comboBoxGrado.getSelectedItem();

        comboBoxSeccion.removeAllItems(); // Limpia los elementos actuales

        if ("seleccionar".equals(gradoSeleccionado)) {
            comboBoxSeccion.addItem("Seleccionar");

        } else {
            comboBoxSeccion.addItem("Seleccionar");
            comboBoxSeccion.addItem("A");
            comboBoxSeccion.addItem("B");
            comboBoxSeccion.addItem("C");
        }
    }
}
