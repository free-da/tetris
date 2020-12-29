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
	
	public boolean positionIsLegal() {
		for (Point klotzCoordinate: shapeModel.getFourKlotzCoordinates()) {
			int y = (int)klotzCoordinate.getY();
			int x = (int)klotzCoordinate.getX();
			if ( x < 0  || (x >= gridModel.getNumberOfColumns()-1) || (y >= gridModel.getNumberOfRows() -1) || (gridModel.getKlotzOfCell(y, x) != KlotzTypeModel.NoKlotz) ) { 
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
	
	public void moveLeft() {
		shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX()-1, (int)shapeModel.getAnchorPoint().getY());
		if (positionIsLegal()) {
			gridController.refreshGrid();
			return;
		} else {
			shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX()+1, (int)shapeModel.getAnchorPoint().getY());
			gridController.refreshGrid(); // probably superfluous
		}
	}

	public void moveRight() {
		if (positionIsLegal()) {
			shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX()+1, (int)shapeModel.getAnchorPoint().getY());
			gridController.refreshGrid();
		}
	}

	public void drop() {
	}

	public void moveDown() {
		if (positionIsLegal()) {
			Point anchorPoint = shapeModel.getAnchorPoint();
			shapeModel.setAnchorPoint((int)anchorPoint.getX(), (int)anchorPoint.getY()+1);
			gridController.refreshGrid();
		} else {
			lockInGridAndMakeNewShape();
		}
	}
	
	public void lockInGridAndMakeNewShape() {
		for (Point klotzCoordinate: shapeModel.getFourKlotzCoordinates()) {
			gridModel.setKlotzOfCell((int)klotzCoordinate.getY(), (int)klotzCoordinate.getX(), shapeModel.getKlotzType()); 
		}
		putNewShapeInStartPosition();
		gridController.refreshGrid();
	}
	
	public void putNewShapeInStartPosition() {
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
