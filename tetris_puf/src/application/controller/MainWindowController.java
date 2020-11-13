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
	
	@FXML
	Canvas gameboardCanvas, nextUpCanvas;
	

	public void initialize() {
		TetrisGridModel gameboardGridModel = new TetrisGridModel(32, 16);
		gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		newShape = gameBoardGridController.newTetrisShape();

		TetrisGridModel nextGridModel = new TetrisGridModel(16, 7);
		NextGridController nextGridController = new NextGridController(nextUpCanvas, nextGridModel);
//		nextGridController.newTetrisShape();
	}

	public void setSceneAndSetupListeners(Scene scene) {
		// TODO Auto-generated method stub
		InputEventController inputEventController = new InputEventController(scene, newShape, gameBoardGridController);
	}   

}
