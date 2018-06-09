import gherkin.deps.com.google.gson.JsonObject;

import javax.persistence.PostRemove;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("")
public class Services{
    // The Java method will process HTTP GET requests
    TestService testService = new TestService();


    @GET
    @Path("/index")
    @Produces("application/json")
    public Equipo getEquipos() {
        // Return some cliched textual content
        Equipo equipo1 = this.testService.recuperarEntidad(Equipo.class, "Argentina");
        Equipo equipo2 = this.testService.recuperarEntidad(Equipo.class, "Islandia");
//        Equipo equipo3 = this.testService.recuperarEntidad(Equipo.class, "Croacia");
        return equipo1;

    }

    @GET
    @Path("{pais}")
    @Produces("application/json")
    public Equipo getEquipobyId(@PathParam("pais") String pais) {
        Equipo equipo1 = this.testService.recuperarEntidad(Equipo.class, pais);
        return equipo1;
    }

    @POST
    @Path("/agregarequipo")
    @Consumes({"application/json"})
    @Produces({"text/plain"})
    public Response createEquipo(Equipo equipo){

        this.testService.crearEntidad(equipo);

        return Response.ok(this.testService.recuperarEntidad(Equipo.class, equipo.getNombre()).getNombre() + " agregado con éxito").build();
//        return Response.ok(this.testService.recuperarEntidad(Equipo.class, equipo.getNombre())).build();
    }



}
// The Java class will be hosted at the URI path "/index"



