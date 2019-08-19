package Exceptions;

/**
 * Excepcion. Se produce si se intenta realizar un push y hay un articulo en 
 * la pila que tiene el mismo codigo que el articulo que se quiere guardar.
 * 
 * @author Felix Aguilar Ferrer
 */
public class ExistsException extends Exception {
    public ExistsException(){
        super("El codigo ya existe dentro de la pila.");
    }
}
