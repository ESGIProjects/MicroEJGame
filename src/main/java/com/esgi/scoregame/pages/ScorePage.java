package com.esgi.scoregame.pages;


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
import ej.widget.container.Split;
import ej.widget.listener.OnClickListener;
import ej.widget.navigation.page.Page;

public class ScorePage extends Page {

	private Split container;

	public ScorePage(double score) {
		Date date = new Date();
		layout(date, date);
	}

	private void layout(Date start, Date end) {
		container = new Split(false, 0.8f);

		// Score
		int score = (int) ((end.getTime() - start.getTime())/1000);

		// Labels
		List labelsList = new List(false);
		Label gameOverTitle = new Label("GAME OVER");
		gameOverTitle.addClassSelector("Score.Title");
		Label scoreSubtitle = new Label("Score : " + score);
		scoreSubtitle.addClassSelector("Score.Subtitle");
		labelsList.add(gameOverTitle);
		labelsList.add(scoreSubtitle);

		// Buttons
		List buttonsList = new List(true);
		Button leaderboardButton = new Button("Best scores");
		leaderboardButton.addClassSelector("Score.Button");
		Button replayButton = new Button("Replay");
		replayButton.addClassSelector("Score.Button");
		Button exitButton = new Button("Exit");
		exitButton.addClassSelector("Score.Button");
		buttonsList.add(leaderboardButton);
		buttonsList.add(replayButton);
		buttonsList.add(exitButton);

		// Container
		container.setFirst(labelsList);
		container.setLast(buttonsList);
		setWidget(container);

		// Listeners
		leaderboardButton.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				System.out.println("BEST SCORES BUTTON");
			}
		});

		replayButton.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				System.out.println("REPLAY BUTTON");
			}
		});

		exitButton.addOnClickListener(new OnClickListener() {

			@Override
			public void onClick() {
				System.out.println("EXIT BUTTON");
			}
		});

		// Style
		applyStylesheet();
	}

	public void applyStylesheet() {
		Stylesheet stylesheet = StyleHelper.getStylesheet();
		
		// 1. Game over title
		EditableStyle gameOverStyle = new EditableStyle();
		gameOverStyle.setAlignment(GraphicsContext.BOTTOM | GraphicsContext.HCENTER);
		gameOverStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("Score.Title"), gameOverStyle);
		
		// 2. Score subtitle
		EditableStyle scoreStyle = new EditableStyle();
		scoreStyle.setAlignment(GraphicsContext.TOP | GraphicsContext.HCENTER);
		scoreStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("Score.Subtitle"), scoreStyle);
		
		// 3. Buttons
		EditableStyle buttonStyle = new EditableStyle();
		buttonStyle.setMargin(new SimpleOutline(5));
		buttonStyle.setBackground(new SimpleRoundedPlainBackground(25));
		buttonStyle.setBackgroundColor(Colors.CYAN);
		buttonStyle.setAlignment(GraphicsContext.VCENTER | GraphicsContext.HCENTER);
		buttonStyle.setForegroundColor(Colors.GREEN);
		stylesheet.addRule(new ClassSelector("Score.Button"), buttonStyle);
		stylesheet.addRule(new DescendantCombinator(new ClassSelector("Score.Button"), new TypeSelector(Label.class)), buttonStyle);

	}

}
