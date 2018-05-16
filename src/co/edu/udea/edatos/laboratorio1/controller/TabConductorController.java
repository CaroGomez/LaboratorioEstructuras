/**
 * Sample Skeleton for 'tabConductor.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import co.edu.udea.edatos.laboratorio1.modelo.dao.ConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TabConductorController {

    ConductorDAO conductorDAO = new FileConductorDAO();

    List<Conductor> conductores = conductorDAO.listarConductores();
    private ArbolB arbol = conductorDAO.retornarArbol();
    private VerArbol ver = new VerArbol();

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

    @FXML // fx:id="btnBuscar"
    private Button btnBuscar; // Value injected by FXMLLoader

    @FXML // fx:id="btnMostrar"
    private Button btnMostrar; // Value injected by FXMLLoader

    @FXML // fx:id="txtBuscar"
    private TextField txtBuscar; // Value injected by FXMLLoader

    @FXML // fx:id="btnTodos"
    private Button btnTodos; // Value injected by FXMLLoader

    @FXML
    void DoBuscar(ActionEvent event) {
        if (txtBuscar.getText().matches("([0-9])+")) {
            Conductor cond = conductorDAO.consultarConductor(txtBuscar.getText());

            if (cond != null) {
                System.out.println(cond.toString());
                conductores.clear();
                conductores.add(cond);
                table.setItems(conductoresList);
                table.refresh();
                txtBuscar.clear();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No enconttrado");
                alert.setHeaderText(null);
                alert.setContentText("El Id buscado no existe");
                alert.showAndWait();
                txtBuscar.clear();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Ingrese sólo números");
            alert.showAndWait();
            txtBuscar.clear();
        }

    }

    @FXML
    void DoMostrar(ActionEvent event) {

        if (!conductores.isEmpty()) {
            ver.mostrarArbol(arbol);
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El arbol está vacío");
                alert.showAndWait();
        }

    }

    @FXML
    void DoTodos(ActionEvent event) {

        conductores = conductorDAO.listarConductores();
        conductoresList = FXCollections.observableList(conductores);
        table.setItems(conductoresList);
        table.refresh();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        table.setItems(conductoresList);
        columNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombres()));
        columApe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        columId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columEdad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEdad()));
        columTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columCodTurn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodTurno()));
        columGen.setCellValueFactory(cellData -> new SimpleStringProperty(Character.toString(cellData.getValue().getGenero())));
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columId != null : "fx:id=\"columId\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columNom != null : "fx:id=\"columNom\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columApe != null : "fx:id=\"columApe\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columGen != null : "fx:id=\"columGen\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columEdad != null : "fx:id=\"columEdad\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columTel != null : "fx:id=\"columTel\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert columCodTurn != null : "fx:id=\"columCodTurn\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert btnMostrar != null : "fx:id=\"btnMostrar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert txtBuscar != null : "fx:id=\"txtBuscar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert btnTodos != null : "fx:id=\"btnTodos\" was not injected: check your FXML file 'tabConductor.fxml'.";

    }
}
