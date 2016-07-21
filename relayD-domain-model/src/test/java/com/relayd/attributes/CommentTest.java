package com.relayd.attributes;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author CrowCounter77 (Mirko@relayd.de)
 * @since 21.07.2016
 * status initial
 *
 */
public class CommentTest {

	@Test
	public void testCreateValidObject() {
		String expectedComment = "Dies ist ein Kommentar.";
		Comment sut = Comment.newInstance(expectedComment);
		assertEquals("Value is not as expected.", expectedComment, sut.toString());
	}
}
