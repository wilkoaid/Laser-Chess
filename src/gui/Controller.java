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
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.stage.Stage;

public class Controller extends HBox {
	
	private Stage stage;
	private Board board;

    @FXML private Button newGameButton;
    @FXML private Button helpButton;
    @FXML private Button quitButton;
    @FXML private ChoiceBox<String> colourChoiceBox = new ChoiceBox<>();
    @FXML private ChoiceBox<String> setupChoiceBox = new ChoiceBox<>();
    @FXML private GridPane grid;

    public Controller(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        this.stage = stage;
        
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
			
			// set new game button function on click
			newGameButton.setOnMouseClicked(e -> {
				// initialise model
				initialiseBoard();
				setStartingColour();
				
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
    	if(colourChoiceBox.getValue().equals("White")) {
    		board.setTurn(Colour.WHITE);
    	} else {
    		board.setTurn(Colour.BLACK);
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
			} else {
				ImageView image = piece.getImage();
				grid.add(image, gridY, gridX);
				image.setFitWidth(50);
				image.setFitHeight(50);
				image.setRotate(piece.getDirection());							
			}
		}
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
