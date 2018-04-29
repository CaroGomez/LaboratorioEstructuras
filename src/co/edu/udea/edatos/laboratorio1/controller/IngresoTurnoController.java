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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IngresoTurnoController {

    private Stage stagePrincipal;
    private LaboratiorioController controllerLab;

    TaxiDAO taxiDAO = new FileTaxiDAO();
    TurnoDAO turnoDAO = new FileTurnoDAO();

    List<Taxi> taxis = taxiDAO.listarTaxis();
    ObservableList<Taxi> taxiList = FXCollections.observableList(taxis);
    
    private ArbolB arbol = turnoDAO.CrearArbol();
    private VerArbol ver = new VerArbol();


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

    @FXML // fx:id="chePlaca"
    private ChoiceBox<Taxi> chePlaca; // Value injected by FXMLLoader

    @FXML // fx:id="btnIngresar"
    private Button btnIngresar; // Value injected by FXMLLoader

    @FXML // fx:id="btnCancelar"
    private Button btnCancelar; // Value injected by FXMLLoader

    @FXML
    void DoCancelar(ActionEvent event) {
        stagePrincipal.close();
    }

    @FXML
    void DoIngresar(ActionEvent event) {

        String cod = txtCod.getText();
        String horario = txtHorario.getText();
        String horas = txtHoras.getText();
        Taxi taxi = chePlaca.getValue();

        if ((cod.equals("")) || (horario.equals("")) || (horas.equals("")) || (taxi == null)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {
            Turno turno = new Turno(cod, horario, horas, taxi.getPlaca());
            if (turnoDAO.guardarTurno(turno)) {
                arbol.insert(new LlaveEntero(Integer.parseInt(turno.getCodigo())), " dirección en disco");
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
        chePlaca.setItems(taxiList);

        assert txtCod != null : "fx:id=\"txtCod\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert txtHorario != null : "fx:id=\"txtHorario\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert txtHoras != null : "fx:id=\"txtHoras\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert chePlaca != null : "fx:id=\"chePlaca\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert btnIngresar != null : "fx:id=\"btnIngresar\" was not injected: check your FXML file 'IngresoTurno.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'IngresoTurno.fxml'.";

    }
}
