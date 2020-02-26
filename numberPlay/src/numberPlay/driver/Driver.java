package numberPlay.driver;

import java.io.File;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;

import numberPlay.observer.NumberPeaksObserver;
import numberPlay.observer.ObserverI;
import numberPlay.observer.RunningAverageObserver;
import numberPlay.observer.TopKNumberObserver;
import numberPlay.observer.filter.EventTriggerFilter;
import numberPlay.observer.filter.FilterI;
import numberPlay.subject.NumberProcessor;
import numberPlay.subject.SubjectI;
import numberPlay.util.EventTrigger;
import numberPlay.util.FileProcessor;
import numberPlay.util.NumberPeaksData;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;
import numberPlay.util.RunningAverageData;
import numberPlay.util.RunningAverageResultsI;
import numberPlay.util.TopKNumbersData;
import numberPlay.util.TopKNumbersResultsI;
import numberPlay.validation.ValidatorFetcher;
import numberPlay.validation.ValidatorUtil;

/**
 * @author Sagar Toke 
 * 
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the argument value
		 * is not given java takes the default value specified in build.xml. To avoid
		 * that, below condition is used FIXME Refactor commandline validation using the
		 * validation design taught in class.
		 */
		final int REQUIRED_NUMBER_OF_ARGS = 6;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || (args[0].equals("${inputNumStream}"))
				|| (args[1].equals("${runAvgWindowSize}")) || (args[2].equals("${runAvgOutFile}"))
				|| (args[3].equals("${k}")) || (args[4].equals("${topKNumOutFile}"))
				|| (args[5].equals("${numPeaksOutFile}"))) {

			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.",
					REQUIRED_NUMBER_OF_ARGS);
			System.exit(0);
		}

		File file = new File(args[0]);
		String inputFilePath = file.getAbsolutePath();
		//System.out.println("path" + inputFilePath);

		try {
			ValidatorUtil.validate("Failed", ValidatorFetcher.existsFileValidator(inputFilePath),
					ValidatorFetcher.checkEmptyFileValidator(inputFilePath),
					ValidatorFetcher.lineContentValidator(inputFilePath), ValidatorFetcher.windowValidator(args[1]),
					ValidatorFetcher.topKNumberValidation(args[3]));
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// FIXME Create an instance of each of the classes implementing PersisterI and
		// the
		// corresponding ResultsI interface.
		// Observers use these objects to dump data to be stored and eventually
		// persisted
		// to the corresponding output file.

		// FIXME Instantiate the subject.
		try {
			SubjectI numberProcessor = new NumberProcessor();
			// FIXME Instantiate the observers, providing the necessary filter and the
			// results object.
			RunningAverageResultsI runningAverageResults = new RunningAverageData();
			TopKNumbersResultsI topKResults = new TopKNumbersData();
			NumberPeaksResultsI numberPeakResults = new NumberPeaksData();

			PersisterI persistRunningAverageResult = new RunningAverageData(args[2]);
			PersisterI persistTopKResult = new TopKNumbersData(args[4]);
			PersisterI persistNumberPeakResult = new NumberPeaksData(args[5]);

			ObserverI runningAverage = new RunningAverageObserver(args[1], runningAverageResults,
					persistRunningAverageResult);
			ObserverI topKNumbers = new TopKNumberObserver(args[3], topKResults, persistTopKResult);
			ObserverI numberPeak = new NumberPeaksObserver(numberPeakResults, persistNumberPeakResult);

			FilterI integerEventFilter = new EventTriggerFilter(EventTrigger.INTEGER_EVENT);
			FilterI floatingPointEventFilter = new EventTriggerFilter(EventTrigger.FLOATING_POINT_EVENT);
			FilterI processingCompleteFilter = new EventTriggerFilter(EventTrigger.PROCESSING_COMPLETE);

			List<FilterI> integerProcessingCompleteFilter = new ArrayList<FilterI>();
			integerProcessingCompleteFilter.add(integerEventFilter);
			integerProcessingCompleteFilter.add(processingCompleteFilter);

			List<FilterI> allEventTriggerFilter = new ArrayList<FilterI>();
			allEventTriggerFilter.add(integerEventFilter);
			allEventTriggerFilter.add(floatingPointEventFilter);
			allEventTriggerFilter.add(processingCompleteFilter);

			// FIXME Register each observer with the subject for the necessary set of
			// events.

			numberProcessor.register(runningAverage, integerProcessingCompleteFilter);
			numberProcessor.register(topKNumbers, allEventTriggerFilter);
			numberProcessor.register(numberPeak, allEventTriggerFilter);

			// FIXME Delegate control to a separate utility class/method that provides
			// numbers one at a time, from the FileProcessor,
			// to the subject.

			numberProcessor.process(inputFilePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
