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
import ej.microui.event.Event;
import ej.microui.event.generator.Pointer;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.style.Element;
import ej.style.State;

public class GameWidget extends Widget implements Element, EventHandler {
	
	// Assets
	private Image wallpaperImage;
	private Image playerImage;
	private Image heartImage;
	private Image[] pokeballImages = new Image[5];
	
	// Attributs du jeu
	int backgroundPosition = 0;
	int lastPressed = 0;
	int lastReleased = 0;
	int diff = 0;
	boolean direction = false;
	
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
			heartImage = Image.createImage("/images/heart.png");
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
		//g.drawRect(player.getX() + 13, player.getY() - 35, 45, 70); // HITBOX DEBUG
		
		// Hearts position
		for (int i = 0; i < player.getLives(); i++)
			drawImage(g, heartImage, i*15 + 5, 5);
		
		// Balls position
		for (Ball ball : balls) {
			drawImage(g, pokeballImages[ball.getBallType()], ball.getX(), ball.getY());
		}
	}
	
	public void animate(){
		TimerTask animator = new AnimatorTask(this, player);
		Timer animationTimer = new Timer();
		animationTimer.schedule(animator, 1, 1000/30);
	}
	
	private class AnimatorTask extends TimerTask {

		private final GameWidget widget;
		private final Player player;
		
		private int hMove = 6;
		private int count = 0;
		
		private Random random;
		
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
			
			// Player swipe effect
			if (widget.diff > 0) {
				if (direction)
					player.setY(player.getY() + 2);
				else
					player.setY(player.getY() - 2);
				widget.diff -= 2;
			}
			
			
			Iterator<Ball> iterator = widget.balls.iterator();
			
			while(iterator.hasNext()) {
				Ball ball = iterator.next();
				
				// Move objects toward player
				int x = ball.getX();
				int y = ball.getY();
				ball.setX(x - hMove);
				
				/*
				 * Check for collision
				 * Player Image size is 70x70px, with transparent areas to the sides.
				 * 1. 12px-wide transparent area to the right means that we need to catch right collisions within 58px (70-12) range from the anchor point (to its right).
				 * 2. 13px-wide transparent area to the left means that we need to catch left collisions within 13px range from the anchor point (to its right). 
				 * 3. The anchor point is vertically centered so we need to catch top and bottom collisions within 35px (70/2) range from the anchor point (both sides).
				 */
				if ((x <= player.getX() + 58) && (y <= player.getY() + 35) && (x + 24 >= player.getX() + 13) && (y + 24 >= player.getY() - 35)) {
					int lives = player.getLives();
					
					System.out.print("COLLISION ");
					
					if (ball.getBallType() == 4) {
						if (lives < 3)
							player.setLives(++lives);
						System.out.println("POTION");
					} else {
						player.setLives(--lives);
						System.out.println("BALL");
					}
					
					iterator.remove();
					continue;
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
			if (player.getLives() <= 0) {
				this.cancel();
				System.out.println("GAME OVER");
			}
			
			widget.repaint();
		}		
	}
	
	@Override
	public boolean handleEvent(int event) {

		if (Event.getType(event) == Event.POINTER) {
			Pointer pointer = (Pointer) Event.getGenerator(event);
			int x = pointer.getX();
			int y = pointer.getY();
			
			/*
			 * Player Image size is 70x70px, but has a displayed area of 45x70px.
			 * On device, finger is not as precise as pointer, so we make the swipe zone larger (89x140px).
			 * That means adding 22px on left and right, and 35px on top and bottom.
			 * 1. Anchor point is already at a 13px offset to the left of the displayed area, so we only need to add 9px.
			 * 2. Right collisions are caught at a 58px offset from anchor point, so we need an 80px (58+22) offset here.
			 * 3. Top and bottom collisions are both at 35px offset, so we need a 70px (35+35) offset on each side. 
			 */
			if ((player.getX() - 9 <= x) && (x <= player.getX() + 80) && (player.getY() - 70 <= y) && (y <= player.getY() + 70)) {
				if (Pointer.isPressed(event)) {
					lastPressed = player.getY();
					return true;
				}
				
				if (Pointer.isDragged(event)) {
					player.setY(y);
					return true;
				}
				
				if (Pointer.isReleased(event)) {
					lastReleased = player.getY();
					diff = Math.abs((int) ((lastReleased - lastPressed) * 0.30));
					System.out.println(diff);
					direction = lastPressed < lastReleased;
					return true;
				}
			}
		}
			
		return false;
	}
	
	public int getCenterX() {
		return getWidth() / 2;
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
