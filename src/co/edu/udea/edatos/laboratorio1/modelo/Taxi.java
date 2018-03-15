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
    private Conductor idConductor;
    private Propietario idPropietario;
    private Turno codigoTurno;
    private Taller codigoTaller;
    
    private List<Taxi> taxis;

    public Taxi() {
    }

    public Taxi(String Placa, String numero_taxi, String Marca, String Modelo, Conductor idConductor, Propietario idPropietario, Turno codigoTurno, Taller codigoTaller, List<Taxi> taxis) {
        this.Placa = Placa;
        this.numero_taxi = numero_taxi;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.idConductor = idConductor;
        this.idPropietario = idPropietario;
        this.codigoTurno = codigoTurno;
        this.codigoTaller = codigoTaller;
        this.taxis = taxis;
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

    public Conductor getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(Conductor idConductor) {
        this.idConductor = idConductor;
    }

    public Propietario getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(Propietario idPropietario) {
        this.idPropietario = idPropietario;
    }

    public Turno getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(Turno codigoTurno) {
        this.codigoTurno = codigoTurno;
    }

    public Taller getCodigoTaller() {
        return codigoTaller;
    }

    public void setCodigoTaller(Taller codigoTaller) {
        this.codigoTaller = codigoTaller;
    }

    public List<Taxi> getTaxis() {
        return taxis;
    }

    public void setTaxis(List<Taxi> taxis) {
        this.taxis = taxis;
    }

    @Override
    public String toString() {
        return "Taxi{" + "Placa=" + Placa + ", numero_taxi=" + numero_taxi + ", Marca=" + Marca + ", Modelo=" + Modelo + ", idConductor=" + idConductor + ", idPropietario=" + idPropietario + ", codigoTurno=" + codigoTurno + ", codigoTaller=" + codigoTaller + ", taxis=" + taxis + '}';
    }

      

}
