package application.model;

import java.util.Arrays;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TetrisGridModel {

	private int numberOfRows, numberOfColumns;
	private KlotzTypeModel[][] gridOfKlotzes; 
	
	private final StringProperty scoreCount = new SimpleStringProperty();

	public final String getScoreCount() {
		return this.scoreCount.get();
	}

	public final void setScoreCount(String value) {
		this.scoreCount.set(value);
	}

	public final StringProperty getScoreCountProperty() {
		return this.scoreCount;
	}
	
	public TetrisGridModel(int rows, int columns) {
		numberOfRows = rows;
		numberOfColumns = columns;
		gridOfKlotzes = new KlotzTypeModel[numberOfRows][numberOfColumns];
		initialiseKlotzTypeModelArray();
		setScoreCount("0");
	}
	
	public void initialiseKlotzTypeModelArray() {
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
