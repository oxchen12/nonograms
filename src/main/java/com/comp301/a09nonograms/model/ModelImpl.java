package com.comp301.a09nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private List<Puzzle> _puzzles;
  private int _active;
  private List<ModelObserver> _observers;

  public ModelImpl(List<Clues> clues) {
    if (clues == null) {
      throw new IllegalArgumentException();
    }
    _puzzles = new ArrayList<>();
    for (Clues clue : clues) {
      if (clue == null) {
        throw new IllegalArgumentException();
      }
      _puzzles.add(new Puzzle(clue, new BoardImpl(clue.getHeight(), clue.getWidth())));
    }
    _active = 0;
    _observers = new ArrayList<>();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _puzzles.get(_active).getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _puzzles.get(_active).getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return _puzzles.get(_active).getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    _puzzles.get(_active).getBoard().toggleCellShaded(row, col);
    notifyObservers();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    _puzzles.get(_active).getBoard().toggleCellEliminated(row, col);
    notifyObservers();
  }

  @Override
  public void clear() {
    _puzzles.get(_active).getBoard().clear();
    notifyObservers();
  }

  @Override
  public int getWidth() {
    return _puzzles.get(_active).getClues().getWidth();
  }

  @Override
  public int getHeight() {
    return _puzzles.get(_active).getClues().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return _puzzles.get(_active).getClues().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return _puzzles.get(_active).getClues().getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return _puzzles.get(_active).getClues().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return _puzzles.get(_active).getClues().getColCluesLength();
  }

  @Override
  public int getPuzzleCount() {
    return _puzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    return _active;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleCount()) {
      throw new IndexOutOfBoundsException();
    }
    _active = index;
    notifyObservers();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    _observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    _observers.remove(observer);
  }

  private void notifyObservers() {
    for (ModelObserver obs : _observers) {
      obs.update(this);
    }
  }

  @Override
  public boolean isSolved() {
    int cellCounter;
    int consecCounter;

    for (int i = 0; i < getHeight(); i++) {
      cellCounter = 0;
      for (int curClue : getRowClues(i)) {
        consecCounter = 0;
        if (curClue != 0) {
          // go to next shaded cell
          while (cellCounter < getWidth()) {
            if (!isShaded(i, cellCounter)) {
              cellCounter++;
            } else {
              break;
            }
          }

          // count consecutive shaded cells
          while (cellCounter < getWidth()) {
            if (isShaded(i, cellCounter)) {
              consecCounter++;
              cellCounter++;
            } else {
              break;
            }
          }

          // ensure that counted consecutive shaded cells match current clue
          if (consecCounter != curClue) {
            return false;
          }
        }
      }

      // ensure that there are no extra shaded cells past completed clues
      while (cellCounter < getWidth()) {
        if (isShaded(i, cellCounter)) {
          return false;
        }
        cellCounter++;
      }
    }

    for (int i = 0; i < getWidth(); i++) {
      cellCounter = 0;
      for (int curClue : getColClues(i)) {
        consecCounter = 0;
        if (curClue != 0) {
          // go to next shaded cell
          while (cellCounter < getHeight()) {
            if (!isShaded(cellCounter, i)) {
              cellCounter++;
            } else {
              break;
            }
          }

          // count consecutive shaded cells
          while (cellCounter < getHeight()) {
            if (isShaded(cellCounter, i)) {
              consecCounter++;
              cellCounter++;
            } else {
              break;
            }
          }

          // ensure that counted consecutive shaded cells match current clue
          if (consecCounter != curClue) {
            return false;
          }
        }
      }

      // ensure that there are no extra shaded cells past completed clues
      while (cellCounter < getHeight()) {
        if (isShaded(cellCounter, i)) {
          return false;
        }
        cellCounter++;
      }
    }

    return true;

    /*    for (int i = 0; i < getHeight(); i++) {
      if (!Arrays.equals(findSegmentLengths(i, false), getRowClues(i))) {
        return false;
      }
    }

    for (int i = 0; i < getWidth(); i++) {
      if (!Arrays.equals(findSegmentLengths(i, true), getColClues(i))) {
        return false;
      }
    }

    return true;*/
  }

  /*private int[] findSegmentLengths(int rowOrCol, boolean isCol) {
    List<Integer> segments = new ArrayList<>();
    boolean cellIsShaded;
    int lengthCounter = 0;
    int rowOrColLength = isCol ? getHeight() : getWidth();
    for (int i = 0; i < rowOrColLength; i++) {
      cellIsShaded = isCol ? isShaded(rowOrCol, i) : isShaded(i, rowOrCol);
      if (!cellIsShaded && lengthCounter != 0) {
        segments.add(lengthCounter);
      }
      lengthCounter = cellIsShaded ? lengthCounter + 1 : 0;
    }

    for (int i = segments.size(); i < (isCol ? getColCluesLength() : getRowCluesLength()); i++) {
      segments.add(0, 0);
    }

    int[] out = new int[segments.size()];
    for (int i = 0; i < segments.size(); i++) out[i] = segments.get(i);
    return out;
  }*/
}
