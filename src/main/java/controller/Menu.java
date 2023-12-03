package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import views.ListaAlumnos;
import views.RegistroAlumno;
import views.PanelMenu;

public class Menu implements ActionListener {

    private RegistroAlumno sistema;
    private ListaAlumnos lista;
    private PanelMenu menu;

    public Menu(RegistroAlumno sistema, PanelMenu menu, ListaAlumnos lista) {

        this.sistema = sistema;
        this.lista = lista;
        this.menu = menu;

        this.menu.btnCerrar.addActionListener(this);
        this.menu.btnListaAlumnos.addActionListener(this);
        this.menu.btnListaPadres.addActionListener(this);
        this.menu.btnRegistrarAlumno.addActionListener(this);
        this.sistema.btnMenu.addActionListener(this);
        this.lista.btnMenu.addActionListener(this);
    }

    public void listaAlumn() {

        menu.setVisible(false);
        lista.setVisible(true);

    }

    public void regisAlumn() {

        menu.setVisible(false);
        sistema.setVisible(true);

    }

    public void volver1() {

        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea volver al menú?\nNo se guardaran los datos que se pueda encontrar actualizando\na menos que ya lo haya guardado", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            sistema.setVisible(false);
            menu.setVisible(true);
        }
    }
    
public void volver2() {

        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea volver al menú?", "Confirmar salida", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            lista.setVisible(false);
            menu.setVisible(true);
        }
    }    
    

    public void cerrar() {
        int opcion = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cerrar el sistema?", "Confirmar cierre", JOptionPane.YES_NO_OPTION);

        if (opcion == JOptionPane.YES_OPTION) {
            menu.setVisible(false);
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu.btnListaAlumnos) {
            listaAlumn();
        }
        if (e.getSource() == menu.btnRegistrarAlumno) {
            regisAlumn();
        }
        if (e.getSource() == menu.btnCerrar) {
            cerrar();
        }
        if (e.getSource() == sistema.btnMenu) {
            volver1();
        }
        if (e.getSource() == lista.btnMenu) {
            volver2();
        }
    }
}
