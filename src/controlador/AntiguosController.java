
package controlador;

import java.io.IOException;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import propinator2000.Trabajador;

public class AntiguosController implements Initializable {

    @FXML
    private Button botonAceptar;
    @FXML
    private Button botonVolver;
    @FXML
    private TextField campoPropina;

    @FXML
    private AnchorPane anchorPanePrincipal;
    
    private double nuevaPropina;
    
    private List<Trabajador> lista = new ArrayList<>();
    

    public double getNuevaPropina() {
        return nuevaPropina;
    }

    public void setNuevaPropina(double nuevaPropina) {
        this.nuevaPropina = nuevaPropina;
    }
    
    
    
    @FXML
    private void botonAceptar(ActionEvent evt){
        //PrincipalController principalController = new PrincipalController();
        //lista = principalController.getTrabajadores();
        lista = PrincipalController.getTrabajadores();
        
        System.out.println(lista.size());
        try{
            nuevaPropina = Double.parseDouble(campoPropina.getText());
            int suma=0;
            for(int i=0; i<lista.size();i++){
                suma+=Double.parseDouble(lista.get(i).getHoras());
                System.out.println("Suma= "+suma);
            }
            
            for( int i=0; i<lista.size();i++){
                double totalPropina = (nuevaPropina/suma)*Double.parseDouble(lista.get(i).getHoras());
                totalPropina = Math.round(totalPropina*100.0)/100.0;
                lista.get(i).setPropina(totalPropina);
                System.out.println(lista.get(i).getNombre() +" "+ lista.get(i).getHoras()+ " "+ lista.get(i).getPropina());
            }
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/TablaTrabajadores.fxml"));
            Parent root=loader.load();
            
            TablaTrabajadoresController controller = loader.getController();
            
            controller.cargarDatos(lista);

            anchorPanePrincipal.getChildren().setAll(root);
        }catch(IOException e){
            e.printStackTrace();
        }
        }catch (NumberFormatException e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error: "+e);
            alerta.setHeaderText(null);
            alerta.setContentText("Introduzca un número válido");
            System.out.println(e);
            e.getStackTrace();
            alerta.showAndWait();
        }catch (Exception e){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error: "+e);
            alerta.setHeaderText(null);
            alerta.setContentText("Error no esperado.\nContacte con su servicio técnico.");
            System.out.println(e);
            e.getStackTrace();
            alerta.showAndWait();
        }
        
        
        
    }
    
    @FXML
    private void botonVolver(ActionEvent evt){
        
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
 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
