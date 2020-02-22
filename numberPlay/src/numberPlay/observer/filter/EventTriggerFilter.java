package numberPlay.observer.filter;

import numberPlay.util.EventTrigger;

public class EventTriggerFilter implements FilterI {
	public EventTrigger et;
	
	public EventTriggerFilter(EventTrigger etIn) {
		et = etIn;
	}
	
	
	
	public EventTrigger getEt() {
		return et;
	}



	public void setEt(EventTrigger etIn) {
		et = etIn;
	}



	@Override
	public boolean check(EventTrigger etIn) {
		if(etIn.equals(et))
		 return true;
		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof EventTriggerFilter)) {
			return false;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		return 0 ;
	}
}
