/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.controlador;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import pe.unmsm.cine.entidades.Empresa;
import pe.unmsm.cine.gui.VistaConfiguraciones;
import pe.unmsm.cine.gui.VistaMenuPrincipal;
import pe.unmsm.cine.dao.ConfiguracionesDAO;
import pe.unmsm.cine.gui.VistaLogin;
/**

/**
 *
 * @author Jollja
 */
public class ConfiguracionesControlador {
    VistaLogin vLogin;
    VistaConfiguraciones vConfiguraciones;
    ConfiguracionesDAO BDConfiguracion = new ConfiguracionesDAO();
    
    public void setVistaConfiguraciones(VistaConfiguraciones vConfiguraciones) {
        this.vConfiguraciones= vConfiguraciones;
        vConfiguraciones.btnguardar.addActionListener(this::clickGuardar);
        vConfiguraciones.btncambiar.addActionListener(this::clickCambiar);
        vConfiguraciones.btnBuscar.addActionListener(this::clickUbicacion);
        vConfiguraciones.btnSalir.addActionListener(this::clickFinalizar);
        Empresa nuevo= BDConfiguracion.get(1);
        vConfiguraciones.txtNombEmpresa.setText(nuevo.getNombre());
        vConfiguraciones.txtDireccion.setText(nuevo.getDireccion());
        vConfiguraciones.txtTelefono.setText(nuevo.getTelefono());
        vConfiguraciones.txtruc.setText(nuevo.getRuc());
        vConfiguraciones.txtmensaje.setText(nuevo.getMensaje());
        vConfiguraciones.txtruta.setText(nuevo.getLogo());
        vConfiguraciones.txtboleta.setText(nuevo.getLink());
         Image foto = vConfiguraciones.getToolkit().getImage(vConfiguraciones.txtruta.getText());
            foto= foto.getScaledInstance(100,100,10);
            vConfiguraciones.Imagen.setIcon(new ImageIcon(foto));
        
        //vConfiguraciones.btnSalir.addActionListener(this::clickSalir);
    }
    
    public void clickGuardar(ActionEvent e){
        String empresa = vConfiguraciones.txtNombEmpresa.getText();
        String direccion = vConfiguraciones.txtDireccion.getText();
        String telefono = vConfiguraciones.txtTelefono.getText();
        String ruc = vConfiguraciones.txtruc.getText();
        String mensaje = vConfiguraciones.txtmensaje.getText();
        String logo= vConfiguraciones.txtruta.getText();
        String link = vConfiguraciones.txtboleta.getText();
        BDConfiguracion.modificar(new Empresa(1,empresa,direccion,telefono,ruc,mensaje,logo,link));
        

    }
    
    public void clickCambiar(ActionEvent e){
        File archivo;
        JFileChooser abrirImagen = new JFileChooser();
        
      abrirImagen.setFileFilter(new FileNameExtensionFilter("Extensiones de Imagen","jpg","png","jpeg"));
      
     
      int respuesta = abrirImagen.showOpenDialog(abrirImagen);
        if(respuesta ==JFileChooser.APPROVE_OPTION){
            archivo = abrirImagen.getSelectedFile();
            vConfiguraciones.txtruta.setText(archivo.getAbsolutePath());
            Image foto = vConfiguraciones.getToolkit().getImage(vConfiguraciones.txtruta.getText());
            foto= foto.getScaledInstance(100,100,10);
            vConfiguraciones.Imagen.setIcon(new ImageIcon(foto));  
        }    
    }
    public void clickUbicacion(ActionEvent e){
        File archivo;
        JFileChooser crearUbicacion = new JFileChooser();
        crearUbicacion.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //crearUbicacion.setCurrentDirectory(archivo);
        
        int respuesta = crearUbicacion.showOpenDialog(crearUbicacion);
         if(respuesta ==JFileChooser.APPROVE_OPTION){
             archivo=crearUbicacion.getSelectedFile();
             vConfiguraciones.txtboleta.setText(archivo.getAbsolutePath());
             //Agregar el txtboleta a la base de datos.
         
         }
        
        
    }
    public void clickFinalizar(ActionEvent e){
        VistaMenuPrincipal vMenu = new VistaMenuPrincipal();
        MenuPrincipalControlador cMenu = new MenuPrincipalControlador();
        cMenu.setVistaMenuPrincipal(vMenu);
        vMenu.setVisible(true);
        vConfiguraciones.dispose();
    }
}

