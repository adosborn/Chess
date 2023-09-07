import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

public abstract class Piece {
	MovementBehavior movementBehavior;
	int pPositionX; int pPositionY; boolean pAlive; Image pLook; String sTeam;
	public Piece() {
		pPositionX=0;pPositionY=0;
		pAlive=true;pLook=null;sTeam=null;
	}
	public Piece(int positionX, int positionY, boolean alive, Image look, String team) {
		pPositionX=positionX; pPositionY=positionY;
		pAlive=alive; pLook=look; sTeam=team;
	}
	public abstract void show();
	public abstract Image getImage(Piece newPiece);
	public abstract ArrayList<ChessSquare> legalMovements(Piece newPiece);

	public void performMove() {
		movementBehavior.move();
	}
	
	public void resize() {
		System.out.println("Going to resize");
	}
	public int getPositionX() {return pPositionX;} public int getPositionY() {return pPositionY;}
	public void setPositionX(int newPositionX) {pPositionX=newPositionX;} public void setPositionY(int newPositionY) {pPositionY=newPositionY;}
	public boolean getAlive() {return pAlive;} public Image getImage() {return pLook;}
	public void setAlive(boolean newAlive) {pAlive=newAlive;} public void setImage(Image newImage) {pLook=newImage;}
	public String getTeam() {return sTeam;} public void setTeam(String newTeam) {sTeam=newTeam;}
	
	public void pickup() {
		show();
		resize();
	}
}
