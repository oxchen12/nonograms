package com.comp301.a09nonograms.controller;

import com.comp301.a09nonograms.model.Clues;
import com.comp301.a09nonograms.model.Model;

import java.util.Random;

public class ControllerImpl implements Controller {
  private Model _model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    _model = model;
  }

  @Override
  public Model getModel() {
    return _model;
  }

  @Override
  public Clues getClues() {
    return _model;
  }

  @Override
  public boolean isSolved() {
    return _model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    _model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    _model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    if (_model.getPuzzleIndex() < getPuzzleCount() - 1) {
      _model.setPuzzleIndex(_model.getPuzzleIndex() + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    if (_model.getPuzzleIndex() > 0) {
      _model.setPuzzleIndex(_model.getPuzzleIndex() - 1);
    }
  }

  @Override
  public void randPuzzle() {
    // _model.setPuzzleIndex(new Random().nextInt(getPuzzleCount()));
    Random random = new Random();
    int newPuzzle = random.nextInt(getPuzzleCount());
    while (newPuzzle == _model.getPuzzleIndex()) newPuzzle = random.nextInt(getPuzzleCount());
    _model.setPuzzleIndex(newPuzzle);
  }

  @Override
  public void clearBoard() {
    _model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return _model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return _model.getPuzzleCount();
  }
}
