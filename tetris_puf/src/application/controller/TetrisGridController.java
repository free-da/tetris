package application.controller;

import application.model.KlotzTypeModel;
import application.model.TetrisGridModel;
import application.view.TetrisGridView;
import javafx.scene.canvas.Canvas;

public class TetrisGridController {
	Canvas gameboardCanvas;
	TetrisGridModel tetrisGridModel;
	TetrisGridView tetrisGridView;
	
	public TetrisGridController(Canvas gameboardCanvas, TetrisGridModel tetrisGridModel) {
		this.gameboardCanvas = gameboardCanvas;
		this.tetrisGridModel = tetrisGridModel;
		tetrisGridView = new TetrisGridView(tetrisGridModel.getNumberOfRows(), tetrisGridModel.getNumberOfColumns(), gameboardCanvas);
		fillGridWithKlotzes();
	}

	private void fillGridWithKlotzes() {
		for(int i=0; i<tetrisGridModel.getNumberOfRows(); i++) {
			for(int j=0; j<tetrisGridModel.getNumberOfColumns(); j++) {
				if (tetrisGridModel.getKlotzOfCell(i, j) != KlotzTypeModel.NoKlotz) {
					KlotzTypeModel klotz = tetrisGridModel.getKlotzOfCell(i, j) ;
					tetrisGridView.setKlotz(i, j, klotz);
				}
			}
		}
	}

	public void newTetrisShape() {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int rowIndex = 0;
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2 -1;
		setShapeIntoModel(randomKlotzType, rowIndex, columnIndex);
		fillGridWithKlotzes();
	}
	
	public void setShapeIntoModel(KlotzTypeModel klotzType, int rowIndex, int columnIndex) {
		switch (klotzType) {
		case IKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+2, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+3, columnIndex, klotzType);
			break;
		case OKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex-1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex-1, klotzType);
			break;
		case JKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex-1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex+1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex-1, klotzType);
			break;
		case LKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex-1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex+1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex-1, klotzType);
			break;
		case ZKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex-1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex+1, klotzType);			
			break;
		case TKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex-1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex+1, klotzType);			
			break;
		case SKlotz:
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex-1, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex+1, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex, klotzType);
			tetrisGridModel.setKlotzOfCell(rowIndex, columnIndex+1, klotzType);			
			break;
		}
	}
}
