package application.controller;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class InputEventController {
	Scene scene;
	MovementController movement;
	
	public InputEventController(Scene scene, MovementController movement) {
		this.scene = scene;
		this.movement = movement;
				
		scene.setOnKeyPressed(event -> {
			
	        KeyCode keycode = event.getCode();
	        if(keycode == KeyCode.LEFT) { 
	        	movement.moveLeft();
        	}
	        if(keycode == KeyCode.RIGHT) { 
	        	movement.moveRight();
	        }
	        if(keycode == KeyCode.DOWN) {
	        	movement.moveDown();
	        }
	        if(keycode == KeyCode.W || keycode == KeyCode.UP) {
	        	movement.rotateRight();
	        }
	        if(keycode == KeyCode.S) {
	        	movement.rotateLeft();
	        }
	    });
	}
}
