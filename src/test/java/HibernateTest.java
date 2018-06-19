import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HibernateTest {

    private Equipo equipo1;
    private Equipo equipo2;
    private Equipo equipo3;
    private TestService testService;
    private Partido partido;

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

        this.partido = new Partido(new Date(), equipo1, equipo2, "Estadio");
        this.testService.crearEntidad(equipo1);
        this.testService.crearEntidad(equipo2);
        this.testService.crearEntidad(equipo3);
        this.testService.crearEntidad(partido);
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
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
            assertEquals("Argentina", partidoRecuperado.getLocal().getNombre());
            assertEquals("Islandia", partidoRecuperado.getVisitante().getNombre());
            return null;
        });
    }

    @Test
    public void test_VerificarResultadoPartido() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
            assertTrue(partidoRecuperado.resultado(0, 0));
            partidoRecuperado.setResultado(2, 1);
            this.testService.actualizar(partidoRecuperado);
            partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
            assertTrue(partidoRecuperado.resultado(2, 1));
            return null;
        });
    }

    @Test
    public void test_VerificarPuntosPartidoConGanador() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
            partidoRecuperado.setResultado(2, 1);
            partidoRecuperado.sumarPuntos();

            this.testService.actualizar(partidoRecuperado);
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("Argentina");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("Islandia");
            assertEquals(equipoLocalRecuperado.getGolesAFavor(), 2);
            assertEquals(equipoLocalRecuperado.getGolesEnContra(), 1);
            assertEquals(equipoLocalRecuperado.getDiferencia(), 1);
            assertEquals(equipoLocalRecuperado.getPuntos(), 3);
            assertEquals(equipoVisitanteRecuperado.getPuntos(), 0);
            return null;
        });
    }

    @Test
    public void test_VerificarPuntosPartidoEmpatado() {
        Runner.runInSession(() -> {
            Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
            partidoRecuperado.setResultado(1, 1);
            partidoRecuperado.sumarPuntos();

            this.testService.actualizar(partidoRecuperado);
            Equipo equipoLocalRecuperado = this.testService.recuperarPorNombre("Argentina");
            Equipo equipoVisitanteRecuperado = this.testService.recuperarPorNombre("Islandia");
            assertEquals(equipoLocalRecuperado.getPuntos(), 1);
            assertEquals(equipoLocalRecuperado.getGolesAFavor(), 1);
            assertEquals(equipoLocalRecuperado.getGolesEnContra(), 1);
            assertEquals(equipoLocalRecuperado.getDiferencia(), 0);
            assertEquals(equipoVisitanteRecuperado.getPuntos(), 1);
            return null;
        });
    }
}