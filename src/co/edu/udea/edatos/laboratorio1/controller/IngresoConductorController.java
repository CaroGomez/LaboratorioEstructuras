/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.LlaveEntero;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Conductor;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import co.edu.udea.edatos.laboratorio1.modelo.dao.ConductorDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TurnoDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileConductorDAO;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carolina
 */
public class IngresoConductorController {

    Conductor conductor;

    ConductorDAO conductorDAO = new FileConductorDAO();
    TurnoDAO turnoDAO = new FileTurnoDAO();

    List<Turno> turnos = turnoDAO.listarTurnos();

    ObservableList<Turno> turnoList = FXCollections.observableList(turnos);
    ObservableList<String> generoList = FXCollections.observableArrayList("Masculino", "Femenino");

    private ArbolB arbol = conductorDAO.retornarArbol();
    private VerArbol ver = new VerArbol();

    Turno turno;

    private Stage stagePrincipal;

    public void setStagePrincipal(Stage stagePrincipal) {
        this.stagePrincipal = stagePrincipal;
    }

    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtNombre"
    private TextField txtNombre; // Value injected by FXMLLoader

    @FXML // fx:id="txtApellidos"
    private TextField txtApellido; // Value injected by FXMLLoader

    @FXML // fx:id="txtId"
    private TextField txtId; // Value injected by FXMLLoader

    @FXML // fx:id="choGenero"
    private ChoiceBox choGenero; // Value injected by FXMLLoader

    @FXML // fx:id="txtEdad"
    private TextField txtEdad; // Value injected by FXMLLoader

    @FXML // fx:id="txtTel"
    private TextField txtTel; // Value injected by FXMLLoader

    @FXML // fx:id="btnAgregar"
    private Button btnAgregar; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancelar"
    private Button btnCancelar; // Value injected by FXMLLoader

    @FXML // fx:id="PaneResult"
    private AnchorPane PaneResult; // Value injected by FXMLLoader

    @FXML // fx:id="tableResul"
    private TableView<Turno> tableResul; // Value injected by FXMLLoader

    @FXML // fx:id="columCod"
    private TableColumn<Turno, String> columCod; // Value injected by FXMLLoader

    @FXML // fx:id="columHorario"
    private TableColumn<Turno, String> columHorario; // Value injected by FXMLLoader

    @FXML // fx:id="columHoras"
    private TableColumn<Turno, String> columHoras; // Value injected by FXMLLoader

    @FXML // fx:id="columPlaca"
    private TableColumn<Turno, String> columPlaca; // Value injected by FXMLLoader

    @FXML // fx:id="btnSelec"
    private Button btnSelec; // Value injected by FXMLLoader

    @FXML // fx:id="txtTurn"
    private TextField txtTurn; // Value injected by FXMLLoader

    @FXML // fx:id="btnBusTurn"
    private Button btnBusTurn; // Value injected by FXMLLoader

    @FXML
    void DoSelect(ActionEvent event) {

        if (tableResul.getSelectionModel().getSelectedItem() != null) {
            turno = tableResul.getSelectionModel().getSelectedItem();

            txtTurn.clear();
            txtTurn.setText(turno.toString());

            PaneResult.setVisible(false);
        }
    }

    @FXML
    void doBuscarturn(ActionEvent event) {
        PaneResult.setVisible(true);
    }

    @FXML
    void doAgregar(ActionEvent event) {

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String id = txtId.getText();
        String edad = txtEdad.getText();
        String telefono = txtTel.getText();
        char genero = ' ';
        Turno turn = turno;

        if (choGenero.getValue() != null) {
            genero = choGenero.getValue().toString().charAt(0);
        }

        if ((id.equals("")) || (nombre.equals("")) || (apellido.equals("")) || (edad.equals("")) || (telefono.equals("")) || (genero == ' ') || (turn == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {

            conductor = new Conductor(id, nombre, apellido, genero, edad, telefono, turn.getCodigo());
            if (conductorDAO.guardarConductor(conductor)) {
                arbol.insert(new LlaveEntero(Integer.parseInt(conductor.getId())), " dirección en disco");
                stagePrincipal.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("El Id que intenta ingresar ya existe");

                alert.showAndWait();

            }
        }

    }

    @FXML
    void doCancelar(ActionEvent event) {
        stagePrincipal.close();
    }

    @FXML
    void DoSelectThis(MouseEvent event) { // esto sobra

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        choGenero.setItems(generoList);
        tableResul.setItems(turnoList);
        columCod.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columHorario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHorario()));
        columHoras.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoras()));
        columPlaca.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlacaTaxi()));

        assert txtNombre != null : "fx:id=\"txtNombre\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtApellido != null : "fx:id=\"txtApellido\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert choGenero != null : "fx:id=\"choGenero\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtEdad != null : "fx:id=\"txtEdad\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtTel != null : "fx:id=\"txtTel\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert btnAgregar != null : "fx:id=\"btnAgregar\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert PaneResult != null : "fx:id=\"PaneResult\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert tableResul != null : "fx:id=\"tableResul\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert columCod != null : "fx:id=\"columCod\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert columHorario != null : "fx:id=\"columHorario\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert columHoras != null : "fx:id=\"columHoras\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert columPlaca != null : "fx:id=\"columPlaca\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert btnSelec != null : "fx:id=\"btnSelec\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert txtTurn != null : "fx:id=\"txtTurn\" was not injected: check your FXML file 'IngresoConductor.fxml'.";
        assert btnBusTurn != null : "fx:id=\"btnBusTurn\" was not injected: check your FXML file 'IngresoConductor.fxml'.";

    }

}
