package application.controller;

import java.util.Random;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import application.model.TetrisShapeModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class MainWindowController {
	TetrisShapeModel newShape;
	TetrisGridController gameBoardGridController;
	MovementController movement;
	
	@FXML
	Canvas gameboardCanvas, nextUpCanvas;
	

	public void initialize() {
		TetrisGridModel gameboardGridModel = new TetrisGridModel(31, 15);
		gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		newShape = gameBoardGridController.newTetrisShape();
		movement = new MovementController(newShape, gameboardGridModel);

		TetrisGridModel nextGridModel = new TetrisGridModel(14, 7);
		NextGridController nextGridController = new NextGridController(nextUpCanvas, nextGridModel);
		nextGridController.newTetrisShape();
	}

	public void setSceneAndSetupListeners(Scene scene) {
		// TODO Auto-generated method stub
		InputEventController inputEventController = new InputEventController(scene, movement, gameBoardGridController);
	}   

}
