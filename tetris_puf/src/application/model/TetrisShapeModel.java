package application.model;

import java.awt.Point;

public class TetrisShapeModel {
	Point startPositionCoordinate;
	int[][] threeKlotzDistancesRelativeToStartPosition;
	KlotzTypeModel klotzType;

	public TetrisShapeModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex) {
		this.klotzType = klotzType;
		switch (klotzType) {
		case IKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{2, 0},
				{3, 0}
			};
			break;
		case OKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, -1},
				{1, 0},
				{1, -1}
			};
			break;
		case JKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{0, 1},
				{0, 2}
			};
			break;
		case LKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{1, 0},
				{0, 1},
				{0, 2}
			};
			break;
		case ZKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 1},
				{1, 2}
			};
			break;
		case TKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex-1);
			threeKlotzDistancesRelativeToStartPosition = new int[][]{
				{0, 1},
				{1, 1},
				{0, 2}
			};
			break;
		case SKlotz:
			startPositionCoordinate = new Point(rowIndex, columnIndex);
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
			startPositionCoordinate,
			new Point((int)startPositionCoordinate.getX() + threeKlotzDistancesRelativeToStartPosition[0][0], (int)startPositionCoordinate.getY() + threeKlotzDistancesRelativeToStartPosition[0][1]),
			new Point((int)startPositionCoordinate.getX() + threeKlotzDistancesRelativeToStartPosition[1][0], (int)startPositionCoordinate.getY() + threeKlotzDistancesRelativeToStartPosition[1][1]),
			new Point((int)startPositionCoordinate.getX() + threeKlotzDistancesRelativeToStartPosition[2][0], (int)startPositionCoordinate.getY() + threeKlotzDistancesRelativeToStartPosition[2][1]),

		};
		return fourCoordinates;
	}
	
	public KlotzTypeModel getKlotzType() {
		return klotzType;
	}
}
