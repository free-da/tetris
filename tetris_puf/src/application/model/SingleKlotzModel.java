package application.model;

public class SingleKlotzModel {
	private VectorDirectionsModel direction;
	int norm;
	
	public SingleKlotzModel(VectorDirectionsModel direction, int norm) {
		this.setDirection(direction);
		this.norm = norm;
	}

	public VectorDirectionsModel getDirection() {
		return direction;
	}

	public void setDirection(VectorDirectionsModel direction) {
		this.direction = direction;
	}

}
