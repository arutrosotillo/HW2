/**
 * CSE360 HW2
 * Author: Arturo Sotillo Barraca
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Stores and manages Post entities in memory.
 */
public class PostsCollection {

    private final List<Post> posts = new ArrayList<>();
    private int nextPostId = 1;

    /**
     * Creates and saves a new post after validating input through the Post constructor.
     *
     * @param author  author of the post
     * @param title   title of the post
     * @param content content of the post
     * @return the created Post instance
     * @throws InvalidInputException when validation fails
     */
    public Post createPost(String author, String title, String content) throws InvalidInputException {
        Post post = new Post(nextPostId++, author, title, content);
        posts.add(post);
        return post;
    }

    /**
     * Retrieves a post by id to satisfy the Read requirement of CRUD.
     *
     * @param id identifier to locate
     * @return the matching post or null when not found
     */
    public Post readPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null;
    }

    /**
     * Returns all posts; the copy prevents callers from modifying internal state.
     *
     * @return list of all posts
     */
    public List<Post> readAllPosts() {
        return new ArrayList<>(posts);
    }

    /**
     * Updates a post when it exists. Validation failures or missing posts return false.
     *
     * @param id          identifier of the post to update
     * @param newTitle    replacement title
     * @param newContent  replacement content
     * @return true when the update succeeds
     */
    public boolean updatePost(int id, String newTitle, String newContent) {
        Post post = readPostById(id);
        if (post == null) {
            System.out.println("Error: Post ID " + id + " not found.");
            return false;
        }
        try {
            post.updateContent(newTitle, newContent);
            return true;
        } catch (InvalidInputException e) {
            // Surface validation message for the console driver and tests.
            System.out.println("Error updating post: " + e.getMessage());
            return false;
        }
    }

    /**
     * Removes a post when present.
     *
     * @param id identifier of the post to delete
     * @return true if removal occurred
     */
    public boolean deletePost(int id) {
        Iterator<Post> iterator = posts.iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (post.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        System.out.println("Error: Post ID " + id + " not found.");
        return false;
    }

    /**
     * Finds posts that contain a keyword in their title or content.
     *
     * @param keyword term to search for
     * @return list of posts containing the keyword
     */
    public List<Post> searchPostsByKeyword(String keyword) {
        List<Post> matches = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return matches; // Validation: avoid treating empty keywords as matches.
        }
        for (Post post : posts) {
            if (post.matchesKeyword(keyword)) {
                matches.add(post);
            }
        }
        return matches;
    }
}
