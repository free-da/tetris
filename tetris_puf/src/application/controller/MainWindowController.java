package application.controller;

import java.util.Random;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.GridPane;

public class MainWindowController {
//	GameBoardView gameboard = new GameBoardView();
//	TetrisShape nextUpShape;
	
	@FXML
	Canvas gameboardCanvas, nextUpCanvas;

//	@FXML
//	private GridPane playingGrid, nextUpGrid;
	
	public void initialize() {
		TetrisGridModel gameboardGridModel = new TetrisGridModel(32, 16);
		gameboardGridModel.setKlotzOfCell(3, 10, KlotzTypeModel.ZKlotz);
		gameboardGridModel.setKlotzOfCell(16, 5, KlotzTypeModel.OKlotz);

		TetrisGridController gameBoardGridController = new TetrisGridController(gameboardCanvas, gameboardGridModel);
		
		TetrisGridModel nextUpGridModel = new TetrisGridModel(16, 8);
		nextUpGridModel.setKlotzOfCell(3, 5, KlotzTypeModel.TKlotz);
		nextUpGridModel.setKlotzOfCell(9, 5, KlotzTypeModel.IKlotz);

		TetrisGridController nextUpGridController = new TetrisGridController(nextUpCanvas, nextUpGridModel);
	}   
//	
//	private TetrisShape generateRandomShape() {
//		String[] shapeGenerator = new String[] {
//			"I", "O", "L", "Z", "T"
//		};
//		Random Dice = new Random(); 
//		int randomIndex = Dice.nextInt(5); 
//		TetrisShape shape = new TetrisShape(shapeGenerator[randomIndex]);
//		
//		return shape;
//	}
//	
//	@FXML
//	private void placeRandomShapeIntoNextUpGrid() {
//		if (nextUpShape != null) {
//			for(Square klotz:nextUpShape.getSquares()) {
////				klotz.setBackground(new Background(new BackgroundFill(Color.DIMGRAY, null, null)));
//				klotz.setStyle("-fx-background-color: #303030");
//			}
//		}
//		
//		nextUpShape = generateRandomShape();
//		gameboard.insertShapeIntoGrid(nextUpShape, nextUpGrid, 5, 2);
//	}
//	

}
