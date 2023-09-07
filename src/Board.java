import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import java.util.Scanner;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.scene.*;
import javafx.scene.image.*;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Random;
import static javafx.application.Application.launch; 
import javafx.scene.input.MouseEvent; 
import javafx.scene.input.DragEvent; 

public class Board extends Application {
	static int squares = 8;
	static int boardSize = 480;
	static int turn = 0;
	static String playerTurn = "white";
	static boolean dragging = false;
	Piece beingDragged = null;
	Stage window;
	Scene scene1;
	Image pawnLookWhite = new Image("White_Pawn.png");
	Image pawnLookBlack = new Image("Black_Pawn.png");
	Color background = Color.BLACK;
	Image currentImage = new Image("White_Queen.png");
	Rectangle currentBox = new Rectangle (0,0,(int)(boardSize/squares),(int)(boardSize/squares));
	static ArrayList<ChessSquare> chessSquares = new ArrayList<ChessSquare>();
	static ArrayList<Piece> pieces = new ArrayList<Piece>();
	public static void main(String[] args) {
		launch(args);
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		//window.setHeight((squares+2)*(boardSize/squares));
		Group layout1 = new Group();
		drawBoard(layout1);
		fillPieces();
		for (int i=0; i<pieces.size(); i++) {
			layout1.getChildren().add(addToBoard(layout1, i));
		}
		scene1 = new Scene(layout1,boardSize,(squares+2)*(boardSize/squares),background);
		EventHandler<MouseEvent> downEventHandler = new EventHandler<MouseEvent>() { 
		   @Override 
		   public void handle(MouseEvent e) { 
		      System.out.println("Pressed on (" + e.getX() + ", " + e.getY() + ")"); 
		      if (pieceOnSquare((int)e.getX(), (int)e.getY())!=null) {
		    	  if (playerTurn.equals(pieceOnSquare((int)e.getX(), (int)e.getY()).getTeam())){
		    		  beingDragged = pieceOnSquare((int)e.getX(), (int)e.getY());
			    	  System.out.println("correct team - pass");
			    	  dragging = true;
			    	  if (playerTurn.equals("white")) {playerTurn = "black";}
			    	  else playerTurn = "white";
			      }
		      }
		   } 
		};
		EventHandler<MouseEvent> upEventHandler = new EventHandler<MouseEvent>() { 
			@Override 
			public void handle(MouseEvent e) { 
				if (dragging) {
					System.out.println("(" + beingDragged.getPositionX() + ", " + beingDragged.getPositionY() + ")");
					beingDragged.setPositionX((int)findSquare((int)e.getX(),(int)e.getY()).getBoardX());
					beingDragged.setPositionY((int)findSquare((int)e.getX(),(int)e.getY()).getBoardY());
					System.out.println("(" + beingDragged.getPositionX() + ", " + beingDragged.getPositionY() + ")");
					dragging = false;
					beingDragged = null;
				}
				System.out.println("Released on (" + e.getX() + ", " + e.getY() + ")"); 
				dragging = false;
			} 
		};
		scene1.addEventFilter(MouseEvent.MOUSE_PRESSED, downEventHandler);
		scene1.addEventFilter(MouseEvent.MOUSE_RELEASED, upEventHandler);
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				layout1.getChildren().clear();
				drawBoard(layout1);
				for (int i=0; i<pieces.size(); i++) {
					layout1.getChildren().add(addToBoard(layout1, i));
				}
			}
		};
		window.setScene(scene1);
		window.setTitle("title");
		window.show();
		timer.start();
	}
	public static int getTurn() {return turn;}
	public void drawBoard(Group layout) {
		Color sColor = Color.WHITE;
		int num = 0;
		for (int y = 0; y < squares; y++) {
			if (squares%2==0)num++;
			for (int x = 0; x < squares; x++) {
				if (num%2==1)sColor=(Color.WHITE);
				else sColor=(Color.GRAY);
				num++;
				newChessSquare(x*(boardSize/squares), (y+1)*(boardSize/squares), x*(boardSize/squares)+(1/2)*(boardSize/squares),
						      (y+1)*(boardSize/squares)+(1/2)*(boardSize/squares), x, y, boardSize/squares,sColor, false);
				layout.getChildren().add(chessSquares.get(chessSquares.size()-1).getSquare());
			}
		}
	}
	public void newChessSquare(double x, double y, double centerX, double centerY, int boardX, int boardY, double size, Color color, boolean pieceOn) {
		chessSquares.add(new ChessSquare(x, y, centerX, centerY, boardX, boardY, size, color, pieceOn));
		chessSquares.get(chessSquares.size()-1).getSquare().setFill(color);
	}
	public ImageView addToBoard(Group layout, int k) {
		currentImage=(pieces.get(k).getImage());
		ImageView view = new ImageView(currentImage);
		view.setLayoutX(gridFindSquare(pieces.get(k).getPositionX(), pieces.get(k).getPositionY()).getCenterX());
		view.setLayoutY(gridFindSquare(pieces.get(k).getPositionX(), pieces.get(k).getPositionY()).getCenterY());
		return view;
	}
	public void addPieceTo(Piece p, int x, int y) {
		
	}
	public void fillPieces() {
		addPawns();
		addCastles();
		addKnights();
		addBishops();
		addQueens();
		addKings();
		//for some reason the Black_Night.png isn't working...
	}
	public void addPawns() {
		for (int k=0; k<squares; k++) {
			Pawn p = new Pawn();
			p.setPositionX(k); p.setPositionY(squares-2); p.setAlive(true); 
			p.setTeam("white"); p.setImage(p.getImage(p));
			pieces.add(p);
		}
		for (int k=0; k<squares; k++) {
			Pawn p = new Pawn();
			p.setPositionX(k); p.setPositionY(1); p.setAlive(true); 
			p.setTeam("black"); p.setImage(p.getImage(p));
			pieces.add(p);
		}
	}
	public void addCastles() {
		Castle c1 = new Castle();
		c1.setPositionX(0); c1.setPositionY(squares-1); c1.setAlive(true); c1.setTeam("white"); c1.setImage(c1.getImage(c1));
		pieces.add(c1); Castle c2 = new Castle();
		c2.setPositionX(squares-1); c2.setPositionY(squares-1); c2.setAlive(true); c2.setTeam("white"); c2.setImage(c2.getImage(c2));
		pieces.add(c2); Castle c3 = new Castle();
		c3.setPositionX(0); c3.setPositionY(0); c3.setAlive(true); c3.setTeam("black"); c3.setImage(c3.getImage(c3));
		pieces.add(c3); Castle c4 = new Castle();
		c4.setPositionX(squares-1); c4.setPositionY(0); c4.setAlive(true); c4.setTeam("black"); c4.setImage(c4.getImage(c4));
		pieces.add(c4);
	}
	public void addKnights() {
		Knight k1 = new Knight();
		k1.setPositionX(1); k1.setPositionY(squares-1); k1.setAlive(true); k1.setTeam("white"); k1.setImage(k1.getImage(k1));
		pieces.add(k1); Knight k2 = new Knight();
		k2.setPositionX(squares-2); k2.setPositionY(squares-1); k2.setAlive(true); k2.setTeam("white"); k2.setImage(k2.getImage(k2));
		pieces.add(k2); Knight k3 = new Knight();
		k3.setPositionX(1); k3.setPositionY(0); k3.setAlive(true); k3.setTeam("black"); k3.setImage(k3.getImage(k3));
		pieces.add(k3); Knight k4 = new Knight();
		k4.setPositionX(squares-2); k4.setPositionY(0); k4.setAlive(true); k4.setTeam("black"); k4.setImage(k4.getImage(k4));
		pieces.add(k4);
	}
	public void addBishops() {
		Bishop b1 = new Bishop();
		b1.setPositionX(2); b1.setPositionY(squares-1); b1.setAlive(true); b1.setTeam("white"); b1.setImage(b1.getImage(b1));
		pieces.add(b1); Bishop b2 = new Bishop();
		b2.setPositionX(squares-3); b2.setPositionY(squares-1); b2.setAlive(true); b2.setTeam("white"); b2.setImage(b2.getImage(b2));
		pieces.add(b2); Bishop b3 = new Bishop();
		b3.setPositionX(2); b3.setPositionY(0); b3.setAlive(true); b3.setTeam("black"); b3.setImage(b3.getImage(b3));
		pieces.add(b3); Bishop b4 = new Bishop();
		b4.setPositionX(squares-3); b4.setPositionY(0); b4.setAlive(true); b4.setTeam("black"); b4.setImage(b4.getImage(b4));
		pieces.add(b4);
	}
	public void addQueens() {
		Queen q1 = new Queen();
		q1.setPositionX(3); q1.setPositionY(squares-1); q1.setAlive(true); q1.setTeam("white"); q1.setImage(q1.getImage(q1)); 
		pieces.add(q1); Queen q2 = new Queen();
		q2.setPositionX(3); q2.setPositionY(0); q2.setAlive(true); q2.setTeam("black"); q2.setImage(q2.getImage(q2)); 
		pieces.add(q2); 
	}
	public void addKings() {
		King k1 = new King();
		k1.setPositionX(4); k1.setPositionY(squares-1); k1.setAlive(true); k1.setTeam("white"); k1.setImage(k1.getImage(k1)); 
		pieces.add(k1); King k2 = new King();
		k2.setPositionX(4); k2.setPositionY(0); k2.setAlive(true); k2.setTeam("black"); k2.setImage(k2.getImage(k2)); 
		pieces.add(k2); 
	}
	public static ChessSquare findSquare(int x, int y) {
		for (int k=0; k<(chessSquares.size()); k++) {
			if (x>chessSquares.get(k).getX() && x<(chessSquares.get(k).getX()+(boardSize/squares)) &&
				y>chessSquares.get(k).getY() && y<(chessSquares.get(k).getY()+(boardSize/squares)))  {return chessSquares.get(k);}
		}
		return null;
	}
	public static ChessSquare gridFindSquare(int x, int y) {
		for (int k=0; k<(chessSquares.size()); k++) {
			if (chessSquares.get(k).getBoardX() == x && chessSquares.get(k).getBoardY() == y) {return chessSquares.get(k);}
		}
		return null;
	}
	public static Piece pieceOnSquare(int x, int y) {
		for(int k = 0; k < pieces.size(); k++) {
			//System.out.println(pieces.get(k).getPositionX());
			//System.out.println(pieces.get(k).getPositionY());
			ChessSquare squareOn = gridFindSquare(pieces.get(k).getPositionX(), pieces.get(k).getPositionY());
			//System.out.println(squareOn.getX());
			if (squareOn.equals(findSquare(x,y))) {
				return pieces.get(k);
			}
		}
		return null;
	}
}



