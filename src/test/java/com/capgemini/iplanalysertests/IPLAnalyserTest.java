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
	public void GivenBattingCSVFile_WhenLoaded_GivesTopAverageBatsmans() {
		try {
			List<BattingDataCSV> list=iplAnalyser.getTopAverageBatsmen(IPL_BATTING_DATA);
			for(BattingDataCSV p :list) {
				System.out.println(p);
			}
			Assert.assertEquals("Virat Kohli",list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

}
