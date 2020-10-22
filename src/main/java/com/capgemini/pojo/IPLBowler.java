package com.capgemini.pojo;

import com.opencsv.bean.CsvBindByName;

public class IPLBowler {
	@CsvBindByName(column = "POS", required = true)
	public int pos;

	@CsvBindByName(column = "PLAYER", required = true)
	public String playerName;

	@CsvBindByName(column = "Mat", required = true)
	public int matches;

	@CsvBindByName(column = "Inns", required = true)
	public int innings;

	@CsvBindByName(column = "OV", required = true)
	public double overs;

	@CsvBindByName(column = "Runs", required = true)
	public int runs;

	@CsvBindByName(column = "Wkts", required = true)
	public int wickets;

	@CsvBindByName(column = "Avg", required = true)
	public String average;

	@CsvBindByName(column = "BBI", required = true)
	public String bbi;

	@CsvBindByName(column = "Econ", required = true)
	public double economy;

	@CsvBindByName(column = "SR", required = true)
	public String sr;

	@CsvBindByName(column = "4w", required = true)
	public int fourWickets;

	@CsvBindByName(column = "5w", required = true)
	public int fiveWickets;

	public IPLBowler() {
	}

	@Override
	public String toString() {
		return "Bowler [pos=" + pos + ", playerName=" + playerName + ", matches=" + matches + ", innings=" + innings
				+ ", overs=" + overs + ", runs=" + runs + ", wickets=" + wickets + ", average=" + average + ", bbi="
				+ bbi + ", economy=" + economy + ", sr=" + sr + ", fourWickets=" + fourWickets + ", fiveWickets="
				+ fiveWickets + "]";
	}

	public double getAverage() {
		if (average.equals("-")) {
			return 1000;
		} else
			return Double.parseDouble(average);
	}

	public double getStrikeRate() {
		if (sr.equals("-")) {
			return 100;
		} else
			return Double.parseDouble(sr);
	}

}
