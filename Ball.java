import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball { 
	private double x, y, radius, left, right;
	private double speedX, speedY, paddleSpeed, speed;
	private boolean startGame = false, fireBall = false;
	private int fireCount = 0;
	private Color color;
	Ball(double x, double y, double radius, double speedX, double speedY, double right, double paddleSpeed, double speed, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.speedX = speedX;
		this.speedY = speedY;
		this.left = x;
		this.right = right;
		this.paddleSpeed = paddleSpeed;
		this.speed = speed;
		this.color = color;
	}
	public void setColor (Color color) {
		this.color = color;
	}
	public Color getColor() {
		return color;
	}
	public void setFireBall(boolean fireBall) {
		this.fireBall = fireBall;
	}
	public boolean getFireBall() {
		return fireBall;
	}
	public void setFireCount(int fireCount) {
		this.fireCount = fireCount;
	}
	public int getFireCount() {
		return fireCount;
	}
 	public void setRadius(double radius) {
		this.radius = radius;
	}		
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getSpeed() {
		return speed;
	}
	public void setStartGame(boolean startGame) {
		this.startGame = startGame;
	}
	public boolean getStartGame() {
		return startGame;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public void setLeftt(double left) {
		this.left = left;
	}
	public double getSpeedX() {
		return speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	public double getRadius() {
		return radius;
	}
	public void moveLeft() {
		if (x > left + paddleSpeed)
			x-=paddleSpeed;
		else
			x = left;
	}
	public void moveRight() {
		if (x < right - paddleSpeed)
			x+=paddleSpeed;
		else
			x = right;
	}
	public void move() {
		x += speedX;
		y += speedY;
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color oldcolor = g2.getColor();
		g2.setColor(color);
		g2.fillOval((int)x, (int)y, (int)radius, (int)radius);
		g2.setColor(oldcolor);
	}
}