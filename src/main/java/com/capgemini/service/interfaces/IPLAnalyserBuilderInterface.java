package com.capgemini.service.interfaces;

import java.util.List;

import com.capgemini.exceptions.IPLAnalyserException;
import com.capgemini.pojo.IPLAllrounder;
import com.capgemini.pojo.IPLBatsman;
import com.capgemini.pojo.IPLBowler;

public interface IPLAnalyserBuilderInterface {

	public List<IPLBatsman> loadCSVBattingData(String filePath, Class<IPLBatsman> classname)
			throws IPLAnalyserException;

	public List<IPLBowler> loadCSVBowlingData(String filePath, Class<IPLBowler> classname) throws IPLAnalyserException;

	public List<IPLBatsman> getTopAverageBatsmen(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getTopStrikeRatedBatsmen(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getTopSixHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getTopFourHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getHigestStrikeRateWith6snad4sBatsmen(String filePath, int topPlayers)
			throws IPLAnalyserException;

	public List<IPLBatsman> getBestAverageWithStrikeRate(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getHighestRunsWithAverage(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBowler> getBestBowlingAveragePlayers(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBowler> getBestBowlingStrikeRatePlayers(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBowler> getBestBowlingEconomyRatePlayers(String filePath, int topPlayers)
			throws IPLAnalyserException;

	public List<IPLBowler> getBestStrikeRateWith4wAnd5w(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBowler> getBestAverageWithStrikeRateBowlers(String filePath, int topPlayers)
			throws IPLAnalyserException;

	public List<IPLBowler> getBestWicketsWithBestAverageBowlers(String filePath, int topPlayers)
			throws IPLAnalyserException;

	public List<IPLAllrounder> getBestBattingAndBowlingAveragePlayers(String filePathBatting, String filePathBowling,
			int topPlayers) throws IPLAnalyserException;

	public List<IPLAllrounder> getMostRunsAndWicketGettingPlayers(String filePathBatting, String filePathBowling,
			int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getHighestHundredsWithAverage(String filePath, int topPlayers) throws IPLAnalyserException;

	public List<IPLBatsman> getZeroHundredsOrFiftiesWithGoodAverageBatsmen(String filePath, int topPlayers)
			throws IPLAnalyserException;

}
