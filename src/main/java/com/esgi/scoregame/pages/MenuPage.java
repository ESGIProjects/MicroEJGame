package com.esgi.scoregame.pages;

import com.esgi.scoregame.MainActivity;

import ej.components.dependencyinjection.ServiceLoaderFactory;
import ej.exit.ExitHandler;
import ej.microui.display.Colors;
import ej.microui.display.GraphicsContext;
import ej.style.Stylesheet;
import ej.style.background.SimpleRoundedPlainBackground;
import ej.style.outline.SimpleOutline;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.DescendantCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.container.List;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class MenuPage extends Page {
	
	private Split container;
	
	public MenuPage() {
		container = new Split(false,0.3f);
	
		// Title
		Label title = new Label("ScoreGame");
		title.addClassSelector("Menu.Title");
		
		// Buttons
		List buttonList = new List(false);
		Button play = new Button("Play");
		play.addClassSelector("Menu.Button");
		Button scores = new Button("Scores");
		scores.addClassSelector("Menu.Button");
		Button exit = new Button("Exit");
		exit.addClassSelector("Menu.Button");
		buttonList.add(play);
		buttonList.add(scores);
		buttonList.add(exit);
		
		// Fill the container
		container.setFirst(title);
		container.setLast(buttonList);
		setWidget(container);
		
		// Listeners
		play.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("PLAY BUTTON");
				MainActivity.goTo(new GamePage());
			}
		});
		
		scores.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("SCORES BUTTON");
				MainActivity.goTo(new LeaderboardPage());
			}
		});
		
		exit.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("EXIT BUTTON");
				ExitHandler exitHandler = ServiceLoaderFactory.getServiceLoader().getService(ExitHandler.class);
				
				if (exitHandler != null) {
					exitHandler.exit();
				}
			}
		});
		
		// Style
		applyStylesheet();
	}
	
	public void applyStylesheet() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();
		
		// 1. Title
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		titleStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("Menu.Title"), titleStyle);
		
		
		// 2. Buttons & Labels
		EditableStyle buttonStyle = new EditableStyle();
		SimpleOutline buttonMargin = new SimpleOutline(5);
		buttonStyle.setMargin(buttonMargin);
		
		SimpleRoundedPlainBackground buttonBackground = new SimpleRoundedPlainBackground(25);
		buttonStyle.setBackground(buttonBackground);
		
		buttonStyle.setBackgroundColor(Colors.GREEN);
		buttonStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		buttonStyle.setForegroundColor(0x68bcf7); // cyan
		
		stylesheet.addRule(new ClassSelector("Menu.Button"), buttonStyle);
		stylesheet.addRule(new DescendantCombinator(new ClassSelector("Menu.Button"), new TypeSelector(Label.class)), buttonStyle);
	}

}
