import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PartidoTest extends HibernateTest {

    @Before
    public void prepare() {
        super.prepare();
    }

    @After
    public void cleanup() {
        super.cleanup();
    }

    @Test
    public void test_VerificarPartidoCargado() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            assertEquals("Argentina", partidoRecuperado.getEquipoLocal().getNombre());
            assertEquals("Islandia", partidoRecuperado.getEquipoVisitante().getNombre());
            return null;
        });
    }

    @Test
    public void test_VerificarResultadoPartido() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            assertThat(partidoRecuperado.getResultado().getGolesLocal()).isEqualTo(null);
            assertThat(partidoRecuperado.getResultado().getGolesVisitantes()).isEqualTo(null);
            this.resultado.setGolesLocal(2);
            this.resultado.setGolesVisitantes(1);
            partidoRecuperado.setResultado(resultado);
            partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            assertThat(partidoRecuperado.getResultado().getGolesLocal()).isEqualTo(2);
            assertThat(partidoRecuperado.getResultado().getGolesVisitantes()).isEqualTo(1);
            return null;
        });
    }

    @Test
    public void test_VerificarPuntosPartidoConGanador() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            this.resultado.setGolesLocal(2);
            this.resultado.setGolesVisitantes(1);
            partidoRecuperado.setResultado(resultado);
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("Argentina");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("Islandia");
            assertThat(equipoLocalRecuperado.getGolesAFavor()).isEqualTo(2);
            assertThat(equipoLocalRecuperado.getGolesEnContra()).isEqualTo(1);
            assertThat(equipoLocalRecuperado.getDiferencia()).isEqualTo(1);
            assertThat(equipoLocalRecuperado.getPuntos()).isEqualTo(3);
            assertThat(equipoVisitanteRecuperado.getPuntos()).isEqualTo(0);
            return null;
        });
    }

    @Test
    public void test_VerificarPuntosPartidoEmpatado() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            this.resultado.setGolesLocal(1);
            this.resultado.setGolesVisitantes(1);
            partidoRecuperado.setResultado(resultado);
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("Argentina");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("Islandia");
            assertThat(equipoLocalRecuperado.getPuntos()).isEqualTo(1);
            assertThat(equipoLocalRecuperado.getGolesAFavor()).isEqualTo(1);
            assertThat(equipoLocalRecuperado.getGolesEnContra()).isEqualTo(1);
            assertThat(equipoLocalRecuperado.getDiferencia()).isEqualTo(0);
            assertThat(equipoVisitanteRecuperado.getPuntos()).isEqualTo(1);

            return null;
        });
    }

    @Test
    public void test_RevertirUltimoResultado() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            this.resultado.setGolesLocal(2);
            this.resultado.setGolesVisitantes(1);
            partidoRecuperado.setResultado(resultado);
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("Argentina");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("Islandia");
            Resultado nuevoResultado = new Resultado();
            nuevoResultado.setGolesLocal(0);
            nuevoResultado.setGolesVisitantes(1);
            partidoRecuperado.revertirUltimoResultado(resultado, nuevoResultado);
            assertThat(equipoLocalRecuperado.getGolesAFavor()).isEqualTo(0);
            assertThat(equipoLocalRecuperado.getGolesEnContra()).isEqualTo(0);
            assertThat(equipoLocalRecuperado.getDiferencia()).isEqualTo(0);
            assertThat(equipoLocalRecuperado.getPuntos()).isEqualTo(0);
            assertThat(equipoVisitanteRecuperado.getGolesAFavor()).isEqualTo(0);
            assertThat(equipoVisitanteRecuperado.getGolesEnContra()).isEqualTo(0);
            assertThat(equipoVisitanteRecuperado.getDiferencia()).isEqualTo(0);
            assertThat(equipoVisitanteRecuperado.getPuntos()).isEqualTo(0);
            return null;
        });
    }
}