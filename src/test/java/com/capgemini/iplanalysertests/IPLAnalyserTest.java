package com.capgemini.iplanalysertests;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.analyser.IplAnalyser;
import com.capgemini.exceptions.IPLAnalyserException;
import com.capgemini.pojo.IPLAllrounder;
import com.capgemini.pojo.IPLBatsman;
import com.capgemini.pojo.IPLBowler;

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
		try {
			List<IPLBatsman> list = iplAnalyser.loadCSVBattingData(IPL_BATTING_DATA, IPLBatsman.class);
			Assert.assertEquals(100, list.size());
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenABowlingCSVFile_WhenLoaded_ReturnsTheNumberOfEntries() {
		try {
			List<IPLBowler> list = iplAnalyser.loadCSVBowlingData(IPL_BOWLING_DATA, IPLBowler.class);
			Assert.assertEquals(99, list.size());
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesTopAverageBatsmen() {
		try {
			List<IPLBatsman> list = iplAnalyser.getTopAverageBatsmen(IPL_BATTING_DATA, 5);
			Assert.assertEquals("MS Dhoni", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesTopStrikeRatedBatsmen() {
		try {
			List<IPLBatsman> list = iplAnalyser.getTopStrikeRatedBatsmen(IPL_BATTING_DATA, 5);
			Assert.assertEquals("Ishant Sharma", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHigestNumberOfSixHitterBatsman() {
		try {
			List<IPLBatsman> list = iplAnalyser.getTopSixHitterBatsmen(IPL_BATTING_DATA, 5);
			Assert.assertEquals("Andre Russell", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHigestNumberOfFourHitterBatsman() {
		try {
			List<IPLBatsman> list = iplAnalyser.getTopFourHitterBatsmen(IPL_BATTING_DATA, 5);
			Assert.assertEquals("Shikhar Dhawan", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHigestStrikeRateWith6sand4sBatsmen() {
		try {
			List<IPLBatsman> list = iplAnalyser.getHigestStrikeRateWith6snad4sBatsmen(IPL_BATTING_DATA, 5);
			Assert.assertEquals("Andre Russell", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHighestAverageWithStrikeRate() {
		try {
			List<IPLBatsman> list = iplAnalyser.getBestAverageWithStrikeRate(IPL_BATTING_DATA, 5);
			Assert.assertEquals("Andre Russell", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesHighestRunsWithAverage() {
		try {
			List<IPLBatsman> list = iplAnalyser.getHighestRunsWithAverage(IPL_BATTING_DATA, 5);
			Assert.assertEquals("David Warner", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBowlingCSVFile_WhenLoaded_GivesBestAverageBowlers() {
		try {
			List<IPLBowler> list = iplAnalyser.getBestBowlingAveragePlayers(IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Anukul Roy", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBowlingCSVFile_WhenLoaded_GivesBestStrikeRateBowlers() {
		try {
			List<IPLBowler> list = iplAnalyser.getBestBowlingStrikeRatePlayers(IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Alzarri Joseph", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBowlingCSVFile_WhenLoaded_GivesBestEconomyRateBowlers() {
		try {
			List<IPLBowler> list = iplAnalyser.getBestBowlingEconomyRatePlayers(IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Shivam Dube", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBowlingCSVFile_WhenLoaded_GivesBestStrikeRatesWith4wand5ws() {
		try {
			List<IPLBowler> list = iplAnalyser.getBestStrikeRateWith4wAnd5w(IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Kagiso Rabada", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBowlingCSVFile_WhenLoaded_GivesBestBolwingAveragesWithBestBowlingStrikeRatePlayers() {
		try {
			List<IPLBowler> list = iplAnalyser.getBestAverageWithStrikeRateBowlers(IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Alzarri Joseph", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBowlingCSVFile_WhenLoaded_GivesBestWicketsWithAveragesPlayers() {
		try {
			List<IPLBowler> list = iplAnalyser.getBestWicketsWithBestAverageBowlers(IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Imran Tahir", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBothCSVFile_WhenLoaded_GivesAllRoundBattingAndBowlingAveragePlayers() {
		try {
			List<IPLAllrounder> list = iplAnalyser.getBestBattingAndBowlingAveragePlayers(IPL_BATTING_DATA,
					IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Andre Russell", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBothCSVFile_WhenLoaded_GivesMostRunAndMostWicketTakenByAnyPlayer() {
		try {
			List<IPLAllrounder> list = iplAnalyser.getMostRunsAndWicketGettingPlayers(IPL_BATTING_DATA,
					IPL_BOWLING_DATA, 5);
			Assert.assertEquals("Hardik Pandya", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesMostHundredWithGoodAverageBatsman() {
		try {
			List<IPLBatsman> list = iplAnalyser.getHighestHundredsWithAverage(IPL_BATTING_DATA, 5);
			Assert.assertEquals("David Warner", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

	@Test
	public void GivenBattingCSVFile_WhenLoaded_GivesZeroHundredOrFiftiesWithBestAverageBatsman() {
		try {
			List<IPLBatsman> list = iplAnalyser.getZeroHundredsOrFiftiesWithGoodAverageBatsmen(IPL_BATTING_DATA, 5);
			Assert.assertEquals("Marcus Stoinis", list.get(0).playerName);
		} catch (IPLAnalyserException e) {
			fail();
			e.printStackTrace();
		}
	}

}
