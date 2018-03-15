package co.edu.udea.edatos.laboratorio1.modelo.dao;

import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;

import java.util.List;

public class DAOTest {

    public static void main(String []args) {
        ConductorDAO conductorDAO =new FileConductorDAO();
        Conductor c = new Conductor("123", "jose", "lopez", 'M', "35", "312891512");

        /*try{
            personaDAO.guardarPersona(p);
        }catch (LlaveDuplicadaException lde){
            lde.printStackTrace();
        }*/

        /*List<Persona> personas = personaDAO.listarPersonas();
        personas.forEach(System.out::println);
        personas.forEach(System.out::println);*/

        c=conductorDAO.consultarConductorxId("1125412540");
        System.out.println(c);

        c=conductorDAO.consultarConductorxId("123");
        System.out.println(c);

    }
}
