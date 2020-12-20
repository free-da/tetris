package application.model;

import java.awt.Point;

import application.controller.TetrisGridController;

public class TetrisShapeModel {
	Point positionCoordinate;
	int[][] threeKlotzDistancesRelativeToStartPosition;
	KlotzTypeModel klotzType;
	TetrisGridModel tetrisGridModel;

	public TetrisShapeModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex, TetrisGridModel tetrisGridModel) {
		this.klotzType = klotzType;
		this.tetrisGridModel = tetrisGridModel;
		switch (klotzType) {
		case IKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{0, 2},
				{0, 3}
			};
			break;
		case OKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{-1, 0},
				{0, 1},
				{-1, 1}
			};
			break;
		case JKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 0},
				{2, 0}
			};
			break;
		case LKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 0},
				{2, 0}
			};
			break;
		case ZKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{1, 1},
				{2, 1}
			};
			break;
		case TKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{1, 1},
				{2, 0}
			};
			break;
		case SKlotz:
			positionCoordinate = new Point(columnIndex, rowIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{0, 1},
				{-1, 1}
			};
			break;
		}
	}
	
	// returns: point array of all four Klotzes of current Tetromino, starts with anchor point
	public Point[] getFourKlotzCoordinates() {
		Point[] fourCoordinates = new Point[] 	{
			positionCoordinate,
			new Point((int)positionCoordinate.getX() + threeKlotzDistancesRelativeToStartPosition[0][0], (int)positionCoordinate.getY() + threeKlotzDistancesRelativeToStartPosition[0][1]),
			new Point((int)positionCoordinate.getX() + threeKlotzDistancesRelativeToStartPosition[1][0], (int)positionCoordinate.getY() + threeKlotzDistancesRelativeToStartPosition[1][1]),
			new Point((int)positionCoordinate.getX() + threeKlotzDistancesRelativeToStartPosition[2][0], (int)positionCoordinate.getY() + threeKlotzDistancesRelativeToStartPosition[2][1]),
			};
		return fourCoordinates;
	}
	
	public KlotzTypeModel getKlotzType() {
		return klotzType;
	}
	
	public Point getPositionCoordinate() {
		return positionCoordinate;
	}
	
	public void setPositionCoordinate(int x, int y) {
		positionCoordinate.move(x,y);
	}
	
	public boolean moveIsInBounds() {
		for (Point klotzCoordinate:getFourKlotzCoordinates()) {
			
//			if ( (1 >= y-1) || (y+1 > tetrisGridModel.getNumberOfColumns()) || (x+1 > tetrisGridModel.getNumberOfRows()) ) { //intentionally false
//				return false;
//			}
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
//		if ((0 <= (int)positionCoordinate.getY()-1) && ((int)positionCoordinate.getY()-1 <= tetrisGridModel.getNumberOfColumns())) {
		if (moveIsInBounds()) {
			setPositionCoordinate((int)positionCoordinate.getX()-1, (int)positionCoordinate.getY());
			grid.refreshGrid();	
		}
		debugCoordinates();
	}

	public void moveRight(TetrisGridController grid) {
//		if ((0 <= (int)positionCoordinate.getY()+1) && ((int)positionCoordinate.getY()+1 <= tetrisGridModel.getNumberOfColumns() -1)) {
		if (moveIsInBounds()) {
			setPositionCoordinate((int)positionCoordinate.getX()+1, (int)positionCoordinate.getY());
			grid.refreshGrid();
		}
		debugCoordinates();
	}

	public void drop(TetrisGridController grid) {
		// TODO Auto-generated method stub
//		setPositionCoordinate((int)positionCoordinate.getX() +1, (int)positionCoordinate.getY());
//		grid.refreshGrid();
	}

	public void moveDown(TetrisGridController grid) {
		if (moveIsInBounds()) {
			setPositionCoordinate((int)positionCoordinate.getX(), (int)positionCoordinate.getY()+1);
			grid.refreshGrid();
		}
		debugCoordinates();
	}

	public void moveUp(TetrisGridController grid) {
		if (moveIsInBounds()) {
			setPositionCoordinate((int)positionCoordinate.getX(), (int)positionCoordinate.getY()-1);
			grid.refreshGrid();
		}
		debugCoordinates();
	}
}
