/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.udea.practica1.modelo;

import java.util.List;

/**
 *
 * @author Telecentro
 */
public class Conductor {

    private String id;
    private String nombres;
    private String apellidos;
    private String genero;
    private String edad;
    private String telefono;
    
    private List<Conductor> conductores;

    public Conductor() {
    }

    public Conductor(String id, String nombres, String apellidos, String genero, String edad, String telefono) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Conductor{" + "id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", genero=" + genero + ", edad=" + edad + ", telefono=" + telefono + '}';
    }

}
