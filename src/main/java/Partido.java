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
    private Equipo local;
    @OneToOne
    private Equipo visitante;

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

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public Resultado getResultado() {
        return resultado;
    }

    public Equipo getLocal() {
        return this.local;
    }

    public Equipo getVisitante() {
        return this.visitante;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public void setResultado(Integer golesLocal, Integer golesVisitantes) {

        this.resultado.setGolesLocal(golesLocal);
        this.resultado.setGolesVisitantes(golesVisitantes);
        local.sumarGoles(this.resultado.getGolesLocal(), this.resultado.getGolesVisitantes());
        visitante.sumarGoles(this.resultado.getGolesVisitantes(), this.resultado.getGolesLocal());
        this.sumarPuntos();

    }


    public void sumarPuntos() {
        if (esGanadorLocal()) {
            local.sumarPuntos(3);
        } else {
            if (esEmpate()) {
                local.sumarPuntos(1);
                visitante.sumarPuntos(1);
            } else {
                visitante.sumarPuntos(3);
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
