/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import co.edu.udea.edatos.laboratorio1.modelo.dao.ConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TurnoDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTurnoDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carolina
 */
public class IngresoConductorController {
    
    Conductor conductor; 
    
    ConductorDAO conductorDAO = new FileConductorDAO();
    TurnoDAO turnoDAO = new FileTurnoDAO();
    
    List<Turno> turnos = turnoDAO.listarTurnos();
    ObservableList<Turno> taxiList = FXCollections.observableList(turnos);
    ObservableList<String> generoList = FXCollections.observableArrayList("Masculino", "Femenino");
    
    private Stage stagePrincipal; 

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }
    
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtNombre"
    private TextField txtNombre; // Value injected by FXMLLoader

    @FXML // fx:id="txtApellidos"
    private TextField txtApellidos; // Value injected by FXMLLoader

    @FXML // fx:id="txtId"
    private TextField txtId; // Value injected by FXMLLoader

    @FXML // fx:id="choGenero"
    private ChoiceBox<?> choGenero; // Value injected by FXMLLoader

    @FXML // fx:id="txtEdad"
    private TextField txtEdad; // Value injected by FXMLLoader

    @FXML // fx:id="txtTel"
    private TextField txtTel; // Value injected by FXMLLoader

    @FXML // fx:id="choTurno"
    private ChoiceBox<?> choTurno; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregar"
    private Button btnAgregar; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancelar"
    private Button btnCancelar; // Value injected by FXMLLoader

    @FXML
    void doAgregar(ActionEvent event) {

        String nombre = txtNombre.getText();
        String apellido = txtApellidos.getText();
        String id = txtId.getText();
        String edad = txtEdad.getText();
        String telefono = txtTel.getText();
        char genero = ' ';
        Turno turno = (Turno) choTurno.getValue();
        
        if (choGenero.getValue() != null) {
            genero = choGenero.getValue().toString().charAt(0);
        }

        if ((id.equals("")) || (nombre.equals("")) || (apellido.equals("")) || (edad.equals("")) || (telefono.equals("")) || (genero == ' ') || (turno==null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {

            conductor = new Conductor(id, nombre, apellido, genero, edad, telefono, turno.getCodigo());
            if(conductorDAO.guardarConductor(conductor)){
                stagePrincipal.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El Id que intenta ingresar ya existe");

                alert.showAndWait();

            }
        }

    }

    @FXML
    void doCancelar(ActionEvent event) {
        stagePrincipal.close();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtApellidos != null : "fx:id=\"txtApellidos\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert choGenero != null : "fx:id=\"choGenero\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtEdad != null : "fx:id=\"txtEdad\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtTel != null : "fx:id=\"txtTel\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert choTurno != null : "fx:id=\"choTurno\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'IngresoConductor.fxml'.";

    } 
    
}
