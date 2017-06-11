package com.esgi.scoregame.pages;

import ej.container.Split;
import ej.navigation.page.Page;
import ej.widget.composed.Button;

public class GamePage extends Page {
	
	private Split split;
	private GameWidget widget;
	private Button mButtonBack;

	
	public GamePage() {
		split = new Split(false, 0.90f);
		widget = new GameWidget();
		mButtonBack = new Button("BACK");

		
		split.setFirst(widget);
		split.setLast(mButtonBack);
		setWidget(split);
	}
	
}
