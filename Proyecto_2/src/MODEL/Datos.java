package MODEL;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class Datos {

    private String rutaProductos;

    public Datos() {
        this.rutaProductos = "productos.json";
    }

    /*this method id for charging the products and we are using a list for charging the list better
    using the json we are reading the file that rutaProductos have and with the json object we are adding 
    the objects that productos file have.
     */
    public List<Producto> cargarProductos(String categoria) {
        ArrayList<Producto> infoProductos = new ArrayList();

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
                infoProductos.add(new Producto(nombre, precio, peso, imagen));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return infoProductos;

    }
}
