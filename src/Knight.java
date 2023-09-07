import java.util.ArrayList;
import javafx.scene.image.Image;

public class Knight extends Piece{
	public Knight() {
		movementBehavior = new PawnMove();
	}
	public void show() {
		Image image1 = new Image("White_Pawn.png");
	}
	public Image getImage(Piece knight) {
		if (knight.getTeam().equals("white")) return new Image("White_Knight.png");
		else return new Image("White_Knight.png");
	}

	public ArrayList<ChessSquare> legalMovements(Piece knight) {
		ChessSquare squareOn = Board.findSquare(knight.getPositionX(), knight.getPositionY());
		ArrayList<ChessSquare> options = new ArrayList<ChessSquare>();
		int direction=0;
		//need to write the list of possible moves 
		return options;
	}

}
