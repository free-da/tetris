package application;

import application.controller.MainWindowController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
    public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameBoard.fxml"));
		Parent root = (Parent)loader.load();
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add("application/style.css");
		
		MainWindowController controller = (MainWindowController)loader.getController();
		controller.setSceneAndSetupListeners(scene, stage);
        
		stage.setTitle("PuF - Tetris");

        stage.setScene(scene);
        stage.show();	
    }

	public static void main(String[] args) {
		launch(args);
	}
	
}
