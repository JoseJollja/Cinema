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
public class Empresa {
    private int cod;
    private String nombre;
    private String direccion;
    private String telefono;
    private String ruc;
    private String mensaje;
    private String logo;
    private String link;

    public Empresa(String nombre, String direccion, String telefono, String ruc, String mensaje,String logo,String link) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ruc = ruc;
        this.mensaje = mensaje;
        this.logo=logo;
        this.link = link;
    }

    public Empresa(int cod, String nombre, String direccion, String telefono, String ruc, String mensaje,String logo,String link) {
        this.cod = cod;             
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ruc = ruc;
        this.mensaje = mensaje;
        this.logo = logo;
        this.link = link;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getLogo(){
        return logo;
    }
    public void setLogo(String logo){
        this.logo=logo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    @Override
    public String toString() {
        return "Empresa{" + "cod=" + cod + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono=" + telefono + ", ruc=" + ruc + ", mensaje=" + mensaje + '}';
    }
    
    
}
