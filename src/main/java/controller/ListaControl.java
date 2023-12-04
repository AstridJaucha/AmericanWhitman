package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import models.Alumno;
import models.GestorBD;
import views.ListaAlumnos;

public class ListaControl implements ActionListener {

    private GestorBD gestor;
    private ListaAlumnos lista;
    private Alumno al;
    private DefaultTableModel lisAlumn;

    public ListaControl(ListaAlumnos lista, GestorBD gestor, Alumno al) {
        this.lista = lista;
        this.gestor = gestor;
        this.al = al;

        this.lista.btnTodos.addActionListener(this);
        this.lista.btnBuscar.addActionListener(this);
        this.lista.btnBuscaEspe.addActionListener(this);

        lisAlumn = new DefaultTableModel();
        this.lista.tblEstudiantes.setModel(lisAlumn);
    }

    private void tablaTodos() {
        gestor.tablaTodos(lisAlumn);
    }

    private void tablaestu() {
        gestor.tablaestu(lisAlumn);
    }

    private void buscaEspe() {
        gestor.especial(lisAlumn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lista.btnTodos) {
            tablaTodos();
        } else if (e.getSource() == lista.btnBuscar) {
            tablaestu();
        } else if (e.getSource() == lista.btnBuscaEspe) {
            buscaEspe();
        }
    }

}
