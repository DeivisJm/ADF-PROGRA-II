package MODEL;

import GUI.Administrador;
import GUI.Usuario;
import com.google.gson.annotations.SerializedName;

public class Cliente {

    private int id;

    @SerializedName("cedula") // Mapea la clave del JSON a este campo
    private String cedula;

    private String nombre;

    @SerializedName("primer_apellido") // Mapea la clave del JSON
    private String primerApellido;

    @SerializedName("segundo_apellido") // Mapea la clave del JSON
    private String segundoApellido;

    @SerializedName("telefono") // Mapea la clave del JSON
    private String telefono;

    private String correo;

   

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(int id, String cedula, String nombre, String primerApellido, String segundoApellido, String telefono, String correo) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.correo = correo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
