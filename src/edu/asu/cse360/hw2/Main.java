/**
 * CSE360 HW2
 * Author: Arturo Sotillo Barraca
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

import java.util.List;

/**
 * Console driver that demonstrates CRUD functionality and validation scenarios.
 */
public class Main {

    public static void main(String[] args) {
        PostsCollection postsCollection = new PostsCollection();
        RepliesCollection repliesCollection = new RepliesCollection(postsCollection);

        System.out.println("--- CREATE POSTS ---");
        try {
            postsCollection.createPost("Alice", "Welcome to CSE360", "Let's discuss homework tips.");
            postsCollection.createPost("Bob", "Project Ideas", "Share your project inspirations here.");
        } catch (InvalidInputException e) {
            System.out.println("Unexpected validation error: " + e.getMessage());
        }

        System.out.println("\n--- READ ALL POSTS ---");
        List<Post> posts = postsCollection.readAllPosts();
        for (Post post : posts) {
            System.out.println(post.read());
            System.out.println();
        }

        System.out.println("--- UPDATE POST #1 ---");
        boolean updateResult = postsCollection.updatePost(1, "Updated Welcome", "Share resources and study tips here.");
        System.out.println("Update success: " + updateResult);
        System.out.println(postsCollection.readPostById(1).read());

        System.out.println("\n--- DELETE POST #2 ---");
        boolean deleteResult = postsCollection.deletePost(2);
        System.out.println("Delete success: " + deleteResult);

        System.out.println("\n--- CREATE REPLIES ---");
        try {
            repliesCollection.createReply(1, "Charlie", "Thanks for the update!");
            repliesCollection.createReply(1, "Dana", "Looking forward to collaborating.");
            repliesCollection.createReply(99, "Eve", "This should fail because the post is missing.");
        } catch (InvalidInputException e) {
            System.out.println("Unexpected validation error: " + e.getMessage());
        }
        List<Reply> replies = repliesCollection.readRepliesForPost(1);
        for (Reply reply : replies) {
            System.out.println(reply.read());
            System.out.println();
        }

        System.out.println("--- UPDATE REPLY #1 ---");
        boolean replyUpdate = repliesCollection.updateReply(1, "Appreciate the resources shared.");
        System.out.println("Reply update success: " + replyUpdate);
        System.out.println(repliesCollection.readRepliesForPost(1).get(0).read());

        System.out.println("\n--- DELETE REPLY #2 ---");
        boolean replyDelete = repliesCollection.deleteReply(2);
        System.out.println("Reply delete success: " + replyDelete);

        System.out.println("\n--- INPUT VALIDATION ERROR (empty title) ---");
        try {
            postsCollection.createPost("Frank", "", "This should not be created.");
        } catch (InvalidInputException e) {
            System.out.println("Validation message: " + e.getMessage());
        }

        System.out.println("\n--- INVALID UPDATE OPERATIONS ---");
        postsCollection.updatePost(42, "Missing Post", "No update should happen.");
        repliesCollection.updateReply(42, "No reply exists.");

        System.out.println("\nDemo complete.");
    }
}
