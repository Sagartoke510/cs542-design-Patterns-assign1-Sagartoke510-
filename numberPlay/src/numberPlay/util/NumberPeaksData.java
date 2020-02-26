package numberPlay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {
	private static List<Double>peakValue = new ArrayList<Double>();
	private FileWriter outputFileWriter;
	private String numberPeakOutputFile;
	private File outputFile;
	
	public NumberPeaksData(String numberPeaksOutputFileIn) {
		numberPeakOutputFile = numberPeaksOutputFileIn;
	}

	public NumberPeaksData() {}

	@Override
	public void store(Double d) {
		peakValue.add(d);
	}

	@Override
	public void writeToFile() {
		try {
			if(!Files.exists(Paths.get(numberPeakOutputFile))) {
				outputFile = new File(numberPeakOutputFile);
				outputFile.createNewFile();
				outputFileWriter = new FileWriter(numberPeakOutputFile);
				for(Double n:peakValue) {
					outputFileWriter.write(String.valueOf(n)+"\n");
				}
			}else {
			outputFileWriter = new FileWriter(numberPeakOutputFile);
			for(Double n : peakValue) {
				outputFileWriter.write(String.valueOf(n)+ "\n");
			}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void close() {
		
		try {
			outputFileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
