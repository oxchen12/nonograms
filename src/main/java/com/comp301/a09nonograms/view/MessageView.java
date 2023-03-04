package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class MessageView implements FXComponent {
  private Controller _controller;

  public MessageView(Controller controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    _controller = controller;
  }

  @Override
  public Parent render() {
    Label out;
    out =
        new Label(
            String.format(
                "Puzzle %d/5 (%d × %d)%s",
                _controller.getPuzzleIndex() + 1,
                _controller.getModel().getWidth(),
                _controller.getModel().getHeight(),
                _controller.isSolved() ? " - Solved!" : ""));
    out.getStyleClass().add("message");
    return out;
  }
}
