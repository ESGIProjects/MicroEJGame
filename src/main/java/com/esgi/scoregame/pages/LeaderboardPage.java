package com.esgi.scoregame.pages;


import java.util.ArrayList;
import java.util.Date;

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
import ej.widget.container.Scroll;
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class LeaderboardPage extends Page {

	private Split container;
	private ArrayList<Integer> scores;

	public LeaderboardPage() {
		scores = MainActivity.getScores();
		layout();
	}

	private void layout() {
		container = new Split(false, 0.2f);

		// Title
		Label titleLabel = new Label("BEST SCORES");
		titleLabel.addClassSelector("BestScores.Title");
		
		// Scores
		Scroll listScroll = new Scroll(false, true);
		List scoresList = new List(false);
		
		for (Integer score : scores) {
			Label scoreLabel = new Label(score.toString());
			scoreLabel.addClassSelector("BestScores.Label");
			scoresList.add(scoreLabel);
		}
		
		// Back button
		Button backButton = new Button("Back");
		backButton.addClassSelector("BestScores.Button");
		scoresList.add(backButton);
		
		listScroll.setWidget(scoresList);
		
		// Container
		container.setFirst(titleLabel);
		container.setLast(listScroll);
		setWidget(container);

		// Listeners
		backButton.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				System.out.println("BEST SCORES BUTTON");		
				MainActivity.goBack();
			}
		});

		// Style
		applyStylesheet();
	}

	public void applyStylesheet() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();
		
		// 1. Best scores title
		EditableStyle titleStyle = new EditableStyle();
		titleStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		titleStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("BestScores.Title"), titleStyle);
		
		// 2. Score label
		EditableStyle scoreStyle = new EditableStyle();
		scoreStyle.setMargin(new SimpleOutline(5));
		scoreStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		stylesheet.addRule(new ClassSelector("BestScores.Label"), scoreStyle);
		
		// 3. Back button
		EditableStyle buttonStyle = new EditableStyle();
		buttonStyle.setMargin(new SimpleOutline(5));
		buttonStyle.setPadding(new SimpleOutline(10));
		buttonStyle.setBackground(new SimpleRoundedPlainBackground(25));
		buttonStyle.setBackgroundColor(Colors.CYAN);
		buttonStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		buttonStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("BestScores.Button"), scoreStyle);
		stylesheet.addRule(new DescendantCombinator(new ClassSelector("BestScores.Button"), new TypeSelector(Label.class)), buttonStyle);

	}

}
