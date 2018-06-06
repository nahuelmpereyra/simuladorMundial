import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorld {
    // The Java method will process HTTP GET requests
    TestService testService = new TestService();
    @GET
    // The Java method will produce content identified by the MIME Media type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
       Equipo equipo1 = this.testService.recuperarEntidad(Equipo.class, "Argentina");
      // Equipo equipo2 = this.testService.recuperarEntidad(Equipo.class, "Islandia");
       //Equipo equipo3 = this.testService.recuperarEntidad(Equipo.class, "Croacia");
       return equipo1.getNombre(); //+ equipo2.getNombre();// + equipo3.getNombre();

    }
}