package BO;

import DAO.MarcaDAO;
import MODEL.Marca;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deivi
 */
public class MarcaBO {

    private MarcaDAO marcaDAO = new MarcaDAO();

    public void guardarMarca(Marca marca) {
        marcaDAO.guardarMarca(marca);
    }

    public void actualizarTabla(DefaultTableModel modeloTabla) {
        marcaDAO.actualizarTabla(modeloTabla);
    }

    public void editarMarca(int id, String nombre) {
        marcaDAO.editarMarca(id, nombre);
    }
}
