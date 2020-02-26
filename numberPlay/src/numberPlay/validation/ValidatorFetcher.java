package numberPlay.validation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import numberPlay.cmdLineExceptions.TopKSizeException;
import numberPlay.cmdLineExceptions.WindowException;

public class ValidatorFetcher {

	public static Validator existsFileValidator(String inputFilePath) {
		return new Validator() {

			@Override
			public void run() throws FileNotFoundException {
				File file = new File(inputFilePath);
				if (!file.exists()) {
					throw new FileNotFoundException("Input file doesn't exists");
				}
			}

		};
	}

	public static Validator checkEmptyFileValidator(String inputFilePath) {
		return new Validator() {

			@Override
			public void run() throws IOException {
				File file = new File(inputFilePath);
				if (file.length() == 0) {
					throw new IOException("Input File is Empty");
				}
			}

		};

	}

	public static Validator lineContentValidator(String inputFilePath) {
		return new Validator() {

			@Override
			public void run() throws IOException {
				String check="";
				String pattern ="^[0-9\\.\\-\\n]*S";
				check = Files.readString(Paths.get(inputFilePath));
				if (check.matches(pattern)) {
					throw new IOException("Invalid line Contents");
				}
			}

		};
	}

	public static Validator windowValidator(String windowSizeIn) {
		return new Validator() {

			@Override
			public void run() throws WindowException {
				if (Integer.parseInt(windowSizeIn) <= 0)
					throw new WindowException("Invalid window Size");
			}
		};
	}

	public static Validator topKNumberValidation(String topKNum) {
		return new Validator() {

			@Override
			public void run() throws TopKSizeException {
				if (Integer.parseInt(topKNum) <= 0)
					throw new TopKSizeException("Invalid Value for top k");
			}

		};
	}

}
