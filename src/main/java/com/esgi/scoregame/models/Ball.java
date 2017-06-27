package com.esgi.scoregame.models;

public class Ball {
	private int x;
	private int y;
	private int ballType = 0;
	
	public Ball(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Ball(int x, int y, int ballType) {
		this(x, y);
		this.ballType = ballType;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getBallType() {
		return ballType;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setBallType(int ballType) {
		this.ballType = ballType;
	}
}
