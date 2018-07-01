import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Servicio generico para guardar o recuperar objetos de forma genreica. Usado
 * principalmente en tests
 */
public class TestService {

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

    public Equipo recuperarPorNombre(String nombre) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Equipo WHERE nombre = :elNombre";
            Query<Equipo> query = session.createQuery(hql, Equipo.class)
                    .setParameter("elNombre", nombre);
            if (!query.getResultList().isEmpty()) {
                return query.getResultList().get(0);
            } else {
                return null;
            }
        });


    }

    public List<Equipo> recuperarEquiposPorZona(String zona) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Equipo WHERE zona = :laZona ORDER BY puntos DESC, diferencia DESC, golesAFavor DESC";
            Query<Equipo> query = session.createQuery(hql, Equipo.class)
                    .setParameter("laZona", zona);

            return query.getResultList();

        });
    }

    public List<Equipo> buscarEquipos(String nombre) {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String nombreFormateado = "%" + nombre + "%";
            String hql = "FROM Equipo WHERE nombre LIKE :elNombre";
            Query<Equipo> query = session.createQuery(hql, Equipo.class)
                    .setParameter("elNombre", nombreFormateado);

            return query.getResultList();

        });
    }

    public Boolean hayCabezaDeSerieEnZona(String zona) {
        return this.recuperarEquiposPorZona(zona).stream().anyMatch(equipo -> equipo.getEsCabezaDeSerie());
    }


    public List<Equipo> recuperarEquipos() {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Equipo ORDER BY puntos DESC, diferencia DESC, golesAFavor DESC";
            // En realidad, en caso de tener mismos puntos y diferencia, se decide por el resultado entre ambos equipos, y luego goles a favor.
            Query<Equipo> query = session.createQuery(hql, Equipo.class);
            return query.getResultList();
        });
    }

    public List<Partido> recuperarPartidos() {
        return Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Partido";
            Query<Partido> query = session.createQuery(hql, Partido.class);
            return query.getResultList();
        });
    }

    public void actualizar(Object object) {
        Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            session.update(object);
            return null;
        });
    }

    public void eliminarPartidosDe(Equipo equipo) {
        Runner.runInSession(() -> {
            Session session = Runner.getCurrentSession();
            String hql = "FROM Partido WHERE equipoLocal_nombre = :elNombre OR equipoVisitante_nombre = :elNombre";
            Query<Partido> query = session.createQuery(hql, Partido.class)
                    .setParameter("elNombre", equipo.getNombre());
            List<Partido> partidos = query.list();
            for (Partido partido : partidos) {
                session.delete(partido);
            }

            return null;
        });
    }
}