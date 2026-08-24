package com.geofort.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests the contact-message model.
 */
final class ContactMessageTest {

    /**
     * Verifies that contact-message values are stored.
     */
    @Test
    void shouldStoreContactMessageValues() {

        ContactMessage contactMessage =
                new ContactMessage(
                        "George",
                        "george@example.com",
                        "Test message");

        assertEquals(
                "George",
                contactMessage.name());

        assertEquals(
                "george@example.com",
                contactMessage.email());

        assertEquals(
                "Test message",
                contactMessage.message());
    }
}
