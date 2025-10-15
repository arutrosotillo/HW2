/**
 * CSE360 HW2
 * Author: ChatGPT
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a reply to a discussion post.
 */
public class Reply {

    private final int id;
    private final int postId;
    private final String author;
    private String content;
    private final LocalDateTime createdAt;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Builds a reply after validating required fields to prevent invalid data storage.
     *
     * @param id       unique reply identifier
     * @param postId   identifier of the post that receives the reply
     * @param author   reply author, must be non-empty
     * @param content  reply content, must be non-empty
     * @throws InvalidInputException when author or content are empty
     */
    public Reply(int id, int postId, String author, String content) throws InvalidInputException {
        validateText(author, "Author");
        validateText(content, "Content");
        this.id = id;
        this.postId = postId;
        this.author = author.trim();
        this.content = content.trim();
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Updates reply content while ensuring the new text is non-empty.
     *
     * @param newContent replacement content for the reply
     * @throws InvalidInputException when the new content is invalid
     */
    public void updateReply(String newContent) throws InvalidInputException {
        validateText(newContent, "Content");
        this.content = newContent.trim();
    }

    /**
     * Formats the reply for console output.
     *
     * @return formatted text describing the reply
     */
    public String read() {
        return String.format("Reply #%d to Post #%d by %s\nContent: %s\nCreated: %s",
                id,
                postId,
                author,
                content,
                createdAt.format(FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("Reply #%d on Post #%d by %s", id, postId, author);
    }

    private static void validateText(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " must not be null or empty.");
        }
    }

    public int getId() {
        return id;
    }

    public int getPostId() {
        return postId;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
