import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LlaveTest extends HibernateTest {

    @Before
    public void prepare() {
        super.prepare();
    }

    @After
    public void cleanup() {
        super.cleanup();
    }

    @Test
    public void test_VerificarLlaveCargada() {
        Runner.runInSession(() -> {
            Llave llaveRecuperada = this.testService.recuperarEntidad(Llave.class, this.llave.getId());
            assertEquals("Argentina", llaveRecuperada.getEquipoLocal().getNombre());
            assertEquals("Islandia", llaveRecuperada.getEquipoVisitante().getNombre());
            assertEquals("Argentina", llaveRecuperada.getGanador().getNombre());
            return null;
        });
    }

}