package com.esgi.scoregame.pages;

import com.esgi.scoregame.MainActivity;

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
		title.addClassSelector("TITLE");
		
		// Buttons
		List buttonList = new List(false);
		Button play = new Button("Play");
		Button scores = new Button("Scores");
		Button exit = new Button("Exit");
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
			}
		});
		
		scores.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("SCORES BUTTON");
			}
		});
		
		exit.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("EXIT BUTTON");
			}
		});
		
		// Style
		applyStylesheet();
	}
	
	private void applyStylesheet() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();
		
		// 1. Title
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		titleStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("TITLE"), titleStyle);
		
		
		// 2. Buttons & Labels
		EditableStyle buttonStyle = new EditableStyle();
		SimpleOutline buttonMargin = new SimpleOutline(5);
		buttonStyle.setMargin(buttonMargin);
		
		SimpleRoundedPlainBackground buttonBackground = new SimpleRoundedPlainBackground(25);
		buttonStyle.setBackground(buttonBackground);
		
		buttonStyle.setBackgroundColor(Colors.GREEN);
		buttonStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		buttonStyle.setForegroundColor(0x68bcf7); // cyan
		
		stylesheet.addRule(new DescendantCombinator(new TypeSelector(Button.class), new TypeSelector(Label.class)), buttonStyle);
	}

}
