package MODEL;

import GUI.*;

public class Marca {

    private int id;
    private String nombre;
    private Administrador tico;

    public Marca(Administrador tico) {
        this.tico = tico;
    }

    public Marca(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
