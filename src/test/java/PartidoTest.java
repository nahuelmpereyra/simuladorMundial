import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class PartidoTest extends HibernateTest {

    protected Partido partido;
    protected Resultado resultado;

    @Before
    public void prepare() {
        super.prepare();
        Equipo local = new Equipo();
        local.setNombre("local");
        this.testService.crearEntidad(local);
        Equipo visitante = new Equipo();
        visitante.setNombre("visitante");
        this.testService.crearEntidad(visitante);
        partido = new Partido();
        partido.setEquipoLocal(local);
        partido.setEquipoVisitante(visitante);
        resultado = new Resultado();
        resultado.setGolesLocal(2);
        resultado.setGolesVisitantes(1);
        this.testService.crearEntidad(partido);
        this.testService.crearEntidad(resultado);
    }

    @After
    public void cleanup() {
        super.cleanup();
    }

    @Test
    public void test_VerificarPartidoCargado() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, partido.getId());
            assertEquals("local", partidoRecuperado.getEquipoLocal().getNombre());
            assertEquals("visitante", partidoRecuperado.getEquipoVisitante().getNombre());
            return null;
        });
    }

    @Test
    public void test_VerificarResultadoPartido() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.getId());
            assertThat(partidoRecuperado.getResultado().getGolesLocal()).isEqualTo(null);
            assertThat(partidoRecuperado.getResultado().getGolesVisitantes()).isEqualTo(null);

            partidoRecuperado.setResultado(this.resultado);
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
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("local");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("visitante");
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
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("local");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("visitante");
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
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("local");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("visitante");
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