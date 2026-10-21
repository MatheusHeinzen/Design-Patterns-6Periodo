package br.pucpr.table;

import br.pucpr.user.Theme;

public class Table {
  private static final int EXTRA_WIDTH = 7;

  public void print(TableData data, boolean alignRight, Theme theme) {
    if (data == null || data.getRowCount() == 0) {
      System.out.println("ERRO: Lista vazia ou nula.");
      return;
    }

    var columnCount = data.getColumnCount();
    var widths = new int[columnCount];
    var headers = new String[columnCount];
    var borderWidth = 1;
    for (var c = 0; c < columnCount; c++) {
      headers[c] = data.getHeader(c);
      widths[c] = headers[c].length() + EXTRA_WIDTH;
      borderWidth += widths[c] + 3;
    }

    var sb = new StringBuilder();
    var border = theme.getBorderChar();
    sb.repeat(border, borderWidth).append("\n");
    appendRow(sb, headers, widths);
    sb.repeat(border, borderWidth).append("\n");
    for (var r = 0; r < data.getRowCount(); r++) {
      var cells = new String[columnCount];
      for (var c = 0; c < columnCount; c++) {
        cells[c] = data.getCell(r, c);
      }
      appendRow(sb, cells, widths);
    }
    sb.repeat(border, borderWidth).append("\n");

    if (alignRight) {
      for (var line : sb.toString().split("\n")) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
  }

  private static void appendRow(StringBuilder sb, String[] cells, int[] widths) {
    sb.append("|");
    for (var c = 0; c < cells.length; c++) {
      sb.append(String.format(" %-" + widths[c] + "s |", fit(cells[c], widths[c])));
    }
    sb.append("\n");
  }

  private static String fit(String value, int width) {
    if (value == null) {
      value = "";
    }
    if (value.length() <= width) {
      return value;
    }
    if (width <= 3) {
      return value.substring(0, width);
    }
    return value.substring(0, width - 3) + "...";
  }
}
