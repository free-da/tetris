package application.model;

import java.awt.Point;

import application.controller.TetrisGridController;

public class TetrisShapeModel {
	Point positionCoordinate;
	int[][] threeKlotzDistancesRelativeToStartPosition;
	KlotzTypeModel klotzType;

	public TetrisShapeModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex) {
		this.klotzType = klotzType;
		switch (klotzType) {
		case IKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{2, 0},
				{3, 0}
			};
			break;
		case OKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, -1},
				{1, 0},
				{1, -1}
			};
			break;
		case JKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{0, 1},
				{0, 2}
			};
			break;
		case LKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{0, 1},
				{0, 2}
			};
			break;
		case ZKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 1},
				{1, 2}
			};
			break;
		case TKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 1},
				{0, 2}
			};
			break;
		case SKlotz:
			positionCoordinate = new Point(rowIndex, columnIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 0},
				{1, -1}
			};
			break;
		}
	}
	
	public Point[] getFourKlotzCoordinates() {
		Point[] fourCoordinates = new Point[] {
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
		positionCoordinate = new Point(x,y);
	}

	public void moveLeft(TetrisGridController grid) {
		setPositionCoordinate((int)positionCoordinate.getX(), (int)positionCoordinate.getY()-1);
		grid.refreshGrid();
	}

	public void moveRight(TetrisGridController grid) {
		setPositionCoordinate((int)positionCoordinate.getX(), (int)positionCoordinate.getY()+1);
		grid.refreshGrid();
	}

	public void drop(TetrisGridController grid) {
		// TODO Auto-generated method stub
//		setPositionCoordinate((int)positionCoordinate.getX() +1, (int)positionCoordinate.getY());
//		grid.refreshGrid();
	}

	public void moveDown(TetrisGridController grid) {
		setPositionCoordinate((int)positionCoordinate.getX() +1, (int)positionCoordinate.getY());
		grid.refreshGrid();
	}

	public void moveUp(TetrisGridController grid) {
		setPositionCoordinate((int)positionCoordinate.getX() -1, (int)positionCoordinate.getY());
		grid.refreshGrid();
	}
}
