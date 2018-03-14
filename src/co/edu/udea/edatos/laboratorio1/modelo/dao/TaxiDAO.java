/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import co.edu.udea.edatos.laboratorio1.modelo.Taxi;

/**
 *
 * @author Carolina
 */
public interface TaxiDAO {
    
    public Taxi consultarTaxi(String numero_Taxi);
    public void registrarTaxi(Taxi taxi);
    
}
