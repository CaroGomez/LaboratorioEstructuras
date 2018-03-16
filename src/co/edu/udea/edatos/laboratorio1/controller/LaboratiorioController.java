/**
 * Sample Skeleton for 'Laboratiorio.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.dao.ConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO;
import co.edu.udea.edatos.laboratorio1.vista.Main;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class LaboratiorioController {

    private Main ProgramaPrincipal;
    PropietarioDAO propietarioDAO = new FilePropietarioDAO();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnIngresar"
    private Button btnIngresar; // Value injected by FXMLLoader

    @FXML // fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnPropietario"
    private Button btnPropietario; // Value injected by FXMLLoader

    @FXML
    void Ingresar(ActionEvent event) {
        
        List<Propietario> propietarios = propietarioDAO.listarPropietarios();
        
        if (propietarios.isEmpty()) {
            txtResult.appendText("No existen propietarios\n");
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("No existen Propietarios");
            alert.setHeaderText("Aún no existen propietarios");
            alert.setContentText("¿Desea agregar un propietario?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                
                ProgramaPrincipal.mostrarIngresoPropietario();
                
            } else {
               
            }
        } else {
            ProgramaPrincipal.mostrarVentanaSecundaria();
        }
    }
    
    @FXML
    void doIngresarPro(ActionEvent event) {
        ProgramaPrincipal.mostrarIngresoPropietario();

    }

    public void setProgramaPrincipal(Main ProgramaPrincipal) {
        this.ProgramaPrincipal = ProgramaPrincipal;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnIngresar != null : "fx:id=\"btnIngresar\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert btnPropietario != null : "fx:id=\"btnPropietario\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
    }
}
