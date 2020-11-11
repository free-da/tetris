package application.controller;

import application.model.TetrisShapeModel;
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

	private void fillGridWithKlotzes() {
		for(int i=0; i<tetrisGridModel.getNumberOfRows(); i++) {
			for(int j=0; j<tetrisGridModel.getNumberOfColumns(); j++) {
				if (tetrisGridModel.getKlotzOfCell(i, j) != KlotzTypeModel.NoKlotz) {
					KlotzTypeModel klotz = tetrisGridModel.getKlotzOfCell(i, j) ;
					tetrisGridView.setKlotz(i, j, klotz);
				}
			}
		}
		if (newShape != null) {
			int[][] coordinates = newShape.getFourKlotzCoordinates();
			for(int[] coordinate:coordinates) {
				KlotzTypeModel klotz = newShape.getKlotzType() ;
				tetrisGridView.setKlotz(coordinate[0], coordinate[1], klotz);
			}
		}
	}

	public void newTetrisShape() {
		KlotzTypeModel randomKlotzType = KlotzTypeModel.randomKlotzType();
		int rowIndex = 0;
		int columnIndex = tetrisGridModel.getNumberOfColumns() / 2 -1;
		newShape = new TetrisShapeModel(randomKlotzType, rowIndex, columnIndex);
		fillGridWithKlotzes();
	}
}
