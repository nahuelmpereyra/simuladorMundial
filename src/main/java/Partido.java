import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @JsonbDateFormat("yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fecha;
    @OneToOne
    private Equipo local;
    @OneToOne
    private Equipo visitante;

    private String estadio;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Resultado resultado;


    public LocalDateTime getFecha() {
        return this.fecha;
    }

    public int getId() {
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

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
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

    public void setResultado(int golesLocal, int golesVisitantes) {

        this.resultado.setResultados(golesLocal, golesVisitantes);
        local.sumarGoles(golesLocal, golesVisitantes);
        visitante.sumarGoles(golesVisitantes, golesLocal);

    }

    public boolean resultado(int golesLocal, int golesVisitantes) {

        return (resultado.golesLocal == golesLocal && resultado.golesVisitantes == golesVisitantes);
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
        return resultado.golesVisitantes == resultado.golesLocal;
    }

    private boolean esGanadorLocal() {
        return resultado.golesLocal > resultado.golesVisitantes;
    }
}
