package br.pucpr.planet;

import br.pucpr.print.TablePrinter;
import br.pucpr.user.Theme;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class PlanetasPrinter extends TablePrinter<Planet> {
  private static final double KM_POR_UA = 149_600_000.0;

  public void print(ArrayList<Planet> planets, boolean alignRight, Theme theme) {
    super.print(planets, alignRight, theme);
  }

  @Override
  protected String[] headers() {
    return new String[] {"Nome", "Diâmetro", "Dist. sol (km)", "Dist. sol (ua)", "Tipo"};
  }

  @Override
  protected int[] widths() {
    return new int[] {12, 12, 16, 14, 8};
  }

  @Override
  protected String[] formatRow(Planet planet) {
    return new String[] {
      planet.name(),
      formatDiameter(planet.diameterKm()),
      formatKm(planet.sunDistanceKm()),
      formatUa(planet.sunDistanceKm()),
      formatType(planet.type())
    };
  }

  @Override
  protected String emptyMessage() {
    return "ERRO: Lista de planetas vazia ou nula.";
  }

  private static String formatDiameter(double diameterKm) {
    var format =
        new DecimalFormat("#,##0.0", DecimalFormatSymbols.getInstance(Locale.of("pt", "BR")));
    return format.format(diameterKm);
  }

  private static String formatKm(long sunDistanceKm) {
    var format =
        new DecimalFormat("#,##0", DecimalFormatSymbols.getInstance(Locale.of("pt", "BR")));
    return format.format(sunDistanceKm);
  }

  private static String formatUa(long sunDistanceKm) {
    var format =
        new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.of("pt", "BR")));
    return format.format(sunDistanceKm / KM_POR_UA);
  }

  private static String formatType(PlanetType type) {
    return switch (type) {
      case ROCK -> "Rochoso";
      case GAS -> "Gososo";
      case ICE -> "Gelado";
      case DWARF -> "Anão";
    };
  }
}
