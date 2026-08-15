package br.pucpr.print;

import br.pucpr.user.Theme;
import java.util.ArrayList;

public abstract class TablePrinter<T> {

  public void print(ArrayList<T> items, boolean alignRight, Theme theme) {
    if (items == null || items.isEmpty()) {
      System.out.println(emptyMessage());
      return;
    }

    final var borderChar = theme.getBorderChar();
    final var widths = widths();
    final var borderWidth = borderWidth(widths);

    var sb = new StringBuilder();
    sb.repeat(borderChar, borderWidth).append("\n");
    sb.append(formatLine(headers(), widths));
    sb.repeat(borderChar, borderWidth).append("\n");

    for (var item : items) {
      if (item == null) {
        continue;
      }
      sb.append(formatLine(formatRow(item), widths));
    }

    sb.repeat(borderChar, borderWidth).append("\n");

    if (alignRight) {
      var lines = sb.toString().split("\n");
      for (var line : lines) {
        System.out.println("                    " + line);
      }
    } else {
      System.out.print(sb);
    }
  }

  protected abstract String[] headers();

  protected abstract int[] widths();

  protected abstract String[] formatRow(T item);

  protected abstract String emptyMessage();

  private static int borderWidth(int[] widths) {
    var total = 1;
    for (var width : widths) {
      total += width + 3;
    }
    return total;
  }

  private static String formatLine(String[] cells, int[] widths) {
    var sb = new StringBuilder("|");
    for (var i = 0; i < widths.length; i++) {
      sb.append(String.format(" %-" + widths[i] + "s |", cells[i]));
    }
    sb.append("\n");
    return sb.toString();
  }
}
