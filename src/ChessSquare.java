import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

public class ChessSquare extends Group{
	double sX; double sY;
	double sCenterX; double sCenterY;
	int sBoardX; int sBoardY;
	Color sColor;
	double sSize;
	boolean sPieceOn;
	Rectangle square;
	public ChessSquare() {
		sX=0;sY=0;
		sCenterX=0; sCenterY=0;
		sBoardX=0; sBoardY=0;
		sColor=Color.BLACK;
		sSize=0; sPieceOn=false;
		square = new Rectangle(sX,sY,sSize,sSize);
	}
	public ChessSquare(double x, double y, double centerX, double centerY, int boardX, int boardY, double size, Color color,  boolean pieceOn) {
		square = new Rectangle(x,y,size,size);
		sX=x; sY=y;
		sCenterX=centerX; sCenterY=centerY;
		sBoardX=boardX; sBoardY=boardY;
		sSize=size;
		sColor=color;
		sPieceOn=pieceOn;
	}
	public double getX() {return sX;} public double getY() {return sY;}
	public void setX(double newX) {sX=newX;} public void setY(double newY) {sY=newY;}
	public double getCenterX() {return sCenterX;} public double getCenterY() {return sCenterY;}
	public void setCenterX(double newCenterX) {sCenterX=newCenterX;} public void setCenterY(double newCenterY) {sCenterY=newCenterY;}
	public int getBoardX() {return sBoardX;} public int getBoardY() {return sBoardY;}
	public void setBoardX(int newBoardX) {sBoardX=newBoardX;} public void setBoardY(int newBoardY) {sBoardY=newBoardY;}
	public boolean getPieceOn() {return sPieceOn;} public void setPieceOn(boolean newPieceOn) {sPieceOn=newPieceOn;}
	public Rectangle getSquare() {return square;} public Color getColor() {return sColor;}
}
