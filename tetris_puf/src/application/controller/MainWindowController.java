package application.controller;

import application.model.TetrisGridModel;
import application.model.TetrisShapeModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;

import javafx.stage.Stage;

public class MainWindowController {
	TetrisGridModel gameboardGridModel;
	TetrisShapeModel newShape;
	TetrisGridController gameBoardGridController;
	TetrisGridController nextGridController;
	MovementController movement;
	
	@FXML
	Canvas gameboardCanvas, nextUpCanvas;
	public Label pointsLabel;
	
	public void initialize() {
		
		gameboardGridModel = new TetrisGridModel(31, 15);
		gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		//newShape = gameBoardGridController.newTetrisShape(0);
		
		TetrisGridModel nextGridModel = new TetrisGridModel(14, 7);
		nextGridController = new TetrisGridController(nextUpCanvas, nextGridModel);
		//nextGridController.newTetrisShape(6);
		
		newGame();
		movement = new MovementController(newShape, gameboardGridModel, gameBoardGridController, nextGridController, this);
		//bind pointsLabel
		pointsLabel.textProperty().bind(gameboardGridModel.scoreCountProperty());		
	}

	public void setSceneAndSetupListeners(Scene scene) {
		InputEventController inputEventController = new InputEventController(scene, movement);
	}
	
	public void newGame() {
		gameboardGridModel.initialiseKlotzTypeModelArray();
		gameboardGridModel.setScoreCount("0");
		if(gameBoardGridController.newShape==null)newShape = gameBoardGridController.newTetrisShape(0);
		if(nextGridController.newShape==null)nextGridController.newTetrisShape(6);
	}
	
	public void gameOver() {
		System.out.println("TODO: Display Game Over Screen");
        newGame();
	}

}
