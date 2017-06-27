package com.esgi.scoregame;

import java.util.ArrayList;

import com.esgi.scoregame.pages.GamePage;
import com.esgi.scoregame.pages.MenuPage;
import com.esgi.scoregame.pages.ScorePage;

import ej.microui.MicroUI;
import ej.mwt.Desktop;
import ej.mwt.Panel;
import ej.wadapps.app.Activity;
import ej.widget.navigation.navigator.HistorizedNavigator;
import ej.widget.navigation.transition.HorizontalTransitionManager;

public class MainActivity implements Activity {
	
	private static HistorizedNavigator navigator;
	private static ArrayList<Double> scores;

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRestart() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		MicroUI.start();
		
		navigator = new HistorizedNavigator();
		navigator.setTransitionManager(new HorizontalTransitionManager());
		
		scores = new ArrayList<Double>();
		
		//navigator.show(new MenuPage());
		navigator.show(new GamePage()); // Development purpose
		
		Desktop desktop = new Desktop();
		Panel mainPanel = new Panel();
		
		mainPanel.setWidget(navigator);
		mainPanel.show(desktop, true);
		
		desktop.show();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub

	}
	
	/*public static void display(Page p){
		nd.show(p);
	}*/
}
