package application.model;

public class TetrisShapeModel {
	int[] startPositionCoordinate;
	int[][] threeOtherKlotzCoordinates;
	KlotzTypeModel klotzType;

	public TetrisShapeModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex) {
		this.klotzType = klotzType;
		switch (klotzType) {
		case IKlotz:
			startPositionCoordinate = new int[] {rowIndex, columnIndex};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex+1, columnIndex},
				{rowIndex+2, columnIndex},
				{rowIndex+3, columnIndex}
			};
			break;
		case OKlotz:
			startPositionCoordinate = new int[] {rowIndex, columnIndex};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex, columnIndex-1},
				{rowIndex+1, columnIndex},
				{rowIndex+1, columnIndex-1}
			};
			break;
		case JKlotz:
			startPositionCoordinate = new int[] {rowIndex+1, columnIndex-1};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex+1, columnIndex},
				{rowIndex+1, columnIndex+1},
				{rowIndex, columnIndex-1}
			};
			break;
		case LKlotz:
			startPositionCoordinate = new int[] {rowIndex, columnIndex-1};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex, columnIndex},
				{rowIndex, columnIndex+1},
				{rowIndex+1, columnIndex-1}
			};
			break;
		case ZKlotz:
			startPositionCoordinate = new int[] {rowIndex, columnIndex-1};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex, columnIndex},
				{rowIndex+1, columnIndex},
				{rowIndex+1, columnIndex+1}
			};
			break;
		case TKlotz:
			startPositionCoordinate = new int[] {rowIndex, columnIndex-1};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex, columnIndex},
				{rowIndex+1, columnIndex},
				{rowIndex, columnIndex+1}
			};
			break;
		case SKlotz:
			startPositionCoordinate = new int[] {rowIndex+1, columnIndex-1};
			threeOtherKlotzCoordinates = new int[][]{
				{rowIndex+1, columnIndex},
				{rowIndex, columnIndex},
				{rowIndex, columnIndex+1}
			};
			break;
		}
	}
	
	public int[][] getFourKlotzCoordinates() {
		int[][] fourCoordinates = new int[][] {
			{startPositionCoordinate[0], startPositionCoordinate[1]},
			{threeOtherKlotzCoordinates[0][0], threeOtherKlotzCoordinates[0][1]},
			{threeOtherKlotzCoordinates[1][0], threeOtherKlotzCoordinates[1][1]},
			{threeOtherKlotzCoordinates[2][0], threeOtherKlotzCoordinates[2][1]}
		};
		return fourCoordinates;
	}
	
	public KlotzTypeModel getKlotzType() {
		return klotzType;
	}
}
