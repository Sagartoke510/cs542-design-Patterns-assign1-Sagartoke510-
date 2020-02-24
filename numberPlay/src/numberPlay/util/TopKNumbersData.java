package numberPlay.util;

import java.util.List;

public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	private Integer topKNumber;
	public TopKNumbersData(String topKNum) throws Exception {
		if(Integer.parseInt(topKNum)<=0)
			throw new Exception("Invalid value for top k");
			
		topKNumber=Integer.parseInt(topKNum);
	}

	@Override
	public void store(List<Double> topK) {}

	@Override
	public void writeToFile() {}
	
	@Override
	public void close() {}
}
