package testing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

/**
 * This class is only used for testing that PlayState works as intended. You
 * will probably not have a similar class in your game.
 */
public class Tester {
	// Internal class, mostly to show how it works in case you need it.
	public class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	private Point position;
	private Image banana;
	private Image car;
	private Image smile;
	private int angle = 0;
	
	public Tester() {
		position = new Point(0, 0);

		banana = new Image("bolt_gold.png");
		car = new Image("shield_gold.png");
		smile = new Image("player.png");
	}

    /**
     * Sets the transform for the GraphicsContext to rotate around a pivot point.
     *
     * @param gc the graphics context the transform to applied to.
     * @param angle the angle of rotation.
     * @param px the x pivot coordinate for the rotation (in canvas coordinates).
     * @param py the y pivot coordinate for the rotation (in canvas coordinates).
     */
    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }

    /**
     * Draws an image on a graphics context.
     *
     * The image is drawn at (topLeftX, topLeftY) rotated by angle pivoted around the point:
     *   (topLeftX + image.getWidth() / 2, topLeftY + image.getHeight() / 2)
     *
     * @param g the graphics context the image is to be drawn on.
     * @param angle the angle of rotation.
     * @param topLeftX the top left x coordinate where the image will be plotted (in canvas coordinates).
     * @param topLeftY the top left y coordinate where the image will be plotted (in canvas coordinates).
     */
    private void drawRotatedImage(GraphicsContext g, Image image, double angle, double topLeftX, double topLeftY) {
    	g.save(); // saves the current state on stack, including the current transform
        rotate(g, angle, topLeftX + image.getWidth() / 2, topLeftY + image.getHeight() / 2);
        g.drawImage(image, topLeftX, topLeftY);
        g.restore(); // back to original state (before rotation)
    }
	
	/**
	 * Updates the position of the car and the banana.
	 */
	public void update() {
		// We move the position for the images each "tick", or call from the timer.
		// Can be adjusted to accomodate how fast you wish to move.
		position.x += 2;
		position.y += 2;
		angle += 3; // Updates the angle of the rotating image
	}

	public void delegate(GraphicsContext g) {
		if (g != null) {
			draw(g);
		}
	}

	private void draw(GraphicsContext g) {
		if (position.x >= SCREEN_WIDTH || position.y >= SCREEN_HEIGHT) {
			// The position is exiting the screen, so we reset it
			position.x = 0.0;
			position.y = 0.0;
		}

		drawRotatedImage(g, smile, angle, 500, 500);
		g.drawImage(car, position.x, position.y, 100, 100);
		g.drawImage(banana, position.x + 150, position.y + 150, 100, 100);
	}
}
