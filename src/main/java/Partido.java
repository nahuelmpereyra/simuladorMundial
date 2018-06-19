import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    private Date fecha;
    @OneToOne
    private Equipo local;
    @OneToOne
    private Equipo visitante;

    private String estadio;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Resultado resultado;


    public Partido(Date fecha, Equipo equipo1, Equipo equipo2, String estadio) {
        this.fecha = fecha;
        this.local = equipo1;
        this.visitante = equipo2;
        this.estadio = estadio;
        this.resultado = new Resultado();

    }

    public Partido() {
    }

    public Date getFecha() {
        return this.fecha;
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
