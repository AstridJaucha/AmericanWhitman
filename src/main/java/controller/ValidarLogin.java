package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.GestorBD;
import views.Login;
import views.RegistroAlumno;
import models.Personal;
import views.PanelMenu;


public class ValidarLogin implements ActionListener {

    private Login login;
    private GestorBD gestor;
    private RegistroAlumno sistema;
    private PanelMenu menu;
    private Personal x;
    

    public ValidarLogin(Login login, RegistroAlumno sistema, GestorBD gestor, Personal x, PanelMenu menu) {

        this.sistema = sistema;
        this.login = login;
        this.gestor = gestor;
        this.x=x;
        this.menu=menu;

        this.login.btnIngresar.addActionListener(this);
    }

   
    public boolean Ingreso() {
        x.setIdPersonal(login.txtUsuario.getText());
        x.setClavePers(login.txtClave.getText());
        
        if (gestor.Validar(x.getIdPersonal(), x.getClavePers())) {
            System.out.println("Ingreso correcto de usuario " + x.getIdPersonal());
            // Cierra el JFrame de "Login"
            login.setVisible(false);
            // Establece la visibilidad del JFrame "RegistroAlumno"
            menu.setVisible(true);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            
        }
        return false;
    }
    public void inser(){
        String id=x.getIdPersonal();
        
        gestor.registraPersonal(id);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login.btnIngresar) {
            if (Ingreso()) {
                inser();
            }
            
        }
       
    }
}
