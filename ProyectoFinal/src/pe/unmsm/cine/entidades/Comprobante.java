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
public class Comprobante {
    private int codComprobante;
    private int codAsientos;
    private int codUsuario;
    private int codConfig;
    private double costo;

    public Comprobante(int codComprobante, int codAsientos, int codUsuario, int codConfig, double costo) {
        this.codComprobante = codComprobante;
        this.codAsientos = codAsientos;
        this.codUsuario = codUsuario;
        this.codConfig = codConfig;
        this.costo = costo;
    }
    public int getCodComprobante() {
        return codComprobante;
    }

    public int getCodAsientos() {
        return codAsientos;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public int getCodConfig() {
        return codConfig;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }
}
