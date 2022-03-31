package com._31536000.algo.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Trie {
	private HashMap<Character, Integer> charMap;

	private List<Integer> base, check, failure;

	public Trie() {
		charMap = new HashMap<Character, Integer>();
		base = new ArrayList<Integer>();
		check = new ArrayList<Integer>();
		failure = new ArrayList<Integer>();
	}

	public boolean add(String word) {
		return add(word.toCharArray());
	}

	private int get(char c) {
		if (charMap.containsKey(c)) return charMap.get(c);
		charMap.put(c, charMap.size() + 1);
		return charMap.size();
	}

	public boolean add(char[] word) {
		int index = 0, next;
		for (char i : word) {
			next = base.get(index) + get(i);
			if (index == check.get(next)) { // 遷移成功
				index = next;
			} else {
				
			}
		}
	}

	public boolean find(char[] word) {
		try {
			int index = 0, next;
			for (char i : word) {
				next = base.get(index) + get(i);
				if (index == check.get(next)) { // 遷移成功
					index = next;
				} else return false;
			}
			return base.get(index) <= 0;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

}
