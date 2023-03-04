package com.comp301.a09nonograms.model;

public class Puzzle {
  private Clues _clues;
  private Board _board;

  public Puzzle(Clues clues, Board board) {
    if (clues == null || board == null) {
      throw new IllegalArgumentException();
    }
    _clues = clues;
    _board = board;
  }

  public Clues getClues() {
    return _clues;
  }

  public Board getBoard() {
    return _board;
  }
}
