package com.geofort.controller;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * Tests the contact servlet.
 */
final class ContactServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher dispatcher;
    private Connection connection;
    private PreparedStatement statement;
    private ContactServlet servlet;

    /**
     * Creates test objects before each test.
     *
     * @throws Exception when test setup fails
     */
    @BeforeEach
    void setUp() throws Exception {

        request = Mockito.mock(HttpServletRequest.class);
        response = Mockito.mock(HttpServletResponse.class);
        dispatcher = Mockito.mock(RequestDispatcher.class);
        connection = Mockito.mock(Connection.class);
        statement = Mockito.mock(PreparedStatement.class);

        servlet = Mockito.spy(new ContactServlet());

        doReturn(connection)
                .when(servlet)
                .createConnection();

        when(request.getRequestDispatcher(
                "/WEB-INF/views/contact.jsp"))
                .thenReturn(dispatcher);
    }

    /**
     * Verifies that the contact page is forwarded correctly.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldForwardToContactPage() throws Exception {

        servlet.doGet(request, response);

        verify(dispatcher).forward(request, response);
    }

    /**
     * Verifies successful contact-form submission.
     *
     * @throws Exception when servlet processing fails
     */
    @Test
    void shouldSaveContactMessage() throws Exception {

        when(request.getParameter("name"))
                .thenReturn("George");

        when(request.getParameter("email"))
                .thenReturn("george@example.com");

        when(request.getParameter("message"))
                .thenReturn("Test message");

        when(connection.prepareStatement(
                Mockito.anyString()))
                .thenReturn(statement);

        servlet.doPost(request, response);

        verify(statement).setString(
                1,
                "George");

        verify(statement).setString(
                2,
                "george@example.com");

        verify(statement).setString(
                3,
                "Test message");

        verify(statement).executeUpdate();

        verify(request).setAttribute(
                "successMessage",
                "Your message was submitted successfully.");

        verify(dispatcher).forward(request, response);
    }

    /**
     * Verifies database errors are converted to servlet errors.
     *
     * @throws Exception when test setup fails
     */
    @Test
    void shouldThrowServletExceptionOnDatabaseError()
            throws Exception {

        when(request.getParameter("name"))
                .thenReturn("George");

        when(request.getParameter("email"))
                .thenReturn("george@example.com");

        when(request.getParameter("message"))
                .thenReturn("Test message");

        when(connection.prepareStatement(
                Mockito.anyString()))
                .thenThrow(new SQLException(
                        "Database unavailable"));

        assertThrows(
                ServletException.class,
                () -> servlet.doPost(
                        request,
                        response));
    }
}
