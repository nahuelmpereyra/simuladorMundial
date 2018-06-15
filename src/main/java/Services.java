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

        Equipo equipoRecuperado = testService.recuperarPorNombre(equipo.getNombre());

        try {
            if (equipoRecuperado != null) {
                throw new Exception("Equipo ya existente");
            } else {
                this.testService.crearEntidad(equipo);

                String nombre = "\"" + equipo.getNombre() + "\"";
                String zona = "\"" + equipo.getZona() + "\"";
                String ok = String.format("{\n" + "\"equipo\": " + nombre + ",\n" + "\"zona\": " + zona + "\n" + "}");
                return Response.status(Response.Status.OK)
                        .entity(ok)
                        .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(getErrorJson(e.getMessage()))
                    .build();
        }

    }

//    private boolean esZonaValida(String zona) {
//        return this.zonasValidas.contains(zona);
//    }


    private String getErrorJson(String message) {
        String msg = "\"" + message + "\"";
        return String.format("{\n" + "\"error\": " + msg + "\n" + "}");

    }
}



