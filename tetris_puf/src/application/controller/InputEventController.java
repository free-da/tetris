package application.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputEventController {
	Scene scene;
	MovementController move;
	TetrisGridController grid;
	
	public InputEventController(Scene scene, MovementController move) {
		this.scene = scene;
		this.move = move;
				
		scene.setOnKeyPressed(event -> {
			
	        KeyCode keycode = event.getCode();
	        System.out.println(keycode);
	        if(keycode == KeyCode.LEFT) { 
	        	move.moveLeft();
        	}
	        if(keycode == KeyCode.RIGHT) { 
	        	move.moveRight();
	        }
	        if(keycode == KeyCode.DOWN) {
	        	move.moveDown();
	        }
	        if(keycode == KeyCode.W) {
	        	move.rotateRight();
	        }
	        if(keycode == KeyCode.S) {
	        	move.rotateLeft();
	        }
	        move.debugCoordinates();
	    });    
	}
}
