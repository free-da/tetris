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
			
	        KeyCode keycode = event.getCode();
	        System.out.println(keycode);
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
	    });    
	}
}
