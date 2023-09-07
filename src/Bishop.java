import java.util.ArrayList;
import javafx.scene.image.Image;

public class Bishop extends Piece{
	public Bishop() {
		movementBehavior = new PawnMove();
		// = "White_Pawn.png";
	}
	public void show() {
		Image image1 = new Image("White_Bishup.png");
	}
	public Image getImage(Piece castle) {
		if (castle.getTeam().equals("white")) return new Image("White_Bishop.png");
		else return new Image("Black_Bishop.png");
	}
	public ArrayList<ChessSquare> legalMovements(Piece bishop) {
		ChessSquare squareOn = Board.findSquare(bishop.getPositionX(), bishop.getPositionY());
		ArrayList<ChessSquare> options = new ArrayList<ChessSquare>();
		int direction=0;
		//need to write the list of possible moves 
		return options;
	}
}
