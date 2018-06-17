import javax.persistence.*;
import java.util.Date;


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


    public Partido(Date fecha, Equipo equipo1, Equipo equipo2, String estadio) {
        this.fecha = fecha;
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.estadio = estadio;
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

}
