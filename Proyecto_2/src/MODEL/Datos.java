package MODEL;

import GUI.Administrador;
import GUI.Usuario;
import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Datos {

    private String rutaProductos;

    private Administrador tico;
    private Usuario tico2;

    private int id;
    private String cédula;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String teléfono;
    private String correo;

    public Datos(Administrador tico) {
        this.tico = tico;
    }

    public Datos(Usuario tico2) {
        this.tico2 = tico2;
    }

    public Datos() {
        this.rutaProductos = "productos.json";
    }
    

    /* 
     * Este método es para cargar los productos. 
     * Estamos usando una lista para cargar la lista de forma más eficiente.
     * Usando JSON, leemos el archivo que contiene la rutaProductos 
     * y con el objeto JSON, agregamos los objetos que tiene el archivo productos.
     */
    public List<Producto> cargarProductos(String categoria) {
        ArrayList<Producto> infoProductos = new ArrayList<>();

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(this.rutaProductos)) {
            JSONObject main = (JSONObject) parser.parse(reader);
            JSONArray categoriaProductos = (JSONArray) main.get(categoria);

            for (Object categoriaProducto : categoriaProductos) {
                JSONObject producto = (JSONObject) categoriaProducto;
                String nombre = (String) producto.get("nombre");
                String peso = (String) producto.get("peso");
                double precio = (double) producto.get("precio");
                String imagen = (String) producto.get("imagen");
                int cantidad = ((Long) producto.get("cantidad")).intValue();

                infoProductos.add(new Producto(nombre, precio, peso, imagen, cantidad));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return infoProductos;
    }
}
