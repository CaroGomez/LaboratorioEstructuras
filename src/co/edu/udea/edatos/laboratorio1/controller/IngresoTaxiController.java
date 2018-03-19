/**
 * Sample Skeleton for 'Ingreso.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TaxiDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTaxiDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IngresoTaxiController {

    private Stage stagePrincipal;
    private LaboratiorioController controllerLab;
    
    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    PropietarioDAO propietarioDAO = new FilePropietarioDAO();
    TaxiDAO taxiDAO = new FileTaxiDAO();

    List<Propietario> propietarios = propietarioDAO.listarPropietarios();
    ObservableList<Propietario> generoList = FXCollections.observableList(propietarios);
    
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPlaca"
    private TextField txtPlaca; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumTaxi"
    private TextField txtNumTaxi; // Value injected by FXMLLoader

    @FXML // fx:id="listView"
    private ChoiceBox<Propietario> listView; // Value injected by FXMLLoader

    @FXML // fx:id="txtMarca"
    private TextField txtMarca; // Value injected by FXMLLoader

    @FXML // fx:id="txtModelo"
    private TextField txtModelo; // Value injected by FXMLLoader

    @FXML
    private Button btnIngresar;

    @FXML
    private Button btnCancelar;

    @FXML
    void DoCancelar(ActionEvent event) {
        stagePrincipal.close();
    }

    @FXML
    void doIngresar(ActionEvent event) {
        String placa = txtPlaca.getText();
        String numTaxi = txtNumTaxi.getText();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();
        Propietario pro = listView.getValue();

        if ((placa.equals("")) || (numTaxi.equals("")) || (marca.equals("")) || (modelo.equals("")) || (pro == null)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {

            Taxi taxi = new Taxi(placa, numTaxi, marca, modelo, pro.getId());

            if (taxiDAO.guardarTaxi(taxi)) {
                stagePrincipal.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("La placa que intenta ingresar ya existe");

                alert.showAndWait();

            }
        }

        // controllerLab.mostrar("Taxi agregado correctamente");
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        listView.setItems(generoList);
        assert txtPlaca != null : "fx:id=\"txtPlaca\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtNumTaxi != null : "fx:id=\"txtNumTaxi\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtModelo != null : "fx:id=\"txtModelo\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert btnIngresar != null : "fx:id=\"btnIngresar\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
       
    }
}
