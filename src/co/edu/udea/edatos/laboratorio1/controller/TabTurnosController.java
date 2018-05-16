/**
 * Sample Skeleton for 'tabTurnos.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TurnoDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTurnoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TabTurnosController {

    TurnoDAO turnoDAO = new FileTurnoDAO();

    List<Turno> turnos = turnoDAO.listarTurnos();
    ObservableList<Turno> turnosList = FXCollections.observableList(turnos);

    private ArbolB arbol = turnoDAO.retornarArbol();
    private VerArbol ver = new VerArbol();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<Turno> table; // Value injected by FXMLLoader

    @FXML // fx:id="columCod"
    private TableColumn<Turno, String> columCod; // Value injected by FXMLLoader

    @FXML // fx:id="columHorario"
    private TableColumn<Turno, String> columHorario; // Value injected by FXMLLoader

    @FXML // fx:id="columHoras"
    private TableColumn<Turno, String> columHoras; // Value injected by FXMLLoader

    @FXML // fx:id="columPlacaTaxi"
    private TableColumn<Turno, String> columPlacaTaxi; // Value injected by FXMLLoader

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
            Turno tur = turnoDAO.consultarTurno(txtBuscar.getText());
            
            if (tur != null) {
                System.out.println(tur.toString());
                turnos.clear();
                turnos.add(tur);
                table.setItems(turnosList);
                table.refresh();
                txtBuscar.clear();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No enconttrado");
                alert.setHeaderText(null);
                alert.setContentText("El codigo buscado no existe");
                alert.showAndWait();
                txtBuscar.clear();
            }
        }
        else {
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

       if (!turnos.isEmpty()) {
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
        turnos = turnoDAO.listarTurnos();
        turnosList = FXCollections.observableList(turnos);
        table.setItems(turnosList);
        table.refresh();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        table.setItems(turnosList);
        columCod.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columHorario.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHorario()));
        columHoras.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getHoras()));
        columPlacaTaxi.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPlacaTaxi()));
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabTurnos.fxml'.";
        assert columCod != null : "fx:id=\"columCod\" was not injected: check your FXML file 'tabTurnos.fxml'.";
        assert columHorario != null : "fx:id=\"columHorario\" was not injected: check your FXML file 'tabTurnos.fxml'.";
        assert columHoras != null : "fx:id=\"columHoras\" was not injected: check your FXML file 'tabTurnos.fxml'.";
        assert columPlacaTaxi != null : "fx:id=\"columPlacaTaxi\" was not injected: check your FXML file 'tabTurnos.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert btnMostrar != null : "fx:id=\"btnMostrar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert txtBuscar != null : "fx:id=\"txtBuscar\" was not injected: check your FXML file 'tabTurnos.fxml'.";
        assert btnTodos != null : "fx:id=\"btnTodos\" was not injected: check your FXML file 'tabTurnos.fxml'.";

    }
}
