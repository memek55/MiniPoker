package Controller;


import java.io.File;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import Game.PlayerChoose;
import Game.PokerGame;
import Model.Card;
import Model.DeckOfCards;
import Model.HandType;
import Model.Player;
import Model.PokerModel;
import View.MenuArea;
import View.PlayerPane;
import View.PokerView;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class PokerController {
	private PokerModel model;
	private PokerView view;
	MediaPlayer mediaPlayer;
	
	public void play() {
	}
	
	public PokerController(PokerModel model, PokerView view) {
		this.model = model;
		this.view = view;
		
		view.getShuffleButton().setOnAction( e -> shuffle() );
		view.getDealButton().setOnAction( e -> deal() ); // handler
		
		view.quiteGame().setOnAction(e -> quiteGame());
		view.changePlayer().setOnAction(e -> {
		  					view.primaryStage.close();
		  					Platform.runLater( () -> new PokerGame().start(new Stage()));});
		
		
		view.getScores().setOnAction(e -> popUpScores());
		
	}

	/**
     * Remove all cards from players hands, and shuffle the deck
     */
    private void shuffle() {
    	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) {
    		Player p = model.getPlayer(i);
    		p.discardHand();
    		PlayerPane pp = view.getPlayerPane(i);
    		pp.updatePlayerDisplay();
    	}
    	model.getDeck().shuffle();
    }
    
    /**
     * Deal each player five cards, then evaluate the two hands
     */
    private void deal() {
    	int cardsRequired = PokerGame.NUM_PLAYERS * Player.HAND_SIZE;
    	DeckOfCards deck = model.getDeck();
    	if (cardsRequired <= deck.getCardsRemaining()) {
        	for (int i = 0; i < PokerGame.NUM_PLAYERS; i++) { // Discard Hands to both Players
        		Player p = model.getPlayer(i);
        		p.discardHand();
        		for (int j = 0; j < Player.HAND_SIZE; j++) {
        			
        			Card card = deck.dealCard();
        			p.addCard(card);
        		}
        		p.evaluateHand();
        	}	
        	for( int c = 0 ; c < PokerGame.NUM_PLAYERS; c++) { // Add hands to static ArrayList for all Players
        		Player p = model.getPlayer(c);
        		
        		p.addHandTypeToList();
        	}
        	for( int b = 0 ; b < PokerGame.NUM_PLAYERS; b++) { // Evaluate Winner
        		Player p = model.getPlayer(b);
        		p.evaluateWinner();
        	}
         // ClearArrayList
        	Player.handTypeList.clear();
        	
        	for (int a = 0 ; a < PokerGame.NUM_PLAYERS; a++) { // Update PlayerPane
        	PlayerPane pp = view.getPlayerPane(a);
    		pp.updatePlayerDisplay();
        	}
        	
    	} else {
            Alert alert = new Alert(AlertType.INFORMATION, "Not enough cards - shuffle first");
            alert.setTitle("Shuffle!");
            alert.showAndWait();
    }
}
    private void quiteGame() {
    	System.exit(0);
    }
    
    
    private void popUpScores() {
    	ArrayList <String> players = new ArrayList<>();
    	for( int i = 0; i < PokerGame.NUM_PLAYERS ; i++) {
    		players.add("Player " + (i+1) + ": " + (view.getPlayerPane(i).counter/5) +" Round Wins.");
    	}
    	String str = String.join("\n", players);
    	Alert a = new Alert(AlertType.NONE, str);
		a.setTitle("Scores");
		a.getDialogPane().getButtonTypes().add(ButtonType.OK);
		
		a.showAndWait();
	}
    
    
    public static  void setTwoPlayers() {
		PokerGame.NUM_PLAYERS = 2;
	}
    
    public static void setThreePlayers() {
		PokerGame.NUM_PLAYERS = 3;	
	}
    
    public static  void setFourPlayers() {
		PokerGame.NUM_PLAYERS = 4;
	}
    
   // public static void startGame() {
   // 	PokerGame.launch();
   // }

	
    
}

