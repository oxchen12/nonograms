package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.model.Model;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent {
  private Controller _controller;

  public PuzzleView(Controller controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    _controller = controller;
  }

  @Override
  public Parent render() {
    Model model = _controller.getModel();
    GridPane rowClues = new GridPane();
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getRowCluesLength(); j++) {
        int clueNum = model.getRowClues(i)[j];
        Label rowClue = (clueNum == 0) ? new Label("") : new Label(Integer.toString(clueNum));
        rowClue.setPrefSize(30, 30);
        rowClue.setAlignment(Pos.CENTER);
        rowClues.add(rowClue, j, i);
      }
    }

    GridPane colClues = new GridPane();
    for (int i = 0; i < model.getWidth(); i++) {
      for (int j = 0; j < model.getColCluesLength(); j++) {
        int clueNum = model.getColClues(i)[j];
        Label colClue = (clueNum == 0) ? new Label("") : new Label(Integer.toString(clueNum));
        colClue.setPrefSize(30, 30);
        colClue.setAlignment(Pos.CENTER);
        colClues.add(colClue, i, j);
      }
    }

    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    for (int i = 0; i < model.getHeight(); i++) {
      for (int j = 0; j < model.getWidth(); j++) {
        StackPane cur = new StackPane();
        cur.setPrefSize(30, 30);
        if (_controller.isShaded(i, j)) {
          cur.getStyleClass().add("game-cell-shaded");
        } else if (_controller.isEliminated(i, j)) {
          cur.getStyleClass().add("game-cell-eliminated");
        } else {
          cur.getStyleClass().add("game-cell-empty");
        }
        final int iFinal = i;
        final int jFinal = j;
        cur.setOnMouseClicked(
            mouseEvent -> {
              switch (mouseEvent.getButton()) {
                case PRIMARY:
                  _controller.toggleShaded(iFinal, jFinal);
                  break;
                case SECONDARY:
                  _controller.toggleEliminated(iFinal, jFinal);
                  break;
              }
            });
        grid.add(cur, j, i);
      }
    }

    GridPane out = new GridPane();
    out.add(rowClues, 0, 1);
    out.add(colClues, 1, 0);
    out.add(grid, 1, 1);
    out.setAlignment(Pos.CENTER);
    return out;
  }
}
