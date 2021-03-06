import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonbDateFormat("dd/MM/yyyy HH:mm")
    private LocalDateTime fecha;

    @OneToOne
    private Equipo equipoLocal;

    @OneToOne
    private Equipo equipoVisitante;

    private String estadio;

    private String ciudad;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Resultado resultado = new Resultado();


    public Integer getId() {
        return id;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setEquipoLocal(Equipo equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public void setEquipoVisitante(Equipo equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Resultado getResultado() {
        return resultado;
    }

    public Equipo getEquipoLocal() {
        return this.equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
        Torneo.getTorneo().cargarResultado(this);
    }

    public void revertirUltimoResultado(Resultado resultadoAnterior, Resultado resultado) {
        this.resultado = resultado;
        Torneo.getTorneo().actualizarEquipos(resultadoAnterior, this);
    }
    
}
