import gherkin.deps.com.google.gson.JsonObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("")
public class Services {
    // The Java method will process HTTP GET requests
    DaoServicios daoServicios = new DaoServicios();


    @GET
    @Path("/index")
    @Produces("application/json")
    public List<Equipo> getEquipos() {

        List<Equipo> equipos=this.daoServicios.recuperarEquipos();
        return equipos;

    }

    @GET
    @Path("{pais}")
    @Produces("application/json")
    public Equipo getEquipobyId(@PathParam("pais") String pais) {
        Equipo equipo1 = this.daoServicios.recuperarEntidad(Equipo.class, pais);
        return equipo1;
    }

    @POST
    @Path("/agregarequipo")
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEquipo(Equipo equipo) {
        if (daoServicios.recuperarPorNombre(equipo.getNombre()) == null) {
            this.daoServicios.crearEntidad(equipo);

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



