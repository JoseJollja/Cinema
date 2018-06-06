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
public class Cliente {
    private String nombre;
    private String apellido;
    private String puntos;
    
public Cliente(String nombre, String apellido, String puntos){
    this.nombre=nombre;
    this.apellido= apellido;
    this.puntos=puntos;
}
 public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellido;
    }

    public void setApellidos(String apellido) {
        this.apellido = apellido;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
    
    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", apellidos=" + apellido + ", puntos =" + puntos +  '}';
    }







}


