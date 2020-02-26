package numberPlay.observer.filter;

import numberPlay.util.EventTrigger;

/**
 * 
 * @author Sagar Toke
 *
 *         The class {@code EventTrigger} handles the event trigger mechanism
 *
 */
public class EventTriggerFilter implements FilterI {
	public EventTrigger et;
	private int hashcode;

	/**
	 * Creats new EventFilter with event created by subject-NUmberProcessor and sets
	 * hashcode values depending on the event triggered
	 * 
	 * @param etIn
	 */

	public EventTriggerFilter(EventTrigger etIn) {
		et = etIn;
		if (et.equals(EventTrigger.INTEGER_EVENT))
			hashcode = 1;
		else if (et.equals(EventTrigger.FLOATING_POINT_EVENT))
			hashcode = 2;
		else
			hashcode = 3;
	}

	public EventTrigger getEt() {
		return et;
	}

	public void setEt(EventTrigger etIn) {
		et = etIn;
	}

	/**
	 * Method to check if given event is present in filters or not
	 * @param et event to be triggered
	 * @return {@value true} if valid, otherwise {@value false}
	 */
	@Override
	public boolean check(EventTrigger etIn) {
		if (etIn.equals(et))
			return true;
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof EventTriggerFilter)) {
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}
}
