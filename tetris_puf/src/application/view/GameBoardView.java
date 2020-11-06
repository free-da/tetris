package application.view;

import application.model.Square;
import application.model.TetrisShape;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.effect.InnerShadow;

public class GameBoardView {
	private int cellWidthAndHeight = 12;
    
    public void insertShapeIntoGrid(TetrisShape shape, GridPane grid, int rowOffset, int columnOffset) {
    	InnerShadow innerShadow = new InnerShadow(5, Color.BLACK);

    	for(Square square : shape.getSquares()) {
    		square.setBackground(new Background(new BackgroundFill(square.getColor(), null, null)));
    		square.setEffect(innerShadow);
    		grid.add(square, square.getPositionColumn()+columnOffset, square.getPositionRow()+rowOffset);
    	}
    }
    
    public void buildUpGridAndAddPanes(int numberOfColumns, int numberOfRows, GridPane grid) {
    	InnerShadow innerShadow = new InnerShadow(5, Color.BLACK);
    	for(int i=0; i<numberOfColumns; i++) {
    		for (int j=0; j<numberOfRows; j++) {
    			Pane pane = new Pane();
    			pane.getStyleClass().add("game-grid-cell");
    			pane.setPrefSize(cellWidthAndHeight, cellWidthAndHeight);
    			pane.setEffect(innerShadow);
    			grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

    			GridPane.setRowIndex(pane, j);
    	    	GridPane.setColumnIndex(pane, i);
    			grid.getChildren().add(pane);
    		}
    	}
    }

}