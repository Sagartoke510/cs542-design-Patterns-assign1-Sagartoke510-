package numberPlay.subject;

import java.util.List;

import numberPlay.observer.ObserverI;
import numberPlay.observer.filter.FilterI;
import numberPlay.util.EventTrigger;

public interface SubjectI{
	public void process(String inputFilePath);
	public void register(ObserverI o, List<FilterI> f);
	public void notifyAll(EventTrigger et, Number n);
	
	
}