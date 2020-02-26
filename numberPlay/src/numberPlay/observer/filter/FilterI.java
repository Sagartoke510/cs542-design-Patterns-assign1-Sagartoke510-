package numberPlay.observer.filter;

import numberPlay.util.EventTrigger;

/**
 * 
 * @author sagar
 *the interface {@code FilterI} is defined to be implemented by EventTriggerFIlter
 */
public interface FilterI {
	
	/**
	 * Method to check if given event is present in filters or not
	 * @param et event to be triggered
	 * @return {@value true} if valid, otherwise {@value false}
	 */
	boolean check(EventTrigger et);

}
