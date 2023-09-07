
public class PieceFactory {
	public Piece PieceFactory(String piece) {
		Piece chessPiece = null;
		switch(piece) {
			case "pawn": chessPiece = new Pawn();
		}
		return chessPiece;
	}
}
