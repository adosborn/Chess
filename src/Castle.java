import java.util.ArrayList;
import javafx.scene.image.Image;

public class Castle extends Piece{
	public Castle() {
		movementBehavior = new PawnMove();
		// = "White_Pawn.png";
	}
	public void show() {
		Image image1 = new Image("White_Pawn.png");
	}
	public Image getImage(Piece castle) {
		if (castle.getTeam().equals("white")) return new Image("White_Castle.png");
		else return new Image("Black_Castle.png");
	}
	public ArrayList<ChessSquare> legalMovements(Piece castle) {
		ChessSquare squareOn = Board.findSquare(castle.getPositionX(), castle.getPositionY());
		ArrayList<ChessSquare> options = new ArrayList<ChessSquare>();
		int direction=0;
		//need to write the list of possible moves 
		return options;
	}
}
