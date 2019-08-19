package Exceptions;

/**
 * Excepcion. Se produce si el usuario introduce un valor no valido por teclado.
 * 
 * @author Felix Aguilar Ferrer
 */
public class InputException extends Exception {
    public InputException(){
        super("Introduccion de datos no valida.");
    }
}
