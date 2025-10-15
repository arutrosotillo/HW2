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
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Integration-style tests for the collection classes.
 */
public class CollectionTest {

    @Test
    public void testCreateReadUpdateDeletePost() throws InvalidInputException {
        // Validates the complete CRUD workflow for posts.
        PostsCollection postsCollection = new PostsCollection();
        Post created = postsCollection.createPost("Alice", "Greetings", "Hello class");
        assertEquals(1, created.getId());

        Post read = postsCollection.readPostById(created.getId());
        assertNotNull(read);

        boolean updated = postsCollection.updatePost(created.getId(), "Updated", "Updated content");
        assertTrue(updated);
        assertEquals("Updated", postsCollection.readPostById(created.getId()).getTitle());

        boolean deleted = postsCollection.deletePost(created.getId());
        assertTrue(deleted);
        assertEquals(0, postsCollection.readAllPosts().size());
    }

    @Test
    public void testSearchPostsByKeyword() throws InvalidInputException {
        // Ensures keyword search returns the proper records.
        PostsCollection postsCollection = new PostsCollection();
        postsCollection.createPost("Alice", "Java Tips", "Share ideas");
        postsCollection.createPost("Bob", "Python Talk", "Different language");

        List<Post> matches = postsCollection.searchPostsByKeyword("java");
        assertEquals(1, matches.size());
        assertEquals("Java Tips", matches.get(0).getTitle());
    }

    @Test
    public void testDeleteNonexistentPostReturnsFalse() throws InvalidInputException {
        // Deleting a missing post should fail gracefully.
        PostsCollection postsCollection = new PostsCollection();
        postsCollection.createPost("Alice", "Title", "Content");
        assertFalse(postsCollection.deletePost(99));
    }

    @Test
    public void testValidationExceptionsAreThrown() {
        // Confirms validation propagates through collection creation methods.
        PostsCollection postsCollection = new PostsCollection();
        assertThrows(InvalidInputException.class, () -> postsCollection.createPost("Alice", "", "Content"));
    }

    @Test
    public void testRepliesRespectValidation() throws InvalidInputException {
        // Ensures replies can only be created for existing posts and validate content.
        PostsCollection postsCollection = new PostsCollection();
        RepliesCollection repliesCollection = new RepliesCollection(postsCollection);
        postsCollection.createPost("Alice", "Topic", "Content");

        assertThrows(InvalidInputException.class, () -> repliesCollection.createReply(1, "Bob", ""));
        Reply reply = repliesCollection.createReply(1, "Bob", "Valid reply");
        assertNotNull(reply);
        Reply missing = repliesCollection.createReply(2, "Carl", "Should fail");
        assertNull(missing);
    }
}
