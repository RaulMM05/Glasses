package gestion.fct;

import java.io.IOException;

import gestion.fct.appController.AppController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{

	
	@Override
	public void start(Stage primaryStage) throws IOException {
		AppController app = new AppController(primaryStage);
	    app.changeScene(AppController.FXML_LOGIN);
	    primaryStage.setResizable(false);
		primaryStage.initStyle(StageStyle.TRANSPARENT);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch();
	}
}
