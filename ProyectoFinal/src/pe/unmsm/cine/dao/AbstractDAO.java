package pe.unmsm.cine.dao;

import pe.unmsm.cine.estructuras.ListaDoble;
import java.util.ArrayList;


/**
 * E: tipo de dato Producto,Categoria
 * K: tipo de dato de valor buscados
 */
public interface AbstractDAO<E, K> {
    
    boolean registrar(E e);
    boolean modificar(E e);
    E get(K id);
    ListaDoble<E> getAll();
    boolean eliminar(K id);

}
