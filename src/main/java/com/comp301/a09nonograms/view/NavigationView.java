package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NavigationView implements FXComponent {
  private Controller _controller;

  public NavigationView(Controller controller) {
    if (controller == null) {
      throw new IllegalArgumentException();
    }
    _controller = controller;
  }

  @Override
  public Parent render() {
    BorderPane out = new BorderPane();

    VBox navigation = new VBox(10);
    navigation.setAlignment(Pos.CENTER);
    Button prevButton = new Button("Previous");
    Button randButton = new Button("Random");
    Button nextButton = new Button("Next");
    prevButton.setPrefWidth(100);
    randButton.setPrefWidth(100);
    nextButton.setPrefWidth(100);
    prevButton.setOnAction(e -> _controller.prevPuzzle());
    randButton.setOnAction(e -> _controller.randPuzzle());
    nextButton.setOnAction(e -> _controller.nextPuzzle());
    prevButton.getStyleClass().add("control-button");
    randButton.getStyleClass().add("control-button");
    nextButton.getStyleClass().add("control-button");
    navigation.getChildren().addAll(prevButton, randButton, nextButton);

    out.setCenter(navigation);
    out.setRight(new HBox(50));
    return out;
  }
}
