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
        Equipo local = new Equipo();
        local.setNombre("local");
        this.testService.crearEntidad(local);
        Equipo visitante = new Equipo();
        visitante.setNombre("visitante");
        this.testService.crearEntidad(visitante);
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

            Equipo localRecuperado = this.testService.recuperarPorNombre("local");
            Equipo visitanteRecuperado = this.testService.recuperarPorNombre("visitante");

            assertThat(localRecuperado.getPartidosGanados()).isEqualTo(0);

            Partido partido = new Partido();
            partido.setEquipoLocal(localRecuperado);
            partido.setEquipoVisitante(visitanteRecuperado);
            Resultado resultado = new Resultado();
            resultado.setGolesLocal(1);
            resultado.setGolesVisitantes(0);
            this.testService.crearEntidad(resultado);
            this.testService.crearEntidad(partido);
            partido.setResultado(resultado);
            assertThat(localRecuperado.getPartidosGanados()).isEqualTo(1);
            return null;
        });
    }

    @Test
    public void test_PartidosEmpatadosDeUnEquipo() {
        Runner.runInSession(() -> {

            Equipo localRecuperado = this.testService.recuperarPorNombre("local");
            Equipo visitanteRecuperado = this.testService.recuperarPorNombre("visitante");

            assertThat(localRecuperado.getPartidosEmpatados()).isEqualTo(0);

            Partido partido = new Partido();
            partido.setEquipoLocal(localRecuperado);
            partido.setEquipoVisitante(visitanteRecuperado);
            Resultado resultado = new Resultado();
            resultado.setGolesLocal(1);
            resultado.setGolesVisitantes(1);
            this.testService.crearEntidad(resultado);
            this.testService.crearEntidad(partido);
            partido.setResultado(resultado);
            assertThat(localRecuperado.getPartidosEmpatados()).isEqualTo(1);
            return null;
        });
    }

    @Test
    public void test_PartidosPerdidosDeUnEquipo() {
        Runner.runInSession(() -> {
            Equipo localRecuperado = this.testService.recuperarPorNombre("local");
            Equipo visitanteRecuperado = this.testService.recuperarPorNombre("visitante");

            assertThat(localRecuperado.getPartidosPerdidos()).isEqualTo(0);

            Partido partido = new Partido();
            partido.setEquipoLocal(localRecuperado);
            partido.setEquipoVisitante(visitanteRecuperado);
            Resultado resultado = new Resultado();
            resultado.setGolesLocal(0);
            resultado.setGolesVisitantes(1);
            this.testService.crearEntidad(resultado);
            this.testService.crearEntidad(partido);
            partido.setResultado(resultado);
            assertThat(localRecuperado.getPartidosPerdidos()).isEqualTo(1);
            return null;
        });
    }
}