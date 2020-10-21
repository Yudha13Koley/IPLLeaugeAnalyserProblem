package com.capgemini.analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public int loadCSVBowlingData(String filePath, Class<BowlingDataCSV>classname) throws IPLAnalyserException {
		List<BowlingDataCSV> list = loadCSVData(filePath, classname);
		Map<Integer, BowlingDataCSV> hashmap = new HashMap<>();
		for (BowlingDataCSV player : list) {
			hashmap.put(player.pos, player);
		}
		return hashmap.size();
	}

}
