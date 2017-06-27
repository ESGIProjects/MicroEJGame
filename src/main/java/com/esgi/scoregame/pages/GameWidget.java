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
	private Image[] pokeballImages = new Image[4];
	
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
		
		// Ball test
		
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
		TimerTask animator = new AnimatorTask(this);
		Timer animationTimer = new Timer();
		animationTimer.schedule(animator, 1000/30, 1000/30);
	}
	
	private class AnimatorTask extends TimerTask {

		private final int ABSOLUTE_INCREMENT = 6;
		private final GameWidget widget;
		
		private int left = 0;
		private int right;
		private int top = 0;
		private int bottom;
		
		private int count = 0;
		
		private Random random;
		
		private int hMove = ABSOLUTE_INCREMENT;
			
		public AnimatorTask(GameWidget widget) {
			this.widget = widget;
			right = widget.getWidth();
			bottom = widget.getHeight();
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
			
			/*for (Ball ball : widget.balls) {
				
			}*/
			
			Iterator<Ball> iterator = widget.balls.iterator();
			
			while(iterator.hasNext()) {
				Ball ball = iterator.next();
				
				// Move balls toward player
				int x = ball.getX();
				ball.setX(x - hMove);
				
				// Delete balls that are outside the screen
				if (x <= -24) {
					iterator.remove();
				}
			}
			
			// Create new balls
			if (count % 30 == 0) {
				int randomHeight = random.nextInt(10) * 24;
				int randomBall = random.nextInt(4);
				widget.balls.add(new Ball(getWidth(), randomHeight, randomBall));
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
	
	private void drawImageToCenter(GraphicsContext g, Image image) {
		g.drawImage(image, getCenterX(), getCenterY(), GraphicsContext.VCENTER | GraphicsContext.HCENTER);
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
