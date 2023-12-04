package Principal;

import javax.swing.JOptionPane;
import controller.*;
import models.*;
import views.*;
import controller.BuscarAlumno;

public class Principal {

    public static void main(String[] args) {

//nuevas instancias de clase
        Login vista = new Login();

        RegistroAlumno sistema = new RegistroAlumno();
        Personal x = new Personal();
        Alumno al = new Alumno();
        ListaAlumnos lista = new ListaAlumnos();
        PanelMenu panel = new PanelMenu();

        GestorBD modelo = new GestorBD(sistema, al, lista);

        ValidarAlumno val = new ValidarAlumno(sistema, al);

        Menu menu = new Menu(sistema, panel, lista);
        ComboBox a = new ComboBox(sistema, lista);
        Validar validar = new Validar(vista, sistema, modelo, x, panel);
        RegistrarAlumno registrar = new RegistrarAlumno(sistema, modelo, al, val);
        BuscarAlumno buscar = new BuscarAlumno(sistema, modelo, al);
        
        ListaControl bus = new ListaControl(lista, modelo, al);

        Actualizar actualizar = new Actualizar(sistema, modelo, al, val);
        vista.setVisible(true);
    }

}
