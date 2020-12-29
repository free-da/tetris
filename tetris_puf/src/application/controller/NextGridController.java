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
		nextGridView = new TetrisGridView(nextGridModel.getNumberOfRows(), nextGridModel.getNumberOfColumns(), canvas);
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
				nextGridView.setKlotz((int)coordinate.getY(), (int)coordinate.getX(), klotz);
			}
		}
	}
	
	public void refreshGrid() {
		for(int columns=0; columns<=nextGridModel.getNumberOfColumns(); columns++) {
			for(int rows=0; rows<nextGridModel.getNumberOfRows(); rows++) {
				nextGridView.setKlotz(rows, columns, KlotzTypeModel.NoKlotz);
			}
		}
		fillGridWithKlotzes();
	}

	public TetrisShapeModel newTetrisShape() {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int rowIndex = nextGridModel.getNumberOfRows() / 2 - 1;
		int columnIndex = nextGridModel.getNumberOfColumns() / 2;
		nextShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex, nextGridModel);
		refreshGrid();
		return nextShape;
	}
}
