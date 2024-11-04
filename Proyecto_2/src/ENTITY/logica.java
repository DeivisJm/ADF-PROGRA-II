package ENTITY;

import GUI.*;
import DAO.DB;

/**
 *
 * @author deivi
 */

public class logica {

    public static void main(String[] args) {
        DB dbConnection = new DB();

        // Verifica si la conexión se realizó correctamente
        if (dbConnection.getConnection() != null) {
            System.out.println("Conexion a la base de datos con exito.");
        } else {
            System.out.println("Error al establecer la conexion a la base de datos.");
            return;
        }

        // Mostrar el formulario de inicio de sesión
        Login log = new Login();
        log.setVisible(true);
        log.setResizable(false);
        log.setLocationRelativeTo(null);

    }
}
