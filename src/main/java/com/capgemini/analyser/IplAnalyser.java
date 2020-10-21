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

	public <E> List<E> loadCSVData(String filePath, Class<E> classname) throws IPLAnalyserException {
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

	public int loadCSVBattingData(String filePath, Class<BattingDataCSV> classname) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVData(filePath, classname);
		Map<Integer, BattingDataCSV> hashmap = new HashMap<>();
		for (BattingDataCSV player : list) {
			hashmap.put(player.pos, player);
		}
		return hashmap.size();
	}

	public int loadCSVBowlingData(String filePath, Class<BowlingDataCSV> classname) throws IPLAnalyserException {
		List<BowlingDataCSV> list = loadCSVData(filePath, classname);
		Map<Integer, BowlingDataCSV> hashmap = new HashMap<>();
		for (BowlingDataCSV player : list) {
			hashmap.put(player.pos, player);
		}
		return hashmap.size();
	}

	public List<BattingDataCSV> getTopAverageBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(BattingDataCSV::getAverage).reversed();
		list = getSortedList(list, comparator);
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getTopStrikeRatedBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(player -> player.strikeRate);
		comparator = Collections.reverseOrder(comparator);
		list = getSortedList(list, comparator);
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
		List<BattingDataCSV> list = loadCSVData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(player -> player.sixes);
		comparator = Collections.reverseOrder(comparator);
		list = getSortedList(list, comparator);
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getTopFourHitterBatsmen(String filePath, int topPlayers) throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(player -> player.fours);
		comparator = Collections.reverseOrder(comparator);
		list = getSortedList(list, comparator);
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

	public List<BattingDataCSV> getHigestStrikeRateWith6snad4sBatsmen(String filePath, int topPlayers)
			throws IPLAnalyserException {
		List<BattingDataCSV> list = loadCSVData(filePath, BattingDataCSV.class);
		Comparator<BattingDataCSV> comparator = Comparator.comparing(BattingDataCSV::getHittingValue).reversed();
		list = getSortedList(list, comparator);
		List<BattingDataCSV> newList = new LinkedList<>();
		for (int i = 0; i < topPlayers; i++) {
			newList.add(list.get(i));
		}
		return newList;
	}

}
