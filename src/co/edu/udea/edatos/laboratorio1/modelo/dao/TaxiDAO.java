/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo.dao;

import ArbolB.ArbolB;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import java.util.List;

/**
 *
 * @author Carolina
 */
public interface TaxiDAO {
    
    public ArbolB CrearArbol();
    public List<Taxi> listarTaxis();
    public Taxi consultarTaxi(String numero_Taxi);
    public Taxi consultarTaxixPlaca(String placa_taxi);
    public boolean guardarTaxi(Taxi taxi);
    
}
