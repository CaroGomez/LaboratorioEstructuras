/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import ArbolB.ArbolB;
import co.edu.udea.edatos.laboratorio1.modelo.Taller;
import java.util.List;

/**
 *
 * @author Carolina
 */
public interface TallerDAO {
    
    public List<Taller> listarTalleres();
    public Taller consultarTaller(String codigo);
    public boolean guardarTaller(Taller taller);
    public ArbolB CrearArbol();
    
}
