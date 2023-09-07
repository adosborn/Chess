import java.util.ArrayList;
import java.util.Collection;
import javafx.scene.image.Image;

public class Pawn extends Piece{
	public Pawn() {
		movementBehavior = new PawnMove();
		// = "White_Pawn.png";
	}
	public void show() {
		Image image1 = new Image("White_Pawn.png");
	}
	public Image getImage(Piece pawn) {
		if (pawn.getTeam().equals("white")) return new Image("White_Pawn.png");
		else return new Image("Black_Pawn.png");
	}
	public ArrayList<ChessSquare> legalMovements(Piece pawn) {
		//need to add diagonal moved once I create a list of all pieces in play to check if one is an enemy
		ChessSquare squareOn = Board.findSquare(pawn.getPositionX(), pawn.getPositionY());
		ArrayList<ChessSquare> options = new ArrayList<ChessSquare>();
		int direction=0;
		if (pawn.getTeam().toLowerCase().equals("white")) direction = -1; else direction = 1;
		if (squareOn.getBoardY()<Board.squares&&!Board.gridFindSquare(squareOn.getBoardX(),squareOn.getBoardY()+direction).getPieceOn()) {
			options.add(Board.gridFindSquare(squareOn.getBoardX(),squareOn.getBoardY()+direction));
		}
		if (pawn.getTeam().toLowerCase().equals("white")&&squareOn.getBoardY()==(Board.squares-1)&&!Board.gridFindSquare(squareOn.getBoardX(),squareOn.getBoardY()+(2*direction)).getPieceOn()) {
			options.add(Board.gridFindSquare(squareOn.getBoardX(),squareOn.getBoardY()+(2*direction)));
		}
		else if (pawn.getTeam().toLowerCase().equals("black")&&squareOn.getBoardY()==2&&!Board.gridFindSquare(squareOn.getBoardX(),squareOn.getBoardY()+(2*direction)).getPieceOn()) {
			options.add(Board.gridFindSquare(squareOn.getBoardX(),squareOn.getBoardY()+(2*direction)));
		}
		return options;
	}
}
	