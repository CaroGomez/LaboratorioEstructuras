/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import ArbolB.ArbolB;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import java.util.List;

/**
 *
 * @author Carolina
 */
public interface TurnoDAO {
    
    public List<Turno> listarTurnos();
    public Turno consultarTurno(String codigo);
    public boolean guardarTurno(Turno turno);
    public ArbolB retornarArbol();
    
}
