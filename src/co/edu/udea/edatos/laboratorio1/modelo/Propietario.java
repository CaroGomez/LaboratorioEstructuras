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
public class Propietario {
    
    //Datos: identificación, nombres, apellidos, género, edad, teléfono.
    
    private String Id;
    private String nombres;
    private String apellidos;
    private char genero;
    private String edad;
    private String telefono;
    
    private List<Propietario> propietarios;

    public Propietario() {
    }
    
    
    public Propietario(String Id, String nombres, String apellidos, char genero, String edad, String telefono, List<Propietario> propietarios) {
        this.Id = Id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.telefono = telefono;
        this.propietarios = propietarios;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
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

    public List<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    @Override
    public String toString() {
        return "Propietario{" + "Id=" + Id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", genero=" + genero + ", edad=" + edad + ", telefono=" + telefono + ", propietarios=" + propietarios + '}';
    }
    
    
    
}
