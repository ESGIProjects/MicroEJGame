package com.esgi.scoregame.pages;


import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class ScorePage extends Page {

private Split container;
	
	public ScorePage(double score){
		
		container = new Split(false,0.3f);
		Label scoreLabel = new Label("Score : " + score);
		
		Split buttonList = new Split(true, 0.5f);
		
		Button replay = new Button("Replay");
		Button exit = new Button("Exit");
		
		buttonList.setFirst(replay);
		buttonList.setLast(exit);
		
		container.setFirst(scoreLabel);
		container.setLast(buttonList);
		
		setWidget(container);
		
		replay.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				//MainActivity.display(new MenuPage());
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
