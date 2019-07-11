package gui;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import engine.Colour;
import engine.action.Action;
import engine.action.Move;
import engine.board.Board;
import engine.board.Tile;
import engine.piece.Deflector;
import engine.piece.Piece;
import engine.piece.Switch;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

public class Controller extends HBox {
	
	private Stage stage;
	private Board board;
	private Piece pieceSelected;
	private boolean isSelected = false;
	private ImageView imageViewSelected = null;

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
				board.calculateAllActions();
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
			
			Image image = piece.getImage();
			ImageView imageView = new ImageView(image);
			grid.add(imageView, gridY, gridX);
			imageView.setFitWidth(50);
			imageView.setFitHeight(50);
			
			if(piece instanceof Switch || 
					piece instanceof Deflector) {
				imageView.setRotate(piece.getDirection() - 45);	
			} else {
				imageView.setRotate(piece.getDirection());	
			}
			
			imageView.setOnMouseClicked(e -> {
				if(isSelected) {
					unhighlightMoves();
					imageViewSelected.setEffect(null);
				}
				pieceSelected = piece;
				isSelected = true;
				imageViewSelected = imageView;
				DropShadow shadow = new DropShadow();
				shadow.setBlurType(BlurType.GAUSSIAN);
				shadow.setHeight(5);
				shadow.setWidth(5);
				shadow.setRadius(10);
				shadow.setColor(Color.RED);
				imageView.setEffect(shadow);
				highlightMoves();
				if(pieceSelected instanceof Switch) {
					// show swap actions too
				}
			});
			
		}
    }
    
    private void highlightMoves() {
		List<Action> possibleActions = pieceSelected.getActions();
		for(Action action : possibleActions) {
			if(action instanceof Move) {
				Tile destination = ((Move) action).getDestination();
				Rectangle node = (Rectangle) grid.getChildren().get((destination.getFile().getVal()-1) + (destination.getRank().getVal()-1)*10);
				node.setFill(Color.LIGHTGREEN);
			}
		}
	}
    
    private void unhighlightMoves(){
    	List<Action> possibleActions = pieceSelected.getActions();
		for(Action action : possibleActions) {
			if(action instanceof Move) {
				Tile destination = ((Move) action).getDestination();
				Rectangle node = (Rectangle) grid.getChildren().get((destination.getFile().getVal()-1) + (destination.getRank().getVal()-1)*10);
				node.setFill(Color.GRAY);
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
				
				rec.setOnMouseClicked(e -> {
					if(rec.getFill().equals(Color.LIGHTGREEN)) {
						// perform move or swap action
					}
				});
			}
    	}
	}

}
