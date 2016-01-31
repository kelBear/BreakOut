import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Block { 
		private boolean hit;
		private double x, y;
		private Color colour;
		private double width, height, speedX, speedY;
		Block(double x, double y, Color colour, double width, double height, double speedX, double speedY) {
			this.x = x;
			this.y = y;
			hit = false;
			this.colour = colour;
			this.width = width;
			this.height = height;
			this.speedX = speedX;
			this.speedY = speedY;
		}
		public boolean getHit() {
			return hit;
		}
		public void setHit(boolean hit) {
			this.hit = hit;
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
		public double getSpeedX() {
			return speedX;
		}
		public void setSpeedX(double speed) {
			this.speedX = speed;
		}
		public double getSpeedY() {
			return speedY;
		}
		public void setSpeedY(double speed) {
			this.speedY = speed;
		}
		public void move() {
			x += speedX;
			//y += speedY;
		}
		public void draw(Graphics g) {
			if (!hit) {
				Graphics2D g2 = (Graphics2D) g;
				Color oldcolor = g2.getColor();
				Stroke oldstroke = g2.getStroke();
				g2.setColor(colour);
				g2.fillRect((int)x, (int)(Math.ceil(y)), (int)width, (int)height);
				g2.setColor(Color.BLACK);
				g2.setStroke(new BasicStroke((int)(height*0.2)));
				g2.drawRect((int)x, (int)y, (int)width, (int)height);
				g2.setColor(oldcolor);
				g2.setStroke(oldstroke);
			}
		}
	}