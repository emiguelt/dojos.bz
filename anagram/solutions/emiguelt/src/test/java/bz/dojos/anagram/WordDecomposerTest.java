package bz.dojos.anagram;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class WordDecomposerTest {

	private WordDecomposer decomposer;

	@Before
	public void setup() {
		decomposer = new WordDecomposer();
	}

	@Test
	public void givenEmptyString_thenReturnEmptyList() {
		assertTrue(split(null).isEmpty());
		assertTrue(split("").isEmpty());
	}

	private List<Character> split(String a) {
		return decomposer.split(a);
	}

	@Test
	public void givenOneCharString_thenReturnOneSizeListContainingChar() {
		assertSplit("a");
		assertSplit("b");
		assertSplit("c");
		assertSplit(" ");
	}

	@Test
	public void givenTwoCharString_thenReturnTwoSizeListContainingChars() {
		assertSplit("ab");
		assertSplit("bc");
		assertSplit("cd");
		assertSplit(" a");
	}

	@Test
	public void givenThreeCharString_thenReturnThreeSizeListContainingChars() {
		assertSplit("abc");
		assertSplit("bcd");
		assertSplit("cde");
		assertSplit(" ab");
	}

	@Test
	public void givenEmptyList_ThenReturnEmptyList_inSortMethod() {
		assertSort(Collections.emptyList(), Collections.emptyList());
	}

	@Test
	public void givenOneSizeList_thenReturnEquivalenteList_InSortMethod() {
		assertSort(Arrays.asList('a'), Arrays.asList('a'));
	}

	@Test
	public void givenTwoSizeList_thenReturnSortedList_InSortMethod() {
		assertSort(Arrays.asList('b', 'a'), Arrays.asList('a', 'b'));
	}

	@Test
	public void givenThreeSizeList_thenReturnSortedList_InSortMethod() {
		assertSort(Arrays.asList('b', 'a', 'a'), Arrays.asList('a', 'a', 'b'));
	}

	private void assertSort(List<Character> input, List<Character> expected) {
		List<Character> result = sort(input);
		assertEquals(expected.size(), result.size());
		assertEquals(expected, result);
	}

	private List<Character> sort(List<Character> list) {
		return decomposer.sort(list);
	}

	private void assertSplit(final String word) {
		List<Character> chars = split(word);
		assertEquals(word.length(), chars.size());
		chars.forEach(ch -> {
			if (!word.contains(ch + ""))
				fail("word not found");
		});
	}

}
