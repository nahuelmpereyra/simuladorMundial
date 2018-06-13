import gherkin.deps.com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("")
public class Services {
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEquipo(Equipo equipo) {
        if (testService.recuperarPorNombre(equipo.getNombre()) == null) {
            this.testService.crearEntidad(equipo);

            JsonObject okay = new JsonObject();
            okay.addProperty("nombre", equipo.getNombre());
            okay.addProperty("zona", equipo.getZona());
            return Response.status(Response.Status.OK)
                    .entity(okay.toString())
                    .build();
        } else {
            JsonObject error = new JsonObject();
            error.addProperty("error", "Equipo ya existente");
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error.toString())
                    .build();
        }

    }


}



