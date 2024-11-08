
package controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import propinator2000.Trabajador;
import static propinator2000.Trabajador.generarNombreArchivo;


 //@author adriDani
 
public class TablaTrabajadoresController implements Initializable {
    
    @FXML
    private AnchorPane anchorPanePrincipal;
    @FXML
    private Button botonVolver;
    @FXML
    private Button botonGuardar;
    @FXML
    private TableView<Trabajador> tablaTrabajadores;
    @FXML
    private TableColumn<Trabajador, String> columnaNombre;
    @FXML
    private TableColumn<Trabajador, String> columnaHoras;
    @FXML
    private TableColumn<Trabajador, Double> columnaPropina;
    
    private List<String> nombres = new ArrayList<>();
    private List<String> horas = new ArrayList<>();

    public void cargarDatos(List<Trabajador> lista){
        for (int i=0;i<lista.size();i++){
            Trabajador trabajador = new Trabajador(lista.get(i).getNombre(), lista.get(i).getHoras(), lista.get(i).getPropina());
            tablaTrabajadores.getItems().add(trabajador);
        }
    }
    
    public void cargarDatos(List<String> nombres, List<String> horas, double propina){
        System.out.println("Nombres size: " + nombres.size());
        System.out.println("Horas size: "+ horas.size());
        System.out.println("Propina: " + propina);
        double suma=0;
        for (int i=0; i<horas.size();i++){
            suma += Double.parseDouble(horas.get(i));
            System.out.println("Nueva suma: "+suma);
        }
        for (int i=0; i<nombres.size();i++){
            double total = (propina/suma)*Double.parseDouble(horas.get(i));
            total = Math.round(total *100.0)/100.0;
            Trabajador trabajador = new Trabajador(nombres.get(i), horas.get(i), total);
            tablaTrabajadores.getItems().add(trabajador);
        }

    }
    
    public void botonVolver(ActionEvent evt){
        System.out.println("Funciona?");
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
            alerta.setContentText("Error al cargar la ventana anterior. Reinicie el programa.\nSi el problema persiste, contacte con el administrador.");
            System.out.println(e);
            e.getStackTrace();
            alerta.showAndWait();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(null);
            alerta.setContentText("Error no esperado.\nContacte con su servicio técnico.");
            e.printStackTrace();;
            System.out.println(e);
            alerta.showAndWait();
        }

    }
    
    public void botonGuardar(ActionEvent evt){
        guardar();
    }
    
    public void guardar(){
        String ruta= System.getProperty("user.home")+File.separator+("calculator2000")+File.separator;
        String extension = ".txt";
        String nombreArchivo = generarNombreArchivo(ruta, extension);
        
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

            try(PrintWriter tabla = new PrintWriter(new FileOutputStream(archivo))){

                tabla.println("Nombre\tHoras\tPropina");
                for (Trabajador trabajador : tablaTrabajadores.getItems()){
                    tabla.println(trabajador.getNombre()+"\t"+trabajador.getHoras()+"\t"+trabajador.getPropina());
                }
                Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("¡Conseguido!");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Datos guardados correctamente: " + System.lineSeparator() +archivo);
                    System.out.println("Guachi");
                    alerta.showAndWait();


            }catch (FileNotFoundException e){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("¡Error!");
                alerta.setHeaderText(null);
                alerta.setContentText("No se ha podido guardar el archivo: "+System.lineSeparator()+e);
                System.out.println("Error al guardar");
                alerta.showAndWait();
            }
        
        
    }
        
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaHoras.setCellValueFactory(new PropertyValueFactory<>("horas"));
        columnaPropina.setCellValueFactory(new PropertyValueFactory<>("propina"));
        
    }    
    
}
