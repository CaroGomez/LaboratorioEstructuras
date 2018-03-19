/**
 * Sample Skeleton for 'tabPropietarios.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabPropietariosController {
    
    PropietarioDAO propietarioDAO = new FilePropietarioDAO();
    
    List<Propietario> propietarios = propietarioDAO.listarPropietarios();
    ObservableList<Propietario> PropietariosList = FXCollections.observableList(propietarios);
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<Propietario> table; // Value injected by FXMLLoader

    @FXML // fx:id="columNom"
    private TableColumn<Propietario, String> columNom; // Value injected by FXMLLoader
    
    @FXML // fx:id="columApe"
    private TableColumn<Propietario, String> columApe; // Value injected by FXMLLoader

    @FXML // fx:id="columId"
    private TableColumn<Propietario, String> columId; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        table.setItems(PropietariosList);
        columNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombres()));
        columApe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        columId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabPropietarios.fxml'.";
        assert columNom != null : "fx:id=\"columNom\" was not injected: check your FXML file 'tabPropietarios.fxml'.";
        assert columApe != null : "fx:id=\"columApe\" was not injected: check your FXML file 'tabPropietarios.fxml'.";
        assert columId != null : "fx:id=\"columId\" was not injected: check your FXML file 'tabPropietarios.fxml'.";

    }
}
