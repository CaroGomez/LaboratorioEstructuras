/**
 * Sample Skeleton for 'Ingreso.fxml' Controller Class
 */
package co.edu.udea.edatos.laboratorio1.controller;

import ArbolB.ArbolB;
import ArbolB.LlaveCadena;
import ArbolB.VerArbol;
import co.edu.udea.edatos.laboratorio1.modelo.Propietario;
import co.edu.udea.edatos.laboratorio1.modelo.Taxi;
import co.edu.udea.edatos.laboratorio1.modelo.dao.PropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.TaxiDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FilePropietarioDAO;
import co.edu.udea.edatos.laboratorio1.modelo.dao.impl.FileTaxiDAO;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
   
    
    ObservableList<Propietario> PropietariosList = FXCollections.observableList(propietarios);
   

    private ArbolB arbol = taxiDAO.retornarArbol();
    private VerArbol ver = new VerArbol();
    
    Propietario prop;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPlaca"
    private TextField txtPlaca; // Value injected by FXMLLoader

    @FXML // fx:id="txtNumTaxi"
    private TextField txtNumTaxi; // Value injected by FXMLLoader

   @FXML // fx:id="txtProp"
    private TextField txtProp; // Value injected by FXMLLoader

    @FXML // fx:id="txtMarca"
    private TextField txtMarca; // Value injected by FXMLLoader

    @FXML // fx:id="txtModelo"
    private TextField txtModelo; // Value injected by FXMLLoader

    @FXML // fx:id="tableResul"
    private TableView<Propietario> tableResul; // Value injected by FXMLLoader

    @FXML // fx:id="PaneResult"
    private AnchorPane PaneResult; // Value injected by FXMLLoader

    @FXML // fx:id="columId"
    private TableColumn<Propietario, String> columId; // Value injected by FXMLLoader

    @FXML // fx:id="columNom"
    private TableColumn<Propietario, String> columNom; // Value injected by FXMLLoader

    @FXML // fx:id="columApe"
    private TableColumn<Propietario, String> columApe; // Value injected by FXMLLoader

    @FXML // fx:id="columGen"
    private TableColumn<Propietario, String> columGen; // Value injected by FXMLLoader

    @FXML // fx:id="columEdad"
    private TableColumn<Propietario, String> columEdad; // Value injected by FXMLLoader

    @FXML // fx:id="columTel"
    private TableColumn<Propietario, String> columTel; // Value injected by FXMLLoader
    
    
    @FXML // fx:id="listViewProp"
    private ListView<Propietario> listViewProp; // Value injected by FXMLLoader

    @FXML // fx:id="btnBusProp"
    private Button btnBusProp; // Value injected by FXMLLoader

    @FXML // fx:id="btnSelec"
    private Button btnSelec; // Value injected by FXMLLoader

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
        Propietario pro = prop;

        if ((placa.equals("")) || (numTaxi.equals("")) || (marca.equals("")) || (modelo.equals("")) || (pro == null)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("No puede quedar ningun campo el blanco");

            alert.showAndWait();
        } else {

            Taxi taxi = new Taxi(placa, numTaxi, marca, modelo, pro.getId());

            if (taxiDAO.guardarTaxi(taxi)) {
                arbol.insert(new LlaveCadena(taxi.getPlaca()), " dirección en disco");
                stagePrincipal.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("La placa o el número de taxi que intenta ingresar ya existe");

                alert.showAndWait();

            }
        }

        // controllerLab.mostrar("Taxi agregado correctamente");
    }

    @FXML
    void doBuscarpro(ActionEvent event) {
        PaneResult.setVisible(true);

    }

    @FXML
    void DoSelect(ActionEvent event) {
      txtProp.clear();
      txtProp.setText(prop.toString());
       
       PaneResult.setVisible(false);
       
    }
    
    
    @FXML
    void DoSelectThis(MouseEvent event) { // arreglar esto 
        prop = new Propietario(columId.getCellData(0), columNom.getCellData(0), columApe.getCellData(0), columGen.getCellData(0).charAt(0), columEdad.getCellData(0), columTel.getCellData(0));
       
        
    }

    
    
    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        tableResul.setItems(PropietariosList);
        columNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombres()));
        columApe.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getApellidos()));
        columId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        columEdad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEdad()));
        columTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTelefono()));
        columGen.setCellValueFactory(cellData -> new SimpleStringProperty(Character.toString(cellData.getValue().getGenero())));
        assert txtPlaca != null : "fx:id=\"txtPlaca\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtNumTaxi != null : "fx:id=\"txtNumTaxi\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtProp != null : "fx:id=\"txtProp\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtMarca != null : "fx:id=\"txtMarca\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert txtModelo != null : "fx:id=\"txtModelo\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert btnIngresar != null : "fx:id=\"btnIngresar\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert btnCancelar != null : "fx:id=\"btnCancelar\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert PaneResult != null : "fx:id=\"PaneResult\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert tableResul != null : "fx:id=\"table\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert columId != null : "fx:id=\"columId\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert columNom != null : "fx:id=\"columNom\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert columApe != null : "fx:id=\"columApe\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert columGen != null : "fx:id=\"columGen\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert columEdad != null : "fx:id=\"columEdad\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert columTel != null : "fx:id=\"columTel\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert btnBusProp != null : "fx:id=\"btnBusProp\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
        assert btnSelec != null : "fx:id=\"btnSelec\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
         assert listViewProp != null : "fx:id=\"listViewProp\" was not injected: check your FXML file 'IngresoTaxi.fxml'.";
    }
}
