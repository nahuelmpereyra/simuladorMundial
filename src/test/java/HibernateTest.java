import org.junit.After;
import org.junit.Before;

import java.time.LocalDateTime;
import java.time.Month;

public class HibernateTest {



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
    protected Equipo argentina;
    protected Equipo islandia;
    protected Equipo croacia;
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

    protected Partido partidoA1;
    protected Resultado resultadoA1;
    protected Partido partidoA2;
    protected Resultado resultadoA2;
    protected Partido partidoA3;
    protected Resultado resultadoA3;
    protected Partido partidoA4;
    protected Resultado resultadoA4;
    protected Partido partidoA5;
    protected Resultado resultadoA5;
    protected Partido partidoA6;
    protected Resultado resultadoA6;

    protected Partido partidoB1;
    protected Resultado resultadoB1;
    protected Partido partidoB2;
    protected Resultado resultadoB2;
    protected Partido partidoB3;
    protected Resultado resultadoB3;
    protected Partido partidoB4;
    protected Resultado resultadoB4;
    protected Partido partidoB5;
    protected Resultado resultadoB5;
    protected Partido partidoB6;
    protected Resultado resultadoB6;

    protected Partido partidoC1;
    protected Resultado resultadoC1;
    protected Partido partidoC2;
    protected Resultado resultadoC2;
    protected Partido partidoC3;
    protected Resultado resultadoC3;
    protected Partido partidoC4;
    protected Resultado resultadoC4;
    protected Partido partidoC5;
    protected Resultado resultadoC5;
    protected Partido partidoC6;
    protected Resultado resultadoC6;

    protected Partido partidoD1;
    protected Resultado resultadoD1;
    protected Partido partidoD2;
    protected Resultado resultadoD2;
    protected Partido partidoD3;
    protected Resultado resultadoD3;
    protected Partido partidoD4;
    protected Resultado resultadoD4;
    protected Partido partidoD5;
    protected Resultado resultadoD5;
    protected Partido partidoD6;
    protected Resultado resultadoD6;

    protected Partido partidoE1;
    protected Resultado resultadoE1;
    protected Partido partidoE2;
    protected Resultado resultadoE2;
    protected Partido partidoE3;
    protected Resultado resultadoE3;
    protected Partido partidoE4;
    protected Resultado resultadoE4;
    protected Partido partidoE5;
    protected Resultado resultadoE5;
    protected Partido partidoE6;
    protected Resultado resultadoE6;

    protected Partido partidoF1;
    protected Resultado resultadoF1;
    protected Partido partidoF2;
    protected Resultado resultadoF2;
    protected Partido partidoF3;
    protected Resultado resultadoF3;
    protected Partido partidoF4;
    protected Resultado resultadoF4;
    protected Partido partidoF5;
    protected Resultado resultadoF5;
    protected Partido partidoF6;
    protected Resultado resultadoF6;

    protected Partido partidoG1;
    protected Resultado resultadoG1;
    protected Partido partidoG2;
    protected Resultado resultadoG2;
    protected Partido partidoG3;
    protected Resultado resultadoG3;
    protected Partido partidoG4;
    protected Resultado resultadoG4;
    protected Partido partidoG5;
    protected Resultado resultadoG5;
    protected Partido partidoG6;
    protected Resultado resultadoG6;

    protected Partido partidoH1;
    protected Resultado resultadoH1;
    protected Partido partidoH2;
    protected Resultado resultadoH2;
    protected Partido partidoH3;
    protected Resultado resultadoH3;
    protected Partido partidoH4;
    protected Resultado resultadoH4;
    protected Partido partidoH5;
    protected Resultado resultadoH5;
    protected Partido partidoH6;
    protected Resultado resultadoH6;

    @Before
    public void prepare() {
        System.out.println("Empezando el Before");

        SessionFactoryProvider.getInstance().setSessionFactoryTest();


        this.testService = new TestService();
        Torneo torneo = Torneo.getTorneo();
        if (this.testService.recuperarEntidad(Torneo.class, 1) == null) {
            this.testService.crearEntidad(torneo);
        }


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
        this.argentina = new Equipo();
        this.islandia = new Equipo();
        this.croacia = new Equipo();
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
        argentina.setZona("D");
        argentina.setNombre("Argentina");
        islandia.setZona("D");
        islandia.setNombre("Islandia");
        croacia.setZona("D");
        croacia.setNombre("Croacia");
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
        this.testService.crearEntidad(argentina);
        this.testService.crearEntidad(islandia);
        this.testService.crearEntidad(croacia);
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
        this.testService.crearEntidad(japon);
        this.testService.crearEntidad(senegal);


        // GRUPO A:

        this.partidoA1 = new Partido();
        this.partidoA1.setFecha(LocalDateTime.of(2018, Month.JUNE, 14, 12, 00));
        this.partidoA1.setEquipoLocal(rusia);
        this.partidoA1.setEquipoVisitante(arabiaSaudita);
        this.partidoA1.setEstadio("Estadio Olímpico Luzhnikí");
        this.partidoA1.setCiudad("Moscú");
        this.resultadoA1 = new Resultado();
        this.resultadoA1.setGolesLocal(5);
        this.resultadoA1.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoA1);
        this.testService.crearEntidad(partidoA1);
        this.partidoA1.setResultado(resultadoA1);

        this.partidoA2 = new Partido();
        this.partidoA2.setFecha(LocalDateTime.of(2018, Month.JUNE, 15, 9, 00));
        this.partidoA2.setEquipoLocal(egipto);
        this.partidoA2.setEquipoVisitante(uruguay);
        this.partidoA2.setEstadio("Ekaterimburgo Arena");
        this.partidoA2.setCiudad("Ekaterimburgo");
        this.resultadoA2 = new Resultado();
        this.resultadoA2.setGolesLocal(0);
        this.resultadoA2.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoA2);
        this.testService.crearEntidad(partidoA2);
        this.partidoA2.setResultado(resultadoA2);

        this.partidoA3 = new Partido();
        this.partidoA3.setFecha(LocalDateTime.of(2018, Month.JUNE, 19, 15, 00));
        this.partidoA3.setEquipoLocal(rusia);
        this.partidoA3.setEquipoVisitante(egipto);
        this.partidoA3.setEstadio("Zenit Arena");
        this.partidoA3.setCiudad("San Petersburgo");
        this.resultadoA3 = new Resultado();
        this.resultadoA3.setGolesLocal(3);
        this.resultadoA3.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoA3);
        this.testService.crearEntidad(partidoA3);
        this.partidoA3.setResultado(resultadoA3);

        this.partidoA4 = new Partido();
        this.partidoA4.setFecha(LocalDateTime.of(2018, Month.JUNE, 20, 12, 00));
        this.partidoA4.setEquipoLocal(uruguay);
        this.partidoA4.setEquipoVisitante(arabiaSaudita);
        this.partidoA4.setEstadio("Rostov Arena");
        this.partidoA4.setCiudad("Rostov del Don");
        this.resultadoA4 = new Resultado();
        this.resultadoA4.setGolesLocal(1);
        this.resultadoA4.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoA4);
        this.testService.crearEntidad(partidoA4);
        this.partidoA4.setResultado(resultadoA4);

        this.partidoA5 = new Partido();
        this.partidoA5.setFecha(LocalDateTime.of(2018, Month.JUNE, 25, 11, 00));
        this.partidoA5.setEquipoLocal(uruguay);
        this.partidoA5.setEquipoVisitante(rusia);
        this.partidoA5.setEstadio("Samara Arena");
        this.partidoA5.setCiudad("Samara");
        this.resultadoA5 = new Resultado();
        this.resultadoA5.setGolesLocal(3);
        this.resultadoA5.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoA5);
        this.testService.crearEntidad(partidoA5);
        this.partidoA5.setResultado(resultadoA5);

        this.partidoA6 = new Partido();
        this.partidoA6.setFecha(LocalDateTime.of(2018, Month.JUNE, 25, 11, 00));
        this.partidoA6.setEquipoLocal(arabiaSaudita);
        this.partidoA6.setEquipoVisitante(egipto);
        this.partidoA6.setEstadio("Volgogrado Arena");
        this.partidoA6.setCiudad("Volgogrado");
        this.resultadoA6 = new Resultado();
        this.resultadoA6.setGolesLocal(2);
        this.resultadoA6.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoA6);
        this.testService.crearEntidad(partidoA6);
        this.partidoA6.setResultado(resultadoA6);

        // GRUPO B:

        this.partidoB1 = new Partido();
        this.partidoB1.setFecha(LocalDateTime.of(2018, Month.JUNE, 15, 12, 00));
        this.partidoB1.setEquipoLocal(marruecos);
        this.partidoB1.setEquipoVisitante(iran);
        this.partidoB1.setEstadio("Zenit Arena");
        this.partidoB1.setCiudad("San Petersburgo");
        this.resultadoB1 = new Resultado();
        this.resultadoB1.setGolesLocal(0);
        this.resultadoB1.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoB1);
        this.testService.crearEntidad(partidoB1);
        this.partidoB1.setResultado(resultadoB1);

        this.partidoB2 = new Partido();
        this.partidoB2.setFecha(LocalDateTime.of(2018, Month.JUNE, 15, 15, 00));
        this.partidoB2.setEquipoLocal(portugal);
        this.partidoB2.setEquipoVisitante(espania);
        this.partidoB2.setEstadio("Estadio Fisht");
        this.partidoB2.setCiudad("Sochi");
        this.resultadoB2 = new Resultado();
        this.resultadoB2.setGolesLocal(3);
        this.resultadoB2.setGolesVisitantes(3);
        this.testService.crearEntidad(resultadoB2);
        this.testService.crearEntidad(partidoB2);
        this.partidoB2.setResultado(resultadoB2);

        this.partidoB3 = new Partido();
        this.partidoB3.setFecha(LocalDateTime.of(2018, Month.JUNE, 20, 9, 00));
        this.partidoB3.setEquipoLocal(portugal);
        this.partidoB3.setEquipoVisitante(marruecos);
        this.partidoB3.setEstadio("Estadio Olímpico Luzhnikí");
        this.partidoB3.setCiudad("Moscú");
        this.resultadoB3 = new Resultado();
        this.resultadoB3.setGolesLocal(1);
        this.resultadoB3.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoB3);
        this.testService.crearEntidad(partidoB3);
        this.partidoB3.setResultado(resultadoB3);

        this.partidoB4 = new Partido();
        this.partidoB4.setFecha(LocalDateTime.of(2018, Month.JUNE, 20, 15, 00));
        this.partidoB4.setEquipoLocal(iran);
        this.partidoB4.setEquipoVisitante(espania);
        this.partidoB4.setEstadio("Kazán Arena");
        this.partidoB4.setCiudad("Kazán");
        this.resultadoB4 = new Resultado();
        this.resultadoB4.setGolesLocal(0);
        this.resultadoB4.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoB4);
        this.testService.crearEntidad(partidoB4);
        this.partidoB4.setResultado(resultadoB4);

        this.partidoB5 = new Partido();
        this.partidoB5.setFecha(LocalDateTime.of(2018, Month.JUNE, 25, 15, 00));
        this.partidoB5.setEquipoLocal(espania);
        this.partidoB5.setEquipoVisitante(marruecos);
        this.partidoB5.setEstadio("Arena Baltika");
        this.partidoB5.setCiudad("Kaliningrado");
        this.resultadoB5 = new Resultado();
        this.resultadoB5.setGolesLocal(2);
        this.resultadoB5.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoB5);
        this.testService.crearEntidad(partidoB5);
        this.partidoB5.setResultado(resultadoB5);

        this.partidoB6 = new Partido();
        this.partidoB6.setFecha(LocalDateTime.of(2018, Month.JUNE, 25, 15, 00));
        this.partidoB6.setEquipoLocal(iran);
        this.partidoB6.setEquipoVisitante(portugal);
        this.partidoB6.setEstadio("Mordovia Arena");
        this.partidoB6.setCiudad("Saransk");
        this.resultadoB6 = new Resultado();
        this.resultadoB6.setGolesLocal(1);
        this.resultadoB6.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoB6);
        this.testService.crearEntidad(partidoB6);
        this.partidoB6.setResultado(resultadoB6);

        // GRUPO C:

        this.partidoC1 = new Partido();
        this.partidoC1.setFecha(LocalDateTime.of(2018, Month.JUNE, 16, 7, 00));
        this.partidoC1.setEquipoLocal(francia);
        this.partidoC1.setEquipoVisitante(australia);
        this.partidoC1.setEstadio("Kazán Arena");
        this.partidoC1.setCiudad("Kazán");
        this.resultadoC1 = new Resultado();
        this.resultadoC1.setGolesLocal(2);
        this.resultadoC1.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoC1);
        this.testService.crearEntidad(partidoC1);
        this.partidoC1.setResultado(resultadoC1);

        this.partidoC2 = new Partido();
        this.partidoC2.setFecha(LocalDateTime.of(2018, Month.JUNE, 16, 13, 00));
        this.partidoC2.setEquipoLocal(peru);
        this.partidoC2.setEquipoVisitante(dinamarca);
        this.partidoC2.setEstadio("Mordovia Arena");
        this.partidoC2.setCiudad("Saransk");
        this.resultadoC2 = new Resultado();
        this.resultadoC2.setGolesLocal(0);
        this.resultadoC2.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoC2);
        this.testService.crearEntidad(partidoC2);
        this.partidoC2.setResultado(resultadoC2);

        this.partidoC3 = new Partido();
        this.partidoC3.setFecha(LocalDateTime.of(2018, Month.JUNE, 21, 9, 00));
        this.partidoC3.setEquipoLocal(dinamarca);
        this.partidoC3.setEquipoVisitante(australia);
        this.partidoC3.setEstadio("Samara Arena");
        this.partidoC3.setCiudad("Samara");
        this.resultadoC3 = new Resultado();
        this.resultadoC3.setGolesLocal(1);
        this.resultadoC3.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoC3);
        this.testService.crearEntidad(partidoC3);
        this.partidoC3.setResultado(resultadoC3);

        this.partidoC4 = new Partido();
        this.partidoC4.setFecha(LocalDateTime.of(2018, Month.JUNE, 21, 12, 00));
        this.partidoC4.setEquipoLocal(francia);
        this.partidoC4.setEquipoVisitante(peru);
        this.partidoC4.setEstadio("Ekaterimburgo Arena");
        this.partidoC4.setCiudad("Ekaterimburgo");
        this.resultadoC4 = new Resultado();
        this.resultadoC4.setGolesLocal(1);
        this.resultadoC4.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoC4);
        this.testService.crearEntidad(partidoC4);
        this.partidoC4.setResultado(resultadoC4);

        this.partidoC5 = new Partido();
        this.partidoC5.setFecha(LocalDateTime.of(2018, Month.JUNE, 26, 11, 00));
        this.partidoC5.setEquipoLocal(dinamarca);
        this.partidoC5.setEquipoVisitante(francia);
        this.partidoC5.setEstadio("Estadio Olímpico Luzhnikí");
        this.partidoC5.setCiudad("Moscú");
        this.resultadoC5 = new Resultado();
        this.resultadoC5.setGolesLocal(0);
        this.resultadoC5.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoC5);
        this.testService.crearEntidad(partidoC5);
        this.partidoC5.setResultado(resultadoC5);

        this.partidoC6 = new Partido();
        this.partidoC6.setFecha(LocalDateTime.of(2018, Month.JUNE, 26, 11, 00));
        this.partidoC6.setEquipoLocal(australia);
        this.partidoC6.setEquipoVisitante(peru);
        this.partidoC6.setEstadio("Estadio Fisht");
        this.partidoC6.setCiudad("Sochi");
        this.resultadoC6 = new Resultado();
        this.resultadoC6.setGolesLocal(0);
        this.resultadoC6.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoC6);
        this.testService.crearEntidad(partidoC6);
        this.partidoC6.setResultado(resultadoC6);

        // GRUPO D:

        this.partidoD1 = new Partido();
        this.partidoD1.setFecha(LocalDateTime.of(2018, Month.JUNE, 16, 10, 00));
        this.partidoD1.setEquipoLocal(argentina);
        this.partidoD1.setEquipoVisitante(islandia);
        this.partidoD1.setEstadio("Otkrytie Arena");
        this.partidoD1.setCiudad("Moscú");
        this.resultadoD1 = new Resultado();
        this.resultadoD1.setGolesLocal(1);
        this.resultadoD1.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoD1);
        this.testService.crearEntidad(partidoD1);
        this.partidoD1.setResultado(resultadoD1);

        this.partidoD2 = new Partido();
        this.partidoD2.setFecha(LocalDateTime.of(2018, Month.JUNE, 16, 16, 00));
        this.partidoD2.setEquipoLocal(croacia);
        this.partidoD2.setEquipoVisitante(nigeria);
        this.partidoD2.setEstadio("Arena Baltika");
        this.partidoD2.setCiudad("Kaliningrado");
        this.resultadoD2 = new Resultado();
        this.resultadoD2.setGolesLocal(2);
        this.resultadoD2.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoD2);
        this.testService.crearEntidad(partidoD2);
        this.partidoD2.setResultado(resultadoD2);

        this.partidoD3 = new Partido();
        this.partidoD3.setFecha(LocalDateTime.of(2018, Month.JUNE, 21, 15, 00));
        this.partidoD3.setEquipoLocal(argentina);
        this.partidoD3.setEquipoVisitante(croacia);
        this.partidoD3.setEstadio("Estadio de Nizhni Nóvgorod");
        this.partidoD3.setCiudad("Nizhni Nóvgorod");
        this.resultadoD3 = new Resultado();
        this.resultadoD3.setGolesLocal(0);
        this.resultadoD3.setGolesVisitantes(3);
        this.testService.crearEntidad(resultadoD3);
        this.testService.crearEntidad(partidoD3);
        this.partidoD3.setResultado(resultadoD3);

        this.partidoD4 = new Partido();
        this.partidoD4.setFecha(LocalDateTime.of(2018, Month.JUNE, 22, 12, 00));
        this.partidoD4.setEquipoLocal(nigeria);
        this.partidoD4.setEquipoVisitante(islandia);
        this.partidoD4.setEstadio("Volgogrado Arena");
        this.partidoD4.setCiudad("Volgogrado");
        this.resultadoD4 = new Resultado();
        this.resultadoD4.setGolesLocal(2);
        this.resultadoD4.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoD4);
        this.testService.crearEntidad(partidoD4);
        this.partidoD4.setResultado(resultadoD4);

        this.partidoD5 = new Partido();
        this.partidoD5.setFecha(LocalDateTime.of(2018, Month.JUNE, 26, 15, 00));
        this.partidoD5.setEquipoLocal(argentina);
        this.partidoD5.setEquipoVisitante(nigeria);
        this.partidoD5.setEstadio("Zenit Arena");
        this.partidoD5.setCiudad("San Petersburgo");
        this.resultadoD5 = new Resultado();
        this.resultadoD5.setGolesLocal(2);
        this.resultadoD5.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoD5);
        this.testService.crearEntidad(partidoD5);
        this.partidoD5.setResultado(resultadoD5);

        this.partidoD6= new Partido();
        this.partidoD6.setFecha(LocalDateTime.of(2018, Month.JUNE, 26, 15, 00));
        this.partidoD6.setEquipoLocal(islandia);
        this.partidoD6.setEquipoVisitante(croacia);
        this.partidoD6.setEstadio("Rostov Arena");
        this.partidoD6.setCiudad("Rostov del Don");
        this.resultadoD6 = new Resultado();
        this.resultadoD6.setGolesLocal(1);
        this.resultadoD6.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoD6);
        this.testService.crearEntidad(partidoD6);
        this.partidoD6.setResultado(resultadoD6);

        // GRUPO E:

        this.partidoE1 = new Partido();
        this.partidoE1.setFecha(LocalDateTime.of(2018, Month.JUNE, 17, 9, 00));
        this.partidoE1.setEquipoLocal(costaRica);
        this.partidoE1.setEquipoVisitante(serbia);
        this.partidoE1.setEstadio("Samara Arena");
        this.partidoE1.setCiudad("Samara");
        this.resultadoE1 = new Resultado();
        this.resultadoE1.setGolesLocal(0);
        this.resultadoE1.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoE1);
        this.testService.crearEntidad(partidoE1);
        this.partidoE1.setResultado(resultadoE1);

        this.partidoE2 = new Partido();
        this.partidoE2.setFecha(LocalDateTime.of(2018, Month.JUNE, 17, 15, 00));
        this.partidoE2.setEquipoLocal(brasil);
        this.partidoE2.setEquipoVisitante(suiza);
        this.partidoE2.setEstadio("Rostov Arena");
        this.partidoE2.setCiudad("Rostov del Don");
        this.resultadoE2 = new Resultado();
        this.resultadoE2.setGolesLocal(1);
        this.resultadoE2.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoE2);
        this.testService.crearEntidad(partidoE2);
        this.partidoE2.setResultado(resultadoE2);

        this.partidoE3 = new Partido();
        this.partidoE3.setFecha(LocalDateTime.of(2018, Month.JUNE, 22, 9, 00));
        this.partidoE3.setEquipoLocal(brasil);
        this.partidoE3.setEquipoVisitante(costaRica);
        this.partidoE3.setEstadio("Zenit Arena");
        this.partidoE3.setCiudad("San Petersburgo");
        this.resultadoE3 = new Resultado();
        this.resultadoE3.setGolesLocal(2);
        this.resultadoE3.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoE3);
        this.testService.crearEntidad(partidoE3);
        this.partidoE3.setResultado(resultadoE3);

        this.partidoE4 = new Partido();
        this.partidoE4.setFecha(LocalDateTime.of(2018, Month.JUNE, 22, 15, 00));
        this.partidoE4.setEquipoLocal(serbia);
        this.partidoE4.setEquipoVisitante(suiza);
        this.partidoE4.setEstadio("Arena Baltika");
        this.partidoE4.setCiudad("Kaliningrado");
        this.resultadoE4 = new Resultado();
        this.resultadoE4.setGolesLocal(1);
        this.resultadoE4.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoE4);
        this.testService.crearEntidad(partidoE4);
        this.partidoE4.setResultado(resultadoE4);

        this.partidoE5 = new Partido();
        this.partidoE5.setFecha(LocalDateTime.of(2018, Month.JUNE, 27, 15, 00));
        this.partidoE5.setEquipoLocal(serbia);
        this.partidoE5.setEquipoVisitante(brasil);
        this.partidoE5.setEstadio("Otkrytie Arena");
        this.partidoE5.setCiudad("Moscú");
        this.resultadoE5 = new Resultado();
        this.resultadoE5.setGolesLocal(0);
        this.resultadoE5.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoE5);
        this.testService.crearEntidad(partidoE5);
        this.partidoE5.setResultado(resultadoE5);

        this.partidoE6= new Partido();
        this.partidoE6.setFecha(LocalDateTime.of(2018, Month.JUNE, 27, 15, 00));
        this.partidoE6.setEquipoLocal(suiza);
        this.partidoE6.setEquipoVisitante(costaRica);
        this.partidoE6.setEstadio("Estadio de Nizhni Nóvgorod");
        this.partidoE6.setCiudad("Nizhni Nóvgorod");
        this.resultadoE6 = new Resultado();
        this.resultadoE6.setGolesLocal(2);
        this.resultadoE6.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoE6);
        this.testService.crearEntidad(partidoE6);
        this.partidoE6.setResultado(resultadoE6);

        // GRUPO F:

        this.partidoF1 = new Partido();
        this.partidoF1.setFecha(LocalDateTime.of(2018, Month.JUNE, 17, 12, 00));
        this.partidoF1.setEquipoLocal(alemania);
        this.partidoF1.setEquipoVisitante(mexico);
        this.partidoF1.setEstadio("Estadio Olímpico Luzhniki");
        this.partidoF1.setCiudad("Moscú");
        this.resultadoF1 = new Resultado();
        this.resultadoF1.setGolesLocal(0);
        this.resultadoF1.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoF1);
        this.testService.crearEntidad(partidoF1);
        this.partidoF1.setResultado(resultadoF1);

        this.partidoF2 = new Partido();
        this.partidoF2.setFecha(LocalDateTime.of(2018, Month.JUNE, 18, 9, 00));
        this.partidoF2.setEquipoLocal(suecia);
        this.partidoF2.setEquipoVisitante(coreaDelSur);
        this.partidoF2.setEstadio("Estadio de Nizhni Nóvgorod");
        this.partidoF2.setCiudad("Nizhni Nóvgorod");
        this.resultadoF2 = new Resultado();
        this.resultadoF2.setGolesLocal(1);
        this.resultadoF2.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoF2);
        this.testService.crearEntidad(partidoF2);
        this.partidoF2.setResultado(resultadoF2);

        this.partidoF3 = new Partido();
        this.partidoF3.setFecha(LocalDateTime.of(2018, Month.JUNE, 23, 12, 00));
        this.partidoF3.setEquipoLocal(coreaDelSur);
        this.partidoF3.setEquipoVisitante(mexico);
        this.partidoF3.setEstadio("Rostov Arena");
        this.partidoF3.setCiudad("Rostov del Don");
        this.resultadoF3 = new Resultado();
        this.resultadoF3.setGolesLocal(0);
        this.resultadoF3.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoF3);
        this.testService.crearEntidad(partidoF3);
        this.partidoF3.setResultado(resultadoF3);

        this.partidoF4 = new Partido();
        this.partidoF4.setFecha(LocalDateTime.of(2018, Month.JUNE, 23, 15, 00));
        this.partidoF4.setEquipoLocal(alemania);
        this.partidoF4.setEquipoVisitante(suecia);
        this.partidoF4.setEstadio("Estadio Fisht");
        this.partidoF4.setCiudad("Sochi");
        this.resultadoF4 = new Resultado();
        this.resultadoF4.setGolesLocal(2);
        this.resultadoF4.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoF4);
        this.testService.crearEntidad(partidoF4);
        this.partidoF4.setResultado(resultadoF4);

        this.partidoF5 = new Partido();
        this.partidoF5.setFecha(LocalDateTime.of(2018, Month.JUNE, 27, 11, 00));
        this.partidoF5.setEquipoLocal(coreaDelSur);
        this.partidoF5.setEquipoVisitante(alemania);
        this.partidoF5.setEstadio("Kazán Arena");
        this.partidoF5.setCiudad("Kazán");
        this.resultadoF5 = new Resultado();
        this.resultadoF5.setGolesLocal(2);
        this.resultadoF5.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoF5);
        this.testService.crearEntidad(partidoF5);
        this.partidoF5.setResultado(resultadoF5);

        this.partidoF6= new Partido();
        this.partidoF6.setFecha(LocalDateTime.of(2018, Month.JUNE, 27, 11, 00));
        this.partidoF6.setEquipoLocal(mexico);
        this.partidoF6.setEquipoVisitante(suecia);
        this.partidoF6.setEstadio("Ekaterimburgo Arena");
        this.partidoF6.setCiudad("Ekaterimburgo");
        this.resultadoF6 = new Resultado();
        this.resultadoF6.setGolesLocal(0);
        this.resultadoF6.setGolesVisitantes(3);
        this.testService.crearEntidad(resultadoF6);
        this.testService.crearEntidad(partidoF6);
        this.partidoF6.setResultado(resultadoF6);

        // GRUPO G:

        this.partidoG1 = new Partido();
        this.partidoG1.setFecha(LocalDateTime.of(2018, Month.JUNE, 18, 12, 00));
        this.partidoG1.setEquipoLocal(belgica);
        this.partidoG1.setEquipoVisitante(panama);
        this.partidoG1.setEstadio("Estadio Fisht");
        this.partidoG1.setCiudad("Sochi");
        this.resultadoG1 = new Resultado();
        this.resultadoG1.setGolesLocal(3);
        this.resultadoG1.setGolesVisitantes(0);
        this.testService.crearEntidad(resultadoG1);
        this.testService.crearEntidad(partidoG1);
        this.partidoG1.setResultado(resultadoG1);

        this.partidoG2 = new Partido();
        this.partidoG2.setFecha(LocalDateTime.of(2018, Month.JUNE, 18, 15, 00));
        this.partidoG2.setEquipoLocal(tunez);
        this.partidoG2.setEquipoVisitante(inglaterra);
        this.partidoG2.setEstadio("Volgogrado Arena");
        this.partidoG2.setCiudad("Volgogrado");
        this.resultadoG2 = new Resultado();
        this.resultadoG2.setGolesLocal(1);
        this.resultadoG2.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoG2);
        this.testService.crearEntidad(partidoG2);
        this.partidoG2.setResultado(resultadoG2);

        this.partidoG3 = new Partido();
        this.partidoG3.setFecha(LocalDateTime.of(2018, Month.JUNE, 23, 9, 00));
        this.partidoG3.setEquipoLocal(belgica);
        this.partidoG3.setEquipoVisitante(tunez);
        this.partidoG3.setEstadio("Otkrytie Arena");
        this.partidoG3.setCiudad("Moscú");
        this.resultadoG3 = new Resultado();
        this.resultadoG3.setGolesLocal(5);
        this.resultadoG3.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoG3);
        this.testService.crearEntidad(partidoG3);
        this.partidoG3.setResultado(resultadoG3);

        this.partidoG4 = new Partido();
        this.partidoG4.setFecha(LocalDateTime.of(2018, Month.JUNE, 24, 9, 00));
        this.partidoG4.setEquipoLocal(inglaterra);
        this.partidoG4.setEquipoVisitante(panama);
        this.partidoG4.setEstadio("Estadio de Nizhni Nóvgorod");
        this.partidoG4.setCiudad("Nizhni Nóvgorod");
        this.resultadoG4 = new Resultado();
        this.resultadoG4.setGolesLocal(6);
        this.resultadoG4.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoG4);
        this.testService.crearEntidad(partidoG4);
        this.partidoG4.setResultado(resultadoG4);

        this.partidoG5 = new Partido();
        this.partidoG5.setFecha(LocalDateTime.of(2018, Month.JUNE, 28, 15, 00));
        this.partidoG5.setEquipoLocal(inglaterra);
        this.partidoG5.setEquipoVisitante(belgica);
        this.partidoG5.setEstadio("Arena Baltika");
        this.partidoG5.setCiudad("Kaliningrado");
        this.resultadoG5 = new Resultado();
        this.resultadoG5.setGolesLocal(0);
        this.resultadoG5.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoG5);
        this.testService.crearEntidad(partidoG5);
        this.partidoG5.setResultado(resultadoG5);

        this.partidoG6= new Partido();
        this.partidoG6.setFecha(LocalDateTime.of(2018, Month.JUNE, 28, 15, 00));
        this.partidoG6.setEquipoLocal(panama);
        this.partidoG6.setEquipoVisitante(tunez);
        this.partidoG6.setEstadio("Mordovia Arena");
        this.partidoG6.setCiudad("Saransk");
        this.resultadoG6 = new Resultado();
        this.resultadoG6.setGolesLocal(1);
        this.resultadoG6.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoG6);
        this.testService.crearEntidad(partidoG6);
        this.partidoG6.setResultado(resultadoG6);

        // GRUPO H:

        this.partidoH1 = new Partido();
        this.partidoH1.setFecha(LocalDateTime.of(2018, Month.JUNE, 19, 9, 00));
        this.partidoH1.setEquipoLocal(colombia);
        this.partidoH1.setEquipoVisitante(japon);
        this.partidoH1.setEstadio("Mordovia Arena");
        this.partidoH1.setCiudad("Saransk");
        this.resultadoH1 = new Resultado();
        this.resultadoH1.setGolesLocal(1);
        this.resultadoH1.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoH1);
        this.testService.crearEntidad(partidoH1);
        this.partidoH1.setResultado(resultadoH1);

        this.partidoH2 = new Partido();
        this.partidoH2.setFecha(LocalDateTime.of(2018, Month.JUNE, 19, 12, 00));
        this.partidoH2.setEquipoLocal(polonia);
        this.partidoH2.setEquipoVisitante(senegal);
        this.partidoH2.setEstadio("Otkrytie Arena");
        this.partidoH2.setCiudad("Moscú");
        this.resultadoH2 = new Resultado();
        this.resultadoH2.setGolesLocal(1);
        this.resultadoH2.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoH2);
        this.testService.crearEntidad(partidoH2);
        this.partidoH2.setResultado(resultadoH2);

        this.partidoH3 = new Partido();
        this.partidoH3.setFecha(LocalDateTime.of(2018, Month.JUNE, 24, 12, 00));
        this.partidoH3.setEquipoLocal(japon);
        this.partidoH3.setEquipoVisitante(senegal);
        this.partidoH3.setEstadio("Ekaterimburgo Arena");
        this.partidoH3.setCiudad("Ekaterimburgo");
        this.resultadoH3 = new Resultado();
        this.resultadoH3.setGolesLocal(2);
        this.resultadoH3.setGolesVisitantes(2);
        this.testService.crearEntidad(resultadoH3);
        this.testService.crearEntidad(partidoH3);
        this.partidoH3.setResultado(resultadoH3);

        this.partidoH4 = new Partido();
        this.partidoH4.setFecha(LocalDateTime.of(2018, Month.JUNE, 24, 15, 00));
        this.partidoH4.setEquipoLocal(polonia);
        this.partidoH4.setEquipoVisitante(colombia);
        this.partidoH4.setEstadio("Kazán Arena");
        this.partidoH4.setCiudad("Kazán");
        this.resultadoH4 = new Resultado();
        this.resultadoH4.setGolesLocal(0);
        this.resultadoH4.setGolesVisitantes(3);
        this.testService.crearEntidad(resultadoH4);
        this.testService.crearEntidad(partidoH4);
        this.partidoH4.setResultado(resultadoH4);

        this.partidoH5 = new Partido();
        this.partidoH5.setFecha(LocalDateTime.of(2018, Month.JUNE, 28, 11, 00));
        this.partidoH5.setEquipoLocal(japon);
        this.partidoH5.setEquipoVisitante(polonia);
        this.partidoH5.setEstadio("Volgogrado Arena");
        this.partidoH5.setCiudad("Volgogrado");
        this.resultadoH5 = new Resultado();
        this.resultadoH5.setGolesLocal(0);
        this.resultadoH5.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoH5);
        this.testService.crearEntidad(partidoH5);
        this.partidoH5.setResultado(resultadoH5);

        this.partidoH6= new Partido();
        this.partidoH6.setFecha(LocalDateTime.of(2018, Month.JUNE, 28, 11, 00));
        this.partidoH6.setEquipoLocal(senegal);
        this.partidoH6.setEquipoVisitante(colombia);
        this.partidoH6.setEstadio("Samara Arena");
        this.partidoH6.setCiudad("Samara");
        this.resultadoH6 = new Resultado();
        this.resultadoH6.setGolesLocal(0);
        this.resultadoH6.setGolesVisitantes(1);
        this.testService.crearEntidad(resultadoH6);
        this.testService.crearEntidad(partidoH6);
        this.partidoH6.setResultado(resultadoH6);




//        this.partido = new Partido();
//        this.partido.setFecha(LocalDateTime.now());
//        this.partido.setEquipoLocal(argentina);
//        this.partido.setEquipoVisitante(islandia);
//        this.partido.setEstadio("Otkrytie Arena");
//        this.partido.setCiudad("Moscú");
//        this.resultado = new Resultado();
//        this.llave = new Llave();
//        this.llave.setEquipoLocal(argentina);
//        this.llave.setEquipoVisitante(islandia);
//        this.llave.setGanador(argentina);

//        this.testService.crearEntidad(partido);
//        this.testService.crearEntidad(resultado);
//        this.testService.crearEntidad(llave);
        torneo.armarLlavesDelTorneo();
        System.out.println("Terminando el Before");
    }

    @After
    public void cleanup() {
     //   SessionFactoryProvider.destroy();
        System.out.println("Terminando el After");
    }
}