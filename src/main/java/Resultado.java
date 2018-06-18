import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resultado {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        public int id;

        int golesLocal;
        int golesVisitantes;

        public void setResultados(int local,int visitante){
            this.golesLocal= local;
            this.golesVisitantes= visitante;
        }
        public boolean resultado(int local, int visitante){
            return golesLocal== local && golesVisitantes==visitante;
        }
}
