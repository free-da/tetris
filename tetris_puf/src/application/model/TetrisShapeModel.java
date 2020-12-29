package application.model;

import java.awt.Point;

public class TetrisShapeModel {
	private Point anchorPoint;
	private SingleKlotzModel[] threeKlotzVectorsRelativeToAnchorPoint;
	KlotzTypeModel klotzType;
	TetrisGridModel tetrisGridModel;

	public TetrisShapeModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex, TetrisGridModel tetrisGridModel) {
		this.klotzType = klotzType;
		this.tetrisGridModel = tetrisGridModel;
		switch (klotzType) {
		case IKlotz:
			anchorPoint = new Point(columnIndex, rowIndex+1);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 2),	
				new SingleKlotzModel(VectorDirectionsModel.NORTH, 1),
			});
			break;
		case OKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.WEST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHWEST, 1),	
			});
			break;
		case JKlotz:
			anchorPoint = new Point(columnIndex, rowIndex +1);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.NORTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHEAST, 1),
			});
			break;
		case LKlotz:
			anchorPoint = new Point(columnIndex, rowIndex+1);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.NORTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHWEST, 1),
			});
			break;
		case ZKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.EAST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHWEST, 1),			
			});
			break;
		case TKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.EAST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.WEST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),
			});
			break;
		case SKlotz:
			anchorPoint = new Point(columnIndex, rowIndex);
			setThreeKlotzVectorsRelativeToAnchorPoint(new SingleKlotzModel[]{
				new SingleKlotzModel(VectorDirectionsModel.WEST, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTH, 1),	
				new SingleKlotzModel(VectorDirectionsModel.SOUTHEAST, 1),
			});
			break;
		}
	}
	
	public Point getSingleKlotzCoordinate(SingleKlotzModel klotz) {
		Point coordinate = new Point();
		switch(klotz.getDirection()) {
		case NORTH:
			coordinate.move((int)getAnchorPoint().getX(), (int)getAnchorPoint().getY() - klotz.norm);
			break;
		case NORTHWEST:
			coordinate.move((int)getAnchorPoint().getX() + klotz.norm, (int)getAnchorPoint().getY() - klotz.norm);
			break;
		case WEST:
			coordinate.move((int)getAnchorPoint().getX() + klotz.norm, (int)getAnchorPoint().getY());
			break;
		case SOUTHWEST:
			coordinate.move((int)getAnchorPoint().getX() + klotz.norm, (int)getAnchorPoint().getY() + klotz.norm);
			break;
		case SOUTH:
			coordinate.move((int)getAnchorPoint().getX(), (int)getAnchorPoint().getY() + klotz.norm);
			break;
		case SOUTHEAST:
			coordinate.move((int)getAnchorPoint().getX() - klotz.norm, (int)getAnchorPoint().getY() + klotz.norm);
			break;
		case EAST:
			coordinate.move((int)getAnchorPoint().getX() - klotz.norm, (int)getAnchorPoint().getY());
			break;
		case NORTHEAST:
			coordinate.move((int)getAnchorPoint().getX() - klotz.norm, (int)getAnchorPoint().getY() - klotz.norm);
			break;
		}
		return coordinate;
	}
	
	// returns: point array of all four Klotzes of current Tetromino, starts with anchor point
	public Point[] getFourKlotzCoordinates() {
		Point[] fourCoordinates = new Point[4];
		fourCoordinates[0] = getAnchorPoint();
		int counter = 1;
		for (SingleKlotzModel klotz : getThreeKlotzVectorsRelativeToAnchorPoint()) {
			Point coordinate = getSingleKlotzCoordinate(klotz);
			fourCoordinates[counter] = coordinate;
			counter++;
		}
		return fourCoordinates;
	}
	
	public KlotzTypeModel getKlotzType() {
		return klotzType;
	}
	
	public Point getAnchorPoint() {
		return anchorPoint;
	}
	
	public void setAnchorPoint(int x, int y) {
		anchorPoint.move(x,y);
	}

	public SingleKlotzModel[] getThreeKlotzVectorsRelativeToAnchorPoint() {
		return threeKlotzVectorsRelativeToAnchorPoint;
	}

	public void setThreeKlotzVectorsRelativeToAnchorPoint(SingleKlotzModel[] threeKlotzVectorsRelativeToAnchorPoint) {
		this.threeKlotzVectorsRelativeToAnchorPoint = threeKlotzVectorsRelativeToAnchorPoint;
	}

}
