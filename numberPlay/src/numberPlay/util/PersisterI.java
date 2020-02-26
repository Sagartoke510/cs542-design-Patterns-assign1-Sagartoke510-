package numberPlay.util;

/**
* To be implemented by classes that persist generated output data.
*/
public interface PersisterI {
	/**
	 * Method to close resources opened for writing to file
	 */
	void close();
	
	/**
	 * Method to write result in output file
	 */
	void writeToFile();
}
