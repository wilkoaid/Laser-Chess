package gui;

import java.io.IOException;

import engine.Colour;
import engine.board.Board;
import engine.board.Tile;
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
			quitButton.setOnMouseClicked(e -> closeWindow());
			
			// set new game button function on click
			newGameButton.setOnMouseClicked(e -> {
				// initialise model
				initialiseBoard();
				setStartingColour();
				
				// draw tiles
				for(Tile tile : board.getBoard()) {
					if(!tile.isOffboard()) {
						Rectangle rec = new Rectangle(50, 50);
						rec.setFill(Color.GRAY);
						rec.setStroke(Color.BLACK);
						grid.add(rec, tile.getFile().getVal()-1, tile.getRank().getVal()-1);
					}
				}
				
				// draw pieces
				ImageView laser = new ImageView("laser.png");
				grid.add(laser, 0, 0);
				laser.setFitHeight(50);
				laser.setFitWidth(50);
			});
			
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    public void closeWindow() {
    	this.stage.close();
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

}
