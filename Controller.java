import java.awt.Dimension;
import java.awt.Graphics;

public class Controller {
	
	static View v;
	static Model m;
	
	Controller (Dimension dim, int fps, double speed) {
		v = new View(this);
		m = new Model(v, fps, speed);
		v.splashScreen(dim);
	}
	
	public void setObjects(Dimension dim, boolean feature) {
		m.setObjects(dim, feature);
	}
	
	public void resize(Dimension dim) {
		m.resize(dim);
	}
	
	public void moveLeft() {
		m.moveLeft();
	}
	
	public void moveRight() {
		m.moveRight();
	}
	
	public void runGame() {
		m.runGame();
	}
	
	public void draw(Graphics g) {
		m.draw(g);
	}
	
	public int getLives() {
		return m.getLives();
	}
	
	public int getFPS() {
		return m.getFPS();
	}
	
	public int getScore() {
		return m.getScore();
	}
	
	public String getMessage() {
		return m.getMessage();
	}
	
	public void restart() {
		m.restart();
	}
	
	public void setMoveLeft(boolean moveLeft) {
		m.setMoveLeft(moveLeft);
	}
	
	public void setMoveRight(boolean moveRight) {
		m.setMoveRight(moveRight);
	}
	
	public boolean getStartGame() {
		return m.ball.getStartGame();
	}
}

