package application.controller;

import java.awt.Point;

import application.model.KlotzTypeModel;
import application.model.SingleKlotzModel;
import application.model.TetrisGridModel;
import application.model.TetrisShapeModel;
import application.model.VectorDirectionsModel;

public class MovementController {
	TetrisShapeModel shapeModel;
	TetrisGridModel gridModel;
	TetrisGridController gridController;
	TetrisGridController nextController; 
	
	public MovementController(TetrisShapeModel shapeModel, TetrisGridModel gridModel, TetrisGridController gridController, TetrisGridController nextController) {
		this.shapeModel = shapeModel;
		this.gridModel = gridModel;
		this.gridController = gridController;
		this.nextController = nextController;
	}
	
	public boolean positionIsLegal(int offsetX, int offsetY) {
		for (Point klotzCoordinate: shapeModel.getFourKlotzCoordinates()) {
			int y = (int)klotzCoordinate.getY() + offsetY;
			int x = (int)klotzCoordinate.getX() + offsetX;
			if(singlePointPositionIsIllegal(x, y)) {
				return false;
			}
		}
		return true;
	}

	private boolean singlePointPositionIsIllegal(int x, int y) {
		if ( x < 0  || (x >= gridModel.getNumberOfColumns()) || (y >= gridModel.getNumberOfRows()) || (gridModel.getKlotzOfCell(y, x) != KlotzTypeModel.NoKlotz) ) { 
			System.out.println("YOU CAN'T DO THAT!!");
			return true;
		}
		return false;		
	}

	public void debugCoordinates() {
		for (Point klotzCoordinate:shapeModel.getFourKlotzCoordinates()) {
			int y = (int)klotzCoordinate.getY();
			int x = (int)klotzCoordinate.getX();
			System.out.print("("+ x +","); //DEBUG
			System.out.println(y+ ")"); //DEBUG
		}
	}
	
	private void setShapeToNewPositionAndRefreshGrid(int offsetX, int offsetY) {
		shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX() + offsetX, (int)shapeModel.getAnchorPoint().getY() + offsetY);
		gridController.refreshGrid();
	}
	
	public void moveLeft() {
		int offsetX = -1;
		int offsetY = 0;
		if (positionIsLegal(offsetX, offsetY)) {
			setShapeToNewPositionAndRefreshGrid(offsetX, offsetY);
		}
	}

	public void moveRight() {
		int offsetX = 1;
		int offsetY = 0;
		if (positionIsLegal(offsetX, offsetY)) {
			setShapeToNewPositionAndRefreshGrid(offsetX, offsetY);
		}
	}

	public void moveDown() {
		int offsetX = 0;
		int offsetY = 1;
		if (positionIsLegal(offsetX, offsetY)) {
			setShapeToNewPositionAndRefreshGrid(offsetX, offsetY);
		} else {
			lockInGridAndMakeNewShape();
		}
	}
	
	public void lockInGridAndMakeNewShape() {
		for (Point klotzCoordinate: shapeModel.getFourKlotzCoordinates()) {
			//minimal game over
			if ((int)klotzCoordinate.getY()==0) {
				System.out.println("Game Over");
	            System.exit(0);
			}
			//end minimal game over
			gridModel.setKlotzOfCell((int)klotzCoordinate.getY(), (int)klotzCoordinate.getX(), shapeModel.getKlotzType()); 
		}
		
		//clear full rows and flush
		System.out.println("First full row: " + checkFirstFullRows() + " Calling clearFullRow()");
		if(checkFirstFullRows() > -1) {
			clearFullRow(checkFirstFullRows());
		}
		
		incrementScoreCount(50);
		putNextShapeInStartPositionAndNewShapeInNextGrid();
		gridController.refreshGrid();
	}
	
	public void putNextShapeInStartPositionAndNewShapeInNextGrid() {
		KlotzTypeModel nextShapeKlotzType = nextController.newShape.getKlotzType();
		shapeModel = gridController.newTetrisShape(0, nextShapeKlotzType);
		nextController.newTetrisShape(6);
	}

	public void rotateRight() {
		//do not rotate OKlotzes
		if (shapeModel.getKlotzType()==KlotzTypeModel.OKlotz) {
			return;
		}
		
		for (SingleKlotzModel Klotz : shapeModel.getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Klotz.setDirection(VectorDirectionsModel.rotateRight(Klotz.getDirection()));
		}
		
		boolean positionIsLegal = true;
		for (Point positionCoordinate : shapeModel.getFourKlotzCoordinates()) {
			if (singlePointPositionIsIllegal((int)positionCoordinate.getX(), (int)positionCoordinate.getY())) {
				positionIsLegal = false;
			}
		}
		
		if (positionIsLegal) {
			gridController.refreshGrid();	
		} else {
			rotateLeft();
		}
	}
	
	public void rotateLeft() {
		//do not rotate OKlotzes
		if (shapeModel.getKlotzType()==KlotzTypeModel.OKlotz) {
			return;
		}
		for (SingleKlotzModel Klotz : shapeModel.getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Klotz.setDirection(VectorDirectionsModel.rotateLeft(Klotz.getDirection()));
		}
		boolean positionIsLegal = true;
		for (Point positionCoordinate : shapeModel.getFourKlotzCoordinates()) {
			if (singlePointPositionIsIllegal((int)positionCoordinate.getX(), (int)positionCoordinate.getY())) {
				positionIsLegal = false;
			}
		}
		
		if (positionIsLegal) {
			gridController.refreshGrid();	
		} else {
			rotateRight();
		}
	}
	
	private void incrementScoreCount(int increment) {
		int newScore = Integer.parseInt(gridModel.getScoreCount());
		newScore += increment;
		gridModel.setScoreCount(Integer.toString(newScore));
	}
	
	private int checkFirstFullRows() {
		boolean rowIsFull = false;
		for(int i = 0; i < gridModel.getNumberOfRows(); i++) {
			for (int j = 0; j < gridModel.getNumberOfColumns(); j++) {
				if (gridModel.getKlotzOfCell(i, j) == KlotzTypeModel.NoKlotz) {
					rowIsFull = false;
					break;
				} else {
					rowIsFull = true;
				}
			}
			if (rowIsFull) {
				return i;
			}
		}
		return -1;
	}
	
	private void clearFullRow(int rowIndex) {
		//flush row above first full row
		for (int y = rowIndex; y > 0; y--) {
			for (int x = 0; x < gridModel.getNumberOfColumns(); x++) {
				gridModel.setKlotzOfCell(y, x, gridModel.getKlotzOfCell(y-1, x));
			}
		}
		//fill 0th row with empty NoKotz-Types
		for (int x = 0; x < gridModel.getNumberOfColumns(); x++) {
			gridModel.setKlotzOfCell(0, x, KlotzTypeModel.NoKlotz);
		}
	}
}
