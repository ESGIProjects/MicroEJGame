package com.esgi.scoregame.models;

public class Player {
	private int x;
	private int y;
	private int momentum = 0;
	private int lives = 3;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Player(int x, int y, int lives) {
		this(x, y);
		this.lives = lives;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
	}
}
