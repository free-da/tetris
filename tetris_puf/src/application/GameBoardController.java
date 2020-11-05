package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.effect.InnerShadow;

public class GameBoardController {
	private int cellWidth = 12;
	private int cellHeight = 12;
	
	@FXML
	private GridPane playingGrid, nextUpGrid;
	
	@FXML
	private Label pointsLabel;
	
	@FXML
	private TitledPane pointsTitle, nextUpTitle;
	
	@FXML
	private Button startButton;

	
    public void initialize() {
//		Parent root = FXMLLoader.load(getClass().getResource("StartScreen.fxml"));
        Font.loadFont(getClass().getResource("digital.ttf").toExternalForm(), 16);
    	buildUpGridAndAddPanels();
    	buildUpGridForNextUpShape();
    	buildUpLabelForPoints();
    }   
    
    public void showGameboard(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("GameBoard.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("PuF - Tetris");

        stage.setScene(scene);
        stage.show();	
    }
    
    @FXML
    private void buildUpLabelForPoints() {
    }
    
    @FXML
    private void buildUpGridAndAddPanels() {
    	playingGrid.getChildren().remove(startButton);
       	buildUpGrid(18,36,playingGrid);
    }
    
    @FXML
    private void buildUpGridForNextUpShape() {
    	buildUpGrid(8,16,nextUpGrid);
    }
    
    private void buildUpGrid(int numberOfColumns, int numberOfRows, GridPane grid) {
    	
    	InnerShadow innerShadow = new InnerShadow(5, Color.BLACK);
    	for(int i=0; i<numberOfColumns; i++) {
    		for (int j=0; j<numberOfRows; j++) {
    			Pane pane = new Pane();
    			pane.getStyleClass().add("game-grid-cell");
    			pane.setPrefSize(cellWidth, cellHeight);
    			pane.setEffect(innerShadow);
    			grid.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

    			GridPane.setRowIndex(pane, j);
    	    	GridPane.setColumnIndex(pane, i);
    			grid.getChildren().add(pane);
    		}
    	}
    }

}
