package co.edu.udea.edatos.laboratorio1.modelo.dao;

import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.archivos.dao.impl.FileConductoresDAO;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;

import java.util.List;

public class DAOTest {

    public static void main(String []args) {
        ConductoresDAO conductoresDAO =new FileConductoresDAO();
        Conductor c = new Conductor("123", "jose", "lopez", 'M', "35", "312891512");

        /*try{
            personaDAO.guardarPersona(p);
        }catch (LlaveDuplicadaException lde){
            lde.printStackTrace();
        }*/

        /*List<Persona> personas = personaDAO.listarPersonas();
        personas.forEach(System.out::println);
        personas.forEach(System.out::println);*/

        c=conductoresDAO.consultarConductorxId("123");
        System.out.println(c);

        c=conductoresDAO.consultarConductorxId("123");
        System.out.println(c);

    }
}
