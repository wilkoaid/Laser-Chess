package gui;

import java.io.IOException;
import java.util.Set;

import engine.Colour;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.Deflector;
import engine.piece.Piece;
import engine.piece.Switch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class Controller extends HBox {
	
	private Stage stage;
	private Board board;
	private Piece pieceSelected;

    @FXML private Button newGameButton;
    @FXML private Button helpButton;
    @FXML private Button quitButton;
    @FXML private ChoiceBox<String> colourChoiceBox = new ChoiceBox<>();
    @FXML private ChoiceBox<String> setupChoiceBox = new ChoiceBox<>();
    @FXML private GridPane grid;
    @FXML private Text turnText;
    @FXML private Button rotateClockwise;
    @FXML private Button rotateAntiClockwise;

    public Controller(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        this.stage = stage;
        this.stage.setMinHeight(600);
        this.stage.setMinWidth(800);
        
        board = new Board(Colour.WHITE);

        try {
        	fxmlLoader.load();
        	
        	// set starting colour drop-down menu
			colourChoiceBox.getItems().addAll("<< starting side >>","White", "Black");
			colourChoiceBox.setValue("<< starting side >>");
			
			// set board setup drop-down menu
			setupChoiceBox.getItems().addAll("<< board setup >>","ACE");
			setupChoiceBox.setValue("<< board setup >>");
			
			// set quit button function on click
			quitButton.setOnMouseClicked(e -> this.stage.close());
			
			
			ImageView rotateClockwiseIcon = new ImageView("resources/icons/rotate_clockwise.png");
			rotateClockwiseIcon.setFitHeight(15);
			rotateClockwiseIcon.setFitWidth(15);
			rotateClockwise.setGraphic(rotateClockwiseIcon);
			rotateClockwise.setOnMouseClicked(e -> {
				// get piece currently selected
				
				// rotate piece in model
				
				// rotate piece image
				
			});
			
			ImageView rotateAntiClockwiseIcon = new ImageView("resources/icons/rotate_anticlockwise.png");
			rotateAntiClockwiseIcon.setFitHeight(15);
			rotateAntiClockwiseIcon.setFitWidth(15);
			rotateAntiClockwise.setGraphic(rotateAntiClockwiseIcon);
			rotateAntiClockwise.setOnMouseClicked(e -> {
				// get piece currently selected
				
				// rotate piece in model
				
				// rotate piece image
				
			});
			
			// set turn text
			turnText.setText("");
			
			// set new game button function on click
			newGameButton.setOnMouseClicked(e -> {
				// initialise model
				initialiseBoard();
				setStartingColour();
				
				// set turn text
				turnText.setText("Current turn: " + board.getTurn().toString());
				
				// clear any former nodes to prevent duplicates
				grid.getChildren().clear();
				
				//draw tiles background
				drawTiles();
				
				// draw black and white pieces
				drawPieces(board.getOccupiedTilesBlack());
				drawPieces(board.getOccupiedTilesWhite());
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public void initialiseBoard() {
    	if(setupChoiceBox.getValue().equals("ACE")) {
    		board.initialiseBoardACE();
    	} 
    	
    	// else ifs for other setups...
    }

    public void setStartingColour() {
    	if(colourChoiceBox.getValue().equals("Black")) {
    		board.setTurn(Colour.BLACK);
    	} else {
    		board.setTurn(Colour.WHITE);
    	}
    }
    
    private void drawPieces(Set<Tile> occupiedTiles) {
    	for(Tile tile : occupiedTiles) {
			Piece piece = tile.getPiece();
			int gridY = tile.getFile().getVal()-1;
			int gridX = tile.getRank().getVal()-1;
			if(piece instanceof Switch || 
					piece instanceof Deflector) {
				ImageView image = piece.getImage();
				grid.add(image, gridY, gridX);
				image.setFitWidth(50);
				image.setFitHeight(50);
				image.setRotate(piece.getDirection() - 45);	
				
				// highlight move actions when piece is clicked on
				image.setOnMouseClicked(e -> {
					pieceSelected = piece;
					highlightMoves();
					
				});
			} else {
				ImageView image = piece.getImage();
				grid.add(image, gridY, gridX);
				image.setFitWidth(50);
				image.setFitHeight(50);
				image.setRotate(piece.getDirection());							
			}
		}
    }
    
    private void highlightMoves() {
		
	}

	private void drawTiles() {
    	for(Tile tile : board.getBoard()) {
			if(!tile.isOffboard()) {
				Rectangle rec = new Rectangle(50, 50);
				rec.setFill(Color.GRAY);
				rec.setStroke(Color.BLACK);
				grid.add(rec, tile.getFile().getVal()-1, tile.getRank().getVal()-1);
			}
		}
    }

}
