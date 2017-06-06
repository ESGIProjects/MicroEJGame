package com.esgi.scoregame.pages;

import java.io.IOException;

import ej.microui.display.Display;
import ej.microui.display.Displayable;
import ej.microui.display.GraphicsContext;
import ej.microui.display.Image;
import ej.microui.util.EventHandler;
import ej.mwt.Widget;
import ej.style.Element;
import ej.style.State;

public class GameWidget extends Widget {
	
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void render(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.drawImage(player, 100, 100, 100);
	}

	@Override
	public void validate(int widthHint, int heightHint) {
		// TODO Auto-generated method stub
		
	}


}
