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
public class Taller {
    

private String codigo;
private String nombre;
private String telefono;
private String direccion;

private List<Taller> talleres;

    public Taller() {
    }



    public Taller(String codigo, String nombre, String telefono, String direccion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
       
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Taller> getTalleres() {
        return talleres;
    }

    public void setTalleres(List<Taller> talleres) {
        this.talleres = talleres;
    }

    @Override
    public String toString() {
        return "Taller{" + "codigo=" + codigo + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", talleres=" + talleres + '}';
    }
    
    



}
