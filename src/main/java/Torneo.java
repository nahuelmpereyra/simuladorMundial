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

    public void armarLlavesDelTorneo() {

        //Llaves octavos
        Llave llaveOctavos1 = new Llave();
        Llave llaveOctavos2 = new Llave();
        Llave llaveOctavos3 = new Llave();
        Llave llaveOctavos4 = new Llave();
        Llave llaveOctavos5 = new Llave();
        Llave llaveOctavos6 = new Llave();
        Llave llaveOctavos7 = new Llave();
        Llave llaveOctavos8 = new Llave();

        //Llaves cuartos
        Llave llaveCuartos1 = new Llave();
        Llave llaveCuartos2 = new Llave();
        Llave llaveCuartos3 = new Llave();
        Llave llaveCuartos4 = new Llave();

        //Llaves semi
        Llave llaveSemi1 = new Llave();
        Llave llaveSemi2 = new Llave();

        //Llaves final
        Llave llaveFinal = new Llave();


        //identificando Llaves octavos
        llaveOctavos1.setId("octavos1");
        llaveOctavos2.setId("octavos2");
        llaveOctavos3.setId("octavos3");
        llaveOctavos4.setId("octavos4");
        llaveOctavos5.setId("octavos5");
        llaveOctavos6.setId("octavos6");
        llaveOctavos7.setId("octavos7");
        llaveOctavos8.setId("octavos8");

        //Llaves cuartos
        llaveCuartos1.setId("cuartos1");
        llaveCuartos2.setId("cuartos2");
        llaveCuartos3.setId("cuartos3");
        llaveCuartos4.setId("cuartos4");

        //Llaves semi
        llaveSemi1.setId("semi1");
        llaveSemi2.setId("semi2");

        //Llaves final
        llaveFinal.setId("final");

        llaveOctavos1.setEquipoLocal(equipoEnPosicionDeGrupo(1,"A"));
        llaveOctavos1.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"B"));
        llaveOctavos2.setEquipoLocal(equipoEnPosicionDeGrupo(1,"C"));
        llaveOctavos2.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"D"));

        llaveOctavos3.setEquipoLocal(equipoEnPosicionDeGrupo(1,"E"));
        llaveOctavos3.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"F"));
        llaveOctavos4.setEquipoLocal(equipoEnPosicionDeGrupo(1,"G"));
        llaveOctavos4.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"H"));

        llaveOctavos5.setEquipoLocal(equipoEnPosicionDeGrupo(1,"B"));
        llaveOctavos5.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"A"));
        llaveOctavos6.setEquipoLocal(equipoEnPosicionDeGrupo(1,"D"));
        llaveOctavos6.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"C"));

        llaveOctavos7.setEquipoLocal(equipoEnPosicionDeGrupo(1,"F"));
        llaveOctavos7.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"E"));
        llaveOctavos8.setEquipoLocal(equipoEnPosicionDeGrupo(1,"H"));
        llaveOctavos8.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"G"));

        this.testService.crearEntidad(llaveOctavos1);
        this.testService.crearEntidad(llaveOctavos2);
        this.testService.crearEntidad(llaveOctavos3);
        this.testService.crearEntidad(llaveOctavos4);
        this.testService.crearEntidad(llaveOctavos5);
        this.testService.crearEntidad(llaveOctavos6);
        this.testService.crearEntidad(llaveOctavos7);
        this.testService.crearEntidad(llaveOctavos8);
        this.testService.crearEntidad(llaveCuartos1);
        this.testService.crearEntidad(llaveCuartos2);
        this.testService.crearEntidad(llaveCuartos3);
        this.testService.crearEntidad(llaveCuartos4);
        this.testService.crearEntidad(llaveSemi1);
        this.testService.crearEntidad(llaveSemi2);
        this.testService.crearEntidad(llaveFinal);

    }

    public void actualizarLlaves(){

        Llave llaveOctavos1 = this.testService.recuperarEntidad(Llave.class, "octavos1");
        llaveOctavos1.setEquipoLocal(equipoEnPosicionDeGrupo(1,"A"));
        llaveOctavos1.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"B"));
        this.testService.actualizar(llaveOctavos1);

        Llave llaveOctavos2 = this.testService.recuperarEntidad(Llave.class, "octavos2");
        llaveOctavos2.setEquipoLocal(equipoEnPosicionDeGrupo(1,"B"));
        llaveOctavos2.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"A"));
        this.testService.actualizar(llaveOctavos2);

        Llave llaveOctavos3 = this.testService.recuperarEntidad(Llave.class, "octavos3");
        llaveOctavos3.setEquipoLocal(equipoEnPosicionDeGrupo(1,"C"));
        llaveOctavos3.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"D"));
        this.testService.actualizar(llaveOctavos3);

        Llave llaveOctavos4 = this.testService.recuperarEntidad(Llave.class, "octavos4");
        llaveOctavos4.setEquipoLocal(equipoEnPosicionDeGrupo(1,"D"));
        llaveOctavos4.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"C"));
        this.testService.actualizar(llaveOctavos4);

        Llave llaveOctavos5 = this.testService.recuperarEntidad(Llave.class, "octavos5");
        llaveOctavos5.setEquipoLocal(equipoEnPosicionDeGrupo(1,"E"));
        llaveOctavos5.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"F"));
        this.testService.actualizar(llaveOctavos5);

        Llave llaveOctavos6 = this.testService.recuperarEntidad(Llave.class, "octavos6");
        llaveOctavos6.setEquipoLocal(equipoEnPosicionDeGrupo(1,"F"));
        llaveOctavos6.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"E"));
        this.testService.actualizar(llaveOctavos6);

        Llave llaveOctavos7 = this.testService.recuperarEntidad(Llave.class, "octavos7");
        llaveOctavos7.setEquipoLocal(equipoEnPosicionDeGrupo(1,"G"));
        llaveOctavos7.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"H"));
        this.testService.actualizar(llaveOctavos7);

        Llave llaveOctavos8 = this.testService.recuperarEntidad(Llave.class, "octavos8");
        llaveOctavos8.setEquipoLocal(equipoEnPosicionDeGrupo(1,"H"));
        llaveOctavos8.setEquipoVisitante(equipoEnPosicionDeGrupo(2,"G"));
        this.testService.actualizar(llaveOctavos8);

        this.resetearLlavesPosteriores();

    }

    private void resetearLlavesPosteriores() {

        List<Llave> llavesRecuperadas = this.testService.recuperarLlaves();
        llavesRecuperadas.stream().forEach(llave -> llave.setGanador(null));
        llavesRecuperadas.stream().forEach(llave -> this.testService.actualizar(llave));
    }

    public void resetearLlavesPosterioresDeLlave(Llave llave) {

        Llave llaveRecuperada = this.testService.recuperarEntidad(Llave.class, llave.getId());

        if(llaveRecuperada.getId().equals("octavos1") || llaveRecuperada.getId().equals("octavos2")){

            Llave llaveCuartos1 = this.testService.recuperarEntidad(Llave.class, "cuartos1");
            llaveCuartos1.setGanador(null);
            Llave llaveSemi1 = this.testService.recuperarEntidad(Llave.class, "semi1");
            llaveSemi1.setGanador(null);
            this.testService.actualizar(llaveRecuperada);
            this.testService.actualizar(llaveCuartos1);
            this.testService.actualizar(llaveSemi1);
        }

        if(llaveRecuperada.getId().equals("octavos3") || llaveRecuperada.getId().equals("octavos4")){

            Llave llaveCuartos2 = this.testService.recuperarEntidad(Llave.class, "cuartos2");
            llaveCuartos2.setGanador(null);
            Llave llaveSemi1 = this.testService.recuperarEntidad(Llave.class, "semi1");
            llaveSemi1.setGanador(null);
            this.testService.actualizar(llaveRecuperada);
            this.testService.actualizar(llaveCuartos2);
            this.testService.actualizar(llaveSemi1);
        }

        if(llaveRecuperada.getId().equals("octavos5") || llaveRecuperada.getId().equals("octavos6")){

            Llave llaveCuartos3 = this.testService.recuperarEntidad(Llave.class, "cuartos3");
            llaveCuartos3.setGanador(null);
            Llave llaveSemi2 = this.testService.recuperarEntidad(Llave.class, "semi2");
            llaveSemi2.setGanador(null);
            this.testService.actualizar(llaveRecuperada);
            this.testService.actualizar(llaveCuartos3);
            this.testService.actualizar(llaveSemi2);
        }

        if(llaveRecuperada.getId().equals("octavos7") || llaveRecuperada.getId().equals("octavos8")){

            Llave llaveCuartos4 = this.testService.recuperarEntidad(Llave.class, "cuartos4");
            llaveCuartos4.setGanador(null);
            Llave llaveSemi2 = this.testService.recuperarEntidad(Llave.class, "semi2");
            llaveSemi2.setGanador(null);
            this.testService.actualizar(llaveRecuperada);
            this.testService.actualizar(llaveCuartos4);
            this.testService.actualizar(llaveSemi2);
        }
        Llave llaveFinal = this.testService.recuperarEntidad(Llave.class, "final");
        llaveFinal.setGanador(null);
        this.testService.actualizar(llaveFinal);
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

        this.crearLlave(null, null);
        this.crearLlave(null, null);
        this.crearLlave(null, null);
        this.crearLlave(null, null);
        this.crearLlave(null, null);
        this.crearLlave(null, null);
        this.crearLlave(null, null);


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
