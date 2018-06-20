import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public void setGolesVisitantes(int golesVisitantes) {
        this.golesVisitantes = golesVisitantes;
    }

    private int golesLocal;
    private int golesVisitantes;


    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitantes() {
        return golesVisitantes;
    }
    

}
