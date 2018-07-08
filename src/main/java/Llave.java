import javax.persistence.*;

@Entity
public class Llave {

    @Id
    private String id;
    @OneToOne
    private Equipo equipoLocal;

    @OneToOne
    private Equipo equipoVisitante;

    @OneToOne
    private Equipo ganador;

    public String getId() {
        return id;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Equipo getGanador() {
        return ganador;
    }

    public void setGanador(Equipo ganador) {
        this.ganador = ganador;
    }

    public void setId(String id) {this.id = id;}
}
