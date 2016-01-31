import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Paddle { 
	private double x, y, width, height, left, right, speed;
	Paddle(double x, double y, double width, double height, double right, double speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.left = x;
		this.right = right;
		this.speed = speed;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setRight(double right) {
		this.right = right;
	}
	public void setLeftt(double left) {
		this.left = left;
	}
	public double getSpeed() {
		return speed;
	}
	public void moveLeft() {
		if (x > left + speed)
			x-=speed;
		else
			x = left;
	}
	public void moveRight() {
		if (x < right - speed)
			x+=speed;
		else
			x = right;
	}
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Color oldcolor = g2.getColor();
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect((int)x, (int)y, (int)width, (int)height);
		g2.setColor(oldcolor);
	}
}