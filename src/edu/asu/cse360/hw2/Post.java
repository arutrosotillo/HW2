/**
 * CSE360 HW2
 * Author: Arturo Sotillo Barraca
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a discussion post and contains validation logic required for CRUD operations.
 */
public class Post {

    private final int id;
    private String author;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new post after validating the provided data.
     * Input validation ensures the system cannot store malformed records.
     *
     * @param id       unique identifier assigned by the collection
     * @param author   non-empty author name
     * @param title    non-empty post title
     * @param content  non-empty post content
     * @throws InvalidInputException if any argument is invalid
     */
    public Post(int id, String author, String title, String content) throws InvalidInputException {
        validateText(author, "Author");
        validateText(title, "Title");
        validateText(content, "Content");
        this.id = id;
        this.author = author.trim();
        this.title = title.trim();
        this.content = content.trim();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    /**
     * Updates the post title and content with validation to uphold data integrity.
     *
     * @param newTitle   replacement title
     * @param newContent replacement content
     * @throws InvalidInputException if either field is blank or null
     */
    public void updateContent(String newTitle, String newContent) throws InvalidInputException {
        validateText(newTitle, "Title");
        validateText(newContent, "Content");
        this.title = newTitle.trim();
        this.content = newContent.trim();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Provides a formatted representation of the post for console output.
     *
     * @return formatted string containing all post details
     */
    public String read() {
        return String.format(
                "Post #%d by %s\nTitle: %s\nContent: %s\nCreated: %s\nUpdated: %s",
                id,
                author,
                title,
                content,
                createdAt.format(FORMATTER),
                updatedAt.format(FORMATTER));
    }

    /**
     * Determines if the post contains the provided keyword in its title or content.
     * The check is case-insensitive for usability.
     *
     * @param keyword search term supplied by the user
     * @return true if the keyword appears in the title or content
     */
    public boolean matchesKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false; // Empty keywords are rejected to satisfy validation requirements.
        }
        String lowerKeyword = keyword.toLowerCase();
        return title.toLowerCase().contains(lowerKeyword) || content.toLowerCase().contains(lowerKeyword);
    }

    @Override
    public String toString() {
        return String.format("Post #%d: %s by %s", id, title, author);
    }

    /**
     * Validates that text fields are neither null nor empty, enforcing assignment requirements.
     *
     * @param value the text being validated
     * @param fieldName name of the field for error messaging
     * @throws InvalidInputException when the value is null or empty
     */
    private static void validateText(String value, String fieldName) throws InvalidInputException {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidInputException(fieldName + " must not be null or empty.");
        }
    }

    // Accessors to support testing and reporting logic.
    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
