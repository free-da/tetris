package application.controller;

import java.awt.Point;

import application.model.SingleKlotzModel;
import application.model.TetrisGridModel;
import application.model.TetrisShapeModel;
import application.model.VectorDirectionsModel;

public class MovementController {
	TetrisShapeModel shapeModel;
	TetrisGridModel gridModel;
	
	public MovementController(TetrisShapeModel shapeModel, TetrisGridModel gridModel) {
		this.shapeModel = shapeModel;
		this.gridModel = gridModel;
	}

	public boolean moveIsInBounds() {
		for (Point klotzCoordinate: shapeModel.getFourKlotzCoordinates()) {
			int y = (int)klotzCoordinate.getY();
			int x = (int)klotzCoordinate.getX();
			if ( x < 0  || (x >= gridModel.getNumberOfColumns()-1) || (y > gridModel.getNumberOfRows()) ) { 
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
	
	public void moveLeft(TetrisGridController grid) {
		shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX()-1, (int)shapeModel.getAnchorPoint().getY());
		if (moveIsInBounds()) {
			grid.refreshGrid();
			return;
		} else {
			shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX()+1, (int)shapeModel.getAnchorPoint().getY());
			grid.refreshGrid(); // probably superfluous
		}
	}

	public void moveRight(TetrisGridController grid) {
		if (moveIsInBounds()) {
			shapeModel.setAnchorPoint((int)shapeModel.getAnchorPoint().getX()+1, (int)shapeModel.getAnchorPoint().getY());
			grid.refreshGrid();
		}
	}

	public void drop(TetrisGridController grid) {
	}

	public void moveDown(TetrisGridController grid) {
		Point anchorPoint = shapeModel.getAnchorPoint();
		shapeModel.setAnchorPoint((int)anchorPoint.getX(), (int)anchorPoint.getY()+1);
		grid.refreshGrid();
	}

	public void rotateRight(TetrisGridController grid) {
		for (SingleKlotzModel Klotz : shapeModel.getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Klotz.setDirection(VectorDirectionsModel.rotateRight(Klotz.getDirection()));
			grid.refreshGrid();
		}
	}
	
	public void rotateLeft(TetrisGridController grid) {
		for (SingleKlotzModel Klotz : shapeModel.getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Klotz.setDirection(VectorDirectionsModel.rotateLeft(Klotz.getDirection()));
			grid.refreshGrid();
		}
	}
}
