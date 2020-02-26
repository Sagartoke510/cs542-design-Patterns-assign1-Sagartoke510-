package numberPlay.cmdLineExceptions;

/**
*Class {@code TopKSizeException} is one type of exception
*/
public class TopKSizeException extends Exception {

	/**
	 * Constructs a exception with specific message
	 * @param TopKSizeMessage the detailed Exception message
	 */
	public TopKSizeException(String TopKSizeMessage ) {
		super(TopKSizeMessage);
	}
}
