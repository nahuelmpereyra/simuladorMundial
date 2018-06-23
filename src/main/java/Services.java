import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("")
public class Services {
    TestService testService = new TestService();
    Gson gson = new Gson();


    @GET
    @Path("/equipos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Equipo> getEquipos() {
        return this.testService.recuperarEquipos();
    }

    @GET
    @Path("/partidos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Partido> getPartidos() {
        return this.testService.recuperarPartidos();
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
            if (equipo.getNombre() == null || equipo.getZona() == null || equipo.getNombre() == "" || equipo.getZona() == "") {
                throw new Exception("Faltan datos del equipo a agregar");
            }
            if (equipoRecuperado != null) {
                throw new Exception("Equipo ya existente");
            } else {
                if (equipo.getEsCabezaDeSerie() && this.testService.hayCabezaDeSerieEnZona(equipo.getZona())) {

                    throw new Exception("Ya existe un cabeza de serie en el grupo " + equipo.getZona());
                } else {
                    if (testService.recuperarEquiposPorZona(equipo.getZona()).size() < 4) {
                        this.testService.crearEntidad(equipo);

                        String ok = gson.toJson(equipo);
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

    @POST
    @Path("/agregarpartido")
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response createPartido(Partido partido) {

        Equipo equipoLocal = testService.recuperarPorNombre(partido.getLocal().getNombre());
        Equipo equipoVisitante = testService.recuperarPorNombre(partido.getVisitante().getNombre());


        try {
            if (equipoLocal == null || equipoVisitante == null) {
                throw new Exception("Al menos uno de los equipos no existe");
            } else {
                this.testService.crearEntidad(partido);
                String ok = gson.toJson(partido);
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


    @POST
    @Path("/partidos/{idPartido}")
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response cargarResultado(@PathParam("idPartido") Integer id, Resultado resultado) {

        Partido partidoRecuperado = testService.recuperarEntidad(Partido.class, id);

        try {
            if (partidoRecuperado == null) {
                throw new Exception("El partido no existe");
            } else {

                partidoRecuperado.setResultado(resultado.getGolesLocal(), resultado.getGolesVisitantes());
                this.testService.actualizar(partidoRecuperado);
                this.testService.actualizar(partidoRecuperado.getLocal());
                this.testService.actualizar(partidoRecuperado.getVisitante());
                partidoRecuperado = testService.recuperarEntidad(Partido.class, id);
                String ok = gson.toJson(partidoRecuperado);
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


    @POST
    @Path("/eliminarequipo")
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarEquipo(Equipo equipo) {

        Equipo equipoRecuperado = testService.recuperarPorNombre(equipo.getNombre());
        try {
            if (equipoRecuperado == null) {
                throw new Exception("No existe el equipo");
            } else {
                this.testService.eliminarEntidad(equipo);

                String ok = gson.toJson("Equipo eliminado");
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


    @PUT
    @Path("/editarequipo")
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response editarEquipo(Equipo equipoAEditar) {

        Equipo equipoRecuperado = testService.recuperarPorNombre(equipoAEditar.getNombre());
        try {
            if (equipoRecuperado == null) {
                throw new Exception("No existe un equipo con el nombre " + equipoAEditar.getNombre());
            } else {
                if (equipoAEditar.getEsCabezaDeSerie() && this.testService.hayCabezaDeSerieEnZona(equipoAEditar.getZona()) && !equipoRecuperado.getEsCabezaDeSerie()) {

                    throw new Exception("Ya existe un cabeza de serie en el grupo " + equipoAEditar.getZona());
                } else {
                    if (testService.recuperarEquiposPorZona(equipoAEditar.getZona()).size() == 4 && (equipoRecuperado.getZona() != equipoAEditar.getZona())) {
                        throw new Exception("La zona " + equipoAEditar.getZona() + " ya tiene 4 equipos");
                    }
                }
                this.testService.actualizar(equipoAEditar);

                String ok = gson.toJson("Equipo editado");
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

    @DELETE
    @Path("/eliminarpartido/{idPartido}")
    @Consumes({"application/json"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarPartido(@PathParam("idPartido") Integer idPartido) {

        Partido partidoRecuperado = testService.recuperarEntidad(Partido.class, idPartido);

        try {
            if (partidoRecuperado == null) {
                throw new Exception("No existe el partido");
            } else {
                this.testService.eliminarEntidad(partidoRecuperado);

                String ok = gson.toJson("Partido entre " + partidoRecuperado.getLocal().getNombre() + " y " + partidoRecuperado.getVisitante().getNombre() + " eliminado con éxito");
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

    @GET
    @Path("/equipos/nombre={nombre}")
    @Produces("application/json")
    public List<Equipo> buscarEquipos(@PathParam("nombre") String nombre) {
        return this.testService.buscarEquipos(nombre);
    }

    private String getErrorJson(String message) {
        String msg = "\"" + message + "\"";
        return String.format("{\n" + "\"error\": " + msg + "\n" + "}");

    }
}



