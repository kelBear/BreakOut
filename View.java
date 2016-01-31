import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

// game window
// draws everything based on the game state
// receives notification from the model when something changes, and 
// draws components based on the model.
public class View extends JComponent {	

	static JFrame f = new JFrame("Breakout");

	Controller c;

	View(Controller c) {
		this.c = c;
	}

	public void setGame(boolean feature, Dimension windowSize) {
		View canvas = new View(this.c);
		f.setResizable(true);
		f.setContentPane(canvas);
		f.setLocationRelativeTo(null);
		Dimension minimum = new Dimension((int)(windowSize.getWidth()*0.7), (int)(windowSize.getHeight()*0.7));
		Dimension maximum= new Dimension((int)(windowSize.getWidth()*0.95), (int)(windowSize.getHeight()*0.95));
		f.setMinimumSize(minimum);
		f.setMaximumSize(maximum);
		f.setVisible(true);
		f.setBackground(Color.BLACK);

		c.setObjects(windowSize, feature);

		f.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension dim = f.getSize();
				c.resize(dim);
			}
		});

		f.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (c.getStartGame())
						c.setMoveLeft(true);
					else
						c.moveLeft();
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (c.getStartGame())
						c.setMoveRight(true);
					else
						c.moveRight();
				}
				else if (e.getKeyCode() == KeyEvent.VK_Q) {
					f.dispose();
					System.exit(0);
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					c.runGame();
				}
				else if (e.getKeyCode() == KeyEvent.VK_N) {
					c.restart();
				}
			}
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					//if (c.getStartGame())
						c.setMoveLeft(false);
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					//if (c.getStartGame())
						c.setMoveRight(false);
				}
			}
		});

		f.setFocusable(true);

		f.repaint();
	}

	private void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("\n"))
			g.drawString(line,  x,  y += g.getFontMetrics().getHeight());
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		c.draw(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.PLAIN, (int)(f.getSize().getWidth()*0.011)));
		g.drawString("FPS: " + c.getFPS(), (int)(f.getSize().getWidth()*0.015), (int)(f.getSize().getWidth()*0.02));
		g.drawString("Lives: " + c.getLives(), (int)(f.getSize().getWidth()*0.015), (int)(f.getSize().getWidth()*0.0355));
		g.drawString("Score: " + c.getScore(), (int)(f.getSize().getWidth()*0.015), (int)(f.getSize().getWidth()*0.05));
		g.setFont(new Font("Arial", Font.PLAIN, (int)(f.getSize().getWidth()*0.017)));
		drawString(g, c.getMessage(), (int)(f.getSize().getWidth()*0.4), (int)(f.getSize().getWidth()*0.0125));
	}

	public void update() {
		f.repaint();
	}

	//create the splash screen window
	public void splashScreen(Dimension dim) {
		Dimension newSize = new Dimension((int)(dim.getWidth()*0.9), (int)(dim.getHeight()*0.9));
		f.setSize(newSize);

		//===================== create panel objects =============================
		JPanel mainpanel = new JPanel();
		mainpanel.setLayout(new FlowLayout());

		//===================== create button panel =============================
		JButton startbtn = new JButton(">  Normal Mode");
		startbtn.setForeground(Color.YELLOW);
		setButtonProperties(startbtn, newSize);
		JButton featurebtn = new JButton(" >  Feature Mode");
		featurebtn.setForeground(Color.MAGENTA);
		setButtonProperties(featurebtn, newSize);
		JButton quitbtn = new JButton(">  Quit Game  ");
		quitbtn.setForeground(Color.GREEN);
		setButtonProperties(quitbtn, newSize);

		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(3, 1));
		btnPanel.add(startbtn);
		btnPanel.add(featurebtn);
		btnPanel.add(quitbtn);

		// ================== create label panel =====================
		Dimension tempSize1 = new Dimension((int)(newSize.getWidth()), (int)(newSize.getHeight()*0.25));
		JPanel lblPanel = new JPanel();
		JLabel instructions = new JLabel("<html><center>How to play:<br>Catch the ball with the paddle to continue bouncing the ball.<br>Clear all blocks to win the game. You have 3 lives in total.<br><br>Use &larr & &rarr arrow keys to move paddle.<br>Use Space bar to release the ball from the paddle.<br>Press 'q' to exit any time during the game.</center></html>", SwingConstants.CENTER);
		instructions.setFont(new Font("Courier New", Font.PLAIN, (int)(newSize.getHeight()*0.02)));
		instructions.setForeground(Color.WHITE);
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setPreferredSize(tempSize1);

		lblPanel.setBackground(Color.BLACK);
		lblPanel.add(instructions);

		// ================= create name panel =============================
		JPanel namePanel = new JPanel();
		JLabel name = new JLabel("BREAKOUT");
		name.setFont(new Font("Bauhaus 93", Font.BOLD, (int)(newSize.getHeight()*0.27)));
		name.setForeground(Color.WHITE);
		namePanel.setBackground(Color.BLACK);
		namePanel.add(name);

		// ================= create information panel ===========================
		Dimension tempSize2 = new Dimension((int)(newSize.getWidth()), (int)(newSize.getHeight()*0.005));
		JPanel infoPanel = new JPanel(new GridLayout(3, 1));
		JLabel myName = new JLabel("Name: Yunrong Guo");
		myName.setFont(new Font("Courier New", Font.PLAIN, (int)(newSize.getHeight()*0.02)));
		myName.setForeground(Color.WHITE);
		myName.setPreferredSize(tempSize2);
		myName.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(myName);
		JLabel id = new JLabel("********");
		id.setFont(new Font("Courier New", Font.PLAIN, (int)(newSize.getHeight()*0.02)));
		id.setForeground(Color.WHITE);
		id.setHorizontalAlignment(JLabel.CENTER);
		infoPanel.add(id);
		//infoPanel.add(new JLabel(" "));
		infoPanel.setBackground(Color.BLACK);

		mainpanel.setBackground(Color.BLACK);
		mainpanel.add(namePanel);
		mainpanel.add(infoPanel, BorderLayout.CENTER);
		mainpanel.add(lblPanel);
		mainpanel.add(btnPanel);

		f.setBackground(Color.BLACK);
		f.getContentPane().add(mainpanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//================== add listeners ===============
		startbtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				startbtn.setForeground(Color.CYAN);
			}
			public void mouseExited(MouseEvent e) {
				startbtn.setForeground(Color.YELLOW);
			}
			public void mouseClicked(MouseEvent e) {
				startbtn.setForeground(Color.WHITE);
				f.getContentPane().removeAll();
				f.repaint();
				setGame(false, newSize);
			}
		});
		quitbtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				quitbtn.setForeground(Color.CYAN);
			}
			public void mouseExited(MouseEvent e) {
				quitbtn.setForeground(Color.GREEN);
			}
			public void mouseClicked(MouseEvent e) {
				quitbtn.setForeground(Color.WHITE);
				f.dispose();
				System.exit(0);
			}
		});
		featurebtn.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				featurebtn.setForeground(Color.CYAN);
			}
			public void mouseExited(MouseEvent e) {
				featurebtn.setForeground(Color.MAGENTA);
			}
			public void mouseClicked(MouseEvent e) {
				featurebtn.setForeground(Color.WHITE);
				f.getContentPane().removeAll();
				f.repaint();
				setGame(true, newSize);
			}
		});

		//=============== add keyboard listener ============
		KeyAdapter listener = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_Q) {
					f.dispose();
					System.exit(0);
				}
			}
		};

		f.addKeyListener(listener);
		f.setFocusable(true);

		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setVisible(true);	

	}

	public void setButtonProperties(JButton btn, Dimension size) {
		btn.setBorderPainted(false);
		btn.setBackground(Color.BLACK);
		btn.setFont(new Font("Courier New", Font.BOLD, (int)(size.getHeight()*0.045)));
		btn.setFocusPainted(false);
	}

}
