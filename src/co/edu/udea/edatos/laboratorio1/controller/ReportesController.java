/**
 * Sample Skeleton for 'Reportes.fxml' Controller Class
 */

package co.edu.udea.edatos.laboratorio1.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.stage.Stage;

public class ReportesController {
    
    private Stage stagePrincipal;
    
    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="tabPropietario"
    private Tab tabPropietario; // Value injected by FXMLLoader

    @FXML // fx:id="tabCondunctor"
    private Tab tabCondunctor; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert tabPropietario != null : "fx:id=\"tabPropietario\" was not injected: check your FXML file 'Reportes.fxml'.";
        assert tabCondunctor != null : "fx:id=\"tabCondunctor\" was not injected: check your FXML file 'Reportes.fxml'.";

    }
}