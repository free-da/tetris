package application.controller;

import application.model.TetrisGridModel;
import application.model.TetrisShapeModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

public class MainWindowController {
	TetrisShapeModel newShape;
	TetrisGridController gameBoardGridController;
	TetrisGridController nextGridController;
	MovementController movement;
	
	@FXML
	Canvas gameboardCanvas, nextUpCanvas;
	public Label pointsLabel;
	
	public void initialize() {
		
		TetrisGridModel gameboardGridModel = new TetrisGridModel(31, 15);
		gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		newShape = gameBoardGridController.newTetrisShape(0);
		
		TetrisGridModel nextGridModel = new TetrisGridModel(14, 7);
		nextGridController = new TetrisGridController(nextUpCanvas, nextGridModel);
		nextGridController.newTetrisShape(6);
		
		movement = new MovementController(newShape, gameboardGridModel, gameBoardGridController, nextGridController);
		//bind pointsLabel
		pointsLabel.textProperty().bind(gameboardGridModel.scoreCountProperty());
		gameboardGridModel.setScoreCount("0");
		
	}

	public void setSceneAndSetupListeners(Scene scene) {
		InputEventController inputEventController = new InputEventController(scene, movement);
	}   

}
