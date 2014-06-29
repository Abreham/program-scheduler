package com.thought.exercise.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thought.exercise.domain.Pair;

public class ReadFileUtil {
	public static final ReadFileUtil Instance = new ReadFileUtil();
	
	private ReadFileUtil(){
		
	}

	private static final String TIME_PATTERN = "([^\\w\\r\\n]+)((([0-9]+)(?:min))$|lightning$)";
	private static final Pattern pattern = Pattern.compile(TIME_PATTERN , Pattern.CASE_INSENSITIVE);
	private Matcher matcher;

	public  List<Pair<String,String>> readLines(String filename)
			throws IOException {
		FileReader fileReader = new FileReader(filename);

		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		List<Pair<String,String>> lines = new ArrayList<Pair<String,String>>();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			Pair<String, String> rawdata = parseRawData(line);
			if(rawdata != null){
				lines.add(rawdata);
			}	
		}

		bufferedReader.close();
		return lines;
	}

	private Pair<String, String> parseRawData(String rawData) {
		matcher = pattern.matcher(rawData);
		Pair<String, String> pair = null;
		if (matcher.find()) {
			if (matcher.group().trim().equals("lightning")) {
				pair = new Pair<String, String>(rawData.substring(0,matcher.start()), "5");
			}else{
				pair = new Pair<String, String>(rawData.substring(0,matcher.start()), matcher.group(4));
			}
		}
		return pair;
	}
}
