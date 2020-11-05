package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public class BasicFXMLController {
	
	@FXML
	private GridPane grid;

    public void initialize() {
    	buildUpGridAndAddPanels();
    }   
    
    @FXML
    private void buildUpGridAndAddPanels() {

    	for(int i=0; i<55; i++) {
    		for (int j=0; j<70; j++) {
    			Pane pane = new Pane();
    			pane.getStyleClass().add("game-grid-cell");
    			pane.setPrefSize(10, 10);
    			grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

//    			pane.getChildren().add(new Label(String.valueOf(i) + "," + String.valueOf(j)));
    			GridPane.setRowIndex(pane, j);
    	    	GridPane.setColumnIndex(pane, i);
    			grid.getChildren().add(pane);
    		}
    	}
    	grid.setGridLinesVisible(true);

    }

}
