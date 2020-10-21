package com.capgemini.iplanalysertests;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.analyser.IplAnalyser;
import com.capgemini.exceptions.IPLAnalyserException;
import com.capgemini.pojo.BattingDataCSV;
import com.capgemini.pojo.BowlingDataCSV;

public class IPLAnalyserTest {
	IplAnalyser iplAnalyser;

	private static final String IPL_BATTING_DATA = "./WP DP Data_01 IPL2019FactsheetMostRuns - WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String IPL_BOWLING_DATA = "./WP DP Data_02 IPL2019FactsheetMostWkts - WP DP Data_02 IPL2019FactsheetMostWkts.csv";

	@Before
	public void init() {
		iplAnalyser = new IplAnalyser();
	}

	@Test
	public void GivenABattingCSVFile_WhenLoaded_ReturnsTheNumberOfEntries() {
		int noOfEntries;
		try {
			noOfEntries = iplAnalyser.loadCSVBattingData(IPL_BATTING_DATA, BattingDataCSV.class);
			Assert.assertEquals(100, noOfEntries);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenABowlingCSVFile_WhenLoaded_ReturnsTheNumberOfEntries() {
		int noOfEntries;
		try {
			noOfEntries = iplAnalyser.loadCSVBowlingData(IPL_BOWLING_DATA, BowlingDataCSV.class);
			Assert.assertEquals(99, noOfEntries);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesTopAverageBatsmen() {
		try {
			List<BattingDataCSV> list = iplAnalyser.getTopAverageBatsmen(IPL_BATTING_DATA, 5);
			for (BattingDataCSV p : list) {
				System.out.println(p.playerName);
			}
			Assert.assertEquals("MS Dhoni", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesTopStrikeRatedBatsmen() {
		try {
			List<BattingDataCSV> list = iplAnalyser.getTopStrikeRatedBatsmen(IPL_BATTING_DATA, 5);
			for (BattingDataCSV p : list) {
				System.out.println(p.playerName);
			}
			Assert.assertEquals("Ishant Sharma", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHigestNumberOfSixHitterBatsman() {
		try {
			List<BattingDataCSV> list = iplAnalyser.getTopSixHitterBatsmen(IPL_BATTING_DATA, 5);
			for (BattingDataCSV p : list) {
				System.out.println(p.playerName);
			}
			Assert.assertEquals("Andre Russell", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHigestNumberOfFourHitterBatsman() {
		try {
			List<BattingDataCSV> list = iplAnalyser.getTopFourHitterBatsmen(IPL_BATTING_DATA, 5);
			for (BattingDataCSV p : list) {
				System.out.println(p.playerName);
			}
			Assert.assertEquals("Shikhar Dhawan", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHigestStrikeRateWith6sand4sBatsmen() {
		try {
			List<BattingDataCSV> list = iplAnalyser.getHigestStrikeRateWith6snad4sBatsmen(IPL_BATTING_DATA, 5);
			for (BattingDataCSV p : list) {
				System.out.println(p.playerName);
			}
			Assert.assertEquals("Andre Russell", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

}
