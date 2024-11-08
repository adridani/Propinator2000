
package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import propinator2000.Trabajador;
import static propinator2000.Trabajador.generarNombreArchivo;

/**
 * FXML Controller class
 *
 * @author AdriDani
 */
public class NuevosController implements Initializable, Serializable {
    
    @FXML
    private Button botonVolver;
    @FXML
    private Label campoPropina;
    @FXML
    private TextField campoTotal;
    @FXML
    private Label campoPregunta;
    @FXML
    private Button botonAceptar;
    @FXML
    private TextField campoNumero;
    @FXML
    private AnchorPane anchorPanePrincipal;
    
    private List<TextField> listaCamposNombre = new ArrayList<>();
    private List<TextField> listaCamposHoras = new ArrayList<>();
    
    private List<String>nombres = new ArrayList<>();
    private List<String>horas = new ArrayList<>();
    private double propinas = 0;

    public List<String> getNombres() {
        return nombres;
    }

    public void setNombres(List<String> nombres) {
        this.nombres = nombres;
    }

    public List<String> getHoras() {
        return horas;
    }

    public void setHoras(List<String> horas) {
        this.horas = horas;
    }

    

    public double getPropinas() {
        return propinas;
    }

    public void setPropinas(double propinas) {
        this.propinas = propinas;
    }
    
    
    
    @FXML
    private void botonAceptar(ActionEvent evt){
        int numeroTrabajadores=0;
        
            try {
                numeroTrabajadores = Integer.parseInt(campoNumero.getText());
                if(numeroTrabajadores>30){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Número máximo superado");
                    alerta.setHeaderText(null);
                    alerta.setContentText("El número máximo de trabajadores es 30");
                    System.out.println("Demasiados trabajadores");
                    campoNumero.setText("");
                    alerta.showAndWait();
                    
                    return;
                }
            } catch (NumberFormatException e) {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error IO: "+e);
                alerta.setHeaderText(null);
                alerta.setContentText("Por favor, ingrese un número válido.");
                System.out.println(e);
                e.getStackTrace();
                System.out.println("Por favor, ingrese un número válido.");
                alerta.showAndWait();
                return;
            }
        
        
        botonVolver.setVisible(true);
        campoNumero.setVisible(false);
        campoPregunta.setVisible(false);
        botonAceptar.setVisible(false);
        campoPropina.setVisible(true);
        campoTotal.setVisible(true);
        
        anchorPanePrincipal.getChildren().removeIf(node -> node instanceof TextField && node != campoNumero);
        listaCamposHoras.clear();
        listaCamposNombre.clear();
        
        double posicionInicialY = 100;
        double incrementoY = 40;
        
        for (int i = 0; i<numeroTrabajadores; i++){
            // Campo para el nombre
            TextField campoNombre = new TextField();
            TextField campoHoras = new TextField();
            campoNombre.setPromptText("Trabajador/a " + (i + 1));
            campoHoras.setPromptText("Horas semanales");
            if (i<10){
                campoNombre.setLayoutX(20);
                campoNombre.setLayoutY(posicionInicialY + (i * incrementoY));
                campoHoras.setLayoutX(220);
                campoHoras.setLayoutY(posicionInicialY + (i * incrementoY));
            }
            else if (i>9 && i <20){
                int index = i-10;
                campoNombre.setLayoutX(440);
                campoNombre.setLayoutY(posicionInicialY + (index * incrementoY));
                campoHoras.setLayoutX(640);
                campoHoras.setLayoutY(posicionInicialY + (index * incrementoY));
            }else{
                int index = i-20;
                campoNombre.setLayoutX(860);
                campoNombre.setLayoutY(posicionInicialY + (index * incrementoY));
                campoHoras.setLayoutX(1060);
                campoHoras.setLayoutY(posicionInicialY + (index * incrementoY));
            }
            
            anchorPanePrincipal.getChildren().addAll(campoNombre, campoHoras);
            listaCamposNombre.add(campoNombre);
            listaCamposHoras.add(campoHoras);
            
            
        }
        Button botonTabla = new Button("Generar Tabla");
        botonTabla.setLayoutX(440);
        botonTabla.setLayoutY(650);
        botonTabla.setPrefWidth(350);
        botonTabla.setOnAction(this::generarTabla);
        botonTabla.setStyle("-fx-background-color: linear-gradient(to bottom, #ffeb3b, #ff9800);");
        anchorPanePrincipal.getChildren().add(botonTabla);
            
    }
    
    public void botonVolver(ActionEvent evt){
        System.out.println("Funciona?");
        if(campoTotal.isVisible()){
            anchorPanePrincipal.getChildren().removeIf(node -> node instanceof TextField && node != campoNumero);
            campoNumero.setVisible(true);
            campoPregunta.setVisible(true);
            botonAceptar.setVisible(true);
            campoPropina.setVisible(false);
            campoTotal.setVisible(false);
            campoNumero.setText("");
        }else{
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Principal.fxml"));
            Parent root = loader.load();
            anchorPanePrincipal.getChildren().setAll(root);
            //campoPropina.setVisible(false);
            //campoTotal.setVisible(false);
            

            }catch (IOException e){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error IO: "+e);
                alerta.setHeaderText(null);
                alerta.setContentText("Error al cargar la ventana anterior. Reinicie el programa.\nSi el problema persiste, contacte con el administrador.");
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
    }
    
    private void guardar(){
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
                try(FileOutputStream guardar = new FileOutputStream(archivo); ObjectOutputStream guardarObjeto = new ObjectOutputStream(guardar);){
                    double suma=0;
                    for (int i=0; i<nombres.size();i++){
                        double total = (propinas/suma)*Double.parseDouble(horas.get(i));
                        total = Math.round(total *100.0)/100.0;
                        Trabajador trabajador = new Trabajador(nombres.get(i), horas.get(i), total);
                        guardarObjeto.writeObject(trabajador);
                    }
                }
                catch (FileNotFoundException e){
                    System.out.println(e);
                }
                catch (IOException e){
                    System.out.println(e);
                }
                catch (Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                }
    }
    
    private void generarTabla(ActionEvent evt){
        
        try{
            propinas = Double.parseDouble(campoTotal.getText());
        }catch(NumberFormatException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error IO: "+e);
            alerta.setHeaderText(null);
            alerta.setContentText("Por favor, ingrese un número válido.");
            System.out.println(e);
            e.getStackTrace();
            System.out.println("Por favor, ingrese un número válido.");
            alerta.showAndWait();
            return;
        }
        
        for(int i = 0; i<listaCamposNombre.size();i++){
            nombres.add(listaCamposNombre.get(i).getText());
            horas.add(listaCamposHoras.get(i).getText());
            
        }
        
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/TablaTrabajadores.fxml"));
            Parent root=loader.load();
            
            TablaTrabajadoresController controller = loader.getController();
            controller.cargarDatos(nombres, horas, propinas);
            anchorPanePrincipal.getChildren().setAll(root);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(nombres +" "+ horas+ " "+ propinas);
        
        guardar();
    }   

    
    //creo que tengo que desarrollar la funcion cargarDatos cuando cree el fxml de TablaTrabajadores
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        campoPropina.setVisible(false);
        campoTotal.setVisible(false);
    }    
    
}
