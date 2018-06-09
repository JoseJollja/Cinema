/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.controlador;
import pe.unmsm.cine.gui.VistaConfiguraciones;
import pe.unmsm.cine.gui.VistaUsuarios;
import pe.unmsm.cine.gui.VistaMenuPrincipal;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Jollja
 */
public class MenuPrincipalControlador {
    
    VistaMenuPrincipal vMenu; 

    public void setVistaMenuPrincipal(VistaMenuPrincipal vMenu){
        this.vMenu = vMenu;
   
        vMenu.btnUsuarios.addActionListener(this::clickUsuarios);
        vMenu.btnConfiguracion.addActionListener(this::clickConfiguraciones);
        vMenu.btnSalir.addActionListener(this::clickSalir); 
    }
    
   
   
    public void clickUsuarios(ActionEvent e){
        VistaUsuarios vUsuario = new VistaUsuarios();
        UsuarioControlador cUsuario = new UsuarioControlador();
        cUsuario.setVistaHabitacion(vUsuario);
        vUsuario.setVisible(true);
        vMenu.dispose();
    }
    
    public void clickConfiguraciones(ActionEvent e){
        VistaConfiguraciones vConfig = new VistaConfiguraciones();
        ConfiguracionesControlador cConfig = new ConfiguracionesControlador();
        cConfig.setVistaConfiguraciones(vConfig);
        vConfig.setVisible(true);
        vMenu.dispose();
    }
    
    
  
    
    public void clickSalir(ActionEvent e){
        vMenu.dispose();
    }
    
}

