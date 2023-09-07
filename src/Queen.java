import java.util.ArrayList;
import javafx.scene.image.Image;

public class Queen extends Piece{
	public Queen() {
		movementBehavior = new PawnMove();
		// = "White_Pawn.png";
	}
	public void show() {
		Image image1 = new Image("White_Queen.png");
	}
	public Image getImage(Piece queen) {
		if (queen.getTeam().equals("white")) return new Image("White_Queen.png");
		else return new Image("Black_Queen.png");
	}
	public ArrayList<ChessSquare> legalMovements(Piece queen) {
		ChessSquare squareOn = Board.findSquare(queen.getPositionX(), queen.getPositionY());
		ArrayList<ChessSquare> options = new ArrayList<ChessSquare>();
		int direction=0;
		//need to write the list of possible moves 
		return options;
	}
}
