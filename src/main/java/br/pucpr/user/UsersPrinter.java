package br.pucpr.user;

import br.pucpr.print.TablePrinter;
import java.util.ArrayList;

public class UsersPrinter extends TablePrinter<User> {
  private boolean maskCpf;

  public void print(ArrayList<User> users, boolean maskCpf, boolean alignRight, Theme theme) {
    this.maskCpf = maskCpf;
    print(users, alignRight, theme);
  }

  @Override
  protected String[] headers() {
    return new String[] {"ID", "NOME", "EMAIL", "CPF"};
  }

  @Override
  protected int[] widths() {
    return new int[] {5, 20, 22, 14};
  }

  @Override
  protected String[] formatRow(User user) {
    return new String[] {
      formatId(user.id()),
      formatName(user),
      validateAndFormatEmail(user.email()),
      formatCpf(user.cpf(), maskCpf)
    };
  }

  @Override
  protected String emptyMessage() {
    return "ERRO: Lista de usuários vazia ou nula.";
  }

  private static String formatId(Long id) {
    return id != null ? id.toString() : "0";
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

  private static String validateAndFormatEmail(String email) {
    return email == null || !email.contains("@") ? "INVÁLIDO" : email;
  }

  private static String formatName(User user) {
    var name = user.name();
    if (name == null || name.isEmpty()) {
      return "NÃO INFORMADO";
    }
    if (name.length() > 20) {
      name = name.substring(0, 17) + "...";
    }
    return name;
  }
}
