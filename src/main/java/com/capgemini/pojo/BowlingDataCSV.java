package com.capgemini.pojo;

import com.opencsv.bean.CsvBindByName;

public class BowlingDataCSV {
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
	
	public BowlingDataCSV() {
	}

	@Override
	public String toString() {
		return "BowlingDataCSV [pos=" + pos + ", playerName=" + playerName + ", matches=" + matches + ", innings="
				+ innings + ", overs=" + overs + ", runs=" + runs + ", wickets=" + wickets + ", average=" + average
				+ ", bbi=" + bbi + ", economy=" + economy + ", sr=" + sr + ", fourWickets=" + fourWickets
				+ ", fiveWickets=" + fiveWickets + "]";
	}
	
}