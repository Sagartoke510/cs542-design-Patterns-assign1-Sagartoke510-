package numberPlay.subject;

import java.util.List;

import numberPlay.observer.ObserverI;
import numberPlay.observer.filter.FilterI;
import numberPlay.util.EventTrigger;

/**
 * The interface is a Subject for process, registering and notify observers for particular events
 * @author Sagar Toke
 *
 */
public interface SubjectI{
	
	/**
	 * Method which reads line of file of the specified path
	 * @param inputFilePath path of the file
	 */
	public void process(String inputFilePath);
	
	/**
	 * Method used for registering each observer with subject based on the filters applied on observer
	 * @param o observer to be registered
	 * @param f list of Filters
	 */
	public void register(ObserverI o, List<FilterI> f);
	
	/**
	 * Method used for notifying all observer to update as per event triggered
	 * @param et event triggered
	 * @param n number stream on which operation will be performed
	 */
	public void notifyAll(EventTrigger et, Number n);
	
	
}