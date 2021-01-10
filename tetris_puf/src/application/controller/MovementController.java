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
	MainWindowController mainWindowController;
	
	public MovementController(TetrisShapeModel shapeModel, TetrisGridModel gridModel, TetrisGridController gridController, TetrisGridController nextController, MainWindowController mainWindowController) {
		this.shapeModel = shapeModel;
		this.gridModel = gridModel;
		this.gridController = gridController;
		this.nextController = nextController;
		this.mainWindowController = mainWindowController;
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
	
	private void setShapeToNewPosition(int offsetX, int offsetY) {
		shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX() + offsetX, (int)shapeModel.getAnchorPoint().getY() + offsetY);
	}
	
	public void moveLeft() {
		int offsetX = -1;
		int offsetY = 0;
		if (positionIsLegal(offsetX, offsetY)) {
			setShapeToNewPosition(offsetX, offsetY);
		}
	}

	public void moveRight() {
		int offsetX = 1;
		int offsetY = 0;
		if (positionIsLegal(offsetX, offsetY)) {
			setShapeToNewPosition(offsetX, offsetY);
		}
	}

	public void moveDown() {
		int offsetX = 0;
		int offsetY = 1;
		if (positionIsLegal(offsetX, offsetY)) {
			setShapeToNewPosition(offsetX, offsetY);
		} else {
			lockInGridAndMakeNewShape();
		}
	}
	
	public void lockInGridAndMakeNewShape() {
		for (Point klotzCoordinate: shapeModel.getFourKlotzCoordinates()) {
			gridModel.setKlotzOfCell((int)klotzCoordinate.getY(), (int)klotzCoordinate.getX(), shapeModel.getKlotzType());
			//minimal game over
			if ((int)klotzCoordinate.getY()==0) {
				System.out.println("Game Over");
	            //System.exit(0);
				mainWindowController.gameOver();
				return;
			}
			//end minimal game over
			 
		}
		
		//clear full rows
		//System.out.println("First full row: " + gridController.checkFirstFullRows() + " Calling clearFullRow()");
		while (gridController.checkFirstFullRows() > -1) {
			gridController.clearFullRow(gridController.checkFirstFullRows());
			gridController.incrementScoreCount(1000); //score per cleared row
		}		
		gridController.incrementScoreCount(50); //score per locked shape
		putNextShapeInStartPositionAndNewShapeInNextGrid();
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
			shapeModel.tellObserversIChanged();
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
			shapeModel.tellObserversIChanged();
		} else {
			rotateRight();
		}
	}
}
