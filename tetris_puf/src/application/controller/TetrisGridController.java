package application.controller;

import application.model.TetrisShapeModel;

import java.awt.Point;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import application.view.TetrisGridView;
import javafx.scene.canvas.Canvas;

public class TetrisGridController {
	Canvas gameboardCanvas;
	TetrisGridModel tetrisGridModel;
	TetrisGridView tetrisGridView;
	TetrisShapeModel newShape;
	
	public TetrisGridController(Canvas gameboardCanvas, TetrisGridModel tetrisGridModel) {
		this.gameboardCanvas = gameboardCanvas;
		this.tetrisGridModel = tetrisGridModel;
		tetrisGridView = new TetrisGridView(tetrisGridModel.getNumberOfRows(), tetrisGridModel.getNumberOfColumns(), gameboardCanvas);
		fillGridWithKlotzes();
	}
	
	public void refreshGrid() {
		for(int i=0; i<tetrisGridModel.getNumberOfRows(); i++) {
			for(int j=0; j<tetrisGridModel.getNumberOfColumns(); j++) {
				tetrisGridView.setKlotz(i, j, KlotzTypeModel.NoKlotz);
			}
		}
		fillGridWithKlotzes();
	}

	public void fillGridWithKlotzes() {
		for(int i=0; i<tetrisGridModel.getNumberOfRows(); i++) {
			for(int j=0; j<tetrisGridModel.getNumberOfColumns(); j++) {
				if (tetrisGridModel.getKlotzOfCell(i, j) != KlotzTypeModel.NoKlotz) {
					KlotzTypeModel klotz = tetrisGridModel.getKlotzOfCell(i, j) ;
					tetrisGridView.setKlotz(i, j, klotz);
				}
			}
		}
		if (newShape != null) {
			Point[] coordinates = newShape.getFourKlotzCoordinates();
			for(Point coordinate:coordinates) {
				KlotzTypeModel klotz = newShape.getKlotzType() ;
				tetrisGridView.setKlotz((int)coordinate.getX(), (int)coordinate.getY(), klotz);
			}
		}
	}

	public TetrisShapeModel newTetrisShape() {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int rowIndex = 0;
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2 -1;
		newShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex);
		fillGridWithKlotzes();
		return newShape;
	}
}
