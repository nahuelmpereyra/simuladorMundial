import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {

    private String zona;
    @Id
    private String nombre;

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
}
