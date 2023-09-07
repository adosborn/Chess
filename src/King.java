import java.util.ArrayList;
import javafx.scene.image.Image;

public class King extends Piece{
	public King() {
		movementBehavior = new PawnMove();
		// = "White_Pawn.png";
	}
	public void show() {
		Image image1 = new Image("White_King.png");
	}
	public Image getImage(Piece king) {
		if (king.getTeam().equals("white")) return new Image("White_King.png");
		else return new Image("Black_King.png");
	}
	public ArrayList<ChessSquare> legalMovements(Piece king) {
		ChessSquare squareOn = Board.findSquare(king.getPositionX(), king.getPositionY());
		ArrayList<ChessSquare> options = new ArrayList<ChessSquare>();
		int direction=0;
		//need to write the list of possible moves 
		return options;
	}
}
