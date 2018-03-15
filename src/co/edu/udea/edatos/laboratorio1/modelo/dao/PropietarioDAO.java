/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;
import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import java.util.List;

/**
 *
 * @author Andres
 */
public interface PropietarioDAO {
    
    public List<Propietario> listarPropietarios();
    public Propietario consultarPropietario(String identificacion);
    public void guardarPropietario(Propietario propietario) throws LlaveDuplicadaException;
    public void eliminarPropietario(String identificacion);

    
}
