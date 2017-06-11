package com.esgi.scoregame.pages;

import java.io.IOException;

import ej.microui.display.Colors;
import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.style.Element;
import ej.style.State;

public class GameWidget extends Widget implements Element {
	
	private Image player;
	private Image[] pokeball = new Image[4];

	public GameWidget() {
		super();
	
		loadAssets();
		// TODO Auto-generated constructor stub
		
	}
	
	public void loadAssets() {
		try {
			player = Image.createImage("/images/pikachu.png");
			pokeball[0] = Image.createImage("/images/pokeball.png");
			pokeball[1] = Image.createImage("/images/superball.png");
			pokeball[2] = Image.createImage("/images/hyperball.png");
			pokeball[3] = Image.createImage("/images/masterball.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.setColor(Colors.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
		//g.drawImage(player, 0, 0, GraphicsContext.TOP | GraphicsContext.LEFT);
		
		for (int i = 0; i < pokeball.length; i++)
			drawImage(g, pokeball[i], 30 * i, 0);
		
		drawImageToCenter(g, player);
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
		g.drawImage(image, x, y, GraphicsContext.TOP | GraphicsContext.LEFT);
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
