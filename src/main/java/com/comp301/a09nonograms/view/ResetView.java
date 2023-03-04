package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ResetView implements FXComponent {
  private Controller _controller;

  public ResetView(Controller controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    _controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane out = new BorderPane();
    Button resetButton = new Button("Reset");
    resetButton.setPrefWidth(100);
    resetButton.setOnAction(e -> _controller.clearBoard());
    resetButton.getStyleClass().add("control-button");
    out.setLeft(new HBox(50));
    out.setCenter(resetButton);
    return out;
  }
}
