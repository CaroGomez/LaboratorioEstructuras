package co.edu.udea.edatos.laboratorio1.modelo.dao;

import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;

import java.util.List;

public class DAOTest {

    public static void main(String []args) {
        ConductorDAO conductorDAO =new FileConductorDAO();
        Conductor c = new Conductor("15987", "Juana", "Carlos", 'F', "50", "3128915125","000045");

        try{
            conductorDAO.guardarConductor(c);
        }catch (LlaveDuplicadaException lde){
            lde.printStackTrace();
        }

        List<Conductor> personas = conductorDAO.listarConductores();
        personas.forEach(System.out::println);
        //personas.forEach(System.out::println);

        c=conductorDAO.consultarConductorxId("123");
        System.out.println(c);

        c=conductorDAO.consultarConductorxId("123452");
        System.out.println(c);

    }
}
