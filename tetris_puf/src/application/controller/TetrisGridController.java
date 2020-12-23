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
		for(int columns=0; columns<=tetrisGridModel.getNumberOfColumns(); columns++) {
			for(int rows=0; rows<tetrisGridModel.getNumberOfRows(); rows++) {
				tetrisGridView.setKlotz(rows, columns, KlotzTypeModel.NoKlotz);
			}
		}
		fillGridWithKlotzes();
	}

	public void fillGridWithKlotzes() {
		for(int columns=0; columns<tetrisGridModel.getNumberOfColumns(); columns++) {
			for(int rows=0; rows<tetrisGridModel.getNumberOfRows(); rows++) {
				if (tetrisGridModel.getKlotzOfCell(rows, columns) != KlotzTypeModel.NoKlotz) {
					KlotzTypeModel klotz = tetrisGridModel.getKlotzOfCell(rows, columns) ;
					tetrisGridView.setKlotz(rows, columns, klotz);
				}
			}
		}
		if (newShape != null) {
			Point[] coordinates = newShape.getFourKlotzCoordinates();
			for(Point coordinate:coordinates) {
				KlotzTypeModel klotz = newShape.getKlotzType() ;
				tetrisGridView.setKlotz((int)coordinate.getY(), (int)coordinate.getX(), klotz);
			}
		}
	}

	public TetrisShapeModel newTetrisShape() {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int rowIndex = 0;
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2;
		newShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex, tetrisGridModel);
		fillGridWithKlotzes();
		return newShape;
	}
}
