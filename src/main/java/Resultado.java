import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    private Integer golesLocal = null;
    private Integer golesVisitantes = null;


    public void setGolesLocal(Integer golesLocal) {
        this.golesLocal = golesLocal;
    }

    public void setGolesVisitantes(Integer golesVisitantes) {
        this.golesVisitantes = golesVisitantes;
    }


    public Integer getGolesLocal() {
        return golesLocal;
    }

    public Integer getGolesVisitantes() {
        return golesVisitantes;
    }


}
