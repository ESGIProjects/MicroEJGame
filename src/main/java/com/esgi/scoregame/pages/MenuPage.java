package com.esgi.scoregame.pages;

import com.esgi.scoregame.MainActivity;

import ej.container.List;
import ej.container.Split;
import ej.navigation.page.Page;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.listener.OnClickListener;

public class MenuPage extends Page {
	
	private Split container;
	
	public MenuPage(){
		
		container = new Split(false,0.3f);
		Label title = new Label("ScoreGame");
		
		List buttonList = new List(false);
		
		Button play = new Button("Play");
		Button exit = new Button("Exit");
		
		buttonList.add(play);
		buttonList.add(exit);
		
		container.setFirst(title);
		container.setLast(buttonList);
		
		setWidget(container);
		
		play.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				//MainActivity.display(new GamePage());
			}
		});
		
		exit.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				// TODO
			}
		});
	}

}
