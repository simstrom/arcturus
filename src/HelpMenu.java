import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import main.Main;

public class HelpMenu extends VBox {
	
	public HelpMenu(Pane pane, Stage stage, String style) {
	
	//ENERGY
	Image icon1 = new Image("/resources/bolt_gold.png");
	Label text1 = new Label("Energy");
	text1.setTextFill(Color.BLACK);
	Label text2 = new Label("Gather energy to increase your flight speed.\nTo infinity and beyond!");
	text2.setTextFill(Color.BLACK);
	VBox textsect = new VBox(10, text1, text2);
	Canvas canvas = new Canvas( 36, 36 );
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.drawImage(icon1, getWidth(), getHeight());
	Font theFont = Font.font( "Futura", FontWeight.BOLD, 16 );
    text1.setFont(theFont);
    HBox section1 = new HBox(canvas, textsect);	
    section1.setSpacing(50);
    
    //SHIELD
    Image icon2 = new Image("/resources/shield_gold.png");
	Label text3 = new Label("Shield");
	text1.setTextFill(Color.BLACK);
	Label text4 = new Label("Gather shields to strengthen your defences.\nYou are unstoppable!");
	text2.setTextFill(Color.BLACK);
	VBox textsect2 = new VBox(10, text3, text4);
	Canvas canvas2 = new Canvas( 36, 36 );
    GraphicsContext gc2 = canvas2.getGraphicsContext2D();
    gc2.drawImage(icon2, getWidth(), getHeight());
    text3.setFont(theFont);
    HBox section2 = new HBox(canvas2, textsect2);	
    section2.setSpacing(50);
    
	Button back = new Button("GOT IT!");
	back.setTranslateY(170);
	
	getChildren().addAll(section1, section2, back);
	section1.setAlignment(Pos.CENTER);
	section2.setAlignment(Pos.CENTER);
	setAlignment(Pos.CENTER);
	setSpacing(80);



    
	}  
}
