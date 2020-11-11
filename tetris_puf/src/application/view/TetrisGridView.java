package application.view;

import application.model.KlotzTypeModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Lighting;
import javafx.scene.effect.Shadow;
import javafx.scene.paint.Color;


public class TetrisGridView {
	double cellSize = 13;
	double borderWidth = 1;
	Canvas canvas;
	int numberOfRows, numberOfColumns;
	
	public TetrisGridView(int rows, int columns, Canvas tetrisGridCanvas) {
		canvas = tetrisGridCanvas;
		numberOfRows = rows;
		numberOfColumns = columns;
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        drawGridLines(graphicsContext);
	}
	
	public void setKlotz(int rowIndex, int columnIndex, KlotzTypeModel klotzType) {
		Color color;
		switch (klotzType) {
			case IKlotz:
				color = Color.AQUA;
				break;
			case OKlotz:
				color = Color.FUCHSIA;
				break;
			case LKlotz:
				color = Color.CHARTREUSE;
				break;
			case ZKlotz:
				color = Color.YELLOW;
				break;
			case TKlotz:
				color = Color.DARKORANGE;
				break;
			default:
				color = Color.BLACK;
				break;
			}
		drawKlotz(rowIndex, columnIndex, color);
	}
	
	private void drawKlotz(int rowIndex, int columnIndex, Color color) {
		Lighting effect = new Lighting();
		double xStart = columnIndex * (cellSize + borderWidth) + (0.5*borderWidth);
		double yStart = rowIndex * (cellSize + borderWidth) + (0.5*borderWidth);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(color);
		gc.setEffect(effect);

		gc.fillRect(xStart, yStart, cellSize, cellSize);

	}
	
	private void drawGridLines(GraphicsContext gc) {
//		DropShadow effect = new DropShadow(BlurType.GAUSSIAN, Color.BLACK, 0.3, 0.6, 2, 2);
		gc.setLineWidth(borderWidth);
		gc.setStroke(Color.DIMGREY);
//		gc.setEffect(effect);
		double xStart = 0;
		double yStart = 0;
		for (int i=0; i<numberOfRows; i++) { 
	        gc.strokeLine(0, yStart, canvas.getWidth(), yStart);
	        yStart += cellSize + borderWidth;
		}
		for (int j=0; j<numberOfColumns; j++) {
			gc.strokeLine(xStart, 0, xStart, canvas.getHeight());
	        xStart += cellSize + borderWidth;
		}
	}
}
