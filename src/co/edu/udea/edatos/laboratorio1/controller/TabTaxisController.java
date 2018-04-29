/**
 * Sample Skeleton for 'tabTaxis.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TaxiDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTaxiDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabTaxisController {

    TaxiDAO taxiDAO = new FileTaxiDAO();

    List<Taxi> taxis = taxiDAO.listarTaxis();
    ObservableList<Taxi> taxisList = FXCollections.observableList(taxis);

    private ArbolB arbol = taxiDAO.CrearArbol();
    private VerArbol ver = new VerArbol();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<Taxi> table; // Value injected by FXMLLoader

    @FXML // fx:id="columPlaca"
    private TableColumn<Taxi, String> columPlaca; // Value injected by FXMLLoader

    @FXML // fx:id="columNumTaxi"
    private TableColumn<Taxi, String> columNumTaxi; // Value injected by FXMLLoader

    @FXML // fx:id="columMarca"
    private TableColumn<Taxi, String> columMarca; // Value injected by FXMLLoader

    @FXML // fx:id="columModel"
    private TableColumn<Taxi, String> columModel; // Value injected by FXMLLoader

    @FXML // fx:id="columIdProp"
    private TableColumn<Taxi, String> columIdProp; // Value injected by FXMLLoader

    @FXML // fx:id="btnBuscar"
    private Button btnBuscar; // Value injected by FXMLLoader

    @FXML // fx:id="btnMostrar"
    private Button btnMostrar; // Value injected by FXMLLoader

    @FXML
    void DoBuscar(ActionEvent event) {

    }

    @FXML
    void DoMostrar(ActionEvent event) {

        ver.mostrarArbol(arbol);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        table.setItems(taxisList);
        columPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
        columNumTaxi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumero_taxi()));
        columMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        columModel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        columIdProp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdPropietario()));
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabTaxis.fxml'.";
        assert columPlaca != null : "fx:id=\"columPlaca\" was not injected: check your FXML file 'tabTaxis.fxml'.";
        assert columNumTaxi != null : "fx:id=\"columNumTaxi\" was not injected: check your FXML file 'tabTaxis.fxml'.";
        assert columMarca != null : "fx:id=\"columMarca\" was not injected: check your FXML file 'tabTaxis.fxml'.";
        assert columModel != null : "fx:id=\"columModel\" was not injected: check your FXML file 'tabTaxis.fxml'.";
        assert columIdProp != null : "fx:id=\"columIdProp\" was not injected: check your FXML file 'tabTaxis.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert btnMostrar != null : "fx:id=\"btnMostrar\" was not injected: check your FXML file 'tabConductor.fxml'.";

    }
}
