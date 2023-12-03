
package controller;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import models.GestorBD;
import views.RegistroAlumno;

public class ComboBox {
    private RegistroAlumno vista; // La ventana de la interfaz de usuario
    
    public ComboBox(RegistroAlumno vista) {
        this.vista = vista;
    }

    public  void DatosComboBoxNivel(JComboBox<String> comboBoxNivel) {
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
    public void DatosComboBoxSeccion(JComboBox<String> comboBoxSeccion) {
        // Lógica para cargar datos desde la capa de acceso a datos (GestorBD)
        GestorBD.DatosComboBoxSeccion(comboBoxSeccion);
    }
}