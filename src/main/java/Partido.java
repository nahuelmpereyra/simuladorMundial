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


    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;

    @OneToOne
    private Equipo equipoLocal;

    @OneToOne
    private Equipo equipoVisitante;

    private String estadio;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Resultado resultado = new Resultado();


    public LocalDateTime getFecha() {
        return this.fecha;
    }

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

    public Resultado getResultado() {
        return resultado;
    }

    public Equipo getEquipoLocal() {
        return this.equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return this.equipoVisitante;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public void setResultado(Integer golesLocal, Integer golesVisitantes) {

        this.resultado.setGolesLocal(golesLocal);
        this.resultado.setGolesVisitantes(golesVisitantes);
        equipoLocal.sumarGoles(this.resultado.getGolesLocal(), this.resultado.getGolesVisitantes());
        equipoVisitante.sumarGoles(this.resultado.getGolesVisitantes(), this.resultado.getGolesLocal());
        this.sumarPuntos();

    }


    public void sumarPuntos() {
        if (esGanadorLocal()) {
            equipoLocal.sumarPuntos(3);
        } else {
            if (esEmpate()) {
                equipoLocal.sumarPuntos(1);
                equipoVisitante.sumarPuntos(1);
            } else {
                equipoVisitante.sumarPuntos(3);
            }
        }


    }

    private boolean esEmpate() {
        return resultado.getGolesVisitantes() == resultado.getGolesLocal();
    }

    private boolean esGanadorLocal() {
        return resultado.getGolesLocal() > resultado.getGolesVisitantes();
    }
}
