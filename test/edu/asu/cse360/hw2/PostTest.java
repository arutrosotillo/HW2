/**
 * CSE360 HW2
 * Author: ChatGPT
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests verifying Post requirements.
 */
public class PostTest {

    @Test
    public void testValidCreation() throws InvalidInputException {
        // Ensures the Create operation stores valid data correctly.
        Post post = new Post(1, "Author", "Title", "Content");
        assertEquals(1, post.getId());
        assertEquals("Author", post.getAuthor());
        assertEquals("Title", post.getTitle());
        assertEquals("Content", post.getContent());
        assertNotNull(post.getCreatedAt());
    }

    @Test
    public void testInvalidCreationEmptyTitle() {
        // Confirms validation prevents empty titles from being saved.
        assertThrows(InvalidInputException.class, () -> new Post(1, "Author", "", "Content"));
    }

    @Test
    public void testUpdateContent() throws InvalidInputException {
        // Covers the Update operation by changing title and content.
        Post post = new Post(1, "Author", "Old", "Old content");
        post.updateContent("New", "New content");
        assertEquals("New", post.getTitle());
        assertEquals("New content", post.getContent());
    }

    @Test
    public void testMatchesKeyword() throws InvalidInputException {
        // Validates the keyword search feature specified in requirements.
        Post post = new Post(1, "Author", "Hello World", "Discussing Java");
        assertTrue(post.matchesKeyword("hello"));
        assertFalse(post.matchesKeyword("python"));
    }
}
