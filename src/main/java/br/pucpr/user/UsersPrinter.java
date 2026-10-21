package br.pucpr.user;

import br.pucpr.table.TableData;
import java.util.ArrayList;

public class UsersPrinter implements TableData {
  private static final String[] HEADERS = {"ID", "NOME", "EMAIL", "CPF"};

  private final ArrayList<User> users;
  private final boolean maskCpf;

  public UsersPrinter(ArrayList<User> users, boolean maskCpf) {
    this.users = users;
    this.maskCpf = maskCpf;
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
    return users == null ? 0 : users.size();
  }

  @Override
  public String getCell(int row, int column) {
    var user = users.get(row);
    return switch (column) {
      case 0 -> user.id() != null ? user.id().toString() : "0";
      case 1 -> formatName(user.name());
      case 2 -> user.email() == null || !user.email().contains("@") ? "INVÁLIDO" : user.email();
      case 3 -> formatCpf(user.cpf(), maskCpf);
      default -> "";
    };
  }

  private static String formatName(String name) {
    return name == null || name.isEmpty() ? "NÃO INFORMADO" : name;
  }

  private static String formatCpf(String cpf, boolean mask) {
    if (cpf == null || cpf.length() != 11) {
      return "CPF INVÁLIDO";
    }
    if (mask) {
      return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
    }
    return cpf.substring(0, 3)
        + "."
        + cpf.substring(3, 6)
        + "."
        + cpf.substring(6, 9)
        + "-"
        + cpf.substring(9, 11);
  }
}
