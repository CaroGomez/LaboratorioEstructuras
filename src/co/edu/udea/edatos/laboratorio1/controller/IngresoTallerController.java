/**
 * Sample Skeleton for 'IngresoTaller.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.dao.exceptions.LlaveDuplicadaException;
import co.edu.udea.edatos.laboratorio1.modelo.Taller;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TallerDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTallerDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IngresoTallerController {

    private Stage stagePrincipal;
    private LaboratiorioController controllerLab;
    TallerDAO tallerDAO = new FileTallerDAO();

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCod"
    private TextField txtCod; // Value injected by FXMLLoader

    @FXML // fx:id="txtNombre"
    private TextField txtNombre; // Value injected by FXMLLoader

    @FXML // fx:id="txtDir"
    private TextField txtDir; // Value injected by FXMLLoader

    @FXML // fx:id="txtTel"
    private TextField txtTel; // Value injected by FXMLLoader

    @FXML
    void DoCancelar(ActionEvent event) {
        stagePrincipal.close();
    }

    @FXML
    void DoIngresar(ActionEvent event) throws LlaveDuplicadaException {
        String codigo = txtCod.getText();
        String nombre = txtNombre.getText();
        String direccion = txtDir.getText();
        String telefono = txtTel.getText();

        if ((codigo.equals("")) || (nombre.equals("")) || (direccion.equals("")) || (telefono.equals(""))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();

        } else {
            Taller taller = new Taller(codigo, nombre, telefono, direccion);

            if (tallerDAO.guardarTaller(taller)) {
                stagePrincipal.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El Codigo que intenta ingresar ya existe");

                alert.showAndWait();

            }

        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCod != null : "fx:id=\"txtCod\" was not injected: check your FXML file 'IngresoTaller.fxml'.";
        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'IngresoTaller.fxml'.";
        assert txtDir != null : "fx:id=\"txtDir\" was not injected: check your FXML file 'IngresoTaller.fxml'.";
        assert txtTel != null : "fx:id=\"txtTel\" was not injected: check your FXML file 'IngresoTaller.fxml'.";

    }
}
