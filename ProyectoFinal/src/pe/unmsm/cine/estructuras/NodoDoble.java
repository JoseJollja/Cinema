/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.unmsm.cine.estructuras;

/**
 *
 * @author Jollja
 */
public class NodoDoble<T> {
    NodoDoble<T> sig;
    NodoDoble<T> ant;
    T dato;

    public NodoDoble(T dato) {
        this.dato = dato;
    }
  
}
