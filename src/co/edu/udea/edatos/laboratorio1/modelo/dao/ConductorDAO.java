/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import ArbolB.ArbolB;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import java.util.List;

/**
 *
 * @author Carolina
 */
public interface ConductorDAO {

    public List<Conductor> listarConductores();
    public ArbolB CrearArbol();

    public Conductor consultarConductorxId(String identificacion);

    public boolean guardarConductor(Conductor conductor); 

}
