package com.esgi.scoregame.pages;

import ej.microui.display.GraphicsContext;
import ej.style.Stylesheet;
import ej.style.selector.TypeSelector;
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
		
		mButtonBack.addOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick() {
				System.out.println("Back to Main Menu");
			}
		});
		
		split.setFirst(widget);
		split.setLast(mButtonBack);
		setWidget(split);
		
		applyStylesheet();
	}
	
	private void applyStylesheet() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();
		
		// Button
		EditableStyle buttonStyle = new EditableStyle();
		buttonStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		buttonStyle.setForegroundColor(0x6da05e);
		buttonStyle.setBackgroundColor(0x68bcf7);
		stylesheet.addRule(new TypeSelector(Button.class), buttonStyle);
		stylesheet.addRule(new TypeSelector(Label.class), buttonStyle);
	}
}