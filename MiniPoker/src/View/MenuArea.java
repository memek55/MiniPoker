package View;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;

public class MenuArea extends MenuBar{
	public Menu option = new Menu("Options");
	
	public MenuItem changePlayer = new MenuItem("Restart and Change Player");
	public MenuItem score = new MenuItem("Score");
	public MenuItem quitGame = new MenuItem("Quit Game");
	
	
	public MenuArea() {
		super();
			
			option.getItems().addAll(changePlayer, score, quitGame);
			this.getMenus().addAll(option);
			
			
			
			
	}
	
	
}