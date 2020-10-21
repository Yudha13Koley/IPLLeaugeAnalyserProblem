package com.capgemini.analyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.capgemini.factory.CSVBuilderFactory;
import com.capgemini.interfaces.ICSVBuilder;
import com.capgemini.pojo.BattingDataCSV;

public class IplAnalyser {

	public int loadCSVData(String filePath) {
		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.getCSVBuilder();
			return 0;
					//csvBuilder.getIteratorForCSVFile(reader, BattingDataCSV.class)
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
	}

	

}
