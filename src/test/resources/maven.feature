Feature: Gradle-Cucumber integration

  Scenario: Just a failing scenario
    When I run a failing step


  Scenario: Nuevo Equipo agregado
    Given Un nuevo Equipo
    When Equipo se agrega
    Then El equipo se carga en la base de equipos