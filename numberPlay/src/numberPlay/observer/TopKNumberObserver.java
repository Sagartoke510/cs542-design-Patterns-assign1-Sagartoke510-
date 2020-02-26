package numberPlay.observer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import numberPlay.util.EventTrigger;
import numberPlay.util.PersisterI;
import numberPlay.util.TopKNumbersResultsI;

/**
 * The class {@code TopKNumberObserver} is an observer which implements Observer interface and calculated top k value from the number stream
 * @author Sagar Toke
 *
 */
public class TopKNumberObserver implements ObserverI {

	private Integer k;
	private TopKNumbersResultsI topKResults;
	private PersisterI persistTopKResult;
	private static List<Double> numbers = new ArrayList<Double>();
	
	public TopKNumberObserver(String kIn, TopKNumbersResultsI topKResultsIn, PersisterI persistTopKResultIn) {
		
		k = Integer.parseInt(kIn);
		topKResults = topKResultsIn;
		persistTopKResult = persistTopKResultIn;
		
	}


	@Override
	public void update(Number n, EventTrigger et) {
		if(!et.equals(EventTrigger.PROCESSING_COMPLETE)) {
			if(numbers.size()==k) {
				numbers.remove(k-1);
				numbers.add(n.doubleValue());
				topKCalc(numbers);
			}else {
				numbers.add(n.doubleValue());
				topKCalc(numbers);
			}
			this.topKResults.store(numbers);
		}else {
			this.persistTopKResult.writeToFile();
			this.persistTopKResult.close();
		}
		
	}


	private void topKCalc(List<Double> numbers) {
		Collections.sort(numbers);
		Collections.reverse(numbers);
	}

}
