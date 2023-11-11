package Task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class MyWordCountApp {
// public static final String fileName = "data/hamlet.txt";
	public static final String fileName = "data/fit.txt";
// <word, its occurences>
	private Map<String, Integer> map = new HashMap<String, Integer>();

// Load data from fileName into the above map (containing <word, its
// occurences>)
// using the guide given in TestReadFile.java
	public void loadData() throws FileNotFoundException {
		Scanner input = new Scanner(new File(fileName));
		while (input.hasNext()) {
			String word = input.next();
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
	}

// Returns the number of unique tokens in the file data/hamlet.txt or fit.txt
	public int countUnique() {
		int cnt = 0;
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 1)
				++cnt;
		}
		return cnt;
	}

// Returns the number of distinct tokens in the file data/hamlet.txt or
// fit.txt
	public int countDistinct() {
		return map.size();
	}

// Prints out the number of times each unique token appears in the file
// data/hamlet.txt (or fit.txt)
// In this method, we do not consider the order of tokens.
	public void printWordCounts() throws FileNotFoundException {
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.print(entry + " ");
		}
	}

// Prints out the number of times each unique token appears in the file
// data/hamlet.txt (or fit.txt) according to ascending order of tokens
// Example: An - 3, Bug - 10, ...
	public void printWordCountsAlphabet() {
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		treeMap.putAll(map);
		System.out.println(treeMap);
	}

	public static void run() throws FileNotFoundException {
		MyWordCountApp mw = new MyWordCountApp();
		// Map<String, Integer> map = new HashMap<String, Integer>();
		mw.loadData();
		System.out.println("Load data vào Map:");
		System.out.println(mw.map + "\n");
		System.out.println("Đếm số từ xuất hiện 1 lần:");
		System.out.println(mw.countUnique() + "\n");
		System.out.println("Đếm số từ có trong file:");
		System.out.println(mw.countDistinct() + "\n");
		System.out.println("In ra cái Map:");
		mw.printWordCounts();
		System.out.println("\n");
		System.out.println("In ra cái Map theo bảng chữ cái:");
		mw.printWordCountsAlphabet();

	}

	public static void main(String[] args) throws FileNotFoundException {
		run();
	}
}
