package com.translatetxml.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class SegmentHelperTest {

	@Test
	public void testGetUniqueWordCount() {
		Integer numberOfUniqueWords = 1;
		SegmentHelper segmentHelper = new SegmentHelper("I would like to see the library please. I would like to see the library.");
		assertEquals(numberOfUniqueWords, segmentHelper.getUniqueWordCount());
	}

	@Test
	public void testGetWordCount() {
		Integer numberOfUniqueWords = 8;
		SegmentHelper segmentHelper = new SegmentHelper("I would like to see the library please.");
		assertEquals(numberOfUniqueWords, segmentHelper.getWordCount());
	}

}
