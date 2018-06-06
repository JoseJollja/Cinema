 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.entidades;

/**
 *
 * @author Jollja
 */
public class Usuario {
    
    
private int codigo;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String id;
    private String contraseña;

    public Usuario(String nombres, String apellidos, String telefono, String id, String contraseña) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.id = id;
        this.contraseña = contraseña;
    }

    public Usuario(int codigo, String nombres, String apellidos, String telefono, String id, String contraseña) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.id = id;
        this.contraseña = contraseña;
    }

    public int getCodigo() {
        return codigo;
    }
    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }   
    
}