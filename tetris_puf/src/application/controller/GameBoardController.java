package application.controller;

import application.model.TetrisShape;
import application.view.GameBoardView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;

public class GameBoardController {

	@FXML
	private GridPane playingGrid, nextUpGrid;
	
	@FXML
	private Label pointsLabel;
	
	@FXML
	private TitledPane pointsTitle, nextUpTitle;
	
	public void initialize() {
		GameBoardView gameboard = new GameBoardView();
	
		gameboard.buildUpGridAndAddPanes(18,36,playingGrid);
		gameboard.buildUpGridAndAddPanes(8,16,nextUpGrid);
		
	 	TetrisShape iShape = new TetrisShape("I");
	 	TetrisShape lShape = new TetrisShape("L");
	 	TetrisShape oShape = new TetrisShape("O");
	 	TetrisShape zShape = new TetrisShape("Z");
	 	TetrisShape tShape = new TetrisShape("T");
	
	 	gameboard.insertShapeIntoGrid(iShape,playingGrid,0,0);
	 	gameboard.insertShapeIntoGrid(lShape,playingGrid,3,2);
	 	gameboard.insertShapeIntoGrid(oShape,playingGrid,6,4);
	 	gameboard.insertShapeIntoGrid(zShape,playingGrid,10,0);
	 	gameboard.insertShapeIntoGrid(tShape,playingGrid,14,4);
	 }   
}
