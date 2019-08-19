package Exceptions;

/**
 * Excepcion. Se produce si se intenta realizar un pop y no hay articulos en 
 * la pila.
 * 
 * @author Felix Aguilar Ferrer
 */
public class EmptyException extends Exception {
    public EmptyException(){
        super("La pila esta vacia.");
    }
}
