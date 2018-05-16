/**
 * Sample Skeleton for 'IngresoTurno.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.LlaveEntero;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TaxiDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TurnoDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTaxiDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTurnoDAO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class IngresoTurnoController {

    private Stage stagePrincipal;
    private LaboratiorioController controllerLab;

    TaxiDAO taxiDAO = new FileTaxiDAO();
    TurnoDAO turnoDAO = new FileTurnoDAO();

    List<Taxi> taxis = taxiDAO.listarTaxis();
    ObservableList<Taxi> taxiList = FXCollections.observableList(taxis);

    private ArbolB arbol = turnoDAO.retornarArbol();
    private VerArbol ver = new VerArbol();

    Taxi tax;

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCod"
    private TextField txtCod; // Value injected by FXMLLoader

    @FXML // fx:id="txtHorario"
    private TextField txtHorario; // Value injected by FXMLLoader

    @FXML // fx:id="txtHoras"
    private TextField txtHoras; // Value injected by FXMLLoader

    @FXML // fx:id="btnIngresar"
    private Button btnIngresar; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancelar"
    private Button btnCancelar; // Value injected by FXMLLoader

    @FXML // fx:id="txtPlaca"
    private TextField txtPlaca; // Value injected by FXMLLoader

    @FXML // fx:id="btnBusTaxi"
    private Button btnBusTaxi; // Value injected by FXMLLoader

    @FXML // fx:id="PaneResult"
    private AnchorPane PaneResult; // Value injected by FXMLLoader

    @FXML // fx:id="tableResult"
    private TableView<Taxi> tableResult; // Value injected by FXMLLoader

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

    @FXML // fx:id="btnSelec"
    private Button btnSelec; // Value injected by FXMLLoader

    @FXML
    void DoCancelar(ActionEvent event) {
        stagePrincipal.close();
    }

    @FXML
    void DoSelect(ActionEvent event) {
        if (tableResult.getSelectionModel().getSelectedItem() != null) {
            tax = tableResult.getSelectionModel().getSelectedItem();

            txtPlaca.clear();
            txtPlaca.setText(tax.toString());

            PaneResult.setVisible(false);

        }
    }

    @FXML
    void doBuscarTaxi(ActionEvent event) {

        PaneResult.setVisible(true);
    }

    @FXML
    void DoIngresar(ActionEvent event) {

        String cod = txtCod.getText();
        String horario = txtHorario.getText();
        String horas = txtHoras.getText();
        Taxi taxi = tax;

        if ((cod.equals("")) || (horario.equals("")) || (horas.equals("")) || (taxi == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {
            Turno turno = new Turno(cod, horario, horas, taxi.getPlaca());
            if (turnoDAO.guardarTurno(turno)) {
                arbol.insert(new LlaveEntero(Integer.parseInt(turno.getCodigo())), "");
                stagePrincipal.close();
            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El código que intenta ingresar ya existe");

                alert.showAndWait();

            }

        }

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        tableResult.setItems(taxiList);
        columPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlaca()));
        columNumTaxi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNumero_taxi()));
        columMarca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMarca()));
        columModel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModelo()));
        columIdProp.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdPropietario()));
       

        assert txtCod != null : "fx:id=\"txtCod\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert txtHorario != null : "fx:id=\"txtHorario\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert txtHoras != null : "fx:id=\"txtHoras\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert txtPlaca != null : "fx:id=\"txtPlaca\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert btnBusTaxi != null : "fx:id=\"btnBusTaxi\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert btnIngresar != null : "fx:id=\"btnIngresar\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert PaneResult != null : "fx:id=\"PaneResult\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert tableResult != null : "fx:id=\"tableResult\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert columPlaca != null : "fx:id=\"columPlaca\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert columNumTaxi != null : "fx:id=\"columNumTaxi\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert columMarca != null : "fx:id=\"columMarca\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert columModel != null : "fx:id=\"columModel\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert columIdProp != null : "fx:id=\"columIdProp\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert btnSelec != null : "fx:id=\"btnSelec\" was not injected: check your FXML file 'IngresoTurno.fxml'.";

    }
}
