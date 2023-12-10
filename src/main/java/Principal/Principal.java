package Principal;

import controller.*;
import models.*;
import views.*;
import controller.BuscarAlumno;

public class Principal {

    public static void main(String[] args) {
        Login vista = new Login();

        RegistroAlumno sistema = new RegistroAlumno();
        Personal x = new Personal();
        Alumno al = new Alumno();
        Jovenes nin = new Jovenes();
        ListaAlumnos lista = new ListaAlumnos();
        PanelMenu panel = new PanelMenu();
        DatosExcel datex = new DatosExcel();
        RatificarMatricula rat = new RatificarMatricula();
        GestorBD modelo = new GestorBD(sistema, al, lista, nin, rat, datex);
        BuscarDatos busca = new BuscarDatos(modelo, nin, sistema);
        ValidarRegistro val = new ValidarRegistro(sistema, al);
        ValidarRatifica vlrat = new ValidarRatifica(rat, al);
        Menu menu = new Menu(sistema, panel, lista, rat);
        ComboBox a = new ComboBox(sistema, lista, rat);
        ValidarLogin validar = new ValidarLogin(vista, sistema, modelo, x, panel);
        RegistrarAlumno registrar = new RegistrarAlumno(sistema, modelo, al, val);
        BuscarAlumno buscar = new BuscarAlumno(modelo, al, rat);

        ListaControl bus = new ListaControl(lista, modelo, al);

        Actualizar actualizar = new Actualizar(modelo, al, vlrat, rat);

        ExcelReporte excelReporte = new ExcelReporte(sistema, datex, rat, modelo);
        vista.setVisible(true);
    }

}
