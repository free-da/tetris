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
			if ( x < 0  || (x >= gridModel.getNumberOfColumns()) || (y >= gridModel.getNumberOfRows()) || (gridModel.getKlotzOfCell(y, x) != KlotzTypeModel.NoKlotz) ) { 
				return false;
			}
		}
		return true;
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

	public void drop() {
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
			gridModel.setKlotzOfCell((int)klotzCoordinate.getY(), (int)klotzCoordinate.getX(), shapeModel.getKlotzType()); 
		}
		putNextShapeInStartPositionAndNewShapeInNextGrid();
		gridController.refreshGrid();
	}
	
	public void putNextShapeInStartPositionAndNewShapeInNextGrid() {
		KlotzTypeModel nextShapeKlotzType = nextController.newShape.getKlotzType();
		shapeModel = gridController.newTetrisShape(0, nextShapeKlotzType);
		nextController.newTetrisShape(6);
	}

	public void rotateRight() {
		for (SingleKlotzModel Klotz : shapeModel.getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Klotz.setDirection(VectorDirectionsModel.rotateRight(Klotz.getDirection()));
			gridController.refreshGrid();
		}
	}
	
	public void rotateLeft() {
		for (SingleKlotzModel Klotz : shapeModel.getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Klotz.setDirection(VectorDirectionsModel.rotateLeft(Klotz.getDirection()));
			gridController.refreshGrid();
		}
	}
}
