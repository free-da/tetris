package application.controller;

import application.model.TetrisShapeModel;

import java.awt.Point;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import application.view.TetrisGridView;
import javafx.scene.canvas.Canvas;

public class NextGridController{
	Canvas nextCanvas;
	TetrisGridModel nextGridModel;
	TetrisGridView nextGridView;
	TetrisShapeModel nextShape;
	
	public NextGridController(Canvas canvas, TetrisGridModel nextGridModel) {
		this.nextCanvas = canvas;
		this.nextGridModel = nextGridModel;
		nextGridView = new TetrisGridView(nextGridModel.getNumberOfYGridLines(), nextGridModel.getNumberOfXGridLines(), canvas);
		fillGridWithKlotzes();
	}

	private void fillGridWithKlotzes() {
		for(int rows=0; rows<nextGridModel.getNumberOfRows(); rows++) {
			for(int columns=0; columns<nextGridModel.getNumberOfColumns(); columns++) {
				if (nextGridModel.getKlotzOfCell(rows, columns) != KlotzTypeModel.NoKlotz) {
					KlotzTypeModel klotz = nextGridModel.getKlotzOfCell(rows, columns) ;
					nextGridView.setKlotz(rows, columns, klotz);
				}
			}
		}
		if (nextShape != null) {
			Point[] coordinates = nextShape.getFourKlotzCoordinates();
			for(Point coordinate:coordinates) {
				KlotzTypeModel klotz = nextShape.getKlotzType() ;
				nextGridView.setKlotz((int)coordinate.getX(), (int)coordinate.getY(), klotz);
			}
		}
	}

	public void newTetrisShape() {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int rowIndex = nextGridModel.getNumberOfYGridLines() / 2 - 1;
		int columnIndex = nextGridModel.getNumberOfXGridLines() / 2;
		nextShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex, nextGridModel);
		fillGridWithKlotzes();
	}
}
