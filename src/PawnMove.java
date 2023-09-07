import java.util.ArrayList;

public class PawnMove implements MovementBehavior{
	ArrayList<ChessSquare> legalMoves = new ArrayList<ChessSquare>();
	public void move() {
		System.out.println("forward 1 unless first turn, than can forward 2");
		
	}
}
