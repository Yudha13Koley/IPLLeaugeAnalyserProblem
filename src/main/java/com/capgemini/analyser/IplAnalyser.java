package com.capgemini.analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.capgemini.exception.CSVBuilderException;
import com.capgemini.exceptions.IPLAnalyserException;
import com.capgemini.factory.CSVBuilderFactory;
import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.pojo.IPLAllrounder;
import com.capgemini.pojo.IPLBatsman;
import com.capgemini.pojo.IPLBowler;

public class IplAnalyser {

	private <E> List<E> loadCSVData(String filePath, Class<E> classname) throws IPLAnalyserException {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			List<E> list = csvBuilder.getListForCSVFile(reader, classname);
			return list;
		} catch (CSVBuilderException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.PARSE_EXCEPTION);
		} catch (NoSuchFileException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.WRONG_FILE);
		} catch (IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.IO_EXCEPTION);
		}
	}

	public List<IPLBatsman> loadCSVBattingData(String filePath, Class<IPLBatsman> classname)
			throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVData(filePath, classname);
		Map<Integer, IPLBatsman> hashmap = new HashMap<>();
		for (IPLBatsman player : list) {
			hashmap.put(player.pos, player);
		}
		List<IPLBatsman> newlist = new LinkedList<>();
		for (Map.Entry<Integer, IPLBatsman> entry : hashmap.entrySet()) {
			newlist.add(entry.getValue());
		}
		return newlist;
	}

	public List<IPLBowler> loadCSVBowlingData(String filePath, Class<IPLBowler> classname) throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVData(filePath, classname);
		Map<Integer, IPLBowler> hashmap = new HashMap<>();
		for (IPLBowler player : list) {
			hashmap.put(player.pos, player);
		}
		List<IPLBowler> newlist = new LinkedList<>();
		for (Map.Entry<Integer, IPLBowler> entry : hashmap.entrySet()) {
			newlist.add(entry.getValue());
		}
		return newlist;
	}

	public List<IPLBatsman> getTopAverageBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> comparator = Comparator.comparing(IPLBatsman::getAverage).reversed();
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLBatsman> getTopStrikeRatedBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> comparator = Comparator.comparing(player -> player.strikeRate);
		return getSortedList(list, Collections.reverseOrder(comparator), topPlayers);
	}

	public List<IPLBatsman> getTopSixHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> comparator = Comparator.comparing(player -> player.sixes);
		return getSortedList(list, Collections.reverseOrder(comparator), topPlayers);
	}

	public List<IPLBatsman> getTopFourHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> comparator = Comparator.comparing(player -> player.fours);
		return getSortedList(list, Collections.reverseOrder(comparator), topPlayers);
	}

	public List<IPLBatsman> getHigestStrikeRateWith6snad4sBatsmen(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> comparator = Comparator
				.comparing(player -> player.strikeRate * (player.fours + player.sixes));
		return getSortedList(list, comparator.reversed(), topPlayers);
	}

	public List<IPLBatsman> getBestAverageWithStrikeRate(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> comparator = Comparator.comparing(p -> p.getAverage() * p.strikeRate);
		return getSortedList(list, Collections.reverseOrder(comparator), topPlayers);
	}

	public List<IPLBatsman> getHighestRunsWithAverage(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> runcomparator = Comparator.comparing(p -> p.runs);
		Comparator<IPLBatsman> comparator = runcomparator.thenComparing(Comparator.comparing(p -> p.getAverage()));
		return getSortedList(list, Collections.reverseOrder(comparator), topPlayers);
	}

	public List<IPLBowler> getBestBowlingAveragePlayers(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVBowlingData(filePath, IPLBowler.class);
		Comparator<IPLBowler> comparator = Comparator.comparing(p -> p.getAverage());
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLBowler> getBestBowlingStrikeRatePlayers(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVBowlingData(filePath, IPLBowler.class);
		Comparator<IPLBowler> comparator = Comparator.comparing(p -> p.getStrikeRate());
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLBowler> getBestBowlingEconomyRatePlayers(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVBowlingData(filePath, IPLBowler.class);
		Comparator<IPLBowler> comparator = Comparator.comparing(p -> p.economy);
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLBowler> getBestStrikeRateWith4wAnd5w(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVBowlingData(filePath, IPLBowler.class);
		Comparator<IPLBowler> strikeRateComparator = Comparator.comparing(p -> p.getStrikeRate());
		Comparator<IPLBowler> fourAndFiveWicketComparator = Comparator.comparing(p -> p.fourWickets + p.fiveWickets);
		Comparator<IPLBowler> comparator = fourAndFiveWicketComparator.reversed().thenComparing(strikeRateComparator);
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLBowler> getBestAverageWithStrikeRateBowlers(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVBowlingData(filePath, IPLBowler.class);
		Comparator<IPLBowler> comparator = Comparator.comparing(p -> p.getAverage() * p.getStrikeRate());
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLBowler> getBestWicketsWithBestAverageBowlers(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<IPLBowler> list = loadCSVBowlingData(filePath, IPLBowler.class);
		Comparator<IPLBowler> wicketsComparator = Comparator.comparing(p -> p.wickets);
		Comparator<IPLBowler> averageComparator = Comparator.comparing(p -> p.getAverage());
		Comparator<IPLBowler> comparator = wicketsComparator.reversed().thenComparing(averageComparator);
		return getSortedList(list, comparator, topPlayers);
	}

	public List<IPLAllrounder> getBestBattingAndBowlingAveragePlayers(String filePathBatting, String filePathBowling,
			int topPlayers) throws IPLAnalyserException {
		List<IPLAllrounder> allRouders = getAllRoundersList(filePathBatting, filePathBowling);
		Comparator<IPLAllrounder> comparator = Comparator.comparing(p -> p.battingAverage / p.bowlingAverage);
		return getSortedList(allRouders, comparator.reversed(), topPlayers);
	}

	public List<IPLAllrounder> getMostRunsAndWicketGettingPlayers(String filePathBatting, String filePathBowling,
			int topPlayers) throws IPLAnalyserException {
		List<IPLAllrounder> allRouders = getAllRoundersList(filePathBatting, filePathBowling);
		Comparator<IPLAllrounder> comparator = Comparator.comparing(player -> player.runs * player.wickets);
		return getSortedList(allRouders, comparator.reversed(), topPlayers);
	}

	public List<IPLBatsman> getHighestHundredsWithAverage(String filePath, int topPlayers) throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> hundredcomparator = Comparator.comparing(p -> p.hundreds);
		Comparator<IPLBatsman> comparator = hundredcomparator
				.thenComparing(Comparator.comparing(IPLBatsman::getAverage));
		return getSortedList(list, comparator.reversed(), topPlayers);
	}

	public List<IPLBatsman> getZeroHundredsOrFiftiesWithGoodAverageBatsmen(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<IPLBatsman> list = loadCSVBattingData(filePath, IPLBatsman.class);
		Comparator<IPLBatsman> avgComparator = Comparator.comparing(IPLBatsman::getAverage).reversed();
		Predicate<IPLBatsman> isZeroHundredOrFifty = player -> (player.fifties + player.hundreds == 0);
		list = list.stream().filter(isZeroHundredOrFifty).collect(Collectors.toList());
		return getSortedList(list, avgComparator, topPlayers);
	}

	private <E> List<E> getSortedList(List<E> list, Comparator<E> comparator, int topPlayers) {
		return list.stream().sorted(comparator).limit(topPlayers).collect(Collectors.toList());
	}

	private IPLBowler isAvailableInBowlers(String playerName, List<IPLBowler> bowlerList) {
		Predicate<IPLBowler> isAvailableInList = player -> player.playerName.equalsIgnoreCase(playerName);
		return bowlerList.stream().filter(isAvailableInList).findFirst().orElse(null);
	}

	private List<IPLAllrounder> getAllRoundersList(String filePathBatting, String filePathBowling)
			throws IPLAnalyserException {
		List<IPLBatsman> batsmenList = loadCSVBattingData(filePathBatting, IPLBatsman.class);
		List<IPLBowler> bowlerList = loadCSVBowlingData(filePathBowling, IPLBowler.class);
		List<IPLAllrounder> allRouders = new LinkedList<>();
		for (IPLBatsman player : batsmenList) {
			IPLBowler playerInBowlerList = isAvailableInBowlers(player.playerName, bowlerList);
			if (playerInBowlerList != null) {
				allRouders.add(new IPLAllrounder(player.playerName, player.runs, playerInBowlerList.wickets,
						player.getAverage(), playerInBowlerList.getAverage()));
			}
		}
		return allRouders;
	}

}
