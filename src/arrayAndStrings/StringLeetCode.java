package arrayAndStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class StringLeetCode {
	public static void main(String[] args) {
//		System.out.println(percentageLetter("sgawtb", 's'));
//		System.out.println(isSumEqual("j", "j", "bi"));
//		System.out.println(digitCount("030"));
//		System.out.println(canBeTypedWords("leet code", "lt"));
		String[] words = { "Hello", "Alaska", "Dad", "Peace" };
		String[] res = findWords(words);
		for (String i : res) {
			System.out.println(i);
		}
	}

//	helper functions
	public static boolean isPalindrome(String str) {
		char[] charArr = str.toCharArray();

		int left = 0, right = str.length() - 1;

		for (; left < right; left++, right--)
			if (charArr[left] != charArr[right])
				return false;

		return true;
	}

//Leetcode String Easy:
//	500. Keyboard Row
	public static String[] findWords(String[] words) {
		ArrayList<String> res = new ArrayList<>();

		HashSet<Character> row1 = new HashSet<>(Arrays.asList('q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p')),
				row2 = new HashSet<>(Arrays.asList('a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l')),
				row3 = new HashSet<>(Arrays.asList('z', 'x', 'c', 'v', 'b', 'n', 'm'));

		for (String word : words) {
			word = word.toLowerCase();

			boolean flag1 = true, flag2 = true, flag3 = true;
			for (int i = 0; i < word.length(); i++)
				if (!row1.contains(word.charAt(i)))
					flag1 = false;
			for (int i = 0; i < word.length(); i++)
				if (!row2.contains(word.charAt(i)))
					flag2 = false;
			for (int i = 0; i < word.length(); i++)
				if (!row3.contains(word.charAt(i)))
					flag3 = false;

			if (flag1 || flag2 || flag3)
				res.add(word);
		}

		return res.toArray(new String[0]);
	}

//	2085. Count Common Words With One Occurrence

	public int countWords(String[] words1, String[] words2) {
		int count = 0;
		HashMap<String, Integer> wordFreq = new HashMap<>();

		for (String word : words1)
			wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);

		for (String word : words2) {
			int freq = wordFreq.getOrDefault(word, 0);
			if (wordFreq.containsKey(word) && freq < 2)
				wordFreq.put(word, freq - 1);
		}

		for (Map.Entry<String, Integer> e : wordFreq.entrySet())
			if (e.getValue() == 0)
				count++;

		return count;
	}

//	1935. Maximum Number of Words You Can Type
	public static int canBeTypedWords(String text, String brokenLetters) {
		boolean[] brokenAlpha = new boolean[26];
		int count = 0;

		for (char ch : brokenLetters.toCharArray())
			brokenAlpha[ch - 'a'] = true;

		for (int i = 0; i < text.length(); i++) {
			boolean isTyped = true;
			while (i < text.length() && text.charAt(i) != ' ') {
				if (brokenAlpha[text.charAt(i) - 'a'])
					isTyped = false;
				i++;
			}
			if (isTyped)
				count++;
		}

		return count;
	}

	public String kthDistinct(String[] arr, int k) {
		HashMap<String, Integer> alphaFreq = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			if (alphaFreq.containsKey(arr[i]))
				alphaFreq.put(arr[i], 1 + alphaFreq.get(arr[i]));
			else
				alphaFreq.put(arr[i], 1);
		}

		for (int i = 0; i < arr.length && k > 0; i++) {
			if (alphaFreq.get(arr[i]) == 1) {
				if (--k == 0)
					return arr[i];
			}
		}

		return "";
	}

//	2283. Check if Number Has Equal Digit Count and Digit Value
	public static boolean digitCount(String num) {
		int[] digits = new int[10];

		for (int i = 0; i < num.length(); i++)
			digits[num.charAt(i) - '0']++;

		for (int i = 0; i < num.length(); i++)
			if (digits[i] != num.charAt(i) - '0')
				return false;

		return true;
	}

//	1880. Check if Word Equals Summation of Two Words
	public static boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
		StringBuilder op1 = new StringBuilder(), op2 = new StringBuilder(), target = new StringBuilder();
		int sum1 = 0, sum2 = 0, sum3 = 0;
		for (int i = 0; i < firstWord.length(); i++)
			op1.append(firstWord.charAt(i) - 'a');
		for (int i = 0; i < secondWord.length(); i++)
			op2.append(secondWord.charAt(i) - 'a');
		for (int i = 0; i < targetWord.length(); i++)
			target.append(targetWord.charAt(i) - 'a');

		System.out.println("sum1: " + op1 + " sum2: " + op2 + " sum3: " + target);

		return Integer
				.parseInt(target.toString()) == (Integer.parseInt(op1.toString()) + Integer.parseInt(op2.toString()));
	}

//	2278. Percentage of Letter in String
	public static int percentageLetter(String s, char letter) {
		int count = 0;

		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) == letter)
				count++;
		return 100 * count / s.length();
//		return (int) (Math.floor(count * 100 / (float) s.length()));
	}

//	2351. First Letter to Appear Twice
	public static char repeatedCharacter(String s) {
		boolean[] alpha = new boolean[26];
		Character res = null;

		for (int i = 0; i < s.length(); i++) {
			if (!alpha[s.charAt(i) - 'a'])
				alpha[s.charAt(i) - 'a'] = true;
			else {
				res = s.charAt(i);
				break;
			}
		}

		return res;
	}

//	1941. Check if All Characters Have Equal Number of Occurrences
	public static boolean areOccurrencesEqual(String s) {
		int[] alpha = new int[26];

		for (int i = 0; i < s.length(); i++)
			alpha[s.charAt(i) - 'a']++;

		int temp = alpha[s.charAt(0) - 'a'];

		for (int i = 0; i < 26; i++)
			if (alpha[i] > 0 && temp != alpha[i])
				return false;

		return true;
	}

//	2185. Counting Words With a Given Prefix
	public static int prefixCount(String[] words, String pref) {
		int count = words.length;

		for (String word : words) {
			if (word.length() < pref.length()) {
				--count;
				continue;
			}

			for (int i = 0; i < pref.length() && i < word.length(); i++)
				if (pref.charAt(i) != word.charAt(i)) {
					--count;
					break;
				}
		}

		return count;
	}

//	1812. Determine Color of a Chessboard Square
	public static boolean squareIsWhite(String s) {
		int x = s.charAt(0) - 'a';
		int y = s.charAt(1) - '0';

		return x % 2 == 0 ? y % 2 == 0 : !(y % 2 == 0);
//		if (x % 2 == 0) {
//			if (y % 2 == 0)
//				return true;
//			else
//				return false;
//		} else {
//			if (y % 2 == 0)
//				return false;
//			else
//				return true;
//		}
	}

//	1704. Determine if String Halves Are Alike
	public static boolean halvesAreAlike(String s) {
		HashSet<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
		int count = 0;

		for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
			count += vowels.contains(s.charAt(i)) ? 1 : 0;
			count -= vowels.contains(s.charAt(j)) ? 1 : 0;
		}

		return count == 0;
//		Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
//		int count = 0, i = 0, j = s.length() - 1;
//
//		while (i < j) {
//			if (set.contains(s.charAt(i++)))
//				++count;
//			if (set.contains(s.charAt(j--)))
//				--count;
//		}
//
//		return count == 0;
	}

//	1370. Increasing Decreasing String
	public static String sortString(String s) {
		StringBuilder res = new StringBuilder(s.length());
		int[] alphaCount = new int[26];

		for (int i = 0; i < s.length(); i++)
			alphaCount[s.charAt(i) - 'a']++;

		while (res.length() < s.length()) {
			for (int i = 0; i < 26; i++)
				if (alphaCount[i]-- > 0)
					res.append((char) (i + 'a'));

			for (int i = 25; i >= 0; i--)
				if (alphaCount[i]-- > 0)
					res.append((char) (i + 'a'));
		}

		return res.toString();
	}

//	1374. Generate a String With Characters That Have Odd Counts
	public static String generateTheString(int n) {
		char[] charArr = new char[n];

		Arrays.fill(charArr, 'a');

		if (n % 2 == 0)
			charArr[0] = 'b';

		return new String(charArr);
	}

//	1436. Destination City
	public String destCity(List<List<String>> paths) {
//		HashMap<String, String> pathMap = new HashMap<>();
//		
//		for (List<String> path: paths)
//			pathMap.put(path.get(0), path.get(1));
//		
//		String temp = paths.get(0).get(0);
//		String res = null;
//
//		while (temp != null) {
//			res = temp;
//			temp = pathMap.get(temp);
//		}
//
//		return res;
		HashSet<String> set = new HashSet<>();

		for (List<String> path : paths)
			set.add(path.get(0));

		for (List<String> path : paths)
			if (!set.contains(path.get(1)))
				return path.get(1);

		return null;
	}

//	2108. Find First Palindromic String in the Array
	public static String firstPalindrome(String[] words) {
		String res = "";
		for (String word : words)
			if (isPalindrome(word)) {
				res = word;
				break;
			}
		return res;
	}

//	(s.charAt(i + 1) >= 49 && s.charAt(i + 1) <= 57) &&
//	1309. Decrypt String from Alphabet to Integer Mapping
	public static String freqAlphabets(String s) {
		StringBuilder res = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			if (i + 2 < s.length() && s.charAt(i + 2) == '#') {
				res.append((char) ('a' + Integer.valueOf(s.charAt(i) + "" + s.charAt(i + 1)) - 1));
				i += 2;
			} else
				res.append((char) ('a' + s.charAt(i) - '0' - 1));
		}

		return res.toString();
	}

//	1967. Number of Strings That Appear as Substrings in Word
	public static int numOfStrings(String[] patterns, String word) {
		boolean[] alpha = new boolean[26];
		int count = 0;

		for (String pattern : patterns) {
			if (word.contains(pattern))
				count++;
		}

		return count;
	}

//	2103. Rings and Rods
//	public static int countPoints(String rings) {
//		StringBuilder[] rodes = new StringBuilder[10];
//		for (int i = 0; i < 10; i++)
//			rodes[i] = new StringBuilder("");
//
//		int count = 0;
//
//		for (int i = 0; i < rings.length(); i += 2)
//			rodes[rings.charAt(i + 1) - '0'].append(rings.charAt(i));
//
//		for (StringBuilder rode : rodes) {
//			boolean red = false, green = false, blue = false;
//			for (int i = 0; i < rode.length(); i++) {
//				if (rode.charAt(i) == 'R')
//					red = true;
//				else if (rode.charAt(i) == 'G')
//					green = true;
//				else
//					blue = true;
//				if (red && green && blue) {
//					count++;
//					break;
//				}
//			}
//		}
//		return count;
//	}

	public static int countPoints(String rings) {
		int[] rods = new int[10];
		for (int i = 0; i < rings.length() - 1; i += 2) {
			if (rings.charAt(i) == 'R')
				rods[rings.charAt(i + 1) - '0'] |= 1;
			if (rings.charAt(i) == 'G')
				rods[rings.charAt(i + 1) - '0'] |= 2;
			if (rings.charAt(i) == 'B')
				rods[rings.charAt(i + 1) - '0'] |= 4;
		}
		int total = 0;
		for (int i = 0; i < rods.length; i++) {
			if (rods[i] == 7)
				total++;
		}
		return total;
	}

//	557. Reverse Words in a String III O(m*n)
	public static String reverseWords(String s) {
		char[] str = s.toCharArray();
		int start = 0, end = 0;

		for (int i = 0; i < str.length; i++) {
			while (end + 1 < str.length && str[end + 1] != ' ')
				end++;
			i = end + 1;

			while (start < end) {
				char temp = str[start];
				str[start] = str[end];
				str[end] = temp;
				start++;
				end--;
			}

			start = end = i + 1;
		}
		return new String(str);
	}

//	public static String reverseWords(String s) {
//		char[] str = s.toCharArray();
//		int lastSpaceIdx = -1;
//
//		for (int i = 0; i < str.length + 1; i++) {
//			if (i == str.length || str[i] == ' ') {
//				int start = lastSpaceIdx + 1;
//				int end = i - 1;
//				reverse(str, start, end);
//				lastSpaceIdx = i;
//			}
//		}
//
//		return new String(str);
//	}

//	1684. Count the Number of Consistent Strings
	public int countConsistentStrings(String allowed, String[] words) {
		HashSet allowedCharSet = new HashSet();
		int count = 0;

		for (int i = 0; i < allowed.length(); i++)
			allowedCharSet.add(allowed.charAt(i));

		for (String word : words) {
			boolean flag = true;

			for (int i = 0; i < word.length(); i++)
				if (!allowedCharSet.contains(word.charAt(i))) {
					flag = false;
					break;
				}

			if (flag)
				count++;
		}
		return count;
	}

//	709. To Lower Case
	public static String toLowerCase(String s) {
		char[] res = s.toCharArray();

		for (int i = 0; i < s.length(); i++)
			if (res[i] >= 'A' && res[i] <= 'Z')
				res[i] = (char) (res[i] | 32);

		return new String(res);
	}

//  1816
	public static String truncateSentence(String s, int k) {
		int i = 0;
		for (; i < s.length(); i++)
			if (s.charAt(i) == ' ' && --k == 0)
				break;
		return s.substring(0, i);
	}

//	2418. Sort the People
	public static String[] sortPeople(String[] names, int[] heights) {
		HashMap<Integer, String> map = new HashMap();

		for (int i = 0; i < heights.length; i++)
			map.put(heights[i], names[i]);

		Arrays.sort(heights);

		String[] res = new String[names.length];

		int i = 0;

		for (int j = heights.length - 1; j >= 0; j--)
			res[i++] = map.get(heights[j]);

		return res;
	}

//	804. Unique Morse Code Words
	public static int uniqueMorseRepresentations(String[] words) {
		String[] morseTable = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..",
				"--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.." };

		HashSet<String> uniqueCodes = new HashSet<>();

		for (String word : words) {
			StringBuilder morseCode = new StringBuilder();

			for (char ch : word.toCharArray()) {
				morseCode.append(morseTable[ch - 'a']);
			}

			uniqueCodes.add(morseCode.toString());
		}

		return uniqueCodes.size();
	}

	// 1662. Check If Two String Arrays are Equivalent
	public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
		String one = String.join("", word1);
		String two = String.join("", word2);

		return one.equals(two);
	}

	// 1832. Check if the sentence is panagram
	// efficient solution O(n), O(1)
//	public boolean checkIfPangram(String sentence) {
//		int seen = 0;
//		for (char c : sentence.toCharArray()) {
//			int ci = c - 'a';
//			seen = seen | (1 << ci);
//		}
//		return seen == ((1 << 26) - 1);
//	}

	public static boolean checkIfPangram(String sentence) {
		HashSet<Character> set = new HashSet<>();
		for (int i = 0; i < sentence.length(); i++)
			set.add(sentence.charAt(i));

		boolean res = true;

		for (char ch = 'a'; ch <= 'z'; ch++) {
			if (!set.contains(ch)) {
				res = false;
				break;
			}
		}
		return res;
	}

	// 1773. count items matching a rule
	public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
		int match = 0;

		for (List<String> item : items) {
			if (ruleKey.equals("type") && ruleValue.equals(item.get(0)))
				match++;

			if (ruleKey.equals("color") && ruleValue.equals(item.get(1)))
				match++;

			if (ruleKey.equals("name") && ruleValue.equals(item.get(2)))
				match++;
		}

		return match;
	}

	// Sorting the sentence
	public static String sortSentence(String s) {
		String[] words = s.split(" ");
		String[] wordsOrdered = new String[words.length];

		for (String word : words)
			wordsOrdered[Character.getNumericValue(word.charAt(word.length() - 1)) - 1] = word.substring(0,
					word.length() - 1);

		return String.join(" ", wordsOrdered);
	}

	// 2325. Decode the message
	public static String decodeMessage(String key, String message) {
		HashMap<Character, Character> map = new HashMap<>();
		StringBuilder res = new StringBuilder();

		int j = 0;

		for (int i = 0; i < key.length(); i++) {
			if (key.charAt(i) == ' ')
				continue;
			if (!map.containsKey(key.charAt(i))) {
				map.put(key.charAt(i), (char) ('a' + j));
				j++;
			}
		}

		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == ' ') {
				res.append(" ");
				continue;
			}
			res.append(map.get(message.charAt(i)));
		}

		return res.toString();
	}

//	2194. Cells in a Range on an Excel Sheet
	public static List<String> cellsInRange(String s) {
		List<String> res = new ArrayList<>();

		char c1 = s.charAt(0), c2 = s.charAt(3);
		char r1 = s.charAt(1), r2 = s.charAt(4);

		for (char i = c1; i <= c2; i++)
			for (char j = r1; j <= r2; j++)
				res.add(i + "" + j);

		return res;
	}

//	1528. Shuffle Ring - Easy
	public static String restoreString(String s, int[] indices) {
		StringBuilder res = new StringBuilder(indices.length);
		char[] midCharArray = new char[indices.length];

		for (int i = 0; i < s.length(); i++)
			midCharArray[indices[i]] = s.charAt(i);

		for (char ch : midCharArray)
			res.append(ch);

		return res.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static int numJewelsInStones(String jewels, String stones) {
		int numOfJewels = 0;

		HashSet setOfJewels = new HashSet();

		for (char ch : jewels.toCharArray())
			setOfJewels.add(ch);

		for (char ch : stones.toCharArray())
			if (setOfJewels.contains(ch))
				numOfJewels++;

		return numOfJewels;
	}

	public static String interpret(String command) {
		HashMap<String, String> map = new HashMap<>();

		String res = new String(command);

		map.put("G", "G");
		map.put("()", "o");
		map.put("(al)", "al");

		for (Map.Entry<String, String> pair : map.entrySet()) {
			res = res.replace(pair.getKey(), pair.getValue());
		}

		return res;
	}
}
