import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    private Date fecha;
    @OneToOne
    private Equipo equipo1;
    @OneToOne
    private Equipo equipo2;

    private String estadio;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Resultado resultado;


    public Partido(Date fecha, Equipo equipo1, Equipo equipo2, String estadio) {
        this.fecha = fecha;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.estadio = estadio;
        this.resultado= new Resultado();

    }

    public Partido() {
    }

    public Date getFecha() {
        return this.fecha;
    }

    public Equipo getEquipo1() {
        return this.equipo1;
    }

    public Equipo getEquipo2() {
        return this.equipo2;
    }

    public String getEstadio() {
        return this.estadio;
    }

    public void setResultado(int golesLocal, int golesVisitantes) {

        this.resultado.setResultados(golesLocal,golesVisitantes);

    }

    public boolean resultado(int golesLocal,int golesVisitantes) {

        return (resultado.golesLocal== golesLocal && resultado.golesVisitantes==golesVisitantes);
    }
}
