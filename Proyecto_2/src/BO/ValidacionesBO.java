package BO;

import java.util.regex.*;

/**
 *
 * @author deivis
 */
public class ValidacionesBO {

    public boolean validarLetras(String valor) {
        //Pattern patron = Pattern.compile("[a­zA­Z]{5,10}");
        Pattern patron = Pattern.compile("^[a-zA-ZñÑáéíóúÁÉÍÓÚ]+$");
        Matcher m = patron.matcher(valor);
        return m.matches();
    }

    public boolean validarNumeros(String valor) {
        Pattern patron = Pattern.compile("[0-9]*");
        Matcher m = patron.matcher(valor);
        return m.matches();
    }

}
