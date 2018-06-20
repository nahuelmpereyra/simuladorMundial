import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {

    private String zona;
    @Id
    private String nombre;

    private Boolean esCabezaDeSerie = false;

    private Integer puntos = 0;
    private Integer golesAFavor = 0;
    private Integer golesEnContra = 0;
    private Integer diferencia = 0;

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

    public Boolean getEsCabezaDeSerie() {
        return esCabezaDeSerie;
    }

    public void setEsCabezaDeSerie(Boolean bool) {
        this.esCabezaDeSerie = bool;
    }

    public void sumarPuntos(Integer puntos) {
        this.puntos += puntos;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void sumarGoles(Integer golesLocal, Integer golesVisitantes) {
        golesAFavor += golesLocal;
        golesEnContra += golesVisitantes;
        actualizarDiferencia();
    }

    public void actualizarDiferencia() {
        diferencia = golesAFavor - golesEnContra;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public Integer getGolesAFavor() {
        return golesAFavor;
    }

    public Integer getGolesEnContra() {
        return golesEnContra;
    }
}
