package numberPlay.observer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import numberPlay.util.EventTrigger;
import numberPlay.util.NumberPeaksResultsI;
import numberPlay.util.PersisterI;

/**
 * 
 * @author Sagar Toke This class {@code NumberPeakObserver} is an observer which
 *         implements Observer Interface and calculated peaks in the input
 *         stream
 */
public class NumberPeaksObserver implements ObserverI {

	private NumberPeaksResultsI numberPeakResults;
	private List<Number> numbers = new ArrayList<Number>();
	private double peakValue = 0.0;
	private PersisterI persisterResult;

	public NumberPeaksObserver(NumberPeaksResultsI numberPeakResultsIn, PersisterI persisterIn) {
		persisterResult = persisterIn;
		numberPeakResults = numberPeakResultsIn;
	}

	@Override
	public void update(Number n, EventTrigger et) {
		if (!et.equals(EventTrigger.PROCESSING_COMPLETE)) {
			if (numbers.size() == 0) {
				numbers.add(n);
			} else {
				if (BigDecimal.valueOf(n.doubleValue())
						.compareTo(BigDecimal.valueOf(numbers.get(numbers.size() - 1).doubleValue())) < 0) {
					peakValue = numbers.get(numbers.size() - 1).doubleValue();
				}
				numbers.add(n);
			}
			if (peakValue != 0) {
				numberPeakResults.store(peakValue);
				peakValue = 0.0;
			}

		} else {
			persisterResult.writeToFile();
			persisterResult.close();
		}

	}

}
