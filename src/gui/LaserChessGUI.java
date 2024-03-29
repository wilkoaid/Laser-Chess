package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LaserChessGUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Controller control = new Controller(stage);
		stage.setScene(new Scene(control));
		stage.setTitle("Laser Chess");
		stage.setWidth(800);
		stage.setHeight(600);
		stage.show();
	}
}
