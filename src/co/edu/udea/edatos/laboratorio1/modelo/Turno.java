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
public class Turno {

    private String codigo;
    private String horario;
    private String horas;
    private String placaTaxi;

    private List<Turno> turnos;

    public Turno() {
    }

    public Turno(String codigo, String horario, String horas, String placaTaxi) {
        this.codigo = codigo;
        this.horario = horario;
        this.horas = horas;
        this.placaTaxi = placaTaxi;
        
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getPlacaTaxi() {
        return placaTaxi;
    }

    public void setPlacaTaxi(String placaTaxi) {
        this.placaTaxi = placaTaxi;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return codigo + " " + horario + " " + horas + " " + placaTaxi;
    }

    
}
