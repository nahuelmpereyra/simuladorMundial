Feature: Gradle-Cucumber integration

  Scenario: Just a failing scenario
    When I run a failing step


  Scenario: Nuevo Equipo agregado
    Given Un nuevo Equipo
    When Equipo se agrega
    Then El equipo se carga en la base de equipos

  Scenario: Setear Equipo como cabeza de serie
    Given Un nuevo Equipo
    When Equipo se agrega
    When Seteo el Equipo como cabeza de serie
    Then El Equipo figura como cabeza de serie en la base de equipos

  Scenario: Nuevo Partido agregado
    Given Un nuevo Equipo
    Given Otro nuevo Equipo
    Given Un nuevo Partido
    #When Otro nuevo Equipo se agrega
    When Equipo se agrega
    When Partido se agrega
    Then El Partido se carga en la base de partidos