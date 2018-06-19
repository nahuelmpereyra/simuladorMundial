import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class SimuladosStepdefs {

    private Equipo equipo1;
    private Equipo equipo2;
    private Partido partido;
    private TestService testService = new TestService();


    @Given("^Un nuevo Equipo$")
    public void un_nuevo_Equipo() throws Throwable {
        SessionFactoryProvider.getInstance().setSessionFactoryTest();
        equipo1 = new Equipo();
        equipo1.setNombre("Arabia Saudita");
        equipo1.setZona("A");
    }

    @When("^Equipo se agrega$")
    public void equipo_se_agrega() throws Throwable {

        this.testService.crearEntidad(equipo1);


    }

    @Then("^El equipo se carga en la base de equipos$")
    public void el_equipo_se_carga_en_la_base_de_equipos() throws Throwable {
        String nombreEquipo = this.testService.recuperarEntidad(Equipo.class, equipo1.getNombre()).getNombre();

        assertThat(nombreEquipo).isEqualTo("Arabia Saudita");
    }


    @When("^Seteo el Equipo como cabeza de serie$")
    public void seteo_el_Equipo_como_cabeza_de_serie() throws Throwable {
        equipo1.setEsCabezaDeSerie(true);
    }

    @Then("^El Equipo figura como cabeza de serie en la base de equipos$")
    public void el_Equipo_figura_como_cabeza_de_serie_en_la_base_de_equipos() throws Throwable {
        Equipo equipoRecuperado = this.testService.recuperarEntidad(Equipo.class, equipo1.getNombre());
        assertThat(equipoRecuperado.getEsCabezaDeSerie());
        assertThat(this.testService.hayCabezaDeSerieEnZona(equipoRecuperado.getZona()));

    }

    @Given("^Otro nuevo Equipo$")
    public void otro_nuevo_Equipo() throws Throwable {
        equipo2 = new Equipo();
        equipo2.setNombre("Rusia");
        equipo2.setZona("A");
        this.testService.crearEntidad(equipo2);
    }

    @Given("^Un nuevo Partido$")
    public void un_nuevo_Partido() throws Throwable {
        partido = new Partido(new Date(), equipo1, equipo2, "Moscu");

    }

    //@When("^Otro nuevo equipo se agrega$")
    //public void otro_nuevo_equipo_se_agrega() throws Throwable {
    //       this.testService.crearEntidad(equipo2);
    //}


    @When("^Partido se agrega$")
    public void partido_se_agrega() throws Throwable {
        this.testService.crearEntidad(partido);

    }

    @Then("^El Partido se carga en la base de partidos$")
    public void el_Partido_se_carga_en_la_base_de_partidos() throws Throwable {
        Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
        //System.out.println("Partido recuperado: " + partidoRecuperado);
        //System.out.println("Partido.estadio: " + partido.getEstadio());
        assertThat(partidoRecuperado.getLocal().getNombre()).isEqualTo("Arabia Saudita");
        assertThat(partidoRecuperado.getVisitante().getNombre()).isEqualTo("Rusia");
    }


    @When("^Seteo resultado del partido$")
    public void seteo_resultado_del_partido() throws Throwable {
        partido.setResultado(2, 1);
    }

    @Then("^El Partido tiene un resultado$")
    public void el_Partido_tiene_un_resultado() throws Throwable {
        Partido partidoRecuperado = this.testService.recuperarEntidad(Partido.class, this.partido.id);
        assertThat(partidoRecuperado.resultado(2, 1));
    }


}
