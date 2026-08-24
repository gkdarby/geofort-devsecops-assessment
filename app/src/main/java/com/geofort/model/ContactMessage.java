package com.geofort.model;

/**
 * Represents a message submitted through the contact form.
 *
 * @param name name of the sender
 * @param email email address of the sender
 * @param message message submitted by the sender
 */
public record ContactMessage(
        String name,
        String email,
        String message) {
}
