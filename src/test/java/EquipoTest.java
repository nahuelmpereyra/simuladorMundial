import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EquipoTest extends HibernateTest {

    @Before
    public void prepare() {
        super.prepare();
    }

    @After
    public void cleanup() {
        super.cleanup();
    }

    @Test
    public void test_recuperarEquipo() {
        Runner.runInSession(() -> {

            Equipo equipo = this.testService.recuperarEntidad(Equipo.class, "Islandia");
            assertEquals("Islandia", equipo.getNombre());
            assertEquals("D", equipo.getZona());
            assertFalse(equipo.getEsCabezaDeSerie());
            System.out.println("Terminando test_recuperarEquipo");

            return null;
        });

    }

    @Test
    public void test_recuperarEquipoPorNombre() {
        Runner.runInSession(() -> {
            Equipo equipo = this.testService.recuperarPorNombre("Islandia");
            assertEquals("Islandia", equipo.getNombre());
            assertEquals("D", equipo.getZona());
            System.out.println("Terminando test_recuperarEquipoPorNombre");
            return null;
        });
    }

    @Test
    public void test_RecuperarEquipoInexistente() {
        Runner.runInSession(() -> {
            Equipo equipo = this.testService.recuperarPorNombre("Chipre");
            assertEquals(null, equipo);
            System.out.println("Terminando test_recuperarEquipoInexistente");
            return null;
        });
    }


    @Test
    public void test_PartidosGanadosDeUnEquipo() {
        Runner.runInSession(() -> {
            Equipo equipo = this.testService.recuperarPorNombre("Argentina");
            assertThat(equipo.getPartidosGanados()).isEqualTo(0);
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            this.resultado.setGolesLocal(1);
            this.resultado.setGolesVisitantes(0);
            partidoRecuperado.setResultado(resultado);
            assertThat(equipo.getPartidosGanados()).isEqualTo(1);
            return null;
        });
    }

    @Test
    public void test_PartidosEmpatadosDeUnEquipo() {
        Runner.runInSession(() -> {
            Equipo equipo = this.testService.recuperarPorNombre("Argentina");
            assertThat(equipo.getPartidosEmpatados()).isEqualTo(0);
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            this.resultado.setGolesLocal(1);
            this.resultado.setGolesVisitantes(1);
            partidoRecuperado.setResultado(resultado);
            assertThat(equipo.getPartidosEmpatados()).isEqualTo(1);
            return null;
        });
    }

    @Test
    public void test_PartidosPerdidosDeUnEquipo() {
        Runner.runInSession(() -> {
            Equipo equipo = this.testService.recuperarPorNombre("Argentina");
            assertThat(equipo.getPartidosPerdidos()).isEqualTo(0);
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            this.resultado.setGolesLocal(0);
            this.resultado.setGolesVisitantes(1);
            partidoRecuperado.setResultado(resultado);
            assertThat(equipo.getPartidosPerdidos()).isEqualTo(1);
            return null;
        });
    }
}