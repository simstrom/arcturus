import java.io.File;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import main.Main;

public class MainMenu extends Pane {
	
	public MainMenu(Stage stage, String style) {
		
		Label logo = new Label("ARCTURUS");
		Image bg = new Image("/resources/menubg.png");
		Button start = new Button("PLAY");
		Button score = new Button("SCORE");
		Button help = new Button("HELP");
		Button exit = new Button("QUIT");
		Label by = new Label("© 2021, Vallastaden Studios™");
	
        Canvas canvas = new Canvas( 800, 800 );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(bg, getWidth(), getHeight());
		
		VBox menuList = new VBox(30, start, score, help, exit);
		menuList.setAlignment(Pos.CENTER);
		
		VBox contents = new VBox(60, logo, menuList, by);
		Font theFont = Font.font( "Futura", FontWeight.BOLD, 104 );
		Font subFont = Font.font( "Futura", FontWeight.LIGHT, 14 );

		logo.setFont(theFont);
		logo.setStyle("-fx-text-fill: #6671DA");
		by.setFont(subFont);
		by.setStyle("-fx-text-fill: #363C70");
		
		Glow glow = new Glow();   
        glow.setLevel(1.0);  
        logo.setEffect(glow);
        by.setEffect(glow);
		contents.setTranslateX(130);
		contents.setTranslateY(200);
		contents.setAlignment(Pos.CENTER);
					
		getChildren().addAll(canvas, contents);
		

	}
}
