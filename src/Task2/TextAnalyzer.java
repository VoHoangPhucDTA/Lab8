package Task2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer {
// <word, its positions>
	private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

// load words in the text file given by fileName and store into map by using add
// method in Task 2.1.
// Using BufferedReader reffered in file TextFileUtils.java
	public void load(String fileName) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(fileName));
		String line = null;
		int index = 1;
		while (true) {
			line = reader.readLine();
			if (line == null)
				break;
			StringTokenizer tokens = new StringTokenizer(line, " ");
			while (tokens.hasMoreTokens()) {
				String w = tokens.nextToken();
				if (!tokens.hasMoreTokens()) {
					add(w, -index);
				} else {
					add(w, index);
				}
				++index;
			}
		}
		reader.close();
	}
// In the following method, if the word is not in the map, then adding that word
// to the map containing the position of the word in the file. If the word is
// already in the map, then its word position is added to the list of word
// positions for this word.
// Remember to negate the word position if the word is at the end of a line in
// the text file

// {word, [0,1,2,3,..]}

	public void add(String word, int position) {
		if (this.map.containsKey(word)) {
			ArrayList<Integer> values = map.get(word);
			values.add(position);
			this.map.put(word, values);
		} else {
			ArrayList<Integer> values = new ArrayList<Integer>();
			values.add(position);
			this.map.put(word, values);
		}
	}

// This method should display the words of the text file along with the
// positions of each word, one word per line, in alphabetical order
	public void displayWords() {
		TreeMap<String, ArrayList<Integer>> sortedMap = new TreeMap<>(map);
		for (Map.Entry<String, ArrayList<Integer>> entry : sortedMap.entrySet()) {
			String word = entry.getKey();
			ArrayList<Integer> positions = entry.getValue();
			System.out.print(word + ": ");
			for (int i = 0; i < positions.size(); i++) {
				int position = positions.get(i);
				System.out.print(position);
				if (i < positions.size() - 1) {
					System.out.print(", ");
				}
			}
			System.out.println();
		}
	}

// This method will display the content of the text file stored in the map
	public void displayText() {
		int size = 0;
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			int sizeEntry = entry.getValue().size();
			size += sizeEntry;
		}
		String[] text = new String[size];
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			String key = entry.getKey();
			for (Integer it : entry.getValue()) {
				if (it >= 0) {
					text[it - 1] = key;
				} else {
					it = Math.abs(it);
					text[it - 1] = key + "\n";
				}
			}
		}

		for (int i = 0; i < text.length; i++) {
			System.out.print(text[i] + " ");
		}
	}

// This method will return the word that occurs most frequently in the text file
	public String mostFrequentWord() {
		int max = Integer.MIN_VALUE;
		String result = "";
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			ArrayList<Integer> list = entry.getValue();
			if (list.size() > max) {
				max = list.size();
			}
		}
		for (Map.Entry<String, ArrayList<Integer>> entry : map.entrySet()) {
			ArrayList<Integer> list = entry.getValue();
			if (list.size() == max)
				result = entry.getKey();
		}
		return result;
	}

	public static void run() throws IOException {
		TextAnalyzer ta = new TextAnalyzer();
		ta.load("data/short.txt");
		System.out.println("In các từ trong file theo thứ tự bảng chữ cái");
		ta.displayWords();
		System.out.println("\n");
		System.out.print("Từ xuất hiện nhiều nhất trong file: ");
		System.out.println(ta.mostFrequentWord() + "\n\n");

		System.out.println("In các từ theo thứ tự trong Map: ");
		ta.displayText();

	}

	public static void main(String[] args) throws IOException {
		run();
	}

}
