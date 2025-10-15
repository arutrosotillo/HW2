/**
 * CSE360 HW2
 * Author: Arturo Sotillo Barraca
 * Description: Implements CRUD and input validation for the Student Discussion System.
 * This file demonstrates compliance with the Foundations F25 structure and documentation format.
 */
package edu.asu.cse360.hw2;

/**
 * Custom exception used to signal invalid user input detected during CRUD operations.
 */
public class InvalidInputException extends Exception {

    /**
     * Creates a new exception with the provided validation message.
     *
     * @param message description of the validation failure
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
