import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;


/**
 * Servicio generico para guardar o recuperar objetos de forma genreica. Usado
 * principalmente en tests
 */
public class
TestService {

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

    public Integer recuperarCantidadDeEquiposPorZona(String zona) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Equipo WHERE zona = :laZona";
            Query<Equipo> query = session.createQuery(hql, Equipo.class)
                    .setParameter("laZona", zona);

                return query.getResultList().size();

            
        });
    }
}