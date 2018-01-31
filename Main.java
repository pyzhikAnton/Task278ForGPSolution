import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static final String MESSAGE_YES = "YES";
	private static final String MESSAGE_NO = "NO";
	private static final String INPUT_FILE_ADDRESS = "input.txt";
	private static final String OUTPUT_FILE_ADDRESS = "output.txt";

	String sequenceS;
	String sequenceT;
	int[] indexes;

	private Main() {
		try {
			initializeFieldsFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeFieldsFromFile() throws IOException {
		Scanner sc = new Scanner(new FileReader(INPUT_FILE_ADDRESS));
		sequenceS = sc.nextLine();
		sequenceT = sc.nextLine();
		sc.close();
	}

	public static void main(String[] args) {

		Main obj = new Main();
		try {
			if (obj.createSIndexesInT() && obj.checkIndexes()) {
				obj.printAnswer(MESSAGE_YES);
			} else {
				obj.printAnswer(MESSAGE_NO);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printAnswer(String message) throws IOException {
		FileWriter writer = new FileWriter(OUTPUT_FILE_ADDRESS, false);
		writer.write(message);
		writer.close();
	}

	private boolean checkIndexes() {
		for (int i = 0, j = i + 1; i < indexes.length - 1 && j < indexes.length; i++, j++) {
			if (indexes[i] >= indexes[j]) {
				return false;
			}
		}
		return true;
	}

	private boolean createSIndexesInT() {
		indexes = new int[sequenceS.length()];
		for (int i = 0; i < indexes.length; i++) {
			char temp = sequenceS.charAt(i);
			if (i == 0) {
				indexes[i] = sequenceT.indexOf(temp);
			} else {
				indexes[i] = sequenceT.indexOf(temp, indexes[i - 1] + 1);
			}
			if (indexes[i] < 0) {
				return false;
			}
		}
		return true;
	}

}
