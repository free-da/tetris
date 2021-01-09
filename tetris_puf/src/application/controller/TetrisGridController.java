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
		refreshGrid();
		return newShape;
	}
	

	public TetrisShapeModel newTetrisShape(int rowIndex, KlotzTypeModel klotzType) {
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2;
		newShape = new TetrisShapeModel(klotzType, rowIndex, columnIndex, tetrisGridModel);
		newShape.addPropertyChangeListener(this);
		refreshGrid();
		return newShape;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		System.out.println("Name      = " + event.getPropertyName());
        System.out.println("Old Value = " + event.getOldValue());
        System.out.println("New Value = " + event.getNewValue());
        refreshGrid();
		
	}
	
	public int checkFirstFullRows() {
		boolean rowIsFull = false;
		for(int i = 0; i < tetrisGridModel.getNumberOfRows(); i++) {
			for (int j = 0; j < tetrisGridModel.getNumberOfColumns(); j++) {
				if (tetrisGridModel.getKlotzOfCell(i, j) == KlotzTypeModel.NoKlotz) {
					rowIsFull = false;
					break;
				} else {
					rowIsFull = true;
				}
			}
			if (rowIsFull) {
				return i;
			}
		}
		return -1;
	}
	
	public void clearFullRow(int rowIndex) {
		//flush row above first full row
		for (int y = rowIndex; y > 0; y--) {
			for (int x = 0; x < tetrisGridModel.getNumberOfColumns(); x++) {
				tetrisGridModel.setKlotzOfCell(y, x, tetrisGridModel.getKlotzOfCell(y-1, x));
			}
		}
		//fill 0th row with empty NoKotz-Types
		for (int x = 0; x < tetrisGridModel.getNumberOfColumns(); x++) {
			tetrisGridModel.setKlotzOfCell(0, x, KlotzTypeModel.NoKlotz);
		}
	}
	
	public void incrementScoreCount(int increment) {
		int newScore = Integer.parseInt(tetrisGridModel.getScoreCount());
		newScore += increment;
		tetrisGridModel.setScoreCount(Integer.toString(newScore));
	}
	
}
