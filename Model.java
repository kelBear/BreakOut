import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

// model keeps track of game state (objects in the game)
// contains a Timer that ticks periodically to advance the game
// AND calls an update() method in the View to tell it to redraw
public class Model { 
	int blocksleft, lives, score;
	boolean restart = false, feature = false;
	boolean moveLeft = false, moveRight = false;

	View v;
	Timer timer;
	Dimension dim;
	static Ball ball;
	static Paddle paddle;	
	static final int BLOCKS_PER_ROW = 10, BLOCKS_PER_COLUMN = 5;
	static Block[][] blocks = new Block[BLOCKS_PER_COLUMN][BLOCKS_PER_ROW];

	int fps = 40;
	double speed = 0;

	String message = "";

	Model(View v, int fps, double speed) {
		this.v = v;
		this.fps = fps;
		//speed factors: ball speed Y = 0.35, paddle speed = 0.35, block speed X = 0.9 
		this.speed = speed;
	}

	public void resizeBlocks() {
		double blockwidth = dim.getWidth()*0.08;
		double blockheight = dim.getHeight()*0.05;
		double border = (dim.getWidth()-blockwidth*10)/2;
		double topborder = dim.getHeight()*0.1;
		if (feature)
			topborder = blocks[0][0].getY();
		for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				blocks[i][j].setX(j*blockwidth+border);
				blocks[i][j].setY(i*blockheight+topborder);
				blocks[i][j].setWidth(blockwidth);
				blocks[i][j].setHeight(blockheight);
			}
		}
	}

	public void resizeBall() {
		double radius = dim.getHeight()*0.02;
		double left = paddle.getWidth()/2 - radius/2;
		//int bottom = (int)(dim.getHeight()*0.902);
		double bottom = dim.getHeight()*0.94 - paddle.getHeight() - ball.getRadius();
		double right = dim.getWidth()*0.99 - left - radius;
		ball.setRadius(radius);
		ball.setRight(right);
		ball.setLeftt(left);
		if (!ball.getStartGame()) {
			ball.setX(left + paddle.getX());
			ball.setY(bottom);
		}
	}

	public void resizePaddle() {
		double paddlewidth = dim.getWidth()*0.1;
		//		double bottom = dim.getHeight()*0.92;
		double bottom = dim.getHeight()*0.94 - paddle.getHeight();
		double left = 0;
		double right = dim.getWidth()*0.99 - paddlewidth;
		paddle.setWidth(paddlewidth);
		paddle.setY(bottom);
		paddle.setRight(right);
		paddle.setLeftt(left);
		if (paddle.getX() + paddle.getWidth() >= right)
			paddle.setX(right - paddle.getWidth());
	}

	public void setFeatureBlocks() {
		double blockwidth = dim.getWidth()*0.08;
		double blockheight = dim.getHeight()*0.05;
		double border = (dim.getWidth()-blockwidth*10)/2;
		double topborder = dim.getHeight()*0.1;
		//		double blockspeedX = dim.getWidth()*0.09/fps;
		double blockspeedX = dim.getWidth()*0.09/fps;
		double blockspeedY = dim.getHeight()*0.01;
		for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				blocks[i][j] = new Block(j*blockwidth+border, i*blockheight+topborder, new Color(new Random().nextFloat(), new Random().nextFloat(), new Random().nextFloat()).brighter(), blockwidth, blockheight, blockspeedX, blockspeedY);
			}
		}
	}

	public void setBlocks() {
		double blockwidth = dim.getWidth()*0.08;
		double blockheight = dim.getHeight()*0.05;
		double border = (dim.getWidth()-blockwidth*10)/2;
		double topborder = dim.getHeight()*0.1;
		for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				switch(i){
				case 0:
					blocks[i][j] = new Block(j*blockwidth+border, i*blockheight+topborder, Color.RED, blockwidth, blockheight, 0, 0);
					break;
				case 1:
					blocks[i][j] = new Block(j*blockwidth+border, i*blockheight+topborder, Color.ORANGE, blockwidth, blockheight, 0, 0);
					break;
				case 2:
					blocks[i][j] = new Block(j*blockwidth+border, i*blockheight+topborder, Color.YELLOW, blockwidth, blockheight, 0, 0);
					break;
				case 3:
					blocks[i][j] = new Block(j*blockwidth+border, i*blockheight+topborder, Color.GREEN, blockwidth, blockheight, 0, 0);
					break;
				case 4:
					blocks[i][j] = new Block(j*blockwidth+border, i*blockheight+topborder, Color.CYAN, blockwidth, blockheight, 0, 0);
					break;
				default:
					blocks[i][j] = new Block(0, 0, Color.BLACK, 0, 0, 0, 0);
					break;
				}
			}
		}
	}

	public void setPaddle() {
		double paddlewidth = dim.getWidth()*0.1;
		double paddleheight = dim.getHeight()*0.018 + 10;
		double left = 0;
		double bottom = dim.getHeight()*0.94 - paddleheight;
		double right = dim.getWidth()*0.99 - paddlewidth;
		//		double paddleSpeed = dim.getWidth()*0.3/fps;
		double paddleSpeed = dim.getWidth()*(speed)/fps;
		paddle = new Paddle(left, bottom, paddlewidth, paddleheight, right, paddleSpeed);
	}

	public void setBall() {
		double radius = dim.getHeight()*0.02;
		double left = paddle.getWidth()/2 - radius/2;
		//double bottom = dim.getHeight()*0.902;
		double bottom = dim.getHeight()*0.94 - paddle.getHeight() - radius;
		double right = dim.getWidth()*0.99 - left - radius;
		double speedX = 0;
		//		double speedY = -dim.getHeight()*0.35/fps;
		double speedY = -dim.getHeight()*(speed)/fps;
		double speed = Math.sqrt(Math.pow(speedX, 2) + Math.pow(speedY, 2));
		ball = new Ball(left, bottom, radius, speedX, speedY, right, paddle.getSpeed(), speed, Color.WHITE);
	}

	public void resize(Dimension dim) {
		this.dim = dim;
		resizeBlocks();
		resizeBall();
		resizePaddle();
		v.update();
	}

	public void setObjects(Dimension dim, boolean feature) {
		this.dim = dim;
		blocksleft = 50;
		lives = 3;
		score = 0;
		this.feature = feature;
		if (!feature)
			setBlocks();
		else
			setFeatureBlocks();
		setPaddle();
		setBall();
		v.update();
	}

	public void runGame() {
		if (ball.getStartGame() == false) {
			message = "";
			ball.setStartGame(true);
			timer = new Timer();
			timer.scheduleAtFixedRate(new GameTask(), 0, 1000/fps);
		}
	}

	public class GameTask extends TimerTask {

		@Override
		public void run() {
			if (!checkOut()) {
				checkPaddle();
				if (checkBlock()) {
					stop();
					winGame();
				}
				checkBorder();
				if (feature) {
					processBlocks();
					if (checkBlockBottom() == true) {
						stop();
						if (lives != 0)
							gameEnd();
					}
					if (ball.getFireBall()) {
						if (ball.getFireCount() > 0)
							ball.setFireCount(ball.getFireCount()-1);
						if (ball.getFireCount() == 0) {
							message = "";
							ball.setFireCount(0);
							ball.setFireBall(false);
							ball.setColor(Color.WHITE);
						}
					}
				}
				ball.move();
				v.update();
			}
			else {
				stop();
				lives--;
				if (lives == 0)
					gameEnd();
				else
					resetBall();
			}
			//	}
			if (moveLeft) {
				moveLeft();
			}
			else if (moveRight) {
				moveRight();
			}
		}
		public void stop() {
			timer.cancel();
			v.update();
		}

	}

	public void processBlocks() {
		boolean left = false, right = false;
		if (blocks[0][0].getX() - blocks[0][0].getSpeedX() < 1) {
			left = true;
			right = false;
		}
		else if (blocks[0][BLOCKS_PER_ROW-1].getX()+blocks[0][BLOCKS_PER_ROW-1].getWidth() + blocks[0][BLOCKS_PER_ROW-1].getSpeedX() > dim.getWidth()-10) {
			right = true;
			left = false;
		}
		else {
			left = false;
			right = false;
		}
		for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				if (left)
					blocks[i][j].setSpeedX(Math.abs(blocks[i][j].getSpeedX()));
				else if (right)
					blocks[i][j].setSpeedX(Math.abs(blocks[i][j].getSpeedX())*-1);
				blocks[i][j].move();
			}
		}
	}

	public void gameEnd() {
		//boo hoo - you lose (do you wanna play again?)
		message = "You are out of lives! :(\nPress 'n' to play again!";
		restart = true;
	}

	public void resetBall() {
		message = "You lost a life! :(";
		ball.setStartGame(false);
		ball.setX(paddle.getX() + paddle.getWidth()/2 - ball.getRadius()/2);
		ball.setY(dim.getHeight()*0.94 - paddle.getHeight() - ball.getRadius());
		ball.setColor(Color.WHITE);
		ball.setFireBall(false);
		ball.setFireCount(0);
	}

	public void winGame() {
		//yay - you win (do you wanna play again?)
		message = "You Win! :)\nPress 'n' to play again!";
		restart = true;
	}

	public void restart() {
		if (restart == true) {
			restart = false;
			ball.setStartGame(false);
			message = "";
			setObjects(dim, feature);
		}
	}

	public boolean checkOut() {
		if ((ball.getY())- ball.getRadius() >= paddle.getY()) {
			if (!checkPaddle())
				return true;
		}
		return false;
	}

	public boolean checkBlockBottom() {
		for (int i = BLOCKS_PER_COLUMN-1; i >= 0; i--) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				if (!blocks[i][j].getHit()) {
					if ((blocks[i][j].getY() + blocks[i][j].getHeight()) >= paddle.getY())
						return true;
				}
			}
		}
		return false;
	}

	public boolean checkPaddle() {
		Shape newball = new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
		Shape newpaddle = new Rectangle2D.Double(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
		Area areaball = new Area(newball);
		areaball.intersect(new Area(newpaddle));
		if (!areaball.isEmpty() && ball.getSpeedY()>0){
			double d = (ball.getX()+ ball.getRadius()/2 - (paddle.getX() + paddle.getWidth()/2));
			d /= (paddle.getWidth()/2);
			ball.setSpeedY(-Math.sqrt(Math.pow(ball.getSpeed(), 2) - Math.abs(ball.getSpeed()*d)));
			ball.setSpeedX(ball.getSpeed()*d);
			if (feature) {
				for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
					for (int j = 0; j < BLOCKS_PER_ROW; j++) {
						blocks[i][j].setY(Math.abs(blocks[i][j].getY()) + Math.abs(blocks[i][j].getSpeedY()));
					}
				}
			}
		}
		return (!areaball.isEmpty());
	}

	public boolean checkBlock() {
		for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				if (blocks[i][j].getHit() == false) {
					Shape newball = new Ellipse2D.Double(ball.getX(), ball.getY(), ball.getRadius(), ball.getRadius());
					Shape newblock = new Rectangle2D.Double(blocks[i][j].getX(), blocks[i][j].getY(), blocks[i][j].getWidth(), blocks[i][j].getHeight());
					Area areaball = new Area(newball);
					areaball.intersect(new Area(newblock));
					if (!areaball.isEmpty()) {	
						if (!feature || !ball.getFireBall()) {
							if(ball.getY()>=blocks[i][j].getY()+blocks[i][j].getHeight()-Math.abs(ball.getSpeedY())&&ball.getX()>=blocks[i][j].getX()-ball.getRadius()/2&&ball.getX()<=blocks[i][j].getX()+blocks[i][j].getWidth()+ball.getRadius()/2){
								ball.setSpeedY(-ball.getSpeedY());
								//System.out.println("bot");
							}
							else if(ball.getY()<=blocks[i][j].getY()-ball.getRadius()+Math.abs(ball.getSpeedY())&&ball.getX()>=blocks[i][j].getX()-ball.getRadius()/2&&ball.getX()<=blocks[i][j].getX()+blocks[i][j].getWidth()+ball.getRadius()/2){
								ball.setSpeedY(-ball.getSpeedY());
								//System.out.println("top");
							}
							else if(ball.getX()+ball.getRadius()<=blocks[i][j].getX()+Math.abs(ball.getSpeedX())&&ball.getY()>=blocks[i][j].getY()-ball.getRadius()/2&&ball.getY()<=blocks[i][j].getY()+blocks[i][j].getHeight()+ball.getRadius()/2){
								ball.setSpeedX(-ball.getSpeedX());
								//System.out.println("left");
							}
							else if(ball.getX()>=blocks[i][j].getX()+blocks[i][j].getWidth()-Math.abs(ball.getSpeedX())&&ball.getY()>=blocks[i][j].getY()-ball.getRadius()/2&&ball.getY()<=blocks[i][j].getY()+blocks[i][j].getHeight()+ball.getRadius()/2){
								ball.setSpeedX(-ball.getSpeedX());
								//System.out.println("right");
							}
							else {
								ball.setSpeedX(-ball.getSpeedX());
								ball.setSpeedY(-ball.getSpeedY());
								//System.out.println("corner");
							}
						}
						else if (feature && ball.getFireBall()) {}
						blocks[i][j].setHit(true);
						blocksleft--;
						score += 10;
						if (blocksleft == 0) {
							return true;
						}
						else {
							if (feature) {
								if (blocksleft < 50 && blocksleft % 10 == 0 && !ball.getFireBall()) {
									message = "You have triggered fire ball!";
									ball.setColor(Color.RED);
									ball.setFireBall(true);
									ball.setFireCount(3*fps);
								}
							}
						}
						return false;
					}
				}
			}
		}
		return false;
	}

	public void checkBorder() {
		if ((ball.getX() <= 0 && ball.getSpeedX() < 0 ) || ((ball.getX()+ball.getRadius()) >= dim.getWidth() - ball.getRadius() && ball.getSpeedX() > 0))
			ball.setSpeedX(-ball.getSpeedX());
		if (ball.getY() <= 0)
			ball.setSpeedY(-ball.getSpeedY());
	}

	public void draw(Graphics g) {
		for (int i = 0; i < BLOCKS_PER_COLUMN; i++) {
			for (int j = 0; j < BLOCKS_PER_ROW; j++) {
				blocks[i][j].draw(g);
			}
		}
		paddle.draw(g);
		ball.draw(g);
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}

	public void moveLeft() {
		paddle.moveLeft();

		if (ball.getStartGame() == false)
			ball.moveLeft();
		v.update();
	}

	public void moveRight() {
		paddle.moveRight();
		if (ball.getStartGame() == false)
			ball.moveRight();
		v.update();
	}

	public int getLives() {
		return lives;
	}

	public int getFPS() {
		return fps;
	}

	public int getScore() {
		return score;
	}

	public String getMessage() {
		return message;
	}

}