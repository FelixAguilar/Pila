package Main;

import Exceptions.EmptyException;
import Exceptions.ExistsException;
import Exceptions.InputException;

import Colors.ConsoleColors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Felix Aguilar Ferrer
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        boolean exit = false; // Para salir del menu.
        Stack stack = new Stack("Stack.dat"); // Definimos la pila.
        Article a; // Definimos un articulo para realizar operaciones.
        
        while (!exit){
            try {
                
                // Menu del programa y entrada de la opcion por teclado.
                System.out.println("1 - Push");
                System.out.println("2 - Pop");
                System.out.println("3 - Mostrar");
                System.out.println("0 - Salir");
                System.out.print("Introduce la operacion a realizar: ");
                String action = keyboard();
                System.out.println("");
                
                // Selector de accion a realizar.
                switch (action){
                    case "0": // Salir del programa.
                        
                        exit = true;
                        
                        break;
                        
                    case "1": // Introducir un articulo en la pila.
                        
                        // Introduccion de un articulo.
                        System.out.println("Introduce el nombre:");
                        String name = keyboard();
                        System.out.println("Introduce el codigo:");
                        int code = Integer.parseInt(keyboard());
                        a = new Article(name, code);
                        System.out.println("");
                        
                        // Realiza un push a la pila.
                        try{
                            stack.push(a);
                        } catch (IOException | ExistsException ex) {
                            System.out.println(ConsoleColors.RED + "Excepcion: "
                                + ex.getMessage() + ConsoleColors.RESET + "\n");
                        }
                        
                        break;
                        
                    case "2": // Obtener un articulo en la pila.
                        
                        // Realiza un pop a la pila.
                        try {
                            a = stack.pop();
                            System.out.println(a.toString() + "\n");
                        } catch (IOException | EmptyException ex) {
                            System.out.println(ConsoleColors.RED + "Excepcion: "
                                + ex.getMessage() + ConsoleColors.RESET + "\n");
                        }
                        break;
                        
                    case "3": // Mostrar el contenido de la pila.
                        
                        // Imprime por pantalla todos los articulos activos en
                        // la pila.
                        try {
                            System.out.println(stack.show());
                        } catch (IOException | EmptyException ex) {
                            System.out.println(ConsoleColors.RED + "Excepcion: "
                                + ex.getMessage() + ConsoleColors.RESET + "\n");
                        }
                        break;
                        
                    default: // Si se introduce un valor no valido.
                        throw new InputException();
                }
            } catch (InputException ex) {
                System.out.println(ConsoleColors.RED + "Excepcion: " 
                        + ex.getMessage() + ConsoleColors.RESET + "\n");
            }
        }
        
    }
    
    /**
     * Devuelve un String introducido por teclado.
     * 
     * @return 
     */
    public static String keyboard() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = "";
        try {
            text = br.readLine();
        } catch (IOException ex) {
            System.out.println(ConsoleColors.RED + "Excepcion: " 
                    + ex.getMessage() + ConsoleColors.RESET + "\n");
        }
        return text;
    }
    
}
