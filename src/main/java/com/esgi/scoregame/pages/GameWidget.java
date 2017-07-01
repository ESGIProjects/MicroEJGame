package com.esgi.scoregame.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.esgi.scoregame.models.Ball;
import com.esgi.scoregame.models.Player;

import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.mwt.Widget;
import ej.style.Element;
import ej.style.State;

public class GameWidget extends Widget implements Element {
	
	// Assets
	private Image wallpaperImage;
	private Image playerImage;
	private Image[] pokeballImages = new Image[5];
	
	// Attributs du jeu
	int backgroundPosition = 0;
	private Date startDate;
	private Player player;
	private List<Ball> balls;

	public GameWidget() {
		super();
		
		loadAssets();
		
		startDate = new Date();	
		player = new Player(20, 122);
		balls = new ArrayList<Ball>();
				
		animate();		
	}
	
	public void loadAssets() {
		try {
			wallpaperImage = Image.createImage("/images/wallpaper.png");
			playerImage = Image.createImage("/images/pikachu.png");
			pokeballImages[0] = Image.createImage("/images/pokeball.png");
			pokeballImages[1] = Image.createImage("/images/superball.png");
			pokeballImages[2] = Image.createImage("/images/hyperball.png");
			pokeballImages[3] = Image.createImage("/images/masterball.png");
			pokeballImages[4] = Image.createImage("/images/potion.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GraphicsContext g) {
		// Wallpapers
		drawImage(g, wallpaperImage, -backgroundPosition, 0);
		drawImage(g, wallpaperImage, getWidth() - backgroundPosition, 0);
		
		// Player position
		drawImage(g, playerImage, player.getX(), player.getY(), GraphicsContext.VCENTER | GraphicsContext.LEFT);
		
		// Balls position
		for (Ball ball : balls) {
			drawImage(g, pokeballImages[ball.getBallType()], ball.getX(), ball.getY());
		}
	}
	
	public void animate(){
		TimerTask animator = new AnimatorTask(this, player);
		Timer animationTimer = new Timer();
		animationTimer.schedule(animator, 1000/30, 1000/30);
	}
	
	private class AnimatorTask extends TimerTask {

		private final int ABSOLUTE_INCREMENT = 6;
		private final GameWidget widget;
		private final Player player;
		
		private int count = 0;
		
		private Random random;
		
		private int hMove = ABSOLUTE_INCREMENT;
			
		public AnimatorTask(GameWidget widget, Player player) {
			this.widget = widget;
			this.player = player;
			random = new Random();
		}
		
		@Override
		public void run() {
			
			count++;
			
			// Animate background
			widget.backgroundPosition++;
			
			if (widget.backgroundPosition >= widget.getWidth()) {
				widget.backgroundPosition = 0;
			}
			
			Iterator<Ball> iterator = widget.balls.iterator();
			
			while(iterator.hasNext()) {
				Ball ball = iterator.next();
				
				// Move objects toward player
				int x = ball.getX();
				int y = ball.getY();
				ball.setX(x - hMove);
				
				// Check for collision
				if ((x <= player.getX() + 35) && (y <= player.getY() + 35) && (x + 24 >= player.getX() - 35) && (y + 24 >= player.getY() - 35)) {
					int lives = player.getLives();
					
					if (ball.getBallType() == 4 && lives < 3) {
						player.setLives(++lives);
					} else {
						player.setLives(--lives);
					}
					
					iterator.remove();
				}
				
				// Delete objects that are outside the screen
				if (x <= -24) {
					iterator.remove();
				}
			}
			
			// Create new balls every second
			if (count % 30 == 0) {
				int randomHeight = random.nextInt(10) * 24;
				int randomBall = random.nextInt(4);
				widget.balls.add(new Ball(getWidth(), randomHeight, randomBall));
			}
			
			// Create new potion between every 6 balls
			if (count % 200 == 0) {
				int randomHeight = random.nextInt(10) * 24;
				widget.balls.add(new Ball(getWidth(), randomHeight, 4));
			}
			
			// Stop game if no more lives
			System.out.println(player.getLives());
			if (player.getLives() <= 0) {
				this.cancel();
			}
			
			widget.repaint();
		}		
	}
	
	public int getCenterX() {
		return getWidth() / 2;
	}
	
	private int getCenterY() {
		return getHeight() / 2;
	}
	
	private void drawImage(GraphicsContext g, Image image, int x, int y) {
		drawImage(g, image, x, y, GraphicsContext.TOP | GraphicsContext.LEFT);
	}
	
	private void drawImage(GraphicsContext g, Image image, int x, int y, int anchor) {
		g.drawImage(image, x, y, anchor);
	}

	@Override
	public void validate(int widthHint, int heightHint) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean hasClassSelector(String classSelector) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isInState(State state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAttribute(String attribute) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getParentElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element[] getChildrenElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Element getChild(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getChildrenCount() {
		// TODO Auto-generated method stub
		return 0;
	}


}
