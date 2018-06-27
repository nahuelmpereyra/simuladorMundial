import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class HibernateTest {

    private Equipo equipo1;
    private Equipo equipo2;
    private Equipo equipo3;
    private TestService testService;
    private Partido partido;
    private Resultado resultado;

    @Before
    public void prepare() {
        System.out.println("Empezando el Before");

        SessionFactoryProvider.getInstance().setSessionFactoryTest();


        this.testService = new TestService();
        Torneo torneo = Torneo.getTorneo();
        if (this.testService.recuperarEntidad(Torneo.class, 1) == null) {
            this.testService.crearEntidad(torneo);
        }

        this.equipo1 = new Equipo();
        this.equipo2 = new Equipo();
        this.equipo3 = new Equipo();
        equipo1.setZona("D");
        equipo1.setNombre("Argentina");
        equipo2.setZona("D");
        equipo2.setNombre("Islandia");
        equipo3.setZona("D");
        equipo3.setNombre("Croacia");

        this.partido = new Partido();
        this.partido.setFecha(LocalDateTime.now());
        this.partido.setEquipoLocal(equipo1);
        this.partido.setEquipoVisitante(equipo2);
        this.partido.setEstadio("Estadio");
        this.testService.crearEntidad(equipo1);
        this.testService.crearEntidad(equipo2);
        this.testService.crearEntidad(equipo3);
        this.testService.crearEntidad(partido);
        this.resultado = new Resultado();
        System.out.println("Terminando el Before");
    }

    @After
    public void cleanup() {
        SessionFactoryProvider.destroy();
        System.out.println("Terminando el After");
    }

    @Test
    public void test_recuperarEquipo() {
        Runner.runInSession(() -> {

            Equipo equipo = this.testService.recuperarEntidad(Equipo.class, "Islandia");
            assertEquals("Islandia", equipo.getNombre());
            assertEquals("D", equipo.getZona());
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
}