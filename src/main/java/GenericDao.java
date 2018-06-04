import org.hibernate.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GenericDao<T> {

    private Class<T> aClass;

    public GenericDao(Class<T> aClass) {
        super();
        this.aClass = aClass;
    }

    public GenericDao() {

    }

    public T recuperar(Serializable propiedad) {
        Session session = Runner.getCurrentSession();
        return session.get(aClass, propiedad);
    }

    public void guardar(T transientObject) {
        Session session = Runner.getCurrentSession();
        session.save(transientObject);

    }

    public List<T> recuperarTodos(List<Integer> numeros) {
        List<T> todos = new ArrayList<T>();
        for (Integer nro : numeros) {
            todos.add(this.recuperar(nro));
        }
        return todos;
    }

}