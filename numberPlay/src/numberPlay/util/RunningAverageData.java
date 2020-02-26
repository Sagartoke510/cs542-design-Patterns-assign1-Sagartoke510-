package numberPlay.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import numberPlay.observer.RunningAverageObserver;

/**
 * The class {@code RunningAverageData} is used to store result generated by {@code RunningAverageObserver} and write tha result into output file.
 * @author Sagar Toke
 *
 */
public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	private String runningAverageOutputFile;
	private static List<String> runningAverage = new ArrayList<String>();
	private File outputFile;
	private FileWriter outputFileWriter;
	private DecimalFormat df = new DecimalFormat("#.##");

	public RunningAverageData() {
	}

	public RunningAverageData(String runningAverageOutputFileIn) {
		runningAverageOutputFile = runningAverageOutputFileIn;
	}

	@Override
	public void store(Double d) {
		runningAverage.add(df.format(d));

	}

	@Override
	public void writeToFile() {
		try {

			if (!Files.exists(Paths.get(runningAverageOutputFile))) {
				outputFile = new File(runningAverageOutputFile);
				outputFile.createNewFile();

				outputFileWriter = new FileWriter(runningAverageOutputFile);
				for (String n : runningAverage) {
					outputFileWriter.write(n +"\n");
				}
			} else {
				outputFileWriter = new FileWriter(runningAverageOutputFile);
				for (String n : runningAverage) {
					outputFileWriter.write(n + "\n");
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
