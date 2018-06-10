import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Torneo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany
    private List<Equipo> equipos = new ArrayList<>();
    private static Torneo miTorneo;

    public static Torneo getTorneo(){
        if (miTorneo == null){
            miTorneo = new Torneo();
        }
        return miTorneo;
    }


    public List<Equipo> getEquipos() {

        return this.equipos;
    }

    public void agregarEquipo(Equipo equipo) {
        this.equipos.add(equipo);
    }
}
