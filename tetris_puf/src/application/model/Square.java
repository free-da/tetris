package application.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Square extends Pane {
	private int positionRow;
	private int positionColumn;
	private Color color;
	
	public Square (int row, int column, Color color) {
		positionRow = row;
		positionColumn = column;
		this.color = color;
	}
	
	public int getPositionRow() {
		return positionRow;
	}
	
	public int getPositionColumn() {
		return positionColumn;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return color;
	}
}
