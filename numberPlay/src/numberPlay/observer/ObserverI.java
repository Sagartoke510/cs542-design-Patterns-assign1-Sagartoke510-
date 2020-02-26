package numberPlay.observer;

import numberPlay.util.EventTrigger;

/**
 * This interface is an observer
 * @author Sagar Toke
 *
 */
public interface ObserverI{
	
	/**
	 * Method used to update specific observer to perform specific task on the number passed and event triggered 
	 * @param n numbers on which task needs to be performed
	 * @param et event triggered
	 */
	public void update(Number n, EventTrigger et);

}