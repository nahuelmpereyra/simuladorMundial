import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {

    private String zona;
    @Id
    private String nombre;

    private Boolean esCabezaDeSerie = false;

    private Integer puntos=0;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public Boolean getEsCabezaDeSerie() { return esCabezaDeSerie; }

    public void setEsCabezaDeSerie(Boolean bool) {this.esCabezaDeSerie = bool;}

    public void sumarPuntos(int puntos) {
        this.puntos += puntos;
    }

    public int getPuntos() {
        return puntos;
    }
}
