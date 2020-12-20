package application.view;

import application.model.KlotzTypeModel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;


public class TetrisGridView {
	double cellSize = 12;
	double borderWidth = 2;
	Canvas canvas;
	int numberOfRows, numberOfColumns;
	
	public TetrisGridView(int rows, int columns, Canvas tetrisGridCanvas) {
		canvas = tetrisGridCanvas;
		numberOfRows = rows;
		numberOfColumns = columns;
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        drawGridLines(graphicsContext);
	}

	public void setKlotz(int columnIndex, int rowIndex, KlotzTypeModel klotzType) {
		Color color;
		switch (klotzType) {
			case IKlotz:
				color = Color.DODGERBLUE;
				break;
			case OKlotz:
				color = Color.VIOLET;
				break;
			case LKlotz:
				color = Color.CHARTREUSE;
				break;
			case JKlotz:
				color= Color.ORANGE;
				break;
			case ZKlotz:
				color = Color.YELLOW;
				break;
			case TKlotz:
				color = Color.TURQUOISE;
				break;
			case SKlotz:
				color = Color.SALMON;
				break;
			default:
				color = Color.DIMGREY;
				break;
			}
		if (klotzType != KlotzTypeModel.NoKlotz) {
			drawKlotz(rowIndex, columnIndex, color);	
		} else {
			drawNoKlotz(rowIndex, columnIndex);
		}
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
	
	private void drawNoKlotz(int rowIndex, int columnIndex) {
		double xStart = columnIndex * (cellSize + borderWidth) + (0.5*borderWidth);
		double yStart = rowIndex * (cellSize + borderWidth) + (0.5*borderWidth);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.rgb(48,48,48));
		gc.setEffect(null);

		gc.fillRect(xStart, yStart, cellSize, cellSize);
	}
	
	private void drawGridLines(GraphicsContext gc) {
		gc.setLineWidth(borderWidth);
		gc.setStroke(Color.DIMGREY);
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

//	public void clearCanvas() {
//	}
}
