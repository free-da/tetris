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
		for(int i=0; i<nextGridModel.getNumberOfRows(); i++) {
			for(int j=0; j<nextGridModel.getNumberOfColumns(); j++) {
				if (nextGridModel.getKlotzOfCell(i, j) != KlotzTypeModel.NoKlotz) {
					KlotzTypeModel klotz = nextGridModel.getKlotzOfCell(i, j) ;
					nextGridView.setKlotz(i, j, klotz);
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

//	public void newTetrisShape() {
//		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
//		int rowIndex = nextGridModel.getNumberOfRows() / 2 - 1;
//		int columnIndex = nextGridModel.getNumberOfColumns() / 2;
//		nextShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex);
//		fillGridWithKlotzes();
//	}
}
