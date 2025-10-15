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
 * Manages Reply entities and enforces validation rules.
 */
public class RepliesCollection {

    private final List<Reply> replies = new ArrayList<>();
    private final PostsCollection postsCollection;
    private int nextReplyId = 1;

    /**
     * Builds the collection and links it to the PostsCollection so replies can validate post ids.
     *
     * @param postsCollection shared post repository
     */
    public RepliesCollection(PostsCollection postsCollection) {
        this.postsCollection = postsCollection;
    }

    /**
     * Creates a reply when the parent post exists.
     *
     * @param postId  identifier of the parent post
     * @param author  reply author
     * @param content reply content
     * @return the created reply or null when validation fails
     * @throws InvalidInputException when author or content are invalid
     */
    public Reply createReply(int postId, String author, String content) throws InvalidInputException {
        if (postsCollection.readPostById(postId) == null) {
            System.out.println("Error: Cannot add reply because Post ID " + postId + " does not exist.");
            return null;
        }
        Reply reply = new Reply(nextReplyId++, postId, author, content);
        replies.add(reply);
        return reply;
    }

    /**
     * Reads all replies for the specified post.
     *
     * @param postId parent post identifier
     * @return list of replies
     */
    public List<Reply> readRepliesForPost(int postId) {
        List<Reply> results = new ArrayList<>();
        for (Reply reply : replies) {
            if (reply.getPostId() == postId) {
                results.add(reply);
            }
        }
        return results;
    }

    /**
     * Updates a reply's content with validation.
     *
     * @param replyId    identifier of the reply
     * @param newContent replacement content
     * @return true when successful
     */
    public boolean updateReply(int replyId, String newContent) {
        Reply reply = findReplyById(replyId);
        if (reply == null) {
            System.out.println("Error: Reply ID " + replyId + " not found.");
            return false;
        }
        try {
            reply.updateReply(newContent);
            return true;
        } catch (InvalidInputException e) {
            System.out.println("Error updating reply: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a reply by id.
     *
     * @param replyId identifier of the reply to delete
     * @return true when a reply was removed
     */
    public boolean deleteReply(int replyId) {
        Iterator<Reply> iterator = replies.iterator();
        while (iterator.hasNext()) {
            Reply reply = iterator.next();
            if (reply.getId() == replyId) {
                iterator.remove();
                return true;
            }
        }
        System.out.println("Error: Reply ID " + replyId + " not found.");
        return false;
    }

    private Reply findReplyById(int replyId) {
        for (Reply reply : replies) {
            if (reply.getId() == replyId) {
                return reply;
            }
        }
        return null;
    }
}
