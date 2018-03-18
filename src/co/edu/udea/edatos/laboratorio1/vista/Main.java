/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.udea.edatos.laboratorio1.vista;

import co.edu.udea.edatos.laboratorio1.controller.IngresoConductorController;
import co.edu.udea.edatos.laboratorio1.controller.IngresoTaxiController;
import co.edu.udea.edatos.laboratorio1.controller.IngresoPropietarioController;
import co.edu.udea.edatos.laboratorio1.controller.IngresoTallerController;
import co.edu.udea.edatos.laboratorio1.controller.IngresoTurnoController;
import co.edu.udea.edatos.laboratorio1.controller.LaboratiorioController;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stagePrincipal;
    private AnchorPane rootPane;

    @Override
    public void start(Stage stagePrincipal) throws Exception {
        this.stagePrincipal = stagePrincipal;
        mostrarVentanaPrincipal();

    }

    /*
     * cargamos la ventana principal
     */
    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("Laboratiorio.fxml"));
            rootPane=(AnchorPane) loader.load();
            Scene scene = new Scene(rootPane);
            stagePrincipal.setTitle("Ventana Principal");
            stagePrincipal.setScene(scene);
            LaboratiorioController controller = loader.getController();
            controller.setProgramaPrincipal(this);
            stagePrincipal.show();
        } catch (IOException e) {
    }
}

    public void mostrarIngresoTaxi() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("IngresoTaxi.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Nuevo Taxi");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            IngresoTaxiController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
    }
}
    
    public void mostrarIngresoPropietario() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("IngresoPropietario.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Nuevo Propetario");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            IngresoPropietarioController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
    }
}
    
    public void mostrarIngresoTaller() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("IngresoTaller.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Nuevo Taller");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            IngresoTallerController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
    }
}
    
    public void mostrarIngresoTurno() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("IngresoTurno.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Nuevo Turno");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            IngresoTurnoController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
    }
}
    public void mostrarIngresoConductor() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("IngresoConductor.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Nuevo Conductor");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            IngresoConductorController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
    }
}

    public static void main(String[] args) {
        launch(args);
    }
}