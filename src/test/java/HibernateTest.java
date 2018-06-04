import org.junit.Before;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HibernateTest {

    private Equipo equipo1;
    private TestService testService;

    @Before
    public void prepare(){
        this.testService = new TestService();
        Torneo torneo = Torneo.getTorneo();
        if(this.testService.recuperarEntidad(Torneo.class, 1) == null ){
            this.testService.crearEntidad(torneo);
        }

        this.equipo1 = new Equipo();
        equipo1.setZona("D");
        equipo1.setNombre("Islandia");
        this.testService.crearEntidad(equipo1);
        torneo.agregarEquipo(equipo1);

    }


    @Test
    public void test_recuperarEquipo() {
        Runner.runInSession(() -> {
            Equipo equipo = this.testService.recuperarEntidad(Equipo.class, "Islandia");
            assertEquals("Islandia", equipo.getNombre());
            assertEquals("D", equipo.getZona());
            //this.testService.eliminarEntidad(equipo);
            return null;
        });

    }
}