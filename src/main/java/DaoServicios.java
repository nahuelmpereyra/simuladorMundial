import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Servicio generico para guardar o recuperar objetos de forma genreica. Usado
 * principalmente en tests
 */
public class
DaoServicios {

    public void crearEntidad(Object object) {
        Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            session.save(object);
            return null;
        });

    }

    public <T> T recuperarEntidad(Class<T> tipo, Serializable key) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            T valor = session.get(tipo, key);
            return valor;
        });
    }

    public void eliminarEntidad(Object object) {
        Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            session.delete(object);
            return null;
        });
    }

    public Equipo recuperarPorNombre(String nombre){
        return Runner.runInSession(() -> {
        Session session = Runner.getCurrentSession();
        String hql = "FROM Equipo WHERE nombre = :elNombre";
        Query<Equipo> query = session.createQuery(hql, Equipo.class)
                .setParameter("elNombre", nombre);
        if (!query.getResultList().isEmpty()){
            return query.getResultList().get(0);
        }
        else {
            return null;
        }
        });
    }



    public List<Equipo> recuperarEquipos(){
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Equipo";
            Query<Equipo> query = session.createQuery(hql, Equipo.class);
            if (!query.getResultList().isEmpty()){
                return query.getResultList();
            }
            else {
                return new ArrayList<>();
            }
        });
    }


}