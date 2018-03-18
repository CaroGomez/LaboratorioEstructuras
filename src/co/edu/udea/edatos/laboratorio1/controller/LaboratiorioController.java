/**
 * Sample Skeleton for 'Laboratiorio.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TaxiDAO;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTaxiDAO;
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
    TaxiDAO taxiDAO = new FileTaxiDAO();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnIngresar"
    private Button btnIngresarTaxi; // Value injected by FXMLLoader

    @FXML // fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnPropietario"
    private Button btnPropietario; // Value injected by FXMLLoader

    @FXML // fx:id="btnTurno"
    private Button btnTurno; // Value injected by FXMLLoader

    @FXML // fx:id="btnTaller"
    private Button btnTaller; // Value injected by FXMLLoader

    @FXML // fx:id="btnConductor"
    private Button btnConductor; // Value injected by FXMLLoader

    @FXML
    void IngresarTaxi(ActionEvent event) {

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

            } 
        } else {

            ProgramaPrincipal.mostrarIngresoTaxi();

        }
    }

    @FXML
    void doIngresarPro(ActionEvent event) {
        ProgramaPrincipal.mostrarIngresoPropietario();

    }

    @FXML
    void doIngresarTaller(ActionEvent event) {
        ProgramaPrincipal.mostrarIngresoTaller();
    }

    @FXML
    void doIngresarTurno(ActionEvent event) {
        List<Taxi> taxis = taxiDAO.listarTaxis();

        
        
        if (taxis.isEmpty()) {
            txtResult.appendText("No existen Taxis\n");

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("No existen Taxis");
            alert.setHeaderText("Aún no existen Taxis");
            alert.setContentText("¿Desea agregar un Taxi?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                ProgramaPrincipal.mostrarIngresoTaxi();

            } 
        } else {

            ProgramaPrincipal.mostrarIngresoTurno();

        }
    }

    @FXML
    void doIngresarCond(ActionEvent event) {
        
        ProgramaPrincipal.mostrarIngresoConductor();
    }

    public void setProgramaPrincipal(Main ProgramaPrincipal) {
        this.ProgramaPrincipal = ProgramaPrincipal;
    }

    /*void mostrar(String mensaje){
        txtResult.appendText(mensaje + " ");
    }*/
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnIngresarTaxi != null : "fx:id=\"btnIngresar\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert btnPropietario != null : "fx:id=\"btnPropietario\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert btnTurno != null : "fx:id=\"btnTurno\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert btnTaller != null : "fx:id=\"btnTaller\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
        assert btnConductor != null : "fx:id=\"btnConductor\" was not injected: check your FXML file 'Laboratiorio.fxml'.";
    }
}
