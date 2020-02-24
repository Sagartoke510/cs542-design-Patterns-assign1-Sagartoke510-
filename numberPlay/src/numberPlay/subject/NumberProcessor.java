package numberPlay.subject;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import numberPlay.observer.ObserverI;
import numberPlay.observer.filter.FilterI;
import numberPlay.util.EventTrigger;
import numberPlay.util.FileProcessor;

public class NumberProcessor implements SubjectI {
	Map<FilterI, List<ObserverI>> observers;
	
	

	public NumberProcessor() {
		observers = new HashMap<FilterI, List<ObserverI>>();
	}

	@Override
	public void process(String inputFilePath) {
		FileProcessor fp;
		
		try {
			
				fp = new FileProcessor(inputFilePath);
				while(fp.getLine()!=null) {
					Number n = 	NumberFormat.getInstance().parse(fp.poll());
					if(n instanceof Long)
						notifyAll(EventTrigger.INTEGER_EVENT, n);
					if (n instanceof Float || n instanceof Double)
						notifyAll(EventTrigger.FLOATING_POINT_EVENT,n);
					if (n.equals(null))
						notifyAll(EventTrigger.PROCESSING_COMPLETE, n);
				}	
				if(fp.getLine() == null)
					notifyAll(EventTrigger.PROCESSING_COMPLETE, null);
				fp.close();
			} catch (InvalidPathException | SecurityException | IOException | ParseException e) {
				e.printStackTrace();
		}
		
	}

	@Override
	public void register(ObserverI o, List<FilterI> f) {
		for(FilterI ft: f) {
		if (!observers.containsKey(ft)) {
			observers.put(ft, new ArrayList<ObserverI>());
		}
		observers.get(ft).add(o);
		}
	}

	@Override
	public void notifyAll(EventTrigger et, Number n) {
		for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
			if (entry.getKey().check(et)) {
				for (ObserverI o : entry.getValue()) {
					o.update(n, et);
				}
			}
		}
	}
	
}

	

