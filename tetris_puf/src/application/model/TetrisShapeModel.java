package application.model;

import java.awt.Point;

import application.controller.TetrisGridController;

public class TetrisShapeModel {
	Point anchorPoint;
	SingleKlotzModel[] threeKlotzVectorsRelativeToAnchorPoint;
	KlotzTypeModel klotzType;
	TetrisGridModel tetrisGridModel;

	public TetrisShapeModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex, TetrisGridModel tetrisGridModel) {
		this.klotzType = klotzType;
		this.tetrisGridModel = tetrisGridModel;
		switch (klotzType) {
		case IKlotz:
			anchorPoint = new Point(columnIndex, rowIndex+1);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 2),	
				new SingleKlotzModel(VectorDirectionsModel.NORTH, 1),
			};
			break;
		case OKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.WEST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHWEST, 1),	
			};
			break;
		case JKlotz:
			anchorPoint = new Point(columnIndex, rowIndex+1);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.NORTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHEAST, 1),
			};
			break;
		case LKlotz:
			anchorPoint = new Point(columnIndex, rowIndex+1);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.NORTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHWEST, 1),
			};
			break;
		case ZKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.EAST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHWEST, 1),			
			};
			break;
		case TKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.EAST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.WEST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),
			};
			break;
		case SKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			threeKlotzVectorsRelativeToAnchorPoint = new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.WEST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHEAST, 1),
			};
			break;
		}
	}
	
	// returns: point array of all four Klotzes of current Tetromino, starts with anchor point
	public Point[] getFourKlotzCoordinates() {
		Point[] fourCoordinates = new Point[4];
		fourCoordinates[0] = anchorPoint;
		int counter = 1;
		for (SingleKlotzModel Klotz : threeKlotzVectorsRelativeToAnchorPoint) {
			Point coordinate = new Point();
			switch(Klotz.direction) {
			case NORTH:
				coordinate.move((int)anchorPoint.getX(), (int)anchorPoint.getY() - Klotz.norm);
				break;
			case NORTHWEST:
				coordinate.move((int)anchorPoint.getX() + Klotz.norm, (int)anchorPoint.getY() - Klotz.norm);
				break;
			case WEST:
				coordinate.move((int)anchorPoint.getX() + Klotz.norm, (int)anchorPoint.getY());
				break;
			case SOUTHWEST:
				coordinate.move((int)anchorPoint.getX() + Klotz.norm, (int)anchorPoint.getY() + Klotz.norm);
				break;
			case SOUTH:
				coordinate.move((int)anchorPoint.getX(), (int)anchorPoint.getY() + Klotz.norm);
				break;
			case SOUTHEAST:
				coordinate.move((int)anchorPoint.getX() - Klotz.norm, (int)anchorPoint.getY() + Klotz.norm);
				break;
			case EAST:
				coordinate.move((int)anchorPoint.getX() - Klotz.norm, (int)anchorPoint.getY());
				break;
			case NORTHEAST:
				coordinate.move((int)anchorPoint.getX() - Klotz.norm, (int)anchorPoint.getY() - Klotz.norm);
				break;
			}
			fourCoordinates[counter] = coordinate;
			counter++;
		}
		return fourCoordinates;
	}
	
	public KlotzTypeModel getKlotzType() {
		return klotzType;
	}
	
	public Point getPositionCoordinate() {
		return anchorPoint;
	}
	
	public void setPositionCoordinate(int x, int y) {
		anchorPoint.move(x,y);
	}
	
	public boolean moveIsInBounds() {
		for (Point klotzCoordinate:getFourKlotzCoordinates()) {
			int y = (int)klotzCoordinate.getY();
			int x = (int)klotzCoordinate.getX();
			if ( x < 0  || (x >= tetrisGridModel.getNumberOfColumns()-1) || (y > tetrisGridModel.getNumberOfRows()) ) { 
				return false;
			}
		}
		return true;
	}

	public void debugCoordinates() {
		for (Point klotzCoordinate:getFourKlotzCoordinates()) {
			int y = (int)klotzCoordinate.getY();
			int x = (int)klotzCoordinate.getX();
			System.out.print("("+ x +","); //DEBUG
			System.out.println(y+ ")"); //DEBUG
		}
	}
	
	public void moveLeft(TetrisGridController grid) {
		setPositionCoordinate((int)anchorPoint.getX()-1, (int)anchorPoint.getY());
		if (moveIsInBounds()) {
			grid.refreshGrid();
			return;
		} else {
			setPositionCoordinate((int)anchorPoint.getX()+1, (int)anchorPoint.getY());
			grid.refreshGrid(); // probably superfluous
		}
		debugCoordinates();
	}

	public void moveRight(TetrisGridController grid) {
		if (moveIsInBounds()) {
			setPositionCoordinate((int)anchorPoint.getX()+1, (int)anchorPoint.getY());
			grid.refreshGrid();
		}
		debugCoordinates();
	}

	public void drop(TetrisGridController grid) {
	}

	public void moveDown(TetrisGridController grid) {
		setPositionCoordinate((int)anchorPoint.getX(), (int)anchorPoint.getY()+1);
		grid.refreshGrid();
		debugCoordinates();
	}

	public void rotateRight(TetrisGridController grid) {
		for (SingleKlotzModel Klotz : threeKlotzVectorsRelativeToAnchorPoint) {
			Klotz.direction = VectorDirectionsModel.rotateRight(Klotz.direction);
			if(moveIsInBounds()) {
				grid.refreshGrid();
			} else {
				this.rotateLeft(grid);
			}
		}
	}
	
	public void rotateLeft(TetrisGridController grid) {
		for (SingleKlotzModel Klotz : threeKlotzVectorsRelativeToAnchorPoint) {
			Klotz.direction = VectorDirectionsModel.rotateLeft(Klotz.direction);
			grid.refreshGrid();
		}
	}
}
