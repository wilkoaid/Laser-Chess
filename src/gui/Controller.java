package gui;

import java.io.IOException;

import engine.Colour;
import engine.board.Board;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class Controller extends HBox {

    @FXML private Button newGameButton;
    @FXML private Button helpButton;
    @FXML private Button quitButton;
    @FXML private ComboBox<String> colourComboBox = new ComboBox<>();
    @FXML private ComboBox<String> setupComboBox = new ComboBox<>();

    public Controller() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("View.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        Board board = new Board(Colour.WHITE);
        
        try {
			fxmlLoader.load();
			colourComboBox.getItems().addAll("White", "Black");
			setupComboBox.getItems().addAll("ACE");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        



        












    }


}
