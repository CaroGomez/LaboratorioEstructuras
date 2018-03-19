/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.modelo;

import java.util.List;

/**
 *
 * @author Telecentro
 */
public class Taxi {

    private String Placa;
    private String numero_taxi;
    private String Marca;
    private String Modelo;
    private String idPropietario;
    
    private List<Taxi> taxis;

    public Taxi() {
    }

    public Taxi(String Placa, String numero_taxi, String Marca, String Modelo, String idPropietario) {
        this.Placa = Placa;
        this.numero_taxi = numero_taxi;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.idPropietario = idPropietario;
    }

    

    public String getPlaca() {
        return Placa;
    }

    public void setPlaca(String Placa) {
        this.Placa = Placa;
    }

    public String getNumero_taxi() {
        return numero_taxi;
    }

    public void setNumero_taxi(String numero_taxi) {
        this.numero_taxi = numero_taxi;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public String getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(String idPropietario) {
        this.idPropietario = idPropietario;
    }

    public List<Taxi> getTaxis() {
        return taxis;
    }

    public void setTaxis(List<Taxi> taxis) {
        this.taxis = taxis;
    }

    @Override
    public String toString() {
        return Placa + " " + numero_taxi + " " + Marca + " " + Modelo + " " + idPropietario;
    }

      

}
