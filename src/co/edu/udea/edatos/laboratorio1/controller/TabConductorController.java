/**
 * Sample Skeleton for 'tabConductor.fxml' Controller Class
 */

package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import co.edu.udea.edatos.laboratorio1.modelo.dao.ConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabConductorController {
    
    ConductorDAO conductorDAO = new FileConductorDAO();
    
    List<Conductor> conductores = conductorDAO.listarConductores();
    ObservableList<Conductor> conductoresList = FXCollections.observableList(conductores);

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<Conductor> table; // Value injected by FXMLLoader

    @FXML // fx:id="columId"
    private TableColumn<Conductor, String> columId; // Value injected by FXMLLoader

    @FXML // fx:id="columNom"
    private TableColumn<Conductor, String> columNom; // Value injected by FXMLLoader

    @FXML // fx:id="columApe"
    private TableColumn<Conductor, String> columApe; // Value injected by FXMLLoader

    @FXML // fx:id="columGen"
    private TableColumn<Conductor, String> columGen; // Value injected by FXMLLoader

    @FXML // fx:id="columEdad"
    private TableColumn<Conductor, String> columEdad; // Value injected by FXMLLoader

    @FXML // fx:id="columTel"
    private TableColumn<Conductor, String> columTel; // Value injected by FXMLLoader

    @FXML // fx:id="columCodTurn"
    private TableColumn<Conductor, String> columCodTurn; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        table.setItems(conductoresList);
        columNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombres()));
        columApe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        columId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columEdad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEdad()));
        columTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columCodTurn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodTurno()));
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columId != null : "fx:id=\"columId\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columNom != null : "fx:id=\"columNom\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columApe != null : "fx:id=\"columApe\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columGen != null : "fx:id=\"columGen\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columEdad != null : "fx:id=\"columEdad\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columTel != null : "fx:id=\"columTel\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columCodTurn != null : "fx:id=\"columCodTurn\" was not injected: check your FXML file 'tabConductor.fxml'.";

    }
}
