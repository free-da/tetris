package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;




public class Main extends Application {

	@Override
    public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("BasicLayout.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("PuF - Tetris");

        stage.setScene(scene);
        stage.show();	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
