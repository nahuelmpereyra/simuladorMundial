import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.assertj.core.api.Assertions.assertThat;

public class SimuladosStepdefs {

    private Equipo equipo;
    private TestService testService = new TestService();


    @Given("^Un nuevo Equipo$")
    public void un_nuevo_Equipo() throws Throwable {
        SessionFactoryProvider.getInstance().setSessionFactoryTest();
        equipo = new Equipo();
        equipo.setNombre("Arabia Saudita");
        equipo.setZona("A");
    }

    @When("^Equipo se agrega$")
    public void equipo_se_agrega() throws Throwable {

        this.testService.crearEntidad(equipo);


    }
    @Then("^El equipo se carga en la base de equipos$")
    public void el_equipo_se_carga_en_la_base_de_equipos() throws Throwable {
        String nombreEquipo  = this.testService.recuperarEntidad(Equipo.class, equipo.getNombre()).getNombre();

        assertThat(nombreEquipo).isEqualTo("Arabia Saudita");
    }



    @When("^Seteo el Equipo como cabeza de serie$")
    public void seteo_el_Equipo_como_cabeza_de_serie() throws Throwable {
        equipo.setEsCabezaDeSerie(true);
    }

    @Then("^El Equipo figura como cabeza de serie en la base de equipos$")
    public void el_Equipo_figura_como_cabeza_de_serie_en_la_base_de_equipos() throws Throwable {
        Equipo equipoRecuperado  = this.testService.recuperarEntidad(Equipo.class, equipo.getNombre());
        assertThat(equipoRecuperado.getEsCabezaDeSerie());
        assertThat(this.testService.hayCabezaDeSerieEnZona(equipoRecuperado.getZona()));

    }




}
