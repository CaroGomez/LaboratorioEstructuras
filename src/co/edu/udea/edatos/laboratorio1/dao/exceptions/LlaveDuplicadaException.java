package co.edu.udea.edatos.laboratorio1.dao.exceptions;

public class LlaveDuplicadaException extends Exception {

    @Override
    public String getMessage() {
        return "El archivo ya contiene un registro con la llave especificada";
    }
}
