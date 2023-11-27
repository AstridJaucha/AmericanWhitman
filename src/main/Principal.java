package main;

import models.*;
import views.*;
import controller.*;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
         
//nuevas instancias de clase
        Login vista = new Login();
        GestorBD modelo = new GestorBD();
        RegistroAlumno sistema = new RegistroAlumno();
        Personal x = new Personal();
        Alumno al = new Alumno();
        ValidarAlumno val = new ValidarAlumno(sistema, al);
        Validar validar = new Validar(vista, sistema, modelo, x);
        RegistrarAlumno resgitrar = new RegistrarAlumno(sistema, modelo, al,val);
        vista.setVisible(true);
    }

}
