package application.controller;

import application.model.TetrisShapeModel;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputEventController {
	Scene scene;
	TetrisShapeModel shape;
	TetrisGridController grid;
	
	public InputEventController(Scene scene, TetrisShapeModel shape, TetrisGridController grid) {
		this.scene = scene;
		this.shape = shape;
		this.grid = grid;
		
		scene.setOnKeyPressed(event -> {
			
//	        double change = 10.0;
//	        //Add shift modifier to simulate "Running Speed"
//	        if(event.isShiftDown()) { change = 50.0; }
//	        //What key did the user press?
	        KeyCode keycode = event.getCode();
	        System.out.println(keycode);
//	        //Step 2c: Add Zoom controls
	        if(keycode == KeyCode.LEFT) { 
	        	shape.moveLeft(grid);
        	}
	        if(keycode == KeyCode.RIGHT) { 
	        	shape.moveRight(grid);
	        }
	        if(keycode == KeyCode.DOWN) {
	        	shape.moveDown(grid);
	        }
	        if(keycode == KeyCode.UP) {
	        	shape.moveUp(grid);
	        }
//	        //Step 2d:  Add Strafe controls
//	        if(keycode == KeyCode.A) { camera.setTranslateX(camera.getTranslateX() - change); }
//	        if(keycode == KeyCode.D) { camera.setTranslateX(camera.getTranslateX() + change); }
	    });    
	}
}
