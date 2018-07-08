import org.junit.After;
import org.junit.Before;

import java.time.LocalDateTime;

public class HibernateTest {


    protected Equipo equipo1;
    protected Equipo equipo2;
    protected Equipo equipo3;
    protected TestService testService;
    protected Partido partido;
    protected Resultado resultado;
    protected Llave llave;

    protected Equipo uruguay;
    protected Equipo rusia;
    protected Equipo egipto;
    protected Equipo arabiaSaudita;
    protected Equipo espania;
    protected Equipo portugal;
    protected Equipo marruecos;
    protected Equipo iran;
    protected Equipo francia;
    protected Equipo australia;
    protected Equipo dinamarca;
    protected Equipo peru;
    protected Equipo nigeria;
    protected Equipo brasil;
    protected Equipo suiza;
    protected Equipo costaRica;
    protected Equipo serbia;
    protected Equipo alemania;
    protected Equipo mexico;
    protected Equipo suecia;
    protected Equipo coreaDelSur;
    protected Equipo belgica;
    protected Equipo inglaterra;
    protected Equipo panama;
    protected Equipo tunez;
    protected Equipo colombia;
    protected Equipo polonia;
    protected Equipo japon;
    protected Equipo senegal;

    @Before
    public void prepare() {
        System.out.println("Empezando el Before");

        SessionFactoryProvider.getInstance().setSessionFactoryTest();


        this.testService = new TestService();
        Torneo torneo = Torneo.getTorneo();
        if (this.testService.recuperarEntidad(Torneo.class, 1) == null) {
            this.testService.crearEntidad(torneo);
        }

        this.equipo1 = new Equipo();
        this.equipo2 = new Equipo();
        this.equipo3 = new Equipo();
        equipo1.setZona("D");
        equipo1.setNombre("Argentina");
        equipo2.setZona("D");
        equipo2.setNombre("Islandia");
        equipo3.setZona("D");
        equipo3.setNombre("Croacia");


        // Sólo para pruebas:

        this.rusia = new Equipo();
        this.uruguay = new Equipo();
        this.egipto = new Equipo();
        this.arabiaSaudita = new Equipo();
        this.espania = new Equipo();
        this.portugal = new Equipo();
        this.marruecos = new Equipo();
        this.iran = new Equipo();
        this.francia = new Equipo();
        this.australia = new Equipo();
        this.peru = new Equipo();
        this.dinamarca = new Equipo();
        this.nigeria = new Equipo();
        this.brasil = new Equipo();
        this.suiza = new Equipo();
        this.costaRica = new Equipo();
        this.serbia = new Equipo();
        this.alemania = new Equipo();
        this.mexico = new Equipo();
        this.coreaDelSur = new Equipo();
        this.suecia = new Equipo();
        this.belgica = new Equipo();
        this.panama = new Equipo();
        this.tunez = new Equipo();
        this.inglaterra = new Equipo();
        this.polonia = new Equipo();
        this.senegal = new Equipo();
        this.japon = new Equipo();
        this.colombia = new Equipo();
        rusia.setZona("A");
        rusia.setNombre("Rusia");
        uruguay.setZona("A");
        uruguay.setNombre("Uruguay");
        egipto.setZona("A");
        egipto.setNombre("Egipto");
        arabiaSaudita.setZona("A");
        arabiaSaudita.setNombre("Arabia Saudita");
        espania.setZona("B");
        espania.setNombre("España");
        portugal.setZona("B");
        portugal.setNombre("Portugal");
        marruecos.setZona("B");
        marruecos.setNombre("Marruecos");
        iran.setZona("B");
        iran.setNombre("Iran");
        francia.setZona("C");
        francia.setNombre("Francia");
        dinamarca.setZona("C");
        dinamarca.setNombre("Dinamarca");
        peru.setZona("C");
        peru.setNombre("Peru");
        australia.setZona("C");
        australia.setNombre("Australia");
        nigeria.setZona("D");
        nigeria.setNombre("Nigeria");
        brasil.setZona("E");
        brasil.setNombre("Brasil");
        suiza.setZona("E");
        suiza.setNombre("Suiza");
        costaRica.setZona("E");
        costaRica.setNombre("Costa Rica");
        serbia.setZona("E");
        serbia.setNombre("Serbia");
        alemania.setZona("F");
        alemania.setNombre("Alemania");
        mexico.setZona("F");
        mexico.setNombre("Mexico");
        coreaDelSur.setZona("F");
        coreaDelSur.setNombre("Corea del Sur");
        suecia.setZona("F");
        suecia.setNombre("Suecia");
        belgica.setZona("G");
        belgica.setNombre("Belgica");
        panama.setZona("G");
        panama.setNombre("Panama");
        tunez.setZona("G");
        tunez.setNombre("Tunez");
        inglaterra.setZona("G");
        inglaterra.setNombre("Inglaterra");
        polonia.setZona("H");
        polonia.setNombre("Polonia");
        senegal.setZona("H");
        senegal.setNombre("Senegal");
        colombia.setZona("H");
        colombia.setNombre("Colombia");
        japon.setZona("H");
        japon.setNombre("Japon");

        this.testService.crearEntidad(uruguay);
        this.testService.crearEntidad(egipto);
        this.testService.crearEntidad(arabiaSaudita);
        this.testService.crearEntidad(rusia);
        this.testService.crearEntidad(espania);
        this.testService.crearEntidad(portugal);
        this.testService.crearEntidad(marruecos);
        this.testService.crearEntidad(iran);
        this.testService.crearEntidad(francia);
        this.testService.crearEntidad(dinamarca);
        this.testService.crearEntidad(peru);
        this.testService.crearEntidad(australia);
        this.testService.crearEntidad(nigeria);
        this.testService.crearEntidad(brasil);
        this.testService.crearEntidad(serbia);
        this.testService.crearEntidad(suiza);
        this.testService.crearEntidad(costaRica);
        this.testService.crearEntidad(alemania);
        this.testService.crearEntidad(mexico);
        this.testService.crearEntidad(coreaDelSur);
        this.testService.crearEntidad(suecia);
        this.testService.crearEntidad(belgica);
        this.testService.crearEntidad(inglaterra);
        this.testService.crearEntidad(panama);
        this.testService.crearEntidad(tunez);
        this.testService.crearEntidad(polonia);
        this.testService.crearEntidad(colombia);
        this.testService.crearEntidad(senegal);
        this.testService.crearEntidad(japon);
        //

        this.partido = new Partido();
        this.partido.setFecha(LocalDateTime.now());
        this.partido.setEquipoLocal(equipo1);
        this.partido.setEquipoVisitante(equipo2);
        this.partido.setEstadio("Estadio");
        this.resultado = new Resultado();
//        this.llave = new Llave();
//        this.llave.setEquipoLocal(equipo1);
//        this.llave.setEquipoVisitante(equipo2);
//        this.llave.setGanador(equipo1);
        this.testService.crearEntidad(equipo1);
        this.testService.crearEntidad(equipo2);
        this.testService.crearEntidad(equipo3);
        this.testService.crearEntidad(partido);
        this.testService.crearEntidad(resultado);
//        this.testService.crearEntidad(llave);
        torneo.armarLlavesDelTorneo();
        System.out.println("Terminando el Before");
    }

    @After
    public void cleanup() {
        SessionFactoryProvider.destroy();
        System.out.println("Terminando el After");
    }
}