package numberPlay.observer;

import java.util.LinkedList;
import java.util.Queue;

import numberPlay.util.EventTrigger;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageResultsI;

/**
 * This is observer class which implements an observer interface  and calculates running average on input stream based on the window provided
 * @author Sagar Toke
 *
 */
public class RunningAverageObserver implements ObserverI {

	private Integer windowSize;
	private RunningAverageResultsI runningAverageResults;
	private static Queue<Number> numbers = new LinkedList<Number>();
	private double runningAverage;
	private PersisterI persistRunningAverageResult;
	private double sum;

	public RunningAverageObserver(String windowSizeIn, RunningAverageResultsI runningAverageResultsIn,
			PersisterI persistRunningAverageResultIn) {
		windowSize = Integer.parseInt(windowSizeIn);
		runningAverageResults = runningAverageResultsIn;
		persistRunningAverageResult = persistRunningAverageResultIn;

	}

	@Override
	public void update(Number n, EventTrigger et) {
		if (!et.equals(EventTrigger.PROCESSING_COMPLETE)) {

			sum = sum + n.intValue();
			numbers.add(n);
			if (numbers.size() > windowSize) {

				sum = sum - numbers.remove().intValue();
			}
			runningAverage = sum / numbers.size();
			this.runningAverageResults.store(runningAverage);

		} else {
			this.persistRunningAverageResult.writeToFile();
			this.persistRunningAverageResult.close();
		}

	}

	
}
