package DAO;

import MODEL.Compra;
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
public class CompraDAO {

    public void guardarCompra(Compra compra) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray comprasArray = (JSONArray) parser.parse(new FileReader("compra.json"));

            JSONObject compraJSON = new JSONObject();
            compraJSON.put("id", compra.getId());
            compraJSON.put("fecha", compra.getFecha());
            compraJSON.put("montoTotal", compra.getMontoTotal());
            compraJSON.put("id_cliente", compra.getId_cliente());
            compraJSON.put("id_detallecompra", compra.getId_detalleCompra());

            comprasArray.add(compraJSON);

            try (FileWriter fileWriter = new FileWriter("compra.json")) {
                fileWriter.write(comprasArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray comprasArray = (JSONArray) parser.parse(new FileReader("compra.json"));

            modeloTabla.setRowCount(0);

            for (Object obj : comprasArray) {
                JSONObject compraJSON = (JSONObject) obj;
                int idCompra = Integer.parseInt(compraJSON.get("id").toString());
                if (idCompra != 0) {
                    String fecha = compraJSON.getOrDefault("fecha", "").toString();
                    double montoTotal = Double.parseDouble(compraJSON.get("montoTotal").toString());
                    int idCliente = Integer.parseInt(compraJSON.get("id_cliente").toString());
                    int idDetalleCompra = Integer.parseInt(compraJSON.get("id_detallecompra").toString());
                    modeloTabla.addRow(new Object[]{idCompra, fecha, montoTotal, idCliente, idDetalleCompra});
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void editarCompra(int idCompra, String fecha, double montoTotal, int idCliente, int idDetalleCompra) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray comprasArray = (JSONArray) parser.parse(new FileReader("compra.json"));

            for (Object obj : comprasArray) {
                JSONObject compraJSON = (JSONObject) obj;
                int compraId = Integer.parseInt(compraJSON.get("id").toString());
                if (compraId == idCompra) {
                    if (!fecha.isEmpty()) {
                        compraJSON.put("fecha", fecha);
                    }
                    if (montoTotal >= 0) {
                        compraJSON.put("montoTotal", montoTotal);
                    }
                    if (idCliente != 0) {
                        compraJSON.put("id_cliente", idCliente);
                    }
                    if (idDetalleCompra != 0) {
                        compraJSON.put("id_detallecompra", idDetalleCompra);
                    }
                    break;
                }
            }

            try (FileWriter fileWriter = new FileWriter("compra.json")) {
                fileWriter.write(comprasArray.toJSONString());
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCompra(int idCompra) {
        try {
            JSONParser parser = new JSONParser();
            JSONArray comprasArray = (JSONArray) parser.parse(new FileReader("compra.json"));

            List<Integer> indicesAEliminar = new ArrayList<>();
            boolean compraEncontrada = false;
            for (int i = 0; i < comprasArray.size(); i++) {
                JSONObject compraJSON = (JSONObject) comprasArray.get(i);
                int compraId = Integer.parseInt(compraJSON.get("id").toString());
                if (compraId == idCompra) {
                    indicesAEliminar.add(i);
                    compraEncontrada = true;
                }
            }

            if (compraEncontrada) {
                for (int i : indicesAEliminar) {
                    comprasArray.remove(i);
                }

                try (FileWriter fileWriter = new FileWriter("compra.json")) {
                    fileWriter.write(comprasArray.toJSONString());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
