package application.controller;

import application.model.TetrisShapeModel;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputEventController {
	Scene scene;
	MovementController move;
	TetrisGridController grid;
	
	public InputEventController(Scene scene, MovementController move, TetrisGridController grid) {
		this.scene = scene;
		this.move = move;
		this.grid = grid;
		
		scene.setOnKeyPressed(event -> {
			
	        KeyCode keycode = event.getCode();
	        System.out.println(keycode);
	        if(keycode == KeyCode.LEFT) { 
	        	move.moveLeft(grid);
        	}
	        if(keycode == KeyCode.RIGHT) { 
	        	move.moveRight(grid);
	        }
	        if(keycode == KeyCode.DOWN) {
	        	move.moveDown(grid);
	        }
	        if(keycode == KeyCode.W) {
	        	move.rotateRight(grid);
	        }
	        if(keycode == KeyCode.S) {
	        	move.rotateLeft(grid);
	        }
	        move.debugCoordinates();
	    });    
	}
}
