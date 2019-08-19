package Main;

/**
 * Clase Article, contiene dos atributos, name (nombre) y code (codigo).
 * 
 * @author Felix Aguilar Ferrer
 */
public class Article {
    
    public static final int MAX = 20; //Longitud maxima de un articulo.
    private String name;
    private int code;

    public Article(String name, int code) {
        this.name = name;
        this.code = code;
        resize();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        resize();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Articles{" + "name=" + name + ", code=" + code + '}';
    }
    
    /**
     * Canbia la longitud. El nombre tendra una longitud de 20 caracteres, esto
     * lo hace añadiendo espacios en blanco hasta llegar a 20 o bien recortando 
     * el string a una logitud de 20.
     *
     * @return 
     */
    private void resize(){
        String name = this.name;
        while(name.length() < MAX){
            name = name + " ";
        }
        if(name.length() > MAX){
            name = name.substring(0, MAX);
        }
        this.name = name;
    }
}
