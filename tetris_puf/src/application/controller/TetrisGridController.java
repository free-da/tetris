package application.controller;

import application.model.TetrisShapeModel;

import java.awt.Point;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import application.view.TetrisGridView;
import javafx.scene.canvas.Canvas;

public class TetrisGridController implements PropertyChangeListener {
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
        System.out.println("I listen");

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

	public TetrisShapeModel newTetrisShape(int rowIndex) {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2;
		newShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex, tetrisGridModel);
		newShape.addPropertyChangeListener(this);
//		refreshGrid();
		return newShape;
	}
	

	public TetrisShapeModel newTetrisShape(int rowIndex, KlotzTypeModel klotzType) {
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2;
		newShape = new TetrisShapeModel(klotzType, rowIndex, columnIndex, tetrisGridModel);
		newShape.addPropertyChangeListener(this);
//		refreshGrid();
		return newShape;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Name      = " + event.getPropertyName());
        System.out.println("Old Value = " + event.getOldValue());
        System.out.println("New Value = " + event.getNewValue());
        refreshGrid();
		
	}
	
	
}
