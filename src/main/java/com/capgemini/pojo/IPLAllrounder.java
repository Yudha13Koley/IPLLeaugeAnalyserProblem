package com.capgemini.pojo;

public class IPLAllrounder {

	public String playerName;
	public int runs;
	public int wickets;
	public double battingAverage;
	public double bowlingAverage;

	public IPLAllrounder(String playerName, int runs, int wickets, double battingAverage, double bowlingAverage) {
		super();
		this.playerName = playerName;
		this.runs = runs;
		this.wickets = wickets;
		this.battingAverage = battingAverage;
		this.bowlingAverage = bowlingAverage;
	}

	@Override
	public String toString() {
		return "Allrounder [playerName=" + playerName + ", runs=" + runs + ", wickets=" + wickets + ", battingAverage="
				+ battingAverage + ", bowlingAverage=" + bowlingAverage + "]";
	}

}
