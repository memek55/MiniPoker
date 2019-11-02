package Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerChoose {

	public static void display() {
		
		
		Stage window = new Stage();
		window.setTitle("Mini Poker by Mert Emek");
		BorderPane layout = new BorderPane();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		VBox v1 = new VBox();
		
		
		Label label1 = new Label("Mini Poker by Mert Emek");
		Label label2 = new Label("Choose Player");
		
		
		RadioButton rb2 = new RadioButton("2 Player");
		RadioButton rb3 = new RadioButton("3 Player");
		RadioButton rb4 = new RadioButton("4 Player");
		ToggleGroup tg = new ToggleGroup();
        tg.getToggles().addAll(rb2, rb3, rb4);
		rb3.setSelected(true);
		
		Button button = new Button();
		button.setText("Start the Game");
		button.setOnAction(e->{
				int i = 0;
			if(rb2.isSelected())
				i = 2;
			if(rb3.isSelected())
				i = 3;
			if(rb4.isSelected())
				i = 4;
			PokerGame.NUM_PLAYERS = i;
			window.close();
			
		});
	
		
		layout.setTop(h1);
		h1.getChildren().add(label1);
		h1.setAlignment(Pos.CENTER);
		h1.setPadding(new Insets(50,50,50,50));
		h1.setSpacing(40);
		h1.setStyle("-fx-background-color: #3e753b;");
		
		layout.setBottom(h2);
		h2.getChildren().add(button);
		h2.setAlignment(Pos.CENTER_RIGHT);
		h2.setPadding(new Insets(50,50,50,50));
		h2.setSpacing(40);
		h2.setStyle("-fx-background-color: #3e753b;");
		
		
		layout.setCenter(h3);
		h3.getChildren().addAll(label2, v1);
		h3.setAlignment(Pos.CENTER);
		v1.getChildren().addAll(rb2, rb3, rb4);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(50,50,50,50));
		v1.setSpacing(5);
		
		
		Scene scene = new Scene(layout, 900, 500);
		window.setScene(scene);
		window.showAndWait();
			
	}

}
