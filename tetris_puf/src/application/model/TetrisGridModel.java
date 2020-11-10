package application.model;

import java.util.Arrays;

public class TetrisGridModel {

	int numberOfRows, numberOfColumns;
	KlotzTypeModel[][] gridOfKlotzes; 
	
	public TetrisGridModel(int rows, int columns) {
		numberOfRows = rows;
		numberOfColumns = columns;
		gridOfKlotzes = new KlotzTypeModel[numberOfRows][numberOfColumns];
		for(KlotzTypeModel[] column:gridOfKlotzes) {
			Arrays.fill(column, KlotzTypeModel.NoKlotz);	
		}
	}
	
	public int getNumberOfRows() {
		return numberOfRows;
	}
	
	public int getNumberOfColumns() {
		return numberOfColumns;
	}
	
	public KlotzTypeModel getKlotzOfCell(int rowIndex, int columnIndex) {
		return gridOfKlotzes[rowIndex][columnIndex];
	}
	
	public void setKlotzOfCell(int rowIndex, int columnIndex, KlotzTypeModel klotzType) {
		gridOfKlotzes[rowIndex][columnIndex] = klotzType;
	}
}
