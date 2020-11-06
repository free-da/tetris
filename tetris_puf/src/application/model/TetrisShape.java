package application.model;

import application.model.Square;
import javafx.scene.paint.Color;

public class TetrisShape {

	private Square square1;
	private Square square2;
	private Square square3;
	private Square square4;

	public TetrisShape(String shape) {
		switch (shape) {
		case "I":
			Color color = Color.AQUA;
			square1 = new Square(0,0,color);
			square2 = new Square(0,1,color);
			square3 = new Square(0,2,color);
			square4 = new Square(0,3,color);
			break;
		case "O":
			color = Color.FUCHSIA;
			square1 = new Square(0,0,color);
			square2 = new Square(1,0,color);
			square3 = new Square(0,1,color);
			square4 = new Square(1,1,color);
			break;
		case "L":
			color = Color.CHARTREUSE;
			square1 = new Square(0,0,color);
			square2 = new Square(1,0,color);
			square3 = new Square(1,1,color);
			square4 = new Square(1,2,color);
			break;
		case "Z":
			color = Color.YELLOW;
			square1 = new Square(0,0,color);
			square2 = new Square(0,1,color);
			square3 = new Square(1,1,color);
			square4 = new Square(1,2,color);
			break;
		case "T":
			color = Color.DARKORANGE;
			square1 = new Square(0,0,color);
			square2 = new Square(0,1,color);
			square3 = new Square(0,2,color);
			square4 = new Square(1,1,color);
			break;
		}
	}
	
	public Square[] getSquares() {
		Square[] squares = new Square[] {
			square1, square2, square3, square4
		};
		return squares;
	}
}
