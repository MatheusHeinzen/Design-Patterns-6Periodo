package br.pucpr.planet;

import br.pucpr.table.TableData;
import java.util.ArrayList;

public class PlanetasPrinter implements TableData {
  private static final String[] HEADERS = {
    "Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo"
  };

  private final ArrayList<Planet> planets;

  public PlanetasPrinter(ArrayList<Planet> planets) {
    this.planets = planets;
  }

  @Override
  public int getColumnCount() {
    return HEADERS.length;
  }

  @Override
  public String getHeader(int column) {
    return HEADERS[column];
  }

  @Override
  public int getRowCount() {
    return planets == null ? 0 : planets.size();
  }

  @Override
  public String getCell(int row, int column) {
    var planet = planets.get(row);
    return switch (column) {
      case 0 -> planet.name() == null || planet.name().isEmpty() ? "NÃO INFORMADO" : planet.name();
      case 1 -> String.format("%,.1f", planet.diameterKm());
      case 2 -> String.format("%,d", planet.sunDistanceKm());
      case 3 -> String.format("%.02f", Planet.kmToAu(planet.sunDistanceKm()));
      case 4 -> formatType(planet.type());
      default -> "";
    };
  }

  private static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gasoso";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}
