package application.model;

import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Square extends Pane {
	private int positionRow;
	private int positionColumn;
	
	public Square (int row, int column, Color color) {
		positionRow = row;
		positionColumn = column;
		this.setBackground(new Background(new BackgroundFill(color, null, null)));

		InnerShadow innerShadow = new InnerShadow(5, Color.BLACK);
		this.setEffect(innerShadow);
	}
	
	public int getPositionRow() {
		return positionRow;
	}
	
	public int getPositionColumn() {
		return positionColumn;
	}
}
