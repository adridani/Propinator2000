/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import propinator2000.Trabajador;
import static propinator2000.Trabajador.generarCarga;


/**
 * FXML Controller class
 *
 * @author AdriDani
 */
public class PrincipalController implements Initializable {
    
    
    @FXML
    private Label campoPropina;
    @FXML
    private TextField campoTotal;
    @FXML
    private AnchorPane anchorPanePrincipal;
    
    private static List<Trabajador> trabajadores = new ArrayList<>();

    public static List<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<Trabajador> trabajadores) {
        this.trabajadores = trabajadores;
    }
    
    

    public void botonNuevos(ActionEvent evt){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Nuevos.fxml"));
            Parent root = loader.load();
            anchorPanePrincipal.getChildren().setAll(root);
            //campoPropina.setVisible(false);
            //campoTotal.setVisible(false);
            

        }catch (IOException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error IO: "+e);
            alerta.setHeaderText(null);
            alerta.setContentText("Error al cargar la ventana. Reinicie el programa.\nSi el problema persiste, contacte con el administrador.");
            System.out.println(e);
            e.getStackTrace();
            alerta.showAndWait();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Error no esperado.\nContacte con su servicio técnico.");
            System.out.println(e);
            alerta.showAndWait();
        }
    }
    
    
    public void botonAntiguos(ActionEvent evt){
        cargar();
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Antiguos.fxml"));
            Parent root = loader.load();
            anchorPanePrincipal.getChildren().setAll(root);
        }catch (IOException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error IO: "+e);
            alerta.setHeaderText(null);
            alerta.setContentText("Error al cargar la ventana. Reinicie el programa.\nSi el problema persiste, contacte con el administrador.");
            System.out.println(e);
            e.getStackTrace();
            alerta.showAndWait();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Error no esperado.\nContacte con su servicio técnico.");
            System.out.println(e);
            alerta.showAndWait();
        }
        
    }
    
    private void cargar(){
        String ruta= System.getProperty("user.home")+File.separator+("calculator2000")+File.separator;
        
        String nombreArchivo = ruta + "binarios.data";
        
        File archivo = new File(nombreArchivo);
        File carpeta = archivo.getParentFile();
        
        if(!carpeta.exists()){
                if(carpeta.mkdirs()){
                    System.out.println("Carpeta creada: "+ carpeta.getAbsolutePath());
                }else{
                    JOptionPane.showMessageDialog(null, "Error al crear la carpeta", "Error" ,2);
                    return;
                }
            }
                try(FileInputStream cargar = new FileInputStream(archivo); ObjectInputStream cargarObjeto = new ObjectInputStream(cargar);){
                    trabajadores.clear();
                    while(true){
                        try{
                            Trabajador trabajador = (Trabajador) cargarObjeto.readObject();
                            trabajadores.add(trabajador);
                            System.out.println(trabajadores);
                        }catch(EOFException e){
                            break;
                        }
                    }
                    System.out.println("Sacargaobien");
                    
                }
                catch (FileNotFoundException e){
                    e.printStackTrace();
                    System.out.println(e);
                }
                catch (IOException e){
                    e.printStackTrace();
                    System.out.println(e);
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
