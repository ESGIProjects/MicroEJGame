package com.esgi.scoregame;

import java.util.ArrayList;

import com.esgi.scoregame.pages.GamePage;
import com.esgi.scoregame.pages.MenuPage;
import com.esgi.scoregame.pages.ScorePage;

import ej.microui.MicroUI;
import ej.navigation.desktop.NavigationDesktop;
import ej.navigation.page.Page;
import ej.wadapps.app.Activity;

public class MainActivity implements Activity {
	
	private static NavigationDesktop nd;
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
		
		nd = new NavigationDesktop();
		scores = new ArrayList<Double>();
		
		//nd.show(new MenuPage());
		nd.show(new GamePage()); // Development purpose
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
	
	public static void display(Page p){
		nd.show(p);
	}
}
