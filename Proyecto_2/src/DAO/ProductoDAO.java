package DAO;

import MODEL.Producto;
import org.json.simple.*;
import org.json.simple.parser.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author deivi
 */
public class ProductoDAO {

    public void guardarProducto(String subcategoria, Producto producto) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject;

            try (FileReader reader = new FileReader("productos.json")) {
                jsonObject = (JSONObject) parser.parse(reader);
            }

            JSONArray subcategoriaArray = (JSONArray) jsonObject.get(subcategoria);
            if (subcategoriaArray == null) {
                subcategoriaArray = new JSONArray();
                jsonObject.put(subcategoria, subcategoriaArray);
            }

            JSONObject productoJSON = new JSONObject();
            productoJSON.put("id", producto.getIdProducto());
            productoJSON.put("nombre", producto.getNombre());
            productoJSON.put("precio", producto.getPrecio());
            productoJSON.put("peso", producto.getPeso());
            productoJSON.put("id_proveedor", producto.getIdProveedor());
            productoJSON.put("id_categoria", producto.getIdCategoria());
            productoJSON.put("id_marca", producto.getIdMarca());

            subcategoriaArray.add(productoJSON);

            try (FileWriter fileWriter = new FileWriter("productos.json")) {
                fileWriter.write(jsonObject.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla, String subcategoria) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject;

            try (FileReader reader = new FileReader("productos.json")) {
                jsonObject = (JSONObject) parser.parse(reader);
            }

            JSONArray subcategoriaArray = (JSONArray) jsonObject.get(subcategoria);
            if (subcategoriaArray != null) {
                modeloTabla.setRowCount(0);

                for (Object obj : subcategoriaArray) {
                    JSONObject productoJSON = (JSONObject) obj;
                    String id = productoJSON.get("id").toString();
                    String nombre = productoJSON.get("nombre").toString();
                    double precio = Double.parseDouble(productoJSON.get("precio").toString());
                    String peso = productoJSON.get("peso").toString();
                    String idProveedor = productoJSON.get("id_proveedor").toString();
                    String idCategoria = productoJSON.get("id_categoria").toString();
                    String idMarca = productoJSON.get("id_marca").toString();

                    modeloTabla.addRow(new Object[]{id, nombre, precio, peso, idProveedor, idCategoria, idMarca});
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(String subcategoria, String idProductoAEliminar) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject;

            try (FileReader reader = new FileReader("productos.json")) {
                jsonObject = (JSONObject) parser.parse(reader);
            }

            JSONArray subcategoriaArray = (JSONArray) jsonObject.get(subcategoria);
            if (subcategoriaArray != null) {
                List<Integer> indicesAEliminar = new ArrayList<>();
                for (int i = 0; i < subcategoriaArray.size(); i++) {
                    JSONObject productoJSON = (JSONObject) subcategoriaArray.get(i);
                    String productoId = productoJSON.get("id").toString();
                    if (productoId.equals(idProductoAEliminar)) {
                        indicesAEliminar.add(i);
                    }
                }

                for (int i : indicesAEliminar) {
                    subcategoriaArray.remove(i);
                }
                try (FileWriter fileWriter = new FileWriter("productos.json")) {
                    fileWriter.write(jsonObject.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void editarProducto(String subcategoria, Producto producto) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject;

            try (FileReader reader = new FileReader("productos.json")) {
                jsonObject = (JSONObject) parser.parse(reader);
            }

            JSONArray subcategoriaArray = (JSONArray) jsonObject.get(subcategoria);
            for (int i = 0; i < subcategoriaArray.size(); i++) {
                JSONObject productoJSON = (JSONObject) subcategoriaArray.get(i);
                String productoId = productoJSON.get("id").toString();
                if (productoId.equals(producto.getIdProducto())) {
                    if (!producto.getNombre().isEmpty()) {
                        productoJSON.put("nombre", producto.getNombre());
                    }
                    if (producto.getPrecio() > 0) {
                        productoJSON.put("precio", producto.getPrecio());
                    }
                    if (!producto.getPeso().isEmpty()) {
                        productoJSON.put("peso", producto.getPeso());
                    }
                    if (!producto.getIdProveedor().isEmpty()) {
                        productoJSON.put("id_proveedor", producto.getIdProveedor());
                    }
                    if (!producto.getIdCategoria().isEmpty()) {
                        productoJSON.put("id_categoria", producto.getIdCategoria());
                    }
                    if (!producto.getIdMarca().isEmpty()) {
                        productoJSON.put("id_marca", producto.getIdMarca());
                    }
                    break;
                }
            }
            try (FileWriter fileWriter = new FileWriter("productos.json")) {
                fileWriter.write(jsonObject.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
