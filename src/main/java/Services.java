import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("")
public class Services {
    TestService testService = new TestService();


    @GET
    @Path("/equipos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Equipo> getEquipos() {
        return this.testService.recuperarEquipos();
    }

    @GET
    @Path("/equipos/{zona}")
    @Produces("application/json")
    public List<Equipo> getEquiposByZona(@PathParam("zona") String zona) {
        return this.testService.recuperarEquiposPorZona(zona);
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

        Equipo equipoRecuperado = testService.recuperarPorNombre(equipo.getNombre());

        try {
            if (equipoRecuperado != null) {
                throw new Exception("Equipo ya existente");
            } else {
                if (equipo.getEsCabezaDeSerie() && this.testService.hayCabezaDeSerieEnZona(equipo.getZona())) {

                    throw new Exception("Ya existe un cabeza de serie en el grupo " + equipo.getZona());
                } else {
                    if (testService.recuperarEquiposPorZona(equipo.getZona()).size() < 4) {
                        this.testService.crearEntidad(equipo);


                        String nombre = "\"" + equipo.getNombre() + "\"";
                        String zona = "\"" + equipo.getZona() + "\"";
                        String ok = String.format("{\n" + "\"equipo\": " + nombre + ",\n" + "\"zona\": " + zona + "\n" + "}");
                        return Response.status(Response.Status.OK)
                                .entity(ok)
                                .build();
                    } else {
                        throw new Exception("No puede haber mas de 4 equipos por zona");
                    }
                }
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(getErrorJson(e.getMessage()))
                    .build();
        }

    }

    private String getErrorJson(String message) {
        String msg = "\"" + message + "\"";
        return String.format("{\n" + "\"error\": " + msg + "\n" + "}");

    }
}



