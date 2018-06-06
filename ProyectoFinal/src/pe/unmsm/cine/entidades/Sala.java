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
public class Sala {
    private int codigo;
    private int nroSala;
    private int cantMaxPersonas;
    private double precio;
    private String tipo;
    private boolean estado;

    public Sala(int nroSala, int cantMaxPersonas, double precio, String tipo, boolean estado) {

        this.nroSala = nroSala;
        this.cantMaxPersonas = cantMaxPersonas;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;
    }

    public Sala(int codigo, int nroSala, int cantMaxPersonas, double precio, String tipo, boolean estado) {
        this.codigo = codigo;
        this.nroSala = nroSala;
        this.cantMaxPersonas = cantMaxPersonas;
        this.precio = precio;
        this.tipo = tipo;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getNroSala() {
        return nroSala;
    }

    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    public int getCantMaxPersonas() {
        return cantMaxPersonas;
    }

    public void setCantMaxPersonas(int cantMaxPersonas) {
        this.cantMaxPersonas = cantMaxPersonas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Habitacion{" + "codigo=" + codigo + ", nroHabitacion=" + nroSala + ", cantMaxPersonas=" + cantMaxPersonas + ", precio=" + precio + ", tipo=" + tipo + ", estado=" + estado + '}';
    }
    
}
