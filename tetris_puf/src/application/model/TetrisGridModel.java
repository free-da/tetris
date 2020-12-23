package application.model;

import java.util.Arrays;

public class TetrisGridModel {

	int numberOfRows, numberOfColumns, numberOfXGridLines, numberOfYGridLines;
	KlotzTypeModel[][] gridOfKlotzes; 
	
	public TetrisGridModel(int yGridLines, int xGridLines) {
		numberOfRows = yGridLines - 2;
		numberOfColumns = xGridLines - 2;
		numberOfXGridLines = xGridLines;
		numberOfYGridLines = yGridLines;
		gridOfKlotzes = new KlotzTypeModel[numberOfRows][numberOfColumns];
		for(KlotzTypeModel[] column:gridOfKlotzes) {
			Arrays.fill(column, KlotzTypeModel.NoKlotz);	
		}
	}

	public int getNumberOfXGridLines() {
		return numberOfXGridLines;
	}
	
	public int getNumberOfYGridLines() {
		return numberOfYGridLines;
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
