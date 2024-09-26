package MODEL;

import GUI.*;


public class Cliente {

    private int id;
    private String cédula;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String teléfono;
    private String correo;
    private Administrador tico;
    private Usuario tico2;

    public Cliente(Administrador tico) {
        this.tico = tico;
    }

    public Cliente(Usuario tico2) {
        this.tico2 = tico2;
    }

    public Cliente(int id, String cédula, String nombre, String primerApellido, String segundoApellido, String teléfono, String correo) {
        this.id = id;
        this.cédula = cédula;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.teléfono = teléfono;
        this.correo = correo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCédula() {
        return cédula;
    }

    public void setCédula(String cédula) {
        this.cédula = cédula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

}
