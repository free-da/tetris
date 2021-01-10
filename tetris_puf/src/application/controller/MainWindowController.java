package application.controller;

import application.model.TetrisGridModel;
import application.model.TetrisShapeModel;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainWindowController {
	TetrisGridModel gameboardGridModel;
	TetrisShapeModel newShape;
	TetrisGridController gameBoardGridController;
	TetrisGridController nextGridController;
	InputEventController inputEventController;
	MovementController movement;
	Stage stage;
	Scene scene;
	
	static double time;
	static AnimationTimer timer;


	@FXML
	Canvas gameboardCanvas, nextUpCanvas;
	public Label pointsLabel;
	
	public void initialize() {
		
		gameboardGridModel = new TetrisGridModel(31, 15);
		gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		newShape = gameBoardGridController.newTetrisShape(0);
		
		TetrisGridModel nextGridModel = new TetrisGridModel(14, 7);
		nextGridController = new TetrisGridController(nextUpCanvas, nextGridModel);
		nextGridController.newTetrisShape(6);
		
		movement = new MovementController(newShape, gameboardGridModel, gameBoardGridController, nextGridController, this);
		//bind pointsLabel
		pointsLabel.textProperty().bind(gameboardGridModel.scoreCountProperty());	
		startAnimationTimer();
	}

	public void setSceneAndSetupListeners(Scene scene, Stage stage) {
		this.scene = scene;
		this.stage = stage;
		inputEventController = new InputEventController(scene, movement);
	}
	
	public void newGame() {
		initialize();
		setSceneAndSetupListeners(scene, stage);
	}
	
	public void startAnimationTimer() {

		//minimal game loop
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                time += 0.015;

                if (time >= 0.5) {
                	movement.moveDown();
                    time = 0;
                }
            }
        };
        timer.start();
	}
	
	public void stopAnimationTimer() {
		timer.stop();
	}
	
	public void gameOver() {
		stopAnimationTimer();
		//game over screen
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        Button quitButton = new Button("Quit");
        Button newGameButton = new Button("New Game");
        
        quitButton.setOnAction(event -> {System.exit(0); dialog.close();});
        newGameButton.setOnAction(event -> {newGame(); dialog.close();});
        
        dialogVbox.getChildren().add(new Text("Game Over"));
        dialogVbox.getChildren().add(new Text("Score"));
        dialogVbox.getChildren().add(newGameButton);
        dialogVbox.getChildren().add(quitButton);
        
        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}

}
