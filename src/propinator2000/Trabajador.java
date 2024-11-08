
package propinator2000;


import java.io.Serializable;
import java.time.LocalDate;


/**
 *
 * @author "AdriDani"
 */
public class Trabajador implements Serializable{

    private String nombre;
    private String horas;
    private double propina;

    public double getPropina() {
        return propina;
    }

    public void setPropina(double propina) {
        this.propina = propina;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }
    
    public Trabajador(String nombre, String horas, double propina) {
        this.nombre = nombre;
        this.horas = horas;
        this.propina = propina;
    }
    
    public static String generarNombreArchivo(String base, String extension){
        
        LocalDate local = LocalDate.now();
        String serie = Integer.toString(local.getDayOfMonth()) + "-"+Integer.toString(local.getMonthValue());
        String nombreArchivo = base + "propinas" + serie + extension;
        
        return nombreArchivo;
    }
    
    public static String generarCarga(String base, String extension){
        
        LocalDate local = LocalDate.now();
        String serie = Integer.toString(local.getDayOfMonth()) + "-"+Integer.toString(local.getMonthValue());
        String nombreArchivo = base + "propinas" + serie + extension;
        
        
        return nombreArchivo;
    }
}


