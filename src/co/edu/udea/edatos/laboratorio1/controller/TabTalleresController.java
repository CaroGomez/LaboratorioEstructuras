/**
 * Sample Skeleton for 'tabTalleres.fxml' Controller Class
 */

package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Taller;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TallerDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTallerDAO;
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

public class TabTalleresController {
    
    TallerDAO tallerDAO = new FileTallerDAO();
    
    List<Taller> talleres = tallerDAO.listarTalleres();
    ObservableList<Taller> talleresList = FXCollections.observableList(talleres);
    
    private ArbolB arbol = tallerDAO.CrearArbol();
    private VerArbol ver = new VerArbol();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="table"
    private TableView<Taller> table; // Value injected by FXMLLoader

    @FXML // fx:id="columCod"
    private TableColumn<Taller, String> columCod; // Value injected by FXMLLoader

    @FXML // fx:id="columNom"
    private TableColumn<Taller, String> columNom; // Value injected by FXMLLoader

    @FXML // fx:id="columTel"
    private TableColumn<Taller, String> columTel; // Value injected by FXMLLoader

    @FXML // fx:id="columDir"
    private TableColumn<Taller, String> columDir; // Value injected by FXMLLoader
    
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
        table.setItems(talleresList);
        columCod.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        columNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        columTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columDir.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));
        assert table != null : "fx:id=\"table\" was not injected: check your FXML file 'tabTalleres.fxml'.";
        assert columCod != null : "fx:id=\"columCod\" was not injected: check your FXML file 'tabTalleres.fxml'.";
        assert columNom != null : "fx:id=\"columNom\" was not injected: check your FXML file 'tabTalleres.fxml'.";
        assert columTel != null : "fx:id=\"columTel\" was not injected: check your FXML file 'tabTalleres.fxml'.";
        assert columDir != null : "fx:id=\"columDir\" was not injected: check your FXML file 'tabTalleres.fxml'.";
        assert btnBuscar != null : "fx:id=\"btnBuscar\" was not injected: check your FXML file 'tabConductor.fxml'.";
        assert btnMostrar != null : "fx:id=\"btnMostrar\" was not injected: check your FXML file 'tabConductor.fxml'.";

    }
}
