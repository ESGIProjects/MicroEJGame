package com.esgi.scoregame.pages;

import com.esgi.scoregame.MainActivity;

import ej.microui.display.GraphicsContext;
import ej.style.Stylesheet;
import ej.style.selector.ClassSelector;
import ej.style.selector.TypeSelector;
import ej.style.selector.combinator.DescendantCombinator;
import ej.style.util.EditableStyle;
import ej.style.util.StyleHelper;
import ej.widget.basic.Label;
import ej.widget.composed.Button;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class GamePage extends Page {
	
	private Split split;
	private GameWidget widget;
	private Button mButtonBack;

	
	public GamePage() {
		split = new Split(false, 0.90f);
		widget = new GameWidget();
		mButtonBack = new Button("Back to Main Menu");
		mButtonBack.addClassSelector("Game.Button");
		
		mButtonBack.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Back to Main Menu");
				MainActivity.goBack();
			}
		});
		
		split.setFirst(widget);
		split.setLast(mButtonBack);
		setWidget(split);
		
		applyStylesheet();
	}
	
	public void applyStylesheet() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();
		
		// Button
		EditableStyle buttonStyle = new EditableStyle();
		buttonStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		buttonStyle.setForegroundColor(0x6da05e);
		buttonStyle.setBackgroundColor(0x68bcf7);		
		stylesheet.addRule(new ClassSelector("Game.Button"), buttonStyle);
		stylesheet.addRule(new DescendantCombinator(new ClassSelector("Game.Button"), new TypeSelector(Label.class)), buttonStyle);
	}
}