/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import co.edu.udea.edatos.laboratorio1.modelo.Turno;

/**
 *
 * @author Carolina
 */
public interface TurnoDAO {
    
    public Turno consultarTurno(String codigo);
    public void registrarTurno(Turno turno);
    
}
