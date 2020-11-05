package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class BasicFXMLController {
	private int cellWidth = 12;
	private int cellHeight = 12;
	
	@FXML
	private GridPane playingGrid, nextUpGrid;
	
	@FXML
	private Label pointsLabel;
	
	@FXML
	private TitledPane pointsTitle, nextUpTitle;

    public void initialize() {
    	buildUpGridAndAddPanels();
    	buildUpGridForNextUpShape();
    	buildUpLabelForPoints();
    }   
    
    @FXML
    private void buildUpLabelForPoints() {
    	pointsTitle.getStyleClass().add("nextUpBackground");
    	pointsLabel.getStyleClass().add("pointsBackground");
    }
    
    @FXML
    private void buildUpGridAndAddPanels() {
    	
    	nextUpTitle.getStyleClass().add("nextUpBackground");
    	buildUpGrid(50,60,playingGrid);
    }
    
    @FXML
    private void buildUpGridForNextUpShape() {
    	buildUpGrid(5,5,nextUpGrid);
    }
    
    private void buildUpGrid(int numberOfColumns, int numberOfRows, GridPane grid) {
    
    	for(int i=0; i<numberOfColumns; i++) {
    		for (int j=0; j<numberOfRows; j++) {
    			Pane pane = new Pane();
    			pane.getStyleClass().add("game-grid-cell");
    			pane.setPrefSize(cellWidth, cellHeight);
    			grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

    			GridPane.setRowIndex(pane, j);
    	    	GridPane.setColumnIndex(pane, i);
    			grid.getChildren().add(pane);
    		}
    	}
    }

}
