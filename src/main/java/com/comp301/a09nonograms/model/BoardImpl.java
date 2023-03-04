package com.comp301.a09nonograms.model;

public class BoardImpl implements Board {
  // space = 0, shaded = 1, eliminated = 2
  private int[][] _cells;

  public BoardImpl(int numRows, int numCols) {
    if (numRows < 1 || numCols < 1) {
      throw new IllegalArgumentException();
    }
    _cells = new int[numRows][numCols];
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _cells[row][col] == 1;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _cells[row][col] == 2;
  }

  @Override
  public boolean isSpace(int row, int col) {
    return _cells[row][col] == 0;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row < 0 || col < 0 || row >= _cells.length || col >= _cells[0].length) {
      throw new IllegalArgumentException();
    }
    _cells[row][col] = _cells[row][col] == 1 ? 0 : 1;
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row < 0 || col < 0 || row >= _cells.length || col >= _cells[0].length) {
      throw new IllegalArgumentException();
    }
    _cells[row][col] = _cells[row][col] == 2 ? 0 : 2;
  }

  @Override
  public void clear() {
    _cells = new int[_cells.length][_cells[0].length];
  }
}
