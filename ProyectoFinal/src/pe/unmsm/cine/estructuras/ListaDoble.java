package pe.unmsm.cine.estructuras;

import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *  cAMBIAR NOMBERE A PAQUTES A pe.unmsm.sistemas...
 *  Lists dobles en controlador no en vista
 *  llenar tabla, etc en vista
 *  en service validaciones de fondo.Ejm si ya existe un producto con codigo repétido en la base de datos
 * Añadir fecha de caducidad para meter pilas
 * Constantes en paquete util y renombrarlo a util
 * Guardar imagenes como BlopB
 * Una clase no termina en impl
 * Usar el getconexion y no hacer la conexion a cada rato
 *  
 */
public class ListaDoble<T> implements Iterable<T> {

    private NodoDoble<T> cabecera;
    private NodoDoble<T> ultimo;
    Comparator<T> comparador;
    int tam = 0;

    @Override
    public Iterator<T> iterator() {
        return new MiIteradorAscendente();
    }

    private class NodoDoble<T> {

        T dato;
        NodoDoble<T> sig, ant;

        NodoDoble(T dato) {
            this.dato = dato;
            sig = null;
            ant = null;
        }
    }

    public NodoDoble<T> getCabecera() {
        return cabecera;
    }

    public void setCabecera(NodoDoble<T> cabecera) {
        this.cabecera = cabecera;
    }

    public Comparator<T> getComparador() {
        return comparador;
    }

    public void setComparador(Comparator<T> comparador) {
        this.comparador = comparador;
    }

    
    private class MiIteradorAscendente implements Iterator<T> {

        NodoDoble<T> aux;

        public MiIteradorAscendente() {
            aux = cabecera;
        }

        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            T datoG = aux.dato;
            aux = aux.sig;
            return datoG;
        }

    }

    /*
        Recorre la lista comenzando del ultimo elemento y finalizando en el primero
     */
    private class MiIteradorDescendente implements Iterator<T> {

        NodoDoble<T> aux;

        public MiIteradorDescendente() {
            aux = ultimo;
        }

        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            T datoG = aux.dato;
            aux = aux.ant;
            return datoG;
        }

    }

    public void insertarAlInicio(T nuevo) {
        NodoDoble<T> nuevoNodo = new NodoDoble<>(nuevo);
        nuevoNodo.sig = cabecera;

        if (cabecera != null) {
            cabecera.ant = nuevoNodo;
        } else {
            ultimo = nuevoNodo;
        }
        cabecera = nuevoNodo;
        tam++;
    }

    public void insertarAlFinal(T nuevo) {
        NodoDoble<T> nuevoNodo = new NodoDoble<>(nuevo);
        nuevoNodo.ant = ultimo;

        if (cabecera != null) {
            ultimo.sig = nuevoNodo;
        } else {
            cabecera = nuevoNodo;
        }
        ultimo = nuevoNodo;
        tam++;
    }

    @Override
    public String toString() {
        String cadena = "";
        NodoDoble<T> aux = cabecera;
        while (aux != null) {
            cadena += aux.dato + ", ";
            aux = aux.sig;
        }
        return cadena;
    }

    private void intercambiar(NodoDoble<T> i, NodoDoble<T> j) {

        T aux = i.dato;
        i.dato = j.dato;
        j.dato = aux;
    }

    /*
    Metodo que ordena la lista doble usando el metodo QuickSort
     */
    public void ordenar() {
        reducirQuick(cabecera, ultimo);
    }

    private void reducirQuick(NodoDoble inicio, NodoDoble fin) {
        NodoDoble izq = inicio;
        NodoDoble der = fin;
        NodoDoble pivote = inicio;

        while (izq != der) {
            while (izq != pivote && comparador.compare((T) izq.dato, (T) pivote.dato) < 0) {
                izq = izq.sig;
            }
            while (der != pivote && comparador.compare((T) izq.dato, (T) pivote.dato) > 0) {
                der = der.ant;
            }
            intercambiar(izq, der);

            if (pivote == izq) {
                pivote = der;
            } else if (pivote == der) {
                pivote = izq;
            }
        }

        if (inicio != pivote) {
            reducirQuick(inicio, pivote.ant);
        }
        if (fin != pivote) {
            reducirQuick(pivote.sig, fin);
        }
    }

    public int getTam() {
        return tam;
    }

    public T get(int pos) {
        if (cabecera != null && pos >= 0 && pos < tam) {

            NodoDoble<T> aux = cabecera;
            int cont = 0;
            while (cont != pos && aux != null) {
                cont++;
                aux = aux.sig;
            }
            return aux.dato;

        } else {
            System.out.println("ERROE en metodo get de ListaDoble. Cabecera es null, o la posicion a eliminar esta fuera de rango");
            return null;
        }
    }

    public void eliminar(int pos) {//pos inicia de 0

        if (cabecera != null && pos >= 0 && pos < tam) {
            
            if (pos == 0) {                     //eliminar primer elemento
                if (tam != 1) {                 //si tiene mas de un elemento
                    cabecera = cabecera.sig;
                    cabecera.ant.sig = null;
                    cabecera.ant = null;
                } else {                        //si tiene 1 elemento
                    cabecera = null;
                }
            } else if (pos == tam - 1) {        //si se quiere eliminar el ultimo elemento
                ultimo = ultimo.ant;
                ultimo.sig.ant = null;
                ultimo.sig = null;
            } else {                            //eliminar elemento intermedio

                NodoDoble<T> aux = cabecera;
                int cont = 0;
                while (cont != pos && aux != null) {
                    cont++;
                    aux = aux.sig;
                }

                aux.ant.sig = aux.sig;
                aux.sig.ant = aux.ant;
                aux.ant = null;
                aux.sig = null;

            }
            tam--;
        } else {
            System.out.println("Cabecera es null, o la posicion a eliminar esta fuera de rango");
        }
    }
    
    /*
        Devuelve una sub lista doble donde cada elemento de esta cumple el requisito
        especificado como argumento. IMPORTANTE! Es solo una sublista con
        referencias a los objetos originales. Cualquier cambio en esta sublista
        afecta a la otra.
    */
    public ListaDoble<T> filtrar(Predicate<T> predicado){
        ListaDoble<T> nuevaLista = new ListaDoble<>();
        NodoDoble<T> aux = cabecera;
                
        while(aux!=null){
            if(predicado.test(aux.dato)){
                nuevaLista.insertarAlFinal(aux.dato);
            }
            aux=aux.sig;
        }
        
        return nuevaLista;
    }
    
    /*
        Busca y retorna un elemento de la lista doble que tiene como llave el
        valor especificado como primer parametro, el segundo parametro es la
        funcion que aplicada al generico del que esta conformado la lista doble
        resulta ser la llave de ese objeto.
        Ejm: buscar(1,Producto::getCodigo). Busca el producto de codigo 1
    */
    public <K> T buscar(K key, Function<T,K> funcion){
        NodoDoble<T> aux = cabecera;
                
        while(aux!=null){
           if(key.equals(funcion.apply(aux.dato))){
               return aux.dato;
           }
            aux=aux.sig;
        }
        
        return null;
    }
    
    /*
    Imitacion del forEach de stream
    */
    public void paraCada(Consumer<T> accion){
        NodoDoble<T> aux = cabecera;
                
        while(aux!=null){
            accion.accept(aux.dato);
            aux=aux.sig;
        }
    }
    
    public int size(){
        return tam;
    }
    
    public Stream<T> stream() {
        Iterator<T> iterator = iterator();
        Iterable<T> iterable = () -> iterator;
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
