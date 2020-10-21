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
import java.util.stream.Collectors;

import com.capgemini.exception.CSVBuilderException;
import com.capgemini.exceptions.IPLAnalyserException;
import com.capgemini.factory.CSVBuilderFactory;
import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.pojo.BattingDataCSV;
import com.capgemini.pojo.BowlingDataCSV;

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

	public List<BattingDataCSV> loadCSVBattingData(String filePath, Class<BattingDataCSV> classname)
			throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVData(filePath, classname);
		Map<Integer, BattingDataCSV> hashmap = new HashMap<>();
		for (BattingDataCSV player : list) {
			hashmap.put(player.pos, player);
		}
		List<BattingDataCSV> newlist = new LinkedList<>();
		for (Map.Entry<Integer, BattingDataCSV> entry : hashmap.entrySet()) {
			newlist.add(entry.getValue());
		}
		return newlist;
	}

	public List<BowlingDataCSV> loadCSVBowlingData(String filePath, Class<BowlingDataCSV> classname)
			throws IPLAnalyserException {
		List<BowlingDataCSV> list = loadCSVData(filePath, classname);
		Map<Integer, BowlingDataCSV> hashmap = new HashMap<>();
		for (BowlingDataCSV player : list) {
			hashmap.put(player.pos, player);
		}
		List<BowlingDataCSV> newlist = new LinkedList<>();
		for (Map.Entry<Integer, BowlingDataCSV> entry : hashmap.entrySet()) {
			newlist.add(entry.getValue());
		}
		return newlist;
	}

	public List<BattingDataCSV> getTopAverageBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(BattingDataCSV::getAverage).reversed();
		list = getSortedList(list, comparator);
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getTopStrikeRatedBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(player -> player.strikeRate);
		list = getSortedList(list, Collections.reverseOrder(comparator));
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	private <E> List<E> getSortedList(List<E> list, Comparator<E> comparator) {
		return list.stream().sorted(comparator).collect(Collectors.toList());
	}

	public List<BattingDataCSV> getTopSixHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(player -> player.sixes);
		list = getSortedList(list, Collections.reverseOrder(comparator));
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getTopFourHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(player -> player.fours);
		list = getSortedList(list, Collections.reverseOrder(comparator));
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}
	/*
	 * public List<BattingDataCSV> getHigestStrikeRateWith6snad4sBatsmen(String
	 * filePath, int topPlayers) throws IPLAnalyserException { List<BattingDataCSV>
	 * list = loadCSVBattingData(filePath, BattingDataCSV.class);
	 * Comparator<BattingDataCSV> sixandfourscomparator =
	 * Comparator.comparing(player -> player.sixes + player.fours);
	 * Comparator<BattingDataCSV> strikeratecomparator = Comparator.comparing(player
	 * -> player.strikeRate); Comparator<BattingDataCSV> comparator =
	 * strikeratecomparator.thenComparing(sixandfourscomparator).reversed(); list =
	 * getSortedList(list, comparator); List<BattingDataCSV> newList = new
	 * LinkedList<>(); for (int i = 0; i < topPlayers; i++) {
	 * newList.add(list.get(i)); } return newList; }
	 */

	public List<BattingDataCSV> getHigestStrikeRateWith6snad4sBatsmen(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(BattingDataCSV::getHittingValue).reversed();
		list = getSortedList(list, comparator);
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getBestAverageWithStrikeRate(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(p -> p.getAverage() * p.strikeRate);
		list = getSortedList(list, Collections.reverseOrder(comparator));
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getHighestRunsWithAverage(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVBattingData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> runcomparator = Comparator.comparing(p -> p.runs);
		Comparator<BattingDataCSV> comparator = runcomparator.thenComparing(Comparator.comparing(p -> p.getAverage()));
		list = getSortedList(list, Collections.reverseOrder(comparator));
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BowlingDataCSV> getBestBowlingAveragePlayers(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<BowlingDataCSV> list = loadCSVBowlingData(filePath, BowlingDataCSV.class);
		Comparator<BowlingDataCSV> comparator = Comparator.comparing(p -> p.getAverage());
		list = getSortedList(list, comparator);
		List<BowlingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

}
