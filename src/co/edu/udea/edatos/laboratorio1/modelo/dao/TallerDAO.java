/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.Taller;
import java.util.List;

/**
 *
 * @author Carolina
 */
public interface TallerDAO {
    
    public List<Taller> listarTalleres();
    public Taller consultarTaller(String codigo);
    public void guardarTaller(Taller taller) throws LlaveDuplicadaException;
    
}
