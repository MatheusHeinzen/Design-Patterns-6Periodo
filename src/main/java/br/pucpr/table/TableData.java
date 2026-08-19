package br.pucpr.table;

public interface TableData {
  int getColumnCount();

  String getHeader(int column);

  int getRowCount();

  String getCell(int row, int column);
}
