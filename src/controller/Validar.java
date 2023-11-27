package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.GestorBD;
import views.Login;
import views.RegistroAlumno;
import models.Personal;


public class Validar implements ActionListener {

    private Login login;
    private GestorBD gestor;
    private RegistroAlumno sistema;
    private Personal x;
    

    public Validar(Login login, RegistroAlumno sistema, GestorBD gestor, Personal x) {

        this.sistema = sistema;
        this.login = login;
        this.gestor = gestor;
        this.x=x;

        this.login.btnIngresar.addActionListener(this);
    }

   
    public void Ingreso() {
        x.setIdPersonal(login.txtUsuario.getText());
        x.setClavePers(login.txtClave.getText());
        
        if (gestor.Validar(x.getIdPersonal(), x.getClavePers())) {
            JOptionPane.showMessageDialog(null, "Ingreso exitoso");
            // Cierra el JFrame de "Login"
            login.setVisible(false);
            // Establece la visibilidad del JFrame "RegistroAlumno"
            sistema.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.btnIngresar) {
            Ingreso();
        }
       
    }
}
