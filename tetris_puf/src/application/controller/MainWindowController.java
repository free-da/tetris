package application.controller;

import java.util.Random;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;

public class MainWindowController {
	@FXML
	Canvas gameboardCanvas, nextUpCanvas;

	public void initialize() {
		TetrisGridModel gameboardGridModel = new TetrisGridModel(32, 16);

		TetrisGridController gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		gameBoardGridController.newTetrisShape();

		TetrisGridModel nextUpGridModel = new TetrisGridModel(16, 8);

		TetrisGridController nextUpGridController = new TetrisGridController(nextUpCanvas, nextUpGridModel);
	}   

}
