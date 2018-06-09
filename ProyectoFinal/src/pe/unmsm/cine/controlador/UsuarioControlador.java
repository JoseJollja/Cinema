/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.controlador;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import pe.unmsm.cine.dao.UsuarioDAO;
import pe.unmsm.cine.entidades.Usuario;
import pe.unmsm.cine.estructuras.ListaDoble;
import pe.unmsm.cine.gui.VistaMenuPrincipal;
import pe.unmsm.cine.gui.VistaUsuarios;

/**
 *
 * @author Jollja
 */
public class UsuarioControlador {
    VistaUsuarios vUsuario;
    UsuarioDAO BDUsuario = new UsuarioDAO();
    ListaDoble <Usuario> lista = new ListaDoble<>();
    
    
    public void setVistaHabitacion(VistaUsuarios vUsuario){
        this.vUsuario = vUsuario;
        vUsuario.botonAgregar.addActionListener(this::agregarUsuario);
        vUsuario.botonModificar.addActionListener(this::modificarUsuario);
        vUsuario.botonEliminar.addActionListener(this::eliminarUsuario);
        vUsuario.botonConsultar.addActionListener(this::consultar);
        vUsuario.botonFinalizar.addActionListener(this::finalizar);
        lista = BDUsuario.getAll();
        llenarTabla(lista);
    }
    
    public void agregarUsuario(ActionEvent e){
        String nombre = vUsuario.txtnombre.getText();
        String apellido = vUsuario.txtapellidos.getText();
        String telefono = vUsuario.txttelefono.getText();
        String id = vUsuario.txtid.getText();
        String contraseña = vUsuario.txtcontraseña.getText();
        
        BDUsuario.registrar(new Usuario(nombre,apellido,telefono,id,contraseña));
        lista = BDUsuario.getAll();
        llenarTabla(lista);
        vUsuario.txtnombre.setText("");
        vUsuario.txtapellidos.setText("");
        vUsuario.txttelefono.setText("");
        vUsuario.txtid.setText("");
        vUsuario.txtcontraseña.setText("");
    }
    
    public void llenarTabla(ListaDoble<Usuario> lista){
        String titulos[] = {"CODIGO","NOMBRES", "APELLIDOS", "TELEFONO", "ID"};
        DefaultTableModel modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        
        modelo.setRowCount(0);
        
        Iterator<Usuario> iterador = lista.iterator();
        
        while(iterador.hasNext()){
            Usuario usuario = iterador.next();
            modelo.addRow(new Object[]{usuario.getCodigo(),usuario.getNombres(),usuario.getApellidos(),
                                       usuario.getTelefono(),usuario.getId()});
        }
        vUsuario.jTablaResultados.setModel(modelo);
    }
    
    public void modificarUsuario(ActionEvent e){
        TableModel tableModel = vUsuario.jTablaResultados.getModel();
        int fils = tableModel.getRowCount();
        
        for(int i=0; i<fils; i++) { 
            Usuario usu = lista.get(i);
            
            String nombre = String.valueOf(tableModel.getValueAt(i,0));
            String apellido = String.valueOf(tableModel.getValueAt(i,1));
            String telefono = String.valueOf(tableModel.getValueAt(i,2));
            String id = String.valueOf(tableModel.getValueAt(i,3));
            String clave = String.valueOf(tableModel.getValueAt(i,4));
            
            usu.setNombres(nombre);
            usu.setApellidos(apellido);
            usu.setTelefono(telefono);
            usu.setId(id);
            usu.setContraseña(clave);
            
            BDUsuario.modificar(usu);
        }
    }
    
    public void eliminarUsuario(ActionEvent e){
        int filaSelect = vUsuario.jTablaResultados.getSelectedRow();
        if(filaSelect >= 0){
            Usuario usuario = lista.get(filaSelect);
            int cod = usuario.getCodigo();
            BDUsuario.eliminar(cod);
            lista = BDUsuario.getAll();
            llenarTabla(lista);
        }
        else{
            JOptionPane.showMessageDialog(null, "Fila no seleccionada");
        }
    }
    
    public void consultar(ActionEvent e){
        llenarTabla(lista);
    }
    
    public void finalizar(ActionEvent e){
        VistaMenuPrincipal vMenu = new VistaMenuPrincipal();
        MenuPrincipalControlador cMenu = new MenuPrincipalControlador();
        cMenu.setVistaMenuPrincipal(vMenu);
        vMenu.setVisible(true);
        vUsuario.dispose();
    }
    
}

