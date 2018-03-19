/**
 * Sample Skeleton for 'tabTurnos.fxml' Controller Class
 */

package co.edu.udea.edatos.laboratorio1.controller;

import co.edu.udea.edatos.laboratorio1.modelo.Turno;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TurnoDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTurnoDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TabTurnosController {
    
    TurnoDAO turnoDAO = new FileTurnoDAO();
    
    List<Turno> turnos = turnoDAO.listarTurnos();
    ObservableList<Turno> turnosList = FXCollections.observableList(turnos);

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

    }
}
