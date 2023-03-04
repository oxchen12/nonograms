package com.comp301.a09nonograms.view;

import com.comp301.a09nonograms.PuzzleLibrary;
import com.comp301.a09nonograms.controller.Controller;
import com.comp301.a09nonograms.controller.ControllerImpl;
import com.comp301.a09nonograms.model.Model;
import com.comp301.a09nonograms.model.ModelImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    Model model = new ModelImpl(PuzzleLibrary.create());
    Controller controller = new ControllerImpl(model);
    FXComponent messageView = new MessageView(controller);
    FXComponent puzzleView = new PuzzleView(controller);
    FXComponent navigationView = new NavigationView(controller);
    FXComponent resetView = new ResetView(controller);

    BorderPane root = new BorderPane();
    root.setPrefSize(800, 550);
    root.setTop(messageView.render());
    root.setCenter(puzzleView.render());
    root.setLeft(resetView.render());
    root.setRight(navigationView.render());
    model.addObserver(
        observer -> {
          root.setTop(messageView.render());
          root.setCenter(puzzleView.render());
        });
    Scene scene = new Scene(root, 1000, 750);
    scene.getStylesheets().add("style/main.css");
    stage.setScene(scene);
    stage.setTitle("Nonograms");
    stage.show();
  }
}
