/**
 * Sample Skeleton for 'tabPropietarios.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabPropietariosController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<?> table; // Value injected by FXMLLoader

    @FXML // fx:id="columNom"
    private TableColumn<?, ?> columNom; // Value injected by FXMLLoader

    @FXML // fx:id="columApe"
    private TableColumn<?, ?> columApe; // Value injected by FXMLLoader

    @FXML // fx:id="columId"
    private TableColumn<?, ?> columId; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabPropietarios.fxml'.";
        assert columNom != null : "fx:id=\"columNom\" was not injected: check your FXML file 'tabPropietarios.fxml'.";
        assert columApe != null : "fx:id=\"columApe\" was not injected: check your FXML file 'tabPropietarios.fxml'.";
        assert columId != null : "fx:id=\"columId\" was not injected: check your FXML file 'tabPropietarios.fxml'.";

    }
}
