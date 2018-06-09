/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.controlador;


import java.awt.Image;
import pe.unmsm.cine.gui.VistaLogin;
import pe.unmsm.cine.gui.VistaMenuPrincipal;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import pe.unmsm.cine.dao.UsuarioDAO;
import pe.unmsm.cine.entidades.Empresa;
import pe.unmsm.cine.entidades.Usuario;
import pe.unmsm.cine.estructuras.ListaDoble;
import pe.unmsm.cine.gui.VistaConfiguraciones;
import pe.unmsm.cine.dao.ConfiguracionesDAO;

/**
 *
 * @author Jollja
 */

public class LoginControlador {
    
    VistaLogin vLogin;
    VistaConfiguraciones vConfiguraciones;
    ConfiguracionesDAO BDConfiguracion = new ConfiguracionesDAO();
    
    UsuarioDAO BDUsuario = new UsuarioDAO();
    ListaDoble<Usuario> lista;
    
    public void setVistaLogin(VistaLogin vLogin){
        this.vLogin = vLogin;
        vLogin.btnIngresar.addActionListener(this::clickIngresar);
        vLogin.btnSalir.addActionListener(this::clickSalir);
        lista = BDUsuario.getAll();
       Empresa nuevo= BDConfiguracion.get(1);
        vLogin.txtusuario.setText(nuevo.getLogo());
        Image foto = vLogin.getToolkit().getImage(vLogin.txtusuario.getText());
            foto= foto.getScaledInstance(100,100,10);
            vLogin.Imagen.setIcon(new ImageIcon(foto));
            vLogin.txtusuario.setText("");
            
        
    }
    
    public void clickIngresar(ActionEvent e) {
        String usuario = vLogin.txtusuario.getText();
        String contra = String.valueOf(vLogin.txtcontra.getPassword());
        Iterator<Usuario> iterador = lista.iterator();
        boolean escorrecto = false;
        while (iterador.hasNext()) {
            Usuario usu = iterador.next();
            if(usu.getId().equals(usuario) && usu.getContraseña().equals(contra))
                escorrecto = true;
        }
        if(escorrecto){
            VistaMenuPrincipal vMenu = new VistaMenuPrincipal();
            MenuPrincipalControlador cMenu = new MenuPrincipalControlador();
            cMenu.setVistaMenuPrincipal(vMenu);   
            vMenu.setVisible(true);
            vLogin.dispose();
        }
        else{
            JOptionPane.showMessageDialog(vLogin,"El usuario  no existe");

        }
        
    }

    public void clickSalir(ActionEvent e) {
        vLogin.dispose();
    }
    
    /* 
    btnNombreDelBotonConProduct para botones
    txt para los jtextfields
    */
    
   
}
