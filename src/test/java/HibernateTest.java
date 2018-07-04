import org.junit.After;
import org.junit.Before;

import java.time.LocalDateTime;

public class HibernateTest {


    protected Equipo equipo1;
    protected Equipo equipo2;
    protected Equipo equipo3;
    protected TestService testService;
    protected Partido partido;
    protected Resultado resultado;
    protected Llave llave;

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
        this.resultado = new Resultado();
        this.llave = new Llave();
        this.llave.setEquipoLocal(equipo1);
        this.llave.setEquipoVisitante(equipo2);
        this.llave.setGanador(equipo1);
        this.testService.crearEntidad(equipo1);
        this.testService.crearEntidad(equipo2);
        this.testService.crearEntidad(equipo3);
        this.testService.crearEntidad(partido);
        this.testService.crearEntidad(resultado);
        this.testService.crearEntidad(llave);
        System.out.println("Terminando el Before");
    }

    @After
    public void cleanup() {
        SessionFactoryProvider.destroy();
        System.out.println("Terminando el After");
    }
}