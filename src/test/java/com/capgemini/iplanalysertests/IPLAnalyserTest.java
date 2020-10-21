package com.capgemini.iplanalysertests;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.analyser.IplAnalyser;



public class IPLAnalyserTest {
	

	private static final String IPL_BATTING_DATA = "./WP DP Data_01 IPL2019FactsheetMostRuns - WP DP Data_01 IPL2019FactsheetMostRuns.csv";
	private static final String IPL_BOWLING_DATA = "./WP DP Data_02 IPL2019FactsheetMostWkts - WP DP Data_02 IPL2019FactsheetMostWkts.csv";

	@Test
	public void GivenABattingCSVFile_WhenLoaded_ReturnsTheNumberOfEntries() {
		IplAnalyser iplAnalyser=new IplAnalyser();
		int noOfEntries=iplAnalyser.loadCSVData(IPL_BATTING_DATA);
		Assert.assertEquals(0,noOfEntries);
	}
	
	@Test
	public void GivenABowlingCSVFile_WhenLoaded_ReturnsTheNumberOfEntries() {
		IplAnalyser iplAnalyser=new IplAnalyser();
		int noOfEntries=iplAnalyser.loadCSVData(IPL_BOWLING_DATA);
		Assert.assertEquals(0,noOfEntries);
	}

}
