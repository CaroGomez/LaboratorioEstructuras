/**
 * Sample Skeleton for 'IngresoPropietario.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO;
import java.net.URL;
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

public class IngresoPropietarioController {

    ObservableList<String> generoList = FXCollections.observableArrayList("Masculino", "Femenino");
    Propietario propietario;
    PropietarioDAO propietarioDAO = new FilePropietarioDAO();

    private Stage stagePrincipal;

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtApellido"
    private TextField txtApellido; // Value injected by FXMLLoader

    @FXML // fx:id="txtNombre"
    private TextField txtNombre; // Value injected by FXMLLoader

    @FXML // fx:id="txtId"
    private TextField txtId; // Value injected by FXMLLoader

    @FXML // fx:id="chsBox"
    private ChoiceBox chsBox; // Value injected by FXMLLoader

    @FXML // fx:id="txtEdad"
    private TextField txtEdad; // Value injected by FXMLLoader

    @FXML // fx:id="txtTelefono"
    private TextField txtTelefono; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregar"
    private Button btnAgregar; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancealr"
    private Button btnCancealr; // Value injected by FXMLLoader

    @FXML
    void DoAgregar(ActionEvent event) throws LlaveDuplicadaException {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String id = txtId.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();
        char genero = ' ';
        if (chsBox.getValue() != null) {
            genero = chsBox.getValue().toString().charAt(0);
        }

        if ((id.equals(""))|| (nombre.equals(""))|| (apellido.equals("")) || (edad.equals(""))|| (telefono.equals(""))|| (genero == ' ') ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {

            propietario = new Propietario(id, nombre, apellido, genero, edad, telefono);

            propietarioDAO.guardarPropietario(propietario);

            stagePrincipal.close();
        }

    }

    @FXML
    void DoCancelar(ActionEvent event) {
        stagePrincipal.close();

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        chsBox.setItems(generoList);
        assert txtApellido != null : "fx:id=\"txtApellido\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert chsBox != null : "fx:id=\"chsBox\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert txtEdad != null : "fx:id=\"txtEdad\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert txtTelefono != null : "fx:id=\"txtTelefono\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";
        assert btnCancealr != null : "fx:id=\"btnCancealr\" was not injected: check your FXML file 'IngresoPropietario.fxml'.";

    }
}
