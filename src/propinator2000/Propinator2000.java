/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package propinator2000;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author $"AdriDani"
 */
public class Propinator2000 extends Application{
    
    @Override
    public void start(Stage primaryStage) {
        try {
            // Cargar el archivo FXML principal
            Parent root = FXMLLoader.load(getClass().getResource("/vista/Principal.fxml"));
            
            // Crear la escena con el contenido del FXML
            Scene scene = new Scene(root);
            
            // Configurar el escenario (Stage) y mostrarlo
            primaryStage.setTitle("Calculadora de propinas");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        // TODO code application logic here
    }

}
