import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Supplier;

public class Runner {

    private static final ThreadLocal<Session> CONTEXTO = new ThreadLocal<>();

    public static <T> T runInSession(Supplier<T> bloque) {
        if (CONTEXTO.get() != null) {
            return bloque.get();
        }

        Session session = null;
        Transaction tx = null;

        try {
            session = SessionFactoryProvider.getInstance().createSession();
            tx = session.beginTransaction();

            CONTEXTO.set(session);

            T resultado = bloque.get();

            tx.commit();
            return resultado;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null) {
                CONTEXTO.set(null);
                session.close();
            }
        }
    }

    public static Session getCurrentSession() {
        Session session = CONTEXTO.get();
        if (session == null) {
            throw new RuntimeException("No hay ninguna session en el contexto");
        }
        return session;
    }

}