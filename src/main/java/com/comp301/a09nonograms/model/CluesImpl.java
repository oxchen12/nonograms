package com.comp301.a09nonograms.model;

import java.nio.channels.FileLockInterruptionException;

public class CluesImpl implements Clues {
  private int _width;
  private int _height;
  private int[][] _rowClues;
  private int[][] _colClues;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues.length == 0 || colClues.length == 0) {
      throw new IllegalArgumentException();
    }
    _width = colClues.length;
    _height = rowClues.length;
    _rowClues = rowClues;
    _colClues = colClues;
  }

  @Override
  public int getWidth() {
    return _width;
  }

  @Override
  public int getHeight() {
    return _height;
  }

  @Override
  public int[] getRowClues(int index) {
    if (index < 0 || index >= _height) {
      throw new IndexOutOfBoundsException();
    }
    return _rowClues[index];
  }

  @Override
  public int[] getColClues(int index) {
    if (index < 0 || index >= _width) {
      throw new IndexOutOfBoundsException();
    }
    return _colClues[index];
  }

  @Override
  public int getRowCluesLength() {
    return _rowClues[0].length;
  }

  @Override
  public int getColCluesLength() {
    return _colClues[0].length;
  }
}
