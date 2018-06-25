import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Equipo {

    private String zona;
    @Id
    private String nombre;

    private Boolean esCabezaDeSerie = false;
    private String imagenSrc ="";
    private Integer puntos = 0;
    private Integer golesAFavor = 0;
    private Integer golesEnContra = 0;
    private Integer diferencia = 0;
    private Integer partidosJugados = 0;
    private Integer partidosGanados = 0;
    private Integer partidosEmpatados = 0;
    private Integer partidosPerdidos = 0;

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        this.imagenSrc = "../../imagenes/"+nombre+".png";
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

    public void setPuntos(Integer puntos) { this.puntos = puntos; }


    public void actualizarDiferencia() {
        diferencia = golesAFavor - golesEnContra;
    }

    public Integer getDiferencia() {
        return diferencia;
    }

    public Integer getGolesAFavor() {
        return golesAFavor;
    }

    public Integer getGolesEnContra() { return golesEnContra; }

    public void setPartidosJugados(Integer partidosJugados) { this.partidosJugados = partidosJugados; }

    public Integer getPartidosGanados() { return partidosGanados; }

    public void setPartidosGanados(Integer partidosGanados) { this.partidosGanados = partidosGanados; }

    public Integer getPartidosEmpatados() { return partidosEmpatados; }

    public void setPartidosEmpatados(Integer partidosEmpatados) { this.partidosEmpatados = partidosEmpatados; }

    public Integer getPartidosPerdidos() { return partidosPerdidos; }

    public void setPartidosPerdidos(Integer partidosPerdidos) { this.partidosPerdidos = partidosPerdidos; }

    public void sumarPartidosJugados() { this.partidosJugados++; }

    public void sumarPartidosGanados() { this.partidosGanados++; }

    public void sumarPartidosPerdidos() { this.partidosPerdidos++; }

    public void sumarPartidosEmpatados() { this.partidosEmpatados++; }

    public void restarPartidosJugados(){ this.partidosJugados--; }

    public void restarPartidosPerdidos() { this.partidosPerdidos--; }

    public void restarPartidosEmpatados() { this.partidosEmpatados--; }

    public void restarPartidosGanados() { this.partidosGanados--; }

    public void restarPuntos(Integer puntos) { this.puntos -= puntos; }

    public void restarGolesAFavor(Integer goles) { this.golesAFavor -= goles; }

    public void sumarGolesEnContra(Integer goles) { this.golesEnContra += goles; }

    public void sumarGolesAFavor(Integer goles) { this.golesAFavor+= goles; }

    public void restarGolesEnContra(Integer goles) { this.golesEnContra-= goles; }
}
