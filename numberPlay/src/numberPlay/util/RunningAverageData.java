package numberPlay.util;

public class RunningAverageData implements PersisterI, RunningAverageResultsI {
	
	
	public RunningAverageData(String windowSizeIn) throws Exception {
		if(Integer.parseInt(windowSizeIn)<=0)
			throw new Exception("Invalid window Size");
			
	}

	@Override
	public void store(Double d) {}

	@Override
	public void writeToFile() {}

	@Override
	public void close() {}
}
