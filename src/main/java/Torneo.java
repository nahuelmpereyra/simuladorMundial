import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class Torneo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany
    private List<Equipo> equipos = new ArrayList<>();

    @Transient
    private TestService testService = new TestService();

    @Transient
    private List<String> grupos = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H");

    @Transient
    private List<Equipo> listaPrimeros = new ArrayList();

    @Transient
    private List<Equipo> listaSegundos = new ArrayList();

    private static Torneo miTorneo;

    public static Torneo getTorneo() {
        if (miTorneo == null) {
            miTorneo = new Torneo();
        }
        return miTorneo;
    }


    public List<Equipo> getEquipos() {

        return this.equipos;
    }

    public void actualizarEquipos(Resultado resultadoAnterior, Partido partido) {

        this.limpiarResultado(resultadoAnterior, partido.getEquipoLocal(), partido.getEquipoVisitante());
        this.testService.actualizar(partido);
        this.testService.actualizar(partido.getEquipoLocal());
        this.testService.actualizar(partido.getEquipoVisitante());
    }

    public void cargarResultado(Partido partido) {
        Equipo equipoLocal = partido.getEquipoLocal();
        Equipo equipoVisitante = partido.getEquipoVisitante();

        if (esGanadorLocal(partido.getResultado())) {
            this.sumarPuntos(equipoLocal, 3);
            this.sumarPartidosGanados(equipoLocal);
            this.sumarPartidosPerdidos(equipoVisitante);
        } else {
            if (esEmpate(partido.getResultado())) {
                this.sumarPuntos(equipoLocal, 1);
                this.sumarPuntos(equipoVisitante, 1);
                this.sumarPartidosEmpatados(equipoLocal);
                this.sumarPartidosEmpatados(equipoVisitante);
            } else {
                this.sumarPuntos(equipoVisitante, 3);
                this.sumarPartidosGanados(equipoVisitante);
                this.sumarPartidosPerdidos(equipoLocal);
            }
        }
        this.sumarGoles(partido.getResultado(), equipoLocal, equipoVisitante);
        equipoLocal.sumarPartidosJugados();
        equipoVisitante.sumarPartidosJugados();
        this.testService.actualizar(partido);
        this.testService.actualizar(partido.getEquipoLocal());
        this.testService.actualizar(partido.getEquipoVisitante());
    }

    private void sumarGoles(Resultado resultado, Equipo equipoLocal, Equipo equipoVisitante) {
        equipoLocal.sumarGolesAFavor(resultado.getGolesLocal());
        equipoLocal.sumarGolesEnContra(resultado.getGolesVisitantes());
        equipoVisitante.sumarGolesAFavor(resultado.getGolesVisitantes());
        equipoVisitante.sumarGolesEnContra(resultado.getGolesLocal());
        equipoLocal.actualizarDiferencia();
        equipoVisitante.actualizarDiferencia();
    }

    private void sumarPartidosEmpatados(Equipo equipo) {
        equipo.sumarPartidosEmpatados();
    }

    private void sumarPartidosPerdidos(Equipo equipo) {
        equipo.sumarPartidosPerdidos();
    }

    private void sumarPartidosGanados(Equipo equipo) {
        equipo.sumarPartidosGanados();
    }

    private void sumarPuntos(Equipo equipo, Integer puntos) {
        equipo.sumarPuntos(puntos);
    }

    private void limpiarResultado(Resultado ultimoResultado, Equipo equipoLocal, Equipo equipoVisitante) {

        if (esGanadorLocal(ultimoResultado)) {
            this.restarPuntos(equipoLocal, 3);
            this.restarPartidosGanados(equipoLocal);
            this.restarPartidosPerdidos(equipoVisitante);
        } else {
            if (esEmpate(ultimoResultado)) {
                this.restarPuntos(equipoLocal, 1);
                this.restarPuntos(equipoVisitante, 1);
                this.restarPartidosEmpatados(equipoLocal);
                this.restarPartidosEmpatados(equipoVisitante);
            } else {
                this.restarPuntos(equipoVisitante, 3);
                this.restarPartidosGanados(equipoVisitante);
                this.restarPartidosPerdidos(equipoLocal);
            }
        }
        this.restarGoles(ultimoResultado, equipoLocal, equipoVisitante);
        equipoLocal.restarPartidosJugados();
        equipoVisitante.restarPartidosJugados();

    }

    private void restarPartidosPerdidos(Equipo equipo) {
        equipo.restarPartidosPerdidos();
    }

    private void restarPartidosEmpatados(Equipo equipo) {
        equipo.restarPartidosEmpatados();
    }

    private void restarPartidosGanados(Equipo equipo) {
        equipo.restarPartidosGanados();
    }

    private void restarPuntos(Equipo equipo, Integer puntos) {
        equipo.restarPuntos(puntos);
    }

    private void restarGoles(Resultado ultimoResultado, Equipo equipoLocal, Equipo equipoVisitante) {
        equipoLocal.restarGolesAFavor(ultimoResultado.getGolesLocal());
        equipoLocal.restarGolesEnContra(ultimoResultado.getGolesVisitantes());
        equipoVisitante.restarGolesAFavor(ultimoResultado.getGolesVisitantes());
        equipoVisitante.restarGolesEnContra(ultimoResultado.getGolesLocal());
        equipoLocal.actualizarDiferencia();
        equipoVisitante.actualizarDiferencia();
    }


    private boolean esEmpate(Resultado resultado) {
        return resultado.getGolesVisitantes() == resultado.getGolesLocal();
    }

    private boolean esGanadorLocal(Resultado resultado) {
        return resultado.getGolesLocal() > resultado.getGolesVisitantes();
    }

    public void armarLlaves() {

        grupos.stream().forEach(grupo -> listaPrimeros.add(equipoEnPosicionDeGrupo(1, grupo)));
        grupos.stream().forEach(grupo -> listaSegundos.add(equipoEnPosicionDeGrupo(2, grupo)));

        listaPrimeros.stream().forEach(equipo -> this.crearLlave(equipo, this.buscarParejaDeEquipo(listaPrimeros.indexOf(equipo))));

        this.crearLlave(null,null);
        this.crearLlave(null,null);
        this.crearLlave(null,null);
        this.crearLlave(null,null);
        this.crearLlave(null,null);
        this.crearLlave(null,null);
        this.crearLlave(null,null);


    }

    private Equipo buscarParejaDeEquipo(Integer indice) {
        if (indice % 2 == 0) {
            return listaSegundos.get(indice + 1);
        } else {
            return listaSegundos.get(indice - 1);
        }
    }

    private void crearLlave(Equipo equipoLocal, Equipo equipoVisitante) {
        Llave llave = new Llave();
        llave.setEquipoLocal(equipoLocal);
        llave.setEquipoVisitante(equipoVisitante);

        this.testService.crearEntidad(llave);
    }

    private Equipo equipoEnPosicionDeGrupo(Integer posicion, String zona) {
        return this.testService.recuperarEquiposPorZona(zona).get(posicion - 1);
    }


}
