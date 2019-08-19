package Main;

import Exceptions.ExistsException;
import Exceptions.EmptyException;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Uso de la pila. Contiene todos los metodos de acceso al fichero utilizado
 * para la pila.
 * 
 * @author Felix Aguilar Ferrer
 */
public class Stack {
    
    // Bytes de un articulo (40 bytes = nombre, 4 bytes = codigo).
    private static final int SIZEART = (Article.MAX * 2) + 4;
    private int TOP; // Indica cuantos registros hay en la pila.
    
    private File f;
    private RandomAccessFile raf;

    /**
     * Constructor de la pila. Prepara para generar un archivo nuevo para 
     * almacenar la pila e inicializa el contador de registros en la pila.
     * 
     * @param file 
     */
    public Stack(String file){
        f  = new File(file);
        f.deleteOnExit(); // Elimina el archivo al finalizar el uso de la clase.
        TOP = 0; // La pila siempre comienza en 0 articulos.
    }
    
    /**
     * Guarda un articulo. Si no existe un articulo que contenga el mismo
     * codigo, entonces escribira el articulo en la cima de la pila y movera el
     * puntero de la pila en uno hacia arriba.
     * 
     * @param a
     * @throws IOException
     * @throws ExistsException
     */
    public void push(Article a) throws IOException, ExistsException{
        
        raf = new RandomAccessFile(f, "rw");
        
        // Mientras haya articulos en la pila.
        while (raf.getFilePointer() < (TOP * SIZEART)){
            
            // Saltamos la lectura del nombre del articulo.
            raf.seek(raf.getFilePointer() + (Article.MAX * 2));
            
            // Leemos el codigo y lo comparamos con el introducido.
            int code = raf.readInt();
            if (a.getCode() == code){
                
                // Si existe un articulo en la pila con el mismo codigo se
                // devuelve una excepcion.
                throw new ExistsException();
            }
        }
        
        // Escribe el articulo en la pila.
        raf.writeChars(a.getName());
        raf.writeInt(a.getCode());       
        raf.close();
        
        // Augmenta el numero de articulos en la pila en 1.
        TOP++;
    }
    
    /**
     * Devuelve un articulo. Si hay articulos en la pila, este devolvera el
     * articulo que se encuentre en la cima de la pila y movera el puntero de la
     * pila en uno hacia abajo.
     * 
     * @return
     * @throws IOException
     * @throws EmptyException 
     */
    public Article pop() throws IOException, EmptyException{
        
        // Si la pila esta vacia devuelve una excepcion.
        if (TOP != 0){
            
            raf = new RandomAccessFile(f, "r");
            
            // Ponemos el puntero antes de la posicion mas alta de la pila.
            raf.seek((TOP-1) * SIZEART);
            
            // Generamos un articulo con el nombre y el codigo del ultimo 
            // articulo en la pila para devolverlo.
            String name = "";
            for (int i = 0;  i < Article.MAX; i++){
                name = name + raf.readChar();
            }
            int code = raf.readInt();
            Article a = new Article(name, code);
            
            // Disminuye en el numero de articulos en la pila en 1.
            TOP--;
            
            raf.close();
            return a;
        }
        throw new EmptyException();  
    }
    
    /**
     * Devuelve un String. Contiene todos los articulos activos en la pila.
     * 
     * @return
     * @throws IOException 
     * @throws EmptyException 
     */
    public String show() throws IOException, EmptyException{
        
        //Si la pila esta vacia devuelve una excepción.
        if (TOP != 0){
            raf = new RandomAccessFile(f, "r");
            String s  = "";

            // Recorremos todos los articulos activos en la pila, generando un
            // articulo por cada uno de ellos y los guardamos en un String.
            while (raf.getFilePointer() < (TOP * SIZEART)){
                String name = "";
                for (int i = 0; i < Article.MAX; i++){
                    name = name + raf.readChar();
                }
                int code = raf.readInt();
                Article a = new Article(name, code);

                // Se guarda un articulo en cada linea.
                s = s + a.toString() + "\n";
            }

            return s;
        }
        throw new EmptyException();
    }
}
