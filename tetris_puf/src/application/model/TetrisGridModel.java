package application.model;

import java.util.Arrays;

public class TetrisGridModel {

	int numberOfRows, numberOfColumns;
	KlotzTypeModel[][] gridOfKlotzes; 
	
	public TetrisGridModel(int yGridLines, int xGridLines) {
		numberOfRows = yGridLines;
		numberOfColumns = xGridLines;
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
