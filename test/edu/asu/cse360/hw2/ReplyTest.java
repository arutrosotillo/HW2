/**
 * CSE360 HW2
 * Author: ChatGPT
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the Reply entity.
 */
public class ReplyTest {

    @Test
    public void testValidCreation() throws InvalidInputException {
        // Verifies replies can be created with valid data.
        Reply reply = new Reply(1, 2, "Student", "Great post!");
        assertEquals(1, reply.getId());
        assertEquals(2, reply.getPostId());
        assertEquals("Student", reply.getAuthor());
        assertEquals("Great post!", reply.getContent());
    }

    @Test
    public void testInvalidCreationEmptyContent() {
        // Confirms validation rejects empty reply content.
        assertThrows(InvalidInputException.class, () -> new Reply(1, 2, "Student", ""));
    }

    @Test
    public void testUpdateReply() throws InvalidInputException {
        // Ensures the Update operation changes the reply content.
        Reply reply = new Reply(1, 2, "Student", "Initial");
        reply.updateReply("Updated message");
        assertEquals("Updated message", reply.getContent());
    }
}
